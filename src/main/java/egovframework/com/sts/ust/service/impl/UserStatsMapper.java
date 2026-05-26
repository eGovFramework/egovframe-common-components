package egovframework.com.sts.ust.service.impl;

import java.util.List;

import org.egovframe.rte.psl.dataaccess.mapper.EgovMapper;

import egovframework.com.sts.com.StatsVO;

/**
 * 사용자 통계 검색 Mapper 인터페이스
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
 *  2011.06.30  이기하          패키지 분리(sts -> sts.sst)
 *  2026.05.27  dasomel         @EgovMapper 인터페이스 방식으로 전환
 *
 *  </pre>
 */
@EgovMapper("userStatsDAO")
public interface UserStatsMapper {

	/**
	 * 사용자 통계를 조회한다
	 * @param vo StatsVO
	 * @return List
	 * @exception Exception
	 */
	List<StatsVO> selectUserStats(StatsVO vo) throws Exception;

	/**
	 * 사용자 통계를 위한 집계를 하루단위로 작업하는 배치 프로그램
	 * @exception Exception
	 */
	void summaryUserStats() throws Exception;

}
