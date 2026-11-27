package egovframework.com.uss.umt.service;

import lombok.Getter;
import lombok.Setter;

/**
 * 일반회원 비밀번호 관리 VO 클래스
 * PasswordManageVO를 상속받아 일반회원 관련 필드를 추가
 *
 * @author 공통서비스
 * @since 2025.12.16
 * @version 1.0
 * @see PasswordManageVO
 */
@Getter
@Setter
public class MberPasswordManageVO extends PasswordManageVO {

	private static final long serialVersionUID = 1L;

	/**
	 * 일반회원 ID
	 */
	private String mberId;

	/**
	 * 일반회원 PW
	 */
	private String mberPassword;

}
