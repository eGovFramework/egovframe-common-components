package egovframework.com.sec.rgm.web;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.lang.reflect.Method;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.jupiter.api.Test;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 권한그룹관리 목록 화면의 전송 URL이 실제 요청 매핑을 갖는지 확인한다.
 *
 * <p>이 화면에는 submit 버튼이 없고 검색어 입력칸 하나만 암묵적 제출을 막는 필드다.
 * 그래서 검색어 칸에서 Enter를 치면 form 의 action 으로 그대로 POST 된다.
 * JS 는 조회·등록·삭제·페이징마다 action 을 다시 지정하지만, 그 경로를 타지 않는 Enter 는
 * form 에 적힌 action 을 그대로 쓴다.</p>
 */
class EgovAuthorGroupManageJspTest {

	private static final String LIST_JSP =
			"src/main/webapp/WEB-INF/jsp/egovframework/com/sec/rgm/EgovAuthorGroupManage.jsp";

	private static final Pattern FORM_TAG_ACTION =
			Pattern.compile("<form:form[^>]*action=\"\\$\\{pageContext\\.request\\.contextPath\\}([^\"]+)\"");

	private static final Pattern SCRIPT_ACTION =
			Pattern.compile("\\.action\\s*=\\s*\"<c:url\\s+value='([^']+)'\\s*/>\"");

	@Test
	void everyActionInListJspHasHandler() throws IOException {
		Set<String> mappings = mappingsOf(EgovAuthorGroupController.class);

		List<String> actions = actionsOf(LIST_JSP);
		assertFalse(actions.isEmpty(), LIST_JSP + " 에서 전송 URL을 찾지 못했습니다.");

		for (String action : actions) {
			assertTrue(mappings.contains(action), "처리할 핸들러가 없는 전송 URL 입니다 : " + action);
		}
	}

	private List<String> actionsOf(String jsp) throws IOException {
		String source = new String(Files.readAllBytes(Paths.get(jsp)), StandardCharsets.UTF_8);
		List<String> actions = new ArrayList<>();
		for (Pattern pattern : Arrays.asList(FORM_TAG_ACTION, SCRIPT_ACTION)) {
			Matcher matcher = pattern.matcher(source);
			while (matcher.find()) {
				actions.add(matcher.group(1));
			}
		}
		return actions;
	}

	private Set<String> mappingsOf(Class<?>... controllers) {
		Set<String> mappings = new HashSet<>();
		for (Class<?> controller : controllers) {
			for (Method method : controller.getDeclaredMethods()) {
				PostMapping post = method.getAnnotation(PostMapping.class);
				if (post != null) {
					mappings.addAll(Arrays.asList(post.value()));
				}
				RequestMapping request = method.getAnnotation(RequestMapping.class);
				if (request != null) {
					mappings.addAll(Arrays.asList(request.value()));
				}
			}
		}
		return mappings;
	}
}
