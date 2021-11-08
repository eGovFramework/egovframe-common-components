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
import egovframework.com.cop.cmy.service.CommunityUser;
import egovframework.com.cop.cmy.service.CommunityVO;
import egovframework.com.test.EgovTestV1;
import egovframework.rte.fdl.cmmn.exception.FdlException;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;
import egovframework.rte.fdl.string.EgovDateUtil;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@ContextConfiguration(classes = { EgovCommuManageDAOTest_Configuration.class })
public class EgovCommuManageDAOTest_selectCommuManagerList extends EgovTestV1 {

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
	CommunityUser cmmntyUser;

	// given
	CommunityVO cmmntyVO;

	// when
	List<CommunityUser> commuManagerList;

	@Test
	public void test() {
		log.debug("test");
		testData();
		testData_insertCommuMaster();
		testData_insertCommuUserRqst();
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

	void testData_insertCommuUserRqst() {
		cmmntyUser = new CommunityUser();
		cmmntyUser.setCmmntyId(community.getCmmntyId());
		cmmntyUser.setEmplyrId(authenticatedUser.getUniqId());
		cmmntyUser.setMngrAt("Y"); // 관리자여부

		cmmntyUser.setMberSttus("P"); // 회원 상태
//		A	회원 가입 신청 상태
//		D	회원 가입 삭제 상태
//		P	회원 가입 승인 상태

		cmmntyUser.setUseAt("Y");
		cmmntyUser.setFrstRegisterId(authenticatedUser.getUniqId());

		egovCommuManageDAO.insertCommuUserRqst(cmmntyUser);
	}

	void given() {
		cmmntyVO = new CommunityVO();
		cmmntyVO.setCmmntyId(community.getCmmntyId());
	}

	void when() {
		commuManagerList = egovCommuManageDAO.selectCommuManagerList(cmmntyVO);
	}

	void then() {
		log.debug("emplyrId={}, {}", cmmntyUser.getEmplyrId(), commuManagerList.get(0).getEmplyrId());
		log.debug("emplyrNm={}, {}", cmmntyUser.getEmplyrNm(), commuManagerList.get(0).getEmplyrNm());

		assertEquals(cmmntyUser.getEmplyrId(), commuManagerList.get(0).getEmplyrId());
//		assertEquals(cmmntyUser.getEmplyrNm(), commuManagerList.get(0).getEmplyrNm());
	}

}