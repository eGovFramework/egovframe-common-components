package egovframework.com.sec.security.filter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.UUID;

import javax.servlet.Filter;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.mock.web.MockFilterConfig;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.security.web.csrf.DefaultCsrfToken;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

/**
 * 실행환경 4.3.2 Spring Security 간소화 설정(context-security.xml) + EgovSpringSecurityLoginFilter 통합 검증
 *
 * <p>EgovWebApplicationInitializer 와 같은 순서(springSecurityFilterChain → 로그인/로그아웃 필터)로 구성한다.
 * 로컬 MySQL(globals.properties Globals.mysql.*)과 script/dml 초기 데이터(TEST1 / rhdxhd12, ROLE_ADMIN)가 필요하며,
 * DB 에 접속할 수 없으면 건너뛴다. 잘못된 비밀번호로 actionLogin.do 를 호출하지 않으므로 로그인 실패 횟수(잠금)를 바꾸지 않는다.</p>
 *
 * <pre>
 *  수정일        수정자        수정내용
 *  ----------   --------   ---------------------------
 *  2026.11.09   유지보수        최초 생성 (실행환경 4.3.2 보안 설정 전환 검증)
 * </pre>
 */
@ExtendWith(SpringExtension.class)
@WebAppConfiguration
@ContextConfiguration(locations = { "classpath*:egovframework/spring/com/**/context-*.xml" })
@ActiveProfiles({ "mysql", "security" })
class EgovSpringSecurityLoginFilterTest {

	private static final String SEED_USER_SE = "USR";
	private static final String SEED_USER_ID = "TEST1";
	private static final String SEED_PASSWORD = "rhdxhd12";
	private static final String SEED_ESNTL_ID = "USRCNFRM_00000000000";
	private static final String SEED_PASSWORD_HASH = "raHLBnHFcunwNzcDcfad4PhD11hHgXSUr7fc1Jk9uoQ=";

	@Autowired
	private WebApplicationContext wac;

	@Autowired
	@Qualifier("springSecurityFilterChain")
	private Filter springSecurityFilterChain;

	private MockMvc mockMvc;

	@BeforeAll
	static void requireDatabase() {
		boolean reachable;
		try (Socket socket = new Socket()) {
			socket.connect(new InetSocketAddress("127.0.0.1", 3306), 1000);
			reachable = true;
		} catch (Exception e) {
			reachable = false;
		}
		assumeTrue(reachable, "MySQL(127.0.0.1:3306) 에 접속할 수 없어 보안 통합 테스트를 건너뜁니다");
	}

	@BeforeEach
	void setUp() throws Exception {
		SecurityContextHolder.clearContext();

		EgovSpringSecurityLoginFilter loginFilter = new EgovSpringSecurityLoginFilter();
		MockFilterConfig loginConfig = new MockFilterConfig(wac.getServletContext(), "egovSpringSecurityLoginFilter");
		loginConfig.addInitParameter("loginURL", "/uat/uia/egovLoginUsr.do");
		loginConfig.addInitParameter("loginProcessURL", "/uat/uia/actionLogin.do");
		loginFilter.init(loginConfig);

		EgovSpringSecurityLogoutFilter logoutFilter = new EgovSpringSecurityLogoutFilter();
		MockFilterConfig logoutConfig = new MockFilterConfig(wac.getServletContext(), "egovSpringSecurityLogoutFilter");
		logoutConfig.addInitParameter("logoutSuccessURL", "/uat/uia/egovLoginUsr.do");
		logoutFilter.init(logoutConfig);

		mockMvc = MockMvcBuilders.webAppContextSetup(wac)
			.addFilter(springSecurityFilterChain, "/*")
			.addFilter(loginFilter, "*.do")
			.addFilter(logoutFilter, "/uat/uia/actionLogout.do")
			.build();
	}

	@Test
	void seedPasswordHashMatchesIdSaltedSha256() {
		// 5.1.0 egov-sha256 과 같은 알고리즘이어야 DB 초기 데이터가 그대로 유효하다
		assertEquals(SEED_PASSWORD_HASH,
			egovframework.com.utl.sim.service.EgovFileScrty.encryptPassword(SEED_PASSWORD, SEED_USER_ID));
	}

