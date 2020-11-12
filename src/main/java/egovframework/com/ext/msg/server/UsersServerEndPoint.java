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
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.websocket.EncodeException;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Random;

/**
* @Class Name : UsersServerEndPoint.java
* @Description : 현재 가능한 대화사용자 리스트를 처리하는 WebSocket 서버클래스
* @Modification Information
*
*    수정일               수정자                수정내용
*    ----------   ---------    ---------------------------------
*    2014.11.27   이영지                
*    2020.11.02   신용호               KISA 보안약점 조치 (Random Seed값 추가)
*
*/
@ServerEndpoint(value = "/usersServerEndpoint"/* ,configurator=ServerAppConfig.class*/)
public class UsersServerEndPoint {
	private static final Logger LOGGER = LoggerFactory.getLogger(UsersServerEndPoint.class);
	private static Set<Session> connectedAllUsers = Collections.synchronizedSet(new HashSet<Session>());

	//Spring bean과 연동하기 위해서는 ServerAppConfig를 configurator로 등록해주면 된다.
	/*
	 * @Resource(name="TestService") TestService testService;
	 */

	/**
	 * Handshaking 함수
	 * @param userSession 사용자 session
	 */
	@OnOpen
	public void handleOpen(Session userSession) {
		connectedAllUsers.add(userSession);
	}

	/**
	 * Message전달 함수
	 * @param message 메시지
	 * @param userSession 사용자 session
	 * @throws IOException
	 * @throws EncodeException
	 */
	@OnMessage
	public void handleMessage(String message, Session userSession) throws IOException, EncodeException {
		String username = (String) userSession.getUserProperties().get("username");

		JsonObject jsonObject = Json.createReader(new StringReader(message)).readObject();

		String connectionType = jsonObject.getString("connectionType");

		if ("firstConnection".equals(connectionType) && username == null) {
			// 맨 처음 접속 시,
			// 사용자의 이름을 가져옴
			username = jsonObject.getString("username");

			LOGGER.info(username + " is entered.");

			if (username != null && !isExisted(username)) {
				userSession.getUserProperties().put("username", username);

				for (Session session : connectedAllUsers) {
					session.getBasicRemote().sendText(buildJsonUserData(getUsers()));
				}
			} else {
				// username을 다시 입력하게하는 로직 넣기.
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
			String connectingUser = jsonObject.getString("connectingUser");

			if (connectingUser != null && !username.equals(connectingUser)) {
				// 사용자들 중 선택한 유저와 연결
				for (Session session : connectedAllUsers) {
					if (connectingUser.equals(session.getUserProperties().get("username"))) {
						// 선택한 사용자면 chatroomMember로 추가.
						chatroomMembers.add(session);
					}
				}

				// chatroomMembers에게 room입장하라는 신호 보내기
				for (Session session : chatroomMembers) {

					session.getBasicRemote().sendText(
							Json.createObjectBuilder().add("enterChatId", chatroomId).add("username", (String) session.getUserProperties().get("username")).build().toString());
				}
			}
		}
	}

	/**
	 * 연결을 끊기 직전에 호출되는 함수
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
			Json.createObjectBuilder().add("disconnectedUser", disconnectedUser).build().toString();

			for (Session session : connectedAllUsers) {
				session.getBasicRemote().sendText(Json.createObjectBuilder().add("disconnectedUser", disconnectedUser).build().toString());
			}
		}
	}

	/**
	 * 연결되어있는 user정보를 가져오는 함수
	 * @return user set
	 */
	private Set<String> getUsers() {
		HashSet<String> returnSet = new HashSet<String>();

		for (Session session : connectedAllUsers) {
			if (session.getUserProperties().get("username") != null) {
				returnSet.add(session.getUserProperties().get("username").toString());
			};
		}
		return returnSet;
	}

	/**
	 * 유저 정보가 담긴 Set<String>을 json으로 변환해주는 함수
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
	 * 동일한 username을 가진 user session이 있는지 확인하는 함수
	 * @param username 사용자이름
	 * @return 존재여부
	 */
	private boolean isExisted(String username) {
		// 이미 username을 가진 session이 있는지 검사.
		for (Session existedUser : connectedAllUsers) {
			if (username.equals(existedUser.getUserProperties().get("username"))) {
				return true;
			}
		}
		return false;
	}

	/**
	 * chatroomId를 위한 랜덤값을 생성하는 함수
	 * @return chatroomId
	 */
	private String genRandom() {
		String chatroomId = "";
		Random rnd = new Random();
		for (int i = 0; i < 8; i++) {
			rnd.setSeed(System.currentTimeMillis());
			chatroomId += (char) ((rnd.nextDouble() * 26) + 97);//KISA 보안약점 조치 (2018-10-29, 윤창원)
		}
		return chatroomId;
	}

}
