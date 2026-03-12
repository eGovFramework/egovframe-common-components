package egovframework.com.cmm.web;

/*
 * Copyright 2001-2006 The Apache Software Foundation.
 *
 * Licensed under the Apache License, Version 2.0 (the ";License&quot;);
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS"; BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;

import egovframework.com.cmm.service.EgovProperties;
import egovframework.com.utl.fcc.service.EgovFileUploadUtil;
import jakarta.servlet.http.HttpServletRequest;

/**
 * 실행환경의 파일업로드 처리를 위한 기능 클래스
 *
 * @author 공통서비스개발팀 이삼섭
 * @since 2009.06.01
 * @version 1.0
 * @see
 *
 *      <pre>
 * << 개정이력(Modification Information) >>
 *
 *  수정일                수정자             수정내용
 *  ----------   --------    ---------------------------
 *  2009.03.25   이삼섭              최초 생성
 *  2011.06.11   서준식              스프링 3.0 업그레이드 API변경으로인한 수정
 *  2020.10.27   신용호              예외처리 수정
 *  2020.10.29   신용호              허용되지 않는 확장자 업로드 제한 (globals.properties > Globals.fileUpload.Extensions)
 *  2025.07.01   유지보수            Spring Framework 6.2.8, JDK 17 기반으로 업데이트
 *
 *      </pre>
 */
public class EgovMultipartResolver extends StandardServletMultipartResolver {

	private static final Logger LOGGER = LoggerFactory.getLogger(EgovMultipartResolver.class);

	public EgovMultipartResolver() {
		super();
	}

	/**
	 * multipart 요청을 파싱하여 파일 업로드 보안 검증을 수행한다.
	 *
	 * @param request HTTP 요청
	 * @param encoding 인코딩
	 * @return MultipartHttpServletRequest
	 * @throws MultipartException multipart 파싱 중 오류 발생 시
	 */
	@Override
	public MultipartHttpServletRequest resolveMultipart(HttpServletRequest request) throws MultipartException {
		try {
			MultipartHttpServletRequest multipartRequest = super.resolveMultipart(request);

			// 파일 업로드 보안 검증 수행
			validateUploadedFiles(multipartRequest);

			return multipartRequest;
		} catch (MultipartException e) {
			LOGGER.error("Multipart parsing failed: {}", e.getMessage(), e);
			throw e;
		}
	}

	/**
	 * 업로드된 파일들의 보안 검증을 수행한다.
	 *
	 * @param multipartRequest MultipartHttpServletRequest
	 * @throws SecurityException 보안 검증 실패 시
	 */
	private void validateUploadedFiles(MultipartHttpServletRequest multipartRequest) throws SecurityException {
		Map<String, List<MultipartFile>> fileMap = multipartRequest.getMultiFileMap();
		String whiteListFileUploadExtensions = EgovProperties.getProperty("Globals.fileUpload.Extensions");

		// 파일 개수 제한 검증 추가
		validateFileCount(multipartRequest);

		LOGGER.debug("File upload validation - Whitelist extensions: {}", whiteListFileUploadExtensions);

		for (Map.Entry<String, List<MultipartFile>> entry : fileMap.entrySet()) {
			String fieldName = entry.getKey();
			List<MultipartFile> files = entry.getValue();

			for (MultipartFile file : files) {
				if (file != null && !file.isEmpty()) {
					validateFile(file, whiteListFileUploadExtensions);
					LOGGER.debug("File validation passed for field [{}]: {} ({} bytes)",
						fieldName, file.getOriginalFilename(), file.getSize());
				}
			}
		}
	}

