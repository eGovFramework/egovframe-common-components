package egovframework.com.sym.bat.service.impl;

import java.util.List;

import egovframework.com.sym.bat.service.BatchOpert;
import egovframework.com.sym.bat.service.EgovBatchOpertService;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

/**
 * 배치작업관리에 대한 ServiceImpl 클래스를 정의한다.
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
@Service("egovBatchOpertService")
public class EgovBatchOpertServiceImpl extends EgovAbstractServiceImpl implements EgovBatchOpertService {

	/**
	 * 배치작업DAO
	 */
	@Resource(name = "batchOpertDao")
	private BatchOpertDao dao;

	/**
	 * 배치작업을 삭제한다.
	 * @param batchOpert    삭제대상 배치작업model
	 * @exception Exception Exception
	 */
	@Override
	public void deleteBatchOpert(BatchOpert batchOpert) throws Exception {
		dao.deleteBatchOpert(batchOpert);
	}

	/**
	 * 배치작업을 등록한다.
	 * @param batchOpert    등록대상 배치작업model
	 * @exception Exception Exception
	 */
	@Override
	public void insertBatchOpert(BatchOpert batchOpert) throws Exception {
		dao.insertBatchOpert(batchOpert);
	}

	/**
	 * 배치작업을 상세조회 한다.
	 * @return 배치작업정보
	 *
	 * @param batchOpert 조회대상 배치작업model
	 * @exception Exception Exception
	 */
	@Override
	public BatchOpert selectBatchOpert(BatchOpert batchOpert) throws Exception {
		return dao.selectBatchOpert(batchOpert);
	}

	/**
	 * 배치작업의 목록을 조회 한다.
	 * @return 배치작업목록
	 *
	 * @param searchVO 	조회정보가 담긴 VO
	 * @exception Exception Exception
	 */
	@Override
	public List<?> selectBatchOpertList(BatchOpert searchVO) throws Exception {
		List<?> result = dao.selectBatchOpertList(searchVO);
		return result;
	}

	/**
	 * 배치작업 목록 전체 건수를(을) 조회한다.
	 * @return 목록건수
	 *
	 * @param searchVO    조회할 정보가 담긴 VO
	 * @exception Exception Exception
	 */
	@Override
	public int selectBatchOpertListCnt(BatchOpert searchVO) throws Exception {
		int cnt = dao.selectBatchOpertListCnt(searchVO);
		return cnt;
	}

	/**
	 * 배치작업정보를 수정한다.
	 *
	 * @param batchOpert    수정대상 배치작업model
	 * @exception Exception Exception
	 */
	@Override
	public void updateBatchOpert(BatchOpert batchOpert) throws Exception {
		dao.updateBatchOpert(batchOpert);
	}

}