package egovframework.com.dam.mgm.service.impl;

import java.util.List;

import org.egovframe.rte.psl.dataaccess.mapper.EgovMapper;
import org.egovframe.rte.psl.dataaccess.util.EgovMap;

import egovframework.com.dam.mgm.service.KnoManagement;
import egovframework.com.dam.mgm.service.KnoManagementVO;

/**
 * 지식정보 관리를 위한 MyBatis 매퍼 인터페이스
 *
 * <pre>
 *  == 개정이력(Modification Information) ==
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2026.05.28  dasomel     @EgovMapper 인터페이스 방식으로 전환
 *
 * </pre>
 */
@EgovMapper
public interface KnoManagementMapper {

	/**
	 * 등록된 지식정보 목록을 조회한다.
	 * @param searchVO 지식정보 조회 조건 VO
	 * @return 지식정보 목록
	 */
	List<EgovMap> selectKnoManagementList(KnoManagementVO searchVO);

	/**
	 * 지식정보 목록 총 개수를 조회한다.
	 * @param searchVO 지식정보 조회 조건 VO
	 * @return 총 개수
	 */
	int selectKnoManagementTotCnt(KnoManagementVO searchVO);

	/**
	 * 지식정보 상세 정보를 조회한다.
	 * @param knoManagement 조회할 지식정보 식별 정보가 담긴 모델
	 * @return 지식정보 상세 모델
	 */
	KnoManagement selectKnoManagement(KnoManagement knoManagement);

	/**
	 * 지식정보 정보를 신규로 등록한다.
	 * @param knoManagement 등록할 지식정보 모델
	 */
	void insertKnoManagement(KnoManagement knoManagement);

	/**
	 * 기 등록된 지식정보 정보를 수정한다.
	 * @param knoManagement 수정할 지식정보 모델
	 */
	void updateKnoManagement(KnoManagement knoManagement);

	/**
	 * 기 등록된 지식정보 정보를 삭제한다.
	 * @param knoManagement 삭제할 지식정보 모델
	 */
	void deleteKnoManagement(KnoManagement knoManagement);

}
