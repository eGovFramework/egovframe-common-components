package egovframework.com.utl.fcc.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * EgovDateUtil.convertDate(String, String, String, String) 포맷 인자 처리 검증.
 *
 * <p>변경 전: fromDateFormat/toDateFormat이 비어 있을 때만 기본 포맷을 대입하고,
 * 값이 채워진 경우(else)에 사용자 지정 포맷을 대입하는 분기가 누락되어 있었다.
 * 그 결과 호출자가 명시적 포맷을 넘기면 내부 포맷 변수가 빈 문자열로 남아
 * {@code new SimpleDateFormat("")}로 파싱·포매팅되어 결과가 비거나 의도와 달랐다.
 * 순수 static 유틸이므로 Spring 컨텍스트·DB 없이 결정적으로 검증한다.</p>
 */
public class EgovDateUtilConvertDateTest {

	@Test
	public void usesExplicitFromAndToFormats() {
		// 변경 전: 두 포맷이 빈 문자열로 남아 SimpleDateFormat("")로 처리 → ""
		String result = EgovDateUtil.convertDate("20240115", "yyyyMMdd", "yyyy-MM-dd", "");

		assertEquals("2024-01-15", result, "명시한 from/to 포맷이 적용되어야 한다");
	}

	@Test
	public void convertsBetweenDifferentExplicitFormats() {
		String result = EgovDateUtil.convertDate("2024/01/15", "yyyy/MM/dd", "yyyyMMdd", "");

		assertEquals("20240115", result);
	}

	@Test
	public void emptyFormatsStillFallBackToDefaults() {
		// 회귀 가드: 빈 포맷은 종전대로 기본 포맷(yyyyMMddHHmmss → yyyy-MM-dd HH:mm:ss) 사용
		String result = EgovDateUtil.convertDate("20240115093000", "", "", "");

		assertEquals("2024-01-15 09:30:00", result);
	}

	@Test
	public void emptySourceReturnsEmpty() {
		assertEquals("", EgovDateUtil.convertDate("", "yyyyMMdd", "yyyy-MM-dd", ""));
	}
}
