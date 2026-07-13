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
 * {@link EgovDateUtil#validChkTime(String)}가 자매 메서드 validChkDate처럼
 * 콜론이 포함된 "HH:mm"(길이 5) 입력을 정규화하는지 검증한다.
 * (기존에는 길이 4만 허용해 콜론 제거 분기가 도달 불가능한 dead code였다)
 */
class EgovDateUtilValidChkTimeTest {

	@Test
	void validChkTime_colonSeparatedTime_isNormalized() {
		assertEquals("1230", EgovDateUtil.validChkTime("12:30"),
				"\"HH:mm\" 입력은 콜론을 제거해 \"HHmm\"으로 정규화되어야 한다");
		assertEquals("0905", EgovDateUtil.validChkTime("09:05"),
				"앞자리 0이 있는 시각도 정규화되어야 한다");
	}

	@Test
	void validChkTime_plainTime_isReturnedAsIs() {
		assertEquals("1230", EgovDateUtil.validChkTime("1230"),
				"콜론이 없는 4자리 입력은 그대로 반환되어야 한다");
	}

	@Test
	void validChkTime_invalidLength_throws() {
		assertThrows(IllegalArgumentException.class, () -> EgovDateUtil.validChkTime("123"),
				"4자리 미만은 예외여야 한다");
		assertThrows(IllegalArgumentException.class, () -> EgovDateUtil.validChkTime("123456"),
				"5자리를 초과하는 입력은 예외여야 한다");
		assertThrows(IllegalArgumentException.class, () -> EgovDateUtil.validChkTime(null),
				"널 입력은 예외여야 한다");
	}
}
