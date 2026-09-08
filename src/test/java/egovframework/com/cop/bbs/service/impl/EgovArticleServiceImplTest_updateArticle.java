package egovframework.com.cop.bbs.service.impl;

import java.util.Date;

import org.egovframe.rte.fdl.string.EgovDateUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import egovframework.com.cop.bbs.service.BoardVO;
import egovframework.com.cop.bbs.service.EgovArticleService;
import egovframework.com.test.EgovTestV1;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@ContextConfiguration(classes = { EgovArticleServiceImplTest_AAB_Configuration.class })
class EgovArticleServiceImplTest_updateArticle extends EgovTestV1 {

	@Autowired
	private EgovArticleServiceImplTest_AAC_TestData egovArticleServiceImplTest_AAC_TestData;

	@Autowired
	private EgovArticleService egovArticleService;

	@Test
	void updateArticle() {
		// given
		BoardVO boardVO = egovArticleServiceImplTest_AAC_TestData.selectArticleList();

		String today = " 수정 " + EgovDateUtil.toString(new Date(), null, null);

		boardVO.setNttSj("test 게시물제목" + today); // 게시물제목

//		boardVO.setNttSj("");
//		boardVO.setNttCn("");
//		boardVO.setNtceBgnde("");
//		boardVO.setNtceEndde("");
//		boardVO.setLastUpdusrId("");
//		boardVO.setAtchFileId("");
//		boardVO.setSjBoldAt("");
//		boardVO.setNoticeAt("");
//		boardVO.setSecretAt("");
//		boardVO.setBbsId("");
//		boardVO.setNttId(0l);

		// when
		egovArticleService.updateArticle(boardVO);

		// then
	}

}