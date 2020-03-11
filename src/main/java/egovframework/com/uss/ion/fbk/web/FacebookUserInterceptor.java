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
 * Before a request is handled:
 * 1. sets the current User in the {@link SecurityContext} from a cookie, if present and the user is still connected to Facebook.
 * 2. requires that the user sign-in if he or she hasn't already.
 * @author Keith Donald
 */
package egovframework.com.uss.ion.fbk.web;

import egovframework.com.uss.ion.fbk.service.FacebookSecurityContext;
import egovframework.com.uss.ion.fbk.service.FacebookUser;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.servlet.view.RedirectView;

/**
 * facebook 연결을 확인하는 인터셉터 클래스
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
 *  </pre>
 */

public final class FacebookUserInterceptor extends HandlerInterceptorAdapter {

	private final UsersConnectionRepository connectionRepository;

	private final FacebookUserCookieGenerator userCookieGenerator = new FacebookUserCookieGenerator();

	public FacebookUserInterceptor(UsersConnectionRepository connectionRepository) {
		this.connectionRepository = connectionRepository;
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		rememberUser(request, response);
		handleSignOut(request, response);
		if (FacebookSecurityContext.userSignedIn() || requestForSignIn(request)) {
			return true;
		} else {
			return requireSignIn(request, response);
		}
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
		FacebookSecurityContext.remove();
	}

	// internal helpers

	private void rememberUser(HttpServletRequest request, HttpServletResponse response) {
		String userId = userCookieGenerator.readCookieValue(request);
		if (userId == null || userId == "") {
			return;
		}
		if (!userNotFound(userId)) {
			userCookieGenerator.removeCookie(response);
			return;
		}
		FacebookSecurityContext.setCurrentUser(new FacebookUser(userId));
	}

	private void handleSignOut(HttpServletRequest request, HttpServletResponse response) {
		if (FacebookSecurityContext.userSignedIn() && request.getServletPath().startsWith("/uss/ion/fbk/facebookSignout.do")) {
			connectionRepository.createConnectionRepository(FacebookSecurityContext.getCurrentUser().getId()).removeConnections("facebook");
			userCookieGenerator.removeCookie(response);
			FacebookSecurityContext.remove();
		}
	}

	private boolean requestForSignIn(HttpServletRequest request) {
		return request.getServletPath().startsWith("/uss/ion/fbk/facebookSignin.do");
	}

	private boolean requireSignIn(HttpServletRequest request, HttpServletResponse response) throws Exception {
		new RedirectView("/uss/ion/fbk/facebookSignin.do", true).render(null, request, response);
		return false;
	}

	private boolean userNotFound(String userId) {
		// doesn't bother checking a local user database: simply checks if the userId is connected to Facebook
		return connectionRepository.createConnectionRepository(userId).findPrimaryConnection(Facebook.class) != null;
	}

}
