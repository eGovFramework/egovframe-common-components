package egovframework.com.cmm.web;

import static org.junit.Assert.assertEquals;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

import org.egovframe.rte.fdl.cmmn.exception.BaseRuntimeException;
import org.junit.Test;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * EgovAtchFileIdPropertyEditor 테스트
 * 
 * @author 이백행
 * @since 2023-05-09
 *
 */
@RequiredArgsConstructor
@Slf4j
public class EgovAtchFileIdPropertyEditorTest {

	/**
	 * EgovAtchFileIdPropertyEditor 테스트
	 */
	@Test
	public void test() {
		final String text = "test 이백행 2023-05-09";

		final String enc = StandardCharsets.UTF_8.name();

		if (log.isDebugEnabled()) {
			log.debug("enc={}", enc);
		}

		String encText;
		try {
			encText = URLEncoder.encode(text, enc);
		} catch (UnsupportedEncodingException e) {
			throw new BaseRuntimeException("UnsupportedEncodingException encode", e);
		}

		if (log.isDebugEnabled()) {
			log.debug("encText={}", encText);
		}

		assertEquals("", "test+%EC%9D%B4%EB%B0%B1%ED%96%89+2023-05-09", encText);
	}

}
