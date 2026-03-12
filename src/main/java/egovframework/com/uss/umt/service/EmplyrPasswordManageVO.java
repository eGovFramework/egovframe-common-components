package egovframework.com.uss.umt.service;

/**
 * 일반회원 비밀번호 관리 VO 클래스
 * PasswordManageVO를 상속받아 일반회원 관련 필드를 추가
 * 
 * @author 공통서비스 개발팀 조재영
 * @since 2009.04.10
 * @version 1.0
 * @see PasswordManageVO
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2009.04.10  조재영          최초 생성
 *   2017.07.21  장동한 			로그인인증제한 작업
 *   2025.12.16  공통서비스      PasswordManageVO 상속으로 변경
 *
 * </pre>
 */
public class EmplyrPasswordManageVO extends PasswordManageVO {

	private static final long serialVersionUID = 1L;

	/**
	 * 일반회원 ID
	 */
	private String emplyrId;
	
	/**
	 * 일반회원 PW
	 */
	private String emplyrPassword;

	public String getEmplyrId() {
		return emplyrId;
	}

	public void setEmplyrId(String emplyrId) {
		this.emplyrId = emplyrId;
	}

	public String getEmplyrPassword() {
		return emplyrPassword;
	}

	public void setEmplyrPassword(String emplyrPassword) {
		this.emplyrPassword = emplyrPassword;
	}

}