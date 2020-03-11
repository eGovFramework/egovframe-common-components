package egovframework.com.utl.sys.trm.service;

import java.io.Serializable;

import egovframework.com.cmm.ComDefaultVO;

/**
 * 송수신모니터링Log에 대한 model 클래스
 *
 * @author 김진만
 * @version 1.0
 * @updated 21-6-2010 오전 10:27:13
 * @see
 * <pre>
 * == 개정이력(Modification Information) ==
 *
 *   수정일       수정자           수정내용
 *  -------     --------    ---------------------------
 *  2010.08.16   김진만     최초 생성
 * </pre>
 */
public class TrsmrcvMntrngLog extends ComDefaultVO implements Serializable {

	private static final long serialVersionUID = 5898403212784202108L;
	/**
	 * 로그ID
	 */
	private String logId;
	/**
	 * 연계ID
	 */
	private String cntcId;
	/**
	 * 테스트클래스명
	 */
	private String testClassNm;
	/**
	 * 관리자명
	 */
	private String mngrNm;
	/**
	 * 관리자이메일주소
	 */
	private String mngrEmailAddr;
	/**
	 * 모니터링상태
	 */
	private String mntrngSttus;
	/**
	 * 최종수정자 아이디
	 */
	private String lastUpdusrId;
	/**
	 * 최종수정시점
	 */
	private String lastUpdusrPnttm;
	/**
	 * 최초등록자 아이디
	 */
	private String frstRegisterId;
	/**
	 * 최초등록시점
	 */
	private String frstRegisterPnttm;
	/**
	 * 생성일시
	 */
	private String creatDt;
	/**
	 * 로그정보
	 */
	private String logInfo;


	/**
	 * 연계명
	 */
	private String cntcNm;
	/**
	 * 제공기관명
	 */
	private String provdInsttNm;
	/**
	 * 제공시스템명
	 */
	private String provdSysNm;
	/**
	 * 제공서비스명
	 */
	private String provdSvcNm;
	/**
	 * 요청기관명
	 */
	private String requstInsttNm;
	/**
	 * 요청시스템명
	 */
	private String requstSysNm;
	/**
	 * 모니터링상태명
	 */
	private String mntrngSttusNm;

	/**
	 * @return the cntcId
	 */
	public String getCntcId() {
		return cntcId;
	}
	/**
	 * @return the testClassNm
	 */
	public String getTestClassNm() {
		return testClassNm;
	}
	/**
	 * @return the mngrNm
	 */
	public String getMngrNm() {
		return mngrNm;
	}
	/**
	 * @return the mngrEmailAddr
	 */
	public String getMngrEmailAddr() {
		return mngrEmailAddr;
	}
	/**
	 * @return the mntrngSttus
	 */
	public String getMntrngSttus() {
		return mntrngSttus;
	}
	/**
	 * @return the lastUpdusrId
	 */
	public String getLastUpdusrId() {
		return lastUpdusrId;
	}
	/**
	 * @return the lastUpdusrPnttm
	 */
	public String getLastUpdusrPnttm() {
		return lastUpdusrPnttm;
	}
	/**
	 * @return the cntcNm
	 */
	public String getCntcNm() {
		return cntcNm;
	}
	/**
	 * @return the provdInsttNm
	 */
	public String getProvdInsttNm() {
		return provdInsttNm;
	}
	/**
	 * @return the provdSysNm
	 */
	public String getProvdSysNm() {
		return provdSysNm;
	}
	/**
	 * @return the provdSvcNm
	 */
	public String getProvdSvcNm() {
		return provdSvcNm;
	}
	/**
	 * @return the requstInsttNm
	 */
	public String getRequstInsttNm() {
		return requstInsttNm;
	}
	/**
	 * @return the requstSysNm
	 */
	public String getRequstSysNm() {
		return requstSysNm;
	}
	/**
	 * @return the mntrngSttusNm
	 */
	public String getMntrngSttusNm() {
		return mntrngSttusNm;
	}

