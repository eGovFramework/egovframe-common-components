package egovframework.com.cop.bbs.service.impl;

import static org.junit.Assert.assertEquals;

import javax.annotation.Resource;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.ContextConfiguration;

import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.com.cop.bbs.service.Blog;
import egovframework.com.cop.bbs.service.BlogUser;
import egovframework.com.test.EgovTestV1;
import egovframework.rte.fdl.cmmn.exception.FdlException;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@ContextConfiguration(classes = { EgovBBSMasterDAOTest_AAA_Configuration.class })
public class EgovBBSMasterDAOTest_insertBoardBlogUserRqst extends EgovTestV1 {

	@Autowired
	private EgovBBSMasterDAO egovBBSMasterDAO;

	@Resource(name = "egovBBSMstrIdGnrService")
	private EgovIdGnrService egovBBSMstrIdGnrService;

	@Resource(name = "egovNttIdGnrService")
	private EgovIdGnrService egovNttIdGnrService;

	@Resource(name = "egovBlogIdGnrService")
	private EgovIdGnrService egovBlogIdGnrService;

	@Test
//	@Commit
	public void test() {
		log.debug("test");

		// given
		BlogUser blogUser = testData();

		// when
		boolean result = false;
		try {
			egovBBSMasterDAO.insertBoardBlogUserRqst(blogUser);
			result = true;
		} catch (Exception e) {
			log.error(e.getMessage());
		}

		// then
		assertEquals(result, true);
	}

	public BlogUser testData() {
		LoginVO loginVO = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();

		Blog blog = new Blog();
		try {
			blog.setBlogId(egovBlogIdGnrService.getNextStringId());
		} catch (FdlException e) {
			log.error(e.getMessage());
		}

		egovBBSMasterDAO.insertBlogMaster(blog);

		BlogUser blogUser = new BlogUser();
		blogUser.setBlogId(blog.getBlogId());
		blogUser.setEmplyrId(loginVO.getUniqId());
		blogUser.setMngrAt("Y");

//		blogUser.setMberSttus("A"); // 회원 가입 신청 상태
//		blogUser.setMberSttus("D"); // 회원 가입 삭제 상태
		blogUser.setMberSttus("P"); // 회원 가입 승인 상태

		blogUser.setUseAt("Y");
		blogUser.setFrstRegisterId(loginVO.getUniqId());

		return blogUser;
	}

}