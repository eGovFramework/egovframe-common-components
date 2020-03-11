package egovframework.com.uss.ion.nws.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.com.cmm.service.impl.EgovComAbstractDAO;
import egovframework.com.uss.ion.nws.service.NewsVO;

@Repository("EgovNewsDAO")
public class EgovNewsDAO extends EgovComAbstractDAO {

	public List<?> selectNewsList(NewsVO searchVO) {
		return list("NewsManage.selectNewsList", searchVO);
	}

	public int selectNewsListCnt(NewsVO searchVO) {
		return (Integer) selectOne("NewsManage.selectNewsListCnt", searchVO);
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
