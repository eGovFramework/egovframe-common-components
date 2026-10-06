package egovframework.com.uss.ion.ans.web;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.ui.ModelMap;

import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.service.EgovUserDetailsService;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.com.uss.ion.ans.service.AnnvrsryManage;
import egovframework.com.uss.ion.ans.service.AnnvrsryManageVO;
import egovframework.com.uss.ion.ans.service.EgovAnnvrsryManageService;

/**
 * selectAnnvrsryGdcc의 소유권 검증 회귀 테스트.
 *
 * 알림 화면은 요청이 보낸 annId로 기념일을 그대로 읽어 화면에 싣는다. 같은 컨트롤러의
 * 상세 조회와 같이 레코드의 USID(기념일 대상자)를 로그인 사용자와 대조하고 관리자만 예외로 둔다.
 * 수정·삭제는 대상자 본인만 허용한다(사용자부재와 같은 당사자 기준).
 */
class EgovAnnvrsryManageControllerOwnershipTest {

	private static final String OWNER_UNIQ_ID = "USRCNFRM_00000000001";
	private static final String OTHER_UNIQ_ID = "USRCNFRM_00000000009";
	private static final String ADMIN_UNIQ_ID = "USRCNFRM_00000000002";

	/** 요청한 annId의 소유자를 고정해 돌려주는 스텁. */
	private static final class StubService implements EgovAnnvrsryManageService {
		private final String ownerUniqId;
		private boolean updated;
		private boolean deleted;

		private StubService(String ownerUniqId) {
			this.ownerUniqId = ownerUniqId;
		}

		@Override
		public AnnvrsryManageVO selectAnnvrsryManage(AnnvrsryManageVO annvrsryManageVO) {
			AnnvrsryManageVO stored = new AnnvrsryManageVO();
			stored.setAnnId(annvrsryManageVO.getAnnId());
			stored.setUsid(ownerUniqId);
			// 일괄 등록처럼 등록자(관리자)와 대상자가 다른 경우
			stored.setFrstRegisterId(ADMIN_UNIQ_ID);
			stored.setAnnvrsryNm("결혼기념일");
			stored.setAnnvrsryDe("20261225");
			stored.setCldrSe("1");
			stored.setAnnvrsrySetup("Y");
			return stored;
		}

		@Override
		public List<AnnvrsryManageVO> selectAnnvrsryManageList(AnnvrsryManageVO annvrsryManageVO) {
			throw new UnsupportedOperationException();
		}

		@Override
		public int selectAnnvrsryManageListTotCnt(AnnvrsryManageVO annvrsryManageVO) {
			throw new UnsupportedOperationException();
		}

		@Override
		public void insertAnnvrsryManage(AnnvrsryManage annvrsryManage) {
			throw new UnsupportedOperationException();
		}

		@Override
		public void updateAnnvrsryManage(AnnvrsryManage annvrsryManage) {
			updated = true;
		}

		@Override
		public void deleteAnnvrsryManage(AnnvrsryManage annvrsryManage) {
			deleted = true;
		}

		@Override
		public List<AnnvrsryManageVO> selectAnnvrsryGdcc(AnnvrsryManageVO annvrsryManageVO) {
			throw new UnsupportedOperationException();
		}

		@Override
		public int selectAnnvrsryManageDplctAt(AnnvrsryManage annvrsryManage) {
			return 0;
		}

		@Override
		public List<AnnvrsryManageVO> selectAnnvrsryManageBnde(InputStream inputStream) {
			throw new UnsupportedOperationException();
		}

		@Override
		public void insertAnnvrsryManageBnde(AnnvrsryManageVO annvrsryManageVO, String checkedAnnvrsryManageForInsert) {
			throw new UnsupportedOperationException();
		}
	}

