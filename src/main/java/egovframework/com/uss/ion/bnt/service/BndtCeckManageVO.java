package egovframework.com.uss.ion.bnt.service;

import java.io.Serializable;
import java.util.List;

/**
 * 개요
 * - 당직체크관리에 대한 Vo 클래스를 정의한다.
 *
 * 상세내용
 * - 당직체크관리의 목록 항목을 관리한다.
 * @author 이용
 * @version 1.0
 * @created 06-15-2010 오후 2:08:56
 */

public class BndtCeckManageVO extends BndtCeckManage implements Serializable {

	private static final long serialVersionUID = -9114350207789216858L;

	/**
	 * 당직체크리스트관리 목록
	 */
	List<BndtCeckManageVO> bndtCeckManageList;

	/**
	 * 당직체크리스트 Temp변수 1
	 */
	private String bndtCeckTemp1;

	/**
	 * 당직체크리스트 당직체크구분 조회조건 변수
	 */
	private String searchBndtCeckSe;

	/**
	 * 당직체크리스트 당직체크코드 조회조건 변수
	 */
	private String searchBndtCeckCd;

	/**
	 * 당직체크리스트 당직체크구분 조회조건 변수
	 */
	private String searchUseAt;

	/**
	 * @return the searchUseAt
	 */
	public String getSearchUseAt() {
		return searchUseAt;
	}
	/**
	 * @param searchUseAt the searchUseAt to set
	 */
	public void setSearchUseAt(String searchUseAt) {
		this.searchUseAt = searchUseAt;
	}
	/**
	 * @return the bndtCeckManageList
	 */
	public List<BndtCeckManageVO> getBndtCeckManageList() {
		return bndtCeckManageList;
	}
	/**
	 * @param bndtCeckManageList the bndtCeckManageList to set
	 */
	public void setBndtCeckManageList(List<BndtCeckManageVO> bndtCeckManageList) {
		this.bndtCeckManageList = bndtCeckManageList;
	}

	/**
	 * @return the bndtCeckTemp1
	 */
	public String getBndtCeckTemp1() {
		return bndtCeckTemp1;
	}
	/**
	 * @param bndtCeckTemp1 the bndtCeckTemp1 to set
	 */
	public void setBndtCeckTemp1(String bndtCeckTemp1) {
		this.bndtCeckTemp1 = bndtCeckTemp1;
	}
	/**
	 * @return the searchBndtCeckSe
	 */
	public String getSearchBndtCeckSe() {
		return searchBndtCeckSe;
	}
	/**
	 * @param searchBndtCeckSe the searchBndtCeckSe to set
	 */
	public void setSearchBndtCeckSe(String searchBndtCeckSe) {
		this.searchBndtCeckSe = searchBndtCeckSe;
	}
	/**
	 * @return the searchBndtCeckCd
	 */
	public String getSearchBndtCeckCd() {
		return searchBndtCeckCd;
	}
	/**
	 * @param searchBndtCeckCd the searchBndtCeckCd to set
	 */
	public void setSearchBndtCeckCd(String searchBndtCeckCd) {
		this.searchBndtCeckCd = searchBndtCeckCd;
	}



}
