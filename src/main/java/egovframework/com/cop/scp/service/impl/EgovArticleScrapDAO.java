package egovframework.com.cop.scp.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.com.cmm.service.impl.EgovComAbstractDAO;
import egovframework.com.cop.bbs.service.BoardVO;
import egovframework.com.cop.scp.service.Scrap;
import egovframework.com.cop.scp.service.ScrapVO;

@Repository("EgovArticleScrapDAO")
public class EgovArticleScrapDAO extends EgovComAbstractDAO{

	public List<?> selectArticleScrapList(ScrapVO scrapVO) {
		return list("ArticleScrap.selectArticleScrapList", scrapVO);
	}

	public int selectArticleScrapListCnt(ScrapVO scrapVO) {
		return (Integer)selectOne("ArticleScrap.selectArticleScrapListCnt", scrapVO);
	}

	public void insertArticleScrap(Scrap scrap) {
		insert("ArticleScrap.insertArticleScrap", scrap);
	}

	public ScrapVO selectArticleScrapDetail(ScrapVO scrapVO) {
		return (ScrapVO) selectOne("ArticleScrap.selectArticleScrapDetail", scrapVO);
	}

	public void deleteArticleScrap(ScrapVO scrapVO) {
		update("ArticleScrap.deleteArticleScrap", scrapVO);
	}

	public void updateArticleScrap(Scrap scrap) {
		update("ArticleScrap.updateArticleScrap", scrap);
	}

}
