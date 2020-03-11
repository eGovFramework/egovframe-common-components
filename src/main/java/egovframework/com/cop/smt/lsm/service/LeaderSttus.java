package egovframework.com.cop.smt.lsm.service;

import java.io.Serializable;

/**
 * 개요
 * - 간부상태에 대한 model 클래스를 정의한다.
 * 
 * 상세내용
 * - 간부ID, 간부상태 항목을 관리한다.
 * @author 장철호
 * @version 1.0
 * @created 28-6-2010 오전 10:59:06
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2010.6.28	장철호          최초 생성
 *
 * </pre>
 *  */
@SuppressWarnings("serial")
public class LeaderSttus  implements Serializable{

	/** 간부ID */
	private String leaderId;
	/** 간부명 */
	private String leaderNm;
	/** 소속 */
	private String orgnztNm;
	/** 간부상태 */
	private String leaderSttus;
	/** 간부상태 */
	private String leaderSttusNm;
	/** 최초등록자ID */
	private String frstRegisterId = "";
	/** 최초등록시점 */
	private String frstRegisterPnttm = "";
	/** 최종수정자ID */
	private String lastUpdusrId = "";
	/** 최종수정자명 */
	private String lastUpdusrNm = "";
	/** 최종수정시점 */
	private String lastUpdusrPnttm = "";
	public String getLeaderId() {
		return leaderId;
	}
	public void setLeaderId(String leaderId) {
		this.leaderId = leaderId;
	}
	public String getLeaderNm() {
		return leaderNm;
	}
	public void setLeaderNm(String leaderNm) {
		this.leaderNm = leaderNm;
	}
	public String getOrgnztNm() {
		return orgnztNm;
	}
	public void setOrgnztNm(String orgnztNm) {
		this.orgnztNm = orgnztNm;
	}
	public String getLeaderSttus() {
		return leaderSttus;
	}
	public void setLeaderSttus(String leaderSttus) {
		this.leaderSttus = leaderSttus;
	}
	public String getLeaderSttusNm() {
		return leaderSttusNm;
	}
	public void setLeaderSttusNm(String leaderSttusNm) {
		this.leaderSttusNm = leaderSttusNm;
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
	public String getLastUpdusrNm() {
		return lastUpdusrNm;
	}
	public void setLastUpdusrNm(String lastUpdusrNm) {
		this.lastUpdusrNm = lastUpdusrNm;
	}
	public String getLastUpdusrPnttm() {
		return lastUpdusrPnttm;
	}
	public void setLastUpdusrPnttm(String lastUpdusrPnttm) {
		this.lastUpdusrPnttm = lastUpdusrPnttm;
	}
	
	
}