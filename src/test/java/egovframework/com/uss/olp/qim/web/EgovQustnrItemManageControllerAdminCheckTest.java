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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributesModelMap;

import egovframework.com.cmm.ComDefaultVO;
import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.service.EgovUserDetailsService;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.com.uss.olp.qim.service.EgovQustnrItemManageService;
import egovframework.com.uss.olp.qim.service.QustnrItemManageVO;

/**
 * 설문항목관리 쓰기 경로(삭제·수정·등록)의 관리자 검증 회귀 테스트.
 *
 * 형제 경로 egovQustnrItemManageDetail/egovQustnrItemManageListPopup의 cmd=del은
 * ROLE_ADMIN 검증을 거치는데, 같은 설문항목을 대상으로 하는 qustnrItemManageModify·
 * qustnrItemManageRegist는 로그인 여부만 확인하고 ROLE_ADMIN은 확인하지 않았다.
 * 로그인만 한 일반 사용자가 이 두 경로로 설문항목을 수정·등록할 수 있었다
 * (형제 경로 비교로 드러나는 자기모순).
 *
 * 항목의 소유자는 항목을 등록한 사람이 아니라 상위 설문을 등록한 사람이다.
 * 그래서 남의 설문에 항목을 붙이는 등록도 막는다.
 */
class EgovQustnrItemManageControllerAdminCheckTest {

	private static final String SURVEY_ID = "QMANAGE_000000000001";
	private static final String SURVEY_OWNER = "USRCNFRM_00000000001";

	/** 설문 상세는 SURVEY_OWNER 가 등록한 설문 하나, 문항 상세는 그 설문에 속한 문항 하나를 돌려주는 프록시 */
	private static <T> T surveyStub(Class<T> type) {
		return type.cast(java.lang.reflect.Proxy.newProxyInstance(
				EgovQustnrItemManageControllerAdminCheckTest.class.getClassLoader(),
				new Class<?>[] { type },
				(proxy, method, args) -> {
					org.egovframe.rte.psl.dataaccess.util.EgovMap row = new org.egovframe.rte.psl.dataaccess.util.EgovMap();
					if ("selectQustnrManageDetail".equals(method.getName())) {
						row.put("qestnrId", SURVEY_ID);
						row.put("frstRegisterId", SURVEY_OWNER);
						return List.of(row);
					}
					if ("selectQustnrQestnManageDetail".equals(method.getName())) {
						row.put("qestnrId", SURVEY_ID);
						row.put("qestnrTmplatId", "QTMPLA_00000000000001");
						return List.of(row);
					}
					return null;
				}));
	}

	private static final class StubService implements EgovQustnrItemManageService {
		private boolean deleteCalled = false;
		private boolean updateCalled = false;
		private boolean insertCalled = false;

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
			// 항목 등록자(USRCNFRM_00000000007)는 설문 등록자(SURVEY_OWNER)와 다르다 — 소유권은 설문 기준
			org.egovframe.rte.psl.dataaccess.util.EgovMap stored = new org.egovframe.rte.psl.dataaccess.util.EgovMap();
			stored.put("frstRegisterId", "USRCNFRM_00000000007");
			stored.put("qestnrId", SURVEY_ID);
			return List.of(stored);
		}

		@Override
		public int selectQustnrItemManageListCnt(ComDefaultVO searchVO) {
			return 0;
		}

		@Override
		public void insertQustnrItemManage(QustnrItemManageVO qustnrItemManageVO) {
			insertCalled = true;
		}

