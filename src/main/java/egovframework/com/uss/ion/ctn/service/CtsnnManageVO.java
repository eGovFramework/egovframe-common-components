package egovframework.com.uss.ion.ctn.service;

import java.io.Serializable;
import java.util.List;

/**
 * 개요
 * - 경조관리에 대한 Vo 클래스를 정의한다.
 * 
 * 상세내용
 * - 경조관리의 목록 항목을 관리한다.
 * @author 이용
 * @version 1.0
 * @created 06-15-2010 오후 2:08:56
 */

public class CtsnnManageVO extends CtsnnManage implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;	
	/**
	 * 배너 목록
	 */	
	List<CtsnnManageVO> ctsnnManageList;

	/**
	 * @return the annvrsryManageList
	 */
	public List<CtsnnManageVO> getCtsnnManageList() {
		return ctsnnManageList;
	}
	/**
	 * @param bannerList the bannerList to set
	 */
	public void setCtsnnManageList(List<CtsnnManageVO> ctsnnManageList) {
		this.ctsnnManageList = ctsnnManageList;
	}

	/**
	*  신청자명	      
	*/ 
	private String usNm;
	
	/**
	*  승인자명     
	*/ 
	private String sanctnerNm;
	
	/**
	*  경조코드명   
	*/ 
	private String ctsnnCdNm;

	/**
	*  사용자 소속명	      
	*/ 
	private String orgnztNm;
	
	/**
	*  승인자 소속명	      
	*/ 
	private String sanctnerOrgnztNm;

	/**
	*  검색시작일자      
	*/ 
	private String searchFromDate;
	
	/**
	*  검색종료일자
	*/ 
	private String searchToDate;
	
	/**
	*  검색 성명
	*/ 
	private String searchNm;
	
	/**
	*  검색 진행구분
	*/ 
	private String searchConfmAt;

	/**
	*  가족관계명
	*/ 
	private String relateNm;

	/**
	*  searchToDateView
	*/ 
	private String searchToDateView;
	
	/**
	*  searchFromDateView
	*/ 
	private String searchFromDateView;	
	

	/**
	 * @return the searchToDateView
	 */
	public String getSearchToDateView() {
		return searchToDateView;
	}
	/**
	 * @param searchToDateView the searchToDateView to set
	 */
	public void setSearchToDateView(String searchToDateView) {
		this.searchToDateView = searchToDateView;
	}
	/**
	 * @return the searchFromDateView
	 */
	public String getSearchFromDateView() {
		return searchFromDateView;
	}
	/**
	 * @param searchFromDateView the searchFromDateView to set
	 */
	public void setSearchFromDateView(String searchFromDateView) {
		this.searchFromDateView = searchFromDateView;
	}
	/**
	 * @return the usNm
	 */
	public String getUsNm() {
		return usNm;
	}
	/**
	 * @param usNm the usNm to set
	 */
	public void setUsNm(String usNm) {
		this.usNm = usNm;
	}
	/**
	 * @return the sanctnerNm
	 */
	public String getSanctnerNm() {
		return sanctnerNm;
	}
	/**
	 * @param sanctnerNm the sanctnerNm to set
	 */
	public void setSanctnerNm(String sanctnerNm) {
		this.sanctnerNm = sanctnerNm;
	}
	/**
	 * @return the ctsnnCdNm
	 */
	public String getCtsnnCdNm() {
		return ctsnnCdNm;
	}
	/**
	 * @param ctsnnCdNm the ctsnnCdNm to set
	 */
	public void setCtsnnCdNm(String ctsnnCdNm) {
		this.ctsnnCdNm = ctsnnCdNm;
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
	 * @return the sanctnerOrgnztNm
	 */
	public String getSanctnerOrgnztNm() {
		return sanctnerOrgnztNm;
	}
	/**
	 * @param sanctnerOrgnztNm the sanctnerOrgnztNm to set
	 */
	public void setSanctnerOrgnztNm(String sanctnerOrgnztNm) {
		this.sanctnerOrgnztNm = sanctnerOrgnztNm;
	}
	/**
	 * @return the searchFromDate
	 */
	public String getSearchFromDate() {
		return searchFromDate;
	}
	/**
	 * @param searchFromDate the searchFromDate to set
	 */
	public void setSearchFromDate(String searchFromDate) {
		this.searchFromDate = searchFromDate;
	}
	/**
	 * @return the searchToDate
	 */
	public String getSearchToDate() {
		return searchToDate;
	}
	/**
	 * @param searchToDate the searchToDate to set
	 */
	public void setSearchToDate(String searchToDate) {
		this.searchToDate = searchToDate;
	}
	/**
	 * @return the searchNm
	 */
	public String getSearchNm() {
		return searchNm;
	}
	/**
	 * @param searchNm the searchNm to set
	 */
	public void setSearchNm(String searchNm) {
		this.searchNm = searchNm;
	}
	/**
	 * @return the searchConfmAt
	 */
	public String getSearchConfmAt() {
		return searchConfmAt;
	}
	/**
	 * @param searchConfmAt the searchConfmAt to set
	 */
	public void setSearchConfmAt(String searchConfmAt) {
		this.searchConfmAt = searchConfmAt;
	}
	/**
	 * @return the relateNm
	 */
	public String getRelateNm() {
		return relateNm;
	}
	/**
	 * @param relateNm the relateNm to set
	 */
	public void setRelateNm(String relateNm) {
		this.relateNm = relateNm;
	}    
}
