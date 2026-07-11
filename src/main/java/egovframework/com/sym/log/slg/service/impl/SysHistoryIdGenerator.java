package egovframework.com.sym.log.slg.service.impl;

import java.security.SecureRandom;
import java.util.Base64;

/**
 * 시스템 이력 식별자를 생성한다.
 */
final class SysHistoryIdGenerator {

	private static final String PREFIX = "HT_";
	private static final int RANDOM_BYTE_LENGTH = 13;
	private static final int ENCODED_RANDOM_LENGTH = 17;
	private static final Base64.Encoder URL_ENCODER = Base64.getUrlEncoder().withoutPadding();

	private final SecureRandom secureRandom = new SecureRandom();

	String getNextId() {
		byte[] randomBytes = new byte[RANDOM_BYTE_LENGTH];
		secureRandom.nextBytes(randomBytes);

		// 17 Base64 URL-safe characters retain 102 random bits from the 13-byte value.
		String encodedRandom = URL_ENCODER.encodeToString(randomBytes);
		return PREFIX + encodedRandom.substring(0, ENCODED_RANDOM_LENGTH);
	}
}
