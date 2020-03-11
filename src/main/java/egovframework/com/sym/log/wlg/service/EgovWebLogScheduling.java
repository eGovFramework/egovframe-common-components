package egovframework.com.sym.log.wlg.service;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

/**
 * @Class Name : EgovWebLogScheduling.java
 * @Description : 웹로그 요약을 위한 스케쥴링 클래스
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

@Service("egovWebLogScheduling")
public class EgovWebLogScheduling extends EgovAbstractServiceImpl {

	@Resource(name="EgovWebLogService")
	private EgovWebLogService webLogService;

	/**
	 * 웹 로그정보를 요약한다.
	 * 전날의 로그를 요약하여 입력하고, 6개월전의 로그를 삭제한다.
	 *
	 * @param
	 * @return
	 * @throws Exception
	 */
	public void webLogSummary() throws Exception {
		webLogService.logInsertWebLogSummary();
	}

}
