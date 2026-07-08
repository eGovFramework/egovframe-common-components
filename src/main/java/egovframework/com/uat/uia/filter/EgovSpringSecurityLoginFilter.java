package egovframework.com.uat.uia.filter;

import java.io.IOException;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import egovframework.com.cmm.EgovMessageSource;
import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.config.EgovLoginConfig;
import egovframework.com.cmm.service.EgovProperties;
import egovframework.com.uat.uia.service.EgovLoginService;
import egovframework.com.utl.sim.service.EgovClntInfo;
import jakarta.annotation.Resource;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletRequestWrapper;
import jakarta.servlet.http.HttpServletResponse;

@Component("egovSpringSecurityLoginFilter")
public class EgovSpringSecurityLoginFilter extends OncePerRequestFilter {

	private static final Logger LOGGER = LoggerFactory.getLogger(EgovSpringSecurityLoginFilter.class);

	private static final String LOGIN_PATH = "/uat/uia/egovLoginUsr.do";
	private static final String ACTION_LOGIN_PATH = "/uat/uia/actionLogin.do";

	@Resource(name = "loginService")
	private EgovLoginService loginService;

	@Resource(name = "egovLoginConfig")
	private EgovLoginConfig egovLoginConfig;

	@Resource(name = "egovMessageSource")
	private EgovMessageSource egovMessageSource;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		if (!"security".equals(EgovProperties.getProperty("Globals.Auth").trim())) {
			filterChain.doFilter(request, response);
			return;
		}

		if (!isActionLoginUri(request)) {
			filterChain.doFilter(request, response);
			return;
		}

		String id = request.getParameter("id");
		String password = request.getParameter("password");
		String userSe = request.getParameter("userSe");

		if (!StringUtils.hasText(id) || !StringUtils.hasText(password)) {
			request.setAttribute("loginMessage", egovMessageSource.getMessage("fail.common.login", request.getLocale()));
			request.getRequestDispatcher(LOGIN_PATH).forward(request, response);
			return;
		}

		if (!StringUtils.hasText(userSe)) {
			userSe = "GNR";
		}

		LoginVO loginVO = new LoginVO();
		loginVO.setId(id.trim());
		loginVO.setPassword(password.trim());
		loginVO.setUserSe(userSe);

		if (egovLoginConfig.isLock()) {
			try {
				Map<?, ?> mapLockUserInfo = invokeSelectLoginIncorrect(loginVO);
				if (mapLockUserInfo != null) {
					// 로그인인증제한 처리
					String sLoginIncorrectCode = invokeProcessLoginIncorrect(loginVO, mapLockUserInfo);
					if (!sLoginIncorrectCode.equals("E")) {
						if (sLoginIncorrectCode.equals("L")) {
							request.setAttribute("loginMessage",
									egovMessageSource.getMessageArgs("fail.common.loginIncorrect", new Object[] { egovLoginConfig.getLockCount(), request.getLocale() }));
						} else if (sLoginIncorrectCode.equals("C")) {
							request.setAttribute("loginMessage", egovMessageSource.getMessage("fail.common.login", request.getLocale()));
						}
						request.getRequestDispatcher(LOGIN_PATH).forward(request, response);
						return;
					}
				} else {
					request.setAttribute("loginMessage", egovMessageSource.getMessage("fail.common.login", request.getLocale()));
					request.getRequestDispatcher(LOGIN_PATH).forward(request, response);
					return;
				}
			} catch (IllegalArgumentException e) {
				LOGGER.error("[{}] Login lock check failed : {}", e.getClass().getName(), e.getMessage());
				forwardToLoginFailure(request, response);
				return;
			} catch (LoginServiceInvocationException e) {
				logLoginBridgeFailure(e);
				forwardToLoginFailure(request, response);
				return;
			}
		}

