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
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

/**
 * {@link EgovKoreanCurrencyUtil}의 숫자→한글 금액 변환을 검증한다.
 */
class EgovKoreanCurrencyUtilTest {

	@Test
	void toKoreanReading_values() {
		assertEquals("영", EgovKoreanCurrencyUtil.toKoreanReading(0));
		assertEquals("오", EgovKoreanCurrencyUtil.toKoreanReading(5));
		assertEquals("일십오", EgovKoreanCurrencyUtil.toKoreanReading(15));
		assertEquals("일백", EgovKoreanCurrencyUtil.toKoreanReading(100));
		assertEquals("일천", EgovKoreanCurrencyUtil.toKoreanReading(1000));
		assertEquals("오천", EgovKoreanCurrencyUtil.toKoreanReading(5000));
		assertEquals("일만", EgovKoreanCurrencyUtil.toKoreanReading(10000));
		assertEquals("일천이백삼십사", EgovKoreanCurrencyUtil.toKoreanReading(1234));
		assertEquals("일백이십삼만사천오백육십칠", EgovKoreanCurrencyUtil.toKoreanReading(1234567));
	}

	@Test
	void toKoreanReading_largeUnits() {
		assertEquals("일억", EgovKoreanCurrencyUtil.toKoreanReading(100000000L));
		assertEquals("일조", EgovKoreanCurrencyUtil.toKoreanReading(1000000000000L));
		// 큰 단위 사이에 0 그룹은 건너뛴다: 100,000,001 = 일억일
		assertEquals("일억일", EgovKoreanCurrencyUtil.toKoreanReading(100000001L));
	}

	@Test
	void toKoreanCurrency_documentForm() {
		assertEquals("일금 오천원정", EgovKoreanCurrencyUtil.toKoreanCurrency(5000));
		assertEquals("일금 일백오십만원정", EgovKoreanCurrencyUtil.toKoreanCurrency(1500000));
		assertEquals("일금 영원정", EgovKoreanCurrencyUtil.toKoreanCurrency(0));
	}

	@Test
	void toKoreanAmountMixed_form() {
		assertEquals("1,234원(일금 일천이백삼십사원정)", EgovKoreanCurrencyUtil.toKoreanAmountMixed(1234));
	}

	@Test
	void toKoreanReading_negativeThrows() {
		assertThrows(IllegalArgumentException.class, () -> EgovKoreanCurrencyUtil.toKoreanReading(-1));
	}
}
