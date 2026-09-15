package egovframework.com.utl.sys.dbm.web;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.ui.ModelMap;

import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.service.EgovUserDetailsService;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.com.utl.sys.dbm.service.DbMntrng;
import egovframework.com.utl.sys.dbm.service.DbMntrngLog;
import egovframework.com.utl.sys.dbm.service.EgovDbMntrngService;

/**
 * DB서비스모니터링 목록·로그 조회의 로그인 검증 회귀 테스트.
 *
 * 형제 상세조회 selectDbMntrng는 이미 로그인 검증(egovAssertLoginUser)을 거치는데,
 * 같은 조회 계열인 selectDbMntrngList·selectDbMntrngLog·selectDbMntrngLogList는
 * 그 검증이 빠져 있었다(형제 경로 비교로 드러나는 자기모순).
 */
class EgovDbMntrngControllerLoginCheckTest {

	private static final class StubService implements EgovDbMntrngService {
		private boolean listCalled = false;
		private boolean logCalled = false;
		private boolean logListCalled = false;

		@Override
		public void deleteDbMntrng(DbMntrng dbMntrng) {
			throw new UnsupportedOperationException();
		}

		@Override
		public void insertDbMntrng(DbMntrng dbMntrng) {
			throw new UnsupportedOperationException();
		}

		@Override
		public void insertDbMntrngLog(DbMntrngLog dbMntrngLog) {
			throw new UnsupportedOperationException();
		}

		@Override
		public DbMntrng selectDbMntrng(DbMntrng dbMntrng) {
			throw new UnsupportedOperationException();
		}

		@Override
		public DbMntrngLog selectDbMntrngLog(DbMntrngLog dbMntrngLog) {
			logCalled = true;
			return new DbMntrngLog();
		}

		@Override
		public List<DbMntrng> selectDbMntrngList(DbMntrng searchVO) {
			listCalled = true;
			return List.of();
		}

		@Override
		public int selectDbMntrngListCnt(DbMntrng searchVO) {
			return 0;
		}

		@Override
		public List<DbMntrngLog> selectDbMntrngLogList(DbMntrngLog searchVO) {
			logListCalled = true;
			return List.of();
		}

		@Override
		public int selectDbMntrngLogListCnt(DbMntrngLog searchVO) {
			return 0;
		}

		@Override
		public void updateDbMntrng(DbMntrng dbMntrng) {
			throw new UnsupportedOperationException();
		}
	}

	private static void bindAnonymous() {
		EgovUserDetailsService stub = new EgovUserDetailsService() {
			@Override
			public Object getAuthenticatedUser() {
				return null;
			}

			@Override
			public List<String> getAuthorities() {
				return List.of();
			}

			@Override
			public Boolean isAuthenticated() {
				return Boolean.FALSE;
			}
		};
		new EgovUserDetailsHelper().setEgovUserDetailsService(stub);
	}

	private static void bindLoginUser(String uniqId) {
		LoginVO login = new LoginVO();
		login.setUniqId(uniqId);
		EgovUserDetailsService stub = new EgovUserDetailsService() {
			@Override
			public Object getAuthenticatedUser() {
				return login;
			}

			@Override
			public List<String> getAuthorities() {
				return List.of();
			}

			@Override
			public Boolean isAuthenticated() {
				return Boolean.TRUE;
			}
		};
		new EgovUserDetailsHelper().setEgovUserDetailsService(stub);
	}

	private static void setPrivateField(Object target, String fieldName, Object value) {
		try {
			java.lang.reflect.Field field = target.getClass().getDeclaredField(fieldName);
			field.setAccessible(true);
			field.set(target, value);
		} catch (ReflectiveOperationException e) {
			throw new IllegalStateException(e);
		}
	}

	/** getInt(...)에 10을 돌려주는 것 외엔 페이징 계산에 관여하지 않는 최소 프록시. */
	private static org.egovframe.rte.fdl.property.EgovPropertyService noopPropertiesService() {
		return (org.egovframe.rte.fdl.property.EgovPropertyService) java.lang.reflect.Proxy.newProxyInstance(
				EgovDbMntrngControllerLoginCheckTest.class.getClassLoader(),
				new Class<?>[] { org.egovframe.rte.fdl.property.EgovPropertyService.class },
				(proxy, method, args) -> {
					Class<?> returnType = method.getReturnType();
					if (returnType == int.class) {
						return 10;
					}
					if (returnType == boolean.class) {
						return false;
					}
					if (returnType == long.class) {
						return 0L;
					}
					if (returnType == double.class) {
						return 0.0d;
					}
					if (returnType == float.class) {
						return 0.0f;
					}
					return null;
				});
	}

	private static EgovDbMntrngController controllerWith(StubService service) {
		EgovDbMntrngController controller = new EgovDbMntrngController();
		setPrivateField(controller, "egovDbMntrngService", service);
		setPrivateField(controller, "propertyService", noopPropertiesService());
		return controller;
	}

	@Test
	void logByAnonymousIsRejected() {
		StubService service = new StubService();
		bindAnonymous();

		EgovDbMntrngController controller = controllerWith(service);
		assertThrows(IllegalStateException.class,
				() -> controller.selectDbMntrngLog(new DbMntrngLog(), new ModelMap()),
				"An anonymous caller must not be able to read a DB monitoring log entry.");
		assertTrue(!service.logCalled, "selectDbMntrngLog must not be reached without login.");
	}

	@Test
	void logByLoggedInUserSucceeds() throws Exception {
		StubService service = new StubService();
		bindLoginUser("USRCNFRM_00000000001");

		EgovDbMntrngController controller = controllerWith(service);
		controller.selectDbMntrngLog(new DbMntrngLog(), new ModelMap());
		assertTrue(service.logCalled, "A logged-in user must be able to read a DB monitoring log entry.");
	}

	@Test
	void listByAnonymousIsRejected() {
		StubService service = new StubService();
		bindAnonymous();

		EgovDbMntrngController controller = controllerWith(service);
		assertThrows(IllegalStateException.class,
				() -> controller.selectDbMntrngList(new DbMntrng(), new ModelMap()),
				"An anonymous caller must not be able to read the DB monitoring list.");
		assertTrue(!service.listCalled, "selectDbMntrngList must not be reached without login.");
	}

	@Test
	void listByLoggedInUserSucceeds() throws Exception {
		StubService service = new StubService();
		bindLoginUser("USRCNFRM_00000000001");

		EgovDbMntrngController controller = controllerWith(service);
		controller.selectDbMntrngList(new DbMntrng(), new ModelMap());
		assertTrue(service.listCalled, "A logged-in user must be able to read the DB monitoring list.");
	}

	@Test
	void logListByAnonymousIsRejected() {
		StubService service = new StubService();
		bindAnonymous();

		EgovDbMntrngController controller = controllerWith(service);
		assertThrows(IllegalStateException.class,
				() -> controller.selectDbMntrngLogList(new DbMntrngLog(), new ModelMap()),
				"An anonymous caller must not be able to read the DB monitoring log list.");
		assertTrue(!service.logListCalled, "selectDbMntrngLogList must not be reached without login.");
	}

	@Test
	void logListByLoggedInUserSucceeds() throws Exception {
		StubService service = new StubService();
		bindLoginUser("USRCNFRM_00000000001");

		EgovDbMntrngController controller = controllerWith(service);
		controller.selectDbMntrngLogList(new DbMntrngLog(), new ModelMap());
		assertTrue(service.logListCalled, "A logged-in user must be able to read the DB monitoring log list.");
	}
}
