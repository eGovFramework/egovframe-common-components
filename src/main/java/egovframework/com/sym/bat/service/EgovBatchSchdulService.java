package egovframework.com.sym.bat.service;

import java.util.List;

/**
 * 배치스케줄관리에 대한 Service Interface를 정의한다.
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
public interface EgovBatchSchdulService {

	/**
	 * 배치스케줄을  삭제한다.
	 *
	 * @param batchSchdul    삭제대상 배치스케줄model
	 * @exception Exception Exception
	 */
	public void deleteBatchSchdul(BatchSchdul batchSchdul) throws Exception;

	/**
	 * 배치스케줄을 등록한다.
	 *
	 * @param batchSchdul    등록대상 배치스케줄model
	 * @exception Exception Exception
	 */
	public void insertBatchSchdul(BatchSchdul batchSchdul) throws Exception;

	/**
	 * 배치스케줄을  상세조회 한다.
	 * @return 배치스케줄정보
	 *
	 * @param batchSchdul    조회대상 배치스케줄model
	 * @exception Exception Exception
	 */
	public BatchSchdul selectBatchSchdul(BatchSchdul batchSchdul) throws Exception;

	/**
	 * 배치스케줄 목록을 조회한다.
	 * @return 배치스케줄목록
	 *
	 * @param searchVO    조회조건VO
	 * @exception Exception Exception
	 */
	public List<?> selectBatchSchdulList(BatchSchdul searchVO) throws Exception;

	/**
	 * 배치스케줄 목록 전체 건수를(을) 조회한다.
	 * @return 목록건수
	 *
	 * @param searchVO    조회할 정보가 담긴 VO
	 * @exception Exception Exception
	 */
	public int selectBatchSchdulListCnt(BatchSchdul searchVO) throws Exception;

	/**
	 * 배치스케줄을 수정한다.
	 *
	 * @param batchSchdul    수정대상 배치스케줄model
	 * @exception Exception Exception
	 */
	public void updateBatchSchdul(BatchSchdul batchSchdul) throws Exception;

	/**
	 * 배치결과를 등록한다.
	 * @param batchResult    등록대상 배치결과model
	 * @exception Exception Exception
	 */
	public void insertBatchResult(BatchResult batchResult) throws Exception;

	/**
	 * 배치결과정보를 수정한다.
	 *
	 * @param batchResult    수정대상 배치결과model
	 * @exception Exception Exception
	 */
	public void updateBatchResult(BatchResult batchResult) throws Exception;

}