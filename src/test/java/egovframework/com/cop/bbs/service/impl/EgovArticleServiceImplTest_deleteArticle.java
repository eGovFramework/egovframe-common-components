package egovframework.com.cop.bbs.service.impl;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import egovframework.com.cop.bbs.service.BoardVO;
import egovframework.com.cop.bbs.service.EgovArticleService;
import egovframework.com.test.EgovTestV1;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@ContextConfiguration(classes = { EgovArticleServiceImplTest_AAB_Configuration.class })
public class EgovArticleServiceImplTest_deleteArticle extends EgovTestV1 {

	@Autowired
	private EgovArticleServiceImplTest_AAC_TestData egovArticleServiceImplTest_AAC_TestData;

	@Autowired
	private EgovArticleService egovArticleService;

	@Test
	public void test() {
		log.debug("test");

		// given
		BoardVO boardVO = egovArticleServiceImplTest_AAC_TestData.selectArticleList();

		// when
		boolean result = true;
		try {
			egovArticleService.deleteArticle(boardVO);
		} catch (Exception e) {
			log.error(e.getMessage());
			result = false;
		}

		// then
		assertEquals(result, true);
	}

}