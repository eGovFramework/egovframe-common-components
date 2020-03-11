package egovframework.com.uss.ion.bnt.service;

import java.io.Serializable;
import java.util.List;

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

public class BndtManageVO extends BndtManage implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;	
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

    
    
}
