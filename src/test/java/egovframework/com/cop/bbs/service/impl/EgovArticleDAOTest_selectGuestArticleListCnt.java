package egovframework.com.cop.bbs.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Date;

import org.egovframe.rte.fdl.cmmn.exception.BaseRuntimeException;
import org.egovframe.rte.fdl.cmmn.exception.FdlException;
import org.egovframe.rte.fdl.idgnr.EgovIdGnrService;
import org.egovframe.rte.fdl.string.EgovDateUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
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
class EgovArticleDAOTest_selectGuestArticleListCnt extends EgovTestV1 {

	@Autowired
	private EgovArticleDAO egovArticleDAO;

	@Autowired
	private EgovBBSMasterDAO egovBBSMasterDAO;

	@Autowired
	private EgovIdGnrService egovNttIdGnrService;

	@Autowired
	private EgovIdGnrService egovBBSMstrIdGnrService;

	@Test
	void selectGuestArticleListCnt() {
		// given
		LoginVO loginVO = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();

		// insertBBSMasterInf
		BoardMaster boardMaster = new BoardMaster();
		try {
			boardMaster.setBbsId(egovBBSMstrIdGnrService.getNextStringId());
		} catch (FdlException e) {
			throw new BaseRuntimeException(e);
		}
		boardMaster.setFrstRegisterId(loginVO.getUniqId());

		egovBBSMasterDAO.insertBBSMasterInf(boardMaster);

		// insertArticle
		Board board = new Board();

		try {
			board.setNttId((long) egovNttIdGnrService.getNextIntegerId());
		} catch (FdlException e) {
			throw new BaseRuntimeException(e);
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

		// selectGuestArticleListCnt
		BoardVO vo = new BoardVO();
		vo.setBbsId(boardMaster.getBbsId());

		// when
		int guestArticleListCnt = egovArticleDAO.selectGuestArticleListCnt(vo);
		log.debug("guestArticleListCnt={}", guestArticleListCnt);

		// then
		assertEquals(guestArticleListCnt, 1);
	}

}