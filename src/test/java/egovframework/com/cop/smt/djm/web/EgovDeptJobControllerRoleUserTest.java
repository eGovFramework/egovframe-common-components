package egovframework.com.cop.smt.djm.web;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.lang.reflect.Proxy;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import egovframework.com.cmm.EgovMessageSource;
import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.exception.EgovAccessDeniedException;
import egovframework.com.cmm.service.EgovCmmUseService;
import egovframework.com.cmm.service.EgovUserDetailsService;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.com.cop.smt.djm.service.DeptJob;
import egovframework.com.cop.smt.djm.service.DeptJobBxVO;
import egovframework.com.cop.smt.djm.service.DeptJobVO;
import egovframework.com.cop.smt.djm.service.EgovDeptJobService;

/**
 * 부서업무 등록·조회·수정·삭제를 일반 사용자(ROLE_USER)로 호출했을 때의 권한 처리를 확인한다.
 *
 * <p>목록은 로그인 사용자 부서의 업무만 보인다. 일반 사용자는 업무담당자로 자신이 채워지고, 담당자·등록자·같은 부서 업무만 조회한다.
 * 수정·삭제는 등록자와 상관없이 같은 부서 업무만 가능하고, 다른 부서 업무는 관리자도 막는다.</p>
 */
class EgovDeptJobControllerRoleUserTest {

	private static final String ME = "USRCNFRM_00000000002";
	private static final String OTHER = "USRCNFRM_00000000003";
	private static final String MY_DEPT = "ORGNZT_0000000000001";
	private static final String OTHER_DEPT = "ORGNZT_0000000000002";

	private final List<String> calls = new java.util.ArrayList<>();
	private DeptJob inserted;
	private DeptJob updated;
	private DeptJobVO stored;
	private DeptJobBxVO storedBx = bx(MY_DEPT);
	private DeptJobVO listRequest;
	private DeptJobBxVO ordrRequest;

	@AfterEach
	void tearDown() {
		ReflectionTestUtils.setField(EgovUserDetailsHelper.class, "egovUserDetailsService", null);
	}

	private static DeptJobBxVO bx(String deptId) {
		DeptJobBxVO vo = new DeptJobBxVO();
		vo.setDeptId(deptId);
		return vo;
	}

	private static DeptJobVO job(String deptId, String chargerId, String registerId) {
		DeptJobVO vo = new DeptJobVO();
		vo.setDeptJobId("DEPTJOB_00000000000001");
		vo.setDeptId(deptId);
		vo.setChargerId(chargerId);
		vo.setFrstRegisterId(registerId);
		vo.setAtchFileId("");
		return vo;
	}

	private void login(String uniqId, String... roles) {
		LoginVO user = new LoginVO();
		user.setUniqId(uniqId);
		user.setName("홍길동");
		user.setOrgnztId(MY_DEPT);
		EgovUserDetailsService auth = (EgovUserDetailsService) Proxy.newProxyInstance(
				getClass().getClassLoader(), new Class<?>[] { EgovUserDetailsService.class },
				(proxy, method, args) -> {
					switch (method.getName()) {
					case "isAuthenticated":
						return Boolean.TRUE;
					case "getAuthenticatedUser":
						return user;
					case "getAuthorities":
						return List.of(roles);
					default:
						return null;
					}
				});
		ReflectionTestUtils.setField(EgovUserDetailsHelper.class, "egovUserDetailsService", auth);
	}

