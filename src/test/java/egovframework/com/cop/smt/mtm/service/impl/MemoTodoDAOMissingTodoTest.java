package egovframework.com.cop.smt.mtm.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;

import org.junit.jupiter.api.Test;

import egovframework.com.cop.smt.mtm.service.MemoTodoVO;

class MemoTodoDAOMissingTodoTest {

	@Test
	void missingTodoReturnsNullBeforeSplittingDateAndTime() {
		StubMemoTodoDAO dao = new StubMemoTodoDAO(null);
		MemoTodoVO query = new MemoTodoVO();
		query.setTodoId("999999999");

		assertNull(dao.selectMemoTodo(query));
		assertSame(query, dao.parameter);
	}

	@Test
	void existingTodoKeepsDateTimeFieldsAndOriginalRow() {
		MemoTodoVO row = new MemoTodoVO();
		row.setTodoBeginTime("2026-09-090905");
		row.setTodoEndTime("2026-09-092359");

		MemoTodoVO result = new StubMemoTodoDAO(row).selectMemoTodo(new MemoTodoVO());

		assertSame(row, result);
		assertEquals("2026-09-09", result.getTodoDe());
		assertEquals("09", result.getTodoBeginHour());
		assertEquals("05", result.getTodoBeginMin());
		assertEquals("23", result.getTodoEndHour());
		assertEquals("59", result.getTodoEndMin());
		assertEquals("2026-09-090905", result.getTodoBeginTime());
		assertEquals("2026-09-092359", result.getTodoEndTime());
	}

	private static final class StubMemoTodoDAO extends MemoTodoDAO {
		private final MemoTodoVO row;
		private Object parameter;

		private StubMemoTodoDAO(MemoTodoVO row) {
			this.row = row;
		}

		@Override
		@SuppressWarnings("unchecked")
		public <T> T selectOne(String queryId, Object parameter) {
			assertEquals("MemoTodoDAO.selectMemoTodo", queryId);
			this.parameter = parameter;
			return (T) row;
		}
	}
}
