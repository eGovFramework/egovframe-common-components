package egovframework.com.sym.log.ulg.service;

import java.util.Map;

/**
 * @Class Name : EgovUserLogService.java
 * @Description : 사용로그 관리를 위한 서비스 인터페이스
 * @Modification Information
 *
 *    수정일         수정자         수정내용
 *    -------        -------     -------------------
 *    2009. 3. 11.   이삼섭         최초생성
 *    2011. 7. 01.   이기하         패키지 분리(sym.log -> sym.log.ulg)
 *
 * @author 공통 서비스 개발팀 이삼섭
 * @since 2009. 3. 11.
 * @version
 * @see
 *
 */
public interface EgovUserLogService {

	/**
	 * 사용자 로그정보를 생성한다.
	 *
	 * @param
	 */
	public void logInsertUserLog() throws Exception;

	/**
	 * 사용자로그 상세정보를 조회한다.
	 *
	 * @param userLog
	 * @return userLog
	 * @throws Exception
	 */
	public UserLog selectUserLog(UserLog userLog) throws Exception;

	/**
	 * 사용자 로그정보 목록을 조회한다.
	 *
	 * @param UserLog
	 */
	public Map<?, ?> selectUserLogInf(UserLog userLog) throws Exception;

}
