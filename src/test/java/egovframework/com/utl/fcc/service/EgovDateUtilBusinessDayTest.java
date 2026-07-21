package egovframework.com.utl.fcc.service;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

public class EgovDateUtilBusinessDayTest {

	@Test
	void testWeekendOnly() {
		Set<String> holidays = new HashSet<>();

		assertEquals(5, EgovDateUtil.getBusinessDaysBetween("20250812", "20250818", holidays),
				"화요일부터 다음 월요일까지는 주말을 제외하면 5영업일이어야 한다");
		assertEquals(5, EgovDateUtil.getBusinessDaysBetween("20250812", "20250818", null),
				"공휴일 집합이 null이면 주말만 제외한 5영업일이어야 한다");
	}

	@Test
	void testLiberationDay() {
		assertEquals(4, EgovDateUtil.getBusinessDaysBetween("20250812", "20250818", Set.of("20250815")),
				"주말과 금요일인 광복절을 제외하면 4영업일이어야 한다");
	}

	@Test
	void testHolidayOnWeekendIsNotDeductedTwice() {
		assertEquals(5, EgovDateUtil.getBusinessDaysBetween("20250811", "20250817", Set.of("20250816")),
				"토요일 공휴일은 주말 제외와 중복 차감되지 않아야 한다");
	}

	@Test
	void testYearBoundary() {
		assertEquals(3, EgovDateUtil.getBusinessDaysBetween("20251231", "20260102", new HashSet<>()),
				"연말 수요일부터 새해 금요일까지 세 날짜가 모두 평일이므로 3영업일이어야 한다");
	}

	@Test
	void testHyphenatedDateFormat() {
		assertEquals(4,
				EgovDateUtil.getBusinessDaysBetween("2025-08-12", "2025-08-18", Set.of("20250815")),
				"yyyy-MM-dd 형식도 허용하고 주말과 광복절을 제외한 4영업일이어야 한다");
	}

	@Test
	void testSameDate() {
		Set<String> holidays = new HashSet<>();

		assertEquals(1, EgovDateUtil.getBusinessDaysBetween("20250812", "20250812", holidays),
				"시작일과 종료일이 같은 평일이면 1영업일이어야 한다");
		assertEquals(0, EgovDateUtil.getBusinessDaysBetween("20250816", "20250816", holidays),
				"시작일과 종료일이 같은 토요일이면 0영업일이어야 한다");
		assertEquals(0, EgovDateUtil.getBusinessDaysBetween("20250815", "20250815", Set.of("20250815")),
				"시작일과 종료일이 같은 공휴일이면 0영업일이어야 한다");
	}

	@Test
	void testFromDateAfterToDate() {
		assertEquals(0, EgovDateUtil.getBusinessDaysBetween("20250818", "20250812", new HashSet<>()),
				"시작일이 종료일보다 늦으면 0영업일이어야 한다");
	}

	@Test
	void testInvalidCalendarDateRejected() {
		assertThrows(IllegalArgumentException.class,
				() -> EgovDateUtil.getBusinessDaysBetween("20260230", "20260305", new HashSet<>()),
				"존재하지 않는 날짜(2월 30일)는 조용히 롤오버되지 않고 예외여야 한다");
		assertThrows(IllegalArgumentException.class,
				() -> EgovDateUtil.getBusinessDaysBetween("20260101", "20260229", new HashSet<>()),
				"윤년이 아닌 해의 2월 29일은 예외여야 한다");
		assertThrows(IllegalArgumentException.class,
				() -> EgovDateUtil.getBusinessDaysBetween("20261301", "20261305", new HashSet<>()),
				"13월 같은 존재하지 않는 월은 예외여야 한다");
	}
}