	/**
	 * 개별 파일의 보안 검증을 수행한다.
	 *
	 * @param file 검증할 파일
	 * @param whiteListFileUploadExtensions 허용된 파일 확장자 목록
	 * @throws SecurityException 보안 검증 실패 시
	 */
	private void validateFile(MultipartFile file, String whiteListFileUploadExtensions) throws SecurityException {
		String fileName = file.getOriginalFilename();

		if (fileName == null || fileName.trim().isEmpty()) {
			LOGGER.warn("File name is null or empty");
			throw new SecurityException("File name is null or empty");
		}

		String fileExtension = EgovFileUploadUtil.getFileExtension(fileName);
		LOGGER.debug("Validating file: {} with extension: {}", fileName, fileExtension);

		// 확장자가 없는 경우 처리 불가
		if (fileExtension == null || fileExtension.trim().isEmpty()) {
			LOGGER.warn("File extension not found for file: {}", fileName);
			throw new SecurityException("[No file extension] File extension not allowed.");
		}

		// 화이트리스트 검증
		if (whiteListFileUploadExtensions != null && !whiteListFileUploadExtensions.trim().isEmpty()) {
			String[] allowedExtensions = whiteListFileUploadExtensions.split(",");
			boolean isAllowed = false;

			for (String allowedExt : allowedExtensions) {
				String trimmedExt = allowedExt.trim().toLowerCase();
				// 점(.)으로 시작하는 경우 제거
				if (trimmedExt.startsWith(".")) {
					trimmedExt = trimmedExt.substring(1);
				}
				if (trimmedExt.equals(fileExtension.toLowerCase())) {
					isAllowed = true;
					break;
				}
			}

			if (!isAllowed) {
				LOGGER.warn("File extension [{}] not allowed for file: {}", fileExtension, fileName);
				throw new SecurityException("[" + fileExtension + "] File extension not allowed.");
			}
		} else {
			LOGGER.debug("No file extension whitelist configured, allowing all extensions");
		}

		// 파일 크기 검증 (기본값: 10MB)
		long maxFileSize = getMaxFileSize();
		if (file.getSize() > maxFileSize) {
			LOGGER.warn("File size [{}] exceeds maximum allowed size [{}] for file: {}",
				file.getSize(), maxFileSize, fileName);
			throw new SecurityException("File size exceeds maximum allowed size.");
		}
	}

	/**
	 * 파일 개수 제한을 검증한다.
	 *
	 * @param multipartRequest MultipartHttpServletRequest
	 * @throws SecurityException 파일 개수 제한 초과 시
	 */
	private void validateFileCount(MultipartHttpServletRequest multipartRequest) throws SecurityException {
		Map<String, List<MultipartFile>> fileMap = multipartRequest.getMultiFileMap();
		int totalFileCount = 0;

		// 실제 파일이 업로드된 개수만 계산 (빈 파일 제외)
		for (List<MultipartFile> files : fileMap.values()) {
			for (MultipartFile file : files) {
				if (file != null && !file.isEmpty()) {
					totalFileCount++;
				}
			}
		}

		int maxFileCount = getMaxFileCount();
		if (totalFileCount > maxFileCount) {
			LOGGER.warn("File count [{}] exceeds maximum allowed count [{}]", totalFileCount, maxFileCount);
			throw new SecurityException("File count exceeds maximum allowed count: " + totalFileCount + " > " + maxFileCount);
		}

		LOGGER.debug("File count validation passed: {} files (max: {})", totalFileCount, maxFileCount);
	}

	/**
	 * 최대 파일 개수를 반환한다.
	 *
	 * @return 최대 파일 개수
	 */
	private int getMaxFileCount() {
		String maxFileCountStr = EgovProperties.getProperty("Globals.fileUpload.maxFileCount");
		if (StringUtils.hasText(maxFileCountStr)) {
			try {
				return Integer.parseInt(maxFileCountStr);
			} catch (NumberFormatException e) {
				LOGGER.warn("Invalid maxFileCount configuration: {}, using default", maxFileCountStr);
			}
		}
		// 기본값: 10개 (Tomcat 9.0.106+ 기본값과 동일)
		return 10;
	}

	/**
	 * 최대 파일 크기를 반환한다.
	 *
	 * @return 최대 파일 크기 (바이트)
	 */
	private long getMaxFileSize() {
		String maxFileSizeStr = EgovProperties.getProperty("Globals.fileUpload.maxFileSize");
		if (StringUtils.hasText(maxFileSizeStr)) {
			try {
				return Long.parseLong(maxFileSizeStr);
			} catch (NumberFormatException e) {
				LOGGER.warn("Invalid maxFileSize configuration: {}, using default", maxFileSizeStr);
			}
		}
		// 기본값: 100MB (수정됨)
		return 100 * 1024 * 1024;
	}

	/**
	 * multipart 요청이 완료된 후 정리 작업을 수행한다.
	 *
	 * @param request HTTP 요청
	 */
	@Override
	public void cleanupMultipart(MultipartHttpServletRequest request) {
		// 2026.02.28 KISA 취약점 조치 상위 클래스에서 throwable로 처리
			super.cleanupMultipart(request);
	}

	/**
	 * 요청이 multipart 요청인지 확인한다.
	 *
	 * @param request HTTP 요청
	 * @return multipart 요청 여부
	 */
	@Override
	public boolean isMultipart(HttpServletRequest request) {
		boolean isMultipart = super.isMultipart(request);
		LOGGER.debug("Request isMultipart check result: {}", isMultipart);
		return isMultipart;
	}

}
