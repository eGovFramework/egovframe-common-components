package egovframework.com.uss.olp.qim.service;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;
/**
 * 설문항목관리 VO Class 구현
 * @author 공통서비스 장동한
 * @since 2009.03.20
 * @version 1.0
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2009.03.20  장동한          최초 생성
 *   2024.09.07  권태성          필수값 @NotEmpty 추가
 * </pre>
 */
public class QustnrItemManageVO implements Serializable {

	private static final long serialVersionUID = -8233519594470362395L;

	/** 설문문항 아이디 */
	private String qestnrQesitmId = "";

	/** 설문지 아이디 */
	@NotEmpty(message = "설문정보{common.required.msg}")
	private String qestnrId = "";

	/** 항목순번 */
	@NotEmpty(message = "항목순번{common.required.msg}")
	private String iemSn = "";

	/** 항목내용 */
	@NotEmpty(message = "항목내용{common.required.msg}")
	private String qustnrIemId = "";

	/** 설문항목아이디 */
	private String iemCn = "";

	/** 기타답변여부 */
	@NotEmpty(message = "기타답변여부{common.required.msg}")
	private String etcAnswerAt = "";

	/** 설문항목(을)를 아이디 */
	@NotEmpty(message = "설문문항정보{common.required.msg}")
	private String qestnrTmplatId = "";

	/** 최초등록시점  */
	private String frstRegisterPnttm = "";

	/** 최초등록아이디 */
	private String frstRegisterId = "";

	/** 최종수정일 */
	private String lastUpdusrPnttm = "";

	/** 최종수정자 아이디 */
	private String lastUpdusrId = "";

	/** 컨트롤 명령어 */
	private String cmd = "";

	/**
	 * qestnrQesitmId attribute 를 리턴한다.
	 * @return the String
	 */
	public String getQestnrQesitmId() {
		return qestnrQesitmId;
	}

	/**
	 * qestnrQesitmId attribute 값을 설정한다.
	 * @return qestnrQesitmId String
	 */
	public void setQestnrQesitmId(String qestnrQesitmId) {
		this.qestnrQesitmId = qestnrQesitmId;
	}

	/**
	 * qestnrId attribute 를 리턴한다.
	 * @return the String
	 */
	public String getQestnrId() {
		return qestnrId;
	}

	/**
	 * qestnrId attribute 값을 설정한다.
	 * @return qestnrId String
	 */
	public void setQestnrId(String qestnrId) {
		this.qestnrId = qestnrId;
	}

	/**
	 * iemSn attribute 를 리턴한다.
	 * @return the String
	 */
	public String getIemSn() {
		return iemSn;
	}

	/**
	 * iemSn attribute 값을 설정한다.
	 * @return iemSn String
	 */
	public void setIemSn(String iemSn) {
		this.iemSn = iemSn;
	}

	/**
	 * qustnrIemId attribute 를 리턴한다.
	 * @return the String
	 */
	public String getQustnrIemId() {
		return qustnrIemId;
	}

	/**
	 * qustnrIemId attribute 값을 설정한다.
	 * @return qustnrIemId String
	 */
	public void setQustnrIemId(String qustnrIemId) {
		this.qustnrIemId = qustnrIemId;
	}

	/**
	 * iemCn attribute 를 리턴한다.
	 * @return the String
	 */
	public String getIemCn() {
		return iemCn;
	}

	/**
	 * iemCn attribute 값을 설정한다.
	 * @return iemCn String
	 */
	public void setIemCn(String iemCn) {
		this.iemCn = iemCn;
	}

	/**
	 * etcAnswerAt attribute 를 리턴한다.
	 * @return the String
	 */
	public String getEtcAnswerAt() {
		return etcAnswerAt;
	}

	/**
	 * etcAnswerAt attribute 값을 설정한다.
	 * @return etcAnswerAt String
	 */
	public void setEtcAnswerAt(String etcAnswerAt) {
		this.etcAnswerAt = etcAnswerAt;
	}

	/**
	 * qestnrTmplatId attribute 를 리턴한다.
	 * @return the String
	 */
	public String getQestnrTmplatId() {
		return qestnrTmplatId;
	}

	/**
	 * qestnrTmplatId attribute 값을 설정한다.
	 * @return qestnrTmplatId String
	 */
	public void setQestnrTmplatId(String qestnrTmplatId) {
		this.qestnrTmplatId = qestnrTmplatId;
	}

	/**
	 * frstRegisterPnttm attribute 를 리턴한다.
	 * @return the String
	 */
	public String getFrstRegisterPnttm() {
		return frstRegisterPnttm;
	}

	/**
	 * frstRegisterPnttm attribute 값을 설정한다.
	 * @return frstRegisterPnttm String
	 */
	public void setFrstRegisterPnttm(String frstRegisterPnttm) {
		this.frstRegisterPnttm = frstRegisterPnttm;
	}

	/**
	 * frstRegisterId attribute 를 리턴한다.
	 * @return the String
	 */
	public String getFrstRegisterId() {
		return frstRegisterId;
	}

	/**
	 * frstRegisterId attribute 값을 설정한다.
	 * @return frstRegisterId String
	 */
	public void setFrstRegisterId(String frstRegisterId) {
		this.frstRegisterId = frstRegisterId;
	}

	/**
	 * lastUpdusrPnttm attribute 를 리턴한다.
	 * @return the String
	 */
	public String getLastUpdusrPnttm() {
		return lastUpdusrPnttm;
	}

	/**
	 * lastUpdusrPnttm attribute 값을 설정한다.
	 * @return lastUpdusrPnttm String
	 */
	public void setLastUpdusrPnttm(String lastUpdusrPnttm) {
		this.lastUpdusrPnttm = lastUpdusrPnttm;
	}

	/**
	 * lastUpdusrId attribute 를 리턴한다.
	 * @return the String
	 */
	public String getLastUpdusrId() {
		return lastUpdusrId;
	}

	/**
	 * lastUpdusrId attribute 값을 설정한다.
	 * @return lastUpdusrId String
	 */
	public void setLastUpdusrId(String lastUpdusrId) {
		this.lastUpdusrId = lastUpdusrId;
	}

	/**
	 * cmd attribute 를 리턴한다.
	 * @return the String
	 */
	public String getCmd() {
		return cmd;
	}

	/**
	 * cmd attribute 값을 설정한다.
	 * @return cmd String
	 */
	public void setCmd(String cmd) {
		this.cmd = cmd;
	}


}
