package egovframework.com.cop.bbs.service.impl;

import static org.junit.Assert.assertEquals;

import javax.annotation.Resource;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.com.cop.bbs.service.Blog;
import egovframework.com.cop.bbs.service.BoardVO;
import egovframework.com.test.EgovTestV1;

import org.egovframe.rte.fdl.cmmn.exception.FdlException;
import org.egovframe.rte.fdl.idgnr.EgovIdGnrService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@ContextConfiguration(classes = { EgovArticleDAOTest_Configuration.class })
public class EgovArticleDAOTest_selectLoginUser extends EgovTestV1 {

	@Resource(name = "egovBBSMstrIdGnrService")
	private EgovIdGnrService egovBBSMstrIdGnrService;

	@Resource(name = "egovNttIdGnrService")
	private EgovIdGnrService egovNttIdGnrService;

	@Resource(name = "egovBlogIdGnrService")
	private EgovIdGnrService egovBlogIdGnrService;

	@Autowired
	private EgovArticleDAO egovArticleDAO;
	
	@Autowired
	private EgovBBSMasterDAO egovBBSMasterDAO;
	
	@Autowired
	private EgovArticleDAOTest_AaaTestData egovArticleDAOTest_AaaTestData;

	@Test
//	@Commit
	public void test() throws Exception {
		log.debug("test");

		// given
		LoginVO loginVO = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();

//		Blog blog = insertBlogMaster(loginVO);
		Blog blog = egovArticleDAOTest_AaaTestData.insertBlogMaster(loginVO);
		
		BoardVO boardVO = new BoardVO();
		boardVO.setFrstRegisterId(blog.getFrstRegisterId());
		boardVO.setBlogId(blog.getBlogId());

		// when
		int loginUser = egovArticleDAO.selectLoginUser(boardVO);
		log.debug("loginUser={}", loginUser);

		// then
		assertEquals(loginUser, 1);
	}

	Blog insertBlogMaster(LoginVO loginVO) throws FdlException {
		Blog blog = new Blog();
		blog.setBlogId(egovBlogIdGnrService.getNextStringId());
//		blog.setBbsId(""); // 게시판 ID
		blog.setBlogIntrcn("test 블로그 소개");
		blog.setBlogNm("test 블로그 명");
		blog.setFrstRegisterId(loginVO.getUniqId()); // 최초등록자ID
//		blog.setFrstRegisterPnttm("");
//		blog.setLastUpdusrId("");
//		blog.setLastUpdusrPnttm("");
		blog.setRegistSeCode("REGC01"); // 등록구분코드 COM001 REGC01 단일 게시판 이용등록
//		blog.setTmplatId(""); // 템플릿 ID
		blog.setUseAt("Y"); // 사용여부
//		blog.setEmplyrId("");
//		blog.setUserNm("");
//		blog.setTmplatNm("");
		blog.setBlogAt("Y"); // 블로그 여부

		egovBBSMasterDAO.insertBlogMaster(blog);

		return blog;
	}

}