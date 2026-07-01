package egovframework.com.cop.smt.sdm.service;

import java.io.Serializable;

import org.egovframe.rte.ptl.reactive.validation.EgovNullCheck;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

/**
 * 부서일정 VO Class 구현
 * @author 공통서비스 장동한
 * @since 2009.04.10
 * @version 1.0
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2009.04.10  장동한          최초 생성
 *
 * </pre>
 */
@Getter
@Setter
@SuppressWarnings("serial")
public class DeptSchdulManageVO implements Serializable {

	/** 일정ID */
	private String schdulId;

	/** 일정구분(회의/교육/세미나/강의 기타) */
	@EgovNullCheck
	private String schdulSe;

	/** 일정부서ID */
	private String schdulDeptId;

	/** 일정종류(부서일정/개인일정) */
	private String schdulKindCode;

	/** 일정시작일자 */
	private String schdulBgnde;

	/** 일정종료일자 */
	private String schdulEndde;

	/** 일정명 */
	@EgovNullCheck
	@Size(max=255)
	private String schdulNm;

	/** 일정내용 */
	@EgovNullCheck
	@Size(max=2500)
	private String schdulCn;

	/** 일정장소 */
	private String schdulPlace;

	/** 일정중요도코드 */
	@EgovNullCheck
	private String schdulIpcrCode;

	/** 일정담담자ID */
	private String schdulChargerId;

	/** 첨부파일ID */
	private String atchFileId;

	/** 반복구분(반복, 연속, 요일반복) */
	private String reptitSeCode;

	/** 최초등록시점 */
	private String frstRegisterPnttm = "";

	/** 최초등록자ID */
	private String frstRegisterId = "";

	/** 최종수정시점 */
	private String lastUpdusrPnttm = "";

	/** 최종수정ID */
	private String lastUpdusrId = "";

	/** 일정시작일자(시간) */
	@EgovNullCheck
	private String schdulBgndeHH = "";

	/** 일정시작일자(분) */
	@EgovNullCheck
	private String schdulBgndeMM = "";

	/** 일정종료일자(시간) */
	@EgovNullCheck
	private String schdulEnddeHH = "";

	/** 일정종료일자(분) */
	@EgovNullCheck
	private String schdulEnddeMM = "";

	/** 일정시작일자(Year/Month/Day) */
	@EgovNullCheck
	private String schdulBgndeYYYMMDD = "";

	/** 일정종료일자(Year/Month/Day) */
	@EgovNullCheck
	private String schdulEnddeYYYMMDD = "";

	/** 담당부서 */
	@EgovNullCheck
	private String schdulDeptName = "";

	/** 담당자명 */
	@EgovNullCheck
	private String schdulChargerName = "";

}
