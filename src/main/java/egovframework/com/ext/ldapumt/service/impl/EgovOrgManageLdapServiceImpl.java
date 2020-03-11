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
import java.util.Map;

import egovframework.com.ext.ldapumt.service.EgovOrgManageLdapService;
import egovframework.com.ext.ldapumt.service.LdapTreeObject;
import egovframework.com.ext.ldapumt.service.UcorgVO;
import egovframework.com.ext.ldapumt.service.UserVO;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

/**
*
* 조직도 기능 관련 서비스 객체 
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
@Service("orgManageLdapService")
public class EgovOrgManageLdapServiceImpl extends EgovAbstractServiceImpl implements EgovOrgManageLdapService {

	@Resource(name = "DeptManageLdapDAO")
	private DeptManageLdapDAO deptManageLdapDAO;

	@Resource(name = "UserManageLdapDAO")
	private UserManageLdapDAO userManageLdapDAO;

	/**
	 * 등록된 부서의 정보를 조회한다.
	 */
	public Map<String, Object> selectDeptManage(String dn) {
		UcorgVO vo = deptManageLdapDAO.selectDeptManageByDn(dn);

		@SuppressWarnings("unchecked")
		Map<String, Object> map = new org.apache.commons.beanutils.BeanMap(vo);

		return map;
	}

	/**
	 * 등록된 사용자의 정보를 조회한다.
	 */
	public Map<String, Object> selectUserManage(String dn) {
		UserVO vo = userManageLdapDAO.selectUserManageByDn(dn);
		
		@SuppressWarnings("unchecked")
		Map<String, Object> map = new org.apache.commons.beanutils.BeanMap(vo);
		
		return map;
	}
	
	/**
	 * 등록된 부서의 목록을 조회한다.
	 */
	@SuppressWarnings("unchecked")
	public Map<String, Object> selectDeptManageSubList(String dn) throws Exception {
		UcorgVO u = deptManageLdapDAO.selectDeptManageByDn(dn);

		LdapTreeObject object = new LdapTreeObject(u.getOu(), dn);

		List<Object> list = deptManageLdapDAO.selectDeptManageSubList(dn);

		for (Object o : list) {
			UcorgVO vo = (UcorgVO) o;
			boolean hasChildren = deptManageLdapDAO.hasChildren(vo.getDn());
			object.addChild(vo, hasChildren);
		}

		List<Object> userList = userManageLdapDAO.selectUserManageList(dn);

		for (Object o : userList) {
			UserVO vo = (UserVO) o;
			object.addChild(vo);
		}

		Map<String, Object> map = new org.apache.commons.beanutils.BeanMap(object);

		return map;
	}

	/**
	 * ouCode로 하위부서의 목록을 조회한다.
	 */
	public List<Object> selectDeptManageSubListByOuCode(String ouCode) throws Exception {
		return deptManageLdapDAO.selectDeptManageSubListByOuCode(ouCode);
	}

	/**
	 * VO의 조건에 부합하는 부서를 조회한다. 
	 */
	public UcorgVO selectDeptManage(UcorgVO vo) throws Exception {
		return deptManageLdapDAO.selectDeptManage(vo);
	}


	/**
	 * 기등록된 부서정보를 수정한다.
	 */
	public void updateDeptManage(UcorgVO vo) throws Exception {
		deptManageLdapDAO.updateDeptManage(vo);
	}

	/**
	 * 부서를 추가한다.
	 */
	public Map<String, Object> insertDeptManage(String parentDn, String ou) throws Exception {
		UcorgVO vo = new UcorgVO();
		vo.setDn("ou=" + ou + ", " + parentDn);
		vo.setOu(ou);
		vo.setOuCode("0000000");

		deptManageLdapDAO.insertDeptManage(vo);
		LdapTreeObject object = new LdapTreeObject(vo.getOu(), vo.getDn());
		@SuppressWarnings("unchecked")
		Map<String, Object> map = new org.apache.commons.beanutils.BeanMap(object);

		return map;
	}

	/**
	 * 사용자를 추가한다.
	 */
	public Map<String, Object> insertUserManage(String parentDn, String cn) throws Exception {
		UserVO vo = new UserVO();
		vo.setDn("cn=" + cn + ", " + parentDn);
		vo.setCn(cn);

		userManageLdapDAO.insertUserManage(vo);

		LdapTreeObject object = new LdapTreeObject(vo.getOu(), vo.getDn());
		@SuppressWarnings("unchecked")
		Map<String, Object> map = new org.apache.commons.beanutils.BeanMap(object);

		return map;
	}

	/**
	 * 사용자 정보를 삭제한다.
	 */
	public void deleteDeptManage(String dn) {
		deptManageLdapDAO.deleteDeptManage(dn);
	}

	/**
	 * 부서의 이름을 변경한다.
	 */
	public void renameDeptManage(String dn, String name) {
		String[] nodes = dn.split(",");
		nodes[0] = "ou=" + name;

		String newDn = "";
		for (String node : nodes) {
			newDn = newDn + "," + node;
		}

		newDn = newDn.substring(1);
		deptManageLdapDAO.moveDeptManage(dn, newDn);
	}

	/**
	 * 사용자의 이름을 변경한다.
	 */
	public void renameUserManage(String dn, String name) {
		String[] nodes = dn.split(",");
		nodes[0] = "cn=" + name;

		String newDn = "";
		for (String node : nodes) {
			newDn = newDn + "," + node;
		}

		newDn = newDn.substring(1);
		userManageLdapDAO.moveUserManage(dn, newDn);
	}

	/**
	 * 조직을 이동한다.
	 */
	public void moveOrgManage(String dn, String parentDn) {
		String name = dn.split(",")[0];

		deptManageLdapDAO.moveDeptManage(dn, name + "," + parentDn);
	}

	/**
	 * 부서정보를 수정한다.
	 */
	public void modifyDeptManage(UcorgVO ucorgVO) throws Exception {
		deptManageLdapDAO.updateDeptManage(ucorgVO);		
	}

	/**
	 * 사용자의 정보를 수정한다.
	 */
	public void modifyUserManage(UserVO userVO) {
		userManageLdapDAO.updateUserManage(userVO);
	}

}
