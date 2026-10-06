package egovframework.com.uat.uap.filter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockFilterConfig;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockServletContext;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.context.support.StaticWebApplicationContext;

import egovframework.com.cmm.EgovMessageSource;
import egovframework.com.uat.uap.service.EgovLoginPolicyService;
import egovframework.com.uat.uap.service.LoginPolicy;
import egovframework.com.uat.uap.service.LoginPolicyVO;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;

class EgovLoginPolicyFilterTest {

	private static final String LOGIN_URL = "/uat/uia/egovLoginUsr.do";

	private MockHttpServletRequest request;
	private MockHttpServletResponse response;
	private StubLoginPolicyService loginPolicyService;
	private RecordingFilterChain filterChain;
	private EgovLoginPolicyFilter filter;

	@BeforeEach
	void setUp() throws ServletException {
		MockServletContext servletContext = new MockServletContext();
		StaticWebApplicationContext ctx = new StaticWebApplicationContext();
		ctx.setServletContext(servletContext);
		ctx.refresh();

		loginPolicyService = new StubLoginPolicyService();
		ctx.getBeanFactory().registerSingleton("egovLoginPolicyService", loginPolicyService);
		ctx.getBeanFactory().registerSingleton("egovMessageSource", new StubEgovMessageSource());
		servletContext.setAttribute(WebApplicationContext.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE, ctx);

		request = new MockHttpServletRequest("POST", "/uat/uia/actionLogin.do");
		response = new MockHttpServletResponse();
		filterChain = new RecordingFilterChain();
		filter = new EgovLoginPolicyFilter();
		filter.init(new MockFilterConfig(servletContext));

		RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
	}

	@AfterEach
	void tearDown() {
		RequestContextHolder.resetRequestAttributes();
	}

	@Test
	void missingIdDoesNotContinueFilterChain() throws IOException, ServletException {
		// id 파라미터가 없으면 로그인 화면으로 리다이렉트하고 즉시 종료한다.
		request.setParameter("userSe", "USR");

		filter.doFilter(request, response, filterChain);

		assertFalse(filterChain.called);
		assertTrue(response.isCommitted());
		assertEquals(request.getContextPath() + LOGIN_URL, response.getRedirectedUrl());
		assertEquals(0, loginPolicyService.selectLoginPolicyCallCount);
	}

	@Test
	void missingUserSeDoesNotContinueFilterChain() throws IOException, ServletException {
		// userSe 파라미터가 없으면 로그인 화면으로 리다이렉트하고 즉시 종료한다.
		request.setParameter("id", "tester");

		filter.doFilter(request, response, filterChain);

		assertFalse(filterChain.called);
		assertTrue(response.isCommitted());
		assertEquals(request.getContextPath() + LOGIN_URL, response.getRedirectedUrl());
		assertEquals(0, loginPolicyService.selectLoginPolicyCallCount);
	}

	@Test
	void existingIdAndUserSeWithNoPolicyContinuesFilterChain() throws IOException, ServletException {
		// 로그인 정책이 없으면 기존 동작처럼 요청을 다음 필터로 전달한다.
		request.setParameter("id", "tester");
		request.setParameter("userSe", "USR");
		loginPolicyService.loginPolicyVO = null;

		filter.doFilter(request, response, filterChain);

		assertTrue(filterChain.called);
		assertEquals(1, filterChain.callCount);
		assertNull(response.getRedirectedUrl());
	}

	@Test
	void limitedPolicyWithDifferentIpRedirectsLogin() throws IOException, ServletException {
		// 제한 정책의 IP와 요청 IP가 다르면 체인을 호출하지 않고 로그인 화면으로 보낸다.
		request.setParameter("id", "tester");
		request.setParameter("userSe", "USR");
		request.setRemoteAddr("10.0.0.1");

		LoginPolicyVO loginPolicyVO = new LoginPolicyVO();
		loginPolicyVO.setEmplyrId("tester");
		loginPolicyVO.setLmttAt("Y");
		loginPolicyVO.setIpInfo("10.0.0.2");
		loginPolicyService.loginPolicyVO = loginPolicyVO;

		filter.doFilter(request, response, filterChain);

		assertFalse(filterChain.called);
		assertTrue(response.getRedirectedUrl().startsWith(LOGIN_URL + "?loginMessage="));
	}

	private static class StubLoginPolicyService implements EgovLoginPolicyService {

		private LoginPolicyVO loginPolicyVO;
		private int selectLoginPolicyCallCount;

		@Override
		public List<LoginPolicyVO> selectLoginPolicyList(LoginPolicyVO loginPolicyVO) {
			throw new UnsupportedOperationException();
		}

		@Override
		public int selectLoginPolicyListTotCnt(LoginPolicyVO loginPolicyVO) {
			throw new UnsupportedOperationException();
		}

		@Override
		public LoginPolicyVO selectLoginPolicy(LoginPolicyVO loginPolicyVO) {
			selectLoginPolicyCallCount++;
			return this.loginPolicyVO;
		}

		@Override
		public void insertLoginPolicy(LoginPolicy loginPolicy) {
			throw new UnsupportedOperationException();
		}

		@Override
		public void updateLoginPolicy(LoginPolicy loginPolicy) {
			throw new UnsupportedOperationException();
		}

		@Override
		public void deleteLoginPolicy(LoginPolicy loginPolicy) {
			throw new UnsupportedOperationException();
		}
	}

	private static class StubEgovMessageSource extends EgovMessageSource {

		@Override
		public String getMessage(String code) {
			return "login-ip-denied";
		}
	}

	private static class RecordingFilterChain implements FilterChain {

		private boolean called;
		private int callCount;

		@Override
		public void doFilter(ServletRequest request, ServletResponse response) {
			called = true;
			callCount++;
		}
	}
}
