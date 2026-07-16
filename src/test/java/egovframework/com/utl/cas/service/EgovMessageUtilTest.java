package egovframework.com.utl.cas.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

class EgovMessageUtilTest {

	@Test
	void getErrorMsg_knownKey_returnsConfiguredMessage() {
		assertEquals("Hello, Test~!", EgovMessageUtil.getErrorMsg("test.message"));
	}

	@Test
	void getInfoMsg_knownKey_returnsConfiguredMessage() {
		assertEquals("Hello, Test~!", EgovMessageUtil.getInfoMsg("test.message"));
	}

	@Test
	void getWarnMsg_knownKey_returnsConfiguredMessage() {
		assertEquals("Hello, Test~!", EgovMessageUtil.getWarnMsg("test.message"));
	}

	@Test
	void getConfirmMsg_knownKey_returnsConfiguredMessage() {
		assertEquals("Hello, Test~!", EgovMessageUtil.getConfirmMsg("test.message"));
	}

	@Test
	void getErrorMsg_withParam_substitutesPlaceholdersInOrder() {
		assertEquals("A : B", EgovMessageUtil.getErrorMsg("param.message", new String[] { "A", "B" }));
	}

	@Test
	void getInfoMsg_withParam_substitutesPlaceholdersInOrder() {
		assertEquals("A : B", EgovMessageUtil.getInfoMsg("param.message", new String[] { "A", "B" }));
	}

	@Test
	void getWarnMsg_withParam_substitutesPlaceholdersInOrder() {
		assertEquals("A : B", EgovMessageUtil.getWarnMsg("param.message", new String[] { "A", "B" }));
	}

	@Test
	void getConfirmMsg_withParam_substitutesPlaceholdersInOrder() {
		assertEquals("A : B", EgovMessageUtil.getConfirmMsg("param.message", new String[] { "A", "B" }));
	}

	@Test
	void getErrorMsg_withParam_noPlaceholderLeavesMessageUnchanged() {
		// test.message에는 {0} 같은 자리표시자가 없으므로 파라미터 배열은 결과에 영향을 주지 않는다
		assertEquals("Hello, Test~!", EgovMessageUtil.getErrorMsg("test.message", new String[] { "ignored" }));
	}

	@Test
	void getErrorMsg_unknownKey_returnsEmptyString() {
		assertEquals("", EgovMessageUtil.getErrorMsg("no.such.key.defined"));
	}

	@Test
	void getErrorMsg_emptyCode_returnsEmptyStringWithoutLookup() {
		assertEquals("", EgovMessageUtil.getErrorMsg(""));
	}

	@Test
	void getErrorMsg_blankCode_returnsEmptyStringWithoutLookup() {
		assertEquals("", EgovMessageUtil.getErrorMsg("   "));
	}

	@Test
	void getErrorMsg_nullCode_throwsNullPointerException() {
		// strCode.trim() 호출 시점에 NPE가 발생하는 현재 계약을 회귀 방지 목적으로 고정한다
		assertThrows(NullPointerException.class, () -> EgovMessageUtil.getErrorMsg(null));
	}
}
