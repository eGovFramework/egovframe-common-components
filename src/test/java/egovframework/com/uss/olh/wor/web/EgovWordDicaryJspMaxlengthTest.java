package egovframework.com.uss.olh.wor.web;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.lang.reflect.Field;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.jupiter.api.Test;

import egovframework.com.uss.olh.wor.service.WordDicaryVO;
import jakarta.validation.constraints.Size;

/**
 * 용어사전 등록·수정 화면의 영문명 JSP maxlength가 WordDicaryVO의 @Size 제약을 넘지 않는지 확인한다.
 */
class EgovWordDicaryJspMaxlengthTest {

	private static final String BASE = "src/main/webapp/WEB-INF/jsp/egovframework/com/uss/olh/wor/";

	private static final Pattern HTML_COMMENT = Pattern.compile("<!--.*?-->", Pattern.DOTALL);

	@Test
	void wordDicaryRegistMaxlengthWithinSize() throws Exception {
		assertMaxlengthWithinSize("EgovWordDicaryRegist.jsp");
	}

	@Test
	void wordDicaryUpdtMaxlengthWithinSize() throws Exception {
		assertMaxlengthWithinSize("EgovWordDicaryUpdt.jsp");
	}

	private void assertMaxlengthWithinSize(String jsp) throws Exception {
		String source = stripComments(new String(Files.readAllBytes(Paths.get(BASE + jsp)), StandardCharsets.UTF_8));

		int sizeMax = sizeMaxOf("engNm");
		Matcher m = Pattern.compile("path=\"engNm\"[^>]*maxlength=\"(\\d+)\"").matcher(source);
		assertTrue(m.find(), jsp + " 에서 engNm 의 maxlength 를 찾지 못했습니다.");
		int jspMax = Integer.parseInt(m.group(1));
		assertTrue(jspMax <= sizeMax, jsp + " 의 engNm maxlength=" + jspMax
				+ " 가 WordDicaryVO 의 @Size(max=" + sizeMax + ") 를 넘습니다.");
	}

	private int sizeMaxOf(String field) throws NoSuchFieldException {
		Field f = WordDicaryVO.class.getDeclaredField(field);
		return f.getAnnotation(Size.class).max();
	}

	private String stripComments(String source) {
		return HTML_COMMENT.matcher(source).replaceAll("");
	}
}