	/**
	 * @param cntcId the cntcId to set
	 */
	public void setCntcId(String cntcId) {
		this.cntcId = cntcId;
	}
	/**
	 * @param testClassNm the testClassNm to set
	 */
	public void setTestClassNm(String testClassNm) {
		this.testClassNm = testClassNm;
	}
	/**
	 * @param mngrNm the mngrNm to set
	 */
	public void setMngrNm(String mngrNm) {
		this.mngrNm = mngrNm;
	}
	/**
	 * @param mngrEmailAddr the mngrEmailAddr to set
	 */
	public void setMngrEmailAddr(String mngrEmailAddr) {
		this.mngrEmailAddr = mngrEmailAddr;
	}
	/**
	 * @param mntrngSttus the mntrngSttus to set
	 */
	public void setMntrngSttus(String mntrngSttus) {
		this.mntrngSttus = mntrngSttus;
	}
	/**
	 * @param lastUpdusrId the lastUpdusrId to set
	 */
	public void setLastUpdusrId(String lastUpdusrId) {
		this.lastUpdusrId = lastUpdusrId;
	}
	/**
	 * @param lastUpdusrPnttm the lastUpdusrPnttm to set
	 */
	public void setLastUpdusrPnttm(String lastUpdusrPnttm) {
		this.lastUpdusrPnttm = lastUpdusrPnttm;
	}
	/**
	 * @param cntcNm the cntcNm to set
	 */
	public void setCntcNm(String cntcNm) {
		this.cntcNm = cntcNm;
	}
	/**
	 * @param provdInsttNm the provdInsttNm to set
	 */
	public void setProvdInsttNm(String provdInsttNm) {
		this.provdInsttNm = provdInsttNm;
	}
	/**
	 * @param provdSysNm the provdSysNm to set
	 */
	public void setProvdSysNm(String provdSysNm) {
		this.provdSysNm = provdSysNm;
	}
	/**
	 * @param provdSvcNm the provdSvcNm to set
	 */
	public void setProvdSvcNm(String provdSvcNm) {
		this.provdSvcNm = provdSvcNm;
	}
	/**
	 * @param requstInsttNm the requstInsttNm to set
	 */
	public void setRequstInsttNm(String requstInsttNm) {
		this.requstInsttNm = requstInsttNm;
	}
	/**
	 * @param requstSysNm the requstSysNm to set
	 */
	public void setRequstSysNm(String requstSysNm) {
		this.requstSysNm = requstSysNm;
	}
	/**
	 * @param mntrngSttusNm the mntrngSttusNm to set
	 */
	public void setMntrngSttusNm(String mntrngSttusNm) {
		this.mntrngSttusNm = mntrngSttusNm;
	}
	/**
	 * @return the logId
	 */
	public String getLogId() {
		return logId;
	}
	/**
	 * @return the frstRegisterId
	 */
	public String getFrstRegisterId() {
		return frstRegisterId;
	}
	/**
	 * @return the frstRegisterPnttm
	 */
	public String getFrstRegisterPnttm() {
		return frstRegisterPnttm;
	}
	/**
	 * @return the creatDt
	 */
	public String getCreatDt() {
		return creatDt;
	}
	/**
	 * @return the logInfo
	 */
	public String getLogInfo() {
		return logInfo;
	}
	/**
	 * @param logId the logId to set
	 */
	public void setLogId(String logId) {
		this.logId = logId;
	}
	/**
	 * @param frstRegisterId the frstRegisterId to set
	 */
	public void setFrstRegisterId(String frstRegisterId) {
		this.frstRegisterId = frstRegisterId;
	}
	/**
	 * @param frstRegisterPnttm the frstRegisterPnttm to set
	 */
	public void setFrstRegisterPnttm(String frstRegisterPnttm) {
		this.frstRegisterPnttm = frstRegisterPnttm;
	}
	/**
	 * @param creatDt the creatDt to set
	 */
	public void setCreatDt(String creatDt) {
		this.creatDt = creatDt;
	}
	/**
	 * @param logInfo the logInfo to set
	 */
	public void setLogInfo(String logInfo) {
		this.logInfo = logInfo;
	}



}