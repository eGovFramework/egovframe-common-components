package egovframework.com.utl.sys.pxy.web;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.lang.reflect.Method;

import org.junit.jupiter.api.Test;

import egovframework.com.cmm.annotation.RequireAdmin;
import egovframework.com.utl.sys.pxy.service.ProxyLog;

/**
 * 프록시서비스·프록시로그 목록화면 진입에 @RequireAdmin이 붙어있는지 검증.
 *
 * <p>같은 컨트롤러의 목록·상세·등록·수정·삭제는 모두 @RequireAdmin으로 막혀 있는데
 * 목록화면 진입 둘만 빠져 있었다. 같은 utl/sys 네임스페이스의 서버자원모니터링은
 * 구조가 같은 selectServerResrceMntrngListView에도 붙여둔다.</p>
 *
 * <p>@RequireAdmin은 Spring AOP(@annotation 포인트컷)로 위빙되므로 컨트롤러를 직접
 * 생성해 호출하는 단위 테스트로는 차단 동작을 재현할 수 없다. 이 테스트는 애노테이션이
 * 실제로 붙어있는지만 고정한다.</p>
 */
public class EgovProxySvcControllerAdminCheckTest {

	@Test
	public void proxySvcListViewRequiresAdmin() throws Exception {
		Method method = EgovProxySvcController.class.getDeclaredMethod("selectProxySvcListView");
		assertTrue(method.isAnnotationPresent(RequireAdmin.class),
				"프록시서비스 목록화면은 관리자만 열 수 있어야 한다.");
	}

	@Test
	public void proxyLogListViewRequiresAdmin() throws Exception {
		Method method = EgovProxySvcController.class.getDeclaredMethod("selectProxyLogListView",
				ProxyLog.class, org.springframework.ui.Model.class);
		assertTrue(method.isAnnotationPresent(RequireAdmin.class),
				"프록시로그 목록화면은 관리자만 열 수 있어야 한다.");
	}
}
