package egovframework.com.cop.bbs.service.impl;

import static org.junit.Assert.assertEquals;

import java.util.Date;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import egovframework.com.cop.bbs.service.BoardVO;
import egovframework.com.cop.bbs.service.EgovArticleService;
import egovframework.com.test.EgovTestV1;
import org.egovframe.rte.fdl.string.EgovDateUtil;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@ContextConfiguration(classes = { EgovArticleServiceImplTest_AAB_Configuration.class })
public class EgovArticleServiceImplTest_updateArticle extends EgovTestV1 {

	@Autowired
	private EgovArticleServiceImplTest_AAC_TestData egovArticleServiceImplTest_AAC_TestData;

	@Autowired
	private EgovArticleService egovArticleService;

	@Test
	public void test() {
		log.debug("test");

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
		boolean result = true;
		try {
			egovArticleService.updateArticle(boardVO);
		} catch (Exception e) {
			log.error(e.getMessage());
			result = false;
		}

		// then
		assertEquals(result, true);
	}

}