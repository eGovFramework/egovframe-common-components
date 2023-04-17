package egovframework.com.cop.bbs.service.impl;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import egovframework.com.cop.bbs.service.BoardVO;
import egovframework.com.test.EgovTestV1;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@ContextConfiguration(classes = { EgovArticleDAOTest_Configuration.class })
public class EgovArticleDAOTest_selectBlogListManagerCnt extends EgovTestV1 {

	@Autowired
	private EgovArticleDAO egovArticleDAO;

	@Autowired
	private EgovArticleDAOTest_AaaTestData egovArticleDAOTest_AaaTestData;

	@Test
//	@Commit
	public void test() throws Exception {
		log.debug("test");

		// given
		BoardVO vo = egovArticleDAOTest_AaaTestData.selectBlogListManagerCnt();

//		vo.setSearchCnd("0");
//		vo.setSearchWrd("test 게시판명");
//		vo.setSearchWrd(vo.getBbsNm());

		vo.setSearchCnd("1");
//		vo.setSearchWrd("test 게시판소개");
		vo.setSearchWrd(vo.getSearchWrd());

		// when
		int blogListManagerCnt = egovArticleDAO.selectBlogListManagerCnt(vo);
		log.debug("blogListManagerCnt={}", blogListManagerCnt);

		// then
		assertEquals(blogListManagerCnt, 1);
	}

}