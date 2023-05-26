package egovframework.com.cop.bbs.service.impl;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.com.cop.bbs.service.Blog;
import egovframework.com.cop.bbs.service.BoardVO;
import egovframework.com.cop.bbs.service.EgovArticleService;
import egovframework.com.test.EgovTestV1;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@ContextConfiguration(classes = { EgovArticleServiceImplTest_AAB_Configuration.class })
public class EgovArticleServiceImplTest_selectLoginUser extends EgovTestV1 {

	@Autowired
	private EgovArticleDAOTest_AaaTestData egovArticleDAOTest_AaaTestData;

	@Autowired
	private EgovArticleService egovArticleService;

	@Test
	public void test() {
		log.debug("test");

		// given
		LoginVO loginVO = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
		Blog blog = null;
		blog = egovArticleDAOTest_AaaTestData.insertBlogMaster(loginVO);

		BoardVO boardVO = new BoardVO();
		boardVO.setFrstRegisterId(blog.getFrstRegisterId());
		boardVO.setBlogId(blog.getBlogId());

		// when
		int loginUser = egovArticleService.selectLoginUser(boardVO);

		log.debug("loginUser={}", loginUser);

		// then
		assertEquals(loginUser, 1);
	}

}