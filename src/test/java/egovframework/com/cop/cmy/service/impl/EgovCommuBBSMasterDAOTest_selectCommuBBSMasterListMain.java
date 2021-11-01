package egovframework.com.cop.cmy.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;

import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.com.cop.bbs.service.BoardMasterVO;
import egovframework.com.test.EgovTestV1;
import egovframework.rte.fdl.string.EgovDateUtil;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@ContextConfiguration(classes = { EgovCommuBBSMasterDAOTest_Configuration.class })
public class EgovCommuBBSMasterDAOTest_selectCommuBBSMasterListMain extends EgovTestV1 {

	// context-idgn-bbs.xml
//	@Resource(name = "egovBBSMstrIdGnrService")
//	private EgovIdGnrService egovBBSMstrIdGnrService;

	@Resource(name = "EgovCommuBBSMasterDAO")
	private EgovCommuBBSMasterDAO egovCommuBBSMasterDAO;

	// testData
	String today;
	LoginVO authenticatedUser;

	// given
	BoardMasterVO boardMasterVO;

	// when
	List<BoardMasterVO> commuBBSMasterListMain;

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
		boardMasterVO = new BoardMasterVO();
		boardMasterVO.setCmmntyId("");
	}

	void when() {
		commuBBSMasterListMain = egovCommuBBSMasterDAO.selectCommuBBSMasterListMain(boardMasterVO);
	}

	void then() {
//		log.debug("bbsId={}", boardVO.getBbsId());
//		log.debug("bbsTyCode={}", boardVO.getBbsTyCode());
//		log.debug("bbsTyCodeNm={}", boardVO.getBbsTyCodeNm());
//		log.debug("bbsNm={}", boardVO.getBbsNm());
//		log.debug("tmplatId={}", boardVO.getTmplatId());
//		log.debug("useAt={}", boardVO.getUseAt());
//		log.debug("frstRegisterPnttm={}", boardVO.getFrstRegisterPnttm());
//
//		assertEquals(commuBBSMasterListMain.get(0).getBbsId(), boardVO.getBbsId());
//		assertEquals(commuBBSMasterListMain.get(0).getBbsTyCode(), boardVO.getBbsTyCode());
//		assertEquals(commuBBSMasterListMain.get(0).getBbsTyCodeNm(), boardVO.getBbsTyCodeNm());
//		assertEquals(commuBBSMasterListMain.get(0).getBbsNm(), boardVO.getBbsNm());
//		assertEquals(commuBBSMasterListMain.get(0).getTmplatId(), boardVO.getTmplatId());
//		assertEquals(commuBBSMasterListMain.get(0).getUseAt(), boardVO.getUseAt());
//		assertEquals(commuBBSMasterListMain.get(0).getFrstRegisterPnttm(), boardVO.getFrstRegisterPnttm());
	}

}