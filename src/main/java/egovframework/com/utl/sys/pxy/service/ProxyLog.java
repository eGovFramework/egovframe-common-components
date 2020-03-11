package egovframework.com.utl.sys.pxy.service;

import egovframework.com.cmm.ComDefaultVO;

/**
 * 개요
 * - 프록시로그정보에 대한 model 클래스를 정의한다.
 * 
 * 상세내용
 * - 프록시로그정보의 프록시 ID, 로그 ID, 클라이언트 IP, 클라이언트 포트, 접속 시간 등의 항목을 관리한다.
 * @author lee.m.j
 * @version 1.0
 * @created 28-6-2010 오전 10:44:49
 */
public class ProxyLog extends ComDefaultVO {
	
	private static final long serialVersionUID = 1L;
	/**
	 * 프록시 ID
	 */
	private String proxyId;
	/**
	 * 프록시 명
	 */
	private String proxyNm;	
	/**
	 * 로그 ID
	 */
	private String logId;
	/**
	 * 클라이언트 IP
	 */
	private String clntIp;
	/**
	 * 클라이언트 포트
	 */
	private String clntPort;
	/**
	 * 접속시간
	 */
	private String conectTime;
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
	 * @return the proxyId
	 */
	public String getProxyId() {
		return proxyId;
	}
	/**
	 * @param proxyId the proxyId to set
	 */
	public void setProxyId(String proxyId) {
		this.proxyId = proxyId;
	}
	/**
	 * @return the proxyNm
	 */
	public String getProxyNm() {
		return proxyNm;
	}
	/**
	 * @param proxyNm the proxyNm to set
	 */
	public void setProxyNm(String proxyNm) {
		this.proxyNm = proxyNm;
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
	 * @return the clntIp
	 */
	public String getClntIp() {
		return clntIp;
	}
	/**
	 * @param clntIp the clntIp to set
	 */
	public void setClntIp(String clntIp) {
		this.clntIp = clntIp;
	}
	/**
	 * @return the clntPort
	 */
	public String getClntPort() {
		return clntPort;
	}
	/**
	 * @param clntPort the clntPort to set
	 */
	public void setClntPort(String clntPort) {
		this.clntPort = clntPort;
	}
	/**
	 * @return the conectTime
	 */
	public String getConectTime() {
		return conectTime;
	}
	/**
	 * @param conectTime the conectTime to set
	 */
	public void setConectTime(String conectTime) {
		this.conectTime = conectTime;
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