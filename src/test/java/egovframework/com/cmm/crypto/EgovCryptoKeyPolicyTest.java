package egovframework.com.cmm.crypto;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

/**
 * 암호화 키 규칙 테스트
 */
class EgovCryptoKeyPolicyTest {

	@Test
	void 기본_키와_해시를_판별한다() {
		assertTrue(EgovCryptoKeyPolicy.isDefaultKey("egovframe"));
		assertTrue(EgovCryptoKeyPolicy.isDefaultKey(" EgovFrame "), "대소문자·앞뒤 공백 변형도 기본 키다");
		assertFalse(EgovCryptoKeyPolicy.isDefaultKey("egovframe2026!Key"));
		assertFalse(EgovCryptoKeyPolicy.isDefaultKey(null));
		assertTrue(EgovCryptoKeyPolicy.isDefaultKeyHash(EgovCryptoKeyPolicy.DEFAULT_KEY_HASH));
	}

	@Test
	void 해시는_실행환경_검증과_같은_방식이다() {
		assertEquals(EgovCryptoKeyPolicy.DEFAULT_KEY_HASH, EgovCryptoKeyPolicy.hash("egovframe", "SHA-256"),
				"배포된 기본 해시가 기본 키에서 그대로 계산돼야 한다");
		String key = EgovCryptoKeyPolicy.generateKey();
		String hash = EgovCryptoKeyPolicy.hash(key, null);
		assertTrue(EgovCryptoKeyPolicy.matches(key, hash, "SHA-256"));
		assertFalse(EgovCryptoKeyPolicy.matches(key, EgovCryptoKeyPolicy.DEFAULT_KEY_HASH, "SHA-256"));
		assertFalse(EgovCryptoKeyPolicy.matches(null, hash, "SHA-256"));
	}

	@Test
	void 자동_생성_키는_43자_URL_safe_이고_매번_다르다() {
		Set<String> keys = new HashSet<>();
		for (int i = 0; i < 200; i++) {
			String key = EgovCryptoKeyPolicy.generateKey();
			assertEquals(43, key.length());
			assertTrue(key.matches("[A-Za-z0-9_-]+"), key);
			keys.add(key);
		}
		assertEquals(200, keys.size());
	}

	@Test
	void 직접_입력한_키의_규칙() {
		assertNull(EgovCryptoKeyPolicy.validateUserKey("MySite-Crypto-2026"));
		assertNotNull(EgovCryptoKeyPolicy.validateUserKey(""), "빈 키");
		assertNotNull(EgovCryptoKeyPolicy.validateUserKey("EGOVFRAME"), "기본 키 변형");
		assertNotNull(EgovCryptoKeyPolicy.validateUserKey("Egov-Key-26"), "11자 - 12자 미만");
		assertNull(EgovCryptoKeyPolicy.validateUserKey("Egov-Key-26!"), "12자는 통과");
		assertNotNull(EgovCryptoKeyPolicy.validateUserKey("My Site Crypto 2026"), "공백");
		assertNotNull(EgovCryptoKeyPolicy.validateUserKey("암호화키-Crypto-2026"), "한글");
		assertNotNull(EgovCryptoKeyPolicy.validateUserKey("MySite\\Crypto2026"), "역슬래시");
		assertNotNull(EgovCryptoKeyPolicy.validateUserKey("mysitecryptokeyabc"), "문자 종류 1가지");
		assertNull(EgovCryptoKeyPolicy.validateUserKey("mysitecryptokey2026!"), "소문자·숫자·기호 3가지");
	}
}
