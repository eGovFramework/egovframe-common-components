package egovframework.com.cmm.crypto;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.Locale;

import org.egovframe.rte.fdl.crypto.config.EgovCryptoConfig;
import org.egovframe.rte.fdl.crypto.config.EgovCryptoConfigReader;
import org.junit.jupiter.api.Test;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.core.io.DefaultResourceLoader;

/**
 * 암호화 키 기동 가드 테스트 - 판정(기동은 막지 않음)과 다국어 로그 문구
 */
class EgovCryptoKeyGuardTest {

	private static final String MAIN_CONFIG = "egovframework/egovProps/conf/egov-crypto-config.properties";

	private static EgovCryptoConfig config(String key, String keyHash) {
		EgovCryptoConfig config = new EgovCryptoConfig();
		config.setAlgorithm("SHA-256");
		config.setAlgorithmKey(key);
		config.setAlgorithmKeyHash(keyHash);
		return config;
	}

	/** 애플리케이션과 같은 메시지 파일을 읽는 메시지 소스 */
	static ResourceBundleMessageSource messageSource() {
		ResourceBundleMessageSource source = new ResourceBundleMessageSource();
		source.setBasename(EgovCryptoKeyMessages.BASENAME);
		source.setFallbackToSystemLocale(false);
		return source;
	}

	private static EgovCryptoKeyGuard guard(EgovCryptoConfig config, String path) {
		EgovCryptoKeyGuard guard = new EgovCryptoKeyGuard(config, path);
		guard.setMessageSource(messageSource());
		guard.afterPropertiesSet();
		return guard;
	}

	@Test
	void 배포된_기본_키면_안전하지_않음으로_판정하고_기동은_막지_않는다() {
		// 저장소의 설정 파일은 개발자가 초기화했을 수 있으므로 배포 기본값을 직접 만든다
		EgovCryptoKeyGuard guard = guard(config(EgovCryptoKeyPolicy.DEFAULT_KEY, EgovCryptoKeyPolicy.DEFAULT_KEY_HASH), MAIN_CONFIG);

		assertFalse(guard.isSecure());
		assertEquals(Arrays.asList(EgovCryptoKeyGuard.PROBLEM_DEFAULT_KEY, EgovCryptoKeyGuard.PROBLEM_DEFAULT_KEY_HASH),
				guard.getProblems());
	}

	@Test
	void 로그_문구는_한국어와_영어로_나온다() {
		EgovCryptoKeyGuard guard = guard(config(EgovCryptoKeyPolicy.DEFAULT_KEY, EgovCryptoKeyPolicy.DEFAULT_KEY_HASH), MAIN_CONFIG);

		String ko = guard.describe(Locale.KOREAN);
		assertTrue(ko.contains("algorithmKey 가 배포 기본값"), ko);
		assertTrue(ko.contains("init-crypto-key.sh"), "조치 방법을 알려야 한다");
		assertTrue(ko.contains(MAIN_CONFIG), "읽은 설정 경로를 보여야 한다");

		String en = guard.describe(Locale.ENGLISH);
		assertTrue(en.contains("algorithmKey is the published default value"), en);
		assertTrue(en.contains("init-crypto-key.bat"), en);
		assertFalse(en.matches("(?s).*[\\uac00-\\ud7a3].*"), "영어 문구에 한글이 섞이지 않는다");
	}

	@Test
	void 설정_파일을_못_찾아_기본값으로_대체돼도_안전하지_않다() {
		String wrongPath = "classpath:egovframework/egovProps/conf/no-such-crypto-config.properties";
		EgovCryptoConfig config = new EgovCryptoConfigReader(wrongPath, new DefaultResourceLoader()).readConfig();

		EgovCryptoKeyGuard guard = guard(config, wrongPath);
		assertFalse(guard.isSecure());
		assertTrue(guard.describe(Locale.KOREAN).contains(wrongPath));
	}

	@Test
	void 키만_바꾸고_기본_해시를_둔_경우() {
		EgovCryptoKeyGuard guard = guard(config("MySite-Crypto-2026", EgovCryptoKeyPolicy.DEFAULT_KEY_HASH), "");
		assertEquals(Collections.singletonList(EgovCryptoKeyGuard.PROBLEM_DEFAULT_KEY_HASH), guard.getProblems());
		assertTrue(guard.describe(Locale.KOREAN).contains("Globals.CryptoConfigPath 미지정"), "경로가 없으면 기본 경로 안내");
	}

	@Test
	void 키와_해시가_맞지_않는_경우() {
		String otherHash = EgovCryptoKeyPolicy.hash("Another-Key-2026!", "SHA-256");
		EgovCryptoKeyGuard guard = guard(config("MySite-Crypto-2026", otherHash), "");
		assertEquals(Collections.singletonList(EgovCryptoKeyGuard.PROBLEM_MISMATCH), guard.getProblems());
	}

	@Test
	void 설정이_없는_경우() {
		EgovCryptoKeyGuard guard = guard(null, "");
		assertEquals(Collections.singletonList(EgovCryptoKeyGuard.PROBLEM_NO_CONFIG), guard.getProblems());
	}

	@Test
	void 초기화된_키는_안전하다() {
		String key = EgovCryptoKeyPolicy.generateKey();
		EgovCryptoKeyGuard guard = guard(config(key, EgovCryptoKeyPolicy.hash(key, "SHA-256")), MAIN_CONFIG);
		assertTrue(guard.isSecure());
		assertTrue(guard.getProblems().isEmpty());
	}

	@Test
	void 메시지_소스가_없어도_메시지_파일에서_문구를_꺼낸다() {
		EgovCryptoKeyGuard guard = new EgovCryptoKeyGuard(
				config(EgovCryptoKeyPolicy.DEFAULT_KEY, EgovCryptoKeyPolicy.DEFAULT_KEY_HASH), MAIN_CONFIG);
		guard.afterPropertiesSet();
		assertTrue(guard.describe(Locale.ENGLISH).contains("Crypto") || guard.describe(Locale.ENGLISH).contains("crypto"));
		assertFalse(guard.describe(Locale.KOREAN).contains("comCmm.cryptoKey."), "키 이름이 그대로 나오지 않는다");
	}
}
