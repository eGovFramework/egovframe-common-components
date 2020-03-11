package egovframework.com.cop.bbs.service;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 *  게시판 속성정보를 담기위한 엔티티 클래스
 * @author 공통서비스개발팀 이삼섭
 * @since 2009.06.01
 * @version 1.0
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2009.03.12  이삼섭          최초 생성
 *   2009.06.26  한성곤		2단계 기능 추가 (댓글관리, 만족도조사)
 *
 * </pre>
 */
@SuppressWarnings("serial")
public class BoardMaster implements Serializable {
    
    /** 게시판 아이디 */
    private String bbsId = "";
    
    /** 게시판 소개 */
    private String bbsIntrcn = "";
    
    /** 게시판 명 */
    private String bbsNm = "";
    
    /** 게시판 유형코드 */
    private String bbsTyCode = "";
    
    /** 파일첨부가능여부 */
    private String fileAtchPosblAt = "";
    
    /** 최초등록자 아이디 */
    private String frstRegisterId = "";
    
    /** 최초등록시점 */
    private String frstRegisterPnttm = "";
    
    /** 최종수정자 아이디 */
    public String lastUpdusrId = "";
    
    /** 최종수정시점 */
    private String lastUpdusrPnttm = "";
    
    /** 첨부가능파일숫자 */
    private int atchPosblFileNumber = 0;
    
    /** 첨부가능파일사이즈 */
    private String atchPosblFileSize = "";
    
    /** 답장가능여부 */
    private String replyPosblAt = "";
    
    /** 템플릿 아이디 */
    private String tmplatId = "";
    
    /** 사용여부 */
    private String useAt = "";
    
    /** 사용플래그 */
    private String bbsUseFlag = "";
    
    /** 대상 아이디 */
    private String trgetId = "";
    
    /** 등록구분코드 */
    private String registSeCode = "";
    
    /** 유일 아이디 */
    private String uniqId = "";
    
    /** 템플릿 명 */
    private String tmplatNm = "";
    
    /** 커뮤니티 ID */
    private String cmmntyId;
    
    /** 블로그 ID */
    private String blogId;
    
    /** 블로그 사용 유무 */
    private String blogAt;
    
    //---------------------------------
    // 2009.06.26 : 2단계 기능 추가
    //---------------------------------
    /** 추가 option (댓글-comment, 만족도조사-stsfdg) */
    private String option = "";
    
    /** 댓글 여부 */
    private String commentAt = "";
    
    /** 만족도조사 */
    private String stsfdgAt = "";
    ////-------------------------------

    /**
     * bbsId attribute를 리턴한다.
     * 
     * @return the bbsId
     */
    public String getBbsId() {
	return bbsId;
    }

    /**
     * bbsId attribute 값을 설정한다.
     * 
     * @param bbsId
     *            the bbsId to set
     */
    public void setBbsId(String bbsId) {
	this.bbsId = bbsId;
    }

    /**
     * bbsIntrcn attribute를 리턴한다.
     * 
     * @return the bbsIntrcn
     */
    public String getBbsIntrcn() {
	return bbsIntrcn;
    }

    /**
     * bbsIntrcn attribute 값을 설정한다.
     * 
     * @param bbsIntrcn
     *            the bbsIntrcn to set
     */
    public void setBbsIntrcn(String bbsIntrcn) {
	this.bbsIntrcn = bbsIntrcn;
    }

    /**
     * bbsNm attribute를 리턴한다.
     * 
     * @return the bbsNm
     */
    public String getBbsNm() {
	return bbsNm;
    }

    /**
     * bbsNm attribute 값을 설정한다.
     * 
     * @param bbsNm
     *            the bbsNm to set
     */
    public void setBbsNm(String bbsNm) {
	this.bbsNm = bbsNm;
    }

    /**
     * bbsTyCode attribute를 리턴한다.
     * 
     * @return the bbsTyCode
     */
    public String getBbsTyCode() {
	return bbsTyCode;
    }

    /**
     * bbsTyCode attribute 값을 설정한다.
     * 
     * @param bbsTyCode
     *            the bbsTyCode to set
     */
    public void setBbsTyCode(String bbsTyCode) {
	this.bbsTyCode = bbsTyCode;
    }

    /**
     * fileAtchPosblAt attribute를 리턴한다.
     * 
     * @return the fileAtchPosblAt
     */
    public String getFileAtchPosblAt() {
	return fileAtchPosblAt;
    }

