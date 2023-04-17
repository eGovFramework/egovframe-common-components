package egovframework.com.cop.bbs.service.impl;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import egovframework.com.cop.bbs.service.BoardVO;
import egovframework.com.cop.bbs.service.EgovArticleService;
import egovframework.com.test.EgovTestV1;
import org.egovframe.rte.fdl.cmmn.exception.FdlException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@ContextConfiguration(classes = { EgovArticleServiceImplTest_AAB_Configuration.class })
public class EgovArticleServiceImplTest_selectArticleDetailDefault extends EgovTestV1 {

	@Autowired
	private EgovArticleServiceImplTest_AAC_TestData egovArticleServiceImplTest_AAC_TestData;

	@Autowired
	private EgovArticleService egovArticleService;

	@Test
	public void test() throws FdlException {
		log.debug("test");

		// given
		BoardVO boardVO = egovArticleServiceImplTest_AAC_TestData.selectArticleList();
		boardVO.setSearchCnd("5");

		log.debug("getBbsId={}", boardVO.getBbsId());
		
		log.debug("nttId={}", boardVO.getNttId());
		log.debug("nttSj={}", boardVO.getNttSj());
		log.debug("nttCn={}", boardVO.getNttCn());
		log.debug("frstRegisterId={}", boardVO.getFrstRegisterId());
		log.debug("frstRegisterNm={}", boardVO.getFrstRegisterNm());
//		log.debug("frstRegistPnttm={}", boardVO.getFrstRegistPnttm());
//		log.debug("rdcnt={}", boardVO.getRdcnt());
//		log.debug("parntscttNo={}", boardVO.getParntscttNo());
//		log.debug("answerAt={}", boardVO.getAnswerAt());
//		log.debug("answerLc={}", boardVO.getAnswerLc());
		log.debug("useAt={}", boardVO.getUseAt());
		log.debug("atchFileId={}", boardVO.getAtchFileId());
		log.debug("bbsId={}", boardVO.getBbsId());
		log.debug("ntceBgnde={}", boardVO.getNtceBgnde());
		log.debug("ntceEndde={}", boardVO.getNtceEndde());
		log.debug("sjBoldAt={}", boardVO.getSjBoldAt());
		log.debug("noticeAt={}", boardVO.getNoticeAt());
		log.debug("secretAt={}", boardVO.getSecretAt());
		log.debug("commentCo={}", boardVO.getCommentCo());
		log.debug("ntcrId={}", boardVO.getNtcrId());
		log.debug("blogId={}", boardVO.getBlogId());
		log.debug("replyPosblAt={}", boardVO.getReplyPosblAt());

		// when
		List<BoardVO> results = egovArticleService.selectArticleDetailDefault(boardVO);

		// then
		assertEquals(boardVO.getBbsId(), results.get(0).getBbsId());
	}

}