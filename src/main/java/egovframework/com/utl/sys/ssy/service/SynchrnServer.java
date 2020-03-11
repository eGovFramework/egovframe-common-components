package egovframework.com.utl.sys.ssy.service;

import egovframework.com.cmm.ComDefaultVO;

/**
 * 개요
 * - 동기화대상 서버에 대한 model 클래스를 정의한다.
 * 
 * 상세내용
 * - 동기화대상 서버의 ID, 서버 명, 서버 IP, FTP ID, FTP 비밀번호, 동기화위치 등의 항목을 관리한다.
 * @author lee.m.j
 * @version 1.0
 * @created 28-6-2010 오전 10:44:57
 */
public class SynchrnServer extends ComDefaultVO {

	private static final long serialVersionUID = 1L;
	/**
	 * 서버 ID
	 */
	private String serverId;
	/**
	 * 서버 명
	 */
	private String serverNm;
	/**
	 * 서버 IP
	 */
	private String serverIp;
	/**
	 * 서버 Port
	 */
	private String serverPort;	
	/**
	 * FTP ID
	 */
	private String ftpId;
	/**
	 * FTP 비밀번호
	 */
	private String ftpPassword;
	/**
	 * 동기화 위치
	 */
	private String synchrnLc;
	/**
	 * 반영 여부
	 */
	private String reflctAt;
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
	 * @return the serverIp
	 */
	public String getServerIp() {
		return serverIp;
	}
	/**
	 * @param serverIp the serverIp to set
	 */
	public void setServerIp(String serverIp) {
		this.serverIp = serverIp;
	}
	/**
	 * @return the serverPort
	 */
	public String getServerPort() {
		return serverPort;
	}
	/**
	 * @param serverPort the serverPort to set
	 */
	public void setServerPort(String serverPort) {
		this.serverPort = serverPort;
	}
	/**
	 * @return the ftpId
	 */
	public String getFtpId() {
		return ftpId;
	}
	/**
	 * @param ftpId the ftpId to set
	 */
	public void setFtpId(String ftpId) {
		this.ftpId = ftpId;
	}
	/**
	 * @return the ftpPassword
	 */
	public String getFtpPassword() {
		return ftpPassword;
	}
	/**
	 * @param ftpPassword the ftpPassword to set
	 */
	public void setFtpPassword(String ftpPassword) {
		this.ftpPassword = ftpPassword;
	}
	/**
	 * @return the synchrnLc
	 */
	public String getSynchrnLc() {
		return synchrnLc;
	}
	/**
	 * @param synchrnLc the synchrnLc to set
	 */
	public void setSynchrnLc(String synchrnLc) {
		this.synchrnLc = synchrnLc;
	}
	/**
	 * @return the reflctAt
	 */
	public String getReflctAt() {
		return reflctAt;
	}
	/**
	 * @param reflctAt the reflctAt to set
	 */
	public void setReflctAt(String reflctAt) {
		this.reflctAt = reflctAt;
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