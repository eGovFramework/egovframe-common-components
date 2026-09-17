package egovframework.com.uss.umt.web;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.lang.reflect.Proxy;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;

import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.service.EgovUserDetailsService;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.com.uss.umt.service.EgovEmplyrManageService;
import egovframework.com.uss.umt.service.EmplyrManageVO;

/**
 * updateUser·deleteUser 가 형제 컨트롤러(EgovMberManageController·EgovEntrprsManageController)와
 * 동일하게 "관리자는 다른 사용자를 수정·삭제할 수 있어야 한다"를 지킨다는 것을 확인한다.
 *
 * <p>고친 전 코드는 로그인한 관리자에게도 "수정·삭제 대상이 로그인한 자기 자신"까지 요구해,
 * 관리자가 다른 직원을 관리할 수 없었다(둘 다 {@code @RequireAdmin} 으로 이미 관리자만
 * 도달 가능한데, 그 위에 자기 자신 검사까지 겹쳐 걸었다). {@code EgovUmtAuthorizationHelper.canModifyUser}
 * 로 형제와 같은 처리를 쓰도록 고쳤다 — 관리자면 대상과 무관하게 통과한다.</p>
 */
class EgovEmplyrManageControllerAdminCanModifyOthersTest {

	private static final String ACCESS_DENIED = "egovframework/com/cmm/error/accessDenied";

	@BeforeEach
	void setUp() {
		bindLoginUser("admin1");
	}

	@AfterEach
	void tearDown() {
		ReflectionTestUtils.setField(EgovUserDetailsHelper.class, "egovUserDetailsService", null);
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
				return List.of("ROLE_ADMIN");
			}

			@Override
			public Boolean isAuthenticated() {
				return Boolean.TRUE;
			}
		};
		ReflectionTestUtils.setField(EgovUserDetailsHelper.class, "egovUserDetailsService", stub);
	}

	/** selectEmplyr 만 필요한 값을 돌려주고, 나머지 호출은 그냥 통과시키는 최소 프록시. */
	private static EgovEmplyrManageService serviceStub(EmplyrManageVO currentEmplyr) {
		return (EgovEmplyrManageService) Proxy.newProxyInstance(
				EgovEmplyrManageControllerAdminCanModifyOthersTest.class.getClassLoader(),
				new Class<?>[] { EgovEmplyrManageService.class },
				(proxy, method, args) -> {
					if ("selectEmplyr".equals(method.getName())) {
						return currentEmplyr;
					}
					return null;
				});
	}

	private EgovEmplyrManageController newControllerWithStub(EmplyrManageVO currentEmplyr) {
		EgovEmplyrManageController controller = new EgovEmplyrManageController();
		ReflectionTestUtils.setField(controller, "emplyrManageService", serviceStub(currentEmplyr));
		return controller;
	}

	@Test
	void updateUser_adminCanModifyAnotherEmployee() throws Exception {
		EmplyrManageVO current = new EmplyrManageVO();
		current.setUniqId("otherUser");

		EgovEmplyrManageController controller = newControllerWithStub(current);
		EmplyrManageVO submitted = new EmplyrManageVO();
		submitted.setUniqId("otherUser");
		BindingResult bindingResult = new BeanPropertyBindingResult(submitted, "emplyrManageVO");
		Model model = new ExtendedModelMap();

		String view = controller.updateUser(submitted, bindingResult, model);

		assertNotEquals(ACCESS_DENIED, view, "관리자(admin1)가 다른 직원(otherUser)을 수정하는 요청이 차단되면 안 된다.");
		assertEquals("forward:/uss/umt/EgovEmplyrManage.do", view);
	}

	@Test
	void deleteUser_adminCanDeleteAnotherEmployee() throws Exception {
		EgovEmplyrManageController controller = newControllerWithStub(null);
		Model model = new ExtendedModelMap();

		String view = controller.deleteUser("0:otherUser", null, model);

		assertNotEquals(ACCESS_DENIED, view, "관리자(admin1)가 다른 직원(otherUser)을 삭제하는 요청이 차단되면 안 된다.");
		assertEquals("forward:/uss/umt/EgovEmplyrManage.do", view);
	}
}
