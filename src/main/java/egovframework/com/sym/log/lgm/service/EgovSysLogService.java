package egovframework.com.sym.log.lgm.service;

import java.util.Map;

/**
 * @Class Name : EgovSysLogService.java
 * @Description : 로그관리(시스템)를 위한 서비스 인터페이스
 * @Modification Information
 *
 *    수정일       수정자         수정내용
 *    -------      -------     -------------------
 *    2009. 3. 11.  이삼섭      최초생성
 *    2011. 7. 01.  이기하      패키지 분리(sym.log -> sym.log.lgm)
 *
 * @author 공통 서비스 개발팀 이삼섭
 * @since 2009. 3. 11.
 * @version
 * @see
 *
 */

public interface EgovSysLogService {
	
	/**
	 * 시스템 로그정보를 생성한다.
	 *
	 * @param SysLog
	 */
	public void logInsertSysLog(SysLog sysLog) throws Exception;

	/**
	 * 시스템 로그정보를 요약한다.
	 *
	 * @param
	 */
	public void logInsertSysLogSummary() throws Exception;
	
	/**
	 * 시스템 로그정보 목록을 조회한다.
	 *
	 * @param SysLog
	 */
	public Map<?, ?> selectSysLogInf(SysLog sysLog) throws Exception;

	/**
	 * 시스템로그 상세정보를 조회한다.
	 *
	 * @param sysLog
	 * @return sysLog
	 * @throws Exception
	 */
	public SysLog selectSysLog(SysLog sysLog) throws Exception;

}
