package egovframework.com.utl.fcc.service;

import static org.junit.jupiter.api.Assertions.*;

import java.time.Duration;

import org.junit.jupiter.api.Test;

public class EgovNumberUtilTest {

	@Test
	void testRandomInRange() {
		// 반복 호출해도 항상 [startNum, endNum] 구간 안의 값을 반환해야 한다.
		for (int i = 0; i < 1000; i++) {
			int r = EgovNumberUtil.getRandomNum(10, 20);
			assertTrue(r >= 10 && r <= 20, "구간 밖 값: " + r);
		}
	}

	@Test
	void testStartEqualsEnd() {
		assertEquals(7, EgovNumberUtil.getRandomNum(7, 7), "단일 값 구간은 그 값을 반환해야 한다.");
	}

	@Test
	void testNegativeRange() {
		for (int i = 0; i < 1000; i++) {
			int r = EgovNumberUtil.getRandomNum(-20, -10);
			assertTrue(r >= -20 && r <= -10, "음수 구간 밖 값: " + r);
		}
	}

	@Test
	void testMaxValueDoesNotOverflow() {
		// 기존 코드는 endNum + 1 오버플로로 IllegalArgumentException이 발생했다.
		assertTimeoutPreemptively(Duration.ofSeconds(2), () -> {
			int r = EgovNumberUtil.getRandomNum(0, Integer.MAX_VALUE);
			assertTrue(r >= 0, "음수 반환: " + r);
		});
	}

	@Test
	void testFullIntRange() {
		// MIN..MAX 전체 구간(구간 크기 2^32)에서도 예외 없이 동작해야 한다.
		assertTimeoutPreemptively(Duration.ofSeconds(2),
				() -> EgovNumberUtil.getRandomNum(Integer.MIN_VALUE, Integer.MAX_VALUE));
	}

	@Test
	void testStartGreaterThanEndThrows() {
		// 기존 코드는 endNum < startNum일 때 무한 루프에 빠졌다. 이제는 즉시 예외를 던진다.
		assertTimeoutPreemptively(Duration.ofSeconds(2), () -> assertThrows(IllegalArgumentException.class,
				() -> EgovNumberUtil.getRandomNum(20, 10)));
	}
}
