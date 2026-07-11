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
		// 정상 입력: 지원되는 요일 코드 -> 국문 요일명
		//   THR: 기존 지원 값(하위호환) / THU: 이번 수정으로 추가된 표준 3글자 대문자 약어
		//   ※ addYMDtoWeek() 의 실제 출력값인 "Thu"(첫 글자만 대문자)는 아직 미지원 —
		//     sWeek.equals("THU") 는 대소문자를 구분하므로 "Thu" 입력 시 여전히 null 반환됨
		// -------------------------------------------------------------------

		@ParameterizedTest(name = "{0} -> {1}")
		@DisplayName("지원되는 요일 코드 -> 국문 요일명")
		@CsvSource({
			"SUN, 일요일",
			"MON, 월요일",
			"TUE, 화요일",
			"WED, 수요일",
			"THR, 목요일",
			"THU, 목요일",
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
}
