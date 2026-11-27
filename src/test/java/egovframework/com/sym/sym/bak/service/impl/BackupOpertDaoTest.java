package egovframework.com.sym.sym.bak.service.impl;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import egovframework.com.sym.sym.bak.service.BackupOpert;
import egovframework.com.sym.sym.bak.service.BackupSchdulDfk;

/**
 * 백업작업 목록조회의 요일정보 일괄조회가 IN 절 상한을 넘지 않는지 검증한다.
 *
 * <p>BackupScheduler.init()은 recordCountPerPage를 10000으로 두고 목록을 조회하므로
 * 요일정보 일괄조회에 1000개를 넘는 백업작업ID가 전달될 수 있다. Oracle은 IN 절 항목을
 * 1000개로 제한해 초과 시 ORA-01795가 발생하므로 DAO가 ID 목록을 나눠 조회해야 한다.</p>
 *
 * <p>DB 없이 selectList 호출을 가로채 청크 크기와 부모 ID별 매핑 결과를 확인한다.</p>
 */
class BackupOpertDaoTest {

	/** Oracle의 IN 절 항목 상한. 초과하면 ORA-01795가 발생한다. */
	private static final int ORACLE_IN_CLAUSE_LIMIT = 1000;

	/**
	 * selectList 호출을 가로채 요일정보 조회 청크 크기를 기록하는 DAO.
	 */
	private static class RecordingBackupOpertDao extends BackupOpertDao {

		private final List<BackupOpert> rows;
		private final List<Integer> dfkChunkSizes = new ArrayList<>();

		RecordingBackupOpertDao(List<BackupOpert> rows) {
			this.rows = rows;
		}

		@Override
		@SuppressWarnings("unchecked")
		public <E> List<E> selectList(String queryId, Object parameterObject) {
			if ("BackupOpertDao.selectBackupOpertList".equals(queryId)) {
				return (List<E>) rows;
			}
			if ("BackupOpertDao.selectBackupSchdulDfkListByIds".equals(queryId)) {
				List<String> backupOpertIds = (List<String>) parameterObject;
				dfkChunkSizes.add(backupOpertIds.size());
				List<BackupSchdulDfk> dfkList = new ArrayList<>();
				for (String backupOpertId : backupOpertIds) {
					dfkList.add(dfk(backupOpertId, "1"));
					dfkList.add(dfk(backupOpertId, "2"));
				}
				return (List<E>) dfkList;
			}
			throw new IllegalStateException("예상하지 못한 쿼리 호출 : " + queryId);
		}

		private BackupSchdulDfk dfk(String backupOpertId, String executSchdulDfkSe) {
			BackupSchdulDfk dfk = new BackupSchdulDfk();
			dfk.setBackupOpertId(backupOpertId);
			dfk.setExecutSchdulDfkSe(executSchdulDfkSe);
			dfk.setExecutSchdulDfkSeNm(backupOpertId + "-" + executSchdulDfkSe);
			return dfk;
		}
	}

	@ParameterizedTest
	@ValueSource(ints = { 1000, 1001, 2000 })
	void selectBackupOpertList_요일정보_일괄조회는_IN절_상한을_넘지_않는다(int rowCount) {
		RecordingBackupOpertDao dao = new RecordingBackupOpertDao(backupOperts(rowCount));

		dao.selectBackupOpertList(new BackupOpert());

		int totalIds = 0;
		for (Integer chunkSize : dao.dfkChunkSizes) {
			assertTrue(chunkSize <= ORACLE_IN_CLAUSE_LIMIT,
					"IN 절 항목이 " + ORACLE_IN_CLAUSE_LIMIT + "개를 넘으면 안 된다 : " + chunkSize);
			totalIds += chunkSize;
		}
		assertEquals(rowCount, totalIds, "모든 백업작업ID가 조회에 전달되어야 한다");
		assertEquals((rowCount + ORACLE_IN_CLAUSE_LIMIT - 1) / ORACLE_IN_CLAUSE_LIMIT, dao.dfkChunkSizes.size(),
				"조회 횟수는 ID 수를 IN 절 상한으로 나눈 올림값이어야 한다");
	}

	@ParameterizedTest
	@ValueSource(ints = { 1000, 1001, 2000 })
	void selectBackupOpertList_청크가_나뉘어도_요일정보가_모두_매핑된다(int rowCount) {
		RecordingBackupOpertDao dao = new RecordingBackupOpertDao(backupOperts(rowCount));

		List<BackupOpert> resultList = dao.selectBackupOpertList(new BackupOpert());

		assertEquals(rowCount, resultList.size());
		for (BackupOpert result : resultList) {
			String backupOpertId = result.getBackupOpertId();
			assertArrayEquals(new String[] { "1", "2" }, result.getExecutSchdulDfkSes(),
					backupOpertId + "의 요일정보가 매핑되어야 한다");
			assertEquals(backupOpertId + "-1," + backupOpertId + "-2 00:00:00", result.getExecutSchdul(),
					backupOpertId + "에는 자신의 요일정보만 매핑되어야 한다");
		}
	}

	private List<BackupOpert> backupOperts(int rowCount) {
		List<BackupOpert> rows = new ArrayList<>();
		for (int i = 0; i < rowCount; i++) {
			BackupOpert backupOpert = new BackupOpert();
			backupOpert.setBackupOpertId(String.format("BOPERT%04d", i));
			backupOpert.setExecutCycle("02");
			backupOpert.setExecutSchdulDe("");
			backupOpert.setExecutSchdulHour("00");
			backupOpert.setExecutSchdulMnt("00");
			backupOpert.setExecutSchdulSecnd("00");
			rows.add(backupOpert);
		}
		return rows;
	}
}
