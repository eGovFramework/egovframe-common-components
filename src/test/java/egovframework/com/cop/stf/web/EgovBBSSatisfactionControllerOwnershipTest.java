package egovframework.com.cop.stf.web;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;

import org.egovframe.rte.fdl.property.EgovPropertyService;

import egovframework.com.cmm.EgovMessageSource;
import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.service.EgovUserDetailsService;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.com.cop.bbs.service.EgovBBSSatisfactionService;
import egovframework.com.cop.bbs.service.Satisfaction;
import egovframework.com.cop.bbs.service.SatisfactionVO;

/**
 * 만족도조사 수정·상세조회의 소유권 검증 회귀 테스트.
 *
 * 로그인 사용자가 자신이 등록하지 않은 만족도 레코드를 수정·조회하려 하면 차단돼야 한다(IDOR 방지).
 * 수정 전 코드는 인증 여부만 확인하고 소유자를 대조하지 않아, 아래 attacker 테스트가 실패한다.
 */
class EgovBBSSatisfactionControllerOwnershipTest {

	private static final String OWNER = "USRCNFRM_00000000001";
	private static final String ATTACKER = "USRCNFRM_00000000009";

	/** selectSatisfaction은 저장된 소유자를 돌려주고, updateSatisfaction 호출 여부를 기록하는 스텁. */
	private static final class StubService implements EgovBBSSatisfactionService {
		private final Satisfaction stored;
		private boolean updateCalled = false;

		StubService(String ownerUniqId) {
			this.stored = new Satisfaction();
			this.stored.setFrstRegisterId(ownerUniqId);
		}

		@Override
		public Satisfaction selectSatisfaction(SatisfactionVO satisfactionVO) {
			return stored;
		}

		@Override
		public void updateSatisfaction(Satisfaction satisfaction) {
			updateCalled = true;
		}

		@Override
		public boolean canUseSatisfaction(String bbsId) {
			throw new UnsupportedOperationException();
		}

		@Override
		public Map<String, Object> selectSatisfactionList(SatisfactionVO satisfactionVO) {
			return Map.of("resultCnt", "0", "resultList", List.of(), "summary", Map.of());
		}

		@Override
		public void insertSatisfaction(Satisfaction satisfaction) {
			throw new UnsupportedOperationException();
		}

		@Override
		public void deleteSatisfaction(SatisfactionVO satisfactionVO) {
			throw new UnsupportedOperationException();
		}

		@Override
		public String getSatisfactionPassword(Satisfaction satisfaction) {
			throw new UnsupportedOperationException();
		}
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

	private static EgovBBSSatisfactionController controllerWith(StubService service) {
		EgovBBSSatisfactionController controller = new EgovBBSSatisfactionController();
		controller.bbsSatisfactionService = service;
		controller.egovMessageSource = new EgovMessageSource() {
			@Override
			public String getMessage(String code) {
				return code;
			}
		};
		controller.propertyService = new EgovPropertyService() {
			@Override
			public int getInt(String key) {
				return 10;
			}

			@Override
			public int getInt(String key, int defaultValue) {
				return defaultValue;
			}

			@Override
			public boolean getBoolean(String key) {
				throw new UnsupportedOperationException();
			}

			@Override
			public boolean getBoolean(String key, boolean defaultValue) {
				throw new UnsupportedOperationException();
			}

			@Override
			public double getDouble(String key) {
				throw new UnsupportedOperationException();
			}

			@Override
			public double getDouble(String key, double defaultValue) {
				throw new UnsupportedOperationException();
			}

			@Override
			public float getFloat(String key) {
				throw new UnsupportedOperationException();
			}

			@Override
			public float getFloat(String key, float defaultValue) {
				throw new UnsupportedOperationException();
			}

			@Override
			public java.util.Iterator<?> getKeys() {
				throw new UnsupportedOperationException();
			}

			@Override
			public java.util.Iterator<?> getKeys(String prefix) {
				throw new UnsupportedOperationException();
			}

			@Override
			public long getLong(String key) {
				throw new UnsupportedOperationException();
			}

			@Override
			public long getLong(String key, long defaultValue) {
				throw new UnsupportedOperationException();
			}

			@Override
			public String getString(String key) {
				throw new UnsupportedOperationException();
			}

			@Override
			public String getString(String key, String defaultValue) {
				throw new UnsupportedOperationException();
			}

			@Override
			public String[] getStringArray(String key) {
				throw new UnsupportedOperationException();
			}

			@Override
			public void refreshPropertyFiles() {
				throw new UnsupportedOperationException();
			}
		};
		return controller;
	}

