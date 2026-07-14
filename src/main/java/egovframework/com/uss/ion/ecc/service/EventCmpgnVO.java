package egovframework.com.uss.ion.ecc.service;

import egovframework.com.cmm.ComDefaultVO;
import lombok.Getter;
import lombok.Setter;

import org.egovframe.rte.ptl.reactive.validation.EgovNullCheck;
import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
/**
 * 행사/이벤트/캠페인 VO Class 구현
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
public class EventCmpgnVO extends ComDefaultVO {

	private static final long serialVersionUID = 1L;

	/**
	 * 행사/이벤트/캠페인ID
	 */
	private String eventId = "";

	/**
	 * 사업연도
	 */
	private String bsnsYear = "";

	/**
	 * 사업코드
	 */
	private String bsnsCode = "";

	/**
	 * 행사시작일자
	 */
	@EgovNullCheck
	@Pattern(regexp="^\\d{4}-\\d{2}-\\d{2}$", message="{validation.pattern.date}")
	private String eventSvcBeginDe = "";

	/**
	 * 서비스이용 인원수
	 */
	@Min(0)
	private int svcUseNmprCo = 0;

	/**
	 * 담당자명
	 */
	private String chargerNm = "";

	/**
	 * 행사내용
	 */
	@EgovNullCheck
	@Size(max=1000)
	private String eventCn = "";

	/**
	 * 행사종료일자
	 */
	@EgovNullCheck
	@Pattern(regexp="^\\d{4}-\\d{2}-\\d{2}$", message="{validation.pattern.date}")
	private String eventSvcEndDe = "";

	/**
	 * 행사유형코드
	 */
	@EgovNullCheck
	private String eventTyCode = "";

	/**
	 * 행사유형코드명
	 */
	private String eventTyCodeNm = "";

	/**
	 * 준비물내용
	 */
	@Size(max=1000)
	private String prparetgCn = "";

	/**
	 * 최초등록시점
	 */
	private String frstRegisterPnttm = "";
	/**
	 * 최초등록ID
	 */
	private String frstRegisterId = "";
	/**
	 * 최초등록자
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
	/**
	 * 화면처리 명령어
	 */
	private String cmd = "";


	/**
	 * 행사/이벤트 승인여부
	 */
	@EgovNullCheck
	private String eventConfmAt = "";

	/**
	 * 행사/이벤트 승인일
	 */
	@EgovNullCheck
	@Pattern(regexp="^\\d{4}-\\d{2}-\\d{2}$", message="{validation.pattern.date}")
	private String eventConfmDe = "";

	/**
	 * 행사시작일이 행사종료일보다 이전인지 검증 (시작일 &lt;= 종료일)
	 *
	 * @return true: 유효 (시작일 &lt;= 종료일 또는 둘 중 하나 미입력), false: 무효
	 */
	@AssertTrue(message = "{comUssIonEcc.eventCmpgnVO.eventSvcDateRange}")
	public boolean isEventSvcDateRangeValid() {
		if (eventSvcBeginDe == null || eventSvcEndDe == null) {
			return true;
		}
		String begin = eventSvcBeginDe.trim();
		String end = eventSvcEndDe.trim();
		if (begin.isEmpty() || end.isEmpty()) {
			return true;
		}
		return begin.compareTo(end) <= 0;
	}

}
