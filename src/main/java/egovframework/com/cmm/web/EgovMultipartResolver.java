package egovframework.com.cmm.web;

import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import egovframework.com.cmm.service.EgovProperties;
import egovframework.com.utl.fcc.service.EgovFileUploadUtil;

/**
 * 파일 업로드 Multipart Resolver (commons-fileupload 1.5 / Spring 5.3 CommonsMultipartResolver 기반)
 *
 * 4.3.x 공통컴포넌트와 동일하게 CommonsMultipartResolver 를 확장하며, 업로드 파일에 대해
 * 확장자 화이트리스트(Globals.fileUpload.Extensions), 개별 파일 크기(Globals.fileUpload.maxSize),
 * 파일 개수(Globals.fileUpload.maxFileCount) 를 검증한다.
 *
 * @author 공통서비스 개발팀
 * @since 2009.03.19
 * @version 1.0
 *
 *      <pre>
 *  == 개정이력(Modification Information) ==
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2009.03.19  공통서비스 개발팀    최초 생성
 *   2026.11.08  유지보수        실행환경 4.3.2 적용 - StandardServletMultipartResolver → CommonsMultipartResolver, 검증 로직 유지
 *
 *      </pre>
 */
public class EgovMultipartResolver extends CommonsMultipartResolver {

	private static final Logger LOGGER = LoggerFactory.getLogger(EgovMultipartResolver.class);

	public EgovMultipartResolver() {
		super();
	}

	public EgovMultipartResolver(ServletContext servletContext) {
		super(servletContext);
	}

	@Override
	public MultipartHttpServletRequest resolveMultipart(HttpServletRequest request) throws MultipartException {
		MultipartHttpServletRequest multipartRequest = super.resolveMultipart(request);
		try {
			// 파일 업로드 보안 검증 수행
			validateUploadedFiles(multipartRequest);
		} catch (SecurityException e) {
			// 검증 실패 시 임시 저장된 업로드 파일 정리
			cleanupMultipart(multipartRequest);
			throw e;
		}
		return multipartRequest;
	}

	private void validateUploadedFiles(MultipartHttpServletRequest multipartRequest) throws SecurityException {
		Map<String, List<MultipartFile>> fileMap = multipartRequest.getMultiFileMap();
		String whiteListFileUploadExtensions = EgovProperties.getProperty("Globals.fileUpload.Extensions");
		long maxFileSize = getMaxFileSize();

		// 파일 개수 제한 검증
		validateFileCount(fileMap);

		LOGGER.debug("File upload validation - Whitelist extensions: {}", whiteListFileUploadExtensions);

		for (Map.Entry<String, List<MultipartFile>> entry : fileMap.entrySet()) {
			String fieldName = entry.getKey();
			List<MultipartFile> files = entry.getValue();

			for (MultipartFile file : files) {
				if (file != null && !file.isEmpty()) {
					validateFile(file, whiteListFileUploadExtensions, maxFileSize);
					LOGGER.debug("File validation passed for field [{}]: {} ({} bytes)",
						fieldName, file.getOriginalFilename(), file.getSize());
				}
			}
		}
	}

	private void validateFile(MultipartFile file, String whiteListFileUploadExtensions, long maxFileSize) throws SecurityException {
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

		// 파일 크기 검증
		if (file.getSize() > maxFileSize) {
			LOGGER.warn("File size [{}] exceeds maximum allowed size [{}] for file: {}",
				file.getSize(), maxFileSize, fileName);
			throw new SecurityException("File size exceeds maximum allowed size.");
		}
	}

	private void validateFileCount(Map<String, List<MultipartFile>> fileMap) throws SecurityException {
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

	private int getMaxFileCount() {
		String maxFileCountStr = EgovProperties.getProperty("Globals.fileUpload.maxFileCount");
		if (StringUtils.hasText(maxFileCountStr)) {
			try {
				return Integer.parseInt(maxFileCountStr.trim());
			} catch (NumberFormatException e) {
				LOGGER.warn("Invalid maxFileCount configuration: {}, using default", maxFileCountStr);
			}
		}
		// 기본값: 10개
		return 10;
	}

	private long getMaxFileSize() {
		String maxFileSizeStr = EgovProperties.getProperty("Globals.fileUpload.maxSize");
		if (StringUtils.hasText(maxFileSizeStr)) {
			try {
				return Long.parseLong(maxFileSizeStr.trim());
			} catch (NumberFormatException e) {
				LOGGER.warn("Invalid maxFileSize configuration: {}, using default", maxFileSizeStr);
			}
		}
		// 기본값: 100MB
		return 100L * 1024 * 1024;
	}
}
