package egovframework.dev.com.utl.sim.service;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.util.List;
import java.util.Vector;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import egovframework.com.utl.sim.service.EgovFileToolBean;
import lombok.extern.slf4j.Slf4j;

/**
 * EgovFileToolBean.parsFileByChar() Test Class 구현 (AOP)
 * @author 표준프레임워크 신용호
 * @since 2019.04.25
 * @version 4.3
 * @see
 * <pre>
 *
 *  수정일         수정자          수정내용
 *  ----------   -----------   ---------------------------
 *  2025.03.31   신용호          최초 생성
 *
 */
@ContextConfiguration(classes = TestFileToolBasePathAOP.TestConfig.class)
@ActiveProfiles({ "mysql", "dummy" })
@Slf4j
@ExtendWith(SpringExtension.class)
public class TestFileToolBasePathAOP {

	@Autowired
	EgovFileToolBean fileTool;

	@Configuration
	static class TestConfig {
		@Bean
		public EgovFileToolBean fileTool() {
			return new EgovFileToolBean();
		}
	}
	
	// 파일구분자
	static final char FILE_SEPARATOR = File.separatorChar;
	
	@Test
	void testParsFileByChar_outside() {
		// 허용되지 않은 base path (D:/TEMP/ 등) 사용 시 SecurityException 발생 → 기대 동작, 테스트 성공
		String basePath = "C:/egovframework_outside/upload/";
		String parFile = "test.device.api.web/pom.xml";
		String parChar = ":";
		int parField = 99;

		SecurityException thrown = assertThrows(SecurityException.class, () -> {
			fileTool.parsFileByChar(basePath, parFile, parChar, parField);
		}, "Unacceptable base path 사용 시 SecurityException이 발생해야 합니다.");
		assertTrue(thrown.getMessage() != null && thrown.getMessage().contains("Unacceptable base path"),
				"예외 메시지에 'Unacceptable base path'가 포함되어야 합니다.");
	}

	@Test
	void testParsFileByChar_inside() {
		// 허용된 base path(Globals.fileStorePath 하위) 사용 시 예외 없이 성공
		String basePath = "C:/egovframework/upload/";
		String parFile = "test.device.api.web/pom.xml";
		String parChar = ":";
		int parField = 99;

		Vector<List<String>> result = assertDoesNotThrow(() ->
				fileTool.parsFileByChar(basePath, parFile, parChar, parField),
				"허용된 base path 사용 시 예외가 발생하면 안 됩니다.");
		assertNotNull(result, "parsFileByChar 반환값은 null이 아니어야 합니다.");
	}

}
