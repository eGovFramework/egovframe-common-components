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
	/**
	 * 현재 시스템 IP를 반환합니다.
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
	 * 현재 시스템 포트를 반환합니다.
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
	 * 로그 ID를 반환합니다.
	 */
	public String getLogId() {
		return logId;
	}

	/**
	 * 로그 ID를 설정합니다.
	 */
	public void setLogId(String logId) {
		this.logId = logId;
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