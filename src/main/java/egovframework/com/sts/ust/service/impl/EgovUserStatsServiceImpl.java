package egovframework.com.sts.ust.service.impl;

import java.util.List;

import egovframework.com.sts.com.StatsVO;
import egovframework.com.sts.ust.service.EgovUserStatsService;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

/**
 * 사용자 통계 검색 비즈니스 구현 클래스
 * @author 공통서비스 개발팀 박지욱
 * @since 2009.03.12
 * @version 1.0
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일     수정자          수정내용
 *  -------    --------    ---------------------------
 *  2009.03.19  박지욱          최초 생성
 *  2011.06.30  이기하          패키지 분리(sts -> sts.ust)
 *
 *  </pre>
 */
@Service("userStatsService")
public class EgovUserStatsServiceImpl extends EgovAbstractServiceImpl implements
	EgovUserStatsService {

    @Resource(name="userStatsDAO")
    private UserStatsDAO userStatsDAO;

    /**
	 * 사용자 통계를 조회한다
	 * @param vo StatsVO
	 * @return List
	 * @exception Exception
	 */
    @Override
	public List<?> selectUserStats(StatsVO vo) throws Exception {
        return userStatsDAO.selectUserStats(vo);
	}

    /**
	 * 사용자 통계를 위한 집계를 하루단위로 작업하는 배치 프로그램
	 * @exception Exception
	 */
	@Override
	public void summaryUserStats() throws Exception {
		userStatsDAO.summaryUserStats();
	}
}
