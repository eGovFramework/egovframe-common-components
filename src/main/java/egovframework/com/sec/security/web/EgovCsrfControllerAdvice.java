package egovframework.com.sec.security.web;

import org.egovframe.rte.fdl.security.config.EgovSecurityConfig;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;

/**
 * JSP/Thymeleaf 화면에서 {@code ${_csrf}} 표현식을 사용할 수 있도록 CSRF 토큰을 모델에 노출한다.
 */
@ControllerAdvice
@Configuration
public class EgovCsrfControllerAdvice {

	private final HttpSessionCsrfTokenRepository csrfTokenRepository = new HttpSessionCsrfTokenRepository();

	@Resource(name = "egovSecurityConfig")
	private EgovSecurityConfig egovSecurityConfig;

	@ModelAttribute("_csrf")
	public CsrfToken csrfToken(HttpServletRequest request) {
		if (egovSecurityConfig == null || !egovSecurityConfig.isCsrf()) {
			return null;
		}
		return resolveCsrfToken(request);
	}

	private CsrfToken resolveCsrfToken(HttpServletRequest request) {
		Object csrfAttribute = request.getAttribute("_csrf");
		if (csrfAttribute instanceof CsrfToken token) {
			return token;
		}

		Object tokenAttribute = request.getAttribute(CsrfToken.class.getName());
		if (tokenAttribute instanceof CsrfToken token) {
			return token;
		}

		return csrfTokenRepository.loadToken(request);
	}

}
