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
import egovframework.com.ext.oauth.service.EgovSignupService;
import egovframework.com.uss.umt.service.MberManageVO;
import egovframework.com.uss.umt.service.impl.MberManageDAO;
import egovframework.com.utl.fcc.service.EgovStringUtil;
import egovframework.com.utl.sim.service.EgovFileScrty;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

import javax.annotation.Resource;

import org.springframework.social.connect.UserProfile;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.WebRequest;

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
 *  </pre>
 */

@Service("signupService")
public class EgovSignupServiceImpl extends EgovAbstractServiceImpl implements EgovSignupService {

	@Resource(name="mberManageDAO")
	private MberManageDAO mberManageDAO;

	/**
	 * 소셜 계정으로 일반회원 가입을 처리한다
	 * @param UserProfile profile
	 * @param WebRequest request
	 * @param String key
	 * @return String
	 * @exception Exception
	 */
	@Override
	public String signup(UserProfile profile, WebRequest request, String key) throws Exception {

		String result = null;

		key = EgovStringUtil.replace(key, "facebook", "F");
		key = EgovStringUtil.replace(key, "twitter", "T");

		if (mberManageDAO.selectMber(key) == null) {
			MberManageVO mberManageVO = new MberManageVO();

			mberManageVO.setUniqId(key);
			mberManageVO.setMberId(key);


			if (profile.getLastName() != null) {
				mberManageVO.setMberNm(profile.getFirstName() + " " + profile.getLastName());
			} else {
				mberManageVO.setMberNm(profile.getFirstName());
			}

			// 필수사항
			mberManageVO.setPassword(EgovStringUtil.getRandomStr("a".charAt(0), "z".charAt(0)));
			mberManageVO.setZip("000000");
			mberManageVO.setAdres("소셜로그인");
			mberManageVO.setAreaNo("00");
			mberManageVO.setMiddleTelno("000");
			mberManageVO.setMoblphonNo("0000-0000");
			mberManageVO.setEndTelno("0000");

			// 선택사항
			mberManageVO.setMberSttus("P");
			mberManageVO.setMberEmailAdres(profile.getEmail());	// facebook만 가능 twitter은 불가
			mberManageVO.setGroupId("GROUP_00000000000000");

			//패스워드 암호화
			String pass = EgovFileScrty.encryptPassword(mberManageVO.getPassword(), mberManageVO.getMberId());
			mberManageVO.setPassword(pass);

			result = mberManageDAO.insertMber(mberManageVO);


		} else {
			result = "true";
		}

		// 1. 소셜 로그인 처리
		LoginVO resultVO = new LoginVO();
		resultVO.setId(key);

		if (profile.getLastName() != null) {
			resultVO.setName(profile.getFirstName() + " " + profile.getLastName());
		} else {
			resultVO.setName(profile.getFirstName());
		}

		resultVO.setEmail(profile.getEmail());

		// 2. 로그인 정보를 세션에 저장
		request.setAttribute("loginVO", resultVO, 1);

		return result;
	}

}
