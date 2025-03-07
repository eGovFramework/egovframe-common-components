package egovframework.com.cop.bbs.service.impl;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import egovframework.com.cop.bbs.service.Board;
import egovframework.com.cop.bbs.service.EgovArticleService;
import egovframework.com.test.EgovTestV1;
import lombok.extern.slf4j.Slf4j;
/**
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일			수정자		수정내용
 *  -------			--------	---------------------------
 *   2024.10.29		inganyoyo	Transaction 처리 오류 수정(Article)
 * </pre>
 */

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
		Board board = egovArticleServiceImplTest_AAC_TestData.insertArticle();

		boolean result = true;

		// when
		try {
      egovArticleService.insertArticleAndFiles(board, null);
    } catch (Exception e) {
			log.error(e.getMessage());
			result = false;
		}

		log.debug("result={}", result);

		// then
		assertTrue(result);
	}

}