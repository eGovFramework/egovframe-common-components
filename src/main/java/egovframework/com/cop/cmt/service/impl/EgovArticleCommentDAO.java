package egovframework.com.cop.cmt.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.com.cop.cmt.service.Comment;
import egovframework.com.cop.cmt.service.CommentVO;
import jakarta.annotation.Resource;

/**
 * 게시판 댓글에 대한 DAO 클래스
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2026.05.28  dasomel         @EgovMapper 인터페이스 위임 방식으로 전환
 * </pre>
 */
@Repository("EgovArticleCommentDAO")
public class EgovArticleCommentDAO {

	@Resource(name = "egovArticleCommentMapper")
	private EgovArticleCommentMapper mapper;

	public List<CommentVO> selectArticleCommentList(CommentVO commentVO) {
		return mapper.selectArticleCommentList(commentVO);
	}

	public int selectArticleCommentListCnt(CommentVO commentVO) {
		return mapper.selectArticleCommentListCnt(commentVO);
	}

	public void insertArticleComment(Comment comment) {
		mapper.insertArticleComment(comment);
	}

	public void deleteArticleComment(CommentVO commentVO) {
		mapper.deleteArticleComment(commentVO);
	}

	public CommentVO selectArticleCommentDetail(CommentVO commentVO) {
		return mapper.selectArticleCommentDetail(commentVO);
	}

	public void updateArticleComment(Comment comment) {
		mapper.updateArticleComment(comment);
	}

}