    /**
     * fileAtchPosblAt attribute 값을 설정한다.
     * 
     * @param fileAtchPosblAt
     *            the fileAtchPosblAt to set
     */
    public void setFileAtchPosblAt(String fileAtchPosblAt) {
	this.fileAtchPosblAt = fileAtchPosblAt;
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
     * atchPosblFileNumber attribute를 리턴한다.
     * 
     * @return the atchPosblFileNumber
     */
    public int getAtchPosblFileNumber() {
	return atchPosblFileNumber;
    }

    /**
     * atchPosblFileNumber attribute 값을 설정한다.
     * 
     * @param atchPosblFileNumber
     *            the atchPosblFileNumber to set
     */
    public void setAtchPosblFileNumber(int atchPosblFileNumber) {
	this.atchPosblFileNumber = atchPosblFileNumber;
    }

    /**
     * atchPosblFileSize attribute를 리턴한다.
     * 
     * @return the atchPosblFileSize
     */
    public String getAtchPosblFileSize() {
	return atchPosblFileSize;
    }

    /**
     * atchPosblFileSize attribute 값을 설정한다.
     * 
     * @param atchPosblFileSize
     *            the atchPosblFileSize to set
     */
    public void setAtchPosblFileSize(String atchPosblFileSize) {
	this.atchPosblFileSize = atchPosblFileSize;
    }

    /**
     * replyPosblAt attribute를 리턴한다.
     * 
     * @return the replyPosblAt
     */
    public String getReplyPosblAt() {
	return replyPosblAt;
    }

    /**
     * replyPosblAt attribute 값을 설정한다.
     * 
     * @param replyPosblAt
     *            the replyPosblAt to set
     */
    public void setReplyPosblAt(String replyPosblAt) {
	this.replyPosblAt = replyPosblAt;
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
     * bbsUseFlag attribute를 리턴한다.
     * 
     * @return the bbsUseFlag
     */
    public String getBbsUseFlag() {
	return bbsUseFlag;
    }

    /**
     * bbsUseFlag attribute 값을 설정한다.
     * 
     * @param bbsUseFlag
     *            the bbsUseFlag to set
     */
    public void setBbsUseFlag(String bbsUseFlag) {
	this.bbsUseFlag = bbsUseFlag;
    }

    /**
     * trgetId attribute를 리턴한다.
     * 
     * @return the trgetId
     */
    public String getTrgetId() {
	return trgetId;
    }

    /**
     * trgetId attribute 값을 설정한다.
     * 
     * @param trgetId
     *            the trgetId to set
     */
    public void setTrgetId(String trgetId) {
	this.trgetId = trgetId;
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
     * uniqId attribute를 리턴한다.
     * 
     * @return the uniqId
     */
    public String getUniqId() {
	return uniqId;
    }

    /**
     * uniqId attribute 값을 설정한다.
     * 
     * @param uniqId
     *            the uniqId to set
     */
    public void setUniqId(String uniqId) {
	this.uniqId = uniqId;
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

    /**
     * option attribute를 리턴한다.
     * @return the option
     */
    public String getOption() {
        return option;
    }

    /**
     * option attribute 값을 설정한다.
     * @param option the option to set
     */
    public void setOption(String option) {
        this.option = option;
    }

    /**
     * commentAt attribute를 리턴한다.
     * @return the commentAt
     */
    public String getCommentAt() {
        return commentAt;
    }

    /**
     * commentAt attribute 값을 설정한다.
     * @param commentAt the commentAt to set
     */
    public void setCommentAt(String commentAt) {
        this.commentAt = commentAt;
    }

    /**
     * stsfdgAt attribute를 리턴한다.
     * @return the stsfdgAt
     */
    public String getStsfdgAt() {
        return stsfdgAt;
    }

    /**
     * stsfdg attribute 값을 설정한다.
     * @param stsfdgAt the stsfdgAt to set
     */
    public void setStsfdgAt(String stsfdgAt) {
        this.stsfdgAt = stsfdgAt;
    }
    
    /**
     * cmmntyId attribute를 리턴한다.
     * @return the cmmntyId
     */
    public String getCmmntyId() {
    	return cmmntyId;
    }
    
    /**
     * cmmntyId attribute 값을 설정한다.
     * @param cmmntyId the cmmntyId to set
     */
    public void setCmmntyId(String cmmntyId) {
    	this.cmmntyId = cmmntyId;
    }

    public String getBlogId() {
		return blogId;
	}

	public void setBlogId(String blogId) {
		this.blogId = blogId;
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
