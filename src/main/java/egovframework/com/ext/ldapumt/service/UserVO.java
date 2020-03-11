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

	public String getOu() {
		return ou;
	}

	public void setOu(String ou) {
		this.ou = ou;
	}

	public String getCn() {
		return cn;
	}

	public void setCn(String cn) {
		this.cn = cn;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public String getInsayn() {
		return insayn;
	}

	public void setInsayn(String insayn) {
		this.insayn = insayn;
	}

	public String getOuCode() {
		return ouCode;
	}

	public void setOuCode(String ouCode) {
		this.ouCode = ouCode;
	}

	public String getParentouCode() {
		return parentouCode;
	}

	public void setParentouCode(String parentouCode) {
		this.parentouCode = parentouCode;
	}

	public String getTopouCode() {
		return topouCode;
	}

	public void setTopouCode(String topouCode) {
		this.topouCode = topouCode;
	}

	public String getUcOrgFullName() {
		return ucOrgFullName;
	}

	public void setUcOrgFullName(String ucOrgFullName) {
		this.ucOrgFullName = ucOrgFullName;
	}

	public String getUserFullName() {
		return userFullName;
	}

	public void setUserFullName(String userFullName) {
		this.userFullName = userFullName;
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
}
