package egovframework.com.cop.cmt.service.impl;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ EgovArticleCommentDAOTest_deleteArticleComment.class,
		EgovArticleCommentDAOTest_insertArticleComment.class,
		EgovArticleCommentDAOTest_selectArticleCommentDetail.class,
		EgovArticleCommentDAOTest_selectArticleCommentList.class,
		EgovArticleCommentDAOTest_selectArticleCommentListCnt.class,
		EgovArticleCommentDAOTest_updateArticleComment.class })
public class EgovArticleCommentDAOTest_AllTests {

}
