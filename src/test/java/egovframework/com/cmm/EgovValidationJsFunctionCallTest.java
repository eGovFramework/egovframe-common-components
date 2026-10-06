package egovframework.com.cmm;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * JSP가 호출하는 validate*() 함수가 EgovValidation.js에 실제로 정의돼 있는지 확인한다.
 *
 * 정의 없이 호출되면 제출 시 ReferenceError가 나 클라이언트 검증이 통째로 건너뛰어진다.
 *
 * @author 최완택
 * @since 2026-09-15
 */
@DisplayName("EgovValidation.js 함수 호출")
class EgovValidationJsFunctionCallTest {

	private static final Path JS_FILE =
			Paths.get("src/main/webapp/js/egovframework/com/cmm/EgovValidation.js");

	private static final Path WEBAPP_ROOT = Paths.get("src/main/webapp");

	private static final Pattern FUNCTION_DEF = Pattern.compile("function\\s+(validate[A-Z]\\w*)\\s*\\(");

	private static final Pattern FUNCTION_CALL = Pattern.compile("\\b(validate[A-Z]\\w*)\\s*\\(");

	@Test
	@DisplayName("JSP가 부르는 validate*() 함수가 모두 정의돼 있다")
	void everyCalledValidateFunctionIsDefined() throws IOException {
		Set<String> defined = definedFunctions();
		assertFalse(defined.isEmpty(), "EgovValidation.js에서 정의된 함수를 찾지 못했습니다.");

		List<String> missing = new ArrayList<>();
		try (Stream<Path> paths = Files.walk(WEBAPP_ROOT)) {
			for (Path jsp : paths.filter(p -> p.toString().endsWith(".jsp")).toList()) {
				String source = stripJspComments(read(jsp));
				Matcher matcher = FUNCTION_CALL.matcher(source);
				Set<String> seen = new TreeSet<>();
				while (matcher.find()) {
					seen.add(matcher.group(1));
				}
				for (String name : seen) {
					if (!defined.contains(name)) {
						missing.add(name + " ← " + jsp);
					}
				}
			}
		}

		assertTrue(missing.isEmpty(), "정의 없이 호출된 검증 함수:\n" + String.join("\n", missing));
	}

	private Set<String> definedFunctions() throws IOException {
		Matcher matcher = FUNCTION_DEF.matcher(read(JS_FILE));
		Set<String> defined = new HashSet<>();
		while (matcher.find()) {
			defined.add(matcher.group(1));
		}
		return defined;
	}

	private String stripJspComments(String source) {
		return source.replaceAll("(?s)<%--.*?--%>", "");
	}

	private String read(Path path) throws IOException {
		return new String(Files.readAllBytes(path), StandardCharsets.UTF_8);
	}
}
