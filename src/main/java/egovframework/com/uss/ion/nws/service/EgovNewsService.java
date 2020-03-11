package egovframework.com.uss.ion.nws.service;

import java.util.List;

import egovframework.rte.fdl.cmmn.exception.FdlException;

public interface EgovNewsService {

	List<?> selectNewsList(NewsVO searchVO);

	int selectNewsListCnt(NewsVO searchVO);

	void insertNews(NewsVO newsVO) throws FdlException;

	NewsVO selectNewsDetail(NewsVO newsVO) throws Exception;

	void updateNews(NewsVO newsVO);

	void deleteNews(NewsVO newsVO);

}