	private EgovDeptJobController controller() {
		EgovDeptJobService service = (EgovDeptJobService) Proxy.newProxyInstance(
				getClass().getClassLoader(), new Class<?>[] { EgovDeptJobService.class },
				(proxy, method, args) -> {
					calls.add(method.getName());
					switch (method.getName()) {
					case "selectDeptJob":
						return stored;
					case "selectDeptJobBx":
						return storedBx;
					case "updateDeptJobBxOrdr":
						ordrRequest = (DeptJobBxVO) args[0];
						return true;
					case "insertDeptJob":
						inserted = (DeptJob) args[0];
						return null;
					case "updateDeptJob":
						updated = (DeptJob) args[0];
						return null;
					case "selectDept":
						return "부서";
					case "selectDeptJobList":
						listRequest = (DeptJobVO) args[0];
						Map<String, Object> map = new HashMap<>();
						map.put("resultList", Collections.emptyList());
						map.put("resultCnt", "0");
						return map;
					default:
						return null;
					}
				});
		EgovDeptJobController controller = new EgovDeptJobController();
		ReflectionTestUtils.setField(controller, "deptJobService", service);
		ReflectionTestUtils.setField(controller, "cmmUseService", Proxy.newProxyInstance(
				getClass().getClassLoader(), new Class<?>[] { EgovCmmUseService.class },
				(proxy, method, args) -> "selectCmmCodeDetail".equals(method.getName()) ? Collections.emptyList() : null));
		ReflectionTestUtils.setField(controller, "propertyService", Proxy.newProxyInstance(
				getClass().getClassLoader(), new Class<?>[] { org.egovframe.rte.fdl.property.EgovPropertyService.class },
				(proxy, method, args) -> "getInt".equals(method.getName()) ? Integer.valueOf(10) : null));
		ReflectionTestUtils.setField(controller, "egovMessageSource", new EgovMessageSource() {
			@Override
			public String getMessage(String code) {
				return code;
			}
		});
		return controller;
	}

	private static MultipartHttpServletRequest emptyMultipartRequest() {
		return (MultipartHttpServletRequest) Proxy.newProxyInstance(
				EgovDeptJobControllerRoleUserTest.class.getClassLoader(),
				new Class<?>[] { MultipartHttpServletRequest.class },
				(proxy, method, args) -> "getFiles".equals(method.getName()) ? Collections.<MultipartFile>emptyList()
						: (method.getReturnType() == boolean.class ? (Object) false : null));
	}

	// ---- 목록: 로그인 사용자 부서의 부서업무만 ----

	@Test
	void listIsLimitedToTheLoginUsersDept() throws Exception {
		login(ME, "ROLE_USER", "ROLE_ADMIN");
		DeptJobVO request = new DeptJobVO();
		request.setSearchDeptId(OTHER_DEPT); // 요청 값은 무시돼야 한다

		controller().selectDeptJobList(request, new ModelMap());

		assertEquals(MY_DEPT, listRequest.getSearchDeptId());
	}

	// ---- 업무함 순서 변경 ----

	@Test
	void orderChangeUsesTheStoredDeptAndOrderNotTheRequest() {
		login(ME, "ROLE_USER", "ROLE_ADMIN");
		storedBx = bx(MY_DEPT);
		storedBx.setIndictOrdr(3);
		DeptJobBxVO request = new DeptJobBxVO();
		request.setDeptJobBxId("DX_001");
		request.setDeptId(OTHER_DEPT); // 요청 값은 무시돼야 한다
		request.setIndictOrdr(1);
		request.setOrdrCnd("up");

		controller().updateDeptJobBxOrdr(request, new ModelMap());

		assertEquals(MY_DEPT, ordrRequest.getDeptId(), "순서 변경은 원본 업무함의 부서 안에서만 일어나야 한다.");
		assertEquals(Integer.valueOf(3), ordrRequest.getIndictOrdr());
	}

	// ---- 등록 ----

	@Test
	void roleUserRegistersWithSelfAsChargerEvenIfRequestNamesSomeoneElse() throws Exception {
		login(ME, "ROLE_USER");
		DeptJobVO vo = new DeptJobVO();
		vo.setDeptJobBxId("DEPTJOBBX_00000000000001");
		vo.setChargerId(OTHER);

		String view = controller().insertDeptJob(emptyMultipartRequest(), vo, new BeanPropertyBindingResult(vo, "deptJobVO"),
				new ModelMap());

		assertEquals("forward:/cop/smt/djm/selectDeptJobList.do", view);
		assertEquals(ME, inserted.getChargerId());
		assertEquals(ME, inserted.getFrstRegisterId());
	}

