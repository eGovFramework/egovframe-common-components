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
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

/**
 * {@link EgovHangulSearchUtil}의 초성 추출·초성 검색을 검증한다.
 */
class EgovHangulSearchUtilTest {

	@Test
	void extractChosung_basic() {
		assertEquals("ㅅㅅㅈㅈ", EgovHangulSearchUtil.extractChosung("삼성전자"));
		assertEquals("ㄱㄴㄷ", EgovHangulSearchUtil.extractChosung("가나다"));
		assertEquals("ㅎ", EgovHangulSearchUtil.extractChosung("힣"));
		assertEquals("ㄱ", EgovHangulSearchUtil.extractChosung("가"));
	}

	@Test
	void extractChosung_keepsNonHangul() {
		assertEquals("Aㄷ 101ㅎ", EgovHangulSearchUtil.extractChosung("A동 101호"));
		assertEquals("", EgovHangulSearchUtil.extractChosung(null));
		assertEquals("hello", EgovHangulSearchUtil.extractChosung("hello"));
	}

	@Test
	void matchesChosung_search() {
		assertTrue(EgovHangulSearchUtil.matchesChosung("삼성전자", "ㅅㅅ"));
		assertTrue(EgovHangulSearchUtil.matchesChosung("삼성전자", "ㅈㅈ"));
		assertTrue(EgovHangulSearchUtil.matchesChosung("삼성전자", "ㅅㅈ"), "성전 = ㅅㅈ 연속 매칭");
		assertFalse(EgovHangulSearchUtil.matchesChosung("삼성전자", "ㅈㅅ"), "역순 초성은 불일치");
		assertTrue(EgovHangulSearchUtil.matchesChosung("행정안전부", "ㅎㅈ"));
		assertFalse(EgovHangulSearchUtil.matchesChosung("행정안전부", "ㅁㅁ"));
	}

	@Test
	void matchesChosung_edgeCases() {
		assertTrue(EgovHangulSearchUtil.matchesChosung("무엇이든", ""), "빈 질의는 매칭");
		assertFalse(EgovHangulSearchUtil.matchesChosung(null, "ㅅ"), "null 텍스트는 불일치");
	}

	@Test
	void matchesChosung_normalizesSyllableQuery() {
		// 질의에 완성형 음절이 섞이면 초성으로 정규화하여 비교한다.
		assertTrue(EgovHangulSearchUtil.matchesChosung("삼성전자", "삼ㅅ"), "'삼'→'ㅅ'로 정규화되어 'ㅅㅅ' 매칭");
	}

	@Test
	void isHangulSyllable() {
		assertTrue(EgovHangulSearchUtil.isHangulSyllable('가'));
		assertTrue(EgovHangulSearchUtil.isHangulSyllable('힣'));
		assertFalse(EgovHangulSearchUtil.isHangulSyllable('ㄱ'), "호환 자모는 음절이 아니다");
		assertFalse(EgovHangulSearchUtil.isHangulSyllable('A'));
	}
}
