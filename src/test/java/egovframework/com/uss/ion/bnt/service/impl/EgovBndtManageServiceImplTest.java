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

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.egovframe.rte.fdl.excel.EgovExcelService;

import egovframework.com.uss.ion.bnt.service.BndtManageVO;

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

	/** 첫 열이 당직일자인 .xls 통합문서를 메모리에서 만든다. */
	private static byte[] xlsWithBndtDates(String... bndtDates) throws Exception {
		try (HSSFWorkbook workbook = new HSSFWorkbook(); ByteArrayOutputStream out = new ByteArrayOutputStream()) {
			HSSFSheet sheet = workbook.createSheet("당직자");
			HSSFRow header = sheet.createRow(0);
			header.createCell(0).setCellValue("당직일자");
			header.createCell(1).setCellValue("당직자ID");
			header.createCell(2).setCellValue("당직자명");
			for (int i = 0; i < bndtDates.length; i++) {
				HSSFRow row = sheet.createRow(i + 1);
				row.createCell(0).setCellValue(bndtDates[i]);
				row.createCell(1).setCellValue("user" + i);
				row.createCell(2).setCellValue("홍길동" + i);
			}
			workbook.write(out);
			return out.toByteArray();
		}
	}

	/** loadWorkbook만 응답하는 EgovExcelService 대역. */
	private static EgovExcelService excelServiceReturning(byte[] xls) {
		return (EgovExcelService) Proxy.newProxyInstance(
				EgovExcelService.class.getClassLoader(),
				new Class<?>[] { EgovExcelService.class },
				(proxy, method, args) -> {
					if ("loadWorkbook".equals(method.getName())) {
						return new HSSFWorkbook(new ByteArrayInputStream(xls));
					}
					return null;
				});
	}

	/** 조회 결과가 없는 상황을 재현하는 DAO 대역. */
	private static class NoMatchDao extends BndtManageDAO {
		@Override
		public BndtManageVO selectBndtManageBnde(BndtManageVO bndtManageVO) {
			return null;
		}
	}

	private static void inject(Object target, String fieldName, Object value) throws Exception {
		Field field = target.getClass().getDeclaredField(fieldName);
		field.setAccessible(true);
		field.set(target, value);
	}

	private static List<BndtManageVO> readXls(String... bndtDates) throws Exception {
		EgovBndtManageServiceImpl service = new EgovBndtManageServiceImpl();
		inject(service, "excelZipService", excelServiceReturning(xlsWithBndtDates(bndtDates)));
		inject(service, "bndtManageDAO", new NoMatchDao());

		try (InputStream in = new ByteArrayInputStream(new byte[0])) {
			return service.selectBndtManageBnde(in);
		}
	}

	@Test
	void selectBndtManageBnde_xls_skipsRowsWithShortBndtDate() throws Exception {
		// .xlsx 처리와 동일하게 8자리 미만 당직일자 행은 목록에서 제외돼야 한다.
		List<BndtManageVO> list = readXls("20260101", "2026", "", "20260103");

		assertEquals(2, list.size());
		assertEquals("20260101", list.get(0).getBndtDe());
		assertEquals("20260103", list.get(1).getBndtDe());
	}

	@Test
	void selectBndtManageBnde_xls_validRowsKeepWeekday() throws Exception {
		List<BndtManageVO> list = readXls("20260101");

		assertEquals(1, list.size());
		assertTrue(list.get(0).getDateWeek() >= 1 && list.get(0).getDateWeek() <= 7);
	}
}
