package egovframework.com.uat.uap.web;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;

import java.lang.reflect.Proxy;
import java.util.Collections;
import java.util.List;

import org.egovframe.rte.fdl.crypto.EgovEnvCryptoService;
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
	void insertLoginPolicyAddsEmplyrIdToModel() throws Exception {
		EgovLoginPolicyController controller = new EgovLoginPolicyController();
		StubLoginPolicyService loginPolicyService = new StubLoginPolicyService();
		controller.egovLoginPolicyService = loginPolicyService;
		controller.egovMessageSource = new StubMessageSource();
		controller.cryptoService = cryptoService();
		EgovUserDetailsHelper userDetailsHelper = new EgovUserDetailsHelper();
		previousUserDetailsService = userDetailsHelper.getEgovUserDetailsService();
		userDetailsHelper.setEgovUserDetailsService(new StubUserDetailsService());

		LoginPolicy loginPolicy = new LoginPolicy();
		loginPolicy.setEmplyrId("TEST1");
		loginPolicy.setEmplyrIdEncrypt(encryptEmplyrId("TEST1"));
		loginPolicy.setIpInfo("127.0.0.1");
		loginPolicy.setLmttAt("Y");
		loginPolicy.setSearchCondition("1");
		loginPolicy.setSearchKeyword("tester");
		loginPolicy.setPageIndex(2);

		ModelMap model = new ModelMap();

		String viewName = controller.insertLoginPolicy(loginPolicy,
				new BeanPropertyBindingResult(loginPolicy, "loginPolicy"), model,
				new RedirectAttributesModelMap());

		assertEquals("redirect:/uat/uap/getLoginPolicy.do", viewName);
		assertSame(loginPolicy, loginPolicyService.insertedLoginPolicy);
		assertEquals("TEST1", model.get("emplyrId"));
		assertEquals("1", model.get("searchCondition"));
		assertEquals("tester", model.get("searchKeyword"));
		assertEquals("2", String.valueOf(model.get("pageIndex")));
	}

	@Test
	void insertLoginPolicyRedirectsToListWhenEncryptedEmplyrIdDoesNotMatch() throws Exception {
		EgovLoginPolicyController controller = new EgovLoginPolicyController();
		StubLoginPolicyService loginPolicyService = new StubLoginPolicyService();
		controller.egovLoginPolicyService = loginPolicyService;
		controller.egovMessageSource = new StubMessageSource();
		controller.cryptoService = cryptoService();
		EgovUserDetailsHelper userDetailsHelper = new EgovUserDetailsHelper();
		previousUserDetailsService = userDetailsHelper.getEgovUserDetailsService();
		userDetailsHelper.setEgovUserDetailsService(new StubUserDetailsService());

		LoginPolicy loginPolicy = new LoginPolicy();
		loginPolicy.setEmplyrId("TEST1");
		loginPolicy.setEmplyrIdEncrypt(encryptEmplyrId("TEST2"));
		loginPolicy.setIpInfo("127.0.0.1");
		loginPolicy.setLmttAt("Y");

		RedirectAttributesModelMap redirectAttributes = new RedirectAttributesModelMap();
		String viewName = controller.insertLoginPolicy(loginPolicy,
				new BeanPropertyBindingResult(loginPolicy, "loginPolicy"), new ModelMap(), redirectAttributes);

		assertEquals("redirect:/uat/uap/selectLoginPolicyList.do", viewName);
		assertNull(loginPolicyService.insertedLoginPolicy);
		assertEquals("fail.common.insert", redirectAttributes.getFlashAttributes().get("message"));
	}

	@Test
	void updateLoginPolicyAcceptsMatchingEncryptedEmplyrId() throws Exception {
		EgovLoginPolicyController controller = new EgovLoginPolicyController();
		StubLoginPolicyService loginPolicyService = new StubLoginPolicyService();
		controller.egovLoginPolicyService = loginPolicyService;
		controller.egovMessageSource = new StubMessageSource();
		controller.cryptoService = cryptoService();
		EgovUserDetailsHelper userDetailsHelper = new EgovUserDetailsHelper();
		previousUserDetailsService = userDetailsHelper.getEgovUserDetailsService();
		userDetailsHelper.setEgovUserDetailsService(new StubUserDetailsService());

		LoginPolicy loginPolicy = new LoginPolicy();
		loginPolicy.setEmplyrId("TEST1");
		loginPolicy.setEmplyrIdEncrypt(encryptEmplyrId("TEST1"));
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
	void updateLoginPolicyRedirectsToListWhenEncryptedEmplyrIdDoesNotMatch() throws Exception {
		EgovLoginPolicyController controller = new EgovLoginPolicyController();
		StubLoginPolicyService loginPolicyService = new StubLoginPolicyService();
		controller.egovLoginPolicyService = loginPolicyService;
		controller.egovMessageSource = new StubMessageSource();
		controller.cryptoService = cryptoService();
		EgovUserDetailsHelper userDetailsHelper = new EgovUserDetailsHelper();
		previousUserDetailsService = userDetailsHelper.getEgovUserDetailsService();
		userDetailsHelper.setEgovUserDetailsService(new StubUserDetailsService());

		LoginPolicy loginPolicy = new LoginPolicy();
		loginPolicy.setEmplyrId("TEST1");
		loginPolicy.setEmplyrIdEncrypt(encryptEmplyrId("TEST2"));
		loginPolicy.setIpInfo("192.168.0.10");
		loginPolicy.setLmttAt("Y");

		RedirectAttributesModelMap redirectAttributes = new RedirectAttributesModelMap();
		String viewName = controller.updateLoginPolicy(loginPolicy,
				new BeanPropertyBindingResult(loginPolicy, "loginPolicy"), new ModelMap(),
				redirectAttributes);

		assertEquals("redirect:/uat/uap/selectLoginPolicyList.do", viewName);
		assertNull(loginPolicyService.updatedLoginPolicy);
		assertEquals("fail.common.update", redirectAttributes.getFlashAttributes().get("message"));
	}

	@Test
	void deleteLoginPolicyAcceptsMatchingEncryptedEmplyrId() throws Exception {
		EgovLoginPolicyController controller = new EgovLoginPolicyController();
		StubLoginPolicyService loginPolicyService = new StubLoginPolicyService();
		controller.egovLoginPolicyService = loginPolicyService;
		controller.egovMessageSource = new StubMessageSource();
		controller.cryptoService = cryptoService();

		LoginPolicy loginPolicy = new LoginPolicy();
		loginPolicy.setEmplyrId("TEST1");
		loginPolicy.setEmplyrIdEncrypt(encryptEmplyrId("TEST1"));

		ModelMap model = new ModelMap();
		String viewName = controller.deleteLoginPolicy(loginPolicy, model, new RedirectAttributesModelMap());

		assertEquals("redirect:/uat/uap/selectLoginPolicyList.do", viewName);
		assertSame(loginPolicy, loginPolicyService.deletedLoginPolicy);
		assertEquals("success.common.delete", model.get("message"));
	}

	@Test
	void deleteLoginPolicyRedirectsToListWhenEncryptedEmplyrIdDoesNotMatch() throws Exception {
		EgovLoginPolicyController controller = new EgovLoginPolicyController();
		StubLoginPolicyService loginPolicyService = new StubLoginPolicyService();
		controller.egovLoginPolicyService = loginPolicyService;
		controller.egovMessageSource = new StubMessageSource();
		controller.cryptoService = cryptoService();

		LoginPolicy loginPolicy = new LoginPolicy();
		loginPolicy.setEmplyrId("TEST1");
		loginPolicy.setEmplyrIdEncrypt(encryptEmplyrId("TEST2"));

		RedirectAttributesModelMap redirectAttributes = new RedirectAttributesModelMap();
		String viewName = controller.deleteLoginPolicy(loginPolicy, new ModelMap(), redirectAttributes);

		assertEquals("redirect:/uat/uap/selectLoginPolicyList.do", viewName);
		assertNull(loginPolicyService.deletedLoginPolicy);
		assertEquals("fail.common.delete", redirectAttributes.getFlashAttributes().get("message"));
	}

	private static EgovEnvCryptoService cryptoService() {
		return (EgovEnvCryptoService) Proxy.newProxyInstance(EgovEnvCryptoService.class.getClassLoader(),
				new Class<?>[] {EgovEnvCryptoService.class}, (proxy, method, args) -> {
					if ("decrypt".equals(method.getName())) {
						return decryptEmplyrId((String) args[0]);
					}
					if ("encrypt".equals(method.getName())) {
						return encryptEmplyrId((String) args[0]);
					}
					if (Boolean.TYPE.equals(method.getReturnType())) {
						return Boolean.FALSE;
					}
					if (Integer.TYPE.equals(method.getReturnType())) {
						return 0;
					}
					return null;
				});
	}

	private static String encryptEmplyrId(String emplyrId) {
		return "encrypted:" + emplyrId;
	}

	private static String decryptEmplyrId(String emplyrIdEncrypt) {
		if (emplyrIdEncrypt != null && emplyrIdEncrypt.startsWith("encrypted:")) {
			return emplyrIdEncrypt.substring("encrypted:".length());
		}
		throw new IllegalArgumentException("Invalid encrypted ID.");
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
		private LoginPolicy deletedLoginPolicy;

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
		}

		@Override
		public void deleteLoginPolicy(LoginPolicy loginPolicy) {
			deletedLoginPolicy = loginPolicy;
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
