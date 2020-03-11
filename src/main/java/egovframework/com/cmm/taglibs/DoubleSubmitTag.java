package egovframework.com.cmm.taglibs;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import egovframework.com.cmm.util.EgovDoubleSubmitHelper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.tagext.TagSupport;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * TagSupport to support to double submit preventer
 * @author Vincent Han
 * @since 2014.08.07
 * @version 1.0
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일        수정자       수정내용
 *  -------       --------    ---------------------------
 *   2014.08.07	표준프레임워크센터	최초 생성
 *
 * </pre>
 */
public class DoubleSubmitTag extends TagSupport {
	private static final Logger LOGGER = LoggerFactory.getLogger(DoubleSubmitTag.class);

	/**
	 * Generated Serial Version UID
	 */
	private static final long serialVersionUID = 5242217605452312594L;
	
	private String tokenKey = EgovDoubleSubmitHelper.DEFAULT_TOKEN_KEY;

	public String getTokenKey() {
		return tokenKey;
	}

	public void setTokenKey(String tokenKey) {
		this.tokenKey = tokenKey;
	}

	@SuppressWarnings("unchecked")
	public int doStartTag()	throws JspException {
		StringBuilder buffer = new StringBuilder();
		
		HttpServletRequest request = (HttpServletRequest)pageContext.getRequest();
		HttpSession session = request.getSession();
		
		Map<String, String> map = null;
		
		if (session.getAttribute(EgovDoubleSubmitHelper.SESSION_TOKEN_KEY) == null) {
			map = new HashMap<String, String>();
			
			session.setAttribute(EgovDoubleSubmitHelper.SESSION_TOKEN_KEY, map);
		} else {
			map = (Map<String, String>) session.getAttribute(EgovDoubleSubmitHelper.SESSION_TOKEN_KEY);
		}
				
		// First call (check session)
		if (map.get(tokenKey) == null) {
			
			map.put(tokenKey, EgovDoubleSubmitHelper.getNewUUID());

			LOGGER.debug("[Double Submit] session token created({}) : {}", tokenKey, map.get(tokenKey)); 
		}
		
		buffer.append("<input type='hidden' name='").append(EgovDoubleSubmitHelper.PARAMETER_NAME).append("' value='").append(map.get(tokenKey)).append("'/>");
		
		try {
			pageContext.getOut().print(buffer.toString());
		} catch (IOException e) {
			throw new JspTagException("Error:  IOException while writing to the user");
		}
		
        return SKIP_BODY;
	}
	
}
