package egovframework.com.cop.bbs.service.impl;

import java.util.Date;

import javax.annotation.Resource;

import org.egovframe.rte.fdl.cmmn.exception.FdlException;
import org.egovframe.rte.fdl.idgnr.EgovIdGnrService;
import org.egovframe.rte.fdl.string.EgovDateUtil;
import org.springframework.stereotype.Service;

import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.com.cop.bbs.service.Blog;
import egovframework.com.cop.bbs.service.Board;
import egovframework.com.cop.bbs.service.BoardMaster;
import egovframework.com.cop.bbs.service.BoardVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class EgovArticleDAOTest_AaaTestData {

	private final EgovBBSMasterDAO egovBBSMasterDAO;
	private final EgovArticleDAO egovArticleDAO;

	@Resource(name = "egovBBSMstrIdGnrService")
	private EgovIdGnrService egovBBSMstrIdGnrService;

	@Resource(name = "egovNttIdGnrService")
	private EgovIdGnrService egovNttIdGnrService;

	@Resource(name = "egovBlogIdGnrService")
	private EgovIdGnrService egovBlogIdGnrService;

	public Blog insertBlogMaster(LoginVO loginVO) {
		String today = " " + EgovDateUtil.toString(new Date(), null, null);

		Blog blog = new Blog();
		try {
			blog.setBlogId(egovBlogIdGnrService.getNextStringId());
		} catch (FdlException e) {
			log.error("FdlException egovBlogIdGnrService");
		}
		try {
			blog.setBbsId(egovBBSMstrIdGnrService.getNextStringId()); // 게시판 ID
		} catch (FdlException e) {
			log.error("FdlException egovBBSMstrIdGnrService");
		}
		blog.setBlogIntrcn("test 블로그 소개" + today);
		blog.setBlogNm("test 블로그 명" + today);
		blog.setFrstRegisterId(loginVO.getUniqId()); // 최초등록자ID
//		blog.setFrstRegisterPnttm("");
//		blog.setLastUpdusrId("");
//		blog.setLastUpdusrPnttm("");
		blog.setRegistSeCode("REGC02"); // 등록구분코드 COM001 REGC02 커뮤니티 등록
//		blog.setTmplatId(""); // 템플릿 ID
		blog.setUseAt("Y"); // 사용여부
//		blog.setEmplyrId("");
//		blog.setUserNm("");
//		blog.setTmplatNm("");
		blog.setBlogAt("Y"); // 블로그 여부

		egovBBSMasterDAO.insertBlogMaster(blog);

		return blog;
	}

	public Board insertArticle() {
		BoardMaster boardMaster = insertBBSMasterInf();
		Board board = insertArticle(boardMaster);
		return board;
	}

	private BoardMaster insertBBSMasterInf() {
		BoardMaster boardMaster = new BoardMaster();
		try {
			boardMaster.setBbsId(egovBBSMstrIdGnrService.getNextStringId());
		} catch (FdlException e) {
			log.error("FdlException egovBBSMstrIdGnrService");
		}

		egovBBSMasterDAO.insertBBSMasterInf(boardMaster);

		return boardMaster;
	}

	private Board insertArticle(BoardMaster boardMaster) {
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

		LoginVO loginVO = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
		board.setFrstRegisterId(loginVO.getUniqId());

		egovArticleDAO.insertArticle(board);

		return board;
	}
	
	public BoardVO selectArticleDetailDefaultCnt() {
		BoardVO boardVO = new BoardVO();

		Board board = insertArticle();

		boardVO.setBbsId(board.getBbsId());

		return boardVO;
	}
	
	public BoardVO selectBlogListManagerCnt() {
		BoardVO boardVO = new BoardVO();

		LoginVO loginVO = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();

		Blog blog = insertBlogMaster(loginVO);

		BoardMaster boardMaster = new BoardMaster();
		boardMaster.setBbsId(blog.getBbsId());
		boardMaster.setBlogId(blog.getBlogId());
		String today = " " + EgovDateUtil.toString(new Date(), null, null);
		boardMaster.setBbsNm("test 게시판명" + today);
		boardMaster.setBbsIntrcn("test 게시판소개" + today);
		boardMaster.setUseAt("Y");
		boardMaster.setFrstRegisterId(loginVO.getUniqId());
		egovBBSMasterDAO.insertBBSMasterInf(boardMaster);

		boardVO.setBlogId(blog.getBlogId());
		boardVO.setBbsNm(boardMaster.getBbsNm());
		boardVO.setSearchWrd(boardMaster.getBbsIntrcn());
		boardVO.setBbsId(blog.getBbsId());
		boardVO.setUseAt(boardMaster.getUseAt());
//		boardVO.setFrstRegisterNm(loginVO.getName());
		boardVO.setFrstRegisterNm("테스트1");
		boardVO.setFrstRegisterPnttm(today.substring(1, 11));

		return boardVO;
	}

}