package egovframework.com.sts.bst.service.impl;

import java.util.List;

import org.egovframe.rte.psl.dataaccess.mapper.EgovMapper;

import egovframework.com.sts.com.StatsVO;

/**
 * 게시물 통계 Mapper 인터페이스
 * @author 공통서비스 개발팀 박지욱
 * @since 2009.03.19
 * @version 1.0
 * <pre>
 * 개정이력(Modification Information)
 *
 *   수정일      수정자          수정내용
 *  -------    --------    ---------------------------
 *  2009.03.19  박지욱          최초 생성
 *  2011.06.30  이기하          패키지 분리(sts -> sts.bst)
 * </pre>
 */
@EgovMapper("bbsStatsMapper")
public interface BbsStatsMapper {

	/**
	 * 게시물 생성글수 통계를 조회한다
	 * @param vo StatsVO
	 * @return List
	 */
	List<StatsVO> selectBbsCretCntStats(StatsVO vo);

	/**
	 * 게시물 총조회수 통계를 조회한다
	 * @param vo StatsVO
	 * @return List
	 */
	List<StatsVO> selectBbsTotCntStats(StatsVO vo);

	/**
	 * 게시물 평균조회수 통계를 조회한다
	 * @param vo StatsVO
	 * @return List
	 */
	List<StatsVO> selectBbsAvgCntStats(StatsVO vo);

	/**
	 * 최고조회 게시물 통계정보를 조회한다
	 * @param vo StatsVO
	 * @return List
	 */
	List<StatsVO> selectBbsMaxCntStats(StatsVO vo);

	/**
	 * 최소조회 게시물 통계정보를 조회한다
	 * @param vo StatsVO
	 * @return List
	 */
	List<StatsVO> selectBbsMinCntStats(StatsVO vo);

	/**
	 * 게시물 최고게시자 통계를 조회한다
	 * @param vo StatsVO
	 * @return List
	 */
	List<StatsVO> selectBbsMaxUserStats(StatsVO vo);

	/**
	 * 게시물 통계를 위한 집계 여부를 조회한다
	 * @param vo StatsVO
	 * @return StatsVO
	 */
	StatsVO selectBbsSummary(StatsVO vo);

	/**
	 * 게시판 생성글수를 조회한다
	 * @param vo StatsVO
	 * @return StatsVO
	 */
	StatsVO selectBbsCreatCo(StatsVO vo);

	/**
	 * 게시판 총조회수를 조회한다
	 * @param vo StatsVO
	 * @return StatsVO
	 */
	StatsVO selectBbsTotInqireCo(StatsVO vo);

	/**
	 * 게시판 평균조회수를 조회한다
	 * @param vo StatsVO
	 * @return StatsVO
	 */
	StatsVO selectBbsAvrgInqireCo(StatsVO vo);

	/**
	 * 최고조회 게시물ID를 조회한다
	 * @param vo StatsVO
	 * @return StatsVO
	 */
	StatsVO selectBbsMxmmInqireBbsId(StatsVO vo);

	/**
	 * 최소조회 게시물ID를 조회한다
	 * @param vo StatsVO
	 * @return StatsVO
	 */
	StatsVO selectBbsMummInqireBbsId(StatsVO vo);

	/**
	 * 최고게시자ID를 조회한다
	 * @param vo StatsVO
	 * @return StatsVO
	 */
	StatsVO selectBbsTopNtcepersonId(StatsVO vo);

	/**
	 * 게시물 통계 집계를 등록한다
	 * @param vo StatsVO
	 */
	void summaryBbsStats(StatsVO vo);

}
