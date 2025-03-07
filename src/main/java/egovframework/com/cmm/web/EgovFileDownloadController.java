package egovframework.com.cmm.web;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.egovframe.rte.fdl.cryptography.EgovEnvCryptoService;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import egovframework.com.cmm.EgovBrowserUtil;
import egovframework.com.cmm.EgovWebUtil;
import egovframework.com.cmm.service.EgovFileMngService;
import egovframework.com.cmm.service.FileVO;
import egovframework.com.cmm.util.EgovBasicLogger;
import egovframework.com.cmm.util.EgovResourceCloseHelper;
import egovframework.com.cmm.util.EgovUserDetailsHelper;

/**
 * 파일 다운로드를 위한 컨트롤러 클래스
 * 
 * @author 공통서비스개발팀 이삼섭
 * @since 2009.06.01
 * @version 1.0
 * @see
 *
 *      <pre>
 * << 개정이력(Modification Information) >>
 *
 *     수정일      	수정자           수정내용
 *  ------------   --------    ---------------------------
 *   2009.03.25  	이삼섭          최초 생성
 *   2014.02.24		이기하          IE11 브라우저 한글 파일 다운로드시 에러 수정
 *   2018.08.28		신용호          Safari, Chrome, Firefox, Opera 한글파일 다운로드 처리 수정 (macOS에서 확장자 exe붙는 문제 처리)
 *   2022.12.02     윤창원          File ID 암호화 처리
 *
 * Copyright (C) 2009 by MOPAS  All right reserved.
 *      </pre>
 */
@Controller
public class EgovFileDownloadController {

	/** 암호화서비스 */
	@Resource(name = "egovEnvCryptoService")
	EgovEnvCryptoService cryptoService;

	@Resource(name = "EgovFileMngService")
	private EgovFileMngService fileService;

	/**
	 * 첨부파일로 등록된 파일에 대하여 다운로드를 제공한다.
	 *
	 * @param commandMap
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "/cmm/fms/FileDown.do")
	public void cvplFileDownload(@RequestParam Map<String, Object> commandMap, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();

		if (isAuthenticated) {

			// 암호화된 atchFileId 를 복호화하고 동일한 세션인 경우만 다운로드할 수 있다. (2022.12.06 추가) - 파일아이디가 유추 불가능하도록 조치
			String param_atchFileId = (String) commandMap.get("atchFileId");
	    	param_atchFileId = param_atchFileId.replaceAll(" ", "+");
			byte[] decodedBytes = Base64.getDecoder().decode(param_atchFileId);
			String decodedString = cryptoService.decrypt(new String(decodedBytes));
			String decodedSessionId = StringUtils.substringBefore(decodedString, "|");
			String decodedFileId = StringUtils.substringAfter(decodedString, "|");
			String fileSn = (String) commandMap.get("fileSn");

			String sessionId = request.getSession().getId();

			boolean isSameSessionId = StringUtils.equals(decodedSessionId, sessionId);

			if (!isSameSessionId) {
				throw new Exception();
			}

			FileVO fileVO = new FileVO();
			fileVO.setAtchFileId(decodedFileId);
			fileVO.setFileSn(fileSn);
			FileVO fvo = fileService.selectFileInf(fileVO);

			File uFile = new File(fvo.getFileStreCours(), fvo.getStreFileNm());
			long fSize = uFile.length();

			if (fSize > 0) {
				String mimetype = "application/x-msdownload";

				String userAgent = request.getHeader("User-Agent");
				HashMap<String, String> result = EgovBrowserUtil.getBrowser(userAgent);
				if (!EgovBrowserUtil.MSIE.equals(result.get(EgovBrowserUtil.TYPEKEY))) {
					mimetype = "application/x-stuff";
				}

				String contentDisposition = EgovBrowserUtil.getDisposition(fvo.getOrignlFileNm(), userAgent, "UTF-8");
				// response.setBufferSize(fSize); // OutOfMemeory 발생
				response.setContentType(mimetype);
				// response.setHeader("Content-Disposition", "attachment; filename=\"" +
				// contentDisposition + "\"");
				response.setHeader("Content-Disposition", contentDisposition);
				response.setContentLengthLong(fSize);

				/*
				 * FileCopyUtils.copy(in, response.getOutputStream()); in.close();
				 * response.getOutputStream().flush(); response.getOutputStream().close();
				 */
				BufferedInputStream in = null;
				BufferedOutputStream out = null;

				try {
					in = new BufferedInputStream(new FileInputStream(uFile));
					out = new BufferedOutputStream(response.getOutputStream());

					FileCopyUtils.copy(in, out);
					out.flush();
				} catch (IOException ex) {
					// 다음 Exception 무시 처리
					// Connection reset by peer: socket write error
					EgovBasicLogger.ignore("IO Exception", ex);
				} finally {
					EgovResourceCloseHelper.close(in, out);
				}

			} else {
				response.setContentType("application/x-msdownload");

				PrintWriter printwriter = response.getWriter();

				printwriter.println("<html>");
				printwriter.println("<br><br><br><h2>Could not get file name:<br>"
						+ EgovWebUtil.clearXSSMaximum(fvo.getOrignlFileNm()) + "</h2>");// 2022.01 Potential XSS in
																						// Servlet
				printwriter
						.println("<br><br><br><center><h3><a href='javascript: history.go(-1)'>Back</a></h3></center>");
				printwriter.println("<br><br><br>&copy; webAccess");
				printwriter.println("</html>");

				printwriter.flush();
				printwriter.close();
			}
		}
	}
}
