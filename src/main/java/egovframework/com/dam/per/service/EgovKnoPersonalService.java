package egovframework.com.dam.per.service;

import java.util.List;

/**
 * 개요
 * - 개인지식정보에 대한 Service Interface를 정의한다.
 *
 * 상세내용
 * - 개인지식정보에 대한 등록, 수정, 삭제, 조회 기능을 제공한다.
 * - 개인지식정보의 조회기능은 목록조회, 상세조회로 구분된다.
 * @author 박종선
 * @version 1.0
 * @created 12-8-2010 오후 3:44:40
 */

public interface EgovKnoPersonalService {

	/**
	 * 등록된 개인지식 정보를 조회 한다.
	 * @param KnoPersonalVO - 개인지식 VO
	 * @return String - 개인지식정보 목록
	 *
	 * @param KnoPersonalVO
	 */
	List<?> selectKnoPersonalList(KnoPersonalVO searchVO) throws Exception;

	/**
	 * 개인지식 목록 총 갯수를 조회한다.
	 * @param KnoPersonalVO - 개인지식 Vo
	 * @return int - 개인지식 토탈 카운트 수
	 *
	 * @param KnoPersonalVO
	 */
	int selectKnoPersonalTotCnt(KnoPersonalVO searchVO) throws Exception;

	/**
	 * 개인지식정보 상세 정보를 조회 한다.
	 * @param KnoPersonalVO - 개인지식정보 VO
	 * @return String - 개인지식 VO
	 *
	 * @param KnoPersonalVO
	 */
	KnoPersonal selectKnoPersonal(KnoPersonal knoPersonal) throws Exception;

	/**
	 * 개인지식 정보를 신규로 등록한다.
	 * @param KnoNm - 개인지식정보 model
	 *
	 * @param KnoNm
	 */
	void insertKnoPersonal(KnoPersonal knoPersonal) throws Exception;

	/**
	 * 기 등록 된 개인지식 정보를 수정 한다.
	 * @param KnoNm - 개인지식정보 model
	 *
	 * @param KnoNm
	 */
	void updateKnoPersonal(KnoPersonal knoPersonal) throws Exception;

	/**
	 * 기 등록된 개인지식 정보를 삭제한다.
	 * @param KnoNm - 개인지식정보 model
	 *
	 * @param KnoNm
	 */
	void deleteKnoPersonal(KnoPersonal knoPersonal) throws Exception;

}