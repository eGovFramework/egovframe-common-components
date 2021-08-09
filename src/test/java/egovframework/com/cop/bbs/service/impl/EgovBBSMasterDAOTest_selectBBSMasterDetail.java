package egovframework.com.cop.bbs.service.impl;

import static org.junit.Assert.assertEquals;

import java.util.Date;

import javax.annotation.Resource;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.com.cop.bbs.service.BoardMaster;
import egovframework.com.cop.bbs.service.BoardMasterVO;
import egovframework.com.test.EgovTestV1;
import egovframework.rte.fdl.cmmn.exception.FdlException;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;
import egovframework.rte.fdl.string.EgovDateUtil;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@ContextConfiguration(classes = { EgovBBSMasterDAOTest_AAA_Configuration.class })
public class EgovBBSMasterDAOTest_selectBBSMasterDetail extends EgovTestV1 {

	@Autowired
	private EgovBBSMasterDAO egovBBSMasterDAO;

	@Resource(name = "egovBBSMstrIdGnrService")
	private EgovIdGnrService egovBBSMstrIdGnrService;

	@Resource(name = "egovNttIdGnrService")
	private EgovIdGnrService egovNttIdGnrService;

	@Resource(name = "egovBlogIdGnrService")
	private EgovIdGnrService egovBlogIdGnrService;

	@Test
	public void test() {
		log.debug("test");

		// given
		BoardMasterVO boardMasterVO = testData();

		// when
		BoardMasterVO bbsMasterDetail = egovBBSMasterDAO.selectBBSMasterDetail(boardMasterVO);
		debug(bbsMasterDetail);

		// then
		assertEquals(bbsMasterDetail.getBbsId(), boardMasterVO.getBbsId());

		assertEquals(bbsMasterDetail.getBbsId(), boardMasterVO.getBbsId());
		assertEquals(bbsMasterDetail.getBbsTyCode(), boardMasterVO.getBbsTyCode());
//		assertEquals(bbsMasterDetail.getBbsNm(), boardMasterVO.getBbsNm());
//		assertEquals(bbsMasterDetail.getBbsIntrcn(), boardMasterVO.getBbsIntrcn());
//		assertEquals(bbsMasterDetail.getReplyPosblAt(), boardMasterVO.getReplyPosblAt());
//		assertEquals(bbsMasterDetail.getFileAtchPosblAt(), boardMasterVO.getFileAtchPosblAt());
//		assertEquals(bbsMasterDetail.getAtchPosblFileNumber(), boardMasterVO.getAtchPosblFileNumber());
//		assertEquals(bbsMasterDetail.getAtchPosblFileSize(), boardMasterVO.getAtchPosblFileSize());
//		assertEquals(bbsMasterDetail.getTmplatId(), boardMasterVO.getTmplatId());
//		assertEquals(bbsMasterDetail.getFrstRegisterId(), boardMasterVO.getFrstRegisterId());
//		assertEquals(bbsMasterDetail.getFrstRegisterNm(), boardMasterVO.getFrstRegisterNm());
//		assertEquals(bbsMasterDetail.getFrstRegisterPnttm(), boardMasterVO.getFrstRegisterPnttm());
//		assertEquals(bbsMasterDetail.getBbsTyCodeNm(), boardMasterVO.getBbsTyCodeNm());
//		assertEquals(bbsMasterDetail.getTmplatNm(), boardMasterVO.getTmplatNm());
//		assertEquals(bbsMasterDetail.getAuthFlag(), boardMasterVO.getAuthFlag());
//		assertEquals(bbsMasterDetail.getTmplatCours(), boardMasterVO.getTmplatCours());
//		assertEquals(bbsMasterDetail.getCmmntyId(), boardMasterVO.getCmmntyId());
//		assertEquals(bbsMasterDetail.getBlogId(), boardMasterVO.getBlogId());
	}

	public BoardMasterVO testData() {
		BoardMaster boardMaster = new BoardMaster();
		try {
			boardMaster.setBbsId(egovBBSMstrIdGnrService.getNextStringId());
		} catch (FdlException e) {
			log.error(e.getMessage());
		}

		String today = " " + EgovDateUtil.toString(new Date(), null, null);

		LoginVO loginVO = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();

		boardMaster.setBbsTyCode("BBST01"); // COM101 BBST01 통합게시판
		boardMaster.setBbsNm("test 게시판명" + today); // 게시판명
		boardMaster.setBbsIntrcn("test 게시판소개" + today); // 게시판소개
		boardMaster.setReplyPosblAt("Y"); // 답장가능여부
		boardMaster.setFileAtchPosblAt("Y"); // 파일첨부가능여부
		boardMaster.setAtchPosblFileNumber(3); // 첨부가능파일숫자
		boardMaster.setTmplatId(""); // 템플릿ID
		boardMaster.setUseAt("Y"); // 사용여부
		boardMaster.setCmmntyId(""); // 커뮤니티ID
		boardMaster.setFrstRegisterId(loginVO.getUniqId()); // 최초등록자ID
		try {
			boardMaster.setBlogId(egovBlogIdGnrService.getNextStringId());
		} catch (FdlException e) {
			log.error(e.getMessage());
		} // 블로그 ID
		boardMaster.setBlogAt("Y"); // 블로그 여부

		egovBBSMasterDAO.insertBBSMasterInf(boardMaster);

		BoardMasterVO boardMasterVO = new BoardMasterVO();
		boardMasterVO.setBbsId(boardMaster.getBbsId());
		boardMasterVO.setUniqId(loginVO.getUniqId());

		boardMasterVO.setBbsTyCode(boardMaster.getBbsTyCode());

		return boardMasterVO;
	}

	void debug(BoardMasterVO bbsMasterDetail) {
		log.debug("bbsMasterDetail={}", bbsMasterDetail);
		log.debug("getBbsId={}", bbsMasterDetail.getBbsId());
		log.debug("getAuthFlag={}", bbsMasterDetail.getAuthFlag());

		log.debug("bbsId={}", bbsMasterDetail.getBbsId());
		log.debug("bbsTyCode={}", bbsMasterDetail.getBbsTyCode());
		log.debug("bbsNm={}", bbsMasterDetail.getBbsNm());
		log.debug("bbsIntrcn={}", bbsMasterDetail.getBbsIntrcn());
		log.debug("replyPosblAt={}", bbsMasterDetail.getReplyPosblAt());
		log.debug("fileAtchPosblAt={}", bbsMasterDetail.getFileAtchPosblAt());
		log.debug("atchPosblFileNumber={}", bbsMasterDetail.getAtchPosblFileNumber());
		log.debug("atchPosblFileSize={}", bbsMasterDetail.getAtchPosblFileSize());
		log.debug("tmplatId={}", bbsMasterDetail.getTmplatId());
		log.debug("frstRegisterId={}", bbsMasterDetail.getFrstRegisterId());
		log.debug("frstRegisterNm={}", bbsMasterDetail.getFrstRegisterNm());
		log.debug("frstRegisterPnttm={}", bbsMasterDetail.getFrstRegisterPnttm());
		log.debug("bbsTyCodeNm={}", bbsMasterDetail.getBbsTyCodeNm());
		log.debug("tmplatNm={}", bbsMasterDetail.getTmplatNm());
		log.debug("authFlag={}", bbsMasterDetail.getAuthFlag());
		log.debug("tmplatCours={}", bbsMasterDetail.getTmplatCours());
		log.debug("cmmntyId={}", bbsMasterDetail.getCmmntyId());
		log.debug("blogId={}", bbsMasterDetail.getBlogId());
	}

}