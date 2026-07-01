package egovframework.com.uss.olp.mgt.service;

import java.io.Serializable;

import org.egovframe.rte.ptl.reactive.validation.EgovNullCheck;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

/**
 * 회의관리 Vo Class 구현
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
public class MeetingManageVO implements Serializable {

	private static final long serialVersionUID = -4820974750521985908L;

	/** 회의ID */
	private String mtgId = "";

	/** 회의명 */
	@EgovNullCheck
	@Size(max=250)
	private String mtgNm = "";

	/** 회의안건내용 */
	@EgovNullCheck
	@Size(max=1000)
	private String mtgMtrCn = "";

	/** 회의순번 */
	@EgovNullCheck
	@Size(max=10)
	@Pattern(regexp="^[0-9]*$", message="{validation.pattern.integer}")
	private String mtgSn = "";

	/** 회의수 */
	@EgovNullCheck
	@Size(max=5)
	@Pattern(regexp="^[0-9]*$", message="{validation.pattern.integer}")
	private String mtgCo = "";

	/** 회의일자 */
	@EgovNullCheck
	@Pattern(regexp="^\\d{4}-\\d{2}-\\d{2}$", message="{validation.pattern.date}")
	private String mtgDe = "";

	/** 회의장소 */
	@EgovNullCheck
	@Size(max=250)
	private String mtgPlace = "";

	/** 회의시작시간 */
	private String mtgBeginTime = "";

	/** 회의종료시간 */
	private String mtgEndTime = "";

	/** 비공개회의여부 */
	private String clsdrMtgAt = "";

	/** 열람개시일자 */
	@EgovNullCheck
	@Pattern(regexp="^\\d{4}-\\d{2}-\\d{2}$", message="{validation.pattern.date}")
	private String readngBeginDe = "";

	/** 열람여부 */
	private String readngAt = "";

	/** 회의결과내용 */
	@Size(max=1000)
	private String mtgResultCn = "";

	/** 회의결과유무 */
	private String mtgResultEnnc = "";

	/** 기타사항 */
	@Size(max=1000)
	private String etcMatter = "";

	/** 주관부서ID */
	private String mngtDeptId = "";

	/** 주관부서명 */
	private String mngtDeptNm = "";

	/** 주관자ID */
	private String mnaerId = "";

	/** 주관자명 */
	private String mnaerNm = "";

	/** 주관자명 */
	private String mnaerDeptId = "";

	/** 주관자부서명 */
	private String mnaerDeptNm = "";

	/** 주관자직위직급코드 */
	private String mnaerOfcpsClsfCode = "";

	/** 회의여부 */
	private String mtnAt = "";

	/** 불참석자수 */
	@Pattern(regexp="^[0-9]*$", message="{validation.pattern.integer}")
	private String nonatdrnCo = "";

	/** 참석자수 */
	@Pattern(regexp="^[0-9]*$", message="{validation.pattern.integer}")
	private String atdrnCo = "";

	/** 회의시작 시간 */
	@EgovNullCheck
	private String mtgBeginHH = "";

	/** 회의시작 분 */
	@EgovNullCheck
	private String mtgBeginMM = "";

	/** 회의종료 시간 */
	@EgovNullCheck
	private String mtgEndHH = "";

	/** 회의종료 분 */
	@EgovNullCheck
	private String mtgEndMM = "";

	/** 최초등록시점  */
	private String frstRegisterPnttm = "";

	/** 최초등록아이디 */
	private String frstRegisterId = "";

	/** 최종수정일 */
	private String lastUpdusrPnttm = "";

	/** 최종수정자 아이디 */
	private String lastUpdusrId = "";

	/** 화면 명령 처리 */
	private String cmd = "";

}
