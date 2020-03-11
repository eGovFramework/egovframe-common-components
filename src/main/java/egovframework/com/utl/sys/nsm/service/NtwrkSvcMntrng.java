package egovframework.com.utl.sys.nsm.service;

import java.io.Serializable;

/**
 * 개요
 * - 네트워크서비스 모니터링대상에 대한 model 클래스를 정의한다.
 * 
 * 상세내용
 * - 시스템IP, 시스템IP 1, 시스템IP 2, 시스템IP 3, 시스템IP 4, 구 시스템IP, 시스템포트, 구 시스템포트, 시스템명, 관리자명, 관리자이메일주소, 모니터링상태, 생성일시, 로그정보 항목을 관리한다.
 * @author 장철호
 * @version 1.0
 * @created 28-6-2010 오전 11:33:43
 */
@SuppressWarnings("serial")
public class NtwrkSvcMntrng implements Serializable {

	/**
	 * 시스템IP
	 */
	private String sysIp;
	/**
	 * 시스템IP 1
	 */
	private String sysIp1;
	/**
	 * 시스템IP 2
	 */
	private String sysIp2;
	/**
	 * 시스템IP 3
	 */
	private String sysIp3;
	/**
	 * 시스템IP 4
	 */
	private String sysIp4;
	/**
	 * 구시스템IP
	 */
	private String oldSysIp;
	/**
	 * 시스템포트
	 */
	private String sysPort;
	/**
	 * 구시스템포트
	 */
	private String oldSysPort;
	/**
	 * 시스템명
	 */
	private String sysNm;
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
	 * 생성일시
	 */
	private String creatDt;
	/**
	 * 로그정보
	 */
	private String logInfo;
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
	public String getSysIp1() {
		return sysIp1;
	}
	public void setSysIp1(String sysIp1) {
		this.sysIp1 = sysIp1;
	}
	public String getSysIp2() {
		return sysIp2;
	}
	public void setSysIp2(String sysIp2) {
		this.sysIp2 = sysIp2;
	}
	public String getSysIp3() {
		return sysIp3;
	}
	public void setSysIp3(String sysIp3) {
		this.sysIp3 = sysIp3;
	}
	public String getSysIp4() {
		return sysIp4;
	}
	public void setSysIp4(String sysIp4) {
		this.sysIp4 = sysIp4;
	}
	public String getOldSysIp() {
		return oldSysIp;
	}
	public void setOldSysIp(String oldSysIp) {
		this.oldSysIp = oldSysIp;
	}
	public String getSysPort() {
		return sysPort;
	}
	public void setSysPort(String sysPort) {
		this.sysPort = sysPort;
	}
	public String getOldSysPort() {
		return oldSysPort;
	}
	public void setOldSysPort(String oldSysPort) {
		this.oldSysPort = oldSysPort;
	}
	public String getSysNm() {
		return sysNm;
	}
	public void setSysNm(String sysNm) {
		this.sysNm = sysNm;
	}
	public String getMngrNm() {
		return mngrNm;
	}
	public void setMngrNm(String mngrNm) {
		this.mngrNm = mngrNm;
	}
	public String getMngrEmailAddr() {
		return mngrEmailAddr;
	}
	public void setMngrEmailAddr(String mngrEmailAddr) {
		this.mngrEmailAddr = mngrEmailAddr;
	}
	public String getMntrngSttus() {
		return mntrngSttus;
	}
	public void setMntrngSttus(String mntrngSttus) {
		this.mntrngSttus = mntrngSttus;
	}
	public String getCreatDt() {
		return creatDt;
	}
	public void setCreatDt(String creatDt) {
		this.creatDt = creatDt;
	}
	public String getLogInfo() {
		return logInfo;
	}
	public void setLogInfo(String logInfo) {
		this.logInfo = logInfo;
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