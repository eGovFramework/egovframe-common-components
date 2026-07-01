/*
 * eGovFrame OAuth
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
 * @author 이기하(슈퍼개발자K3)
 */
package egovframework.com.ext.oauth.web;

import java.security.SecureRandom;
import java.util.Base64;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import egovframework.com.ext.oauth.service.OAuthConfig;
import egovframework.com.ext.oauth.service.OAuthLogin;
import egovframework.com.ext.oauth.service.OAuthUniversalUser;
import egovframework.com.ext.oauth.service.OAuthVO;
import jakarta.servlet.http.HttpSession;

/**
 * 소셜 계정으로 일반회원 가입을 처리하는 컨트롤러 클래스
 * @author 이기하
 * @since 2014.10.08
 * @version 1.0
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일     	수정자          수정내용
 *  -----------    --------    ---------------------------
 *  2014.10.08		이기하		최초 생성
 *  2018.10.02		신용호		Facebook 관련 ProviderSignInUtils 초기화 수정
 *  2022.11.11      김혜준		시큐어코딩 처리
 *  2023.07.26		송인서		필요하지 않은 필드 값 교체 및 구조 단순화
 *  </pre>
 */

@Controller
public class EgovSignupController {

	private static final Logger LOGGER = LoggerFactory.getLogger(EgovSignupController.class);

	/** OAuth CSRF 방어용 state 값을 보관하는 세션 키 */
	private static final String OAUTH_STATE_SESSION_KEY = "oauthState";

	private static final SecureRandom SECURE_RANDOM = new SecureRandom();

	/** 예측 불가능한 OAuth state 값을 생성한다(URL-safe Base64). */
	private static String generateState() {
		byte[] bytes = new byte[32];
		SECURE_RANDOM.nextBytes(bytes);
		return Base64.getUrlEncoder().withoutPadding().encodeToString(bytes);
	}

	@Autowired
	private OAuthVO naverAuthVO;

	@Autowired
	private OAuthVO googleAuthVO;

	@Autowired
	private OAuthVO kakaoAuthVO;

	@RequestMapping(value = "/uat/uia/oauthLoginUsr", method = RequestMethod.GET)
	public String login(Model model, HttpSession session) throws Exception {
		LOGGER.debug("===>>> OAuth Login .....");

		// 콜백에서 대조할 CSRF 방어용 state를 1회 생성해 세션에 보관하고 모든 제공자 인가 URL에 부착한다.
		String state = generateState();
		session.setAttribute(OAUTH_STATE_SESSION_KEY, state);

		OAuthLogin naverLogin = new OAuthLogin(naverAuthVO);
		model.addAttribute("naver_url", naverLogin.getOAuthURL(state));

		OAuthLogin googleLogin = new OAuthLogin(googleAuthVO);
		model.addAttribute("google_url", googleLogin.getOAuthURL(state));

		OAuthLogin kakaoLogin = new OAuthLogin(kakaoAuthVO);
		model.addAttribute("kakao_url", kakaoLogin.getOAuthURL(state));

		return "egovframework/com/uat/uia/EgovLoginUsrOauth";
	}

	@RequestMapping(value = "/auth/{oauthService}/callback", method = { RequestMethod.GET, RequestMethod.POST })
	public String oauthLoginCallback(@PathVariable String oauthService, Model model,
			@RequestParam String code, @RequestParam(required = false) String state, HttpSession session) throws Exception {

		LOGGER.debug("oauthLoginCallback: service={}", oauthService);

		// CSRF(로그인 CSRF) 방어 - 콜백의 state를 세션에 보관한 값과 대조한다. 1회용이므로 검증 후 제거한다.
		String sessionState = (String) session.getAttribute(OAUTH_STATE_SESSION_KEY);
		session.removeAttribute(OAUTH_STATE_SESSION_KEY);
		if (sessionState == null || !sessionState.equals(state)) {
			LOGGER.warn("OAuth state가 일치하지 않아 콜백 요청을 차단합니다. service={}", oauthService);
			model.addAttribute("message", "Invalid OAuth state.");
			return "egovframework/com/uat/uia/EgovLoginUsrOauthResult";
		}

		OAuthVO oauthVO = null;
		if (StringUtils.equals(OAuthConfig.GOOGLE_SERVICE_NAME, oauthService)) {
			oauthVO = googleAuthVO;
		} else if (StringUtils.equals(OAuthConfig.NAVER_SERVICE_NAME, oauthService)) {
			oauthVO = naverAuthVO;
		} else {
			oauthVO = kakaoAuthVO;
		}

		// 1. code를 이용해서 Access Token 받기
		// 2. Access Token을 이용해서 사용자 제공정보 가져오기
		OAuthLogin oauthLogin = new OAuthLogin(oauthVO);

		OAuthUniversalUser oauthUser = oauthLogin.getUserProfile(code); // 1,2번 동시
		LOGGER.debug("Profile ===>>" + oauthUser);

		// ========================================================================
		// 다음 부분은 업무의 목적에 맞게 커스텀 코드를 작성한다.
		// 3. 해당 유저가 DB에 존재하는지 체크 (google, naver, kakao에서 전달받은 ID가 존재하는지 체크)
//		String resultDBInfo = ""; // DB 체크 결과

		// 2022.11.11 시큐어코딩 처리
		if (oauthUser == null) {
			// 미존재시 가입페이지로!!
			model.addAttribute("message", "This user does not exist. Please sign up.");
		} else {
			// 존재시 로그인 처리
			model.addAttribute("message", "OAuth Sign-in succeeded.");
		}

		return "egovframework/com/uat/uia/EgovLoginUsrOauthResult";
	}

}
