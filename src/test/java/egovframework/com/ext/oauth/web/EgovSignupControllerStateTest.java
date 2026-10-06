package egovframework.com.ext.oauth.web;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;

import jakarta.servlet.http.HttpSession;

/**
 * {@link EgovSignupController} 의 OAuth state(CSRF) 검증을 확인한다.
 *
 * <p>이전 구현은 인가 URL의 state를 상수("test")로 두거나 아예 부착하지 않았고, 콜백에서
 * state를 검증하지 않아 로그인 CSRF가 가능했다. 본 테스트는 콜백이 세션에 보관된 state와
 * 일치하지 않으면 사용자 프로필 조회로 진행하지 않고 차단함을 검증한다. 차단 경로는 외부
 * OAuth 서버 호출 이전에 동작하므로 네트워크 없이 결정적으로 검증된다.</p>
 */
class EgovSignupControllerStateTest {

    private static final String RESULT_VIEW = "egovframework/com/uat/uia/EgovLoginUsrOauthResult";

    /** getAttribute/setAttribute/removeAttribute만 Map으로 처리하는 HttpSession 페이크. */
    private static HttpSession fakeSession(Map<String, Object> store) {
        InvocationHandler handler = (proxy, method, args) -> {
            switch (method.getName()) {
                case "getAttribute":
                    return store.get((String) args[0]);
                case "setAttribute":
                    store.put((String) args[0], args[1]);
                    return null;
                case "removeAttribute":
                    store.remove((String) args[0]);
                    return null;
                default:
                    Class<?> rt = method.getReturnType();
                    if (rt.equals(boolean.class)) {
                        return false;
                    }
                    return rt.isPrimitive() ? 0 : null;
            }
        };
        return (HttpSession) Proxy.newProxyInstance(
                HttpSession.class.getClassLoader(), new Class<?>[] { HttpSession.class }, handler);
    }

    @Test
    @DisplayName("콜백 state가 세션 state와 다르면 차단한다")
    void callbackWithMismatchedStateIsBlocked() throws Exception {
        EgovSignupController controller = new EgovSignupController();
        Model model = new ExtendedModelMap();
        Map<String, Object> store = new HashMap<>();
        store.put("oauthState", "expected-state");

        // 외부 OAuth 호출(getUserProfile)에 도달하기 전에 차단되어야 한다.
        String view = controller.oauthLoginCallback("naver", model, "any-code", "forged-state", fakeSession(store));

        assertEquals(RESULT_VIEW, view);
        assertEquals("Invalid OAuth state.", model.getAttribute("message"));
    }

    @Test
    @DisplayName("콜백에 state가 없으면 차단한다")
    void callbackWithoutStateIsBlocked() throws Exception {
        EgovSignupController controller = new EgovSignupController();
        Model model = new ExtendedModelMap();
        Map<String, Object> store = new HashMap<>();
        store.put("oauthState", "expected-state");

        String view = controller.oauthLoginCallback("naver", model, "any-code", null, fakeSession(store));

        assertEquals(RESULT_VIEW, view);
        assertEquals("Invalid OAuth state.", model.getAttribute("message"));
    }

    @Test
    @DisplayName("생성된 state는 예측 불가능하고 매번 다르다")
    void generatedStateIsUnpredictable() throws Exception {
        Method method = EgovSignupController.class.getDeclaredMethod("generateState");
        method.setAccessible(true);

        String first = (String) method.invoke(null);
        String second = (String) method.invoke(null);

        assertTrue(first != null && !first.isBlank());
        assertNotEquals(first, second);
        assertNotEquals("test", first);
    }
}
