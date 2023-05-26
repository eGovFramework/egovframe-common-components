package egovframework.com.cop.bbs.service.impl;

import static org.junit.Assert.assertEquals;

import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import egovframework.com.cop.bbs.service.BoardVO;
import egovframework.com.cop.bbs.service.EgovArticleService;
import egovframework.com.test.EgovTestV1;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@ContextConfiguration(classes = { EgovArticleServiceImplTest_AAB_Configuration.class })
public class EgovArticleServiceImplTest_selectGuestArticleList extends EgovTestV1 {

	@Autowired
	private EgovArticleServiceImplTest_AAC_TestData egovArticleServiceImplTest_AAC_TestData;

	@Autowired
	private EgovArticleService egovArticleService;

	@Test
	public void test() {
		log.debug("test");

		// given
		BoardVO boardVO = egovArticleServiceImplTest_AAC_TestData.selectArticleList();

		boardVO.setFirstIndex(0);
		boardVO.setRecordCountPerPage(10);

		// when
		Map<String, Object> guestArticle = egovArticleService.selectGuestArticleList(boardVO);
		@SuppressWarnings("unchecked")
		List<BoardVO> resultList = (List<BoardVO>) guestArticle.get("resultList");
		String resultCnt = (String) guestArticle.get("resultCnt");

		resultList.forEach(result -> {
			log.debug("result={}", result);
			log.debug("nttId={}", result.getNttId());
			log.debug("nttSj={}", result.getNttSj());
//			log.debug("frstRegistPnttm={}", result.getFrstRegistPnttm());
			log.debug("nttCn={}", result.getNttCn());
			log.debug("useAt={}", result.getUseAt());
			log.debug("bbsId={}", result.getBbsId());
			log.debug("frstRegisterNm={}", result.getFrstRegisterNm());
			log.debug("frstRegisterId={}", result.getFrstRegisterId());
		});

		log.debug("resultCnt={}", resultCnt);

		// then
		assertEquals(resultList.get(0).getBbsId(), boardVO.getBbsId());
		assertEquals(resultList.get(0).getNttId(), boardVO.getNttId());
		assertEquals(resultCnt, "1");
	}

}