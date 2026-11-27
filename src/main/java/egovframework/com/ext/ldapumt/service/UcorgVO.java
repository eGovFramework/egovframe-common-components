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
* 부서 VO 객 체
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
public class UcorgVO extends LdapObject {
	private String ou = "";
	private String ouCode = "";
	private String docSystemInfo = "";
	private String ouDocumentReceipientSymbol = "";
	private String ouSMTPAddress = "";
	private String parentouCode = "";
	private String repouCode = "";
	private String signCertificate = "";
	private String topouCode = "";
	private String ucChieftitle = "";
	private String ucOrganizationalUnitName = "";
	private String ucOrgFullName = "";
	private String useGroupware = "";
	private String wsignCertificate = "";
	private String wuserCertificate = "";

	private Integer ouLevel = null;
	private Integer ouOrder = null;
	private Integer typeBig = null;
	private Integer typeMid = null;
	private Integer typeSml = null;

	private String ouReceiveDocumentYN = "";
	private String ouSendOutDocumentYN = "";
}
