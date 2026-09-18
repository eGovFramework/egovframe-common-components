package egovframework.com.sym.log.slg.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;

import egovframework.com.sym.log.slg.service.SysHistory;

class EgovSysHistoryServiceImplTest {

	private static final String HIST_ID_PATTERN = "HT_\\d{17}";

	@Test
	void insertSysHistoryKeepsTimestampIdFormat() throws Exception {
		RecordingSysHistoryDAO sysHistoryDAO = new RecordingSysHistoryDAO();

		createService(sysHistoryDAO).insertSysHistory(new SysHistory());

		String histId = sysHistoryDAO.histIds.get(0);
		assertEquals(20, histId.length());
		assertTrue(histId.matches(HIST_ID_PATTERN), "기존 HT_yyyyMMddHHmmssSSS 형식이 유지되어야 한다: " + histId);
	}

	@Test
	void insertSysHistoryAssignsUniqueIdsWithinSameMillisecond() throws Exception {
		RecordingSysHistoryDAO sysHistoryDAO = new RecordingSysHistoryDAO();
		EgovSysHistoryServiceImpl firstService = createService(sysHistoryDAO);
		EgovSysHistoryServiceImpl secondService = createService(sysHistoryDAO);

		int insertCountPerService = 1_000;
		for (int i = 0; i < insertCountPerService; i++) {
			firstService.insertSysHistory(new SysHistory());
			secondService.insertSysHistory(new SysHistory());
		}

		List<String> histIds = sysHistoryDAO.histIds;
		assertEquals(insertCountPerService * 2, histIds.size());
		assertEquals(histIds.size(), new HashSet<>(histIds).size());
		assertTrue(histIds.stream().allMatch(histId -> histId.matches(HIST_ID_PATTERN)));

		for (int i = 1; i < histIds.size(); i++) {
			assertTrue(histIds.get(i).compareTo(histIds.get(i - 1)) > 0,
					"이력 ID는 등록 순서대로 증가해야 한다: " + histIds.get(i - 1) + ", " + histIds.get(i));
		}
	}

	private EgovSysHistoryServiceImpl createService(RecordingSysHistoryDAO sysHistoryDAO) {
		EgovSysHistoryServiceImpl service = new EgovSysHistoryServiceImpl();
		ReflectionTestUtils.setField(service, "sysHistoryDAO", sysHistoryDAO);
		return service;
	}

	private static class RecordingSysHistoryDAO extends SysHistoryDAO {

		private final List<String> histIds = new ArrayList<>();

		@Override
		public int insertSysHistory(SysHistory history) {
			histIds.add(history.getHistId());
			return 1;
		}
	}
}
