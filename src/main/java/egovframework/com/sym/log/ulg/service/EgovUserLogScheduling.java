package egovframework.com.sym.log.ulg.service;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

/**
 * @Class Name : EgovUserLogScheduling.java
 * @Description : 사용로그 요약을 위한 스케쥴링 클래스
 * @Modification Information
 *
 *    수정일         수정자        수정내용
 *    -------        -------     -------------------
 *    2009. 3. 11.   이삼섭        최초생성
 *    2011. 7. 01.   이기하        패키지 분리(sym.log -> sym.log.ulg)
 *
 * @author 공통 서비스 개발팀 이삼섭
 * @since 2009. 3. 11.
 * @version
 * @see
 *
 */
@Service("egovUserLogScheduling")
public class EgovUserLogScheduling extends EgovAbstractServiceImpl {

	@Resource(name="EgovUserLogService")
	private EgovUserLogService userLogService;

	/**
	 * 사용자 로그정보를 생성한다.
	 *
	 * @param
	 * @return
	 * @throws Exception
	 */
	public void userLogInsert() throws Exception {
		userLogService.logInsertUserLog();
	}

}
