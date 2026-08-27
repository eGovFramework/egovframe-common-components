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

/**
 * 한글 초성 추출 및 초성 검색 유틸리티.
 *
 * <p>한글 음절(가~힣)의 초성을 유니코드 산술로 추출해, "ㅅㅅ"으로 "삼성"을 찾는 초성 검색을 제공한다.
 * 게시판·행정용어·기관명 등 한국어 목록의 빠른 필터링에 사용한다. 초성은 키보드 입력과 동일한
 * 호환 자모(U+3131~)로 산출한다.</p>
 *
 * @author z3rotig4r
 * @since 2026.07.28
 * @version 1.0
 */
public final class EgovHangulSearchUtil {

	private static final char HANGUL_BASE = 0xAC00; // '가'
	private static final char HANGUL_LAST = 0xD7A3; // '힣'
	private static final int JUNGSUNG_COUNT = 21;
	private static final int JONGSUNG_COUNT = 28;
	private static final int SYLLABLE_BLOCK = JUNGSUNG_COUNT * JONGSUNG_COUNT; // 588

	/** 초성 19자(호환 자모). */
	private static final char[] CHOSUNG = { 'ㄱ', 'ㄲ', 'ㄴ', 'ㄷ', 'ㄸ', 'ㄹ', 'ㅁ', 'ㅂ', 'ㅃ', 'ㅅ', 'ㅆ', 'ㅇ', 'ㅈ', 'ㅉ',
			'ㅊ', 'ㅋ', 'ㅌ', 'ㅍ', 'ㅎ' };

	private EgovHangulSearchUtil() {
	}

	/**
	 * 문자열의 각 한글 음절을 초성으로 치환한 문자열을 반환한다. 한글 음절이 아닌 문자는 그대로 둔다.
	 *
	 * <pre>
	 * extractChosung("삼성전자")   = "ㅅㅅㅈㅈ"
	 * extractChosung("가나다")     = "ㄱㄴㄷ"
	 * extractChosung("A동 101호")  = "A동 101호" 중 한글만 초성화 → "A동" 유지 규칙에 따름
	 * </pre>
	 *
	 * @param text 원본 문자열
	 * @return 초성 치환 문자열(null 입력은 빈 문자열)
	 */
	public static String extractChosung(String text) {
		if (text == null) {
			return "";
		}
		StringBuilder sb = new StringBuilder(text.length());
		for (int i = 0; i < text.length(); i++) {
			char ch = text.charAt(i);
			if (isHangulSyllable(ch)) {
				sb.append(CHOSUNG[(ch - HANGUL_BASE) / SYLLABLE_BLOCK]);
			} else {
				sb.append(ch);
			}
		}
		return sb.toString();
	}

	/**
	 * 텍스트의 초성열이 초성 질의를 부분 문자열로 포함하는지 확인한다.
	 *
	 * <pre>
	 * matchesChosung("삼성전자", "ㅅㅅ")   = true  (삼성)
	 * matchesChosung("삼성전자", "ㅅㅈ")   = true  (성전 — 초성열의 연속 부분)
	 * matchesChosung("삼성전자", "ㅈㅅ")   = false (역순 초성은 불일치)
	 * </pre>
	 *
	 * @param text  대상 텍스트
	 * @param query 초성 질의(예: "ㅅㅅ"). 일반 한글 음절이 섞이면 그 음절의 초성으로 정규화한다.
	 * @return 초성 검색 매칭 여부. query가 비어 있으면 true.
	 */
	public static boolean matchesChosung(String text, String query) {
		if (EgovStringUtil.isEmpty(query)) {
			return true;
		}
		if (text == null) {
			return false;
		}
		return extractChosung(text).contains(normalizeQuery(query));
	}

	/**
	 * 한글 음절 여부.
	 *
	 * @param ch 문자
	 * @return 가~힣 범위이면 true
	 */
	public static boolean isHangulSyllable(char ch) {
		return ch >= HANGUL_BASE && ch <= HANGUL_LAST;
	}

	/** 질의에 완성형 한글 음절이 섞이면 초성으로 바꾼다(호환 자모·기타 문자는 그대로). */
	private static String normalizeQuery(String query) {
		StringBuilder sb = new StringBuilder(query.length());
		for (int i = 0; i < query.length(); i++) {
			char ch = query.charAt(i);
			if (isHangulSyllable(ch)) {
				sb.append(CHOSUNG[(ch - HANGUL_BASE) / SYLLABLE_BLOCK]);
			} else {
				sb.append(ch);
			}
		}
		return sb.toString();
	}
}
