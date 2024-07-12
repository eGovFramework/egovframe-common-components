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

import egovframework.com.cop.bbs.service.Board;
import egovframework.com.cop.bbs.service.BoardMaster;
import egovframework.com.cop.bbs.service.BoardVO;
import egovframework.com.test.EgovTestV1;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@ContextConfiguration(classes = { EgovArticleDAOTest_Configuration.class })
public class EgovArticleDAOTest_selectArticleList extends EgovTestV1 {

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
//	@Rollback(true)
//	@Rollback(false)
	public void test() throws Exception {
		log.debug("test");
		
		Board board = testData();

		// given
		BoardVO boardVO = new BoardVO();
		boardVO.setBbsId(board.getBbsId());

		boardVO.setSearchCnd("0");
//		boardVO.setSearchCnd("1");
//		boardVO.setSearchCnd("2");

		boardVO.setSearchWrd(board.getNttSj());
		boardVO.setRecordCountPerPage(10);
		boardVO.setFirstIndex(0);

		// when
		List<BoardVO> results = egovArticleDAO.selectArticleList(boardVO);
		int size = results.size();

		log.debug("results={}", results);
		log.debug("size={}", size);

		for (BoardVO result : results) {
			log.debug("result={}", result);
			log.debug("getBbsId={}", result.getBbsId());
			log.debug("getNttId={}", result.getNttId());
			log.debug("getNttSj={}", result.getNttSj());
		}
		
		log.debug("board={}", board);
		log.debug("getBbsId={}", board.getBbsId());
		log.debug("getNttId={}", board.getNttId());
		log.debug("getNttSj={}", board.getNttSj());

		// then
		assertEquals(results.get(0).getNttId(), board.getNttId());
		assertEquals(results.get(0).getBbsId(), board.getBbsId());
		assertEquals(results.get(0).getNttSj(), board.getNttSj());
	}
	
	private Board testData() {
		log.debug("testData");

		// given
		BoardMaster boardMaster = new BoardMaster();
		try {
			boardMaster.setBbsId(egovBBSMstrIdGnrService.getNextStringId());
		} catch (FdlException e) {
			log.error("FdlException egovBBSMstrIdGnrService");
		}

		Board board = new Board();

		try {
			board.setNttId(egovNttIdGnrService.getNextIntegerId());
		} catch (FdlException e) {
			log.error("FdlException egovNttIdGnrService");
		}
		board.setBbsId(boardMaster.getBbsId());

		String today = " " + EgovDateUtil.toString(new Date(), null, null);
		board.setNttSj("test 게시물제목" + today);
		board.setNttCn("test 게시물내용" + today);

		board.setParnts("0");
		board.setReplyLc("0");
		board.setReplyAt("N");

		// when
		egovBBSMasterDAO.insertBBSMasterInf(boardMaster);

		egovArticleDAO.insertArticle(board);

		return board;
	}

}