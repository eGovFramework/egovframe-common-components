package egovframework.com.uss.ion.mtg.service;

import java.util.List;

import egovframework.com.cmm.ComDefaultVO;
import org.egovframe.rte.ptl.reactive.validation.EgovNullCheck;
import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

/**
 * 개요
 * - 회의실예약에 대한 Vo 클래스를 정의한다.
 *
 * 상세내용
 * - 회의실예약의 예약ID,회의실코드,회의제목,예약자ID,예약시작시간,예약종료시간 및 목록 항목을 관리한다.
 * @author 이용
 * @version 1.0
 * @created 06-15-2010 오후 2:08:56
 */
public class MtgPlaceResveVO extends ComDefaultVO {

	private static final long serialVersionUID = 1L;

	/** 예약ID */
	private String resveId;

	/** 회의실코드 */
	private String mtgPlaceId;

	/** 회의제목 */
	@EgovNullCheck
	@Size(max = 100)
	private String mtgSj;

	/** 예약자ID */
	private String resveManId;

	/** 예약일자 */
	@EgovNullCheck
	@Size(max = 10)
	@Pattern(regexp = "^\\d{4}-\\d{2}-\\d{2}$", message = "{validation.pattern.date}")
	private String resveDe;

	/** 예약시작시간 */
	@EgovNullCheck
	private String resveBeginTm;

	/** 예약종료시간 */
	@EgovNullCheck
	private String resveEndTm;

	/** 참석인원 */
	private int atndncNmpr;

	/** 회의내용 */
	@Size(max = 200)
	private String mtgCn;

	/**
	 * 예약시작시간이 예약종료시간보다 이전인지 검증(같을 수 없음).
	 * @return true: 유효(시작 < 종료), false: 무효
	 */
	@AssertTrue(message = "{comUssIonMtg.mtgPlaceResveTimeStartBeforeEnd}")
	public boolean isResveTimeRangeValid() {
		if (resveBeginTm == null || resveEndTm == null) return true;
		int begin = toMinutes(resveBeginTm);
		int end = toMinutes(resveEndTm);
		return begin < end;
	}

	private static int toMinutes(String s) {
		if (s == null) return 0;
		s = s.trim();
		if (s.length() >= 4) {
			int h = Integer.parseInt(s.substring(0, 2), 10);
			int m = Integer.parseInt(s.substring(2, 4), 10);
			return h * 60 + m;
		}
		if (s.length() == 3) {
			int h = Integer.parseInt(s.substring(0, 1), 10);
			int m = Integer.parseInt(s.substring(1, 3), 10);
			return h * 60 + m;
		}
		return 0;
	}

	/** 최초등록자ID */
	private String frstRegisterId;

	/** 최초등록시점 */
	private String frstRegisterPnttm;

	/** 최종수정자ID */
	private String lastUpdusrId;

	/** 최종수정시점 */
	private String lastUpdusrPnttm;

	/** 예약 목록 */
	private List<MtgPlaceResveVO> mtgPlaceResveList;

	public String getResveId() { return resveId; }
	public void setResveId(String resveId) { this.resveId = resveId; }
	public String getMtgPlaceId() { return mtgPlaceId; }
	public void setMtgPlaceId(String mtgPlaceId) { this.mtgPlaceId = mtgPlaceId; }
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
	public String getFrstRegisterId() { return frstRegisterId; }
	public void setFrstRegisterId(String frstRegisterId) { this.frstRegisterId = frstRegisterId; }
	public String getFrstRegisterPnttm() { return frstRegisterPnttm; }
	public void setFrstRegisterPnttm(String frstRegisterPnttm) { this.frstRegisterPnttm = frstRegisterPnttm; }
	public String getLastUpdusrId() { return lastUpdusrId; }
	public void setLastUpdusrId(String lastUpdusrId) { this.lastUpdusrId = lastUpdusrId; }
	public String getLastUpdusrPnttm() { return lastUpdusrPnttm; }
	public void setLastUpdusrPnttm(String lastUpdusrPnttm) { this.lastUpdusrPnttm = lastUpdusrPnttm; }
	public List<MtgPlaceResveVO> getMtgPlaceResveList() { return mtgPlaceResveList; }
	public void setMtgPlaceResveList(List<MtgPlaceResveVO> mtgPlaceResveList) { this.mtgPlaceResveList = mtgPlaceResveList; }
}
