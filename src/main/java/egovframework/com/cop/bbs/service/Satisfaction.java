package egovframework.com.cop.bbs.service;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * 만족도조사 서비스 데이터 처리 모델
 * @author 공통컴포넌트개발팀 한성곤
 * @since 2009.06.29
 * @version 1.0
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2009.06.29  한성곤          최초 생성
 *
 * </pre>
 */
@SuppressWarnings("serial")
public class Satisfaction implements Serializable {
    /** 만족도 번호 */
    private String stsfdgNo = "";
    
    /** 게시판 ID */
    private String bbsId = "";
    
    /** 게시물 번호 */
    private long nttId = 0L;
    
    /** 작성자 ID */
    private String wrterId = "";
    
    /** 작성자명 */
    private String wrterNm = "";
    
    /** 패스워드 */
    private String stsfdgPassword = "";
    
    /** 만족도 내용 */
    private String stsfdgCn = "";
    
    /** 만족도 */
    private int stsfdg = 0;
    
    /** 사용 여부 */
    private String useAt = "";

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
    
    /** 확인 패스워드 */
    private String confirmPassword = "";

    /**
     * stsfdgNo attribute를 리턴한다.
     * @return the stsfdgNo
     */
    public String getStsfdgNo() {
        return stsfdgNo;
    }

    /**
     * stsfdgNo attribute 값을 설정한다.
     * @param stsfdgNo the stsfdgNo to set
     */
    public void setStsfdgNo(String stsfdgNo) {
        this.stsfdgNo = stsfdgNo;
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
     * wrterId attribute를 리턴한다.
     * @return the wrterId
     */
    public String getWrterId() {
        return wrterId;
    }

    /**
     * wrterId attribute 값을 설정한다.
     * @param wrterId the wrterId to set
     */
    public void setWrterId(String wrterId) {
        this.wrterId = wrterId;
    }

    /**
     * wrterNm attribute를 리턴한다.
     * @return the wrterNm
     */
    public String getWrterNm() {
        return wrterNm;
    }

    /**
     * wrterNm attribute 값을 설정한다.
     * @param wrterNm the wrterNm to set
     */
    public void setWrterNm(String wrterNm) {
        this.wrterNm = wrterNm;
    }

    /**
     * stsfdgPassword attribute를 리턴한다.
     * @return the stsfdgPassword
     */
    public String getStsfdgPassword() {
        return stsfdgPassword;
    }

    /**
     * stsfdgPassword attribute 값을 설정한다.
     * @param stsfdgPassword the stsfdgPassword to set
     */
    public void setStsfdgPassword(String stsfdgPassword) {
        this.stsfdgPassword = stsfdgPassword;
    }

    /**
     * stsfdgCn attribute를 리턴한다.
     * @return the stsfdgCn
     */
    public String getStsfdgCn() {
        return stsfdgCn;
    }

    /**
     * stsfdgCn attribute 값을 설정한다.
     * @param stsfdgCn the stsfdgCn to set
     */
    public void setStsfdgCn(String stsfdgCn) {
        this.stsfdgCn = stsfdgCn;
    }

    /**
     * stsfdg attribute를 리턴한다.
     * @return the stsfdg
     */
    public int getStsfdg() {
        return stsfdg;
    }

    /**
     * stsfdg attribute 값을 설정한다.
     * @param stsfdg the stsfdg to set
     */
    public void setStsfdg(int stsfdg) {
        this.stsfdg = stsfdg;
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
     * confirmPassword attribute를 리턴한다.
     * @return the confirmPassword
     */
    public String getConfirmPassword() {
        return confirmPassword;
    }

    /**
     * confirmPassword attribute 값을 설정한다.
     * @param confirmPassword the confirmPassword to set
     */
    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }
    
    /**
     * toString 메소드를 대치한다.
     */
    public String toString() {
	return ToStringBuilder.reflectionToString(this);
    }
}
