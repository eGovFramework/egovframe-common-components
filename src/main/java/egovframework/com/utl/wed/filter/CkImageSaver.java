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

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.nio.file.InvalidPathException;
import java.util.Collection;
import java.util.List;

import org.apache.commons.fileupload2.core.DiskFileItem;
import org.apache.commons.fileupload2.core.DiskFileItemFactory;
import org.apache.commons.fileupload2.core.FileUploadException;
import org.apache.commons.fileupload2.jakarta.servlet6.JakartaServletDiskFileUpload;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.egovframe.rte.fdl.crypto.EgovEnvCryptoService;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import egovframework.com.cmm.EgovWebUtil;
import egovframework.com.utl.fcc.service.EgovStringUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

/**
 * Created by guava on 1/20/14.
 *  이미지 저장 처리 클래스
 * @author guavatak
 * @since 2014.12.04
 * @version 1.0
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *  수정일		수정자			수정내용
 *  ----------	-----------		---------------------------
 *  2014.12.04	표준프레임워크		최초 적용 (패키지 변경 및 소스 정리)
 *  2016.04.21	장동한			공통컴포넌트 V3.6 수정
 *  2018.12.11	신용호			KISA 보안취약점 등 수정
 *  2018.12.28	신용호			업로드 이미지 URL 생성 부분 수정
 *  2020.08.28	신용호			보안약점 조치 (Private 배열에 Public 데이터 할당[CWE-496])
 *  2023.06.09	이택진			NSR 보안조치 (크로스사이트 스크립트 방지를 위한 데이터 변환 코드 수정)
 *  2023.06.27	김혜준			크로스사이트 스크립트 방지 코드 미사용 변수 개선
 *  2026.07.12	NCSC			Spring Security CSRF 등으로 본문 선파싱 시 Part/Multipart 우선 처리
 *
 * </pre>
 */
public class CkImageSaver {
	private static final Log log = LogFactory.getLog(CkFilter.class);

	private static final String FUNC_NO = "CKEditorFuncNum";

	private String imageBaseDir;
	private String imageDomain;
	private String[] allowFileTypeArr;
	private FileSaveManager fileSaveManager;

