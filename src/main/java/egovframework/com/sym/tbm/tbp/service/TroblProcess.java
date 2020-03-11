package egovframework.com.sym.tbm.tbp.service;

import egovframework.com.cmm.ComDefaultVO;

/**
 * 개요
 * - 장애처리결과정보에 대한 model 클래스를 정의한다.
 * 
 * 상세내용
 * - 장애관리의 장애ID, 장애명, 장애종류, 장애설명, 장애발생시간, 장애요청자명, 장애요청시간, 장애처리결과, 장애처리자명, 장애처리시간,
 * 처리상태 항목을 관리한다.
 * @author lee.m.j
 * @version 1.0
 * @created 28-6-2010 오전 10:44:57
 */
public class TroblProcess extends ComDefaultVO {

	private static final long serialVersionUID = 1L;
	/**
	 * 장애 ID
	 */
	private String troblId;
	/**
	 * 장애 명
	 */
	private String troblNm;
	/**
	 * 장애 종류
	 */
	private String troblKnd;
	/**
	 * 장애 종류 명
	 */
	private String troblKndNm;	
	/**
	 * 장애 설명
	 */
	private String troblDc;
	/**
	 * 장애 발생 시간
	 */
	private String troblOccrrncTime;
	/**
	 * 장애 요청자 명
	 */
	private String troblRqesterNm;
	/**
	 * 장애 요청 시간
	 */
	private String troblRequstTime;
	/**
	 * 장애 처리 결과
	 */
	private String troblProcessResult;
	/**
	 * 장애 처리자 명
	 */
	private String troblOpetrNm;
	/**
	 * 장애 처리 시간
	 */
	private String troblProcessTime;
	/**
	 * 처리 상태
	 */
	private String processSttus;
	/**
	 * 처리 상태명
	 */
	private String processSttusNm;	
    /**
	 * 최초등록시점
	 */   
    private String frstRegisterPnttm;
    /**
	 * 최초등록자ID
	 */        
    private String frstRegisterId;	
	/**
	 * 최종수정시점
	 */
	private String lastUpdusrPnttm;
	/**
	 * 최종수정자ID
	 */
	private String lastUpdusrId;

	/**
	 * @return the troblId
	 */
	public String getTroblId() {
		return troblId;
	}
	/**
	 * @param troblId the troblId to set
	 */
	public void setTroblId(String troblId) {
		this.troblId = troblId;
	}
	/**
	 * @return the troblNm
	 */
	public String getTroblNm() {
		return troblNm;
	}
	/**
	 * @param troblNm the troblNm to set
	 */
	public void setTroblNm(String troblNm) {
		this.troblNm = troblNm;
	}
	/**
	 * @return the troblKnd
	 */
	public String getTroblKnd() {
		return troblKnd;
	}
	/**
	 * @param troblKnd the troblKnd to set
	 */
	public void setTroblKnd(String troblKnd) {
		this.troblKnd = troblKnd;
	}
	/**
	 * @return the troblKndNm
	 */
	public String getTroblKndNm() {
		return troblKndNm;
	}
	/**
	 * @param troblKndNm the troblKndNm to set
	 */
	public void setTroblKndNm(String troblKndNm) {
		this.troblKndNm = troblKndNm;
	}
	/**
	 * @return the troblDc
	 */
	public String getTroblDc() {
		return troblDc;
	}
	/**
	 * @param troblDc the troblDc to set
	 */
	public void setTroblDc(String troblDc) {
		this.troblDc = troblDc;
	}
	/**
	 * @return the troblOccrrncTime
	 */
	public String getTroblOccrrncTime() {
		return troblOccrrncTime;
	}
	/**
	 * @param troblOccrrncTime the troblOccrrncTime to set
	 */
	public void setTroblOccrrncTime(String troblOccrrncTime) {
		this.troblOccrrncTime = troblOccrrncTime;
	}
	/**
	 * @return the troblRqesterNm
	 */
	public String getTroblRqesterNm() {
		return troblRqesterNm;
	}
	/**
	 * @param troblRqesterNm the troblRqesterNm to set
	 */
	public void setTroblRqesterNm(String troblRqesterNm) {
		this.troblRqesterNm = troblRqesterNm;
	}
	/**
	 * @return the troblRequstTime
	 */
	public String getTroblRequstTime() {
		return troblRequstTime;
	}
	/**
	 * @param troblRequstTime the troblRequstTime to set
	 */
	public void setTroblRequstTime(String troblRequstTime) {
		this.troblRequstTime = troblRequstTime;
	}
	/**
	 * @return the troblProcessResult
	 */
	public String getTroblProcessResult() {
		return troblProcessResult;
	}
	/**
	 * @param troblProcessResult the troblProcessResult to set
	 */
	public void setTroblProcessResult(String troblProcessResult) {
		this.troblProcessResult = troblProcessResult;
	}
	/**
	 * @return the troblOpetrNm
	 */
	public String getTroblOpetrNm() {
		return troblOpetrNm;
	}
	/**
	 * @param troblOpetrNm the troblOpetrNm to set
	 */
	public void setTroblOpetrNm(String troblOpetrNm) {
		this.troblOpetrNm = troblOpetrNm;
	}
	/**
	 * @return the troblProcessTime
	 */
	public String getTroblProcessTime() {
		return troblProcessTime;
	}
	/**
	 * @param troblProcessTime the troblProcessTime to set
	 */
	public void setTroblProcessTime(String troblProcessTime) {
		this.troblProcessTime = troblProcessTime;
	}
	/**
	 * @return the processSttus
	 */
	public String getProcessSttus() {
		return processSttus;
	}
	/**
	 * @param processSttus the processSttus to set
	 */
	public void setProcessSttus(String processSttus) {
		this.processSttus = processSttus;
	}
	/**
	 * @return the processSttusNm
	 */
	public String getProcessSttusNm() {
		return processSttusNm;
	}
	/**
	 * @param processSttusNm the processSttusNm to set
	 */
	public void setProcessSttusNm(String processSttusNm) {
		this.processSttusNm = processSttusNm;
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
}