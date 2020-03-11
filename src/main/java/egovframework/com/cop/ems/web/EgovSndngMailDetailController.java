package egovframework.com.cop.ems.web;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;

import egovframework.com.cmm.EgovWebUtil;
import egovframework.com.cmm.service.Globals;
import egovframework.com.cop.ems.service.EgovSndngMailDetailService;
import egovframework.com.cop.ems.service.SndngMailVO;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 발송메일을 상세 조회하는 컨트롤러 클래스
 * @author 공통서비스 개발팀 박지욱
 * @since 2009.03.12
 * @version 1.0
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *  수정일                 수정자             수정내용
 *  ----------    --------    ---------------------------
 *  2009.03.12    박지욱              최초 생성
 *  2011.10.10	   이기하		    보안점검 후속조치(교차접속 스크립트 공격 취약성 방지(파라미터 문자열 교체),
 *  											  HTTP 응답분할 방지)
 *  2017.03.03 	   조성원 	           시큐어코딩(ES)-부적절한 예외 처리[CWE-253, CWE-440, CWE-754]
 *  2019.11.29 	   신용호 	      KISA 보안약점 조치 : HTTP응답분할(HTTP_Response_Splitting,CRLF)취약점 조치
 *
 *  </pre>
 */
@Controller
public class EgovSndngMailDetailController {

	private static final Logger LOGGER = LoggerFactory.getLogger(EgovSndngMailDetailController.class);

	/** EgovSndngMailDetailService */
	@Resource(name = "sndngMailDetailService")
	private EgovSndngMailDetailService sndngMailDetailService;

	/**
	 * 발송메일을 상세 조회한다.
	 * @param sndngMailVO SndngMailVO
	 * @return String
	 * @exception Exception
	 */
	@RequestMapping(value = "/cop/ems/selectSndngMailDetail.do")
	public String selectSndngMail(@ModelAttribute("sndngMailVO") SndngMailVO sndngMailVO, ModelMap model) throws Exception {

		if (sndngMailVO == null || sndngMailVO.getMssageId() == null || sndngMailVO.getMssageId().equals("")) {
			return "egovframework/com/cmm/egovError";
		}

		// 1. 발송메일을 상세 조회한다.
		SndngMailVO resultMailVO = sndngMailDetailService.selectSndngMail(sndngMailVO);

		// 2. 결과 리턴
		model.addAttribute("resultInfo", resultMailVO);
		if (!resultMailVO.getMssageId().equals("")) {
			// 발송메일 상세조회 화면 이동
			return "egovframework/com/cop/ems/EgovMailDetail";
		} else {
			// 오류 페이지 이동
			return "egovframework/com/cmm/egovError";
		}
	}

	/**
	 * 발송메일을 삭제한다.
	 * @param sndngMailVO SndngMailVO
	 * @return String
	 * @exception Exception
	 */
	@RequestMapping(value = "/cop/ems/deleteSndngMail.do")
	public String deleteSndngMail(@ModelAttribute("sndngMailVO") SndngMailVO sndngMailVO, ModelMap model) throws Exception {

		if (sndngMailVO == null || sndngMailVO.getMssageId() == null || sndngMailVO.getMssageId().equals("")) {
			return "egovframework/com/cmm/egovError";
		}

		// 1. 발송메일을 삭제한다.
		sndngMailDetailService.deleteSndngMail(sndngMailVO);

		// 2. 첨부파일을 삭제한다.
		sndngMailDetailService.deleteAtchmnFile(sndngMailVO);

		// 3. 발송메일 목록 페이지 이동
		return "redirect:/cop/ems/selectSndngMailList.do";
	}

	/**
	 * 발송메일 내용조회로 돌아간다.
	 * @param sndngMailVO SndngMailVO
	 * @return String
	 * @exception Exception
	 */
	@RequestMapping(value = "/cop/ems/backSndngMailDetail.do")
	public String backSndngMailDtls(@ModelAttribute("sndngMailVO") SndngMailVO sndngMailVO, ModelMap model) throws Exception {

		return "redirect:/cop/ems/selectSndngMailList.do";
	}

	/**
	 * XML형태의 발송요청메일을 조회한다.
	 * @param sndngMailVO SndngMailVO
	 * @exception Exception
	 */
	@RequestMapping(value = "/cop/ems/selectSndngMailXml.do")
	public void selectSndngMailXml(@ModelAttribute("sndngMailVO") SndngMailVO sndngMailVO, HttpServletResponse response, ModelMap model) throws Exception {
		String xmlFile = Globals.MAIL_REQUEST_PATH + sndngMailVO.getMssageId() + ".xml";
		File uFile = new File(EgovWebUtil.filePathBlackList(xmlFile));
		int fSize = (int) uFile.length();

		if (fSize > 0) {
			String mimetype = "application/x-msdownload;charset=UTF-8";

			response.setContentType(mimetype);
			response.setHeader("Content-Disposition", "attachment; filename=\"" + EgovWebUtil.removeCRLF(uFile.getName()) + "\"");
			response.setContentLength(fSize);

			BufferedInputStream in = null;
			try {
				in = new BufferedInputStream(new FileInputStream(uFile));
				FileCopyUtils.copy(in, response.getOutputStream());
			} finally {
				if (in != null) {
					try {
						in.close();
					 //2017.03.03 	조성원 	시큐어코딩(ES)-부적절한 예외 처리[CWE-253, CWE-440, CWE-754]
			         }catch (IOException ignore){
			        	 LOGGER.error("[IOException] : Connection Close");
			         } catch (Exception ignore) {
						LOGGER.error("["+ ignore.getClass() +"] : Connection Close ", ignore.getMessage());
					 }
				}
			}
			response.getOutputStream().flush();
			response.getOutputStream().close();
		} else {
			response.setContentType("application/x-msdownload");
			PrintWriter printwriter = response.getWriter();
			printwriter.println("<html>");
			printwriter.println("<br><br><br><h2>Could not get file name:<br>" + EgovWebUtil.clearXSSMinimum(xmlFile) + "</h2>");
			printwriter.println("<br><br><br><center><h3><a href='javascript: history.go(-1)'>Back</a></h3></center>");
			printwriter.println("<br><br><br>&copy; webAccess");
			printwriter.println("</html>");
			printwriter.flush();
			printwriter.close();
		}
	}
}