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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;

import egovframework.com.cmm.LoginVO;
import jakarta.servlet.http.HttpSession;

/**
 * WebSocket 핸드셰이크 시 HttpSession의 LoginVO 검증
 */
public class UsersServerHandshakeInterceptor implements HandshakeInterceptor {

	private static final Logger LOGGER = LoggerFactory.getLogger(UsersServerHandshakeInterceptor.class);
	private static final String SESSION_ATTR_LOGIN_VO = "loginVO";

	/**
	 * 핸드셰이크에 필요한 로그인 세션 여부를 검증한다.
	 */
	public static void assertMessengerHttpSessionAuthenticated(HttpSession httpSession) {
		if (httpSession == null) {
			LOGGER.warn("WebSocket handshake rejected: HttpSession is null.");
			throw new SecurityException("WebSocket handshake rejected: no HTTP session.");
		}
		Object loginAttr = httpSession.getAttribute(SESSION_ATTR_LOGIN_VO);
		if (!(loginAttr instanceof LoginVO)) {
			LOGGER.warn("WebSocket handshake rejected: loginVO missing or invalid in HttpSession.");
			throw new SecurityException("WebSocket handshake rejected: user not authenticated.");
		}
	}

	@Override
	public boolean beforeHandshake(@NonNull ServerHttpRequest request, @NonNull ServerHttpResponse response,
			@NonNull WebSocketHandler wsHandler, @NonNull Map<String, Object> attributes) throws Exception {
		if (!(request instanceof ServletServerHttpRequest servletRequest)) {
			LOGGER.warn("WebSocket handshake rejected: ServerHttpRequest is not servlet-based.");
			throw new SecurityException("WebSocket handshake rejected: unsupported request type.");
		}
		HttpSession session = servletRequest.getServletRequest().getSession(false);
		assertMessengerHttpSessionAuthenticated(session);
		return true;
	}

	@Override
	public void afterHandshake(@NonNull ServerHttpRequest request, @NonNull ServerHttpResponse response,
			@NonNull WebSocketHandler wsHandler, @Nullable Exception exception) {
		// no-op
	}
}