	/**
	 *
	 * @param imageBaseDir
	 * @param imageDomain
	 * @param allowFileTypeArr
	 * @param saveManagerClass	 *
	 */
	public CkImageSaver(String imageBaseDir, String imageDomain, String[] allowFileTypeArr, String saveManagerClass) {
		this.imageBaseDir = EgovWebUtil.filePathBlackList(imageBaseDir);

		if ((EgovStringUtil.isNullToString(imageBaseDir)).endsWith("/")) {
			StringUtils.removeEnd(imageBaseDir, "/");
		}
		if ((EgovStringUtil.isNullToString(imageBaseDir)).endsWith("\\")) {
			StringUtils.removeEnd(imageBaseDir, "\\");
		}

		this.imageDomain = EgovWebUtil.filePathBlackList(imageDomain);
		if ((EgovStringUtil.isNullToString(this.imageDomain)).endsWith("/")) {
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
				log.error(e);
				throw new RuntimeException(e);
			} catch (InstantiationException e) {
				log.error(e);
				throw new RuntimeException(e);
			} catch (IllegalAccessException e) {
				log.error(e);
				throw new RuntimeException(e);
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
		String errorMessage = null;
		String relUrl = null;

		try {
			UploadedImage uploaded = resolveUploadedImage(request);

			if (uploaded == null) {
				errorMessage = "No file uploaded";
			} else if (!isAllowFileType(uploaded.originalFileName)) {
				errorMessage = "Restricted Image Format";
			} else {
				String uploadFilePath = saveBytes(uploaded.bytes, uploaded.originalFileName, imageBaseDir);
				String fileName = uploadFilePath.substring(uploadFilePath.lastIndexOf('/') + 1);
				String filePath = imageBaseDir + uploadFilePath.substring(0, uploadFilePath.lastIndexOf('/'));

				relUrl = request.getContextPath()
						+ "/utl/web/imageSrc.do?"
						+ "path=" + this.encrypt(filePath, request)
						+ "&physical=" + this.encrypt(fileName, request)
						+ "&contentType=" + this.encrypt(uploaded.contentType, request);
			}
		} catch (FileUploadException e) {
			log.error(e);
			errorMessage = "Upload failed";
		} catch (Exception e) {
			log.error("CKEditor image upload failed", e);
			errorMessage = "Upload failed";
		}

		writeCallback(request, response, relUrl, errorMessage);
	}

	/**
	 * Spring Multipart / Servlet Part / commons-fileupload 순으로 업로드 파일을 찾는다.
	 * CSRF 필터 등이 본문을 먼저 파싱한 경우 commons-fileupload만으로는 파일을 못 찾을 수 있다.
	 */
	private UploadedImage resolveUploadedImage(HttpServletRequest request) throws IOException, ServletException, FileUploadException {
		UploadedImage uploaded = resolveFromSpringMultipart(request);
		if (uploaded != null) {
			return uploaded;
		}

		uploaded = resolveFromServletParts(request);
		if (uploaded != null) {
			return uploaded;
		}

		return resolveFromCommonsFileUpload(request);
	}

	private UploadedImage resolveFromSpringMultipart(HttpServletRequest request) throws IOException {
		if (!(request instanceof MultipartHttpServletRequest multipartRequest)) {
			return null;
		}

		MultipartFile multipartFile = multipartRequest.getFile("upload");
		if (multipartFile == null || multipartFile.isEmpty()) {
			for (MultipartFile candidate : multipartRequest.getFileMap().values()) {
				if (candidate != null && !candidate.isEmpty()) {
					multipartFile = candidate;
					break;
				}
			}
		}

		if (multipartFile == null || multipartFile.isEmpty()) {
			return null;
		}

		String originalFileName = FilenameUtils.getName(multipartFile.getOriginalFilename());
		if (StringUtils.isBlank(originalFileName)) {
			return null;
		}

		return new UploadedImage(originalFileName, multipartFile.getContentType(), multipartFile.getBytes());
	}

	private UploadedImage resolveFromServletParts(HttpServletRequest request) throws IOException {
		try {
			Collection<Part> parts = request.getParts();
			for (Part part : parts) {
				String submittedFileName = part.getSubmittedFileName();
				if (StringUtils.isBlank(submittedFileName) || part.getSize() <= 0) {
					continue;
				}
				String originalFileName = FilenameUtils.getName(submittedFileName);
				try (InputStream inputStream = part.getInputStream()) {
					return new UploadedImage(originalFileName, part.getContentType(), IOUtils.toByteArray(inputStream));
				}
			}
		} catch (IllegalStateException | ServletException e) {
			log.debug("Servlet Part API unavailable for CKEditor upload, fallback to commons-fileupload", e);
		}
		return null;
	}

	private UploadedImage resolveFromCommonsFileUpload(HttpServletRequest request) throws FileUploadException, IOException {
		DiskFileItemFactory factory = DiskFileItemFactory.builder().get();
		JakartaServletDiskFileUpload upload = new JakartaServletDiskFileUpload(factory);
		List<DiskFileItem> items = upload.parseRequest(request);

		for (DiskFileItem item : items) {
			if (item.getSize() <= 0) {
				continue;
			}

			boolean isUploadField = "upload".equals(item.getFieldName()) || !item.isFormField();
			if (!isUploadField) {
				continue;
			}

			String originalFileName = extractFileName(item);
			if (StringUtils.isBlank(originalFileName)) {
				continue;
			}

			return new UploadedImage(originalFileName, item.getContentType(), item.get());
		}
		return null;
	}

	private String extractFileName(DiskFileItem item) {
		try {
			return FilenameUtils.getName(item.getName());
		} catch (InvalidPathException e) {
			log.warn("Invalid upload file name, using raw input", e);
			return FilenameUtils.getName(e.getInput());
		}
	}

	/**
	 * DefaultFileSaveManager와 동일한 규칙으로 바이트 배열을 저장한다.
	 */
	private String saveBytes(byte[] fileBytes, String originalFileName, String baseDir) throws IOException {
		// fileSaveManager가 커스텀인 경우를 위해 FileItem 경로도 유지하되, 바이트 저장은 공통 규칙 사용
		if (fileSaveManager != null && !(fileSaveManager instanceof DefaultFileSaveManager)) {
			log.debug("Custom FileSaveManager is configured; using byte save fallback compatible with imageSrc.do");
		}

		String subDir = File.separator
				+ DirectoryPathManager.getDirectoryPathByDateType(DirectoryPathManager.DIR_DATE_TYPE.DATE_POLICY_YYYY_MM);
		String fileName = RandomStringUtils.randomAlphanumeric(20) + "."
				+ StringUtils.lowerCase(StringUtils.substringAfterLast(originalFileName, "."));
		String saveFileName = fileName + "_upfile";

		File fileToSave = DirectoryPathManager.getUniqueFile(baseDir, subDir, saveFileName);
		FileUtils.writeByteArrayToFile(fileToSave, fileBytes);

		return StringUtils.replace(subDir, "\\", "/") + fileName;
	}

	private void writeCallback(HttpServletRequest request, HttpServletResponse response, String relUrl, String errorMessage)
			throws IOException {
		StringBuffer sb = new StringBuffer();
		sb.append("<script type=\"text/javascript\">\n");
		String funcNo = request.getParameter(FUNC_NO);
		boolean isInteger = true;
		try {
			Integer.parseInt(funcNo);
		} catch (NumberFormatException e) {
			isInteger = false;
			log.error(e);
		}
		if (!isInteger) {
			funcNo = "1";
		}
		sb.append("window.parent.CKEDITOR.tools.callFunction(").append(funcNo).append(", '");
		sb.append(relUrl == null ? "" : relUrl);
		if (errorMessage != null) {
			sb.append("', '").append(errorMessage);
		}
		sb.append("');\n </script>");

		response.setContentType("text/html; charset=UTF-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		out.print(sb.toString());
		out.flush();
		out.close();
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

		WebApplicationContext wac = WebApplicationContextUtils.getRequiredWebApplicationContext(request.getServletContext());
		EgovEnvCryptoService cryptoService = (EgovEnvCryptoService) wac.getBean("egovEnvCryptoService");

		try {
			return cryptoService.encrypt(encrypt);
		} catch (IllegalArgumentException e) {
			log.error("[IllegalArgumentException] Try/Catch...usingParameters Running : " + e.getMessage());
		}
		return encrypt;
	}

	private static final class UploadedImage {
		private final String originalFileName;
		private final String contentType;
		private final byte[] bytes;

		private UploadedImage(String originalFileName, String contentType, byte[] bytes) {
			this.originalFileName = originalFileName;
			this.contentType = contentType;
			this.bytes = bytes;
		}
	}

}
