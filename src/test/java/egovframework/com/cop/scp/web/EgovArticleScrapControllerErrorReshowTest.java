package egovframework.com.cop.scp.web;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;

import org.springframework.web.multipart.MultipartFile;

import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.service.EgovUserDetailsService;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.com.cop.bbs.service.Board;
import egovframework.com.cop.bbs.service.BoardVO;
import egovframework.com.cop.bbs.service.EgovArticleService;
import egovframework.com.cop.scp.service.EgovArticleScrapService;
import egovframework.com.cop.scp.service.Scrap;
import egovframework.com.cop.scp.service.ScrapVO;

/**
 * 스크랩 수정 검증실패 재표시 회귀 테스트.
 *
 * 형제 POST 핸들러 updateArticleScrapView는 EgovArticleScrapUpdt 뷰를 그리기 전에
 * model에 articleScrapVO·articleVO를 담는데, 검증 실패 시 같은 뷰로 되돌아가는
 * updateArticleScrap의 hasErrors() 분기는 이 둘을 담지 않는다. JSP의
 * &lt;form:form modelAttribute="articleScrapVO"&gt;가 그 이름의 model 속성을 찾지 못해
 * 재표시가 깨진다.
 */
class EgovArticleScrapControllerErrorReshowTest {

	private static final String OWNER = "USRCNFRM_00000000001";
	private static final String OUTSIDER = "USRCNFRM_00000000009";

	private static final class StubService implements EgovArticleScrapService {
		private final ScrapVO stored;

		StubService(ScrapVO stored) {
			this.stored = stored;
		}

		@Override
		public Map<String, Object> selectArticleScrapList(ScrapVO scrapVO) {
			throw new UnsupportedOperationException();
		}

		@Override
		public void insertArticleScrap(Scrap scrap) {
			throw new UnsupportedOperationException();
		}

		@Override
		public ScrapVO selectArticleScrapDetail(ScrapVO scrapVO) {
			return stored;
		}

		@Override
		public void deleteArticleScrap(ScrapVO scrapVO) {
			throw new UnsupportedOperationException();
		}

		@Override
		public void updateArticleScrap(Scrap scrap) {
			throw new UnsupportedOperationException("검증 실패 시 호출되면 안 된다.");
		}
	}

	private static final class StubArticleService implements EgovArticleService {
		private final BoardVO article;

		StubArticleService(BoardVO article) {
			this.article = article;
		}

		@Override
		public Map<String, Object> selectArticleList(BoardVO boardVO) {
			throw new UnsupportedOperationException();
		}

		@Override
		public BoardVO selectArticleDetail(BoardVO boardVO) {
			return article;
		}

		@Override
		public void insertArticleAndFiles(Board board, List<MultipartFile> files) {
			throw new UnsupportedOperationException();
		}

		@Override
		public void updateArticle(Board board) {
			throw new UnsupportedOperationException();
		}

		@Override
		public void updateArticleAndFiles(Board board, List<MultipartFile> files, String atchFileId) {
			throw new UnsupportedOperationException();
		}

		@Override
		public void deleteArticle(Board board) {
			throw new UnsupportedOperationException();
		}

		@Override
		public List<BoardVO> selectNoticeArticleList(BoardVO boardVO) {
			throw new UnsupportedOperationException();
		}

