package egovframework.com.sym.mnu.mpm.web;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.lang.reflect.Field;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.jupiter.api.Test;

import egovframework.com.sym.mnu.mpm.service.MenuManageVO;
import jakarta.validation.constraints.Size;

/**
 * 메뉴관리 수정 화면의 프로그램파일명 JSP maxlength가 MenuManageVO의 @Size 제약을 넘지 않는지 확인한다.
 */
class EgovMenuJspMaxlengthTest {

	private static final String BASE = "src/main/webapp/WEB-INF/jsp/egovframework/com/sym/mnu/mpm/";

	private static final Pattern HTML_COMMENT = Pattern.compile("<!--.*?-->", Pattern.DOTALL);

	@Test
	void menuDetailSelectUpdtMaxlengthWithinSize() throws Exception {
		String source = stripComments(new String(
				Files.readAllBytes(Paths.get(BASE + "EgovMenuDetailSelectUpdt.jsp")), StandardCharsets.UTF_8));

		int sizeMax = sizeMaxOf("progrmFileNm");
		Matcher m = Pattern.compile("path=\"progrmFileNm\"[^>]*maxlength=\"(\\d+)\"").matcher(source);
		assertTrue(m.find(), "progrmFileNm 의 maxlength 를 찾지 못했습니다.");
		int jspMax = Integer.parseInt(m.group(1));
		assertTrue(jspMax <= sizeMax, "EgovMenuDetailSelectUpdt.jsp 의 progrmFileNm maxlength=" + jspMax
				+ " 가 MenuManageVO 의 @Size(max=" + sizeMax + ") 를 넘습니다.");
	}

	private int sizeMaxOf(String field) throws NoSuchFieldException {
		Field f = MenuManageVO.class.getDeclaredField(field);
		return f.getAnnotation(Size.class).max();
	}

	private String stripComments(String source) {
		return HTML_COMMENT.matcher(source).replaceAll("");
	}
}
