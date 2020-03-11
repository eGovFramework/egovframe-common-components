package egovframework.com.uss.ion.rec.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.com.cmm.service.impl.EgovComAbstractDAO;
import egovframework.com.uss.ion.rec.service.RecomendSiteVO;

@Repository("EgovRecomendSiteDAO")
public class EgovRecomendSiteDAO extends EgovComAbstractDAO{

	public List<?> selectRecomendSiteList(RecomendSiteVO searchVO) {
		return list("RecomendSite.selectRecomendSiteList", searchVO);
	}

	public int selectRecomendSiteListCnt(RecomendSiteVO searchVO) {
		return (Integer) selectOne("RecomendSite.selectRecomendSiteListCnt", searchVO);
	}

	public void insertRecomendSite(RecomendSiteVO recomendSiteVO) {
		insert("RecomendSite.insertRecomendSite", recomendSiteVO);
	}

	public RecomendSiteVO selectRecomendSiteDetail(RecomendSiteVO recomendSiteVO) {
		return (RecomendSiteVO) selectOne("RecomendSite.selectRecomendSiteDetail", recomendSiteVO);
	}

	public void updateRecomendSite(RecomendSiteVO recomendSiteVO) {
		update("RecomendSite.updateRecomendSite", recomendSiteVO);
	}

	public void deleteRecomendSite(RecomendSiteVO recomendSiteVO) {
		delete("RecomendSite.deleteRecomendSite", recomendSiteVO);
	}

}
