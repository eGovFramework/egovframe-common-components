package egovframework.com.cop.cmt.service.impl;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ EgovArticleCommentServiceImplTest_insertArticleComment.class,
		EgovArticleCommentServiceImplTest_selectArticleCommentDetail.class,
		EgovArticleCommentServiceImplTest_selectArticleCommentList.class,
		EgovArticleCommentServiceImplTest_canUseComment.class,
		EgovArticleCommentServiceImplTest_updateArticleComment.class })
public class EgovArticleCommentServiceImplTest_AllTests {

}
