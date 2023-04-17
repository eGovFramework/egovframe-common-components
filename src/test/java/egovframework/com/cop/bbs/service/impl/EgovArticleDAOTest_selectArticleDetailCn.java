package egovframework.com.cop.bbs.service.impl;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import egovframework.com.cop.bbs.service.Board;
import egovframework.com.cop.bbs.service.BoardVO;
import egovframework.com.test.EgovTestV1;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@ContextConfiguration(classes = { EgovArticleDAOTest_Configuration.class })
public class EgovArticleDAOTest_selectArticleDetailCn extends EgovTestV1 {

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
		boardVO.setNttId(board.getNttId());

		// when
		List<BoardVO> articleDetailCn = egovArticleDAO.selectArticleDetailCn(boardVO);
		log.debug("articleDetailCn={}", articleDetailCn);
		articleDetailCn.forEach(article -> {
			log.debug("getBbsId={}", article.getBbsId());
			log.debug("getNttId={}", article.getNttId());
		});

		// then
		assertEquals(articleDetailCn.size(), 1);

		articleDetailCn.forEach(article -> {
			assertEquals(article.getBbsId(), board.getBbsId());
			assertEquals(article.getNttId(), board.getNttId());
		});
	}

}