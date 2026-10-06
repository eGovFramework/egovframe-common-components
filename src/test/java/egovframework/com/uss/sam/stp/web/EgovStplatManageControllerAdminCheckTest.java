package egovframework.com.uss.sam.stp.web;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.lang.reflect.Method;

import org.junit.jupiter.api.Test;

import egovframework.com.cmm.annotation.RequireAdmin;

/**
 * insertStplatCn·updateStplatCn·deleteStplatCn에 @RequireAdmin이 붙어있는지 확인한다.
 *
 * 약관관리(StplatManage)는 사이트 전체가 공유하는 이용약관 텍스트다. 목록조회·상세조회는 로그인조차
 * 필요 없는 공개 열람이고(EgovStplatListInqire.do·EgovStplatDetailInqire.do 둘 다 인증 체크 없음),
 * 등록·수정은 로그인만, 삭제는 그마저도 확인하지 않았다 — 등록·수정·삭제 전부 이 PR 이전에는
 * 관리자 여부를 가리지 않았다.
 *
 * @RequireAdmin은 Spring AOP(`@annotation(RequireAdmin)` 포인트컷)로 위빙되므로 컨트롤러를
 * 직접 new해서 부르는 단위 테스트로는 실제 차단 동작을 재현할 수 없다(프록시를 거치지 않음).
 * 이 AOP 자체가 실제로 작동한다는 것은 이미 별도로 E2E 확인됐다. 이 테스트는 그 전제 위에서
 * "애노테이션이 세 메서드에 실제로 붙어있는가"라는 구조적 사실만 고정한다.
 */
class EgovStplatManageControllerAdminCheckTest {

	private static Method method(String name, Class<?>... paramTypes) throws NoSuchMethodException {
		return EgovStplatManageController.class.getDeclaredMethod(name, paramTypes);
	}

	@Test
	void insertStplatCnRequiresAdmin() throws Exception {
		Method m = method("insertStplatCn", egovframework.com.uss.sam.stp.service.StplatManageDefaultVO.class,
				egovframework.com.uss.sam.stp.service.StplatManageVO.class,
				org.springframework.validation.BindingResult.class, org.springframework.ui.ModelMap.class);
		assertTrue(m.isAnnotationPresent(RequireAdmin.class), "약관 등록은 관리자만 가능해야 한다.");
	}

	@Test
	void updateStplatCnRequiresAdmin() throws Exception {
		Method m = method("updateStplatCn", egovframework.com.uss.sam.stp.service.StplatManageDefaultVO.class,
				egovframework.com.uss.sam.stp.service.StplatManageVO.class,
				org.springframework.validation.BindingResult.class);
		assertTrue(m.isAnnotationPresent(RequireAdmin.class), "약관 수정은 관리자만 가능해야 한다.");
	}

	@Test
	void deleteStplatCnRequiresAdmin() throws Exception {
		Method m = method("deleteStplatCn", egovframework.com.uss.sam.stp.service.StplatManageVO.class,
				egovframework.com.uss.sam.stp.service.StplatManageDefaultVO.class);
		assertTrue(m.isAnnotationPresent(RequireAdmin.class), "약관 삭제는 관리자만 가능해야 한다.");
	}
}
