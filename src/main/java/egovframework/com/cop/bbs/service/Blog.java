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
public class Blog implements Serializable {

    /** 블로그 아이디 */
    private String blogId = "";
    
    /** 게시판 아이디 */
    private String bbsId = "";
    
    /** 블로그 소개 */
    private String blogIntrcn = "";
    
    /** 블로그 명 */
    private String blogNm = "";
    
    /** 최초등록자 아이디 */
    private String frstRegisterId = "";
    
    /** 최초등록시점 */
    private String frstRegisterPnttm = "";
    
    /** 최종수정자 아이디 */
    private String lastUpdusrId = "";
    
    /** 최종수정시점 */
    private String lastUpdusrPnttm = "";
    
    /** 등록구분코드 */
    private String registSeCode = "";
    
    /** 템플릿 아이디 */
    private String tmplatId = "";
    
    /** 템플릿 아이디 */
    private String useAt = "";

    /** 사용자 아이디 */
    private String emplyrId = "";

    /** 사용자명 */
    private String userNm = "";

    /** 템플릿 명 */
    private String tmplatNm = "";
    
    /**  블로그 게시판 여부 */
	private String blogAt = "";

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

    public String getBbsId() {
		return bbsId;
	}

	public void setBbsId(String bbsId) {
		this.bbsId = bbsId;
	}

	/**
     * blogIntrcn attribute를 리턴한다.
     * 
     * @return the blogIntrcn
     */
    public String getBlogIntrcn() {
	return blogIntrcn;
    }

    /**
     * blogIntrcn attribute 값을 설정한다.
     * 
     * @param blogIntrcn
     *            the blogIntrcn to set
     */
    public void setBlogIntrcn(String blogIntrcn) {
	this.blogIntrcn = blogIntrcn;
    }

    /**
     * blogNm attribute를 리턴한다.
     * 
     * @return the blogNm
     */
    public String getBlogNm() {
	return blogNm;
    }

    /**
     * blogNm attribute 값을 설정한다.
     * 
     * @param blogNm
     *            the blogNm to set
     */
    public void setBlogNm(String blogNm) {
	this.blogNm = blogNm;
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
     * registSeCode attribute를 리턴한다.
     * 
     * @return the registSeCode
     */
    public String getRegistSeCode() {
	return registSeCode;
    }

    /**
     * registSeCode attribute 값을 설정한다.
     * 
     * @param registSeCode
     *            the registSeCode to set
     */
    public void setRegistSeCode(String registSeCode) {
	this.registSeCode = registSeCode;
    }

    /**
     * tmplatId attribute를 리턴한다.
     * 
     * @return the tmplatId
     */
    public String getTmplatId() {
	return tmplatId;
    }

    /**
     * tmplatId attribute 값을 설정한다.
     * 
     * @param tmplatId
     *            the tmplatId to set
     */
    public void setTmplatId(String tmplatId) {
	this.tmplatId = tmplatId;
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
     * userNm attribute를 리턴한다.
     * 
     * @return the userNm
     */
    public String getUserNm() {
	return userNm;
    }

    /**
     * userNm attribute 값을 설정한다.
     * 
     * @param userNm
     *            the userNm to set
     */
    public void setUserNm(String userNm) {
	this.userNm = userNm;
    }

    /**
     * tmplatNm attribute를 리턴한다.
     * 
     * @return the tmplatNm
     */
    public String getTmplatNm() {
	return tmplatNm;
    }

    /**
     * tmplatNm attribute 값을 설정한다.
     * 
     * @param tmplatNm
     *            the tmplatNm to set
     */
    public void setTmplatNm(String tmplatNm) {
	this.tmplatNm = tmplatNm;
    }

    public String getBlogAt() {
		return blogAt;
	}

	public void setBlogAt(String blogAt) {
		this.blogAt = blogAt;
	}

	/**
     * toString 메소드를 대치한다.
     */
    public String toString() {
	return ToStringBuilder.reflectionToString(this);
    }
	
}
