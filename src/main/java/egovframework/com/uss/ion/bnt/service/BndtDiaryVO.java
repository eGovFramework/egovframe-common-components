package egovframework.com.uss.ion.bnt.service;

import java.io.Serializable;
import java.util.List;

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

public class BndtDiaryVO extends BndtDiary implements Serializable {

	private static final long serialVersionUID = 1767342530176012296L;
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

}
