/**
 * @Transactional Test Class 구현
 * @author 표준프레임워크 신용호
 * @since 2020.01.17
 * @version 3.9
 * @see
 * <pre>
 *
 *  수정일              수정자               수정내용
 *  ----------  -----------  ---------------------------
 *  2020.01.17  신용호                최초 생성
 *
 * @ 특징
   - HTMLTagFilter Mock 테스트 수행
   
 * </pre>
 */

package egovframework.com.filter;

import static org.junit.Assert.assertEquals;
import java.util.Map;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.filter.CorsFilter;

import egovframework.com.cmm.filter.HTMLTagFilter;
import egovframework.com.cmm.filter.HTMLTagFilterRequestWrapper;

/**
 * Unit tests for {@link CorsFilter}.
 * @author Sebastien Deleuze
 */
public class HTMLTagFilterTest {
	
	protected Logger egovLogger = LoggerFactory.getLogger(HTMLTagFilterTest.class);

	HTMLTagFilter filter = new HTMLTagFilter();
	MockHttpServletRequest request;
	HTMLTagFilterRequestWrapper wrapper;

	@Test
	public void testGetMethodGetParameter() {
		request = new MockHttpServletRequest("GET", "/notExistUrl.do");
		request.addParameter("title", "<b>Text</b>");
		request.addParameter("globalParameter", "<b>Text</b>");
		wrapper = new HTMLTagFilterRequestWrapper(request);

		egovLogger.debug("testGetMethodGetParameter===> wrapper.getParameter(title) = "+wrapper.getParameter("title"));
		egovLogger.debug("testGetMethodGetParameter===> wrapper.getParameter(globalParameter) = "+wrapper.getParameter("globalParameter"));
		assertEquals(wrapper.getParameter("title"), "&lt;b&gt;Text&lt;/b&gt;");
		//assertEquals(wrapper.getParameter("globalParameter"), is("<b>Text</b>"));
		assertEquals(wrapper.getParameter("globalParameter"), "&lt;b&gt;Text&lt;/b&gt;");

		request = new MockHttpServletRequest("GET", "/url1.do");
		request.addParameter("title", "<b>Text</b>");
		request.addParameter("mode", "<script>Text</script>");
		request.addParameter("globalParameter", "<script>Text</script>");
		wrapper = new HTMLTagFilterRequestWrapper(request);

		egovLogger.debug("testGetMethodGetParameter===> wrapper.getParameter(title) = "+wrapper.getParameter("title"));
		egovLogger.debug("testGetMethodGetParameter===> wrapper.getParameter(mode) = "+wrapper.getParameter("mode"));
		egovLogger.debug("testGetMethodGetParameter===> wrapper.getParameter(globalParameter) = "+wrapper.getParameter("globalParameter"));
		assertEquals(wrapper.getParameter("title"), "&lt;b&gt;Text&lt;/b&gt;");
		assertEquals(wrapper.getParameter("mode"), "&lt;script&gt;Text&lt;/script&gt;");
		assertEquals(wrapper.getParameter("globalParameter"), "&lt;script&gt;Text&lt;/script&gt;");
	}

	@Test
	public void testPostMethodGetParameter() {
		request = new MockHttpServletRequest("POST", "/notExistUrl.do");
		request.addParameter("title", "<b>Text</b>");
		request.addParameter("globalParameter", "<b>Text</b>");
		wrapper = new HTMLTagFilterRequestWrapper(request);

		egovLogger.debug("testPostMethodGetParameter===> wrapper.getParameter(title) = "+wrapper.getParameter("title"));
		egovLogger.debug("testPostMethodGetParameter===> wrapper.getParameter(globalParameter) = "+wrapper.getParameter("globalParameter"));
		assertEquals(wrapper.getParameter("title"), "&lt;b&gt;Text&lt;/b&gt;");
		assertEquals(wrapper.getParameter("globalParameter"), "&lt;b&gt;Text&lt;/b&gt;");
		//assertEquals(wrapper.getParameter("globalParameter"), is("<b>Text</b>"));

		request = new MockHttpServletRequest("POST", "/url1.do");
		request.addParameter("title", "<b>Text</b>");
		request.addParameter("mode", "<script>Text</script>");
		request.addParameter("globalParameter", "<script>Text</script>");
		wrapper = new HTMLTagFilterRequestWrapper(request);

		egovLogger.debug("testPostMethodGetParameter===> wrapper.getParameter(title) = "+wrapper.getParameter("title"));
		egovLogger.debug("testPostMethodGetParameter===> wrapper.getParameter(mode) = "+wrapper.getParameter("mode"));
		egovLogger.debug("testPostMethodGetParameter===> wrapper.getParameter(globalParameter) = "+wrapper.getParameter("globalParameter"));
		assertEquals(wrapper.getParameter("title"), "&lt;b&gt;Text&lt;/b&gt;");
		assertEquals(wrapper.getParameter("mode"), "&lt;script&gt;Text&lt;/script&gt;");
		assertEquals(wrapper.getParameter("globalParameter"), "&lt;script&gt;Text&lt;/script&gt;");
	}

