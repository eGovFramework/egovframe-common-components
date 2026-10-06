package egovframework.com.uss.umt.validation;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;

/**
 * MBER_NM DDL 문법·업무분류 누락 검증.
 * VO 제약만으로는 Altibase CHAR 미지원과 업무분류별 스크립트 누락을 확인하지 못하므로
 * 실제 DDL 파일을 전수 검사한다.
 */
class MberNmDdlConsistencyTest {

	private static final Pattern MBER_NM_TYPE = Pattern.compile("(?m)^\\s*MBER_NM\\s+(VARCHAR2?\\([^)]+\\))");

	@Test
	void altibaseDoesNotUseUnsupportedCharQualifier() throws IOException {
		List<Path> files = ddlFiles("altibase");
		assertFalse(files.isEmpty(), "Altibase DDL files should exist");

		List<String> violations = new ArrayList<String>();
		for (Path file : files) {
			String sql = read(file);
			if (!sql.contains("COMTNGNRLMBER")) {
				continue;
			}
			String type = extractMberNmType(sql);
			if (type == null) {
				violations.add(rel(file) + " : COMTNGNRLMBER exists but MBER_NM type is missing");
				continue;
			}
			if (type.matches("(?i).*\\b(CHAR|BYTE)\\b.*")) {
				violations.add(rel(file) + " : unsupported Altibase length qualifier " + type);
			}
			if (!"VARCHAR2(150)".equals(type)) {
				violations.add(rel(file) + " : expected VARCHAR2(150) but was " + type);
			}
		}
		assertTrue(violations.isEmpty(), String.join(System.lineSeparator(), violations));
	}

	@Test
	void oracleFamilyUsesFiftyCharacterSemantics() throws IOException {
		for (String vendor : new String[] {"oracle", "tibero", "goldilocks"}) {
			List<Path> files = ddlFiles(vendor);
			assertFalse(files.isEmpty(), vendor + " DDL files should exist");

			List<String> violations = new ArrayList<String>();
			for (Path file : files) {
				String sql = read(file);
				if (!sql.contains("COMTNGNRLMBER")) {
					continue;
				}
				String type = extractMberNmType(sql);
				if (!"VARCHAR2(50 CHAR)".equals(type)) {
					violations.add(rel(file) + " : expected VARCHAR2(50 CHAR) but was " + type);
				}
			}
			assertTrue(violations.isEmpty(), vendor + System.lineSeparator() + String.join(System.lineSeparator(), violations));
		}
	}

	@Test
	void everyBusinessCategoryCreateScriptDefinesMberNm() throws IOException {
		Path businessRoot = projectRoot().resolve("src/script/ddl");
		int checked = 0;
		List<String> missing = new ArrayList<String>();
		try (Stream<Path> stream = Files.walk(businessRoot)) {
			List<Path> createScripts = stream
					.filter(Files::isRegularFile)
					.filter(path -> path.getFileName().toString().endsWith(".sql"))
					.filter(path -> path.getFileName().toString().contains("_create_"))
					.collect(Collectors.toList());
			for (Path file : createScripts) {
				String sql = read(file);
				if (!sql.contains("CREATE TABLE COMTNGNRLMBER")) {
					continue;
				}
				checked++;
				if (extractMberNmType(sql) == null) {
					missing.add(rel(file));
				}
			}
		}
		assertTrue(checked > 0, "business-category create scripts with COMTNGNRLMBER should exist");
		assertTrue(missing.isEmpty(), "MBER_NM missing in:" + System.lineSeparator() + String.join(System.lineSeparator(), missing));
	}

	private static String extractMberNmType(String sql) {
		Matcher matcher = MBER_NM_TYPE.matcher(sql);
		return matcher.find() ? matcher.group(1) : null;
	}

	private static List<Path> ddlFiles(String vendor) throws IOException {
		List<Path> files = new ArrayList<Path>();
		files.addAll(sqlFiles(projectRoot().resolve("script/ddl").resolve(vendor)));
		files.addAll(sqlFiles(projectRoot().resolve("src/script/ddl").resolve(vendor)));
		return files;
	}

	private static List<Path> sqlFiles(Path dir) throws IOException {
		if (!Files.isDirectory(dir)) {
			return new ArrayList<Path>();
		}
		try (Stream<Path> stream = Files.walk(dir)) {
			return stream.filter(Files::isRegularFile)
					.filter(path -> path.getFileName().toString().endsWith(".sql"))
					.collect(Collectors.toList());
		}
	}

	private static String read(Path file) throws IOException {
		return new String(Files.readAllBytes(file), StandardCharsets.UTF_8);
	}

	private static String rel(Path file) {
		return projectRoot().relativize(file).toString().replace('\\', '/');
	}

	private static Path projectRoot() {
		Path dir = Paths.get("").toAbsolutePath();
		for (int i = 0; i < 6; i++) {
			if (Files.isDirectory(dir.resolve("src/script/ddl")) && Files.isDirectory(dir.resolve("script/ddl"))) {
				return dir;
			}
			Path parent = dir.getParent();
			if (parent == null) {
				break;
			}
			dir = parent;
		}
		throw new IllegalStateException("Cannot locate project root from " + Paths.get("").toAbsolutePath());
	}

}
