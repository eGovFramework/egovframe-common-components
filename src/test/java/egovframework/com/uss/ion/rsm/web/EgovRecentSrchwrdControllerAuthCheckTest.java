package egovframework.com.uss.ion.rsm.web;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.egovframe.rte.psl.dataaccess.util.EgovMap;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.ui.ModelMap;

import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.service.EgovUserDetailsService;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.com.uss.ion.rsm.service.EgovRecentSrchwrdService;
import egovframework.com.uss.ion.rsm.service.RecentSrchwrd;

/**
 * 최근검색어 상세조회의 로그인 사용자 확인 회귀 테스트.
 *
 * <pre>
 * detailRecentSrchwrd.do 는 cmd=del 이면 삭제까지 수행한다.
 * 이 경로를 지키는 것은 메서드 첫 줄의 egovAssertLoginUser() 하나뿐이며,
 * Globals.Auth 를 session 으로 두고 기동하면 이 메서드 외에 요청을 막는 처리가 없다.
 * 미사용 코드 정리 과정에서 그 호출이 다시 빠지면 미인증 요청이 삭제에 도달한다.
 * </pre>
 */
class EgovRecentSrchwrdControllerAuthCheckTest {

	/** deleteRecentSrchwrd 도달 여부만 기록하는 스텁 */
	private static final class StubService implements EgovRecentSrchwrdService {
		private boolean deleteCalled;
		private boolean detailCalled;

		@Override
		public void deleteRecentSrchwrd(RecentSrchwrd recentSrchwrd) {
			deleteCalled = true;
		}

		@Override
		public RecentSrchwrd selectRecentSrchwrdDetail(RecentSrchwrd recentSrchwrd) {
			detailCalled = true;
			return recentSrchwrd;
		}

		@Override
		public List<EgovMap> selectRecentSrchwrdList(RecentSrchwrd searchVO) {
			return List.of();
		}

		@Override
		public int selectRecentSrchwrdListCnt(RecentSrchwrd searchVO) {
			return 0;
		}

		@Override
		public void insertRecentSrchwrd(RecentSrchwrd recentSrchwrd) {
			// 이 테스트에서 사용하지 않는다.
		}

		@Override
		public void updateRecentSrchwrd(RecentSrchwrd recentSrchwrd) {
			// 이 테스트에서 사용하지 않는다.
		}

		@Override
		public List<EgovMap> selectRecentSrchwrdResultInquire(RecentSrchwrd recentSrchwrd) {
			return List.of();
		}

		@Override
		public List<EgovMap> selectRecentSrchwrdResultList(RecentSrchwrd searchVO) {
			return List.of();
		}

		@Override
		public int selectRecentSrchwrdResultListCnt(RecentSrchwrd searchVO) {
			return 0;
		}

		@Override
		public void insertRecentSrchwrdResult(RecentSrchwrd recentSrchwrd) {
			// 이 테스트에서 사용하지 않는다.
		}

		@Override
		public void deleteRecentSrchwrdResult(RecentSrchwrd recentSrchwrd) {
			// 이 테스트에서 사용하지 않는다.
		}

		@Override
		public void deleteRecentSrchwrdResultAll(RecentSrchwrd recentSrchwrd) {
			// 이 테스트에서 사용하지 않는다.
		}
	}

	private static void bindAnonymous() {
		bind(null, List.of(), Boolean.FALSE);
	}

	private static void bindLoginUser(String uniqId) {
		LoginVO login = new LoginVO();
		login.setUniqId(uniqId);
		login.setUserSe("USR");
		bind(login, List.of("ROLE_USER"), Boolean.TRUE);
	}

	private static void bind(LoginVO login, List<String> authorities, Boolean authenticated) {
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
				return authenticated;
			}
		};
		new EgovUserDetailsHelper().setEgovUserDetailsService(stub);
	}

	private static EgovRecentSrchwrdController controllerWith(StubService service) throws Exception {
		EgovRecentSrchwrdController controller = new EgovRecentSrchwrdController();
		Field field = EgovRecentSrchwrdController.class.getDeclaredField("egovRecentSrchwrdService");
		field.setAccessible(true);
		field.set(controller, service);
		return controller;
	}

	private static Map<String, String> commandMap(String cmd) {
		Map<String, String> commandMap = new HashMap<>();
		commandMap.put("cmd", cmd);
		return commandMap;
	}

	@Test
	@DisplayName("미인증 요청은 cmd=del 이어도 삭제에 도달하지 못한다")
	void anonymousDeleteDoesNotReachTheService() throws Exception {
		bindAnonymous();
		StubService service = new StubService();
		EgovRecentSrchwrdController controller = controllerWith(service);

		assertThrows(IllegalStateException.class, () -> controller.egovRecentSrchwrdDetail(new RecentSrchwrd(),
				commandMap("del"), new ModelMap()));

		assertFalse(service.deleteCalled, "미인증 요청이 deleteRecentSrchwrd 까지 도달했다");
	}

	@Test
	@DisplayName("미인증 요청은 조회 경로에도 도달하지 못한다")
	void anonymousDetailDoesNotReachTheService() throws Exception {
		bindAnonymous();
		StubService service = new StubService();
		EgovRecentSrchwrdController controller = controllerWith(service);

		assertThrows(IllegalStateException.class, () -> controller.egovRecentSrchwrdDetail(new RecentSrchwrd(),
				commandMap(""), new ModelMap()));

		assertFalse(service.detailCalled, "미인증 요청이 selectRecentSrchwrdDetail 까지 도달했다");
	}

	@Test
	@DisplayName("인증된 요청은 cmd=del 로 삭제까지 진행한다")
	void authenticatedDeleteReachesTheService() throws Exception {
		bindLoginUser("USRCNFRM_00000000001");
		StubService service = new StubService();
		EgovRecentSrchwrdController controller = controllerWith(service);

		String view = controller.egovRecentSrchwrdDetail(new RecentSrchwrd(), commandMap("del"), new ModelMap());

		assertTrue(service.deleteCalled, "인증된 요청인데 deleteRecentSrchwrd 가 호출되지 않았다");
		assertEquals("redirect:/uss/ion/rsm/listRecentSrchwrd.do", view);
	}

	@Test
	@DisplayName("인증된 요청은 cmd 가 없으면 상세조회로 간다")
	void authenticatedDetailReachesTheService() throws Exception {
		bindLoginUser("USRCNFRM_00000000001");
		StubService service = new StubService();
		EgovRecentSrchwrdController controller = controllerWith(service);

		String view = controller.egovRecentSrchwrdDetail(new RecentSrchwrd(), commandMap(""), new ModelMap());

		assertTrue(service.detailCalled, "인증된 요청인데 selectRecentSrchwrdDetail 이 호출되지 않았다");
		assertEquals("egovframework/com/uss/ion/rsm/EgovRecentSrchwrdDetail", view);
	}
}
