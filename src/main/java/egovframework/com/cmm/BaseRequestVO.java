package egovframework.com.cmm;

import java.io.Serializable;

/**
 * @Class Name : BaseRequestVO.java
 * @Description : 로그인/검색 요청 기본 VO
 * @Modification Information
 *
 *<pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일              수정자          수정내용
 *   ----------  --------  ---------------------------
 *   2025.12.09            기본 요청 VO 생성
 *</pre>
 *
 *  @since 2025.12.09
 *  @version 1.0
 *  @see
 *
 */
public class BaseRequestVO implements Serializable{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	/** 사용자구분 */
	private String userSe;

	/**
	 * userSe attribute 를 리턴한다.
	 * @return String
	 */
	public String getUserSe() {
		return userSe;
	}

	/**
	 * userSe attribute 값을 설정한다.
	 * @param userSe String
	 */
	public void setUserSe(String userSe) {
		this.userSe = userSe;
	}
}
