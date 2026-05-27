package egovframework.com.cop.scp.service.impl;

import java.util.List;

import jakarta.annotation.Resource;

import org.springframework.stereotype.Repository;

import egovframework.com.cop.scp.service.Scrap;
import egovframework.com.cop.scp.service.ScrapVO;

@Repository("EgovArticleScrapDAO")
public class EgovArticleScrapDAO {

	@Resource(name = "egovArticleScrapMapper")
	private EgovArticleScrapMapper egovArticleScrapMapper;

	public List<ScrapVO> selectArticleScrapList(ScrapVO scrapVO) {
		return egovArticleScrapMapper.selectArticleScrapList(scrapVO);
	}

	public int selectArticleScrapListCnt(ScrapVO scrapVO) {
		return egovArticleScrapMapper.selectArticleScrapListCnt(scrapVO);
	}

	public void insertArticleScrap(Scrap scrap) {
		egovArticleScrapMapper.insertArticleScrap(scrap);
	}

	public ScrapVO selectArticleScrapDetail(ScrapVO scrapVO) {
		return egovArticleScrapMapper.selectArticleScrapDetail(scrapVO);
	}

	public void deleteArticleScrap(ScrapVO scrapVO) {
		egovArticleScrapMapper.deleteArticleScrap(scrapVO);
	}

	public void updateArticleScrap(Scrap scrap) {
		egovArticleScrapMapper.updateArticleScrap(scrap);
	}

}
