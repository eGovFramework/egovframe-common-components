package egovframework.com.uss.olh.omm.service;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ToStringBuilder;

import egovframework.com.cmm.ComDefaultVO;

/**
 * 온라인메뉴얼 VO Class 구현
 * @author 공통서비스 장동한
 * @since 2009.07.03
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
public class OnlineManualVO extends ComDefaultVO implements Serializable {

	private static final long serialVersionUID = -7024282928339275971L;

	/** 온라인메뉴얼 아이디 */
    private String onlineMnlId;

    /** 온라인메뉴얼 명 */
    private String onlineMnlNm;

    /** 온라인메뉴얼 구분코드 */
    private String onlineMnlSeCode;

    /** 온라인메뉴얼 구분코드 */
    private String onlineMnlSeCodeNm;
    
    /** 온라인메뉴얼 정의 */
    private String onlineMnlDf;

    /** 온라인메뉴얼 설명 */
    private String onlineMnlDc;

    /** 최초등록시점 */
    private String frstRegisterPnttm;

    /** 최초등록아이디 */
    private String frstRegisterId;
    
    /** 최초등록자 */
    private String frstRegisterNm;

    /** 최종수정일 */
    private String lastUpdusrPnttm;

    /** 최종수정자 아이디 */
    private String lastUpdusrId;

    /** 컨트롤 명령어 */
    private String cmd;

    /**
     * onlineMnlId 리턴
     *
     * @return the onlineMnlId
     */
    public String getOnlineMnlId() {
        return onlineMnlId;
    }

    /**
     * onlineMnlId 설정
     *
     * @param onlineMnlId the onlineMnlId to set
     */
    public void setOnlineMnlId(String onlineMnlId) {
        this.onlineMnlId = onlineMnlId;
    }

    /**
     * onlineMnlNm 리턴
     *
     * @return the onlineMnlNm
     */
    public String getOnlineMnlNm() {
        return onlineMnlNm;
    }

    /**
     * onlineMnlNm 설정
     *
     * @param onlineMnlNm the onlineMnlNm to set
     */
    public void setOnlineMnlNm(String onlineMnlNm) {
        this.onlineMnlNm = onlineMnlNm;
    }

    /**
     * onlineMnlSeCode 리턴
     *
     * @return the onlineMnlSeCode
     */
    public String getOnlineMnlSeCode() {
        return onlineMnlSeCode;
    }

    /**
     * onlineMnlSeCode 설정
     *
     * @param onlineMnlSeCode the onlineMnlSeCode to set
     */
    public void setOnlineMnlSeCode(String onlineMnlSeCode) {
        this.onlineMnlSeCode = onlineMnlSeCode;
    }

    /**
     * onlineMnlSeCodeNm 리턴
     *
     * @return the onlineMnlSeCode
     */
    public String getOnlineMnlSeCodeNm() {
        return onlineMnlSeCodeNm;
    }

    /**
     * onlineMnlSeCodeNm 설정
     *
     * @param onlineMnlSeCodeNm the onlineMnlSeCodeNm to set
     */
    public void setOnlineMnlSeCodeNm(String onlineMnlSeCodeNm) {
        this.onlineMnlSeCodeNm = onlineMnlSeCodeNm;
    }
    
    /**
     * onlineMnlDf 리턴
     *
     * @return the onlineMnlDf
     */
    public String getOnlineMnlDf() {
        return onlineMnlDf;
    }

    /**
     * onlineMnlDf 설정
     *
     * @param onlineMnlDf the onlineMnlDf to set
     */
    public void setOnlineMnlDf(String onlineMnlDf) {
        this.onlineMnlDf = onlineMnlDf;
    }

    /**
     * onlineMnlDc 리턴
     *
     * @return the onlineMnlDc
     */
    public String getOnlineMnlDc() {
        return onlineMnlDc;
    }

    /**
     * onlineMnlDc 설정
     *
     * @param onlineMnlDc the onlineMnlDc to set
     */
    public void setOnlineMnlDc(String onlineMnlDc) {
        this.onlineMnlDc = onlineMnlDc;
    }

    /**
     * frstRegisterPnttm 리턴
     *
     * @return the frstRegisterPnttm
     */
    public String getFrstRegisterPnttm() {
        return frstRegisterPnttm;
    }

    /**
     * frstRegisterPnttm 설정
     *
     * @param frstRegisterPnttm the frstRegisterPnttm to set
     */
    public void setFrstRegisterPnttm(String frstRegisterPnttm) {
        this.frstRegisterPnttm = frstRegisterPnttm;
    }

    /**
     * frstRegisterId 리턴
     *
     * @return the frstRegisterId
     */
    public String getFrstRegisterId() {
        return frstRegisterId;
    }

    /**
     * frstRegisterId 설정
     *
     * @param frstRegisterId the frstRegisterId to set
     */
    public void setFrstRegisterId(String frstRegisterId) {
        this.frstRegisterId = frstRegisterId;
    }
    
    /**
     * frstRegisterNm 리턴
     *
     * @return the frstRegisterNm
     */
    public String getFrstRegisterNm() {
    	return frstRegisterNm;
    }
    
    /**
     * frstRegisterNm 설정
     *
     * @param frstRegisterNm the frstRegisterNm to set
     */
    public void setFrstRegisterNm(String frstRegisterNm) {
    	this.frstRegisterNm = frstRegisterNm;
    }

    /**
     * lastUpdusrPnttm 리턴
     *
     * @return the lastUpdusrPnttm
     */
    public String getLastUpdusrPnttm() {
        return lastUpdusrPnttm;
    }

    /**
     * lastUpdusrPnttm 설정
     *
     * @param lastUpdusrPnttm the lastUpdusrPnttm to set
     */
    public void setLastUpdusrPnttm(String lastUpdusrPnttm) {
        this.lastUpdusrPnttm = lastUpdusrPnttm;
    }

    /**
     * lastUpdusrId 리턴
     *
     * @return the lastUpdusrId
     */
    public String getLastUpdusrId() {
        return lastUpdusrId;
    }

    /**
     * lastUpdusrId 설정
     *
     * @param lastUpdusrId the lastUpdusrId to set
     */
    public void setLastUpdusrId(String lastUpdusrId) {
        this.lastUpdusrId = lastUpdusrId;
    }

    /**
     * cmd 리턴
     *
     * @return the cmd
     */
    public String getCmd() {
        return cmd;
    }

    /**
     * cmd 설정
     *
     * @param cmd the cmd to set
     */
    public void setCmd(String cmd) {
        this.cmd = cmd;
    }

    /**
   	 * toString 메소드를 대치한다.
   	 */
   	public String toString(){
   		return ToStringBuilder.reflectionToString(this);
   	}


}
