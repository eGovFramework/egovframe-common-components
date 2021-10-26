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
import egovframework.com.test.EgovTestV1;
import egovframework.rte.fdl.cmmn.exception.FdlException;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;
import egovframework.rte.fdl.string.EgovDateUtil;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@ContextConfiguration(classes = { EgovArticleCommentDAOTest_Configuration.class })
public class EgovArticleCommentDAOTest_insertArticleComment extends EgovTestV1 {

	@Resource(name = "egovBBSMstrIdGnrService")
	private EgovIdGnrService egovBBSMstrIdGnrService;

	@Resource(name = "egovNttIdGnrService")
	private EgovIdGnrService egovNttIdGnrService;

//	@Resource(name = "egovBlogIdGnrService")
//	private EgovIdGnrService egovBlogIdGnrService;

	@Resource(name = "egovAnswerNoGnrService")
	private EgovIdGnrService egovAnswerNoGnrService;

	@Resource
	private EgovBBSMasterDAO egovBBSMasterDAO;

	@Resource
	private EgovArticleDAO egovArticleDAO;

	@Resource
	private EgovArticleCommentDAO egovArticleCommentDAO;

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
		testData2();
		testData3();
		given();
		when();
		then();
	}

	void testData() {
		today = " " + EgovDateUtil.toString(new Date(), null, null);
		authenticatedUser = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
	}

	void testData2() {
		boardMaster = new BoardMaster();
		try {
			boardMaster.setBbsId(egovBBSMstrIdGnrService.getNextStringId());
		} catch (FdlException e) {
			log.error(e.getMessage());
		}

		egovBBSMasterDAO.insertBBSMasterInf(boardMaster);
	}

	void testData3() {
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
		try {
			comment.setCommentNo(egovAnswerNoGnrService.getNextLongId() + "");
		} catch (FdlException e) {
			log.error(e.getMessage());
		}

//		comment.setCommentNo("");
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
			egovArticleCommentDAO.insertArticleComment(comment);
			result = true;
		} catch (Exception e) {
			log.error(e.getMessage());
		}
	}

	void then() {
		log.debug("result={}", result);

		assertEquals(result, true);
	}

}