package egovframework.com.cmm;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * 같은 매퍼의 데이터베이스별 변종은 같은 쿼리 아이디 집합을 선언해야 한다.
 *
 * 한 변종에만 쿼리가 빠지면 그 데이터베이스로 기동했을 때만 실패한다.
 *
 * @author 최완택
 * @since 2026-08-31
 */
@DisplayName("매퍼 변종")
class EgovMapperVariantTest {

	private static final Path MAPPER_ROOT = Paths.get("src/main/resources/egovframework/mapper");

	private static final Pattern FILE_NAME = Pattern
			.compile("(.+)_SQL_(mysql|maria|oracle|postgres|cubrid|tibero|altibase|goldilocks)\\.xml$");

	private static final Pattern NAMESPACE = Pattern.compile("<mapper\\s+namespace=\"([^\"]+)\"");

	private static final Pattern STATEMENT_ID = Pattern
			.compile("<(?:select|insert|update|delete)\\s[^>]*id=\"([^\"]+)\"");

	@Test
	@DisplayName("데이터베이스별 변종이 같은 쿼리 아이디를 선언한다")
	void mapperVariantsDeclareSameStatementIds() throws IOException {
		Map<String, Map<String, Set<String>>> groups = new LinkedHashMap<>();

		try (Stream<Path> paths = Files.walk(MAPPER_ROOT)) {
			for (Path path : paths.filter(Files::isRegularFile).toList()) {
				Matcher fileName = FILE_NAME.matcher(path.getFileName().toString());
				if (!fileName.matches()) {
					continue;
				}
				groups.computeIfAbsent(fileName.group(1), key -> new LinkedHashMap<>())
						.put(fileName.group(2), statementIds(path));
			}
		}

		List<String> missing = new ArrayList<>();

		for (Map.Entry<String, Map<String, Set<String>>> group : groups.entrySet()) {
			Set<String> union = new TreeSet<>();
			group.getValue().values().forEach(union::addAll);

			for (Map.Entry<String, Set<String>> variant : group.getValue().entrySet()) {
				Set<String> gap = new TreeSet<>(union);
				gap.removeAll(variant.getValue());
				if (!gap.isEmpty()) {
					missing.add(group.getKey() + "_SQL_" + variant.getKey() + ".xml " + gap);
				}
			}
		}

		assertTrue(missing.isEmpty(), "변종에 빠진 쿼리: " + missing);
	}

	/**
	 * 아이디에 네임스페이스 접두어를 붙인 변종과 붙이지 않은 변종이 섞여 있다.
	 * MyBatis 는 두 형태를 모두 등록하므로 접두어 유무는 동작 차이가 아니다. 벗겨서 비교한다.
	 */
	private Set<String> statementIds(Path path) throws IOException {
		String source = Files.readString(path, StandardCharsets.UTF_8);

		Matcher namespace = NAMESPACE.matcher(source);
		String prefix = namespace.find() ? namespace.group(1) + "." : "";

		Set<String> ids = new TreeSet<>();
		Matcher statement = STATEMENT_ID.matcher(source);
		while (statement.find()) {
			String id = statement.group(1);
			ids.add(id.startsWith(prefix) ? id.substring(prefix.length()) : id);
		}
		return ids;
	}

}
