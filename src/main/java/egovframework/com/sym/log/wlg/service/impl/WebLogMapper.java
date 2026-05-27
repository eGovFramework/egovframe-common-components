package egovframework.com.sym.log.wlg.service.impl;

import java.util.List;

import org.egovframe.rte.psl.dataaccess.mapper.EgovMapper;

import egovframework.com.sym.log.wlg.service.WebLog;

/**
 * 웹 로그 관리를 위한 Mapper 인터페이스
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *    수정일         수정자         수정내용
 *    -------        -------     -------------------
 *    2009. 3. 11.   이삼섭         최초생성
 *    2011. 7. 01.   이기하         패키지 분리(sym.log -> sym.log.wlg)
 *    2026. 5. 28.   dasomel        XML 기반 DAO → @EgovMapper 인터페이스로 전환
 * </pre>
 */
@EgovMapper("webLogMapper")
public interface WebLogMapper {

	void logInsertWebLog(WebLog webLog);

	void logInsertWebLogSummary();

	void logDeleteWebLogSummary();

	WebLog selectWebLog(WebLog webLog);

	List<WebLog> selectWebLogInf(WebLog webLog);

	int selectWebLogInfCnt(WebLog webLog);
}
