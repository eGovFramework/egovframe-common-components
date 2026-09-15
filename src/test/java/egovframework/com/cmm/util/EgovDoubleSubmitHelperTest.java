package egovframework.com.cmm.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.lang.reflect.Proxy;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicBoolean;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import jakarta.servlet.http.HttpSession;

class EgovDoubleSubmitHelperTest {

	@AfterEach
	void resetRequestContext() {
		RequestContextHolder.resetRequestAttributes();
	}

	@Test
	void checkAndSaveToken_acceptsDefaultTokenOnceAndAllowsTheRotatedToken() {
		Map<String, String> tokens = defaultTokens("TOKEN");
		MockHttpSession session = sessionWithTokens(tokens);
		bindRequest(session, "TOKEN");

		assertTrue(EgovDoubleSubmitHelper.checkAndSaveToken());
		String rotatedToken = tokens.get(EgovDoubleSubmitHelper.DEFAULT_TOKEN_KEY);
		assertNotNull(rotatedToken);
		assertFalse(rotatedToken.isEmpty());
		assertNotEquals("TOKEN", rotatedToken);

		bindRequest(session, "TOKEN");
		assertFalse(EgovDoubleSubmitHelper.checkAndSaveToken());
		assertEquals(rotatedToken, tokens.get(EgovDoubleSubmitHelper.DEFAULT_TOKEN_KEY));

		bindRequest(session, rotatedToken);
		assertTrue(EgovDoubleSubmitHelper.checkAndSaveToken());
		assertNotEquals(rotatedToken, tokens.get(EgovDoubleSubmitHelper.DEFAULT_TOKEN_KEY));
	}

	@ParameterizedTest
	@ValueSource(strings = { "WRONG", "" })
	void checkAndSaveToken_preservesValidTokenAfterAnInvalidSubmission(String invalidToken) {
		Map<String, String> tokens = defaultTokens("TOKEN");
		MockHttpSession session = sessionWithTokens(tokens);
		bindRequest(session, invalidToken);

		assertFalse(EgovDoubleSubmitHelper.checkAndSaveToken());
		assertEquals(defaultTokens("TOKEN"), tokens);

		bindRequest(session, "TOKEN");
		assertTrue(EgovDoubleSubmitHelper.checkAndSaveToken());
	}

	@Test
	void checkAndSaveToken_keepsFormKeysIndependent() {
		Map<String, String> tokens = new HashMap<>(Map.of("FORM_A", "TOKEN", "FORM_B", "TOKEN"));
		MockHttpSession session = sessionWithTokens(tokens);
		bindRequest(session, "TOKEN");

		assertTrue(EgovDoubleSubmitHelper.checkAndSaveToken("FORM_A"));
		String firstFormToken = tokens.get("FORM_A");
		assertNotEquals("TOKEN", firstFormToken);
		assertEquals("TOKEN", tokens.get("FORM_B"));

		bindRequest(session, "TOKEN");
		assertTrue(EgovDoubleSubmitHelper.checkAndSaveToken("FORM_B"));
		assertNotEquals("TOKEN", tokens.get("FORM_B"));
		assertEquals(firstFormToken, tokens.get("FORM_A"));
		assertEquals(2, tokens.size());
	}

	@Test
	void checkAndSaveToken_keepsSessionsIndependentEvenWithTheSameToken() {
		Map<String, String> firstTokens = defaultTokens("TOKEN");
		Map<String, String> secondTokens = defaultTokens("TOKEN");
		MockHttpSession firstSession = sessionWithTokens(firstTokens);
		MockHttpSession secondSession = sessionWithTokens(secondTokens);
		bindRequest(firstSession, "TOKEN");

		assertTrue(EgovDoubleSubmitHelper.checkAndSaveToken());
		String rotatedFirstToken = firstTokens.get(EgovDoubleSubmitHelper.DEFAULT_TOKEN_KEY);
		assertEquals(defaultTokens("TOKEN"), secondTokens);

		bindRequest(secondSession, "TOKEN");
		assertTrue(EgovDoubleSubmitHelper.checkAndSaveToken());
		assertEquals(rotatedFirstToken, firstTokens.get(EgovDoubleSubmitHelper.DEFAULT_TOKEN_KEY));
	}

	@Test
	void checkAndSaveToken_rejectsAnotherSessionsTokenWithoutConsumingEitherToken() {
		Map<String, String> firstTokens = defaultTokens("FIRST_TOKEN");
		Map<String, String> secondTokens = defaultTokens("SECOND_TOKEN");
		MockHttpSession firstSession = sessionWithTokens(firstTokens);
		MockHttpSession secondSession = sessionWithTokens(secondTokens);
		bindRequest(secondSession, "FIRST_TOKEN");

		assertFalse(EgovDoubleSubmitHelper.checkAndSaveToken());
		assertEquals(defaultTokens("FIRST_TOKEN"), firstTokens);
		assertEquals(defaultTokens("SECOND_TOKEN"), secondTokens);

		bindRequest(firstSession, "FIRST_TOKEN");
		assertTrue(EgovDoubleSubmitHelper.checkAndSaveToken());
		bindRequest(secondSession, "SECOND_TOKEN");
		assertTrue(EgovDoubleSubmitHelper.checkAndSaveToken());
	}

	@Test
	void checkAndSaveToken_rejectsUnregisteredFormKeyWithoutChangingStoredTokens() {
		Map<String, String> tokens = defaultTokens("TOKEN");
		MockHttpSession session = sessionWithTokens(tokens);
		bindRequest(session, "TOKEN");

		assertFalse(EgovDoubleSubmitHelper.checkAndSaveToken("UNKNOWN"));
		assertEquals(defaultTokens("TOKEN"), tokens);
		assertTrue(EgovDoubleSubmitHelper.checkAndSaveToken());
	}

