package egovframework.com.sym.log.wlg.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.com.cmm.service.impl.EgovComAbstractDAO;
import egovframework.com.sym.log.wlg.service.WebLog;

/**
 * @Class Name : WebLogDAO.java
 * @Description : 웹로그 관리를 위한 데이터 접근 클래스
 * @Modification Information
 *
 *    수정일         수정자         수정내용
 *    -------        -------     -------------------
 *    2009. 3. 11.   이삼섭         최초생성
 *    2011. 7. 01.   이기하         패키지 분리(sym.log -> sym.log.wlg)
 *    2026. 6. 16.   이백행         [2026년 컨트리뷰션] 불필요한 예외 제거
 *
 * @author 공통 서비스 개발팀 이삼섭
 * @since 2009. 3. 11.
 * @version
 * @see
 *
 */
@Repository("webLogDAO")
public class WebLogDAO extends EgovComAbstractDAO {

	/**
	 * 웹 로그를 기록한다.
	 *
	 * @param WebLog
	 * @return
	 */
	public void logInsertWebLog(WebLog webLog) {
		insert("WebLog.logInsertWebLog", webLog);
	}

	/**
	 * 웹 로그정보를 요약한다.
	 *
	 * @param
	 * @return
	 */
	public void logInsertWebLogSummary() {
		insert("WebLog.logInsertWebLogSummary", null);
		delete("WebLog.logDeleteWebLogSummary", null);
	}

	/**
	 * 웹 로그정보 상세정보를 조회한다.
	 *
	 * @param webLog
	 * @return webLog
	 */
	public WebLog selectWebLog(WebLog webLog) {

		return (WebLog) selectOne("WebLog.selectWebLog", webLog);
	}

	/**
	 * 웹 로그정보 목록을 조회한다.
	 *
	 * @param webLog
	 * @return
	 */
	public List<WebLog> selectWebLogInf(WebLog webLog) {
		return selectList("WebLog.selectWebLogInf", webLog);
	}

	/**
	 * 웹 로그정보 목록의 숫자를 조회한다.
	 * @param webLog
	 * @return
	 */
	public int selectWebLogInfCnt(WebLog webLog) {

		return (Integer)selectOne("WebLog.selectWebLogInfCnt", webLog);
	}

}
