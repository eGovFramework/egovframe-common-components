package egovframework.com.cmm;

import org.egovframe.rte.ptl.reactive.validation.EgovNullCheck;

import lombok.Getter;
import lombok.Setter;

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
 *   2026.05.27            Lombok @Getter/@Setter 적용
 *</pre>
 *
 *  @since 2025.12.09
 *  @version 1.0
 *  @see
 *
 */
@Getter
@Setter
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

}
