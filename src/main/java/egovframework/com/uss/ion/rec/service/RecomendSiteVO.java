package egovframework.com.uss.ion.rec.service;


/**
 * 
 * 추천사이트정보를 처리하는 VO 클래스
 * @author 공통서비스 개발팀 박정규
 * @since 2009.04.01
 * @version 1.0
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2009.04.01  박정규          최초 생성
 *
 * </pre>
 */
public class RecomendSiteVO extends RecomendSiteDefaultVO {
	
    private static final long serialVersionUID = 1L;
    
    /** 추천사이트 ID */
    private String recomendSiteId;
    
    /** 추천사이트 URL */
    private String recomendSiteUrl;
    
    /** 추천사이트명 */
    private String recomendSiteNm;
    
    /** 추천사이트설명 */
    private String recomendSiteDc;
    
    /** 추천사유내용 */
    private String recomendResnCn;

    /** 추천승인여부 */
    private String recomendConfmAt;
    
    /** 승인일자 */
    private String confmDe;

    /** 최초등록시점 */
    private String frstRegisterPnttm;

    /** 최초등록자ID */
    private String frstRegisterId;

    /** 최종수정시점 */
    private String lastUpdusrPnttm;

    /** 최종수정자ID */
    private String lastUpdusrId;

	/**
	 * recomendSiteId attribute 를 리턴한다.
	 * @return the String
	 */
	public String getRecomendSiteId() {
		return recomendSiteId;
	}

	/**
	 * recomendSiteId attribute 값을 설정한다.
	 * @return recomendSiteId String
	 */
	public void setRecomendSiteId(String recomendSiteId) {
		this.recomendSiteId = recomendSiteId;
	}

	/**
	 * recomendSiteUrl attribute 를 리턴한다.
	 * @return the String
	 */
	public String getRecomendSiteUrl() {
		return recomendSiteUrl;
	}

	/**
	 * recomendSiteUrl attribute 값을 설정한다.
	 * @return recomendSiteUrl String
	 */
	public void setRecomendSiteUrl(String recomendSiteUrl) {
		this.recomendSiteUrl = recomendSiteUrl;
	}

	/**
	 * recomendSiteNm attribute 를 리턴한다.
	 * @return the String
	 */
	public String getRecomendSiteNm() {
		return recomendSiteNm;
	}

	/**
	 * recomendSiteNm attribute 값을 설정한다.
	 * @return recomendSiteNm String
	 */
	public void setRecomendSiteNm(String recomendSiteNm) {
		this.recomendSiteNm = recomendSiteNm;
	}

	/**
	 * recomendSiteDc attribute 를 리턴한다.
	 * @return the String
	 */
	public String getRecomendSiteDc() {
		return recomendSiteDc;
	}

	/**
	 * recomendSiteDc attribute 값을 설정한다.
	 * @return recomendSiteDc String
	 */
	public void setRecomendSiteDc(String recomendSiteDc) {
		this.recomendSiteDc = recomendSiteDc;
	}

	/**
	 * recomendResnCn attribute 를 리턴한다.
	 * @return the String
	 */
	public String getRecomendResnCn() {
		return recomendResnCn;
	}

	/**
	 * recomendResnCn attribute 값을 설정한다.
	 * @return recomendResnCn String
	 */
	public void setRecomendResnCn(String recomendResnCn) {
		this.recomendResnCn = recomendResnCn;
	}

	/**
	 * recomendConfmAt attribute 를 리턴한다.
	 * @return the String
	 */
	public String getRecomendConfmAt() {
		return recomendConfmAt;
	}

	/**
	 * recomendConfmAt attribute 값을 설정한다.
	 * @return recomendConfmAt String
	 */
	public void setRecomendConfmAt(String recomendConfmAt) {
		this.recomendConfmAt = recomendConfmAt;
	}

	/**
	 * confmDe attribute 를 리턴한다.
	 * @return the String
	 */
	public String getConfmDe() {
		return confmDe;
	}

	/**
	 * confmDe attribute 값을 설정한다.
	 * @return confmDe String
	 */
	public void setConfmDe(String confmDe) {
		this.confmDe = confmDe;
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
