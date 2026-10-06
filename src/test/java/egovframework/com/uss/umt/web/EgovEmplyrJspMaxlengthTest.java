package egovframework.com.uss.umt.web;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.lang.reflect.Field;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.jupiter.api.Test;

import egovframework.com.uss.umt.service.EmplyrManageVO;
import jakarta.validation.constraints.Size;

/**
 * 업무사용자관리 화면의 JSP maxlength가 EmplyrManageVO의 @Size 제약을 넘지 않는지 확인한다.
 */
class EgovEmplyrJspMaxlengthTest {

	private static final String BASE = "src/main/webapp/WEB-INF/jsp/egovframework/com/uss/umt/";

	private static final String[] FIELDS = { "emplyrNm", "areaNo", "homemiddleTelno", "homeendTelno" };

	private static final Pattern HTML_COMMENT = Pattern.compile("<!--.*?-->", Pattern.DOTALL);

	@Test
	void emplyrInsertMaxlengthWithinSize() throws Exception {
		assertMaxlengthWithinSize("EgovEmplyrInsert.jsp");
	}

	@Test
	void emplyrSelectUpdtMaxlengthWithinSize() throws Exception {
		assertMaxlengthWithinSize("EgovEmplyrSelectUpdt.jsp");
	}

	private void assertMaxlengthWithinSize(String jsp) throws Exception {
		String source = stripComments(new String(Files.readAllBytes(Paths.get(BASE + jsp)), StandardCharsets.UTF_8));

		for (String field : FIELDS) {
			int sizeMax = sizeMaxOf(field);
			Matcher m = Pattern.compile("path=\"" + field + "\"[^>]*maxlength=\"(\\d+)\"").matcher(source);
			assertTrue(m.find(), jsp + " 에서 " + field + " 의 maxlength 를 찾지 못했습니다.");
			int jspMax = Integer.parseInt(m.group(1));
			assertTrue(jspMax <= sizeMax, jsp + " 의 " + field + " maxlength=" + jspMax
					+ " 가 EmplyrManageVO 의 @Size(max=" + sizeMax + ") 를 넘습니다.");
		}
	}

	private int sizeMaxOf(String field) throws NoSuchFieldException {
		Field f = EmplyrManageVO.class.getDeclaredField(field);
		return f.getAnnotation(Size.class).max();
	}

	private String stripComments(String source) {
		return HTML_COMMENT.matcher(source).replaceAll("");
	}
}
