package egovframework.com.cop.bbs.service;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * 블로그게시판 관리를 위한 모델 클래스
 * @author 공통서비스개발팀 양희훈
 * @since 2017.09.12
 * @version 1.0
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일            수정자           수정내용
 *  -----------   --------   ---------------------------
 *   2017.09.12  양희훈          최초 생성
 *
 * </pre>
 */
@SuppressWarnings("serial")
public class BlogUser implements Serializable {

    /** 블로그아이디 */
    private String blogId = "";
    
    /** 최초등록자 아이디 */
    private String frstRegisterId = "";
    
    /** 최초등록시점 */
    private String frstRegisterPnttm = "";
    
    /** 최종수정자 아이디 */
    private String lastUpdusrId = "";
    
    /** 최종수정시점 */
    private String lastUpdusrPnttm = "";
    
    /** 관리자여부 */
    private String mngrAt = "";
    
    /** 탈퇴일 */
    private String secsnDe = "";
    
    /** 가입일 */
    private String sbscrbDe = "";
    
    /** 사용여부 */
    private String useAt = "";
    
    /** 사용자 아이디 */
    private String emplyrId = "";
    
    /** 사용자명 */
    private String emplyrNm = "";
    
    /** 회원 ID */
    private String userId = "";
   
    /** 회원 이메일 */
    private String userEmail = "";
    
    /** 회원 상태 */
    private String mberSttus = "";

    /** 회원 상태 코드명 */
    private String mberSttusNm = "";

    /**
     * blogId attribute를 리턴한다.
     * 
     * @return the blogId
     */
    public String getBlogId() {
	return blogId;
    }

    /**
     * blogId attribute 값을 설정한다.
     * 
     * @param blogId
     *            the blogId to set
     */
    public void setBlogId(String blogId) {
	this.blogId = blogId;
    }

    /**
     * frstRegisterId attribute를 리턴한다.
     * 
     * @return the frstRegisterId
     */
    public String getFrstRegisterId() {
	return frstRegisterId;
    }

    /**
     * frstRegisterId attribute 값을 설정한다.
     * 
     * @param frstRegisterId
     *            the frstRegisterId to set
     */
    public void setFrstRegisterId(String frstRegisterId) {
	this.frstRegisterId = frstRegisterId;
    }

    /**
     * frstRegisterPnttm attribute를 리턴한다.
     * 
     * @return the frstRegisterPnttm
     */
    public String getFrstRegisterPnttm() {
	return frstRegisterPnttm;
    }

    /**
     * frstRegisterPnttm attribute 값을 설정한다.
     * 
     * @param frstRegisterPnttm
     *            the frstRegisterPnttm to set
     */
    public void setFrstRegisterPnttm(String frstRegisterPnttm) {
	this.frstRegisterPnttm = frstRegisterPnttm;
    }

    /**
     * lastUpdusrId attribute를 리턴한다.
     * 
     * @return the lastUpdusrId
     */
    public String getLastUpdusrId() {
	return lastUpdusrId;
    }

    /**
     * lastUpdusrId attribute 값을 설정한다.
     * 
     * @param lastUpdusrId
     *            the lastUpdusrId to set
     */
    public void setLastUpdusrId(String lastUpdusrId) {
	this.lastUpdusrId = lastUpdusrId;
    }

    /**
     * lastUpdusrPnttm attribute를 리턴한다.
     * 
     * @return the lastUpdusrPnttm
     */
    public String getLastUpdusrPnttm() {
	return lastUpdusrPnttm;
    }

    /**
     * lastUpdusrPnttm attribute 값을 설정한다.
     * 
     * @param lastUpdusrPnttm
     *            the lastUpdusrPnttm to set
     */
    public void setLastUpdusrPnttm(String lastUpdusrPnttm) {
	this.lastUpdusrPnttm = lastUpdusrPnttm;
    }

    /**
     * mngrAt attribute를 리턴한다.
     * 
     * @return the mngrAt
     */
    public String getMngrAt() {
	return mngrAt;
    }

