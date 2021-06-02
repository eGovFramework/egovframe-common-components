package egovframework.com.cop.bbs.service.impl;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;

import egovframework.com.cop.bbs.service.Board;
import egovframework.com.cop.bbs.service.BoardMaster;
import egovframework.com.cop.bbs.service.BoardVO;
import egovframework.com.test.EgovTestV1;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@ContextConfiguration(classes = { EgovArticleDAOTest_Configuration.class })
public class EgovArticleDAOTest_replyArticle extends EgovTestV1 {

	@Autowired
	private EgovBBSMasterDAO egovBBSMasterDAO;

	@Autowired
	private EgovArticleDAO egovArticleDAO;

	@Autowired
	@Qualifier("egovBBSMstrIdGnrService")
	private EgovIdGnrService egovBBSMstrIdGnrService;

	@Autowired
	@Qualifier("egovNttIdGnrService")
	private EgovIdGnrService egovNttIdGnrService;

	@Test
	@Rollback(true)
//	@Rollback(false)
	public void test() throws Exception {
		log.debug("test");

		// given
		BoardMaster boardMaster = new BoardMaster();
		boardMaster.setBbsId(egovBBSMstrIdGnrService.getNextStringId());

		Board board = new Board();
		board.setNttId(egovNttIdGnrService.getNextLongId());
		board.setBbsId(boardMaster.getBbsId());

		BoardVO boardVO = new BoardVO();
		boardVO.setNttId(board.getNttId());
		boardVO.setBbsId(board.getBbsId());

		log.debug("getNttId={}", boardVO.getNttId());
		log.debug("getBbsId={}", boardVO.getBbsId());

		Board boardReplyArticle = new Board();
		boardReplyArticle.setNttId(egovNttIdGnrService.getNextLongId());
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
		boardReplyArticle.setParnts(selectArticleDetail.getParnts());
		boardReplyArticle.setSortOrdr(selectArticleDetail.getSortOrdr());
		boardReplyArticle.setReplyLc(String.valueOf(Integer.valueOf(selectArticleDetail.getReplyLc()) + 1));

		boolean result = false;
		try {
			egovArticleDAO.replyArticle(boardReplyArticle);
			result = true;
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		log.debug("result={}", result);

		// then
		assertEquals(result, true);
	}

}
