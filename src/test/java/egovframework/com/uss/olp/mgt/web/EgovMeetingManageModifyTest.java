package egovframework.com.uss.olp.mgt.web;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.lang.reflect.Proxy;
import java.util.List;
import java.util.Map;

import org.egovframe.rte.psl.dataaccess.util.EgovMap;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.parallel.ResourceLock;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import egovframework.com.cmm.EgovMessageSource;
import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.service.EgovUserDetailsService;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.com.uss.olp.mgt.service.EgovMeetingManageService;
import egovframework.com.uss.olp.mgt.service.MeetingManageVO;

@ResourceLock("EgovUserDetailsHelper")
class EgovMeetingManageModifyTest {

	private static final String VIEW = "egovframework/com/uss/olp/mgt/EgovMeetingManageModify";
	private static final String PREFIX = "/uss/olp/mgt/";
	private EgovUserDetailsService previousAuth;
	private LocalValidatorFactoryBean validator;
	private MockMvc mvc;
	private EgovMap stored;
	private MeetingManageVO updated;
	private boolean authenticated = true;
	private int reads;
	private int writes;

	@BeforeEach
	void setUp() {
		previousAuth = (EgovUserDetailsService) ReflectionTestUtils.getField(EgovUserDetailsHelper.class, "egovUserDetailsService");
		LoginVO user = new LoginVO();
		user.setUniqId("TEST_USER");
		new EgovUserDetailsHelper().setEgovUserDetailsService(new EgovUserDetailsService() {
			public Object getAuthenticatedUser() { return authenticated ? user : null; }
			public List<String> getAuthorities() { return List.of(); }
			public Boolean isAuthenticated() { return authenticated; }
		});
		stored = new EgovMap();
		validInput().forEach((key, values) -> stored.put(key, values.get(0)));
		stored.put("mtgNm", "저장된 회의명");
		stored.put("mtgSn", 1);
		stored.put("mtgCo", 2);
		stored.put("mtgBeginTime", "09:00");
		stored.put("mtgEndTime", "09:05");
		stored.put("mnaerIds", "stored.login");
		stored.put("mnaerNm", "저장된 사용자명");
		stored.put("clsdrMtgAt", "1");
		stored.put("mtgResultEnnc", "1");
		EgovMeetingManageService service = (EgovMeetingManageService) Proxy.newProxyInstance(
				getClass().getClassLoader(), new Class<?>[] {EgovMeetingManageService.class}, (proxy, method, args) -> {
					assertEquals("MEETING_1", ((MeetingManageVO) args[0]).getMtgId());
					if ("selectMeetingManageDetail".equals(method.getName())) {
						reads++;
						return List.of(stored);
					}
					if ("updateMeetingManage".equals(method.getName())) {
						writes++;
						updated = (MeetingManageVO) args[0];
						return null;
					}
					throw new AssertionError("Unexpected service call: " + method.getName());
				});
		EgovMeetingManageController controller = new EgovMeetingManageController();
		ReflectionTestUtils.setField(controller, "egovMeetingManageService", service);
		ReflectionTestUtils.setField(controller, "egovMessageSource", new EgovMessageSource() {
			@Override
			public String getMessage(String code) { return code; }
		});
		validator = new LocalValidatorFactoryBean();
		validator.afterPropertiesSet();
		mvc = MockMvcBuilders.standaloneSetup(controller).setValidator(validator).build();
	}

	@AfterEach
	void tearDown() {
		new EgovUserDetailsHelper().setEgovUserDetailsService(previousAuth);
		if (validator != null) validator.close();
	}

	@Test
	void initialViewBindsStoredFieldsAndNormalizesTimeSelections() throws Exception {
		Map<String, Object> model = mvc.perform(post(PREFIX + "EgovMeetingManageModifyView.do")
				.param("mtgId", "MEETING_1").param("mtgNm", "request must not replace stored title"))
				.andExpect(view().name(VIEW)).andReturn().getModelAndView().getModel();
		MeetingManageVO form = (MeetingManageVO) model.get("meetingManageVO");
		BeanWrapperImpl fields = new BeanWrapperImpl(form);
		for (String key : validInput().keySet()) {
			if (key.matches("mtg(Begin|End)(HH|MM)") || key.equals("mnaerNm")) continue;
			assertEquals(String.valueOf(stored.get(key)), fields.getPropertyValue(key), key);
		}
		assertEquals("stored.login", form.getMnaerNm());
		assertEquals("9", form.getMtgBeginHH());
		assertEquals("0", form.getMtgBeginMM());
		assertEquals("9", form.getMtgEndHH());
		assertEquals("5", form.getMtgEndMM());
		assertEquals("1", form.getClsdrMtgAt());
		assertEquals("1", form.getMtgResultEnnc());
		assertSame(form, ((BindingResult) model.get(BindingResult.MODEL_KEY_PREFIX + "meetingManageVO")).getTarget());
		assertEquals(1, reads);
		assertEquals(0, writes);
	}

