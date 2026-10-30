package egovframework.com.sym.log.slg.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.dao.DataAccessResourceFailureException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.interceptor.DefaultTransactionAttribute;
import org.springframework.transaction.interceptor.NameMatchTransactionAttributeSource;
import org.springframework.transaction.interceptor.TransactionInterceptor;
import org.springframework.transaction.support.SimpleTransactionStatus;

import egovframework.com.sym.log.slg.service.EgovSysHistoryService;
import egovframework.com.sym.log.slg.service.SysHistory;

class EgovSysHistoryServiceImplTest {

	private static final String FIRST_ID = "HT_20261027130000000";
	private static final String NEXT_ID = "HT_20261027130000001";

	@Test
	void duplicateInsertRollsBackToSavepointAndRetriesWithNewTimestamp() throws Exception {
		List<String> attemptedIds = new ArrayList<>();
		RecordingTransactionManager transactions = new RecordingTransactionManager();
		SysHistoryDAO dao = new SysHistoryDAO() {
			@Override
			public int insertSysHistory(SysHistory history) {
				attemptedIds.add(history.getHistId());
				transactions.events.add("insert");
				if (attemptedIds.size() == 1) {
					throw new DuplicateKeyException("existing HIST_ID");
				}
				return 1;
			}
		};
		EgovSysHistoryService service = transactional(
				withDao(serviceWithClockSequence(FIRST_ID, NEXT_ID), dao), transactions);
		SysHistory history = new SysHistory();

		assertNull(service.insertSysHistory(history));

		assertEquals(List.of(FIRST_ID, NEXT_ID), attemptedIds);
		attemptedIds.forEach(EgovSysHistoryServiceImplTest::assertHistIdFormat);
		assertEquals(NEXT_ID, history.getHistId());
		assertEquals(List.of("savepoint", "insert", "rollbackToSavepoint", "releaseSavepoint",
				"savepoint", "insert", "releaseSavepoint", "commit"), transactions.events);
	}

	@Test
	void productionClockKeepsTimestampFormatAndLength() throws Exception {
		List<String> insertedIds = new ArrayList<>();
		SysHistoryDAO dao = new SysHistoryDAO() {
			@Override
			public int insertSysHistory(SysHistory history) {
				insertedIds.add(history.getHistId());
				return 1;
			}
		};
		EgovSysHistoryServiceImpl service = withDao(new EgovSysHistoryServiceImpl(), dao);

		service.insertSysHistory(new SysHistory());

		assertEquals(1, insertedIds.size());
		assertHistIdFormat(insertedIds.get(0));
	}

	@Test
	void restartWithResetClockSequenceRetriesIdStillStoredInDatabase() throws Exception {
		// DAO double의 저장 내용은 유지하고 서비스와 테스트 시계 상태만 새로 만든다.
		Set<String> storedIds = new HashSet<>();
		List<String> attemptedIds = new ArrayList<>();
		SysHistoryDAO dao = new SysHistoryDAO() {
			@Override
			public int insertSysHistory(SysHistory history) {
				String id = history.getHistId();
				attemptedIds.add(id);
				if (!storedIds.add(id)) {
					throw new DuplicateKeyException("HIST_ID persisted before restart");
				}
				return 1;
			}
		};
		SysHistory beforeRestart = new SysHistory();
		withDao(serviceWithClockSequence(FIRST_ID, NEXT_ID), dao).insertSysHistory(beforeRestart);

		SysHistory afterRestart = new SysHistory();
		withDao(serviceWithClockSequence(FIRST_ID, NEXT_ID), dao).insertSysHistory(afterRestart);

		assertEquals(List.of(FIRST_ID, FIRST_ID, NEXT_ID), attemptedIds);
		assertNotEquals(beforeRestart.getHistId(), afterRestart.getHistId());
		assertEquals(Set.of(FIRST_ID, NEXT_ID), storedIds);
		assertHistIdFormat(afterRestart.getHistId());
	}

