package egovframework.com.uss.ion.ecc.service;

import java.io.Serializable;

import egovframework.com.cmm.ComDefaultVO;
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
public class EventCmpgnVO extends ComDefaultVO implements Serializable {

	private static final long serialVersionUID = -5817021412630105195L;

	/**
	 * 행사/이벤트/캠페인ID
	 */
	private String eventId = "";

	/**
	 * 사업년도
	 */
	private String bsnsYear = "";

	/**
	 * 사업코드
	 */
	private String bsnsCode = "";

	/**
	 * 행사시작일자
	 */
	private String eventSvcBeginDe = "";

	/**
	 * 서비스이용 인원수
	 */
	private int svcUseNmprCo = 0;

	/**
	 * 담당자명
	 */
	private String chargerNm = "";

	/**
	 * 행사내용
	 */
	private String eventCn = "";

	/**
	 * 행사종료일자
	 */
	private String eventSvcEndDe = "";

	/**
	 * 행사유형코드
	 */
	private String eventTyCode = "";
	
	/**
	 * 행사유형코드명
	 */
	private String eventTyCodeNm = "";

	/**
	 * 준비물내용
	 */
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
	private String eventConfmAt = "";

	/**
	 * 행사/이벤트 승인일
	 */
	private String eventConfmDe = "";


	/**
	 * eventConfmDe attribute 를 리턴한다.
	 * @return the String
	 */
	public String getEventConfmDe() {
		return eventConfmDe;
	}
	/**
	 * eventConfmDe attribute 값을 설정한다.
	 * @return eventId String
	 */
	public void setEventConfmDe(String eventConfmDe) {
		this.eventConfmDe = eventConfmDe;
	}

	/**
	 * eventConfmAt attribute 를 리턴한다.
	 * @return the String
	 */
	public String getEventConfmAt() {
		return eventConfmAt;
	}
	/**
	 * eventConfmAt attribute 값을 설정한다.
	 * @return eventId String
	 */
	public void setEventConfmAt(String eventConfmAt) {
		this.eventConfmAt = eventConfmAt;
	}


