package egovframework.com.cmm;

import java.lang.reflect.Method;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class EgovComCrossSiteHndlrTest {

	private static final String DIFF_CHAR = "()[]{}\"',:;= \t\r\n%!+-";
	private static final String[] DIFF_CHAR_REPRESENTATION = { "&#40;", "&#41;", "&#91;", "&#93;", "&#123;",
			"&#125;", "&#34;", "&#39;", "&#44;", "&#58;", "&#59;", "&#61;", " ", "\t", "\r", "\n", "&#37;",
			"&#33;", "&#43;", "&#45;" };
	private static final int HIGHEST_SPECIAL = '>';
	private static final char[][] SPECIAL_CHARACTERS_REPRESENTATION = new char[HIGHEST_SPECIAL + 1][];
	static {
		SPECIAL_CHARACTERS_REPRESENTATION['&'] = "&amp;".toCharArray();
		SPECIAL_CHARACTERS_REPRESENTATION['<'] = "&lt;".toCharArray();
		SPECIAL_CHARACTERS_REPRESENTATION['>'] = "&gt;".toCharArray();
		SPECIAL_CHARACTERS_REPRESENTATION['"'] = "&#034;".toCharArray();
		SPECIAL_CHARACTERS_REPRESENTATION['\''] = "&#039;".toCharArray();
	}

	static Stream<String> goldenInputs() {
		Stream<String> individualDiffChars = DIFF_CHAR.chars().mapToObj(c -> String.valueOf((char) c));
		return Stream.concat(individualDiffChars,
				Stream.of("", "&", "<", ">", "\"", "'", "평문 Korean English 123", longMixedString()));
	}

	@ParameterizedTest
	@MethodSource("goldenInputs")
	void producesOutputIdenticalToOriginalAlgorithm(String input) throws Exception {
		assertEquals(referenceEscape(input), productionEscape(input));
	}

	@Test
	void preservesDiffCharPriorityOverSpecialCharacterRepresentations() throws Exception {
		assertEquals("&#34;", productionEscape("\""));
		assertEquals("&#39;", productionEscape("'"));
		assertEquals("&amp;", productionEscape("&"));
		assertEquals("&lt;", productionEscape("<"));
		assertEquals("&gt;", productionEscape(">"));
		assertNotEquals("&#034;", productionEscape("\""));
		assertNotEquals("&#039;", productionEscape("'"));
	}

	@Test
	void escapesTheNonNullValuePathUsedByDoStartTag() throws Exception {
		String input = "a\"b<c";
		assertEquals(referenceEscape(input), productionEscape(input));
	}

	private static String productionEscape(String input) throws Exception {
		EgovComCrossSiteHndlr handler = new EgovComCrossSiteHndlr();
		handler.setValue(input);
		Method method = EgovComCrossSiteHndlr.class.getDeclaredMethod("getWriteEscapedXml");
		method.setAccessible(true);
		return (String) method.invoke(handler);
	}

	private static String referenceEscape(String input) {
		boolean booleanDiff = false;
		String sRtn = "";
		String text = input;
		int start = 0;
		int length = text.length();
		char[] buffer = text.toCharArray();
		char[] cDiffChar = DIFF_CHAR.toCharArray();

		for (int i = 0; i < length; i++) {
			char c = buffer[i];
			booleanDiff = false;
			for (int k = 0; k < cDiffChar.length; k++) {
				if (c == cDiffChar[k]) {
					sRtn = sRtn + DIFF_CHAR_REPRESENTATION[k];
					booleanDiff = true;
					continue;
				}
			}

			if (booleanDiff) {
				continue;
			}

			if (c <= HIGHEST_SPECIAL) {
				char[] escaped = SPECIAL_CHARACTERS_REPRESENTATION[c];
				if (escaped != null) {
					for (int j = 0; j < escaped.length; j++) {
						sRtn = sRtn + escaped[j];
					}
					start = i + 1;
				} else {
					sRtn = sRtn + c;
				}
			} else {
				sRtn = sRtn + c;
			}
		}

		return sRtn;
	}

	private static String longMixedString() {
		String unit = "plain평문()[]{}\"',:;= \t\r\n%!+-&<>끝";
		StringBuilder input = new StringBuilder(unit.length() * 200);
		for (int i = 0; i < 200; i++) {
			input.append(unit);
		}
		return input.toString();
	}
}
