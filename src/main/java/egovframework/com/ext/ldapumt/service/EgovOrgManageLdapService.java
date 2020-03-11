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
package egovframework.com.ext.ldapumt.service;

import java.util.List;
import java.util.Map;

/**
*
* Controller에서 요청하는 서비스를 제공하는 Service객체의 인터페이스를 정의한다.
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

public interface EgovOrgManageLdapService {

	/**
	 * 하위부서 목록 조회 
	 * @param dn 조회할 부서의 dn
	 * @return
	 * @throws Exception
	 */
	public Map<String, Object> selectDeptManageSubList(String dn) throws Exception;

	/**
	 * 하위부서의 목록 조회
	 * @param ouCode 조회할 부서의  oucode 
	 * @return
	 * @throws Exception
	 */
	public List<Object> selectDeptManageSubListByOuCode(String ouCode) throws Exception;

	/**
	 * 등록된 부서의 상세정보를 조회한다.
	 * @param vo - 부서 Vo
	 * @return deptManageVO - 부서 Vo
	 * @param bannerVO
	 */
	public UcorgVO selectDeptManage(UcorgVO vo) throws Exception;

	/**
	 * 기 등록된 부서정보를 수정한다.
	 * @param vo - 부서 vo
	 */
	public void updateDeptManage(UcorgVO vo) throws Exception;

	/**
	 * 신규부서를 등록 
	 * @param parentDn 등록할 부서의 상위부서 
	 * @param ou 부id
	 * @return
	 * @throws Exception
	 */
	public Map<String, Object> insertDeptManage(String parentDn, String ou) throws Exception;
	
	/**
	 * 신규 사용자를 등록 
	 * @param parentDn 사용자의 부서
	 * @param cn 사용자의 id
	 * @return
	 * @throws Exception
	 */
	public Map<String, Object> insertUserManage(String parentDn, String cn) throws Exception;

	/**
	 * 부서 정보 삭제  
	 * @param dn
	 */
	public void deleteDeptManage(String dn);

	/**
	 * 부서 이름 변경 
	 * @param dn
	 * @param name
	 */
	public void renameDeptManage(String dn, String name);

	/**
	 * 사용자 이름 변경 
	 * @param dn
	 * @param name
	 */
	public void renameUserManage(String dn, String name);

	/**
	 * 조직을 이동한다
	 * @param dn
	 * @param parentDn
	 */
	public void moveOrgManage(String dn, String parentDn);

	/**
	 * 등록된 부서의 상세정보를 조회한다.
	 * @param dn 부서의 DN
	 * @return
	 */
	public Map<String, Object> selectDeptManage(String dn);

	/**
	 * 등록된 사용자의 상세정보를 조회한다.
	 * @param dn 사용자의 DN
	 * @return
	 */
	public Map<String, Object> selectUserManage(String dn);

	/**
	 * 등록된 부서의 정보를 수정한다.
	 * @param ucorgVO
	 * @throws Exception
	 */
	public void modifyDeptManage(UcorgVO ucorgVO) throws Exception;

	/**
	 * 등록된 사용자의 정보를 수정한다.
	 * @param ucorgVO
	 * @throws Exception
	 */
	public void modifyUserManage(UserVO userVO);
}
