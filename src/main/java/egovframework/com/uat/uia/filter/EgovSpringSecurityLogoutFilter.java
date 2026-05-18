package egovframework.com.uat.uia.filter;

import java.io.IOException;

import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import egovframework.com.cmm.service.EgovProperties;
import egovframework.com.sym.log.clg.service.EgovLoginLogService;
import jakarta.annotation.Resource;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletRequestWrapper;
import jakarta.servlet.http.HttpServletResponse;

@Component("egovSpringSecurityLogoutFilter")
public class EgovSpringSecurityLogoutFilter extends OncePerRequestFilter {

	private static final String ACTION_LOGOUT_PATH = "/uat/uia/actionLogout.do";
	private static final String SECURITY_LOGOUT_PATH = "/uat/uia/actionSecurityLogout.do";

	@Resource(name = "EgovLoginLogService")
	private EgovLoginLogService loginLogService;

	@Override
	protected boolean shouldNotFilter(HttpServletRequest request) {
		if (!"security".equals(EgovProperties.getProperty("Globals.Auth").trim())) {
			return true;
		}
		String uri = stripContextPath(request);
		if (!uri.equals(ACTION_LOGOUT_PATH)) {
			return true;
		}
		return false;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		request.getSession().setAttribute("loginVO", null);
		request.getSession().setAttribute("accessUser", null);

		HttpServletRequest wrapped = new SecurityLogoutPathRequestWrapper(request, SECURITY_LOGOUT_PATH);
		filterChain.doFilter(wrapped, response);
	}

	private static String stripContextPath(HttpServletRequest request) {
		String contextPath = request.getContextPath();
		String uri = request.getRequestURI();
		if (StringUtils.hasText(contextPath) && uri.startsWith(contextPath)) {
			return uri.substring(contextPath.length());
		}
		return uri;
	}

	private static final class SecurityLogoutPathRequestWrapper extends HttpServletRequestWrapper {

		private final String securityLogoutPath;

		SecurityLogoutPathRequestWrapper(HttpServletRequest request, String securityLogoutPath) {
			super(request);
			this.securityLogoutPath = securityLogoutPath;
		}

		@Override
		public String getServletPath() {
			return securityLogoutPath;
		}

		@Override
		public String getPathInfo() {
			return null;
		}

		@Override
		public String getRequestURI() {
			String cp = getContextPath();
			if (ObjectUtils.isEmpty(cp)) {
				return securityLogoutPath;
			}
			return cp + securityLogoutPath;
		}

		@Override
		public StringBuffer getRequestURL() {
			StringBuffer url = new StringBuffer();
			url.append(getScheme()).append("://").append(getServerName());
			int port = getServerPort();
			if (port > 0 && (("http".equalsIgnoreCase(getScheme()) && port != 80)
					|| ("https".equalsIgnoreCase(getScheme()) && port != 443))) {
				url.append(':').append(port);
			}
			url.append(getRequestURI());
			return url;
		}
	}
}