	@Test
	void directPostToSpringSecurityLoginEndpointDoesNotAuthenticate() throws Exception {
		// ESNTL_ID(추측 가능한 값) 와 저장 해시 모두로 /egov_security_login 을 직접 호출해도 인증되면 안 된다
		for (String credential : new String[] { SEED_ESNTL_ID, SEED_PASSWORD_HASH }) {
			MockHttpSession session = new MockHttpSession();
			CsrfToken token = csrfToken(session);

			MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/egov_security_login")
					.session(session)
					.param(token.getParameterName(), token.getToken())
					.param("egov_security_username", SEED_USER_SE + SEED_USER_ID)
					.param("egov_security_password", credential))
				.andReturn();

			assertNotEquals("/EgovContent.do", result.getResponse().getRedirectedUrl(),
				"직접 호출이 인증 성공(defaultTargetUrl)으로 처리됨 : credential=" + credential);
			assertFalse(isAuthenticated(result), "직접 호출로 인증됨 : credential=" + credential);
		}
	}

	@Test
	void loginThroughBridgeFilterAuthenticatesWithRoles() throws Exception {
		MockHttpSession session = new MockHttpSession();
		MvcResult result = login(session);

		assertEquals("/EgovContent.do", result.getResponse().getRedirectedUrl());
		assertTrue(isAuthenticated(result), "로그인 후 SecurityContext 에 인증 정보가 없음");

		Authentication authentication = securityContext(result).getAuthentication();
		assertEquals(SEED_USER_ID, authentication.getName());
		boolean admin = false;
		for (GrantedAuthority authority : authentication.getAuthorities()) {
			admin |= "ROLE_ADMIN".equals(authority.getAuthority());
		}
		assertTrue(admin, "ROLE_ADMIN 권한이 없음 : " + authentication.getAuthorities());
		assertNotNull(result.getRequest().getSession(false).getAttribute("loginVO"));
	}

	@Test
	void loginWithoutCsrfTokenIsRejectedBeforeAuthentication() throws Exception {
		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/uat/uia/actionLogin.do")
				.session(new MockHttpSession())
				.param("id", SEED_USER_ID)
				.param("password", SEED_PASSWORD)
				.param("userSe", SEED_USER_SE))
			.andReturn();

		assertFalse(isAuthenticated(result), "CSRF 토큰 없이 로그인됨");
		// 실행환경 4.3.2 CsrfAccessDeniedHandler 는 csrfAccessDeniedUrl 로 forward 한다
		assertEquals("/egovCSRFAccessDenied.do", result.getResponse().getForwardedUrl());
		// CsrfFilter 에서 거부되어 로그인 필터(EgovLoginService)까지 가지 않아야 한다
		javax.servlet.http.HttpSession session = result.getRequest().getSession(false);
		assertTrue(session == null || session.getAttribute("loginVO") == null, "CSRF 거부 전에 로그인 처리가 실행됨");
	}

	@Test
	void logoutClearsAuthenticationAndRedirectsToLogin() throws Exception {
		MockHttpSession session = new MockHttpSession();
		MvcResult loggedIn = login(session);
		assertTrue(isAuthenticated(loggedIn));

		MockHttpSession authenticatedSession = (MockHttpSession) loggedIn.getRequest().getSession(false);
		CsrfToken token = csrfToken(authenticatedSession);

		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/uat/uia/actionLogout.do")
				.session(authenticatedSession)
				.param(token.getParameterName(), token.getToken()))
			.andReturn();

		assertEquals("/uat/uia/egovLoginUsr.do", result.getResponse().getRedirectedUrl());
		assertTrue(authenticatedSession.isInvalid(), "로그아웃 후 세션이 무효화되지 않음");
		assertNull(SecurityContextHolder.getContext().getAuthentication());
	}

	private MvcResult login(MockHttpSession session) throws Exception {
		CsrfToken token = csrfToken(session);
		return mockMvc.perform(MockMvcRequestBuilders.post("/uat/uia/actionLogin.do")
				.session(session)
				.param(token.getParameterName(), token.getToken())
				.param("id", SEED_USER_ID)
				.param("password", SEED_PASSWORD)
				.param("userSe", SEED_USER_SE))
			.andReturn();
	}

	/** 세션에 CSRF 토큰을 저장한다 (JSP 의 ${_csrf} hidden 필드와 같은 역할) */
	private static CsrfToken csrfToken(MockHttpSession session) {
		HttpSessionCsrfTokenRepository repository = new HttpSessionCsrfTokenRepository();
		CsrfToken token = new DefaultCsrfToken("X-CSRF-TOKEN", "_csrf", UUID.randomUUID().toString());
		MockHttpServletRequest request = new MockHttpServletRequest();
		request.setSession(session);
		repository.saveToken(token, request, new MockHttpServletResponse());
		return token;
	}

	private static SecurityContext securityContext(MvcResult result) {
		javax.servlet.http.HttpSession session = result.getRequest().getSession(false);
		if (session == null) {
			return null;
		}
		return (SecurityContext) session.getAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY);
	}

	private static boolean isAuthenticated(MvcResult result) {
		SecurityContext context = securityContext(result);
		return context != null && context.getAuthentication() != null && context.getAuthentication().isAuthenticated()
			&& !"anonymousUser".equals(context.getAuthentication().getName());
	}
}
