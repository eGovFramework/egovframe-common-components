package egovframework.com.uss.ion.bnt.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.egovframe.rte.fdl.excel.EgovExcelService;
import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;

import egovframework.com.uss.ion.bnt.service.BndtManageVO;

/**
 * 당직 엑셀 일괄등록의 조회 조건 매핑 회귀 테스트.
 *
 * <p>매퍼 selectBndtManageBnde 는 {@code WHERE ESNTL_ID = #{tempBndtId} AND USER_NM = #{tempBndtNm}}
 * 로 두 필드의 의미를 못박는다(여덟 개 DB 매퍼가 모두 같다). 그런데 조회용 VO 를 채우는 곳만
 * 당직자ID 를 tempBndtNm 에, 당직자명을 tempBndtId 에 넣어 서로 뒤바뀌어 있었다. 같은 메서드
 * 11줄 아래에서 결과 VO 를 채울 때는 {@code setTempBndtNm(sTempNm)} 으로 올바르게 넣는다.</p>
 *
 * <p>그 결과 조회가 항상 빈 결과를 돌려주고, 소속명(tempOrgnztNm)·동명이인 수(tempCount)가
 * 채워지지 않은 채 확인 화면이 그려진다.</p>
 */
class EgovBndtManageServiceImplBndeTest {

	private static final String BNDT_ID = "USRCNFRM_00000000001";
	private static final String BNDT_NM = "홍길동";

	/** selectBndtManageBnde 에 실제로 전달된 조회 조건을 기록하는 DAO 스텁. */
	private static final class RecordingDAO extends BndtManageDAO {
		private BndtManageVO lastQuery;

		@Override
		public BndtManageVO selectBndtManageBnde(BndtManageVO bndtManageVO) {
			lastQuery = bndtManageVO;
			return null;
		}
	}

	private static InputStream oneRowWorkbook() throws Exception {
		HSSFWorkbook wb = new HSSFWorkbook();
		HSSFSheet sheet = wb.createSheet("당직자");
		HSSFRow header = sheet.createRow(0);
		header.createCell(0).setCellValue("당직일자");
		header.createCell(1).setCellValue("당직자ID");
		header.createCell(2).setCellValue("당직자명");
		HSSFRow row = sheet.createRow(1);
		row.createCell(0).setCellValue("20260827");
		row.createCell(1).setCellValue(BNDT_ID);
		row.createCell(2).setCellValue(BNDT_NM);

		ByteArrayOutputStream out = new ByteArrayOutputStream();
		wb.write(out);
		wb.close();
		return new ByteArrayInputStream(out.toByteArray());
	}

	private static EgovExcelService excelServiceStub(InputStream source) throws Exception {
		final HSSFWorkbook wb = new HSSFWorkbook(source);
		return (EgovExcelService) Proxy.newProxyInstance(
				EgovExcelService.class.getClassLoader(),
				new Class<?>[] { EgovExcelService.class },
				(proxy, method, args) -> "loadWorkbook".equals(method.getName()) ? wb : null);
	}

	@Test
	void bulkUploadLooksUpTheDutyOfficerWithIdAndNameInTheOrderTheMapperExpects() throws Exception {
		EgovBndtManageServiceImpl service = new EgovBndtManageServiceImpl();
		RecordingDAO dao = new RecordingDAO();
		ReflectionTestUtils.setField(service, "bndtManageDAO", dao);
		ReflectionTestUtils.setField(service, "excelZipService", excelServiceStub(oneRowWorkbook()));

		List<BndtManageVO> result = service.selectBndtManageBnde(new ByteArrayInputStream(new byte[0]));

		assertNotNull(dao.lastQuery, "조회가 호출돼야 한다.");
		assertEquals(BNDT_ID, dao.lastQuery.getTempBndtId(),
				"tempBndtId 는 매퍼의 ESNTL_ID 조건에 쓰이므로 당직자ID 여야 한다.");
		assertEquals(BNDT_NM, dao.lastQuery.getTempBndtNm(),
				"tempBndtNm 은 매퍼의 USER_NM 조건에 쓰이므로 당직자명이어야 한다.");
		assertEquals(1, result.size());
	}

	private static final List<String> UNUSED = new ArrayList<>();
}
