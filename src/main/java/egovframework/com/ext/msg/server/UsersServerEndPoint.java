/*
 * eGovFrame Web Messager
 * Copyright The eGovFrame Open Community (http://open.egovframe.go.kr)).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * @author 이영지(슈퍼개발자K3)
 */
package egovframework.com.ext.msg.server;

import java.io.IOException;
import java.io.StringReader;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import egovframework.com.cmm.EgovWebUtil;
import egovframework.com.ext.msg.server.config.UsersServerEndpointConfigurator;
import jakarta.websocket.EndpointConfig;
import jakarta.json.Json;
import jakarta.json.JsonArrayBuilder;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;
import jakarta.websocket.EncodeException;
import jakarta.websocket.OnClose;
import jakarta.websocket.OnMessage;
import jakarta.websocket.OnOpen;
import jakarta.websocket.Session;
import jakarta.websocket.server.ServerEndpoint;

/**
 * 현재 가능한 대화사용자 리스트를 처리하는 WebSocket 서버클래스
 * 
 * @author 이영지
 * @since 2014.11.27
 * @version 1.0
 * @see
 *
 *      <pre>
 *  == 개정이력(Modification Information) ==
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2014.11.27  이영지          최초 생성
 *   2020.11.02  신용호          KISA 보안약점 조치 (Random Seed값 추가)
 *   2023.06.09  김장하          NSR 보안조치 (사용자이름 크로스사이트 스크립트 방지)
 *   2025.06.23  이백행          PMD로 소프트웨어 보안약점 진단하고 제거하기-CloseResource(리소스 닫기), EmptyControlStatement(빈 제어문), UnnecessarySemicolon(불필요한 세미콜론)
 *   2026.05.11  유지보수        WebSocket 핸드셰이크 시 HttpSession의 LoginVO 검증(UsersServerEndpointConfigurator)
 *
 *      </pre>
 */
@ServerEndpoint(value = "/usersServerEndpoint", configurator = UsersServerEndpointConfigurator.class)
public class UsersServerEndPoint {

	private static final Logger LOGGER = LoggerFactory.getLogger(UsersServerEndPoint.class);
	private static Set<Session> connectedAllUsers = Collections.synchronizedSet(new HashSet<Session>());

	/**
	 *
	 * Handshaking 함수
	 *
	 * @param userSession 사용자 session
	 * @param config      핸드셰이크(UsersServerEndpointConfigurator)에서 바인딩한 인증 신원을 담은 설정
	 */
	@OnOpen
	public void handleOpen(Session userSession, EndpointConfig config) {
		// 핸드셰이크 시 서버가 검증한 실제 로그인 계정을 세션 신원으로 강제 바인딩한다.
		// (클라이언트가 이후 메시지로 보내는 username은 신뢰하지 않는다 - 신원 사칭 방지)
		Object authUsername = config.getUserProperties().get(UsersServerEndpointConfigurator.AUTHENTICATED_USERNAME_PROPERTY);
		if (authUsername instanceof String) {
			userSession.getUserProperties().put("username", authUsername);
		}
		connectedAllUsers.add(userSession);
	}

	/**
	 * Message전달 함수
	 * 
	 * @param message     메시지
	 * @param userSession 사용자 session
	 * @throws IOException
	 * @throws EncodeException
	 */
	@OnMessage
	public void handleMessage(String message, Session userSession) throws EncodeException {
		String username = (String) userSession.getUserProperties().get("username");

		try (JsonReader jsonReader = Json.createReader(new StringReader(message));) {// 2022.01 Resources should be closed

			JsonObject jsonObject = jsonReader.readObject();

			String connectionType = jsonObject.getString("connectionType");

			if ("firstConnection".equals(connectionType)) {
				// 신원(username)은 클라이언트가 보낸 값을 신뢰하지 않고 @OnOpen에서 핸드셰이크 인증 정보로
				// 이미 서버가 강제 바인딩해 두었다(신원 사칭 방지). 클라이언트가 보낸 username 필드는 사용하지 않는다.

				// 로그 위조(CRLF Injection) 방지 - 사용자 입력에 포함된 개행 문자를 제거한 뒤 기록한다.
				LOGGER.info("{} is entered.", username == null ? null : username.replaceAll("[\\r\\n]", ""));

				if (username != null && !isExisted(username, userSession)) {
					// 사용자 목록 payload는 한 번만 생성하고, 공유 락은 세션 스냅샷에만 보유한다.
					// 실제 sendText(네트워크 I/O)는 락 밖에서 수행해 전송 지연이 다른 작업을 막지 않도록 한다.
					String userListPayload = buildJsonUserData(getUsers());
					broadcast(userListPayload);
				} else {
					if (LOGGER.isDebugEnabled()) {
						LOGGER.debug("username을 다시 입력하게하는 로직 넣기.");
					}
				}

			} else if ("chatConnection".equals(connectionType)) {
				// chatroomId로 또다른 webSocket url에 접근한다.
				// id generation으로 대체가능.
				String chatroomId = genRandom();

				// 다른 사용자와 대화하고자 시도할 때
				// 채팅룸 사용자 저장
				Set<Session> chatroomMembers = new HashSet<Session>();
				chatroomMembers.add(userSession);

				// 선택한 사용자를 사용자들 안에서 찾기.
				String connectingUser = EgovWebUtil.clearXSSMaximum(jsonObject.getString("connectingUser"));

				if (connectingUser != null && !username.equals(connectingUser)) {
					// 사용자들 중 선택한 유저와 연결
					synchronized (connectedAllUsers) {
						for (Session session : connectedAllUsers) {// NOPMD - CloseResource
							if (connectingUser.equals(session.getUserProperties().get("username"))) {
								// 선택한 사용자면 chatroomMember로 추가.
								chatroomMembers.add(session);
							}
						}
					}

					// chatroomMembers에게 room입장하라는 신호 보내기
					for (Session session : chatroomMembers) {// NOPMD - CloseResource

						session.getBasicRemote()
								.sendText(Json.createObjectBuilder().add("enterChatId", chatroomId)
										.add("username", (String) session.getUserProperties().get("username")).build()
										.toString());
					}
				}
			}

		} catch (IOException ioe) {
			LOGGER.error("UsersServerEndPoint IOException", ioe);
		} catch (Exception e) {
			LOGGER.error("UsersServerEndPoint Exception", e);
		}

	}

