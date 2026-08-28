package egovframework.com.uss.ion.vct.web;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
import egovframework.com.uss.ion.vct.service.EgovVcatnManageService;
import egovframework.com.uss.ion.vct.service.VcatnManageVO;

/**
 * updtVcatnManage의 신청자ID 위조 방지 회귀 테스트.
 *
 * updtVcatnManage는 내부적으로 기존 레코드를 삭제(서비스 계층에서 소유자·관리자만 허용)한 뒤 새 값으로
 * 재등록한다. 신규 등록(insertVcatnManage)은 이미 applcntId를 로그인 사용자로 강제하는데, 수정 경로만
 * 그 정규화가 빠져 있었다 — 자기 신청 건을 수정하면서 폼의 applcntId 히든필드만 타인 uniqId로 조작하면
 * 그 사람 명의로 레코드가 재등록되고 연차 잔여일수 갱신도 그 사람 앞으로 걸렸다.
 */
class EgovVcatnManageControllerOwnershipTest {

	private static final String OWNER_UNIQ_ID = "USRCNFRM_00000000001";
	private static final String VICTIM_UNIQ_ID = "USRCNFRM_00000000009";
	private static final String ADMIN_UNIQ_ID = "USRCNFRM_00000000002";

	/** updtVcatnManage에 실제로 전달된 VO를 그대로 기록하는 스텁. */
	private static final class StubService implements EgovVcatnManageService {
		private VcatnManageVO lastUpdtArg;

		@Override
		public List<VcatnManageVO> selectVcatnManageList(VcatnManageVO vcatnManageVO) {
			throw new UnsupportedOperationException();
		}

		@Override
		public int selectVcatnManageListTotCnt(VcatnManageVO vcatnManageVO) {
			throw new UnsupportedOperationException();
		}

		@Override
		public VcatnManageVO selectVcatnManage(VcatnManageVO vcatnManageVO) {
			throw new UnsupportedOperationException();
		}

		@Override
		public String insertVcatnManage(VcatnManageVO vcatnManageVO) {
			throw new UnsupportedOperationException();
		}

		@Override
		public String updtVcatnManage(VcatnManageVO vcatnManageVO) {
			lastUpdtArg = vcatnManageVO;
			return "01";
		}

		@Override
		public void deleteVcatnManage(VcatnManageVO vcatnManageVO) {
			throw new UnsupportedOperationException();
		}

		@Override
		public int selectVcatnManageDplctAt(VcatnManageVO vcatnManageVO) {
			throw new UnsupportedOperationException();
		}

		@Override
		public List<VcatnManageVO> selectVcatnManageConfmList(VcatnManageVO vcatnManageVO) {
			throw new UnsupportedOperationException();
		}

		@Override
		public int selectVcatnManageConfmListTotCnt(VcatnManageVO vcatnManageVO) {
			throw new UnsupportedOperationException();
		}

		@Override
		public void updtVcatnManageConfm(VcatnManageVO vcatnManageVO) {
			throw new UnsupportedOperationException();
		}

		@Override
		public VcatnManageVO selectIndvdlYrycManage(String sUsid) {
			throw new UnsupportedOperationException();
		}

		@Override
		public void updtIndvdlYrycManage(VcatnManageVO vcatnManageVO) {
			throw new UnsupportedOperationException();
		}
	}

	private static void bindLoginUser(String uniqId) {
		bindLoginUser(uniqId, List.of());
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

	private static EgovVcatnManageController controllerWith(StubService service) throws Exception {
		EgovVcatnManageController controller = new EgovVcatnManageController();
		Field serviceField = EgovVcatnManageController.class.getDeclaredField("egovVcatnManageService");
		serviceField.setAccessible(true);
		serviceField.set(controller, service);

		Field messageSourceField = EgovVcatnManageController.class.getDeclaredField("egovMessageSource");
		messageSourceField.setAccessible(true);
		messageSourceField.set(controller, new egovframework.com.cmm.EgovMessageSource() {
			@Override
			public String getMessage(String code) {
				return code;
			}
		});
		return controller;
	}

	@Test
	void updtVcatnManageIgnoresAForgedApplcntIdAndKeepsTheRecordOwner() throws Exception {
		StubService service = new StubService();
		EgovVcatnManageController controller = controllerWith(service);
		bindLoginUser(OWNER_UNIQ_ID);

		VcatnManageVO vcatnManageVO = new VcatnManageVO();
		vcatnManageVO.setApplcntIdKey(OWNER_UNIQ_ID); // 실제로 삭제될 자기 소유 레코드의 키
		vcatnManageVO.setVcatnSeKey("01");
		vcatnManageVO.setBgndeKey("20260101");
		vcatnManageVO.setEnddeKey("20260101");
		vcatnManageVO.setApplcntId(VICTIM_UNIQ_ID); // 조작된 값: 재등록될 신청자ID를 타인으로 위조 시도
		vcatnManageVO.setVcatnSe("01");
		vcatnManageVO.setBgnde("20260102");
		vcatnManageVO.setEndde("20260102");

		BindingResult bindingResult = new BeanPropertyBindingResult(vcatnManageVO, "vcatnManageVO");
		SessionStatus status = new SimpleSessionStatus();
		ModelMap model = new ModelMap();

		controller.updtVcatnManage(vcatnManageVO, bindingResult, status, model);

		assertEquals(OWNER_UNIQ_ID, service.lastUpdtArg.getApplcntId(),
				"수정 경로는 재등록되는 신청자ID를 폼 제출값이 아니라 삭제 대상 레코드의 소유자로 강제해야 한다.");
	}

	@Test
	void updtVcatnManageKeepsTheOriginalOwnerWhenAnAdministratorEditsSomeoneElsesLeave() throws Exception {
		StubService service = new StubService();
		EgovVcatnManageController controller = controllerWith(service);
		bindLoginUser(ADMIN_UNIQ_ID, List.of("ROLE_ADMIN"));

		VcatnManageVO vcatnManageVO = new VcatnManageVO();
		// 관리자가 타인(VICTIM)의 휴가를 수정한다. 삭제는 서비스 계층에서 ROLE_ADMIN 으로 허용된다.
		vcatnManageVO.setApplcntIdKey(VICTIM_UNIQ_ID);
		vcatnManageVO.setVcatnSeKey("01");
		vcatnManageVO.setBgndeKey("20260101");
		vcatnManageVO.setEnddeKey("20260101");
		vcatnManageVO.setApplcntId(VICTIM_UNIQ_ID);
		vcatnManageVO.setVcatnSe("01");
		vcatnManageVO.setBgnde("20260102");
		vcatnManageVO.setEndde("20260102");

		BindingResult bindingResult = new BeanPropertyBindingResult(vcatnManageVO, "vcatnManageVO");
		SessionStatus status = new SimpleSessionStatus();
		ModelMap model = new ModelMap();

		controller.updtVcatnManage(vcatnManageVO, bindingResult, status, model);

		assertEquals(VICTIM_UNIQ_ID, service.lastUpdtArg.getApplcntId(),
				"관리자가 타인의 휴가를 수정해도 재등록되는 신청자ID는 원래 소유자여야 한다."
						+ " 로그인 사용자로 덮으면 소유자와 연차 차감 대상이 관리자로 바뀐다.");
	}
}
