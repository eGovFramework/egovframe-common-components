package egovframework.com.sym.sym.srv.service;

import egovframework.com.cmm.ComDefaultVO;

/**
 * 개요
 * - 서버정보에 대한 model 클래스를 정의한다.
 * 
 * 상세내용
 * - 서버정보의 서버ID, 서버명, 서버종류 등의 항목을 관리한다.
 * @author 이문준
 * @version 1.0
 * @created 28-6-2010 오전 10:44:54
 */
public class Server extends ComDefaultVO {

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
	 * 서버 종류
	 */
	private String serverKnd;
	/**
	 * 서버 종류명
	 */
	private String serverKndNm;
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
	 * @return the serverKnd
	 */
	public String getServerKnd() {
		return serverKnd;
	}
	/**
	 * @param serverKnd the serverKnd to set
	 */
	public void setServerKnd(String serverKnd) {
		this.serverKnd = serverKnd;
	}
	/**
	 * @return the serverKndNm
	 */
	public String getServerKndNm() {
		return serverKndNm;
	}
	/**
	 * @param serverKndNm the serverKndNm to set
	 */
	public void setServerKndNm(String serverKndNm) {
		this.serverKndNm = serverKndNm;
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