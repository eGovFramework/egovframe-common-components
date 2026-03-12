package egovframework.com.uss.ion.nws.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.com.cmm.service.impl.EgovComAbstractDAO;
import egovframework.com.uss.ion.nws.service.NewsVO;

@Repository("EgovNewsDAO")
public class EgovNewsDAO extends EgovComAbstractDAO {

	public List<NewsVO> selectNewsList(NewsVO newsVO) {
		return selectList("NewsManage.selectNewsList", newsVO);
	}

	public int selectNewsListCnt(NewsVO newsVO) {
		return (Integer) selectOne("NewsManage.selectNewsListCnt", newsVO);
	}

	public void insertNews(NewsVO newsVO) {
		insert("NewsManage.insertNews", newsVO);
	}

	public NewsVO selectNewsDetail(NewsVO newsVO) {
		return (NewsVO) selectOne("NewsManage.selectNewsDetail", newsVO);
	}

	public void updateNews(NewsVO newsVO) {
		update("NewsManage.updateNews", newsVO);
	}

	public void deleteNews(NewsVO newsVO) {
		delete("NewsManage.deleteNews", newsVO);
	}

}
