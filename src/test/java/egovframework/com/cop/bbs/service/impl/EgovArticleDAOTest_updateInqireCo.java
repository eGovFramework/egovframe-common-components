package egovframework.com.cop.bbs.service.impl;

import org.egovframe.rte.fdl.cmmn.exception.BaseRuntimeException;
import org.egovframe.rte.fdl.cmmn.exception.FdlException;
import org.egovframe.rte.fdl.idgnr.EgovIdGnrService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import egovframework.com.cop.bbs.service.Board;
import egovframework.com.cop.bbs.service.BoardMaster;
import egovframework.com.cop.bbs.service.BoardVO;
import egovframework.com.test.EgovTestV1;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@ContextConfiguration(classes = { EgovArticleDAOTest_Configuration.class })
class EgovArticleDAOTest_updateInqireCo extends EgovTestV1 {

	@Autowired
	private EgovBBSMasterDAO egovBBSMasterDAO;

	@Autowired
	private EgovArticleDAO egovArticleDAO;

	@Autowired
	private EgovIdGnrService egovBBSMstrIdGnrService;

	@Autowired
	private EgovIdGnrService egovNttIdGnrService;

	@Test
	void updateInqireCo() {
		// given
		BoardMaster boardMaster = new BoardMaster();
		try {
			boardMaster.setBbsId(egovBBSMstrIdGnrService.getNextStringId());
		} catch (FdlException e) {
			throw new BaseRuntimeException(e);
		}

		Board board = new Board();
		board.setBbsId(boardMaster.getBbsId());
		try {
			board.setNttId(egovNttIdGnrService.getNextLongId());
		} catch (FdlException e) {
			throw new BaseRuntimeException(e);
		}

		BoardVO boardVO = new BoardVO();
		boardVO.setBbsId(board.getBbsId());
		boardVO.setNttId(board.getNttId());

		// when
		egovBBSMasterDAO.insertBBSMasterInf(boardMaster);
		egovArticleDAO.insertArticle(board);

		boardVO.setInqireCo(egovArticleDAO.selectMaxInqireCo(boardVO));
		log.debug("getInqireCo={}", boardVO.getInqireCo());

		egovArticleDAO.updateInqireCo(boardVO);

		// then
	}

}