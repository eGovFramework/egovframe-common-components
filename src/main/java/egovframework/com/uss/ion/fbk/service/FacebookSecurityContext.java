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
 * Simple SecurityContext that stores the currently signed-in connection in a thread local.
 * @author Keith Donald
 */
package egovframework.com.uss.ion.fbk.service;


/**
 * SecurityContext 클래스
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

public final class FacebookSecurityContext {

	private static final ThreadLocal<FacebookUser> currentUser = new ThreadLocal<FacebookUser>();

	public static FacebookUser getCurrentUser() {
		FacebookUser user = currentUser.get();
		if (user == null) {
			throw new IllegalStateException("No user is currently signed in");
		}
		return user;
	}

	public static void setCurrentUser(FacebookUser user) {
		currentUser.set(user);
	}

	public static boolean userSignedIn() {
		return currentUser.get() != null;
	}

	public static void remove() {
		currentUser.remove();
	}

}
