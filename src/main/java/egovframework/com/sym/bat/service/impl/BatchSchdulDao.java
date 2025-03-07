package egovframework.com.sym.bat.service.impl;
import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.com.cmm.service.impl.EgovComAbstractDAO;
import egovframework.com.sym.bat.service.BatchSchdul;
import egovframework.com.sym.bat.service.BatchSchdulDfk;

/**
 * 배치스케줄관리에 대한 DAO 클래스를 정의한다.
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
@Repository("batchSchdulDao")
public class BatchSchdulDao extends EgovComAbstractDAO {

	/**
	 * 배치스케줄을 삭제한다.
	 *
	 * @param batchSchdul    삭제할 배치스케줄 VO
	 * @exception Exception Exception
	 */
	public void deleteBatchSchdul(BatchSchdul batchSchdul)
	  throws Exception{
		// slave 테이블 삭제
		delete("BatchSchdulDao.deleteBatchSchdulDfk", batchSchdul.getBatchSchdulId());
		// master 테이블 삭제
		delete("BatchSchdulDao.deleteBatchSchdul", batchSchdul);
	}

	/**
	 * 배치스케줄을 등록한다.
	 *
	 * @param batchSchdul 저장할 배치스케줄 VO
	 * @exception Exception Exception
	 */
	public void insertBatchSchdul(BatchSchdul batchSchdul)
	  throws Exception{
		// master 테이블 인서트
		insert("BatchSchdulDao.insertBatchSchdul", batchSchdul);
		// slave 테이블 인서트
		if (batchSchdul.getExecutSchdulDfkSes() != null && batchSchdul.getExecutSchdulDfkSes().length != 0) {
			String batchSchdulId = batchSchdul.getBatchSchdulId();
			String [] dfkSes = batchSchdul.getExecutSchdulDfkSes();
			for (int i = 0; i < dfkSes.length; i++) {
				BatchSchdulDfk batchSchdulDfk = new BatchSchdulDfk();
				batchSchdulDfk.setBatchSchdulId(batchSchdulId);
				batchSchdulDfk.setExecutSchdulDfkSe(dfkSes[i]);
				insert("BatchSchdulDao.insertBatchSchdulDfk", batchSchdulDfk);
			}
		}
	}

	/**
	 * 배치스케줄정보를 상세조회 한다.
	 * @return 배치스케줄정보
	 *
	 * @param batchSchdul    조회할 KEY가 있는 배치스케줄 VO
	 * @exception Exception Exception
	 */
	public BatchSchdul selectBatchSchdul(BatchSchdul batchSchdul)
	  throws Exception{
		BatchSchdul result = (BatchSchdul)selectOne("BatchSchdulDao.selectBatchSchdul", batchSchdul);
		// 스케줄요일정보를 가져온다.
		List<BatchSchdulDfk> dfkSeList = selectList("BatchSchdulDao.selectBatchSchdulDfkList", result.getBatchSchdulId());
		String [] dfkSes = new String [dfkSeList.size()];
		for (int j = 0; j < dfkSeList.size(); j++) {
			dfkSes[j] = dfkSeList.get(j).getExecutSchdulDfkSe();
		}
		result.setExecutSchdulDfkSes(dfkSes);
		// 화면표시용 실행스케줄 속성을 만든다.
		result.makeExecutSchdul(dfkSeList);

		return result ;
	}

	/**
	 * 배치스케줄정보목록을  조회한다.
	 * @return 배치스케줄목록
	 *
	 * @param searchVO    조회조건이 저장된 VO
	 * @exception Exception Exception
	 */
	public List<BatchSchdul> selectBatchSchdulList(BatchSchdul searchVO)
	  throws Exception{
		List<BatchSchdul> resultList = selectList("BatchSchdulDao.selectBatchSchdulList", searchVO);

		for (int i = 0; i < resultList.size(); i++) {
			BatchSchdul result = resultList.get(i);
			// 스케줄요일정보를 가져온다.
			List<BatchSchdulDfk> dfkSeList = selectList("BatchSchdulDao.selectBatchSchdulDfkList", result.getBatchSchdulId());
			String [] dfkSes = new String [dfkSeList.size()];
			for (int j = 0; j < dfkSeList.size(); j++) {
				dfkSes[j] = dfkSeList.get(j).getExecutSchdulDfkSe();
			}
			result.setExecutSchdulDfkSes(dfkSes);
			// 화면표시용 실행스케줄 속성을 만든다.
			result.makeExecutSchdul(dfkSeList);
		}
		return resultList;
	}

	/**
	 * 배치스케줄 목록 전체 건수를(을) 조회한다.
	 * @return 목록건수
	 *
	 * @param searchVO    조회할 정보가 담긴 VO
	 * @exception Exception Exception
	 */
	public int selectBatchSchdulListCnt(BatchSchdul searchVO)
	  throws Exception{
		return (Integer)selectOne("BatchSchdulDao.selectBatchSchdulListCnt", searchVO);
	}

	/**
	 * 배치스케줄정보를 수정한다.
	 *
	 * @param batchSchdul    수정대상 배치스케줄 VO
	 * @exception Exception Exception
	 */
	public void updateBatchSchdul(BatchSchdul batchSchdul)
	  throws Exception{
		update("BatchSchdulDao.updateBatchSchdul", batchSchdul);
		// slave 테이블 삭제
		delete("BatchSchdulDao.deleteBatchSchdulDfk", batchSchdul.getBatchSchdulId());
		// slave 테이블 인서트
		if (batchSchdul.getExecutSchdulDfkSes() != null && batchSchdul.getExecutSchdulDfkSes().length != 0) {
			String batchSchdulId = batchSchdul.getBatchSchdulId();
			String [] dfkSes = batchSchdul.getExecutSchdulDfkSes();
			for (int i = 0; i < dfkSes.length; i++) {
				BatchSchdulDfk batchSchdulDfk = new BatchSchdulDfk();
				batchSchdulDfk.setBatchSchdulId(batchSchdulId);
				batchSchdulDfk.setExecutSchdulDfkSe(dfkSes[i]);
				insert("BatchSchdulDao.insertBatchSchdulDfk", batchSchdulDfk);
			}
		}
	}

}