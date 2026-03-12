package egovframework.com.uss.ion.bnt.service;

import java.util.List;

import org.egovframe.rte.ptl.reactive.validation.EgovNullCheck;

import egovframework.com.cmm.ComDefaultVO;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

/**
 * 개요
 * - 당직일지에 대한 Vo 클래스를 정의한다.
 *
 * 상세내용
 * - 당직일지의 목록 항목을 관리한다.
 * @author 이용
 * @version 1.0
 * @created 06-15-2010 오후 2:08:56
 */

public class BndtDiaryVO extends ComDefaultVO {

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
	@Size(max=8)
	@Pattern(regexp="^\\d{8}$", message="{validation.pattern.date}")
	private String bndtDe;

	/**
	*  당직체크구분
	*/
	private String bndtCeckSe;

	/**
	*  당직체크코드
	*/
	private String bndtCeckCd;

	/**
	*  당직점검상태
	*/
	private String chckSttus;

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
	List<BndtDiaryVO> bndtDiaryList;

	/**
	 * @return the bndtDiaryList
	 */
	public List<BndtDiaryVO> getBndtDiaryList() {
		return bndtDiaryList;
	}
	/**
	 * @param bannerList the bannerList to set
	 */
	public void setBndtDiaryList(List<BndtDiaryVO> bndtDiaryList) {
		this.bndtDiaryList = bndtDiaryList;
	}

	/**
	*  당직체크코드명
	*/
	private String bndtCeckCdNm;

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
	 * @return the chckSttus
	 */
	public String getChckSttus() {
		return chckSttus;
	}

	/**
	 * @param chckSttus the chckSttus to set
	 */
	public void setChckSttus(String chckSttus) {
		this.chckSttus = chckSttus;
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
