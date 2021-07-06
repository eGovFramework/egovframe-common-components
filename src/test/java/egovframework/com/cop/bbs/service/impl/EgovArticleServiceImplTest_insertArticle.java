package egovframework.com.cop.bbs.service.impl;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import egovframework.com.cop.bbs.service.Board;
import egovframework.com.cop.bbs.service.BoardMaster;
import egovframework.com.cop.bbs.service.EgovArticleService;
import egovframework.com.test.EgovTestV1;
import egovframework.rte.fdl.cmmn.exception.FdlException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@ContextConfiguration(classes = { EgovArticleServiceImplTest_AAB_Configuration.class })
public class EgovArticleServiceImplTest_insertArticle extends EgovTestV1 {

	@Autowired
	private EgovArticleServiceImplTest_AAC_TestData egovArticleServiceImplTest_AAC_TestData;

	@Autowired
	private EgovArticleService egovArticleService;

	@Test
	public void test() {
		log.debug("test");

		// given
		BoardMaster boardMaster = egovArticleServiceImplTest_AAC_TestData.insertBBSMasterInf();

		Board board = new Board();
		board.setBbsId(boardMaster.getBbsId());

		boolean result = true;

		// when
		try {
			egovArticleService.insertArticle(board);
		} catch (FdlException e) {
			log.error(e.getMessage());
			result = false;
		}

		log.debug("result={}", result);

		// then
		assertTrue(result);
	}

}