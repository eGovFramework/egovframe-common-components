package egovframework.com.sym.log.tlg.service;

import java.util.Map;

/**
 * @Class Name : EgovTrsmrcvLogService.java
 * @Description : 송수신 로그 관리를 위한 서비스 인터페이스
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
public interface EgovTrsmrcvLogService {

	/**
	 * 송수신로그 정보를 생성한다.
	 *
	 * @param TrsmrcvLog
	 */
	public void logInsertTrsmrcvLog(TrsmrcvLog trsmrcvLog) throws Exception;

	/**
	 * 송수신 로그정보를 요약한다.
	 *
	 * @param
	 */
	public void logInsertTrsmrcvLogSummary() throws Exception;


	/**
	 * 송수신로그를 조회한다.
	 *
	 * @param trsmrcvLog
	 * @return trsmrcvLog
	 * @throws Exception
	 */
	public TrsmrcvLog selectTrsmrcvLog(TrsmrcvLog trsmrcvLog) throws Exception;

	/**
	 * 송수신 로그정보 목록을 조회한다.
	 *
	 * @param TrsmrcvLog
	 */
	public Map<?, ?> selectTrsmrcvLogInf(TrsmrcvLog trsmrcvLog) throws Exception;

}
