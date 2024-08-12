package egovframework.com.sts.sst.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.egovframe.rte.fdl.cmmn.EgovAbstractServiceImpl;
import org.springframework.stereotype.Service;

import egovframework.com.sts.com.StatsVO;
import egovframework.com.sts.sst.service.EgovScrinStatsService;

/**
 * 화면 통계 검색 비즈니스 구현 클래스
 * 
 * @author 공통서비스 개발팀 박지욱
 * @since 2009.03.12
 * @version 1.0
 * @see
 *
 *      <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자          수정내용
 *  -------    --------    ---------------------------
 *  2009.03.19  박지욱          최초 생성
 *  2011.06.30  이기하          패키지 분리(sts -> sts.sst)
 *   2024.08.13  이백행          시큐어코딩 Exception 제거
 *
 *      </pre>
 */
@Service("scrinStatsService")
public class EgovScrinStatsServiceImpl extends EgovAbstractServiceImpl implements EgovScrinStatsService {

	@Resource(name = "scrinStatsDAO")
	private ScrinStatsDAO scrinStatsDAO;

	/**
	 * 화면 통계를 조회한다
	 * 
	 * @param vo StatsVO
	 * @return List
	 */
	@Override
	public List<StatsVO> selectScrinStats(StatsVO vo) {
		return scrinStatsDAO.selectScrinStats(vo);
	}
}
