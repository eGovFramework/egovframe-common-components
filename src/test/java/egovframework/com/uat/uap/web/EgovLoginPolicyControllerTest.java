package egovframework.com.uat.uap.web;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

import java.util.Collections;
import java.util.List;

import org.egovframe.rte.fdl.cmmn.exception.EgovBizException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.web.servlet.mvc.support.RedirectAttributesModelMap;

import egovframework.com.cmm.EgovMessageSource;
import egovframework.com.cmm.service.EgovUserDetailsService;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.com.uat.uap.service.EgovLoginPolicyService;
import egovframework.com.uat.uap.service.LoginPolicy;
import egovframework.com.uat.uap.service.LoginPolicyVO;

class EgovLoginPolicyControllerTest {

	private EgovUserDetailsService previousUserDetailsService;

	@AfterEach
	void tearDown() {
		new EgovUserDetailsHelper().setEgovUserDetailsService(previousUserDetailsService);
	}

	@Test
	void insertLoginPolicyAddsEmplyrIdToRedirect() throws Exception {
		EgovLoginPolicyController controller = new EgovLoginPolicyController();
		StubLoginPolicyService loginPolicyService = new StubLoginPolicyService();
		controller.egovLoginPolicyService = loginPolicyService;
		controller.egovMessageSource = new StubMessageSource();
		EgovUserDetailsHelper userDetailsHelper = new EgovUserDetailsHelper();
		previousUserDetailsService = userDetailsHelper.getEgovUserDetailsService();
		userDetailsHelper.setEgovUserDetailsService(new StubUserDetailsService());

		LoginPolicy loginPolicy = new LoginPolicy();
		loginPolicy.setEmplyrId("TEST1");
		loginPolicy.setIpInfo("127.0.0.1");
		loginPolicy.setLmttAt("Y");
		loginPolicy.setSearchCondition("1");
		loginPolicy.setSearchKeyword("tester");
		loginPolicy.setPageIndex(2);

		RedirectAttributesModelMap redirectAttributes = new RedirectAttributesModelMap();

		String viewName = controller.insertLoginPolicy(loginPolicy,
				new BeanPropertyBindingResult(loginPolicy, "loginPolicy"), new ModelMap(), redirectAttributes);

		assertEquals("redirect:/uat/uap/getLoginPolicy.do", viewName);
		assertSame(loginPolicy, loginPolicyService.insertedLoginPolicy);
		assertEquals("TEST1", redirectAttributes.get("emplyrId"));
		assertEquals("1", redirectAttributes.get("searchCondition"));
		assertEquals("tester", redirectAttributes.get("searchKeyword"));
		assertEquals("2", String.valueOf(redirectAttributes.get("pageIndex")));
	}

	@Test
	void updateLoginPolicyKeepsSubmittedEmplyrId() throws Exception {
		EgovLoginPolicyController controller = new EgovLoginPolicyController();
		StubLoginPolicyService loginPolicyService = new StubLoginPolicyService();
		controller.egovLoginPolicyService = loginPolicyService;
		controller.egovMessageSource = new StubMessageSource();
		EgovUserDetailsHelper userDetailsHelper = new EgovUserDetailsHelper();
		previousUserDetailsService = userDetailsHelper.getEgovUserDetailsService();
		userDetailsHelper.setEgovUserDetailsService(new StubUserDetailsService());

		LoginPolicy loginPolicy = new LoginPolicy();
		loginPolicy.setEmplyrId("TEST1");
		loginPolicy.setIpInfo("192.168.0.10");
		loginPolicy.setLmttAt("Y");

		String viewName = controller.updateLoginPolicy(loginPolicy,
				new BeanPropertyBindingResult(loginPolicy, "loginPolicy"), new ModelMap(),
				new RedirectAttributesModelMap());

		assertEquals("redirect:/uat/uap/selectLoginPolicyList.do", viewName);
		assertSame(loginPolicy, loginPolicyService.updatedLoginPolicy);
		assertEquals("TEST1", loginPolicyService.updatedLoginPolicy.getEmplyrId());
	}

	@Test
	void updateLoginPolicyReturnsUpdateViewWhenServiceReportsFailure() throws Exception {
		EgovLoginPolicyController controller = new EgovLoginPolicyController();
		StubLoginPolicyService loginPolicyService = new StubLoginPolicyService();
		controller.egovLoginPolicyService = loginPolicyService;
		controller.egovMessageSource = new StubMessageSource();
		EgovUserDetailsHelper userDetailsHelper = new EgovUserDetailsHelper();
		previousUserDetailsService = userDetailsHelper.getEgovUserDetailsService();
		userDetailsHelper.setEgovUserDetailsService(new StubUserDetailsService());

		LoginPolicy loginPolicy = new LoginPolicy();
		loginPolicy.setEmplyrId("TEST1");
		loginPolicy.setIpInfo("192.168.0.10");
		loginPolicy.setLmttAt("Y");
		loginPolicyService.updateException = new EgovBizException("Login policy update was not persisted.");

		ModelMap model = new ModelMap();
		String viewName = controller.updateLoginPolicy(loginPolicy,
				new BeanPropertyBindingResult(loginPolicy, "loginPolicy"), model,
				new RedirectAttributesModelMap());

		assertEquals("egovframework/com/uat/uap/EgovLoginPolicyUpdt", viewName);
		assertSame(loginPolicy, loginPolicyService.updatedLoginPolicy);
		assertEquals("fail.common.update", model.get("message"));
	}

	private static class StubMessageSource extends EgovMessageSource {
		@Override
		public String getMessage(String code) {
			return code;
		}
	}

	private static class StubLoginPolicyService implements EgovLoginPolicyService {

		private LoginPolicy insertedLoginPolicy;
		private LoginPolicy updatedLoginPolicy;
		private Exception updateException;

		@Override
		public List<LoginPolicyVO> selectLoginPolicyList(LoginPolicyVO loginPolicyVO) {
			return Collections.emptyList();
		}

		@Override
		public int selectLoginPolicyListTotCnt(LoginPolicyVO loginPolicyVO) {
			return 0;
		}

		@Override
		public LoginPolicyVO selectLoginPolicy(LoginPolicyVO loginPolicyVO) {
			return null;
		}

		@Override
		public void insertLoginPolicy(LoginPolicy loginPolicy) {
			insertedLoginPolicy = loginPolicy;
		}

		@Override
		public void updateLoginPolicy(LoginPolicy loginPolicy) throws Exception {
			updatedLoginPolicy = loginPolicy;
			if (updateException != null) {
				throw updateException;
			}
		}

		@Override
		public void deleteLoginPolicy(LoginPolicy loginPolicy) {
			// Not used by this test.
		}
	}

	private static class StubUserDetailsService implements EgovUserDetailsService {

		@Override
		public Object getAuthenticatedUser() {
			return null;
		}

		@Override
		public List<String> getAuthorities() {
			return Collections.emptyList();
		}

		@Override
		public Boolean isAuthenticated() {
			return Boolean.FALSE;
		}
	}
}
