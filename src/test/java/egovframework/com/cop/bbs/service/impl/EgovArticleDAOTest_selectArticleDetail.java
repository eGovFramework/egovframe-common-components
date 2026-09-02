package egovframework.com.cop.bbs.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
class EgovArticleDAOTest_selectArticleDetail extends EgovTestV1 {

	@Autowired
	private EgovBBSMasterDAO egovBBSMasterDAO;

	@Autowired
	private EgovArticleDAO egovArticleDAO;

	@Autowired
	private EgovIdGnrService egovBBSMstrIdGnrService;

	@Autowired
	private EgovIdGnrService egovNttIdGnrService;

	@Test
	void selectArticleDetail() {
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

		log.debug("getBbsId={}", boardVO.getBbsId());
		log.debug("getNttId={}", boardVO.getNttId());

		// when
		egovBBSMasterDAO.insertBBSMasterInf(boardMaster);
		egovArticleDAO.insertArticle(board);

		BoardVO selectArticleDetail = egovArticleDAO.selectArticleDetail(boardVO);
		log.debug("selectArticleDetail={}", selectArticleDetail);
		log.debug("getBbsId={}", selectArticleDetail.getBbsId());
		log.debug("getNttId={}", selectArticleDetail.getNttId());

		// then
		assertEquals(selectArticleDetail.getBbsId(), boardVO.getBbsId());
		assertEquals(selectArticleDetail.getNttId(), boardVO.getNttId());
	}

}