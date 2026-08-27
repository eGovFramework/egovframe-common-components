package egovframework.com.uss.olp.qim.web;

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

import egovframework.com.cmm.ComDefaultVO;
import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.service.EgovUserDetailsService;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.com.uss.olp.qim.service.EgovQustnrItemManageService;
import egovframework.com.uss.olp.qim.service.QustnrItemManageVO;

/**
 * 설문항목관리 목록팝업 일괄삭제(cmd=del)의 관리자 검증 회귀 테스트.
 *
 * 형제 경로 egovQustnrItemManageDetail의 cmd=del은 이미 ROLE_ADMIN 검증을 거치는데,
 * 동일한 deleteQustnrItemManage를 호출하는 egovQustnrItemManageListPopup의 cmd=del은
 * 그 검증이 빠져 있었다. 로그인만 한 일반 사용자가 이 팝업 경로로 설문항목을 삭제할 수
 * 있었다(형제 경로 비교로 드러나는 자기모순).
 */
class EgovQustnrItemManageControllerAdminCheckTest {

	private static final class StubService implements EgovQustnrItemManageService {
		private boolean deleteCalled = false;

		@Override
		public void deleteQustnrItemManage(QustnrItemManageVO qustnrItemManageVO) {
			deleteCalled = true;
		}

		@Override
		public List<org.egovframe.rte.psl.dataaccess.util.EgovMap> selectQustnrTmplatManageList(QustnrItemManageVO qustnrItemManageVO) {
			throw new UnsupportedOperationException();
		}

		@Override
		public List<org.egovframe.rte.psl.dataaccess.util.EgovMap> selectQustnrItemManageList(ComDefaultVO searchVO) {
			return List.of();
		}

		@Override
		public List<org.egovframe.rte.psl.dataaccess.util.EgovMap> selectQustnrItemManageDetail(QustnrItemManageVO qustnrItemManageVO) {
			throw new UnsupportedOperationException();
		}

		@Override
		public int selectQustnrItemManageListCnt(ComDefaultVO searchVO) {
			return 0;
		}

		@Override
		public void insertQustnrItemManage(QustnrItemManageVO qustnrItemManageVO) {
			throw new UnsupportedOperationException();
		}

		@Override
		public void updateQustnrItemManage(QustnrItemManageVO qustnrItemManageVO) {
			throw new UnsupportedOperationException();
		}
	}

	@AfterEach
	void clearRequestContext() {
		RequestContextHolder.resetRequestAttributes();
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

	/**
	 * MockHttpServletRequest는 이 로컬 환경에서 NoClassDefFoundError로 깨진다
	 * ([[shell-and-egov-test-env-traps]]). 컨트롤러가 실제로 쓰는 getMethod()만
	 * 최소 구현한 프록시로 대체한다.
	 */
	private static void bindPostRequest() {
		jakarta.servlet.http.HttpServletRequest request = (jakarta.servlet.http.HttpServletRequest) java.lang.reflect.Proxy.newProxyInstance(
				EgovQustnrItemManageControllerAdminCheckTest.class.getClassLoader(),
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

	/** getInt(...)에 10을 돌려주는 것 외엔 페이징 계산에 관여하지 않는 최소 프록시. */
	private static org.egovframe.rte.fdl.property.EgovPropertyService noopPropertiesService() {
		return (org.egovframe.rte.fdl.property.EgovPropertyService) java.lang.reflect.Proxy.newProxyInstance(
				EgovQustnrItemManageControllerAdminCheckTest.class.getClassLoader(),
				new Class<?>[] { org.egovframe.rte.fdl.property.EgovPropertyService.class },
				(proxy, method, args) -> {
					Class<?> returnType = method.getReturnType();
					if (returnType == int.class) {
						return 10;
					}
					if (returnType == boolean.class) {
						return false;
					}
					if (returnType == long.class) {
						return 0L;
					}
					if (returnType == double.class) {
						return 0.0d;
					}
					if (returnType == float.class) {
						return 0.0f;
					}
					return null;
				});
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

	private static EgovQustnrItemManageController controllerWith(StubService service) {
		EgovQustnrItemManageController controller = new EgovQustnrItemManageController();
		setPrivateField(controller, "egovQustnrItemManageService", service);
		setPrivateField(controller, "propertiesService", noopPropertiesService());
		return controller;
	}

	private static String callListPopupDelete(StubService service) throws Exception {
		EgovQustnrItemManageController controller = controllerWith(service);
		bindPostRequest();

		ComDefaultVO searchVO = new ComDefaultVO();
		Map<String, String> commandMap = new HashMap<>();
		commandMap.put("cmd", "del");
		QustnrItemManageVO qustnrItemManageVO = new QustnrItemManageVO();
		ModelMap model = new ModelMap();

		return controller.egovQustnrItemManageListPopup(searchVO, commandMap, qustnrItemManageVO, model);
	}

	@Test
	void deleteByNonAdminIsRejected() {
		StubService service = new StubService();
		bindLoginUser("USRCNFRM_00000000009", List.of());

		assertThrows(IllegalStateException.class, () -> callListPopupDelete(service),
				"A logged-in non-admin must not be able to delete a survey item via the list popup.");
		assertTrue(!service.deleteCalled, "deleteQustnrItemManage must not be reached by a non-admin.");
	}

	@Test
	void deleteByAdminSucceeds() throws Exception {
		StubService service = new StubService();
		bindLoginUser("USRCNFRM_00000000001", List.of("ROLE_ADMIN"));

		callListPopupDelete(service);
		assertTrue(service.deleteCalled, "An admin must be able to delete a survey item via the list popup.");
	}
}
