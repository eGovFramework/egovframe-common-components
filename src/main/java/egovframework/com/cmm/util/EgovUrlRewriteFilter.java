package egovframework.com.cmm.util;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.util.AntPathMatcher;

import egovframework.com.cmm.EgovWebUtil;

/**
 * @Class Name : UrlRewriteFilter.java
 * @Description : UrlRewriteFilter Class
 * @Modification Information
 * @
 * @ 수정일               수정자              수정내용
 * @ ----------   ---------   -------------------------------
 * @ 2014.09.30               최초생성
 * @ 2020.11.02   신용호              KISA 보안약점 조치 (CRLF 제거 조치)
 *
 * @author 전자정부 표준프레임워크 유지보수
 * @since 2014. 09.30
 * @version 1.0
 * @see
 *
 *  Copyright (C) by MOPAS All rights reserved.
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
		this.httpsPort = config.getInitParameter("httpsPort");
		this.httpPort = config.getInitParameter("httpPort");

		this.uriPatterns = targetURI.split(delimiter);

    }

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {

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
					 String site = new String(httpsPath);
					 res.setStatus(HttpServletResponse.SC_MOVED_TEMPORARILY);
					 res.setHeader("Location", EgovWebUtil.removeCRLF(site));

				 }

			}else if(getProtocol.toLowerCase().equals("https")){

				response.setContentType("text/html");

				String httpPath = "http" + "://" + getDomain + ":" + httpPort + uri;

				String site = new String(httpPath);
				res.setStatus(HttpServletResponse.SC_MOVED_TEMPORARILY);
				res.setHeader("Location", EgovWebUtil.removeCRLF(site));

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
