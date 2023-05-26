package egovframework.com.cop.bbs.service.impl;

import static org.junit.Assert.assertEquals;

import java.util.List;
import java.util.Map;

import org.egovframe.rte.fdl.cmmn.exception.FdlException;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import egovframework.com.cop.bbs.service.BoardVO;
import egovframework.com.cop.bbs.service.EgovArticleService;
import egovframework.com.test.EgovTestV1;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@ContextConfiguration(classes = { EgovArticleServiceImplTest_AAB_Configuration.class })
public class EgovArticleServiceImplTest_selectArticleList extends EgovTestV1 {

	@Autowired
	private EgovArticleServiceImplTest_AAC_TestData egovArticleServiceImplTest_AAC_TestData;

	@Autowired
	private EgovArticleService egovArticleService;

	@Test
	public void test() throws FdlException {
		log.debug("test");

		// given
		BoardVO boardVO = egovArticleServiceImplTest_AAC_TestData.selectArticleList();
		boardVO.setFirstIndex(0);
		boardVO.setRecordCountPerPage(10);

		boardVO.setSearchCnd("0");
		boardVO.setSearchWrd(boardVO.getNttSj());

		// when
		Map<String, Object> result = egovArticleService.selectArticleList(boardVO);
		@SuppressWarnings("unchecked")
		List<BoardVO> resultList = (List<BoardVO>) result.get("resultList");
		String resultCnt = (String) result.get("resultCnt");

		log.debug("result={}", result);
		log.debug("resultList={}", resultList);
		log.debug("resultCnt={}", resultCnt);

		resultList.forEach(action -> {
			log.debug("getBbsId={}", action.getBbsId());
			log.debug("getNttSj={}", action.getNttSj());
		});

		// then
		assertEquals(boardVO.getBbsId(), resultList.get(0).getBbsId());
		assertEquals(boardVO.getNttSj(), resultList.get(0).getNttSj());
		assertEquals("1", resultCnt);
	}

}