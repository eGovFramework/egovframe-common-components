package egovframework.com.uss.ion.wik.bmk.service;

import java.io.Serializable;

import egovframework.com.cmm.ComDefaultVO;

/**
 * 위키북마크 Model and VO Class 구현
 * @author 공통콤포넌트 장동한
 * @since 2010.10.20
 * @version 1.0
 * @see <pre>
 * &lt;&lt; 개정이력(Modification Information) &gt;&gt;
 *   
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2010.10.20  장동한          최초 생성
 * 
 * </pre>
 */
@SuppressWarnings("serial")
public class WikiBookmark extends ComDefaultVO implements Serializable{
    	
	/** 위키 북마크 아이디 */
	private String wikiBkmkId;	
	
	/** 사용자ID */
	private String usid;
	
	/** 북마크명 */
	private String wikiBkmkNm;
	
    /** 최초등록시점 */
    private String frstRegisterPnttm;

    /** 최초등록아이디 */
    private String frstRegisterId;

    /** 최종수정일 */
    private String lastUpdusrPnttm;

    /** 최종수정자 아이디 */
    private String lastUpdusrId;

	/**
	 * @return the wikiBookMarkId
	 */
	public String getWikiBkmkId() {
		return wikiBkmkId;
	}

	/**
	 * @param wikiBookMarkId the wikiBookMarkId to set
	 */
	public void setWikiBkmkId(String wikiBookmarkId) {
		this.wikiBkmkId = wikiBookmarkId;
	}

	/**
	 * @return the usid
	 */
	public String getUsid() {
		return usid;
	}

	/**
	 * @param usid the usid to set
	 */
	public void setUsid(String usid) {
		this.usid = usid;
	}

	/**
	 * @return the bookMark
	 */
	public String getWikiBkmkNm() {
		return wikiBkmkNm;
	}

	/**
	 * @param bookMark the bookMark to set
	 */
	public void setWikiBkmkNm(String bookMark) {
		this.wikiBkmkNm = bookMark;
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
