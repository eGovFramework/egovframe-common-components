package egovframework.com.cop.smt.mrm.service;

import java.io.Serializable;

/**
 * 개요
 * - 메모보고에 대한 model 클래스를 정의한다.
 * 
 * 상세내용
 * - 보고서ID, 보고서제목, 작성일자, 작성자ID, 작성자명, 작성자직급명, 보고자ID, 보고자명, 보고내용, 지시사항, 지시사항등록일시, 보고자조회일시,
 * 보고서상태 항목을 관리한다.
 * @author 장철호
 * @version 1.0
 * @created 19-7-2010 오전 10:14:53
 *  <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2010.7.19	장철호          최초 생성
 *
 * </pre>
 */
@SuppressWarnings("serial")
public class MemoReprt implements Serializable{

	/** 보고서ID */
	private String reprtId;
	/** 보고서제목 */
	private String reprtSj;
	/** 보고일자 */
	private String reprtDe;
	/** 작성자ID */
	private String wrterId;
	/** 작성자명 */
	private String wrterNm;
	/** 작성자직급명 */
	private String wrterClsfNm;
	/** 보고자ID */
	private String reportrId;
	/** 보고자명 */
	private String reportrNm;
	/** 보고자직급명 */
	private String reportrClsfNm;
	/** 보고내용 */
	private String reprtCn;
	/** 첨부파일ID */
	private String atchFileId;
	/** 지시사항 */
	private String drctMatter;
	/** 지시사항등록일시 */
	private String drctMatterRegistDt;
	/** 보고자조회일시 */
	private String reportrInqireDt;
	/** 보고서상태 */
	private String reprtSttus;
	/** 최초등록자ID */
	private String frstRegisterId;
	/** 최초등록시점 */
	private String frstRegisterPnttm;
	/** 최종수정자ID */
	private String lastUpdusrId;
	/** 최종수정시점 */
	private String lastUpdusrPnttm;
	
	public String getReprtId() {
		return reprtId;
	}
	public void setReprtId(String reprtId) {
		this.reprtId = reprtId;
	}
	public String getReprtSj() {
		return reprtSj;
	}
	public void setReprtSj(String reprtSj) {
		this.reprtSj = reprtSj;
	}
	public String getReprtDe() {
		return reprtDe;
	}
	public void setReprtDe(String reprtDe) {
		this.reprtDe = reprtDe;
	}
	public String getWrterId() {
		return wrterId;
	}
	public void setWrterId(String wrterId) {
		this.wrterId = wrterId;
	}
	public String getWrterNm() {
		return wrterNm;
	}
	public void setWrterNm(String wrterNm) {
		this.wrterNm = wrterNm;
	}
	public String getWrterClsfNm() {
		return wrterClsfNm;
	}
	public void setWrterClsfNm(String wrterClsfNm) {
		this.wrterClsfNm = wrterClsfNm;
	}
	public String getReportrId() {
		return reportrId;
	}
	public void setReportrId(String reportrId) {
		this.reportrId = reportrId;
	}
	public String getReportrNm() {
		return reportrNm;
	}
	public void setReportrNm(String reportrNm) {
		this.reportrNm = reportrNm;
	}
	public String getReportrClsfNm() {
		return reportrClsfNm;
	}
	public void setReportrClsfNm(String reportrClsfNm) {
		this.reportrClsfNm = reportrClsfNm;
	}
	public String getReprtCn() {
		return reprtCn;
	}
	public void setReprtCn(String reprtCn) {
		this.reprtCn = reprtCn;
	}
	public String getAtchFileId() {
		return atchFileId;
	}
	public void setAtchFileId(String atchFileId) {
		this.atchFileId = atchFileId;
	}
	public String getDrctMatter() {
		return drctMatter;
	}
	public void setDrctMatter(String drctMatter) {
		this.drctMatter = drctMatter;
	}
	public String getDrctMatterRegistDt() {
		return drctMatterRegistDt;
	}
	public void setDrctMatterRegistDt(String drctMatterRegistDt) {
		this.drctMatterRegistDt = drctMatterRegistDt;
	}
	public String getReportrInqireDt() {
		return reportrInqireDt;
	}
	public void setReportrInqireDt(String reportrInqireDt) {
		this.reportrInqireDt = reportrInqireDt;
	}
	public String getReprtSttus() {
		return reprtSttus;
	}
	public void setReprtSttus(String reprtSttus) {
		this.reprtSttus = reprtSttus;
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