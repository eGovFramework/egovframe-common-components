package egovframework.com.uss.ion.rss.service;

import org.egovframe.rte.ptl.reactive.validation.EgovNullCheck;

import egovframework.com.cmm.ComDefaultVO;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

/**
 * RSS태그관리 Model and VO Class 구현
 * @author 공통서비스 장동한
 * @since 2010.06.16
 * @version 1.0
 * @see <pre>
 * &lt;&lt; 개정이력(Modification Information) &gt;&gt;
 *   
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2009.07.03  장동한          최초 생성
 * 
 * </pre>
 */
public class RssManage extends ComDefaultVO {

	private static final long serialVersionUID = 1L;

	/** RSS태그관리 아이디 */
	private String rssId;
	
	/** 대상서비스명 */
	@EgovNullCheck
	@Size(max=255)
	private String trgetSvcNm;
	
	/** 대상테이블명 */
	@EgovNullCheck
	@Size(max=255)
	@Pattern(regexp="^[a-zA-Z0-9_]*$", message="{validation.pattern.english}")
	private String trgetSvcTable;
	
	/** 대상서비스목록개수 */
	@EgovNullCheck
	@Min(1)
	@Max(99999) // maxlength=5 (5자리 숫자 최대값)
	private int trgetSvcListCo;
	
	/** 헤더 TITLE */
	@EgovNullCheck
	@Size(max=255)
	private String hderTitle;
	
	/** 헤더	LINK */
	@EgovNullCheck
	@Size(max=255)
	private String hderLink;
	
	/** 헤더 DESCRIPTION */
	@EgovNullCheck
	@Size(max=4000)
	private String hderDescription;
	
	/** 헤더 TAG */
	@Size(max=255)
	private String hderTag;
	
	/** 헤더 ETC */
	@Size(max=255)
	private String hderEtc;
	
	/** 본문 TITLE */
	@EgovNullCheck
	@Size(max=255)
	private String bdtTitle;
	
	/** 본문 LINK */
	@EgovNullCheck
	@Size(max=255)
	private String bdtLink;
	
	/** 본문 DESCRIPTION */
	@EgovNullCheck
	@Size(max=4000)
	private String bdtDescription;
	
	/** 본문 TAG */
	@Size(max=255)
	private String bdtTag;
	
	/** 본문 ETC */
	@Size(max=255)
	private String bdtEtc;
	
    /** 최초등록시점 */
    private String frstRegisterPnttm;

    /** 최초등록아이디 */
    private String frstRegisterId;

    /** 최종수정일 */
    private String lastUpdusrPnttm;

    /** 최종수정자 아이디 */
    private String lastUpdusrId;

	/**
	 * @return the rssId
	 */
	public String getRssId() {
		return rssId;
	}

	/**
	 * @param rssId the rssId to set
	 */
	public void setRssId(String rssId) {
		this.rssId = rssId;
	}

	/**
	 * @return the trgetSvcNm
	 */
	public String getTrgetSvcNm() {
		return trgetSvcNm;
	}

	/**
	 * @param trgetSvcNm the trgetSvcNm to set
	 */
	public void setTrgetSvcNm(String trgetSvcNm) {
		this.trgetSvcNm = trgetSvcNm;
	}

	/**
	 * @return the trgetSvcTable
	 */
	public String getTrgetSvcTable() {
		return trgetSvcTable;
	}

	/**
	 * @param trgetSvcTable the trgetSvcTable to set
	 */
	public void setTrgetSvcTable(String trgetSvcTable) {
		this.trgetSvcTable = trgetSvcTable;
	}

	/**
	 * @return the trgetSvcListCo
	 */
	public int getTrgetSvcListCo() {
		return trgetSvcListCo;
	}

	/**
	 * @param trgetSvcListCo the trgetSvcListCo to set
	 */
	public void setTrgetSvcListCo(int trgetSvcListCo) {
		this.trgetSvcListCo = trgetSvcListCo;
	}

	/**
	 * @return the hderTitle
	 */
	public String getHderTitle() {
		return hderTitle;
	}

	/**
	 * @param hderTitle the hderTitle to set
	 */
	public void setHderTitle(String hderTitle) {
		this.hderTitle = hderTitle;
	}

	/**
	 * @return the hderLink
	 */
	public String getHderLink() {
		return hderLink;
	}

	/**
	 * @param hderLink the hderLink to set
	 */
	public void setHderLink(String hderLink) {
		this.hderLink = hderLink;
	}

	/**
	 * @return the hderDescription
	 */
	public String getHderDescription() {
		return hderDescription;
	}

	/**
	 * @param hderDescription the hderDescription to set
	 */
	public void setHderDescription(String hderDescription) {
		this.hderDescription = hderDescription;
	}

	/**
	 * @return the hderTag
	 */
	public String getHderTag() {
		return hderTag;
	}

	/**
	 * @param hderTag the hderTag to set
	 */
	public void setHderTag(String hderTag) {
		this.hderTag = hderTag;
	}

	/**
	 * @return the hderEtc
	 */
	public String getHderEtc() {
		return hderEtc;
	}

	/**
	 * @param hderEtc the hderEtc to set
	 */
	public void setHderEtc(String hderEtc) {
		this.hderEtc = hderEtc;
	}

	/**
	 * @return the bdtTitle
	 */
	public String getBdtTitle() {
		return bdtTitle;
	}

	/**
	 * @param bdtTitle the bdtTitle to set
	 */
	public void setBdtTitle(String bdtTitle) {
		this.bdtTitle = bdtTitle;
	}

	/**
	 * @return the bdtLink
	 */
	public String getBdtLink() {
		return bdtLink;
	}

	/**
	 * @param bdtLink the bdtLink to set
	 */
	public void setBdtLink(String bdtLink) {
		this.bdtLink = bdtLink;
	}

	/**
	 * @return the bdtDescription
	 */
	public String getBdtDescription() {
		return bdtDescription;
	}

	/**
	 * @param bdtDescription the bdtDescription to set
	 */
	public void setBdtDescription(String bdtDescription) {
		this.bdtDescription = bdtDescription;
	}

	/**
	 * @return the bdtTag
	 */
	public String getBdtTag() {
		return bdtTag;
	}

	/**
	 * @param bdtTag the bdtTag to set
	 */
	public void setBdtTag(String bdtTag) {
		this.bdtTag = bdtTag;
	}

	/**
	 * @return the bdtEtc
	 */
	public String getBdtEtc() {
		return bdtEtc;
	}

	/**
	 * @param bdtEtc the bdtEtc to set
	 */
	public void setBdtEtc(String bdtEtc) {
		this.bdtEtc = bdtEtc;
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
