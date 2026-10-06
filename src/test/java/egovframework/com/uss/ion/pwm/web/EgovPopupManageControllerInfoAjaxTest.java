package egovframework.com.uss.ion.pwm.web;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.lang.reflect.Proxy;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import egovframework.com.uss.ion.pwm.service.EgovPopupManageService;
import egovframework.com.uss.ion.pwm.service.PopupManageVO;
import jakarta.servlet.ServletException;

class EgovPopupManageControllerInfoAjaxTest {

	private static final String URL = "/uss/ion/pwm/ajaxPopupManageInfo.do";
	private static final String POPUP_ID = "POPUP_000000000001";

	private MockMvc mockMvc;
	private PopupManageVO storedPopup;
	private Exception serviceFailure;
	private int selectCount;

	@BeforeEach
	void setUp() {
		EgovPopupManageService service = (EgovPopupManageService) Proxy.newProxyInstance(
				getClass().getClassLoader(), new Class<?>[] { EgovPopupManageService.class },
				(proxy, method, args) -> {
					if (!"selectPopup".equals(method.getName())) {
						throw new AssertionError("예상하지 못한 서비스 호출: " + method.getName());
					}
					assertEquals(POPUP_ID, ((PopupManageVO) args[0]).getPopupId());
					selectCount++;
					if (serviceFailure != null) {
						throw serviceFailure;
					}
					return storedPopup;
				});
		EgovPopupManageController controller = new EgovPopupManageController();
		ReflectionTestUtils.setField(controller, "egovPopupManageService", service);
		mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
	}

	@AfterEach
	void requestedPopupIsLookedUpOnce() {
		assertEquals(1, selectCount);
	}

	@Test
	void missingPopupReturnsNotFoundWithEmptyBody() throws Exception {
		mockMvc.perform(post(URL).param("popupId", POPUP_ID))
				.andExpect(status().isNotFound())
				.andExpect(content().string(""));
	}

	@Test
	void existingPopupReturnsAllSixFields() throws Exception {
		storedPopup = popup("popup/sample");

		mockMvc.perform(post(URL).param("popupId", POPUP_ID))
				.andExpect(status().isOk())
				.andExpect(content().contentType("text/html;charset=utf-8"))
				.andExpect(content().string("popup/sample||640||480||10||20||Y"));
	}

	@Test
	void existingPopupResponseKeepsEscaping() throws Exception {
		storedPopup = popup("popup/<한글>&\"'.%2E%2F");

		mockMvc.perform(post(URL).param("popupId", POPUP_ID))
				.andExpect(status().isOk())
				.andExpect(content().contentType("text/html;charset=utf-8"))
				.andExpect(content().string("popup/&lt;한글&gt;&amp;&#34;&#39;&#46;&#46;&#47;||640||480||10||20||Y"));
	}

	@Test
	void serviceFailureIsNotTreatedAsMissingPopup() {
		serviceFailure = new IllegalStateException("팝업 조회 실패");

		ServletException thrown = assertThrows(ServletException.class,
				() -> mockMvc.perform(post(URL).param("popupId", POPUP_ID)));

		assertSame(serviceFailure, thrown.getCause());
	}

	private static PopupManageVO popup(String fileUrl) {
		PopupManageVO popup = new PopupManageVO();
		popup.setPopupId(POPUP_ID);
		popup.setFileUrl(fileUrl);
		popup.setPopupWSize("640");
		popup.setPopupHSize("480");
		popup.setPopupHlc("10");
		popup.setPopupWlc("20");
		popup.setStopVewAt("Y");
		return popup;
	}
}
