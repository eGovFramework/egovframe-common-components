package egovframework.com.sym.log.clg.service.impl;

import java.util.List;

import org.egovframe.rte.psl.dataaccess.mapper.EgovMapper;

import egovframework.com.sym.log.clg.service.LoginLog;

/**
 * @Class Name : LoginLogDAO.java
 * @Description : 시스템 로그 관리를 위한 데이터 접근 인터페이스
 * @Modification Information
 *
 *    수정일       수정자         수정내용
 *    -------       -------     -------------------
 *    2009. 3. 11.  이삼섭       최초생성
 *    2011. 7. 01.  이기하       패키지 분리(sym.log -> sym.log.clg)
 *    2026. 5. 28.  dasomel      @EgovMapper 인터페이스 방식으로 전환
 *
 * @author 공통 서비스 개발팀 이삼섭
 * @since 2009. 3. 11.
 * @version
 * @see
 *
 */
@EgovMapper("loginLogDAO")
public interface LoginLogDAO {

	/**
	 * 접속로그를 기록한다.
	 *
	 * @param loginLog
	 */
	void logInsertLoginLog(LoginLog loginLog);

	/**
	 * 접속로그 상세보기를 조회한다.
	 *
	 * @param loginLog
	 * @return loginLog
	 */
	LoginLog selectLoginLog(LoginLog loginLog);

	/**
	 * 접속로그 목록을 조회한다.
	 *
	 * @param loginLog
	 * @return List
	 */
	List<LoginLog> selectLoginLogInf(LoginLog loginLog);

	/**
	 * 접속로그 목록의 숫자를 조회한다.
	 *
	 * @param loginLog
	 * @return int
	 */
	int selectLoginLogInfCnt(LoginLog loginLog);

}
