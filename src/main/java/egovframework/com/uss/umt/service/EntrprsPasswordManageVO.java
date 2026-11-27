package egovframework.com.uss.umt.service;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EntrprsPasswordManageVO extends PasswordManageVO {

	private static final long serialVersionUID = 1L;

	/**
	 * 기업 회원 ID
	 */
	private String entrprsmberId;

	/**
	 * 기업 회원 PW
	 */
	private String entrprsMberPassword;

}
