package egovframework.com.cop.bbs.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.com.cop.bbs.service.Board;
import egovframework.com.cop.bbs.service.BoardMasterVO;
import egovframework.com.cop.bbs.service.BoardVO;
import jakarta.annotation.Resource;

@Repository("EgovArticleDAO")
public class EgovArticleDAO {

	@Resource(name = "egovArticleMapper")
	private EgovArticleMapper egovArticleMapper;

	public List<BoardVO> selectArticleList(BoardVO boardVO) {
		return egovArticleMapper.selectArticleList(boardVO);
	}

	public int selectArticleListCnt(BoardVO boardVO) {
		return egovArticleMapper.selectArticleListCnt(boardVO);
	}

	public int selectMaxInqireCo(BoardVO boardVO) {
		return egovArticleMapper.selectMaxInqireCo(boardVO);
	}

	public void updateInqireCo(BoardVO boardVO) {
		egovArticleMapper.updateInqireCo(boardVO);
	}

	public BoardVO selectArticleDetail(BoardVO boardVO) {
		return egovArticleMapper.selectArticleDetail(boardVO);
	}

	public void replyArticle(Board board) {
		egovArticleMapper.replyArticle(board);
	}

	public void insertArticle(Board board) {
		egovArticleMapper.insertArticle(board);
	}

	public void updateArticle(Board board) {
		egovArticleMapper.updateArticle(board);
	}

	public void deleteArticle(Board board) {
		egovArticleMapper.deleteArticle(board);
	}

	public List<BoardVO> selectNoticeArticleList(BoardVO boardVO) {
		return egovArticleMapper.selectNoticeArticleList(boardVO);
	}

	public List<BoardVO> selectGuestArticleList(BoardVO vo) {
		return egovArticleMapper.selectGuestArticleList(vo);
	}

	public int selectGuestArticleListCnt(BoardVO vo) {
		return egovArticleMapper.selectGuestArticleListCnt(vo);
	}

	/*
	 * 블로그 관련
	 */
	public BoardVO selectArticleCnOne(BoardVO boardVO) {
		return egovArticleMapper.selectArticleCnOne(boardVO);
	}

	public List<BoardVO> selectBlogNmList(BoardVO boardVO) {
		return egovArticleMapper.selectBlogNmList(boardVO);
	}

	public List<BoardMasterVO> selectBlogListManager(BoardVO vo) {
		return egovArticleMapper.selectBlogListManager(vo);
	}

	public int selectBlogListManagerCnt(BoardVO vo) {
		return egovArticleMapper.selectBlogListManagerCnt(vo);
	}

	public List<BoardVO> selectArticleDetailDefault(BoardVO boardVO) {
		return egovArticleMapper.selectArticleDetailDefault(boardVO);
	}

	public int selectArticleDetailDefaultCnt(BoardVO boardVO) {
		return egovArticleMapper.selectArticleDetailDefaultCnt(boardVO);
	}

	public List<BoardVO> selectArticleDetailCn(BoardVO boardVO) {
		return egovArticleMapper.selectArticleDetailCn(boardVO);
	}

	public int selectLoginUser(BoardVO boardVO) {
		return egovArticleMapper.selectLoginUser(boardVO);
	}

}
