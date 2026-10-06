package egovframework.com.uss.olp.cns.web;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.lang.reflect.Field;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.jupiter.api.Test;

import egovframework.com.uss.olp.cns.service.CnsltManageVO;
import jakarta.validation.constraints.Size;

/**
 * 상담 등록·수정 화면의 작성자명 JSP maxlength가 CnsltManageVO의 @Size 제약을 넘지 않는지 확인한다.
 */
class EgovCnsltJspMaxlengthTest {

	private static final String BASE = "src/main/webapp/WEB-INF/jsp/egovframework/com/uss/olp/cns/";

	private static final Pattern HTML_COMMENT = Pattern.compile("<!--.*?-->", Pattern.DOTALL);

	@Test
	void cnsltDtlsRegistMaxlengthWithinSize() throws Exception {
		assertMaxlengthWithinSize("EgovCnsltDtlsRegist.jsp");
	}

	@Test
	void cnsltDtlsUpdtMaxlengthWithinSize() throws Exception {
		assertMaxlengthWithinSize("EgovCnsltDtlsUpdt.jsp");
	}

	private void assertMaxlengthWithinSize(String jsp) throws Exception {
		String source = stripComments(new String(Files.readAllBytes(Paths.get(BASE + jsp)), StandardCharsets.UTF_8));

		int sizeMax = sizeMaxOf("wrterNm");
		Matcher m = Pattern.compile("path=\"wrterNm\"[^>]*maxlength=\"(\\d+)\"").matcher(source);
		assertTrue(m.find(), jsp + " 에서 wrterNm 의 maxlength 를 찾지 못했습니다.");
		int jspMax = Integer.parseInt(m.group(1));
		assertTrue(jspMax <= sizeMax, jsp + " 의 wrterNm maxlength=" + jspMax
				+ " 가 CnsltManageVO 의 @Size(max=" + sizeMax + ") 를 넘습니다.");
	}

	private int sizeMaxOf(String field) throws NoSuchFieldException {
		Field f = CnsltManageVO.class.getDeclaredField(field);
		return f.getAnnotation(Size.class).max();
	}

	private String stripComments(String source) {
		return HTML_COMMENT.matcher(source).replaceAll("");
	}
}
