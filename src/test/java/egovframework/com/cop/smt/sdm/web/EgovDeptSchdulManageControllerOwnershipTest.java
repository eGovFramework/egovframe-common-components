package egovframework.com.cop.smt.sdm.web;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributesModelMap;

import egovframework.com.cmm.ComDefaultVO;
import egovframework.com.cmm.EgovMessageSource;
import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.service.EgovUserDetailsService;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.com.cop.smt.sdm.service.DeptSchdulManageVO;
import egovframework.com.cop.smt.sdm.service.EgovDeptSchdulManageService;
import org.egovframe.rte.psl.dataaccess.util.EgovMap;

/**
 * 부서일정 삭제·수정의 소유권 검증 회귀 테스트.
 *
 * EgovDeptSchdulManageMainList의 "SCHDUL_CHARGER_ID = uniqId OR FRST_REGISTER_ID = uniqId"
 * 조건이 이 도메인의 실제 소유권 정의다. 담당자·등록자 중 아무도 아닌 로그인 사용자가
 * 부서일정을 삭제·수정하려 하면 egovAssertAdminOrChargerOrOwner가 IllegalStateException을
 * 던져 차단해야 한다. 담당자·등록자·ROLE_ADMIN은 모두 통과해야 한다.
 */
class EgovDeptSchdulManageControllerOwnershipTest {

	private static final String CHARGER = "USRCNFRM_00000000001";
	private static final String REGISTRANT = "USRCNFRM_00000000002";
	private static final String OUTSIDER = "USRCNFRM_00000000009";

	private static final class StubService implements EgovDeptSchdulManageService {
		private final DeptSchdulManageVO stored;
		private boolean deleteCalled = false;
		private boolean updateCalled = false;

		StubService(String chargerUniqId, String registerUniqId) {
			this.stored = new DeptSchdulManageVO();
			this.stored.setSchdulChargerId(chargerUniqId);
			this.stored.setFrstRegisterId(registerUniqId);
			this.stored.setSchdulBgnde("202608080900");
			this.stored.setSchdulEndde("202608081000");
		}

		@Override
		public DeptSchdulManageVO selectDeptSchdulManageDetailVO(DeptSchdulManageVO deptSchdulManageVO) {
			return stored;
		}

		@Override
		public void deleteDeptSchdulManage(DeptSchdulManageVO deptSchdulManageVO) {
			deleteCalled = true;
		}

		@Override
		public void updateDeptSchdulManage(DeptSchdulManageVO deptSchdulManageVO) {
			updateCalled = true;
		}

		@Override
		public List<EgovMap> selectDeptSchdulManageAuthorGroupPopup(ComDefaultVO searchVO) {
			throw new UnsupportedOperationException();
		}

		@Override
		public List<EgovMap> selectDeptSchdulManageEmpLyrPopup(ComDefaultVO searchVO) {
			throw new UnsupportedOperationException();
		}

		@Override
		public List<EgovMap> selectDeptSchdulManageMainList(Map<String, String> map) {
			throw new UnsupportedOperationException();
		}

		@Override
		public List<EgovMap> selectDeptSchdulManageRetrieve(Map<String, String> map) {
			throw new UnsupportedOperationException();
		}

		@Override
		public List<EgovMap> selectDeptSchdulManageList(ComDefaultVO searchVO) {
			throw new UnsupportedOperationException();
		}

		@Override
		public List<EgovMap> selectDeptSchdulManageDetail(DeptSchdulManageVO deptSchdulManageVO) {
			throw new UnsupportedOperationException();
		}

		@Override
		public int selectDeptSchdulManageListCnt(ComDefaultVO searchVO) {
			throw new UnsupportedOperationException();
		}

		@Override
		public void insertDeptSchdulManage(DeptSchdulManageVO deptSchdulManageVO) {
			throw new UnsupportedOperationException();
		}
	}

