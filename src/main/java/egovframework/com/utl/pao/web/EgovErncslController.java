package egovframework.com.utl.pao.web;

import java.io.IOException;
import java.sql.SQLException;

import javax.annotation.Resource;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import egovframework.com.utl.pao.service.EgovPrntngOutpt;
import egovframework.com.utl.pao.service.PrntngOutptVO;

/**
 *
 * 전자관인에 관한 Util 테스트를 위한 화면 Controller
 * @author 공통서비스 개발팀 이중호
 * @since 2009.04.01
 * @version 1.0
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *  수정일               수정자            수정내용
 *  ----------   --------   ---------------------------
 *  2009.04.01   이중호            최초 생성
 *  2017-02-14   이정은            시큐어코딩(ES) - 시큐어코딩 부적절한 예외 처리[CWE-253, CWE-440, CWE-754]
 *  2019.12.06   신용호            KISA 보안약점 조치 (부적절한 예외처리) , EgovPropertyService 삭제
 *
 * </pre>
 */
@Controller
public class EgovErncslController extends HttpServlet {

	private static final long serialVersionUID = 8921470672390456794L;

	@Resource(name = "PrntngOutpt")
	private EgovPrntngOutpt prntngOutpt;

	private static final Logger LOGGER = LoggerFactory.getLogger(EgovErncslController.class);

	/**
	 * 서블릿 초기화
	 */
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
	}

	/**
	 * 관인이미지를 조회하여 출력
	 * @param
	 * @param
	 * @return
	 * @exception MyException
	 * @see
	*/
	@RequestMapping(value = "/utl/pao/EgovErncsl.do")
	public void doGet(@RequestParam("sOrgCode") String orgCode, @RequestParam("sErncslSe") String erncslSe, HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		LOGGER.info("EgovErncsl start....");

		PrntngOutptVO req = new PrntngOutptVO();

		req.setOrgCode(orgCode);
		req.setErncslSe(erncslSe);

		PrntngOutptVO res = null;
		try {
			res = prntngOutpt.selectErncsl(req);
		} catch (SQLException e) {
			LOGGER.error("["+ e.getClass() +"] : ", e.getMessage());
			throw new RuntimeException("Service call error", e);
		} catch (Exception e) {
//			LOGGER.error(e.getMessage());
			// 2017-02-14  이정은          시큐어코딩(ES) - 시큐어코딩 부적절한 예외 처리[CWE-253, CWE-440, CWE-754]
			LOGGER.error("["+ e.getClass() +"] : ", e.getMessage());
			throw new RuntimeException("Service call error", e);
		}

		if (res == null) {
			throw new RuntimeException("image not found!!!");
		}

		byte[] img = res.getImgInfo();
		String imgtype = res.getImgType();
		String type = "";

		if (imgtype != null && !"".equals(imgtype)) {
			type = "image/" + imgtype;
		} else {
			LOGGER.debug("Image fileType is null.");
		}
		if (img == null) {
			LOGGER.debug("Image fileInfo is null.");
			return;
		}

		response.setHeader("Content-Type", type.replaceAll("\r", "").replaceAll("\n", ""));
		response.setHeader("Content-Length", "" + img.length);
		response.getOutputStream().write(img);
		response.getOutputStream().flush();
		response.getOutputStream().close();

		LOGGER.info("EgovErncsl end....");
	}
}