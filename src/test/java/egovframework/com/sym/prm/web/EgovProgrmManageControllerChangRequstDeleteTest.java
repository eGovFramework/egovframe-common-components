package egovframework.com.sym.prm.web;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.lang.reflect.Field;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.ui.ModelMap;

import egovframework.com.cmm.ComDefaultVO;
import egovframework.com.cmm.EgovMessageSource;
import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.service.EgovUserDetailsService;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.com.sym.prm.service.EgovProgrmManageService;
import egovframework.com.sym.prm.service.ProgrmManageDtlVO;
import egovframework.com.sym.prm.service.ProgrmManageVO;

/**
 * 프로그램변경요청 삭제의 요청자 확인 회귀 테스트.
 *
 * 요청자ID(RQESTER_ID)는 등록 시 LoginVO.getUniqId()(ESNTL_ID)로 저장되고,
 * 수정(updateProgrmChangeRequst)도 같은 값으로 비교한다.
 * 삭제만 LoginVO.getId()(로그인 아이디)로 비교하면 요청자 본인도 항상 차단된다.
 */
class EgovProgrmManageControllerChangRequstDeleteTest {

	private static final String OWNER_LOGIN_ID = "TEST1";
	private static final String OWNER_UNIQ_ID = "USRCNFRM_00000000000";
	private static final String OTHER_LOGIN_ID = "webmaster";
	private static final String OTHER_UNIQ_ID = "USRCNFRM_99999999999";

	private static final String PROGRM_FILE_NM = "EgovSample.java";
	private static final int RQESTER_NO = 1;

	/** 삭제 호출 여부만 기록하는 스텁. */
	private static final class StubService implements EgovProgrmManageService {

		private boolean deleteCalled = false;

		@Override
		public void deleteProgrmChangeRequst(ProgrmManageDtlVO vo) {
			deleteCalled = true;
		}

		@Override
		public ProgrmManageDtlVO selectProgrmChangeRequst(ProgrmManageDtlVO vo) {
			throw new UnsupportedOperationException();
		}

		@Override
		public ProgrmManageVO selectProgrm(ProgrmManageVO vo) {
			throw new UnsupportedOperationException();
		}

		@Override
		public List<ProgrmManageVO> selectProgrmList(ComDefaultVO vo) {
			throw new UnsupportedOperationException();
		}

		@Override
		public int selectProgrmListTotCnt(ComDefaultVO vo) {
			throw new UnsupportedOperationException();
		}

		@Override
		public void insertProgrm(ProgrmManageVO vo) {
			throw new UnsupportedOperationException();
		}

		@Override
		public void updateProgrm(ProgrmManageVO vo) {
			throw new UnsupportedOperationException();
		}

		@Override
		public void deleteProgrm(ProgrmManageVO vo) {
			throw new UnsupportedOperationException();
		}

		@Override
		public int selectProgrmNMTotCnt(ComDefaultVO vo) {
			throw new UnsupportedOperationException();
		}

		@Override
		public List<ProgrmManageDtlVO> selectProgrmChangeRequstList(ComDefaultVO vo) {
			throw new UnsupportedOperationException();
		}

		@Override
		public int selectProgrmChangeRequstListTotCnt(ComDefaultVO vo) {
			throw new UnsupportedOperationException();
		}

		@Override
		public void insertProgrmChangeRequst(ProgrmManageDtlVO vo) {
			throw new UnsupportedOperationException();
		}

		@Override
		public void updateProgrmChangeRequst(ProgrmManageDtlVO vo) {
			throw new UnsupportedOperationException();
		}

		@Override
		public ProgrmManageDtlVO selectProgrmChangeRequstNo(ProgrmManageDtlVO vo) {
			throw new UnsupportedOperationException();
		}

		@Override
		public List<?> selectChangeRequstProcessList(ComDefaultVO vo) {
			throw new UnsupportedOperationException();
		}

		@Override
		public int selectChangeRequstProcessListTotCnt(ComDefaultVO vo) {
			throw new UnsupportedOperationException();
		}

		@Override
		public void updateProgrmChangeRequstProcess(ProgrmManageDtlVO vo) {
			throw new UnsupportedOperationException();
		}

		@Override
		public void deleteProgrmManageList(String checkedProgrmFileNmForDel) {
			throw new UnsupportedOperationException();
		}

		@Override
		public ProgrmManageDtlVO selectRqesterEmail(ProgrmManageDtlVO vo) {
			throw new UnsupportedOperationException();
		}
	}

	private static void bindLoginUser(String loginId, String uniqId) {
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
				return List.of();
			}

			@Override
			public Boolean isAuthenticated() {
				return Boolean.TRUE;
			}
		};
		new EgovUserDetailsHelper().setEgovUserDetailsService(stub);
	}

	private static EgovProgrmManageController controllerWith(StubService service) throws Exception {
		EgovProgrmManageController controller = new EgovProgrmManageController();

		Field serviceField = EgovProgrmManageController.class.getDeclaredField("progrmManageService");
		serviceField.setAccessible(true);
		serviceField.set(controller, service);

		Field messageSourceField = EgovProgrmManageController.class.getDeclaredField("egovMessageSource");
		messageSourceField.setAccessible(true);
		messageSourceField.set(controller, new EgovMessageSource() {
			@Override
			public String getMessage(String code) {
				return code;
			}
		});
		return controller;
	}

	/** 상세화면이 숨은 필드로 돌려주는 값, 즉 DB에 저장된 요청자ID(ESNTL_ID)를 담은 폼. */
	private static ProgrmManageDtlVO submittedForm(String storedRqesterPersonId) {
		ProgrmManageDtlVO form = new ProgrmManageDtlVO();
		form.setProgrmFileNm(PROGRM_FILE_NM);
		form.setRqesterNo(RQESTER_NO);
		form.setRqesterPersonId(storedRqesterPersonId);
		return form;
	}

	@Test
	void requesterCanDeleteOwnChangeRequest() throws Exception {
		StubService service = new StubService();
		EgovProgrmManageController controller = controllerWith(service);
		bindLoginUser(OWNER_LOGIN_ID, OWNER_UNIQ_ID);

		controller.deleteProgrmChangeRequst(submittedForm(OWNER_UNIQ_ID), new ModelMap());

		assertTrue(service.deleteCalled,
				"등록 시 uniqId로 저장된 요청자 본인은 자신의 변경요청을 삭제할 수 있어야 한다.");
	}

	@Test
	void otherUserCannotDeleteChangeRequest() throws Exception {
		StubService service = new StubService();
		EgovProgrmManageController controller = controllerWith(service);
		bindLoginUser(OTHER_LOGIN_ID, OTHER_UNIQ_ID);

		controller.deleteProgrmChangeRequst(submittedForm(OWNER_UNIQ_ID), new ModelMap());

		assertFalse(service.deleteCalled, "요청자가 아닌 사용자의 삭제는 서비스까지 도달하면 안 된다.");
	}
}
