package egovframework.com.uss.ion.bnt.web;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.ui.ModelMap;

import egovframework.com.cmm.EgovMessageSource;
import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.exception.EgovAccessDeniedException;
import egovframework.com.cmm.service.EgovUserDetailsService;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.com.uss.ion.bnt.service.BndtDiaryVO;
import egovframework.com.uss.ion.bnt.service.EgovBndtManageService;

/**
 * 당직관리는 관리자 전용이라 일지 수정 화면·저장·삭제도 관리자끼리 신뢰한다(일지 작성자를 따로 확인하지 않음).
 * 당직자와 일지 작성자를 같게 하는 규칙은 없다. 일지가 없으면 거부한다.
 */
class EgovBndtDiaryOwnerTest {

	private static final String DIARY_WRITER = "USRCNFRM_WRITER";

	private final EgovUserDetailsService previous = new EgovUserDetailsHelper().getEgovUserDetailsService();
	private final Map<String, Integer> calls = new HashMap<>();

	private EgovBndtManageController controller(String registerId) {
		EgovBndtManageService service = (EgovBndtManageService) Proxy.newProxyInstance(
				getClass().getClassLoader(), new Class<?>[] { EgovBndtManageService.class }, (proxy, method, args) -> {
					calls.merge(method.getName(), 1, Integer::sum);
					return switch (method.getName()) {
						case "selectBndtDiaryRegisterId" -> registerId;
						case "selectBndtDiary" -> List.of();
						default -> null;
					};
				});
		EgovBndtManageController controller = new EgovBndtManageController();
		ReflectionTestUtils.setField(controller, "egovBndtManageService", service);
		ReflectionTestUtils.setField(controller, "egovMessageSource", new EgovMessageSource() {
			@Override
			public String getMessage(String code) {
				return code;
			}
		});
		return controller;
	}

	private static void login(String uniqId) {
		LoginVO login = new LoginVO();
		login.setUniqId(uniqId);
		new EgovUserDetailsHelper().setEgovUserDetailsService(new EgovUserDetailsService() {
			@Override
			public Object getAuthenticatedUser() {
				return login;
			}

			@Override
			public List<String> getAuthorities() {
				return List.of("ROLE_ADMIN");
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

	private static BndtDiaryVO diary() {
		BndtDiaryVO vo = new BndtDiaryVO();
		vo.setBndtId("USRCNFRM_DUTY");
		vo.setBndtDe("20260928");
		return vo;
	}

	@Test
	void diaryWriterCanModifyAndDelete() {
		EgovBndtManageController controller = controller(DIARY_WRITER);
		login(DIARY_WRITER);

		assertDoesNotThrow(() -> controller.selectBndtDiary(diary(), Map.of("cmd", "updt"), new ModelMap()));
		assertDoesNotThrow(() -> controller.updtBndtDiary(null, null, null, diary(), new ModelMap()));
		assertDoesNotThrow(() -> controller.deleteBndtDiary(diary(), new ModelMap()));
		assertTrue(calls.containsKey("updtBndtDiary"));
		assertTrue(calls.containsKey("deleteBndtDiary"));
	}

	@Test
	void otherAdministratorCanModifyAndDelete() {
		EgovBndtManageController controller = controller(DIARY_WRITER);
		login("USRCNFRM_OTHER");

		assertDoesNotThrow(() -> controller.selectBndtDiary(diary(), Map.of("cmd", "updt"), new ModelMap()));
		assertDoesNotThrow(() -> controller.updtBndtDiary(null, null, null, diary(), new ModelMap()));
		assertDoesNotThrow(() -> controller.deleteBndtDiary(diary(), new ModelMap()));
		assertTrue(calls.containsKey("updtBndtDiary"));
		assertTrue(calls.containsKey("deleteBndtDiary"));
	}

	@Test
	void missingDiaryIsRejected() {
		EgovBndtManageController controller = controller(null);
		login(DIARY_WRITER);

		assertThrows(IllegalStateException.class, () -> controller.deleteBndtDiary(diary(), new ModelMap()));
	}
}
