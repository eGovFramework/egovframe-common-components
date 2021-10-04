package egovframework.com.cop.bbs.service.impl;

import static org.junit.Assert.assertEquals;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;

import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.com.cop.bbs.service.Blog;
import egovframework.com.cop.bbs.service.BlogVO;
import egovframework.com.cop.bbs.service.BoardMaster;
import egovframework.com.cop.bbs.service.EgovBBSMasterService;
import egovframework.com.test.EgovTestV1;
import egovframework.rte.fdl.cmmn.exception.FdlException;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;
import egovframework.rte.fdl.string.EgovDateUtil;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@ContextConfiguration(classes = { EgovBBSMasterServiceImplTestConfiguration.class })
public class EgovBBSMasterServiceImplTest_selectBlogListPortlet extends EgovTestV1 {

	@Resource
	private EgovBBSMasterService egovBBSMasterService;

	@Resource
	private EgovBBSMasterDAO egovBBSMasterDAO;

	@Resource(name = "egovBBSMstrIdGnrService")
	private EgovIdGnrService egovBBSMstrIdGnrService;

//	@Resource(name = "egovNttIdGnrService")
//	private EgovIdGnrService egovNttIdGnrService;

	@Resource(name = "egovBlogIdGnrService")
	private EgovIdGnrService egovBlogIdGnrService;

//	@Resource(name = "egovTmplatIdGnrService")
//	private EgovIdGnrService egovTmplatIdGnrService;

	// testData
	String today;
	LoginVO authenticatedUser;
	Blog blog;
	BoardMaster boardMaster;

	// given
	BlogVO blogVO;

	// when
	List<BlogVO> blogListPortlet;

	@Test
	public void test() {
		log.debug("test");
		testData();
		testData2();
		given();
		when();
		then();
	}

	void testData() {
		today = " " + EgovDateUtil.toString(new Date(), null, null);
		authenticatedUser = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();

		// insertBBSMasterInf
		boardMaster = new BoardMaster();
		try {
			boardMaster.setBbsId(egovBBSMstrIdGnrService.getNextStringId());
		} catch (FdlException e) {
			log.error(e.getMessage());
		}
		boardMaster.setBbsTyCode("BBST01"); // COM101 BBST01 통합게시판
		boardMaster.setBbsNm("test 게시판명" + today); // 게시판명
		boardMaster.setBbsIntrcn("test 게시판소개" + today); // 게시판소개
		boardMaster.setReplyPosblAt("Y"); // 답장가능여부
		boardMaster.setFileAtchPosblAt("Y"); // 파일첨부가능여부
		boardMaster.setAtchPosblFileNumber(3); // 첨부가능파일숫자
		boardMaster.setTmplatId(""); // 템플릿ID
		boardMaster.setUseAt("Y"); // 사용여부
		boardMaster.setCmmntyId(""); // 커뮤니티ID
		boardMaster.setFrstRegisterId(authenticatedUser.getUniqId()); // 최초등록자ID
		try {
			boardMaster.setBlogId(egovBlogIdGnrService.getNextStringId()); // 블로그 ID
		} catch (FdlException e) {
			log.error(e.getMessage());
		}
		boardMaster.setBlogAt("Y"); // 블로그 여부

		egovBBSMasterDAO.insertBBSMasterInf(boardMaster);
	}

	void testData2() {
		today = " " + EgovDateUtil.toString(new Date(), null, null);
		authenticatedUser = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();

		// insertBlogMaster
		blog = new Blog();
		blog.setBlogId(boardMaster.getBlogId());
		blog.setBlogNm("test 블로그 명" + today);
//		blog.setBlogIntrcn("");
		blog.setRegistSeCode("REGC02"); // 커뮤니티 등록
//		blog.setTmplatId("");
		blog.setUseAt("Y");
		blog.setFrstRegisterId(authenticatedUser.getUniqId());
		blog.setBbsId(boardMaster.getBbsId());
//		blog.setBlogAt("");
		egovBBSMasterDAO.insertBlogMaster(blog);
	}

	void given() {
		blogVO = new BlogVO();
	}

	void when() {
		try {
			blogListPortlet = egovBBSMasterService.selectBlogListPortlet(blogVO);
		} catch (Exception e) {
			log.error(e.getMessage());
		}
	}

	void then() {
		log.debug("blogId={}", blog.getBlogId());
		log.debug("bbsId={}", blog.getBbsId());
		log.debug("blogNm={}", blog.getBlogNm());

		assertEquals(blogListPortlet.get(0).getBlogId(), blog.getBlogId());
		assertEquals(blogListPortlet.get(0).getBbsId(), blog.getBbsId());
		assertEquals(blogListPortlet.get(0).getBlogNm(), blog.getBlogNm());
	}

}