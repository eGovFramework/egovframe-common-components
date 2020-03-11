package egovframework.com.cop.smt.dsm.service;

import java.io.Serializable;

import egovframework.com.cmm.ComDefaultVO;
/**
 * 일지관리 VO Class 구현
 * @author 공통서비스 장동한
 * @since 2009.04.10
 * @version 1.0
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2009.04.10  장동한          최초 생성
 *
 * </pre>
 */
@SuppressWarnings("serial")
public class DiaryManageVO extends ComDefaultVO implements Serializable {
	
	/** 일지ID */
	private String diaryId;
	
	/** 일정내용 */
	private String schdulCn;
	
	/** 일정ID */
	private String schdulId;
	
	/** 진척율 */
	private String diaryProcsPte;
	
	/** 일정명 */
	private String diaryNm;
	
	/** 지지사항 */
	private String drctMatter;
	
	/** 특이사항 */
	private String partclrMatter;
	
	/** 첨부파일 */
	private String atchFileId;
	
	/** 최초등록시점 */
	private String frstRegisterPnttm = "";
	
	/** 최초등록자ID */
	private String frstRegisterId = "";
	
	/** 최종수정시점 */
	private String lastUpdusrPnttm = "";
	
	/** 최종수정ID */
	private String lastUpdusrId = "";

	/**
	 * diaryId attribute 를 리턴한다.
	 * @return the String
	 */
	public String getDiaryId() {
		return diaryId;
	}

	/**
	 * diaryId attribute 값을 설정한다.
	 * @return diaryId String
	 */
	public void setDiaryId(String diaryId) {
		this.diaryId = diaryId;
	}

	/**
	 * schdulCn attribute 를 리턴한다.
	 * @return the String
	 */
	public String getSchdulCn() {
		return schdulCn;
	}

	/**
	 * schdulCn attribute 값을 설정한다.
	 * @return schdulCn String
	 */
	public void setSchdulCn(String schdulCn) {
		this.schdulCn = schdulCn;
	}

	/**
	 * schdulId attribute 를 리턴한다.
	 * @return the String
	 */
	public String getSchdulId() {
		return schdulId;
	}

	/**
	 * schdulId attribute 값을 설정한다.
	 * @return schdulId String
	 */
	public void setSchdulId(String schdulId) {
		this.schdulId = schdulId;
	}

	/**
	 * diaryProcsPte attribute 를 리턴한다.
	 * @return the String
	 */
	public String getDiaryProcsPte() {
		return diaryProcsPte;
	}

	/**
	 * diaryProcsPte attribute 값을 설정한다.
	 * @return diaryProcsPte String
	 */
	public void setDiaryProcsPte(String diaryProcsPte) {
		this.diaryProcsPte = diaryProcsPte;
	}

	/**
	 * diaryNm attribute 를 리턴한다.
	 * @return the String
	 */
	public String getDiaryNm() {
		return diaryNm;
	}

	/**
	 * diaryNm attribute 값을 설정한다.
	 * @return diaryNm String
	 */
	public void setDiaryNm(String diaryNm) {
		this.diaryNm = diaryNm;
	}

	/**
	 * drctMatter attribute 를 리턴한다.
	 * @return the String
	 */
	public String getDrctMatter() {
		return drctMatter;
	}

	/**
	 * drctMatter attribute 값을 설정한다.
	 * @return drctMatter String
	 */
	public void setDrctMatter(String drctMatter) {
		this.drctMatter = drctMatter;
	}

	/**
	 * partclrMatter attribute 를 리턴한다.
	 * @return the String
	 */
	public String getPartclrMatter() {
		return partclrMatter;
	}

	/**
	 * partclrMatter attribute 값을 설정한다.
	 * @return partclrMatter String
	 */
	public void setPartclrMatter(String partclrMatter) {
		this.partclrMatter = partclrMatter;
	}

	/**
	 * atchFileId attribute 를 리턴한다.
	 * @return the String
	 */
	public String getAtchFileId() {
		return atchFileId;
	}

	/**
	 * atchFileId attribute 값을 설정한다.
	 * @return atchFileId String
	 */
	public void setAtchFileId(String atchFileId) {
		this.atchFileId = atchFileId;
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
 
	
}
