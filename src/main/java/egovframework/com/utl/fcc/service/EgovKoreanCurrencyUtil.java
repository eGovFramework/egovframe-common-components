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

import java.text.DecimalFormat;

/**
 * 숫자 금액을 한글로 표기하는 유틸리티.
 *
 * <p>지출결의서·계약서·세금계산서 등 공문서에서 금액 위·변조 방지를 위해 아라비아 숫자 옆에 한글 금액을
 * 병기하는 한국 행정 관행을 지원한다. 위·변조 방지 표기 관례에 따라 자리 계수가 1이어도 "일"을 표기한다
 * (예: 1,000 → "일천", 10,000 → "일만").</p>
 *
 * @author z3rotig4r
 * @since 2026.07.28
 * @version 1.0
 */
public final class EgovKoreanCurrencyUtil {

	private static final char[] DIGITS = { '영', '일', '이', '삼', '사', '오', '육', '칠', '팔', '구' };
	private static final String[] SMALL_UNITS = { "", "십", "백", "천" };
	private static final String[] BIG_UNITS = { "", "만", "억", "조", "경" };

	private EgovKoreanCurrencyUtil() {
	}

	/**
	 * 금액을 한글 읽기로 변환한다.
	 *
	 * <pre>
	 * toKoreanReading(0)       = "영"
	 * toKoreanReading(15)      = "일십오"
	 * toKoreanReading(1000)    = "일천"
	 * toKoreanReading(5000)    = "오천"
	 * toKoreanReading(1234567) = "일백이십삼만사천오백육십칠"
	 * </pre>
	 *
	 * @param amount 0 이상의 금액
	 * @return 한글 읽기 문자열
	 * @throws IllegalArgumentException 음수인 경우
	 */
	public static String toKoreanReading(long amount) {
		if (amount < 0) {
			throw new IllegalArgumentException("금액은 음수일 수 없습니다: " + amount);
		}
		if (amount == 0) {
			return String.valueOf(DIGITS[0]);
		}

		StringBuilder sb = new StringBuilder();
		int bigUnitIndex = 0;
		long remaining = amount;
		String[] groupReadings = new String[BIG_UNITS.length];

		while (remaining > 0 && bigUnitIndex < BIG_UNITS.length) {
			int group = (int) (remaining % 10000);
			if (group > 0) {
				groupReadings[bigUnitIndex] = readGroup(group) + BIG_UNITS[bigUnitIndex];
			}
			remaining /= 10000;
			bigUnitIndex++;
		}

		for (int i = groupReadings.length - 1; i >= 0; i--) {
			if (groupReadings[i] != null) {
				sb.append(groupReadings[i]);
			}
		}
		return sb.toString();
	}

	/**
	 * 금액을 공문서 표준 한글 금액 표기("일금 …원정")로 변환한다.
	 *
	 * <pre>
	 * toKoreanCurrency(5000)    = "일금 오천원정"
	 * toKoreanCurrency(1500000) = "일금 일백오십만원정"
	 * </pre>
	 *
	 * @param amount 0 이상의 금액
	 * @return "일금 …원정" 형식 문자열
	 */
	public static String toKoreanCurrency(long amount) {
		return "일금 " + toKoreanReading(amount) + "원정";
	}

	/**
	 * 아라비아 숫자와 한글 금액을 병기한다.
	 *
	 * <pre>
	 * toKoreanAmountMixed(1234) = "1,234원(일금 일천이백삼십사원정)"
	 * </pre>
	 *
	 * @param amount 0 이상의 금액
	 * @return "1,234원(일금 …원정)" 형식 문자열
	 */
	public static String toKoreanAmountMixed(long amount) {
		return new DecimalFormat("#,##0").format(amount) + "원(" + toKoreanCurrency(amount) + ")";
	}

	/** 4자리 이하 그룹(1~9999)을 한글로 읽는다. 위·변조 방지 표기라 계수 1도 "일"로 표기한다. */
	private static String readGroup(int group) {
		StringBuilder sb = new StringBuilder();
		for (int pos = 3; pos >= 0; pos--) {
			int unit = (int) Math.pow(10, pos);
			int digit = (group / unit) % 10;
			if (digit != 0) {
				sb.append(DIGITS[digit]).append(SMALL_UNITS[pos]);
			}
		}
		return sb.toString();
	}
}
