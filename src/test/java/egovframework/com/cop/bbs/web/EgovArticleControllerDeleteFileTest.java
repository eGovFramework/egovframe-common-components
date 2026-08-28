package egovframework.com.cop.bbs.web;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.lang.reflect.Field;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.ui.ModelMap;

import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.service.EgovUserDetailsService;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.com.cop.bbs.service.Board;
import egovframework.com.cop.bbs.service.BoardMaster;
import egovframework.com.cop.bbs.service.BoardVO;
import egovframework.com.cop.bbs.service.EgovArticleService;

/**
 * 게시물 삭제 시 첨부파일 정리 회귀 테스트.
 *
 * deleteArticle(Board)는 board.getAtchFileId()로 첨부그룹을 미사용 처리한다. 그런데 삭제 폼
 * (EgovArticleDetail.jsp의 formDelete)은 atchFileId를 전송하지 않아 요청 바인딩 값이 비어 있고,
 * 그대로 넘기면 서비스의 파일 정리 분기가 실행되지 않는다. 같은 컨트롤러의 updateBoardArticle은
 * 이미 "클라이언트가 전달한 atchFileId를 신뢰하지 않고 조회본의 값을 쓴다"로 조치돼 있다.
 */
class EgovArticleControllerDeleteFileTest {

	private static final String OWNER_UNIQ_ID = "USRCNFRM_00000000001";
	private static final String STORED_ATCH_FILE_ID = "FILE_000000000000123";

	/** deleteArticle에 실제로 전달된 Board를 기록하는 스텁. */
	private static final class StubService implements EgovArticleService {
		private final BoardVO stored;
		private Board deletedArg;

		StubService(String atchFileId) {
			this.stored = new BoardVO();
			this.stored.setFrstRegisterId(OWNER_UNIQ_ID);
			this.stored.setNtcrId("writer");
			this.stored.setAtchFileId(atchFileId);
		}

		@Override
		public BoardVO selectArticleDetail(BoardVO boardVO) {
			return stored;
		}

		@Override
		public void deleteArticle(Board board) {
			deletedArg = board;
		}

		@Override
		public java.util.Map<String, Object> selectArticleList(BoardVO boardVO) {
			throw new UnsupportedOperationException();
		}

		@Override
		public void insertArticleAndFiles(Board board,
				List<org.springframework.web.multipart.MultipartFile> files) {
			throw new UnsupportedOperationException();
		}

		@Override
		public void updateArticle(Board board) {
			throw new UnsupportedOperationException();
		}

		@Override
		public void updateArticleAndFiles(Board board,
				List<org.springframework.web.multipart.MultipartFile> files, String atchFileId) {
			throw new UnsupportedOperationException();
		}

		@Override
		public List<BoardVO> selectNoticeArticleList(BoardVO boardVO) {
			throw new UnsupportedOperationException();
		}

		@Override
		public java.util.Map<String, Object> selectGuestArticleList(BoardVO vo) {
			throw new UnsupportedOperationException();
		}

		@Override
		public BoardVO selectArticleCnOne(BoardVO boardVO) {
			throw new UnsupportedOperationException();
		}

		@Override
		public List<BoardVO> selectBlogNmList(BoardVO boardVO) {
			throw new UnsupportedOperationException();
		}

		@Override
		public java.util.Map<String, Object> selectBlogListManager(BoardVO boardVO) {
			throw new UnsupportedOperationException();
		}

		@Override
		public List<BoardVO> selectArticleDetailDefault(BoardVO boardVO) {
			throw new UnsupportedOperationException();
		}

		@Override
		public int selectArticleDetailDefaultCnt(BoardVO boardVO) {
			throw new UnsupportedOperationException();
		}

		@Override
		public List<BoardVO> selectArticleDetailCn(BoardVO boardVO) {
			throw new UnsupportedOperationException();
		}

		@Override
		public int selectLoginUser(BoardVO boardVO) {
			throw new UnsupportedOperationException();
		}
	}

	private static void bindLoginUser(String uniqId) {
		LoginVO login = new LoginVO();
		login.setUniqId(uniqId);
		login.setId(uniqId);
		EgovUserDetailsService stub = new EgovUserDetailsService() {
			@Override
			public Object getAuthenticatedUser() {
				return login;
			}

			@Override
			public List<String> getAuthorities() {
				return List.of();
			}

			@Override
			public Boolean isAuthenticated() {
				return Boolean.TRUE;
			}
		};
		new EgovUserDetailsHelper().setEgovUserDetailsService(stub);
	}

	private static EgovArticleController controllerWith(StubService service) throws Exception {
		EgovArticleController controller = new EgovArticleController();
		Field f = EgovArticleController.class.getDeclaredField("egovArticleService");
		f.setAccessible(true);
		f.set(controller, service);
		return controller;
	}

	private static BoardVO searchVO() {
		BoardVO boardVO = new BoardVO();
		boardVO.setNttId(1L);
		boardVO.setBbsId("BBSMSTR_000000000001");
		boardVO.setBlogAt("");
		return boardVO;
	}

	@Test
	void deleteBoardArticleCleansUpTheAttachmentGroupRecordedOnTheServer() throws Exception {
		StubService service = new StubService(STORED_ATCH_FILE_ID);
		EgovArticleController controller = controllerWith(service);
		bindLoginUser(OWNER_UNIQ_ID);

		// 삭제 폼은 atchFileId를 전송하지 않으므로 요청 바인딩 값은 비어 있다.
		Board board = new Board();
		board.setNttId(1L);
		board.setBbsId("BBSMSTR_000000000001");

		controller.deleteBoardArticle(null, searchVO(), board, new BoardMaster(), new ModelMap());

		assertEquals(STORED_ATCH_FILE_ID, service.deletedArg.getAtchFileId(),
				"삭제 시 첨부그룹 정리는 폼이 보내지 않는 요청값이 아니라 서버에 저장된 atchFileId로 이뤄져야 한다.");
	}

	@Test
	void deleteBoardArticleLeavesTheAttachmentIdEmptyWhenThePostHasNoAttachment() throws Exception {
		StubService service = new StubService("");
		EgovArticleController controller = controllerWith(service);
		bindLoginUser(OWNER_UNIQ_ID);

		Board board = new Board();
		board.setNttId(1L);
		board.setBbsId("BBSMSTR_000000000001");

		controller.deleteBoardArticle(null, searchVO(), board, new BoardMaster(), new ModelMap());

		assertEquals("", service.deletedArg.getAtchFileId(),
				"첨부가 없는 게시물은 빈 값이 그대로 넘어가 서비스의 정리 분기를 건너뛰어야 한다.");
	}
}
