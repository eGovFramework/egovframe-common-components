/**
 * 개요
 * - 네트워크정보에 대한 model 클래스를 정의한다.
 * 
 * 상세내용
 * - 장애관리의 네트워크ID, 네트워크IP, 게이트웨이, SUBNET, 도메인이름서버, 관리항목, 사용자명, 사용여부, 
 *   최종수정자ID, 최종수정시점 항목을 관리한다.
 * @author lee.m.j
 * @version 1.0
 * @created 01-7-2010 오전 10:44:57
 */

package egovframework.com.sym.sym.nwk.service;

import egovframework.com.cmm.ComDefaultVO;

public class Ntwrk extends ComDefaultVO {

    private static final long serialVersionUID = 1L;
	/**
	 * 네트워크ID
	 */
    private String ntwrkId;
	/**
	 * 네트워크IP
	 */
    private String ntwrkIp;
    /**
	 * 게이트웨이
	 */    
    private String gtwy;
    /**
	 * SUBNET
	 */    
    private String subnet;
    /**
	 * 도메인이름서버
	 */    
    private String domnServer;
    /**
	 * 관리항목
	 */    
    private String manageIem;
    /**
	 * 사용자명
	 */        
    private String userNm;
    /**
	 * 사용여부
	 */    
    private String useAt;
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
	 * @return the ntwrkId
	 */
	public String getNtwrkId() {
		return ntwrkId;
	}
	/**
	 * @param ntwrkId the ntwrkId to set
	 */
	public void setNtwrkId(String ntwrkId) {
		this.ntwrkId = ntwrkId;
	}
	/**
	 * @return the ntwrkIp
	 */
	public String getNtwrkIp() {
		return ntwrkIp;
	}
	/**
	 * @param ntwrkIp the ntwrkIp to set
	 */
	public void setNtwrkIp(String ntwrkIp) {
		this.ntwrkIp = ntwrkIp;
	}
	/**
	 * @return the gtwy
	 */
	public String getGtwy() {
		return gtwy;
	}
	/**
	 * @param gtwy the gtwy to set
	 */
	public void setGtwy(String gtwy) {
		this.gtwy = gtwy;
	}
	/**
	 * @return the subnet
	 */
	public String getSubnet() {
		return subnet;
	}
	/**
	 * @param subnet the subnet to set
	 */
	public void setSubnet(String subnet) {
		this.subnet = subnet;
	}
	/**
	 * @return the domnServer
	 */
	public String getDomnServer() {
		return domnServer;
	}
	/**
	 * @param domnServer the domnServer to set
	 */
	public void setDomnServer(String domnServer) {
		this.domnServer = domnServer;
	}
	/**
	 * @return the manageIem
	 */
	public String getManageIem() {
		return manageIem;
	}
	/**
	 * @param manageIem the manageIem to set
	 */
	public void setManageIem(String manageIem) {
		this.manageIem = manageIem;
	}
	/**
	 * @return the userNm
	 */
	public String getUserNm() {
		return userNm;
	}
	/**
	 * @param userNm the userNm to set
	 */
	public void setUserNm(String userNm) {
		this.userNm = userNm;
	}
	/**
	 * @return the useAt
	 */
	public String getUseAt() {
		return useAt;
	}
	/**
	 * @param useAt the useAt to set
	 */
	public void setUseAt(String useAt) {
		this.useAt = useAt;
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
