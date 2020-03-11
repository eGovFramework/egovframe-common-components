package egovframework.com.utl.sys.pxy.service;

import egovframework.com.cmm.ComDefaultVO;

/**
 * 개요
 * - 프록시서비스정보에 대한 model 클래스를 정의한다.
 * 
 * 상세내용
 * - 프록시서비스정보의 ID, 프록시 명, 프록시 IP, 프록시 포트, 서비스 명, 서비스 설명, 서비스 IP, 서비스 포트, 서비스 상태 등의
 * 항목을 관리한다.
 * @author lee.m.j
 * @version 1.0
 * @created 28-6-2010 오전 10:44:50
 */
public class ProxySvc extends ComDefaultVO {

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
	 * 프록시 IP
	 */
	private String proxyIp;
	/**
	 * 프록시 포트
	 */
	private String proxyPort;
	/**
	 * 서비스 명
	 */
	private String trgetSvcNm;
	/**
	 * 서비스 설명
	 */
	private String svcDc;
	/**
	 * 서비스 IP
	 */
	private String svcIp;
	/**
	 * 서비스 포트
	 */
	private String svcPort;
	/**
	 * 서비스 상태
	 */
	private String svcSttus;
	/**
	 * 서비스 상태
	 */
	private String svcSttusNm;
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
	 * @return the proxyIp
	 */
	public String getProxyIp() {
		return proxyIp;
	}
	/**
	 * @param proxyIp the proxyIp to set
	 */
	public void setProxyIp(String proxyIp) {
		this.proxyIp = proxyIp;
	}
	/**
	 * @return the proxyPort
	 */
	public String getProxyPort() {
		return proxyPort;
	}
	/**
	 * @param proxyPort the proxyPort to set
	 */
	public void setProxyPort(String proxyPort) {
		this.proxyPort = proxyPort;
	}
	/**
	 * @return the trgetSvcNm
	 */
	public String getTrgetSvcNm() {
		return trgetSvcNm;
	}
	/**
	 * @param trgetSvcNm the trgetSvcNm to set
	 */
	public void setTrgetSvcNm(String trgetSvcNm) {
		this.trgetSvcNm = trgetSvcNm;
	}
	/**
	 * @return the svcDc
	 */
	public String getSvcDc() {
		return svcDc;
	}
	/**
	 * @param svcDc the svcDc to set
	 */
	public void setSvcDc(String svcDc) {
		this.svcDc = svcDc;
	}
	/**
	 * @return the svcIp
	 */
	public String getSvcIp() {
		return svcIp;
	}
	/**
	 * @param svcIp the svcIp to set
	 */
	public void setSvcIp(String svcIp) {
		this.svcIp = svcIp;
	}
	/**
	 * @return the svcPort
	 */
	public String getSvcPort() {
		return svcPort;
	}
	/**
	 * @param svcPort the svcPort to set
	 */
	public void setSvcPort(String svcPort) {
		this.svcPort = svcPort;
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