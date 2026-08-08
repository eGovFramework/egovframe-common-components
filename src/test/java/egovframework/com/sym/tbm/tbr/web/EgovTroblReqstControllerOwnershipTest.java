package egovframework.com.sym.tbm.tbr.web;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.lang.reflect.Field;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.bind.support.SimpleSessionStatus;

import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.service.EgovUserDetailsService;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.com.sym.tbm.tbr.service.EgovTroblReqstService;
import egovframework.com.sym.tbm.tbr.service.TroblReqst;
import egovframework.com.sym.tbm.tbr.service.TroblReqstVO;

/**
 * updateTroblReqst·requstTroblReqst·requstTroblReqstCancl의 소유권 검증 회귀 테스트.
 *
 * 이 컨트롤러는 등록자ID를 loginVO.getUniqId()가 아니라 loginVO.getId()(로그인 아이디)로 저장한다
 * (insertTroblReqst 참조). 그래서 이 카드는 다른 카드들의 uniqId 기반 egovAssertAdminOrOwner를
 * 그대로 재사용할 수 없고, getId() 기준으로 비교하는 egovAssertAdminOrOwnerById를 새로 둔다.
 * 만약 uniqId 기준으로 잘못 비교했다면, 실제 신청자(OWNER)조차 항상 차단되어 아래
 * "OwnerReachesTheService" 계열 테스트가 실패했을 것이다.
 */
class EgovTroblReqstControllerOwnershipTest {

	private static final String OWNER_LOGIN_ID = "owner1";
	private static final String OWNER_UNIQ_ID = "USRCNFRM_00000000001";
	private static final String ATTACKER_LOGIN_ID = "attacker1";
	private static final String ATTACKER_UNIQ_ID = "USRCNFRM_00000000009";

	/** selectTroblReqst는 저장된 등록자(로그인ID 기준)를 돌려주고, 각 서비스 호출 여부를 기록하는 스텁. */
	private static final class StubService implements EgovTroblReqstService {
		private final TroblReqstVO stored;
		private boolean updateCalled = false;
		private boolean requstCalled = false;

		StubService(String ownerLoginId) {
			this.stored = new TroblReqstVO();
			this.stored.setFrstRegisterId(ownerLoginId);
		}

		@Override
		public List<TroblReqstVO> selectTroblReqstList(TroblReqstVO troblReqstVO) {
			throw new UnsupportedOperationException();
		}

		@Override
		public int selectTroblReqstListTotCnt(TroblReqstVO troblReqstVO) {
			throw new UnsupportedOperationException();
		}

		@Override
		public TroblReqstVO selectTroblReqst(TroblReqstVO troblReqstVO) {
			return stored;
		}

		@Override
		public TroblReqstVO insertTroblReqst(TroblReqst troblReqst, TroblReqstVO troblReqstVO) {
			throw new UnsupportedOperationException();
		}

		@Override
		public void updateTroblReqst(TroblReqst troblReqst) {
			updateCalled = true;
		}

		@Override
		public void deleteTroblReqst(TroblReqst troblReqst) {
			throw new UnsupportedOperationException();
		}

		@Override
		public void requstTroblReqst(TroblReqst troblReqst) {
			requstCalled = true;
		}
	}

	private static void bindLoginUser(String loginId, String uniqId, List<String> authorities) {
		LoginVO login = new LoginVO();
		login.setId(loginId);
		login.setUniqId(uniqId);
		EgovUserDetailsService stub = new EgovUserDetailsService() {
			@Override
			public Object getAuthenticatedUser() {
				return login;
			}

			@Override
			public List<String> getAuthorities() {
				return authorities;
			}

			@Override
			public Boolean isAuthenticated() {
				return Boolean.TRUE;
			}
		};
		new EgovUserDetailsHelper().setEgovUserDetailsService(stub);
	}

	private static EgovTroblReqstController controllerWith(StubService service) throws Exception {
		EgovTroblReqstController controller = new EgovTroblReqstController();
		Field serviceField = EgovTroblReqstController.class.getDeclaredField("egovTroblReqstService");
		serviceField.setAccessible(true);
		serviceField.set(controller, service);

		Field messageSourceField = EgovTroblReqstController.class.getDeclaredField("egovMessageSource");
		messageSourceField.setAccessible(true);
		messageSourceField.set(controller, new egovframework.com.cmm.EgovMessageSource() {
			@Override
			public String getMessage(String code) {
				return code;
			}
		});
		return controller;
	}

	private static TroblReqst requestFor(String troblId) {
		TroblReqst troblReqst = new TroblReqst();
		troblReqst.setTroblId(troblId);
		troblReqst.setTroblNm("edited");
		troblReqst.setTroblKnd("01");
		return troblReqst;
	}

	// ---- updateTroblReqst ----