    /**
     * mngrAt attribute 값을 설정한다.
     * 
     * @param mngrAt
     *            the mngrAt to set
     */
    public void setMngrAt(String mngrAt) {
	this.mngrAt = mngrAt;
    }

    /**
     * secsnDe attribute를 리턴한다.
     * 
     * @return the secsnDe
     */
    public String getSecsnDe() {
	return secsnDe;
    }

    /**
     * secsnDe attribute 값을 설정한다.
     * 
     * @param secsnDe
     *            the secsnDe to set
     */
    public void setSecsnDe(String secsnDe) {
	this.secsnDe = secsnDe;
    }

    /**
     * sbscrbDe attribute를 리턴한다.
     * 
     * @return the sbscrbDe
     */
    public String getSbscrbDe() {
	return sbscrbDe;
    }

    /**
     * sbscrbDe attribute 값을 설정한다.
     * 
     * @param sbscrbDe
     *            the sbscrbDe to set
     */
    public void setSbscrbDe(String sbscrbDe) {
	this.sbscrbDe = sbscrbDe;
    }

    /**
     * useAt attribute를 리턴한다.
     * 
     * @return the useAt
     */
    public String getUseAt() {
	return useAt;
    }

    /**
     * useAt attribute 값을 설정한다.
     * 
     * @param useAt
     *            the useAt to set
     */
    public void setUseAt(String useAt) {
	this.useAt = useAt;
    }

    /**
     * emplyrId attribute를 리턴한다.
     * 
     * @return the emplyrId
     */
    public String getEmplyrId() {
	return emplyrId;
    }

    /**
     * emplyrId attribute 값을 설정한다.
     * 
     * @param emplyrId
     *            the emplyrId to set
     */
    public void setEmplyrId(String emplyrId) {
	this.emplyrId = emplyrId;
    }

    /**
     * emplyrNm attribute를 리턴한다.
     * 
     * @return the emplyrNm
     */
    public String getEmplyrNm() {
	return emplyrNm;
    }

    /**
     * emplyrNm attribute 값을 설정한다.
     * 
     * @param emplyrNm
     *            the emplyrNm to set
     */
    public void setEmplyrNm(String emplyrNm) {
	this.emplyrNm = emplyrNm;
    }
    
    /**
     * userId attribute를 리턴한다.
     * 
     * @return the userId
     */
    public String getUserId() {
    	return userId;
    }
    
    /**
     * userId attribute 값을 설정한다.
     * 
     * @param userId
     *            the userId to set
     */
    public void setUserId(String userId) {
    	this.userId = userId;
    }
    
    /**
     * userEmail attribute를 리턴한다.
     * 
     * @return the userEmail
     */
    public String getUserEmail() {
    	return userEmail;
    }
    
    /**
     * userEmail attribute 값을 설정한다.
     * 
     * @param userEmail
     *            the userEmail to set
     */
    public void setUserEmail(String userEmail) {
    	this.userEmail = userEmail;
    }
    
    /**
     * mberSttus attribute를 리턴한다.
     * 
     * @return the mberSttus
     */
    public String getMberSttus() {
    	return mberSttus;
    }
    
    /**
     * mberSttus attribute 값을 설정한다.
     * 
     * @param mberSttus
     *            the mberSttus to set
     */
    public void setMberSttus(String mberSttus) {
    	this.mberSttus = mberSttus;
    }
    
    /**
     * mberSttusNm attribute를 리턴한다.
     * 
     * @return the mberSttusNm
     */
    public String getMberSttusNm() {
    	return mberSttusNm;
    }
    
    /**
     * mberSttusNm attribute 값을 설정한다.
     * 
     * @param mberSttusNm
     *            the mberSttusNm to set
     */
    public void setMberSttusNm(String mberSttusNm) {
    	this.mberSttusNm = mberSttusNm;
    }

    /**
     * toString 메소드를 대치한다.
     */
    public String toString() {
	return ToStringBuilder.reflectionToString(this);
    }
}
