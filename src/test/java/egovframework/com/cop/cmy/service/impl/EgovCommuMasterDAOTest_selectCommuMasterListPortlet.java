package egovframework.com.cop.cmy.service.impl;

import static org.junit.Assert.assertEquals;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;

import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.com.cop.cmy.service.Community;
import egovframework.com.cop.cmy.service.CommunityVO;
import egovframework.com.test.EgovTestV1;
import egovframework.rte.fdl.cmmn.exception.FdlException;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;
import egovframework.rte.fdl.string.EgovDateUtil;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@ContextConfiguration(classes = { EgovCommuMasterDAOTest_Configuration.class })
public class EgovCommuMasterDAOTest_selectCommuMasterListPortlet extends EgovTestV1 {

	@Resource(name = "EgovCommuMasterDAO")
	private EgovCommuMasterDAO egovCommuMasterDAO;

	@Resource(name = "egovCmmntyIdGnrService")
	private EgovIdGnrService egovCmmntyIdGnrService;

	// testData
	String today;
	LoginVO authenticatedUser;

	Community community;

	// given
	CommunityVO cmmntyVO;

	// when
	List<CommunityVO> commuMasterListPortlet;

	@Test
//	@Commit
	public void test() {
		log.debug("test");
		testData();
		testData_insertCommuMaster();
		given();
		when();
		then();
	}

	void testData() {
		today = " " + EgovDateUtil.toString(new Date(), null, null);
		authenticatedUser = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
	}

	void testData_insertCommuMaster() {
		community = new Community();

		try {
			community.setCmmntyId(egovCmmntyIdGnrService.getNextStringId());
		} catch (FdlException e) {
			log.error("FdlException");
		}

		community.setCmmntyNm("test 커뮤니티명" + today);
		community.setCmmntyIntrcn("test 커뮤니티소개" + today);
		community.setRegistSeCode("REGC06"); // COM001(등록구분코드): REGC06(커뮤니티 게시판 등록)
		community.setTmplatId("TMPLAT_CMNTY_DEFAULT"); // 템플릿ID, COMTNTMPLATINFO: TMPLAT_CMNTY_DEFAULT(커뮤니티 기본템플릿)
		community.setUseAt("Y");
		community.setFrstRegisterId(authenticatedUser.getUniqId());

		egovCommuMasterDAO.insertCommuMaster(community);
	}

	void given() {
		cmmntyVO = new CommunityVO();
	}

	void when() {
		try {
			commuMasterListPortlet = egovCommuMasterDAO.selectCommuMasterListPortlet(cmmntyVO);
		} catch (Exception e) {
			log.error("Exception");
		}
	}

	void then() {
		log.debug("cmmntyId={}, {}", commuMasterListPortlet.get(0).getCmmntyId(), community.getCmmntyId());
		log.debug("cmmntyNm={}, {}", commuMasterListPortlet.get(0).getCmmntyNm(), community.getCmmntyNm());
		log.debug("cmmntyIntrcn={}, {}", commuMasterListPortlet.get(0).getCmmntyIntrcn(), community.getCmmntyIntrcn());
		log.debug("tmplatId={}, {}", commuMasterListPortlet.get(0).getTmplatId(), community.getTmplatId());

		assertEquals(commuMasterListPortlet.get(0).getCmmntyId(), community.getCmmntyId());
		assertEquals(commuMasterListPortlet.get(0).getCmmntyNm(), community.getCmmntyNm());
		assertEquals(commuMasterListPortlet.get(0).getCmmntyIntrcn(), community.getCmmntyIntrcn());
		assertEquals(commuMasterListPortlet.get(0).getTmplatId(), community.getTmplatId());
	}

}