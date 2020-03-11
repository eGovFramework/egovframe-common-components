package egovframework.com.sym.log.wlg.service;

import java.util.Map;

/**
 * @Class Name : EgovWebLogService.java
 * @Description : 웹로그 관리를 위한 서비스 인터페이스
 * @Modification Information
 *
 *    수정일         수정자         수정내용
 *    -------        -------     -------------------
 *    2009. 3. 11.   이삼섭         최초생성
 *    2011. 7. 01.   이기하         패키지 분리(sym.log -> sym.log.wlg)
 *
 * @author 공통 서비스 개발팀 이삼섭
 * @since 2009. 3. 11.
 * @version
 * @see
 *
 */

public interface EgovWebLogService {

	/**
	 * 웹 로그를 기록한다.
	 *
	 * @param WebLog
	 */
	public void logInsertWebLog(WebLog webLog) throws Exception;

	/**
	 * 웹 로그정보를 요약한다.
	 *
	 * @param
	 */
	public void logInsertWebLogSummary() throws Exception;

	/**
	 * 웹로그 상세정보를 조회한다.
	 *
	 * @param webLog
	 * @return webLog
	 * @throws Exception
	 */
	public WebLog selectWebLog(WebLog webLog) throws Exception;

	/**
	 * 웹 로그정보 목록을 조회한다.
	 *
	 * @param WebLog
	 */
	public Map<?, ?> selectWebLogInf(WebLog webLog) throws Exception;

}
