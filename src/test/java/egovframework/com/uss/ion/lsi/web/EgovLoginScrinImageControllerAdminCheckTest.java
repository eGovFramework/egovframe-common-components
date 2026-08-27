package egovframework.com.uss.ion.lsi.web;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.lang.reflect.Method;

import org.junit.jupiter.api.Test;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import egovframework.com.cmm.annotation.RequireAdmin;
import egovframework.com.uss.ion.lsi.service.LoginScrinImageVO;

/**
 * 로그인화면이미지 관리의 수정화면·등록·수정·삭제·일괄삭제에 @RequireAdmin이 붙어있는지 확인한다.
 *
 * @RequireAdmin은 Spring AOP(`@annotation(RequireAdmin)` 포인트컷)로 위빙되므로 컨트롤러를
 * 직접 new해서 부르는 단위 테스트로는 실제 차단 동작을 재현할 수 없다(프록시를 거치지 않음).
 * 이 AOP 자체가 실제로 작동한다는 것은 형제 배너관리(EgovBannerController)가 같은 애노테이션으로
 * 관리자 전용이 되는 것으로 이미 확인된다. 이 테스트는 그 전제 위에서 "애노테이션이 다섯 메서드에
 * 실제로 붙어있는가"라는 구조적 사실만 고정한다 — 애노테이션이 실수로 빠지거나 지워지는 회귀를 잡기 위함이다.
 */
class EgovLoginScrinImageControllerAdminCheckTest {

	private static Method method(String name, Class<?>... paramTypes) throws NoSuchMethodException {
		return EgovLoginScrinImageController.class.getDeclaredMethod(name, paramTypes);
	}

	@Test
	void selectLoginScrinImageRequiresAdmin() throws Exception {
		Method m = method("selectLoginScrinImage", String.class,
				LoginScrinImageVO.class, ModelMap.class);
		assertTrue(m.isAnnotationPresent(RequireAdmin.class),
				"로그인화면이미지 수정화면 진입은 관리자만 가능해야 한다.");
	}

	@Test
	void insertLoginScrinImageRequiresAdmin() throws Exception {
		Method m = method("insertLoginScrinImage", MultipartHttpServletRequest.class,
				LoginScrinImageVO.class, BindingResult.class, SessionStatus.class, ModelMap.class);
		assertTrue(m.isAnnotationPresent(RequireAdmin.class),
				"로그인화면이미지 등록은 관리자만 가능해야 한다.");
	}

	@Test
	void updateLoginScrinImageRequiresAdmin() throws Exception {
		Method m = method("updateLoginScrinImage", MultipartHttpServletRequest.class,
				LoginScrinImageVO.class, BindingResult.class, SessionStatus.class, ModelMap.class);
		assertTrue(m.isAnnotationPresent(RequireAdmin.class),
				"로그인화면이미지 수정은 관리자만 가능해야 한다.");
	}

	@Test
	void deleteLoginScrinImageRequiresAdmin() throws Exception {
		Method m = method("deleteLoginScrinImage", String.class,
				LoginScrinImageVO.class, SessionStatus.class, ModelMap.class);
		assertTrue(m.isAnnotationPresent(RequireAdmin.class),
				"로그인화면이미지 삭제는 관리자만 가능해야 한다.");
	}

	@Test
	void deleteLoginScrinImageListRequiresAdmin() throws Exception {
		Method m = method("deleteLoginScrinImageList", String.class,
				LoginScrinImageVO.class, SessionStatus.class, ModelMap.class);
		assertTrue(m.isAnnotationPresent(RequireAdmin.class),
				"로그인화면이미지 일괄삭제는 관리자만 가능해야 한다.");
	}
}
