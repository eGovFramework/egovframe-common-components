package egovframework.com.cop.com.service.impl;

import static org.junit.Assert.assertEquals;

import java.util.Date;

import javax.annotation.Resource;

import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;

import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.com.cop.com.service.UserInfVO;
import egovframework.com.test.EgovTestV1;
import egovframework.com.uss.umt.service.MberManageVO;
import egovframework.com.uss.umt.service.impl.MberManageDAO;
import egovframework.com.utl.sim.service.EgovFileScrty;
import egovframework.rte.fdl.string.EgovDateUtil;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@ContextConfiguration(classes = { EgovUserInfManageDAOTest_Configuration.class })
public class EgovUserInfManageDAOTest_selectUserListCnt extends EgovTestV1 {

	@Resource(name = "EgovUserInfManageDAO")
	private EgovUserInfManageDAO egovUserInfManageDAO;

	@Resource(name = "mberManageDAO")
	private MberManageDAO mberManageDAO;

	// testData
	String today;
	String today2;
	LoginVO authenticatedUser;

	MberManageVO mberManageVO;
	String insertMber;

	// given
	UserInfVO userVO;

	// when
	int userListCnt;

	@Test
//	@Commit
	public void test() {
		log.debug("test");
		testData();
		testData_insertMber();
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
		mberManageVO.setUniqId("T" + today2);
		mberManageVO.setMberId(mberManageVO.getUniqId());
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
//		mberManageVO.setMberEmailAdres("");
		mberManageVO.setMiddleTelno("1566");

		insertMber = mberManageDAO.insertMber(mberManageVO);
		log.debug("insertMber={}", insertMber);
	}

	void given() {
		userVO = new UserInfVO();
		userVO.setSearchCnd("0");
		userVO.setSearchWrd(mberManageVO.getMberNm());
	}

	void when() {
		try {
			userListCnt = egovUserInfManageDAO.selectUserListCnt(userVO);
		} catch (Exception e) {
			log.error("Exception");
		}
	}

	void then() {
		log.debug("userListCnt={}", userListCnt);

		assertEquals(userListCnt, 1);
	}

}