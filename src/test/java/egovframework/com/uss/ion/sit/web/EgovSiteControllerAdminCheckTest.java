package egovframework.com.uss.ion.sit.web;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.lang.reflect.Method;

import org.junit.jupiter.api.Test;

import egovframework.com.cmm.annotation.RequireAdmin;

/**
 * insertSite·updateSite·deleteSite에 @RequireAdmin이 붙어있는지 확인한다.
 *
 * 사이트관리는 포털이 운영하는 사이트(다중 사이트) 목록을 관리하는 기능이다. 세 메서드 모두
 * 이 PR 이전에는 로그인 여부조차 확인하지 않았다.
 *
 * @RequireAdmin은 Spring AOP(`@annotation(RequireAdmin)` 포인트컷)로 위빙되므로 컨트롤러를
 * 직접 new해서 부르는 단위 테스트로는 실제 차단 동작을 재현할 수 없다(프록시를 거치지 않음).
 * 이 AOP 자체가 실제로 작동한다는 것은 이미 별도로 E2E 확인됐다. 이 테스트는 그 전제 위에서
 * "애노테이션이 세 메서드에 실제로 붙어있는가"라는 구조적 사실만 고정한다.
 */
class EgovSiteControllerAdminCheckTest {

	private static Method method(String name, Class<?>... paramTypes) throws NoSuchMethodException {
		return EgovSiteController.class.getDeclaredMethod(name, paramTypes);
	}

	@Test
	void insertSiteRequiresAdmin() throws Exception {
		Method m = method("insertSite", egovframework.com.uss.ion.sit.service.SiteVO.class,
				org.springframework.validation.BindingResult.class, org.springframework.ui.ModelMap.class);
		assertTrue(m.isAnnotationPresent(RequireAdmin.class), "사이트 등록은 관리자만 가능해야 한다.");
	}

	@Test
	void updateSiteRequiresAdmin() throws Exception {
		Method m = method("updateSite", egovframework.com.uss.ion.sit.service.SiteVO.class,
				org.springframework.validation.BindingResult.class, org.springframework.ui.ModelMap.class);
		assertTrue(m.isAnnotationPresent(RequireAdmin.class), "사이트 수정은 관리자만 가능해야 한다.");
	}

	@Test
	void deleteSiteRequiresAdmin() throws Exception {
		Method m = method("deleteSite", egovframework.com.uss.ion.sit.service.SiteVO.class);
		assertTrue(m.isAnnotationPresent(RequireAdmin.class), "사이트 삭제는 관리자만 가능해야 한다.");
	}
}