	@Test
	public void testGetMethodGetParameterValues() {
		request = new MockHttpServletRequest("GET", "/notExistUrl.do");
		request.addParameter("title", "<b>Text1</b>");
		request.addParameter("title", "<b>Text2</b>");
		request.addParameter("globalParameter", "<b>Text1</b>");
		request.addParameter("globalParameter", "<b>Text2</b>");
		wrapper = new HTMLTagFilterRequestWrapper(request);

		String[] values = wrapper.getParameterValues("title");
		assertEquals(values[0], "&lt;b&gt;Text1&lt;/b&gt;");
		assertEquals(values[1], "&lt;b&gt;Text2&lt;/b&gt;");

		values = wrapper.getParameterValues("globalParameter");
		assertEquals(values[0], "&lt;b&gt;Text1&lt;/b&gt;");
		assertEquals(values[1], "&lt;b&gt;Text2&lt;/b&gt;");
		//assertEquals(values[0], is("<b>Text1</b>"));
		//assertEquals(values[1], is("<b>Text2</b>"));

		request = new MockHttpServletRequest("GET", "/url1.do");
		request.addParameter("title", "<b>Text1</b>");
		request.addParameter("title", "<b>Text2</b>");
		request.addParameter("mode", "<script>Text1</script>");
		request.addParameter("mode", "<script>Text2</script>");
		request.addParameter("globalParameter", "<script>Text1</script>");
		request.addParameter("globalParameter", "<script>Text2</script>");
		wrapper = new HTMLTagFilterRequestWrapper(request);

		values = wrapper.getParameterValues("title");
		assertEquals(values[0], "&lt;b&gt;Text1&lt;/b&gt;");
		assertEquals(values[1], "&lt;b&gt;Text2&lt;/b&gt;");

		values = wrapper.getParameterValues("mode");
		assertEquals(values[0], "&lt;script&gt;Text1&lt;/script&gt;");
		assertEquals(values[1], "&lt;script&gt;Text2&lt;/script&gt;");

		values = wrapper.getParameterValues("globalParameter");
		assertEquals(values[0], "&lt;script&gt;Text1&lt;/script&gt;");
		assertEquals(values[1], "&lt;script&gt;Text2&lt;/script&gt;");
	}

	@Test
	public void testGetMethodGetParameterMap() {
		request = new MockHttpServletRequest("GET", "/notExistUrl.do");
		request.addParameter("title", "<b>Text1</b>");
		request.addParameter("title", "<b>Text2</b>");
		request.addParameter("globalParameter", "<b>Text1</b>");
		wrapper = new HTMLTagFilterRequestWrapper(request);

		Map<String, String[]> map = wrapper.getParameterMap();
		String[] values = (String[])map.get("title");
		assertEquals(values[0], "&lt;b&gt;Text1&lt;/b&gt;");
		assertEquals(values[1], "&lt;b&gt;Text2&lt;/b&gt;");

		values = (String[])map.get("globalParameter");
		assertEquals(values[0], "&lt;b&gt;Text1&lt;/b&gt;");
		//assertEquals(values[0], is("<b>Text1</b>"));

		request = new MockHttpServletRequest("GET", "/url1.do");
		request.addParameter("title", "<b>Text1</b>");
		request.addParameter("title", "<b>Text2</b>");
		request.addParameter("mode", "<script>Text1</script>");
		request.addParameter("mode", "<script>Text2</script>");
		request.addParameter("globalParameter", "<script>Text1</script>");
		wrapper = new HTMLTagFilterRequestWrapper(request);

		map = wrapper.getParameterMap();
		values = (String[])map.get("title");
		assertEquals(values[0], "&lt;b&gt;Text1&lt;/b&gt;");
		assertEquals(values[1], "&lt;b&gt;Text2&lt;/b&gt;");

		values = (String[])map.get("mode");
		assertEquals(values[0], "&lt;script&gt;Text1&lt;/script&gt;");
		assertEquals(values[1], "&lt;script&gt;Text2&lt;/script&gt;");

		values = (String[])map.get("globalParameter");
		egovLogger.debug("===>>>"+values[0]);
		assertEquals(values[0], "&lt;script&gt;Text1&lt;/script&gt;");
	}

	@Test
	public void testContextPath() {
		request = new MockHttpServletRequest("GET", "/test/notExistUrl.do");
		request.setContextPath("/test");
		request.addParameter("title", "<b>Text</b>");
		request.addParameter("globalParameter", "<b>Text</b>");
		wrapper = new HTMLTagFilterRequestWrapper(request);

		assertEquals(wrapper.getParameter("title"), "&lt;b&gt;Text&lt;/b&gt;");
		assertEquals(wrapper.getParameter("globalParameter"), "&lt;b&gt;Text&lt;/b&gt;");
		//assertEquals(wrapper.getParameter("globalParameter"), is("<b>Text</b>"));

		request = new MockHttpServletRequest("GET", "/test/url1.do");
		request.setContextPath("/test");
		request.addParameter("title", "<b>Text</b>");
		request.addParameter("mode", "<script>Text</script>");
		request.addParameter("globalParameter", "<script>Text</script>");
		wrapper = new HTMLTagFilterRequestWrapper(request);

		assertEquals(wrapper.getParameter("title"), "&lt;b&gt;Text&lt;/b&gt;");
		assertEquals(wrapper.getParameter("mode"), "&lt;script&gt;Text&lt;/script&gt;");
		assertEquals(wrapper.getParameter("globalParameter"), "&lt;script&gt;Text&lt;/script&gt;");
	}
}