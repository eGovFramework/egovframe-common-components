package egovframework.com.dam.app.web;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.egovframe.rte.psl.dataaccess.util.EgovMap;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BeanPropertyBindingResult;

import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.exception.EgovAccessDeniedException;
import egovframework.com.cmm.service.EgovUserDetailsService;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.com.dam.app.service.EgovKnoAppraisalService;
import egovframework.com.dam.app.service.KnoAppraisal;
import egovframework.com.dam.app.service.KnoAppraisalVO;

/**
 * 지식평가 상세·수정 화면·저장은 지식유형의 지정 전문가만 허용한다(관리자 예외 없음).
 */
class EgovKnoAppraisalControllerExpertTest {

	private static final String EXPERT = "USRCNFRM_EXPERT";

	private final EgovUserDetailsService previous = new EgovUserDetailsHelper().getEgovUserDetailsService();

	/** 유형 001 의 전문가는 EXPERT 한 명. 대상이 없으면 null. */
	private static final class StubService implements EgovKnoAppraisalService {
		boolean exists = true;
		boolean updated = false;

		@Override
		public KnoAppraisal selectKnoAppraisal(KnoAppraisal knoAppraisal) {
			if (!exists) {
				return null;
			}
			KnoAppraisal stored = new KnoAppraisal();
			stored.setKnoId(knoAppraisal.getKnoId());
			stored.setKnoTypeCd("001");
			return stored;
		}

		@Override
		public int selectKnoAppraisalExpertCnt(KnoAppraisal knoAppraisal) {
			return "001".equals(knoAppraisal.getKnoTypeCd()) && EXPERT.equals(knoAppraisal.getSpeId()) ? 1 : 0;
		}

		@Override
		public void updateKnoAppraisal(KnoAppraisal knoAppraisal) {
			updated = true;
		}

		@Override
		public List<EgovMap> selectKnoAppraisalList(KnoAppraisalVO searchVO) {
			throw new UnsupportedOperationException();
		}

		@Override
		public int selectKnoAppraisalTotCnt(KnoAppraisalVO searchVO) {
			throw new UnsupportedOperationException();
		}

		@Override
		public void insertKnoAppraisal(KnoAppraisal knoAppraisal) {
			throw new UnsupportedOperationException();
		}

		@Override
		public void deleteKnoAppraisal(KnoAppraisal knoAppraisal) {
			throw new UnsupportedOperationException();
		}
	}

	private static void login(String uniqId, List<String> authorities) {
		LoginVO login = new LoginVO();
		login.setUniqId(uniqId);
		new EgovUserDetailsHelper().setEgovUserDetailsService(new EgovUserDetailsService() {
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
		});
	}

	@AfterEach
	void restore() {
		new EgovUserDetailsHelper().setEgovUserDetailsService(previous);
	}

	private static EgovKnoAppraisalController controller(StubService service) {
		EgovKnoAppraisalController controller = new EgovKnoAppraisalController();
		ReflectionTestUtils.setField(controller, "knoAppraisalService", service);
		return controller;
	}

	private static KnoAppraisal request() {
		KnoAppraisal knoAppraisal = new KnoAppraisal();
		knoAppraisal.setKnoId("DMID_1");
		return knoAppraisal;
	}

	@Test
	void expertOpensDetailAndModifyAndSaves() {
		StubService service = new StubService();
		EgovKnoAppraisalController controller = controller(service);
		login(EXPERT, List.of("ROLE_USER"));

		assertDoesNotThrow(() -> controller.selectKnoAppraisal(request(), new ModelMap()));
		assertDoesNotThrow(() -> controller.updateKnoAppraisalView(request(), new ModelMap()));
		KnoAppraisal req = request();
		assertDoesNotThrow(() -> controller.updateKnoAppraisal(req, new BeanPropertyBindingResult(req, "knoId"), new ModelMap()));
		assertTrue(service.updated);
	}

	@Test
	void adminWhoIsNotTheExpertIsRejectedEverywhere() {
		StubService service = new StubService();
		EgovKnoAppraisalController controller = controller(service);
		login("USRCNFRM_ADMIN", List.of("ROLE_ADMIN"));

		assertThrows(EgovAccessDeniedException.class, () -> controller.selectKnoAppraisal(request(), new ModelMap()));
		assertThrows(EgovAccessDeniedException.class, () -> controller.updateKnoAppraisalView(request(), new ModelMap()));
		KnoAppraisal req = request();
		assertThrows(EgovAccessDeniedException.class,
				() -> controller.updateKnoAppraisal(req, new BeanPropertyBindingResult(req, "knoId"), new ModelMap()));
		assertFalse(service.updated);
	}

	@Test
	void missingTargetIsRejected() {
		StubService service = new StubService();
		service.exists = false;
		login(EXPERT, List.of("ROLE_USER"));

		assertThrows(IllegalStateException.class, () -> controller(service).selectKnoAppraisal(request(), new ModelMap()));
	}
}
