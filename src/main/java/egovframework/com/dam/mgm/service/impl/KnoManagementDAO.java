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
	 * 등록된 지식정보 목록을 조회한다.
	 * @param searchVO 지식정보 조회 조건 VO
	 * @return 지식정보 목록(List<EgovMap>)
	 * @throws Exception 데이터 접근 중 오류가 발생한 경우
	 */
	public List<EgovMap> selectKnoManagementList(KnoManagementVO searchVO) throws Exception {
		return selectList("KnoManagementDAO.selectKnoManagementList", searchVO);
	}

	/**
	 * 지식정보 목록 총 개수를 조회한다.
	 * @param searchVO 지식정보 조회 조건 VO
	 * @return 총 개수
	 * @throws Exception 데이터 접근 중 오류가 발생한 경우
	 */
	public int selectKnoManagementTotCnt(KnoManagementVO searchVO) throws Exception {
		return selectOne("KnoManagementDAO.selectKnoManagementTotCnt", searchVO);
	}

	/**
	 * 지식정보 상세 정보를 조회한다.
	 * @param knoManagement 조회할 지식정보 식별 정보가 담긴 모델
	 * @return 지식정보 상세 모델
	 * @throws Exception 식별자가 없거나 해당 지식정보가 존재하지 않거나 데이터 접근 오류가 발생한 경우
	 */
	public KnoManagement selectKnoManagement(KnoManagement knoManagement) throws Exception {
		return selectOne("KnoManagementDAO.selectKnoManagement", knoManagement);
	}

	/**
	 * 지식정보 정보를 신규로 등록한다.
	 * @param knoManagement 등록할 지식정보 모델
	 * @throws Exception 데이터 접근 중 오류가 발생한 경우
	 */
	public void insertKnoManagement(KnoManagement knoManagement) throws Exception {
		insert("KnoManagementDAO.insertKnoManagement", knoManagement);
	}

	/**
	 * 기 등록된 지식정보 정보를 수정한다.
	 * @param knoManagement 수정할 지식정보 모델
	 * @throws Exception 대상이 존재하지 않거나 데이터 접근 중 오류가 발생한 경우
	 */
	public void updateKnoManagement(KnoManagement knoManagement) throws Exception {
		update("KnoManagementDAO.updateKnoManagement", knoManagement);
	}

	/**
	 * 기 등록된 지식정보 정보를 삭제한다.
	 * @param knoManagement 삭제할 지식정보 모델
	 * @throws Exception 대상이 존재하지 않거나 데이터 접근 중 오류가 발생한 경우
	 */
	public void deleteKnoManagement(KnoManagement knoManagement) throws Exception {
		delete("KnoManagementDAO.deleteKnoManagement", knoManagement);
	}

}