package egovframework.com.sts.bst.service;

import java.util.List;

import egovframework.com.sts.com.StatsVO;

/**
 * 게시물 통계 검색 비즈니스 인터페이스 클래스
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
public interface EgovBbsStatsService {

	/**
	 * 게시물 생성글수 통계를 조회한다
	 * @param vo StatsVO
	 * @return List
	 * @exception Exception
	 */
	List<?> selectBbsCretCntStats(StatsVO vo) throws Exception;

	/**
	 * 게시물 총조회수 통계를 조회한다
	 * @param vo StatsVO
	 * @return List
	 * @exception Exception
	 */
	List<?> selectBbsTotCntStats(StatsVO vo) throws Exception;

	/**
	 * 게시물 평균조회수 통계를 조회한다
	 * @param vo StatsVO
	 * @return List
	 * @exception Exception
	 */
	List<?> selectBbsAvgCntStats(StatsVO vo) throws Exception;

	/**
	 * 최고조회 게시물 통계정보를 조회한다
	 * @param vo StatsVO
	 * @return List
	 * @exception Exception
	 */
	List<?> selectBbsMaxCntStats(StatsVO vo) throws Exception;

	/**
	 * 최소조회 게시물 통계정보를 조회한다
	 * @param vo StatsVO
	 * @return List
	 * @exception Exception
	 */
	List<?> selectBbsMinCntStats(StatsVO vo) throws Exception;

	/**
	 * 게시물 최고게시자 통계를 조회한다
	 * @param vo StatsVO
	 * @return List
	 * @exception Exception
	 */
	List<?> selectBbsMaxUserStats(StatsVO vo) throws Exception;

	/**
	 * 게시물 통계를 위한 집계를 하루단위로 작업하는 배치 프로그램
	 * @exception Exception
	 */
	public void summaryBbsStats() throws Exception;
}
