package egovframework.com.uat.uia.filter;

import java.io.IOException;

import org.egovframe.rte.fdl.security.config.EgovSecurityConfig;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import egovframework.com.cmm.service.EgovProperties;
import jakarta.annotation.Resource;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Component("egovSpringSecurityLogoutFilter")
public class EgovSpringSecurityLogoutFilter extends OncePerRequestFilter {

	private static final String ACTION_LOGOUT_PATH = "/uat/uia/actionLogout.do";
	private static final String DEFAULT_LOGOUT_SUCCESS_URL = "/uat/uia/egovLoginUsr.do";

	@Resource(name = "egovSecurityConfig")
	private EgovSecurityConfig egovSecurityConfig;

	@Override
	protected boolean shouldNotFilter(HttpServletRequest request) {
		if (!"security".equals(EgovProperties.getProperty("Globals.Auth").trim())) {
			return true;
		}
		return !ACTION_LOGOUT_PATH.equals(normalizeRequestUri(request));
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		HttpSession session = request.getSession(false);
		if (session != null) {
			session.removeAttribute("loginVO");
			session.removeAttribute("accessUser");
			session.invalidate();
		}

		response.sendRedirect(request.getContextPath() + resolveLogoutSuccessUrl());
	}

	private String resolveLogoutSuccessUrl() {
		String logoutSuccessUrl = egovSecurityConfig != null ? egovSecurityConfig.getLogoutSuccessUrl() : null;
		if (!StringUtils.hasText(logoutSuccessUrl)) {
			logoutSuccessUrl = DEFAULT_LOGOUT_SUCCESS_URL;
		}
		return logoutSuccessUrl.startsWith("/") ? logoutSuccessUrl : "/" + logoutSuccessUrl;
	}

	private static String normalizeRequestUri(HttpServletRequest request) {
		String uri = stripContextPath(request);
		int semicolonIndex = uri.indexOf(';');
		if (semicolonIndex > -1) {
			uri = uri.substring(0, semicolonIndex);
		}
		return uri;
	}

	private static String stripContextPath(HttpServletRequest request) {
		String contextPath = request.getContextPath();
		String uri = request.getRequestURI();
		if (StringUtils.hasText(contextPath) && uri.startsWith(contextPath)) {
			return uri.substring(contextPath.length());
		}
		return uri;
	}

}
