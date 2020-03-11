package egovframework.com.sym.log.clg.service;

import java.util.Map;


/**
 * @Class Name : EgovLoginLogService.java
 * @Description : 시스템 로그 관리를 위한 서비스 인터페이스
 * @Modification Information
 *
 *    수정일       수정자         수정내용
 *    -------      -------     -------------------
 *    2009. 3. 11. 이삼섭        최초생성
 *    2011. 7. 01. 이기하        패키지 분리(sym.log -> sym.log.clg)
 *
 * @author 공통 서비스 개발팀 이삼섭
 * @since 2009. 3. 11.
 * @version
 * @see
 *
 */
public interface EgovLoginLogService {

	/**
	 * 접속로그를 기록한다.
	 *
	 * @param LoginLog
	 */
	public void logInsertLoginLog(LoginLog loinLog) throws Exception;

	/**
	 * 접속로그를 조회한다.
	 *
	 * @param loginLog
	 * @return loginLog
	 * @throws Exception
	 */
	public LoginLog selectLoginLog(LoginLog loginLog) throws Exception;

	/**
	 * 접속로그 목록을 조회한다.
	 *
	 * @param LoginLog
	 */
	public Map<?, ?> selectLoginLogInf(LoginLog loinLog) throws Exception;

}
