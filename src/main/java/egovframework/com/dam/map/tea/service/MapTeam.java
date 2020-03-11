package egovframework.com.dam.map.tea.service;

/**
 * 개요
 * - 지식맵(조직별)에 대한 model 클래스를 정의한다.
 * 
 * 상세내용
 * - 조직분류, 등록일자, 지식URL 항목을 관리한다.
 * @author 박종선
 * @version 1.0
 * @created 22-7-2010 오전 10:57:44
 */
public class MapTeam {

	/**
	 * 조직ID
	 */
	private String orgnztId;
	/**
	 * 조직분류
	 */
	private String orgnztNm;
	/**
	 * 분류일자
	 */
	private String clYmd;
	/**
	 * 지식URL
	 */
	private String knoUrl;
    /**
     * 최초등록자ID
     */
    private String frstRegisterId = "";	
	/**
	 * 최종수정자ID
	 */
	private String lastUpdusrId;
	/**
	 * 최종수정시점
	 */
	private String lastUpdusrPnt;
	
	public String getOrgnztId() {
		return orgnztId;
	}
	public void setOrgnztId(String orgnztId) {
		this.orgnztId = orgnztId;
	}
	public String getOrgnztNm() {
		return orgnztNm;
	}
	public void setOrgnztNm(String orgnztNm) {
		this.orgnztNm = orgnztNm;
	}
	public String getClYmd() {
		return clYmd;
	}
	public void setClYmd(String clYmd) {
		this.clYmd = clYmd;
	}
	public String getKnoUrl() {
		return knoUrl;
	}
	public void setKnoUrl(String knoUrl) {
		this.knoUrl = knoUrl;
	}
	public String getFrstRegisterId() {
		return frstRegisterId;
	}
	public void setFrstRegisterId(String frstRegisterId) {
		this.frstRegisterId = frstRegisterId;
	}	
	public String getLastUpdusrId() {
		return lastUpdusrId;
	}
	public void setLastUpdusrId(String lastUpdusrId) {
		this.lastUpdusrId = lastUpdusrId;
	}
	public String getLastUpdusrPnt() {
		return lastUpdusrPnt;
	}
	public void setLastUpdusrPnt(String lastUpdusrPnt) {
		this.lastUpdusrPnt = lastUpdusrPnt;
	}

}