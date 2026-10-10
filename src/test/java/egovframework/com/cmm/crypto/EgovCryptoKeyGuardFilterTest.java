package egovframework.com.cmm.crypto;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Locale;

import org.egovframe.rte.fdl.crypto.config.EgovCryptoConfig;
import org.junit.jupiter.api.Test;
import org.springframework.context.MessageSource;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.mock.web.MockFilterChain;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

/**
 * 암호화 키 안내 페이지 필터 테스트
 */
class EgovCryptoKeyGuardFilterTest {

	private static final String CONFIG_PATH = "egovframework/egovProps/conf/egov-crypto-config.properties";

	/** 가드 빈과 메시지 소스를 가진 컨텍스트(애플리케이션 루트 컨텍스트 대용) */
	private static GenericApplicationContext context(String key, String keyHash, boolean withGuard) {
		GenericApplicationContext context = new GenericApplicationContext();
		context.registerBean("messageSource", MessageSource.class, EgovCryptoKeyGuardTest::messageSource);
		if (withGuard) {
			EgovCryptoConfig config = new EgovCryptoConfig();
			config.setAlgorithm("SHA-256");
			config.setAlgorithmKey(key);
			config.setAlgorithmKeyHash(keyHash);
			context.registerBean(EgovCryptoKeyGuardFilter.GUARD_BEAN, EgovCryptoKeyGuard.class,
					() -> new EgovCryptoKeyGuard(config, CONFIG_PATH));
		}
		context.refresh();
		return context;
	}

	private static GenericApplicationContext defaultKeyContext() {
		return context(EgovCryptoKeyPolicy.DEFAULT_KEY, EgovCryptoKeyPolicy.DEFAULT_KEY_HASH, true);
	}

	private static MockHttpServletResponse request(GenericApplicationContext context, String method, String uri,
			Locale locale, MockFilterChain chain) throws Exception {
		MockHttpServletRequest request = new MockHttpServletRequest(method, uri);
		request.addPreferredLocale(locale);
		MockHttpServletResponse response = new MockHttpServletResponse();
		new EgovCryptoKeyGuardFilter(context).doFilter(request, response, chain);
		return response;
	}

	@Test
	void 기본_키면_어떤_주소든_안내_페이지를_503_으로_응답한다() throws Exception {
		try (GenericApplicationContext context = defaultKeyContext()) {
			for (String uri : new String[] { "/", "/uat/uia/egovLoginUsr.do", "/EgovContent.do", "/css/egovframework/com/com.css" }) {
				MockFilterChain chain = new MockFilterChain();
				MockHttpServletResponse response = request(context, "GET", uri, Locale.KOREAN, chain);

				assertEquals(503, response.getStatus(), uri);
				assertNull(chain.getRequest(), "다음 필터·화면으로 넘기지 않는다: " + uri);
				assertTrue(response.getContentType().startsWith("text/html"), uri);
				assertEquals("no-store", response.getHeader("Cache-Control"));
			}
		}
	}

	@Test
	void 안내_페이지는_한국어로_이유와_조치_방법을_보여_준다() throws Exception {
		try (GenericApplicationContext context = defaultKeyContext()) {
			String html = request(context, "GET", "/", Locale.KOREAN, new MockFilterChain()).getContentAsString();

			assertTrue(html.contains("<html lang=\"ko\">"), html);
			assertTrue(html.contains("암호화 키 초기화가 필요합니다"), "제목");
			assertTrue(html.contains("공개 저장소에 노출된 기본 암호화 키"), "보안상 이유");
			assertTrue(html.contains("algorithmKey 가 배포 기본값"), "확인된 문제");
			assertTrue(html.contains("init-crypto-key.bat") && html.contains("./init-crypto-key.sh"), "실행 방법");
			assertTrue(html.contains(CONFIG_PATH), "설정 파일 경로");
		}
	}

	@Test
	void 브라우저_언어가_영어면_영어로_보여_준다() throws Exception {
		try (GenericApplicationContext context = defaultKeyContext()) {
			String html = request(context, "GET", "/", Locale.US, new MockFilterChain()).getContentAsString();

			assertTrue(html.contains("<html lang=\"en\">"), html);
			assertTrue(html.contains("Crypto key initialization required"), html);
			assertTrue(html.contains("Run the key initialization tool"), html);
			assertFalse(html.matches("(?s).*[\\uac00-\\ud7a3].*"), "영어 페이지에 한글이 섞이지 않는다");
		}
	}

	@Test
	void HEAD_요청은_본문_없이_503() throws Exception {
		try (GenericApplicationContext context = defaultKeyContext()) {
			MockHttpServletResponse response = request(context, "HEAD", "/", Locale.KOREAN, new MockFilterChain());
			assertEquals(503, response.getStatus());
			assertEquals("", response.getContentAsString());
		}
	}

	@Test
	void 초기화된_키면_그대로_통과시킨다() throws Exception {
		String key = EgovCryptoKeyPolicy.generateKey();
		try (GenericApplicationContext context = context(key, EgovCryptoKeyPolicy.hash(key, "SHA-256"), true)) {
			MockFilterChain chain = new MockFilterChain();
			MockHttpServletResponse response = request(context, "GET", "/EgovContent.do", Locale.KOREAN, chain);
			assertNotNull(chain.getRequest(), "다음 필터로 넘긴다");
			assertEquals(200, response.getStatus());
		}
	}

	@Test
	void 가드_빈이_없으면_통과시킨다() throws Exception {
		try (GenericApplicationContext context = context(null, null, false)) {
			MockFilterChain chain = new MockFilterChain();
			request(context, "GET", "/", Locale.KOREAN, chain);
			assertNotNull(chain.getRequest());
		}
	}

	@Test
	void 메시지는_HTML_이스케이프한다() {
		assertEquals("&lt;a href=&quot;x&quot;&gt;&amp;&#39;", EgovCryptoKeyGuardFilter.escape("<a href=\"x\">&'"));
	}
}
