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
package egovframework.com.ext.msg.web;

import egovframework.com.cmm.annotation.IncludedInfo;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @Class Name : EgovWebSocketMessengerController.java
 * @Description : 웹소켓 메신저 메인화면을 나타내기 위한 컨트롤러
 * @Modification Information
 *
 *    수정일       수정자         수정내용
 *    -------        -------     -------------------
 *    2014. 11. 27.    이영지
 *
 */
@Controller
public class EgovWebSocketMessengerController {

	/**
	 * 웹소켓 메신저 접속화면으로 이동한다.
	 * @param session 사용자세션
	 * @param model 모델
	 * @return view name
	 */
	@IncludedInfo(name="웹소켓 메신저", order = 3200, gid = 100)
	@RequestMapping(value="/cop/msg/websocketMessengerView.do")
	public String websocketMessengerView(HttpSession session, ModelMap model) {
		model.addAttribute("loginVO", session.getAttribute("loginVO"));
		return "egovframework/com/ext/msg/EgovMessenger";
	}

	/**
	 * 웹 소켓 메신저 메인화면(대화상대 리스트화면)으로 이동한다.
	 * @param session 사용자세션
	 * @param model 모델
	 * @return view name
	 */
	@RequestMapping(value="/cop/msg/websocketMessengerMain.do")
	public String websocketMessengerMain(HttpSession session, ModelMap model) {
		model.addAttribute("loginVO", session.getAttribute("loginVO"));
		return "egovframework/com/ext/msg/EgovMessengerMain";
	}

	/**
	 * 대화창을 새로 띄운다.
	 * @param roomId 대화창 아이디
	 * @param username 대화상대 이름
	 * @param session 사용자세션
	 * @param model 모델
	 * @return view name
	 */
	@RequestMapping(value="/cop/msg/websocketMessengePopup.do")
	public String websocketMessengePopup(@RequestParam(value="roomId") String roomId,
										 @RequestParam(value="username") String username,
										 HttpSession session, ModelMap model) {
		model.addAttribute("loginVO", session.getAttribute("loginVO"));
		model.addAttribute("roomId", roomId);
		model.addAttribute("username", username);
		return "egovframework/com/ext/msg/popup/chatPopupBubble";
	}
}
