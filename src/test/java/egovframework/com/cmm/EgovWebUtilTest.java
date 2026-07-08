package egovframework.com.cmm;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

@DisplayName("EgovWebUtil 테스트")
public class EgovWebUtilTest {

	@Nested
	@DisplayName("clearXSSMinimum() — XSS 방지를 위해 특수문자를 HTML 엔티티로 이스케이프한다")
	class ClearXSSMinimum {

		@ParameterizedTest(name = "[{index}] 입력값 \"{0}\" → 빈 문자열 반환")
		@NullAndEmptySource
		@ValueSource(strings = { "   " })
		@DisplayName("null / 빈 문자열 / 공백만 있는 문자열은 빈 문자열을 반환한다")
		void 빈_입력_처리(String input) {
			assertEquals("", EgovWebUtil.clearXSSMinimum(input));
		}

		@ParameterizedTest(name = "[{index}] 입력 ''{0}'' → 기대 ''{1}''")
		@CsvSource(
			quoteCharacter = '\'',
			value = {
				"&, &amp;",
				"<, &lt;",
				">, &gt;",
				"\", &#34;",
				"'''', &#39;",
				"., &#46;"
			}
		)
		@DisplayName("XSS 특수문자가 올바른 HTML 엔티티로 이스케이프된다")
		void 특수문자_이스케이프(String input, String expected) {
			assertEquals(expected, EgovWebUtil.clearXSSMinimum(input));
		}

		@Test
		@DisplayName("<script> 태그 전체가 이스케이프되어 < 와 > 가 남지 않는다")
		void 스크립트_태그_이스케이프() {
			String result = EgovWebUtil.clearXSSMinimum("<script>alert('xss')</script>");
			assertFalse(result.contains("<"), "< 문자가 남아있으면 안 됨");
			assertFalse(result.contains(">"), "> 문자가 남아있으면 안 됨");
		}

		@Test
		@DisplayName("XSS 특수문자가 없는 일반 텍스트는 그대로 반환된다")
		void 일반_텍스트_변경없음() {
			// 점(.)은 &#46;으로 이스케이프되므로 점 없는 텍스트 사용
			assertEquals("hello world", EgovWebUtil.clearXSSMinimum("hello world"));
		}
	}

	@Nested
	@DisplayName("clearXSSMaximum() — null 바이트 시퀀스를 제거한다")
	class ClearXSSMaximum {

		@Test
		@DisplayName("%00 시퀀스가 NPE 없이 제거된다")
		void removesNullByteSequence_withoutNpe() {
			// 변경 전: replaceAll("%00", null)이 "%00" 매칭 시 NullPointerException
			String result = EgovWebUtil.clearXSSMaximum("abc%00def");
			assertEquals("abcdef", result, "%00 시퀀스는 제거되어야 한다");
		}

		@Test
		@DisplayName("%00이 없는 일반 텍스트는 그대로 반환된다")
		void noNullByte_unchangedForPlainText() {
			assertEquals("abcdef", EgovWebUtil.clearXSSMaximum("abcdef"));
		}
	}

	@Nested
	@DisplayName("removeLDAPInjectionRisk() — LDAP 인젝션 위험 문자를 제거한다")
	class RemoveLDAPInjectionRisk {

		@Test
		@DisplayName("LDAP OR 연산자 '|'가 제거된다")
		void removesPipe() {
			// 변경 전: replaceAll("|", "")은 빈 교대 정규식이라 리터럴 '|'를 제거하지 못함
			String result = EgovWebUtil.removeLDAPInjectionRisk("admin|x");
			assertEquals("adminx", result, "LDAP OR 연산자 '|'는 제거되어야 한다");
			assertFalse(result.contains("|"), "결과에 '|'가 남아 있으면 안 된다");
		}

		@Test
		@DisplayName("일반 문자는 그대로 반환된다")
		void keepsNormalCharacters() {
			assertEquals("hong gildong", EgovWebUtil.removeLDAPInjectionRisk("hong gildong"));
		}

		@Test
		@DisplayName("null 또는 공백 입력은 빈 문자열을 반환한다")
		void nullOrBlankReturnsEmpty() {
			assertEquals("", EgovWebUtil.removeLDAPInjectionRisk(null));
			assertEquals("", EgovWebUtil.removeLDAPInjectionRisk("   "));
		}
	}
}
