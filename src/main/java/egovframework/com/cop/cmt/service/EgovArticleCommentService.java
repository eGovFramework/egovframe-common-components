package egovframework.com.cop.cmt.service;

import java.util.Map;

public interface EgovArticleCommentService {

    public boolean canUseComment(String bbsId);

    Map<String, Object> selectArticleCommentList(CommentVO commentVO);

	void insertArticleComment(Comment comment);

	void deleteArticleComment(CommentVO commentVO);

	CommentVO selectArticleCommentDetail(CommentVO commentVO);

	void updateArticleComment(Comment comment);

}
