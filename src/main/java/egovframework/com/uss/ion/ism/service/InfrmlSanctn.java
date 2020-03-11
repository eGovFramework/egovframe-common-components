package egovframework.com.uss.ion.ism.service;

import java.io.Serializable;

/**
 * 개요
 * - 약식결재관리에 대한 model 클래스를 정의한다.
 * 
 * 상세내용
 * - 약식결재ID, 업무구분코드, 업무구분명, 연계URL, 신청자ID, 신청자명, 신청일자, 결재자ID, 결재자명, 결재자소속, 승인여부, 결재일시, 반려사유 의 항목을 관리한다.
 * @author 장철호
 * @version 1.0
 * @created 28-6-2010 오전 11:29:26
 */
@SuppressWarnings("serial")
public class InfrmlSanctn implements Serializable {
	/**
	 * 약식결재ID
	 */
	private String infrmlSanctnId;
	/**
	 * 업무구분코드
	 */
	private String jobSeCode;
	/**
	 * 업무구분명
	 */
	private String jobSeNm;
	/**
	 * 신청자ID
	 */
	private String applcntId;
	/**
	 * 신청자명
	 */
	private String applcntNm;
	/**
	 * 신청일자
	 */
	private String reqstDe = "";
	/**
	 * 결재자ID
	 */
	private String sanctnerId;
	/**
	 * 결재자명
	 */
	private String sanctnerNm;
	/**
	 * 결재자소속
	 */
	private String sanctnerOrgnztNm;
	/**
	 * 승인여부
	 */
	private String confmAt = "";
	/**
	 * 결재일시
	 */
	private String sanctnDt = "";
	/**
	 * 반려사유
	 */
	private String returnResn = "";
	/**
	 * 최초등록자 ID
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
	 * 최종수정자명
	 */
	private String lastUpdusrNm = "";
	/**
	 * 최종수정시점
	 */
	private String lastUpdusrPnttm = "";
	
	public String getInfrmlSanctnId() {
		return infrmlSanctnId;
	}
	public void setInfrmlSanctnId(String infrmlSanctnId) {
		this.infrmlSanctnId = infrmlSanctnId;
	}
	public String getJobSeCode() {
		return jobSeCode;
	}
	public void setJobSeCode(String jobSeCode) {
		this.jobSeCode = jobSeCode;
	}
	public String getJobSeNm() {
		return jobSeNm;
	}
	public void setJobSeNm(String jobSeNm) {
		this.jobSeNm = jobSeNm;
	}
	public String getApplcntId() {
		return applcntId;
	}
	public void setApplcntId(String applcntId) {
		this.applcntId = applcntId;
	}
	public String getApplcntNm() {
		return applcntNm;
	}
	public void setApplcntNm(String applcntNm) {
		this.applcntNm = applcntNm;
	}
	public String getReqstDe() {
		return reqstDe;
	}
	public void setReqstDe(String reqstDe) {
		this.reqstDe = reqstDe;
	}
	public String getSanctnerId() {
		return sanctnerId;
	}
	public void setSanctnerId(String sanctnerId) {
		this.sanctnerId = sanctnerId;
	}
	public String getSanctnerNm() {
		return sanctnerNm;
	}
	public void setSanctnerNm(String sanctnerNm) {
		this.sanctnerNm = sanctnerNm;
	}
	public String getSanctnerOrgnztNm() {
		return sanctnerOrgnztNm;
	}
	public void setSanctnerOrgnztNm(String sanctnerOrgnztNm) {
		this.sanctnerOrgnztNm = sanctnerOrgnztNm;
	}
	public String getConfmAt() {
		return confmAt;
	}
	public void setConfmAt(String confmAt) {
		this.confmAt = confmAt;
	}
	public String getSanctnDt() {
		return sanctnDt;
	}
	public void setSanctnDt(String sanctnDt) {
		this.sanctnDt = sanctnDt;
	}
	public String getReturnResn() {
		return returnResn;
	}
	public void setReturnResn(String returnResn) {
		this.returnResn = returnResn;
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