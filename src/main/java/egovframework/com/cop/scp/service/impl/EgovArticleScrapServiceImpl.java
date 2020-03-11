package egovframework.com.cop.scp.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.com.cop.scp.service.EgovArticleScrapService;
import egovframework.com.cop.scp.service.Scrap;
import egovframework.com.cop.scp.service.ScrapVO;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.fdl.cmmn.exception.FdlException;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;

@Service("EgovArticleScrapService")
public class EgovArticleScrapServiceImpl extends EgovAbstractServiceImpl implements EgovArticleScrapService{

	@Resource(name = "EgovArticleScrapDAO")
    private EgovArticleScrapDAO egovArticleScrapDao;
    
    @Resource(name="egovScrapIdGnrService")
    private EgovIdGnrService idgenService;
	
	@Override
	public Map<String, Object> selectArticleScrapList(ScrapVO scrapVO) {
		List<?> result = egovArticleScrapDao.selectArticleScrapList(scrapVO);
		int cnt = egovArticleScrapDao.selectArticleScrapListCnt(scrapVO);
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("resultList", result);
		map.put("resultCnt", Integer.toString(cnt));

		return map;
	}

	@Override
	public void insertArticleScrap(Scrap scrap) throws FdlException {
		
		String scrapId = idgenService.getNextStringId();
		scrap.setScrapId(scrapId);
		
		egovArticleScrapDao.insertArticleScrap(scrap);
	}

	@Override
	public ScrapVO selectArticleScrapDetail(ScrapVO scrapVO) {
		return egovArticleScrapDao.selectArticleScrapDetail(scrapVO);
	}

	@Override
	public void deleteArticleScrap(ScrapVO scrapVO) {
		egovArticleScrapDao.deleteArticleScrap(scrapVO);
	}

	@Override
	public void updateArticleScrap(Scrap scrap) {
		egovArticleScrapDao.updateArticleScrap(scrap);
	}

}
