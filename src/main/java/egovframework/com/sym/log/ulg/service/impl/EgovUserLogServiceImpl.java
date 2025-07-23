package egovframework.com.sym.log.ulg.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.egovframe.rte.fdl.cmmn.EgovAbstractServiceImpl;
import org.springframework.stereotype.Service;

import egovframework.com.sym.log.ulg.service.EgovUserLogService;
import egovframework.com.sym.log.ulg.service.UserLog;

/**
 * 사용로그 관리를 위한 서비스 구현 클래스
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
 *   2011.07.01  이기하          패키지 분리(sym.log -> sym.log.ulg)
 *   2025.07.14  이백행          2025년 컨트리뷰션 PMD로 소프트웨어 보안약점 진단하고 제거하기-LocalVariableNamingConventions(final이 아닌 변수는 밑줄을 포함할 수 없음)
 *
 *      </pre>
 */
@Service("EgovUserLogService")
public class EgovUserLogServiceImpl extends EgovAbstractServiceImpl implements EgovUserLogService {

	@Resource(name = "userLogDAO")
	private UserLogDAO userLogDAO;

	/**
	 * 사용자 로그정보를 생성한다.
	 *
	 * @param
	 */
	@Override
	public void logInsertUserLog() throws Exception {

		userLogDAO.logInsertUserLog();
	}

	/**
	 * 사용자 로그정보 상제정보를 조회한다.
	 *
	 * @param userLog
	 * @return userLog
	 * @throws Exception
	 */
	@Override
	public UserLog selectUserLog(UserLog userLog) throws Exception {

		return userLogDAO.selectUserLog(userLog);
	}

	/**
	 * 사용자 로그정보 목록을 조회한다.
	 *
	 * @param UserLog
	 */
	@Override
	public Map<String, Object> selectUserLogInf(UserLog userLog) throws Exception {
		List<UserLog> resultList = userLogDAO.selectUserLogInf(userLog);
		int resultCnt = userLogDAO.selectUserLogInfCnt(userLog);

		Map<String, Object> resultMap = new HashMap<>();
		resultMap.put("resultList", resultList);
		resultMap.put("resultCnt", resultCnt); // 또는 Integer.toString(resultCnt) 필요시

		return resultMap;
	}

}