	/**
	 * 연결을 끊기 직전에 호출되는 함수
	 * 
	 * @param userSession
	 * @throws IOException
	 * @throws EncodeException
	 */
	// 예외처리 필요!
	@OnClose
	public void handleClose(Session userSession) throws IOException, EncodeException {

		String disconnectedUser = (String) userSession.getUserProperties().get("username");
		connectedAllUsers.remove(userSession);

		if (disconnectedUser != null) {
			// payload는 한 번만 생성하고, 공유 락은 세션 스냅샷에만 보유한다(전송은 락 밖).
			String payload = Json.createObjectBuilder().add("disconnectedUser", disconnectedUser).build().toString();
			broadcast(payload);
		}
	}

	/**
	 * 접속 중인 모든 사용자에게 동일한 메시지를 전송한다.
	 * 공유 컬렉션 락은 세션 목록 스냅샷에만 보유하고, 네트워크 I/O(sendText)는 락 밖에서 수행한다.
	 * 특정 세션 전송 실패가 나머지 전송을 막지 않도록 세션별로 예외를 처리한다.
	 *
	 * @param payload 전송할 메시지(JSON 문자열)
	 */
	private void broadcast(String payload) {
		List<Session> snapshot;
		synchronized (connectedAllUsers) {
			snapshot = new ArrayList<>(connectedAllUsers);
		}
		for (Session session : snapshot) {
			try {
				session.getBasicRemote().sendText(payload);
			} catch (IOException e) {
				LOGGER.warn("UsersServerEndPoint broadcast 전송 실패: {}", e.getMessage());
			}
		}
	}

	/**
	 * 연결되어있는 user정보를 가져오는 함수
	 * 
	 * @return user set
	 */
	private Set<String> getUsers() {
		HashSet<String> returnSet = new HashSet<String>();

		synchronized (connectedAllUsers) {
			for (Session session : connectedAllUsers) { // NOPMD - CloseResource
				if (session.getUserProperties().get("username") != null) {
					returnSet.add(session.getUserProperties().get("username").toString());
				}
			}
		}
		return returnSet;
	}

	/**
	 * 유저 정보가 담긴 Set<String>을 json으로 변환해주는 함수
	 * 
	 * @param set
	 * @return jsondata
	 */
	private String buildJsonUserData(Set<String> set) {

		JsonArrayBuilder jsonArrayBuilder = Json.createArrayBuilder();

		for (String user : set) {
			jsonArrayBuilder.add(user);
		}
		return Json.createObjectBuilder().add("allUsers", jsonArrayBuilder).build().toString();
	}

	/**
	 * 동일한 username을 가진 다른 user session이 있는지 확인하는 함수
	 * (username은 @OnOpen에서 이미 현재 세션에 바인딩되어 있으므로 자기 자신은 제외하고 검사한다)
	 *
	 * @param username 사용자이름
	 * @param self     현재 세션(중복 검사에서 제외)
	 * @return 존재여부
	 */
	private boolean isExisted(String username, Session self) {
		synchronized (connectedAllUsers) {
			for (Session existedUser : connectedAllUsers) {// NOPMD - CloseResource
				if (existedUser != self && username.equals(existedUser.getUserProperties().get("username"))) {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * chatroomId를 위한 랜덤값을 생성하는 함수
	 * 
	 * @return chatroomId
	 */
	private String genRandom() {
		String chatroomId = "";
		SecureRandom rnd = new SecureRandom(); // 221115 김혜준 2022 시큐어코딩 조치
		for (int i = 0; i < 8; i++) {
			chatroomId += (char) ((rnd.nextDouble() * 26) + 97);// KISA 보안약점 조치 (2018-10-29, 윤창원)
		}
		return chatroomId;
	}

}
