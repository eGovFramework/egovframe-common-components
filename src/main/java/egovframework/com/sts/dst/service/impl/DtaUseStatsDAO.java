/**
 * 개요
 * - 자료이용현황 통계에 대한 DAO 클래스를 정의한다.
 * 
 * 상세내용
 * - 자료이용현황 통계에 대한 등록, 조회 기능을 제공한다.
 * - 자료이용현황 통계의 조회기능은 목록조회, 상세조회로 구분된다.
 * @author lee.m.j
 * @version 1.0
 * @created 08-9-2009 오후 1:40:19
 */

package egovframework.com.sts.dst.service.impl;

import java.util.List;

import egovframework.com.cmm.service.impl.EgovComAbstractDAO;
import egovframework.com.sts.dst.service.DtaUseStats;
import egovframework.com.sts.dst.service.DtaUseStatsVO;

import org.springframework.stereotype.Repository;

@Repository("dtaUseStatsDAO")
public class DtaUseStatsDAO extends EgovComAbstractDAO {

	/**
	 * 자료이용현황 통계정보의 대상목록을 조회한다.
	 * @param dtaUseStatsVO - 자료이용현황 VO
	 * @return List - 자료이용현황 목록
	 */
	@SuppressWarnings("unchecked")
	public List<DtaUseStatsVO> selectDtaUseStatsList(DtaUseStatsVO dtaUseStatsVO) throws Exception {
		return selectList("dtaUseStatsDAO.selectDtaUseStatsList", dtaUseStatsVO);
	}

	/**
	 * 자료이용현황 통계정보의 대상목록 카운트를 조회한다.
	 * @param dtaUseStatsVO - 자료이용현황 VO
	 * @return int
	 */
	public int selectDtaUseStatsListTotCnt(DtaUseStatsVO dtaUseStatsVO) throws Exception {
		return (Integer)selectOne("dtaUseStatsDAO.selectDtaUseStatsListTotCnt", dtaUseStatsVO);
	}	
		
	/**
	 * 자료이용현황 통계정보의 전체 카운트를 조회한다.
	 * @param dtaUseStatsVO - 자료이용현황 VO
	 * @return int
	 */
	public int selectDtaUseStatsListBarTotCnt(DtaUseStatsVO dtaUseStatsVO) throws Exception {
		return (Integer)selectOne("dtaUseStatsDAO.selectDtaUseStatsListBarTotCnt", dtaUseStatsVO);
	}		
	
	/**
	 * 자료이용현황 통계의 상세정보를 조회한다.
	 * @param dtaUseStatsVO - 자료이용현황 VO
	 * @return reprtStatsVO - 자료이용현황 VO
	 */
	@SuppressWarnings("unchecked")
	public List<DtaUseStatsVO> selectDtaUseStats(DtaUseStatsVO dtaUseStatsVO) throws Exception {
		return selectList("dtaUseStatsDAO.selectDtaUseStats", dtaUseStatsVO);
	}

	/**
	 * 자료이용현황 통계정보의 상세정보목록 카운트를 조회한다.
	 * @param dtaUseStatsVO - 자료이용현황 VO
	 * @return int
	 */
	public int selectDtaUseStatsTotCnt(DtaUseStatsVO dtaUseStatsVO) throws Exception {
		return (Integer)selectOne("dtaUseStatsDAO.selectDtaUseStatsTotCnt", dtaUseStatsVO);
	}	
	
    /**
	 * 자료이용현황 정보를 등록을 위한 다운로드 첨부화일 정보를 조회한다.
	 * @param dtaUseStats DtaUseStats
	 * @return DtaUseStats
	 * @exception Exception
	 */
    public DtaUseStats selectInsertDtaUseStats(DtaUseStats dtaUseStats) throws Exception {
        return (DtaUseStats) selectOne("dtaUseStatsDAO.selectInsertDtaUseStats", dtaUseStats);
    }	
	
	/**
	 * 자료이용현황 정보를 등록한다.
	 * @param dtaUseStats - 자료이용현황 model
	 */
	public void insertDtaUseStats(DtaUseStats dtaUseStats) throws Exception {

		insert("dtaUseStatsDAO.insertDtaUseStats", dtaUseStats);
	}

	/**
	 * 등록일자별 통계정보를 그래프로 표현한다.
	 * @param dtaUseStatsVO - 자료이용현황 VO
	 * @return List - 등록일자별 자료이용현황 목록
	 */
	@SuppressWarnings("unchecked")
	public List<DtaUseStatsVO> selectDtaUseStatsBarList(DtaUseStatsVO dtaUseStatsVO) throws Exception {
		return selectList("dtaUseStatsDAO.selectDtaUseStatsBarList", dtaUseStatsVO);
	}
}
