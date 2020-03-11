package egovframework.com.sym.log.lgm.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.com.sym.log.lgm.service.EgovSysLogService;
import egovframework.com.sym.log.lgm.service.SysLog;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;

/**
 * @Class Name : EgovSysLogServiceImpl.java
 * @Description : 로그관리(시스템)를 위한 서비스 구현 클래스
 * @Modification Information
 *
 *    수정일       수정자         수정내용
 *    -------        -------     -------------------
 *    2009. 3. 11.     이삼섭
 *
 * @author 공통 서비스 개발팀 이삼섭
 * @since 2009. 3. 11.
 * @version
 * @see
 *
 */
@Service("EgovSysLogService")
public class EgovSysLogServiceImpl extends EgovAbstractServiceImpl implements EgovSysLogService{
	@Resource(name="SysLogDAO")
	private SysLogDAO sysLogDAO;

    /** ID Generation */
	@Resource(name="egovSysLogIdGnrService")
	private EgovIdGnrService egovSysLogIdGnrService;

	/**
	 * 시스템 로그정보를 생성한다.
	 *
	 * @param SysLog
	 */
	@Override
	public void logInsertSysLog(SysLog sysLog) throws Exception {
		String requstId = egovSysLogIdGnrService.getNextStringId();
		sysLog.setRequstId(requstId);

		sysLogDAO.logInsertSysLog(sysLog);
		
	}

	/**
	 * 시스템 로그정보를 요약한다.
	 *
	 * @param
	 */
	@Override
	public void logInsertSysLogSummary() throws Exception {
		sysLogDAO.logInsertSysLogSummary();
		
	}

	/**
	 * 시스템 로그정보 목록을 조회한다.
	 *
	 * @param SysLog
	 */
	@Override
	public Map<?, ?> selectSysLogInf(SysLog sysLog) throws Exception {

		List<?> _result = sysLogDAO.selectSysLogInf(sysLog);
		int _cnt = sysLogDAO.selectSysLogInfCnt(sysLog);

		Map<String, Object> _map = new HashMap<String, Object>();
		_map.put("resultList", _result);
		_map.put("resultCnt", Integer.toString(_cnt));

		return _map;
	}

	/**
	 * 시스템 로그 상세정보를 조회한다.
	 *
	 * @param sysLog
	 * @return sysLog
	 * @throws Exception
	 */
	@Override
	public SysLog selectSysLog(SysLog sysLog) throws Exception {
		return sysLogDAO.selectSysLog(sysLog);
	}

}
