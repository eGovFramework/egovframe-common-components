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
import egovframework.com.test.EgovTestV1;
import egovframework.rte.fdl.cmmn.exception.FdlException;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;
import egovframework.rte.fdl.string.EgovDateUtil;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@ContextConfiguration(classes = { EgovBBSMasterDAOTest_AAA_Configuration.class })
public class EgovBBSMasterDAOTest_insertBlogMaster extends EgovTestV1 {

	@Autowired
	private EgovBBSMasterDAO egovBBSMasterDAO;

//	@Resource(name = "egovBBSMstrIdGnrService")
//	private EgovIdGnrService egovBBSMstrIdGnrService;
//
//	@Resource(name = "egovNttIdGnrService")
//	private EgovIdGnrService egovNttIdGnrService;

	@Resource(name = "egovBlogIdGnrService")
	private EgovIdGnrService egovBlogIdGnrService;

	@Test
//	@Commit
	public void test() {
		log.debug("test");

		// given
		Blog blog = testData();

		// when
		boolean result = false;
		try {
			egovBBSMasterDAO.insertBlogMaster(blog);
			result = true;
		} catch (Exception e) {
			log.error(e.getMessage());
		}

		// then
		assertEquals(result, true);
	}

	public Blog testData() {
		String today = " " + EgovDateUtil.toString(new Date(), null, null);
		LoginVO authenticatedUser = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();

		Blog blog = new Blog();
		try {
			blog.setBlogId(egovBlogIdGnrService.getNextStringId());
		} catch (FdlException e) {
			log.error(e.getMessage());
		}

		blog.setBlogNm("test 블로그 명" + today);
		blog.setBlogIntrcn("test 블로그 명" + today);
		blog.setRegistSeCode("REGC02"); // 커뮤니티 등록
//		blog.setTmplatId("");
		blog.setUseAt("Y");
		blog.setFrstRegisterId(authenticatedUser.getUniqId());
//		blog.setBbsId("");
		blog.setBlogAt("Y");

		return blog;
	}

}