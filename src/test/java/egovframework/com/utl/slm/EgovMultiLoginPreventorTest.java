package egovframework.com.utl.slm;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import jakarta.servlet.http.HttpSession;

/**
 * EgovMultiLoginPreventor의 동시성 안전성 검증 테스트.
 *
 * invalidateByLoginId가 맵을 순회한 뒤 get(key)로 재조회하던 기존 구현에서는,
 * 순회와 재조회 사이에 다른 스레드가 valueUnbound(remove)로 엔트리를 제거하면
 * get(key)가 null을 반환하여 .invalidate() 호출 시 NPE가 발생했다.
 */
public class EgovMultiLoginPreventorTest {

	@AfterEach
	void clear() {
		EgovMultiLoginPreventor.loginUsers.clear();
	}

	/**
	 * 무효화 시 맵에서 스스로 제거되는 가짜 세션을 만든다(valueUnbound의 remove를 모사).
	 * onInvalidate가 호출되면 호출 횟수를 기록한다.
	 */
	private static HttpSession fakeSession(String loginId, AtomicInteger invalidateCount) {
		InvocationHandler handler = (proxy, method, args) -> {
			String name = method.getName();
			if ("invalidate".equals(name)) {
				invalidateCount.incrementAndGet();
				// 실제 컨테이너의 valueUnbound 동작(엔트리 제거)을 모사한다.
				EgovMultiLoginPreventor.loginUsers.remove(loginId, proxy);
				return null;
			}
			if ("equals".equals(name)) {
				return proxy == args[0];
			}
			if ("hashCode".equals(name)) {
				return System.identityHashCode(proxy);
			}
			if ("toString".equals(name)) {
				return "FakeSession(" + loginId + ")";
			}
			// 그 외 메서드는 사용하지 않으므로 기본값을 반환한다.
			Class<?> rt = method.getReturnType();
			if (rt == boolean.class) {
				return false;
			}
			if (rt == int.class) {
				return 0;
			}
			return null;
		};
		return (HttpSession) Proxy.newProxyInstance(
				EgovMultiLoginPreventorTest.class.getClassLoader(),
				new Class<?>[] { HttpSession.class },
				handler);
	}

	@Test
	void invalidateByLoginId_정상무효화() {
		String loginId = "user1";
		AtomicInteger count = new AtomicInteger();
		HttpSession session = fakeSession(loginId, count);
		EgovMultiLoginPreventor.loginUsers.put(loginId, session);

		EgovMultiLoginPreventor.invalidateByLoginId(loginId);

		assertTrue(count.get() == 1, "기존 세션이 한 번 무효화되어야 한다");
		assertFalse(EgovMultiLoginPreventor.findByLoginId(loginId), "무효화 후 맵에서 제거되어야 한다");
	}

	@Test
	void invalidateByLoginId_엔트리없으면_NPE없이_무동작() {
		// 존재하지 않는 로그인 아이디로 호출해도 NPE가 발생하지 않아야 한다.
		assertDoesNotThrow(() -> EgovMultiLoginPreventor.invalidateByLoginId("absent"));
	}

	/**
	 * 다수 스레드가 add/remove와 invalidateByLoginId를 동시에 수행해도
	 * NPE가 발생하지 않는지 검증한다(동시 엔트리 제거 → get null 시나리오 재현 시도).
	 */
	@Test
	void invalidateByLoginId_동시제거중_NPE없음() throws InterruptedException {
		final int loginIdCount = 50;
		final int threadCount = 16;
		final int roundsPerThread = 2000;

		ExecutorService pool = Executors.newFixedThreadPool(threadCount);
		final CountDownLatch start = new CountDownLatch(1);
		final CountDownLatch done = new CountDownLatch(threadCount);
		final AtomicBoolean failed = new AtomicBoolean(false);
		final AtomicInteger ignored = new AtomicInteger();

		for (int t = 0; t < threadCount; t++) {
			final int seed = t;
			pool.submit(() -> {
				try {
					start.await();
					for (int i = 0; i < roundsPerThread; i++) {
						String loginId = "user" + ((seed * 31 + i) % loginIdCount);
						// 절반은 등록(put), 절반은 무효화(invalidate)를 시도하여
						// put/remove와 순회/조회가 교차되도록 한다.
						if ((i & 1) == 0) {
							EgovMultiLoginPreventor.loginUsers.put(loginId, fakeSession(loginId, ignored));
						} else {
							EgovMultiLoginPreventor.invalidateByLoginId(loginId);
						}
					}
				} catch (NullPointerException npe) {
					failed.set(true);
				} catch (InterruptedException ie) {
					Thread.currentThread().interrupt();
				} finally {
					done.countDown();
				}
			});
		}

		start.countDown();
		assertTrue(done.await(30, TimeUnit.SECONDS), "테스트가 제한 시간 내에 완료되어야 한다");
		pool.shutdownNow();

		assertFalse(failed.get(), "동시 제거 중 invalidateByLoginId에서 NPE가 발생하면 안 된다");
	}
}
