package egovframework.com.cop.smt.djm.web;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.lang.reflect.Proxy;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.ui.ModelMap;

import egovframework.com.cmm.EgovMessageSource;
import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.service.EgovUserDetailsService;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.com.cop.smt.djm.service.DeptJobBxVO;
import egovframework.com.cop.smt.djm.service.DeptJobVO;
import egovframework.com.cop.smt.djm.service.EgovDeptJobService;

/**
 * 부서업무·부서업무함 수정화면이 존재하지 않는 식별자를 받았을 때 처리를 확인한다.
 *
 * <p>같은 컨트롤러의 삭제 경로는 서버에서 원본을 조회한 뒤 {@code if (originDeptJob == null)}로
 * 존재 여부를 확인하고 목록으로 돌려보낸다. 그런데 수정화면 두 곳은 같은 단건 조회 결과를
 * 확인 없이 바로 역참조해, 이미 지워졌거나 없는 식별자로 들어오면 NullPointerException이
 * 나면서 화면이 뜨지 않는다.</p>
 */
class EgovDeptJobControllerModifyNullTest {

	private EgovDeptJobController controller;

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

		// 단건 조회가 없는 식별자에 대해 null 을 돌려주는 상황.
		EgovDeptJobService service = (EgovDeptJobService) Proxy.newProxyInstance(
				getClass().getClassLoader(), new Class<?>[] { EgovDeptJobService.class },
				(proxy, method, args) -> null);

		controller = new EgovDeptJobController();
		ReflectionTestUtils.setField(controller, "deptJobService", service);
		ReflectionTestUtils.setField(controller, "egovMessageSource", new EgovMessageSource() {
			@Override
			public String getMessage(String code) {
				return code;
			}
		});
	}

	@AfterEach
	void tearDown() {
		ReflectionTestUtils.setField(EgovUserDetailsHelper.class, "egovUserDetailsService", null);
	}

	@Test
	void deptJobBxModifyViewFallsBackToTheListWhenTheRecordIsGone() throws Exception {
		DeptJobBxVO vo = new DeptJobBxVO();
		vo.setDeptJobBxId("DEPTJOBBX_00000000000000");

		ModelMap model = new ModelMap();
		String view = controller.modifyDeptJobBx(vo, model);

		assertNotNull(view, "없는 부서업무함으로 수정화면을 열면 예외 대신 화면 이동이 나와야 한다.");
		assertEquals("forward:/cop/smt/djm/selectDeptJobBxList.do", view);
	}

	@Test
	void deptJobModifyViewFallsBackToTheListWhenTheRecordIsGone() throws Exception {
		DeptJobVO vo = new DeptJobVO();
		vo.setDeptJobId("DEPTJOB_00000000000000");

		ModelMap model = new ModelMap();
		String view = controller.modifyDeptJob(vo, model);

		assertNotNull(view, "없는 부서업무로 수정화면을 열면 예외 대신 화면 이동이 나와야 한다.");
		assertEquals("forward:/cop/smt/djm/selectDeptJobList.do", view);
	}
}
