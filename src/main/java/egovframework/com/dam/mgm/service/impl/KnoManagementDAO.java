package egovframework.com.dam.mgm.service.impl;

import java.util.List;

import org.egovframe.rte.psl.dataaccess.util.EgovMap;
import org.springframework.stereotype.Repository;

import egovframework.com.cmm.service.impl.EgovComAbstractDAO;
import egovframework.com.dam.mgm.service.KnoManagement;
import egovframework.com.dam.mgm.service.KnoManagementVO;

/**
 * 개요
 * - 지식정보에 대한 DAO 클래스를 정의한다.
 *
 * 상세내용
 * - 지식정보에 대한 등록, 수정, 삭제, 조회 기능을 제공한다.
 * - 지식정보의 조회기능은 목록조회, 상세조회로 구분된다.
 * @author 박종선
 * @version 1.0
 * @created 12-8-2010 오후 3:44:48
 */

@Repository("KnoManagementDAO")
public class KnoManagementDAO extends EgovComAbstractDAO {

	/**
	 * 등록된 지식정보 정보를 조회 한다.
	 * @param KnoManagementVO - 지식정보 VO
	 * @return String - 지식정보 VO
	 *
	 * @param KnoManagementVO
	 */
	public List<EgovMap> selectKnoManagementList(KnoManagementVO searchVO) throws Exception {
		return selectList("KnoManagementDAO.selectKnoManagementList", searchVO);
	}

	/**
	 * 지식정보 목록 총 개수를 조회한다.
	 * @param KnoManagementVO - 지식정보 Vo
	 * @return int - 지식정보 토탈 카운트 수
	 *
	 * @param KnoManagementVO
	 */
	public int selectKnoManagementTotCnt(KnoManagementVO searchVO) throws Exception {
		return selectOne("KnoManagementDAO.selectKnoManagementTotCnt", searchVO);
	}

	/**
	 * 지식정보 상세 정보를 조회 한다.
	 * @param KnoManagementVO - 지식정보 VO
	 * @return String - 지식정보 VO
	 *
	 * @param KnoManagementVO
	 */
	public KnoManagement selectKnoManagement(KnoManagement knoManagement) throws Exception {
		return selectOne("KnoManagementDAO.selectKnoManagement", knoManagement);
	}

	/**
	 * 지식정보 정보를 신규로 등록한다.
	 * @param KnoNm - 지식정보 model
	 *
	 * @param knoNm
	 */
	public void insertKnoManagement(KnoManagement knoManagement) throws Exception {
		insert("KnoManagementDAO.insertKnoManagement", knoManagement);
	}

	/**
	 * 기 등록 된 지식정보 정보를 수정 한다.
	 * @param ManagementKnoNm - 지식정보 model
	 *
	 * @param knoNm
	 */
	public void updateKnoManagement(KnoManagement knoManagement) throws Exception {
		update("KnoManagementDAO.updateKnoManagement", knoManagement);
	}

	/**
	 * 기 등록된 지식정보 정보를 삭제한다.
	 * @param ManagementKnoNm - 지식정보 model
	 *
	 * @param knoNm
	 */
	public void deleteKnoManagement(KnoManagement knoManagement) throws Exception {
		delete("KnoManagementDAO.deleteKnoManagement", knoManagement);
	}

}