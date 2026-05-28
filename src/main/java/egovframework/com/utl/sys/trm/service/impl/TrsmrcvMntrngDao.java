package egovframework.com.utl.sys.trm.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.com.utl.sys.trm.service.CntcVO;
import egovframework.com.utl.sys.trm.service.TrsmrcvMntrng;
import egovframework.com.utl.sys.trm.service.TrsmrcvMntrngLog;

import jakarta.annotation.Resource;

/**
 * 송수신모니터링관리에 대한 DAO 클래스를 정의한다.
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
 *  2026.05.28   dasomel    @EgovMapper 인터페이스 방식으로 전환
 * </pre>
 */
@Repository("trsmrcvMntrngDao")
public class TrsmrcvMntrngDao {

	@Resource(name = "trsmrcvMntrngMapper")
	private TrsmrcvMntrngMapper trsmrcvMntrngMapper;

	/**
	 * 송수신모니터링을 삭제한다.
	 *
	 * @param trsmrcvMntrng    삭제할 송수신모니터링 VO
	 * @exception Exception Exception
	 */
	public void deleteTrsmrcvMntrng(TrsmrcvMntrng trsmrcvMntrng) throws Exception {
		trsmrcvMntrngMapper.deleteTrsmrcvMntrng(trsmrcvMntrng);
	}

	/**
	 * 송수신모니터링을 등록한다.
	 *
	 * @param trsmrcvMntrng 저장할 송수신모니터링 VO
	 * @exception Exception Exception
	 */
	public void insertTrsmrcvMntrng(TrsmrcvMntrng trsmrcvMntrng) throws Exception {
		trsmrcvMntrngMapper.insertTrsmrcvMntrng(trsmrcvMntrng);
	}

	/**
	 * 송수신모니터링로그를 등록한다.
	 *
	 * @param trsmrcvMntrngLog 저장할 송수신모니터링로그 VO
	 * @exception Exception Exception
	 */
	public void insertTrsmrcvMntrngLog(TrsmrcvMntrngLog trsmrcvMntrngLog) throws Exception {
		trsmrcvMntrngMapper.insertTrsmrcvMntrngLog(trsmrcvMntrngLog);
	}

	/**
	 * 송수신모니터링정보를 상세조회 한다.
	 * @return 송수신모니터링정보
	 *
	 * @param trsmrcvMntrng    조회할 KEY가 있는 송수신모니터링 VO
	 * @exception Exception Exception
	 */
	public TrsmrcvMntrng selectTrsmrcvMntrng(TrsmrcvMntrng trsmrcvMntrng) throws Exception {
		return trsmrcvMntrngMapper.selectTrsmrcvMntrng(trsmrcvMntrng);
	}

	/**
	 * 송수신모니터링로그정보를 상세조회 한다.
	 * @return 송수신모니터링로그정보
	 *
	 * @param trsmrcvMntrngLog    조회할 KEY가 있는 송수신모니터링로그 VO
	 * @exception Exception Exception
	 */
	public TrsmrcvMntrngLog selectTrsmrcvMntrngLog(TrsmrcvMntrngLog trsmrcvMntrngLog) throws Exception {
		return trsmrcvMntrngMapper.selectTrsmrcvMntrngLog(trsmrcvMntrngLog);
	}

	/**
	 * 송수신모니터링정보목록을  조회한다.
	 * @return 송수신모니터링목록
	 *
	 * @param searchVO    조회조건이 저장된 VO
	 * @exception Exception Exception
	 */
	public List<TrsmrcvMntrng> selectTrsmrcvMntrngList(TrsmrcvMntrng searchVO) throws Exception {
		return trsmrcvMntrngMapper.selectTrsmrcvMntrngList(searchVO);
	}

	/**
	 * 송수신모니터링 목록 전체 건수를(을) 조회한다.
	 * @return 목록건수
	 *
	 * @param searchVO    조회할 정보가 담긴 VO
	 * @exception Exception Exception
	 */
	public int selectTrsmrcvMntrngListCnt(TrsmrcvMntrng searchVO) throws Exception {
		return trsmrcvMntrngMapper.selectTrsmrcvMntrngListCnt(searchVO);
	}

	/**
	 * 송수신모니터링로그정보목록을  조회한다.
	 * @return 송수신모니터링로그목록
	 *
	 * @param searchVO    조회조건이 저장된 VO
	 * @exception Exception Exception
	 */
	public List<TrsmrcvMntrngLog> selectTrsmrcvMntrngLogList(TrsmrcvMntrngLog searchVO) throws Exception {
		return trsmrcvMntrngMapper.selectTrsmrcvMntrngLogList(searchVO);
	}

	/**
	 * 송수신모니터링로그 목록 전체 건수를(을) 조회한다.
	 * @return 목록건수
	 *
	 * @param searchVO    조회할 정보가 담긴 VO
	 * @exception Exception Exception
	 */
	public int selectTrsmrcvMntrngLogListCnt(TrsmrcvMntrngLog searchVO) throws Exception {
		return trsmrcvMntrngMapper.selectTrsmrcvMntrngLogListCnt(searchVO);
	}

	/**
	 * 송수신모니터링정보를 수정한다.
	 *
	 * @param trsmrcvMntrng    수정대상 송수신모니터링 VO
	 * @exception Exception Exception
	 */
	public void updateTrsmrcvMntrng(TrsmrcvMntrng trsmrcvMntrng) throws Exception {
		trsmrcvMntrngMapper.updateTrsmrcvMntrng(trsmrcvMntrng);
	}

	/**
	 * 연계정보목록을  조회한다.
	 * @return 연계정보목록
	 *
	 * @param searchVO    조회조건이 저장된 VO
	 * @exception Exception Exception
	 */
	public List<CntcVO> selectCntcList(CntcVO searchVO) throws Exception {
		return trsmrcvMntrngMapper.selectCntcList(searchVO);
	}

	/**
	 * 연계정보 목록 전체 건수를(을) 조회한다.
	 * @return 목록건수
	 *
	 * @param searchVO    조회할 정보가 담긴 VO
	 * @exception Exception Exception
	 */
	public int selectCntcListCnt(CntcVO searchVO) throws Exception {
		return trsmrcvMntrngMapper.selectCntcListCnt(searchVO);
	}

}
