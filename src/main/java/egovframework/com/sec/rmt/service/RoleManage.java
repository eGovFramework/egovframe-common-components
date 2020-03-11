package egovframework.com.sec.rmt.service;

import egovframework.com.cmm.ComDefaultVO;

/**
 * 롤관리에 대한 model 클래스를 정의한다.
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

public class RoleManage extends ComDefaultVO {
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 롤 관리
	 */
	private RoleManage roleManage;
	/**
	 * 롤코드
	 */
	private String roleCode;
	/**
	 * 롤명
	 */
	private String roleNm;	
	/**
	 * 롤패턴
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
	 * 롤 Sort
	 */
	private String roleSort;
	/**
	 * 롤 등록일시
	 */
	private String roleCreatDe;
	/**
	 * 권한 코드
	 */
	private String authorCode;
	
	/**
	 * roleManage attribute 를 리턴한다.
	 * @return RoleManage
	 */
	public RoleManage getRoleManage() {
		return roleManage;
	}
	/**
	 * roleManage attribute 값을 설정한다.
	 * @param roleManage RoleManage 
	 */
	public void setRoleManage(RoleManage roleManage) {
		this.roleManage = roleManage;
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
	 * roleCreatDe attribute 를 리턴한다.
	 * @return String
	 */
	public String getRoleCreatDe() {
		return roleCreatDe;
	}
	/**
	 * roleCreatDe attribute 값을 설정한다.
	 * @param roleCreatDe String 
	 */
	public void setRoleCreatDe(String roleCreatDe) {
		this.roleCreatDe = roleCreatDe;
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
	

}