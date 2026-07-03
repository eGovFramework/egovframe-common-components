package egovframework.com.cop.smt.dsm.service;

import java.io.Serializable;

import org.egovframe.rte.ptl.reactive.validation.EgovNullCheck;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import egovframework.com.cmm.ComDefaultVO;
/**
 * 일지관리 VO Class 구현
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
public class DiaryManageVO extends ComDefaultVO implements Serializable {

	/** 일지ID */
	private String diaryId;

	/** 일정내용 */
	private String schdulCn;

	/** 일정ID */
	@EgovNullCheck
	private String schdulId;

	/** 진척률 */
	@EgovNullCheck
	private String diaryProcsPte;

	/** 일정명 */
	@EgovNullCheck
	@Size(max=255)
	private String diaryNm;

	/** 지지사항 */
	@EgovNullCheck
	@Size(max=2500)
	private String drctMatter;

	/** 특이사항 */
	@EgovNullCheck
	@Size(max=2500)
	private String partclrMatter;

	/** 첨부파일 */
	private String atchFileId;

	/** 최초등록시점 */
	private String frstRegisterPnttm = "";

	/** 최초등록자ID */
	private String frstRegisterId = "";

	/** 최종수정시점 */
	private String lastUpdusrPnttm = "";

	/** 최종수정ID */
	private String lastUpdusrId = "";

}