	@Test
	void roleUserCannotRegisterIntoAnotherDeptsBox() {
		login(ME, "ROLE_USER");
		storedBx = bx(OTHER_DEPT);
		DeptJobVO vo = new DeptJobVO();
		vo.setDeptJobBxId("DEPTJOBBX_00000000000009");

		assertThrows(EgovAccessDeniedException.class, () -> controller().insertDeptJob(emptyMultipartRequest(), vo,
				new BeanPropertyBindingResult(vo, "deptJobVO"), new ModelMap()));
	}

	@Test
	void registerFormFillsDeptAndChargerFromTheLoginUser() {
		login(ME, "ROLE_USER");
		DeptJobVO vo = new DeptJobVO();

		controller().addDeptJob(vo, new ModelMap());

		assertEquals(MY_DEPT, vo.getDeptId());
		assertEquals(ME, vo.getChargerId());
		assertEquals("홍길동", vo.getChargerNm());
	}

	// ---- 수정 ----

	private void updateAs(String uniqId, String jobDept, String... roles) {
		login(uniqId, roles);
		stored = job(jobDept, ME, ME);
		DeptJobVO vo = new DeptJobVO();
		vo.setDeptJobId("DEPTJOB_00000000000001");
		vo.setDeptJobBxId("DEPTJOBBX_00000000000001");
		controller().updateDeptJob(emptyMultipartRequest(), new HashMap<>(), vo, new BeanPropertyBindingResult(vo, "deptJobVO"),
				new ModelMap());
	}

	@Test
	void updatesOwnJobWithoutAttachments() {
		updateAs(ME, MY_DEPT, "ROLE_USER", "ROLE_ADMIN");
		assertTrue(updated != null, "첨부파일이 없어도 같은 부서 업무 수정은 저장돼야 한다.");
	}

	@Test
	void sameDeptUpdatesJobRegisteredBySomeoneElse() {
		updateAs(OTHER, MY_DEPT, "ROLE_USER", "ROLE_ADMIN");
		assertTrue(updated != null, "등록자가 아니어도 같은 부서 업무는 수정할 수 있어야 한다.");
	}

	@Test
	void otherDeptJobCannotBeUpdatedEvenByAdmin() {
		assertThrows(EgovAccessDeniedException.class, () -> updateAs(ME, OTHER_DEPT, "ROLE_USER", "ROLE_ADMIN"));
		assertTrue(updated == null);
	}

	// ---- 삭제 ----

	private void deleteAs(String uniqId, String jobDept, String... roles) {
		login(uniqId, roles);
		stored = job(jobDept, ME, ME);
		DeptJob request = new DeptJob();
		request.setDeptJobId("DEPTJOB_00000000000001");
		controller().deleteDeptJob(request, new ModelMap());
	}

	@Test
	void sameDeptDeletesJobRegisteredBySomeoneElse() {
		deleteAs(OTHER, MY_DEPT, "ROLE_USER", "ROLE_ADMIN");
		assertTrue(calls.contains("deleteDeptJob"));
	}

	@Test
	void otherDeptJobCannotBeDeletedEvenByAdmin() {
		assertThrows(EgovAccessDeniedException.class, () -> deleteAs(ME, OTHER_DEPT, "ROLE_USER", "ROLE_ADMIN"));
		assertTrue(!calls.contains("deleteDeptJob"));
	}

	// ---- 상세조회 ----

	@Test
	void roleUserViewsJobWhereTheyAreChargerEvenInAnotherDept() {
		login(ME, "ROLE_USER");
		stored = job(OTHER_DEPT, ME, OTHER);
		DeptJobVO vo = new DeptJobVO();
		ModelMap model = new ModelMap();

		String view = controller().selectDeptJob(vo, model);

		assertEquals("egovframework/com/cop/smt/djm/EgovDeptJobDetail", view);
		assertEquals(false, model.get("canModify"), "다른 부서 업무는 담당자여도 수정·삭제 버튼을 보이지 않는다.");
	}

	@Test
	void roleUserCannotViewUnrelatedJobInAnotherDept() {
		login(ME, "ROLE_USER");
		stored = job(OTHER_DEPT, OTHER, OTHER);

		assertThrows(EgovAccessDeniedException.class, () -> controller().selectDeptJob(new DeptJobVO(), new ModelMap()));
	}
}
