package egovframework.com.sym.bat.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.TimeZone;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.quartz.CronExpression;

class BatchSchdulCronExpressionTest {

	private static final ZoneId SEOUL = ZoneId.of("Asia/Seoul");

	@ParameterizedTest(name = "{0}")
	@MethodSource("nextExecutions")
	void 다음_실행일이_실행주기를_따른다(String scenario, BatchSchdul schedule, String after, String expected)
			throws ParseException {
		assertEquals(atSeoul(expected), nextExecution(schedule, after));
	}

	private static Stream<Arguments> nextExecutions() {
		return Stream.of(
				Arguments.of("매일: 실행 시각 전에는 오늘 실행", schedule("01", null),
						"2026-09-09T09:29:59", "2026-09-09T09:30:00"),
				Arguments.of("매일: 실행 시각부터는 다음 날 실행", schedule("01", null),
						"2026-09-09T09:30:00", "2026-09-10T09:30:00"),
				Arguments.of("매주 월·수: 금요일 다음은 월요일", schedule("02", null, "2", "4"),
						"2026-09-11T09:30:00", "2026-09-14T09:30:00"),
				Arguments.of("매주 월·수: 월요일 실행 후에는 수요일", schedule("02", null, "2", "4"),
						"2026-09-14T09:30:00", "2026-09-16T09:30:00"),
				Arguments.of("매월 31일: 31일이 없는 2월은 건너뜀", schedule("03", "20260131"),
						"2026-01-31T09:30:00", "2026-03-31T09:30:00"),
				Arguments.of("매년 2월 29일: 다음 윤년에 실행", schedule("04", "20240229"),
						"2024-02-29T09:30:00", "2028-02-29T09:30:00"),
				Arguments.of("한 번: 지정한 날짜에 실행", schedule("05", "20260909"),
						"2026-09-08T09:30:00", "2026-09-09T09:30:00"));
	}

	@ParameterizedTest
	@ValueSource(strings = { "2026-09-09T09:30:00", "2026-09-10T09:30:00" })
	void 한_번_실행한_일정은_다시_실행되지_않는다(String after) throws ParseException {
		assertNull(nextExecution(schedule("05", "20260909"), after));
	}

	@Test
	void 설정한_초에_실행된다() throws ParseException {
		BatchSchdul schedule = schedule("01", null);
		schedule.setExecutSchdulSecnd("15");

		assertEquals(atSeoul("2026-09-09T09:30:15"), nextExecution(schedule, "2026-09-09T09:30:14"));
	}

	private static BatchSchdul schedule(String cycle, String date, String... weekdays) {
		BatchSchdul schedule = new BatchSchdul();
		schedule.setExecutCycle(cycle);
		schedule.setExecutSchdulDe(date);
		schedule.setExecutSchdulHour("09");
		schedule.setExecutSchdulMnt("30");
		schedule.setExecutSchdulSecnd("00");
		if (weekdays.length > 0) {
			schedule.setExecutSchdulDfkSes(weekdays);
		}
		return schedule;
	}

	private static Date nextExecution(BatchSchdul schedule, String after) throws ParseException {
		CronExpression expression = new CronExpression(schedule.toCronExpression());
		expression.setTimeZone(TimeZone.getTimeZone(SEOUL));
		return expression.getNextValidTimeAfter(atSeoul(after));
	}

	private static Date atSeoul(String dateTime) {
		return Date.from(LocalDateTime.parse(dateTime).atZone(SEOUL).toInstant());
	}
}
