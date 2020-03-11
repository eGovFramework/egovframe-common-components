package egovframework.com.uss.ion.nws.service;

/**
 *  
 * 뉴스정보를 처리하는 VO 클래스
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
public class NewsVO extends NewsDefaultVO {
	
    private static final long serialVersionUID = 1L;
    
    /** 뉴스 ID */
    private String newsId;
    
    /** 뉴스제목 */
    private String newsSj;
    
    /** 뉴스내용 */
    private String newsCn;
    
    /** 뉴스출처 */
    private String newsOrigin;
    
    /** 게시일자 */
    private String ntceDe;

    /** 첨부파일ID */ 
    private String atchFileId;
    
    /** 최초등록시점 */
    private String frstRegisterPnttm;

    /** 최초등록자ID */
    private String frstRegisterId;

    /** 최종수정시점 */
    private String lastUpdusrPnttm;

    /** 최종수정자ID */
    private String lastUpdusrId;

	/**
	 * newsId attribute 를 리턴한다.
	 * @return the String
	 */
	public String getNewsId() {
		return newsId;
	}

	/**
	 * newsId attribute 값을 설정한다.
	 * @return newsId String
	 */
	public void setNewsId(String newsId) {
		this.newsId = newsId;
	}

	/**
	 * newsSj attribute 를 리턴한다.
	 * @return the String
	 */
	public String getNewsSj() {
		return newsSj;
	}

	/**
	 * newsSj attribute 값을 설정한다.
	 * @return newsSj String
	 */
	public void setNewsSj(String newsSj) {
		this.newsSj = newsSj;
	}

	/**
	 * newsCn attribute 를 리턴한다.
	 * @return the String
	 */
	public String getNewsCn() {
		return newsCn;
	}

	/**
	 * newsCn attribute 값을 설정한다.
	 * @return newsCn String
	 */
	public void setNewsCn(String newsCn) {
		this.newsCn = newsCn;
	}

	/**
	 * newsOrigin attribute 를 리턴한다.
	 * @return the String
	 */
	public String getNewsOrigin() {
		return newsOrigin;
	}

	/**
	 * newsOrigin attribute 값을 설정한다.
	 * @return newsOrigin String
	 */
	public void setNewsOrigin(String newsOrigin) {
		this.newsOrigin = newsOrigin;
	}

	/**
	 * ntceDe attribute 를 리턴한다.
	 * @return the String
	 */
	public String getNtceDe() {
		return ntceDe;
	}

	/**
	 * ntceDe attribute 값을 설정한다.
	 * @return ntceDe String
	 */
	public void setNtceDe(String ntceDe) {
		this.ntceDe = ntceDe;
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
