package egovframework.com.cop.bbs.service.impl;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import egovframework.com.cop.bbs.service.BoardVO;
import egovframework.com.cop.bbs.service.EgovArticleService;
import egovframework.com.test.EgovTestV1;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@ContextConfiguration(classes = { EgovArticleServiceImplTest_AAB_Configuration.class })
public class EgovArticleServiceImplTest_selectNoticeArticleList extends EgovTestV1 {

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
		List<BoardVO> noticeArticleList = egovArticleService.selectNoticeArticleList(boardVO);

		noticeArticleList.forEach(noticeArticle -> {
			log.debug("bbsId={}", noticeArticle.getBbsId());
			log.debug("nttId={}", noticeArticle.getNttId());
			log.debug("nttSj={}", noticeArticle.getNttSj());
			log.debug("frstRegisterId={}", noticeArticle.getFrstRegisterId());
			log.debug("frstRegisterNm={}", noticeArticle.getFrstRegisterNm());
			log.debug("frstRegisterPnttm={}", noticeArticle.getFrstRegisterPnttm());
			log.debug("inqireCo={}", noticeArticle.getInqireCo());
			log.debug("parnts={}", noticeArticle.getParnts());
			log.debug("replyAt={}", noticeArticle.getReplyAt());
			log.debug("replyLc={}", noticeArticle.getReplyLc());
			log.debug("useAt={}", noticeArticle.getUseAt());
			log.debug("atchFileId={}", noticeArticle.getAtchFileId());
			log.debug("ntceBgnde={}", noticeArticle.getNtceBgnde());
			log.debug("ntceEndde={}", noticeArticle.getNtceEndde());
			log.debug("sjBoldAt={}", noticeArticle.getSjBoldAt());
			log.debug("noticeAt={}", noticeArticle.getNoticeAt());
			log.debug("secretAt={}", noticeArticle.getSecretAt());
			log.debug("commentCo={}", noticeArticle.getCommentCo());
		});

		// then
		assertEquals(noticeArticleList.get(0).getBbsId(), boardVO.getBbsId());
	}

}