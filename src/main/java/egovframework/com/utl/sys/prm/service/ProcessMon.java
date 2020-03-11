package egovframework.com.utl.sys.prm.service;

import java.io.Serializable;

/**
 * 개요
 * - PROCESS모니터링에 대한 model 클래스를 정의한다.
 * 
 * 상세내용
 * - 프로세스명, 프로세스상태, 관리자명, 관리자이메일주소, 최종수정자ID, 최종수정시점 항목을 관리한다.
 * @author 박종선
 * @version 1.0
 * @created 08-9-2010 오후 3:54:46
 */

@SuppressWarnings("serial")
public class ProcessMon implements Serializable  {

	/**
	 * 프로세스 명
	 */
	private String processNm;
	/**
	 * 프로세스아이디
	 */
	private String processId;
	/**
	 * 로그ID
	 */
	private String logId;
	/**
	 * 로그정보
	 */
	private String logInfo;	
	/**
	 * 프로세스 상태
	 */
	private String procsSttus;
	/**
	 * 생성시간
	 */
	private String creatDt;
	/**
	 * 관리자 명
	 */
	private String mngrNm;
	/**
	 * 관리자 이메일 주소
	 */
	private String mngrEmailAddr;
	/**
	 * 최초등록자ID
	 */
	private String frstRegisterId;
	/**
	 * 최초등록시점
	 */
	private String frstRegisterPnttm;
	/**
	 * 최종수정자ID
	 */
	private String lastUpdusrId;
	/**
	 * 최종수정시점
	 */
	private String lastUpdusrPnttm;
	/**
	 * @return the processNm
	 */
	public String getProcessNm() {
		return processNm;
	}
	/**
	 * @param processNm the processNm to set
	 */
	public void setProcessNm(String processNm) {
		this.processNm = processNm;
	}
	/**
	 * @return the processId
	 */
	public String getProcessId() {
		return processId;
	}
	/**
	 * @param processId the processId to set
	 */
	public void setProcessId(String processId) {
		this.processId = processId;
	}
	/**
	 * @return the logId
	 */
	public String getLogId() {
		return logId;
	}
	/**
	 * @param logId the logId to set
	 */
	public void setLogId(String logId) {
		this.logId = logId;
	}
	/**
	 * @return the logInfo
	 */
	public String getLogInfo() {
		return logInfo;
	}
	/**
	 * @param logInfo the logInfo to set
	 */
	public void setLogInfo(String logInfo) {
		this.logInfo = logInfo;
	}
	/**
	 * @return the procsSttus
	 */
	public String getProcsSttus() {
		return procsSttus;
	}
	/**
	 * @param procsSttus the procsSttus to set
	 */
	public void setProcsSttus(String procsSttus) {
		this.procsSttus = procsSttus;
	}
	/**
	 * @return the creatDt
	 */
	public String getCreatDt() {
		return creatDt;
	}
	/**
	 * @param creatDt the creatDt to set
	 */
	public void setCreatDt(String creatDt) {
		this.creatDt = creatDt;
	}
	/**
	 * @return the mngrNm
	 */
	public String getMngrNm() {
		return mngrNm;
	}
	/**
	 * @param mngrNm the mngrNm to set
	 */
	public void setMngrNm(String mngrNm) {
		this.mngrNm = mngrNm;
	}
	/**
	 * @return the mngrEmailAddr
	 */
	public String getMngrEmailAddr() {
		return mngrEmailAddr;
	}
	/**
	 * @param mngrEmailAddr the mngrEmailAddr to set
	 */
	public void setMngrEmailAddr(String mngrEmailAddr) {
		this.mngrEmailAddr = mngrEmailAddr;
	}
	/**
	 * @return the frstRegisterId
	 */
	public String getFrstRegisterId() {
		return frstRegisterId;
	}
	/**
	 * @param frstRegisterId the frstRegisterId to set
	 */
	public void setFrstRegisterId(String frstRegisterId) {
		this.frstRegisterId = frstRegisterId;
	}
	/**
	 * @return the frstRegisterPnttm
	 */
	public String getFrstRegisterPnttm() {
		return frstRegisterPnttm;
	}
	/**
	 * @param frstRegisterPnttm the frstRegisterPnttm to set
	 */
	public void setFrstRegisterPnttm(String frstRegisterPnttm) {
		this.frstRegisterPnttm = frstRegisterPnttm;
	}
	/**
	 * @return the lastUpdusrId
	 */
	public String getLastUpdusrId() {
		return lastUpdusrId;
	}
	/**
	 * @param lastUpdusrId the lastUpdusrId to set
	 */
	public void setLastUpdusrId(String lastUpdusrId) {
		this.lastUpdusrId = lastUpdusrId;
	}
	/**
	 * @return the lastUpdusrPnttm
	 */
	public String getLastUpdusrPnttm() {
		return lastUpdusrPnttm;
	}
	/**
	 * @param lastUpdusrPnttm the lastUpdusrPnttm to set
	 */
	public void setLastUpdusrPnttm(String lastUpdusrPnttm) {
		this.lastUpdusrPnttm = lastUpdusrPnttm;
	}
}