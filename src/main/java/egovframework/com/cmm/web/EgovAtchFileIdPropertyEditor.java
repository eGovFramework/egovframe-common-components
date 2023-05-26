package egovframework.com.cmm.web;

import java.beans.PropertyEditorSupport;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

class EgovAtchFileIdPropertyEditor extends PropertyEditorSupport {

	private static final Logger LOGGER = LoggerFactory.getLogger(EgovAtchFileIdPropertyEditor.class);
	
	public void setAsText(String text) throws IllegalArgumentException {
		LOGGER.debug("===>>> setText : "+text);
		String decryptText = "";
		if (text != null && !"".equals(text) ) {
			try {
				String encText = URLEncoder.encode(text, StandardCharsets.UTF_8.name());
				decryptText = EgovFileMngController.decrypt(encText);
			} catch (Exception e) {
				LOGGER.debug(e.getMessage());
				decryptText = "FILE_ID_DECRIPT_EXCEPTION_01";
			}
		}
		this.setValue(decryptText);

	}


	public String getAsText() {
		LOGGER.debug("===>>> getText : "+getValue());
		return String.valueOf(getValue());

	}		

}