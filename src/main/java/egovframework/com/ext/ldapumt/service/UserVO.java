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

import lombok.Getter;
import lombok.Setter;

/**
*
* 사용자 VO 객 체
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
@Getter
@Setter
public class UserVO extends LdapObject {
	private String ou = "";
	private String cn = "";
	private String displayName = "";
	private String companyName = "";
	private String departmentName = "";
	private String grade = "";
	private String insayn = "";
	private String ouCode = "";
	private String parentouCode = "";
	private String topouCode = "";
	private String ucOrgFullName = "";
	private String userFullName = "";

	private Integer ouLevel = null;
	private Integer ouOrder = null;
}
