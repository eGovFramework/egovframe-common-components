package egovframework.com.cop.cmy.service.impl;

import static org.junit.Assert.assertEquals;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;

import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.com.cop.bbs.service.BoardMaster;
import egovframework.com.cop.bbs.service.BoardMasterVO;
import egovframework.com.cop.bbs.service.impl.EgovBBSMasterDAO;
import egovframework.com.test.EgovTestV1;
import egovframework.rte.fdl.cmmn.exception.FdlException;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;
import egovframework.rte.fdl.string.EgovDateUtil;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@ContextConfiguration(classes = { EgovCommuBBSMasterDAOTest_Configuration.class })
public class EgovCommuBBSMasterDAOTest_selectCommuBBSMasterListMain extends EgovTestV1 {

	@Resource(name = "egovBBSMstrIdGnrService")
	private EgovIdGnrService egovBBSMstrIdGnrService;

	@Resource(name = "egovCmmntyIdGnrService")
	private EgovIdGnrService egovCmmntyIdGnrService;

	@Resource(name = "EgovBBSMasterDAO")
	private EgovBBSMasterDAO egovBBSMasterDAO;

	@Resource(name = "EgovCommuBBSMasterDAO")
	private EgovCommuBBSMasterDAO egovCommuBBSMasterDAO;

	// testData
	String today;
	LoginVO authenticatedUser;

	BoardMaster boardMaster;

	// given
	BoardMasterVO boardMasterVO;

	// when
	List<BoardMasterVO> commuBBSMasterListMain;

	@Test
	public void test() {
		log.debug("test");
		testData();
		testData2_insertBBSMasterInf();
		given();
		when();
		then();
	}

	void testData() {
		today = " " + EgovDateUtil.toString(new Date(), null, null);
		authenticatedUser = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
	}

	void testData2_insertBBSMasterInf() {
		boardMaster = new BoardMaster();
		try {
			boardMaster.setBbsId(egovBBSMstrIdGnrService.getNextStringId());
			boardMaster.setCmmntyId(egovCmmntyIdGnrService.getNextStringId());
		} catch (FdlException e) {
			log.error(e.getMessage());
		}

		boardMaster.setBbsNm("test 게시판명" + today);
		boardMaster.setBbsTyCode("BBST01");
		boardMaster.setUseAt("Y");

		egovBBSMasterDAO.insertBBSMasterInf(boardMaster);
	}

	void given() {
		boardMasterVO = new BoardMasterVO();
		boardMasterVO.setCmmntyId(boardMaster.getCmmntyId());

//		boardMasterVO.setBbsTyCodeNm("통합게시판");
	}

	void when() {
		commuBBSMasterListMain = egovCommuBBSMasterDAO.selectCommuBBSMasterListMain(boardMasterVO);
	}

	void then() {
		log.debug("bbsId={}, {}", boardMaster.getBbsId(), commuBBSMasterListMain.get(0).getBbsId());
		log.debug("bbsTyCode={}, {}", boardMaster.getBbsTyCode(), commuBBSMasterListMain.get(0).getBbsTyCode());
		log.debug("bbsTyCodeNm={}, {}", boardMasterVO.getBbsTyCodeNm(), commuBBSMasterListMain.get(0).getBbsTyCodeNm());
		log.debug("bbsNm={}, {}", boardMaster.getBbsNm(), commuBBSMasterListMain.get(0).getBbsNm());
		log.debug("tmplatId={}", commuBBSMasterListMain.get(0).getTmplatId());
		log.debug("useAt={}, {}", boardMaster.getUseAt(), commuBBSMasterListMain.get(0).getUseAt());
		log.debug("frstRegisterPnttm={}", commuBBSMasterListMain.get(0).getFrstRegisterPnttm());
//
		assertEquals(boardMaster.getBbsId(), commuBBSMasterListMain.get(0).getBbsId());
		assertEquals(boardMaster.getBbsTyCode(), commuBBSMasterListMain.get(0).getBbsTyCode());
//		assertEquals(boardMasterVO.getBbsTyCodeNm(), commuBBSMasterListMain.get(0).getBbsTyCodeNm());
		assertEquals(boardMaster.getBbsNm(), commuBBSMasterListMain.get(0).getBbsNm());
//		assertEquals(boardMasterVO.getTmplatId(), commuBBSMasterListMain.get(0).getTmplatId());
		assertEquals(boardMaster.getUseAt(), commuBBSMasterListMain.get(0).getUseAt());
//		assertEquals(commuBBSMasterListMain.get(0).getFrstRegisterPnttm(), boardVO.getFrstRegisterPnttm());
	}

}