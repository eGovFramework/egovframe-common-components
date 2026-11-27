package egovframework.com.sym.bat.service.impl;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import egovframework.com.sym.bat.service.BatchSchdul;
import egovframework.com.sym.bat.service.BatchSchdulDfk;

/**
 * 배치스케줄 목록조회의 요일정보 일괄조회가 IN 절 상한을 넘지 않는지 검증한다.
 *
 * <p>BatchScheduler.init()은 recordCountPerPage를 10000으로 두고 목록을 조회하므로
 * 요일정보 일괄조회에 1000개를 넘는 배치스케줄ID가 전달될 수 있다. Oracle은 IN 절 항목을
 * 1000개로 제한해 초과 시 ORA-01795가 발생하므로 DAO가 ID 목록을 나눠 조회해야 한다.</p>
 *
 * <p>DB 없이 selectList 호출을 가로채 청크 크기와 부모 ID별 매핑 결과를 확인한다.</p>
 */
class BatchSchdulDaoTest {

	/** Oracle의 IN 절 항목 상한. 초과하면 ORA-01795가 발생한다. */
	private static final int ORACLE_IN_CLAUSE_LIMIT = 1000;

	/**
	 * selectList 호출을 가로채 요일정보 조회 청크 크기를 기록하는 DAO.
	 */
	private static class RecordingBatchSchdulDao extends BatchSchdulDao {

		private final List<BatchSchdul> rows;
		private final List<Integer> dfkChunkSizes = new ArrayList<>();

		RecordingBatchSchdulDao(List<BatchSchdul> rows) {
			this.rows = rows;
		}

		@Override
		@SuppressWarnings("unchecked")
		public <E> List<E> selectList(String queryId, Object parameterObject) {
			if ("BatchSchdulDao.selectBatchSchdulList".equals(queryId)) {
				return (List<E>) rows;
			}
			if ("BatchSchdulDao.selectBatchSchdulDfkListByIds".equals(queryId)) {
				List<String> batchSchdulIds = (List<String>) parameterObject;
				dfkChunkSizes.add(batchSchdulIds.size());
				List<BatchSchdulDfk> dfkList = new ArrayList<>();
				for (String batchSchdulId : batchSchdulIds) {
					dfkList.add(dfk(batchSchdulId, "1"));
					dfkList.add(dfk(batchSchdulId, "2"));
				}
				return (List<E>) dfkList;
			}
			throw new IllegalStateException("예상하지 못한 쿼리 호출 : " + queryId);
		}

		private BatchSchdulDfk dfk(String batchSchdulId, String executSchdulDfkSe) {
			BatchSchdulDfk dfk = new BatchSchdulDfk();
			dfk.setBatchSchdulId(batchSchdulId);
			dfk.setExecutSchdulDfkSe(executSchdulDfkSe);
			dfk.setExecutSchdulDfkSeNm(batchSchdulId + "-" + executSchdulDfkSe);
			return dfk;
		}
	}

	@ParameterizedTest
	@ValueSource(ints = { 1000, 1001, 2000 })
	void selectBatchSchdulList_요일정보_일괄조회는_IN절_상한을_넘지_않는다(int rowCount) {
		RecordingBatchSchdulDao dao = new RecordingBatchSchdulDao(batchSchduls(rowCount));

		dao.selectBatchSchdulList(new BatchSchdul());

		int totalIds = 0;
		for (Integer chunkSize : dao.dfkChunkSizes) {
			assertTrue(chunkSize <= ORACLE_IN_CLAUSE_LIMIT,
					"IN 절 항목이 " + ORACLE_IN_CLAUSE_LIMIT + "개를 넘으면 안 된다 : " + chunkSize);
			totalIds += chunkSize;
		}
		assertEquals(rowCount, totalIds, "모든 배치스케줄ID가 조회에 전달되어야 한다");
		assertEquals((rowCount + ORACLE_IN_CLAUSE_LIMIT - 1) / ORACLE_IN_CLAUSE_LIMIT, dao.dfkChunkSizes.size(),
				"조회 횟수는 ID 수를 IN 절 상한으로 나눈 올림값이어야 한다");
	}

	@ParameterizedTest
	@ValueSource(ints = { 1000, 1001, 2000 })
	void selectBatchSchdulList_청크가_나뉘어도_요일정보가_모두_매핑된다(int rowCount) {
		RecordingBatchSchdulDao dao = new RecordingBatchSchdulDao(batchSchduls(rowCount));

		List<BatchSchdul> resultList = dao.selectBatchSchdulList(new BatchSchdul());

		assertEquals(rowCount, resultList.size());
		for (BatchSchdul result : resultList) {
			String batchSchdulId = result.getBatchSchdulId();
			assertArrayEquals(new String[] { "1", "2" }, result.getExecutSchdulDfkSes(),
					batchSchdulId + "의 요일정보가 매핑되어야 한다");
			assertEquals(batchSchdulId + "-1," + batchSchdulId + "-2 00:00:00", result.getExecutSchdul(),
					batchSchdulId + "에는 자신의 요일정보만 매핑되어야 한다");
		}
	}

	private List<BatchSchdul> batchSchduls(int rowCount) {
		List<BatchSchdul> rows = new ArrayList<>();
		for (int i = 0; i < rowCount; i++) {
			BatchSchdul batchSchdul = new BatchSchdul();
			batchSchdul.setBatchSchdulId(String.format("BSCHDUL%04d", i));
			batchSchdul.setExecutCycle("02");
			batchSchdul.setExecutSchdulDe("");
			batchSchdul.setExecutSchdulHour("00");
			batchSchdul.setExecutSchdulMnt("00");
			batchSchdul.setExecutSchdulSecnd("00");
			rows.add(batchSchdul);
		}
		return rows;
	}
}
