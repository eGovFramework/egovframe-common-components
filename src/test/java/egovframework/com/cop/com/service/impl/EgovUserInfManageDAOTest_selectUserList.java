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
import egovframework.rte.fdl.string.EgovDateUtil;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@ContextConfiguration(classes = { EgovUserInfManageDAOTest_Configuration.class })
public class EgovUserInfManageDAOTest_selectUserList extends EgovTestV1 {

	@Resource(name = "EgovUserInfManageDAO")
	private EgovUserInfManageDAO egovUserInfManageDAO;

	// testData
	String today;
	LoginVO authenticatedUser;

	// given
	UserInfVO userVO;

	// when
	List<UserInfVO> users;

	@Test
//	@Commit
	public void test() {
		log.debug("test");
		testData();
		given();
		when();
		then();
	}

	void testData() {
		today = " " + EgovDateUtil.toString(new Date(), null, null);
		authenticatedUser = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
	}

	void given() {
		userVO = new UserInfVO();
		userVO.setRecordCountPerPage(10);
		userVO.setFirstIndex(0);

		userVO.setSearchCnd("0");
//		userVO.setSearchWrd(authenticatedUser.getName());
		userVO.setSearchWrd("테스트1");
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