/*
 * eGovFrame LDAP조직도관리
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
 * @author 전우성(슈퍼개발자K3)
 */
package egovframework.com.ext.ldapumt.service.impl;

import java.util.List;

import javax.naming.directory.BasicAttribute;
import javax.naming.directory.SearchControls;

import org.springframework.ldap.NameNotFoundException;
import org.springframework.stereotype.Repository;

import egovframework.com.cmm.EgovWebUtil;
import egovframework.com.ext.ldapumt.service.UserVO;

/**
*
* 사용자 관련 기능을 제공하는 DAO객체
* @author 전우성
* @since 2014.10.12
* @version 1.0
* @see
*
* <pre>
* << 개정이력(Modification Information) >>
*
*   수정일      수정자           수정내용
*  -------    --------    ---------------------------
*   2014.10.12  전우성          최초 생성
*
* </pre>
*/
@Repository("UserManageLdapDAO")
public class UserManageLdapDAO extends OrgManageLdapDAO {

	/**
	 *
	 * @param dn
	 * @return
	 */
	public List<Object> selectUserManageList(String dn) {
		List<Object> ucorgList = null;
		String filter = "objectclass=user";

		try {
			ucorgList = ldapTemplate.search(EgovWebUtil.removeLDAPInjectionRisk(dn), filter,
				SearchControls.ONELEVEL_SCOPE, new ObjectMapper<UserVO>(
					UserVO.class));
		} catch (NameNotFoundException e) {
			logger.error("[NameNotFoundException] : search fail");//KISA 보안약점 조치 (2018-10-29, 윤창원)
		}

		return ucorgList;

	}

	/**
	 * 사용자를 추가한다.
	 * @param vo
	 */
	public void insertUserManage(UserVO vo) {
		BasicAttribute ocattr = new BasicAttribute("objectclass");
		ocattr.add("top");
		ocattr.add("user");

		insertOrgManage(vo, ocattr);

	}

	/**
	 * 사용자를 이동한다
	 * @param oldDn 이동 대상 사용자
	 * @param newDn 이동 부서
	 */
	public void moveUserManage(String oldDn, String newDn) {
		ldapTemplate.rename(oldDn, newDn);
	}

	/**
	 * 등록된 사용자를 조회한다
	 * @param dn
	 * @return
	 */
	public UserVO selectUserManageByDn(String dn) {
		return (UserVO)selectOrgManageByDn(dn, UserVO.class);
	}

	/**
	 * 사용자 정보를 수정한다.
	 * @param vo
	 */
	public void updateUserManage(UserVO vo) {
		updateOrg(vo);
	}

}
