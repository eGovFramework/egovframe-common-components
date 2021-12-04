package egovframework.com.cop.com.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;

import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.com.cop.cmy.service.Community;
import egovframework.com.cop.cmy.service.CommunityUser;
import egovframework.com.cop.cmy.service.impl.EgovCommuManageDAO;
import egovframework.com.cop.cmy.service.impl.EgovCommuMasterDAO;
import egovframework.com.cop.com.service.UserInfVO;
import egovframework.com.test.EgovTestV1;
import egovframework.com.uss.umt.service.MberManageVO;
import egovframework.com.uss.umt.service.impl.MberManageDAO;
import egovframework.com.utl.sim.service.EgovFileScrty;
import egovframework.rte.fdl.cmmn.exception.FdlException;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;
import egovframework.rte.fdl.string.EgovDateUtil;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@ContextConfiguration(classes = { EgovUserInfManageDAOTest_Configuration.class })
public class EgovUserInfManageDAOTest_selectClubOprtrList extends EgovTestV1 {

	@Resource(name = "EgovUserInfManageDAO")
	private EgovUserInfManageDAO egovUserInfManageDAO;

	@Resource(name = "mberManageDAO")
	private MberManageDAO mberManageDAO;

	@Resource(name = "egovCmmntyIdGnrService")
	private EgovIdGnrService egovCmmntyIdGnrService;

	@Resource(name = "EgovCommuManageDAO")
	private EgovCommuManageDAO egovCommuManageDAO;

	@Resource(name = "EgovCommuMasterDAO")
	private EgovCommuMasterDAO egovCommuMasterDAO;

	@Resource(name = "egovClbIdGnrService")
	private EgovIdGnrService egovClbIdGnrService;

	// testData
	String today;
	String today2;
	LoginVO authenticatedUser;

	Community community;
	CommunityUser cmmntyUser;

	MberManageVO mberManageVO;
	String insertMber;

	// given
	UserInfVO userVO;

	// when
	List<UserInfVO> clubOprtrs;

	@Test
//	@Commit
	public void test() {
		log.debug("test");
		testData();
		testData_insertMber();
		testData_insertCommuMaster();
		testData_insertCommuUser();
		given();
		when();
		then();
	}

	void testData() {
		today = " " + EgovDateUtil.toString(new Date(), null, null);
		authenticatedUser = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
	}

	void testData_insertMber() {
		today2 = EgovDateUtil.toString(new Date(), "yyyy_MM_dd_HH_mm_ss", null);

		mberManageVO = new MberManageVO();
		mberManageVO.setUniqId("E" + today2);
		mberManageVO.setMberId("U" + today2);
		mberManageVO.setMberNm("T일반회원" + today2);
		mberManageVO.setPassword("rhdxhd12");
		try {
			mberManageVO
					.setPassword(EgovFileScrty.encryptPassword(mberManageVO.getPassword(), mberManageVO.getMberId()));
		} catch (Exception e) {
			log.error("Exception");
		}
//		mberManageVO.setPasswordHint("");
//		mberManageVO.setPasswordCnsr("");
//		mberManageVO.setIhidnum("");
//		mberManageVO.setSexdstnCode("");
		mberManageVO.setZip("100775");
		mberManageVO.setAdres("서울 중구 무교동 한국정보화진흥원");
		mberManageVO.setAreaNo("02");
//		mberManageVO.setMberSttus("");
//		mberManageVO.setDetailAdres("");
		mberManageVO.setEndTelno("2059");
		mberManageVO.setMoblphonNo("1566-2059");
//		mberManageVO.setGroupId("");
//		mberManageVO.setMberFxnum("");
		mberManageVO.setMberEmailAdres("egovframesupport@gmail.com");
		mberManageVO.setMiddleTelno("1566");

		insertMber = mberManageDAO.insertMber(mberManageVO);
		log.debug("insertMber={}", insertMber);
	}

	void testData_insertCommuMaster() {
		community = new Community();
		try {
			community.setCmmntyId(egovCmmntyIdGnrService.getNextStringId());
		} catch (FdlException e) {
			log.error("FdlException");
		}
		community.setUseAt("Y");

		egovCommuMasterDAO.insertCommuMaster(community);
	}

	void testData_insertCommuUser() {
		cmmntyUser = new CommunityUser();
		cmmntyUser.setCmmntyId(community.getCmmntyId());
		cmmntyUser.setEmplyrId(mberManageVO.getUniqId());
		cmmntyUser.setMngrAt("Y"); // 관리자여부

		cmmntyUser.setMberSttus("P"); // 회원 상태
//		A	회원 가입 신청 상태
//		D	회원 가입 삭제 상태
//		P	회원 가입 승인 상태

		cmmntyUser.setUseAt("Y");
		cmmntyUser.setFrstRegisterId(mberManageVO.getUniqId());

		egovCommuManageDAO.insertCommuUserRqst(cmmntyUser);
	}

	void given() {
		userVO = new UserInfVO();
		try {
			userVO.setTrgetId(egovClbIdGnrService.getNextStringId());
		} catch (FdlException e) {
			log.error("egovClbIdGnrService FdlException");
		}

		userVO.setSearchCnd("0");
		userVO.setSearchWrd(mberManageVO.getMberNm());

		userVO.setRecordCountPerPage(10);
		userVO.setFirstIndex(0);
	}

	void when() {
		try {
			clubOprtrs = egovUserInfManageDAO.selectClubOprtrList(userVO);
		} catch (Exception e) {
			log.error("Exception");
		}
	}

	void then() {
		log.debug("clubUsers={}, {}", clubOprtrs, null);

//		log.debug("uniqId={}, {}", clubUsers.get(0).getUniqId(), mberManageVO.getUniqId());
//		log.debug("userId={}, {}", clubUsers.get(0).getUserId(), mberManageVO.getMberId());
//		log.debug("userNm={}, {}", clubUsers.get(0).getUserNm(), mberManageVO.getMberNm());
//		log.debug("userZip={}, {}", clubUsers.get(0).getUserZip(), mberManageVO.getZip());
//		log.debug("userAdres={}, {}", clubUsers.get(0).getUserAdres(), mberManageVO.getAdres());
//		log.debug("userEmail={}, {}", clubUsers.get(0).getUserEmail(), mberManageVO.getMberEmailAdres());
//		log.debug("useAt={}, {}", clubUsers.get(0).getUseAt(), cmmntyUser.getUseAt());
//		log.debug("deletedAt={}, {}", clubUsers.get(0).getDeletedAt(), community.getUseAt());
//
//		assertEquals(clubUsers.get(0).getUniqId(), mberManageVO.getUniqId());
//		assertEquals(clubUsers.get(0).getUserId(), mberManageVO.getMberId());
//		assertEquals(clubUsers.get(0).getUserNm(), mberManageVO.getMberNm());
//		assertEquals(clubUsers.get(0).getUserZip(), mberManageVO.getZip());
//		assertEquals(clubUsers.get(0).getUserAdres(), mberManageVO.getAdres());
//		assertEquals(clubUsers.get(0).getUserEmail(), mberManageVO.getMberEmailAdres());
//		assertEquals(clubUsers.get(0).getUseAt(), cmmntyUser.getUseAt());
//		assertEquals(clubUsers.get(0).getDeletedAt(), community.getUseAt());
	}

}