package egovframework.com.sym.bat.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.com.cmm.service.impl.EgovComAbstractDAO;
import egovframework.com.sym.bat.service.BatchResult;

/**
 * 배치결과관리에 대한 DAO 클래스를 정의한다.
 *
 * @author 김진만
 * @since 2010.06.17
 * @version 1.0
 * @updated 17-6-2010 오전 10:27:13
 * @see
 * <pre>
 * == 개정이력(Modification Information) ==
 *
 *   수정일       수정자           수정내용
 *  -------     --------    ---------------------------
 *  2010.06.17   김진만     최초 생성
 * </pre>
 */
@Repository("batchResultDao")
public class BatchResultDao extends EgovComAbstractDAO {

	/**
	 * 배치결과을 삭제한다.
	 *
	 * @param batchResult    삭제할 배치결과 VO
	 * @exception Exception Exception
	 */
	public void deleteBatchResult(BatchResult batchResult) throws Exception {
		delete("BatchResultDao.deleteBatchResult", batchResult);
	}

	/**
	 * 배치결과을 등록한다.
	 *
	 * @param batchResult 저장할 배치결과 VO
	 * @exception Exception Exception
	 */
	public void insertBatchResult(BatchResult batchResult) throws Exception {
		insert("BatchResultDao.insertBatchResult", batchResult);
	}

	/**
	 * 배치결과정보를 상세조회 한다.
	 * @return 배치결과정보
	 *
	 * @param batchResult    조회할 KEY가 있는 배치결과 VO
	 * @exception Exception Exception
	 */
	public BatchResult selectBatchResult(BatchResult batchResult) throws Exception {
		return (BatchResult) selectOne("BatchResultDao.selectBatchResult", batchResult);
	}

	/**
	 * 배치결과정보목록을  조회한다.
	 * @return 배치결과목록
	 *
	 * @param searchVO    조회조건이 저장된 VO
	 * @exception Exception Exception
	 */
	public List<BatchResult> selectBatchResultList(BatchResult searchVO) throws Exception {
		return selectList("BatchResultDao.selectBatchResultList", searchVO);
	}

	/**
	 * 배치결과 목록 전체 건수를(을) 조회한다.
	 * @return 목록건수
	 *
	 * @param searchVO    조회할 정보가 담긴 VO
	 * @exception Exception Exception
	 */
	public int selectBatchResultListCnt(BatchResult searchVO) throws Exception {
		return (Integer) selectOne("BatchResultDao.selectBatchResultListCnt", searchVO);
	}

	/**
	 * 배치결과정보를 수정한다.
	 *
	 * @param batchResult    수정대상 배치결과 VO
	 * @exception Exception Exception
	 */
	public void updateBatchResult(BatchResult batchResult) throws Exception {
		update("BatchResultDao.updateBatchResult", batchResult);
	}

}