package egovframework.com.cop.bbs.service.impl;

import static org.junit.Assert.assertEquals;

import java.util.Date;
import java.util.List;

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
public class EgovArticleCommentDAOTest_selectArticleCommentList extends EgovTestV1 {

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
//	List<?> articleCommentList;
	List<CommentVO> articleCommentList;

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
		commentVO.setBbsId(boardMaster.getBbsId());
		commentVO.setNttId(board.getNttId());
		commentVO.setSubRecordCountPerPage(10);
		commentVO.setSubFirstIndex(0);
	}

	void when() {
		articleCommentList = egovArticleCommentDAO.selectArticleCommentList(commentVO);
//		articleCommentList = (List<CommentVO>) egovArticleCommentDAO.selectArticleCommentList(commentVO);
	}

	void then() {
		log.debug("commentNo={}", comment.getCommentNo());
		log.debug("nttId={}", commentVO.getNttId());
		log.debug("bbsId={}", commentVO.getBbsId());
		log.debug("wrterId={}", comment.getWrterId());
		log.debug("wrterNm={}", comment.getWrterNm());
		log.debug("commentPassword={}", comment.getCommentPassword());
		log.debug("commentCn={}", comment.getCommentCn());
		log.debug("useAt={}", comment.getUseAt());
		log.debug("frstRegisterPnttm={}", comment.getFrstRegisterPnttm());
		log.debug("frstRegisterNm={}", comment.getFrstRegisterNm());

		assertEquals(articleCommentList.get(0).getCommentNo(), comment.getCommentNo());
		assertEquals(articleCommentList.get(0).getNttId(), commentVO.getNttId());
		assertEquals(articleCommentList.get(0).getBbsId(), commentVO.getBbsId());
		assertEquals(articleCommentList.get(0).getWrterId(), comment.getWrterId());
		assertEquals(articleCommentList.get(0).getWrterNm(), comment.getWrterNm());
		assertEquals(articleCommentList.get(0).getCommentPassword(), comment.getCommentPassword());
		assertEquals(articleCommentList.get(0).getCommentCn(), comment.getCommentCn());
//		assertEquals(articleCommentList.get(0).getUseAt(), comment.getUseAt());
//		assertEquals(articleCommentList.get(0).getFrstRegisterPnttm(), comment.getFrstRegisterPnttm());
//		assertEquals(articleCommentList.get(0).getFrstRegisterNm(), comment.getFrstRegisterNm());
	}

}