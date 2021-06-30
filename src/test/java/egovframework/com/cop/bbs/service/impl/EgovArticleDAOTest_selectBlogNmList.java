package egovframework.com.cop.bbs.service.impl;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import egovframework.com.cop.bbs.service.BoardVO;
import egovframework.com.test.EgovTestV1;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@ContextConfiguration(classes = { EgovArticleDAOTest_Configuration.class })
public class EgovArticleDAOTest_selectBlogNmList extends EgovTestV1 {

	@Autowired
	private EgovArticleDAO egovArticleDAO;

	@Autowired
	private EgovArticleDAOTest_AaaTestData egovArticleDAOTest_AaaTestData;

	@Test
//	@Commit
	public void test() throws Exception {
		log.debug("test");

		// given
		BoardVO boardVO = egovArticleDAOTest_AaaTestData.selectBlogListManagerCnt();

		// when
		List<BoardVO> blogNmList = egovArticleDAO.selectBlogNmList(boardVO);
		log.debug("blogNmList={}", blogNmList);

		for (BoardVO blogNm : blogNmList) {
			log.debug("getBbsId={}", blogNm.getBbsId());
			log.debug("getBbsNm={}", blogNm.getBbsNm());
		}

		// then
		assertEquals(blogNmList.size(), 1);

		for (BoardVO blogNm : blogNmList) {
			assertEquals(blogNm.getBbsId(), boardVO.getBbsId());
			assertEquals(blogNm.getBbsNm(), boardVO.getBbsNm());
		}
	}

}