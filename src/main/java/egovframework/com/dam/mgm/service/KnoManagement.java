package egovframework.com.dam.mgm.service;

/**
 * 개요
 * - 지식정보에 대한 model 클래스를 정의한다.
 * 
 * 상세내용
 * - 소속조직, 지식유형, 등록일자, 지식명, 지식내용, 첨부파일 항목을 관리한다.
 * @author 박종선
 * @version 1.0
 * @created 12-8-2010 오후 3:44:48
 */
public class KnoManagement {

	/**
	 * 지식ID
	 */
	private String knoId;
	/**
	 * 소속조직ID
	 */
	private String orgnztId;
	/**
	 * 소속조직명
	 */
	private String orgnztNm;
	/**
	 * 권한그룹코드
	 */	
	private String authorCode;
	/**
	 * 사용자ID
	 */
	private String emplyrId;
	/**
	 * 전문가ID
	 */
	private String speId;	
	/**
	 * 사용자명
	 */
	private String userNm;
	/**
	 * 지식유형코드
	 */
	private String knoTypeCd;
	/**
	 * 지식유형명
	 */
	private String knoTypeNm;
	/**
	 * 지식명
	 */
	private String knoNm;
	/**
	 * 지식내용
	 */
	private String knoCn;
	/**
	 * 지식평가
	 */
	private String appTypeCd;	
	/**
	 * 지식평가
	 */
	private String knoAps;	
	/**
	 * 평가일자
	 */
	private String appYmd;
	/**
	 * 사용자명
	 */
	private String speNm;	
	/**
	 * 개인지식공개여부
	 */
	private String othbcAt;	
	/**
	 * 등록자명
	 */
	private String regstNm;	
	/**
	 * 수집일자
	 */
	private String colYmd;
	/**
	 * 폐기일자
	 */
	private String junkYmd;	
	/**
	 * 첨부파일ID
	 */
	private String atchFileId;
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
	 * @return the knoId
	 */
	public String getKnoId() {
		return knoId;
	}
	/**
	 * @param knoId the knoId to set
	 */
	public void setKnoId(String knoId) {
		this.knoId = knoId;
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
	 * @return the emplyrId
	 */
	public String getEmplyrId() {
		return emplyrId;
	}
	/**
	 * @param emplyrId the emplyrId to set
	 */
	public void setEmplyrId(String emplyrId) {
		this.emplyrId = emplyrId;
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
	 * @return the knoNm
	 */
	public String getKnoNm() {
		return knoNm;
	}
	/**
	 * @param knoNm the knoNm to set
	 */
	public void setKnoNm(String knoNm) {
		this.knoNm = knoNm;
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
	 * @return the knoCn
	 */
	public String getKnoCn() {
		return knoCn;
	}
	/**
	 * @param knoCn the knoCn to set
	 */
	public void setKnoCn(String knoCn) {
		this.knoCn = knoCn;
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
	 * @return the knoAps
	 */
	public String getKnoAps() {
		return knoAps;
	}
	/**
	 * @param knoAps the knoAps to set
	 */
	public void setKnoAps(String knoAps) {
		this.knoAps = knoAps;
	}
	/**
	 * @return the appYmd
	 */
	public String getAppYmd() {
		return appYmd;
	}
	/**
	 * @param appYmd the appYmd to set
	 */
	public void setAppYmd(String appYmd) {
		this.appYmd = appYmd;
	}
	/**
	 * @return the othbcAt
	 */
	public String getOthbcAt() {
		return othbcAt;
	}
	/**
	 * @param othbcAt the othbcAt to set
	 */
	public void setOthbcAt(String othbcAt) {
		this.othbcAt = othbcAt;
	}
	/**
	 * @return the regstNm
	 */
	public String getRegstNm() {
		return regstNm;
	}
	/**
	 * @param regstNm the regstNm to set
	 */
	public void setRegstNm(String regstNm) {
		this.regstNm = regstNm;
	}
	/**
	 * @return the colYmd
	 */
	public String getColYmd() {
		return colYmd;
	}
	/**
	 * @param colYmd the colYmd to set
	 */
	public void setColYmd(String colYmd) {
		this.colYmd = colYmd;
	}
	/**
	 * @return the atchFileId
	 */
	public String getAtchFileId() {
		return atchFileId;
	}
	/**
	 * @param atchFileId the atchFileId to set
	 */
	public void setAtchFileId(String atchFileId) {
		this.atchFileId = atchFileId;
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
	 * @return the authorCode
	 */
	public String getAuthorCode() {
		return authorCode;
	}
	/**
	 * @param authorCode the authorCode to set
	 */
	public void setAuthorCode(String authorCode) {
		this.authorCode = authorCode;
	}
	/**
	 * @return the speNm
	 */
	public String getSpeNm() {
		return speNm;
	}
	/**
	 * @param speNm the speNm to set
	 */
	public void setSpeNm(String speNm) {
		this.speNm = speNm;
	}
	/**
	 * @return the junkYmd
	 */
	public String getJunkYmd() {
		return junkYmd;
	}
	/**
	 * @param junkYmd the junkYmd to set
	 */
	public void setJunkYmd(String junkYmd) {
		this.junkYmd = junkYmd;
	}

}