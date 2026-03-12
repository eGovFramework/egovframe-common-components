package egovframework.com.cop.adb.service;

import java.io.Serializable;

import org.egovframe.rte.ptl.reactive.validation.EgovNullCheck;
import jakarta.validation.constraints.Size;
/**
 * 주소록 관리를 위한 모델 클래스
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
 *   2009.9.25  윤성록          최초 생성
 *   2016.12.13 최두영          클래스명 변경
 *
 * </pre>
 */
@SuppressWarnings("serial")
public class AddressBook implements Serializable{
  
    /** 주소록 아이디 */
    private String adbkId = "";
    
    /** 주소록 명 */
    @EgovNullCheck
    @Size(max=50)
    private String adbkNm = "";
    
    /** 주소록 공개범위 */
    @EgovNullCheck
    private String othbcScope = "";
    
    /** 최초등록자 부서 */
    private String trgetOrgnztId = "";
    
    /** 주소록 사용여부 */
    private String useAt = "";
    
    /** 주소록 등록자 아이디*/
    private String wrterId = "";
    
    /** 최초등록자 아이디 */
    private String frstRegisterId = "";
    
    /** 최초등록시점 */
    private String frstRegisterPnttm = "";
    
    /** 최종수정자 아이디 */
    private String lastUpdusrId = "";
    
    /** 최종수정시점 */
    private String lastUpdusrPnttm = "";
    
    
    /**
     * AdbkId attribute를 리턴한다.
     * 
     * @return the AdbkId
     */
    public String getAdbkId() {
        return adbkId;
    }

    /**
     * AdbkId attribute 값을 설정한다.
     * 
     * @param AdbkId
     *            the AdbkId to set
     */
    public void setAdbkId(String adbkId) {
        this.adbkId = adbkId;
    }   
    
    /**
     * adbkNm attribute를 리턴한다.
     * 
     * @return the adbkNm
     */
    public String getAdbkNm() {
        return adbkNm;
    }

    /**
     * AdbkNm attribute 값을 설정한다.
     * 
     * @param AdbkNm
     *            the AdbkNm to set
     */
    public void setAdbkNm(String adbkNm) {
        this.adbkNm = adbkNm;
    }

    /**
     * othbcScope attribute를 리턴한다.
     * 
     * @return the othbcScope
     */
    public String getOthbcScope() {
        return othbcScope;
    }

    /**
     * othbcScope attribute 값을 설정한다.
     * 
     * @param othbcScope
     *            the othbcScope to set
     */
    public void setOthbcScope(String othbcScope) {
        this.othbcScope = othbcScope;
    }

    /**
     * trgetOrgnztId attribute를 리턴한다.
     * 
     * @return the trgetOrgnztId
     */
    public String getTrgetOrgnztId() {
        return trgetOrgnztId;
    }

    /**
     * trgetOrgnztId attribute 값을 설정한다.
     * 
     * @param trgetOrgnztId
     *            the trgetOrgnztId to set
     */
    public void setTrgetOrgnztId(String trgetOrgnztId) {
        this.trgetOrgnztId = trgetOrgnztId;
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
     * wrterId attribute를 리턴한다.
     * 
     * @return the wrterId
     */
    public String getWrterId() {
        return wrterId;
    }

    /**
     * wrterId attribute 값을 설정한다.
     * 
     * @param wrterId
     *            the wrterId to set
     */
    public void setWrterId(String wrterId) {
        this.wrterId = wrterId;
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
     * @param AdbkNm
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

  
    
}
