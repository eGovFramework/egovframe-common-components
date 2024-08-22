package egovframework.com.cop.scp.service;

import java.util.Map;

public interface EgovArticleScrapService {

	Map<String, Object> selectArticleScrapList(ScrapVO scrapVO);

	void insertArticleScrap(Scrap scrap) throws Exception;

	ScrapVO selectArticleScrapDetail(ScrapVO scrapVO);

	void deleteArticleScrap(ScrapVO scrapVO);

	void updateArticleScrap(Scrap scrap);

}
