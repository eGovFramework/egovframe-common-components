/**
 * 개요
 * - 보고서통계에 대한 Vo 클래스를 정의한다.
 * 
 * 상세내용
 * - 보고서통계정보의 목록 항목을 관리한다.
 * @author lee.m.j
 * @version 1.0
 * @created 03-8-2009 오후 2:09:16
 *  <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자          수정내용
 *  -------    --------    ---------------------------
 *  2009.8.3   lee.m.j          최초 생성 *  
 *  2011.8.26	정진오			IncludedInfo annotation 추가
 *
 *  </pre>
 */

package egovframework.com.sts.rst.service;

import java.util.List;

public class ReprtStatsVO extends ReprtStats {

	/** serialVersionUID */
	private static final long serialVersionUID = 1L;
	/** 보고서유형 */	
	private String pmReprtTy;
	/** 기간구분 */	
	private String pmDateTy;
	/** 보고서통계 시작일자 */	
	private String pmFromDate;
	/** 보고서통계 종료일자 */	
	private String pmToDate;
	/** 보고서통계 목록 */	
	List <ReprtStatsVO> reprtStatsList;
	/** 보고서통계 상세 목록 */	
	List <ReprtStatsVO> reprtStatsDetailList;
	/** 등록일자별 통계 그래프 목록 */	
	List <ReprtStatsVO> reprtStatsBarList;	
	/** 보고서유형별 통계 그래프 목록 */	
	List <ReprtStatsVO> reprtStatsByReprtTyList;
	/** 진행상태별 통계 그래프 목록 */	
	List <ReprtStatsVO> reprtStatsByReprtSttusList;
	/** 삭제여부 */
	String [] delYn;
	/** 보고서통계 그래프 사이즈 단위 */		
	float maxUnit = 50.0f;

	/**
	 * @return the pmReprtTy
	 */
	public String getPmReprtTy() {
		return pmReprtTy;
	}
	/**
	 * @param pmReprtTy the pmReprtTy to set
	 */
	public void setPmReprtTy(String pmReprtTy) {
		this.pmReprtTy = pmReprtTy;
	}
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
	 * @return the reprtStatsList
	 */
	public List<ReprtStatsVO> getReprtStatsList() {
		return reprtStatsList;
	}
	/**
	 * @param reprtStatsList the reprtStatsList to set
	 */
	public void setReprtStatsList(List<ReprtStatsVO> reprtStatsList) {
		this.reprtStatsList = reprtStatsList;
	}
	/**
	 * @return the reprtStatsDetailList
	 */
	public List<ReprtStatsVO> getReprtStatsDetailList() {
		return reprtStatsDetailList;
	}
	/**
	 * @param reprtStatsDetailList the reprtStatsDetailList to set
	 */
	public void setReprtStatsDetailList(List<ReprtStatsVO> reprtStatsDetailList) {
		this.reprtStatsDetailList = reprtStatsDetailList;
	}
	/**
	 * @return the reprtStatsBarList
	 */
	public List<ReprtStatsVO> getReprtStatsBarList() {
		return reprtStatsBarList;
	}
	/**
	 * @param reprtStatsBarList the reprtStatsBarList to set
	 */
	public void setReprtStatsBarList(List<ReprtStatsVO> reprtStatsBarList) {
		this.reprtStatsBarList = reprtStatsBarList;
	}
	/**
	 * @return the reprtStatsByReprtTyList
	 */
	public List<ReprtStatsVO> getReprtStatsByReprtTyList() {
		return reprtStatsByReprtTyList;
	}
	/**
	 * @param reprtStatsByReprtTyList the reprtStatsByReprtTyList to set
	 */
	public void setReprtStatsByReprtTyList(
			List<ReprtStatsVO> reprtStatsByReprtTyList) {
		this.reprtStatsByReprtTyList = reprtStatsByReprtTyList;
	}
	/**
	 * @return the reprtStatsByReprtSttusList
	 */
	public List<ReprtStatsVO> getReprtStatsByReprtSttusList() {
		return reprtStatsByReprtSttusList;
	}
	/**
	 * @param reprtStatsByReprtSttusList the reprtStatsByReprtSttusList to set
	 */
	public void setReprtStatsByReprtSttusList(
			List<ReprtStatsVO> reprtStatsByReprtSttusList) {
		this.reprtStatsByReprtSttusList = reprtStatsByReprtSttusList;
	}
	/**
	 * @return the delYn
	 */
	public String[] getDelYn() {
		return delYn;
	}
	/**
	 * @param delYn the delYn to set
	 */
	public void setDelYn(String[] delYn) {
		this.delYn = delYn;
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
