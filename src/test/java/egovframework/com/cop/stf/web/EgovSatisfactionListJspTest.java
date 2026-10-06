package egovframework.com.cop.stf.web;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.jupiter.api.Test;

/**
 * 만족도 목록 화면의 페이징 태그가 이 화면이 정의한 페이징 함수를 가리키는지 확인한다.
 *
 * <p>만족도 화면은 자기 페이징 함수 fn_egov_select_satisfactionList 를 직접 정의하고,
 * "만족도 초기화" 버튼도 그 함수를 부른다. 페이징 태그만 다른 화면의 함수를 가리키면
 * 댓글을 끈 게시판(ANSWER_AT='N', STSFDG_AT='Y')에서 페이징이 동작하지 않는다.
 * 그 화면에는 댓글 form 이 없어 함수가 참조할 대상이 사라지기 때문이다.</p>
 */
class EgovSatisfactionListJspTest {

	private static final String SATISFACTION_JSP =
			"src/main/webapp/WEB-INF/jsp/egovframework/com/cop/stf/EgovSatisfactionList.jsp";

	private static final Pattern PAGINATION_JS_FUNCTION =
			Pattern.compile("<ui:pagination[^>]*jsFunction=\"([^\"]+)\"");

	private static final Pattern FUNCTION_DECLARATION =
			Pattern.compile("function\\s+(\\w+)\\s*\\(");

	@Test
	void paginationCallsFunctionDeclaredInSameJsp() throws IOException {
		String source = new String(Files.readAllBytes(Paths.get(SATISFACTION_JSP)), StandardCharsets.UTF_8);

		List<String> jsFunctions = matches(PAGINATION_JS_FUNCTION, source);
		assertEquals(1, jsFunctions.size(), SATISFACTION_JSP + " 의 페이징 태그를 찾지 못했습니다.");

		Set<String> declared = new HashSet<>(matches(FUNCTION_DECLARATION, source));
		String jsFunction = jsFunctions.get(0);

		assertTrue(declared.contains(jsFunction),
				"이 화면에 정의되지 않은 함수를 페이징 태그가 가리키고 있습니다 : " + jsFunction);
	}

	private List<String> matches(Pattern pattern, String source) {
		List<String> found = new ArrayList<>();
		Matcher matcher = pattern.matcher(source);
		while (matcher.find()) {
			found.add(matcher.group(1));
		}
		return found;
	}
}