	private static void bindLoginUser(String uniqId, List<String> authorities) {
		LoginVO login = new LoginVO();
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

	private static EgovAnnvrsryManageController controllerWith(StubService service) throws Exception {
		EgovAnnvrsryManageController controller = new EgovAnnvrsryManageController();
		Field serviceField = EgovAnnvrsryManageController.class.getDeclaredField("egovAnnvrsryManageService");
		serviceField.setAccessible(true);
		serviceField.set(controller, service);

		Field messageSourceField = EgovAnnvrsryManageController.class.getDeclaredField("egovMessageSource");
		messageSourceField.setAccessible(true);
		messageSourceField.set(controller, new egovframework.com.cmm.EgovMessageSource() {
			@Override
			public String getMessage(String code) {
				return code;
			}
		});
		return controller;
	}

	private static AnnvrsryManageVO request(String annId) {
		AnnvrsryManageVO annvrsryManageVO = new AnnvrsryManageVO();
		annvrsryManageVO.setAnnId(annId);
		return annvrsryManageVO;
	}

	@Test
	void selectAnnvrsryGdccRejectsAnotherUsersAnniversary() throws Exception {
		EgovAnnvrsryManageController controller = controllerWith(new StubService(OTHER_UNIQ_ID));
		bindLoginUser(OWNER_UNIQ_ID, List.of());
		ModelMap model = new ModelMap();

		assertThrows(IllegalStateException.class,
				() -> controller.selectAnnvrsryGdcc(request("ANN_0000000000009"), model),
				"남의 기념일 annId로 알림 화면을 요청하면 거부해야 한다.");
	}

	@Test
	void selectAnnvrsryGdccAllowsTheOwner() throws Exception {
		EgovAnnvrsryManageController controller = controllerWith(new StubService(OWNER_UNIQ_ID));
		bindLoginUser(OWNER_UNIQ_ID, List.of());
		ModelMap model = new ModelMap();

		String view = controller.selectAnnvrsryGdcc(request("ANN_0000000000001"), model);

		assertEquals("egovframework/com/uss/ion/ans/EgovAnnvrsryGdcc", view,
				"소유자 본인의 요청은 그대로 알림 화면을 받아야 한다.");
	}

	@Test
	void selectAnnvrsryGdccAllowsAnAdministrator() throws Exception {
		EgovAnnvrsryManageController controller = controllerWith(new StubService(OTHER_UNIQ_ID));
		bindLoginUser(ADMIN_UNIQ_ID, List.of("ROLE_ADMIN"));
		ModelMap model = new ModelMap();

		String view = controller.selectAnnvrsryGdcc(request("ANN_0000000000009"), model);

		assertEquals("egovframework/com/uss/ion/ans/EgovAnnvrsryGdcc", view,
				"관리자는 형제 경로와 마찬가지로 타인의 기념일도 볼 수 있어야 한다.");
	}

	// ---- 수정·삭제: 기념일 대상자(usid) 본인만, 등록자·관리자 예외 없음 ----

	private static AnnvrsryManage target(String annId) {
		AnnvrsryManage annvrsryManage = new AnnvrsryManage();
		annvrsryManage.setAnnId(annId);
		return annvrsryManage;
	}

	@Test
	void targetPersonUpdatesAndDeletes() throws Exception {
		StubService service = new StubService(OWNER_UNIQ_ID);
		EgovAnnvrsryManageController controller = controllerWith(service);
		bindLoginUser(OWNER_UNIQ_ID, List.of("ROLE_ADMIN"));

		AnnvrsryManage req = target("ANN_0000000000001");
		controller.updateAnnvrsryManage(req, new org.springframework.validation.BeanPropertyBindingResult(req, "annvrsryManage"),
				request("ANN_0000000000001"), new org.springframework.web.bind.support.SimpleSessionStatus(), new ModelMap());
		controller.deleteAnnvrsryManage(target("ANN_0000000000001"), new org.springframework.web.bind.support.SimpleSessionStatus(), new ModelMap());

		org.junit.jupiter.api.Assertions.assertTrue(service.updated && service.deleted, "대상자 본인은 수정·삭제할 수 있어야 한다.");
	}

	@Test
	void registrantAdministratorWhoIsNotTheTargetIsRejected() throws Exception {
		StubService service = new StubService(OWNER_UNIQ_ID);
		EgovAnnvrsryManageController controller = controllerWith(service);
		bindLoginUser(ADMIN_UNIQ_ID, List.of("ROLE_ADMIN"));

		AnnvrsryManage req = target("ANN_0000000000001");
		assertThrows(IllegalStateException.class, () -> controller.updateAnnvrsryManage(req,
				new org.springframework.validation.BeanPropertyBindingResult(req, "annvrsryManage"), request("ANN_0000000000001"),
				new org.springframework.web.bind.support.SimpleSessionStatus(), new ModelMap()));
		assertThrows(IllegalStateException.class, () -> controller.deleteAnnvrsryManage(target("ANN_0000000000001"),
				new org.springframework.web.bind.support.SimpleSessionStatus(), new ModelMap()),
				"일괄 등록한 관리자(등록자)라도 대상자가 아니면 삭제할 수 없다.");
		org.junit.jupiter.api.Assertions.assertFalse(service.updated || service.deleted);
	}
}