	private static Object[] callUpdate(String ownerUniqId, String loginUniqId) throws Exception {
		StubService service = new StubService(ownerUniqId);
		EgovBBSSatisfactionController controller = controllerWith(service);
		bindLoginUser(loginUniqId);

		SatisfactionVO searchVO = new SatisfactionVO();
		Satisfaction satisfaction = new Satisfaction();
		satisfaction.setStsfdgNo("8000001");
		satisfaction.setWrterNm("edited");
		satisfaction.setStsfdg(1);
		satisfaction.setStsfdgPassword("dummy");
		BindingResult bindingResult = new BeanPropertyBindingResult(satisfaction, "satisfaction");

		ModelMap model = new ModelMap();
		String view = controller.updateSatisfaction(searchVO, satisfaction, bindingResult, model);
		return new Object[] { service, view, model };
	}

	@Test
	void updateByNonOwnerDoesNotReachTheUpdateService() throws Exception {
		Object[] r = callUpdate(OWNER, ATTACKER);
		StubService service = (StubService) r[0];
		ModelMap model = (ModelMap) r[2];
		assertFalse(service.updateCalled,
				"A logged-in non-owner must not be able to update another member's satisfaction record.");
		assertEquals("forward:/cop/bbs/selectArticleDetail.do", r[1]);
		assertTrue(model.containsAttribute("subMsg"), "The rejection must surface the checkerUser message.");
	}

	@Test
	void updateByOwnerReachesTheUpdateService() throws Exception {
		Object[] r = callUpdate(OWNER, OWNER);
		StubService service = (StubService) r[0];
		assertTrue(service.updateCalled, "The owner must be able to update their own satisfaction record.");
	}

	// ---- selectSingleSatisfaction (상세조회) ----

	private static Object[] callViewDetail(String ownerUniqId, String loginUniqId) throws Exception {
		StubService service = new StubService(ownerUniqId);
		service.stored.setStsfdgCn("private feedback");
		EgovBBSSatisfactionController controller = controllerWith(service);
		bindLoginUser(loginUniqId);

		SatisfactionVO searchVO = new SatisfactionVO();
		ModelMap model = new ModelMap();
		controller.selectSingleSatisfaction(searchVO, model);
		return new Object[] { searchVO, model };
	}

	@Test
	void viewDetailByNonOwnerDoesNotExposeTheContent() throws Exception {
		Object[] r = callViewDetail(OWNER, ATTACKER);
		SatisfactionVO searchVO = (SatisfactionVO) r[0];
		ModelMap model = (ModelMap) r[1];
		assertTrue(model.containsAttribute("subMsg"), "The rejection must surface the checkerUser message.");
		assertFalse("private feedback".equals(searchVO.getStsfdgCn()),
				"A logged-in non-owner must not receive another member's satisfaction content.");
	}

	@Test
	void viewDetailByOwnerSucceeds() throws Exception {
		Object[] r = callViewDetail(OWNER, OWNER);
		SatisfactionVO searchVO = (SatisfactionVO) r[0];
		ModelMap model = (ModelMap) r[1];
		assertFalse(model.containsAttribute("subMsg"), "The owner must not be rejected.");
		assertTrue("private feedback".equals(searchVO.getStsfdgCn()), "The owner must receive their own content.");
	}
}
