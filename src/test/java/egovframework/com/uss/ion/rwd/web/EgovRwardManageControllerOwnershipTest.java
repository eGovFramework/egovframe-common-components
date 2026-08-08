package egovframework.com.uss.ion.rwd.web;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.bind.support.SimpleSessionStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import egovframework.com.cmm.EgovMessageSource;
import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.service.EgovFileMngService;
import egovframework.com.cmm.service.EgovUserDetailsService;
import egovframework.com.cmm.service.FileVO;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.com.uss.ion.rwd.service.EgovRwardManageService;
import egovframework.com.uss.ion.rwd.service.RwardManage;
import egovframework.com.uss.ion.rwd.service.RwardManageVO;

/**
 * 포상관리 수정·삭제·승인의 소유권 검증 회귀 테스트.
 *
 * updtRwardManage·deleteRwardManage는 신청자(FRST_REGISTER_ID) 본인 또는 관리자만,
 * updtRwardManageConfm(승인/반려)은 지정된 승인권자(SANCTNER_ID) 또는 관리자만 통과해야
 * 한다. 수정 전 코드는 승인 처리에서 SANCTNER_ID 대조 없이 호출자를 그대로 승인권자로
 * 취급했고, 수정·삭제는 신청자 대조 자체가 없었다(승인 UPDATE문 자체는 SANCTNER_ID를
 * 갱신하지 않아 그 시도는 no-op이었지만, 대조 없이 승인·반려 상태를 바꿀 수 있었다).
 */
class EgovRwardManageControllerOwnershipTest {

	private static final String OWNER = "USRCNFRM_00000000001";
	private static final String SANCTNER = "USRCNFRM_00000000002";
	private static final String OUTSIDER = "USRCNFRM_00000000009";

	private static final class StubService implements EgovRwardManageService {
		private final RwardManageVO stored;
		private boolean updateCalled = false;
		private boolean deleteCalled = false;
		private boolean confmCalled = false;

		StubService(String ownerUniqId, String sanctnerUniqId) {
			this.stored = new RwardManageVO();
			this.stored.setFrstRegisterId(ownerUniqId);
			this.stored.setSanctnerId(sanctnerUniqId);
		}

		@Override
		public RwardManageVO selectRwardManage(RwardManageVO rwardManageVO) {
			return stored;
		}

		@Override
		public void updtRwardManage(RwardManage rwardManage) {
			updateCalled = true;
		}

		@Override
		public void deleteRwardManage(RwardManage rwardManage) {
			deleteCalled = true;
		}

		@Override
		public void updtRwardManageConfm(RwardManage rwardManage) {
			confmCalled = true;
		}

		@Override
		public List<RwardManageVO> selectRwardManageList(RwardManageVO rwardManageVO) {
			throw new UnsupportedOperationException();
		}

		@Override
		public int selectRwardManageListTotCnt(RwardManageVO rwardManageVO) {
			throw new UnsupportedOperationException();
		}

		@Override
		public void insertRwardManage(RwardManage rwardManage) {
			throw new UnsupportedOperationException();
		}

		@Override
		public List<RwardManageVO> selectRwardManageConfmList(RwardManageVO rwardManageVO) {
			throw new UnsupportedOperationException();
		}

		@Override
		public int selectRwardManageConfmListTotCnt(RwardManageVO rwardManageVO) {
			throw new UnsupportedOperationException();
		}
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

