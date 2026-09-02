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
class EgovArticleDAOTest_updateArticle extends EgovTestV1 {

	@Autowired
	private EgovBBSMasterDAO egovBBSMasterDAO;

	@Autowired
	private EgovArticleDAO egovArticleDAO;

	@Autowired
	private EgovIdGnrService egovBBSMstrIdGnrService;

	@Autowired
	private EgovIdGnrService egovNttIdGnrService;

	@Test
	void test() {
		// given

		// insertBBSMasterInf
		BoardMaster boardMaster = new BoardMaster();
		try {
			boardMaster.setBbsId(egovBBSMstrIdGnrService.getNextStringId());
		} catch (FdlException e) {
			throw new BaseRuntimeException(e);
		}

		egovBBSMasterDAO.insertBBSMasterInf(boardMaster);

		// insertArticle
		Board boardInsertArticle = new Board();

		try {
			boardInsertArticle.setNttId((long) egovNttIdGnrService.getNextIntegerId());
		} catch (FdlException e) {
			throw new BaseRuntimeException(e);
		}
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
		egovArticleDAO.updateArticle(selectArticleDetail);

		// then
	}

}