package egovframework.com.cop.scp.service;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * 스크랩 서비스 데이터 처리 모델
 * @author 공통컴포넌트개발팀 한성곤
 * @since 2009.07.10
 * @version 1.0
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2009.07.10  한성곤          최초 생성
 *
 * </pre>
 */
@SuppressWarnings("serial")
public class Scrap implements Serializable {
    /** 스크랩 ID */
    private String scrapId = "";
    
    /** 게시판 ID */
    private String bbsId = "";
    
    /** 게시물 번호 */
    private long nttId = 0L;
    
    /** 스크랩명 */
    private String scrapNm = "";
    
    /** 사용 여부 */
    private String useAt = "";
    
    /** 유일 아이디 */
    private String uniqId = "";

    /** 최초등록자 아이디 */
    private String frstRegisterId = "";
    
    /** 최초 등록자명 */
    private String frstRegisterNm = "";
    
    /** 최초등록시점 */
    private String frstRegisterPnttm = "";
    
    /** 최종수정자 아이디 */
    private String lastUpdusrId = "";
    
    /** 최종수정시점 */
    private String lastUpdusrPnttm = "";

    /**
     * scrapId attribute를 리턴한다.
     * @return the scrapId
     */
    public String getScrapId() {
        return scrapId;
    }

    /**
     * scrapId attribute 값을 설정한다.
     * @param scrapId the scrapId to set
     */
    public void setScrapId(String scrapId) {
        this.scrapId = scrapId;
    }

    /**
     * bbsId attribute를 리턴한다.
     * @return the bbsId
     */
    public String getBbsId() {
        return bbsId;
    }

    /**
     * bbsId attribute 값을 설정한다.
     * @param bbsId the bbsId to set
     */
    public void setBbsId(String bbsId) {
        this.bbsId = bbsId;
    }

    /**
     * nttId attribute를 리턴한다.
     * @return the nttId
     */
    public long getNttId() {
        return nttId;
    }

    /**
     * nttId attribute 값을 설정한다.
     * @param nttId the nttId to set
     */
    public void setNttId(long nttId) {
        this.nttId = nttId;
    }

    /**
     * scrapNm attribute를 리턴한다.
     * @return the scrapNm
     */
    public String getScrapNm() {
        return scrapNm;
    }

    /**
     * scrapNm attribute 값을 설정한다.
     * @param scrapNm the scrapNm to set
     */
    public void setScrapNm(String scrapNm) {
        this.scrapNm = scrapNm;
    }

    /**
     * useAt attribute를 리턴한다.
     * @return the useAt
     */
    public String getUseAt() {
        return useAt;
    }

    /**
     * useAt attribute 값을 설정한다.
     * @param useAt the useAt to set
     */
    public void setUseAt(String useAt) {
        this.useAt = useAt;
    }

    /**
     * frstRegisterId attribute를 리턴한다.
     * @return the frstRegisterId
     */
    public String getFrstRegisterId() {
        return frstRegisterId;
    }

    /**
     * frstRegisterId attribute 값을 설정한다.
     * @param frstRegisterId the frstRegisterId to set
     */
    public void setFrstRegisterId(String frstRegisterId) {
        this.frstRegisterId = frstRegisterId;
    }

    /**
     * frstRegisterNm attribute를 리턴한다.
     * @return the frstRegisterNm
     */
    public String getFrstRegisterNm() {
        return frstRegisterNm;
    }

    /**
     * frstRegisterNm attribute 값을 설정한다.
     * @param frstRegisterNm the frstRegisterNm to set
     */
    public void setFrstRegisterNm(String frstRegisterNm) {
        this.frstRegisterNm = frstRegisterNm;
    }

    /**
     * frstRegisterPnttm attribute를 리턴한다.
     * @return the frstRegisterPnttm
     */
    public String getFrstRegisterPnttm() {
        return frstRegisterPnttm;
    }

    /**
     * frstRegisterPnttm attribute 값을 설정한다.
     * @param frstRegisterPnttm the frstRegisterPnttm to set
     */
    public void setFrstRegisterPnttm(String frstRegisterPnttm) {
        this.frstRegisterPnttm = frstRegisterPnttm;
    }

    /**
     * lastUpdusrId attribute를 리턴한다.
     * @return the lastUpdusrId
     */
    public String getLastUpdusrId() {
        return lastUpdusrId;
    }

    /**
     * lastUpdusrId attribute 값을 설정한다.
     * @param lastUpdusrId the lastUpdusrId to set
     */
    public void setLastUpdusrId(String lastUpdusrId) {
        this.lastUpdusrId = lastUpdusrId;
    }

    /**
     * lastUpdusrPnttm attribute를 리턴한다.
     * @return the lastUpdusrPnttm
     */
    public String getLastUpdusrPnttm() {
        return lastUpdusrPnttm;
    }

    /**
     * lastUpdusrPnttm attribute 값을 설정한다.
     * @param lastUpdusrPnttm the lastUpdusrPnttm to set
     */
    public void setLastUpdusrPnttm(String lastUpdusrPnttm) {
        this.lastUpdusrPnttm = lastUpdusrPnttm;
    }
    
    /**
     * uniqId attribute를 리턴한다.
     * @return the uniqId
     */
    public String getUniqId() {
        return uniqId;
    }

    /**
     * uniqId attribute 값을 설정한다.
     * @param uniqId the uniqId to set
     */
    public void setUniqId(String uniqId) {
        this.uniqId = uniqId;
    }

    /**
     * toString 메소드를 대치한다.
     */
    public String toString() {
	return ToStringBuilder.reflectionToString(this);
    }
}
