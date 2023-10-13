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
	/**
	 * 시스템 IP를 반환합니다.
	 */
	public String getSysIp() {
		return sysIp;
	}

	/**
	 * 시스템 IP를 설정합니다.
	 */
	public void setSysIp(String sysIp) {
		this.sysIp = sysIp;
	}

	/**
	 * 시스템 IP1을 반환합니다.
	 */
	public String getSysIp1() {
		return sysIp1;
	}

	/**
	 * 시스템 IP1을 설정합니다.
	 */
	public void setSysIp1(String sysIp1) {
		this.sysIp1 = sysIp1;
	}

	/**
	 * 시스템 IP2를 반환합니다.
	 */
	public String getSysIp2() {
		return sysIp2;
	}

	/**
	 * 시스템 IP2를 설정합니다.
	 */
	public void setSysIp2(String sysIp2) {
		this.sysIp2 = sysIp2;
	}

	/**
	 * 시스템 IP3을 반환합니다.
	 */
	public String getSysIp3() {
		return sysIp3;
	}

	/**
	 * 시스템 IP3을 설정합니다.
	 */
	public void setSysIp3(String sysIp3) {
		this.sysIp3 = sysIp3;
	}

	/**
	 * 시스템 IP4를 반환합니다.
	 */
	public String getSysIp4() {
		return sysIp4;
	}

	/**
	 * 시스템 IP4를 설정합니다.
	 */
	public void setSysIp4(String sysIp4) {
		this.sysIp4 = sysIp4;
	}

	/**
	 * 이전 시스템 IP를 반환합니다.
	 */
	public String getOldSysIp() {
		return oldSysIp;
	}

	/**
	 * 이전 시스템 IP를 설정합니다.
	 */
	public void setOldSysIp(String oldSysIp) {
		this.oldSysIp = oldSysIp;
	}

	/**
	 * 시스템 포트를 반환합니다.
	 */
	public String getSysPort() {
		return sysPort;
	}

	/**
	 * 시스템 포트를 설정합니다.
	 */
	public void setSysPort(String sysPort) {
		this.sysPort = sysPort;
	}

	/**
	 * 이전 시스템 포트를 반환합니다.
	 */
	public String getOldSysPort() {
		return oldSysPort;
	}

	/**
	 * 이전 시스템 포트를 설정합니다.
	 */
	public void setOldSysPort(String oldSysPort) {
		this.oldSysPort = oldSysPort;
	}

	/**
	 * 시스템 이름을 반환합니다.
	 */
	public String getSysNm() {
		return sysNm;
	}

	/**
	 * 시스템 이름을 설정합니다.
	 */
	public void setSysNm(String sysNm) {
		this.sysNm = sysNm;
	}

	/**
	 * 관리자 이름을 반환합니다.
	 */
	public String getMngrNm() {
		return mngrNm;
	}

	/**
	 * 관리자 이름을 설정합니다.
	 */
	public void setMngrNm(String mngrNm) {
		this.mngrNm = mngrNm;
	}

	/**
	 * 관리자 이메일 주소를 반환합니다.
	 */
	public String getMngrEmailAddr() {
		return mngrEmailAddr;
	}

	/**
	 * 관리자 이메일 주소를 설정합니다.
	 */
	public void setMngrEmailAddr(String mngrEmailAddr) {
		this.mngrEmailAddr = mngrEmailAddr;
	}

	/**
	 * 모니터링 상태를 반환합니다.
	 */
	public String getMntrngSttus() {
		return mntrngSttus;
	}

	/**
	 * 모니터링 상태를 설정합니다.
	 */
	public void setMntrngSttus(String mntrngSttus) {
		this.mntrngSttus = mntrngSttus;
	}

	/**
	 * 생성 날짜를 반환합니다.
	 */
	public String getCreatDt() {
		return creatDt;
	}

	/**
	 * 생성 날짜를 설정합니다.
	 */
	public void setCreatDt(String creatDt) {
		this.creatDt = creatDt;
	}

	/**
	 * 로그 정보를 반환합니다.
	 */
	public String getLogInfo() {
		return logInfo;
	}

	/**
	 * 로그 정보를 설정합니다.
	 */
	public void setLogInfo(String logInfo) {
		this.logInfo = logInfo;
	}

	/**
	 * 최초 등록자 ID를 반환합니다.
	 */
	public String getFrstRegisterId() {
		return frstRegisterId;
	}

	/**
	 * 최초 등록자 ID를 설정합니다.
	 */
	public void setFrstRegisterId(String frstRegisterId) {
		this.frstRegisterId = frstRegisterId;
	}

	/**
	 * 최초 등록 시간을 반환합니다.
	 */
	public String getFrstRegisterPnttm() {
		return frstRegisterPnttm;
	}

	/**
	 * 최초 등록 시간을 설정합니다.
	 */
	public void setFrstRegisterPnttm(String frstRegisterPnttm) {
		this.frstRegisterPnttm = frstRegisterPnttm;
	}

	/**
	 * 마지막 수정자 ID를 반환합니다.
	 */
	public String getLastUpdusrId() {
		return lastUpdusrId;
	}

	/**
	 * 마지막 수정자 ID를 설정합니다.
	 */
	public void setLastUpdusrId(String lastUpdusrId) {
		this.lastUpdusrId = lastUpdusrId;
	}

	/**
	 * 마지막 수정 시간을 반환합니다.
	 */
	public String getLastUpdusrPnttm() {
		return lastUpdusrPnttm;
	}

	/**
	 * 마지막 수정 시간을 설정합니다.
	 */
	public void setLastUpdusrPnttm(String lastUpdusrPnttm) {
		this.lastUpdusrPnttm = lastUpdusrPnttm;
	}

}