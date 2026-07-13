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
package egovframework.com.uss.ion.bnt.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.lang.reflect.Method;

import org.junit.jupiter.api.Test;

/**
 * {@link EgovBndtManageServiceImpl}의 당직일자 요일 계산이 짧은/빈 날짜 문자열에서
 * StringIndexOutOfBoundsException 없이 동작하는지 검증한다.
 * (.xls(HSSF) 일괄등록 경로는 .xlsx(XSSF) 경로와 달리 길이 가드가 없어 예외가 발생했다)
 */
class EgovBndtManageServiceImplTest {

	private static int callGetDateWeekInt(String sDate) throws Exception {
		EgovBndtManageServiceImpl service = new EgovBndtManageServiceImpl();
		Method m = EgovBndtManageServiceImpl.class.getDeclaredMethod("getDateWeekInt", String.class);
		m.setAccessible(true);
		return (int) m.invoke(service, sDate);
	}

	@Test
	void getDateWeekInt_shortOrEmptyDate_returnsZeroWithoutException() {
		assertEquals(0, assertDoesNotThrow(() -> callGetDateWeekInt("")),
				"빈 당직일자는 예외 없이 0을 반환해야 한다");
		assertEquals(0, assertDoesNotThrow(() -> callGetDateWeekInt("2025")),
				"8자리 미만 당직일자는 예외 없이 0을 반환해야 한다");
		assertEquals(0, assertDoesNotThrow(() -> callGetDateWeekInt("2025-08")),
				"하이픈 제거 후에도 8자리 미만이면 0을 반환해야 한다");
	}

	@Test
	void getDateWeekInt_nullDate_returnsZero() {
		assertEquals(0, assertDoesNotThrow(() -> callGetDateWeekInt(null)),
				"널 당직일자는 0을 반환해야 한다");
	}

	@Test
	void getDateWeekInt_validDate_returnsWeekdayInRange() throws Exception {
		int week = callGetDateWeekInt("20250815");
		assertTrue(week >= 1 && week <= 7, "정상 8자리 날짜는 1~7의 요일 값을 반환해야 한다: " + week);
		assertEquals(callGetDateWeekInt("20250815"), callGetDateWeekInt("2025-08-15"),
				"yyyyMMdd와 yyyy-MM-dd 입력은 동일한 요일을 반환해야 한다");
	}
}
