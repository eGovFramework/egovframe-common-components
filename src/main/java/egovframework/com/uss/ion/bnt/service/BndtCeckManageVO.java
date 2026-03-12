package egovframework.com.uss.ion.bnt.service;

import java.util.List;

import org.egovframe.rte.ptl.reactive.validation.EgovNullCheck;

import egovframework.com.cmm.ComDefaultVO;
import jakarta.validation.constraints.Size;

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

public class BndtCeckManageVO extends ComDefaultVO {

	private static final long serialVersionUID = 1L;

	/**
	*  당직체크구분
	*/
	@EgovNullCheck
	@Size(max=10)
	private String bndtCeckSe;

	/**
	*  당직체크코드
	*/
	@EgovNullCheck
	@Size(max=10)
	private String bndtCeckCd;

	/**
	*  당직체크코드명
	*/
	@EgovNullCheck
	@Size(max=100)
	private String bndtCeckCdNm;

	/**
	*  사용여부
	*/
	@EgovNullCheck
	private String useAt;

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

	/**
	 * @return the bndtCeckSe
	 */
	public String getBndtCeckSe() {
		return bndtCeckSe;
	}

	/**
	 * @param bndtCeckSe the bndtCeckSe to set
	 */
	public void setBndtCeckSe(String bndtCeckSe) {
		this.bndtCeckSe = bndtCeckSe;
	}

	/**
	 * @return the bndtCeckCd
	 */
	public String getBndtCeckCd() {
		return bndtCeckCd;
	}

	/**
	 * @param bndtCeckCd the bndtCeckCd to set
	 */
	public void setBndtCeckCd(String bndtCeckCd) {
		this.bndtCeckCd = bndtCeckCd;
	}

	/**
	 * @return the bndtCeckCdNm
	 */
	public String getBndtCeckCdNm() {
		return bndtCeckCdNm;
	}

	/**
	 * @param bndtCeckCdNm the bndtCeckCdNm to set
	 */
	public void setBndtCeckCdNm(String bndtCeckCdNm) {
		this.bndtCeckCdNm = bndtCeckCdNm;
	}

	/**
	 * @return the useAt
	 */
	public String getUseAt() {
		return useAt;
	}

	/**
	 * @param useAt the useAt to set
	 */
	public void setUseAt(String useAt) {
		this.useAt = useAt;
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
