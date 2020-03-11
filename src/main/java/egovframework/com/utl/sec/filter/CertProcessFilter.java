package egovframework.com.utl.sec.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

/**
 * GPKISecureWeb 인증서 로그인 서비스 filter
 * @author 공통컴포넌트개발팀 한성곤
 * @since 2009.08.13
 * @version 1.0
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2009.08.13  한성곤          최초 생성
 *
 * </pre>
 */
public class CertProcessFilter implements Filter {
    @SuppressWarnings("unused")
    private FilterConfig config;

    /**
     * Filter init 메소드를 대치한다.
     */
    public void init(FilterConfig config) throws ServletException {
	this.config = config;
    }
    
    /**
     * Filter doFilter 메소드를 대치한다.
     * RequestWrapper를 통해 HTML Tag 변환을 처리한다.
     */
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
	chain.doFilter(new CertProcessRequestWrapper((HttpServletRequest)request), response);
    }
    
    /**
     * Filter destroy 메소드를 대치한다.
     */
    public void destroy() {	
	// Empty Method
    }
}
