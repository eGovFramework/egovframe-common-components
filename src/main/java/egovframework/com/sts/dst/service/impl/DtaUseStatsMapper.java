package egovframework.com.sts.dst.service.impl;

import java.util.List;

import org.egovframe.rte.psl.dataaccess.mapper.EgovMapper;

import egovframework.com.sts.dst.service.DtaUseStats;
import egovframework.com.sts.dst.service.DtaUseStatsVO;

/**
 * 자료이용현황 통계에 대한 Mapper 인터페이스
 * @author lee.m.j
 * @version 1.0
 * @since 2009.09.08
 * <pre>
 * 개정이력(Modification Information)
 *
 *   수정일      수정자          수정내용
 *  -------    --------    ---------------------------
 *  2009.09.08  lee.m.j          최초 생성
 * </pre>
 */
@EgovMapper("dtaUseStatsMapper")
public interface DtaUseStatsMapper {

	/**
	 * 자료이용현황 통계정보의 대상목록을 조회한다.
	 * @param dtaUseStatsVO 자료이용현황 VO
	 * @return List 자료이용현황 목록
	 */
	List<DtaUseStatsVO> selectDtaUseStatsList(DtaUseStatsVO dtaUseStatsVO);

	/**
	 * 자료이용현황 통계정보의 대상목록 카운트를 조회한다.
	 * @param dtaUseStatsVO 자료이용현황 VO
	 * @return int
	 */
	int selectDtaUseStatsListTotCnt(DtaUseStatsVO dtaUseStatsVO);

	/**
	 * 자료이용현황 통계정보의 전체 카운트를 조회한다.
	 * @param dtaUseStatsVO 자료이용현황 VO
	 * @return int
	 */
	int selectDtaUseStatsListBarTotCnt(DtaUseStatsVO dtaUseStatsVO);

	/**
	 * 자료이용현황 통계의 상세정보를 조회한다.
	 * @param dtaUseStatsVO 자료이용현황 VO
	 * @return List 자료이용현황 상세 목록
	 */
	List<DtaUseStatsVO> selectDtaUseStats(DtaUseStatsVO dtaUseStatsVO);

	/**
	 * 자료이용현황 통계정보의 상세정보목록 카운트를 조회한다.
	 * @param dtaUseStatsVO 자료이용현황 VO
	 * @return int
	 */
	int selectDtaUseStatsTotCnt(DtaUseStatsVO dtaUseStatsVO);

	/**
	 * 자료이용현황 정보를 등록을 위한 다운로드 첨부화일 정보를 조회한다.
	 * @param dtaUseStats DtaUseStats
	 * @return DtaUseStats
	 */
	DtaUseStats selectInsertDtaUseStats(DtaUseStats dtaUseStats);

	/**
	 * 자료이용현황 정보를 등록한다.
	 * @param dtaUseStats 자료이용현황 model
	 */
	void insertDtaUseStats(DtaUseStats dtaUseStats);

	/**
	 * 등록일자별 통계정보를 그래프로 표현한다.
	 * @param dtaUseStatsVO 자료이용현황 VO
	 * @return List 등록일자별 자료이용현황 목록
	 */
	List<DtaUseStatsVO> selectDtaUseStatsBarList(DtaUseStatsVO dtaUseStatsVO);

}
