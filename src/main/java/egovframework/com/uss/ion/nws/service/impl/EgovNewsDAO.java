package egovframework.com.uss.ion.nws.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.com.uss.ion.nws.service.NewsVO;
import jakarta.annotation.Resource;

@Repository("EgovNewsDAO")
public class EgovNewsDAO {

	@Resource(name = "EgovNewsMapper")
	private EgovNewsMapper egovNewsMapper;

	public List<NewsVO> selectNewsList(NewsVO newsVO) {
		return egovNewsMapper.selectNewsList(newsVO);
	}

	public int selectNewsListCnt(NewsVO newsVO) {
		return egovNewsMapper.selectNewsListCnt(newsVO);
	}

	public void insertNews(NewsVO newsVO) {
		egovNewsMapper.insertNews(newsVO);
	}

	public NewsVO selectNewsDetail(NewsVO newsVO) {
		return egovNewsMapper.selectNewsDetail(newsVO);
	}

	public void updateNews(NewsVO newsVO) {
		egovNewsMapper.updateNews(newsVO);
	}

	public void deleteNews(NewsVO newsVO) {
		egovNewsMapper.deleteNews(newsVO);
	}
}
