package egovframework.com.cmm.util;

import static org.junit.jupiter.api.Assertions.assertEquals;

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

import org.junit.jupiter.api.Test;

import jakarta.servlet.http.HttpSession;

class EgovDoubleSubmitHelperTest {

	@Test
	void checkAndSaveToken_allowsOnlyOneConcurrentSubmitForSameToken()
			throws InterruptedException, ExecutionException, TimeoutException {
		final String token = "TOKEN";
		Map<String, String> tokenMap = new CoordinatedTokenMap(EgovDoubleSubmitHelper.DEFAULT_TOKEN_KEY);
		tokenMap.put(EgovDoubleSubmitHelper.DEFAULT_TOKEN_KEY, token);
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

		private CoordinatedTokenMap(String tokenKey) {
			this.tokenKey = tokenKey;
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
