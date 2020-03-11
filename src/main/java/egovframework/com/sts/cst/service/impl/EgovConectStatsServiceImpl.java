package egovframework.com.sts.cst.service.impl;

import java.util.List;

import egovframework.com.sts.com.StatsVO;
import egovframework.com.sts.cst.service.EgovConectStatsService;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

/**
 * 접속 통계 검색 비즈니스 구현 클래스
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
 *  2011.06.30  이기하          패키지 분리(sts -> sts.cst)
 *
 *  </pre>
 */
@Service("conectStatsService")
public class EgovConectStatsServiceImpl extends EgovAbstractServiceImpl implements
	EgovConectStatsService {

    @Resource(name="conectStatsDAO")
    private ConectStatsDAO conectStatsDAO;

    /**
	 * 접속 통계를 조회한다
	 * @param vo StatsVO
	 * @return List
	 * @exception Exception
	 */
    @Override
	public List<?> selectConectStats(StatsVO vo) throws Exception {
        return conectStatsDAO.selectConectStats(vo);
	}
}
