package egovframework.com.cop.smt.sdm.web;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributesModelMap;

import egovframework.com.cmm.ComDefaultVO;
import egovframework.com.cmm.EgovMessageSource;
import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.exception.EgovAccessDeniedException;
import egovframework.com.cmm.service.EgovUserDetailsService;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.com.cop.smt.sdm.service.DeptSchdulManageVO;
import egovframework.com.cop.smt.sdm.service.EgovDeptSchdulManageService;
import org.egovframe.rte.psl.dataaccess.util.EgovMap;

/**
 * 부서일정 삭제·수정·등록의 같은 부서 검증 회귀 테스트.
 *
 * 부서일정은 같은 부서원이 함께 쓰는 자원이다. 삭제·수정폼·수정 저장은 저장된 일정의 부서(SCHDUL_DEPT_ID)가,
 * 등록 저장은 요청한 일정 부서가 로그인 사용자의 부서(orgnztId)와 같아야 통과한다.
 * 등록자·담당자 여부는 따지지 않고, 관리자도 예외가 아니다. 수정 저장은 부서를 저장된 값으로 고정한다.
 *
 * 부서일정은 관리자 전용 메뉴라 상세조회(egovDeptSchdulManageDetail)는 다른 부서 일정도 볼 수 있다.
 */
class EgovDeptSchdulManageControllerOwnershipTest {

	private static final String CHARGER = "USRCNFRM_00000000001";
	private static final String REGISTRANT = "USRCNFRM_00000000002";
	private static final String COLLEAGUE = "USRCNFRM_00000000003";
	private static final String OUTSIDER = "USRCNFRM_00000000009";

	private static final String DEPT = "ORGNZT_0000000000001";
	private static final String OTHER_DEPT = "ORGNZT_0000000000002";

	private static final class StubService implements EgovDeptSchdulManageService {
		private final DeptSchdulManageVO stored;
		private boolean deleteCalled = false;
		private DeptSchdulManageVO updated;
		private DeptSchdulManageVO inserted;

		StubService(String chargerUniqId, String registerUniqId, String deptId) {
			this.stored = new DeptSchdulManageVO();
			this.stored.setSchdulChargerId(chargerUniqId);
			this.stored.setFrstRegisterId(registerUniqId);
			this.stored.setSchdulDeptId(deptId);
			this.stored.setSchdulKindCode("1");
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
			updated = deptSchdulManageVO;
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
			return List.of();
		}

		@Override
		public int selectDeptSchdulManageListCnt(ComDefaultVO searchVO) {
			throw new UnsupportedOperationException();
		}

		@Override
		public void insertDeptSchdulManage(DeptSchdulManageVO deptSchdulManageVO) {
			inserted = deptSchdulManageVO;
		}
	}

	@AfterEach
	void clearRequestContext() {
		org.springframework.web.context.request.RequestContextHolder.resetRequestAttributes();
	}

