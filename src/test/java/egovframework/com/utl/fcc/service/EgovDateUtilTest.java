package egovframework.com.utl.fcc.service;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

/**
 * EgovDateUtil 단위 테스트
 */
public class EgovDateUtilTest {

	@Nested
	@DisplayName("convertWeek()")
	class ConvertWeek {

		// -------------------------------------------------------------------
		// 정상 입력: 지원되는 요일 코드 -> 국문 요일명 (대소문자 구분 없이 인식)
		//   THR: 기존 지원 값(하위호환) / THU, Thu, thu: 대소문자 무관하게 목요일로 인식
		//   addYMDtoWeek() 의 실제 출력값인 "Thu"(첫 글자만 대문자)도 이제 정상 인식됨
		// -------------------------------------------------------------------

		@ParameterizedTest(name = "{0} -> {1}")
		@DisplayName("지원되는 요일 코드 -> 국문 요일명")
		@CsvSource({
			"SUN, 일요일",
			"Sun, 일요일",
			"MON, 월요일",
			"TUE, 화요일",
			"WED, 수요일",
			"THR, 목요일",
			"THU, 목요일",
			"Thu, 목요일",
			"Thu, 목요일",
			"FRI, 금요일",
			"SAT, 토요일"
		})
		void supportedWeekCodes(String input, String expected) {
			assertEquals(expected, EgovDateUtil.convertWeek(input));
		}

		@Test
		@DisplayName("빈 문자열 입력 -> null 반환")
		void emptyStringReturnsNull() {
			assertNull(EgovDateUtil.convertWeek(""));
		}

		@Test
		@DisplayName("존재하지 않는 요일 문자열 -> null 반환")
		void unknownStringReturnsNull() {
			assertNull(EgovDateUtil.convertWeek("XXX"));
		}

	}

	@Nested
	@DisplayName("addYMDtoWeek()")
	class AddYMDtoWeek {


		@ParameterizedTest(name = "{0}일 후 -> {1}")
		@DisplayName("월요일부터 일요일까지 -> 요일 문자열(Mon~Sun)")
		@CsvSource({
			"0, Mon",
			"1, Tue",
			"2, Wed",
			"3, Thu",
			"4, Fri",
			"5, Sat",
			"6, Sun"
		})
		void mondayToSunday(int dayOffset, String expected) {
			assertEquals(expected, EgovDateUtil.addYMDtoWeek("20240101", 0, 0, dayOffset));
		}

	}
}
