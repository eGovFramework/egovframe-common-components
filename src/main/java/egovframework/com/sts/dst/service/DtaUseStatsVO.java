/**
 * 개요
 * -자료이용현황 통계에 대한 Vo 클래스를 정의한다.
 * 
 * 상세내용
 * - 자료이용현황 통계정보의 목록 항목을 관리한다.
 * @author lee.m.j
 * @version 1.0
 * @created 08-9-2009 오후 1:40:19
 */

package egovframework.com.sts.dst.service;

import java.util.List;

public class DtaUseStatsVO extends DtaUseStats {
	
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;	
	/**
	 * 기간구분
	 */	
	private String pmDateTy;
	/**
	 * 자료이용현황 통계 시작일자
	 */	
	private String pmFromDate;
	/**
	 * 자료이용현황 통계 종료일자
	 */	
	private String pmToDate;
	/**
	 * 자료이용현황 통계
	 */
	List<DtaUseStatsVO> dtaUseStatsList;
	/**
	 * 등록일자별 통계 그래프 목록
	 */	
	List <DtaUseStatsVO> dtaUseStatsBarList;
	/**
	 * 등록일자별 통계 그래프 사이즈 단위
	 */		
	float maxUnit = 50.0f;

	/**
	 * @return the pmDateTy
	 */
	public String getPmDateTy() {
		return pmDateTy;
	}
	/**
	 * @param pmDateTy the pmDateTy to set
	 */
	public void setPmDateTy(String pmDateTy) {
		this.pmDateTy = pmDateTy;
	}
	/**
	 * @return the pmFromDate
	 */
	public String getPmFromDate() {
		return pmFromDate;
	}
	/**
	 * @param pmFromDate the pmFromDate to set
	 */
	public void setPmFromDate(String pmFromDate) {
		this.pmFromDate = pmFromDate;
	}
	/**
	 * @return the pmToDate
	 */
	public String getPmToDate() {
		return pmToDate;
	}
	/**
	 * @param pmToDate the pmToDate to set
	 */
	public void setPmToDate(String pmToDate) {
		this.pmToDate = pmToDate;
	}
	/**
	 * @return the dtaUseStatsList
	 */
	public List<DtaUseStatsVO> getDtaUseStatsList() {
		return dtaUseStatsList;
	}
	/**
	 * @param dtaUseStatsList the dtaUseStatsList to set
	 */
	public void setDtaUseStatsList(List<DtaUseStatsVO> dtaUseStatsList) {
		this.dtaUseStatsList = dtaUseStatsList;
	}
	/**
	 * @return the dtaUseStatsBarList
	 */
	public List<DtaUseStatsVO> getDtaUseStatsBarList() {
		return dtaUseStatsBarList;
	}
	/**
	 * @param dtaUseStatsBarList the dtaUseStatsBarList to set
	 */
	public void setDtaUseStatsBarList(List<DtaUseStatsVO> dtaUseStatsBarList) {
		this.dtaUseStatsBarList = dtaUseStatsBarList;
	}
	/**
	 * @return the maxUnit
	 */
	public float getMaxUnit() {
		return maxUnit;
	}
	/**
	 * @param maxUnit the maxUnit to set
	 */
	public void setMaxUnit(float maxUnit) {
		this.maxUnit = maxUnit;
	}
}
