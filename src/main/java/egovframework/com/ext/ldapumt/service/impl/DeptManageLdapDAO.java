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

import static org.springframework.ldap.query.LdapQueryBuilder.query;

import java.util.List;
import java.util.Map;

import egovframework.com.ext.ldapumt.service.UcorgVO;

import javax.annotation.Resource;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.BasicAttribute;
import javax.naming.directory.DirContext;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;

import org.apache.commons.beanutils.BeanMap;
import org.springframework.ldap.NameNotFoundException;
import org.springframework.ldap.core.ContextSource;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.query.ContainerCriteria;
import org.springframework.stereotype.Repository;

/**
*
* 부서 관련 기능을 제공하는 DAO객체 
* @author 전우성
* @since 2014.10.12
* @version 1.0
* @see
*
* <pre>
* << 개정이력(Modification Information) >>
*
*  수정일               수정자            수정내용
*  ----------   --------   ---------------------------
*  2014.10.12   전우성            최초 생성
*  2017-02-13   이정은            시큐어코딩(ES) - 시큐어코딩 부적절한 예외 처리[CWE-253, CWE-440, CWE-754]
*  2020.08.28   정진호            표준프레임워크 v3.10 개선
*
* </pre>
*/
@Repository("DeptManageLdapDAO")
public class DeptManageLdapDAO extends OrgManageLdapDAO {

	@Resource(name = "ldapTemplate")
	public LdapTemplate ldapTemplate;

	/**
	 * DN의 하위부서 목록을 조회
	 * @param dn 조회할 객체의 Distinguished Name
	 * @return
	 * @throws Exception
	 */
	public List<Object> selectDeptManageSubList(String dn) throws Exception {
		List<Object> ucorgList = null;
		String filter = "objectclass=ucorg2";

		try {
			ucorgList = ldapTemplate.search(dn, filter, SearchControls.ONELEVEL_SCOPE, new ObjectMapper<UcorgVO>(
					UcorgVO.class));
		} catch (NameNotFoundException e) {
			logger.error("[NameNotFoundException] : search fail");//KISA 보안약점 조치 (2018-10-29, 윤창원)
		}

		return ucorgList;
	}

	/**
	 * ouCode를 활용하여 하위 부서를 조
	 * @param ouCode
	 * @return
	 * @throws Exception
	 */
	public List<Object> selectDeptManageSubListByOuCode(String ouCode) throws Exception {
		ContainerCriteria criteria = query().where("objectclass").is("ucorg2").and("parentoucode").is(ouCode);

		List<Object> list = ldapTemplate.search(criteria, new ObjectMapper<UcorgVO>(UcorgVO.class));

		return list;
	}

	/**
	 * 등록된 부서의 상세정보를 조회한다.
	 * @param vo 부서 Vo
	 * @return deptManageVO 부서 Vo
	 * @param bannerVO
	 */
	public UcorgVO selectDeptManage(UcorgVO vo) throws Exception {
		final ContainerCriteria criteria = query().where("objectclass").is("ucorg2");

		@SuppressWarnings("unchecked")
		Map<Object, Object> introspected = new BeanMap(vo);

		for (Object key : introspected.keySet()) {
			if (key.equals("dn") || key.equals("class") || introspected.get(key) == null
					|| introspected.get(key).equals(""))
				continue;

			ContainerCriteria c = query().where((String) key).is(String.valueOf(introspected.get(key)));
			criteria.and(c);
		}

		List<Object> list = null;
		try {
			list = ldapTemplate.search(criteria, new ObjectMapper<UcorgVO>(UcorgVO.class));
		//2017.02.07 	이정은 	시큐어코딩(ES)-오류 메시지를 통한 정보노출[CWE-209]
		} catch (NullPointerException e) {
			logger.error("[NullPointerException] : search fail");
		} catch (Exception e) {
			logger.error("[" + e.getClass() +"] search fail : " + e.getMessage());
		}

		return list == null ? null : (UcorgVO) list.get(0);
	}

	/**
	 * 등록된 부서의 상세정보를 조회한다. 
	 * @param dn
	 * @return
	 */
	public UcorgVO selectDeptManageByDn(String dn) {
		return (UcorgVO) selectOrgManageByDn(dn, UcorgVO.class);
	}

	/**
	 * 기 등록된 부서정보를 수정한다.
	 * @param vo 부서 vo
	 */
	public void updateDeptManage(UcorgVO vo) throws Exception {
		updateOrg(vo);
	}

	/**
	 * 부서정보를 등한다.
	 * @param vo 부서 vo
	 */
	public void insertDeptManage(UcorgVO vo) throws Exception {
		BasicAttribute ocattr = new BasicAttribute("objectclass");
		ocattr.add("top");
		ocattr.add("ucorg2");

		insertOrgManage(vo, ocattr);
	}

	/**
	 * 부서를 이동한다.
	 * @param oldDn 이동대상 부서
	 * @param newDn 이동할 부서
	 */
	public void moveDeptManage(String oldDn, String newDn) {
		ldapTemplate.rename(oldDn, newDn);
	}

	/**
	 * 부서를 삭한다.
	 * @param vo 부서 vo
	 */
	public void deleteDeptManage(String dn) {
		ldapTemplate.unbind(dn, true);
	}

	/**
	 * 하위 부서 존재여부를 확인한다.
	 * @param vo 부서 vo
	 */
	public boolean hasChildren(String dn) throws NamingException {
		ContextSource contextSource = ldapTemplate.getContextSource();
		DirContext ctx = contextSource.getReadOnlyContext();

		String filter = "objectclass=*";
		SearchControls control = new SearchControls();
		control.setSearchScope(SearchControls.ONELEVEL_SCOPE);

		NamingEnumeration<SearchResult> n = ctx.search(dn, filter, control);

		if(n !=null && n.hasMore()){
			return true;
		}
			
		return false;
	}
}
