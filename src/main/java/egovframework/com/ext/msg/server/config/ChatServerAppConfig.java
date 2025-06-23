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

import java.util.HashMap;
import java.util.Map;

import javax.websocket.HandshakeResponse;
import javax.websocket.server.HandshakeRequest;
import javax.websocket.server.ServerEndpointConfig;
import javax.websocket.server.ServerEndpointConfig.Configurator;

import egovframework.com.ext.msg.server.ChatServerEndPoint;

/**
 * 사용자리스트에서 다른사용자 선택 시, 사용자와 대화가능한 방(새로운 EndPoint 객체)을 만드는 Configurator
 * 
 * @author 공통컴포넌트 개발팀 홍길동
 * @since 2009.06.01
 * @version 1.0
 * @see
 *
 *      <pre>
 *  == 개정이력(Modification Information) ==
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2009.03.20  홍길동          최초 생성
 *   2014.11.27  이영지          최초 생성
 *   2025.06.23  이백행          PMD로 소프트웨어 보안약점 진단하고 제거하기-FieldNamingConventions(필드 명명 규칙)
 *
 *      </pre>
 */
public class ChatServerAppConfig extends Configurator {

	// 대화창 서버객체(ChatServerEndPoint) 저장하는 Map
	private final static Map<String, ChatServerEndPoint> ENDPOINT_MAP = new HashMap<String, ChatServerEndPoint>();
	private String currentUri;

	@SuppressWarnings("unchecked")
	@Override
	public <T> T getEndpointInstance(Class<T> endpointClass) throws InstantiationException {

		ChatServerEndPoint endpoint = ENDPOINT_MAP.get(currentUri);

		if (endpoint == null) {
			endpoint = new ChatServerEndPoint();
			ENDPOINT_MAP.put(currentUri, endpoint);
		}

		return (T) endpoint;
	}

	@Override
	public void modifyHandshake(ServerEndpointConfig sec, HandshakeRequest request, HandshakeResponse response) {
		currentUri = request.getRequestURI().toString();
		super.modifyHandshake(sec, request, response);
	}
}
