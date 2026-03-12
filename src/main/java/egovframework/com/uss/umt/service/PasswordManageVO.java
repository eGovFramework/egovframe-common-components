package egovframework.com.uss.umt.service;

import org.egovframe.rte.ptl.reactive.validation.EgovNullCheck;
import org.egovframe.rte.ptl.reactive.validation.EgovPwdCheck;

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
	
	public String getUserTy() {
		return userTy;
	}

	public void setUserTy(String userTy) {
		this.userTy = userTy;
	}

	public String getUniqId() {
		return uniqId;
	}

	public void setUniqId(String uniqId) {
		this.uniqId = uniqId;
	}

	public String getOldPassword() {
		return oldPassword;
	}

	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPassword2() {
		return password2;
	}

	public void setPassword2(String password2) {
		this.password2 = password2;
	}

}
