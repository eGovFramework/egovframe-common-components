package egovframework.com.cmm.util;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

/**
 * EgovDoubleSubmitHelper UUID 생성 메서드 테스트
 */
public class EgovDoubleSubmitHelperTest {

	@Test
	void testGetNewUuid_notNull() {
		String uuid = EgovDoubleSubmitHelper.getNewUUID();
		assertNotNull(uuid);
	}

	@Test
	void testGetNewUuid_upperCase() {
		String uuid = EgovDoubleSubmitHelper.getNewUUID();
		assertTrue(uuid.equals(uuid.toUpperCase()), "UUID should be upper case");
	}

	@Test
	void testGetNewUuid_uuidFormat() {
		String uuid = EgovDoubleSubmitHelper.getNewUUID();
		assertTrue(uuid.matches("[0-9A-F]{8}-[0-9A-F]{4}-[0-9A-F]{4}-[0-9A-F]{4}-[0-9A-F]{12}"),
				"UUID format should match standard pattern");
	}

	@Test
	void testGetNewUuid_uniqueness() {
		String uuid1 = EgovDoubleSubmitHelper.getNewUUID();
		String uuid2 = EgovDoubleSubmitHelper.getNewUUID();
		assertNotEquals(uuid1, uuid2, "Each call should return a different UUID");
	}

	@Test
	void testSessionTokenKey_notEmpty() {
		assertNotNull(EgovDoubleSubmitHelper.SESSION_TOKEN_KEY);
		assertTrue(EgovDoubleSubmitHelper.SESSION_TOKEN_KEY.length() > 0);
	}

	@Test
	void testParameterName_notEmpty() {
		assertNotNull(EgovDoubleSubmitHelper.PARAMETER_NAME);
		assertTrue(EgovDoubleSubmitHelper.PARAMETER_NAME.length() > 0);
	}

	@Test
	void testDefaultTokenKey_value() {
		assertNotNull(EgovDoubleSubmitHelper.DEFAULT_TOKEN_KEY);
		assertTrue(EgovDoubleSubmitHelper.DEFAULT_TOKEN_KEY.equals("DEFAULT"));
	}
}
