package egovframework.com.cop.adb.service;

import java.io.Serializable;

/**
 * 주소록구성원 관리를 위한 모델 클래스
 * @author 공통컴포넌트개발팀 윤성록
 * @since 2009.09.25
 * @version 1.0
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *	2016.12.13 최두영          클래스명 변경
 * </pre>
 */
@SuppressWarnings("serial")
public class AddressBookUser implements Serializable{

    /** 주소록구성원 아이디 */
    private String adbkUserId = "";  
    
    /** 주소록 아이디 */
    private String adbkId = "";
    
    /** 사용자 아이디 */
    private String emplyrId = "";
    
    /** 명함 아이디 */
    private String ncrdId = "";
    
    /** 주소록구성원 이름 */
    private String nm = "";    

    /** 이메일 주소  */
    private String emailAdres = "";
    
    /** 집 전화번호  */
    private String homeTelno = "";
    
    /** 폰 번호  */
    private String moblphonNo = "";
    
    /** 회사 번호  */
    private String offmTelno = "";
    
    /** 팩스 번호  */
    private String fxnum = "";
        
    /**
     * adbkUserId attribute를 리턴한다.
     * 
     * @return the adbkUserId
     */
    public String getAdbkUserId() {
        return adbkUserId;
    }
    
    /**
     * adbkUserId attribute 값을 설정한다.
     * 
     * @param adbkUserId
     *            the adbkUserId to set
     */
    public void setAdbkUserId(String adbkUserId) {
        this.adbkUserId = adbkUserId;
    }
    
    /**
     * adbkId attribute를 리턴한다.
     * 
     * @return the adbkId
     */
    public String getAdbkId() {
        return adbkId;
    }
    
    /**
     * adbkId attribute 값을 설정한다.
     * 
     * @param adbkId
     *            the adbkId to set
     */
    public void setAdbkId(String adbkId) {
        this.adbkId = adbkId;
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
     * ncrdId attribute를 리턴한다.
     * 
     * @return the ncrdId
     */
    public String getNcrdId() {
        return ncrdId;
    }
    
    /**
     * ncrdId attribute 값을 설정한다.
     * 
     * @param ncrdId
     *            the ncrdId to set
     */
    public void setNcrdId(String ncrdId) {
        this.ncrdId = ncrdId;
    }
    
    /**
     * nm attribute를 리턴한다.
     * 
     * @return the nm
     */
    public String getNm() {
        return nm;
    }
    
    /**
     * nm attribute 값을 설정한다.
     * 
     * @param nm
     *            the nm to set
     */
    public void setNm(String nm) {
        this.nm = nm;
    }
    
    /**
     * emailAdres attribute를 리턴한다.
     * 
     * @return the emailAdres
     */
    public String getEmailAdres() {
        return emailAdres;
    }
    
    /**
     * emailAdres attribute 값을 설정한다.
     * 
     * @param emailAdres
     *            the emailAdres to set
     */
    public void setEmailAdres(String emailAdres) {
        this.emailAdres = emailAdres;
    }    
    
    /**
     * homeTelno attribute를 리턴한다.
     * 
     * @return the homeTelno
     */
    public String getHomeTelno() {
        return homeTelno;
    }

    /**
     * homeTelno attribute 값을 설정한다.
     * 
     * @param homeTelno
     *            the homeTelno to set
     */
    public void setHomeTelno(String homeTelno) {
        this.homeTelno = homeTelno;
    }

    /**
     * moblphonNo attribute를 리턴한다.
     * 
     * @return the moblphonNo
     */
    public String getMoblphonNo() {
        return moblphonNo;
    }

    /**
     * moblphonNo attribute 값을 설정한다.
     * 
     * @param moblphonNo
     *            the moblphonNo to set
     */
    public void setMoblphonNo(String moblphonNo) {
        this.moblphonNo = moblphonNo;
    }

    /**
     * offmTelno attribute를 리턴한다.
     * 
     * @return the offmTelno
     */
    public String getOffmTelno() {
        return offmTelno;
    }

    /**
     * offmTelno attribute 값을 설정한다.
     * 
     * @param offmTelno
     *            the offmTelno to set
     */
    public void setOffmTelno(String offmTelno) {
        this.offmTelno = offmTelno;
    }

    /**
     * fxnum attribute를 리턴한다.
     * 
     * @return the fxnum
     */
    public String getFxnum() {
        return fxnum;
    }

    /**
     * fxnum attribute 값을 설정한다.
     * 
     * @param fxnum
     *            the fxnum to set
     */
    public void setFxnum(String fxnum) {
        this.fxnum = fxnum;
    }
}
