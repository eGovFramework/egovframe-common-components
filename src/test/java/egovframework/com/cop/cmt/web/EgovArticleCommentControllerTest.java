package egovframework.com.cop.cmt.web;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.egovframe.rte.fdl.cmmn.exception.FdlException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;

import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.exception.EgovXssException;
import egovframework.com.cmm.service.EgovUserDetailsService;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.com.cop.cmt.service.Comment;
import egovframework.com.cop.cmt.service.CommentVO;
import egovframework.com.cop.cmt.service.EgovArticleCommentService;

class EgovArticleCommentControllerTest {

	private static final String LOGIN_USER_ID = "LOGIN_USER";
	private static final String COMMENT_OWNER_ID = "COMMENT_OWNER";

	@AfterEach
	void tearDown() {
		new EgovUserDetailsHelper().setEgovUserDetailsService(new TestUserDetailsService(null, false));
	}

	@Test
	void updateArticleCommentRejectsNonOwner() {
		EgovArticleCommentController controller = controllerWithCommentOwner(COMMENT_OWNER_ID);
		RecordingArticleCommentService service = (RecordingArticleCommentService) controller.egovArticleCommentService;
		Comment comment = new Comment();
		comment.setCommentNo("COMMENT_1");
		comment.setCommentCn("updated");
		BindingResult bindingResult = new BeanPropertyBindingResult(comment, "comment");

		assertThrows(EgovXssException.class,
				() -> controller.updateArticleComment(null, new CommentVO(), comment, bindingResult, new ModelMap()));
		assertFalse(service.updateCalled);
	}

	@Test
	void deleteArticleCommentRejectsNonOwner() {
		EgovArticleCommentController controller = controllerWithCommentOwner(COMMENT_OWNER_ID);
		RecordingArticleCommentService service = (RecordingArticleCommentService) controller.egovArticleCommentService;
		CommentVO commentVO = new CommentVO();
		commentVO.setCommentNo("COMMENT_1");

		assertThrows(EgovXssException.class,
				() -> controller.deleteArticleComment(null, commentVO, new Comment(), new ModelMap(), new HashMap<>()));
		assertFalse(service.deleteCalled);
	}

	private EgovArticleCommentController controllerWithCommentOwner(String commentOwnerId) {
		LoginVO loginVO = new LoginVO();
		loginVO.setId("login-user");
		loginVO.setUniqId(LOGIN_USER_ID);

		new EgovUserDetailsHelper().setEgovUserDetailsService(new TestUserDetailsService(loginVO, true));

		EgovArticleCommentController controller = new EgovArticleCommentController();
		controller.egovArticleCommentService = new RecordingArticleCommentService(commentOwnerId);
		return controller;
	}

	private static final class TestUserDetailsService implements EgovUserDetailsService {

		private final LoginVO loginVO;
		private final boolean authenticated;

		private TestUserDetailsService(LoginVO loginVO, boolean authenticated) {
			this.loginVO = loginVO;
			this.authenticated = authenticated;
		}

		@Override
		public Object getAuthenticatedUser() {
			return loginVO;
		}

		@Override
		public List<String> getAuthorities() {
			return Collections.emptyList();
		}

		@Override
		public Boolean isAuthenticated() {
			return authenticated;
		}
	}

	private static final class RecordingArticleCommentService implements EgovArticleCommentService {

		private final String commentOwnerId;
		private boolean updateCalled;
		private boolean deleteCalled;

		private RecordingArticleCommentService(String commentOwnerId) {
			this.commentOwnerId = commentOwnerId;
		}

		@Override
		public boolean canUseComment(String bbsId) throws Exception {
			return true;
		}

		@Override
		public Map<String, Object> selectArticleCommentList(CommentVO commentVO) {
			return Collections.emptyMap();
		}

		@Override
		public void insertArticleComment(Comment comment) throws FdlException {
		}

		@Override
		public void deleteArticleComment(CommentVO commentVO) {
			deleteCalled = true;
		}

		@Override
		public CommentVO selectArticleCommentDetail(CommentVO commentVO) {
			CommentVO result = new CommentVO();
			result.setCommentNo(commentVO.getCommentNo());
			result.setWrterId(commentOwnerId);
			return result;
		}

		@Override
		public void updateArticleComment(Comment comment) {
			updateCalled = true;
		}
	}
}
