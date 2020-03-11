package egovframework.com.uss.ion.sit.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.com.uss.ion.sit.service.EgovSiteService;
import egovframework.com.uss.ion.sit.service.SiteVO;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.fdl.cmmn.exception.FdlException;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;

@Service("EgovSiteService")
public class EgovSiteServiceImpl extends EgovAbstractServiceImpl implements EgovSiteService {

	@Resource(name="EgovSiteDAO")
    private EgovSiteDAO egovSiteDao;

    /** ID Generation */
	@Resource(name="egovSiteManageIdGnrService")
	private EgovIdGnrService idgenService;
	
	@Override
	public List<?> selectSiteList(SiteVO searchVO) {
		return egovSiteDao.selectSiteList(searchVO);
	}

	@Override
	public int selectSiteListCnt(SiteVO searchVO) {
		return egovSiteDao.selectSiteListCnt(searchVO);
	}

	@Override
	public SiteVO selectSiteDetail(SiteVO siteVO) throws Exception {
		SiteVO resultVO = egovSiteDao.selectSiteDetail(siteVO);
        if (resultVO == null)
            throw processException("info.nodata.msg");
        return resultVO;
	}

	@Override
	public void insertSite(SiteVO siteVO) throws FdlException {
		String siteId = idgenService.getNextStringId();
		siteVO.setSiteId(siteId);

    	egovSiteDao.insertSite(siteVO);
	}

	@Override
	public void updateSite(SiteVO siteVO) {
		egovSiteDao.updateSite(siteVO);
	}

	@Override
	public void deleteSite(SiteVO siteVO) {
		egovSiteDao.deleteSite(siteVO);
	}

}
