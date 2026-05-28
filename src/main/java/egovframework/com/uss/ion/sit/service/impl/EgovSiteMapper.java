package egovframework.com.uss.ion.sit.service.impl;

import java.util.List;

import org.egovframe.rte.psl.dataaccess.EgovMapper;

import egovframework.com.uss.ion.sit.service.SiteVO;

/**
 * 사이트관리에 대한 Mapper 인터페이스
 * @version 1.0
 */
@EgovMapper("EgovSiteMapper")
public interface EgovSiteMapper {

	List<SiteVO> selectSiteList(SiteVO siteVO);

	int selectSiteListCnt(SiteVO siteVO);

	SiteVO selectSiteDetail(SiteVO siteVO);

	void insertSite(SiteVO siteVO);

	void updateSite(SiteVO siteVO);

	void deleteSite(SiteVO siteVO);
}
