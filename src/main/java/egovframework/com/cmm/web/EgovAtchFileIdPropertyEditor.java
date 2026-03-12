package egovframework.com.cmm.web;

import java.beans.PropertyEditorSupport;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

class EgovAtchFileIdPropertyEditor extends PropertyEditorSupport {

	private static final Logger LOGGER = LoggerFactory.getLogger(EgovAtchFileIdPropertyEditor.class);

	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		LOGGER.debug("===>>> setText : "+text);
		String decryptText = "";
		if (text != null && !"".equals(text) ) {
			// 2026.02.28 KISA 취약점 조치
				String encText = URLEncoder.encode(text, StandardCharsets.UTF_8); // Charset 객체 직접 사용 (Java 10+)
				decryptText = EgovFileMngController.decrypt(encText);
		}
		this.setValue(decryptText);

	}


	@Override
	public String getAsText() {
		LOGGER.debug("===>>> getText : "+getValue());
		return String.valueOf(getValue());

	}

}