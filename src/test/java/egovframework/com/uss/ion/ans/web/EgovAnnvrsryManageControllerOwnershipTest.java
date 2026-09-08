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
 * selectAnnvrsryManage(169행)·updateAnnvrsryManage(316행)·deleteAnnvrsryManage(366행)는 읽어온
 * 레코드의 USID를 로그인 사용자와 대조하고 관리자만 예외로 두는데, 이 경로에만 그 대조가 없다.
 */
class EgovAnnvrsryManageControllerOwnershipTest {

	private static final String OWNER_UNIQ_ID = "USRCNFRM_00000000001";
	private static final String OTHER_UNIQ_ID = "USRCNFRM_00000000009";
	private static final String ADMIN_UNIQ_ID = "USRCNFRM_00000000002";

	/** 요청한 annId의 소유자를 고정해 돌려주는 스텁. */
	private static final class StubService implements EgovAnnvrsryManageService {
		private final String ownerUniqId;

		private StubService(String ownerUniqId) {
			this.ownerUniqId = ownerUniqId;
		}

		@Override
		public AnnvrsryManageVO selectAnnvrsryManage(AnnvrsryManageVO annvrsryManageVO) {
			AnnvrsryManageVO stored = new AnnvrsryManageVO();
			stored.setAnnId(annvrsryManageVO.getAnnId());
			stored.setUsid(ownerUniqId);
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
			throw new UnsupportedOperationException();
		}

		@Override
		public void deleteAnnvrsryManage(AnnvrsryManage annvrsryManage) {
			throw new UnsupportedOperationException();
		}

		@Override
		public List<AnnvrsryManageVO> selectAnnvrsryGdcc(AnnvrsryManageVO annvrsryManageVO) {
			throw new UnsupportedOperationException();
		}

		@Override
		public int selectAnnvrsryManageDplctAt(AnnvrsryManage annvrsryManage) {
			throw new UnsupportedOperationException();
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
}