	@Test
	void updateByNonOwnerDoesNotReachTheUpdateService() throws Exception {
		StubService service = new StubService(OWNER_LOGIN_ID);
		EgovTroblReqstController controller = controllerWith(service);
		bindLoginUser(ATTACKER_LOGIN_ID, ATTACKER_UNIQ_ID, List.of());

		TroblReqst troblReqst = requestFor("T0000001");
		BindingResult bindingResult = new BeanPropertyBindingResult(troblReqst, "troblReqst");
		SessionStatus status = new SimpleSessionStatus();
		ModelMap model = new ModelMap();

		assertThrows(IllegalStateException.class,
				() -> controller.updateTroblReqst(new TroblReqstVO(), troblReqst, bindingResult, status, model),
				"A logged-in non-owner must not be able to update another member's incident request.");
		assertTrue(!service.updateCalled, "updateTroblReqst service must not be reached by a non-owner.");
	}

	@Test
	void updateByOwnerReachesTheUpdateService() throws Exception {
		StubService service = new StubService(OWNER_LOGIN_ID);
		EgovTroblReqstController controller = controllerWith(service);
		bindLoginUser(OWNER_LOGIN_ID, OWNER_UNIQ_ID, List.of());

		TroblReqst troblReqst = requestFor("T0000001");
		BindingResult bindingResult = new BeanPropertyBindingResult(troblReqst, "troblReqst");
		SessionStatus status = new SimpleSessionStatus();
		ModelMap model = new ModelMap();

		controller.updateTroblReqst(new TroblReqstVO(), troblReqst, bindingResult, status, model);
		assertTrue(service.updateCalled,
				"The requester must be able to update their own incident request (getId() must be compared, not getUniqId()).");
	}

	@Test
	void updateByAdminReachesTheUpdateServiceEvenWhenNotOwner() throws Exception {
		StubService service = new StubService(OWNER_LOGIN_ID);
		EgovTroblReqstController controller = controllerWith(service);
		bindLoginUser(ATTACKER_LOGIN_ID, ATTACKER_UNIQ_ID, List.of("ROLE_ADMIN"));

		TroblReqst troblReqst = requestFor("T0000001");
		BindingResult bindingResult = new BeanPropertyBindingResult(troblReqst, "troblReqst");
		SessionStatus status = new SimpleSessionStatus();
		ModelMap model = new ModelMap();

		controller.updateTroblReqst(new TroblReqstVO(), troblReqst, bindingResult, status, model);
		assertTrue(service.updateCalled, "An admin must be able to update any member's incident request.");
	}

	// ---- requstTroblReqst (처리요청) ----

	@Test
	void requstByNonOwnerDoesNotReachTheService() throws Exception {
		StubService service = new StubService(OWNER_LOGIN_ID);
		EgovTroblReqstController controller = controllerWith(service);
		bindLoginUser(ATTACKER_LOGIN_ID, ATTACKER_UNIQ_ID, List.of());

		TroblReqst troblReqst = requestFor("T0000001");
		SessionStatus status = new SimpleSessionStatus();
		ModelMap model = new ModelMap();

		assertThrows(IllegalStateException.class,
				() -> controller.requstTroblReqst("T0000001", troblReqst, status, model),
				"A logged-in non-owner must not be able to submit another member's incident request for processing.");
		assertTrue(!service.requstCalled, "requstTroblReqst service must not be reached by a non-owner.");
	}

	@Test
	void requstByOwnerReachesTheService() throws Exception {
		StubService service = new StubService(OWNER_LOGIN_ID);
		EgovTroblReqstController controller = controllerWith(service);
		bindLoginUser(OWNER_LOGIN_ID, OWNER_UNIQ_ID, List.of());

		TroblReqst troblReqst = requestFor("T0000001");
		SessionStatus status = new SimpleSessionStatus();
		ModelMap model = new ModelMap();

		controller.requstTroblReqst("T0000001", troblReqst, status, model);
		assertTrue(service.requstCalled, "The requester must be able to submit their own incident request.");
	}

	// ---- requstTroblReqstCancl (처리취소) ----

	@Test
	void requstCanclByNonOwnerDoesNotReachTheService() throws Exception {
		StubService service = new StubService(OWNER_LOGIN_ID);
		EgovTroblReqstController controller = controllerWith(service);
		bindLoginUser(ATTACKER_LOGIN_ID, ATTACKER_UNIQ_ID, List.of());

		TroblReqst troblReqst = requestFor("T0000001");
		SessionStatus status = new SimpleSessionStatus();
		ModelMap model = new ModelMap();

		assertThrows(IllegalStateException.class,
				() -> controller.requstTroblReqstCancl("T0000001", troblReqst, status, model),
				"A logged-in non-owner must not be able to cancel another member's incident request processing.");
		assertTrue(!service.requstCalled, "requstTroblReqst service must not be reached by a non-owner via cancel.");
	}

	@Test
	void requstCanclByOwnerReachesTheService() throws Exception {
		StubService service = new StubService(OWNER_LOGIN_ID);
		EgovTroblReqstController controller = controllerWith(service);
		bindLoginUser(OWNER_LOGIN_ID, OWNER_UNIQ_ID, List.of());

		TroblReqst troblReqst = requestFor("T0000001");
		SessionStatus status = new SimpleSessionStatus();
		ModelMap model = new ModelMap();

		controller.requstTroblReqstCancl("T0000001", troblReqst, status, model);
		assertTrue(service.requstCalled, "The requester must be able to cancel their own incident request processing.");
	}
}
