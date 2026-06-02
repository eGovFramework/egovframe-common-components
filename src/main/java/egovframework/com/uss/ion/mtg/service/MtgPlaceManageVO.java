package egovframework.com.uss.ion.mtg.service;

import java.util.List;

import egovframework.com.cmm.ComDefaultVO;
import lombok.Getter;
import lombok.Setter;
import org.egovframe.rte.ptl.reactive.validation.EgovNullCheck;
import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;

/**
 * 개요
 * - 회의실관리에 대한 Vo 클래스를 정의한다.
 *
 * 상세내용
 * - 회의실관리의 회의실코드,회의실명,개방시작시간,개방종료시간,수용가능인원,위치구분,위치상세 및 목록 항목을 관리한다.
 * @author 이용
 * @version 1.0
 * @created 06-15-2010 오후 2:08:56
 */
@Getter
@Setter
public class MtgPlaceManageVO extends ComDefaultVO {

	private static final long serialVersionUID = 1L;

	/** 회의실ID */
	private String mtgPlaceId;

	/** 회의실명 */
	@EgovNullCheck
	@Size(max = 255)
	private String mtgPlaceNm;

	/** 개방시작시간 */
	@EgovNullCheck
	private String opnBeginTm;

	/** 개방종료시간 */
	@EgovNullCheck
	private String opnEndTm;

	/** 수용가능인원 */
	@EgovNullCheck
	@Min(1)
	private int aceptncPosblNmpr;

	/** 위치구분 */
	@EgovNullCheck
	private String lcSe;

	/** 위치상세 */
	@EgovNullCheck
	@Size(max = 200)
	private String lcDetail;

	/**
	 * 개방시작시간이 개방종료시간보다 작은지 검증(서버사이드).
	 * @return true: 유효(시작 < 종료), false: 무효
	 */
	@AssertTrue(message = "{comUssIonMtg.mtgPlaceRegist.checkOpenTime}")
	public boolean isOpnTimeRangeValid() {
		if (opnBeginTm == null || opnEndTm == null) return true;
		String begin = opnBeginTm.trim();
		String end = opnEndTm.trim();
		if (begin.isEmpty() || end.isEmpty()) return true;
		return begin.compareTo(end) < 0;
	}

	/** 첨부파일 */
	private String atchFileId;

	/** 최초등록자ID */
	private String frstRegisterId;

	/** 최초등록시점 */
	private String frstRegisterPnttm;

	/** 최종수정자ID */
	private String lastUpdusrId;

	/** 최종수정시점 */
	private String lastUpdusrPnttm;

	/** 회의실관리 목록 */
	private List<MtgPlaceManageVO> mtgPlaceManageList;

	/** 예약ID */
	private String resveId;

	/** 회의제목 */
	private String mtgSj;

	/** 예약자ID */
	private String resveManId;

	/** 예약일자 */
	private String resveDe;

	/** 예약시작시간 */
	private String resveBeginTm;

	/** 예약종료시간 */
	private String resveEndTm;

	/** 참석인원 */
	private int atndncNmpr;

	/** 회의내용 */
	private String mtgCn;

	/** list 번호 */
	private int rowCount;

	/** 출력 변수 1 */
	private String mtgPlaceTemp1;

	/** 출력 변수 2 */
	private String mtgPlaceTemp2;

	/** 출력 변수 3 */
	private String mtgPlaceTemp3;

	/** 출력 변수 4 */
	private String mtgPlaceTemp4;

	/** 출력 변수 5 */
	private String mtgPlaceTemp5;

	/** ID 변수 */
	private String usidTemp;

	/** 시간출력 변수 */
	private String resveTemp0800, resveTemp0830, resveTemp0900, resveTemp0930, resveTemp1000, resveTemp1030;
	private String resveTemp1100, resveTemp1130, resveTemp1200, resveTemp1230, resveTemp1300, resveTemp1330;
	private String resveTemp1400, resveTemp1430, resveTemp1500, resveTemp1530, resveTemp1600, resveTemp1630;
	private String resveTemp1700, resveTemp1730, resveTemp1800, resveTemp1830, resveTemp1900, resveTemp1930;
	private String resveTemp2000, resveTemp2030, resveTemp2100;

	/** resveDeView 변수 */
	private String resveDeView;
}
