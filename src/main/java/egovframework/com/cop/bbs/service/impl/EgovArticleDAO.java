package egovframework.com.cop.bbs.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.com.cmm.service.impl.EgovComAbstractDAO;
import egovframework.com.cop.bbs.service.Board;
import egovframework.com.cop.bbs.service.BoardMasterVO;
import egovframework.com.cop.bbs.service.BoardVO;

@Repository("EgovArticleDAO")
public class EgovArticleDAO extends EgovComAbstractDAO {

	public List<?> selectArticleList(BoardVO boardVO) {
		return list("BBSArticle.selectArticleList", boardVO);
	}

	public int selectArticleListCnt(BoardVO boardVO) {
		return (Integer)selectOne("BBSArticle.selectArticleListCnt", boardVO);
	}

	public int selectMaxInqireCo(BoardVO boardVO) {
		return (Integer)selectOne("BBSArticle.selectMaxInqireCo", boardVO);
	}

	public void updateInqireCo(BoardVO boardVO) {
		update("BBSArticle.updateInqireCo", boardVO);
	}

	public BoardVO selectArticleDetail(BoardVO boardVO) {
		return (BoardVO) selectOne("BBSArticle.selectArticleDetail", boardVO);
	}
	
	public void replyArticle(Board board) {
		insert("BBSArticle.replyArticle", board);
	}

	public void insertArticle(Board board) {
		insert("BBSArticle.insertArticle", board);
	}

	public void updateArticle(Board board) {
		update("BBSArticle.updateArticle", board);
	}

	public void deleteArticle(Board board) {
		update("BBSArticle.deleteArticle", board);
		
	}

	public List<BoardVO> selectNoticeArticleList(BoardVO boardVO) {
		return (List<BoardVO>) list("BBSArticle.selectNoticeArticleList", boardVO);
	}
	
	public List<?> selectGuestArticleList(BoardVO vo) {
		return list("BBSArticle.selectGuestArticleList", vo);
	}

	public int selectGuestArticleListCnt(BoardVO vo) {
		return (Integer)selectOne("BBSArticle.selectGuestArticleListCnt", vo);
	}
	
	/*
	 * 블로그 관련
	 */
	public BoardVO selectArticleCnOne(BoardVO boardVO) {
		return (BoardVO) selectOne("BBSArticle.selectArticleCnOne", boardVO);
	}
	
	public List<BoardVO> selectBlogNmList(BoardVO boardVO) {
		return (List<BoardVO>) list("BBSArticle.selectBlogNmList", boardVO);
	}
	
	public List<?> selectBlogListManager(BoardVO vo) {
		return list("BBSArticle.selectBlogListManager", vo);
	}
	
	public int selectBlogListManagerCnt(BoardVO vo) {
		return (Integer)selectOne("BBSArticle.selectBlogListManagerCnt", vo);
	}
	
	public List<BoardVO> selectArticleDetailDefault(BoardVO boardVO) {
		return (List<BoardVO>) list("BBSArticle.selectArticleDetailDefault", boardVO);
	}
	
	public int selectArticleDetailDefaultCnt(BoardVO boardVO) {
		return (Integer)selectOne("BBSArticle.selectArticleDetailDefaultCnt", boardVO);
	}
	
	public List<BoardVO> selectArticleDetailCn(BoardVO boardVO) {
		return (List<BoardVO>) list("BBSArticle.selectArticleDetailCn", boardVO);
	}
	
	public int selectLoginUser(BoardVO boardVO) {
		return (Integer)selectOne("BBSArticle.selectLoginUser", boardVO);
	}
	

}
