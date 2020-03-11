package egovframework.com.uss.ion.mtg.service;

import egovframework.com.cmm.ComDefaultVO;

/**
 * 개요
 * - 회의실예약에 대한 model 클래스를 정의한다.
 * 
 * 상세내용
 * - 회의실예약의 예약ID,회의실코드,회의제목,예약자ID,예약시작시간,예약종료시간,최초등록자ID,최초등록시점,최종수정자ID,최종수정시점 항목을 관리한다.
 * @author 이용
 * @version 1.0
 * @created 06-15-2010 오후 2:08:56
 */

public class MtgPlaceResve extends ComDefaultVO {

	/**
	* serialVersionUID
	*/
	private static final long serialVersionUID = 1L;
	
	/**
	*  예약ID	
	*/ 
	private String resveId;

	/**
	*  회의실코드	   
	*/ 
	private String mtgPlaceId;

	/**
	*  회의제목	      
	*/ 
	private String mtgSj;

	/**
	*  예약자ID	      
	*/ 
	private String resveManId;

	/**
	*  예약일자	      
	*/ 
	private String resveDe;
	
	/**
	*  예약시작시간	
	*/ 
	private String resveBeginTm;

	/**
	*  예약종료시간	
	*/ 
	private String resveEndTm;

	/**
	*  참석인원	
	*/ 
	private int atndncNmpr;
	
	/**
	*  회의내용	
	*/ 
	private String mtgCn;
	
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
	 * @return the resveId
	 */
	public String getResveId() {
		return resveId;
	}

	/**
	 * @param resveId the resveId to set
	 */
	public void setResveId(String resveId) {
		this.resveId = resveId;
	}


	/**
	 * @return the mtgPlaceId
	 */
	public String getMtgPlaceId() {
		return mtgPlaceId;
	}

	/**
	 * @param mtgPlaceId the mtgPlaceId to set
	 */
	public void setMtgPlaceId(String mtgPlaceId) {
		this.mtgPlaceId = mtgPlaceId;
	}

	/**
	 * @return the mtgSj
	 */
	public String getMtgSj() {
		return mtgSj;
	}

	/**
	 * @param mtgSj the mtgSj to set
	 */
	public void setMtgSj(String mtgSj) {
		this.mtgSj = mtgSj;
	}

	/**
	 * @return the resveManId
	 */
	public String getResveManId() {
		return resveManId;
	}

	/**
	 * @param resveManId the resveManId to set
	 */
	public void setResveManId(String resveManId) {
		this.resveManId = resveManId;
	}

	/**
	 * @return the resveDe
	 */
	public String getResveDe() {
		return resveDe;
	}

	/**
	 * @param resveDe the resveDe to set
	 */
	public void setResveDe(String resveDe) {
		this.resveDe = resveDe;
	}

	/**
	 * @return the resveBeginTm
	 */
	public String getResveBeginTm() {
		return resveBeginTm;
	}

	/**
	 * @param resveBeginTm the resveBeginTm to set
	 */
	public void setResveBeginTm(String resveBeginTm) {
		this.resveBeginTm = resveBeginTm;
	}

	/**
	 * @return the resveEndTm
	 */
	public String getResveEndTm() {
		return resveEndTm;
	}

	/**
	 * @param resveEndTm the resveEndTm to set
	 */
	public void setResveEndTm(String resveEndTm) {
		this.resveEndTm = resveEndTm;
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
	 * @return the atndncNmpr
	 */
	public int getAtndncNmpr() {
		return atndncNmpr;
	}

	/**
	 * @param atndncNmpr the atndncNmpr to set
	 */
	public void setAtndncNmpr(int atndncNmpr) {
		this.atndncNmpr = atndncNmpr;
	}

	/**
	 * @return the mtgCn
	 */
	public String getMtgCn() {
		return mtgCn;
	}

	/**
	 * @param mtgCn the mtgCn to set
	 */
	public void setMtgCn(String mtgCn) {
		this.mtgCn = mtgCn;
	}
}