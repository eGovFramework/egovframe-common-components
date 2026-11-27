package egovframework.com.utl.fcc.service;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class EgovDateUtilLeapYearTest {

	@Test
	void testLeapYears() {
		// 4의 배수이면서 100의 배수가 아닌 해, 또는 400의 배수인 해는 윤년이다.
		assertTrue(EgovDateUtil.isLeapYear(2024), "2024는 윤년");
		assertTrue(EgovDateUtil.isLeapYear(2000), "2000은 400의 배수이므로 윤년");
		assertTrue(EgovDateUtil.isLeapYear(2400), "2400은 400의 배수이므로 윤년");
		assertTrue(EgovDateUtil.isLeapYear(1600), "1600은 400의 배수이므로 윤년");
	}

	@Test
	void testCommonYears() {
		// 위 조건을 만족하지 않는 해는 평년이다.
		assertFalse(EgovDateUtil.isLeapYear(2023), "2023은 평년");
		assertFalse(EgovDateUtil.isLeapYear(2025), "2025는 평년");
		assertFalse(EgovDateUtil.isLeapYear(2100), "2100은 100의 배수이나 400의 배수가 아니므로 평년");
		assertFalse(EgovDateUtil.isLeapYear(1900), "1900은 100의 배수이나 400의 배수가 아니므로 평년");
		assertFalse(EgovDateUtil.isLeapYear(2200), "2200은 100의 배수이나 400의 배수가 아니므로 평년");
	}
}
