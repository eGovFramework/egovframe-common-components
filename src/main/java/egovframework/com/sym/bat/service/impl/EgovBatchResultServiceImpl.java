package egovframework.com.sym.bat.service.impl;

import java.util.List;

import egovframework.com.sym.bat.service.BatchResult;
import egovframework.com.sym.bat.service.EgovBatchResultService;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

/**
 * 배치결과관리에 대한 ServiceImpl 클래스를 정의한다.
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
@Service("egovBatchResultService")
public class EgovBatchResultServiceImpl extends EgovAbstractServiceImpl implements EgovBatchResultService {

	/**
	 * 배치결과DAO
	 */
	@Resource(name = "batchResultDao")
	private BatchResultDao dao;

	/**
	 * 배치결과을 삭제한다.
	 * @param batchResult    삭제대상 배치결과model
	 * @exception Exception Exception
	 */
	@Override
	public void deleteBatchResult(BatchResult batchResult) throws Exception {
		dao.deleteBatchResult(batchResult);
	}

	/**
	 * 배치결과을 상세조회 한다.
	 * @return 배치결과정보
	 *
	 * @param batchResult 조회대상 배치결과model
	 * @exception Exception Exception
	 */
	@Override
	public BatchResult selectBatchResult(BatchResult batchResult) throws Exception {
		return dao.selectBatchResult(batchResult);
	}

	/**
	 * 배치결과의 목록을 조회 한다.
	 * @return 배치결과목록
	 *
	 * @param searchVO 	조회정보가 담긴 VO
	 * @exception Exception Exception
	 */
	@Override
	public List<?> selectBatchResultList(BatchResult searchVO) throws Exception {
		List<?> result = dao.selectBatchResultList(searchVO);
		return result;
	}

	/**
	 * 배치스케줄 목록 전체 건수를(을) 조회한다.
	 * @return 목록건수
	 *
	 * @param searchVO    조회할 정보가 담긴 VO
	 * @exception Exception Exception
	 */
	@Override
	public int selectBatchResultListCnt(BatchResult searchVO) throws Exception {
		int cnt = dao.selectBatchResultListCnt(searchVO);
		return cnt;
	}

}