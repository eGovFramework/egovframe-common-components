package egovframework.com.sts.cst.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.com.cmm.service.impl.EgovComAbstractDAO;
import egovframework.com.sts.com.StatsVO;

/**
 * 접속 통계 검색 DAO 클래스
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
 *  2011.06.30  이기하          패키지 분리(sts -> sts.cst)
 *   2024.08.12  이백행          시큐어코딩 Exception 제거
 *
 *      </pre>
 */
@Repository("conectStatsDAO")
public class ConectStatsDAO extends EgovComAbstractDAO {

	/**
	 * 접속 통계를 조회한다
	 * 
	 * @param vo StatsVO
	 * @return List
	 */
	public List<StatsVO> selectConectStats(StatsVO vo) {
		return selectList("ConectStatsDAO.selectConectStats", vo);
	}
}