	@Test
	void initialViewHandlesNullableDisplayFields() throws Exception {
		stored.put("mnaerIds", null);
		stored.put("etcMatter", null);
		stored.put("mtgBeginTime", null);
		MeetingManageVO form = (MeetingManageVO) mvc.perform(post(PREFIX + "EgovMeetingManageModifyView.do")
				.param("mtgId", "MEETING_1")).andExpect(view().name(VIEW)).andReturn().getModelAndView()
				.getModel().get("meetingManageVO");
		assertEquals("", form.getMnaerNm());
		assertEquals("", form.getEtcMatter());
		assertEquals("", form.getMtgBeginHH());
		assertEquals("", form.getMtgBeginTime());
	}

	@ParameterizedTest
	@ValueSource(booleans = {false, true})
	void validationErrorPreservesAllSubmittedFieldsAndCheckboxState(boolean checked) throws Exception {
		MultiValueMap<String, String> params = validInput();
		params.set("mtgSn", "abc");
		params.set("mtgNm", "수정한 <회의명> & \"인용\"");
		params.set("mtgMtrCn", "수정한 안건\n</textarea><script>test</script>");
		if (checked) {
			params.set("clsdrMtgAt", "1");
			params.set("mtgResultEnnc", "1");
		}
		Map<String, Object> model = mvc.perform(post(PREFIX + "EgovMeetingManageModify.do").params(params))
				.andExpect(view().name(VIEW)).andExpect(model().attributeHasFieldErrors("meetingManageVO", "mtgSn"))
				.andReturn().getModelAndView().getModel();
		MeetingManageVO form = (MeetingManageVO) model.get("meetingManageVO");
		BeanWrapperImpl fields = new BeanWrapperImpl(form);
		params.forEach((key, values) -> {
			if (!key.startsWith("_")) assertEquals(values.get(0), fields.getPropertyValue(key), key);
		});
		assertEquals(checked ? "1" : "", form.getClsdrMtgAt());
		assertEquals(checked ? "1" : "", form.getMtgResultEnnc());
		BindingResult errors = (BindingResult) model.get(BindingResult.MODEL_KEY_PREFIX + "meetingManageVO");
		assertNotNull(errors);
		assertSame(form, errors.getTarget());
		assertEquals(1, errors.getErrorCount());
		assertEquals("abc", errors.getFieldError("mtgSn").getRejectedValue());
		assertFalse(model.containsKey("resultList"));
		assertEquals(0, reads);
		assertEquals(0, writes);
	}

	@Test
	void validSubmissionUpdatesOnceAndReturnsToList() throws Exception {
		MultiValueMap<String, String> params = validInput();
		mvc.perform(post(PREFIX + "EgovMeetingManageModify.do").params(params))
				.andExpect(redirectedUrl(PREFIX + "EgovMeetingManageList.do"));
		assertEquals(0, reads);
		assertEquals(1, writes);
		assertEquals("TEST_USER", updated.getLastUpdusrId());
		assertEquals("", updated.getClsdrMtgAt());
		assertEquals("", updated.getMtgResultEnnc());
		BeanWrapperImpl fields = new BeanWrapperImpl(updated);
		params.forEach((key, values) -> assertEquals(values.get(0), fields.getPropertyValue(key), key));
	}

	@ParameterizedTest
	@ValueSource(strings = {"EgovMeetingManageModifyView.do", "EgovMeetingManageModify.do"})
	void unauthenticatedRequestsDoNotReadOrUpdateMeetings(String endpoint) throws Exception {
		authenticated = false;
		mvc.perform(post(PREFIX + endpoint).params(validInput()))
				.andExpect(view().name("redirect:/uat/uia/egovLoginUsr.do"));
		assertEquals(0, reads);
		assertEquals(0, writes);
	}

	private static MultiValueMap<String, String> validInput() {
		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		String[][] fields = {
			{"mtgId", "MEETING_1"}, {"mtgNm", "수정한 회의명"}, {"mtgMtrCn", "수정한 안건"},
			{"mtgSn", "3"}, {"mtgCo", "4"}, {"mtgDe", "2026-09-15"}, {"mtgPlace", "수정한 장소"},
			{"mtgBeginTime", "10:30"}, {"mtgEndTime", "11:45"}, {"mtgBeginHH", "10"}, {"mtgBeginMM", "30"},
			{"mtgEndHH", "11"}, {"mtgEndMM", "45"}, {"readngBeginDe", "2026-09-16"}, {"readngAt", "N"},
			{"mtgResultCn", "수정한 결과"}, {"etcMatter", "수정한 기타사항"}, {"mngtDeptId", "NEW_DEPT"},
			{"mngtDeptNm", "수정한 부서"}, {"mnaerId", "NEW_USER"}, {"mnaerNm", "new.login"},
			{"mnaerDeptId", "NEW_USER_DEPT"}, {"mnaerDeptNm", "수정한 주관자부서"}, {"mtnAt", "Y"},
			{"nonatdrnCo", "5"}, {"atdrnCo", "6"}
		};
		for (String[] field : fields) params.set(field[0], field[1]);
		return params;
	}
}
