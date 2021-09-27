package egovframework.com.cop.bbs.service.impl;

import static org.junit.Assert.assertEquals;

import java.util.Date;

import javax.annotation.Resource;

import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import egovframework.com.cmm.EgovComponentChecker;
import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.com.cop.bbs.service.BoardMaster;
import egovframework.com.cop.bbs.service.BoardMasterVO;
import egovframework.com.cop.bbs.service.EgovBBSMasterService;
import egovframework.com.test.EgovTestV1;
import egovframework.rte.fdl.cmmn.exception.FdlException;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;
import egovframework.rte.fdl.string.EgovDateUtil;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@ContextConfiguration(classes = { EgovBBSMasterServiceImplTestConfiguration.class })
public class EgovBBSMasterServiceImplTest_selectBBSMasterInf extends EgovTestV1 {

	@Resource(name = "EgovBBSMasterService")
	private EgovBBSMasterService egovBBSMasterService;

	@Resource(name = "egovBBSMstrIdGnrService")
	private EgovIdGnrService egovBBSMstrIdGnrService;

//	@Resource(name = "egovNttIdGnrService")
//	private EgovIdGnrService egovNttIdGnrService;

	@Resource(name = "egovBlogIdGnrService")
	private EgovIdGnrService egovBlogIdGnrService;

	@Resource(name = "EgovBBSMasterDAO")
	private EgovBBSMasterDAO egovBBSMasterDAO;

	// given
	String today;
	LoginVO authenticatedUser;
	BoardMaster boardMaster;

	BoardMasterVO boardMasterVO;

	// when
	BoardMasterVO bbsMasterInf;

	@Test
	@Transactional(propagation = Propagation.NEVER)
	public void test() {
		log.debug("test");

		boolean hasComponent = EgovComponentChecker.hasComponent("EgovBBSCommentService");
		log.debug("EgovBBSCommentService hasComponent={}", hasComponent);
		hasComponent = EgovComponentChecker.hasComponent("EgovBBSSatisfactionService");
		log.debug("EgovBBSSatisfactionService hasComponent={}", hasComponent);

		testData();
		given();
		when();
		then();
	}

	void testData() {
		log.debug("testData");

		today = " " + EgovDateUtil.toString(new Date(), null, null);
		authenticatedUser = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();

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
		boardMasterVO = new BoardMasterVO();
		boardMasterVO.setBbsId(boardMaster.getBbsId());
//		boardMasterVO.setUniqId("");
	}

	void when() {
		try {
			bbsMasterInf = egovBBSMasterService.selectBBSMasterInf(boardMasterVO);
		} catch (Exception e) {
			log.error(e.getMessage());
		}
	}

	void then() {
		log.debug("bbsMasterInf={}", bbsMasterInf);
		if (bbsMasterInf != null) {
			log.debug("bbsId={}", bbsMasterInf.getBbsId());
			log.debug("bbsTyCode={}", bbsMasterInf.getBbsTyCode());
			log.debug("bbsNm={}", bbsMasterInf.getBbsNm());
			log.debug("bbsIntrcn={}", bbsMasterInf.getBbsIntrcn());
			log.debug("replyPosblAt={}", bbsMasterInf.getReplyPosblAt());
			log.debug("fileAtchPosblAt={}", bbsMasterInf.getFileAtchPosblAt());
			log.debug("atchPosblFileNumber={}", bbsMasterInf.getAtchPosblFileNumber());
			log.debug("atchPosblFileSize={}", bbsMasterInf.getAtchPosblFileSize());
			log.debug("tmplatId={}", bbsMasterInf.getTmplatId());
			log.debug("frstRegisterId={}", bbsMasterInf.getFrstRegisterId());
			log.debug("frstRegisterNm={}", bbsMasterInf.getFrstRegisterNm());
			log.debug("frstRegisterPnttm={}", bbsMasterInf.getFrstRegisterPnttm());
			log.debug("bbsTyCodeNm={}", bbsMasterInf.getBbsTyCodeNm());
			log.debug("tmplatNm={}", bbsMasterInf.getTmplatNm());
			log.debug("authFlag={}", bbsMasterInf.getAuthFlag());
			log.debug("tmplatCours={}", bbsMasterInf.getTmplatCours());
			log.debug("cmmntyId={}", bbsMasterInf.getCmmntyId());
			log.debug("blogId={}", bbsMasterInf.getBlogId());
		}

//		assertEquals(bbsMasterInf, null);

		if (bbsMasterInf != null) {
			assertEquals(bbsMasterInf.getBbsId(), boardMasterVO.getBbsId());
			assertEquals(bbsMasterInf.getBbsTyCode(), boardMaster.getBbsTyCode());
			assertEquals(bbsMasterInf.getBbsNm(), boardMaster.getBbsNm());
			assertEquals(bbsMasterInf.getBbsIntrcn(), boardMaster.getBbsIntrcn());
			assertEquals(bbsMasterInf.getReplyPosblAt(), boardMaster.getReplyPosblAt());
			assertEquals(bbsMasterInf.getFileAtchPosblAt(), boardMaster.getFileAtchPosblAt());
			assertEquals(bbsMasterInf.getAtchPosblFileNumber(), boardMaster.getAtchPosblFileNumber());
//			assertEquals(bbsMasterInf.getAtchPosblFileSize(), boardMasterVO.getAtchPosblFileSize());
			assertEquals(bbsMasterInf.getTmplatId(), boardMaster.getTmplatId());
			assertEquals(bbsMasterInf.getFrstRegisterId(), boardMaster.getFrstRegisterId());
//			assertEquals(bbsMasterInf.getFrstRegisterNm(), boardMasterVO.getFrstRegisterNm());
//			assertEquals(bbsMasterInf.getFrstRegisterPnttm(), boardMasterVO.getFrstRegisterPnttm());
//			assertEquals(bbsMasterInf.getBbsTyCodeNm(), boardMasterVO.getBbsTyCodeNm());
//			assertEquals(bbsMasterInf.getTmplatNm(), boardMasterVO.getTmplatNm());
//			assertEquals(bbsMasterInf.getAuthFlag(), boardMasterVO.getAuthFlag());
//			assertEquals(bbsMasterInf.getTmplatCours(), boardMasterVO.getTmplatCours());
			assertEquals(bbsMasterInf.getCmmntyId(), boardMaster.getCmmntyId());
//			assertEquals(bbsMasterInf.getBlogId(), boardMasterVO.getBlogId());
		}
	}

}