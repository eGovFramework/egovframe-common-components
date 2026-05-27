package egovframework.com.sym.bat.service.impl.mapper;

import java.util.List;

import org.egovframe.rte.psl.dataaccess.mapper.EgovMapper;

import egovframework.com.sym.bat.service.BatchResult;

/**
 * 배치결과관리에 대한 Mapper 인터페이스를 정의한다.
 *
 * @author 김진만
 * @since 2010.06.17
 * @version 2.0
 * @see
 * <pre>
 * == 개정이력(Modification Information) ==
 *
 *   수정일       수정자           수정내용
 *  -------     --------    ---------------------------
 *  2010.06.17   김진만     최초 생성
 *  2025.05.28              @EgovMapper 인터페이스 방식으로 추가
 * </pre>
 */
@EgovMapper("batchResultMapper")
public interface BatchResultMapper {

	/**
	 * 배치결과를 삭제한다.
	 *
	 * @param batchResult 삭제할 배치결과 VO
	 */
	void deleteBatchResult(BatchResult batchResult);

	/**
	 * 배치결과를 등록한다.
	 *
	 * @param batchResult 저장할 배치결과 VO
	 */
	void insertBatchResult(BatchResult batchResult);

	/**
	 * 배치결과정보를 상세조회 한다.
	 *
	 * @param batchResult 조회할 KEY가 있는 배치결과 VO
	 * @return 배치결과정보
	 */
	BatchResult selectBatchResult(BatchResult batchResult);

	/**
	 * 배치결과정보 목록을 조회한다.
	 *
	 * @param searchVO 조회조건이 저장된 VO
	 * @return 배치결과목록
	 */
	List<BatchResult> selectBatchResultList(BatchResult searchVO);

	/**
	 * 배치결과 목록 전체 건수를 조회한다.
	 *
	 * @param searchVO 조회할 정보가 담긴 VO
	 * @return 목록건수
	 */
	int selectBatchResultListCnt(BatchResult searchVO);

	/**
	 * 배치결과정보를 수정한다.
	 *
	 * @param batchResult 수정대상 배치결과 VO
	 */
	void updateBatchResult(BatchResult batchResult);

}
