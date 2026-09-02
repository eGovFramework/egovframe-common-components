package egovframework.com.cop.bbs.service.impl;

import java.util.Date;

import org.egovframe.rte.fdl.cmmn.exception.BaseRuntimeException;
import org.egovframe.rte.fdl.cmmn.exception.FdlException;
import org.egovframe.rte.fdl.idgnr.EgovIdGnrService;
import org.egovframe.rte.fdl.string.EgovDateUtil;
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
class EgovArticleDAOTest_replyArticle extends EgovTestV1 {

	@Autowired
	private EgovBBSMasterDAO egovBBSMasterDAO;

	@Autowired
	private EgovArticleDAO egovArticleDAO;

	@Autowired
	private EgovIdGnrService egovBBSMstrIdGnrService;

	@Autowired
	private EgovIdGnrService egovNttIdGnrService;

	@Test
	void replyArticle() {
		// given
		BoardMaster boardMaster = new BoardMaster();
		try {
			boardMaster.setBbsId(egovBBSMstrIdGnrService.getNextStringId());
		} catch (FdlException e) {
			throw new BaseRuntimeException(e);
		}

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

		BoardVO boardVO = new BoardVO();
		boardVO.setNttId(board.getNttId());
		boardVO.setBbsId(board.getBbsId());

		log.debug("getNttId={}", boardVO.getNttId());
		log.debug("getBbsId={}", boardVO.getBbsId());

		Board boardReplyArticle = new Board();
		try {
			boardReplyArticle.setNttId((long) egovNttIdGnrService.getNextIntegerId());
		} catch (FdlException e) {
			throw new BaseRuntimeException(e);
		}
		boardReplyArticle.setBbsId(boardMaster.getBbsId());

		// when
		egovBBSMasterDAO.insertBBSMasterInf(boardMaster);
		egovArticleDAO.insertArticle(board);

		BoardVO selectArticleDetail = egovArticleDAO.selectArticleDetail(boardVO);
		log.debug("selectArticleDetail={}", selectArticleDetail);
		log.debug("getNttId={}", selectArticleDetail.getNttId());
		log.debug("getBbsId={}", selectArticleDetail.getBbsId());
		log.debug("getParnts={}", selectArticleDetail.getParnts());
		log.debug("getSortOrdr={}", selectArticleDetail.getSortOrdr());
		log.debug("getReplyLc={}", selectArticleDetail.getReplyLc());
		boardReplyArticle.setReplyAt("Y");
		boardReplyArticle.setParnts(Long.toString(selectArticleDetail.getNttId()));
		boardReplyArticle.setSortOrdr(selectArticleDetail.getSortOrdr());
		boardReplyArticle.setReplyLc(Integer.toString(Integer.parseInt(boardVO.getReplyLc()) + 1));

		today = " " + EgovDateUtil.toString(new Date(), null, null);
		boardReplyArticle.setNttSj("RE: test 게시물제목" + today);
		boardReplyArticle.setNttCn("RE: test 게시물내용" + today);

		egovArticleDAO.replyArticle(boardReplyArticle);

		// then
	}

}