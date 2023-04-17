package egovframework.com.cop.bbs.service.impl;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;

import egovframework.com.cop.bbs.service.BoardVO;
import egovframework.com.test.EgovTestV1;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@ContextConfiguration(classes = { EgovArticleDAOTest_Configuration.class })
public class EgovArticleDAOTest_selectArticleList extends EgovTestV1 {

	@Autowired
	private EgovArticleDAO egovArticleDAO;

	@SuppressWarnings("unchecked")
	@Test
	@Rollback(true)
//	@Rollback(false)
	public void test() throws Exception {
		log.debug("test");

		// given
		BoardVO boardVO = new BoardVO();
		boardVO.setBbsId("");

		boardVO.setSearchCnd("0");
//		boardVO.setSearchCnd("1");
//		boardVO.setSearchCnd("2");

		boardVO.setSearchWrd("");
		boardVO.setRecordCountPerPage(10);
		boardVO.setFirstIndex(0);

		// when
//		List<?> selectArticleList = egovArticleDAO.selectArticleList(boardVO);
		List<BoardVO> selectArticleList = (List<BoardVO>) egovArticleDAO.selectArticleList(boardVO);
		int size = selectArticleList.size();

		log.debug("selectArticleList={}", selectArticleList);
		log.debug("size={}", size);

		for (BoardVO selectArticle : selectArticleList) {
			log.debug("selectArticle={}", selectArticle);
			log.debug("getBbsId={}", selectArticle.getBbsId());
			log.debug("getNttId={}", selectArticle.getNttId());
			log.debug("getNttSj={}", selectArticle.getNttSj());
		}

		// then
		assertEquals(true, true);
	}

}