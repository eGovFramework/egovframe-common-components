package egovframework.com.uss.ion.nws.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.com.uss.ion.nws.service.EgovNewsService;
import egovframework.com.uss.ion.nws.service.NewsVO;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.fdl.cmmn.exception.FdlException;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;

@Service("EgovNewsService")
public class EgovNewsServiceImpl extends EgovAbstractServiceImpl implements EgovNewsService {

	@Resource(name="EgovNewsDAO")
    private EgovNewsDAO egovNewsDao;

    /** ID Generation */
	@Resource(name="egovNewsManageIdGnrService")
	private EgovIdGnrService idgenService;
	
	@Override
	public List<?> selectNewsList(NewsVO searchVO) {
		return egovNewsDao.selectNewsList(searchVO);
	}

	@Override
	public int selectNewsListCnt(NewsVO searchVO) {
		return egovNewsDao.selectNewsListCnt(searchVO);
	}

	@Override
	public void insertNews(NewsVO newsVO) throws FdlException {
		String newsId = idgenService.getNextStringId();
		newsVO.setNewsId(newsId);

    	egovNewsDao.insertNews(newsVO);
	}

	@Override
	public NewsVO selectNewsDetail(NewsVO newsVO) throws Exception {
		NewsVO resultVO = egovNewsDao.selectNewsDetail(newsVO);
        if (resultVO == null)
            throw processException("info.nodata.msg");
        return resultVO;
	}

	@Override
	public void updateNews(NewsVO newsVO) {
		egovNewsDao.updateNews(newsVO);
	}

	@Override
	public void deleteNews(NewsVO newsVO) {
		egovNewsDao.deleteNews(newsVO);
	}

}
