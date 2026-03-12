package egovframework.com.cmm;

import org.egovframe.rte.ptl.reactive.validation.EgovNullCheck;

/**
 * @Class Name : LoginRequestVO.java
 * @Description : 로그인 요청 전용 VO
 * @Modification Information
 *
 *<pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일              수정자          수정내용
 *   ----------  --------  ---------------------------
 *   2025.12.09            로그인 전용 VO 생성
 *</pre>
 *
 *  @since 2025.12.09
 *  @version 1.0
 *  @see
 *
 */
public class LoginRequestVO extends BaseRequestVO{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	/** 아이디 */
	@EgovNullCheck
	private String id;

	/** 비밀번호 */
	@EgovNullCheck
	private String password;

	/**
	 * id attribute 를 리턴한다.
	 * @return String
	 */
	public String getId() {
		return id;
	}

	/**
	 * id attribute 값을 설정한다.
	 * @param id String
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * password attribute 를 리턴한다.
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
	}
}
