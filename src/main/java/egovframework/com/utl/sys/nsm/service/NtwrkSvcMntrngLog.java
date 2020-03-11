package egovframework.com.utl.sys.nsm.service;

import java.io.Serializable;

/**
 * 개요
 * - 네트워크서비스 모니터링 로그에 대한 model 클래스를 정의한다.
 * 
 * 상세내용
 * - 시스템IP, 시스템포트, 로그ID, 시스템명, 서비스상태, 로그정보, 생성일시 항목을 관리한다.
 * @author 장철호
 * @version 1.0
 * @created 28-6-2010 오전 11:33:43
 */
@SuppressWarnings("serial")
public class NtwrkSvcMntrngLog implements Serializable {

	/**
	 * 시스템IP
	 */
	private String sysIp;
	/**
	 * 시스템포트
	 */
	private String sysPort;
	/**
	 * 로그ID
	 */
	private String logId;
	/**
	 * 시스템명
	 */
	private String sysNm;
	/**
	 * 모니터링상태
	 */
	private String mntrngSttus;
	/**
	 * 로그정보
	 */
	private String logInfo;
	/**
	 * 생성일시
	 */
	private String creatDt;
	/**
	 * 최초등록자ID
	 */
	private String frstRegisterId = "";
	/**
	 * 최초등록시점
	 */
	private String frstRegisterPnttm = "";
	/**
	 * 최종수정자ID
	 */
	private String lastUpdusrId = "";
	/**
	 * 최종수정시점
	 */
	private String lastUpdusrPnttm = "";
	
	public String getSysIp() {
		return sysIp;
	}
	public void setSysIp(String sysIp) {
		this.sysIp = sysIp;
	}
	public String getSysPort() {
		return sysPort;
	}
	public void setSysPort(String sysPort) {
		this.sysPort = sysPort;
	}
	public String getLogId() {
		return logId;
	}
	public void setLogId(String logId) {
		this.logId = logId;
	}
	public String getSysNm() {
		return sysNm;
	}
	public void setSysNm(String sysNm) {
		this.sysNm = sysNm;
	}
	public String getMntrngSttus() {
		return mntrngSttus;
	}
	public void setMntrngSttus(String mntrngSttus) {
		this.mntrngSttus = mntrngSttus;
	}
	public String getLogInfo() {
		return logInfo;
	}
	public void setLogInfo(String logInfo) {
		this.logInfo = logInfo;
	}
	public String getCreatDt() {
		return creatDt;
	}
	public void setCreatDt(String creatDt) {
		this.creatDt = creatDt;
	}
	public String getFrstRegisterId() {
		return frstRegisterId;
	}
	public void setFrstRegisterId(String frstRegisterId) {
		this.frstRegisterId = frstRegisterId;
	}
	public String getFrstRegisterPnttm() {
		return frstRegisterPnttm;
	}
	public void setFrstRegisterPnttm(String frstRegisterPnttm) {
		this.frstRegisterPnttm = frstRegisterPnttm;
	}
	public String getLastUpdusrId() {
		return lastUpdusrId;
	}
	public void setLastUpdusrId(String lastUpdusrId) {
		this.lastUpdusrId = lastUpdusrId;
	}
	public String getLastUpdusrPnttm() {
		return lastUpdusrPnttm;
	}
	public void setLastUpdusrPnttm(String lastUpdusrPnttm) {
		this.lastUpdusrPnttm = lastUpdusrPnttm;
	}
	
	
}