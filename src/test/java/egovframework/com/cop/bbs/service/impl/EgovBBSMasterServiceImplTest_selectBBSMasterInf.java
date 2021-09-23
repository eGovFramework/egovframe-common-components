package egovframework.com.cop.bbs.service.impl;

import static org.junit.Assert.assertEquals;

import java.util.Date;

import javax.annotation.Resource;

import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;

import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.com.cop.bbs.service.BoardMasterVO;
import egovframework.com.cop.bbs.service.EgovBBSMasterService;
import egovframework.com.test.EgovTestV1;
import egovframework.rte.fdl.string.EgovDateUtil;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@ContextConfiguration(classes = { EgovBBSMasterServiceImplTestConfiguration.class })
public class EgovBBSMasterServiceImplTest_selectBBSMasterInf extends EgovTestV1 {

	@Resource(name = "EgovBBSMasterService")
	private EgovBBSMasterService egovBBSMasterService;

//	@Resource(name = "egovBBSMstrIdGnrService")
//	private EgovIdGnrService egovBBSMstrIdGnrService;

//	@Resource(name = "egovNttIdGnrService")
//	private EgovIdGnrService egovNttIdGnrService;

//	@Resource(name = "egovBlogIdGnrService")
//	private EgovIdGnrService egovBlogIdGnrService;

	// given
	String today;
	LoginVO authenticatedUser;

	BoardMasterVO boardMasterVO;

	// when
	BoardMasterVO bbsMasterInf;

	@Test
	public void test() {
		log.debug("test");
		given();
		when();
		then();
	}

	void given() {
		today = " " + EgovDateUtil.toString(new Date(), null, null);
		authenticatedUser = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();

		boardMasterVO = new BoardMasterVO();
		boardMasterVO.setBbsId("");
		boardMasterVO.setUniqId("");
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

		assertEquals(bbsMasterInf, null);

		assertEquals(bbsMasterInf.getBbsId(), boardMasterVO.getBbsId());
		assertEquals(bbsMasterInf.getBbsTyCode(), boardMasterVO.getBbsTyCode());
		assertEquals(bbsMasterInf.getBbsNm(), boardMasterVO.getBbsNm());
		assertEquals(bbsMasterInf.getBbsIntrcn(), boardMasterVO.getBbsIntrcn());
		assertEquals(bbsMasterInf.getReplyPosblAt(), boardMasterVO.getReplyPosblAt());
		assertEquals(bbsMasterInf.getFileAtchPosblAt(), boardMasterVO.getFileAtchPosblAt());
		assertEquals(bbsMasterInf.getAtchPosblFileNumber(), boardMasterVO.getAtchPosblFileNumber());
		assertEquals(bbsMasterInf.getAtchPosblFileSize(), boardMasterVO.getAtchPosblFileSize());
		assertEquals(bbsMasterInf.getTmplatId(), boardMasterVO.getTmplatId());
		assertEquals(bbsMasterInf.getFrstRegisterId(), boardMasterVO.getFrstRegisterId());
		assertEquals(bbsMasterInf.getFrstRegisterNm(), boardMasterVO.getFrstRegisterNm());
		assertEquals(bbsMasterInf.getFrstRegisterPnttm(), boardMasterVO.getFrstRegisterPnttm());
		assertEquals(bbsMasterInf.getBbsTyCodeNm(), boardMasterVO.getBbsTyCodeNm());
		assertEquals(bbsMasterInf.getTmplatNm(), boardMasterVO.getTmplatNm());
		assertEquals(bbsMasterInf.getAuthFlag(), boardMasterVO.getAuthFlag());
		assertEquals(bbsMasterInf.getTmplatCours(), boardMasterVO.getTmplatCours());
		assertEquals(bbsMasterInf.getCmmntyId(), boardMasterVO.getCmmntyId());
		assertEquals(bbsMasterInf.getBlogId(), boardMasterVO.getBlogId());
	}

}