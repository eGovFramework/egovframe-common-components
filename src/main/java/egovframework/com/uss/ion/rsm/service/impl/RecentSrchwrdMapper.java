package egovframework.com.uss.ion.rsm.service.impl;

import java.util.List;

import org.egovframe.rte.psl.dataaccess.mapper.EgovMapper;
import org.egovframe.rte.psl.dataaccess.util.EgovMap;

import egovframework.com.uss.ion.rsm.service.RecentSrchwrd;

/**
 * 최근검색어에 대한 Mapper 인터페이스
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2026.05.28  dasomel         XML 기반 DAO → @EgovMapper 인터페이스로 전환
 * </pre>
 */
@EgovMapper("onlineRecentSrchwrdMapper")
public interface RecentSrchwrdMapper {

	List<EgovMap> selectRecentSrchwrd(RecentSrchwrd searchVO);

	int selectRecentSrchwrdCnt(RecentSrchwrd searchVO);

	RecentSrchwrd selectRecentSrchwrdDetail(RecentSrchwrd recentSrchwrd);

	void insertRecentSrchwrd(RecentSrchwrd recentSrchwrd);

	void updateRecentSrchwrd(RecentSrchwrd recentSrchwrd);

	void deleteRecentSrchwrd(RecentSrchwrd recentSrchwrd);

	List<EgovMap> selectRecentSrchwrdResultInquire(RecentSrchwrd recentSrchwrd);

	List<?> selectRecentSrchwrdResult(RecentSrchwrd searchVO);

	int selectRecentSrchwrdCntResult(RecentSrchwrd searchVO);

	void insertRecentSrchwrdResult(RecentSrchwrd recentSrchwrd);

	void deleteRecentSrchwrdResult(RecentSrchwrd recentSrchwrd);

	void deleteRecentSrchwrdResultAll(RecentSrchwrd recentSrchwrd);
}
