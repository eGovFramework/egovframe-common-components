package egovframework.com.cop.cmt.service.impl;

import static org.junit.Assert.assertEquals;

import java.util.Date;

import javax.annotation.Resource;

import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;

import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.com.cop.bbs.service.Board;
import egovframework.com.cop.bbs.service.BoardMaster;
import egovframework.com.cop.bbs.service.impl.EgovArticleDAO;
import egovframework.com.cop.bbs.service.impl.EgovBBSMasterDAO;
import egovframework.com.cop.cmt.service.Comment;
import egovframework.com.cop.cmt.service.EgovArticleCommentService;
import egovframework.com.test.EgovTestV1;
import egovframework.rte.fdl.cmmn.exception.FdlException;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;
import egovframework.rte.fdl.string.EgovDateUtil;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@ContextConfiguration(classes = { EgovArticleCommentServiceImplTest_Configuration.class })
public class EgovArticleCommentServiceImplTest_insertArticleComment extends EgovTestV1 {

	// context-idgn-bbs.xml
	@Resource(name = "egovBBSMstrIdGnrService")
	private EgovIdGnrService egovBBSMstrIdGnrService;

	@Resource(name = "egovNttIdGnrService")
	private EgovIdGnrService egovNttIdGnrService;

//	@Resource(name = "egovBlogIdGnrService")
//	private EgovIdGnrService egovBlogIdGnrService;

	// context-idgn-AnswerNo.xml
//	@Resource(name = "egovAnswerNoGnrService")
//	private EgovIdGnrService egovAnswerNoGnrService;

	@Resource(name = "EgovBBSMasterDAO")
	private EgovBBSMasterDAO egovBBSMasterDAO;

	@Resource(name = "EgovArticleDAO")
	private EgovArticleDAO egovArticleDAO;

	@Resource(name = "EgovArticleCommentService")
	private EgovArticleCommentService egovArticleCommentService;

	// testData
	String today;
	LoginVO authenticatedUser;

	BoardMaster boardMaster;

	Board board;

	// given
	Comment comment;

	// when
	boolean result = false;

	@Test
//	@Commit
	public void test() {
		log.debug("test");
		testData();
		testData2_insertBBSMasterInf();
		testData3_insertArticle();
		given();
		when();
		then();
	}

	void testData() {
		today = " " + EgovDateUtil.toString(new Date(), null, null);
		authenticatedUser = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
	}

	void testData2_insertBBSMasterInf() {
		boardMaster = new BoardMaster();
		try {
			boardMaster.setBbsId(egovBBSMstrIdGnrService.getNextStringId());
		} catch (FdlException e) {
			log.error(e.getMessage());
		}

		egovBBSMasterDAO.insertBBSMasterInf(boardMaster);
	}

	void testData3_insertArticle() {
		board = new Board();
		try {
			board.setNttId(egovNttIdGnrService.getNextLongId());
		} catch (FdlException e) {
			log.error(e.getMessage());
		}

		board.setBbsId(boardMaster.getBbsId());
		egovArticleDAO.insertArticle(board);
	}

	void given() {
		comment = new Comment();
		// comment.setCommentNo("");
		comment.setNttId(board.getNttId());
		comment.setBbsId(boardMaster.getBbsId());
		comment.setWrterId(authenticatedUser.getUniqId()); // 작성자ID
		comment.setWrterNm(authenticatedUser.getName()); // 작성자명
		comment.setCommentPassword("test 비밀번호" + today); // 비밀번호
		comment.setCommentCn("test 댓글" + today); // 댓글
		comment.setFrstRegisterId(authenticatedUser.getUniqId()); // 최초등록자ID
	}

	void when() {
		try {
			egovArticleCommentService.insertArticleComment(comment);
			result = true;
		} catch (FdlException e) {
			log.error(e.getMessage());
		}
	}

	void then() {
		log.debug("result={}", result);

		assertEquals(result, true);
	}

}