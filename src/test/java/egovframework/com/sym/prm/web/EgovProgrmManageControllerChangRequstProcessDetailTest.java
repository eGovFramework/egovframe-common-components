package egovframework.com.sym.prm.web;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

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
 * 프로그램변경요청처리 상세조회가 없는 요청을 만났을 때의 회귀 테스트.
 *
 * selectProgrmChangeRequst는 MyBatis selectOne을 그대로 돌려주므로 해당 행이 없으면 null이다.
 * 상세조회가 그 결과를 바로 역참조하면 NullPointerException으로 500이 난다.
 */
class EgovProgrmManageControllerChangRequstProcessDetailTest {

	/** 해당 행이 없어 selectOne이 null을 돌려주는 상황을 재현하는 스텁. */
	private static final class StubService implements EgovProgrmManageService {

		@Override
		public ProgrmManageDtlVO selectProgrmChangeRequst(ProgrmManageDtlVO vo) {
			return null;
		}

		@Override
		public void deleteProgrmChangeRequst(ProgrmManageDtlVO vo) {
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

	private static void bindLoginUser() {
		LoginVO login = new LoginVO();
		login.setId("TEST1");
		login.setUniqId("USRCNFRM_00000000000");
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

	@Test
	void missingChangeRequestGoesBackToTheListInsteadOfThrowing() throws Exception {
		EgovProgrmManageController controller = controllerWith(new StubService());
		bindLoginUser();

		ProgrmManageDtlVO progrmManageDtlVO = new ProgrmManageDtlVO();
		progrmManageDtlVO.setProgrmFileNm("NotExists.java");
		progrmManageDtlVO.setRqesterNo(9999);
		ModelMap model = new ModelMap();

		String view = controller.selectProgrmChangRequstProcess(progrmManageDtlVO, model);

		assertEquals("forward:/sym/prm/EgovProgramChangeRequstProcessListSelect.do", view,
				"없는 변경요청이면 목록으로 되돌려보내야 한다.");
		assertNotNull(model.get("resultMsg"), "실패 사유를 화면에 남겨야 한다.");
	}
}
