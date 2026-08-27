package egovframework.com.uss.ion.wik.bmk.web;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.ui.ModelMap;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import egovframework.com.cmm.EgovMessageSource;
import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.service.EgovUserDetailsService;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.com.uss.ion.wik.bmk.service.EgovWikiBookmarkService;
import egovframework.com.uss.ion.wik.bmk.service.WikiBookmark;

/**
 * 위키북마크 일괄삭제(cmd=del)의 소유권 검증 회귀 테스트.
 *
 * 로그인 사용자가 checkList에 자신이 등록하지 않은 wikiBkmkId를 실어 다른 회원의
 * 북마크를 삭제하려 하면 egovAssertAdminOrOwner가 IllegalStateException을 던져
 * 차단해야 한다(IDOR 방지). 수정 전 코드는 인증 여부만 확인하고 checkList의 각
 * ID에 대해 소유자 대조 없이 그대로 삭제했다.
 */
class EgovWikiBookmarkControllerOwnershipTest {

	private static final String OWNER = "USRCNFRM_00000000001";
	private static final String ATTACKER = "USRCNFRM_00000000009";
	private static final String OWNED_ID = "8000001";
	private static final String OTHERS_ID = "8000002";

	/** selectWikiBookmarkListCnt는 wikiBkmkId+frstRegisterId 조합이 실제 소유 관계일 때만 1을 돌려주는 스텁. */
	private static final class StubService implements EgovWikiBookmarkService {
		private final Map<String, String> ownerByBookmarkId = new HashMap<>();
		private boolean deleteCalled = false;

		StubService(String ownedId, String ownerUniqId) {
			ownerByBookmarkId.put(ownedId, ownerUniqId);
		}

		@Override
		public int selectWikiBookmarkListCnt(WikiBookmark wikiBookmark) {
			String owner = ownerByBookmarkId.get(wikiBookmark.getWikiBkmkId());
			return (owner != null && owner.equals(wikiBookmark.getFrstRegisterId())) ? 1 : 0;
		}

		@Override
		public void deleteWikiBookmark(WikiBookmark wikiBookmark) {
			deleteCalled = true;
		}

		@Override
		public List<?> selectWikiBookmarkList(WikiBookmark wikiBookmark) {
			throw new UnsupportedOperationException();
		}

		@Override
		public int selectWikiBookmarkDuplicationCnt(WikiBookmark wikiBookmark) {
			throw new UnsupportedOperationException();
		}

		@Override
		public void insertWikiBookmark(WikiBookmark wikiBookmark) {
			throw new UnsupportedOperationException();
		}
	}

	@AfterEach
	void clearRequestContext() {
		RequestContextHolder.resetRequestAttributes();
	}

	private static void bindLoginUser(String uniqId) {
		LoginVO login = new LoginVO();
		login.setUniqId(uniqId);
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

	/**
	 * MockHttpServletRequest는 이 로컬 환경의 test classpath에 Jakarta Servlet 6.0 API가 없어
	 * NoClassDefFoundError(ServletConnection)를 던진다(기존 EgovAccessTest도 동일하게 깨져 있음 —
	 * pom.xml의 주석 처리된 jakarta.servlet-api 6.0.0 test 의존성이 그 증거). 컨트롤러가 실제로
	 * 쓰는 메서드(getMethod)만 최소 구현한 프록시로 우회한다.
	 */
	private static void bindPostRequest() {
		jakarta.servlet.http.HttpServletRequest request = (jakarta.servlet.http.HttpServletRequest) java.lang.reflect.Proxy.newProxyInstance(
				EgovWikiBookmarkControllerOwnershipTest.class.getClassLoader(),
				new Class<?>[] { jakarta.servlet.http.HttpServletRequest.class },
				(proxy, method, args) -> {
					if ("getMethod".equals(method.getName())) {
						return "POST";
					}
					Class<?> returnType = method.getReturnType();
					if (returnType == boolean.class) {
						return false;
					}
					return null;
				});
		RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
	}

	private static EgovWikiBookmarkController controllerWith(StubService service) {
		EgovWikiBookmarkController controller = new EgovWikiBookmarkController();
		try {
			java.lang.reflect.Field serviceField = EgovWikiBookmarkController.class.getDeclaredField("egovWikiBookmarkService");
			serviceField.setAccessible(true);
			serviceField.set(controller, service);
		} catch (ReflectiveOperationException e) {
			throw new IllegalStateException(e);
		}
		controller.egovMessageSource = new EgovMessageSource() {
			@Override
			public String getMessage(String code) {
				return code;
			}
		};
		return controller;
	}

	private static String callDelete(StubService service, String loginUniqId, String targetId) throws Exception {
		EgovWikiBookmarkController controller = controllerWith(service);
		bindLoginUser(loginUniqId);
		bindPostRequest();

		WikiBookmark searchVO = new WikiBookmark();
		WikiBookmark wikiBookmark = new WikiBookmark();
		Map<String, String> commandMap = new HashMap<>();
		commandMap.put("cmd", "del");
		ModelMap model = new ModelMap();

		return controller.EgovWikiBookmarkList(searchVO, wikiBookmark, commandMap, List.of(targetId), model);
	}

	@Test
	void deleteByNonOwnerIsRejected() {
		StubService service = new StubService(OWNED_ID, OWNER);
		assertThrows(IllegalStateException.class, () -> callDelete(service, ATTACKER, OWNED_ID),
				"A logged-in non-owner must not be able to delete another member's wiki bookmark via checkList.");
		assertFalse(service.deleteCalled, "deleteWikiBookmark must not be reached by a non-owner.");
	}

	@Test
	void deleteByOwnerSucceeds() throws Exception {
		StubService service = new StubService(OWNED_ID, OWNER);
		String view = callDelete(service, OWNER, OWNED_ID);
		assertTrue(service.deleteCalled, "The owner must be able to delete their own wiki bookmark.");
		assertEquals("redirect:/uss/ion/wik/bmk/listWikiBookmark.do", view);
	}

	@Test
	void deleteByAdminSucceedsEvenWhenNotOwner() throws Exception {
		StubService service = new StubService(OTHERS_ID, OWNER);
		bindLoginUser(ATTACKER);
		EgovUserDetailsService adminStub = new EgovUserDetailsService() {
			@Override
			public Object getAuthenticatedUser() {
				LoginVO login = new LoginVO();
				login.setUniqId(ATTACKER);
				return login;
			}

			@Override
			public List<String> getAuthorities() {
				return List.of("ROLE_ADMIN");
			}

			@Override
			public Boolean isAuthenticated() {
				return Boolean.TRUE;
			}
		};
		new EgovUserDetailsHelper().setEgovUserDetailsService(adminStub);
		bindPostRequest();

		EgovWikiBookmarkController controller = controllerWith(service);
		WikiBookmark searchVO = new WikiBookmark();
		WikiBookmark wikiBookmark = new WikiBookmark();
		Map<String, String> commandMap = new HashMap<>();
		commandMap.put("cmd", "del");
		ModelMap model = new ModelMap();

		controller.EgovWikiBookmarkList(searchVO, wikiBookmark, commandMap, List.of(OTHERS_ID), model);
		assertTrue(service.deleteCalled, "An admin must be able to delete any member's wiki bookmark.");
	}
}
