package egovframework.com.cop.bbs.service.impl;

import static org.junit.Assert.assertTrue;

import egovframework.com.cop.bbs.service.Board;
import egovframework.com.cop.bbs.service.EgovArticleService;
import egovframework.com.test.EgovTestV1;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

@Slf4j
@ContextConfiguration(classes = {EgovArticleServiceImplTest_AAB_Configuration.class})
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