	private static EgovFileMngService noopFileMngService() {
		return new EgovFileMngService() {
			@Override
			public List<FileVO> selectFileInfs(FileVO fvo) {
				throw new UnsupportedOperationException();
			}

			@Override
			public String insertFileInf(FileVO fvo) {
				throw new UnsupportedOperationException();
			}

			@Override
			public String insertFileInfs(List<FileVO> fvoList) {
				throw new UnsupportedOperationException();
			}

			@Override
			public void updateFileInfs(List<FileVO> fvoList) {
				throw new UnsupportedOperationException();
			}

			@Override
			public void deleteFileInfs(List<FileVO> fvoList) {
				throw new UnsupportedOperationException();
			}

			@Override
			public void deleteFileInf(FileVO fvo) {
				throw new UnsupportedOperationException();
			}

			@Override
			public FileVO selectFileInf(FileVO fvo) {
				throw new UnsupportedOperationException();
			}

			@Override
			public int getMaxFileSN(FileVO fvo) {
				throw new UnsupportedOperationException();
			}

			@Override
			public void deleteAllFileInf(FileVO fvo) {
				// no-op for the ownership test
			}

			@Override
			public Map<String, Object> selectFileListByFileNm(FileVO fvo) {
				throw new UnsupportedOperationException();
			}

			@Override
			public List<FileVO> selectImageFileList(FileVO vo) {
				throw new UnsupportedOperationException();
			}
		};
	}

	/**
	 * MockMultipartHttpServletRequest는 이 로컬 환경에서 NoClassDefFoundError로 깨진다
	 * ([[shell-and-egov-test-env-traps]]). 컨트롤러가 실제로 쓰는 getFiles(String)만
	 * 최소 구현한 프록시로 대체한다.
	 */
	private static MultipartHttpServletRequest emptyMultipartRequest() {
		return (MultipartHttpServletRequest) java.lang.reflect.Proxy.newProxyInstance(
				EgovRwardManageControllerOwnershipTest.class.getClassLoader(),
				new Class<?>[] { MultipartHttpServletRequest.class },
				(proxy, method, args) -> {
					if ("getFiles".equals(method.getName())) {
						return Collections.<MultipartFile>emptyList();
					}
					Class<?> returnType = method.getReturnType();
					if (returnType == boolean.class) {
						return false;
					}
					return null;
				});
	}

	private static EgovRwardManageController controllerWith(StubService service) {
		EgovRwardManageController controller = new EgovRwardManageController();
		setPrivateField(controller, "egovRwardManageService", service);
		setPrivateField(controller, "fileMngService", noopFileMngService());
		controller.egovMessageSource = new EgovMessageSource() {
			@Override
			public String getMessage(String code) {
				return code;
			}
		};
		return controller;
	}

	private static RwardManage requestFor(String rwardId) {
		RwardManage rm = new RwardManage();
		rm.setRwardId(rwardId);
		return rm;
	}

	// ---- updtRwardManage ----

	@Test
	void updateByOutsiderIsRejected() {
		StubService service = new StubService(OWNER, SANCTNER);
		EgovRwardManageController controller = controllerWith(service);
		bindLoginUser(OUTSIDER, List.of());

		RwardManage rm = requestFor("1");
		RwardManageVO vo = new RwardManageVO();
		vo.setRwardId("1");
		BindingResult bindingResult = new BeanPropertyBindingResult(rm, "rwardManage");
		ModelMap model = new ModelMap();

		assertThrows(IllegalStateException.class,
				() -> controller.updtRwardManage("N", emptyMultipartRequest(), rm, bindingResult, vo,
						new SimpleSessionStatus(), model),
				"A logged-in non-applicant must not be able to update another member's reward nomination.");
		assertTrue(!service.updateCalled, "updtRwardManage service must not be reached by a non-applicant.");
	}

	@Test
	void updateByApplicantSucceeds() throws Exception {
		StubService service = new StubService(OWNER, SANCTNER);
		EgovRwardManageController controller = controllerWith(service);
		bindLoginUser(OWNER, List.of());

		RwardManage rm = requestFor("1");
		RwardManageVO vo = new RwardManageVO();
		vo.setRwardId("1");
		BindingResult bindingResult = new BeanPropertyBindingResult(rm, "rwardManage");
		ModelMap model = new ModelMap();

		controller.updtRwardManage("N", emptyMultipartRequest(), rm, bindingResult, vo, new SimpleSessionStatus(),
				model);
		assertTrue(service.updateCalled, "The applicant must be able to update their own reward nomination.");
	}

