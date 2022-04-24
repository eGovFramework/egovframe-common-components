package egovframework.com.sts.cst.service.impl;

import java.util.List;

import egovframework.com.cmm.service.impl.EgovComAbstractMapper;
import egovframework.com.sts.com.StatsVO;

import org.springframework.stereotype.Repository;

/**
 * 접속 통계 검색 DAO 클래스
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
@Repository("conectStatsDAO")
public class ConectStatsDAO extends EgovComAbstractMapper {

	/**
	 * 접속 통계를 조회한다
	 * @param vo StatsVO
	 * @return List
	 * @exception Exception
	 */
    public List<?> selectConectStats(StatsVO vo) throws Exception {
        return selectList("ConectStatsDAO.selectConectStats", vo);
    }
}
