package egovframework.com.cop.scp.service;

import java.util.Map;

import egovframework.rte.fdl.cmmn.exception.FdlException;

public interface EgovArticleScrapService {

	Map<String, Object> selectArticleScrapList(ScrapVO scrapVO);

	void insertArticleScrap(Scrap scrap) throws FdlException;

	ScrapVO selectArticleScrapDetail(ScrapVO scrapVO);

	void deleteArticleScrap(ScrapVO scrapVO);

	void updateArticleScrap(Scrap scrap);

}
