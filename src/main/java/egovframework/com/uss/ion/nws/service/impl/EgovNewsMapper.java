package egovframework.com.uss.ion.nws.service.impl;

import java.util.List;

import org.egovframe.rte.psl.dataaccess.EgovMapper;

import egovframework.com.uss.ion.nws.service.NewsVO;

/**
 * 뉴스관리에 대한 Mapper 인터페이스
 * @version 1.0
 */
@EgovMapper("EgovNewsMapper")
public interface EgovNewsMapper {

	List<NewsVO> selectNewsList(NewsVO newsVO);

	int selectNewsListCnt(NewsVO newsVO);

	void insertNews(NewsVO newsVO);

	NewsVO selectNewsDetail(NewsVO newsVO);

	void updateNews(NewsVO newsVO);

	void deleteNews(NewsVO newsVO);
}
