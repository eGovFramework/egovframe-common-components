package egovframework.com.cop.bbs.service.impl;

import static org.junit.Assert.assertEquals;

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
public class EgovArticleServiceImplTest_selectArticleDetail extends EgovTestV1 {

	@Autowired
	private EgovArticleServiceImplTest_AAC_TestData egovArticleServiceImplTest_AAC_TestData;

	@Autowired
	private EgovArticleService egovArticleService;

	@Test
	public void test() throws FdlException {
		log.debug("test");

		// given
		BoardVO boardVO = egovArticleServiceImplTest_AAC_TestData.selectArticleList();

		// selectMaxInqireCo
		log.debug("getBbsId={}", boardVO.getBbsId());
		log.debug("getNttId={}", boardVO.getNttId());

		// updateInqireCo
		log.debug("getLastUpdusrId={}", boardVO.getLastUpdusrId());

		// when
		BoardVO result = egovArticleService.selectArticleDetail(boardVO);

		// then
		assertEquals(boardVO.getBbsId(), result.getBbsId());
		assertEquals(boardVO.getNttId(), result.getNttId());
		assertEquals(boardVO.getNttSj(), result.getNttSj());
	}

}