package egovframework.com.uss.ion.ecc.service;

import egovframework.com.cmm.ComDefaultVO;
import lombok.Getter;
import lombok.Setter;

import org.egovframe.rte.ptl.reactive.validation.EgovNullCheck;
import org.egovframe.rte.ptl.reactive.validation.EgovEmailCheck;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
/**
 * 외부인사관리 VO Class 구현
 * @author 공통서비스 장동한
 * @since 2009.03.20
 * @version 1.0
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2009.03.20  장동한          최초 생성
 *
 * </pre>
 */
@Getter
@Setter
public class TnextrlHrVO extends ComDefaultVO {

	private static final long serialVersionUID = 1L;

	/**
	 * 성별코드
	 */
	@EgovNullCheck
	private String sexdstnCode = "";

	/**
	 * 성별코드명
	 */
	private String sexdstnCodeNm = "";

	/**
	 * 외부인사명
	 */
	@EgovNullCheck
	private String extrlHrNm = "";

	/**
	 * 이메일주소
	 */
	@EgovNullCheck
	@EgovEmailCheck
	private String emailAdres = "";

	/**
	 * 직업유형코드
	 */
	@EgovNullCheck
	private String occpTyCode = "";

	/**
	 * 직업유형코드명
	 */
	private String occpTyCodeNm = "";

	/**
	 * 소속기관명
	 */
	private String psitnInsttNm = "";

	/**
	 * 외부인사ID
	 */
	private String extrlHrId = "";

	/**
	 * 행사/이벤트/캠페인 아이디
	 */
	@EgovNullCheck
	private String eventId = "";

	/**
	 * 행사/이벤트/캠페인내용
	 */
	private String eventCn = "";

	/**
	 * 생년월일
	 */
	private String brth = "";

	/**
	 * 지역번호
	 */
	@EgovNullCheck
	@Size(max=4)
	@Pattern(regexp="^[0-9]*$", message="{validation.pattern.integer}")
	private String areaNo = "";

	/**
	 * 중간전화번호
	 */
	@EgovNullCheck
	@Size(max=4)
	@Pattern(regexp="^[0-9]*$", message="{validation.pattern.integer}")
	private String middleTelno = "";

	/**
	 * 끝전화번호
	 */
	@EgovNullCheck
	@Size(max=4)
	@Pattern(regexp="^[0-9]*$", message="{validation.pattern.integer}")
	private String endTelno = "";

	/**
	 * 생년월일(년)
	 */
	private String brthYYYY = "";

	/**
	 * 생년월일(월)
	 */
	private String brthMM = "";

	/**
	 * 생년월일(일)
	 */
	private String brthDD = "";

	/**
	 * 최초등록시점
	 */
	private String frstRegisterPnttm = "";

	/**
	 * 최초등록ID
	 */
	private String frstRegisterId = "";

	/**
	 * 최초등록ID
	 */
	private String frstRegisterNm = "";

	/**
	 * 최종수정시점
	 */
	private String lastUpdusrPnttm = "";

	/**
	 * 최종수정ID
	 */
	private String lastUpdusrId = "";

}
