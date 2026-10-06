package egovframework.com.cop.bbs.web;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.lang.reflect.Field;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.http.HttpServletRequest;

import org.egovframe.rte.fdl.property.EgovPropertyService;
import org.junit.jupiter.api.Test;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributesModelMap;

import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.exception.EgovAccessDeniedException;
import egovframework.com.cmm.service.EgovUserDetailsService;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.com.cop.bbs.service.Board;
import egovframework.com.cop.bbs.service.BoardMaster;
import egovframework.com.cop.bbs.service.BoardVO;
import egovframework.com.cop.bbs.service.EgovArticleService;

/**
 * 거부된 요청은 DB 를 바꾸지 않는다 — 조회수(RDCNT)·수정자(LAST_UPDUSR_ID)를 올리는 조회를 소유권·비밀글 검사 전에 부르지 않는지,
 * 블로그 JSON 이 남의 비밀글 본문을 내려주지 않는지 확인한다.
 */
class EgovArticleControllerDenyNoSideEffectTest {

	private static final String OWNER = "USRCNFRM_00000000001";
	private static final String OTHER = "USRCNFRM_00000000000";

	private final List<String> countCalls = new ArrayList<>();

	@Test
	void deleteByOtherUser_deniedWithoutCount() throws Exception {
		bindLoginUser(OTHER);
		EgovArticleController controller = controller(article("N"));

		assertThrows(EgovAccessDeniedException.class,
				() -> controller.deleteBoardArticle(stub(HttpServletRequest.class), new BoardVO(), new Board(),
						new BoardMaster(), new ModelMap(), new RedirectAttributesModelMap()));
		assertTrue(countCalls.isEmpty(), "거부 전에 조회수를 올렸다: " + countCalls);
	}

	@Test
	void secretDetailByOtherUser_deniedWithoutCount() throws Exception {
		bindLoginUser(OTHER);
		EgovArticleController controller = controller(article("Y"));

		assertThrows(EgovAccessDeniedException.class, () -> controller.selectArticleDetail(new BoardVO(), new ModelMap()));
		assertTrue(countCalls.isEmpty(), "거부 전에 조회수를 올렸다: " + countCalls);
	}

	@Test
	void secretBlogCnByOtherUser_deniedWithoutCount() throws Exception {
		bindLoginUser(OTHER);
		EgovArticleController controller = controller(article("Y"));

		assertThrows(EgovAccessDeniedException.class, () -> controller.selectArticleBlogDetailCn(new BoardVO(),
				new egovframework.com.cop.cmt.service.CommentVO(), new ModelMap()));
		assertTrue(countCalls.isEmpty(), "거부 전에 조회수를 올렸다: " + countCalls);
	}

	@Test
	void blogDetail_masksSecretBodyForOthersOnly() throws Exception {
		bindLoginUser(OTHER);
		ModelAndView other = controller(article("Y")).selectArticleBlogDetail(new BoardVO(), new ModelMap());
		assertEquals("", ((BoardVO) other.getModel().get("blogCnOne")).getNttCn());
		assertEquals("", firstOf(other).getNttCn());

		bindLoginUser(OWNER);
		ModelAndView owner = controller(article("Y")).selectArticleBlogDetail(new BoardVO(), new ModelMap());
		assertEquals("비밀 본문", ((BoardVO) owner.getModel().get("blogCnOne")).getNttCn());
		assertEquals("비밀 본문", firstOf(owner).getNttCn());
	}

	@Test
	void blogDetail_sanitizesRichTextBody() throws Exception {
		bindLoginUser(OTHER);
		BoardVO stored = article("N");
		stored.setNttCn("<p>본문</p><img src=x onerror=alert(1)><script>alert(2)</script>");
		ModelAndView mav = controller(stored).selectArticleBlogDetail(new BoardVO(), new ModelMap());

		for (String cn : List.of(((BoardVO) mav.getModel().get("blogCnOne")).getNttCn(), firstOf(mav).getNttCn())) {
			assertTrue(cn.contains("<p>본문</p>"), cn);
			assertTrue(!cn.contains("onerror") && !cn.contains("<script"), cn);
		}
	}

	@SuppressWarnings("unchecked")
	private static BoardVO firstOf(ModelAndView mav) {
		return ((List<BoardVO>) mav.getModel().get("blogSubJectList")).get(0);
	}

	private static BoardVO article(String secretAt) {
		BoardVO vo = new BoardVO();
		vo.setFrstRegisterId(OWNER);
		vo.setSecretAt(secretAt);
		vo.setNtcrId("writer");
		vo.setNttCn("비밀 본문");
		return vo;
	}

	private EgovArticleController controller(BoardVO stored) throws Exception {
		EgovArticleController controller = new EgovArticleController();
		// 호출마다 새 객체 — 컨트롤러가 가린 값이 다음 호출에 남지 않게
		EgovArticleService service = (EgovArticleService) Proxy.newProxyInstance(EgovArticleService.class.getClassLoader(),
				new Class<?>[] { EgovArticleService.class }, (proxy, method, args) -> {
					switch (method.getName()) {
					case "selectArticleDetail":
					case "increaseInqireCo":
						countCalls.add(method.getName());
						return method.getName().equals("selectArticleDetail") ? copy(stored) : null;
					case "selectArticleDetailNoCount":
					case "selectArticleCnOne":
						return copy(stored);
					case "selectArticleDetailDefault":
						return new ArrayList<>(List.of(copy(stored)));
					case "selectArticleDetailDefaultCnt":
						return 1;
					default:
						return null;
					}
				});
		setField(controller, "egovArticleService", service);
		setField(controller, "propertyService", (EgovPropertyService) Proxy.newProxyInstance(
				EgovPropertyService.class.getClassLoader(), new Class<?>[] { EgovPropertyService.class },
				(proxy, method, args) -> method.getReturnType() == int.class ? 10 : null));
		return controller;
	}

	private static BoardVO copy(BoardVO src) {
		BoardVO vo = new BoardVO();
		vo.setFrstRegisterId(src.getFrstRegisterId());
		vo.setSecretAt(src.getSecretAt());
		vo.setNtcrId(src.getNtcrId());
		vo.setNttCn(src.getNttCn());
		return vo;
	}

	@SuppressWarnings("unchecked")
	private static <T> T stub(Class<T> type) {
		return (T) Proxy.newProxyInstance(type.getClassLoader(), new Class<?>[] { type }, (proxy, method, args) -> null);
	}

	private static void bindLoginUser(String uniqId) {
		LoginVO login = new LoginVO();
		login.setUniqId(uniqId);
		new EgovUserDetailsHelper().setEgovUserDetailsService(new EgovUserDetailsService() {
			@Override
			public Object getAuthenticatedUser() {
				return login;
			}

			@Override
			public List<String> getAuthorities() {
				return List.of("ROLE_USER");
			}

			@Override
			public Boolean isAuthenticated() {
				return Boolean.TRUE;
			}
		});
	}

	private static void setField(Object target, String name, Object value) throws Exception {
		Field field = EgovArticleController.class.getDeclaredField(name);
		field.setAccessible(true);
		field.set(target, value);
	}
}
