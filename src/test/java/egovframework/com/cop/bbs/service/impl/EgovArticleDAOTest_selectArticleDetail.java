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
public class EgovArticleDAOTest_selectArticleDetail extends EgovTestV1 {

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
		board.setBbsId(boardMaster.getBbsId());
		board.setNttId(egovNttIdGnrService.getNextLongId());

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
