package egovframework.com.uss.ion.bnt.service;

import java.util.List;

import org.egovframe.rte.ptl.reactive.validation.EgovNullCheck;

import egovframework.com.cmm.ComDefaultVO;
import jakarta.validation.constraints.Size;

/**
 * 개요
 * - 당직관리에 대한 Vo 클래스를 정의한다.
 * 
 * 상세내용
 * - 당직관리의 목록 항목을 관리한다.
 * @author 이용
 * @version 1.0
 * @created 06-15-2010 오후 2:08:56
 */

public class BndtManageVO extends ComDefaultVO {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	/**
	*  당직ID	      
	*/ 
	@EgovNullCheck
	@Size(max=20)
	private String bndtId;

	/**
	*  당직일자	      
	*/ 
	@EgovNullCheck
	private String bndtDe;

	/**
	*  비고	         
	*/ 
	@Size(max=2500)
	private String remark;

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
	 * 배너 목록
	 */	
	List<BndtManageVO> bndtManageList;

	/**
	 * 당직 bndtTemp1
	 */	
	private String bndtTemp1;
	
	/**
	 * 당직 bndtTemp2
	 */	
	private String bndtTemp2;
	
	/**
	 * 당직 tempBndtNm
	 */	
	private String tempBndtNm;
	
	/**
	 * 당직 tempBndtId
	 */	
	private String tempBndtId;	

	/**
	 * 당직 tempBndtWeek
	 */	
	private String tempBndtWeek;
	
	/**
	 * 당직 tempOrgnztNm
	 */	
	private String tempOrgnztNm;	
	
	/**
	 * 당직 tempCount
	 */	
	private int tempCount;	

	/**
	 * 당직 dateWeek
	 */	
	private int dateWeek;	
	
	/**
	 * @return the annvrsryManageList
	 */
	public List<BndtManageVO> getBndtManageList() {
		return bndtManageList;
	}
	/**
	 * @param bannerList the bannerList to set
	 */
	public void setBndtManageList(List<BndtManageVO> bndtManageList) {
		this.bndtManageList = bndtManageList;
	}

	/**
	 * @return the tempBndtNm
	 */
	public String getTempBndtNm() {
		return tempBndtNm;
	}
	/**
	 * @param tempBndtNm the tempBndtNm to set
	 */
	public void setTempBndtNm(String tempBndtNm) {
		this.tempBndtNm = tempBndtNm;
	}
	/**
	 * @return the tempBndtId
	 */
	public String getTempBndtId() {
		return tempBndtId;
	}
	/**
	 * @param tempBndtId the tempBndtId to set
	 */
	public void setTempBndtId(String tempBndtId) {
		this.tempBndtId = tempBndtId;
	}
	/**
	 * @return the tempBndtWeek
	 */
	public String getTempBndtWeek() {
		return tempBndtWeek;
	}
	/**
	 * @param tempBndtWeek the tempBndtWeek to set
	 */
	public void setTempBndtWeek(String tempBndtWeek) {
		this.tempBndtWeek = tempBndtWeek;
	}
	/**
	 * @return the tempCount
	 */
	public int getTempCount() {
		return tempCount;
	}
	/**
	 * @param tempCount the tempCount to set
	 */
	public void setTempCount(int tempCount) {
		this.tempCount = tempCount;
	}
	/**
	 * @return the dateWeek
	 */
	public int getDateWeek() {
		return dateWeek;
	}
	/**
	 * @param dateWeek the dateWeek to set
	 */
	public void setDateWeek(int dateWeek) {
		this.dateWeek = dateWeek;
	}
	/**
	 * @return the bndtTemp1
	 */
	public String getBndtTemp1() {
		return bndtTemp1;
	}
	/**
	 * @param bndtTemp1 the bndtTemp1 to set
	 */
	public void setBndtTemp1(String bndtTemp1) {
		this.bndtTemp1 = bndtTemp1;
	}
	/**
	 * @return the bndtTemp2
	 */
	public String getBndtTemp2() {
		return bndtTemp2;
	}
	/**
	 * @param bndtTemp2 the bndtTemp2 to set
	 */
	public void setBndtTemp2(String bndtTemp2) {
		this.bndtTemp2 = bndtTemp2;
	}
	/**
	 * @return the tempOrgnztNm
	 */
	public String getTempOrgnztNm() {
		return tempOrgnztNm;
	}
	/**
	 * @param tempOrgnztNm the tempOrgnztNm to set
	 */
	public void setTempOrgnztNm(String tempOrgnztNm) {
		this.tempOrgnztNm = tempOrgnztNm;
	}

	/**
	 * @return the bndtId
	 */
	public String getBndtId() {
		return bndtId;
	}

	/**
	 * @param bndtId the bndtId to set
	 */
	public void setBndtId(String bndtId) {
		this.bndtId = bndtId;
	}

	/**
	 * @return the bndtDe
	 */
	public String getBndtDe() {
		return bndtDe;
	}

	/**
	 * @param bndtDe the bndtDe to set
	 */
	public void setBndtDe(String bndtDe) {
		this.bndtDe = bndtDe;
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
