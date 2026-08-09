package egovframework.com.uss.ion.ulm.web;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;

import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.annotation.RequireAdmin;
import egovframework.com.cmm.service.EgovUserDetailsService;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.com.uss.ion.ulm.service.EgovUnityLinkService;
import egovframework.com.uss.ion.ulm.service.UnityLink;

/**
 * 통합링크관리 등록·수정·삭제의 관리자 검증 회귀 테스트.
 *
 * 등록(registUnityLink.do)·수정(updtUnityLink.do)은 @RequireAdmin으로 보호한다.
 * @RequireAdmin은 Spring AOP로 위빙되므로 컨트롤러를 직접 new해서 부르는 단위 테스트로는
 * 실제 차단 동작을 재현할 수 없다(프록시를 거치지 않음) — 그 전제는 이미 별도 E2E로 확인됐고,
 * 여기서는 애노테이션이 실제로 붙어있는지만 리플렉션으로 고정한다.
 *
 * 삭제(cmd=del)는 상세조회와 같은 메서드(egovUnityLinkDetail) 안에 있어 @RequireAdmin을
 * 메서드 전체에 붙일 수 없다(그러면 일반 열람까지 막힘). 그래서 인라인 ROLE_ADMIN 체크를
 * 추가했고, 이건 AOP 없이 메서드 본문에서 직접 실행되므로 실제 차단 동작을 컨트롤러 직접
 * 호출로 검증할 수 있다.
 */
class EgovUnityLinkControllerAdminCheckTest {

	private static final class StubService implements EgovUnityLinkService {
		private boolean deleteCalled = false;

		@Override
		public List<?> selectUnityLinkSample(UnityLink unityLink) {
			throw new UnsupportedOperationException();
		}

		@Override
		public List<?> selectUnityLinkList(UnityLink unityLink) {
			throw new UnsupportedOperationException();
		}

		@Override
		public int selectUnityLinkListCnt(UnityLink unityLink) {
			throw new UnsupportedOperationException();
		}

		@Override
		public UnityLink selectUnityLinkDetail(UnityLink unityLink) {
			throw new UnsupportedOperationException();
		}

		@Override
		public void insertUnityLink(UnityLink unityLink) {
			throw new UnsupportedOperationException();
		}

		@Override
		public void updateUnityLink(UnityLink unityLink) {
			throw new UnsupportedOperationException();
		}

		@Override
		public void deleteUnityLink(UnityLink unityLink) {
			deleteCalled = true;
		}
	}

	private static void bindLoginUser(List<String> authorities) {
		LoginVO login = new LoginVO();
		login.setUniqId("USRCNFRM_00000000001");
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

	private static EgovUnityLinkController controllerWith(StubService service) throws Exception {
		EgovUnityLinkController controller = new EgovUnityLinkController();
		Field serviceField = EgovUnityLinkController.class.getDeclaredField("egovUnityLinkService");
		serviceField.setAccessible(true);
		serviceField.set(controller, service);
		return controller;
	}

	// ---- 등록·수정: 구조 테스트(리플렉션) ----

	@Test
	void updateRequiresAdmin() throws Exception {
		Method m = EgovUnityLinkController.class.getDeclaredMethod("egovUnityLinkModify", UnityLink.class,
				BindingResult.class, ModelMap.class);
		assertTrue(m.isAnnotationPresent(RequireAdmin.class), "통합링크 수정은 관리자만 가능해야 한다.");
	}

	@Test
	void registRequiresAdmin() throws Exception {
		Method m = EgovUnityLinkController.class.getDeclaredMethod("egovUnityLinkRegist", UnityLink.class,
				BindingResult.class, ModelMap.class);
		assertTrue(m.isAnnotationPresent(RequireAdmin.class), "통합링크 등록은 관리자만 가능해야 한다.");
	}

	// ---- 삭제: 실제 동작 테스트(인라인 체크) ----

	@Test
	void deleteByNonAdminIsRejected() throws Exception {
		StubService service = new StubService();
		EgovUnityLinkController controller = controllerWith(service);
		bindLoginUser(List.of());

		UnityLink unityLink = new UnityLink();
		Map<String, String> commandMap = new HashMap<>();
		commandMap.put("cmd", "del");
		ModelMap model = new ModelMap();

		assertThrows(IllegalStateException.class,
				() -> controller.egovUnityLinkDetail(unityLink, commandMap, model),
				"관리자가 아니면 통합링크를 삭제할 수 없어야 한다.");
		assertTrue(!service.deleteCalled, "deleteUnityLink 서비스가 호출되면 안 된다.");
	}

	@Test
	void deleteByAdminSucceeds() throws Exception {
		StubService service = new StubService();
		EgovUnityLinkController controller = controllerWith(service);
		bindLoginUser(List.of("ROLE_ADMIN"));

		UnityLink unityLink = new UnityLink();
		Map<String, String> commandMap = new HashMap<>();
		commandMap.put("cmd", "del");
		ModelMap model = new ModelMap();

		controller.egovUnityLinkDetail(unityLink, commandMap, model);
		assertTrue(service.deleteCalled, "관리자는 통합링크를 삭제할 수 있어야 한다.");
	}
}
