package egovframework.com.sts.sst.service;

import java.util.List;

import egovframework.com.sts.com.StatsVO;

/**
 * 화면 통계 검색 비즈니스 인터페이스 클래스
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
 *  2011.06.30  이기하          패키지 분리(sts -> sts.sst)
 *
 *  </pre>
 */
public interface EgovScrinStatsService {

	/**
	 * 화면 통계를 조회한다
	 * @param vo StatsVO
	 * @return List
	 * @exception Exception
	 */
	List<?> selectScrinStats(StatsVO vo) throws Exception;
}
