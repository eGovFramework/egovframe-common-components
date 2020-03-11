package egovframework.com.dam.spe.spe.service;

/**
 * 개요
 * - 지식전문가에 대한 model 클래스를 정의한다.
 * 
 * 상세내용
 * - 소속조직, 지식유형, 승인일자, 전문가성명, 전문지식명 항목을 관리한다.
 * @author 박종선
 * @version 1.0
 * @created 12-8-2010 오후 3:44:51
 */
public class KnoSpecialist {

	/**
	 * 전문가ID
	 */
	private String speId;
	/**
	 * 전문가명
	 */
	private String userNm;
	/**
	 * 소속조직ID
	 */
	private String orgnztId;
	/**
	 * 소속조직명
	 */
	private String orgnztNm;	
	/**
	 * 지식유형코드
	 */
	private String knoTypeCd;
	/**
	 * 승인유형코드
	 */
	private String appTypeCd;
	/**
	 * 승인유형명
	 */
	private String appTypeNm;	
	/**
	 * 지식유형명
	 */
	private String knoTypeNm;
	/**
	 * 전문가설명
	 */
	private String speExpCn;	
	/**
	 * 전문가승인일
	 */
	private String speConfmDe;
	/** 
	 * 최초등록아이디
	 */
	private String frstRegisterId = "";		
	/** 
	 * 최초등록시점
	 */
	private String frstRegisterPnttm = "";
	/**
	 * 최종수정자ID
	 */
	private String lastUpdusrId;
	/**
	 * 최종수정시점
	 */
	private String lastUpdusrPnttm;
	/**
	 * @return the speId
	 */
	public String getSpeId() {
		return speId;
	}
	/**
	 * @param speId the speId to set
	 */
	public void setSpeId(String speId) {
		this.speId = speId;
	}
	/**
	 * @return the userNm
	 */
	public String getUserNm() {
		return userNm;
	}
	/**
	 * @param userNm the userNm to set
	 */
	public void setUserNm(String userNm) {
		this.userNm = userNm;
	}
	/**
	 * @return the orgnztId
	 */
	public String getOrgnztId() {
		return orgnztId;
	}
	/**
	 * @param orgnztId the orgnztId to set
	 */
	public void setOrgnztId(String orgnztId) {
		this.orgnztId = orgnztId;
	}
	/**
	 * @return the orgnztNm
	 */
	public String getOrgnztNm() {
		return orgnztNm;
	}
	/**
	 * @param orgnztNm the orgnztNm to set
	 */
	public void setOrgnztNm(String orgnztNm) {
		this.orgnztNm = orgnztNm;
	}
	/**
	 * @return the knoTypeCd
	 */
	public String getKnoTypeCd() {
		return knoTypeCd;
	}
	/**
	 * @param knoTypeCd the knoTypeCd to set
	 */
	public void setKnoTypeCd(String knoTypeCd) {
		this.knoTypeCd = knoTypeCd;
	}
	/**
	 * @return the knoTypeNm
	 */
	public String getKnoTypeNm() {
		return knoTypeNm;
	}
	/**
	 * @param knoTypeNm the knoTypeNm to set
	 */
	public void setKnoTypeNm(String knoTypeNm) {
		this.knoTypeNm = knoTypeNm;
	}
	/**
	 * @return the appTypeCd
	 */
	public String getAppTypeCd() {
		return appTypeCd;
	}
	/**
	 * @param appTypeCd the appTypeCd to set
	 */
	public void setAppTypeCd(String appTypeCd) {
		this.appTypeCd = appTypeCd;
	}
	/**
	 * @return the appTypeNm
	 */
	public String getAppTypeNm() {
		return appTypeNm;
	}
	/**
	 * @param appTypeNm the appTypeNm to set
	 */
	public void setAppTypeNm(String appTypeNm) {
		this.appTypeNm = appTypeNm;
	}
	/**
	 * @return the speConfmDe
	 */
	public String getSpeConfmDe() {
		return speConfmDe;
	}
	/**
	 * @param speConfmDe the speConfmDe to set
	 */
	public void setSpeConfmDe(String speConfmDe) {
		this.speConfmDe = speConfmDe;
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
	/**
	 * @return the speExpCn
	 */
	public String getSpeExpCn() {
		return speExpCn;
	}
	/**
	 * @param speExpCn the speExpCn to set
	 */
	public void setSpeExpCn(String speExpCn) {
		this.speExpCn = speExpCn;
	}

}