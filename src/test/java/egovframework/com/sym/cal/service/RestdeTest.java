package egovframework.com.sym.cal.service;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class RestdeTest {

	@Test
	public void test_getFormattedRestdeDe_validDate() {
		// Given
		Restde restde = new Restde();
		restde.setRestdeDe("20240831"); // 올바른 8자리 날짜 입력

		// When
		String formattedDate = restde.getFormattedRestdeDe();

		// Then
		assertEquals("2024-08-31", formattedDate); // 예상되는 출력
	}

	@Test
	public void test_getFormattedRestdeDe_nullValue() {
		// Given
		Restde restde = new Restde();
		restde.setRestdeDe(null); // null 입력

		// When
		String formattedDate = restde.getFormattedRestdeDe();

		// Then
		assertEquals(null, formattedDate); // null 반환
	}

	@Test
	public void test_getFormattedRestdeDe_emptyValue() {
		// Given
		Restde restde = new Restde();
		restde.setRestdeDe(""); // 빈 문자열 입력

		// When
		String formattedDate = restde.getFormattedRestdeDe();

		// Then
		assertEquals("", formattedDate); // 빈 문자열 반환
	}

	@Test
	public void test_getFormattedRestdeDe_invalidLength() {
		// Given
		Restde restde = new Restde();
		restde.setRestdeDe("202408"); // 잘못된 길이의 입력

		// When
		String formattedDate = restde.getFormattedRestdeDe();

		// Then
		assertEquals("202408", formattedDate); // 원래 문자열 반환
	}

	@Test
	public void test_getFormattedRestdeDe_nonNumericValue() {
		// Given
		Restde restde = new Restde();
		restde.setRestdeDe("abcd1234"); // 숫자가 아닌 문자가 포함된 입력

		// When
		String formattedDate = restde.getFormattedRestdeDe();

		// Then
		assertEquals("abcd1234", formattedDate); // 원래 문자열 반환
	}

	@Test
	public void test_getFormattedRestdeDe_partialNonNumericValue() {
		// Given
		Restde restde = new Restde();
		restde.setRestdeDe("2024abcd"); // 부분적으로 숫자가 아닌 문자가 포함된 입력

		// When
		String formattedDate = restde.getFormattedRestdeDe();

		// Then
		assertEquals("2024abcd", formattedDate); // 원래 문자열 반환
	}

}