package egovframework.com.sym.log.wlg.service.impl;

import java.util.List;

import egovframework.com.cmm.service.impl.EgovComAbstractDAO;
import egovframework.com.sym.log.wlg.service.WebLog;

import org.springframework.stereotype.Repository;

/**
 * @Class Name : WebLogDAO.java
 * @Description : 웹로그 관리를 위한 데이터 접근 클래스
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
@Repository("webLogDAO")
public class WebLogDAO extends EgovComAbstractDAO {

	/**
	 * 웹 로그를 기록한다.
	 *
	 * @param WebLog
	 * @return
	 * @throws Exception
	 */
	public void logInsertWebLog(WebLog webLog) throws Exception{
		insert("WebLog.logInsertWebLog", webLog);
	}

	/**
	 * 웹 로그정보를 요약한다.
	 *
	 * @param
	 * @return
	 * @throws Exception
	 */
	public void logInsertWebLogSummary() throws Exception{
		insert("WebLog.logInsertWebLogSummary", null);
		delete("WebLog.logDeleteWebLogSummary", null);
	}

	/**
	 * 웹 로그정보 상세정보를 조회한다.
	 *
	 * @param webLog
	 * @return webLog
	 * @throws Exception
	 */
	public WebLog selectWebLog(WebLog webLog) throws Exception{

		return (WebLog) selectOne("WebLog.selectWebLog", webLog);
	}

	/**
	 * 웹 로그정보 목록을 조회한다.
	 *
	 * @param webLog
	 * @return
	 * @throws Exception
	 */
	public List<?> selectWebLogInf(WebLog webLog) throws Exception{
		return list("WebLog.selectWebLogInf", webLog);
	}

	/**
	 * 웹 로그정보 목록의 숫자를 조회한다.
	 * @param webLog
	 * @return
	 * @throws Exception
	 */
	public int selectWebLogInfCnt(WebLog webLog) throws Exception{

		return (Integer)selectOne("WebLog.selectWebLogInfCnt", webLog);
	}

}
