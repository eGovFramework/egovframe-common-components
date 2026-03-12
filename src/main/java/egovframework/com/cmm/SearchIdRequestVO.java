package egovframework.com.cmm;

import org.egovframe.rte.ptl.reactive.validation.EgovNullCheck;
import org.egovframe.rte.ptl.reactive.validation.EgovEmailCheck;

/**
 * @Class Name : SearchIdRequestVO.java
 * @Description : 아이디 찾기 요청 VO
 * @Modification Information
 *
 *<pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일              수정자          수정내용
 *   ----------  --------  ---------------------------
 *   2025.12.09            아이디 찾기 요청 VO 생성
 *</pre>
 *
 *  @since 2025.12.09
 *  @version 1.0
 *  @see
 *
 */
public class SearchIdRequestVO extends BaseRequestVO{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	/** 이름 */
	@EgovNullCheck
	private String name;

	/** 이메일주소 */
	@EgovNullCheck
	@EgovEmailCheck
	private String email;

	/**
	 * name attribute 를 리턴한다.
	 * @return String
	 */
	public String getName() {
		return name;
	}

	/**
	 * name attribute 값을 설정한다.
	 * @param name String
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * email attribute 를 리턴한다.
	 * @return String
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * email attribute 값을 설정한다.
	 * @param email String
	 */
	public void setEmail(String email) {
		this.email = email;
	}
}
