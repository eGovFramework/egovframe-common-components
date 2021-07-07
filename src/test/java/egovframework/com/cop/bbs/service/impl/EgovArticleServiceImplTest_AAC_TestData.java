package egovframework.com.cop.bbs.service.impl;

import java.util.Date;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.github.javaparser.utils.Log;

import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.com.cop.bbs.service.Board;
import egovframework.com.cop.bbs.service.BoardMaster;
import egovframework.rte.fdl.cmmn.exception.FdlException;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;
import egovframework.rte.fdl.string.EgovDateUtil;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EgovArticleServiceImplTest_AAC_TestData {

	private final EgovBBSMasterDAO egovBBSMasterDAO;
//	private final EgovArticleDAO egovArticleDAO;

	@Resource(name = "egovBBSMstrIdGnrService")
	private EgovIdGnrService egovBBSMstrIdGnrService;

	@Resource(name = "egovNttIdGnrService")
	private EgovIdGnrService egovNttIdGnrService;

	@Resource(name = "egovBlogIdGnrService")
	private EgovIdGnrService egovBlogIdGnrService;

	public BoardMaster insertBBSMasterInf() {
		BoardMaster boardMaster = new BoardMaster();
		try {
			boardMaster.setBbsId(egovBBSMstrIdGnrService.getNextStringId());
		} catch (FdlException e) {
			Log.error(e.getMessage());
		}

		egovBBSMasterDAO.insertBBSMasterInf(boardMaster);

		return boardMaster;
	}

	public Board insertArticle() {
		// insertBBSMasterInf
		BoardMaster boardMaster = new BoardMaster();
		try {
			boardMaster.setBbsId(egovBBSMstrIdGnrService.getNextStringId());
		} catch (FdlException e) {
			Log.error(e.getMessage());
		}

		egovBBSMasterDAO.insertBBSMasterInf(boardMaster);

		// insertArticle
		String today = " " + EgovDateUtil.toString(new Date(), null, null);

		LoginVO loginVO = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();

		Board board = new Board();
		board.setBbsId(boardMaster.getBbsId()); // 게시판ID
		board.setNttSj("test 게시물제목" + today); // 게시물제목
		board.setNttCn("test 게시물내용" + today); // 게시물내용
		board.setNtcrId(loginVO.getUniqId()); // 게시자ID
		board.setNtcrNm(loginVO.getName()); // 게시자명
		board.setPassword("test 비밀번호" + today); // 비밀번호
		board.setNtceBgnde("1900-01-01"); // 게시시작일
		board.setNtceEndde("9999-12-31"); // 게시종료일
//		board.setAtchFileId(null); // 첨부파일ID
		board.setFrstRegisterId(loginVO.getUniqId()); // 최초등록자ID
		board.setSjBoldAt("Y"); // 제목볼드여부
		board.setNoticeAt("Y"); // 공지사항여부
		board.setSecretAt("Y"); // 비밀글여부
//		board.setBlogId(null); // 블로그 ID

		return board;
	}

}