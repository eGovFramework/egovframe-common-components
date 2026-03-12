package egovframework.com.secure.path;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import egovframework.com.cmm.EgovWebUtil;
import lombok.extern.slf4j.Slf4j;

/**
 * NullCheck Test Class 구현
 * @author 표준프레임워크 신용호
 * @since 2019.11.29
 * @version 3.9
 * @see
 * <pre>
 *
 *  수정일        수정자       수정내용
 *  ----------  --------   ---------------------------
 *  2024.12.04  신용호       시큐어코딩 테스트 ()
 *  2026.01.26  신용호       JUnit 4 => JUnit 5 마이그레이션
 *
 * </pre>
 */

@Slf4j
public class SecurePathTest {

	@Test
	void testSecurePath() {

		String filePath = "../etc/hosts";
		String basePath = "/egovframe/upload";
		
		String resultPath = EgovWebUtil.filePathBlackList(filePath, basePath);
		
		System.out.println(resultPath);
		assertEquals(resultPath, "/egovframe/upload/etc/hosts");
	}

	
	@Test
	void testSecurePath_withRoot() {

		String filePath = "../etc/hosts";
		String basePath = "/";
		
		assertThrows(SecurityException.class, () -> {
			EgovWebUtil.filePathBlackList(filePath, basePath);
		});

	}
	
	@Test
	void testSecurePath_withEmpty() {

		String filePath = "../etc/hosts";
		String basePath = "";
		
		assertThrows(SecurityException.class, () -> {
			EgovWebUtil.filePathBlackList(filePath, basePath);
		});

	}

	@Test
	void testSecurePath_withNull() {

		String filePath = "../etc/hosts";
		String basePath = null;
		
		assertThrows(SecurityException.class, () -> {
			EgovWebUtil.filePathBlackList(filePath, basePath);
		});

	}

}
