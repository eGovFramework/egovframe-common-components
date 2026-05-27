package egovframework.com.sts.rst.service.impl;

import java.util.List;

import org.egovframe.rte.psl.dataaccess.mapper.EgovMapper;

import egovframework.com.sts.rst.service.ReprtStats;
import egovframework.com.sts.rst.service.ReprtStatsVO;

/**
 * 보고서통계에 대한 Mapper 인터페이스
 * @author lee.m.j
 * @version 1.0
 * @since 2009.08.03
 * <pre>
 * 개정이력(Modification Information)
 *
 *   수정일      수정자          수정내용
 *  -------    --------    ---------------------------
 *  2009.08.03  lee.m.j          최초 생성
 * </pre>
 */
@EgovMapper("reprtStatsMapper")
public interface ReprtStatsMapper {

	/**
	 * 보고서 통계정보의 대상목록을 조회한다.
	 * @param reprtStatsVO 보고서통계 VO
	 * @return List 보고서통계 목록
	 */
	List<ReprtStatsVO> selectReprtStatsList(ReprtStatsVO reprtStatsVO);

	/**
	 * 보고서통계목록 페이징 총 개수를 조회한다.
	 * @param reprtStatsVO 보고서통계 VO
	 * @return int
	 */
	int selectReprtStatsListTotCnt(ReprtStatsVO reprtStatsVO);

	/**
	 * 보고서통계목록 총 개수를 조회한다.
	 * @param reprtStatsVO 보고서통계 VO
	 * @return int
	 */
	int selectReprtStatsListBarTotCnt(ReprtStatsVO reprtStatsVO);

	/**
	 * 보고서 통계정보의 상세정보를 조회한다.
	 * @param reprtStatsVO 보고서통계 VO
	 * @return List 보고서통계 목록
	 */
	List<ReprtStatsVO> selectReprtStats(ReprtStatsVO reprtStatsVO);

	/**
	 * 보고서 통계정보를 생성한 뒤 저장한다.
	 * @param reprtStats 보고서통계 model
	 */
	void insertReprtStats(ReprtStats reprtStats);

	/**
	 * 등록일자별 통계정보를 그래프로 표현한다.
	 * @param reprtStatsVO 보고서통계 VO
	 * @return List 보고서통계 목록
	 */
	List<ReprtStatsVO> selectReprtStatsBarList(ReprtStatsVO reprtStatsVO);

	/**
	 * 보고서유형별 통계정보를 그래프로 표현한다.
	 * @param reprtStatsVO 보고서통계 VO
	 * @return List 보고서통계 목록
	 */
	List<ReprtStatsVO> selectReprtStatsByReprtTyList(ReprtStatsVO reprtStatsVO);

	/**
	 * 진행상태별 통계정보를 그래프로 표현한다.
	 * @param reprtStatsVO 보고서통계 VO
	 * @return List 보고서통계 목록
	 */
	List<ReprtStatsVO> selectReprtStatsByReprtSttusList(ReprtStatsVO reprtStatsVO);

}