	/**
	 * eventId attribute 를 리턴한다.
	 * @return the String
	 */
	public String getEventId() {
		return eventId;
	}
	/**
	 * eventId attribute 값을 설정한다.
	 * @return eventId String
	 */
	public void setEventId(String eventId) {
		this.eventId = eventId;
	}
	/**
	 * bsnsYear attribute 를 리턴한다.
	 * @return the String
	 */
	public String getBsnsYear() {
		return bsnsYear;
	}
	/**
	 * bsnsYear attribute 값을 설정한다.
	 * @return bsnsYear String
	 */
	public void setBsnsYear(String bsnsYear) {
		this.bsnsYear = bsnsYear;
	}
	/**
	 * bsnsCode attribute 를 리턴한다.
	 * @return the String
	 */
	public String getBsnsCode() {
		return bsnsCode;
	}
	/**
	 * bsnsCode attribute 값을 설정한다.
	 * @return bsnsCode String
	 */
	public void setBsnsCode(String bsnsCode) {
		this.bsnsCode = bsnsCode;
	}
	/**
	 * eventSvcBeginDe attribute 를 리턴한다.
	 * @return the String
	 */
	public String getEventSvcBeginDe() {
		return eventSvcBeginDe;
	}
	/**
	 * eventSvcBeginDe attribute 값을 설정한다.
	 * @return eventSvcBeginDe String
	 */
	public void setEventSvcBeginDe(String eventSvcBeginDe) {
		this.eventSvcBeginDe = eventSvcBeginDe;
	}
	/**
	 * svcUseNmprCo attribute 를 리턴한다.
	 * @return the int
	 */
	public int getSvcUseNmprCo() {
		return svcUseNmprCo;
	}
	/**
	 * svcUseNmprCo attribute 값을 설정한다.
	 * @return svcUseNmprCo int
	 */
	public void setSvcUseNmprCo(int svcUseNmprCo) {
		this.svcUseNmprCo = svcUseNmprCo;
	}
	/**
	 * chargerNm attribute 를 리턴한다.
	 * @return the String
	 */
	public String getChargerNm() {
		return chargerNm;
	}
	/**
	 * chargerNm attribute 값을 설정한다.
	 * @return chargerNm String
	 */
	public void setChargerNm(String chargerNm) {
		this.chargerNm = chargerNm;
	}
	/**
	 * eventCn attribute 를 리턴한다.
	 * @return the String
	 */
	public String getEventCn() {
		return eventCn;
	}
	/**
	 * eventCn attribute 값을 설정한다.
	 * @return eventCn String
	 */
	public void setEventCn(String eventCn) {
		this.eventCn = eventCn;
	}
	/**
	 * eventSvcEndDe attribute 를 리턴한다.
	 * @return the String
	 */
	public String getEventSvcEndDe() {
		return eventSvcEndDe;
	}
	/**
	 * eventSvcEndDe attribute 값을 설정한다.
	 * @return eventSvcEndDe String
	 */
	public void setEventSvcEndDe(String eventSvcEndDe) {
		this.eventSvcEndDe = eventSvcEndDe;
	}
	/**
	 * eventTyCode attribute 를 리턴한다.
	 * @return the String
	 */
	public String getEventTyCode() {
		return eventTyCode;
	}
	/**
	 * eventTyCode attribute 값을 설정한다.
	 * @return eventTyCode String
	 */
	public void setEventTyCode(String eventTyCode) {
		this.eventTyCode = eventTyCode;
	}
	/**
	 * eventTyCodeNm attribute 를 리턴한다.
	 * @return the String
	 */
	public String getEventTyCodeNm() {
		return eventTyCodeNm;
	}
	/**
	 * eventTyCodeNm attribute 값을 설정한다.
	 * @return eventTyCodeNm String
	 */
	public void setEventTyCodeNm(String eventTyCodeNm) {
		this.eventTyCodeNm = eventTyCodeNm;
	}
	/**
	 * prparetgCn attribute 를 리턴한다.
	 * @return the String
	 */
	public String getPrparetgCn() {
		return prparetgCn;
	}
	/**
	 * prparetgCn attribute 값을 설정한다.
	 * @return prparetgCn String
	 */
	public void setPrparetgCn(String prparetgCn) {
		this.prparetgCn = prparetgCn;
	}
	/**
	 * frstRegisterPnttm attribute 를 리턴한다.
	 * @return the String
	 */
	public String getFrstRegisterPnttm() {
		return frstRegisterPnttm;
	}
	/**
	 * frstRegisterPnttm attribute 값을 설정한다.
	 * @return frstRegisterPnttm String
	 */
	public void setFrstRegisterPnttm(String frstRegisterPnttm) {
		this.frstRegisterPnttm = frstRegisterPnttm;
	}
	/**
	 * frstRegisterId attribute 를 리턴한다.
	 * @return the String
	 */
	public String getFrstRegisterId() {
		return frstRegisterId;
	}
	/**
	 * frstRegisterId attribute 값을 설정한다.
	 * @return frstRegisterId String
	 */
	public void setFrstRegisterId(String frstRegisterId) {
		this.frstRegisterId = frstRegisterId;
	}
	/**
	 * frstRegisterNm attribute 를 리턴한다.
	 * @return the String
	 */
	public String getFrstRegisterNm() {
		return frstRegisterNm;
	}
	/**
	 * frstRegisterNm attribute 값을 설정한다.
	 * @return frstRegisterNm String
	 */
	public void setFrstRegisterNm(String frstRegisterNm) {
		this.frstRegisterNm = frstRegisterNm;
	}
	/**
	 * lastUpdusrPnttm attribute 를 리턴한다.
	 * @return the String
	 */
	public String getLastUpdusrPnttm() {
		return lastUpdusrPnttm;
	}
	/**
	 * lastUpdusrPnttm attribute 값을 설정한다.
	 * @return lastUpdusrPnttm String
	 */
	public void setLastUpdusrPnttm(String lastUpdusrPnttm) {
		this.lastUpdusrPnttm = lastUpdusrPnttm;
	}
	/**
	 * lastUpdusrId attribute 를 리턴한다.
	 * @return the String
	 */
	public String getLastUpdusrId() {
		return lastUpdusrId;
	}
	/**
	 * lastUpdusrId attribute 값을 설정한다.
	 * @return lastUpdusrId String
	 */
	public void setLastUpdusrId(String lastUpdusrId) {
		this.lastUpdusrId = lastUpdusrId;
	}
	/**
	 * cmd attribute 를 리턴한다.
	 * @return the String
	 */
	public String getCmd() {
		return cmd;
	}
	/**
	 * cmd attribute 값을 설정한다.
	 * @return cmd String
	 */
	public void setCmd(String cmd) {
		this.cmd = cmd;
	}





}
