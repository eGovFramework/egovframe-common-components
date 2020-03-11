package egovframework.com.sts.bst.service.impl;

import java.util.List;

import egovframework.com.sts.bst.service.EgovBbsStatsService;
import egovframework.com.sts.com.StatsVO;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

/**
 * 게시물 통계 검색 비즈니스 구현 클래스
 * @author 공통서비스 개발팀 박지욱
 * @since 2009.03.12
 * @version 1.0
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자          수정내용
 *  -------    --------    ---------------------------
 *  2009.03.19  박지욱          최초 생성
 *  2011.06.30  이기하          패키지 분리(sts -> sts.bst)
 *
 *  </pre>
 */
@Service("bbsStatsService")
public class EgovBbsStatsServiceImpl extends EgovAbstractServiceImpl implements
	EgovBbsStatsService {

    @Resource(name="bbsStatsDAO")
    private BbsStatsDAO bbsStatsDAO;

    /**
	 * 게시물 생성글수 통계를 조회한다
	 * @param vo StatsVO
	 * @return List
	 * @exception Exception
	 */
    @Override
	public List<?> selectBbsCretCntStats(StatsVO vo) throws Exception {
        return bbsStatsDAO.selectBbsCretCntStats(vo);
	}

    /**
	 * 게시물 총조회수 통계를 조회한다
	 * @param vo StatsVO
	 * @return List
	 * @exception Exception
	 */
    @Override
	public List<?> selectBbsTotCntStats(StatsVO vo) throws Exception {
        return bbsStatsDAO.selectBbsTotCntStats(vo);
	}

    /**
	 * 게시물 평균조회수 통계를 조회한다
	 * @param vo StatsVO
	 * @return List
	 * @exception Exception
	 */
    @Override
	public List<?> selectBbsAvgCntStats(StatsVO vo) throws Exception {
        return bbsStatsDAO.selectBbsAvgCntStats(vo);
	}

    /**
	 * 최고조회 게시물 통계정보를 조회한다
	 * @param vo StatsVO
	 * @return List
	 * @exception Exception
	 */
    @Override
	public List<?> selectBbsMaxCntStats(StatsVO vo) throws Exception {
        return bbsStatsDAO.selectBbsMaxCntStats(vo);
	}

    /**
	 * 최소조회 게시물 통계정보를 조회한다
	 * @param vo StatsVO
	 * @return List
	 * @exception Exception
	 */
    @Override
	public List<?> selectBbsMinCntStats(StatsVO vo) throws Exception {
        return bbsStatsDAO.selectBbsMinCntStats(vo);
	}

    /**
	 * 게시물 최고게시자 통계를 조회한다
	 * @param vo StatsVO
	 * @return List
	 * @exception Exception
	 */
    @Override
	public List<?> selectBbsMaxUserStats(StatsVO vo) throws Exception {
        return bbsStatsDAO.selectBbsMaxUserStats(vo);
	}

    /**
	 * 게시물 통계를 위한 집계를 하루단위로 작업하는 배치 프로그램
	 * @exception Exception
	 */
	@Override
	public void summaryBbsStats() throws Exception {
		bbsStatsDAO.summaryBbsStats();
	}
}