	@Test
	void persistentDuplicateStopsAfterTenAttemptsAndPropagatesLastFailure() throws Exception {
		List<String> attemptedIds = new ArrayList<>();
		DuplicateKeyException duplicate = new DuplicateKeyException("persistent collision");
		SysHistoryDAO dao = new SysHistoryDAO() {
			@Override
			public int insertSysHistory(SysHistory history) {
				attemptedIds.add(history.getHistId());
				throw duplicate;
			}
		};
		EgovSysHistoryServiceImpl service = withDao(serviceWithClockSequence(FIRST_ID), dao);

		assertSame(duplicate, assertThrows(DuplicateKeyException.class,
				() -> service.insertSysHistory(new SysHistory())));
		assertEquals(10, attemptedIds.size());
	}

	@Test
	void nonDuplicateFailureIsNotRetried() throws Exception {
		List<String> attemptedIds = new ArrayList<>();
		DataAccessResourceFailureException failure = new DataAccessResourceFailureException("unavailable");
		SysHistoryDAO dao = new SysHistoryDAO() {
			@Override
			public int insertSysHistory(SysHistory history) {
				attemptedIds.add(history.getHistId());
				throw failure;
			}
		};
		RecordingTransactionManager transactions = new RecordingTransactionManager();
		EgovSysHistoryService service = transactional(
				withDao(serviceWithClockSequence(FIRST_ID), dao), transactions);

		assertSame(failure, assertThrows(DataAccessResourceFailureException.class,
				() -> service.insertSysHistory(new SysHistory())));
		assertEquals(1, attemptedIds.size());
		assertEquals(List.of("savepoint", "rollback"), transactions.events);
	}

	private static void assertHistIdFormat(String id) {
		assertEquals(20, id.length());
		assertTrue(id.matches("HT_[0-9]{17}"), id);
	}

	private static EgovSysHistoryServiceImpl serviceWithClockSequence(String... ids) {
		// 실제 시계나 sleep 시간에 의존하지 않고 충돌과 다음 밀리초를 재현한다.
		return new EgovSysHistoryServiceImpl() {
			private int next;

			@Override
			String createHistId() {
				return ids[Math.min(next++, ids.length - 1)];
			}
		};
	}

	private static EgovSysHistoryServiceImpl withDao(EgovSysHistoryServiceImpl service, SysHistoryDAO dao)
			throws Exception {
		Field field = EgovSysHistoryServiceImpl.class.getDeclaredField("sysHistoryDAO");
		field.setAccessible(true);
		field.set(service, dao);
		return service;
	}

	private static EgovSysHistoryService transactional(EgovSysHistoryServiceImpl target,
			RecordingTransactionManager transactions) {
		NameMatchTransactionAttributeSource attributes = new NameMatchTransactionAttributeSource();
		attributes.addTransactionalMethod("insertSysHistory", new DefaultTransactionAttribute());
		TransactionInterceptor interceptor = new TransactionInterceptor();
		interceptor.setTransactionManager(transactions);
		interceptor.setTransactionAttributeSource(attributes);
		ProxyFactory proxy = new ProxyFactory();
		proxy.setTarget(target);
		proxy.setInterfaces(EgovSysHistoryService.class);
		proxy.addAdvice(interceptor);
		return (EgovSysHistoryService) proxy.getProxy();
	}

	/** Spring 트랜잭션 경계를 통과하지만 JDBC 연결은 만들지 않는 테스트 전용 manager. */
	private static class RecordingTransactionManager implements PlatformTransactionManager {
		private final List<String> events = new ArrayList<>();

		@Override
		public TransactionStatus getTransaction(TransactionDefinition definition) {
			return new SimpleTransactionStatus() {
				@Override
				public Object createSavepoint() {
					events.add("savepoint");
					return new Object();
				}

				@Override
				public void rollbackToSavepoint(Object savepoint) {
					events.add("rollbackToSavepoint");
				}

				@Override
				public void releaseSavepoint(Object savepoint) {
					events.add("releaseSavepoint");
				}
			};
		}

		@Override
		public void commit(TransactionStatus status) {
			events.add("commit");
		}

		@Override
		public void rollback(TransactionStatus status) {
			events.add("rollback");
		}
	}
}
