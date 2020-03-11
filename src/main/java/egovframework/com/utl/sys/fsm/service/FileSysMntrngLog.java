package egovframework.com.utl.sys.fsm.service;

import java.io.Serializable;

/**
 * 개요
 * - 파일시스템 모니터링 로그에 대한 model 클래스를 정의한다.
 * 
 * 상세내용
 * - 로그ID, 파일시스템ID, 파일시스템명, 파일시스템관리명, 파일시스템크기, 파일시스템임계치, 파일시스템임계치율, 파일시스템사용량, 파일시스템사용률, 서비스상태, 로그정보, 생성일시 항목을 관리한다.
 * @author 장철호
 * @version 1.0
 * @created 28-6-2010 오전 11:33:26
 */
@SuppressWarnings("serial")
public class FileSysMntrngLog implements Serializable  {
	/**
	 * 로그ID
	 */
	private String logId;
	/**
	 * 파일시스템ID
	 */
	private String fileSysId;
	/**
	 * 파일시스템명
	 */
	private String fileSysNm;
	/**
	 * 파일시스템관리명
	 */
	private String fileSysManageNm;
	/**
	 * 파일시스템크기
	 */
	private int fileSysMg;
	/**
	 * 파일시스템임계치
	 */
	private int fileSysThrhld;
	/**
	 * 파일시스템임계치율
	 */
	private int fileSysThrhldRt;
	/**
	 * 파일시스템사용량
	 */
	private int fileSysUsgQty;
	/**
	 * 파일시스템사용률
	 */
	private double fileSysUsgRt;
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
	
	public String getLogId() {
		return logId;
	}
	public void setLogId(String logId) {
		this.logId = logId;
	}
	public String getFileSysId() {
		return fileSysId;
	}
	public void setFileSysId(String fileSysId) {
		this.fileSysId = fileSysId;
	}
	public String getFileSysNm() {
		return fileSysNm;
	}
	public void setFileSysNm(String fileSysNm) {
		this.fileSysNm = fileSysNm;
	}
	public String getFileSysManageNm() {
		return fileSysManageNm;
	}
	public void setFileSysManageNm(String fileSysManageNm) {
		this.fileSysManageNm = fileSysManageNm;
	}
	public int getFileSysMg() {
		return fileSysMg;
	}
	public void setFileSysMg(int fileSysMg) {
		this.fileSysMg = fileSysMg;
	}
	public int getFileSysThrhld() {
		return fileSysThrhld;
	}
	public void setFileSysThrhld(int fileSysThrhld) {
		this.fileSysThrhld = fileSysThrhld;
	}
	public int getFileSysThrhldRt() {
		return fileSysThrhldRt;
	}
	public void setFileSysThrhldRt(int fileSysThrhldRt) {
		this.fileSysThrhldRt = fileSysThrhldRt;
	}
	public int getFileSysUsgQty() {
		return fileSysUsgQty;
	}
	public void setFileSysUsgQty(int fileSysUsgQty) {
		this.fileSysUsgQty = fileSysUsgQty;
	}
	public double getFileSysUsgRt() {
		return fileSysUsgRt;
	}
	public void setFileSysUsgRt(double fileSysUsgRt) {
		this.fileSysUsgRt = fileSysUsgRt;
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