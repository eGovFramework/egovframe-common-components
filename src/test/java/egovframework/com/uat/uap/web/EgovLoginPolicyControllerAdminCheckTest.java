package egovframework.com.uat.uap.web;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.lang.reflect.Method;

import org.junit.jupiter.api.Test;

import egovframework.com.cmm.annotation.RequireAdmin;

/**
 * insertLoginPolicy·updateLoginPolicy·deleteLoginPolicy에 @RequireAdmin이 붙어있는지 확인한다.
 *
 * @RequireAdmin은 Spring AOP(`@annotation(RequireAdmin)` 포인트컷)로 위빙되므로 컨트롤러를
 * 직접 new해서 부르는 단위 테스트로는 실제 차단 동작을 재현할 수 없다(프록시를 거치지 않음).
 * 이 AOP 자체가 실제로 작동한다는 것은 이미 별도로 E2E 확인됐다(샌드박스에서 비관리자 계정으로
 * @RequireAdmin 붙은 엔드포인트를 호출해 HTTP 302 → accessDenied 확인). 이 테스트는 그 전제 위에서
 * "애노테이션이 세 메서드에 실제로 붙어있는가"라는 구조적 사실만 고정한다 — 애노테이션이 실수로
 * 빠지거나 지워지는 회귀를 잡기 위함이다.
 */
class EgovLoginPolicyControllerAdminCheckTest {

	private static Method method(String name, Class<?>... paramTypes) throws NoSuchMethodException {
		return EgovLoginPolicyController.class.getDeclaredMethod(name, paramTypes);
	}

	@Test
	void insertLoginPolicyRequiresAdmin() throws Exception {
		Method m = method("insertLoginPolicy", egovframework.com.uat.uap.service.LoginPolicy.class,
				org.springframework.validation.BindingResult.class, org.springframework.ui.ModelMap.class);
		assertTrue(m.isAnnotationPresent(RequireAdmin.class),
				"로그인정책 신규등록은 관리자만 가능해야 한다.");
	}

	@Test
	void updateLoginPolicyRequiresAdmin() throws Exception {
		Method m = method("updateLoginPolicy", egovframework.com.uat.uap.service.LoginPolicy.class,
				org.springframework.validation.BindingResult.class, org.springframework.ui.ModelMap.class);
		assertTrue(m.isAnnotationPresent(RequireAdmin.class),
				"로그인정책 수정은 관리자만 가능해야 한다.");
	}

	@Test
	void deleteLoginPolicyRequiresAdmin() throws Exception {
		Method m = method("deleteLoginPolicy", egovframework.com.uat.uap.service.LoginPolicy.class,
				org.springframework.ui.ModelMap.class);
		assertTrue(m.isAnnotationPresent(RequireAdmin.class),
				"로그인정책 삭제는 관리자만 가능해야 한다.");
	}
}
