package egovframework.com.uss.umt.service;

import org.egovframe.rte.ptl.reactive.validation.EgovNullCheck;
import org.egovframe.rte.ptl.reactive.validation.EgovPwdCheck;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PasswordManageVO {

	private static final long serialVersionUID = -4255594107023139972L;

	/** 이전비밀번호 - 비밀번호 변경시 사용*/
	@EgovNullCheck
	private String oldPassword = "";

	/** 새 비밀번호 - 비밀번호 변경시 사용*/
	@EgovNullCheck
	@EgovPwdCheck
	private String password = "";

	/** 새 비밀번호 확인- 비밀번호 변경시 사용*/
	@EgovNullCheck
	@EgovPwdCheck
	private String password2 = "";

	/**
	 * 사용자고유아이디
	 */
	private String uniqId = "";

	/**
	 * 사용자 유형
	 */
	private String userTy = "";

}
