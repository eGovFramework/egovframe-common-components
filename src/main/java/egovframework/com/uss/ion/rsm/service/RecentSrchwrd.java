package egovframework.com.uss.ion.rsm.service;

import java.io.Serializable;

import egovframework.com.cmm.ComDefaultVO;

/**
 * 최근검색어 VO Class 구현
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
public class RecentSrchwrd extends ComDefaultVO implements Serializable {

	private static final long serialVersionUID = 4031295690314547576L;

	/** 최근검색어관리ID */
    private String srchwrdManageId;

    /** 최근검색어관리명 */
    private String srchwrdManageNm;

    /** 최근검색어관리URL */
    private String srchwrdManageUrl;

    /** 최근검색어사용자검색여부 */
    private String srchwrdManageUseYn;

    /** 최근검색어ID */
    private String srchwrdId;

    /** 최근검색어명 */
    private String srchwrdNm;

    /** 최근건색어건수 */
    private String srchwrdCnt;

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

    /** Ajax검색어 */
    private String q;

    /**
     * srchwrdManageId 리턴
     *
     * @return the srchwrdManageId
     */
    public String getSrchwrdManageId() {
        return srchwrdManageId;
    }

    /**
     * srchwrdManageId 설정
     *
     * @param srchwrdManageId the srchwrdManageId to set
     */
    public void setSrchwrdManageId(String srchwrdManageId) {
        this.srchwrdManageId = srchwrdManageId;
    }

    /**
     * srchwrdManageNm 리턴
     *
     * @return the srchwrdManageNm
     */
    public String getSrchwrdManageNm() {
        return srchwrdManageNm;
    }

    /**
     * srchwrdManageNm 설정
     *
     * @param srchwrdManageNm the srchwrdManageNm to set
     */
    public void setSrchwrdManageNm(String srchwrdManageNm) {
        this.srchwrdManageNm = srchwrdManageNm;
    }

    /**
     * srchwrdManageUrl 리턴
     *
     * @return the srchwrdManageUrl
     */
    public String getSrchwrdManageUrl() {
        return srchwrdManageUrl;
    }

    /**
     * srchwrdManageUrl 설정
     *
     * @param srchwrdManageUrl the srchwrdManageUrl to set
     */
    public void setSrchwrdManageUrl(String srchwrdManageUrl) {
        this.srchwrdManageUrl = srchwrdManageUrl;
    }

    /**
     * srchwrdManageUseYn 리턴
     *
     * @return the srchwrdManageUseYn
     */
    public String getSrchwrdManageUseYn() {
        return srchwrdManageUseYn;
    }

    /**
     * srchwrdManageUseYn 설정
     *
     * @param srchwrdManageUseYn the srchwrdManageUseYn to set
     */
    public void setSrchwrdManageUseYn(String srchwrdManageUseYn) {
        this.srchwrdManageUseYn = srchwrdManageUseYn;
    }

    /**
     * srchwrdId 리턴
     *
     * @return the srchwrdId
     */
    public String getSrchwrdId() {
        return srchwrdId;
    }

    /**
     * srchwrdId 설정
     *
     * @param srchwrdId the srchwrdId to set
     */
    public void setSrchwrdId(String srchwrdId) {
        this.srchwrdId = srchwrdId;
    }

    /**
     * srchwrdNm 리턴
     *
     * @return the srchwrdNm
     */
    public String getSrchwrdNm() {
        return srchwrdNm;
    }

    /**
     * srchwrdNm 설정
     *
     * @param srchwrdNm the srchwrdNm to set
     */
    public void setSrchwrdNm(String srchwrdNm) {
        this.srchwrdNm = srchwrdNm;
    }

    /**
     * srchwrdCnt 리턴
     *
     * @return the srchwrdCnt
     */
    public String getSrchwrdCnt() {
        return srchwrdCnt;
    }

    /**
     * srchwrdCnt 설정
     *
     * @param srchwrdCnt the srchwrdCnt to set
     */
    public void setSrchwrdCnt(String srchwrdCnt) {
        this.srchwrdCnt = srchwrdCnt;
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

    /**
     * q 리턴
     *
     * @return the q
     */
    public String getQ() {
        return q;
    }

    /**
     * q 설정
     *
     * @param q the q to set
     */
    public void setQ(String q) {
        this.q = q;
    }

}

