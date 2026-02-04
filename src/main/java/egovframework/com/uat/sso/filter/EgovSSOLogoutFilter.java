package egovframework.com.uat.sso.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import egovframework.com.uat.sso.service.EgovSSOService;

/**
 * Egov SSO 로그아웃 필터
 * 
 * @author 공통서비스 개발팀 서준식
 * @since 2011.08.29
 * @version 1.0
 * @see
 *
 *      <pre>
 *  == 개정이력(Modification Information) ==
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2011.08.29  서준식          최초 생성
 *   2025.07.29  이백행          2025년 컨트리뷰션 PMD로 소프트웨어 보안약점 진단하고 제거하기-UncommentedEmptyMethodBody(빈 메소드에 빈메소드임을 나타내는 주석을 추가할 것)
 *
 *      </pre>
 */
public class EgovSSOLogoutFilter implements Filter {
	private FilterConfig config;

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		ApplicationContext act = WebApplicationContextUtils
				.getRequiredWebApplicationContext(config.getServletContext());
		EgovSSOService egovSSOService = (EgovSSOService) act.getBean("egovSSOService");

		String returnURL = config.getInitParameter("returnURL");

		((HttpServletRequest) request).getSession().setAttribute("loginVO", null);
		egovSSOService.ssoLogout(request, response, ((HttpServletRequest) request).getContextPath() + returnURL);

	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {

		this.config = filterConfig;
	}
}
