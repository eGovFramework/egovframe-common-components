/*
 * CKEditor image upload module for Java.
 * Copyright guavatak (https://github.com/guavatak/ckeditor-upload-filter-java)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * @author guavatak (https://github.com/guavatak/ckeditor-upload-filter-java)
 */
package egovframework.com.utl.wed.filter;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.egovframe.rte.fdl.cmmn.exception.BaseRuntimeException;
import org.egovframe.rte.fdl.cryptography.EgovEnvCryptoService;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import egovframework.com.cmm.EgovWebUtil;
import egovframework.com.utl.fcc.service.EgovStringUtil;
import lombok.extern.slf4j.Slf4j;

/**
 * 이미지 저장 처리 클래스
 * 
 * @author 표준프레임워크
 * @since 2014.12.04
 * @version 1.0
 * @see
 *
 *      <pre>
 *  == 개정이력(Modification Information) ==
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2014.12.04  표준프레임워크    최초 적용 (패키지 변경 및 소스 정리)
 *   2016.04.21  장동한          공통컴포넌트 V3.6 수정
 *   2018.12.11  신용호          KISA 보안취약점 등 수정
 *   2018.12.28  신용호          업로드 이미지 URL 생성 부분 수정
 *   2020.08.28  신용호          보안약점 조치 (Private 배열에 Public 데이터 할당[CWE-496])
 *   2023.06.09  이택진          NSR 보안조치 (크로스사이트 스크립트 방지를 위한 데이터 변환 코드 수정)
 *   2023.06.27  김혜준          크로스사이트 스크립트 방지 코드 미사용 변수 개선
 *   2025.08.30  이선규          2025년 컨트리뷰션 PMD로 소프트웨어 보안약점 진단하고 제거하기-FieldNamingConventions(변수명에 밑줄 사용)
 *   2025.08.30  이선규          2025년 컨트리뷰션 PMD로 소프트웨어 보안약점 진단하고 제거하기-ImmutableField(생성자를 통해 할당된 변수를 Final로 선언하지 않았음)
 *   2025.08.30  이선규          2025년 컨트리뷰션 PMD로 소프트웨어 보안약점 진단하고 제거하기-UselessParentheses(불필요한 괄호사용)
 *   2025.08.30  이선규          2025년 컨트리뷰션 PMD로 소프트웨어 보안약점 진단하고 제거하기-CloseResource(부적절한 자원 해제)
 *
 *      </pre>
 */
@Slf4j
public class CkImageSaver {

	private static final String FUNC_NO = "CKEditorFuncNum";

	private final String imageBaseDir;
	private final String imageDomain;
	private final String[] allowFileTypeArr;
	private final FileSaveManager fileSaveManager;

	/**
	 *
	 * @param imageBaseDir
	 * @param imageDomain
	 * @param allowFileTypeArr
	 * @param saveManagerClass *
	 */
	public CkImageSaver(String imageBaseDir, String imageDomain, String[] allowFileTypeArr, String saveManagerClass) {
		this.imageBaseDir = EgovWebUtil.filePathBlackList(imageBaseDir);

		if (EgovStringUtil.isNullToString(imageBaseDir).endsWith("/")) {
			StringUtils.removeEnd(imageBaseDir, "/");
		}
		if (EgovStringUtil.isNullToString(imageBaseDir).endsWith("\\")) {
			StringUtils.removeEnd(imageBaseDir, "\\");
		}

		this.imageDomain = EgovWebUtil.filePathBlackList(imageDomain);
		if (EgovStringUtil.isNullToString(this.imageDomain).endsWith("/")) {
			StringUtils.removeEnd(this.imageDomain, "/");
		}

		this.allowFileTypeArr = allowFileTypeArr.clone();

		if (StringUtils.isBlank(saveManagerClass)) {
			fileSaveManager = new DefaultFileSaveManager();
		} else {
			try {
				Class<?> klass = Class.forName(saveManagerClass);
				fileSaveManager = (FileSaveManager) klass.newInstance();
			} catch (ClassNotFoundException e) {
				throw new BaseRuntimeException(e);
			} catch (InstantiationException e) {
				throw new BaseRuntimeException(e);
			} catch (IllegalAccessException e) {
				throw new BaseRuntimeException(e);
			}
		}
	}

