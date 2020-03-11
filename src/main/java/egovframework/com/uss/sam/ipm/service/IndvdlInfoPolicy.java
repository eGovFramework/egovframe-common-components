package egovframework.com.uss.sam.ipm.service;

import java.io.Serializable;

/**
 * 개인정보보호정책 VO Class 구현
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
public class IndvdlInfoPolicy implements Serializable {

	private static final long serialVersionUID = 2087042986899364386L;

	/** 개인정보보호정책 아이디 */
    private String indvdlInfoId;

    /** 개인정보보호정책 명 */
    private String indvdlInfoNm;

    /** 개인정보보호정책 내용 */
    private String indvdlInfoDc;

    /** 개인정보보호정책 동의여부 */
    private String indvdlInfoYn;

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
     * indvdlInfoId 리턴
     *
     * @return the indvdlInfoId
     */
    public String getIndvdlInfoId() {
        return indvdlInfoId;
    }

    /**
     * indvdlInfoId 설정
     *
     * @param indvdlInfoId the indvdlInfoId to set
     */
    public void setIndvdlInfoId(String indvdlInfoId) {
        this.indvdlInfoId = indvdlInfoId;
    }

    /**
     * indvdlInfoNm 리턴
     *
     * @return the indvdlInfoNm
     */
    public String getIndvdlInfoNm() {
        return indvdlInfoNm;
    }

    /**
     * indvdlInfoNm 설정
     *
     * @param indvdlInfoNm the indvdlInfoNm to set
     */
    public void setIndvdlInfoNm(String indvdlInfoNm) {
        this.indvdlInfoNm = indvdlInfoNm;
    }

    /**
     * indvdlInfoDc 리턴
     *
     * @return the indvdlInfoDc
     */
    public String getIndvdlInfoDc() {
        return indvdlInfoDc;
    }

    /**
     * indvdlInfoDc 설정
     *
     * @param indvdlInfoDc the indvdlInfoDc to set
     */
    public void setIndvdlInfoDc(String indvdlInfoDc) {
        this.indvdlInfoDc = indvdlInfoDc;
    }

    /**
     * indvdlInfoYn 리턴
     *
     * @return the indvdlInfoYn
     */
    public String getIndvdlInfoYn() {
        return indvdlInfoYn;
    }

    /**
     * indvdlInfoYn 설정
     *
     * @param indvdlInfoYn the indvdlInfoYn to set
     */
    public void setIndvdlInfoYn(String indvdlInfoYn) {
        this.indvdlInfoYn = indvdlInfoYn;
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
