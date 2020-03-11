package egovframework.com.cop.smt.wmr.service;

import java.io.Serializable;

/**
 * 개요
 * - 주간월간보고에 대한 model 클래스를 정의한다.
 * 
 * 상세내용
 * - 보고서ID, 보고서구분, 보고서제목, 작성일자, 작성자ID, 작성자명, 작성자직급명, 보고자ID, 보고자명, 보고시작일자, 보고종료일자,
 * 금주보고내용, 차주보고내용, 특이사항, 첨부파일ID, 승인일시, 보고서상태 항목을 관리한다.
 * @author 장철호
 * @version 1.0
 * @created 19-7-2010 오전 10:12:48
 *   <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2010.7.19	장철호          최초 생성
 *
 * </pre>
 */
@SuppressWarnings("serial")
public class WikMnthngReprt implements Serializable{

	/** 보고서ID */
	private String reprtId;
	/** 보고서구분 */
	private String reprtSe;
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
	/** 보고시작일자 */
	private String reprtBgnDe;
	/** 보고종료일자 */
	private String reprtEndDe;
	/** 금주보고내용 */
	private String reprtThswikCn;
	/** 차주보고내용 */
	private String reprtLesseeCn;
	/** 특이사항 */
	private String partclrMatter;
	/** 첨부파일ID */
	private String atchFileId;
	/** 승인일시 */
	private String confmDt;
	/** 보고서상태 */
	private String reprtSttus;
	/** 최초등록자ID */
	private String frstRegisterId = "";
	/** 최초등록시점*/
	private String frstRegisterPnttm = "";
	/** 최종수정자ID */
	private String lastUpdusrId = "";
	/** 최종수정시점 */
	private String lastUpdusrPnttm = "";
	
	public String getReprtId() {
		return reprtId;
	}
	public void setReprtId(String reprtId) {
		this.reprtId = reprtId;
	}
	public String getReprtSe() {
		return reprtSe;
	}
	public void setReprtSe(String reprtSe) {
		this.reprtSe = reprtSe;
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
	public String getReprtBgnDe() {
		return reprtBgnDe;
	}
	public void setReprtBgnDe(String reprtBgnDe) {
		this.reprtBgnDe = reprtBgnDe;
	}
	public String getReprtEndDe() {
		return reprtEndDe;
	}
	public void setReprtEndDe(String reprtEndDe) {
		this.reprtEndDe = reprtEndDe;
	}
	public String getReprtThswikCn() {
		return reprtThswikCn;
	}
	public void setReprtThswikCn(String reprtThswikCn) {
		this.reprtThswikCn = reprtThswikCn;
	}
	public String getReprtLesseeCn() {
		return reprtLesseeCn;
	}
	public void setReprtLesseeCn(String reprtLesseeCn) {
		this.reprtLesseeCn = reprtLesseeCn;
	}
	public String getPartclrMatter() {
		return partclrMatter;
	}
	public void setPartclrMatter(String partclrMatter) {
		this.partclrMatter = partclrMatter;
	}
	public String getAtchFileId() {
		return atchFileId;
	}
	public void setAtchFileId(String atchFileId) {
		this.atchFileId = atchFileId;
	}
	public String getConfmDt() {
		return confmDt;
	}
	public void setConfmDt(String confmDt) {
		this.confmDt = confmDt;
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