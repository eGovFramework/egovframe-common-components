package egovframework.com.cop.bbs.service.impl;

import static org.junit.Assert.assertEquals;

import java.util.Date;

import javax.annotation.Resource;

import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;

import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.com.cop.cmt.service.Comment;
import egovframework.com.cop.cmt.service.impl.EgovArticleCommentDAO;
import egovframework.com.test.EgovTestV1;
import egovframework.rte.fdl.cmmn.exception.FdlException;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;
import egovframework.rte.fdl.string.EgovDateUtil;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@ContextConfiguration(classes = { EgovArticleCommentDAOTest_Configuration.class })
public class EgovArticleCommentDAOTest_insertArticleComment extends EgovTestV1 {

	@Resource(name = "egovBBSMstrIdGnrService")
	private EgovIdGnrService egovBBSMstrIdGnrService;

	@Resource(name = "egovNttIdGnrService")
	private EgovIdGnrService egovNttIdGnrService;

//	@Resource(name = "egovBlogIdGnrService")
//	private EgovIdGnrService egovBlogIdGnrService;

	@Resource(name = "egovAnswerNoGnrService")
	private EgovIdGnrService egovAnswerNoGnrService;

	@Resource
	private EgovArticleCommentDAO egovArticleCommentDAO;

	// testData
	String today;
	LoginVO authenticatedUser;

	// given
	Comment comment;

	// when
	boolean result = false;

	@Test
	public void test() {
		log.debug("test");
		testData();
		given();
		when();
		then();
	}

	void testData() {
		today = " " + EgovDateUtil.toString(new Date(), null, null);
		authenticatedUser = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
	}

	void given() {
		comment = new Comment();
		try {
			comment.setNttId(egovNttIdGnrService.getNextLongId());
			comment.setBbsId(egovBBSMstrIdGnrService.getNextStringId());
			comment.setCommentNo(egovAnswerNoGnrService.getNextStringId());
		} catch (FdlException e) {
			log.error(e.getMessage());
		}
	}

	void when() {
		try {
			egovArticleCommentDAO.insertArticleComment(comment);
			result = true;
		} catch (Exception e) {
			log.error(e.getMessage());
		}
	}

	void then() {
		log.debug("result={}", result);

		assertEquals(result, true);
	}

}