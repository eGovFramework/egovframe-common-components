package egovframework.com.uss.ion.evt.service;

import egovframework.com.cmm.ComDefaultVO;

/**
 * 개요
 * - 행사관리에 대한 model 클래스를 정의한다.
 * 
 * 상세내용
 * - 행사관리의 행사ID,행사구분,행사명,행사목적,행사시작일자,행사종료일자,행사주최기관명,행사주관기관명,행사장소,행사내용,비용발생여부,참가비용,정원,참조URL,접수시작일자,접수종료일자,최초등록자ID,최초등록시점,최종수정자ID,최종수정시점 항목을 관리한다.
 * @author 이용
 * @version 1.0
 * @created 06-15-2010 오후 2:08:56
 */

public class EventManage extends ComDefaultVO {

	/**
	* serialVersionUID
	*/
	private static final long serialVersionUID = 1L;
	
	/**
	*  행사ID	      
	*/ 
	private String eventId;

	/**
	*  행사구분	      
	*/ 
	private String eventSe;

	/**
	*  행사명	      
	*/ 
	private String eventNm;

	/**
	*  행사목적	      
	*/ 
	private String eventPurps;

	/**
	*  행사시작일자	
	*/ 
	private String eventBeginDe;

	/**
	*  행사종료일자	
	*/ 
	private String eventEndDe;

	/**
	*  행사주최기관명	
	*/ 
	private String eventAuspcInsttNm;

	/**
	*  행사주관기관명	
	*/ 
	private String eventMngtInsttNm;

	/**
	*  행사장소	      
	*/ 
	private String eventPlace;

	/**
	*  행사내용	      
	*/ 
	private String eventCn;

	/**
	*  비용발생여부	
	*/ 
	private String ctOccrrncAt;

	/**
	*  참가비용	      
	*/ 
	private int partcptCt;

	/**
	*  정원	         
	*/ 
	private int psncpa;

	/**
	*  참조URL	      
	*/ 
	private String refrnUrl;

	/**
	*  접수시작일자	
	*/ 
	private String rceptBeginDe;

	/**
	*  접수종료일자	
	*/ 
	private String rceptEndDe;

	/**
	*  최초등록자ID	
	*/ 
	private String frstRegisterId;

	/**
	*  최초등록시점	
	*/ 
	private String frstRegisterPnttm;

	/**
	*  최종수정자ID	
	*/ 
	private String lastUpdusrId;

	/**
	*  최종수정시점	
	*/ 
	private String lastUpdusrPnttm;



	/**
	 * @return the eventId
	 */
	public String getEventId() {
		return eventId;
	}

	/**
	 * @param eventId the eventId to set
	 */
	public void setEventId(String eventId) {
		this.eventId = eventId;
	}

	/**
	 * @return the eventSe
	 */
	public String getEventSe() {
		return eventSe;
	}

	/**
	 * @param eventSe the eventSe to set
	 */
	public void setEventSe(String eventSe) {
		this.eventSe = eventSe;
	}

	/**
	 * @return the eventNm
	 */
	public String getEventNm() {
		return eventNm;
	}

	/**
	 * @param eventNm the eventNm to set
	 */
	public void setEventNm(String eventNm) {
		this.eventNm = eventNm;
	}

	/**
	 * @return the eventPurps
	 */
	public String getEventPurps() {
		return eventPurps;
	}

	/**
	 * @param eventPurps the eventPurps to set
	 */
	public void setEventPurps(String eventPurps) {
		this.eventPurps = eventPurps;
	}

	/**
	 * @return the eventBeginDe
	 */
	public String getEventBeginDe() {
		return eventBeginDe;
	}

	/**
	 * @param eventBeginDe the eventBeginDe to set
	 */
	public void setEventBeginDe(String eventBeginDe) {
		this.eventBeginDe = eventBeginDe;
	}

	/**
	 * @return the eventEndDe
	 */
	public String getEventEndDe() {
		return eventEndDe;
	}

	/**
	 * @param eventEndDe the eventEndDe to set
	 */
	public void setEventEndDe(String eventEndDe) {
		this.eventEndDe = eventEndDe;
	}

	/**
	 * @return the eventAuspcInsttNm
	 */
	public String getEventAuspcInsttNm() {
		return eventAuspcInsttNm;
	}

