package egovframework.com.cop.smt.djm.web;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.ui.ModelMap;

import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.service.EgovUserDetailsService;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.com.cop.smt.djm.service.DeptJobBx;
import egovframework.com.cop.smt.djm.service.EgovDeptJobService;

/**
 * 부서업무함 삭제가 그 안의 부서업무를 고아로 남기지 않는지 확인한다.
 *
 * <p>부서업무 목록 조회는 부서업무함과 조인해 부서 조건으로 거르므로, 함이 사라지면 그 업무들이
 * 목록에도 건수에도 잡히지 않는다. 좌측 함 목록에도 지워진 함이 없어 함 아이디를 넘길 방법이 없다.
 * 화면으로는 회수도 삭제도 못 하는 행이 된다.</p>
 */
class EgovDeptJobControllerDeleteBxTest {

	private EgovDeptJobController controller;
	private final AtomicBoolean deleted = new AtomicBoolean(false);
	private String childCount = "1";

	@BeforeEach
	void setUp() {
		EgovUserDetailsService auth = (EgovUserDetailsService) Proxy.newProxyInstance(
				getClass().getClassLoader(), new Class<?>[] { EgovUserDetailsService.class },
				(proxy, method, args) -> {
					if ("isAuthenticated".equals(method.getName())) {
						return Boolean.TRUE;
					}
					if ("getAuthenticatedUser".equals(method.getName())) {
						return new LoginVO();
					}
					return null;
				});
		ReflectionTestUtils.setField(EgovUserDetailsHelper.class, "egovUserDetailsService", auth);

		EgovDeptJobService service = (EgovDeptJobService) Proxy.newProxyInstance(
				getClass().getClassLoader(), new Class<?>[] { EgovDeptJobService.class },
				(proxy, method, args) -> {
					switch (method.getName()) {
					case "selectDeptJobList":
						Map<String, Object> map = new HashMap<>();
						map.put("resultList", new ArrayList<>());
						map.put("resultCnt", childCount);
						return map;
					case "deleteDeptJobBx":
						deleted.set(true);
						return null;
					default:
						return null;
					}
				});

		controller = new EgovDeptJobController();
		ReflectionTestUtils.setField(controller, "deptJobService", service);
	}

	@AfterEach
	void tearDown() {
		ReflectionTestUtils.setField(EgovUserDetailsHelper.class, "egovUserDetailsService", null);
	}

	@Test
	void deleteDeptJobBxIsRefusedWhenBoxStillHasDeptJob() throws Exception {
		childCount = "1";
		ModelMap model = new ModelMap();

		controller.deleteDeptJobBx(bx(), model);

		assertFalse(deleted.get(), "부서업무가 남아 있으면 부서업무함을 지우지 않아야 한다");
		assertEquals("true", model.get("deptJobBxNotEmpty"), "화면에 알릴 표시가 있어야 한다");
	}

	@Test
	void deleteDeptJobBxProceedsWhenBoxIsEmpty() throws Exception {
		childCount = "0";
		ModelMap model = new ModelMap();

		controller.deleteDeptJobBx(bx(), model);

		assertTrue(deleted.get(), "비어 있는 부서업무함은 지울 수 있어야 한다");
	}

	private DeptJobBx bx() {
		DeptJobBx deptJobBx = new DeptJobBx();
		deptJobBx.setDeptJobBxId("DJBTEST01");
		return deptJobBx;
	}
}
