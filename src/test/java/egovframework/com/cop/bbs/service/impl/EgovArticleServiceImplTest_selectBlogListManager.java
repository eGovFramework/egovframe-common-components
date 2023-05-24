package egovframework.com.cop.bbs.service.impl;

import static org.junit.Assert.assertEquals;

import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import egovframework.com.cop.bbs.service.BoardMasterVO;
import egovframework.com.cop.bbs.service.BoardVO;
import egovframework.com.cop.bbs.service.EgovArticleService;
import egovframework.com.test.EgovTestV1;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@ContextConfiguration(classes = { EgovArticleServiceImplTest_AAB_Configuration.class })
public class EgovArticleServiceImplTest_selectBlogListManager extends EgovTestV1 {

	@Autowired
	private EgovArticleDAOTest_AaaTestData egovArticleDAOTest_AaaTestData;

	@Autowired
	private EgovArticleService egovArticleService;

	@SuppressWarnings("unchecked")
	@Test
	public void test() {
		log.debug("test");

		// given
		BoardVO boardVO = null;
		boardVO = egovArticleDAOTest_AaaTestData.selectBlogListManagerCnt();

		boardVO.setSearchCnd("0");
		boardVO.setSearchWrd("test 게시판명");
//		boardVO.setSearchWrd(vo.getSearchWrd());

		// when
		Map<String, Object> blogListManager = egovArticleService.selectBlogListManager(boardVO);
		List<BoardMasterVO> resultList = (List<BoardMasterVO>) blogListManager.get("resultList");
		String resultCnt = (String) blogListManager.get("resultCnt");

		log.debug("blogListManager={}", blogListManager);
		log.debug("resultList={}", resultList);
		log.debug("resultCnt={}", resultCnt);

		resultList.forEach(result -> {
			log.debug("bbsId={}", result.getBbsId());
			log.debug("bbsNm={}", result.getBbsNm());
			log.debug("useAt={}", result.getUseAt());
			log.debug("frstRegisterNm={}", result.getFrstRegisterNm());
			log.debug("frstRegisterPnttm={}", result.getFrstRegisterPnttm());
		});

		// then
		assertEquals(resultList.get(0).getBbsId(), boardVO.getBbsId());
		assertEquals(resultList.get(0).getBbsNm(), boardVO.getBbsNm());
		assertEquals(resultList.get(0).getUseAt(), boardVO.getUseAt());
		assertEquals(resultList.get(0).getFrstRegisterNm(), boardVO.getFrstRegisterNm());
		assertEquals(resultList.get(0).getFrstRegisterPnttm(), boardVO.getFrstRegisterPnttm());

		assertEquals(resultCnt, "1");
	}

}