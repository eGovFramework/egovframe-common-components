package egovframework.com.cmm.crypto;

import java.security.SecureRandom;
import java.util.Base64;
import java.util.Locale;

import org.egovframe.rte.fdl.crypto.EgovPasswordEncoder;

/**
 * 암호화 키(egov-crypto-config.properties 의 algorithmKey) 규칙
 *
 * <p>배포 기본 키 판별, 무작위 키 생성, 키 해시 계산, 직접 입력한 키의 검증을 한곳에 둔다.
 * 키 초기화 도구({@link EgovCryptoKeyInitializer})와 기동 가드({@link EgovCryptoKeyGuard})가 같은 규칙을 쓴다.</p>
 *
 * @author 개발팀
 * @since 2026.10.02
 * @version 1.0
 *
 *      <pre>
 *  == 개정이력(Modification Information) ==
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2026.10.02  개발팀         최초 생성
 *
 *      </pre>
 */
public final class EgovCryptoKeyPolicy {

	/** 배포 기본 키 - 공개 저장소에 노출된 값이라 운영에 쓰면 안 된다 */
	public static final String DEFAULT_KEY = "egovframe";

	/** 배포 기본 키의 해시(SHA-256 다이제스트 Base64) */
	public static final String DEFAULT_KEY_HASH = "gdyYs/IZqY86VcWhT8emCYfqY1ahw2vtLG+/FzNqtrQ=";

	/** 직접 입력한 키의 최소 길이 */
	public static final int MIN_KEY_LENGTH = 12;

	/** 자동 생성 키의 난수 바이트 수(Base64 URL-safe 43자) */
	private static final int GENERATED_KEY_BYTES = 32;

	/** 직접 입력한 키가 갖춰야 할 문자 종류 수(대문자·소문자·숫자·기호 중) */
	private static final int MIN_CHARACTER_CLASSES = 3;

	private static final SecureRandom RANDOM = new SecureRandom();

	private EgovCryptoKeyPolicy() {
	}

	/**
	 * 배포 기본 키인지 판별한다(대소문자 무시).
	 *
	 * @param key 키
	 * @return 기본 키이면 true
	 */
	public static boolean isDefaultKey(String key) {
		return key != null && DEFAULT_KEY.equals(key.trim().toLowerCase(Locale.ROOT));
	}

	/**
	 * 배포 기본 키의 해시인지 판별한다.
	 *
	 * @param keyHash 키 해시
	 * @return 기본 키의 해시이면 true
	 */
	public static boolean isDefaultKeyHash(String keyHash) {
		return keyHash != null && DEFAULT_KEY_HASH.equals(keyHash.trim());
	}

	/**
	 * 무작위 키를 만든다. {@code .properties} 이스케이프가 필요 없는 Base64 URL-safe 문자(패딩 없음, 43자)다.
	 *
	 * @return 새 키
	 */
	public static String generateKey() {
		byte[] bytes = new byte[GENERATED_KEY_BYTES];
		RANDOM.nextBytes(bytes);
		return Base64.getUrlEncoder().withoutPadding().encodeToString(bytes);
	}

	/**
	 * 키의 해시를 계산한다. 실행환경 암호화 서비스가 키를 검증할 때와 같은 방식(SHA-256 다이제스트 Base64)이다.
	 *
	 * @param key 키
	 * @param algorithm 다이제스트 알고리즘(설정 파일의 algorithm, 비어 있으면 SHA-256)
	 * @return 키 해시
	 */
	public static String hash(String key, String algorithm) {
		return encoder(algorithm, null).encryptPassword(key);
	}

	/**
	 * 키와 키 해시가 서로 맞는지 확인한다.
	 *
	 * @param key 키
	 * @param keyHash 키 해시
	 * @param algorithm 다이제스트 알고리즘(비어 있으면 SHA-256)
	 * @return 맞으면 true
	 */
	public static boolean matches(String key, String keyHash, String algorithm) {
		if (key == null || keyHash == null) {
			return false;
		}
		return encoder(algorithm, keyHash).checkPassword(key);
	}

	/**
	 * 실행환경 패스워드 인코더를 만든다.
	 *
	 * @param algorithm 다이제스트 알고리즘(비어 있으면 SHA-256)
	 * @param keyHash 검증 기준 해시(없으면 null)
	 * @return 인코더
	 */
	static EgovPasswordEncoder encoder(String algorithm, String keyHash) {
		EgovPasswordEncoder encoder = new EgovPasswordEncoder();
		if (algorithm != null && !algorithm.isBlank()) {
			encoder.setAlgorithm(algorithm.trim());
		}
		encoder.setHashedPassword(keyHash);
		return encoder;
	}

	/**
	 * 사람이 직접 입력한 키를 검증한다.
	 *
	 * @param key 입력한 키
	 * @return 문제가 없으면 null, 있으면 위반 내용(메시지 키·인자 - 문구는 message-cryptokey_{ko,en}.properties)
	 */
	public static Violation validateUserKey(String key) {
		if (key == null || key.isEmpty()) {
			return new Violation("initCryptoKey.rule.empty");
		}
		if (isDefaultKey(key)) {
			return new Violation("initCryptoKey.rule.default", DEFAULT_KEY);
		}
		if (key.length() < MIN_KEY_LENGTH) {
			return new Violation("initCryptoKey.rule.length", MIN_KEY_LENGTH, key.length());
		}
		for (int i = 0; i < key.length(); i++) {
			char c = key.charAt(i);
			if (c < 0x21 || c > 0x7E) {
				return new Violation("initCryptoKey.rule.chars");
			}
			if (c == '\\') {
				return new Violation("initCryptoKey.rule.backslash");
			}
		}
		if (characterClasses(key) < MIN_CHARACTER_CLASSES) {
			return new Violation("initCryptoKey.rule.classes", MIN_CHARACTER_CLASSES);
		}
		return null;
	}

	/** 직접 입력한 키의 문자 종류 최소 개수 */
	static int minCharacterClasses() {
		return MIN_CHARACTER_CLASSES;
	}

	/**
	 * 키 규칙 위반 - 메시지 키와 인자. 문구는 언어별 메시지 파일에서 꺼낸다.
	 */
	public static final class Violation {

		private final String code;

		private final Object[] args;

		Violation(String code, Object... args) {
			this.code = code;
			this.args = args;
		}

		/** @return 메시지 키 */
		public String getCode() {
			return code;
		}

		/** @return 메시지 인자 */
		public Object[] getArgs() {
			return args.clone();
		}

		@Override
		public String toString() {
			return code;
		}
	}

	private static int characterClasses(String key) {
		boolean upper = false;
		boolean lower = false;
		boolean digit = false;
		boolean symbol = false;
		for (int i = 0; i < key.length(); i++) {
			char c = key.charAt(i);
			if (c >= 'A' && c <= 'Z') {
				upper = true;
			} else if (c >= 'a' && c <= 'z') {
				lower = true;
			} else if (c >= '0' && c <= '9') {
				digit = true;
			} else {
				symbol = true;
			}
		}
		return (upper ? 1 : 0) + (lower ? 1 : 0) + (digit ? 1 : 0) + (symbol ? 1 : 0);
	}
}
