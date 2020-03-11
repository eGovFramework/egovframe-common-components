package egovframework.com.sym.sym.srv.service;

import egovframework.com.cmm.ComDefaultVO;

/**
 * 개요
 * - 서버장비에 대한 model 클래스를 정의한다.
 * 
 * 상세내용
 * - 서버장비의 ID, 서버 장비 명, 서버 장비 IP, 서버 장비 관리자 명, 운영체제 정보, CPU 정보, 메모리 정보 등의 항목을 관리한다.
 * 
 * @author 이문준
 * @version 1.0
 * @created 28-6-2010 오전 10:44:54
 */
public class ServerEqpmn extends ComDefaultVO {

	private static final long serialVersionUID = 1L;
	/**
	 * 서버 장비 ID
	 */
	private String serverEqpmnId;
	/**
	 * 서버 장비 명
	 */
	private String serverEqpmnNm;
	/**
	 * 서버 장비 IP
	 */
	private String serverEqpmnIp;
	/**
	 * 서버 장비 관리자 명
	 */
	private String serverEqpmnMngrNm;
	/**
	 * 관리자 이메일 주소
	 */
	private String mngrEmailAddr;
	/**
	 * 운영체제 정보
	 */
	private String opersysmInfo;
	/**
	 * CPU 정보
	 */
	private String cpuInfo;
	/**
	 * 메모리 정보
	 */
	private String moryInfo;
	/**
	 * 하드디스크 정보
	 */
	private String hdDisk;
	/**
	 * 기타 정보
	 */
	private String etcInfo;
    /**
	 * 등록일자
	 */    
    private String regstYmd;    
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
	 * @return the serverEqpmnNm
	 */
	public String getServerEqpmnNm() {
		return serverEqpmnNm;
	}
	/**
	 * @param serverEqpmnNm the serverEqpmnNm to set
	 */
	public void setServerEqpmnNm(String serverEqpmnNm) {
		this.serverEqpmnNm = serverEqpmnNm;
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
	 * @return the serverEqpmnMngrNm
	 */
	public String getServerEqpmnMngrNm() {
		return serverEqpmnMngrNm;
	}
	/**
	 * @param serverEqpmnMngrNm the serverEqpmnMngrNm to set
	 */
	public void setServerEqpmnMngrNm(String serverEqpmnMngrNm) {
		this.serverEqpmnMngrNm = serverEqpmnMngrNm;
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
	 * @return the opersysmInfo
	 */
	public String getOpersysmInfo() {
		return opersysmInfo;
	}
	/**
	 * @param opersysmInfo the opersysmInfo to set
	 */
	public void setOpersysmInfo(String opersysmInfo) {
		this.opersysmInfo = opersysmInfo;
	}
	/**
	 * @return the cpuInfo
	 */
	public String getCpuInfo() {
		return cpuInfo;
	}
	/**
	 * @param cpuInfo the cpuInfo to set
	 */
	public void setCpuInfo(String cpuInfo) {
		this.cpuInfo = cpuInfo;
	}
	/**
	 * @return the moryInfo
	 */
	public String getMoryInfo() {
		return moryInfo;
	}
	/**
	 * @param moryInfo the moryInfo to set
	 */
	public void setMoryInfo(String moryInfo) {
		this.moryInfo = moryInfo;
	}
	/**
	 * @return the hdDisk
	 */
	public String getHdDisk() {
		return hdDisk;
	}
	/**
	 * @param hdDisk the hdDisk to set
	 */
	public void setHdDisk(String hdDisk) {
		this.hdDisk = hdDisk;
	}
	/**
	 * @return the etcInfo
	 */
	public String getEtcInfo() {
		return etcInfo;
	}
	/**
	 * @param etcInfo the etcInfo to set
	 */
	public void setEtcInfo(String etcInfo) {
		this.etcInfo = etcInfo;
	}
	/**
	 * @return the regstYmd
	 */
	public String getRegstYmd() {
		return regstYmd;
	}
	/**
	 * @param regstYmd the regstYmd to set
	 */
	public void setRegstYmd(String regstYmd) {
		this.regstYmd = regstYmd;
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