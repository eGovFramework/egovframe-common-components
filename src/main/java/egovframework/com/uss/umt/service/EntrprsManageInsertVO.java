package egovframework.com.uss.umt.service;

import org.egovframe.rte.ptl.reactive.validation.EgovNullCheck;
import org.egovframe.rte.ptl.reactive.validation.EgovPwdCheck;

/**
 * 기업회원 등록용 VO 클래스
 * EntrprsManageVO를 상속받아 비밀번호 검증 필드를 추가
 * 
 * @author 공통서비스
 * @since 2025.12.16
 * @version 1.0
 * @see EntrprsManageVO
 */
public class EntrprsManageInsertVO extends EntrprsManageVO {

	private static final long serialVersionUID = 1L;

	/**
	 * 새 비밀번호 (등록 시 입력)
	 */
	@EgovNullCheck
	@EgovPwdCheck
	private String password;

	/**
	 * 새 비밀번호 확인 (등록 시 확인)
	 */
	@EgovNullCheck
	@EgovPwdCheck
	private String password2;

	/**
	 * password attribute 값을 리턴한다.
	 * @return String
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * password attribute 값을 설정한다.
	 * @param password String
	 */
	public void setPassword(String password) {
		this.password = password;
		// 부모 클래스의 entrprsMberPassword도 함께 설정
		super.setEntrprsMberPassword(password);
	}

	/**
	 * password2 attribute 값을 리턴한다.
	 * @return String
	 */
	public String getPassword2() {
		return password2;
	}

	/**
	 * password2 attribute 값을 설정한다.
	 * @param password2 String
	 */
	public void setPassword2(String password2) {
		this.password2 = password2;
	}
}
