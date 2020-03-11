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
package egovframework.com.ext.oauth.service.impl;

import egovframework.com.cmm.LoginVO;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

import org.springframework.social.connect.Connection;
import org.springframework.social.connect.UserProfile;
import org.springframework.social.connect.web.SignInAdapter;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.NativeWebRequest;

/**
 * 소셜 로그인 구현 클래스
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

@Service
public class EgovSignInAdapterServiceImpl extends EgovAbstractServiceImpl implements SignInAdapter {

	@Override
	public String signIn(String userId, Connection<?> connection, NativeWebRequest request) {

		UserProfile profile = connection.fetchUserProfile();

		// 1. 소셜 로그인 처리
		LoginVO resultVO = new LoginVO();
		resultVO.setId(userId);

		if (profile.getLastName() != null) {
			resultVO.setName(profile.getFirstName() + " " + profile.getLastName());
		} else {
			resultVO.setName(profile.getFirstName());
		}

		resultVO.setEmail(profile.getEmail());

		// 2. 로그인 정보를 세션에 저장
		request.setAttribute("loginVO", resultVO, 1);

		return null;
	}

}
