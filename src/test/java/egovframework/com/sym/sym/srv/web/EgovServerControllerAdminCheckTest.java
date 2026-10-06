package egovframework.com.sym.sym.srv.web;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.lang.reflect.Method;

import org.junit.jupiter.api.Test;

import egovframework.com.cmm.annotation.RequireAdmin;

/**
 * insertServerEqpmn·updateServerEqpmn·insertServer·updateServer·saveServerEqpmnRelate에
 * @RequireAdmin이 붙어있는지 확인한다.
 *
 * @RequireAdmin은 Spring AOP(`@annotation(RequireAdmin)` 포인트컷)로 위빙되므로 컨트롤러를
 * 직접 new해서 부르는 단위 테스트로는 실제 차단 동작을 재현할 수 없다(프록시를 거치지 않음).
 * 이 AOP 자체가 실제로 작동한다는 것은 이미 별도로 E2E 확인됐다(샌드박스에서 비관리자 계정으로
 * @RequireAdmin 붙은 엔드포인트를 호출해 HTTP 302 → accessDenied 확인). 이 테스트는 그 전제 위에서
 * "애노테이션이 다섯 메서드에 실제로 붙어있는가"라는 구조적 사실만 고정한다.
 */
class EgovServerControllerAdminCheckTest {

	private static Method method(String name, Class<?>... paramTypes) throws NoSuchMethodException {
		return EgovServerController.class.getDeclaredMethod(name, paramTypes);
	}

	@Test
	void insertServerEqpmnRequiresAdmin() throws Exception {
		Method m = method("insertServerEqpmn", egovframework.com.sym.sym.srv.service.ServerEqpmnVO.class,
				egovframework.com.sym.sym.srv.service.ServerEqpmn.class,
				org.springframework.validation.BindingResult.class, org.springframework.ui.ModelMap.class);
		assertTrue(m.isAnnotationPresent(RequireAdmin.class), "서버장비 신규등록은 관리자만 가능해야 한다.");
	}

	@Test
	void updateServerEqpmnRequiresAdmin() throws Exception {
		Method m = method("updateServerEqpmn", egovframework.com.sym.sym.srv.service.ServerEqpmnVO.class,
				egovframework.com.sym.sym.srv.service.ServerEqpmn.class,
				org.springframework.validation.BindingResult.class,
				org.springframework.web.bind.support.SessionStatus.class, org.springframework.ui.ModelMap.class);
		assertTrue(m.isAnnotationPresent(RequireAdmin.class), "서버장비 수정은 관리자만 가능해야 한다.");
	}

	@Test
	void insertServerRequiresAdmin() throws Exception {
		Method m = method("insertServer", egovframework.com.sym.sym.srv.service.ServerVO.class,
				egovframework.com.sym.sym.srv.service.Server.class,
				org.springframework.validation.BindingResult.class, org.springframework.ui.ModelMap.class);
		assertTrue(m.isAnnotationPresent(RequireAdmin.class), "서버 신규등록은 관리자만 가능해야 한다.");
	}

	@Test
	void updateServerRequiresAdmin() throws Exception {
		Method m = method("updateServer", egovframework.com.sym.sym.srv.service.ServerVO.class,
				egovframework.com.sym.sym.srv.service.Server.class,
				org.springframework.validation.BindingResult.class,
				org.springframework.web.bind.support.SessionStatus.class, org.springframework.ui.ModelMap.class);
		assertTrue(m.isAnnotationPresent(RequireAdmin.class), "서버 수정은 관리자만 가능해야 한다.");
	}

	@Test
	void saveServerEqpmnRelateRequiresAdmin() throws Exception {
		Method m = method("saveServerEqpmnRelate", String.class, String.class, String.class,
				egovframework.com.sym.sym.srv.service.ServerEqpmnRelate.class,
				org.springframework.web.bind.support.SessionStatus.class, org.springframework.ui.ModelMap.class);
		assertTrue(m.isAnnotationPresent(RequireAdmin.class), "서버-장비 연계 저장은 관리자만 가능해야 한다.");
	}
}
