package egovframework.com.uss.ion.sit.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.com.uss.ion.sit.service.SiteVO;
import jakarta.annotation.Resource;

@Repository("EgovSiteDAO")
public class EgovSiteDAO {

	@Resource(name = "EgovSiteMapper")
	private EgovSiteMapper egovSiteMapper;

	public List<SiteVO> selectSiteList(SiteVO siteVO) {
		return egovSiteMapper.selectSiteList(siteVO);
	}

	public int selectSiteListCnt(SiteVO siteVO) {
		return egovSiteMapper.selectSiteListCnt(siteVO);
	}

	public SiteVO selectSiteDetail(SiteVO siteVO) {
		return egovSiteMapper.selectSiteDetail(siteVO);
	}

	public void insertSite(SiteVO siteVO) {
		egovSiteMapper.insertSite(siteVO);
	}

	public void updateSite(SiteVO siteVO) {
		egovSiteMapper.updateSite(siteVO);
	}

	public void deleteSite(SiteVO siteVO) {
		egovSiteMapper.deleteSite(siteVO);
	}
}
