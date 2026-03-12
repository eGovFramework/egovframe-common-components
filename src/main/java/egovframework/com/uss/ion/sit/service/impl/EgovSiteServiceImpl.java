package egovframework.com.uss.ion.sit.service.impl;

import java.util.List;

import org.egovframe.rte.fdl.cmmn.EgovAbstractServiceImpl;
import org.egovframe.rte.fdl.cmmn.exception.FdlException;
import org.egovframe.rte.fdl.idgnr.EgovIdGnrService;
import org.springframework.stereotype.Service;

import egovframework.com.uss.ion.sit.service.EgovSiteService;
import egovframework.com.uss.ion.sit.service.SiteVO;
import jakarta.annotation.Resource;

@Service("EgovSiteService")
public class EgovSiteServiceImpl extends EgovAbstractServiceImpl implements EgovSiteService {

	@Resource(name="EgovSiteDAO")
    private EgovSiteDAO egovSiteDao;

    /** ID Generation */
	@Resource(name="egovSiteManageIdGnrService")
	private EgovIdGnrService idgenService;

	@Override
	public List<SiteVO> selectSiteList(SiteVO siteVO) {
		return egovSiteDao.selectSiteList(siteVO);
	}

	@Override
	public int selectSiteListCnt(SiteVO siteVO) {
		return egovSiteDao.selectSiteListCnt(siteVO);
	}

	@Override
	public SiteVO selectSiteDetail(SiteVO siteVO) throws Exception {
		SiteVO resultVO = egovSiteDao.selectSiteDetail(siteVO);
        if (resultVO == null) {
			throw processException("info.nodata.msg");
		}
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
