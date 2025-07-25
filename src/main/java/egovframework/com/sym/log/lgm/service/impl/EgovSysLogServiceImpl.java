package egovframework.com.sym.log.lgm.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.egovframe.rte.fdl.cmmn.EgovAbstractServiceImpl;
import org.egovframe.rte.fdl.idgnr.EgovIdGnrService;
import org.springframework.stereotype.Service;

import egovframework.com.sym.log.lgm.service.EgovSysLogService;
import egovframework.com.sym.log.lgm.service.SysLog;

/**
 * 로그관리(시스템)를 위한 서비스 구현 클래스
 * 
 * @author 공통 서비스 개발팀 이삼섭
 * @since 2009.03.11
 * @version 1.0
 * @see
 *
 *      <pre>
 *  == 개정이력(Modification Information) ==
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2009.03.11  이삼섭          최초 생성
 *   2025.07.11  이백행          2025년 컨트리뷰션 PMD로 소프트웨어 보안약점 진단하고 제거하기-LocalVariableNamingConventions(final이 아닌 변수는 밑줄을 포함할 수 없음)
 *
 *      </pre>
 */
@Service("EgovSysLogService")
public class EgovSysLogServiceImpl extends EgovAbstractServiceImpl implements EgovSysLogService {
	@Resource(name = "SysLogDAO")
	private SysLogDAO sysLogDAO;

	/** ID Generation */
	@Resource(name = "egovSysLogIdGnrService")
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
	public Map<String, Object> selectSysLogInf(SysLog sysLog) throws Exception {

		List<SysLog> resultList = sysLogDAO.selectSysLogInf(sysLog);
		int resultCnt = sysLogDAO.selectSysLogInfCnt(sysLog);

		Map<String, Object> map = new HashMap<>();
		map.put("resultList", resultList);
		map.put("resultCnt", resultCnt);

		return map;
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