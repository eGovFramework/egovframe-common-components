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
package egovframework.com.ext.msg.server.model.encoder;

import java.util.Set;

import egovframework.com.ext.msg.server.model.ChatMessage;
import egovframework.com.ext.msg.server.model.Message;
import egovframework.com.ext.msg.server.model.UsersMessage;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.websocket.EncodeException;
import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;


/**
* @Class Name : MessageEncoder.java
* @Description : 서버에서 클라이언트로 전달되는 메시지를 encoding하는 클래스
* @Modification Information
*
*    수정일       수정자         수정내용
*    -------        -------     -------------------
*    2014. 11. 27.    이영지
*
*/
public class MessageEncoder implements Encoder.Text<Message>{

	@Override
	public void destroy() {
	}

	@Override
	public void init(EndpointConfig arg0) {
	}

	/**
	 * 서버에서 클라이언트로 전달되는 메시지를 encoding하는 함수
	 */
	@Override
	public String encode(Message message) throws EncodeException {
		String result = null;
		if (message instanceof ChatMessage) {
			 ChatMessage chatMessage = (ChatMessage) message;
			 result = Json.createObjectBuilder().add("messageType", chatMessage.getClass().getSimpleName())
					 .add("name", chatMessage.getName())
					 .add("message", chatMessage.getMessage())
					 .build().toString();
		} else if (message instanceof UsersMessage) {
			UsersMessage userMessage = (UsersMessage) message;
			result = buildJsonUserData(userMessage.getUsers(), userMessage.getClass().getSimpleName());
		}
		return result;
	}

	private String buildJsonUserData(Set<String> set, String messageType) {
		JsonArrayBuilder jsonArrayBuilder = Json.createArrayBuilder();

		for (String user: set) {
			jsonArrayBuilder.add(user);
		}
		return Json.createObjectBuilder().add("messageType", messageType)
										 .add("users", jsonArrayBuilder)
										 .build().toString();
	}

}
