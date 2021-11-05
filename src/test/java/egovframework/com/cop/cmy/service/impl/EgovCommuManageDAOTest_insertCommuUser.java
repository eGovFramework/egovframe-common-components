package egovframework.com.cop.cmy.service.impl;

import static org.junit.Assert.assertEquals;

import java.util.Date;

import javax.annotation.Resource;

import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;

import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.com.cop.cmy.service.Community;
import egovframework.com.cop.cmy.service.CommunityUserVO;
import egovframework.com.test.EgovTestV1;
import egovframework.rte.fdl.cmmn.exception.FdlException;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;
import egovframework.rte.fdl.string.EgovDateUtil;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@ContextConfiguration(classes = { EgovCommuManageDAOTest_Configuration.class })
public class EgovCommuManageDAOTest_insertCommuUser extends EgovTestV1 {

	@Resource(name = "egovCmmntyIdGnrService")
	private EgovIdGnrService egovCmmntyIdGnrService;

	@Resource(name = "EgovCommuManageDAO")
	private EgovCommuManageDAO egovCommuManageDAO;

	@Resource(name = "EgovCommuMasterDAO")
	private EgovCommuMasterDAO egovCommuMasterDAO;

	// testData
	String today;
	LoginVO authenticatedUser;

	Community community;

	// given
	CommunityUserVO cmmntyUserVO;

	// when
	boolean result = false;

	@Test
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

		egovCommuMasterDAO.insertCommuMaster(community);
	}

	void given() {
		cmmntyUserVO = new CommunityUserVO();
		cmmntyUserVO.setLastUpdusrId(authenticatedUser.getUniqId());
		cmmntyUserVO.setCmmntyId(community.getCmmntyId());
		cmmntyUserVO.setEmplyrId(authenticatedUser.getUniqId());
	}

	void when() {
		try {
			egovCommuManageDAO.insertCommuUser(cmmntyUserVO);
			result = true;
		} catch (Exception e) {
			log.error("Exception");
		}
	}

	void then() {
		log.debug("result={}", result);

		assertEquals(true, result);
	}

}