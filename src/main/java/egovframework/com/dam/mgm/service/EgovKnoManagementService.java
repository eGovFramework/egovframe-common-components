package egovframework.com.dam.mgm.service;

import java.util.List;

/**
 * 개요
 * - 지식정보에 대한 Service Interface를 정의한다.
 *
 * 상세내용
 * - 지식정보에 대한 등록, 수정, 삭제, 조회 기능을 제공한다.
 * - 지식정보의 조회기능은 목록조회, 상세조회로 구분된다.
 * @author 박종선
 * @version 1.0
 * @created 12-8-2010 오후 3:44:38
 */

public interface EgovKnoManagementService {

	/**
	 * 등록된 지식정보 정보를 조회 한다.
	 * @param KnoManagementVO - 지식정보 VO
	 * @return String - 지식정보 VO
	 *
	 * @param KnoManagementVO
	 */
	List<?> selectKnoManagementList(KnoManagementVO searchVO) throws Exception;

	/**
	 * 지식정보 목록 총 갯수를 조회한다.
	 * @param KnoManagementVO - 지식정보 Vo
	 * @return int - 지식정보 토탈 카운트 수
	 *
	 * @param KnoManagementVO
	 */
	int selectKnoManagementTotCnt(KnoManagementVO searchVO) throws Exception;

	/**
	 * 지식정보 상세 정보를 조회 한다.
	 * @param KnoManagementVO - 지식정보 VO
	 * @return String - 지식정보 VO
	 *
	 * @param KnoManagementVO
	 */
	KnoManagement selectKnoManagement(KnoManagement knoManagement) throws Exception;

	/**
	 * 지식정보 정보를 신규로 등록한다.
	 * @param KnoNm - 지식정보 model
	 *
	 * @param knoNm
	 */
	void insertKnoManagement(KnoManagement knoManagement) throws Exception;

	/**
	 * 기 등록 된 지식정보 정보를 수정 한다.
	 * @param ManagementKnoNm - 지식정보 model
	 *
	 * @param knoNm
	 */
	void updateKnoManagement(KnoManagement knoManagement) throws Exception;

	/**
	 * 기 등록된 지식정보 정보를 삭제한다.
	 * @param ManagementKnoNm - 지식정보 model
	 *
	 * @param knoNm
	 */
	void deleteKnoManagement(KnoManagement knoManagement) throws Exception;

}