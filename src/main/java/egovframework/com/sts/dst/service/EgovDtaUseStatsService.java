/**
 * 개요
 * - 자료이용현황 통계에 대한 Service Interface를 정의한다.
 * 
 * 상세내용
 * - 자료이용현황 통계에 대한 등록, 조회 기능을 제공한다.
 * - 자료이용현황 통계의 조회기능은 목록조회, 상세조회로 구분된다.
 * @author lee.m.j
 * @version 1.0
 * @created 08-9-2009 오후 1:40:19
 */

package egovframework.com.sts.dst.service;

import java.util.List;
import java.util.Map;

import org.aspectj.lang.JoinPoint;
import org.springframework.web.bind.annotation.RequestParam;

public interface EgovDtaUseStatsService {

    /**
	 * 자료이용현황 통계정보의 대상목록을 조회한다.
	 * @param dtaUseStatsVO - 자료이용현황 VO
	 * @return List - 자료이용현황 목록
	 */
	public List<DtaUseStatsVO> selectDtaUseStatsList(DtaUseStatsVO dtaUseStatsVO) throws Exception;

	/**
	 * 자료이용현황 통계정보의 대상목록 카운트를 조회한다.
	 * @param dtaUseStatsVO - 자료이용현황 VO
	 * @return int
	 */
	public int selectDtaUseStatsListTotCnt(DtaUseStatsVO dtaUseStatsVO) throws Exception;
	
	/**
	 * 자료이용현황 통계정보의 전체 카운트를 조회한다.
	 * @param dtaUseStatsVO - 자료이용현황 VO
	 * @return int
	 */
	public int selectDtaUseStatsListBarTotCnt(DtaUseStatsVO dtaUseStatsVO) throws Exception;
	
    /**
	 * 자료이용현황 통계의 상세정보를 조회한다.
	 * @param dtaUseStatsVO - 자료이용현황 VO
	 * @return dtaUseStatsVO - 자료이용현황 VO
	 */
	public List<DtaUseStatsVO> selectDtaUseStats(DtaUseStatsVO dtaUseStatsVO) throws Exception;

	/**
	 * 자료이용현황 통계정보의 상세정보목록 카운트를 조회한다.
	 * @param dtaUseStatsVO - 자료이용현황 VO
	 * @return int
	 */
	public int selectDtaUseStatsTotCnt(DtaUseStatsVO dtaUseStatsVO) throws Exception;
	
	/**
	 * 자료이용현황 정보를 생성한다.
	 * @param jp - AOP의 pointcut을 위한 JoinPoint
	 * @param dtaUseStats - 자료이용현황 model
	 */
	public void insertDtaUseStats(JoinPoint jp, @RequestParam Map<String, Object> commandMap) throws Exception;	
	
	/**
	 * 등록일자별 통계정보를 그래프로 표현한다.
	 * @param dtaUseStatsVO - 자료이용현황 VO
	 * @return List - 등록일자별 자료이용현황 목록
	 */
	public List<DtaUseStatsVO> selectDtaUseStatsBarList(DtaUseStatsVO dtaUseStatsVO) throws Exception;
}
