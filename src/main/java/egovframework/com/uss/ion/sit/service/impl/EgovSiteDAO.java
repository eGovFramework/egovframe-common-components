package egovframework.com.uss.ion.sit.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.com.cmm.service.impl.EgovComAbstractDAO;
import egovframework.com.uss.ion.sit.service.SiteVO;

@Repository("EgovSiteDAO")
public class EgovSiteDAO extends EgovComAbstractDAO {

	public List<?> selectSiteList(SiteVO searchVO) {
		return list("SiteManage.selectSiteList", searchVO);
	}

	public int selectSiteListCnt(SiteVO searchVO) {
		return (Integer) selectOne("SiteManage.selectSiteListCnt", searchVO);
	}

	public SiteVO selectSiteDetail(SiteVO siteVO) {
		return (SiteVO) selectOne("SiteManage.selectSiteDetail", siteVO);
	}

	public void insertSite(SiteVO siteVO) {
		insert("SiteManage.insertSite", siteVO);
	}

	public void updateSite(SiteVO siteVO) {
		update("SiteManage.updateSite", siteVO);	
	}

	public void deleteSite(SiteVO siteVO) {
		delete("SiteManage.deleteSite", siteVO);
	}

}
