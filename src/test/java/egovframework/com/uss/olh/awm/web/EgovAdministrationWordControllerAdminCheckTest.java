package egovframework.com.uss.olh.awm.web;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.lang.reflect.Method;

import org.junit.jupiter.api.Test;

import egovframework.com.cmm.annotation.RequireAdmin;

/**
 * insertAdministrationWord·updateAdministrationWord·deleteAdministrationWord에
 * @RequireAdmin이 붙어있는지 확인한다.
 *
 * 행정전문용어사전은 사이트 전체가 공유하는 용어 사전이다. 같은 gid=50 메뉴군의 FAQ(EgovFaqController)는
 * 이미 등록/수정/삭제에 @RequireAdmin으로 보호되는데, 이 컨트롤러는 세 메서드 모두 로그인 여부조차
 * 확인하지 않았다.
 *
 * @RequireAdmin은 Spring AOP(`@annotation(RequireAdmin)` 포인트컷)로 위빙되므로 컨트롤러를
 * 직접 new해서 부르는 단위 테스트로는 실제 차단 동작을 재현할 수 없다(프록시를 거치지 않음).
 * 이 AOP 자체가 실제로 작동한다는 것은 이미 별도로 E2E 확인됐다. 이 테스트는 그 전제 위에서
 * "애노테이션이 세 메서드에 실제로 붙어있는가"라는 구조적 사실만 고정한다.
 */
class EgovAdministrationWordControllerAdminCheckTest {

	private static Method method(String name, Class<?>... paramTypes) throws NoSuchMethodException {
		return EgovAdministrationWordController.class.getDeclaredMethod(name, paramTypes);
	}

	@Test
	void insertRequiresAdmin() throws Exception {
		Method m = method("insertAdministrationWord", egovframework.com.uss.olh.awm.service.AdministrationWordVO.class,
				egovframework.com.uss.olh.awm.service.AdministrationWordVO.class,
				org.springframework.validation.BindingResult.class, org.springframework.ui.Model.class);
		assertTrue(m.isAnnotationPresent(RequireAdmin.class), "행정전문용어사전 등록은 관리자만 가능해야 한다.");
	}

	@Test
	void updateRequiresAdmin() throws Exception {
		Method m = method("updateAdministrationWord", egovframework.com.uss.olh.awm.service.AdministrationWordVO.class,
				egovframework.com.uss.olh.awm.service.AdministrationWordVO.class,
				org.springframework.validation.BindingResult.class, org.springframework.ui.Model.class);
		assertTrue(m.isAnnotationPresent(RequireAdmin.class), "행정전문용어사전 수정은 관리자만 가능해야 한다.");
	}

	@Test
	void deleteRequiresAdmin() throws Exception {
		Method m = method("deleteAdministrationWord", egovframework.com.uss.olh.awm.service.AdministrationWordVO.class,
				egovframework.com.uss.olh.awm.service.AdministrationWordVO.class);
		assertTrue(m.isAnnotationPresent(RequireAdmin.class), "행정전문용어사전 삭제는 관리자만 가능해야 한다.");
	}
}
