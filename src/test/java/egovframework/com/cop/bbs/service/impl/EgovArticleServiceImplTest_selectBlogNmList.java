package egovframework.com.cop.bbs.service.impl;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.ContextConfiguration;

import egovframework.com.cop.bbs.service.BoardVO;
import egovframework.com.cop.bbs.service.EgovArticleService;
import egovframework.com.test.EgovTestV1;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@ContextConfiguration(classes = { EgovArticleServiceImplTest_AAB_Configuration.class })
public class EgovArticleServiceImplTest_selectBlogNmList extends EgovTestV1 {

	@Autowired
	private EgovArticleDAOTest_AaaTestData egovArticleDAOTest_AaaTestData;

	@Autowired
	private EgovArticleService egovArticleService;

	@Test
	@Commit
	public void test() {
		log.debug("test");

		// given
		BoardVO boardVO = null;
		boardVO = egovArticleDAOTest_AaaTestData.selectBlogListManagerCnt();

		// when
		List<BoardVO> blogNmList = egovArticleService.selectBlogNmList(boardVO);

		blogNmList.forEach(blogNm -> {
			log.debug("bbsId={}", blogNm.getBbsId());
			log.debug("blogId={}", blogNm.getBlogId());
			log.debug("bbsNm={}", blogNm.getBbsNm());
		});

		// then
		assertEquals(blogNmList.get(0).getBbsId(), boardVO.getBbsId());
		assertEquals(blogNmList.get(0).getBlogId(), boardVO.getBlogId());
		assertEquals(blogNmList.get(0).getBbsNm(), boardVO.getBbsNm());
	}

}