package egovframework.com.sym.log.clg.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.egovframe.rte.fdl.cmmn.EgovAbstractServiceImpl;
import org.egovframe.rte.fdl.idgnr.EgovIdGnrService;
import org.springframework.stereotype.Service;

import egovframework.com.sym.log.clg.service.EgovLoginLogService;
import egovframework.com.sym.log.clg.service.LoginLog;

/**
 * 접속로그 관리를 위한 서비스 구현 클래스
 * 
 * @author 공통 서비스 개발팀 이삼섭
 * @since 2009. 3. 11.
 * @version 1.0
 * @see
 *
 *      <pre>
 *  == 개정이력(Modification Information) ==
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2009.03.11  이삼섭          최초 생성
 *   2011.07.01  이기하          패키지 분리(stm.log -> sym.log.clg)
 *   2025.07.10  이백행          2025년 컨트리뷰션 PMD로 소프트웨어 보안약점 진단하고 제거하기-LocalVariableNamingConventions(final이 아닌 변수는 밑줄을 포함할 수 없음)
 *
 *      </pre>
 */
@Service("EgovLoginLogService")
public class EgovLoginLogServiceImpl extends EgovAbstractServiceImpl implements EgovLoginLogService {

	@Resource(name = "loginLogDAO")
	private LoginLogDAO loginLogDAO;

	/** ID Generation */
	@Resource(name = "egovLoginLogIdGnrService")
	private EgovIdGnrService egovLoginLogIdGnrService;

	/**
	 * 접속로그를 기록한다.
	 *
	 * @param LoginLog
	 */
	@Override
	public void logInsertLoginLog(LoginLog loinLog) throws Exception {
		String logId = egovLoginLogIdGnrService.getNextStringId();
		loinLog.setLogId(logId);

		loginLogDAO.logInsertLoginLog(loinLog);
	}

	/**
	 * 접속로그를 조회한다.
	 *
	 * @param loginLog
	 * @return loginLog
	 * @throws Exception
	 */
	@Override
	public LoginLog selectLoginLog(LoginLog loginLog) throws Exception {

		return loginLogDAO.selectLoginLog(loginLog);
	}

	/**
	 * 접속로그 목록을 조회한다.
	 *
	 * @param LoginLog
	 */
	@Override
	public Map<String, Object> selectLoginLogInf(LoginLog loinLog) throws Exception {
		List<LoginLog> resultList = loginLogDAO.selectLoginLogInf(loinLog);
		int resultCnt = loginLogDAO.selectLoginLogInfCnt(loinLog);

		Map<String, Object> map = new HashMap<>();
		map.put("resultList", resultList);
		map.put("resultCnt", resultCnt);

		return map;
	}

}
