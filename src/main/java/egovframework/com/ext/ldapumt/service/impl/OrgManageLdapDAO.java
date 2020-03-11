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

import java.util.ArrayList;
import java.util.Map;

import egovframework.com.cmm.service.impl.EgovComAbstractDAO;
import egovframework.com.ext.ldapumt.service.LdapObject;

import javax.annotation.Resource;
import javax.naming.directory.Attribute;
import javax.naming.directory.Attributes;
import javax.naming.directory.BasicAttribute;
import javax.naming.directory.BasicAttributes;
import javax.naming.directory.DirContext;
import javax.naming.directory.ModificationItem;

import org.apache.commons.beanutils.BeanMap;
import org.springframework.ldap.core.LdapTemplate;

/**
*
* 조직도 기능 관련 DAO객체.
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
public class OrgManageLdapDAO extends EgovComAbstractDAO {
	@Resource(name = "ldapTemplate")
	public LdapTemplate ldapTemplate;

	/**
	 * 조직정보를 변경하는 메소드
	 * @param vo
	 * vo의 dn의 객체를 인자로 넘어온 객체로 업데이트.
	 */
	protected void updateOrg(LdapObject vo) {
		String dn = vo.getDn();

		final ArrayList<ModificationItem> itemList = new ArrayList<ModificationItem>();

		introspect(vo, new Executable(){
			@Override
			public void execute(String key, Object value) {
				Attribute attr = new BasicAttribute(key, value);
				ModificationItem item = new ModificationItem(DirContext.REPLACE_ATTRIBUTE, attr);
				itemList.add(item);
			}
		});

		ModificationItem[] items = new ModificationItem[itemList.size()];
		itemList.toArray(items);
		ldapTemplate.modifyAttributes(dn, items);
	}

	/**
	 * DN에 해당하는 객체를 return한다.
	 * @param dn 조회할 객체의 Distinguished Names
	 * @param lookupClass lookup될 vo class
	 * @return lookup된 vo객체
	 */
	@SuppressWarnings("unchecked")
	protected LdapObject selectOrgManageByDn(String dn, @SuppressWarnings("rawtypes") Class lookupClass) {
		LdapObject vo = null;

		vo = (LdapObject) ldapTemplate.lookup(dn, new ObjectMapper<Object>(lookupClass));

		return vo;
	}

	/**
	 * 조직정보를 ldap에 저장한다.
	 * @param vo 저장할 vo
	 * @param attr
	 */
	protected void insertOrgManage(LdapObject vo, BasicAttribute attr) {
		final Attributes attrs = new BasicAttributes();
		attrs.put(attr);

		introspect(vo, new Executable(){
			@Override
			public void execute(String key, Object value) {
				attrs.put(key, value);
			}
		});

		ldapTemplate.bind(vo.getDn(), null, attrs);
	}

	/**
	 * vo의 field별로 특정 명령을 수행
	 * @param vo
	 * @param e
	 */
	private void introspect(LdapObject vo, Executable e) {
		@SuppressWarnings("unchecked")
		Map<String, Object> introspected = new BeanMap(vo);

		for (String key : introspected.keySet()) {
			if (key.equals("dn") || key.equals("class") || introspected.get(key) == null
					|| introspected.get(key).equals(""))
				continue;

			e.execute(key, introspected.get(key));
		}

	}

}
