package egovframework.com.sec.security.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;

/**
 * Spring Security 간소화 설정(4.3.x) 로그아웃 필터
 *
 * /uat/uia/actionLogout.do (POST, CSRF 토큰 포함) 요청에서 세션의 로그인 정보를 제거하고 Spring Security 컨텍스트를 정리한 뒤
 * logoutSuccessURL(context-security.xml 의 logoutSuccessUrl 과 동일) 로 리다이렉트한다.
 * (csrf="true" 인 경우 Spring Security 의 /egov_security_logout 은 POST 만 허용하므로 GET 리다이렉트 대신 직접 처리한다.)
 *
 * @author 공통서비스 개발팀 서준식
 * @since 2011. 8. 29.
 * @version 1.0
 * @see
 *
 * <pre>
 * 개정이력(Modification Information)
 *
 *   수정일      수정자          수정내용
 *  -------    --------    ---------------------------
 *  2011. 8. 29.    서준식        최초생성
 *  2017.07.10      장동한       실행환경 v3.7(Spring Security 4.0.3 적용)
 *  2026.11.08      유지보수      실행환경 4.3.2 적용 - CSRF 사용 시에도 동작하도록 SecurityContextLogoutHandler 로 직접 로그아웃
 *
 *  </pre>
 */
public class EgovSpringSecurityLogoutFilter implements Filter {

	private static final String DEFAULT_LOGOUT_SUCCESS_URL = "/uat/uia/egovLoginUsr.do";

	private FilterConfig config;

	private static final Logger LOGGER = LoggerFactory.getLogger(EgovSpringSecurityLogoutFilter.class);

	public void destroy() {}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;

		LOGGER.debug("EgovSpringSecurityLogoutFilter : {}", httpRequest.getRequestURI());

		HttpSession session = httpRequest.getSession(false);
		if (session != null) {
			session.removeAttribute("loginVO");
			session.removeAttribute("accessUser");
			session.removeAttribute("isLocallyAuthenticated");
		}

		// Spring Security 로그아웃 (SecurityContext 제거 + 세션 무효화)
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		SecurityContextLogoutHandler logoutHandler = new SecurityContextLogoutHandler();
		logoutHandler.setInvalidateHttpSession(true);
		logoutHandler.setClearAuthentication(true);
		logoutHandler.logout(httpRequest, httpResponse, authentication);

		httpResponse.sendRedirect(httpRequest.getContextPath() + resolveLogoutSuccessUrl());
	}

	private String resolveLogoutSuccessUrl() {
		String logoutSuccessUrl = config == null ? null : config.getInitParameter("logoutSuccessURL");
		if (logoutSuccessUrl == null || "".equals(logoutSuccessUrl.trim())) {
			logoutSuccessUrl = DEFAULT_LOGOUT_SUCCESS_URL;
		}
		logoutSuccessUrl = logoutSuccessUrl.trim().replaceAll("\r", "").replaceAll("\n", "");
		return logoutSuccessUrl.startsWith("/") ? logoutSuccessUrl : "/" + logoutSuccessUrl;
	}

	public void init(FilterConfig filterConfig) throws ServletException {
		this.config = filterConfig;
	}
}