	// ---- deleteRwardManage ----

	@Test
	void deleteByOutsiderIsRejected() {
		StubService service = new StubService(OWNER, SANCTNER);
		EgovRwardManageController controller = controllerWith(service);
		bindLoginUser(OUTSIDER, List.of());

		ModelMap model = new ModelMap();
		assertThrows(IllegalStateException.class,
				() -> controller.deleteRwardManage(requestFor("1"), new SimpleSessionStatus(), model),
				"A logged-in non-applicant must not be able to delete another member's reward nomination.");
		assertTrue(!service.deleteCalled, "deleteRwardManage service must not be reached by a non-applicant.");
	}

	@Test
	void deleteByApplicantSucceeds() throws Exception {
		StubService service = new StubService(OWNER, SANCTNER);
		EgovRwardManageController controller = controllerWith(service);
		bindLoginUser(OWNER, List.of());

		ModelMap model = new ModelMap();
		controller.deleteRwardManage(requestFor("1"), new SimpleSessionStatus(), model);
		assertTrue(service.deleteCalled, "The applicant must be able to delete their own reward nomination.");
	}

	@Test
	void deleteByAdminSucceedsEvenWhenNotApplicant() throws Exception {
		StubService service = new StubService(OWNER, SANCTNER);
		EgovRwardManageController controller = controllerWith(service);
		bindLoginUser(OUTSIDER, List.of("ROLE_ADMIN"));

		ModelMap model = new ModelMap();
		controller.deleteRwardManage(requestFor("1"), new SimpleSessionStatus(), model);
		assertTrue(service.deleteCalled, "An admin must be able to delete any reward nomination.");
	}

	// ---- updtRwardManageConfm (승인/반려) ----

	@Test
	void confirmByNonSanctnerIsRejected() {
		StubService service = new StubService(OWNER, SANCTNER);
		EgovRwardManageController controller = controllerWith(service);
		bindLoginUser(OUTSIDER, List.of());

		RwardManage rm = requestFor("1");
		BindingResult bindingResult = new BeanPropertyBindingResult(rm, "rwardManage");
		ModelMap model = new ModelMap();

		assertThrows(IllegalStateException.class,
				() -> controller.updtRwardManageConfm(rm, bindingResult, new SimpleSessionStatus(), model),
				"A logged-in user who is not the designated approver must not be able to confirm/reject someone else's reward nomination.");
		assertTrue(!service.confmCalled, "updtRwardManageConfm service must not be reached by a non-sanctner.");
	}

	@Test
	void confirmBySanctnerSucceeds() throws Exception {
		StubService service = new StubService(OWNER, SANCTNER);
		EgovRwardManageController controller = controllerWith(service);
		bindLoginUser(SANCTNER, List.of());

		RwardManage rm = requestFor("1");
		BindingResult bindingResult = new BeanPropertyBindingResult(rm, "rwardManage");
		ModelMap model = new ModelMap();

		controller.updtRwardManageConfm(rm, bindingResult, new SimpleSessionStatus(), model);
		assertTrue(service.confmCalled, "The designated approver must be able to confirm/reject the reward nomination.");
	}

	@Test
	void confirmByAdminSucceedsEvenWhenNotSanctner() throws Exception {
		StubService service = new StubService(OWNER, SANCTNER);
		EgovRwardManageController controller = controllerWith(service);
		bindLoginUser(OUTSIDER, List.of("ROLE_ADMIN"));

		RwardManage rm = requestFor("1");
		BindingResult bindingResult = new BeanPropertyBindingResult(rm, "rwardManage");
		ModelMap model = new ModelMap();

		controller.updtRwardManageConfm(rm, bindingResult, new SimpleSessionStatus(), model);
		assertTrue(service.confmCalled, "An admin must be able to confirm/reject any reward nomination.");
	}
}
