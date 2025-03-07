package egovframework.com.cmm;

import java.io.IOException;
import java.io.Reader;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.BodyTagSupport;

import org.apache.commons.lang3.StringUtils;

/**
 * Cross-Site Scripting 체크하여 값을 되돌려 받는 핸들러 JSP TLD, 자바에서 사용가능
 *
 * @author 공통서비스 장동한
 * @since 2010.11.09
 * @version 1.0
 * @see <pre>
 * &lt;&lt; 개정이력(Modification Information) &gt;&gt;
 *
 *    수정일     수정자      수정내용
 *   -------    --------    ---------------------------
 *   2010.11.09  장동한      최초 생성
 *   2022.11.11  김혜준      시큐어코딩 처리
 *
 * </pre>
 */
@SuppressWarnings("serial")
public class EgovComCrossSiteHndlr extends BodyTagSupport {

	/*
	 * (One almost wishes XML and JSP could support "anonymous tags," given the
	 * amount of trouble we had naming this one!) :-) - sb
	 */

	// *********************************************************************
	// Internal state

	protected Object value; // tag attribute
	protected String def; // tag attribute
	protected boolean escapeXml; // tag attribute
	private boolean needBody; // non-space body needed?

	// *********************************************************************
	// Construction and initialization

	private final String m_sDiffChar ="()[]{}\"',:;= \t\r\n%!+-";
	private final String m_sArrDiffChar [] = {
						"&#40;","&#41;",
						"&#91;","&#93;",
						"&#123;","&#125;",
						"&#34;","&#39;",
						"&#44;","&#58;",
						"&#59;","&#61;",
						" ","\t", //" ","\t",
						"\r","\n", //"\r","\n",
						"&#37;","&#33;",
						"&#43;","&#45;"
						};
	
	// 23.06.08 taglibs 라이브러리 취약점 패치 간 변경사항	김혜준
	public static final int HIGHEST_SPECIAL = '>';
    public static char[][] specialCharactersRepresentation = new char[HIGHEST_SPECIAL + 1][];
    static {
        specialCharactersRepresentation['&'] = "&amp;".toCharArray();
        specialCharactersRepresentation['<'] = "&lt;".toCharArray();
        specialCharactersRepresentation['>'] = "&gt;".toCharArray();
        specialCharactersRepresentation['"'] = "&#034;".toCharArray();
        specialCharactersRepresentation['\''] = "&#039;".toCharArray();
    }

	/**
	 * Constructs a new handler. As with TagSupport, subclasses should not
	 * provide other constructors and are expected to call the superclass
	 * constructor.
	 */
	public EgovComCrossSiteHndlr() {
		super();
		init();
	}

	// resets local state
	private void init() {
		value = def = null;
		escapeXml = true;
		needBody = false;
	}

	// Releases any resources we may have (or inherit)
	public void release() {
		super.release();
		init();
	}

	// *********************************************************************
	// Tag logic

	// evaluates 'value' and determines if the body should be evaluted
	public int doStartTag() throws JspException {
		needBody = false; // reset state related to 'default'
		this.bodyContent = null; // clean-up body (just in case container is pooling tag handlers)
		JspWriter out = pageContext.getOut();
		try {
			// print value if available; otherwise, try 'default'
			if (value != null) {
				String sWriteEscapedXml = getWriteEscapedXml();
				out.print(sWriteEscapedXml);
				return SKIP_BODY;
			} else {
				// if we don't have a 'default' attribute, just go to the body
				// 2022.11.11 시큐어코딩 처리
				if (StringUtils.isEmpty(def)) {
					needBody = true;
					return EVAL_BODY_BUFFERED;
				} else {
					out(pageContext, escapeXml, def);
				}
				return SKIP_BODY;
			}
		} catch (IOException ex) {
			throw new JspException(ex.toString(), ex);
		}
	}

	// prints the body if necessary; reports errors
	public int doEndTag() throws JspException {
		try {
			if (!needBody){
				return EVAL_PAGE; // nothing more to do
			}
			// trim and print out the body
			if (bodyContent != null && bodyContent.getString() != null){
				out(pageContext, escapeXml, bodyContent.getString().trim());
			}
			return EVAL_PAGE;
		} catch (IOException ex) {
			throw new JspException(ex.toString(), ex);
		}
	}

	// *********************************************************************
	// Public utility methods

