package egovframework.com.uss.ion.mtg.service;

import java.io.Serializable;
import java.util.List;

/**
 * 개요
 * - 회의실예약에 대한 Vo 클래스를 정의한다.
 *
 * 상세내용
 * - 회의실예약의 목록 항목을 관리한다.
 * @author 이용
 * @version 1.0
 * @created 06-15-2010 오후 2:08:56
 */

public class MtgPlaceResveVO extends MtgPlaceResve implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 예약 목록
	 */
	List<MtgPlaceResveVO> mtgPlaceResveList;

	/**
	 * @return the mtgPlaceResveList
	 */
	public List<MtgPlaceResveVO> getMtgPlaceResveList() {
		return mtgPlaceResveList;
	}
	/**
	 * @param MtgPlaceResve the mtgPlaceResve to set
	 */
	public void setMtgPlaceResveList(List<MtgPlaceResveVO> mtgPlaceResveList) {
		this.mtgPlaceResveList = mtgPlaceResveList;
	}



}
