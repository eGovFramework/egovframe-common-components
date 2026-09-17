package egovframework.com.cop.smt.djm.web;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.lang.reflect.Proxy;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.mvc.support.RedirectAttributesModelMap;

import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.service.EgovUserDetailsService;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.com.cop.smt.djm.service.DeptJobBxVO;

/**
 * 부서업무함 등록·수정의 검증 실패 재표시가 표시순서 상한({@code indictOrdrValue})을 다시 담는지 확인한다.
 *
 * <p>등록·수정 화면은 hidden 필드 {@code deptIndictOrdr}를 {@code ${indictOrdrValue}}로 채우고,
 * 저장 전 스크립트가 입력한 표시순서를 이 값과 비교한다. 화면을 띄우는 형제 경로
 * {@code getDeptJobBxOrdr}·{@code modifyDeptJobBx}는 이 값을 담지만 검증 실패 분기는 담지 않아,
 * 재표시된 화면에서는 hidden 값이 비어 상한 비교가 동작하지 않는다.</p>
 */
class EgovDeptJobControllerErrorReshowTest {

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

		controller = new EgovDeptJobController();
	}

	@AfterEach
	void tearDown() {
		ReflectionTestUtils.setField(EgovUserDetailsHelper.class, "egovUserDetailsService", null);
	}

	private static BindingResult failing(DeptJobBxVO target) {
		BindingResult bindingResult = new BeanPropertyBindingResult(target, "deptJobBxVO");
		bindingResult.rejectValue("indictOrdr", "typeMismatch");
		return bindingResult;
	}

	@Test
	void updateDeptJobBx_restoresIndictOrdrValueOnValidationError() throws Exception {
		DeptJobBxVO vo = new DeptJobBxVO();
		ModelMap model = new ModelMap();

		String view = controller.updateDeptJobBx(vo, failing(vo), "3", model);

		assertEquals("egovframework/com/cop/smt/djm/EgovDeptJobBxUpdt", view);
		assertEquals("3", model.get("indictOrdrValue"));
	}

	@Test
	void insertDeptJobBx_restoresIndictOrdrValueOnValidationError() throws Exception {
		DeptJobBxVO vo = new DeptJobBxVO();
		ModelMap model = new ModelMap();

		String view = controller.insertDeptJobBx(vo, failing(vo), "5", new RedirectAttributesModelMap(), model);

		assertEquals("egovframework/com/cop/smt/djm/EgovDeptJobBxRegist", view);
		assertEquals("5", model.get("indictOrdrValue"));
	}
}
