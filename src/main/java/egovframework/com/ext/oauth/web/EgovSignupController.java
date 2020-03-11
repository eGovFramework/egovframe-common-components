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

import javax.annotation.Resource;
import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.UserProfile;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.social.connect.web.ProviderSignInUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.WebRequest;

import egovframework.com.ext.oauth.service.EgovSignupService;
import egovframework.com.ext.oauth.service.OAuthConfig;
import egovframework.com.ext.oauth.service.OAuthLogin;
import egovframework.com.ext.oauth.service.OAuthUniversalUser;
import egovframework.com.ext.oauth.service.OAuthVO;
import egovframework.com.utl.fcc.service.EgovStringUtil;

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
 *  2014.10.08		이기하          최초 생성
 *  2018.10.02		신용호          Facebook 관련 ProviderSignInUtils 초기화 수정
 *  </pre>
 */

@Controller
public class EgovSignupController {

	private static final Logger LOGGER = LoggerFactory.getLogger(EgovSignupController.class);
	
	@Resource(name="signupService")
	private EgovSignupService signupService;

    //private ConnectionRepository connectionRepository;
	private final ProviderSignInUtils providerSignInUtils;

	@Inject
	private OAuthVO naverAuthVO;
	
	@Inject
	private OAuthVO googleAuthVO;

	@Inject
	private OAuthVO kakaoAuthVO;
	
	@Inject
	public EgovSignupController(ConnectionFactoryLocator connectionFactoryLocator,UsersConnectionRepository connectionRepository) {
		//this.providerSignInUtils = new ProviderSignInUtils();
		this.providerSignInUtils = new ProviderSignInUtils(connectionFactoryLocator, connectionRepository);
	}

	@RequestMapping(value="/signup", method=RequestMethod.GET)
	public String signupForm(WebRequest request) throws Exception {
		Connection<?> connection = providerSignInUtils.getConnectionFromSession(request);
		if (connection != null) {
			UserProfile profile = connection.fetchUserProfile();

			String key =  EgovStringUtil.remove(connection.getKey().toString(), ':');
			String account = signupService.signup(profile, request, key);
			if (account != null) {
				providerSignInUtils.doPostSignUp(key, request);
				return "redirect:/";
			}
		}
		return "redirect:/";
	}
	
	@RequestMapping(value = "/uat/uia/oauthLoginUsr", method = RequestMethod.GET)
	public String login(Model model) throws Exception {
		LOGGER.debug("===>>> OAuth Login .....");
		
		OAuthLogin naverLogin = new OAuthLogin(naverAuthVO);
		LOGGER.debug("naverLogin.getOAuthURL() = "+naverLogin.getOAuthURL());
		model.addAttribute("naver_url", naverLogin.getOAuthURL());
		
		OAuthLogin googleLogin = new OAuthLogin(googleAuthVO);
		LOGGER.debug("googleLogin.getOAuthURL() = "+googleLogin.getOAuthURL());
		model.addAttribute("google_url", googleLogin.getOAuthURL());

		OAuthLogin kakaoLogin = new OAuthLogin(kakaoAuthVO);
		LOGGER.debug("kakaoLogin.getOAuthURL() = "+kakaoLogin.getOAuthURL());
		model.addAttribute("kakao_url", kakaoLogin.getOAuthURL());
		
		return "egovframework/com/uat/uia/EgovLoginUsrOauth";
	}

	@RequestMapping(value = "/auth/{oauthService}/callback", 
			method = { RequestMethod.GET, RequestMethod.POST})
	public String oauthLoginCallback(@PathVariable String oauthService,
			Model model, @RequestParam String code, HttpSession session) throws Exception {
		
		LOGGER.debug("oauthLoginCallback: service={}", oauthService);
		LOGGER.debug("===>>> code = "+ code);
		OAuthVO oauthVO = null;
		if (StringUtils.equals(OAuthConfig.GOOGLE_SERVICE_NAME, oauthService))
			oauthVO = googleAuthVO;
		else if (StringUtils.equals(OAuthConfig.NAVER_SERVICE_NAME, oauthService))
			oauthVO = naverAuthVO;
		else
			oauthVO = kakaoAuthVO;
		
		// 1. code를 이용해서 Access Token 받기
		// 2. Access Token을 이용해서 사용자 제공정보 가져오기
		OAuthLogin oauthLogin = new OAuthLogin(oauthVO);
		
		OAuthUniversalUser oauthUser = oauthLogin.getUserProfile(code); // 1,2번 동시
		LOGGER.debug("Profile ===>>" + oauthUser);
		
		// ========================================================================
		// 다음 부분은 업무의 목적에 맞게 커스텀 코드를 작성한다.
		// 3. 해당 유저가 DB에 존재하는지 체크 (google, naver, kakao에서 전달받은 ID가 존재하는지 체크)
		String resultDBInfo = ""; // DB 체크 결과
		
		if ( oauthUser == null || resultDBInfo == null) {
			// 미존재시 가입페이지로!!
			model.addAttribute("message", "This user does not exist. Please sign up.");
			
		} else {
			// 존재시 로그인 처리
			model.addAttribute("message", "OAuth Sign-in succeeded.");
			
		}
		
		return "egovframework/com/uat/uia/EgovLoginUsrOauthResult";
	}
	
}
