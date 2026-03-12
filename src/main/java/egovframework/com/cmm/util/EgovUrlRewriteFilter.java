package egovframework.com.cmm.util;

import java.io.IOException;

import org.springframework.util.AntPathMatcher;

import egovframework.com.cmm.EgovWebUtil;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * URL 재작성 필터
 * 
 * @author 전자정부 표준프레임워크 유지보수
 * @since 2014. 09.30
 * @version 1.0
 * @see
 *
 *      <pre>
 *  == 개정이력(Modification Information) ==
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2014.09.30  표프센          최초생성
 *   2020.11.02  신용호          KISA 보안약점 조치 (CRLF 제거 조치)
 *   2025.05.28  이백행          PMD로 소프트웨어 보안약점 진단하고 제거하기-StringInstantiation(문자열 인스턴스화)
 *
 *      </pre>
 */
public class EgovUrlRewriteFilter implements Filter {

	@SuppressWarnings("unused")
	private FilterConfig config;

	private String targetURI;
	private String httpsPort;
	private String httpPort;

	private String[] uriPatterns;

	@Override
	public void init(FilterConfig config) throws ServletException {

		String delimiter = ",";
		this.config = config;

		this.targetURI = config.getInitParameter("targetURI");
		if (this.targetURI == null || this.targetURI.trim().isEmpty()) {
             throw new ServletException("targetURI 파라미터가 설정되지 않았습니다: " + this.targetURI);
        }
		this.httpsPort = config.getInitParameter("httpsPort");
		this.httpPort = config.getInitParameter("httpPort");

		this.uriPatterns = targetURI.split(delimiter);

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws ServletException, IOException {

		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;

		String uri = req.getRequestURI();
		String getProtocol = req.getScheme();
		String getDomain = req.getServerName();

		AntPathMatcher pm = new AntPathMatcher();

		for (String uriPattern : uriPatterns) {

			if (pm.match(uriPattern.trim(), uri)) {

				if (getProtocol.toLowerCase().equals("http")) {

					response.setContentType("text/html");

					String httpsPath = "https" + "://" + getDomain + ":" + httpsPort + uri;
					res.setStatus(HttpServletResponse.SC_MOVED_TEMPORARILY);
					res.setHeader("Location", EgovWebUtil.removeCRLF(httpsPath));

				}

			} else if (getProtocol.toLowerCase().equals("https")) {

				response.setContentType("text/html");

				String httpPath = "http" + "://" + getDomain + ":" + httpPort + uri;

				res.setStatus(HttpServletResponse.SC_MOVED_TEMPORARILY);
				res.setHeader("Location", EgovWebUtil.removeCRLF(httpPath));

			}
		}

		chain.doFilter(req, res);

	}

	@Override
	public void destroy() {
		this.targetURI = null;
		this.httpsPort = null;
		this.httpPort = null;
		this.uriPatterns = null;
	}

}
