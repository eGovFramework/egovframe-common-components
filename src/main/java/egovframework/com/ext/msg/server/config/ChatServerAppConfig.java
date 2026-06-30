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

import egovframework.com.ext.msg.server.ChatServerEndPoint;
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
 *   2026.06.30  z3rotig4r      getEndpointInstance 무상태화(currentUri/ENDPOINT_MAP 공유상태 제거로 동시 핸드셰이크 race 해소)
 *
 *      </pre>
 */
public class ChatServerAppConfig extends Configurator {

	@SuppressWarnings("unchecked")
	@Override
	public <T> T getEndpointInstance(Class<T> endpointClass) throws InstantiationException {
		// 연결마다 새 인스턴스를 반환하며, 방 그룹핑은 @PathParam을 통해 ChatServerEndPoint에서 처리한다.
		return (T) new ChatServerEndPoint();
	}
}
