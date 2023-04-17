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
import org.egovframe.rte.fdl.idgnr.EgovIdGnrService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@ContextConfiguration(classes = { EgovArticleDAOTest_Configuration.class })
public class EgovArticleDAOTest_updateInqireCo extends EgovTestV1 {

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

		// when
		egovBBSMasterDAO.insertBBSMasterInf(boardMaster);
		egovArticleDAO.insertArticle(board);

		boardVO.setInqireCo(egovArticleDAO.selectMaxInqireCo(boardVO));
		log.debug("getInqireCo={}", boardVO.getInqireCo());

		boolean updateInqireCo = false;
		try {
			egovArticleDAO.updateInqireCo(boardVO);
			updateInqireCo = true;
		} catch (Exception e) {
			log.error(e.getMessage());
		}

		log.debug("updateInqireCo={}", updateInqireCo);

		// then
		assertEquals(updateInqireCo, true);
	}

}