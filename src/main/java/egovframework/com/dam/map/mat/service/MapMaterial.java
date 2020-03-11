package egovframework.com.dam.map.mat.service;

/**
 * 개요
 * - 지식맵(지식유형)에 대한 model 클래스를 정의한다.
 * 
 * 상세내용
 * - 지식유형, 조직분류, 분류일자, 지식URL, 전문가ID 항목을 관리한다.
 * @author 박종선
 * @version 1.0
 * @created 12-8-2010 오후 3:44:52
 */
public class MapMaterial {

	/**
	 * 지식유형코드
	 */
	private String knoTypeCd = "";
	/**
	 * 조직ID
	 */
	private String orgnztId = "";
	/**
	 * 조직명
	 */
	private String orgnztNm = "";	
	/**
	 * 전문가ID
	 */
	private String speId = "";
	/**
	 * 지식유형명
	 */
	private String knoTypeNm = "";
	/**
	 * 분류일자
	 */
	private String clYmd = "";
	/**
	 * 지식URL
	 */
	private String knoUrl = "";
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
	private String lastUpdusrId = "";
	/**
	 * 최종수정시점
	 */
	private String lastUpdusrPnttm = "";
	
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
	 * @return the clYmd
	 */
	public String getClYmd() {
		return clYmd;
	}
	/**
	 * @param clYmd the clYmd to set
	 */
	public void setClYmd(String clYmd) {
		this.clYmd = clYmd;
	}
	/**
	 * @return the knoUrl
	 */
	public String getKnoUrl() {
		return knoUrl;
	}
	/**
	 * @param knoUrl the knoUrl to set
	 */
	public void setKnoUrl(String knoUrl) {
		this.knoUrl = knoUrl;
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