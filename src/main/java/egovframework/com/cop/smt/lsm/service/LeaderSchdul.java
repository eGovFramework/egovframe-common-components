package egovframework.com.cop.smt.lsm.service;

import java.io.Serializable;

/**
 * 개요
 * - 간부일정에 대한 model 클래스를 정의한다.
 * 
 * 상세내용
 * - 일정ID, 일정구분, 일정명, 일정내용, 일정장소, 간부ID, 반복구분코드, 일정시작일자, 일정종료일자, 일정담당자ID 항목을 관리한다.
 * @author 장철호
 * @version 1.0
 * @created 28-6-2010 오전 10:59:06
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2010.6.28	장철호          최초 생성
 *
 * </pre>
 */
@SuppressWarnings("serial")
public class LeaderSchdul  implements Serializable{

	/** 일정ID */
	private String schdulId;
	/** 일정구분 */
	private String schdulSe;
	/** 일정명 */
	private String schdulNm;
	/** 일정내용 */
	private String schdulCn;
	/** 일정장소 */
	private String schdulPlace;
	/** 간부ID */
	private String leaderId;
	/** 간부명 */
	private String leaderName;
	/** 반복구분코드 */
	private String reptitSeCode;
	/** 일정일자 */
	private String schdulDe;
	/** 일정시작일자 */
	private String schdulBgnDe;
	/** 일정종료일자 */
	private String schdulEndDe;
	/** 일정담당자ID */
	private String schdulChargerId;
	/** 일정담당자명 */
	private String schdulChargerName;
	/** 최초등록자ID */
	private String frstRegisterId = "";
	/** 최초등록시점 */
	private String frstRegisterPnttm = "";
	/** 최종수정자ID */
	private String lastUpdusrId = "";
	/** 최종수정시점 */
	private String lastUpdusrPnttm = "";
	
	/** 일정시작일자(시간) */
	private String schdulBgndeHH = "";
	
	/** 일정시작일자(분) */
	private String schdulBgndeMM = "";
	
	/** 일정종료일자(시간) */
	private String schdulEnddeHH = "";
	
	/** 일정종료일자(분) */
	private String schdulEnddeMM = "";
	
	/** 일정시작일자(Year/Month/Day) */
	private String schdulBgndeYYYMMDD = "";
	
	/** 일정종료일자(Year/Month/Day) */
	private String schdulEnddeYYYMMDD = "";

	public String getSchdulId() {
		return schdulId;
	}

	public void setSchdulId(String schdulId) {
		this.schdulId = schdulId;
	}

	public String getSchdulSe() {
		return schdulSe;
	}

	public void setSchdulSe(String schdulSe) {
		this.schdulSe = schdulSe;
	}

	public String getSchdulNm() {
		return schdulNm;
	}

	public void setSchdulNm(String schdulNm) {
		this.schdulNm = schdulNm;
	}

	public String getSchdulCn() {
		return schdulCn;
	}

	public void setSchdulCn(String schdulCn) {
		this.schdulCn = schdulCn;
	}

	public String getSchdulPlace() {
		return schdulPlace;
	}

	public void setSchdulPlace(String schdulPlace) {
		this.schdulPlace = schdulPlace;
	}

	public String getLeaderId() {
		return leaderId;
	}

	public void setLeaderId(String leaderId) {
		this.leaderId = leaderId;
	}

	public String getLeaderName() {
		return leaderName;
	}

	public void setLeaderName(String leaderName) {
		this.leaderName = leaderName;
	}

	public String getReptitSeCode() {
		return reptitSeCode;
	}

	public void setReptitSeCode(String reptitSeCode) {
		this.reptitSeCode = reptitSeCode;
	}

	public String getSchdulDe() {
		return schdulDe;
	}

	public void setSchdulDe(String schdulDe) {
		this.schdulDe = schdulDe;
	}

	public String getSchdulBgnDe() {
		return schdulBgnDe;
	}

	public void setSchdulBgnDe(String schdulBgnDe) {
		this.schdulBgnDe = schdulBgnDe;
	}

	public String getSchdulEndDe() {
		return schdulEndDe;
	}

	public void setSchdulEndDe(String schdulEndDe) {
		this.schdulEndDe = schdulEndDe;
	}

	public String getSchdulChargerId() {
		return schdulChargerId;
	}

	public void setSchdulChargerId(String schdulChargerId) {
		this.schdulChargerId = schdulChargerId;
	}

	public String getSchdulChargerName() {
		return schdulChargerName;
	}

	public void setSchdulChargerName(String schdulChargerName) {
		this.schdulChargerName = schdulChargerName;
	}

	public String getFrstRegisterId() {
		return frstRegisterId;
	}

	public void setFrstRegisterId(String frstRegisterId) {
		this.frstRegisterId = frstRegisterId;
	}

	public String getFrstRegisterPnttm() {
		return frstRegisterPnttm;
	}

	public void setFrstRegisterPnttm(String frstRegisterPnttm) {
		this.frstRegisterPnttm = frstRegisterPnttm;
	}

	public String getLastUpdusrId() {
		return lastUpdusrId;
	}

	public void setLastUpdusrId(String lastUpdusrId) {
		this.lastUpdusrId = lastUpdusrId;
	}

	public String getLastUpdusrPnttm() {
		return lastUpdusrPnttm;
	}

	public void setLastUpdusrPnttm(String lastUpdusrPnttm) {
		this.lastUpdusrPnttm = lastUpdusrPnttm;
	}

	public String getSchdulBgndeHH() {
		return schdulBgndeHH;
	}

	public void setSchdulBgndeHH(String schdulBgndeHH) {
		this.schdulBgndeHH = schdulBgndeHH;
	}

	public String getSchdulBgndeMM() {
		return schdulBgndeMM;
	}

	public void setSchdulBgndeMM(String schdulBgndeMM) {
		this.schdulBgndeMM = schdulBgndeMM;
	}

	public String getSchdulEnddeHH() {
		return schdulEnddeHH;
	}

	public void setSchdulEnddeHH(String schdulEnddeHH) {
		this.schdulEnddeHH = schdulEnddeHH;
	}

	public String getSchdulEnddeMM() {
		return schdulEnddeMM;
	}

	public void setSchdulEnddeMM(String schdulEnddeMM) {
		this.schdulEnddeMM = schdulEnddeMM;
	}

	public String getSchdulBgndeYYYMMDD() {
		return schdulBgndeYYYMMDD;
	}

	public void setSchdulBgndeYYYMMDD(String schdulBgndeYYYMMDD) {
		this.schdulBgndeYYYMMDD = schdulBgndeYYYMMDD;
	}

	public String getSchdulEnddeYYYMMDD() {
		return schdulEnddeYYYMMDD;
	}

	public void setSchdulEnddeYYYMMDD(String schdulEnddeYYYMMDD) {
		this.schdulEnddeYYYMMDD = schdulEnddeYYYMMDD;
	}

}