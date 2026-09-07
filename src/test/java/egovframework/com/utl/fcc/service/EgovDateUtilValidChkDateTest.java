/*
 * Copyright The eGovFrame Open Community (http://open.egovframe.go.kr)).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package egovframework.com.utl.fcc.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * {@link EgovDateUtil#validChkDate(String)}가 자매 메서드 validChkTime과 같은 기준으로
 * 입력을 다루는지 검증한다.
 *
 * <p>이전 구현은 검사에는 {@code trim()}한 길이를, 정규화 판단에는 원본 길이를 써서
 * {@code " 2026-01-01"} 같은 입력이 검사를 통과한 뒤 공백과 하이픈이 남은 채 반환됐다.
 * 또 길이만 확인해 {@code "20260101234"} 같은 값도 통과했다.</p>
 */
class EgovDateUtilValidChkDateTest {

	@Test
	@DisplayName("하이픈이 있는 날짜는 8자리로 정규화된다")
	void hyphenSeparatedDateIsNormalized() {
		assertEquals("20260101", EgovDateUtil.validChkDate("2026-01-01"));
	}

	@Test
	@DisplayName("8자리 날짜는 그대로 반환된다")
	void plainDateIsReturnedAsIs() {
		assertEquals("20260101", EgovDateUtil.validChkDate("20260101"));
	}

	@Test
	@DisplayName("앞뒤 공백이 있어도 8자리로 정규화된다")
	void surroundingWhitespaceIsNormalized() {
		assertEquals("20260101", EgovDateUtil.validChkDate(" 2026-01-01"));
		assertEquals("20260101", EgovDateUtil.validChkDate("2026-01-01 "));
		assertEquals("20260101", EgovDateUtil.validChkDate("  20260101  "));
	}

	@Test
	@DisplayName("길이만 맞고 날짜 형식이 아닌 값은 거부한다")
	void lengthMatchesButFormatDoesNotThrows() {
		assertThrows(IllegalArgumentException.class, () -> EgovDateUtil.validChkDate("2026/01/01"));
		assertThrows(IllegalArgumentException.class, () -> EgovDateUtil.validChkDate("2026010112"));
		assertThrows(IllegalArgumentException.class, () -> EgovDateUtil.validChkDate("abcd-ef-gh"));
	}

	@Test
	@DisplayName("하이픈이 한쪽에만 있는 값은 거부한다")
	void partialHyphenIsRejected() {
		// 두 하이픈이 함께 있거나 모두 없어야 한다. 한쪽만 있으면 yyyyMMdd 반환이 보장되지 않는다.
		assertThrows(IllegalArgumentException.class, () -> EgovDateUtil.validChkDate("2026-0907"));
		assertThrows(IllegalArgumentException.class, () -> EgovDateUtil.validChkDate("202609-07"));
	}

	@Test
	@DisplayName("null과 길이가 다른 입력은 거부한다")
	void nullOrWrongLengthThrows() {
		assertThrows(IllegalArgumentException.class, () -> EgovDateUtil.validChkDate(null));
		assertThrows(IllegalArgumentException.class, () -> EgovDateUtil.validChkDate("2026010"));
		assertThrows(IllegalArgumentException.class, () -> EgovDateUtil.validChkDate(""));
	}

	@Test
	@DisplayName("범위 검증은 정규화된 값을 기준으로 판단한다")
	void validDateComparesNormalizedValue() {
		// 이전에는 원본 문자열과 비교해 하이픈이 있는 입력이 항상 false였다.
		assertTrue(EgovDateUtil.validDate("2026-01-01"));
		assertTrue(EgovDateUtil.validDate("20260101"));
		assertTrue(EgovDateUtil.validDate(" 2026-01-01 "));
	}

	@Test
	@DisplayName("존재하지 않는 날짜는 범위 검증에서 거부한다")
	void validDateRejectsNonExistentDate() {
		assertFalse(EgovDateUtil.validDate("2026-02-30"));
		assertFalse(EgovDateUtil.validDate("20261301"));
	}
}
