package egovframework.com.cop.smt.djm.service;

import java.io.Serializable;

/**
 * 개요
 * - 부서업무함에 대한 model 클래스를 정의한다.
 * 
 * 상세내용
 * - 부서업무함ID, 부서업무함명, 부서ID, 표시순서 의 항목을 관리한다.
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
public class DeptJobBx implements Serializable{

	/** 부서업무함 ID */
	private String deptJobBxId;
	/** 부서업무함명 */
	private String deptJobBxNm;
	/** 부서 ID */
	private String deptId;
	/** 부서명 */
	private String deptNm;
	/** 표시순서 */
	private int indictOrdr;
	/** 최초등록자 ID */
	private String frstRegisterId = "";
	/** 최초등록시점 */
	private String frstRegisterPnttm = "";
	/** 최종수정자 ID */
	private String lastUpdusrId = "";
	/** 최종수정자명 */
	private String lastUpdusrNm = "";
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
	public int getIndictOrdr() {
		return indictOrdr;
	}
	public void setIndictOrdr(int indictOrdr) {
		this.indictOrdr = indictOrdr;
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