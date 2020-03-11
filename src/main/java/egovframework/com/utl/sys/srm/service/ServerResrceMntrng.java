package egovframework.com.utl.sys.srm.service;

import egovframework.com.cmm.ComDefaultVO;

/**
 * 개요
 * - 서버자원모니터링에 대한 model 클래스를 정의한다.
 * 
 * 상세내용
 * - 서버자원모니터링의 ID, 서버 명, 서버 IP, 서버자원종류, 현황정보 등의 항목을 관리한다.
 * @author lee.m.j
 * @version 1.0
 * @created 06-9-2010 오전 11:24:00
 */

public class ServerResrceMntrng extends ComDefaultVO {

	private static final long serialVersionUID = 1L;
	/**
	 * 서버 ID
	 */
	private String serverId;
	/**
	 * 서버 ID
	 */
	private String serverEqpmnId;
	/**
	 * 로그ID
	 */
	private String logId;
	/**
	 * 서버 명
	 */
	private String serverNm;
	/**
	 * 서버 IP
	 */
	private String serverEqpmnIp;
	/**
	 * CPU 사용률
	 */
	private String cpuUseRt;
	/**
	 * 메모리 사용률
	 */
	private String moryUseRt;
	/**
	 * 서비스상태
	 */
	private String svcSttus;
	/**
	 * 서비스상태명
	 */
	private String svcSttusNm;	
	/**
	 * 로그정보
	 */
	private String logInfo;
	/**
	 * 관리자이메일
	 */
	private String mngrEamilAddr;
	/**
	 * 생성일시
	 */
	private String creatDt;
    /**
	 * 최초등록시점
	 */   
    private String frstRegisterPnttm;
    /**
	 * 최초등록자ID
	 */        
    private String frstRegisterId;	
	/**
	 * 최종수정시점
	 */
	private String lastUpdusrPnttm;
	/**
	 * 최종수정자ID
	 */
	private String lastUpdusrId;
	/**
	 * @return the serverId
	 */
	public String getServerId() {
		return serverId;
	}
	/**
	 * @param serverId the serverId to set
	 */
	public void setServerId(String serverId) {
		this.serverId = serverId;
	}
	/**
	 * @return the serverEqpmnId
	 */
	public String getServerEqpmnId() {
		return serverEqpmnId;
	}
	/**
	 * @param serverEqpmnId the serverEqpmnId to set
	 */
	public void setServerEqpmnId(String serverEqpmnId) {
		this.serverEqpmnId = serverEqpmnId;
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
	 * @return the serverNm
	 */
	public String getServerNm() {
		return serverNm;
	}
	/**
	 * @param serverNm the serverNm to set
	 */
	public void setServerNm(String serverNm) {
		this.serverNm = serverNm;
	}
	/**
	 * @return the serverEqpmnIp
	 */
	public String getServerEqpmnIp() {
		return serverEqpmnIp;
	}
	/**
	 * @param serverEqpmnIp the serverEqpmnIp to set
	 */
	public void setServerEqpmnIp(String serverEqpmnIp) {
		this.serverEqpmnIp = serverEqpmnIp;
	}
	/**
	 * @return the cpuUseRt
	 */
	public String getCpuUseRt() {
		return cpuUseRt;
	}
	/**
	 * @param cpuUseRt the cpuUseRt to set
	 */
	public void setCpuUseRt(String cpuUseRt) {
		this.cpuUseRt = cpuUseRt;
	}
	/**
	 * @return the moryUseRt
	 */
	public String getMoryUseRt() {
		return moryUseRt;
	}
	/**
	 * @param moryUseRt the moryUseRt to set
	 */
	public void setMoryUseRt(String moryUseRt) {
		this.moryUseRt = moryUseRt;
	}
	/**
	 * @return the svcSttus
	 */
	public String getSvcSttus() {
		return svcSttus;
	}
	/**
	 * @param svcSttus the svcSttus to set
	 */
	public void setSvcSttus(String svcSttus) {
		this.svcSttus = svcSttus;
	}
	/**
	 * @return the svcSttusNm
	 */
	public String getSvcSttusNm() {
		return svcSttusNm;
	}
	/**
	 * @param svcSttusNm the svcSttusNm to set
	 */
	public void setSvcSttusNm(String svcSttusNm) {
		this.svcSttusNm = svcSttusNm;
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
	 * @return the mngrEamilAddr
	 */
	public String getMngrEamilAddr() {
		return mngrEamilAddr;
	}
	/**
	 * @param mngrEamilAddr the mngrEamilAddr to set
	 */
	public void setMngrEamilAddr(String mngrEamilAddr) {
		this.mngrEamilAddr = mngrEamilAddr;
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
}