package egovframework.com.cop.bbs.service.impl;

import static org.junit.Assert.assertEquals;

import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;

import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.com.cop.bbs.service.Board;
import egovframework.com.cop.bbs.service.BoardMaster;
import egovframework.com.cop.bbs.service.BoardVO;
import egovframework.com.test.EgovTestV1;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;
import egovframework.rte.fdl.string.EgovDateUtil;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@ContextConfiguration(classes = { EgovArticleDAOTest_Configuration.class })
public class EgovArticleDAOTest_selectGuestArticleList extends EgovTestV1 {

	@Autowired
	private EgovArticleDAO egovArticleDAO;

	@Autowired
	private EgovBBSMasterDAO egovBBSMasterDAO;

	@Autowired
	@Qualifier("egovNttIdGnrService")
	private EgovIdGnrService egovNttIdGnrService;

	@Autowired
	@Qualifier("egovBBSMstrIdGnrService")
	private EgovIdGnrService egovBBSMstrIdGnrService;

	@SuppressWarnings("unchecked")
	@Test
//	@Commit
	public void test() throws Exception {
		log.debug("test");

		// given
		LoginVO loginVO = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();

		// insertBBSMasterInf
		BoardMaster boardMaster = new BoardMaster();
		boardMaster.setBbsId(egovBBSMstrIdGnrService.getNextStringId());
		boardMaster.setFrstRegisterId(loginVO.getUniqId());

		egovBBSMasterDAO.insertBBSMasterInf(boardMaster);

		// insertArticle
		Board board = new Board();

		board.setNttId(egovNttIdGnrService.getNextIntegerId());
		board.setBbsId(boardMaster.getBbsId());

		String today = " " + EgovDateUtil.toString(new Date(), null, null);
		board.setNttSj("test 게시물제목" + today);
		board.setNttCn("test 게시물내용" + today);

		board.setParnts("0");
		board.setReplyLc("0");
		board.setReplyAt("N");

		board.setUseAt("Y");
//		board.setNoticeAt("Y");

		board.setFrstRegisterId(loginVO.getUniqId());

		egovArticleDAO.insertArticle(board);

		// selectNoticeArticleList
		BoardVO boardVO = new BoardVO();
		boardVO.setBbsId(boardMaster.getBbsId());
		boardVO.setRecordCountPerPage(10);
		boardVO.setFirstIndex(0);

		// when
		List<BoardVO> noticeArticleList = (List<BoardVO>) egovArticleDAO.selectGuestArticleList(boardVO);

		log.debug("noticeArticleList={}", noticeArticleList);
		log.debug("size={}", noticeArticleList.size());
		for (BoardVO noticeArticle : noticeArticleList) {
			log.debug("noticeArticle={}", noticeArticle);
			log.debug("getBbsId={}", noticeArticle.getBbsId());
			log.debug("getNttId={}", noticeArticle.getNttId());
			log.debug("getNttSj={}", noticeArticle.getNttSj());
			log.debug("getFrstRegisterPnttm={}", noticeArticle.getFrstRegisterPnttm());
			log.debug("getNttCn={}", noticeArticle.getNttCn());
			log.debug("getUseAt={}", noticeArticle.getUseAt());
			log.debug("getFrstRegisterNm={}", noticeArticle.getFrstRegisterNm());
			log.debug("getFrstRegisterId={}", noticeArticle.getFrstRegisterId());
		}

		// then
		assertEquals(noticeArticleList.get(0).getNttId(), board.getNttId());
		assertEquals(noticeArticleList.get(0).getBbsId(), boardVO.getBbsId());
	}

}
