package egovframework.com.cop.bbs.service.impl;

import static org.junit.Assert.assertEquals;

import java.util.Date;

import javax.annotation.Resource;

import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;

import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.com.cop.bbs.service.BoardMaster;
import egovframework.com.cop.bbs.service.EgovBBSMasterService;
import egovframework.com.test.EgovTestV1;
import egovframework.rte.fdl.cmmn.exception.FdlException;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;
import egovframework.rte.fdl.string.EgovDateUtil;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@ContextConfiguration(classes = { EgovBBSMasterServiceImplTestConfiguration.class })
public class EgovBBSMasterServiceImplTest_updateBBSMasterInf extends EgovTestV1 {

	@Resource(name = "EgovBBSMasterService")
	private EgovBBSMasterService egovBBSMasterService;

	@Resource(name = "EgovBBSMasterDAO")
	private EgovBBSMasterDAO egovBBSMasterDAO;

	@Resource(name = "egovBBSMstrIdGnrService")
	private EgovIdGnrService egovBBSMstrIdGnrService;

//	@Resource(name = "egovNttIdGnrService")
//	private EgovIdGnrService egovNttIdGnrService;

	@Resource(name = "egovBlogIdGnrService")
	private EgovIdGnrService egovBlogIdGnrService;

	// testData
	String today;
	String today2;
	LoginVO authenticatedUser;

	// given
	BoardMaster boardMaster;

	// when
	boolean result = false;

	@Test
//	@Transactional(propagation = Propagation.NEVER)
	public void test() {
		log.debug("test");
		testData();
		testData2();
		given();
		when();
		then();
	}

	void testData() {
		log.debug("testData");

		today = " " + EgovDateUtil.toString(new Date(), null, null);
		authenticatedUser = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
	}

	void testData2() {
		log.debug("testData2");

		boardMaster = new BoardMaster();
		try {
			boardMaster.setBbsId(egovBBSMstrIdGnrService.getNextStringId());
		} catch (FdlException e) {
			log.error(e.getMessage());
		}

		boardMaster.setBbsTyCode("BBST01"); // COM101 BBST01 통합게시판
		boardMaster.setBbsNm("test 게시판명" + today); // 게시판명
		boardMaster.setBbsIntrcn("test 게시판소개" + today); // 게시판소개
		boardMaster.setReplyPosblAt("Y"); // 답장가능여부
		boardMaster.setFileAtchPosblAt("Y"); // 파일첨부가능여부
		boardMaster.setAtchPosblFileNumber(3); // 첨부가능파일숫자
		boardMaster.setTmplatId(""); // 템플릿ID
		boardMaster.setUseAt("Y"); // 사용여부
		boardMaster.setCmmntyId(""); // 커뮤니티ID
		boardMaster.setFrstRegisterId(authenticatedUser.getUniqId()); // 최초등록자ID
		try {
			boardMaster.setBlogId(egovBlogIdGnrService.getNextStringId());
		} catch (FdlException e) {
			log.error(e.getMessage());
		} // 블로그 ID
		boardMaster.setBlogAt("Y"); // 블로그 여부

		egovBBSMasterDAO.insertBBSMasterInf(boardMaster);
	}

	void given() {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			log.error(e.getMessage());
		}

		today2 = " " + EgovDateUtil.toString(new Date(), null, null);

		boardMaster.setBbsNm("test 게시판명" + today + " 수정 " + today2); // 게시판명
		boardMaster.setBbsIntrcn("test 게시판소개" + today + " 수정 " + today2); // 게시판소개
		boardMaster.setBbsTyCode("BBST02"); // COM101 BBST01 통합게시판, BBST02 블로그형게시판
		boardMaster.setFileAtchPosblAt("N"); // 파일첨부가능여부
		boardMaster.setAtchPosblFileNumber(30); // 첨부가능파일숫자
//		boardMaster.setTmplatId("");
		boardMaster.setTmplatId("-"); // 템플릿ID
		boardMaster.setLastUpdusrId(authenticatedUser.getUniqId());
//		boardMaster.setUseAt("");
		boardMaster.setUseAt("N"); // 사용여부

		boardMaster.setBbsId(boardMaster.getBbsId());
	}

	void when() {
		try {
			egovBBSMasterService.updateBBSMasterInf(boardMaster);
			result = true;
		} catch (Exception e) {
			log.error(e.getMessage());
		}
	}

	void then() {
		log.debug("result={}", result);

		assertEquals(result, true);
	}

}