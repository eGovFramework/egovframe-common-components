package egovframework.com.uss.ion.rwd.service;

import egovframework.com.cmm.ComDefaultVO;

/**
 * 개요
 * - 포상관리에 대한 model 클래스를 정의한다.
 * 
 * 상세내용
 * - 포상관리의 포상자ID,포상코드,포상일자,공적내용,결재자ID,승인여부,결재일시,반려사유,최초등록자ID,최초등록시점,최종수정자ID,최종수정시점 항목을 관리한다.
 * @author 이용
 * @version 1.0
 * @created 06-15-2010 오후 2:08:56
 */

public class RwardManage extends ComDefaultVO {

	/**
	* serialVersionUID
	*/
	private static final long serialVersionUID = 1L;

	/**
	*  포상ID	      
	*/ 
	private String rwardId;
	
	/**
	*  포상자ID	      
	*/ 
	private String rwardManId;
	
	/**
	*  포상자명	      
	*/ 
	private String rwardManNm;

	public String getRwardManNm() {
		return rwardManNm;
	}

	public void setRwardManNm(String rwardManNm) {
		this.rwardManNm = rwardManNm;
	}

	/**
	*  포상코드	      
	*/ 
	private String rwardCd;

	/**
	*  포상일자	      
	*/ 
	private String rwardDe;

	/**
	*  포상명
	*/ 
	private String rwardNm;
	
	/**
	*  공적내용	      
	*/ 
	private String pblenCn;

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
	*  첨부파일ID	      
	*/ 
	private String atchFileId;

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
	 * @return the rwardId
	 */
	public String getRwardId() {
		return rwardId;
	}

	/**
	 * @param rwardId the rwardId to set
	 */
	public void setRwardId(String rwardId) {
		this.rwardId = rwardId;
	}

	/**
	 * @return the rwardManId
	 */
	public String getRwardManId() {
		return rwardManId;
	}

	/**
	 * @param rwardManId the rwardManId to set
	 */
	public void setRwardManId(String rwardManId) {
		this.rwardManId = rwardManId;
	}

	/**
	 * @return the rwardCd
	 */
	public String getRwardCd() {
		return rwardCd;
	}

	/**
	 * @param rwardCd the rwardCd to set
	 */
	public void setRwardCd(String rwardCd) {
		this.rwardCd = rwardCd;
	}

	/**
	 * @return the rwardDe
	 */
	public String getRwardDe() {
		return rwardDe;
	}

	/**
	 * @param rwardDe the rwardDe to set
	 */
	public void setRwardDe(String rwardDe) {
		this.rwardDe = rwardDe;
	}

	/**
	 * @return the rwardNm
	 */
	public String getRwardNm() {
		return rwardNm;
	}

	/**
	 * @param rwardNm the rwardNm to set
	 */
	public void setRwardNm(String rwardNm) {
		this.rwardNm = rwardNm;
	}

	/**
	 * @return the pblenCn
	 */
	public String getPblenCn() {
		return pblenCn;
	}

	/**
	 * @param pblenCn the pblenCn to set
	 */
	public void setPblenCn(String pblenCn) {
		this.pblenCn = pblenCn;
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
	 * @return the atchFileId
	 */
	public String getAtchFileId() {
		return atchFileId;
	}

	/**
	 * @param atchFileId the atchFileId to set
	 */
	public void setAtchFileId(String atchFileId) {
		this.atchFileId = atchFileId;
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