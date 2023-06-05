package egovframework.com.uss.ion.sit.service;

import java.util.List;

import org.egovframe.rte.fdl.cmmn.exception.FdlException;

public interface EgovSiteService {

	List<SiteVO> selectSiteList(SiteVO searchVO);

	int selectSiteListCnt(SiteVO searchVO);

	SiteVO selectSiteDetail(SiteVO siteVO) throws Exception;

	void insertSite(SiteVO siteVO) throws FdlException;

	void updateSite(SiteVO siteVO);

	void deleteSite(SiteVO siteVO);

}
