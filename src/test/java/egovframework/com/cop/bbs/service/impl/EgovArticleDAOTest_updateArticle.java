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
public class EgovArticleDAOTest_updateArticle extends EgovTestV1 {

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

		// insertBBSMasterInf
		BoardMaster boardMaster = new BoardMaster();
		boardMaster.setBbsId(egovBBSMstrIdGnrService.getNextStringId());

		egovBBSMasterDAO.insertBBSMasterInf(boardMaster);

		// insertArticle
		Board boardInsertArticle = new Board();

		boardInsertArticle.setNttId(egovNttIdGnrService.getNextIntegerId());
		boardInsertArticle.setBbsId(boardMaster.getBbsId());

		String today = " " + EgovDateUtil.toString(new Date(), null, null);
		boardInsertArticle.setNttSj("test 게시물제목" + today);
		boardInsertArticle.setNttCn("test 게시물내용" + today);

		boardInsertArticle.setParnts("0");
		boardInsertArticle.setReplyLc("0");
		boardInsertArticle.setReplyAt("N");

		egovArticleDAO.insertArticle(boardInsertArticle);

		// selectArticleDetail
		BoardVO boardVO = new BoardVO();
		boardVO.setNttId(boardInsertArticle.getNttId());
		boardVO.setBbsId(boardInsertArticle.getBbsId());

		BoardVO selectArticleDetail = egovArticleDAO.selectArticleDetail(boardVO);
		selectArticleDetail.setNttSj(selectArticleDetail.getNttSj() + " 수정");

		// when

		boolean result = false;
		try {
			egovArticleDAO.updateArticle(selectArticleDetail);
			result = true;
		} catch (Exception e) {
			log.error(e.getMessage());
		}

		// then
		assertEquals(result, true);
	}

}