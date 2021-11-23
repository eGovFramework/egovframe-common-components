package egovframework.com.cop.com.service.impl;

import static org.junit.Assert.assertEquals;

import java.util.Date;
import java.util.List;

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
public class EgovUserInfManageDAOTest_selectUserList extends EgovTestV1 {

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
	List<UserInfVO> users;

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
		mberManageVO.setUniqId("TUSRCNFRM_0000000001");
		mberManageVO.setMberId("T" + today2);
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
		userVO.setRecordCountPerPage(10);
		userVO.setFirstIndex(0);

		userVO.setSearchCnd("0");
//		userVO.setSearchWrd(authenticatedUser.getName());
//		userVO.setSearchWrd("테스트1");
		userVO.setSearchWrd(mberManageVO.getMberNm());
	}

	void when() {
		try {
			users = egovUserInfManageDAO.selectUserList(userVO);
		} catch (Exception e) {
			log.error("Exception");
		}
	}

	void then() {
		log.debug("uniqId={}, {}", users.get(0).getUniqId(), userVO.getUniqId());
		log.debug("userId={}, {}", users.get(0).getUserId(), userVO.getUserId());
		log.debug("userNm={}, {}", users.get(0).getUserNm(), userVO.getSearchWrd());
		log.debug("userZip={}, {}", users.get(0).getUserZip(), userVO.getUserZip());
		log.debug("userAdres={}, {}", users.get(0).getUserAdres(), userVO.getUserAdres());
		log.debug("userEmail={}, {}", users.get(0).getUserEmail(), userVO.getUserEmail());

//		assertEquals(users.get(0).getUniqId(), userVO.getUniqId());
//		assertEquals(users.get(0).getUserId(), userVO.getUserId());
		assertEquals(users.get(0).getUserNm(), userVO.getSearchWrd());
//		assertEquals(users.get(0).getUserZip(), userVO.getUserZip());
//		assertEquals(users.get(0).getUserAdres(), userVO.getUserAdres());
//		assertEquals(users.get(0).getUserEmail(), userVO.getUserEmail());
	}

}