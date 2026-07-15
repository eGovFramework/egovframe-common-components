package egovframework.com.utl.wed.web;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

/**
 * CKEditor 이미지 업로드 URL({@code ck.image.url})을 뷰 모델에 노출한다.
 */
@Slf4j
@ControllerAdvice
public class EgovCkEditorControllerAdvice {

	private static final String CK_PROPERTIES = "egovframework/egovProps/ck.properties";
	private static final String CK_IMAGE_URL_KEY = "ck.image.url";
	private static final String DEFAULT_CK_IMAGE_URL = "/ckUploadImage";

	private final String ckImageUrlPath;

	public EgovCkEditorControllerAdvice() {
		this.ckImageUrlPath = loadCkImageUrlPath();
	}

	/**
	 * JSP에서 {@code ${ckImageUploadUrl}} 로 사용한다. (컨텍스트 경로 포함)
	 */
	@ModelAttribute("ckImageUploadUrl")
	public String ckImageUploadUrl(HttpServletRequest request) {
		if (ckImageUrlPath.startsWith("http://") || ckImageUrlPath.startsWith("https://")) {
			return ckImageUrlPath;
		}
		return request.getContextPath() + ckImageUrlPath;
	}

	private static String loadCkImageUrlPath() {
		Properties props = new Properties();
		try (InputStream inStream = Thread.currentThread().getContextClassLoader().getResourceAsStream(CK_PROPERTIES)) {
			if (inStream != null) {
				props.load(inStream);
			}
		} catch (IOException e) {
			log.error("Failed to load {}", CK_PROPERTIES, e);
		}

		String url = StringUtils.trimToEmpty(props.getProperty(CK_IMAGE_URL_KEY));
		if (StringUtils.isBlank(url)) {
			return DEFAULT_CK_IMAGE_URL;
		}
		if (url.startsWith("http://") || url.startsWith("https://")) {
			return url;
		}
		if (!url.startsWith("/")) {
			url = "/" + url;
		}
		return url;
	}

	/**
	 * CkFilter URL 패턴 등록용 (컨텍스트 경로 제외).
	 */
	public static String resolveCkImageUrlPattern() {
		String url = loadCkImageUrlPath();
		if (url.startsWith("http://") || url.startsWith("https://")) {
			try {
				java.net.URI uri = java.net.URI.create(url);
				String path = uri.getPath();
				return StringUtils.isNotBlank(path) ? path : DEFAULT_CK_IMAGE_URL;
			} catch (IllegalArgumentException e) {
				return DEFAULT_CK_IMAGE_URL;
			}
		}
		return url;
	}
}
