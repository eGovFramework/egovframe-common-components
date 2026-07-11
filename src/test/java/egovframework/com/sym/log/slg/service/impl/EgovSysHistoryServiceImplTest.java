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

	@Test
	void insertSysHistoryAssignsUniqueTwentyCharacterIdsAcrossServiceInstances() throws Exception {
		RecordingSysHistoryDAO sysHistoryDAO = new RecordingSysHistoryDAO();
		EgovSysHistoryServiceImpl firstService = createService(sysHistoryDAO);
		EgovSysHistoryServiceImpl secondService = createService(sysHistoryDAO);

		int insertCountPerService = 1_000;
		for (int i = 0; i < insertCountPerService; i++) {
			firstService.insertSysHistory(new SysHistory());
			secondService.insertSysHistory(new SysHistory());
		}

		assertEquals(insertCountPerService * 2, sysHistoryDAO.histIds.size());
		assertEquals(sysHistoryDAO.histIds.size(), new HashSet<>(sysHistoryDAO.histIds).size());
		assertTrue(sysHistoryDAO.histIds.stream().allMatch(histId -> histId.length() == 20));
		assertTrue(sysHistoryDAO.histIds.stream().allMatch(histId -> histId.matches("HT_[A-Za-z0-9_-]{17}")));
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
