package egovframework.com.uat.sso.filter;

import java.io.IOException;

import egovframework.com.uat.sso.service.EgovSSOService;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

/**
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
 *
 *  </pre>
 */

public class EgovSSOLogoutFilter implements Filter{
	private FilterConfig config;

	public void destroy() {}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {

		ApplicationContext act = WebApplicationContextUtils.getRequiredWebApplicationContext(config.getServletContext());
		EgovSSOService egovSSOService = (EgovSSOService) act.getBean("egovSSOService");

		String returnURL = config.getInitParameter("returnURL");

		((HttpServletRequest)request).getSession().setAttribute("loginVO", null);
		egovSSOService.ssoLogout(request, response, ((HttpServletRequest)request).getContextPath() + returnURL);

	}

	public void init(FilterConfig filterConfig) throws ServletException {

		this.config = filterConfig;
	}
}
