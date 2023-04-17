package egovframework.com.cop.bbs.service.impl;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import egovframework.com.cop.bbs.service.Board;
import egovframework.com.cop.bbs.service.BoardVO;
import egovframework.com.test.EgovTestV1;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@ContextConfiguration(classes = { EgovArticleDAOTest_Configuration.class })
public class EgovArticleDAOTest_selectArticleCnOne extends EgovTestV1 {

	@Autowired
	private EgovArticleDAO egovArticleDAO;

	@Autowired
	private EgovArticleDAOTest_AaaTestData egovArticleDAOTest_AaaTestData;

	@Test
//	@Commit
	public void test() throws Exception {
		log.debug("test");

		// given
		Board board = egovArticleDAOTest_AaaTestData.insertArticle();
		BoardVO boardVO = new BoardVO();
		boardVO.setBbsId(board.getBbsId());

		// when
		BoardVO articleCnOne = egovArticleDAO.selectArticleCnOne(boardVO);
		log.debug("articleCnOne={}", articleCnOne);
		log.debug("getNttCn={}", articleCnOne.getNttCn());

		// then
		assertEquals(articleCnOne.getNttCn(), board.getNttCn());
	}

}