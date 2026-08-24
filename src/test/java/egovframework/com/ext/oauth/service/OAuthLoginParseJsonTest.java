package egovframework.com.ext.oauth.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

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
 *
 * <p>반면 사용자 식별자는 선택 동의 항목이 아니다. {@code getUserProfile}은 제공자의 오류 응답도
 * {@code parseJson}에 전달하므로, 식별자가 없는 응답이 사용자 객체로 반환되지 않는지도 함께 검증한다.</p>
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

	@Test
	@DisplayName("구글 오류 응답처럼 sub가 없으면 사용자 객체를 반환하지 않는다")
	void googleWithoutUserId() {
		String body = "{\"error\":\"invalid_token\",\"error_description\":\"Invalid Credentials\"}";

		assertThrows(IllegalStateException.class, () -> invokeParseJson("google", body),
				"식별자가 없는 응답은 프로필로 취급하지 않는다");
	}

	@Test
	@DisplayName("네이버 오류 응답처럼 response.id가 없으면 사용자 객체를 반환하지 않는다")
	void naverWithoutUserId() {
		String body = "{\"resultcode\":\"024\",\"message\":\"Authentication failed\"}";

		assertThrows(IllegalStateException.class, () -> invokeParseJson("naver", body),
				"식별자가 없는 응답은 프로필로 취급하지 않는다");
	}

	@Test
	@DisplayName("카카오 오류 응답처럼 id가 없으면 사용자 객체를 반환하지 않는다")
	void kakaoWithoutUserId() {
		String body = "{\"msg\":\"this access token does not exist\",\"code\":-401}";

		assertThrows(IllegalStateException.class, () -> invokeParseJson("kakao", body),
				"식별자가 없는 응답은 프로필로 취급하지 않는다");
	}
}
