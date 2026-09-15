package egovframework.com.utl.sys.trm.web;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.ui.ModelMap;

import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.service.EgovUserDetailsService;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.com.utl.sys.trm.service.CntcVO;
import egovframework.com.utl.sys.trm.service.EgovTrsmrcvMntrngService;
import egovframework.com.utl.sys.trm.service.TrsmrcvMntrng;
import egovframework.com.utl.sys.trm.service.TrsmrcvMntrngLog;

/**
 * 송수신모니터링 목록·로그 조회의 로그인 검증 회귀 테스트.
 *
 * 형제 상세조회 selectTrsmrcvMntrng는 이미 로그인 검증(egovAssertLoginUser)을 거치는데,
 * 같은 조회 계열인 selectTrsmrcvMntrngList·selectTrsmrcvMntrngLog·selectTrsmrcvMntrngLogList는
 * 그 검증이 빠져 있었다(형제 경로 비교로 드러나는 자기모순).
 */
class EgovTrsmrcvMntrngControllerLoginCheckTest {

	private static final class StubService implements EgovTrsmrcvMntrngService {
		private boolean listCalled = false;
		private boolean logCalled = false;
		private boolean logListCalled = false;

		@Override
		public void deleteTrsmrcvMntrng(TrsmrcvMntrng trsmrcvMntrng) {
			throw new UnsupportedOperationException();
		}

		@Override
		public void insertTrsmrcvMntrng(TrsmrcvMntrng trsmrcvMntrng) {
			throw new UnsupportedOperationException();
		}

		@Override
		public void insertTrsmrcvMntrngLog(TrsmrcvMntrngLog trsmrcvMntrngLog) {
			throw new UnsupportedOperationException();
		}

		@Override
		public TrsmrcvMntrng selectTrsmrcvMntrng(TrsmrcvMntrng trsmrcvMntrng) {
			throw new UnsupportedOperationException();
		}

		@Override
		public TrsmrcvMntrngLog selectTrsmrcvMntrngLog(TrsmrcvMntrngLog trsmrcvMntrngLog) {
			logCalled = true;
			return new TrsmrcvMntrngLog();
		}

		@Override
		public List<TrsmrcvMntrng> selectTrsmrcvMntrngList(TrsmrcvMntrng searchVO) {
			listCalled = true;
			return List.of();
		}

		@Override
		public int selectTrsmrcvMntrngListCnt(TrsmrcvMntrng searchVO) {
			return 0;
		}

		@Override
		public List<TrsmrcvMntrngLog> selectTrsmrcvMntrngLogList(TrsmrcvMntrngLog searchVO) {
			logListCalled = true;
			return List.of();
		}

		@Override
		public int selectTrsmrcvMntrngLogListCnt(TrsmrcvMntrngLog searchVO) {
			return 0;
		}

		@Override
		public void updateTrsmrcvMntrng(TrsmrcvMntrng trsmrcvMntrng) {
			throw new UnsupportedOperationException();
		}

		@Override
		public List<CntcVO> selectCntcList(CntcVO searchVO) {
			throw new UnsupportedOperationException();
		}

		@Override
		public int selectCntcListCnt(CntcVO searchVO) {
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
				EgovTrsmrcvMntrngControllerLoginCheckTest.class.getClassLoader(),
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

	private static EgovTrsmrcvMntrngController controllerWith(StubService service) {
		EgovTrsmrcvMntrngController controller = new EgovTrsmrcvMntrngController();
		setPrivateField(controller, "egovTrsmrcvMntrngService", service);
		setPrivateField(controller, "propertyService", noopPropertiesService());
		return controller;
	}

	@Test
	void logByAnonymousIsRejected() {
		StubService service = new StubService();
		bindAnonymous();

		EgovTrsmrcvMntrngController controller = controllerWith(service);
		assertThrows(IllegalStateException.class,
				() -> controller.selectTrsmrcvMntrngLog(new TrsmrcvMntrngLog(), new ModelMap()),
				"An anonymous caller must not be able to read a transmit/receive monitoring log entry.");
		assertTrue(!service.logCalled, "selectTrsmrcvMntrngLog must not be reached without login.");
	}

	@Test
	void logByLoggedInUserSucceeds() throws Exception {
		StubService service = new StubService();
		bindLoginUser("USRCNFRM_00000000001");

		EgovTrsmrcvMntrngController controller = controllerWith(service);
		controller.selectTrsmrcvMntrngLog(new TrsmrcvMntrngLog(), new ModelMap());
		assertTrue(service.logCalled, "A logged-in user must be able to read a transmit/receive monitoring log entry.");
	}

	@Test
	void listByAnonymousIsRejected() {
		StubService service = new StubService();
		bindAnonymous();

		EgovTrsmrcvMntrngController controller = controllerWith(service);
		assertThrows(IllegalStateException.class,
				() -> controller.selectTrsmrcvMntrngList(new TrsmrcvMntrng(), new ModelMap()),
				"An anonymous caller must not be able to read the transmit/receive monitoring list.");
		assertTrue(!service.listCalled, "selectTrsmrcvMntrngList must not be reached without login.");
	}

	@Test
	void listByLoggedInUserSucceeds() throws Exception {
		StubService service = new StubService();
		bindLoginUser("USRCNFRM_00000000001");

		EgovTrsmrcvMntrngController controller = controllerWith(service);
		controller.selectTrsmrcvMntrngList(new TrsmrcvMntrng(), new ModelMap());
		assertTrue(service.listCalled, "A logged-in user must be able to read the transmit/receive monitoring list.");
	}

	@Test
	void logListByAnonymousIsRejected() {
		StubService service = new StubService();
		bindAnonymous();

		EgovTrsmrcvMntrngController controller = controllerWith(service);
		assertThrows(IllegalStateException.class,
				() -> controller.selectTrsmrcvMntrngLogList(new TrsmrcvMntrngLog(), new ModelMap()),
				"An anonymous caller must not be able to read the transmit/receive monitoring log list.");
		assertTrue(!service.logListCalled, "selectTrsmrcvMntrngLogList must not be reached without login.");
	}

	@Test
	void logListByLoggedInUserSucceeds() throws Exception {
		StubService service = new StubService();
		bindLoginUser("USRCNFRM_00000000001");

		EgovTrsmrcvMntrngController controller = controllerWith(service);
		controller.selectTrsmrcvMntrngLogList(new TrsmrcvMntrngLog(), new ModelMap());
		assertTrue(service.logListCalled, "A logged-in user must be able to read the transmit/receive monitoring log list.");
	}
}
