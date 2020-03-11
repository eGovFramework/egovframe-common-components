package egovframework.com.sym.log.tlg.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import egovframework.com.sym.log.tlg.service.EgovTrsmrcvLogService;
import egovframework.com.sym.log.tlg.service.TrsmrcvLog;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

/**
 * @Class Name : EgovTrsmrcvLogServiceImpl.java
 * @Description : 송수신 로그 관리를 위한 서비스 구현 클래스
 * @Modification Information
 *
 *    수정일         수정자         수정내용
 *    -------        -------     -------------------
 *    2009. 3. 11.   이삼섭         최초생성
 *    2011. 7. 01.   이기하         패키지 분리(sym.log -> sym.log.tlg)
 *
 * @author 공통 서비스 개발팀 이삼섭
 * @since 2009. 3. 11.
 * @version
 * @see
 *
 */
@Service("EgovTrsmrcvLogService")
public class EgovTrsmrcvLogServiceImpl extends EgovAbstractServiceImpl implements
	EgovTrsmrcvLogService {

	@Resource(name="trsmrcvLogDAO")
	private TrsmrcvLogDAO trsmrcvLogDAO;

    /** ID Generation */
	@Resource(name="egovTrsmrcvLogIdGnrService")
	private EgovIdGnrService egovTrsmrcvLogIdGnrService;

	/**
	 * 송수신로그 정보를 생성한다.
	 *
	 * @param TrsmrcvLog
	 */
	@Override
	public void logInsertTrsmrcvLog(TrsmrcvLog trsmrcvLog) throws Exception {
		String requstId = egovTrsmrcvLogIdGnrService.getNextStringId();
		trsmrcvLog.setRequstId(requstId);

		trsmrcvLogDAO.logInsertTrsmrcvLog(trsmrcvLog);
	}

	/**
	 * 송수신 로그정보를 요약한다.
	 *
	 * @param
	 */
	@Override
	public void logInsertTrsmrcvLogSummary() throws Exception {

		trsmrcvLogDAO.logInsertTrsmrcvLogSummary();
	}

	/**
	 * 송수신 로그정보를 조회한다.
	 *
	 * @param trsmrcvLog
	 * @return trsmrcvLog
	 * @throws Exception
	 */
	@Override
	public TrsmrcvLog selectTrsmrcvLog(TrsmrcvLog trsmrcvLog) throws Exception{

		return trsmrcvLogDAO.selectTrsmrcvLog(trsmrcvLog);
	}

	/**
	 * 송수신 로그정보 목록을 조회한다.
	 *
	 * @param TrsmrcvLog
	 */
	@Override
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Map<?, ?> selectTrsmrcvLogInf(TrsmrcvLog trsmrcvLog) throws Exception {
		List<?> _result = trsmrcvLogDAO.selectTrsmrcvLogInf(trsmrcvLog);
		int _cnt = trsmrcvLogDAO.selectTrsmrcvLogInfCnt(trsmrcvLog);

		Map<String, Object> _map = new HashMap();
		_map.put("resultList", _result);
		_map.put("resultCnt", Integer.toString(_cnt));

		return _map;
	}

}
