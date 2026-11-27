package egovframework.com.utl.fcc.service;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class EgovFormatCheckUtilTest {

	@Test
	void testLocalPartWithHyphenIsValid() {
		// 로컬파트에 하이픈(-)이 포함되어도 유효한 이메일로 인정되어야 한다.
		assertTrue(EgovFormatCheckUtil.checkFormatMail("hong-gil", "example.com"), "하이픈 포함 로컬파트");
	}

	@Test
	void testLocalPartWithUnderscoreIsValid() {
		// 로컬파트에 언더스코어(_)가 포함되어도 유효한 이메일로 인정되어야 한다.
		assertTrue(EgovFormatCheckUtil.checkFormatMail("hong_gil", "example.com"), "언더스코어 포함 로컬파트");
	}

	@Test
	void testLocalPartAlphaNumericIsValid() {
		// 영문 대소문자와 숫자로 구성된 로컬파트는 유효해야 한다.
		assertTrue(EgovFormatCheckUtil.checkFormatMail("HongGil123", "example.com"), "영숫자 로컬파트");
	}

	@Test
	void testLocalPartWithDisallowedCharIsInvalid() {
		// 허용되지 않는 문자가 포함된 로컬파트는 거부되어야 한다.
		assertFalse(EgovFormatCheckUtil.checkFormatMail("hong!gil", "example.com"), "느낌표 포함 로컬파트");
		assertFalse(EgovFormatCheckUtil.checkFormatMail("hong gil", "example.com"), "공백 포함 로컬파트");
	}

	@Test
	void testDomainMustContainExactlyOneDot() {
		// 도메인은 점(.)을 정확히 1개 포함하는 xxx.xxx 형태여야 한다.
		assertTrue(EgovFormatCheckUtil.checkFormatMail("honggil", "example.com"), "점 1개 도메인");
		assertFalse(EgovFormatCheckUtil.checkFormatMail("honggil", "examplecom"), "점 없는 도메인");
		assertFalse(EgovFormatCheckUtil.checkFormatMail("honggil", "mail.example.com"), "점 2개 도메인");
	}

	@Test
	void testSingleArgDelegatesToTwoArg() {
		// '@'로 분리되는 단일 인자 메서드도 동일 규칙을 따른다.
		assertTrue(EgovFormatCheckUtil.checkFormatMail("hong-gil@example.com"), "하이픈 포함 전체 이메일");
		assertFalse(EgovFormatCheckUtil.checkFormatMail("hong-gil@@example.com"), "'@' 2개");
	}

	@Test
	void testMailMustNotContainEmptyPart() {
		assertFalse(EgovFormatCheckUtil.checkFormatMail("", "example.com"), "빈 로컬파트");
		assertFalse(EgovFormatCheckUtil.checkFormatMail("honggil", ""), "빈 도메인파트");
		assertFalse(EgovFormatCheckUtil.checkFormatMail("@example.com"), "빈 로컬파트 전체 이메일");
		assertFalse(EgovFormatCheckUtil.checkFormatMail("honggil@"), "빈 도메인파트 전체 이메일");
	}

	@Test
	void testSingleArgRejectsTrailingAtSign() {
		assertFalse(EgovFormatCheckUtil.checkFormatMail("honggil@example.com@"), "끝에 붙은 '@'");
	}

	@Test
	void testNullMailIsInvalid() {
		assertFalse(EgovFormatCheckUtil.checkFormatMail(null), "null 전체 이메일");
		assertFalse(EgovFormatCheckUtil.checkFormatMail(null, "example.com"), "null 로컬파트");
		assertFalse(EgovFormatCheckUtil.checkFormatMail("honggil", null), "null 도메인파트");
	}
}
