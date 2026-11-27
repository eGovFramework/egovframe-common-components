package egovframework.com.cmm;

import org.egovframe.rte.ptl.reactive.validation.EgovNullCheck;
import org.egovframe.rte.ptl.reactive.validation.EgovEmailCheck;

import lombok.Getter;
import lombok.Setter;

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

}
