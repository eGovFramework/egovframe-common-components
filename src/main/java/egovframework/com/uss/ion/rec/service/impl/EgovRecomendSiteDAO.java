package egovframework.com.uss.ion.rec.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.com.uss.ion.rec.service.RecomendSiteVO;
import jakarta.annotation.Resource;

/**
 * 추천사이트에 대한 DAO 클래스
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2026.05.28  dasomel         @EgovMapper 인터페이스 위임 방식으로 전환
 * </pre>
 */
@Repository("EgovRecomendSiteDAO")
public class EgovRecomendSiteDAO {

	@Resource(name = "egovRecomendSiteMapper")
	private EgovRecomendSiteMapper mapper;

	public List<RecomendSiteVO> selectRecomendSiteList(RecomendSiteVO recomendSiteVO) {
		return mapper.selectRecomendSiteList(recomendSiteVO);
	}

	public int selectRecomendSiteListCnt(RecomendSiteVO recomendSiteVO) {
		return mapper.selectRecomendSiteListCnt(recomendSiteVO);
	}

	public void insertRecomendSite(RecomendSiteVO recomendSiteVO) {
		mapper.insertRecomendSite(recomendSiteVO);
	}

	public RecomendSiteVO selectRecomendSiteDetail(RecomendSiteVO recomendSiteVO) {
		return mapper.selectRecomendSiteDetail(recomendSiteVO);
	}

	public void updateRecomendSite(RecomendSiteVO recomendSiteVO) {
		mapper.updateRecomendSite(recomendSiteVO);
	}

	public void deleteRecomendSite(RecomendSiteVO recomendSiteVO) {
		mapper.deleteRecomendSite(recomendSiteVO);
	}
}
