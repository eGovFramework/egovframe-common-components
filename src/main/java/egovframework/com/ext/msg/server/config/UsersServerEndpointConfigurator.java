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

import jakarta.servlet.http.HttpSession;
import jakarta.websocket.HandshakeResponse;
import jakarta.websocket.server.HandshakeRequest;
import jakarta.websocket.server.ServerEndpointConfig;

/**
 * 핸드셰이크에서 UsersServerHandshakeInterceptor로 HttpSession의 LoginVO 검증
 */
public class UsersServerEndpointConfigurator extends ServerAppConfig {

	@Override
	public void modifyHandshake(ServerEndpointConfig sec, HandshakeRequest request, HandshakeResponse response) {
		Object sessionObj = request.getHttpSession();
		if (!(sessionObj instanceof HttpSession)) {
			UsersServerHandshakeInterceptor.assertMessengerHttpSessionAuthenticated(null);
		} else {
			HttpSession httpSession = (HttpSession) sessionObj;
			UsersServerHandshakeInterceptor.assertMessengerHttpSessionAuthenticated(httpSession);
		}
		super.modifyHandshake(sec, request, response);
	}
}