		@Override
		public void updateQustnrItemManage(QustnrItemManageVO qustnrItemManageVO) {
			updateCalled = true;
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
	 *. 컨트롤러가 실제로 쓰는 getMethod()만
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
		setPrivateField(controller, "egovQustnrManageService",
				surveyStub(egovframework.com.uss.olp.qmc.service.EgovQustnrManageService.class));
		setPrivateField(controller, "egovQustnrQestnManageService",
				surveyStub(egovframework.com.uss.olp.qqm.service.EgovQustnrQestnManageService.class));
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
	void deleteByNonOwnerIsRejected() {
		StubService service = new StubService();
		bindLoginUser("USRCNFRM_00000000009", List.of());

		assertThrows(IllegalStateException.class, () -> callListPopupDelete(service),
				"A logged-in non-owner must not be able to delete a survey item via the list popup.");
		assertTrue(!service.deleteCalled, "deleteQustnrItemManage must not be reached by a non-owner.");
	}

	@Test
	void deleteByAdminIsRejectedWhenNotOwner() {
		StubService service = new StubService();
		bindLoginUser("USRCNFRM_00000000009", List.of("ROLE_ADMIN"));

		assertThrows(IllegalStateException.class, () -> callListPopupDelete(service),
				"An admin who is not the registrant must not be able to delete a survey item via the list popup.");
		assertTrue(!service.deleteCalled, "deleteQustnrItemManage must not be reached by a non-owner admin.");
	}

	@Test
	void deleteByOwnerSucceeds() throws Exception {
		StubService service = new StubService();
		bindLoginUser("USRCNFRM_00000000001", List.of());

		callListPopupDelete(service);
		assertTrue(service.deleteCalled, "The survey registrant must be able to delete a survey item via the list popup, even one another admin added.");
	}

	/** hasErrors()가 false를 돌려주는 것 외엔 관여하지 않는 최소 프록시. */
	private static org.springframework.validation.BindingResult noopBindingResult() {
		return (org.springframework.validation.BindingResult) java.lang.reflect.Proxy.newProxyInstance(
				EgovQustnrItemManageControllerAdminCheckTest.class.getClassLoader(),
				new Class<?>[] { org.springframework.validation.BindingResult.class },
				(proxy, method, args) -> {
					if ("hasErrors".equals(method.getName())) {
						return false;
					}
					Class<?> returnType = method.getReturnType();
					if (returnType == boolean.class) {
						return false;
					}
					return null;
				});
	}

	private static String callModify(StubService service) throws Exception {
		EgovQustnrItemManageController controller = controllerWith(service);

		ComDefaultVO searchVO = new ComDefaultVO();
		QustnrItemManageVO qustnrItemManageVO = new QustnrItemManageVO();
		ModelMap model = new ModelMap();

		return controller.qustnrItemManageModify(searchVO, qustnrItemManageVO, noopBindingResult(), model);
	}

	@Test
	void updateByNonAdminIsRejected() {
		StubService service = new StubService();
		bindLoginUser("USRCNFRM_00000000009", List.of());

		assertThrows(IllegalStateException.class, () -> callModify(service),
				"A logged-in non-admin must not be able to modify a survey item.");
		assertTrue(!service.updateCalled, "updateQustnrItemManage must not be reached by a non-admin.");
	}

	@Test
	void updateByAdminSucceeds() throws Exception {
		StubService service = new StubService();
		bindLoginUser("USRCNFRM_00000000001", List.of("ROLE_ADMIN"));

		callModify(service);
		assertTrue(service.updateCalled, "An admin must be able to modify a survey item.");
	}

	private static String callRegist(StubService service) throws Exception {
		EgovQustnrItemManageController controller = controllerWith(service);

		ComDefaultVO searchVO = new ComDefaultVO();
		QustnrItemManageVO qustnrItemManageVO = new QustnrItemManageVO();
		ModelMap model = new ModelMap();
		RedirectAttributes redirectAttributes = new RedirectAttributesModelMap();

		return controller.qustnrItemManageRegist(searchVO, qustnrItemManageVO, noopBindingResult(), model,
				redirectAttributes);
	}

	@Test
	void insertByNonAdminIsRejected() {
		StubService service = new StubService();
		bindLoginUser("USRCNFRM_00000000009", List.of());

		assertThrows(IllegalStateException.class, () -> callRegist(service),
				"A logged-in non-admin must not be able to register a survey item.");
		assertTrue(!service.insertCalled, "insertQustnrItemManage must not be reached by a non-admin.");
	}

	@Test
	void insertByAdminIsRejectedWhenNotSurveyOwner() {
		StubService service = new StubService();
		bindLoginUser("USRCNFRM_00000000009", List.of("ROLE_ADMIN"));

		assertThrows(IllegalStateException.class, () -> callRegist(service),
				"An admin must not be able to add a survey item to another admin's survey.");
		assertTrue(!service.insertCalled, "insertQustnrItemManage must not be reached by a non-owner admin.");
	}

	@Test
	void insertByAdminSucceeds() throws Exception {
		StubService service = new StubService();
		bindLoginUser("USRCNFRM_00000000001", List.of("ROLE_ADMIN"));

		callRegist(service);
		assertTrue(service.insertCalled, "The survey registrant must be able to register a survey item.");
	}
}