	/**
	 *
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	public void saveAndReturnUrlToClient(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// Parse the request
		try {
			FileItemFactory factory = new DiskFileItemFactory();

			// Create a new file upload handler
			ServletFileUpload upload = new ServletFileUpload(factory);

			List<FileItem> /* FileItem */ items = upload.parseRequest(request);
			// We upload just one file at the same time
			FileItem uplFile = items.get(0);

			String errorMessage = null;
			String relUrl = null;

			if (isAllowFileType(FilenameUtils.getName(uplFile.getName()))) {
				String uploadFilePath = fileSaveManager.saveFile(uplFile, imageBaseDir);
				// System.out.println("===>>> uploadFilePath = "+uploadFilePath);

				String fileName = uploadFilePath.substring(uploadFilePath.lastIndexOf('/') + 1);
				String filePath = imageBaseDir + uploadFilePath.substring(0, uploadFilePath.lastIndexOf('/'));

				relUrl = request.getContextPath() + "/utl/web/imageSrc.do?" + "path=" + this.encrypt(filePath, request)
						+ "&physical=" + this.encrypt(fileName, request) + "&contentType="
						+ this.encrypt(uplFile.getContentType(), request);

				// System.out.println("===>>> relUrl = "+relUrl);
			} else {
				errorMessage = "Restricted Image Format";
			}

			StringBuffer sb = new StringBuffer();
			sb.append("<script type=\"text/javascript\">\n");
			// Compressed version of the document.domain automatic fix script.
			// The original script can be found at
			// [fckeditor_dir]/_dev/domain_fix_template.js
			// sb.append("(function(){var d=document.domain;while (true){try{var
			// A=window.parent.document.domain;break;}catch(e)
			// {};d=d.replace(/.*?(?:\\.|$)/,'');if (d.length==0)
			// break;try{document.domain=d;}catch (e){break;}}})();\n");
			// KISA 보안약점 조치 (2018-12-11, 신용호)
			String funcNo = request.getParameter(FUNC_NO);
			boolean isInteger = true;
			try {
				Integer.parseInt(funcNo);
			} catch (NumberFormatException e) {
				isInteger = false;
				throw new BaseRuntimeException(e);
			}
			if (!isInteger) {
				funcNo = "1"; // 가장 많이 사용되는 값
			}
			sb.append("window.parent.CKEDITOR.tools.callFunction(").append(funcNo).append(", '");
			sb.append(relUrl);
			if (errorMessage != null) {
				sb.append("', '").append(errorMessage);
			}
			sb.append("');\n </script>");

			response.setContentType("text/html");
			response.setHeader("Cache-Control", "no-cache");
			PrintWriter out = response.getWriter(); // NOPMD - CloseResource 규칙 무시

			out.print(sb.toString());
			out.flush();
			out.close();

		} catch (FileUploadException e) {
			throw new BaseRuntimeException(e);
		}
	}

	/**
	 *
	 * @param fileName
	 * @return
	 */
	protected boolean isAllowFileType(String fileName) {
		boolean isAllow = false;
		if (allowFileTypeArr != null && allowFileTypeArr.length > 0) {
			for (String allowFileType : allowFileTypeArr) {
				if (StringUtils.equalsIgnoreCase(allowFileType, StringUtils.substringAfterLast(fileName, "."))) {
					isAllow = true;
					break;
				}
			}
		} else {
			isAllow = true;
		}

		return isAllow;
	}

	/**
	 * 암호화
	 *
	 * @param encrypt
	 * @param request
	 * @return
	 */
	private String encrypt(String encrypt, HttpServletRequest request) {

		WebApplicationContext wac = WebApplicationContextUtils
				.getRequiredWebApplicationContext(request.getServletContext());
		EgovEnvCryptoService cryptoService = (EgovEnvCryptoService) wac.getBean("egovEnvCryptoService");

		try {
			return cryptoService.encrypt(encrypt);
		} catch (IllegalArgumentException e) {
			log.error("[IllegalArgumentException] Try/Catch...usingParameters Running : " + e.getMessage());
		}
		return encrypt;
	}

}