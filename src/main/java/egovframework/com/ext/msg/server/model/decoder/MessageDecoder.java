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
package egovframework.com.ext.msg.server.model.decoder;

import java.io.StringReader;

import javax.json.Json;
import javax.json.JsonException;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.websocket.DecodeException;
import javax.websocket.Decoder;
import javax.websocket.EndpointConfig;

import egovframework.com.ext.msg.server.model.ChatMessage;
import egovframework.com.ext.msg.server.model.Message;
import lombok.extern.slf4j.Slf4j;

/**
 * 클라이언트에서 서버로 전달되는 메시지를 decoding하는 클래스
 * 
 * @author 이영지
 * @since 2014.11.27
 * @version 3.9.0
 * @see
 *
 *      <pre>
 *  == 개정이력(Modification Information) ==
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2014.11.27  이영지          최초 생성
 *   2025.06.24  이백행          PMD로 소프트웨어 보안약점 진단하고 제거하기-UncommentedEmptyMethodBody(주석 처리되지 않은 빈 메서드 본문), CloseResource(리소스 닫기)
 *
 *      </pre>
 */
@Slf4j
public class MessageDecoder implements Decoder.Text<Message> {

	@Override
	public void init(EndpointConfig config) {
		// init 주석 추가
	}

	@Override
	public void destroy() {
		// destroy 로그 추가
	}

	/**
	 * 화면에서 넘어오는 데이터를 decoding하는 함수
	 */
	@Override
	public Message decode(String message) throws DecodeException {
		ChatMessage chatMessage = new ChatMessage();

		// 221111 김혜준 2022 시큐어코딩 조치
		JsonObject jsonObject = null;

		try (StringReader stringReader = new StringReader(message);
				JsonReader jsonReader = Json.createReader(stringReader);) {
			jsonObject = jsonReader.readObject();
			chatMessage.setMessage(jsonObject.getString("message"));
			chatMessage.setRoom(jsonObject.getString("room"));
		} catch (JsonException ex) {
			if (log.isErrorEnabled()) {
				log.error(ex.getMessage());
			}
		} finally {
			if (jsonObject != null) {
				jsonObject = null;
			}
		}

		return chatMessage;
	}

	@Override
	public boolean willDecode(String message) {
		boolean flag = true;

		try (JsonReader jsonReader = Json.createReader(new StringReader(message));) {
			jsonReader.readObject();
		} catch (JsonException ex) {// KISA 보안약점 조치 (2018-10-29, 윤창원)
			flag = false;
		} catch (Exception ex) {
			flag = false;
		}
		return flag;
	}

}
