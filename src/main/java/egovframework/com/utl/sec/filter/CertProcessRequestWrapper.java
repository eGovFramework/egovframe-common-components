package egovframework.com.utl.sec.filter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

/**
 * GPKISecureWeb 인증서 로그인 서비스 HttpServletRequestWrapper
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
public class CertProcessRequestWrapper extends HttpServletRequestWrapper {

    /**
     * 생성자를 호출한다.
     * 
     * @param request
     */
    public CertProcessRequestWrapper(HttpServletRequest request) {
	super(request);
    }
    
    /**
     * 변환된 HTML 태그들을 다시 변환한다.
     * 
     * @param src
     * @return
     */
    protected String replaceString(String src) {
	String srcString = src;
	
	srcString = srcString.replaceAll("&lt;", "<");
	srcString = srcString.replaceAll("&gt;", ">");
	srcString = srcString.replaceAll("&quot;", "\"");
	srcString = srcString.replaceAll("&apos;", "'");
	srcString = srcString.replaceAll("&amp;", "&");
	
	return srcString;
    }
    
    /** 
     * getParameterValues 메소드를 대치한다.
     */
    public String[] getParameterValues(String parameter) {
        String values[] = super.getParameterValues(parameter);
        
        if (values == null) {
            return null;
        }
        
        for(int i = 0; i < values.length; i++) {
            if (values[i] != null) {
        	values[i] = replaceString(values[i]);
            }
        }

        return values;
    }

    /**
     * getParameter 메소드를 대치한다.
     */
    public String getParameter(String parameter) {
        String value = super.getParameter(parameter);
        
        if (value == null) {
            return null;
        }
        
        value = replaceString(value);
        
        return value;
    }
}
