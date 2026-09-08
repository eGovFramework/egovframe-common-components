package egovframework.com.cop.bbs.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import egovframework.com.cop.bbs.service.BoardVO;
import egovframework.com.test.EgovTestV1;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@ContextConfiguration(classes = { EgovArticleDAOTest_Configuration.class })
class EgovArticleDAOTest_selectArticleDetailDefaultCnt extends EgovTestV1 {

	@Autowired
	private EgovArticleDAO egovArticleDAO;

	@Autowired
	private EgovArticleDAOTest_AaaTestData egovArticleDAOTest_AaaTestData;

	@Test
	void selectArticleDetailDefaultCnt() {
		// given
		BoardVO boardVO = egovArticleDAOTest_AaaTestData.selectArticleDetailDefaultCnt();

		// when
		int articleDetailDefaultCnt = egovArticleDAO.selectArticleDetailDefaultCnt(boardVO);
		log.debug("articleDetailDefaultCnt={}", articleDetailDefaultCnt);

		// then
		assertEquals(articleDetailDefaultCnt, 1);
	}

}