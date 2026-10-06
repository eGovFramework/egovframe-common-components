package egovframework.com.uss.umt.web;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.jupiter.api.Test;

/**
 * 업무사용자 화면의 스크립트가 참조하는 폼 이름이 실제 폼 이름과 같은지 확인한다.
 *
 * <p>{@code document.이름} 으로 폼을 찾는데 그 이름이 틀리면 undefined 가 되고, 뒤이어 속성이나
 * 메서드를 쓰는 순간 TypeError 로 끊긴다. 화면은 아무 반응이 없고 오류도 보이지 않는다.</p>
 */
class EgovEmplyrJspFormNameTest {

	private static final String BASE = "src/main/webapp/WEB-INF/jsp/egovframework/com/uss/umt/";

	private static final Pattern FORM_NAME = Pattern.compile("<form:form[^>]*\\sname=\"([^\"]+)\"");

	private static final Pattern DOCUMENT_FORM = Pattern.compile("document\\.([A-Za-z_][A-Za-z0-9_]*VO)\\b");

	private static final Pattern HTML_COMMENT = Pattern.compile("<!--.*?-->", Pattern.DOTALL);

	@Test
	void emplyrSelectUpdtScriptUsesItsOwnFormName() throws IOException {
		assertScriptMatchesFormName("EgovEmplyrSelectUpdt.jsp");
	}

	@Test
	void emplyrInsertScriptUsesItsOwnFormName() throws IOException {
		assertScriptMatchesFormName("EgovEmplyrInsert.jsp");
	}

	/** 주석 안의 옛 코드는 실행되지 않으므로 검사 대상에서 뺀다. */
	private String stripComments(String source) {
		return HTML_COMMENT.matcher(source).replaceAll("");
	}

	private void assertScriptMatchesFormName(String jsp) throws IOException {
		String source = stripComments(new String(Files.readAllBytes(Paths.get(BASE + jsp)), StandardCharsets.UTF_8));

		Matcher form = FORM_NAME.matcher(source);
		assertTrue(form.find(), jsp + " 에서 폼 이름을 찾지 못했습니다.");
		String formName = form.group(1);

		List<String> referenced = new ArrayList<>();
		Matcher used = DOCUMENT_FORM.matcher(source);
		while (used.find()) {
			referenced.add(used.group(1));
		}
		assertFalse(referenced.isEmpty(), jsp + " 에서 폼 참조를 찾지 못했습니다.");

		for (String name : referenced) {
			assertEquals(formName, name,
					jsp + " 의 스크립트가 존재하지 않는 폼을 참조합니다 : document." + name);
		}
	}
}
