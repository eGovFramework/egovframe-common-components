package egovframework.com.uss.ion.vct.service;

import egovframework.com.cmm.ComDefaultVO;

/**
 * 개요
 * - 휴가관리에 대한 model 클래스를 정의한다.
 * 
 * 상세내용
 * - 휴가관리의 신청자ID,휴가구분,시작일자,종료일자,신청일자,휴가사유,발생년도,결재자ID,승인여부,결재일시,반려사유,최초등록자ID,최초등록시점,최종수정자ID,최종수정시점 항목을 관리한다.
 * @author 이용
 * @version 1.0
 * @created 06-15-2010 오후 2:08:56
 */

public class VcatnManage extends ComDefaultVO {
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	*  신청자ID	      
	*/ 
	private String applcntId;

	/**
	*  휴가구분	      
	*/ 
	private String vcatnSe;

	/**
	*  시작일자	      
	*/ 
	private String bgnde;

	/**
	*  종료일자	      
	*/ 
	private String endde;
	
	/**
	*  신청일자	      
	*/ 
	private String reqstDe;

	/**
	*  휴가사유	      
	*/ 
	private String vcatnResn;
	
	/**
	*  발생연도	      
	*/ 
	private String occrrncYear;

	/**
	*  정오구분	      
	*/ 
	private String noonSe;
	
	/**
	*  결재자ID	      
	*/ 
	private String sanctnerId;

	/**
	*  승인여부	      
	*/ 
	private String confmAt;

	/**
	*  결재일시	      
	*/ 
	private String sanctnDt;

	/**
	*  반려사유	      
	*/ 
	private String returnResn;

	/**
	*  약식결재ID	      
	*/ 
	private String infrmlSanctnId;	
	
	
	/**
	*  최초등록자ID	
	*/ 
	private String frstRegisterId;

	/**
	*  최초등록시점	
	*/ 
	private String frstRegisterPnttm;

	/**
	*  최종수정자ID	
	*/ 
	private String lastUpdusrId;

	/**
	*  최종수정시점	
	*/ 
	private String lastUpdusrPnttm;

	/**
	*  sanctnDtNm	
	*/ 
	private String sanctnDtNm;
	
	/**
	*  orgnztNm	
	*/ 
	private String orgnztNm;

	/**
	 * @return the sanctnDtNm
	 */
	public String getSanctnDtNm() {
		return sanctnDtNm;
	}

	/**
	 * @param sanctnDtNm the sanctnDtNm to set
	 */
	public void setSanctnDtNm(String sanctnDtNm) {
		this.sanctnDtNm = sanctnDtNm;
	}

	/**
	 * @return the orgnztNm
	 */
	public String getOrgnztNm() {
		return orgnztNm;
	}

	/**
	 * @param orgnztNm the orgnztNm to set
	 */
	public void setOrgnztNm(String orgnztNm) {
		this.orgnztNm = orgnztNm;
	}

	/**
	 * @return the applcntId
	 */
	public String getApplcntId() {
		return applcntId;
	}

	/**
	 * @param applcntId the applcntId to set
	 */
	public void setApplcntId(String applcntId) {
		this.applcntId = applcntId;
	}

	/**
	 * @return the vcatnSe
	 */
	public String getVcatnSe() {
		return vcatnSe;
	}

	/**
	 * @param vcatnSe the vcatnSe to set
	 */
	public void setVcatnSe(String vcatnSe) {
		this.vcatnSe = vcatnSe;
	}

	/**
	 * @return the bgnde
	 */
	public String getBgnde() {
		return bgnde;
	}

	/**
	 * @param bgnde the bgnde to set
	 */
	public void setBgnde(String bgnde) {
		this.bgnde = bgnde;
	}

	/**
	 * @return the endde
	 */
	public String getEndde() {
		return endde;
	}

	/**
	 * @param endde the endde to set
	 */
	public void setEndde(String endde) {
		this.endde = endde;
	}

	/**
	 * @return the reqstDe
	 */
	public String getReqstDe() {
		return reqstDe;
	}

	/**
	 * @param reqstDe the reqstDe to set
	 */
	public void setReqstDe(String reqstDe) {
		this.reqstDe = reqstDe;
	}

	/**
	 * @return the vcatnResn
	 */
	public String getVcatnResn() {
		return vcatnResn;
	}

	/**
	 * @param vcatnResn the vcatnResn to set
	 */
	public void setVcatnResn(String vcatnResn) {
		this.vcatnResn = vcatnResn;
	}

	/**
	 * @return the occrrncYear
	 */
	public String getOccrrncYear() {
		return occrrncYear;
	}

	/**
	 * @param occrrncYear the occrrncYear to set
	 */
	public void setOccrrncYear(String occrrncYear) {
		this.occrrncYear = occrrncYear;
	}

	/**
	 * @return the sanctnerId
	 */
	public String getSanctnerId() {
		return sanctnerId;
	}

	/**
	 * @param sanctnerId the sanctnerId to set
	 */
	public void setSanctnerId(String sanctnerId) {
		this.sanctnerId = sanctnerId;
	}

	/**
	 * @return the confmAt
	 */
	public String getConfmAt() {
		return confmAt;
	}

	/**
	 * @param confmAt the confmAt to set
	 */
	public void setConfmAt(String confmAt) {
		this.confmAt = confmAt;
	}

	/**
	 * @return the sanctnDt
	 */
	public String getSanctnDt() {
		return sanctnDt;
	}

	/**
	 * @param sanctnDt the sanctnDt to set
	 */
	public void setSanctnDt(String sanctnDt) {
		this.sanctnDt = sanctnDt;
	}

	/**
	 * @return the returnResn
	 */
	public String getReturnResn() {
		return returnResn;
	}

	/**
	 * @param returnResn the returnResn to set
	 */
	public void setReturnResn(String returnResn) {
		this.returnResn = returnResn;
	}

	/**
	 * @return the infrmlSanctnId
	 */
	public String getInfrmlSanctnId() {
		return infrmlSanctnId;
	}

	/**
	 * @param infrmlSanctnId the infrmlSanctnId to set
	 */
	public void setInfrmlSanctnId(String infrmlSanctnId) {
		this.infrmlSanctnId = infrmlSanctnId;
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
	 * @return the noonSe
	 */
	public String getNoonSe() {
		return noonSe;
	}

	/**
	 * @param noonSe the noonSe to set
	 */
	public void setNoonSe(String noonSe) {
		this.noonSe = noonSe;
	}
}