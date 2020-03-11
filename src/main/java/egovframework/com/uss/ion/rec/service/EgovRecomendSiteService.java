package egovframework.com.uss.ion.rec.service;

import java.util.List;

import egovframework.rte.fdl.cmmn.exception.FdlException;

public interface EgovRecomendSiteService {

	List<?> selectRecomendSiteList(RecomendSiteVO searchVO);

	int selectRecomendSiteListCnt(RecomendSiteVO searchVO);

	void insertRecomendSite(RecomendSiteVO recomendSiteVO) throws FdlException;

	RecomendSiteVO selectRecomendSiteDetail(RecomendSiteVO recomendSiteVO) throws Exception;

	void updateRecomendSite(RecomendSiteVO recomendSiteVO);

	void deleteRecomendSite(RecomendSiteVO recomendSiteVO);

}
