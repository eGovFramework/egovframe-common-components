package egovframework.com.uss.ion.mtg.service;

import egovframework.com.cmm.ComDefaultVO;

/**
 * 개요
 * - 회의실관리에 대한 model 클래스를 정의한다.
 * 
 * 상세내용
 * - 회의실관리의 회의실코드,회의실명,개방시작시간,개방종료시간,수용가능인원,위치구분,위치상세,최초등록자ID,최초등록시점,최종수정자ID,최종수정시점 항목을 관리한다.
 * @author 이용
 * @version 1.0
 * @created 06-15-2010 오후 2:08:56
 */

public class MtgPlaceManage extends ComDefaultVO {

	/**
	* serialVersionUID
	*/
	private static final long serialVersionUID = 1L;
	
	/**
	*  회의실ID
	*/
	private String mtgPlaceId;
	
	/**
	* 회의실명
	*/
	private String mtgPlaceNm;
	
	/**
	* 개방시작시간
	*/
	private String opnBeginTm;
	
	/**
	* 개방종료시간
	*/
	private String opnEndTm;
	
	/**
	* 수용가능인원
	*/
	private int aceptncPosblNmpr;
	
	/**
	* 위치구분
	*/
	private String lcSe;
	
	/**
	* 위치상세
	*/
	private String lcDetail;
	
	/**
	* 첨부파일
	*/
	private String atchFileId;

	/**
	* 최초등록자ID
	*/
	private String frstRegisterId;
	
	/**
	* 최초등록시점
	*/
	private String frstRegisterPnttm;
	
	/**
	* 최종수정자ID
	*/
	private String lastUpdusrId;
	
	/**
	* 최종수정시점
	*/
	private String lastUpdusrPnttm;

	/**
	 * @return the mtgPlaceCd
	 */
	public String getMtgPlaceId() {
		return mtgPlaceId;
	}

	/**
	 * @param mtgPlaceCd the mtgPlaceCd to set
	 */
	public void setMtgPlaceId(String mtgPlaceId) {
		this.mtgPlaceId = mtgPlaceId;
	}

	/**
	 * @return the mtgPlaceNm
	 */
	public String getMtgPlaceNm() {
		return mtgPlaceNm;
	}

	/**
	 * @param mtgPlaceNm the mtgPlaceNm to set
	 */
	public void setMtgPlaceNm(String mtgPlaceNm) {
		this.mtgPlaceNm = mtgPlaceNm;
	}

	/**
	 * @return the opnBeginTm
	 */
	public String getOpnBeginTm() {
		return opnBeginTm;
	}

	/**
	 * @param opnBeginTm the opnBeginTm to set
	 */
	public void setOpnBeginTm(String opnBeginTm) {
		this.opnBeginTm = opnBeginTm;
	}

	/**
	 * @return the opnEndTm
	 */
	public String getOpnEndTm() {
		return opnEndTm;
	}

	/**
	 * @param opnEndTm the opnEndTm to set
	 */
	public void setOpnEndTm(String opnEndTm) {
		this.opnEndTm = opnEndTm;
	}

	/**
	 * @return the aceptncPosblNmpr
	 */
	public int getAceptncPosblNmpr() {
		return aceptncPosblNmpr;
	}

	/**
	 * @param aceptncPosblNmpr the aceptncPosblNmpr to set
	 */
	public void setAceptncPosblNmpr(int aceptncPosblNmpr) {
		this.aceptncPosblNmpr = aceptncPosblNmpr;
	}

	/**
	 * @return the lcSe
	 */
	public String getLcSe() {
		return lcSe;
	}

	/**
	 * @param lcSe the lcSe to set
	 */
	public void setLcSe(String lcSe) {
		this.lcSe = lcSe;
	}

	/**
	 * @return the lcDetail
	 */
	public String getLcDetail() {
		return lcDetail;
	}

	/**
	 * @param lcDetail the lcDetail to set
	 */
	public void setLcDetail(String lcDetail) {
		this.lcDetail = lcDetail;
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
}