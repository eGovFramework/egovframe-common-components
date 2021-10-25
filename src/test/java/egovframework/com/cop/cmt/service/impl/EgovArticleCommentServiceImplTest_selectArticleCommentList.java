package egovframework.com.cop.cmt.service.impl;

import static org.junit.Assert.assertEquals;

import java.util.Date;
import java.util.List;
import java.util.Map;

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
import egovframework.com.cop.cmt.service.CommentVO;
import egovframework.com.cop.cmt.service.EgovArticleCommentService;
import egovframework.com.test.EgovTestV1;
import egovframework.rte.fdl.cmmn.exception.FdlException;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;
import egovframework.rte.fdl.string.EgovDateUtil;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@ContextConfiguration(classes = { EgovArticleCommentServiceImplTest_Configuration.class })
public class EgovArticleCommentServiceImplTest_selectArticleCommentList extends EgovTestV1 {

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

	Comment comment;

	// given
	CommentVO commentVO;

	// when
	Map<String, Object> articleCommentList;
	List<CommentVO> resultList;
	String resultCnt;

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
		// comment.setCommentNo("");
		comment.setNttId(board.getNttId());
		comment.setBbsId(boardMaster.getBbsId());
		comment.setWrterId(authenticatedUser.getUniqId()); // 작성자ID
		comment.setWrterNm(authenticatedUser.getName()); // 작성자명
		comment.setCommentPassword("test 비밀번호" + today); // 비밀번호
		comment.setCommentCn("test 댓글" + today); // 댓글
		comment.setFrstRegisterId(authenticatedUser.getUniqId()); // 최초등록자ID

		try {
			egovArticleCommentService.insertArticleComment(comment);
		} catch (FdlException e) {
			log.error(e.getMessage());
		}
	}

	void given() {
		commentVO = new CommentVO();
		commentVO.setBbsId(boardMaster.getBbsId());
		commentVO.setNttId(board.getNttId());
		commentVO.setSubRecordCountPerPage(10);
		commentVO.setSubFirstIndex(0);
	}

	@SuppressWarnings("unchecked")
	void when() {
		articleCommentList = egovArticleCommentService.selectArticleCommentList(commentVO);

		resultList = (List<CommentVO>) articleCommentList.get("resultList");
		resultCnt = (String) articleCommentList.get("resultCnt");
	}

	void then() {
		log.debug("articleCommentList={}", articleCommentList);
		log.debug("resultList={}", resultList);
		log.debug("resultCnt={}", resultCnt);

		assertEquals(articleCommentList.size(), 2);
		assertEquals(resultList.size(), 1);
		assertEquals(resultCnt, "1");

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

		assertEquals(resultList.get(0).getCommentNo(), comment.getCommentNo());
		assertEquals(resultList.get(0).getNttId(), commentVO.getNttId());
		assertEquals(resultList.get(0).getBbsId(), commentVO.getBbsId());
		assertEquals(resultList.get(0).getWrterId(), comment.getWrterId());
		assertEquals(resultList.get(0).getWrterNm(), comment.getWrterNm());
		assertEquals(resultList.get(0).getCommentPassword(), comment.getCommentPassword());
		assertEquals(resultList.get(0).getCommentCn(), comment.getCommentCn());
//		assertEquals(resultList.get(0).getUseAt(), comment.getUseAt());
//		assertEquals(resultList.get(0).getFrstRegisterPnttm(), comment.getFrstRegisterPnttm());
//		assertEquals(resultList.get(0).getFrstRegisterNm(), comment.getFrstRegisterNm());
	}

}