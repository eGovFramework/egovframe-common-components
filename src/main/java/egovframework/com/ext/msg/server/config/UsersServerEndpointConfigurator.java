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

import egovframework.com.cmm.LoginVO;
import jakarta.servlet.http.HttpSession;
import jakarta.websocket.HandshakeResponse;
import jakarta.websocket.server.HandshakeRequest;
import jakarta.websocket.server.ServerEndpointConfig;

/**
 * 핸드셰이크에서 UsersServerHandshakeInterceptor로 HttpSession의 LoginVO 검증
 */
public class UsersServerEndpointConfigurator extends ServerAppConfig {

	/** WebSocket 세션 속성에 서버가 강제하는 인증 신원을 저장할 때 사용하는 키 */
	public static final String AUTHENTICATED_USERNAME_PROPERTY = "authUsername";

	@Override
	public void modifyHandshake(ServerEndpointConfig sec, HandshakeRequest request, HandshakeResponse response) {
		Object sessionObj = request.getHttpSession();
		if (!(sessionObj instanceof HttpSession)) {
			UsersServerHandshakeInterceptor.assertMessengerHttpSessionAuthenticated(null);
		} else {
			HttpSession httpSession = (HttpSession) sessionObj;
			UsersServerHandshakeInterceptor.assertMessengerHttpSessionAuthenticated(httpSession);

			// 클라이언트가 스스로 선언하는 값(firstConnection.username)을 신뢰하지 않도록,
			// 인증된 세션의 실제 로그인 사용자명(name)을 WebSocket 세션 속성으로 강제 바인딩한다.
			// (messenger 화면(EgovMessengerMain.jsp 등)은 loginVO.name 을 신원으로 사용하므로 동일하게 맞춘다)
			Object loginAttr = httpSession.getAttribute("loginVO");
			if (loginAttr instanceof LoginVO loginVO && loginVO.getName() != null) {
				sec.getUserProperties().put(AUTHENTICATED_USERNAME_PROPERTY, loginVO.getName());
			}
		}
		super.modifyHandshake(sec, request, response);
	}
}
