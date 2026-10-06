package egovframework.com.cop.bbs.service.impl;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import egovframework.com.cop.bbs.service.BoardVO;
import egovframework.com.test.EgovTestV1;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@ContextConfiguration(classes = { EgovArticleDAOTest_Configuration.class })
class EgovArticleDAOTest_selectArticleListCnt extends EgovTestV1 {

	@Autowired
	private EgovArticleDAO egovArticleDAO;

	@Test
	void selectArticleListCnt() {
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
		assertTrue(selectArticleListCnt >= 0);
	}

}