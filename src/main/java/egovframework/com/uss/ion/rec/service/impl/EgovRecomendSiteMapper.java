package egovframework.com.uss.ion.rec.service.impl;

import java.util.List;

import org.egovframe.rte.psl.dataaccess.mapper.EgovMapper;

import egovframework.com.uss.ion.rec.service.RecomendSiteVO;

/**
 * 추천사이트에 대한 Mapper 인터페이스
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2026.05.28  dasomel         XML 기반 DAO → @EgovMapper 인터페이스로 전환
 * </pre>
 */
@EgovMapper("egovRecomendSiteMapper")
public interface EgovRecomendSiteMapper {

	List<RecomendSiteVO> selectRecomendSiteList(RecomendSiteVO recomendSiteVO);

	int selectRecomendSiteListCnt(RecomendSiteVO recomendSiteVO);

	void insertRecomendSite(RecomendSiteVO recomendSiteVO);

	RecomendSiteVO selectRecomendSiteDetail(RecomendSiteVO recomendSiteVO);

	void updateRecomendSite(RecomendSiteVO recomendSiteVO);

	void deleteRecomendSite(RecomendSiteVO recomendSiteVO);
}