	private static void bindLoginUser(String uniqId, String orgnztId, List<String> authorities) {
		LoginVO login = new LoginVO();
		login.setUniqId(uniqId);
		login.setOrgnztId(orgnztId);
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
	 * NoClassDefFoundError(ServletConnection)로 깨진다.
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

	private static StubService storedInDept() {
		return new StubService(CHARGER, REGISTRANT, DEPT);
	}

	/** 부서가 달라서 막혔는지(부서 정보 누락 등 다른 이유가 아닌지) 확인한다. */
	private static void assertDeniedByDept(org.junit.jupiter.api.function.Executable call) {
		EgovAccessDeniedException e = assertThrows(EgovAccessDeniedException.class, call);
		assertEquals("권한이 없습니다.", e.getMessage());
	}

	// ---- egovDeptSchdulManageDelete: 같은 부서원만 통과 (등록자·담당자 여부 무관, 관리자 예외 없음) ----

	@Test
	void deleteBySameDeptUserSucceedsEvenWhenNeitherChargerNorRegistrant() {
		StubService service = storedInDept();
		EgovDeptSchdulManageController controller = controllerWith(service);
		bindLoginUser(COLLEAGUE, DEPT, List.of());

		assertDoesNotThrow(() -> controller.egovDeptSchdulManageDelete(requestFor("1")));
		assertTrue(service.deleteCalled, "같은 부서원은 부서일정을 삭제할 수 있어야 한다.");
	}

	@Test
	void deleteByOtherDeptUserIsRejectedEvenWhenRegistrant() {
		StubService service = storedInDept();
		EgovDeptSchdulManageController controller = controllerWith(service);
		bindLoginUser(REGISTRANT, OTHER_DEPT, List.of());

		assertDeniedByDept(() -> controller.egovDeptSchdulManageDelete(requestFor("1")));
		assertFalse(service.deleteCalled, "다른 부서 사용자는 등록자라도 삭제에 닿으면 안 된다.");
	}

	@Test
	void personalScheduleOfSameDeptCannotBeDeletedThroughDeptSchedulePath() {
		StubService service = storedInDept();
		service.stored.setSchdulKindCode("2");
		EgovDeptSchdulManageController controller = controllerWith(service);
		bindLoginUser(COLLEAGUE, DEPT, List.of("ROLE_ADMIN"));

		assertThrows(EgovAccessDeniedException.class, () -> controller.egovDeptSchdulManageDelete(requestFor("1")));
		assertFalse(service.deleteCalled, "부서일정 경로로 같은 부서원의 개인일정을 지우면 안 된다.");
	}

	@Test
	void deleteByAdminOfOtherDeptIsRejected() {
		StubService service = storedInDept();
		EgovDeptSchdulManageController controller = controllerWith(service);
		bindLoginUser(OUTSIDER, OTHER_DEPT, List.of("ROLE_ADMIN"));

		assertDeniedByDept(() -> controller.egovDeptSchdulManageDelete(requestFor("1")));
		assertFalse(service.deleteCalled, "관리자도 다른 부서 일정은 삭제할 수 없다.");
	}

	// ---- deptSchdulManageModify (수정폼 진입) ----

	@Test
	void modifyFormBySameDeptUserSucceeds() {
		StubService service = storedInDept();
		EgovDeptSchdulManageController controller = controllerWith(service);
		bindLoginUser(COLLEAGUE, DEPT, List.of());

		ModelMap model = new ModelMap();
		String view = assertDoesNotThrow(() -> controller.deptSchdulManageModify(new ComDefaultVO(), requestFor("1"),
				model, new MockHttpServletRequest()));
		assertEquals("egovframework/com/cop/smt/sdm/EgovDeptSchdulManageModify", view);
	}

	@Test
	void modifyFormByOtherDeptUserIsRejected() {
		StubService service = storedInDept();
		EgovDeptSchdulManageController controller = controllerWith(service);
		bindLoginUser(CHARGER, OTHER_DEPT, List.of());

		assertDeniedByDept(() -> controller.deptSchdulManageModify(new ComDefaultVO(), requestFor("1"),
				new ModelMap(), new MockHttpServletRequest()));
	}

	@Test
	void modifyFormByAdminOfOtherDeptIsRejected() {
		StubService service = storedInDept();
		EgovDeptSchdulManageController controller = controllerWith(service);
		bindLoginUser(OUTSIDER, OTHER_DEPT, List.of("ROLE_ADMIN"));

		assertDeniedByDept(() -> controller.deptSchdulManageModify(new ComDefaultVO(), requestFor("1"),
				new ModelMap(), new MockHttpServletRequest()));
	}

	// ---- deptSchdulManageModifyActor (실제 수정 처리) ----

	private static String modifyActor(EgovDeptSchdulManageController controller, DeptSchdulManageVO vo) {
		return controller.deptSchdulManageModifyActor(emptyMultipartRequest(), Map.of("cmd", "save", "atchFileAt", "Y"),
				vo, new BeanPropertyBindingResult(vo, "deptSchdulManageVO"), new ModelMap(),
				new RedirectAttributesModelMap());
	}

	private static DeptSchdulManageVO modifyRequest() {
		DeptSchdulManageVO vo = requestFor("1");
		vo.setSchdulBgnde("202608080900");
		vo.setSchdulEndde("202608081000");
		return vo;
	}

	@Test
	void modifyActorBySameDeptUserSucceeds() {
		StubService service = storedInDept();
		EgovDeptSchdulManageController controller = controllerWith(service);
		bindLoginUser(COLLEAGUE, DEPT, List.of());

		modifyActor(controller, modifyRequest());
		assertTrue(service.updated != null, "같은 부서원은 부서일정을 수정할 수 있어야 한다.");
	}

	@Test
	void modifyActorCannotMoveScheduleToAnotherDept() {
		StubService service = storedInDept();
		EgovDeptSchdulManageController controller = controllerWith(service);
		bindLoginUser(COLLEAGUE, DEPT, List.of());

		DeptSchdulManageVO vo = modifyRequest();
		vo.setSchdulDeptId(OTHER_DEPT);
		modifyActor(controller, vo);

		assertEquals(DEPT, service.updated.getSchdulDeptId(), "수정 저장은 일정 부서를 저장된 값으로 고정해야 한다.");
	}

	@Test
	void modifyActorByOtherDeptUserIsRejected() {
		StubService service = storedInDept();
		EgovDeptSchdulManageController controller = controllerWith(service);
		bindLoginUser(REGISTRANT, OTHER_DEPT, List.of());

		assertDeniedByDept(() -> modifyActor(controller, modifyRequest()));
		assertTrue(service.updated == null, "다른 부서 사용자는 등록자라도 수정에 닿으면 안 된다.");
	}

	@Test
	void modifyActorByAdminOfOtherDeptIsRejected() {
		StubService service = storedInDept();
		EgovDeptSchdulManageController controller = controllerWith(service);
		bindLoginUser(OUTSIDER, OTHER_DEPT, List.of("ROLE_ADMIN"));

		assertDeniedByDept(() -> modifyActor(controller, modifyRequest()));
		assertTrue(service.updated == null, "관리자도 다른 부서 일정은 수정할 수 없다.");
	}

	// ---- deptSchdulManageRegistActor (등록 처리): 자기 부서 일정만 등록 ----

	private static String registActor(EgovDeptSchdulManageController controller, String schdulDeptId) {
		DeptSchdulManageVO vo = modifyRequest();
		vo.setSchdulDeptId(schdulDeptId);
		return controller.deptSchdulManageRegistActor(emptyMultipartRequest(), new ComDefaultVO(), Map.of("cmd", "save"),
				vo, new BeanPropertyBindingResult(vo, "deptSchdulManageVO"), new ModelMap());
	}

	@Test
	void registerIntoOwnDeptSucceeds() {
		StubService service = storedInDept();
		EgovDeptSchdulManageController controller = controllerWith(service);
		bindLoginUser(COLLEAGUE, DEPT, List.of());

		registActor(controller, DEPT);
		assertEquals(DEPT, service.inserted.getSchdulDeptId(), "자기 부서 일정은 등록할 수 있어야 한다.");
	}

	@Test
	void registerIntoOtherDeptIsRejected() {
		StubService service = storedInDept();
		EgovDeptSchdulManageController controller = controllerWith(service);
		bindLoginUser(COLLEAGUE, DEPT, List.of());

		assertDeniedByDept(() -> registActor(controller, OTHER_DEPT));
		assertTrue(service.inserted == null, "다른 부서 일정은 등록되면 안 된다.");
	}

	@Test
	void registerIntoOtherDeptByAdminIsRejected() {
		StubService service = storedInDept();
		EgovDeptSchdulManageController controller = controllerWith(service);
		bindLoginUser(OUTSIDER, DEPT, List.of("ROLE_ADMIN"));

		assertDeniedByDept(() -> registActor(controller, OTHER_DEPT));
		assertTrue(service.inserted == null, "관리자도 다른 부서 일정은 등록할 수 없다.");
	}

	// ---- egovDeptSchdulManageDetail (상세조회): 관리자 전용, 부서 제한 없음 ----

	@Test
	void viewDetailByAdminOfOtherDeptSucceeds() throws Exception {
		StubService service = storedInDept();
		EgovDeptSchdulManageController controller = controllerWith(service);
		bindLoginUser(OUTSIDER, OTHER_DEPT, List.of("ROLE_ADMIN"));

		ComDefaultVO searchVO = new ComDefaultVO();
		ModelMap model = new ModelMap();
		assertDoesNotThrow(() -> controller.egovDeptSchdulManageDetail(searchVO, requestFor("1"), model));
	}
}
