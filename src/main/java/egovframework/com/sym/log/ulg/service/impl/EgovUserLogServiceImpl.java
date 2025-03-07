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
 * @Class Name : EgovUserLogServiceImpl.java
 * @Description : 사용로그 관리를 위한 서비스 구현 클래스
 * @Modification Information
 *
 *    수정일         수정자         수정내용
 *    -------        -------     -------------------
 *    2009. 3. 11.   이삼섭        최초생성
 *    2011. 7. 01.   이기하        패키지 분리(sym.log -> sym.log.ulg)
 *
 * @author 공통 서비스 개발팀 이삼섭
 * @since 2009. 3. 11.
 * @version
 * @see
 *
 */
@Service("EgovUserLogService")
public class EgovUserLogServiceImpl extends EgovAbstractServiceImpl implements
	EgovUserLogService {

	@Resource(name="userLogDAO")
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
	public UserLog selectUserLog(UserLog userLog) throws Exception{

		return userLogDAO.selectUserLog(userLog);
	}

	/**
	 * 사용자 로그정보 목록을 조회한다.
	 *
	 * @param UserLog
	 */
	@Override
	public Map<?, ?> selectUserLogInf(UserLog userLog) throws Exception {
		List<UserLog> _result = userLogDAO.selectUserLogInf(userLog);
		int _cnt = userLogDAO.selectUserLogInfCnt(userLog);

		Map<String, Object> _map = new HashMap<String, Object>();
		_map.put("resultList", _result);
		_map.put("resultCnt", Integer.toString(_cnt));

		return _map;
	}

}
