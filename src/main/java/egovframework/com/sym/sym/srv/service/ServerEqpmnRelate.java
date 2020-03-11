package egovframework.com.sym.sym.srv.service;

import egovframework.com.cmm.ComDefaultVO;

/**
 * 개요
 * - 서버장비관계에 대한 model 클래스를 정의한다.
 * 
 * 상세내용
 * - 서버장비관계의 서버장비ID, 서버 ID 등의 항목을 관리한다.
 * @author 이문준
 * @version 1.0
 * @created 28-6-2010 오전 10:44:55
 */
public class ServerEqpmnRelate extends ComDefaultVO {

	private static final long serialVersionUID = 1L;
	/**
	 * 서버ID
	 */
	private String serverId;
	/**
	 * 서버장비ID
	 */
	private String serverEqpmnId;
	/**
	 * 서버장비명
	 */
	private String serverEqpmnNm;
	/**
	 * 서버장비IP
	 */
	private String serverEqpmnIp;
	/**
	 * 서버장비관리자
	 */
	private String serverEqpmnMngrNm;
	/**
	 * 등록여부
	 */
	private String regYn;
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
	 * @return the regYn
	 */
	public String getRegYn() {
		return regYn;
	}
	/**
	 * @param regYn the regYn to set
	 */
	public void setRegYn(String regYn) {
		this.regYn = regYn;
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