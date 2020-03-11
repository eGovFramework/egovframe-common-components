package egovframework.com.uss.ion.rwd.service;

import java.io.Serializable;
import java.util.List;

/**
 * 개요
 * - 포상관리에 대한 Vo 클래스를 정의한다.
 *
 * 상세내용
 * - 포상관리의 목록 항목을 관리한다.
 * @author 이용
 * @version 1.0
 * @created 06-15-2010 오후 2:08:56
 */

public class RwardManageVO extends RwardManage implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 포상자 목록
	 */
	List<RwardManageVO> rwardManageList;

	/**
	 * @return the rwardManageList
	 */
	public List<RwardManageVO> getRwardManageList() {
		return rwardManageList;
	}
	/**
	 * @param RwardManage the rwardManage to set
	 */
	public void setRwardManageList(List<RwardManageVO> rwardManageList) {
		this.rwardManageList = rwardManageList;
	}

	/**
	*  포상자명
	*/
	private String rwardManNm;

	/**
	*  승인자명
	*/
	private String sanctnerNm;

	/**
	*  포상코드명
	*/
	private String rwardCdNm;

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
	 * @return the rwardManNm
	 */
	public String getRwardManNm() {
		return rwardManNm;
	}
	/**
	 * @param rwardManNm the rwardManNm to set
	 */
	public void setRwardManNm(String rwardManNm) {
		this.rwardManNm = rwardManNm;
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
	 * @return the rwardCdNm
	 */
	public String getRwardCdNm() {
		return rwardCdNm;
	}
	/**
	 * @param rwardCdNm the rwardCdNm to set
	 */
	public void setRwardCdNm(String rwardCdNm) {
		this.rwardCdNm = rwardCdNm;
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
}
