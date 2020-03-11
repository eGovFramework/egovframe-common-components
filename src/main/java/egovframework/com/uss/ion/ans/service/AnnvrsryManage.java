package egovframework.com.uss.ion.ans.service;

import egovframework.com.cmm.ComDefaultVO;

/**
 * 개요
 * - 기념일관리에 대한 model 클래스를 정의한다.
 * 
 * 상세내용
 * - 기념일관리의 사용자ID,기념일명,기념일자,달력구분,알림설정,알림시작일자,메모,최초등록자ID,최초등록시점,최종수정자ID,최종수정시점 항목을 관리한다.
 * @author 이용
 * @version 1.0
 * @created 06-15-2010 오후 2:08:56
 */

public class AnnvrsryManage extends ComDefaultVO {

	/**
	* serialVersionUID
	*/
	private static final long serialVersionUID = 1L;
	
	/**
	*  기념일ID	      
	*/ 
	private String annId;

	
	/**
	*  사용자ID	      
	*/ 
	private String usid;

	/**
	*  기념일구분	      
	*/ 
	private String annvrsrySe;

	
	/**
	*  기념일명	      
	*/ 
	private String annvrsryNm;

	/**
	*  기념일자	      
	*/ 
	private String annvrsryDe;

	/**
	*  달력구분	      
	*/ 
	private String cldrSe;

	/**
	*  반복구분	      
	*/ 
	private String reptitSe;
	
	/**
	*  알림설정	      
	*/ 
	private String annvrsrySetup;

	/**
	*  알림시작일자	
	*/ 
	private String annvrsryBeginDe;

	/**
	*  메모	         
	*/ 
	private String memo;

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
	 * @return the annId
	 */
	public String getAnnId() {
		return annId;
	}

	/**
	 * @param annId the annId to set
	 */
	public void setAnnId(String annId) {
		this.annId = annId;
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
	 * @return the annvrsrySe
	 */
	public String getAnnvrsrySe() {
		return annvrsrySe;
	}

	/**
	 * @param annvrsrySe the annvrsrySe to set
	 */
	public void setAnnvrsrySe(String annvrsrySe) {
		this.annvrsrySe = annvrsrySe;
	}

	/**
	 * @return the annvrsryNm
	 */
	public String getAnnvrsryNm() {
		return annvrsryNm;
	}

	/**
	 * @param annvrsryNm the annvrsryNm to set
	 */
	public void setAnnvrsryNm(String annvrsryNm) {
		this.annvrsryNm = annvrsryNm;
	}

	/**
	 * @return the annvrsryDe
	 */
	public String getAnnvrsryDe() {
		return annvrsryDe;
	}

	/**
	 * @param annvrsryDe the annvrsryDe to set
	 */
	public void setAnnvrsryDe(String annvrsryDe) {
		this.annvrsryDe = annvrsryDe;
	}

	/**
	 * @return the cldrSe
	 */
	public String getCldrSe() {
		return cldrSe;
	}

	/**
	 * @param cldrSe the cldrSe to set
	 */
	public void setCldrSe(String cldrSe) {
		this.cldrSe = cldrSe;
	}

	/**
	 * @return the annvrsrySetup
	 */
	public String getAnnvrsrySetup() {
		return annvrsrySetup;
	}

	/**
	 * @param annvrsrySetup the annvrsrySetup to set
	 */
	public void setAnnvrsrySetup(String annvrsrySetup) {
		this.annvrsrySetup = annvrsrySetup;
	}

	/**
	 * @return the annvrsryBeginDe
	 */
	public String getAnnvrsryBeginDe() {
		return annvrsryBeginDe;
	}

	/**
	 * @param annvrsryBeginDe the annvrsryBeginDe to set
	 */
	public void setAnnvrsryBeginDe(String annvrsryBeginDe) {
		this.annvrsryBeginDe = annvrsryBeginDe;
	}

	/**
	 * @return the memo
	 */
	public String getMemo() {
		return memo;
	}

	/**
	 * @param memo the memo to set
	 */
	public void setMemo(String memo) {
		this.memo = memo;
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
	 * @return the serialVersionUID
	 */
	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	/**
	 * @return the reptitSe
	 */
	public String getReptitSe() {
		return reptitSe;
	}

	/**
	 * @param reptitSe the reptitSe to set
	 */
	public void setReptitSe(String reptitSe) {
		this.reptitSe = reptitSe;
	}

	
}