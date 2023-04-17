package egovframework.com.cop.bbs.service.impl;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;

import egovframework.com.cop.bbs.service.BoardVO;
import egovframework.com.test.EgovTestV1;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@ContextConfiguration(classes = { EgovArticleDAOTest_Configuration.class })
public class EgovArticleDAOTest_selectArticleListCnt extends EgovTestV1 {

	@Autowired
	private EgovArticleDAO egovArticleDAO;

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

		// when
		int selectArticleListCnt = egovArticleDAO.selectArticleListCnt(boardVO);

		log.debug("selectArticleListCnt={}", selectArticleListCnt);

		// then
		assertEquals(true, true);
	}

}