	/**
	 * @param eventAuspcInsttNm the eventAuspcInsttNm to set
	 */
	public void setEventAuspcInsttNm(String eventAuspcInsttNm) {
		this.eventAuspcInsttNm = eventAuspcInsttNm;
	}

	/**
	 * @return the eventMngtInsttNm
	 */
	public String getEventMngtInsttNm() {
		return eventMngtInsttNm;
	}

	/**
	 * @param eventMngtInsttNm the eventMngtInsttNm to set
	 */
	public void setEventMngtInsttNm(String eventMngtInsttNm) {
		this.eventMngtInsttNm = eventMngtInsttNm;
	}

	/**
	 * @return the eventPlace
	 */
	public String getEventPlace() {
		return eventPlace;
	}

	/**
	 * @param eventPlace the eventPlace to set
	 */
	public void setEventPlace(String eventPlace) {
		this.eventPlace = eventPlace;
	}

	/**
	 * @return the eventCn
	 */
	public String getEventCn() {
		return eventCn;
	}

	/**
	 * @param eventCn the eventCn to set
	 */
	public void setEventCn(String eventCn) {
		this.eventCn = eventCn;
	}

	/**
	 * @return the ctOccrrncAt
	 */
	public String getCtOccrrncAt() {
		return ctOccrrncAt;
	}

	/**
	 * @param ctOccrrncAt the ctOccrrncAt to set
	 */
	public void setCtOccrrncAt(String ctOccrrncAt) {
		this.ctOccrrncAt = ctOccrrncAt;
	}

	/**
	 * @return the partcptCt
	 */
	public int getPartcptCt() {
		return partcptCt;
	}

	/**
	 * @param partcptCt the partcptCt to set
	 */
	public void setPartcptCt(int partcptCt) {
		this.partcptCt = partcptCt;
	}

	/**
	 * @return the garden
	 */
	public int getPsncpa() {
		return psncpa;
	}

	/**
	 * @param garden the garden to set
	 */
	public void setPsncpa(int psncpa) {
		this.psncpa = psncpa;
	}

	/**
	 * @return the refrnUrl
	 */
	public String getRefrnUrl() {
		return refrnUrl;
	}

	/**
	 * @param refrnUrl the refrnUrl to set
	 */
	public void setRefrnUrl(String refrnUrl) {
		this.refrnUrl = refrnUrl;
	}

	/**
	 * @return the rceptBeginDe
	 */
	public String getRceptBeginDe() {
		return rceptBeginDe;
	}

	/**
	 * @param rceptBeginDe the rceptBeginDe to set
	 */
	public void setRceptBeginDe(String rceptBeginDe) {
		this.rceptBeginDe = rceptBeginDe;
	}

	/**
	 * @return the rceptEndDe
	 */
	public String getRceptEndDe() {
		return rceptEndDe;
	}

	/**
	 * @param rceptEndDe the rceptEndDe to set
	 */
	public void setRceptEndDe(String rceptEndDe) {
		this.rceptEndDe = rceptEndDe;
	}

	/**
	 * @return the frstRegisterId
	 */
	public String getFrstRegisterId() {
		return frstRegisterId;
	}

	/**
	 * @param frstRegisterId the frstRegisterId to set
	 */
	public void setFrstRegisterId(String frstRegisterId) {
		this.frstRegisterId = frstRegisterId;
	}

	/**
	 * @return the frstRegisterPnttm
	 */
	public String getFrstRegisterPnttm() {
		return frstRegisterPnttm;
	}

	/**
	 * @param frstRegisterPnttm the frstRegisterPnttm to set
	 */
	public void setFrstRegisterPnttm(String frstRegisterPnttm) {
		this.frstRegisterPnttm = frstRegisterPnttm;
	}

	/**
	 * @return the lastUpdusrId
	 */
	public String getLastUpdusrId() {
		return lastUpdusrId;
	}

	/**
	 * @param lastUpdusrId the lastUpdusrId to set
	 */
	public void setLastUpdusrId(String lastUpdusrId) {
		this.lastUpdusrId = lastUpdusrId;
	}

	/**
	 * @return the lastUpdusrPnttm
	 */
	public String getLastUpdusrPnttm() {
		return lastUpdusrPnttm;
	}

	/**
	 * @param lastUpdusrPnttm the lastUpdusrPnttm to set
	 */
	public void setLastUpdusrPnttm(String lastUpdusrPnttm) {
		this.lastUpdusrPnttm = lastUpdusrPnttm;
	}
	
	
}