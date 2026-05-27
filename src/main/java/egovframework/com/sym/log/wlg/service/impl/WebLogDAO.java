package egovframework.com.sym.log.wlg.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.com.sym.log.wlg.service.WebLog;
import jakarta.annotation.Resource;

/**
 * @Class Name : WebLogDAO.java
 * @Description : 웹로그 관리를 위한 데이터 접근 클래스
 * @Modification Information
 *
 *    수정일         수정자         수정내용
 *    -------        -------     -------------------
 *    2009. 3. 11.   이삼섭         최초생성
 *    2011. 7. 01.   이기하         패키지 분리(sym.log -> sym.log.wlg)
 *    2026. 5. 28.   dasomel        @EgovMapper 인터페이스 위임 방식으로 전환
 *
 * @author 공통 서비스 개발팀 이삼섭
 * @since 2009. 3. 11.
 * @version
 * @see
 *
 */
@Repository("webLogDAO")
public class WebLogDAO {

	@Resource(name = "webLogMapper")
	private WebLogMapper webLogMapper;

	public void logInsertWebLog(WebLog webLog) {
		webLogMapper.logInsertWebLog(webLog);
	}

	public void logInsertWebLogSummary() {
		webLogMapper.logInsertWebLogSummary();
		webLogMapper.logDeleteWebLogSummary();
	}

	public WebLog selectWebLog(WebLog webLog) {
		return webLogMapper.selectWebLog(webLog);
	}

	public List<WebLog> selectWebLogInf(WebLog webLog) {
		return webLogMapper.selectWebLogInf(webLog);
	}

	public int selectWebLogInfCnt(WebLog webLog) {
		return webLogMapper.selectWebLogInfCnt(webLog);
	}
}
