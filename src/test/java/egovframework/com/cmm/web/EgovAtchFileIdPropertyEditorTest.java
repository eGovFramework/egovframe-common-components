package egovframework.com.cmm.web;

import static org.junit.Assert.assertEquals;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EgovAtchFileIdPropertyEditorTest {

	protected Logger egovLogger = LoggerFactory.getLogger(EgovAtchFileIdPropertyEditorTest.class);

	@Test
	public void test() {
		String text = "test 이백행 2023-05-09";

		String enc = StandardCharsets.UTF_8.name();

		egovLogger.debug("enc={}", enc);

		String encText = null;
		try {
			encText = URLEncoder.encode(text, enc);
		} catch (UnsupportedEncodingException e) {
			egovLogger.error("UnsupportedEncodingException");
		}

		egovLogger.debug("encText={}", encText);

		assertEquals("test+%EC%9D%B4%EB%B0%B1%ED%96%89+2023-05-09", encText);
	}

}
