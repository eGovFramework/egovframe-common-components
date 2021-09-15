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
import egovframework.com.cop.bbs.service.EgovBBSMasterService;
import egovframework.com.test.EgovTestV1;
import egovframework.rte.fdl.cmmn.exception.FdlException;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;
import egovframework.rte.fdl.string.EgovDateUtil;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@ContextConfiguration(classes = { EgovBBSMasterServiceImplTestConfiguration.class })
public class EgovBBSMasterServiceImplTest_insertBlogMaster extends EgovTestV1 {

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

	// given
	String today;
	LoginVO authenticatedUser;
	Blog blog;

	// when
	boolean result = false;

	@Test
	public void test() {
		log.debug("test");
		given();
		when();
		then();
	}

	void given() {
		today = " " + EgovDateUtil.toString(new Date(), null, null);
		authenticatedUser = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();

		blog = new Blog();
		try {
			blog.setBlogId(egovBlogIdGnrService.getNextStringId());
		} catch (FdlException e) {
			log.error(e.getMessage());
		}
		blog.setBlogNm("test 블로그 명" + today);
		blog.setBlogIntrcn("test 블로그 소개" + today);
		blog.setRegistSeCode("REGC02"); // 등록구분코드 COM001 REGC02 커뮤니티 등록
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
	}

	void when() {
		try {
			egovBBSMasterService.insertBlogMaster(blog);
			result = true;
		} catch (Exception e) {
			log.error(e.getMessage());
		}
	}

	void then() {
		assertEquals(result, true);
	}

}