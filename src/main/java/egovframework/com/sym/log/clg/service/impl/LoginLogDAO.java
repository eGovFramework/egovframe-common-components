package egovframework.com.sym.log.clg.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.com.cmm.service.impl.EgovComAbstractDAO;
import egovframework.com.sym.log.clg.service.LoginLog;

/**
 * @Class Name : LoginLogDAO.java
 * @Description : 시스템 로그 관리를 위한 데이터 접근 클래스
 * @Modification Information
 *
 *    수정일       수정자         수정내용
 *    -------       -------     -------------------
 *    2009. 3. 11.  이삼섭       최초생성
 *    2011. 7. 01.  이기하       패키지 분리(sym.log -> sym.log.clg)
 *    2026. 6. 16.  이백행       [2026년 컨트리뷰션] 불필요한 예외 제거
 *
 * @author 공통 서비스 개발팀 이삼섭
 * @since 2009. 3. 11.
 * @version
 * @see
 *
 */
@Repository("loginLogDAO")
public class LoginLogDAO extends EgovComAbstractDAO {

	/**
	 * 접속로그를 기록한다.
	 *
	 * @param LoginLog
	 * @return
	 */
	public void logInsertLoginLog(LoginLog loginLog) {
		insert("LoginLog.logInsertLoginLog", loginLog);
	}

	/**
	 * 접속로그 상세보기를 조회한다.
	 *
	 * @param loginLog
	 * @return loginLog
	 */
	public LoginLog selectLoginLog(LoginLog loginLog) {

		return (LoginLog) selectOne("LoginLog.selectLoginLog", loginLog);
	}

	/**
	 * 접속로그를 목록을 조회한다.
	 *
	 * @param loginLog
	 * @return
	 */
	public List<LoginLog> selectLoginLogInf(LoginLog loginLog) {
		return selectList("LoginLog.selectLoginLogInf", loginLog);
	}

	/**
	 * 접속로그 목록의 숫자를 조회한다.
	 * @param loginLog
	 * @return
	 */
	public int selectLoginLogInfCnt(LoginLog loginLog) {

		return (Integer)selectOne("LoginLog.selectLoginLogInfCnt", loginLog);
	}

}
