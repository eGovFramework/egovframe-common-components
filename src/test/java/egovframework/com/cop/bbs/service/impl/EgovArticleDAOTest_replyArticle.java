package egovframework.com.cop.bbs.service.impl;

import static org.junit.Assert.assertEquals;

import java.util.Date;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;

import egovframework.com.cop.bbs.service.Board;
import egovframework.com.cop.bbs.service.BoardMaster;
import egovframework.com.cop.bbs.service.BoardVO;
import egovframework.com.test.EgovTestV1;
import org.egovframe.rte.fdl.idgnr.EgovIdGnrService;
import org.egovframe.rte.fdl.string.EgovDateUtil;

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
//	@Commit
	public void test() throws Exception {
		log.debug("test");

		// given
		BoardMaster boardMaster = new BoardMaster();
		boardMaster.setBbsId(egovBBSMstrIdGnrService.getNextStringId());

		Board board = new Board();
		board.setNttId(egovNttIdGnrService.getNextIntegerId());
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
		boardReplyArticle.setNttId(egovNttIdGnrService.getNextIntegerId());
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

//	@Test
//	@Commit
	public void test2() throws Exception {
		log.debug("test2");

		// given

		// selectArticleDetail

		BoardVO boardVO = new BoardVO();
		boardVO.setNttId(91);
		boardVO.setBbsId("BBSMSTR_000000000171");
		log.debug("getNttId={}", boardVO.getNttId());
		log.debug("getBbsId={}", boardVO.getBbsId());

		BoardVO selectArticleDetail = egovArticleDAO.selectArticleDetail(boardVO);
		log.debug("selectArticleDetail={}", selectArticleDetail);
		log.debug("getNttId={}", selectArticleDetail.getNttId());
		log.debug("getBbsId={}", selectArticleDetail.getBbsId());
		log.debug("getParnts={}", selectArticleDetail.getParnts());
		log.debug("getReplyLc={}", selectArticleDetail.getReplyLc());
		log.debug("getSortOrdr={}", selectArticleDetail.getSortOrdr());

		// replyArticle

		Board boardReplyArticle = new Board();
		boardReplyArticle.setNttId(egovNttIdGnrService.getNextIntegerId());
		boardReplyArticle.setBbsId(boardVO.getBbsId());

		boardReplyArticle.setReplyAt("Y");
		boardReplyArticle.setParnts(Long.toString(selectArticleDetail.getNttId()));
		boardReplyArticle.setSortOrdr(selectArticleDetail.getSortOrdr());
		boardReplyArticle.setReplyLc(Integer.toString(Integer.parseInt(boardVO.getReplyLc()) + 1));

		String today = " " + EgovDateUtil.toString(new Date(), null, null);
		boardReplyArticle.setNttSj("RE: test 게시물제목" + today);
		boardReplyArticle.setNttCn("RE: test 게시물내용" + today);

		log.debug("boardReplyArticle={}", boardReplyArticle);
		log.debug("getNttId={}", boardReplyArticle.getNttId());
		log.debug("getBbsId={}", boardReplyArticle.getBbsId());

		log.debug("getReplyAt={}", boardReplyArticle.getReplyAt());
		log.debug("getParnts={}", boardReplyArticle.getParnts());
		log.debug("getSortOrdr={}", boardReplyArticle.getSortOrdr());
		log.debug("getReplyLc={}", boardReplyArticle.getReplyLc());

		// when
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