package egovframework.com.utl.sys.trm.service.impl;

import java.util.List;

import egovframework.com.utl.sys.trm.service.CntcVO;
import egovframework.com.utl.sys.trm.service.EgovTrsmrcvMntrngService;
import egovframework.com.utl.sys.trm.service.TrsmrcvMntrng;
import egovframework.com.utl.sys.trm.service.TrsmrcvMntrngLog;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

/**
 * 송수신모니터링관리에 대한 ServiceImpl 클래스를 정의한다.
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
@Service("egovTrsmrcvMntrngService")
public class EgovTrsmrcvMntrngServiceImpl extends EgovAbstractServiceImpl implements EgovTrsmrcvMntrngService {

	/**
	 * 송수신모니터링DAO
	 */
	@Resource(name = "trsmrcvMntrngDao")
	private TrsmrcvMntrngDao dao;

	/**
	 * 송수신모니터링을 삭제한다.
	 * @param trsmrcvMntrng    삭제대상 송수신모니터링model
	 * @exception Exception Exception
	 */
	@Override
	public void deleteTrsmrcvMntrng(TrsmrcvMntrng trsmrcvMntrng) throws Exception {
		dao.deleteTrsmrcvMntrng(trsmrcvMntrng);
	}

	/**
	 * 송수신모니터링을 등록한다.
	 * @param trsmrcvMntrng    등록대상 송수신모니터링model
	 * @exception Exception Exception
	 */
	@Override
	public void insertTrsmrcvMntrng(TrsmrcvMntrng trsmrcvMntrng) throws Exception {
		// 상태값을 초기치로 설정한다.
		trsmrcvMntrng.setMntrngSttus("01");
		dao.insertTrsmrcvMntrng(trsmrcvMntrng);

	}

	/**
	 * 송수신모니터링로그를 등록한다.
	 * @param trsmrcvMntrng    등록대상 송수신모니터링로그model
	 * @exception Exception Exception
	 */
	@Override
	public void insertTrsmrcvMntrngLog(TrsmrcvMntrngLog trsmrcvMntrngLog) throws Exception {
		// 상태값을 초기치로 설정한다.
		dao.insertTrsmrcvMntrngLog(trsmrcvMntrngLog);

	}

	/**
	 * 송수신모니터링을 상세조회 한다.
	 * @return 송수신모니터링정보
	 *
	 * @param trsmrcvMntrng 조회대상 송수신모니터링model
	 * @exception Exception Exception
	 */
	@Override
	public TrsmrcvMntrng selectTrsmrcvMntrng(TrsmrcvMntrng trsmrcvMntrng) throws Exception {
		return dao.selectTrsmrcvMntrng(trsmrcvMntrng);
	}

	/**
	 * 송수신모니터링로그를 상세조회 한다.
	 * @return 송수신모니터링로그정보
	 *
	 * @param trsmrcvMntrngLog 조회대상 송수신모니터링로그model
	 * @exception Exception Exception
	 */
	@Override
	public TrsmrcvMntrngLog selectTrsmrcvMntrngLog(TrsmrcvMntrngLog trsmrcvMntrngLog) throws Exception {
		return dao.selectTrsmrcvMntrngLog(trsmrcvMntrngLog);
	}

	/**
	 * 송수신모니터링의 목록을 조회 한다.
	 * @return 송수신모니터링목록
	 *
	 * @param searchVO 	조회정보가 담긴 VO
	 * @exception Exception Exception
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<TrsmrcvMntrng> selectTrsmrcvMntrngList(TrsmrcvMntrng searchVO) throws Exception {
		List<TrsmrcvMntrng> result = (List<TrsmrcvMntrng>) dao.selectTrsmrcvMntrngList(searchVO);
		return result;
	}

	/**
	 * 송수신모니터링 목록 전체 건수를(을) 조회한다.
	 * @return 목록건수
	 *
	 * @param searchVO    조회할 정보가 담긴 VO
	 * @exception Exception Exception
	 */
	@Override
	public int selectTrsmrcvMntrngListCnt(TrsmrcvMntrng searchVO) throws Exception {
		int cnt = dao.selectTrsmrcvMntrngListCnt(searchVO);
		return cnt;
	}

	/**
	 * 송수신모니터링로그의 목록을 조회 한다.
	 * @return 송수신모니터링로그목록
	 *
	 * @param searchVO 	조회정보가 담긴 VO
	 * @exception Exception Exception
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<TrsmrcvMntrngLog> selectTrsmrcvMntrngLogList(TrsmrcvMntrngLog searchVO) throws Exception {
		List<TrsmrcvMntrngLog> result = (List<TrsmrcvMntrngLog>) dao.selectTrsmrcvMntrngLogList(searchVO);
		return result;
	}

	/**
	 * 송수신모니터링로그 목록 전체 건수를(을) 조회한다.
	 * @return 목록건수
	 *
	 * @param searchVO    조회할 정보가 담긴 VO
	 * @exception Exception Exception
	 */
	@Override
	public int selectTrsmrcvMntrngLogListCnt(TrsmrcvMntrngLog searchVO) throws Exception {
		int cnt = dao.selectTrsmrcvMntrngLogListCnt(searchVO);
		return cnt;
	}

	/**
	 * 송수신모니터링정보를 수정한다.
	 *
	 * @param trsmrcvMntrng    수정대상 송수신모니터링model
	 * @exception Exception Exception
	 */
	@Override
	public void updateTrsmrcvMntrng(TrsmrcvMntrng trsmrcvMntrng) throws Exception {
		dao.updateTrsmrcvMntrng(trsmrcvMntrng);
	}

	/**
	 * 연계정보 목록을 조회한다.
	 * @return 연계정보목록
	 *
	 * @param searchVO    조회조건VO
	 * @exception Exception Exception
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<CntcVO> selectCntcList(CntcVO searchVO) throws Exception {
		List<CntcVO> result = (List<CntcVO>) dao.selectCntcList(searchVO);
		return result;
	}

	/**
	 * 연계정보 목록 전체 건수를(을) 조회한다.
	 * @return 목록건수
	 *
	 * @param searchVO    조회할 정보가 담긴 VO
	 * @exception Exception Exception
	 */
	@Override
	public int selectCntcListCnt(CntcVO searchVO) throws Exception {
		int cnt = dao.selectCntcListCnt(searchVO);
		return cnt;
	}

}