package egovframework.com.cop.bbs.service.impl;

import static org.junit.Assert.assertEquals;

import java.util.Date;
import java.util.List;

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
public class EgovBBSMasterDAOTest_selectBBSListPortlet extends EgovTestV1 {

	@Autowired
	private EgovBBSMasterDAO egovBBSMasterDAO;

	@Resource(name = "egovBBSMstrIdGnrService")
	private EgovIdGnrService egovBBSMstrIdGnrService;

//	@Resource(name = "egovNttIdGnrService")
//	private EgovIdGnrService egovNttIdGnrService;

	@Resource(name = "egovBlogIdGnrService")
	private EgovIdGnrService egovBlogIdGnrService;

	@Test
	public void test() {
		log.debug("test");

		// given
		BoardMasterVO boardMasterVO = testData();

		// when
		List<BoardMasterVO> bbsListPortlet = egovBBSMasterDAO.selectBBSListPortlet(boardMasterVO);

		// then
		assertEquals(bbsListPortlet.get(0).getBbsId(), boardMasterVO.getBbsId());
		assertEquals(bbsListPortlet.get(0).getBbsTyCodeNm(), boardMasterVO.getBbsTyCodeNm());
		assertEquals(bbsListPortlet.get(0).getBbsNm(), boardMasterVO.getBbsNm());

		debug(bbsListPortlet);
	}

	public BoardMasterVO testData() {
		BoardMasterVO boardMasterVO = new BoardMasterVO();

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

		boardMasterVO.setBbsId(boardMaster.getBbsId());
		boardMasterVO.setBbsTyCodeNm(boardMaster.getBbsTyCode());
		boardMasterVO.setBbsNm(boardMaster.getBbsNm());

		return boardMasterVO;
	}

	void debug(List<BoardMasterVO> bbsListPortlet) {
		bbsListPortlet.forEach(bbsPortlet -> {
			log.debug("bbsId={}", bbsPortlet.getBbsId());
			log.debug("bbsTyCodeNm={}", bbsPortlet.getBbsTyCodeNm());
			log.debug("bbsNm={}", bbsPortlet.getBbsNm());
		});
	}

}