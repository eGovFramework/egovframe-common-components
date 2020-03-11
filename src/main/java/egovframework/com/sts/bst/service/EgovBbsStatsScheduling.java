package egovframework.com.sts.bst.service;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;


/**
 * 게시물 통계 집계를 위한 스케줄링 클래스
 * @author 공통서비스 개발팀 박지욱
 * @since 2009.04.16
 * @version 1.0
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자          수정내용
 *  -------    --------    ---------------------------
 *  2009.04.16  박지욱          최초 생성
 *  2011.06.30  이기하          패키지 분리(sts -> sts.bst)
 *
 *  </pre>
 */

@Service("egovBbsStatsScheduling")
public class EgovBbsStatsScheduling extends EgovAbstractServiceImpl {

	/** EgovBbsStatsService */
	@Resource(name = "bbsStatsService")
    private EgovBbsStatsService bbsStatsService;

	/**
	 * 게시물 통계를 위한 집계를 하루단위로 작업하는 배치 프로그램
	 * @exception Exception
	 */
	public void summaryBbsStats() throws Exception {
		bbsStatsService.summaryBbsStats();
	}
}
