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

import static org.junit.Assert.assertThat;
import static org.hamcrest.CoreMatchers.is;

import java.util.Map;

import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.filter.CorsFilter;

import egovframework.com.cmm.filter.HTMLTagFilter;
import egovframework.com.cmm.filter.HTMLTagFilterRequestWrapper;

/**
 * Unit tests for {@link CorsFilter}.
 * @author Sebastien Deleuze
 */
public class HTMLTagFilterTest {

	HTMLTagFilter filter = new HTMLTagFilter();
	MockHttpServletRequest request;
	HTMLTagFilterRequestWrapper wrapper;

	@Test
	public void testGetMethodGetParameter() {
		request = new MockHttpServletRequest("GET", "/notExistUrl.do");
		request.addParameter("title", "<b>Text</b>");
		request.addParameter("globalParameter", "<b>Text</b>");
		wrapper = new HTMLTagFilterRequestWrapper(request);

		System.out.println("testGetMethodGetParameter===> wrapper.getParameter(title) = "+wrapper.getParameter("title"));
		System.out.println("testGetMethodGetParameter===> wrapper.getParameter(globalParameter) = "+wrapper.getParameter("globalParameter"));
		assertThat(wrapper.getParameter("title"), is("&lt;b&gt;Text&lt;/b&gt;"));
		//assertThat(wrapper.getParameter("globalParameter"), is("<b>Text</b>"));
		assertThat(wrapper.getParameter("globalParameter"), is("&lt;b&gt;Text&lt;/b&gt;"));

		request = new MockHttpServletRequest("GET", "/url1.do");
		request.addParameter("title", "<b>Text</b>");
		request.addParameter("mode", "<script>Text</script>");
		request.addParameter("globalParameter", "<script>Text</script>");
		wrapper = new HTMLTagFilterRequestWrapper(request);

		System.out.println("testGetMethodGetParameter===> wrapper.getParameter(title) = "+wrapper.getParameter("title"));
		System.out.println("testGetMethodGetParameter===> wrapper.getParameter(mode) = "+wrapper.getParameter("mode"));
		System.out.println("testGetMethodGetParameter===> wrapper.getParameter(globalParameter) = "+wrapper.getParameter("globalParameter"));
		assertThat(wrapper.getParameter("title"), is("&lt;b&gt;Text&lt;/b&gt;"));
		assertThat(wrapper.getParameter("mode"), is("&lt;script&gt;Text&lt;/script&gt;"));
		assertThat(wrapper.getParameter("globalParameter"), is("&lt;script&gt;Text&lt;/script&gt;"));
	}

