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
package egovframework.com.ext.msg.server.config;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import egovframework.com.cmm.LoginVO;
import egovframework.com.ext.msg.server.ChatServerEndPoint;
import jakarta.servlet.http.HttpSession;
import jakarta.websocket.HandshakeResponse;
import jakarta.websocket.server.HandshakeRequest;
import jakarta.websocket.server.ServerEndpointConfig;
import jakarta.websocket.server.ServerEndpointConfig.Configurator;

/**
 * 사용자리스트에서 다른사용자 선택 시, 사용자와 대화가능한 방(새로운 EndPoint 객체)을 만드는 Configurator
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
 *   2025.06.23  이백행          PMD로 소프트웨어 보안약점 진단하고 제거하기-FieldNamingConventions(필드 명명 규칙)
 *
 *      </pre>
 */
public class ChatServerAppConfig extends Configurator {

	/** WebSocket 세션 속성에 서버가 강제하는 인증 신원을 저장할 때 사용하는 키 */
	public static final String AUTHENTICATED_USERNAME_PROPERTY = "authUsername";

	// 대화창 서버객체(ChatServerEndPoint) 저장하는 Map
	// 2026.07.13 KISA 보안취약점 조치 - 컨테이너가 Configurator 인스턴스를 스레드 간 공유/재사용하므로
	// 동시 핸드셰이크에 안전하도록 스레드 안전한 Map으로 변경한다.
	private final static Map<String, ChatServerEndPoint> ENDPOINT_MAP = new ConcurrentHashMap<String, ChatServerEndPoint>();

	// 2026.07.13 KISA 보안취약점 조치 - 인스턴스 필드는 동시 핸드셰이크 간 값이 덮어써지는 경쟁조건을 유발하므로
	// 요청(스레드)별로 격리되는 ThreadLocal을 사용해 modifyHandshake와 getEndpointInstance 사이의 상태를 전달한다.
	private static final ThreadLocal<String> CURRENT_URI = new ThreadLocal<String>();

	@SuppressWarnings("unchecked")
	@Override
	public <T> T getEndpointInstance(Class<T> endpointClass) throws InstantiationException {

		String uri = CURRENT_URI.get();
		try {
			// computeIfAbsent로 check-then-put을 원자적으로 수행하여 손실 업데이트/중복 인스턴스 생성을 방지한다.
			ChatServerEndPoint endpoint = ENDPOINT_MAP.computeIfAbsent(uri, key -> new ChatServerEndPoint());
			return (T) endpoint;
		} finally {
			CURRENT_URI.remove();
		}
	}

	@Override
	public void modifyHandshake(ServerEndpointConfig sec, HandshakeRequest request, HandshakeResponse response) {
		// 인증되지 않은 사용자는 채팅방에 접속할 수 없도록 핸드셰이크 단계에서 차단하고,
		// 신원 사칭 방지를 위해 인증된 실제 로그인 계정을 세션 속성으로 강제 바인딩한다.
		Object sessionObj = request.getHttpSession();
		if (!(sessionObj instanceof HttpSession httpSession)) {
			throw new SecurityException("WebSocket handshake rejected: no HTTP session.");
		}
		Object loginAttr = httpSession.getAttribute("loginVO");
		if (!(loginAttr instanceof LoginVO loginVO) || loginVO.getName() == null) {
			throw new SecurityException("WebSocket handshake rejected: user not authenticated.");
		}
		// chatPopupBubble.jsp 등 채팅 화면이 loginVO.name 을 신원으로 사용하므로 동일하게 맞춘다.
		sec.getUserProperties().put(AUTHENTICATED_USERNAME_PROPERTY, loginVO.getName());

		CURRENT_URI.set(request.getRequestURI().toString());
		super.modifyHandshake(sec, request, response);
	}
}
