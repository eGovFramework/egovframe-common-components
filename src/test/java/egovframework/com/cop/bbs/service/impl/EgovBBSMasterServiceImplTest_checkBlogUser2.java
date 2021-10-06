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
import egovframework.com.cop.bbs.service.BlogVO;
import egovframework.com.cop.bbs.service.EgovBBSMasterService;
import egovframework.com.test.EgovTestV1;
import egovframework.rte.fdl.cmmn.exception.FdlException;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;
import egovframework.rte.fdl.string.EgovDateUtil;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@ContextConfiguration(classes = { EgovBBSMasterServiceImplTestConfiguration.class })
public class EgovBBSMasterServiceImplTest_checkBlogUser2 extends EgovTestV1 {

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

	BlogVO blogVO;

	Blog blog;
	BlogUser blogUser;

	// when
	BlogVO checkBlogUser2;

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
	}

	void testData2() {
		blog = new Blog();

		try {
			blog.setBlogId(egovBlogIdGnrService.getNextStringId());
		} catch (FdlException e) {
			log.error(e.getMessage());
		}

		blog.setFrstRegisterId(authenticatedUser.getUniqId());

		egovBBSMasterDAO.insertBlogMaster(blog);
	}

	void given() {
		blogVO = new BlogVO();
		blogVO.setFrstRegisterId(authenticatedUser.getUniqId());
	}

	void when() {
		checkBlogUser2 = egovBBSMasterService.checkBlogUser2(blogVO);
	}

	void then() {
		log.debug("frstRegisterId={}", blogVO.getFrstRegisterId());

		assertEquals(checkBlogUser2.getFrstRegisterId(), blogVO.getFrstRegisterId());
	}

}