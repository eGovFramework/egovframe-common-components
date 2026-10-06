package egovframework.com.uss.ion.evt.web;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.lang.reflect.Proxy;
import java.util.concurrent.atomic.AtomicBoolean;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.support.SimpleSessionStatus;

import egovframework.com.cmm.EgovMessageSource;
import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.service.EgovUserDetailsService;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.com.uss.ion.evt.service.EgovEventManageService;
import egovframework.com.uss.ion.evt.service.EventManage;
import egovframework.com.uss.ion.evt.service.EventManageVO;

/**
 * 행사 삭제가 그 행사의 참가신청을 뒤에 남기지 않는지 확인한다.
 *
 * <p>행사 삭제는 COMTNEVENTMANAGE 한 행만 지운다. 상세화면은 참가신청 건수가 0일 때만
 * 삭제버튼을 노출하지만(EgovEventReqstDetail.jsp) 삭제 요청을 받는 쪽에는 같은 확인이 없어,
 * 화면을 그린 뒤 신청이 들어오면 참가신청을 남긴 채 행사만 지우는 요청이 실행된다.</p>
 */
class EgovEventManageControllerTest {

	private static final String LOGIN_UNIQ_ID = "USRCNFRM_00000000001";

	private EgovUserDetailsService previousUserDetailsService;

	/** 삭제는 등록자 본인만 가능하므로 로그인 사용자를 묶고, 저장본의 등록자를 같은 사용자로 둔다. */
	@BeforeEach
	void bindLoginUser() {
		previousUserDetailsService = new EgovUserDetailsHelper().getEgovUserDetailsService();
		LoginVO loginVO = new LoginVO();
		loginVO.setUniqId(LOGIN_UNIQ_ID);
		new EgovUserDetailsHelper().setEgovUserDetailsService((EgovUserDetailsService) Proxy.newProxyInstance(
				EgovUserDetailsService.class.getClassLoader(), new Class<?>[] { EgovUserDetailsService.class },
				(proxy, method, args) -> {
					if ("getAuthenticatedUser".equals(method.getName())) {
						return loginVO;
					}
					if ("isAuthenticated".equals(method.getName())) {
						return Boolean.TRUE;
					}
					if ("getAuthorities".equals(method.getName())) {
						return java.util.Collections.emptyList();
					}
					return null;
				}));
	}

	@AfterEach
	void restoreLoginUser() {
		new EgovUserDetailsHelper().setEgovUserDetailsService(previousUserDetailsService);
	}

	private EgovEventManageController controller;
	private final AtomicBoolean deleted = new AtomicBoolean(false);
	private int eventAtdrnCount = 1;

	@BeforeEach
	void setUp() {
		EgovEventManageService service = (EgovEventManageService) Proxy.newProxyInstance(
				getClass().getClassLoader(), new Class<?>[] { EgovEventManageService.class },
				(proxy, method, args) -> {
					switch (method.getName()) {
					case "selectEventManage":
						EventManageVO found = new EventManageVO();
						found.setEventId("EVT_000000000001");
						found.setEventAtdrnCount(eventAtdrnCount);
						found.setFrstRegisterId(LOGIN_UNIQ_ID);
						return found;
					case "deleteEventManage":
						deleted.set(true);
						return null;
					default:
						return null;
					}
				});

		controller = new EgovEventManageController();
		ReflectionTestUtils.setField(controller, "egovEventManageService", service);
		ReflectionTestUtils.setField(controller, "egovMessageSource", new EgovMessageSource() {
			@Override
			public String getMessage(String code) {
				return code;
			}
		});
	}

	@Test
	void deleteEventManageIsRefusedWhenEventStillHasAtdrn() throws Exception {
		eventAtdrnCount = 1;
		ModelMap model = new ModelMap();

		controller.deleteEventManage(event(), new SimpleSessionStatus(), model);

		assertFalse(deleted.get(), "참가신청이 있으면 행사를 지우지 않아야 한다");
		assertEquals("true", model.get("eventAtdrnExist"), "화면에 알릴 표시가 있어야 한다");
	}

	@Test
	void deleteEventManageProceedsWhenEventHasNoAtdrn() throws Exception {
		eventAtdrnCount = 0;
		ModelMap model = new ModelMap();

		controller.deleteEventManage(event(), new SimpleSessionStatus(), model);

		assertTrue(deleted.get(), "참가신청이 없는 행사는 지울 수 있어야 한다");
		assertFalse(model.containsAttribute("eventAtdrnExist"));
	}

	private EventManage event() {
		EventManage eventManage = new EventManage();
		eventManage.setEventId("EVT_000000000001");
		return eventManage;
	}
}
