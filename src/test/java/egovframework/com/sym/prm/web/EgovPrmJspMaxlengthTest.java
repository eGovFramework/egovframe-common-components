package egovframework.com.sym.prm.web;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.lang.reflect.Field;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.jupiter.api.Test;

import egovframework.com.sym.prm.service.ProgrmManageDtlVO;
import egovframework.com.sym.prm.service.ProgrmManageVO;
import jakarta.validation.constraints.Size;

/**
 * 프로그램관리·변경요청 화면의 JSP maxlength가 각 VO의 @Size 제약을 넘지 않는지 확인한다.
 */
class EgovPrmJspMaxlengthTest {

	private static final String BASE = "src/main/webapp/WEB-INF/jsp/egovframework/com/sym/prm/";

	private static final Pattern HTML_COMMENT = Pattern.compile("<!--.*?-->", Pattern.DOTALL);

	@Test
	void programListRegistMaxlengthWithinSize() throws Exception {
		assertFieldWithinSize("EgovProgramListRegist.jsp", "progrmKoreanNm", ProgrmManageVO.class);
	}

	@Test
	void programListDetailSelectUpdtMaxlengthWithinSize() throws Exception {
		assertFieldWithinSize("EgovProgramListDetailSelectUpdt.jsp", "progrmKoreanNm", ProgrmManageVO.class);
	}

	@Test
	void programChangRequstDetailSelectUpdtMaxlengthWithinSize() throws Exception {
		assertFieldWithinSize("EgovProgramChangRequstDetailSelectUpdt.jsp", "rqesterSj", ProgrmManageDtlVO.class);
	}

	private void assertFieldWithinSize(String jsp, String field, Class<?> vo) throws Exception {
		String source = stripComments(new String(Files.readAllBytes(Paths.get(BASE + jsp)), StandardCharsets.UTF_8));

		int sizeMax = sizeMaxOf(vo, field);
		Matcher m = Pattern.compile("path=\"" + field + "\"[^>]*maxlength=\"(\\d+)\"").matcher(source);
		assertTrue(m.find(), jsp + " 에서 " + field + " 의 maxlength 를 찾지 못했습니다.");
		int jspMax = Integer.parseInt(m.group(1));
		assertTrue(jspMax <= sizeMax, jsp + " 의 " + field + " maxlength=" + jspMax
				+ " 가 " + vo.getSimpleName() + " 의 @Size(max=" + sizeMax + ") 를 넘습니다.");
	}

	private int sizeMaxOf(Class<?> vo, String field) throws NoSuchFieldException {
		Field f = vo.getDeclaredField(field);
		return f.getAnnotation(Size.class).max();
	}

	private String stripComments(String source) {
		return HTML_COMMENT.matcher(source).replaceAll("");
	}
}
