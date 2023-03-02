/*
 * Copyright 2014 the original author or authors.
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
 * Signs the user in by setting the currentUser property on the {@link SecurityContext}.
 * Remembers the sign-in after the current request completes by storing the user's id in a cookie.
 * This is cookie is read in {@link UserInterceptor#preHandle(HttpServletRequest, HttpServletResponse, Object)} on subsequent requests.
 * @author Keith Donald
 * @see UserInterceptor
 *
 */
package egovframework.com.uss.ion.fbk.web;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.web.SignInAdapter;
import org.springframework.web.context.request.NativeWebRequest;

import egovframework.com.uss.ion.fbk.service.FacebookSecurityContext;
import egovframework.com.uss.ion.fbk.service.FacebookUser;

/**
 * facebook 연동 로그인 클래스
 * @author 표준프레임워크센터
 * @since 2014.11.10
 * @version 1.0
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일     	수정자          		      수정내용
 *  -----------    --------------------    ---------------------------
 *  2014.11.10		표준프레임워크센터		      최초 생성
 *  2022.11.11   	김혜준			  			  시큐어코딩 처리
 *  
 *  </pre>
 */

public final class FacebookSimpleSignInAdapter implements SignInAdapter {

	@Override
	public String signIn(String userId, Connection<?> connection, NativeWebRequest request) {
		String result = "";
		// 2022.11.11 시큐어코딩 처리
		if (StringUtils.isNotEmpty(userId))  {
			FacebookUserCookieGenerator userCookieGenerator = new FacebookUserCookieGenerator();
			FacebookSecurityContext.setCurrentUser(new FacebookUser(userId));
			HttpServletResponse response = request.getNativeResponse(HttpServletResponse.class);
			if (response != null) {
				userCookieGenerator.addCookie(userId, response);
				result = "success";
			} else {
				result = "failure";
			}
		} else {
			result = "failure";
		}
		return result;
	}

}
