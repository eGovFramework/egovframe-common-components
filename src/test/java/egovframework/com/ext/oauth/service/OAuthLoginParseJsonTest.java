package egovframework.com.ext.oauth.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * {@link OAuthLogin}의 private {@code parseJson(String)}이 OAuth 제공자 프로필 응답에서
 * 선택 동의 항목이 빠졌을 때 발생하던 NPE를 검증한다.
 *
 * <p>네이버는 이메일 제공을, 카카오는 프로필(닉네임) 제공을 사용자가 선택적으로 거부할 수 있어
 * 응답 JSON에서 해당 필드가 통째로 빠질 수 있다. 기존 구현은 {@code get("email")} 등의
 * 반환값을 null 검사 없이 {@code asText()}로 역참조해
 * {@code /auth/{oauthService}/callback} 콜백에서 500 오류가 발생했다.</p>
 */
class OAuthLoginParseJsonTest {

	/** 리플렉션으로 private {@code parseJson}을 호출하고, 내부에서 던진 예외는 원인 그대로 다시 던진다. */
	private OAuthUniversalUser invokeParseJson(String serviceName, String body) throws Throwable {
		OAuthVO oauthVO = new OAuthVO(serviceName, "dummy-client-id", "dummy-secret",
				"http://localhost/callback", "");
		OAuthLogin oauthLogin = new OAuthLogin(oauthVO);

		Method method = OAuthLogin.class.getDeclaredMethod("parseJson", String.class);
		method.setAccessible(true);
		try {
			return (OAuthUniversalUser) method.invoke(oauthLogin, body);
		} catch (InvocationTargetException e) {
			throw e.getCause();
		}
	}

	@Test
	@DisplayName("네이버 이메일 미동의로 email 필드가 없어도 NPE 없이 파싱된다")
	void naverWithoutEmail() throws Throwable {
		String body = "{\"response\":{\"id\":\"nid-123\",\"nickname\":\"홍길동\"}}";

		OAuthUniversalUser user = invokeParseJson("naver", body);

		assertEquals("nid-123", user.getUserId());
		assertEquals("홍길동", user.getNickName());
		assertEquals("", user.getEmail(), "미동의로 빠진 email은 빈 문자열이어야 한다");
	}

	@Test
	@DisplayName("카카오 프로필 미동의로 properties가 없어도 NPE 없이 파싱된다")
	void kakaoWithoutProperties() throws Throwable {
		String body = "{\"id\":98765}";

		OAuthUniversalUser user = invokeParseJson("kakao", body);

		assertEquals("98765", user.getUserId());
		assertEquals("", user.getNickName(), "미동의로 빠진 nickname은 빈 문자열이어야 한다");
	}

	@Test
	@DisplayName("구글 name 미제공 시에도 NPE 없이 파싱된다")
	void googleWithoutName() throws Throwable {
		String body = "{\"sub\":\"g-1\"}";

		OAuthUniversalUser user = invokeParseJson("google", body);

		assertEquals("g-1", user.getUserId());
		assertEquals("", user.getUserName(), "미제공된 name은 빈 문자열이어야 한다");
	}
}