	@AfterEach
	void clearRequestContext() {
		org.springframework.web.context.request.RequestContextHolder.resetRequestAttributes();
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

	private static void setPrivateField(Object target, String fieldName, Object value) {
		try {
			java.lang.reflect.Field field = target.getClass().getDeclaredField(fieldName);
			field.setAccessible(true);
			field.set(target, value);
		} catch (ReflectiveOperationException e) {
			throw new IllegalStateException(e);
		}
	}

	private static EgovDeptSchdulManageController controllerWith(StubService service) {
		EgovDeptSchdulManageController controller = new EgovDeptSchdulManageController();
		setPrivateField(controller, "egovDeptSchdulManageService", service);
		setPrivateField(controller, "cmmUseService", new egovframework.com.cmm.service.EgovCmmUseService() {
			@Override
			public List<egovframework.com.cmm.service.CmmnDetailCode> selectCmmCodeDetail(egovframework.com.cmm.ComDefaultCodeVO comDefaultCodeVO) {
				return Collections.emptyList();
			}

			@Override
			public Map<String, List<egovframework.com.cmm.service.CmmnDetailCode>> selectCmmCodeDetails(
					List<egovframework.com.cmm.ComDefaultCodeVO> comDefaultCodeVOs) {
				return Collections.emptyMap();
			}

			@Override
			public List<egovframework.com.cmm.service.CmmnDetailCode> selectOgrnztIdDetail(egovframework.com.cmm.ComDefaultCodeVO comDefaultCodeVO) {
				return Collections.emptyList();
			}

			@Override
			public List<egovframework.com.cmm.service.CmmnDetailCode> selectGroupIdDetail(egovframework.com.cmm.ComDefaultCodeVO comDefaultCodeVO) {
				return Collections.emptyList();
			}
		});
		controller.egovMessageSource = new EgovMessageSource() {
			@Override
			public String getMessage(String code) {
				return code;
			}
		};
		return controller;
	}

	/**
	 * MockMultipartHttpServletRequest는 MockHttpServletRequest를 상속해 이 로컬 환경에서
	 * NoClassDefFoundError(ServletConnection)로 깨진다([[shell-and-egov-test-env-traps]]).
	 * 컨트롤러가 실제로 쓰는 getFiles(String)만 최소 구현한 프록시로 대체한다.
	 */
	private static MultipartHttpServletRequest emptyMultipartRequest() {
		return (MultipartHttpServletRequest) java.lang.reflect.Proxy.newProxyInstance(
				EgovDeptSchdulManageControllerOwnershipTest.class.getClassLoader(),
				new Class<?>[] { MultipartHttpServletRequest.class },
				(proxy, method, args) -> {
					if ("getFiles".equals(method.getName())) {
						return Collections.<MultipartFile>emptyList();
					}
					Class<?> returnType = method.getReturnType();
					if (returnType == boolean.class) {
						return false;
					}
					return null;
				});
	}

	private static DeptSchdulManageVO requestFor(String schdulId) {
		DeptSchdulManageVO vo = new DeptSchdulManageVO();
		vo.setSchdulId(schdulId);
		return vo;
	}

	// ---- egovDeptSchdulManageDelete: 담당자/등록자/관리자 4갈래 ----

	@Test
	void deleteByOutsiderIsRejected() {
		StubService service = new StubService(CHARGER, REGISTRANT);
		EgovDeptSchdulManageController controller = controllerWith(service);
		bindLoginUser(OUTSIDER, List.of());

		assertThrows(IllegalStateException.class, () -> controller.egovDeptSchdulManageDelete(requestFor("1")),
				"A logged-in user who is neither charger nor registrant must not delete a department schedule.");
	}

	@Test
	void deleteByChargerSucceeds() throws Exception {
		StubService service = new StubService(CHARGER, REGISTRANT);
		EgovDeptSchdulManageController controller = controllerWith(service);
		bindLoginUser(CHARGER, List.of());

		assertDoesNotThrow(() -> controller.egovDeptSchdulManageDelete(requestFor("1")));
		org.junit.jupiter.api.Assertions.assertTrue(service.deleteCalled, "The charger must be able to delete the schedule.");
	}

	@Test
	void deleteByRegistrantSucceeds() throws Exception {
		StubService service = new StubService(CHARGER, REGISTRANT);
		EgovDeptSchdulManageController controller = controllerWith(service);
		bindLoginUser(REGISTRANT, List.of());

		assertDoesNotThrow(() -> controller.egovDeptSchdulManageDelete(requestFor("1")));
		org.junit.jupiter.api.Assertions.assertTrue(service.deleteCalled, "The registrant must be able to delete the schedule.");
	}

	@Test
	void deleteByAdminSucceedsEvenWhenNeitherChargerNorRegistrant() throws Exception {
		StubService service = new StubService(CHARGER, REGISTRANT);
		EgovDeptSchdulManageController controller = controllerWith(service);
		bindLoginUser(OUTSIDER, List.of("ROLE_ADMIN"));

		assertDoesNotThrow(() -> controller.egovDeptSchdulManageDelete(requestFor("1")));
		org.junit.jupiter.api.Assertions.assertTrue(service.deleteCalled, "An admin must be able to delete any department schedule.");
	}

	// ---- deptSchdulManageModify (수정폼 진입) ----

	@Test
	void modifyFormByOutsiderIsRejected() {
		StubService service = new StubService(CHARGER, REGISTRANT);
		EgovDeptSchdulManageController controller = controllerWith(service);
		bindLoginUser(OUTSIDER, List.of());

		ComDefaultVO searchVO = new ComDefaultVO();
		ModelMap model = new ModelMap();
		assertThrows(IllegalStateException.class,
				() -> controller.deptSchdulManageModify(searchVO, requestFor("1"), model),
				"A logged-in outsider must not reach the modify form of another department schedule.");
	}

	@Test
	void modifyFormByChargerSucceeds() throws Exception {
		StubService service = new StubService(CHARGER, REGISTRANT);
		EgovDeptSchdulManageController controller = controllerWith(service);
		bindLoginUser(CHARGER, List.of());

		ComDefaultVO searchVO = new ComDefaultVO();
		ModelMap model = new ModelMap();
		String view = controller.deptSchdulManageModify(searchVO, requestFor("1"), model);
		org.junit.jupiter.api.Assertions.assertTrue(view.contains("EgovDeptSchdulManageModify"));
	}

	// ---- deptSchdulManageModifyActor (실제 수정 처리) ----

	@Test
	void modifyActorByOutsiderIsRejected() {
		StubService service = new StubService(CHARGER, REGISTRANT);
		EgovDeptSchdulManageController controller = controllerWith(service);
		bindLoginUser(OUTSIDER, List.of());

		MultipartHttpServletRequest multiRequest = emptyMultipartRequest();
		Map<String, String> commandMap = Map.of("cmd", "save");
		DeptSchdulManageVO vo = requestFor("1");
		BindingResult bindingResult = new BeanPropertyBindingResult(vo, "deptSchdulManageVO");
		ModelMap model = new ModelMap();
		RedirectAttributes redirectAttributes = new RedirectAttributesModelMap();

		assertThrows(IllegalStateException.class,
				() -> controller.deptSchdulManageModifyActor(multiRequest, commandMap, vo, bindingResult, model,
						redirectAttributes),
				"A logged-in outsider must not be able to save changes to another department schedule.");
		org.junit.jupiter.api.Assertions.assertTrue(!service.updateCalled, "updateDeptSchdulManage must not be reached by an outsider.");
	}

	@Test
	void modifyActorByRegistrantSucceeds() throws Exception {
		StubService service = new StubService(CHARGER, REGISTRANT);
		EgovDeptSchdulManageController controller = controllerWith(service);
		bindLoginUser(REGISTRANT, List.of());

		MultipartHttpServletRequest multiRequest = emptyMultipartRequest();
		Map<String, String> commandMap = Map.of("cmd", "save", "atchFileAt", "Y");
		DeptSchdulManageVO vo = requestFor("1");
		vo.setSchdulBgnde("202608080900");
		vo.setSchdulEndde("202608081000");
		BindingResult bindingResult = new BeanPropertyBindingResult(vo, "deptSchdulManageVO");
		ModelMap model = new ModelMap();
		RedirectAttributes redirectAttributes = new RedirectAttributesModelMap();

		controller.deptSchdulManageModifyActor(multiRequest, commandMap, vo, bindingResult, model, redirectAttributes);
		org.junit.jupiter.api.Assertions.assertTrue(service.updateCalled, "The registrant must be able to save changes.");
	}
}