	@Test
	public void testPostMethodGetParameter() {
		request = new MockHttpServletRequest("POST", "/notExistUrl.do");
		request.addParameter("title", "<b>Text</b>");
		request.addParameter("globalParameter", "<b>Text</b>");
		wrapper = new HTMLTagFilterRequestWrapper(request);

		System.out.println("testPostMethodGetParameter===> wrapper.getParameter(title) = "+wrapper.getParameter("title"));
		System.out.println("testPostMethodGetParameter===> wrapper.getParameter(globalParameter) = "+wrapper.getParameter("globalParameter"));
		assertThat(wrapper.getParameter("title"), is("&lt;b&gt;Text&lt;/b&gt;"));
		assertThat(wrapper.getParameter("globalParameter"), is("&lt;b&gt;Text&lt;/b&gt;"));
		//assertThat(wrapper.getParameter("globalParameter"), is("<b>Text</b>"));

		request = new MockHttpServletRequest("POST", "/url1.do");
		request.addParameter("title", "<b>Text</b>");
		request.addParameter("mode", "<script>Text</script>");
		request.addParameter("globalParameter", "<script>Text</script>");
		wrapper = new HTMLTagFilterRequestWrapper(request);

		System.out.println("testPostMethodGetParameter===> wrapper.getParameter(title) = "+wrapper.getParameter("title"));
		System.out.println("testPostMethodGetParameter===> wrapper.getParameter(mode) = "+wrapper.getParameter("mode"));
		System.out.println("testPostMethodGetParameter===> wrapper.getParameter(globalParameter) = "+wrapper.getParameter("globalParameter"));
		assertThat(wrapper.getParameter("title"), is("&lt;b&gt;Text&lt;/b&gt;"));
		assertThat(wrapper.getParameter("mode"), is("&lt;script&gt;Text&lt;/script&gt;"));
		assertThat(wrapper.getParameter("globalParameter"), is("&lt;script&gt;Text&lt;/script&gt;"));
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
		assertThat(values[0], is("&lt;b&gt;Text1&lt;/b&gt;"));
		assertThat(values[1], is("&lt;b&gt;Text2&lt;/b&gt;"));

		values = wrapper.getParameterValues("globalParameter");
		assertThat(values[0], is("&lt;b&gt;Text1&lt;/b&gt;"));
		assertThat(values[1], is("&lt;b&gt;Text2&lt;/b&gt;"));
		//assertThat(values[0], is("<b>Text1</b>"));
		//assertThat(values[1], is("<b>Text2</b>"));

		request = new MockHttpServletRequest("GET", "/url1.do");
		request.addParameter("title", "<b>Text1</b>");
		request.addParameter("title", "<b>Text2</b>");
		request.addParameter("mode", "<script>Text1</script>");
		request.addParameter("mode", "<script>Text2</script>");
		request.addParameter("globalParameter", "<script>Text1</script>");
		request.addParameter("globalParameter", "<script>Text2</script>");
		wrapper = new HTMLTagFilterRequestWrapper(request);

		values = wrapper.getParameterValues("title");
		assertThat(values[0], is("&lt;b&gt;Text1&lt;/b&gt;"));
		assertThat(values[1], is("&lt;b&gt;Text2&lt;/b&gt;"));

		values = wrapper.getParameterValues("mode");
		assertThat(values[0], is("&lt;script&gt;Text1&lt;/script&gt;"));
		assertThat(values[1], is("&lt;script&gt;Text2&lt;/script&gt;"));

		values = wrapper.getParameterValues("globalParameter");
		assertThat(values[0], is("&lt;script&gt;Text1&lt;/script&gt;"));
		assertThat(values[1], is("&lt;script&gt;Text2&lt;/script&gt;"));
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
		assertThat(values[0], is("&lt;b&gt;Text1&lt;/b&gt;"));
		assertThat(values[1], is("&lt;b&gt;Text2&lt;/b&gt;"));

		values = (String[])map.get("globalParameter");
		assertThat(values[0], is("&lt;b&gt;Text1&lt;/b&gt;"));
		//assertThat(values[0], is("<b>Text1</b>"));

		request = new MockHttpServletRequest("GET", "/url1.do");
		request.addParameter("title", "<b>Text1</b>");
		request.addParameter("title", "<b>Text2</b>");
		request.addParameter("mode", "<script>Text1</script>");
		request.addParameter("mode", "<script>Text2</script>");
		request.addParameter("globalParameter", "<script>Text1</script>");
		wrapper = new HTMLTagFilterRequestWrapper(request);

		map = wrapper.getParameterMap();
		values = (String[])map.get("title");
		assertThat(values[0], is("&lt;b&gt;Text1&lt;/b&gt;"));
		assertThat(values[1], is("&lt;b&gt;Text2&lt;/b&gt;"));

		values = (String[])map.get("mode");
		assertThat(values[0], is("&lt;script&gt;Text1&lt;/script&gt;"));
		assertThat(values[1], is("&lt;script&gt;Text2&lt;/script&gt;"));

		values = (String[])map.get("globalParameter");
		System.out.println("===>>>"+values[0]);
		assertThat(values[0], is("&lt;script&gt;Text1&lt;/script&gt;"));
	}

	@Test
	public void testContextPath() {
		request = new MockHttpServletRequest("GET", "/test/notExistUrl.do");
		request.setContextPath("/test");
		request.addParameter("title", "<b>Text</b>");
		request.addParameter("globalParameter", "<b>Text</b>");
		wrapper = new HTMLTagFilterRequestWrapper(request);

		assertThat(wrapper.getParameter("title"), is("&lt;b&gt;Text&lt;/b&gt;"));
		assertThat(wrapper.getParameter("globalParameter"), is("&lt;b&gt;Text&lt;/b&gt;"));
		//assertThat(wrapper.getParameter("globalParameter"), is("<b>Text</b>"));

		request = new MockHttpServletRequest("GET", "/test/url1.do");
		request.setContextPath("/test");
		request.addParameter("title", "<b>Text</b>");
		request.addParameter("mode", "<script>Text</script>");
		request.addParameter("globalParameter", "<script>Text</script>");
		wrapper = new HTMLTagFilterRequestWrapper(request);

		assertThat(wrapper.getParameter("title"), is("&lt;b&gt;Text&lt;/b&gt;"));
		assertThat(wrapper.getParameter("mode"), is("&lt;script&gt;Text&lt;/script&gt;"));
		assertThat(wrapper.getParameter("globalParameter"), is("&lt;script&gt;Text&lt;/script&gt;"));
	}
}