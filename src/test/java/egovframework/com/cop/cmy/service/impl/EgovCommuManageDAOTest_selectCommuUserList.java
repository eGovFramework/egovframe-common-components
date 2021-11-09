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
import egovframework.com.cop.cmy.service.CommunityUserVO;
import egovframework.com.test.EgovTestV1;
import egovframework.rte.fdl.cmmn.exception.FdlException;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;
import egovframework.rte.fdl.string.EgovDateUtil;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@ContextConfiguration(classes = { EgovCommuManageDAOTest_Configuration.class })
public class EgovCommuManageDAOTest_selectCommuUserList extends EgovTestV1 {

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
	CommunityUserVO cmmntyUserVO;

	// when
	List<CommunityUser> commuUsers;

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
		cmmntyUserVO = new CommunityUserVO();
		cmmntyUserVO.setCmmntyId(community.getCmmntyId());
		cmmntyUserVO.setRecordCountPerPage(10);
		cmmntyUserVO.setFirstIndex(0);

		cmmntyUserVO.setSearchCnd("0");
		cmmntyUserVO.setSearchWrd("테스트1");

		cmmntyUserVO.setEmplyrNm("테스트1");
	}

	void when() {
		commuUsers = egovCommuManageDAO.selectCommuUserList(cmmntyUserVO);
	}

	void then() {
		log.debug("cmmntyId={}, {}", community.getCmmntyId(), commuUsers.get(0).getCmmntyId());
		log.debug("emplyrId={}, {}", cmmntyUser.getEmplyrId(), commuUsers.get(0).getEmplyrId());
		log.debug("emplyrNm={}, {}", cmmntyUserVO.getEmplyrNm(), commuUsers.get(0).getEmplyrNm());
		log.debug("mngrAt={}, {}", cmmntyUser.getMngrAt(), commuUsers.get(0).getMngrAt());
//		log.debug("sbscrbDe={}", boardVO.getSbscrbDe());
//		log.debug("secsnDe={}", boardVO.getSecsnDe());
//		log.debug("userId={}", boardVO.getUserId());
		log.debug("mberSttus={}, {}", cmmntyUser.getMberSttus(), commuUsers.get(0).getMberSttus());
//		log.debug("mberSttusNm={}", boardVO.getMberSttusNm());
//		log.debug("userEmail={}", boardVO.getUserEmail());
		log.debug("useAt={}, {}", cmmntyUser.getUseAt(), commuUsers.get(0).getUseAt());
//		log.debug("frstRegisterPnttm={}", boardVO.getFrstRegisterPnttm());
//		log.debug("frstRegisterId={}", boardVO.getFrstRegisterId());
//		log.debug("lastUpdusrPnttm={}", boardVO.getLastUpdusrPnttm());
//		log.debug("lastUpdusrId={}", boardVO.getLastUpdusrId());

		assertEquals(community.getCmmntyId(), commuUsers.get(0).getCmmntyId());
		assertEquals(cmmntyUser.getEmplyrId(), commuUsers.get(0).getEmplyrId());
		assertEquals(cmmntyUserVO.getEmplyrNm(), commuUsers.get(0).getEmplyrNm());
		assertEquals(cmmntyUser.getMngrAt(), commuUsers.get(0).getMngrAt());
//		assertEquals(commuUsers.get(0).getSbscrbDe(), boardVO.getSbscrbDe());
//		assertEquals(commuUsers.get(0).getSecsnDe(), boardVO.getSecsnDe());
//		assertEquals(commuUsers.get(0).getUserId(), boardVO.getUserId());
		assertEquals(cmmntyUser.getMberSttus(), commuUsers.get(0).getMberSttus());
//		assertEquals(commuUsers.get(0).getMberSttusNm(), boardVO.getMberSttusNm());
//		assertEquals(commuUsers.get(0).getUserEmail(), boardVO.getUserEmail());
		assertEquals(cmmntyUser.getUseAt(), commuUsers.get(0).getUseAt());
//		assertEquals(commuUsers.get(0).getFrstRegisterPnttm(), boardVO.getFrstRegisterPnttm());
//		assertEquals(commuUsers.get(0).getFrstRegisterId(), boardVO.getFrstRegisterId());
//		assertEquals(commuUsers.get(0).getLastUpdusrPnttm(), boardVO.getLastUpdusrPnttm());
//		assertEquals(commuUsers.get(0).getLastUpdusrId(), boardVO.getLastUpdusrId());
	}

}