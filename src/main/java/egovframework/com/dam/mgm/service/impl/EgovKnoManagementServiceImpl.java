package egovframework.com.dam.mgm.service.impl;

import java.util.List;

import egovframework.com.dam.mgm.service.EgovKnoManagementService;
import egovframework.com.dam.mgm.service.KnoManagement;
import egovframework.com.dam.mgm.service.KnoManagementVO;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

/**
 * 개요
 * - 지식정보에 대한 ServiceImpl 클래스를 정의한다.
 *
 * 상세내용
 * - 지식정보에 대한 등록, 수정, 삭제, 조회 기능을 제공한다.
 * - 지식정보의 조회기능은 목록조회, 상세조회로 구분된다.
 * @author 박종선
 * @version 1.0
 * @created 12-8-2010 오후 3:44:39
 */

@Service("KnoManagementService")
public class EgovKnoManagementServiceImpl extends EgovAbstractServiceImpl implements EgovKnoManagementService {

	@Resource(name="KnoManagementDAO")
	private KnoManagementDAO KnoManagementDAO;

	/**
	 * 등록된 지식정보 정보를 조회 한다.
	 * @param KnoManagementVO - 지식정보 VO
	 * @return String - 지식정보 VO
	 *
	 * @param KnoManagementVO
	 */
	@Override
	public List<?> selectKnoManagementList(KnoManagementVO searchVO) throws Exception {
		return KnoManagementDAO.selectKnoManagementList(searchVO);
	}

	/**
	 * 지식정보 목록 총 갯수를 조회한다.
	 * @param KnoManagementVO - 지식정보 Vo
	 * @return int - 지식정보 토탈 카운트 수
	 *
	 * @param KnoManagementVO
	 */
	@Override
	public int selectKnoManagementTotCnt(KnoManagementVO searchVO) throws Exception {
		return KnoManagementDAO.selectKnoManagementTotCnt(searchVO);
	}

	/**
	 * 지식정보 상세 정보를 조회 한다.
	 * @param KnoManagementVO - 지식정보 VO
	 * @return String - 지식정보 VO
	 *
	 * @param KnoManagementVO
	 */
	@Override
	public KnoManagement selectKnoManagement(KnoManagement knoManagement) throws Exception {
		KnoManagement kmt = KnoManagementDAO.selectKnoManagement(knoManagement);
		return kmt;
	}

	/**
	 * 지식정보 정보를 신규로 등록한다.
	 * @param KnoNm - 지식정보 model
	 *
	 * @param knoNm
	 */
	@Override
	public void insertKnoManagement(KnoManagement knoManagement) throws Exception {
		KnoManagementDAO.insertKnoManagement(knoManagement);
	}

	/**
	 * 기 등록 된 지식정보 정보를 수정 한다.
	 * @param ManagementKnoNm - 지식정보 model
	 *
	 * @param knoNm
	 */
	@Override
	public void updateKnoManagement(KnoManagement knoManagement) throws Exception {
		KnoManagementDAO.updateKnoManagement(knoManagement);
	}

	/**
	 * 기 등록된 지식정보 정보를 삭제한다.
	 * @param ManagementKnoNm - 지식정보 model
	 *
	 * @param knoNm
	 */
	@Override
	public void deleteKnoManagement(KnoManagement knoManagement) throws Exception {
		KnoManagementDAO.deleteKnoManagement(knoManagement);
	}

}