package egovframework.com.uss.umt.service;

public class EntrprsPasswordManageVO extends PasswordManageVO {

	/**
	 * 기업 회원 ID
	 */
	private String entrprsmberId;
	
	/**
	 * 기업 회원 PW
	 */
	private String entrprsMberPassword;

	public String getEntrprsMberPassword() {
		return entrprsMberPassword;
	}

	public void setEntrprsMberPassword(String entrprsMberPassword) {
		this.entrprsMberPassword = entrprsMberPassword;
	}

	public String getEntrprsmberId() {
		return entrprsmberId;
	}

	public void setEntrprsmberId(String entrprsmberId) {
		this.entrprsmberId = entrprsmberId;
	}

}
