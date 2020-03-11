package egovframework.com.uss.olp.opm.service;

import java.io.Serializable;

/**
 * 온라인POLL항목 VO Class 구현
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
public class OnlinePollItem implements Serializable {

	private static final long serialVersionUID = -5318527177437211052L;

	/** 온라인POLL 아이디  */
    private String pollId;

    /** 온라인POLL 이름 */
    private String pollNm;

    /** 온라인POLL항목 아이디  */
    private String pollIemId;

    /** 온라인POLL항목 이름 */
    private String pollIemNm;

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
     * pollId 리턴
     *
     * @return the pollId
     */
    public String getPollId() {
        return pollId;
    }

    /**
     * pollId 설정
     *
     * @param pollId the pollId to set
     */
    public void setPollId(String pollId) {
        this.pollId = pollId;
    }

    /**
     * pollNm 리턴
     *
     * @return the pollNm
     */
    public String getPollNm() {
        return pollNm;
    }

    /**
     * pollNm 설정
     *
     * @param pollNm the pollNm to set
     */
    public void setPollNm(String pollNm) {
        this.pollNm = pollNm;
    }

    /**
     * pollIemId 리턴
     *
     * @return the pollIemId
     */
    public String getPollIemId() {
        return pollIemId;
    }

    /**
     * pollIemId 설정
     *
     * @param pollIemId the pollIemId to set
     */
    public void setPollIemId(String pollIemId) {
        this.pollIemId = pollIemId;
    }

    /**
     * pollIemNm 리턴
     *
     * @return the pollIemNm
     */
    public String getPollIemNm() {
        return pollIemNm;
    }

    /**
     * pollIemNm 설정
     *
     * @param pollIemNm the pollIemNm to set
     */
    public void setPollIemNm(String pollIemNm) {
        this.pollIemNm = pollIemNm;
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
