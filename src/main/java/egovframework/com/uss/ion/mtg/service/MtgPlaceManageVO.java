package egovframework.com.uss.ion.mtg.service;

import java.util.List;

import egovframework.com.cmm.ComDefaultVO;
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

	// --- getters/setters (MtgPlaceManage fields) ---
	public String getMtgPlaceId() { return mtgPlaceId; }
	public void setMtgPlaceId(String mtgPlaceId) { this.mtgPlaceId = mtgPlaceId; }
	public String getMtgPlaceNm() { return mtgPlaceNm; }
	public void setMtgPlaceNm(String mtgPlaceNm) { this.mtgPlaceNm = mtgPlaceNm; }
	public String getOpnBeginTm() { return opnBeginTm; }
	public void setOpnBeginTm(String opnBeginTm) { this.opnBeginTm = opnBeginTm; }
	public String getOpnEndTm() { return opnEndTm; }
	public void setOpnEndTm(String opnEndTm) { this.opnEndTm = opnEndTm; }
	public int getAceptncPosblNmpr() { return aceptncPosblNmpr; }
	public void setAceptncPosblNmpr(int aceptncPosblNmpr) { this.aceptncPosblNmpr = aceptncPosblNmpr; }
	public String getLcSe() { return lcSe; }
	public void setLcSe(String lcSe) { this.lcSe = lcSe; }
	public String getLcDetail() { return lcDetail; }
	public void setLcDetail(String lcDetail) { this.lcDetail = lcDetail; }
	public String getAtchFileId() { return atchFileId; }
	public void setAtchFileId(String atchFileId) { this.atchFileId = atchFileId; }
	public String getFrstRegisterId() { return frstRegisterId; }
	public void setFrstRegisterId(String frstRegisterId) { this.frstRegisterId = frstRegisterId; }
	public String getFrstRegisterPnttm() { return frstRegisterPnttm; }
	public void setFrstRegisterPnttm(String frstRegisterPnttm) { this.frstRegisterPnttm = frstRegisterPnttm; }
	public String getLastUpdusrId() { return lastUpdusrId; }
	public void setLastUpdusrId(String lastUpdusrId) { this.lastUpdusrId = lastUpdusrId; }
	public String getLastUpdusrPnttm() { return lastUpdusrPnttm; }
	public void setLastUpdusrPnttm(String lastUpdusrPnttm) { this.lastUpdusrPnttm = lastUpdusrPnttm; }

	// --- VO/list/resve fields ---
	public String getResveDeView() { return resveDeView; }
	public void setResveDeView(String resveDeView) { this.resveDeView = resveDeView; }
	public String getResveId() { return resveId; }
	public void setResveId(String resveId) { this.resveId = resveId; }
	public String getMtgSj() { return mtgSj; }
	public void setMtgSj(String mtgSj) { this.mtgSj = mtgSj; }
	public String getResveManId() { return resveManId; }
	public void setResveManId(String resveManId) { this.resveManId = resveManId; }
	public String getResveDe() { return resveDe; }
	public void setResveDe(String resveDe) { this.resveDe = resveDe; }
	public String getResveBeginTm() { return resveBeginTm; }
	public void setResveBeginTm(String resveBeginTm) { this.resveBeginTm = resveBeginTm; }
	public String getResveEndTm() { return resveEndTm; }
	public void setResveEndTm(String resveEndTm) { this.resveEndTm = resveEndTm; }
	public int getAtndncNmpr() { return atndncNmpr; }
	public void setAtndncNmpr(int atndncNmpr) { this.atndncNmpr = atndncNmpr; }
	public String getMtgCn() { return mtgCn; }
	public void setMtgCn(String mtgCn) { this.mtgCn = mtgCn; }
	public List<MtgPlaceManageVO> getMtgPlaceManageList() { return mtgPlaceManageList; }
	public void setMtgPlaceManageList(List<MtgPlaceManageVO> mtgPlaceManageList) { this.mtgPlaceManageList = mtgPlaceManageList; }
	public int getRowCount() { return rowCount; }
	public void setRowCount(int rowCount) { this.rowCount = rowCount; }
	public String getMtgPlaceTemp1() { return mtgPlaceTemp1; }
	public void setMtgPlaceTemp1(String mtgPlaceTemp1) { this.mtgPlaceTemp1 = mtgPlaceTemp1; }
	public String getMtgPlaceTemp2() { return mtgPlaceTemp2; }
	public void setMtgPlaceTemp2(String mtgPlaceTemp2) { this.mtgPlaceTemp2 = mtgPlaceTemp2; }
	public String getMtgPlaceTemp3() { return mtgPlaceTemp3; }
	public void setMtgPlaceTemp3(String mtgPlaceTemp3) { this.mtgPlaceTemp3 = mtgPlaceTemp3; }
	public String getMtgPlaceTemp4() { return mtgPlaceTemp4; }
	public void setMtgPlaceTemp4(String mtgPlaceTemp4) { this.mtgPlaceTemp4 = mtgPlaceTemp4; }
	public String getMtgPlaceTemp5() { return mtgPlaceTemp5; }
	public void setMtgPlaceTemp5(String mtgPlaceTemp5) { this.mtgPlaceTemp5 = mtgPlaceTemp5; }
	public String getUsidTemp() { return usidTemp; }
	public void setUsidTemp(String usidTemp) { this.usidTemp = usidTemp; }

	public String getResveTemp0800() { return resveTemp0800; }
	public void setResveTemp0800(String v) { this.resveTemp0800 = v; }
	public String getResveTemp0830() { return resveTemp0830; }
	public void setResveTemp0830(String v) { this.resveTemp0830 = v; }
	public String getResveTemp0900() { return resveTemp0900; }
	public void setResveTemp0900(String v) { this.resveTemp0900 = v; }
	public String getResveTemp0930() { return resveTemp0930; }
	public void setResveTemp0930(String v) { this.resveTemp0930 = v; }
	public String getResveTemp1000() { return resveTemp1000; }
	public void setResveTemp1000(String v) { this.resveTemp1000 = v; }
	public String getResveTemp1030() { return resveTemp1030; }
	public void setResveTemp1030(String v) { this.resveTemp1030 = v; }
	public String getResveTemp1100() { return resveTemp1100; }
	public void setResveTemp1100(String v) { this.resveTemp1100 = v; }
	public String getResveTemp1130() { return resveTemp1130; }
	public void setResveTemp1130(String v) { this.resveTemp1130 = v; }
	public String getResveTemp1200() { return resveTemp1200; }
	public void setResveTemp1200(String v) { this.resveTemp1200 = v; }
	public String getResveTemp1230() { return resveTemp1230; }
	public void setResveTemp1230(String v) { this.resveTemp1230 = v; }
	public String getResveTemp1300() { return resveTemp1300; }
	public void setResveTemp1300(String v) { this.resveTemp1300 = v; }
	public String getResveTemp1330() { return resveTemp1330; }
	public void setResveTemp1330(String v) { this.resveTemp1330 = v; }
	public String getResveTemp1400() { return resveTemp1400; }
	public void setResveTemp1400(String v) { this.resveTemp1400 = v; }
	public String getResveTemp1430() { return resveTemp1430; }
	public void setResveTemp1430(String v) { this.resveTemp1430 = v; }
	public String getResveTemp1500() { return resveTemp1500; }
	public void setResveTemp1500(String v) { this.resveTemp1500 = v; }
	public String getResveTemp1530() { return resveTemp1530; }
	public void setResveTemp1530(String v) { this.resveTemp1530 = v; }
	public String getResveTemp1600() { return resveTemp1600; }
	public void setResveTemp1600(String v) { this.resveTemp1600 = v; }
	public String getResveTemp1630() { return resveTemp1630; }
	public void setResveTemp1630(String v) { this.resveTemp1630 = v; }
	public String getResveTemp1700() { return resveTemp1700; }
	public void setResveTemp1700(String v) { this.resveTemp1700 = v; }
	public String getResveTemp1730() { return resveTemp1730; }
	public void setResveTemp1730(String v) { this.resveTemp1730 = v; }
	public String getResveTemp1800() { return resveTemp1800; }
	public void setResveTemp1800(String v) { this.resveTemp1800 = v; }
	public String getResveTemp1830() { return resveTemp1830; }
	public void setResveTemp1830(String v) { this.resveTemp1830 = v; }
	public String getResveTemp1900() { return resveTemp1900; }
	public void setResveTemp1900(String v) { this.resveTemp1900 = v; }
	public String getResveTemp1930() { return resveTemp1930; }
	public void setResveTemp1930(String v) { this.resveTemp1930 = v; }
	public String getResveTemp2000() { return resveTemp2000; }
	public void setResveTemp2000(String v) { this.resveTemp2000 = v; }
	public String getResveTemp2030() { return resveTemp2030; }
	public void setResveTemp2030(String v) { this.resveTemp2030 = v; }
	public String getResveTemp2100() { return resveTemp2100; }
	public void setResveTemp2100(String v) { this.resveTemp2100 = v; }
}