	/**
	 * Outputs <tt>text</tt> to <tt>pageContext</tt>'s current JspWriter. If
	 * <tt>escapeXml</tt> is true, performs the following substring replacements
	 * (to facilitate output to XML/HTML pages):
	 *
	 * & -> &amp; < -> &lt; > -> &gt; " -> &#034; ' -> &#039;
	 *
	 * See also Util.escapeXml().
	 */
	public static void out(PageContext pageContext, boolean escapeXml, Object obj) throws IOException {
		JspWriter w = pageContext.getOut();
		if (!escapeXml) {
			// write chars as is
			if (obj instanceof Reader) {
				Reader reader = (Reader) obj;
				char[] buf = new char[4096];
				int count;
				while ((count = reader.read(buf, 0, 4096)) != -1) {
					w.write(buf, 0, count);
				}
			} else {
				w.write(obj.toString());
			}
		} else {
			// escape XML chars
			if (obj instanceof Reader) {
				Reader reader = (Reader) obj;
				char[] buf = new char[4096];
				int count;
				while ((count = reader.read(buf, 0, 4096)) != -1) {
					writeEscapedXml(buf, count, w);
				}
			} else {
				String text = obj.toString();
				writeEscapedXml(text.toCharArray(), text.length(), w);
			}
		}
	}

	public static void out2(PageContext pageContext, boolean escapeXml, Object obj) throws IOException {
		JspWriter w = pageContext.getOut();
		w.write(obj.toString());
	}

	/**
	 *
	 * Optimized to create no extra objects and write directly to the JspWriter
	 * using blocks of escaped and unescaped characters
	 *
	 */
	private static void writeEscapedXml(char[] buffer, int length, JspWriter w) throws IOException {
		int start = 0;
		for (int i = 0; i < length; i++) {
			char c = buffer[i];
			if (c <= HIGHEST_SPECIAL) {
				char[] escaped = specialCharactersRepresentation[c];
				if (escaped != null) {
					// add unescaped portion
					if (start < i) {
						w.write(buffer, start, i - start);
					}
					// add escaped xml
					w.write(escaped);
					start = i + 1;
				}
			}
		}
		// add rest of unescaped portion
		if (start < length) {
			w.write(buffer, start, length - start);
		}
	}

	/**
	 *
	 * Optimized to create no extra objects and write directly to the JspWriter
	 * using blocks of escaped and unescaped characters
	 *
	 */
	@SuppressWarnings("unused")
	private String getWriteEscapedXml() throws IOException {
		Object obj = this.value;
		boolean booleanDiff = false;
		String sRtn = "";
		String text = obj.toString();
		int start = 0;
		int length = text.length();
		char[] buffer = text.toCharArray();
		char[] cDiffChar =  this.m_sDiffChar.toCharArray();

		for(int i = 0; i < length; i++) {
			char c = buffer[i];
			booleanDiff = false;
			for(int k = 0; k < cDiffChar.length; k++){
				if(c == cDiffChar[k]){
					sRtn = sRtn + m_sArrDiffChar[k];
					booleanDiff = true;
					continue;
				}
			}

			if(booleanDiff) continue;

			if (c <= HIGHEST_SPECIAL) {
				char[] escaped = specialCharactersRepresentation[c];
				if (escaped != null) {
					for (int j = 0; j < escaped.length; j++) {
						sRtn = sRtn + escaped[j];
					}
					start = i + 1;
				}else{
					sRtn = sRtn + c;
				}
			}else{
				sRtn = sRtn + c;
			}
		}

		return sRtn;
	}

	/**
	 *
	 * Optimized to create no extra objects and write directly to the JspWriter
	 * using blocks of escaped and unescaped characters
	 *
	 */
	@SuppressWarnings("unused")
	private String getWriteEscapedXml(String sWriteString) throws IOException {
		Object obj = sWriteString;
		boolean booleanDiff = false;
		String text = obj.toString();
		String sRtn = "";
		int start = 0;
		int length = text.length();
		char[] buffer = text.toCharArray();
		char[] cDiffChar =  this.m_sDiffChar.toCharArray();

		for(int i = 0; i < length; i++) {
			char c = buffer[i];
			booleanDiff = false;
			for (int k = 0; k < cDiffChar.length; k++) {
				if(c == cDiffChar[k]){
					sRtn = sRtn + m_sArrDiffChar[k];
					booleanDiff = true;
					continue;
				}
			}

			if(booleanDiff) continue;

			if (c <= HIGHEST_SPECIAL) {
				char[] escaped = specialCharactersRepresentation[c];
				if (escaped != null) {
					for (int j = 0; j < escaped.length; j++) {
						sRtn = sRtn + escaped[j];
					}
					start = i + 1;
				}else{
					sRtn = sRtn + c;
				}
			}else{
				sRtn = sRtn + c;
			}
		}

		return sRtn;
	}

    // for tag attribute
    public void setValue(Object value) {
        this.value = value;
    }

    // for tag attribute
    public void setDefault(String def) {
        this.def = def;
    }

    // for tag attribute
    public void setEscapeXml(boolean escapeXml) {
        this.escapeXml = escapeXml;
    }

}
