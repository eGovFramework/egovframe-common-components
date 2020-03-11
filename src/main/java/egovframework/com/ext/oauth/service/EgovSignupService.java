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
package egovframework.com.ext.oauth.service;

import org.springframework.social.connect.UserProfile;
import org.springframework.web.context.request.WebRequest;

/**
 * 소셜 계정으로 일반회원 가입을 처리하는 비즈니스 인터페이스 클래스
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
 *  </pre>
 */

public interface EgovSignupService {

	/**
	 * 소셜 계정으로 일반회원 가입을 처리한다
	 * @param UserProfile profile
	 * @param WebRequest request
	 * @param String key
	 * @return String
	 * @exception Exception
	 */
	public String signup(UserProfile profile, WebRequest request, String key) throws Exception;

}
