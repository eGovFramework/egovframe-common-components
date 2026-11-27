package egovframework.com.uss.ion.sit.service;

import java.util.List;

import org.egovframe.rte.fdl.cmmn.exception.FdlException;

public interface EgovSiteService {

	List<SiteVO> selectSiteList(SiteVO siteVO);

	int selectSiteListCnt(SiteVO siteVO);

	SiteVO selectSiteDetail(SiteVO siteVO) throws Exception;

	void insertSite(SiteVO siteVO) throws FdlException;

	void updateSite(SiteVO siteVO);

	void deleteSite(SiteVO siteVO);

}
