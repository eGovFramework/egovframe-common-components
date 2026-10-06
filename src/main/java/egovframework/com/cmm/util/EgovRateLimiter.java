package egovframework.com.cmm.util;

import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Map;

/** JVM 내 요청 횟수 제한. 전달 헤더가 아닌 신뢰할 수 있는 클라이언트 식별자를 사용한다. */
public final class EgovRateLimiter {
    private static final int MAX_KEYS = 10000;
    private static final Map<String, Attempts> ATTEMPTS = new HashMap<>();

    private EgovRateLimiter() { }

    public static boolean allow(String key, int maxAttempts, long windowMillis) {
        return allow(key, maxAttempts, windowMillis, System.currentTimeMillis());
    }

    static synchronized boolean allow(String key, int maxAttempts, long windowMillis, long now) {
        if (key == null || key.isBlank() || maxAttempts <= 0 || windowMillis <= 0) {
            return false;
        }
        Attempts attempts = ATTEMPTS.get(key);
        if (attempts == null) {
            ATTEMPTS.entrySet().removeIf(entry -> entry.getValue().expiresAt <= now);
            if (ATTEMPTS.size() >= MAX_KEYS) {
                return false;
            }
            attempts = new Attempts();
            ATTEMPTS.put(key, attempts);
        }
        while (!attempts.times.isEmpty() && now - attempts.times.peekFirst() >= windowMillis) {
            attempts.times.removeFirst();
        }
        if (attempts.times.size() >= maxAttempts) {
            return false;
        }
        attempts.times.addLast(now);
        attempts.expiresAt = now + windowMillis;
        return true;
    }

    private static final class Attempts {
        private final ArrayDeque<Long> times = new ArrayDeque<>();
        private long expiresAt;
    }
}