		@Override
		public Map<String, Object> selectGuestArticleList(BoardVO vo) {
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
		public Map<String, Object> selectBlogListManager(BoardVO boardVO) {
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

	private static void setPrivateField(Object target, String fieldName, Object value) {
		try {
			java.lang.reflect.Field field = target.getClass().getDeclaredField(fieldName);
			field.setAccessible(true);
			field.set(target, value);
		} catch (ReflectiveOperationException e) {
			throw new IllegalStateException(e);
		}
	}

	private static void bindLoginUser(String uniqId) {
		bindLoginUser(uniqId, List.of());
	}

	private static void bindLoginUser(String uniqId, List<String> authorities) {
		LoginVO login = new LoginVO();
		login.setUniqId(uniqId);
		EgovUserDetailsService stub = new EgovUserDetailsService() {
			@Override
			public Object getAuthenticatedUser() {
				return login;
			}

			@Override
			public List<String> getAuthorities() {
				return authorities;
			}

			@Override
			public Boolean isAuthenticated() {
				return Boolean.TRUE;
			}
		};
		new EgovUserDetailsHelper().setEgovUserDetailsService(stub);
	}

	@Test
	void updateWithValidationErrorsRestoresFormBackingAttributes() throws Exception {
		ScrapVO stored = new ScrapVO();
		stored.setScrapId("1");
		stored.setFrstRegisterId(OWNER);
		stored.setNttId(10L);
		stored.setBbsId("BBSMSTR_000001");
		BoardVO article = new BoardVO();
		article.setNttId(10L);

		EgovArticleScrapController controller = new EgovArticleScrapController();
		setPrivateField(controller, "egovArticleScrapService", new StubService(stored));
		setPrivateField(controller, "egovArticleService", new StubArticleService(article));
		bindLoginUser(OWNER);

		ScrapVO searchVO = new ScrapVO();
		searchVO.setScrapId("1");
		Scrap scrap = new Scrap();
		BindingResult bindingResult = new BeanPropertyBindingResult(scrap, "Scrap");
		bindingResult.reject("scrapNm", "필수 입력값입니다.");
		ModelMap model = new ModelMap();

		controller.updateArticleScrap(searchVO, scrap, bindingResult, model);

		assertTrue(model.containsAttribute("articleScrapVO"),
				"검증실패 재표시 화면의 form:form이 참조하는 articleScrapVO가 model에 있어야 한다.");
		assertTrue(model.containsAttribute("articleVO"),
				"형제 POST 핸들러가 담는 articleVO도 재표시 시 동일하게 담겨야 한다.");
	}

	/**
	 * hasErrors() 분기는 형제 isAuthenticated 분기와 마찬가지로 소유권 검증을 거쳐야 한다.
	 * 그렇지 않으면 타인의 scrapId로 검증 실패를 유도해 그 스크랩·게시물 내용을
	 * 재표시 화면에 그대로 노출시킬 수 있다.
	 */
	@Test
	void updateWithValidationErrorsByOutsiderIsRejected() {
		ScrapVO stored = new ScrapVO();
		stored.setScrapId("1");
		stored.setFrstRegisterId(OWNER);
		stored.setNttId(10L);
		stored.setBbsId("BBSMSTR_000001");
		BoardVO article = new BoardVO();
		article.setNttId(10L);

		EgovArticleScrapController controller = new EgovArticleScrapController();
		setPrivateField(controller, "egovArticleScrapService", new StubService(stored));
		setPrivateField(controller, "egovArticleService", new StubArticleService(article));
		bindLoginUser(OUTSIDER);

		ScrapVO searchVO = new ScrapVO();
		searchVO.setScrapId("1");
		Scrap scrap = new Scrap();
		BindingResult bindingResult = new BeanPropertyBindingResult(scrap, "Scrap");
		bindingResult.reject("scrapNm", "필수 입력값입니다.");
		ModelMap model = new ModelMap();

		assertThrows(IllegalStateException.class,
				() -> controller.updateArticleScrap(searchVO, scrap, bindingResult, model),
				"본인 소유가 아닌 스크랩은 검증실패 재표시로도 내용을 볼 수 없어야 한다.");
		assertFalse(model.containsAttribute("articleScrapVO"),
				"거부되면 타인의 스크랩 내용이 model에 담기면 안 된다.");
	}

	/**
	 * 형제 updateArticleScrapView와 마찬가지로, 존재하지 않는 scrapId는 관리자여도
	 * NPE가 아니라 명시적으로 거부돼야 한다. egovAssertAdminOrOwner(null)은
	 * ROLE_ADMIN이면 통과시키므로, 그 뒤 vo를 그대로 역참조하면 관리자만 겪는
	 * NPE가 된다.
	 */
	@Test
	void updateWithValidationErrorsForMissingScrapByAdminIsRejectedWithoutNpe() {
		EgovArticleScrapController controller = new EgovArticleScrapController();
		setPrivateField(controller, "egovArticleScrapService", new StubService(null));
		setPrivateField(controller, "egovArticleService", new StubArticleService(new BoardVO()));
		bindLoginUser(OUTSIDER, List.of("ROLE_ADMIN"));

		ScrapVO searchVO = new ScrapVO();
		searchVO.setScrapId("999");
		Scrap scrap = new Scrap();
		BindingResult bindingResult = new BeanPropertyBindingResult(scrap, "Scrap");
		bindingResult.reject("scrapNm", "필수 입력값입니다.");
		ModelMap model = new ModelMap();

		assertThrows(IllegalStateException.class,
				() -> controller.updateArticleScrap(searchVO, scrap, bindingResult, model),
				"존재하지 않는 스크랩은 NPE가 아니라 명시적 예외로 거부돼야 한다.");
	}
}
