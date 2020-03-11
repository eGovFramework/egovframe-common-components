package egovframework.com.uss.ion.ulm.service;

import java.io.Serializable;

/**
 * 통합링크관리 VO Class 구현
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
public class UnityLink implements Serializable {

	private static final long serialVersionUID = 1607776015478505197L;

	/** 통합링크 아이디 */
    private String unityLinkId;

    /** 통합링크 그룹 */
    private String unityLinkSeCode;

    /** 통합링크 명 */
    private String unityLinkNm;

    /** 통합링크 URL */
    private String unityLinkUrl;

    /** 통합링크 설명 */
    private String unityLinkDc;

    /** 최초등록시점 */
    private String frstRegisterPnttm;

    /** 최초등록아이디 */
    private String frstRegisterId;

    /** 최종수정일 */
    private String lastUpdusrPnttm;

    /** 최종수정자 아이디 */
    private String lastUpdusrId;

    /** 컨트롤 명령어 */
    private String cmd;

    /**
     * unityLinkId 리턴
     *
     * @return the unityLinkId
     */
    public String getUnityLinkId() {
        return unityLinkId;
    }

    /**
     * unityLinkId 설정
     *
     * @param unityLinkId the unityLinkId to set
     */
    public void setUnityLinkId(String unityLinkId) {
        this.unityLinkId = unityLinkId;
    }

    /**
     * unityLinkSeCode 리턴
     *
     * @return the unityLinkSeCode
     */
    public String getUnityLinkSeCode() {
        return unityLinkSeCode;
    }

    /**
     * unityLinkSeCode 설정
     *
     * @param unityLinkSeCode the unityLinkSeCode to set
     */
    public void setUnityLinkSeCode(String unityLinkSeCode) {
        this.unityLinkSeCode = unityLinkSeCode;
    }

    /**
     * unityLinkNm 리턴
     *
     * @return the unityLinkNm
     */
    public String getUnityLinkNm() {
        return unityLinkNm;
    }

    /**
     * unityLinkNm 설정
     *
     * @param unityLinkNm the unityLinkNm to set
     */
    public void setUnityLinkNm(String unityLinkNm) {
        this.unityLinkNm = unityLinkNm;
    }

    /**
     * unityLinkUrl 리턴
     *
     * @return the unityLinkUrl
     */
    public String getUnityLinkUrl() {
        return unityLinkUrl;
    }

    /**
     * unityLinkUrl 설정
     *
     * @param unityLinkUrl the unityLinkUrl to set
     */
    public void setUnityLinkUrl(String unityLinkUrl) {
        this.unityLinkUrl = unityLinkUrl;
    }

    /**
     * unityLinkDc 리턴
     *
     * @return the unityLinkDc
     */
    public String getUnityLinkDc() {
        return unityLinkDc;
    }

    /**
     * unityLinkDc 설정
     *
     * @param unityLinkDc the unityLinkDc to set
     */
    public void setUnityLinkDc(String unityLinkDc) {
        this.unityLinkDc = unityLinkDc;
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



}
