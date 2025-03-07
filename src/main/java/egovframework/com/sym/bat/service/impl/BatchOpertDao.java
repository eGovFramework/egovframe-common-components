package egovframework.com.sym.bat.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.com.cmm.service.impl.EgovComAbstractDAO;
import egovframework.com.sym.bat.service.BatchOpert;

/**
 * 배치작업관리에 대한 DAO 클래스를 정의한다.
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
@Repository("batchOpertDao")
public class BatchOpertDao extends EgovComAbstractDAO {

	/**
	 * 배치작업을 삭제한다.
	 *
	 * @param batchOpert    삭제할 배치작업 VO
	 * @exception Exception Exception
	 */
	public void deleteBatchOpert(BatchOpert batchOpert) throws Exception {
		delete("BatchOpertDao.deleteBatchOpert", batchOpert);
	}

	/**
	 * 배치작업을 등록한다.
	 *
	 * @param batchOpert 저장할 배치작업 VO
	 * @exception Exception Exception
	 */
	public void insertBatchOpert(BatchOpert batchOpert) throws Exception {
		insert("BatchOpertDao.insertBatchOpert", batchOpert);
	}

	/**
	 * 배치작업정보를 상세조회 한다.
	 * @return 배치작업정보
	 *
	 * @param batchOpert    조회할 KEY가 있는 배치작업 VO
	 * @exception Exception Exception
	 */
	public BatchOpert selectBatchOpert(BatchOpert batchOpert) throws Exception {
		return selectOne("BatchOpertDao.selectBatchOpert", batchOpert);
	}

	/**
	 * 배치작업정보목록을  조회한다.
	 * @return 배치작업목록
	 *
	 * @param searchVO    조회조건이 저장된 VO
	 * @exception Exception Exception
	 */
	public List<BatchOpert> selectBatchOpertList(BatchOpert searchVO) throws Exception {
		return selectList("BatchOpertDao.selectBatchOpertList", searchVO);
	}

	/**
	 * 배치작업 목록 전체 건수를(을) 조회한다.
	 * @return 목록건수
	 *
	 * @param searchVO    조회할 정보가 담긴 VO
	 * @exception Exception Exception
	 */
	public int selectBatchOpertListCnt(BatchOpert searchVO) throws Exception {
		return (Integer) selectOne("BatchOpertDao.selectBatchOpertListCnt", searchVO);
	}

	/**
	 * 배치작업정보를 수정한다.
	 *
	 * @param batchOpert    수정대상 배치작업 VO
	 * @exception Exception Exception
	 */
	public void updateBatchOpert(BatchOpert batchOpert) throws Exception {
		update("BatchOpertDao.updateBatchOpert", batchOpert);
	}

}