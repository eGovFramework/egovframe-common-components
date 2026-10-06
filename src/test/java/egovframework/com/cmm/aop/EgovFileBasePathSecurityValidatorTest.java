package egovframework.com.cmm.aop;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import egovframework.com.cmm.service.EgovProperties;

/**
 * basePath 화이트리스트 검증 테스트.
 * startsWith 문자열 접두사 비교로 인한 형제 디렉토리 우회(partial path traversal) 여부를 검증한다.
 */
class EgovFileBasePathSecurityValidatorTest {

	// 프로퍼티 값이 슬래시로 끝나면 "_evil"이 하위 경로가 되므로 끝 슬래시를 제거한다.
	private final String fileStorePath = EgovProperties.getProperty("Globals.fileStorePath").replaceAll("[/\\\\]+$", "");

	@Test
	void allowsSubdirectoryOfWhitelistedPath() {
		assertTrue(EgovFileBasePathSecurityValidator.validate(fileStorePath + "/sub"));
	}

	@Test
	void rejectsUnrelatedPath() {
		assertFalse(EgovFileBasePathSecurityValidator.validate("/etc/passwd"));
	}

	@Test
	void rejectsRootPath() {
		assertFalse(EgovFileBasePathSecurityValidator.validate("/"));
	}

	@Test
	void rejectsSiblingDirectoryWithWhitelistedPrefix() {
		// /upload/allinone 화이트리스트일 때 /upload/allinone_evil 은 하위 디렉토리가 아니므로 거부되어야 한다.
		assertFalse(EgovFileBasePathSecurityValidator.validate(fileStorePath + "_evil"),
				"sibling-directory prefix bypass: " + fileStorePath + "_evil must be rejected");
	}
}
