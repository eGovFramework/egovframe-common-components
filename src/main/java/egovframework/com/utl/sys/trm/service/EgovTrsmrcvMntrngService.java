package egovframework.com.utl.sys.trm.service;
import java.util.List;

/**
 * 송수신모니터링관리에 대한 Service Interface를 정의한다.
 * 
 * @author 김진만
 * @since 2010.06.21
 * @version 1.0
 * @updated 21-6-2010 오전 10:27:13
 * @see
 * <pre>
 * == 개정이력(Modification Information) ==
 * 
 *   수정일       수정자           수정내용
 *  -------     --------    ---------------------------
 *  2010.06.21   김진만     최초 생성
 * </pre>
 */
public interface EgovTrsmrcvMntrngService {

	/**
	 * 송수신모니터링을  삭제한다.
	 * 
	 * @param trsmrcvMntrng    삭제대상 송수신모니터링model
	 * @exception Exception Exception
	 */
	public void deleteTrsmrcvMntrng(TrsmrcvMntrng trsmrcvMntrng)
	  throws Exception;

	/**
	 * 송수신모니터링을 등록한다.
	 * 
	 * @param trsmrcvMntrng    등록대상 송수신모니터링model
	 * @exception Exception Exception
	 */
	public void insertTrsmrcvMntrng(TrsmrcvMntrng trsmrcvMntrng)
	  throws Exception;

	/**
	 * 송수신모니터링로그를 등록한다.
	 * 
	 * @param trsmrcvMntrngLog    등록대상 송수신모니터링로그model
	 * @exception Exception Exception
	 */
	public void insertTrsmrcvMntrngLog(TrsmrcvMntrngLog trsmrcvMntrngLog)
	  throws Exception;

	/**
	 * 송수신모니터링을  상세조회 한다.
	 * @return 송수신모니터링정보
	 * 
	 * @param trsmrcvMntrng    조회대상 송수신모니터링model
	 * @exception Exception Exception
	 */
	public TrsmrcvMntrng selectTrsmrcvMntrng(TrsmrcvMntrng trsmrcvMntrng)
	  throws Exception;

	/**
	 * 송수신모니터링로그를  상세조회 한다.
	 * @return 송수신모니터링로그정보
	 * 
	 * @param trsmrcvMntrngLog    조회대상 송수신모니터링로그model
	 * @exception Exception Exception
	 */
	public TrsmrcvMntrngLog selectTrsmrcvMntrngLog(TrsmrcvMntrngLog trsmrcvMntrngLog)
	  throws Exception;

	/**
	 * 송수신모니터링 목록을 조회한다.
	 * @return 송수신모니터링목록
	 * 
	 * @param searchVO    조회조건VO
	 * @exception Exception Exception
	 */
	public List<TrsmrcvMntrng> selectTrsmrcvMntrngList(TrsmrcvMntrng searchVO)
	  throws Exception;

	/**
	 * 송수신모니터링 목록 전체 건수를(을) 조회한다.
	 * @return 목록건수
	 * 
	 * @param searchVO    조회할 정보가 담긴 VO
	 * @exception Exception Exception
	 */
	public int selectTrsmrcvMntrngListCnt(TrsmrcvMntrng searchVO)
	  throws Exception;

	/**
	 * 송수신모니터링로그 목록을 조회한다.
	 * @return 송수신모니터링로그목록
	 * 
	 * @param searchVO    조회조건VO
	 * @exception Exception Exception
	 */
	public List<TrsmrcvMntrngLog> selectTrsmrcvMntrngLogList(TrsmrcvMntrngLog searchVO)
	  throws Exception;

	/**
	 * 송수신모니터링로그 목록 전체 건수를(을) 조회한다.
	 * @return 목록건수
	 * 
	 * @param searchVO    조회할 정보가 담긴 VO
	 * @exception Exception Exception
	 */
	public int selectTrsmrcvMntrngLogListCnt(TrsmrcvMntrngLog searchVO)
	  throws Exception;

	/**
	 * 송수신모니터링을 수정한다.
	 * 
	 * @param trsmrcvMntrng    수정대상 송수신모니터링model
	 * @exception Exception Exception
	 */
	public void updateTrsmrcvMntrng(TrsmrcvMntrng trsmrcvMntrng)
	  throws Exception;

	/**
	 * 연계정보 목록을 조회한다.
	 * @return 연계정보목록
	 * 
	 * @param searchVO    조회조건VO
	 * @exception Exception Exception
	 */
	public List<CntcVO> selectCntcList(CntcVO searchVO)
	  throws Exception;
	/**
	 * 연계정보 목록 전체 건수를(을) 조회한다.
	 * @return 목록건수
	 * 
	 * @param searchVO    조회할 정보가 담긴 VO
	 * @exception Exception Exception
	 */
	public int selectCntcListCnt(CntcVO searchVO)
	  throws Exception;
	
}