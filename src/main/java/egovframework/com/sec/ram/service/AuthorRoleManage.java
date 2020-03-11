package egovframework.com.sec.ram.service;

import egovframework.com.cmm.ComDefaultVO;

/**
 * 권한별 롤 관리에 대한 model 클래스를 정의한다.
 * @author 공통서비스 개발팀 이문준
 * @since 2009.06.01
 * @version 1.0
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2009.03.20  이문준          최초 생성
 *
 * </pre>
 */

public class AuthorRoleManage extends ComDefaultVO {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 권한 롤 관리
	 */	
	private AuthorRoleManage authorRole;
	/**
	 * 권한코드
	 */
	private String authorCode;
	/**
	 * 롤코드
	 */
	private String roleCode;
	/**
	 * 롤명
	 */
	private String roleNm;	
	/**
	 * 롤 패턴
	 */
	private String rolePtn;	
	/**
	 * 롤 설명
	 */
	private String roleDc;	
	/**
	 * 롤 타입
	 */
	private String roleTyp;	
	/**
	 * 롤 순서정렬
	 */
	private String roleSort;	
	/**
	 * 롤 등록여부
	 */
	private String regYn;	
	/**
	 * 등록일자
	 */
	private String creatDt;
	/**
	 * authorRole attribute 를 리턴한다.
	 * @return AuthorRoleManage
	 */
	public AuthorRoleManage getAuthorRole() {
		return authorRole;
	}
	/**
	 * authorRole attribute 값을 설정한다.
	 * @param authorRole AuthorRoleManage 
	 */
	public void setAuthorRole(AuthorRoleManage authorRole) {
		this.authorRole = authorRole;
	}
	/**
	 * authorCode attribute 를 리턴한다.
	 * @return String
	 */
	public String getAuthorCode() {
		return authorCode;
	}
	/**
	 * authorCode attribute 값을 설정한다.
	 * @param authorCode String 
	 */
	public void setAuthorCode(String authorCode) {
		this.authorCode = authorCode;
	}
	/**
	 * roleCode attribute 를 리턴한다.
	 * @return String
	 */
	public String getRoleCode() {
		return roleCode;
	}
	/**
	 * roleCode attribute 값을 설정한다.
	 * @param roleCode String 
	 */
	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}
	/**
	 * roleNm attribute 를 리턴한다.
	 * @return String
	 */
	public String getRoleNm() {
		return roleNm;
	}
	/**
	 * roleNm attribute 값을 설정한다.
	 * @param roleNm String 
	 */
	public void setRoleNm(String roleNm) {
		this.roleNm = roleNm;
	}
	/**
	 * rolePtn attribute 를 리턴한다.
	 * @return String
	 */
	public String getRolePtn() {
		return rolePtn;
	}
	/**
	 * rolePtn attribute 값을 설정한다.
	 * @param rolePtn String 
	 */
	public void setRolePtn(String rolePtn) {
		this.rolePtn = rolePtn;
	}
	/**
	 * roleDc attribute 를 리턴한다.
	 * @return String
	 */
	public String getRoleDc() {
		return roleDc;
	}
	/**
	 * roleDc attribute 값을 설정한다.
	 * @param roleDc String 
	 */
	public void setRoleDc(String roleDc) {
		this.roleDc = roleDc;
	}
	/**
	 * roleTyp attribute 를 리턴한다.
	 * @return String
	 */
	public String getRoleTyp() {
		return roleTyp;
	}
	/**
	 * roleTyp attribute 값을 설정한다.
	 * @param roleTyp String 
	 */
	public void setRoleTyp(String roleTyp) {
		this.roleTyp = roleTyp;
	}
	/**
	 * roleSort attribute 를 리턴한다.
	 * @return String
	 */
	public String getRoleSort() {
		return roleSort;
	}
	/**
	 * roleSort attribute 값을 설정한다.
	 * @param roleSort String 
	 */
	public void setRoleSort(String roleSort) {
		this.roleSort = roleSort;
	}
	/**
	 * regYn attribute 를 리턴한다.
	 * @return String
	 */
	public String getRegYn() {
		return regYn;
	}
	/**
	 * regYn attribute 값을 설정한다.
	 * @param regYn String 
	 */
	public void setRegYn(String regYn) {
		this.regYn = regYn;
	}
	/**
	 * creatDt attribute 를 리턴한다.
	 * @return String
	 */
	public String getCreatDt() {
		return creatDt;
	}
	/**
	 * creatDt attribute 값을 설정한다.
	 * @param creatDt String 
	 */
	public void setCreatDt(String creatDt) {
		this.creatDt = creatDt;
	}
	
	
}
