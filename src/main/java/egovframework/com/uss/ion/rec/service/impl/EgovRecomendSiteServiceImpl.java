package egovframework.com.uss.ion.rec.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.com.uss.ion.rec.service.EgovRecomendSiteService;
import egovframework.com.uss.ion.rec.service.RecomendSiteVO;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.fdl.cmmn.exception.FdlException;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;

@Service("EgovRecomendSiteService")
public class EgovRecomendSiteServiceImpl extends EgovAbstractServiceImpl implements EgovRecomendSiteService {

	@Resource(name="EgovRecomendSiteDAO")
    private EgovRecomendSiteDAO egovRecomendSiteDao;

    /** ID Generation */
	@Resource(name="egovRecomendSiteManageIdGnrService")
	private EgovIdGnrService idgenService;
	
	@Override
	public List<?> selectRecomendSiteList(RecomendSiteVO searchVO) {
		return egovRecomendSiteDao.selectRecomendSiteList(searchVO);
	}

	@Override
	public int selectRecomendSiteListCnt(RecomendSiteVO searchVO) {
		return egovRecomendSiteDao.selectRecomendSiteListCnt(searchVO);
	}

	@Override
	public void insertRecomendSite(RecomendSiteVO recomendSiteVO) throws FdlException {
		String	recomendSiteId = idgenService.getNextStringId();
		recomendSiteVO.setRecomendSiteId(recomendSiteId);

    	egovRecomendSiteDao.insertRecomendSite(recomendSiteVO);
	}

	@Override
	public RecomendSiteVO selectRecomendSiteDetail(RecomendSiteVO recomendSiteVO) throws Exception {
		RecomendSiteVO resultVO = egovRecomendSiteDao.selectRecomendSiteDetail(recomendSiteVO);
        if (resultVO == null)
            throw processException("info.nodata.msg");
        return resultVO;
	}

	@Override
	public void updateRecomendSite(RecomendSiteVO recomendSiteVO) {
		egovRecomendSiteDao.updateRecomendSite(recomendSiteVO);
	}

	@Override
	public void deleteRecomendSite(RecomendSiteVO recomendSiteVO) {
		egovRecomendSiteDao.deleteRecomendSite(recomendSiteVO);
	}

}
