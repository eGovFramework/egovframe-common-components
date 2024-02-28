package egovframework.com.cop.bbs.service.impl;

import static org.junit.Assert.assertEquals;

import java.util.Date;
import java.util.List;

import org.egovframe.rte.fdl.cmmn.exception.FdlException;
import org.egovframe.rte.fdl.idgnr.EgovIdGnrService;
import org.egovframe.rte.fdl.string.EgovDateUtil;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;

import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.com.cop.bbs.service.Board;
import egovframework.com.cop.bbs.service.BoardMaster;
import egovframework.com.cop.bbs.service.BoardVO;
import egovframework.com.test.EgovTestV1;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@ContextConfiguration(classes = { EgovArticleDAOTest_Configuration.class })
public class EgovArticleDAOTest_selectGuestArticleList extends EgovTestV1 {

	@Autowired
	private EgovArticleDAO egovArticleDAO;

	@Autowired
	private EgovBBSMasterDAO egovBBSMasterDAO;

	@Autowired
	@Qualifier("egovNttIdGnrService")
	private EgovIdGnrService egovNttIdGnrService;

	@Autowired
	@Qualifier("egovBBSMstrIdGnrService")
	private EgovIdGnrService egovBBSMstrIdGnrService;

	@Test
//	@Commit
	public void test() {
		log.debug("test");
		
		// given
		Board board = testData();

		BoardVO boardVO = new BoardVO();
		boardVO.setBbsId(board.getBbsId());
		boardVO.setRecordCountPerPage(10);
		boardVO.setFirstIndex(0);

		// when
		List<BoardVO> results = egovArticleDAO.selectGuestArticleList(boardVO);

		log.debug("results={}", results);
		log.debug("size={}", results.size());
		for (BoardVO result : results) {
			log.debug("result={}", result);
			log.debug("getBbsId={}", result.getBbsId());
			log.debug("getNttId={}", result.getNttId());
			log.debug("getNttSj={}", result.getNttSj());
			log.debug("getFrstRegisterPnttm={}", result.getFrstRegisterPnttm());
			log.debug("getNttCn={}", result.getNttCn());
			log.debug("getUseAt={}", result.getUseAt());
			log.debug("getFrstRegisterNm={}", result.getFrstRegisterNm());
			log.debug("getFrstRegisterId={}", result.getFrstRegisterId());
		}

		log.debug("board={}", board);
		log.debug("getBbsId={}", board.getBbsId());
		log.debug("getNttId={}", board.getNttId());

		// then
		assertEquals(results.get(0).getNttId(), board.getNttId());
		assertEquals(results.get(0).getBbsId(), board.getBbsId());
	}

	private Board testData() {
		log.debug("testData");

		// given
		LoginVO loginVO = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();

		// insertBBSMasterInf
		BoardMaster boardMaster = new BoardMaster();
		try {
			boardMaster.setBbsId(egovBBSMstrIdGnrService.getNextStringId());
		} catch (FdlException e) {
			log.error("FdlException egovBBSMstrIdGnrService");
		}
		boardMaster.setFrstRegisterId(loginVO.getUniqId());

		egovBBSMasterDAO.insertBBSMasterInf(boardMaster);

		// insertArticle
		Board board = new Board();

		try {
			board.setNttId(egovNttIdGnrService.getNextIntegerId());
		} catch (FdlException e) {
			log.error("FdlException egovBBSMstrIdGnrService");
		}
		board.setBbsId(boardMaster.getBbsId());

		String today = " " + EgovDateUtil.toString(new Date(), null, null);
		board.setNttSj("test 게시물제목" + today);
		board.setNttCn("test 게시물내용" + today);

		board.setParnts("0");
		board.setReplyLc("0");
		board.setReplyAt("N");

		board.setUseAt("Y");
//		board.setNoticeAt("Y");

		board.setFrstRegisterId(loginVO.getUniqId());

		egovArticleDAO.insertArticle(board);

		return board;
	}

}