package egovframework.com.dam.app.service.impl;

import java.util.List;

import org.egovframe.rte.psl.dataaccess.mapper.EgovMapper;
import org.egovframe.rte.psl.dataaccess.util.EgovMap;

import egovframework.com.dam.app.service.KnoAppraisal;
import egovframework.com.dam.app.service.KnoAppraisalVO;

/**
 * 지식정보평가 관리를 위한 MyBatis 매퍼 인터페이스
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
public interface KnoAppraisalMapper {

	/**
	 * 등록된 지식정보평가 목록을 조회한다.
	 * @param searchVO 지식정보평가 조회 조건 VO
	 * @return 지식정보평가 목록
	 */
	List<EgovMap> selectKnoAppraisalList(KnoAppraisalVO searchVO);

	/**
	 * 지식정보평가 목록 총 개수를 조회한다.
	 * @param searchVO 지식정보평가 조회 조건 VO
	 * @return 총 개수
	 */
	int selectKnoAppraisalTotCnt(KnoAppraisalVO searchVO);

	/**
	 * 지식정보평가 상세 정보를 조회한다.
	 * @param knoAppraisal 조회할 지식정보평가 식별 정보가 담긴 모델
	 * @return 지식정보평가 상세 모델
	 */
	KnoAppraisal selectKnoAppraisal(KnoAppraisal knoAppraisal);

	/**
	 * 지식정보평가 정보를 신규로 등록한다.
	 * @param knoAppraisal 등록할 지식정보평가 모델
	 */
	void insertKnoAppraisal(KnoAppraisal knoAppraisal);

	/**
	 * 기 등록된 지식정보평가 정보를 수정한다.
	 * @param knoAppraisal 수정할 지식정보평가 모델
	 */
	void updateKnoAppraisal(KnoAppraisal knoAppraisal);

	/**
	 * 기 등록된 지식정보평가 정보를 삭제한다.
	 * @param knoAppraisal 삭제할 지식정보평가 모델
	 */
	void deleteKnoAppraisal(KnoAppraisal knoAppraisal);

}
