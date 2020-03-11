package egovframework.com.cop.smt.djm.service;

import java.io.Serializable;

/**
 * 개요
 * - 부서업무에 대한 model 클래스를 정의한다.
 * 
 * 상세내용
 * - 부서업무함ID, 부서업무ID, 부서업무명, 부서업무내용, 업무담당자, 우선순위, 첨부파일ID 의 항목을 관리한다.
 * @author 장철호
 * @version 1.0
 * @created 28-6-2010 오전 10:59:04
 *  <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2010.6.28	장철호          최초 생성
 *
 * </pre>
 */
@SuppressWarnings("serial")
public class DeptJob implements Serializable{

	/** 부서업무함 ID */
	private String deptJobBxId;
	/** 부서업무함명 */
	private String deptJobBxNm;
	/** 부서 ID */
	private String deptId;
	/** 부서명 */
	private String deptNm;
	/** 부서업무 ID */
	private String deptJobId;
	/** 부서업무명 */
	private String deptJobNm;
	/** 부서업무내용 */
	private String deptJobCn;
	/** 업무담당자 ID */
	private String chargerId;
	/** 업무담당자명 */
	private String chargerNm;
	/** 우선순위 */
	private String priort;
	/** 첨부파일 ID */
	private String atchFileId;
	/** 최초등록자 ID */
	private String frstRegisterId = "";
	/** 최초등록시점 */
	private String frstRegisterPnttm = "";
	/** 최종수정자ID */
	private String lastUpdusrId = "";
	/** 최종수정시점 */
	private String lastUpdusrPnttm = "";
	
	public String getDeptJobBxId() {
		return deptJobBxId;
	}
	public void setDeptJobBxId(String deptJobBxId) {
		this.deptJobBxId = deptJobBxId;
	}
	public String getDeptJobBxNm() {
		return deptJobBxNm;
	}
	public void setDeptJobBxNm(String deptJobBxNm) {
		this.deptJobBxNm = deptJobBxNm;
	}
	public String getDeptId() {
		return deptId;
	}
	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}
	public String getDeptNm() {
		return deptNm;
	}
	public void setDeptNm(String deptNm) {
		this.deptNm = deptNm;
	}
	public String getDeptJobId() {
		return deptJobId;
	}
	public void setDeptJobId(String deptJobId) {
		this.deptJobId = deptJobId;
	}
	public String getDeptJobNm() {
		return deptJobNm;
	}
	public void setDeptJobNm(String deptJobNm) {
		this.deptJobNm = deptJobNm;
	}
	public String getDeptJobCn() {
		return deptJobCn;
	}
	public void setDeptJobCn(String deptJobCn) {
		this.deptJobCn = deptJobCn;
	}
	public String getChargerId() {
		return chargerId;
	}
	public void setChargerId(String chargerId) {
		this.chargerId = chargerId;
	}
	public String getChargerNm() {
		return chargerNm;
	}
	public void setChargerNm(String chargerNm) {
		this.chargerNm = chargerNm;
	}
	public String getPriort() {
		return priort;
	}
	public void setPriort(String priort) {
		this.priort = priort;
	}
	public String getAtchFileId() {
		return atchFileId;
	}
	public void setAtchFileId(String atchFileId) {
		this.atchFileId = atchFileId;
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