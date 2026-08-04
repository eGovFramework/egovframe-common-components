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

import egovframework.com.cmm.EgovMessageSource;
import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.service.EgovUserDetailsService;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.com.cop.bbs.service.EgovBBSSatisfactionService;
import egovframework.com.cop.bbs.service.Satisfaction;
import egovframework.com.cop.bbs.service.SatisfactionVO;

/**
 * updateSatisfaction의 소유권 검증 회귀 테스트.
 *
 * 로그인 사용자가 자신이 등록하지 않은 만족도 레코드를 수정하려 하면 차단돼야 한다(IDOR 방지).
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
			throw new UnsupportedOperationException();
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
		assertFalse(service.updateCalled,
				"A logged-in non-owner must not be able to update another member's satisfaction record.");
	}

	@Test
	void updateByOwnerReachesTheUpdateService() throws Exception {
		Object[] r = callUpdate(OWNER, OWNER);
		StubService service = (StubService) r[0];
		assertTrue(service.updateCalled, "The owner must be able to update their own satisfaction record.");
	}
}
