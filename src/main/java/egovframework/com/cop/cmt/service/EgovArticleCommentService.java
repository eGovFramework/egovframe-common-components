package egovframework.com.cop.cmt.service;

import java.util.Map;

import egovframework.rte.fdl.cmmn.exception.FdlException;

public interface EgovArticleCommentService {

    public boolean canUseComment(String bbsId) throws Exception;

    Map<String, Object> selectArticleCommentList(CommentVO commentVO);

	void insertArticleComment(Comment comment) throws FdlException;

	void deleteArticleComment(CommentVO commentVO);

	CommentVO selectArticleCommentDetail(CommentVO commentVO);

	void updateArticleComment(Comment comment);

}
