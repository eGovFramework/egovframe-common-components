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
public class EgovArticleServiceImplTest_selectArticleDetailCn extends EgovTestV1 {

	@Autowired
	private EgovArticleServiceImplTest_AAC_TestData egovArticleServiceImplTest_AAC_TestData;

	@Autowired
	private EgovArticleService egovArticleService;

	@Test
	public void test() throws FdlException {
		log.debug("test");

		// given
		BoardVO boardVO = egovArticleServiceImplTest_AAC_TestData.selectArticleList();

		// when
		List<BoardVO> articleDetailCn = egovArticleService.selectArticleDetailCn(boardVO);

		log.debug("nttSj={}", articleDetailCn.get(0).getNttSj());
		log.debug("ntcrId={}", articleDetailCn.get(0).getNtcrId());
		log.debug("ntcrNm={}", articleDetailCn.get(0).getNtcrNm());
		log.debug("nttNo={}", articleDetailCn.get(0).getNttNo());
		log.debug("nttCn={}", articleDetailCn.get(0).getNttCn());
		log.debug("password={}", articleDetailCn.get(0).getPassword());
		log.debug("frstRegisterId={}", articleDetailCn.get(0).getFrstRegisterId());
		log.debug("frstRegisterNm={}", articleDetailCn.get(0).getFrstRegisterNm());
//		log.debug("frstRegistPnttm={}", articleDetailCn.get(0).getFrstRegistPnttm());
		log.debug("ntceBgnde={}", articleDetailCn.get(0).getNtceBgnde());
		log.debug("ntceEndde={}", articleDetailCn.get(0).getNtceEndde());
//		log.debug("rdcnt={}", articleDetailCn.get(0).getRdcnt());
		log.debug("useAt={}", articleDetailCn.get(0).getUseAt());
		log.debug("atchFileId={}", articleDetailCn.get(0).getAtchFileId());
		log.debug("bbsId={}", articleDetailCn.get(0).getBbsId());
		log.debug("nttId={}", articleDetailCn.get(0).getNttId());
		log.debug("sjBoldAt={}", articleDetailCn.get(0).getSjBoldAt());
		log.debug("noticeAt={}", articleDetailCn.get(0).getNoticeAt());
		log.debug("secretAt={}", articleDetailCn.get(0).getSecretAt());
//		log.debug("parntscttNo={}", articleDetailCn.get(0).getParntscttNo());
//		log.debug("answerAt={}", articleDetailCn.get(0).getAnswerAt());
//		log.debug("answerLc={}", articleDetailCn.get(0).getAnswerLc());
		log.debug("sortOrdr={}", articleDetailCn.get(0).getSortOrdr());
		log.debug("bbsTyCode={}", articleDetailCn.get(0).getBbsTyCode());
		log.debug("replyPosblAt={}", articleDetailCn.get(0).getReplyPosblAt());
		log.debug("fileAtchPosblAt={}", articleDetailCn.get(0).getFileAtchPosblAt());
//		log.debug("atchPosblFileNumber={}", articleDetailCn.get(0).getAtchPosblFileNumber());
		log.debug("bbsNm={}", articleDetailCn.get(0).getBbsNm());
		log.debug("");

		log.debug("bbsId={}", articleDetailCn.get(0).getBbsId());
		log.debug("nttId={}", articleDetailCn.get(0).getNttId());
		log.debug("nttSj={}", articleDetailCn.get(0).getNttSj());
		log.debug("ntcrId={}", articleDetailCn.get(0).getNtcrId());
		log.debug("ntcrNm={}", articleDetailCn.get(0).getNtcrNm());
		log.debug("nttNo={}", articleDetailCn.get(0).getNttNo());
		log.debug("nttCn={}", articleDetailCn.get(0).getNttCn());
		log.debug("password={}", articleDetailCn.get(0).getPassword());
		log.debug("frstRegisterId={}", articleDetailCn.get(0).getFrstRegisterId());
		log.debug("frstRegisterNm={}", articleDetailCn.get(0).getFrstRegisterNm());
		log.debug("frstRegisterPnttm={}", articleDetailCn.get(0).getFrstRegisterPnttm());
		log.debug("ntceBgnde={}", articleDetailCn.get(0).getNtceBgnde());
		log.debug("ntceEndde={}", articleDetailCn.get(0).getNtceEndde());
		log.debug("inqireCo={}", articleDetailCn.get(0).getInqireCo());
		log.debug("useAt={}", articleDetailCn.get(0).getUseAt());
		log.debug("atchFileId={}", articleDetailCn.get(0).getAtchFileId());
		log.debug("parnts={}", articleDetailCn.get(0).getParnts());
		log.debug("replyAt={}", articleDetailCn.get(0).getReplyAt());
		log.debug("replyLc={}", articleDetailCn.get(0).getReplyLc());
		log.debug("sortOrdr={}", articleDetailCn.get(0).getSortOrdr());
		log.debug("bbsTyCode={}", articleDetailCn.get(0).getBbsTyCode());
		log.debug("replyPosblAt={}", articleDetailCn.get(0).getReplyPosblAt());
		log.debug("fileAtchPosblAt={}", articleDetailCn.get(0).getFileAtchPosblAt());
		log.debug("posblAtchFileNumber={}", articleDetailCn.get(0).getPosblAtchFileNumber());
		log.debug("bbsNm={}", articleDetailCn.get(0).getBbsNm());
		log.debug("sjBoldAt={}", articleDetailCn.get(0).getSjBoldAt());
		log.debug("noticeAt={}", articleDetailCn.get(0).getNoticeAt());
		log.debug("secretAt={}", articleDetailCn.get(0).getSecretAt());
		log.debug("commentCo={}", articleDetailCn.get(0).getCommentCo());
		log.debug("");

		// then
		assertEquals(articleDetailCn.get(0).getBbsId(), boardVO.getBbsId());
		assertEquals(articleDetailCn.get(0).getNttId(), boardVO.getNttId());
	}

}