	@Test
	void checkAndSaveToken_reportsMissingSessionTokenConfiguration() {
		MockHttpSession session = new MockHttpSession();
		bindRequest(session, "TOKEN");

		assertThrowsExactly(RuntimeException.class, EgovDoubleSubmitHelper::checkAndSaveToken);
		assertNull(session.getAttribute(EgovDoubleSubmitHelper.SESSION_TOKEN_KEY));
	}

	@Test
	void checkAndSaveToken_preservesStoredTokenWhenRequestParameterIsMissing() {
		Map<String, String> tokens = defaultTokens("TOKEN");
		MockHttpSession session = sessionWithTokens(tokens);
		bindRequest(session, null);

		assertThrowsExactly(RuntimeException.class, EgovDoubleSubmitHelper::checkAndSaveToken);
		assertEquals(defaultTokens("TOKEN"), tokens);

		bindRequest(session, "TOKEN");
		assertTrue(EgovDoubleSubmitHelper.checkAndSaveToken());
	}

	@Test
	void checkAndSaveToken_allowsOnlyOneConcurrentSubmitForSameToken()
			throws InterruptedException, ExecutionException, TimeoutException {
		final String token = "TOKEN";
		Map<String, String> tokenMap = new CoordinatedTokenMap(EgovDoubleSubmitHelper.DEFAULT_TOKEN_KEY, token);
		HttpSession session = fakeSessionWithTokenMap(tokenMap);

		ExecutorService pool = Executors.newFixedThreadPool(2);
		CountDownLatch start = new CountDownLatch(1);
		try {
			Future<Boolean> first = pool.submit(() -> checkTokenAfterStart(session, token, start));
			Future<Boolean> second = pool.submit(() -> checkTokenAfterStart(session, token, start));

			start.countDown();

			int successCount = (first.get(5, TimeUnit.SECONDS) ? 1 : 0)
					+ (second.get(5, TimeUnit.SECONDS) ? 1 : 0);

			assertEquals(1, successCount, "same token must be accepted only once under concurrent submit");
		} finally {
			pool.shutdownNow();
		}
	}

	private static Map<String, String> defaultTokens(String token) {
		return new HashMap<>(Map.of(EgovDoubleSubmitHelper.DEFAULT_TOKEN_KEY, token));
	}

	private static MockHttpSession sessionWithTokens(Map<String, String> tokens) {
		MockHttpSession session = new MockHttpSession();
		session.setAttribute(EgovDoubleSubmitHelper.SESSION_TOKEN_KEY, tokens);
		return session;
	}

	private static void bindRequest(HttpSession session, String token) {
		MockHttpServletRequest request = new MockHttpServletRequest();
		request.setMethod("POST");
		request.setSession(session);
		if (token != null) {
			request.setParameter(EgovDoubleSubmitHelper.PARAMETER_NAME, token);
		}
		RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
	}

	private static boolean checkTokenAfterStart(HttpSession session, String token, CountDownLatch start)
			throws InterruptedException {
		start.await();

		return EgovDoubleSubmitHelper.checkAndSaveToken(session, EgovDoubleSubmitHelper.DEFAULT_TOKEN_KEY, token);
	}

	private static HttpSession fakeSessionWithTokenMap(Map<String, String> tokenMap) {
		Map<String, Object> attributes = new ConcurrentHashMap<>();
		attributes.put(EgovDoubleSubmitHelper.SESSION_TOKEN_KEY, tokenMap);

		return (HttpSession) Proxy.newProxyInstance(
				EgovDoubleSubmitHelperTest.class.getClassLoader(),
				new Class<?>[] { HttpSession.class },
				(proxy, method, args) -> {
					String name = method.getName();
					if ("getAttribute".equals(name)) {
						return attributes.get(args[0]);
					}
					if ("setAttribute".equals(name)) {
						attributes.put((String) args[0], args[1]);
						return null;
					}
					if ("removeAttribute".equals(name)) {
						attributes.remove(args[0]);
						return null;
					}
					if ("getAttributeNames".equals(name)) {
						return Collections.enumeration(attributes.keySet());
					}
					if ("invalidate".equals(name)) {
						attributes.clear();
						return null;
					}
					if ("equals".equals(name)) {
						return proxy == args[0];
					}
					if ("hashCode".equals(name)) {
						return System.identityHashCode(proxy);
					}
					if ("toString".equals(name)) {
						return "FakeHttpSession";
					}

					Class<?> returnType = method.getReturnType();
					if (returnType == boolean.class) {
						return false;
					}
					if (returnType == int.class) {
						return 0;
					}
					if (returnType == long.class) {
						return 0L;
					}
					return null;
				});
	}

	private static final class CoordinatedTokenMap extends HashMap<String, String> {
		private static final long serialVersionUID = 1L;

		private final String tokenKey;
		private final CountDownLatch readAttempts = new CountDownLatch(2);
		private final AtomicBoolean coordinateReads = new AtomicBoolean(true);

		private CoordinatedTokenMap(String tokenKey, String token) {
			this.tokenKey = tokenKey;
			super.put(tokenKey, token);
		}

		@Override
		public String get(Object key) {
			String value = super.get(key);
			if (coordinateReads.get() && tokenKey.equals(key)) {
				readAttempts.countDown();
				try {
					if (!readAttempts.await(200, TimeUnit.MILLISECONDS)) {
						coordinateReads.set(false);
					}
				} catch (InterruptedException e) {
					Thread.currentThread().interrupt();
				}
			}
			return value;
		}

		@Override
		public String put(String key, String value) {
			coordinateReads.set(false);
			return super.put(key, value);
		}
	}
}
