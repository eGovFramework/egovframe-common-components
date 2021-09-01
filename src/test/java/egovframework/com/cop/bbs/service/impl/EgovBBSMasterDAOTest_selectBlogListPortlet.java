package egovframework.com.cop.bbs.service.impl;

import static org.junit.Assert.assertEquals;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.com.cop.bbs.service.BlogVO;
import egovframework.com.test.EgovTestV1;
import egovframework.rte.fdl.cmmn.exception.FdlException;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;
import egovframework.rte.fdl.string.EgovDateUtil;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@ContextConfiguration(classes = { EgovBBSMasterDAOTest_AAA_Configuration.class })
public class EgovBBSMasterDAOTest_selectBlogListPortlet extends EgovTestV1 {

	@Autowired
	private EgovBBSMasterDAO egovBBSMasterDAO;

	@Resource(name = "egovBBSMstrIdGnrService")
	private EgovIdGnrService egovBBSMstrIdGnrService;

//	@Resource(name = "egovNttIdGnrService")
//	private EgovIdGnrService egovNttIdGnrService;

	@Resource(name = "egovBlogIdGnrService")
	private EgovIdGnrService egovBlogIdGnrService;

	@Test
//	@Commit
	public void test() {
		log.debug("test");

		// given
		BlogVO blogVO = testData();

		// when
		List<BlogVO> blogListPortlet = null;
		try {
			blogListPortlet = egovBBSMasterDAO.selectBlogListPortlet(blogVO);
		} catch (Exception e) {
			log.error(e.getMessage());
		}

		// then
		assertEquals(blogListPortlet.get(0).getBlogId(), blogVO.getBlogId());
		assertEquals(blogListPortlet.get(0).getBbsId(), blogVO.getBbsId());
		assertEquals(blogListPortlet.get(0).getBlogNm(), blogVO.getBlogNm());
	}

	public BlogVO testData() {
		String today = " " + EgovDateUtil.toString(new Date(), null, null);
		LoginVO authenticatedUser = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();

		BlogVO blogVO = new BlogVO();

		try {
			blogVO.setBlogId(egovBlogIdGnrService.getNextStringId());
		} catch (FdlException e) {
			log.error(e.getMessage());
		}

		blogVO.setBlogNm("test 블로그 명" + today);
		blogVO.setBlogIntrcn("test 블로그 명" + today);
		blogVO.setRegistSeCode("REGC02"); // 커뮤니티 등록
//		blogVO.setTmplatId("");
		blogVO.setUseAt("Y");
		blogVO.setFrstRegisterId(authenticatedUser.getUniqId());

		try {
			blogVO.setBbsId(egovBBSMstrIdGnrService.getNextStringId());
		} catch (FdlException e) {
			log.error(e.getMessage());
		}

		blogVO.setBlogAt("Y");

		egovBBSMasterDAO.insertBlogMaster(blogVO);

		return blogVO;
	}

}