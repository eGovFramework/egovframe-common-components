package egovframework.com.cop.bbs.service.impl;

import static org.junit.Assert.assertEquals;

import java.util.Date;

import javax.annotation.Resource;

import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;

import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.com.cop.bbs.service.Board;
import egovframework.com.cop.bbs.service.BoardMaster;
import egovframework.com.cop.cmt.service.Comment;
import egovframework.com.cop.cmt.service.CommentVO;
import egovframework.com.cop.cmt.service.impl.EgovArticleCommentDAO;
import egovframework.com.test.EgovTestV1;
import egovframework.rte.fdl.cmmn.exception.FdlException;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;
import egovframework.rte.fdl.string.EgovDateUtil;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@ContextConfiguration(classes = { EgovArticleCommentDAOTest_Configuration.class })
public class EgovArticleCommentDAOTest_selectArticleCommentDetail extends EgovTestV1 {

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

	CommentVO commentVO;

	// when
	CommentVO articleCommentDetail;

	@Test
//	@Commit
	public void test() {
		log.debug("test");
		testData();
		testData2_insertBBSMasterInf();
		testData3_insertArticle();
		testData4_insertArticleComment();
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

	void testData4_insertArticleComment() {
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

		egovArticleCommentDAO.insertArticleComment(comment);
	}

	void given() {
		commentVO = new CommentVO();
		commentVO.setCommentNo(comment.getCommentNo());
	}

	void when() {
		articleCommentDetail = egovArticleCommentDAO.selectArticleCommentDetail(commentVO);
	}

	void then() {
		log.debug("articleCommentDetail={}", articleCommentDetail);

		log.debug("commentNo={}, {}", articleCommentDetail.getCommentNo(), commentVO.getCommentNo());
		log.debug("nttId={}, {}", articleCommentDetail.getNttId(), comment.getNttId());
		log.debug("bbsId={}, {}", articleCommentDetail.getBbsId(), comment.getBbsId());
		log.debug("wrterId={}, {}", articleCommentDetail.getWrterId(), comment.getWrterId());
		log.debug("wrterNm={}, {}", articleCommentDetail.getWrterNm(), comment.getWrterNm());
		log.debug("commentPassword={}, {}", articleCommentDetail.getCommentPassword(), comment.getCommentPassword());
		log.debug("commentCn={}, {}", articleCommentDetail.getCommentCn(), comment.getCommentCn());
		log.debug("useAt={}, {}", articleCommentDetail.getUseAt(), comment.getUseAt());
		log.debug("frstRegisterPnttm={}, {}", articleCommentDetail.getFrstRegisterPnttm(),
				comment.getFrstRegisterPnttm());
		log.debug("frstRegisterNm={}, {}", articleCommentDetail.getFrstRegisterNm(), comment.getFrstRegisterNm());

		assertEquals(articleCommentDetail.getCommentNo(), commentVO.getCommentNo());
		assertEquals(articleCommentDetail.getNttId(), comment.getNttId());
		assertEquals(articleCommentDetail.getBbsId(), comment.getBbsId());
		assertEquals(articleCommentDetail.getWrterId(), comment.getWrterId());
		assertEquals(articleCommentDetail.getWrterNm(), comment.getWrterNm());
		assertEquals(articleCommentDetail.getCommentPassword(), comment.getCommentPassword());
		assertEquals(articleCommentDetail.getCommentCn(), comment.getCommentCn());
//		assertEquals(articleCommentDetail.getUseAt(), comment.getUseAt());
//		assertEquals(articleCommentDetail.getFrstRegisterPnttm(), comment.getFrstRegisterPnttm());
//		assertEquals(articleCommentDetail.getFrstRegisterNm(), comment.getFrstRegisterNm());
	}

}