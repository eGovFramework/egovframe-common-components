package egovframework.com.sym.bat.service.impl;

import java.util.List;

import egovframework.com.sym.bat.service.BatchResult;
import egovframework.com.sym.bat.service.BatchSchdul;
import egovframework.com.sym.bat.service.EgovBatchSchdulService;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

/**
 * 배치스케줄관리에 대한 ServiceImpl 클래스를 정의한다.
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
@Service("egovBatchSchdulService")
public class EgovBatchSchdulServiceImpl extends EgovAbstractServiceImpl implements EgovBatchSchdulService {

	/**
	 * 배치스케줄DAO
	 */
	@Resource(name = "batchSchdulDao")
	private BatchSchdulDao batchSchdulDao;

	/**
	 * 배치결과DAO
	 */
	@Resource(name = "batchResultDao")
	private BatchResultDao batchResultDao;

	/**
	 * 배치스케줄을 삭제한다.
	 * @param batchSchdul    삭제대상 배치스케줄model
	 * @exception Exception Exception
	 */
	@Override
	public void deleteBatchSchdul(BatchSchdul batchSchdul) throws Exception {
		batchSchdulDao.deleteBatchSchdul(batchSchdul);
	}

	/**
	 * 배치스케줄을 등록한다.
	 * @param batchSchdul    등록대상 배치스케줄model
	 * @exception Exception Exception
	 */
	@Override
	public void insertBatchSchdul(BatchSchdul batchSchdul) throws Exception {
		batchSchdulDao.insertBatchSchdul(batchSchdul);
	}

	/**
	 * 배치스케줄을 상세조회 한다.
	 * @return 배치스케줄정보
	 *
	 * @param batchSchdul 조회대상 배치스케줄model
	 * @exception Exception Exception
	 */
	@Override
	public BatchSchdul selectBatchSchdul(BatchSchdul batchSchdul) throws Exception {
		return batchSchdulDao.selectBatchSchdul(batchSchdul);
	}

	/**
	 * 배치스케줄의 목록을 조회 한다.
	 * @return 배치스케줄목록
	 *
	 * @param searchVO 	조회정보가 담긴 VO
	 * @exception Exception Exception
	 */
	@Override
	public List<?> selectBatchSchdulList(BatchSchdul searchVO) throws Exception {
		List<?> result = batchSchdulDao.selectBatchSchdulList(searchVO);
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
	public int selectBatchSchdulListCnt(BatchSchdul searchVO) throws Exception {
		int cnt = batchSchdulDao.selectBatchSchdulListCnt(searchVO);
		return cnt;
	}

	/**
	 * 배치스케줄정보를 수정한다.
	 *
	 * @param batchSchdul    수정대상 배치스케줄model
	 * @exception Exception Exception
	 */
	@Override
	public void updateBatchSchdul(BatchSchdul batchSchdul) throws Exception {
		batchSchdulDao.updateBatchSchdul(batchSchdul);
	}

	/**
	 * 배치결과를 등록한다.
	 * @param batchResult    등록대상 배치결과model
	 * @exception Exception Exception
	 */
	@Override
	public void insertBatchResult(BatchResult batchResult) throws Exception {
		batchResultDao.insertBatchResult(batchResult);
	}

	/**
	 * 배치결과정보를 수정한다.
	 *
	 * @param batchResult    수정대상 배치결과model
	 * @exception Exception Exception
	 */
	@Override
	public void updateBatchResult(BatchResult batchResult) throws Exception {
		batchResultDao.updateBatchResult(batchResult);
	}

}