		try {
			LoginVO resultVO = invokeActionLogin(loginVO);
			resultVO.setIp(resolveClientIp(request));

			if (resultVO != null && StringUtils.hasText(resultVO.getId())) {

				request.getSession().setAttribute("loginVO", resultVO);

				String securityUser = resultVO.getUserSe().concat(resultVO.getId());
				String securityPass = resultVO.getUniqId();
				if (!StringUtils.hasText(securityPass)) {
					LOGGER.warn("Login succeeded but uniqId is empty for user {}", securityUser);
					forwardToLoginFailure(request, response);
					return;
				}

				HttpServletRequest wrapped = new LoginFormSpringSecurityParameterWrapper(request, securityUser, securityPass);
				filterChain.doFilter(wrapped, response);
			} else {
				forwardToLoginFailure(request, response);
			}
		} catch (IllegalArgumentException e) {
			LOGGER.error("[{}] Login bridge processing failed : {}", e.getClass().getName(), e.getMessage());
			forwardToLoginFailure(request, response);
		} catch (LoginServiceInvocationException e) {
			logLoginBridgeFailure(e);
			forwardToLoginFailure(request, response);
		}
	}

	private LoginVO invokeActionLogin(LoginVO loginVO) {
		try {
			return loginService.actionLogin(loginVO);
		} catch (IllegalArgumentException e) {
			throw e;
		} catch (Exception e) {
			throw new LoginServiceInvocationException(e);
		}
	}

	private Map<?, ?> invokeSelectLoginIncorrect(LoginVO loginVO) {
		try {
			return loginService.selectLoginIncorrect(loginVO);
		} catch (IllegalArgumentException e) {
			throw e;
		} catch (Exception e) {
			throw new LoginServiceInvocationException(e);
		}
	}

	private String invokeProcessLoginIncorrect(LoginVO loginVO, Map<?, ?> mapLockUserInfo) {
		try {
			return loginService.processLoginIncorrect(loginVO, mapLockUserInfo);
		} catch (IllegalArgumentException e) {
			throw e;
		} catch (Exception e) {
			throw new LoginServiceInvocationException(e);
		}
	}

	private String resolveClientIp(HttpServletRequest request) {
		try {
			return EgovClntInfo.getClntIP(request);
		} catch (IllegalArgumentException e) {
			LOGGER.warn("[{}] Failed to resolve client IP : {}", e.getClass().getName(), e.getMessage());
			return "";
		} catch (Exception e) {
			LOGGER.warn("[{}] Failed to resolve client IP : {}", e.getClass().getName(), e.getMessage());
			return "";
		}
	}

	private void forwardToLoginFailure(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setAttribute("loginMessage",
				egovMessageSource.getMessage("fail.common.login", request.getLocale()));
		request.getRequestDispatcher(LOGIN_PATH).forward(request, response);
	}

	private static void logLoginBridgeFailure(Exception e) {
		Throwable cause = e.getCause() != null ? e.getCause() : e;
		LOGGER.error("[{}] Login bridge processing failed : {}", cause.getClass().getName(), cause.getMessage());
	}

	private static final class LoginServiceInvocationException extends RuntimeException {

		private static final long serialVersionUID = 1L;

		LoginServiceInvocationException(Throwable cause) {
			super(cause);
		}
	}

	private static boolean isActionLoginUri(HttpServletRequest request) {
		return "POST".equalsIgnoreCase(request.getMethod())
				&& ACTION_LOGIN_PATH.equals(normalizeRequestUri(request));
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

	private static final class LoginFormSpringSecurityParameterWrapper extends HttpServletRequestWrapper {

		private final String securityUsername;
		private final String securityPassword;
		private Map<String, String[]> cachedParameterMap;

		LoginFormSpringSecurityParameterWrapper(HttpServletRequest request, String securityUsername,
				String securityPassword) {
			super(request);
			this.securityUsername = securityUsername;
			this.securityPassword = securityPassword;
		}

		@Override
		public String getParameter(String name) {
			if ("username".equals(name)) {
				return securityUsername;
			}
			if ("password".equals(name)) {
				return securityPassword;
			}
			return super.getParameter(name);
		}

		@Override
		public String[] getParameterValues(String name) {
			if ("username".equals(name)) {
				return new String[] { securityUsername };
			}
			if ("password".equals(name)) {
				return new String[] { securityPassword };
			}
			return super.getParameterValues(name);
		}

		@Override
		public Map<String, String[]> getParameterMap() {
			if (cachedParameterMap == null) {
				Map<String, String[]> map = new HashMap<>(super.getParameterMap());
				map.put("username", new String[] { securityUsername });
				map.put("password", new String[] { securityPassword });
				cachedParameterMap = Collections.unmodifiableMap(map);
			}
			return cachedParameterMap;
		}

		@Override
		public Enumeration<String> getParameterNames() {
			Vector<String> names = new Vector<>(Collections.list(super.getParameterNames()));
			if (!names.contains("username")) {
				names.add("username");
			}
			if (!names.contains("password")) {
				names.add("password");
			}
			return names.elements();
		}
	}
}
