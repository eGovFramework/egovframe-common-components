package egovframework.com.sym.ccm.cde.web;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.lang.reflect.Field;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.jupiter.api.Test;

import egovframework.com.cmm.service.CmmnDetailCode;
import jakarta.validation.constraints.Size;

/**
 * 공통상세코드 화면의 클라이언트측 입력 제한이 CmmnDetailCode의 @Size 제약을 넘지 않는지 확인한다.
 * <p>
 * 화면이 바인딩하는 cmmnDetailCodeVO(CmmnDetailCodeVO)는 CmmnDetailCode를 상속하므로 제약의 출처는 CmmnDetailCode다.
 * JSP의 maxlength 속성과 EgovValidation.js의 검증 규칙 두 곳 모두 확인한다.
 * </p>
 */
class EgovCcmCmmnDetailCodeJspMaxlengthTest {

	private static final String BASE = "src/main/webapp/WEB-INF/jsp/egovframework/com/sym/ccm/cde/";

	private static final String VALIDATION_JS = "src/main/webapp/js/egovframework/com/cmm/EgovValidation.js";

	private static final String[] FIELDS = { "code", "codeNm" };

	private static final Pattern HTML_COMMENT = Pattern.compile("<!--.*?-->", Pattern.DOTALL);

	@Test
	void cmmnDetailCodeRegistMaxlengthWithinSize() throws Exception {
		assertMaxlengthWithinSize("EgovCcmCmmnDetailCodeRegist.jsp");
	}

	@Test
	void cmmnDetailCodeUpdtMaxlengthWithinSize() throws Exception {
		assertMaxlengthWithinSize("EgovCcmCmmnDetailCodeUpdt.jsp");
	}

	@Test
	void validationJsRuleWithinSize() throws Exception {
		String source = new String(Files.readAllBytes(Paths.get(VALIDATION_JS)), StandardCharsets.UTF_8);
		String block = validateCmmnDetailCodeVoBlock(source);

		for (String field : FIELDS) {
			int sizeMax = sizeMaxOf(field);
			Matcher m = Pattern.compile("\\b" + field + "\\s*:\\s*\\{.*?maxlength\\s*:\\s*(\\d+)", Pattern.DOTALL)
					.matcher(block);
			assertTrue(m.find(), "validateCmmnDetailCodeVO 에서 " + field + " 의 maxlength 규칙을 찾지 못했습니다.");
			int jsMax = Integer.parseInt(m.group(1));
			assertTrue(jsMax <= sizeMax, "EgovValidation.js 의 " + field + " maxlength=" + jsMax
					+ " 가 CmmnDetailCode 의 @Size(max=" + sizeMax + ") 를 넘습니다.");
		}
	}

	private void assertMaxlengthWithinSize(String jsp) throws Exception {
		String source = stripComments(new String(Files.readAllBytes(Paths.get(BASE + jsp)), StandardCharsets.UTF_8));

		for (String field : FIELDS) {
			int sizeMax = sizeMaxOf(field);
			Matcher m = Pattern.compile("path=\"" + field + "\"[^>]*maxlength=\"(\\d+)\"").matcher(source);
			assertTrue(m.find(), jsp + " 에서 " + field + " 의 maxlength 를 찾지 못했습니다.");
			int jspMax = Integer.parseInt(m.group(1));
			assertTrue(jspMax <= sizeMax, jsp + " 의 " + field + " maxlength=" + jspMax
					+ " 가 CmmnDetailCode 의 @Size(max=" + sizeMax + ") 를 넘습니다.");
		}
	}

	/** validateCmmnDetailCodeVO 함수 본문만 잘라낸다. 같은 필드명이 다른 함수에도 있으므로 범위를 좁힌다. */
	private String validateCmmnDetailCodeVoBlock(String source) {
		int start = source.indexOf("function validateCmmnDetailCodeVO");
		assertTrue(start >= 0, "EgovValidation.js 에서 validateCmmnDetailCodeVO 를 찾지 못했습니다.");
		int end = source.indexOf("\nfunction ", start + 1);
		return end < 0 ? source.substring(start) : source.substring(start, end);
	}

	private int sizeMaxOf(String field) throws NoSuchFieldException {
		Field f = CmmnDetailCode.class.getDeclaredField(field);
		return f.getAnnotation(Size.class).max();
	}

	private String stripComments(String source) {
		return HTML_COMMENT.matcher(source).replaceAll("");
	}
}
