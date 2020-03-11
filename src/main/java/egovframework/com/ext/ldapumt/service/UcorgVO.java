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
public class UcorgVO extends LdapObject{
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
	
	public String getOuReceiveDocumentYN() {
		return ouReceiveDocumentYN;
	}

	public void setOuReceiveDocumentYN(String ouReceiveDocumentYN) {
		this.ouReceiveDocumentYN = ouReceiveDocumentYN;
	}

	public String getOuSendOutDocumentYN() {
		return ouSendOutDocumentYN;
	}

	public void setOuSendOutDocumentYN(String ouSendOutDocumentYN) {
		this.ouSendOutDocumentYN = ouSendOutDocumentYN;
	}

	private String ouSendOutDocumentYN = "";

	public String getOu() {
		return ou;
	}

	public void setOu(String ou) {
		this.ou = ou;
	}

	public String getOuCode() {
		return ouCode;
	}

	public void setOuCode(String ouCode) {
		this.ouCode = ouCode;
	}

	public String getDocSystemInfo() {
		return docSystemInfo;
	}

	public void setDocSystemInfo(String docSystemInfo) {
		this.docSystemInfo = docSystemInfo;
	}

	public String getOuDocumentReceipientSymbol() {
		return ouDocumentReceipientSymbol;
	}

	public void setOuDocumentReceipientSymbol(String ouDocumentReceipientSymbol) {
		this.ouDocumentReceipientSymbol = ouDocumentReceipientSymbol;
	}

	public String getOuSMTPAddress() {
		return ouSMTPAddress;
	}

	public void setOuSMTPAddress(String ouSMTPAddress) {
		this.ouSMTPAddress = ouSMTPAddress;
	}

	public String getParentouCode() {
		return parentouCode;
	}

	public void setParentouCode(String parentouCode) {
		this.parentouCode = parentouCode;
	}

	public String getRepouCode() {
		return repouCode;
	}

	public void setRepouCode(String repouCode) {
		this.repouCode = repouCode;
	}

	public String getSignCertificate() {
		return signCertificate;
	}

	public void setSignCertificate(String signCertificate) {
		this.signCertificate = signCertificate;
	}

	public String getTopouCode() {
		return topouCode;
	}

	public void setTopouCode(String topouCode) {
		this.topouCode = topouCode;
	}

	public String getUcChieftitle() {
		return ucChieftitle;
	}

	public void setUcChieftitle(String ucChieftitle) {
		this.ucChieftitle = ucChieftitle;
	}

	public String getUcOrganizationalUnitName() {
		return ucOrganizationalUnitName;
	}

	public void setUcOrganizationalUnitName(String ucOrganizationalUnitName) {
		this.ucOrganizationalUnitName = ucOrganizationalUnitName;
	}

	public String getUcOrgFullName() {
		return ucOrgFullName;
	}

	public void setUcOrgFullName(String ucOrgFullName) {
		this.ucOrgFullName = ucOrgFullName;
	}

	public String getUseGroupware() {
		return useGroupware;
	}

	public void setUseGroupware(String useGroupware) {
		this.useGroupware = useGroupware;
	}

	public String getWsignCertificate() {
		return wsignCertificate;
	}

	public void setWsignCertificate(String wsignCertificate) {
		this.wsignCertificate = wsignCertificate;
	}

	public String getWuserCertificate() {
		return wuserCertificate;
	}

	public void setWuserCertificate(String wuserCertificate) {
		this.wuserCertificate = wuserCertificate;
	}

	public Integer getOuLevel() {
		return ouLevel;
	}

	public void setOuLevel(Integer ouLevel) {
		this.ouLevel = ouLevel;
	}

	public Integer getOuOrder() {
		return ouOrder;
	}

	public void setOuOrder(Integer ouOrder) {
		this.ouOrder = ouOrder;
	}

	public Integer getTypeBig() {
		return typeBig;
	}

	public void setTypeBig(Integer typeBig) {
		this.typeBig = typeBig;
	}

	public Integer getTypeMid() {
		return typeMid;
	}

	public void setTypeMid(Integer typeMid) {
		this.typeMid = typeMid;
	}

	public Integer getTypeSml() {
		return typeSml;
	}

	public void setTypeSml(Integer typeSml) {
		this.typeSml = typeSml;
	}

}
