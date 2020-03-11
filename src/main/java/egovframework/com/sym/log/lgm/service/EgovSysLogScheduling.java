package egovframework.com.sym.log.lgm.service;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

/**
 * @Class Name : EgovSysLogScheduling.java
 * @Description : 시스템 로그 요약을 위한 스케쥴링 클래스
 * @Modification Information
 *
 *    수정일       수정자         수정내용
 *    -------        -------     -------------------
 *    2009. 3. 11.     이삼섭   최초생성
 *
 * @author 공통 서비스 개발팀 이삼섭
 * @since 2009. 3. 11.
 * @version
 * @see
 *
 */
@Service("egovSysLogScheduling")
public class EgovSysLogScheduling extends EgovAbstractServiceImpl {

	@Resource(name="EgovSysLogService")
	private EgovSysLogService sysLogService;

	/**
	 * 시스템 로그정보를 요약한다.
	 * 전날의 로그를 요약하여 입력하고, 6개월전의 로그를 삭제한다.
	 *
	 * @param
	 * @return
	 * @throws Exception
	 */
	public void sysLogSummary() throws Exception {
		sysLogService.logInsertSysLogSummary();
	}

}
