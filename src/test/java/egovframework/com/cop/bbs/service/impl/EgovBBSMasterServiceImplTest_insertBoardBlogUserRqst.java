package egovframework.com.cop.bbs.service.impl;

import static org.junit.Assert.assertEquals;

import java.util.Date;

import javax.annotation.Resource;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.com.cop.bbs.service.Blog;
import egovframework.com.cop.bbs.service.BlogUser;
import egovframework.com.cop.bbs.service.EgovBBSMasterService;
import egovframework.com.test.EgovTestV1;
import egovframework.rte.fdl.cmmn.exception.FdlException;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;
import egovframework.rte.fdl.string.EgovDateUtil;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@ContextConfiguration(classes = { EgovBBSMasterServiceImplTestConfiguration.class })
public class EgovBBSMasterServiceImplTest_insertBoardBlogUserRqst extends EgovTestV1 {

	@Autowired
	private EgovBBSMasterService egovBBSMasterService;

	@Resource(name = "egovBBSMstrIdGnrService")
	private EgovIdGnrService egovBBSMstrIdGnrService;

//	@Resource(name = "egovNttIdGnrService")
//	private EgovIdGnrService egovNttIdGnrService;

	@Resource(name = "egovBlogIdGnrService")
	private EgovIdGnrService egovBlogIdGnrService;

	@Resource(name = "egovTmplatIdGnrService")
	private EgovIdGnrService egovTmplatIdGnrService;

	@Autowired
	private EgovBBSMasterDAO egovBBSMasterDAO;

	// given
	String today;
	LoginVO authenticatedUser;
	Blog blog;
	BlogUser blogUser;

	// when
	boolean result = false;

	@Test
	public void test() {
		log.debug("test");
		given();
		given2();
		given3();
		when();
		then();
	}

	void given() {
		today = " " + EgovDateUtil.toString(new Date(), null, null);
		authenticatedUser = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
	}

	void given2() {
		blog = new Blog();
		try {
			blog.setBlogId(egovBlogIdGnrService.getNextStringId());
		} catch (FdlException e) {
			log.error(e.getMessage());
		}

		blog.setBlogNm("test 블로그 명" + today);
		blog.setBlogIntrcn("test 블로그 소개" + today);
//		blog.setRegistSeCode("");
		try {
			blog.setTmplatId(egovTmplatIdGnrService.getNextStringId());
		} catch (FdlException e) {
			log.error(e.getMessage());
		}
		blog.setUseAt("Y");
		blog.setFrstRegisterId(authenticatedUser.getUniqId());
		try {
			blog.setBbsId(egovBBSMstrIdGnrService.getNextStringId());
		} catch (FdlException e) {
			log.error(e.getMessage());
		}
		blog.setBlogAt("Y");

		egovBBSMasterDAO.insertBlogMaster(blog);
	}

	void given3() {
		blogUser = new BlogUser();
		blogUser.setBlogId(blog.getBlogId());
		blogUser.setEmplyrId(authenticatedUser.getUniqId());
		blogUser.setMngrAt("Y");

//		blogUser.setMberSttus("A"); // 회원 가입 신청 상태
//		blogUser.setMberSttus("D"); // 회원 가입 삭제 상태
		blogUser.setMberSttus("P"); // 회원 가입 승인 상태

		blogUser.setUseAt("Y");
		blogUser.setFrstRegisterId(authenticatedUser.getUniqId());
	}

	void when() {
		try {
			egovBBSMasterService.insertBoardBlogUserRqst(blogUser);
			result = true;
		} catch (Exception e) {
			log.error(e.getMessage());
		}
	}

	void then() {
		assertEquals(result, true);
	}

}