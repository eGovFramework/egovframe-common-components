package egovframework.com.sym.tbm.tbp.web;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.lang.reflect.Method;

import org.junit.jupiter.api.Test;

import egovframework.com.cmm.annotation.RequireAdmin;

/**
 * insertTroblProcess·deleteTroblProcess에 @RequireAdmin이 붙어있는지 확인한다.
 *
 * TroblProcess(장애처리결과)는 troblOpetrNm(처리자명) 필드로 알 수 있듯 신청자 본인이 아니라
 * IT 운영자가 장애를 처리·기록하는 관리자 기능이다(신청자 개인소유 자원인 장애신청/TroblReqst와는
 * 다른 모듈). 이 파일에는 이 PR 이전에 @RequireAdmin이 붙은 형제 메서드가 없어, FAQ 등 이
 * 저장소에서 이미 확립된 관리자 전용 패턴을 그대로 적용했다.
 *
 * @RequireAdmin은 Spring AOP(`@annotation(RequireAdmin)` 포인트컷)로 위빙되므로 컨트롤러를
 * 직접 new해서 부르는 단위 테스트로는 실제 차단 동작을 재현할 수 없다(프록시를 거치지 않음).
 * 이 AOP 자체가 실제로 작동한다는 것은 이미 별도로 E2E 확인됐다(샌드박스에서 비관리자 계정으로
 * @RequireAdmin 붙은 엔드포인트를 호출해 HTTP 302 → accessDenied 확인). 이 테스트는 그 전제 위에서
 * "애노테이션이 두 메서드에 실제로 붙어있는가"라는 구조적 사실만 고정한다.
 */
class EgovTroblProcessControllerAdminCheckTest {

	private static Method method(String name, Class<?>... paramTypes) throws NoSuchMethodException {
		return EgovTroblProcessController.class.getDeclaredMethod(name, paramTypes);
	}

	@Test
	void insertTroblProcessRequiresAdmin() throws Exception {
		Method m = method("insertTroblProcess", egovframework.com.sym.tbm.tbp.service.TroblProcessVO.class,
				egovframework.com.sym.tbm.tbp.service.TroblProcess.class,
				org.springframework.validation.BindingResult.class,
				org.springframework.web.bind.support.SessionStatus.class, org.springframework.ui.ModelMap.class);
		assertTrue(m.isAnnotationPresent(RequireAdmin.class), "장애처리결과 등록은 관리자만 가능해야 한다.");
	}

	@Test
	void deleteTroblProcessRequiresAdmin() throws Exception {
		Method m = method("deleteTroblProcess", String.class, egovframework.com.sym.tbm.tbp.service.TroblProcess.class,
				org.springframework.ui.ModelMap.class);
		assertTrue(m.isAnnotationPresent(RequireAdmin.class), "장애처리결과 삭제는 관리자만 가능해야 한다.");
	}
}
