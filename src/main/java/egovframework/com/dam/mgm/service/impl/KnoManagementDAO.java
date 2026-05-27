package egovframework.com.dam.mgm.service.impl;

import java.util.List;

import org.egovframe.rte.psl.dataaccess.util.EgovMap;
import org.springframework.stereotype.Repository;

import egovframework.com.dam.mgm.service.KnoManagement;
import egovframework.com.dam.mgm.service.KnoManagementVO;

import jakarta.annotation.Resource;

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
public class KnoManagementDAO {

	@Resource(name = "knoManagementMapper")
	private KnoManagementMapper knoManagementMapper;

	/**
	 * 등록된 지식정보 목록을 조회한다.
	 * @param searchVO 지식정보 조회 조건 VO
	 * @return 지식정보 목록(List<EgovMap>)
	 */
	public List<EgovMap> selectKnoManagementList(KnoManagementVO searchVO) {
		return knoManagementMapper.selectKnoManagementList(searchVO);
	}

	/**
	 * 지식정보 목록 총 개수를 조회한다.
	 * @param searchVO 지식정보 조회 조건 VO
	 * @return 총 개수
	 */
	public int selectKnoManagementTotCnt(KnoManagementVO searchVO) {
		return knoManagementMapper.selectKnoManagementTotCnt(searchVO);
	}

	/**
	 * 지식정보 상세 정보를 조회한다.
	 * @param knoManagement 조회할 지식정보 식별 정보가 담긴 모델
	 * @return 지식정보 상세 모델
	 */
	public KnoManagement selectKnoManagement(KnoManagement knoManagement) {
		return knoManagementMapper.selectKnoManagement(knoManagement);
	}

	/**
	 * 지식정보 정보를 신규로 등록한다.
	 * @param knoManagement 등록할 지식정보 모델
	 */
	public void insertKnoManagement(KnoManagement knoManagement) {
		knoManagementMapper.insertKnoManagement(knoManagement);
	}

	/**
	 * 기 등록된 지식정보 정보를 수정한다.
	 * @param knoManagement 수정할 지식정보 모델
	 */
	public void updateKnoManagement(KnoManagement knoManagement) {
		knoManagementMapper.updateKnoManagement(knoManagement);
	}

	/**
	 * 기 등록된 지식정보 정보를 삭제한다.
	 * @param knoManagement 삭제할 지식정보 모델
	 */
	public void deleteKnoManagement(KnoManagement knoManagement) {
		knoManagementMapper.deleteKnoManagement(knoManagement);
	}

}
