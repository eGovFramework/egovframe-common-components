package egovframework.com.cmm;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.Test;

/**
 * EgovWebUtil 보안 필터 메서드 검증.
 *
 * <p>정규식/replacement 인자 오류로 필터가 의도대로 동작하지 않던 두 결함에 대한
 * 회귀 방지 테스트. 순수 static 유틸이므로 Spring 컨텍스트·DB 없이 결정적으로 검증한다.</p>
 *
 * <ul>
 *   <li>clearXSSMaximum: {@code replaceAll("%00", null)}의 null replacement → "%00" 입력 시 NPE.</li>
 *   <li>removeLDAPInjectionRisk: {@code replaceAll("|", "")}의 빈 교대 정규식 → 리터럴 '|' 미제거.</li>
 * </ul>
 */
public class EgovWebUtilTest {

	@Test
	public void clearXSSMaximum_removesNullByteSequence_withoutNpe() {
		// 변경 전: replaceAll("%00", null)이 "%00" 매칭 시 NullPointerException
		String result = EgovWebUtil.clearXSSMaximum("abc%00def");

		assertEquals("abcdef", result, "%00 시퀀스는 제거되어야 한다");
	}

	@Test
	public void clearXSSMaximum_noNullByte_unchangedForPlainText() {
		String result = EgovWebUtil.clearXSSMaximum("abcdef");

		assertEquals("abcdef", result);
	}

	@Test
	public void removeLDAPInjectionRisk_removesPipe() {
		// 변경 전: replaceAll("|", "")은 빈 교대 정규식이라 리터럴 '|'를 제거하지 못함
		String result = EgovWebUtil.removeLDAPInjectionRisk("admin|x");

		assertEquals("adminx", result, "LDAP OR 연산자 '|'는 제거되어야 한다");
		assertFalse(result.contains("|"), "결과에 '|'가 남아 있으면 안 된다");
	}

	@Test
	public void removeLDAPInjectionRisk_keepsNormalCharacters() {
		String result = EgovWebUtil.removeLDAPInjectionRisk("hong gildong");

		assertEquals("hong gildong", result);
	}

	@Test
	public void removeLDAPInjectionRisk_nullOrBlankReturnsEmpty() {
		assertEquals("", EgovWebUtil.removeLDAPInjectionRisk(null));
		assertEquals("", EgovWebUtil.removeLDAPInjectionRisk("   "));
	}

	@Test
	public void fileInjectPathReplaceAll_removesParentDirectorySequence() {
		assertEquals("etcpasswd", EgovWebUtil.fileInjectPathReplaceAll("../etc/passwd"));
	}

	@Test
	public void fileInjectPathReplaceAll_keepsSingleDotAndFollowingCharacter() {
		assertEquals("report.txt", EgovWebUtil.fileInjectPathReplaceAll("report.txt"));
	}
}
