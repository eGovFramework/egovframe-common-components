package egovframework.com.utl.sys.trm.service;

import java.io.Serializable;

import egovframework.com.cmm.ComDefaultVO;

import lombok.Getter;
import lombok.Setter;

/**
 * 연계정보에 대한 VO 클래스
 *
 * @author 김진만
 * @since 2010.06.21
 * @version 1.0
 * @updated 21-6-2010 오전 10:27:13
 * @see
 * <pre>
 * == 개정이력(Modification Information) ==
 *
 *   수정일       수정자           수정내용
 *  -------     --------    ---------------------------
 *  2010.06.21   김진만     최초 생성
 * </pre>
 */
@Getter
@Setter
public class CntcVO extends ComDefaultVO implements Serializable {

	private static final long serialVersionUID = -4961144967939216693L;

	/**
	 * 연계ID
	 */
	private String cntcId;

	/**
	 * 연계명
	 */
	private String cntcNm;

	/**
	 * 제공기관명
	 */
	private String provdInsttNm;

	/**
	 * 제공시스템명
	 */
	private String provdSysNm;

	/**
	 * 제공서비스명
	 */
	private String provdSvcNm;

	/**
	 * 요청기관명
	 */
	private String requstInsttNm;

	/**
	 * 요청시스템명
	 */
	private String requstSysNm;

}
