package egovframework.com.uss.ion.ctn.service;

import egovframework.com.cmm.ComDefaultVO;

/**
 * 개요
 * - 경조관리에 대한 model 클래스를 정의한다.
 * 
 * 상세내용
 * - 경조관리의 사용자ID,경조코드,신청일자,대상자명,생년월일,발생일자,관계,비고,결재자ID,승인여부,결재일시,반려사유,최초등록자ID,최초등록시점,최종수정자ID,최종수정시점 항목을 관리한다.
 * @author 이용
 * @version 1.0
 * @created 06-15-2010 오후 2:08:56
 */

public class CtsnnManage extends ComDefaultVO {

	/**
	* serialVersionUID
	*/
	private static final long serialVersionUID = 1L;
	
	/**
	*  경조ID	      
	*/ 
	private String ctsnnId;

	/**
	*  사용자ID	      
	*/ 
	private String usid;

	/**
	*  경조코드	      
	*/ 
	private String ctsnnCd;

	/**
	*  신청일자	      
	*/ 
	private String reqstDe;

	/**
	*  경조명	      
	*/ 
   private String ctsnnNm;

	/**
	*  대상자명	      
	*/ 
	private String trgterNm;

	/**
	*  생년월일	      
	*/ 
	private String brth;

	/**
	*  발생일자	      
	*/ 
	private String occrrDe;

	/**
	*  관계	          
	*/ 
	private String relate;

	/**
	*  비고	          
	*/ 
	private String remark;

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
	 * @return the ctsnnId
	 */
	public String getCtsnnId() {
		return ctsnnId;
	}

	/**
	 * @param ctsnnId the ctsnnId to set
	 */
	public void setCtsnnId(String ctsnnId) {
		this.ctsnnId = ctsnnId;
	}

	/**
	 * @return the usid
	 */
	public String getUsid() {
		return usid;
	}

	/**
	 * @param usid the usid to set
	 */
	public void setUsid(String usid) {
		this.usid = usid;
	}

	/**
	 * @return the ctsnnCd
	 */
	public String getCtsnnCd() {
		return ctsnnCd;
	}

	/**
	 * @param ctsnnCd the ctsnnCd to set
	 */
	public void setCtsnnCd(String ctsnnCd) {
		this.ctsnnCd = ctsnnCd;
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
	 * @return the ctsnnNm
	 */
	public String getCtsnnNm() {
		return ctsnnNm;
	}

	/**
	 * @param ctsnnNm the ctsnnNm to set
	 */
	public void setCtsnnNm(String ctsnnNm) {
		this.ctsnnNm = ctsnnNm;
	}

	/**
	 * @return the trgterNm
	 */
	public String getTrgterNm() {
		return trgterNm;
	}

	/**
	 * @param trgterNm the trgterNm to set
	 */
	public void setTrgterNm(String trgterNm) {
		this.trgterNm = trgterNm;
	}

	/**
	 * @return the brth
	 */
	public String getBrth() {
		return brth;
	}

	/**
	 * @param brth the brth to set
	 */
	public void setBrth(String brth) {
		this.brth = brth;
	}

	/**
	 * @return the occrrDe
	 */
	public String getOccrrDe() {
		return occrrDe;
	}

	/**
	 * @param occrrDe the occrrDe to set
	 */
	public void setOccrrDe(String occrrDe) {
		this.occrrDe = occrrDe;
	}

	/**
	 * @return the relate
	 */
	public String getRelate() {
		return relate;
	}

	/**
	 * @param relate the relate to set
	 */
	public void setRelate(String relate) {
		this.relate = relate;
	}

	/**
	 * @return the remark
	 */
	public String getRemark() {
		return remark;
	}

	/**
	 * @param remark the remark to set
	 */
	public void setRemark(String remark) {
		this.remark = remark;
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

}