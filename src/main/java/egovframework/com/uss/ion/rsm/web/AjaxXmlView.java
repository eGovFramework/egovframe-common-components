package egovframework.com.uss.ion.rsm.web;

import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.view.AbstractView;

/**
*
* <pre>
* << 개정이력(Modification Information) >>
*
*   수정일      수정자           수정내용
*  ------- 	   --------    ---------------------------
*   2011.10.10  이기하		보안점검 조치(널포인트 역참조 방지)
* </pre>
*/

public class AjaxXmlView extends AbstractView {

    @SuppressWarnings("rawtypes")
	@Override
	protected void renderMergedOutputModel(Map model, HttpServletRequest request, HttpServletResponse response)
	        throws Exception {
		PrintWriter writer = null;
		try {
			response.setContentType("text/xml");
			response.setHeader("Cache-Control", "no-cache");
			response.setCharacterEncoding("UTF-8");

			writer = response.getWriter();
			writer.write((String) model.get("ajaxXml"));

		} finally {
			// 2011.10.10 보안점검 조치(널포인트 역참조 방지)
			if (writer != null) {
				writer.close();
			}
		}
	}
}