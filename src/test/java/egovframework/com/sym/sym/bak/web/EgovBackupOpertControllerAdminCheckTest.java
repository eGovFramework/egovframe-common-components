package egovframework.com.sym.sym.bak.web;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.lang.reflect.Method;

import org.junit.jupiter.api.Test;

import egovframework.com.cmm.annotation.RequireAdmin;

/**
 * insertBackupOpert·updateBackupOpert에 @RequireAdmin이 붙어있는지 확인한다.
 *
 * 삭제(deleteBackupOpert)·상세조회(selectBackupOpert)는 이미 @RequireAdmin이 붙어있는데
 * 등록·수정에는 빠져 있었다 — 같은 파일 안 형제 메서드 미러링이라 가장 단순한 케이스다.
 *
 * @RequireAdmin은 Spring AOP(`@annotation(RequireAdmin)` 포인트컷)로 위빙되므로 컨트롤러를
 * 직접 new해서 부르는 단위 테스트로는 실제 차단 동작을 재현할 수 없다(프록시를 거치지 않음).
 * 이 AOP 자체가 실제로 작동한다는 것은 이미 별도로 E2E 확인됐다. 이 테스트는 그 전제 위에서
 * "애노테이션이 두 메서드에 실제로 붙어있는가"라는 구조적 사실만 고정한다.
 */
class EgovBackupOpertControllerAdminCheckTest {

	private static Method method(String name, Class<?>... paramTypes) throws NoSuchMethodException {
		return EgovBackupOpertController.class.getDeclaredMethod(name, paramTypes);
	}

	@Test
	void insertRequiresAdmin() throws Exception {
		Method m = method("insertBackupOpert", egovframework.com.sym.sym.bak.service.BackupOpert.class,
				egovframework.com.sym.sym.bak.service.BackupOpert.class,
				org.springframework.validation.BindingResult.class, org.springframework.ui.ModelMap.class,
				org.springframework.web.servlet.mvc.support.RedirectAttributes.class);
		assertTrue(m.isAnnotationPresent(RequireAdmin.class), "백업작업 등록은 관리자만 가능해야 한다.");
	}

	@Test
	void updateRequiresAdmin() throws Exception {
		Method m = method("updateBackupOpert", egovframework.com.sym.sym.bak.service.BackupOpert.class,
				egovframework.com.sym.sym.bak.service.BackupOpert.class,
				org.springframework.validation.BindingResult.class, org.springframework.ui.ModelMap.class,
				org.springframework.web.servlet.mvc.support.RedirectAttributes.class);
		assertTrue(m.isAnnotationPresent(RequireAdmin.class), "백업작업 수정은 관리자만 가능해야 한다.");
	}
}
