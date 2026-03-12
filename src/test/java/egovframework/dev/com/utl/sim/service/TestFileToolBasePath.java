package egovframework.dev.com.utl.sim.service;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.util.List;
import java.util.Vector;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import egovframework.com.utl.sim.service.EgovFileTool;
import lombok.extern.slf4j.Slf4j;

/**
 * EgovFileTool.parsFileByChar() Test Class 구현
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
 *  테스트 조건 : 다음 설정 파일의 경로 확인 필수
 *  /src/test/resources/egovframework/egovProps/globals.properties
 *  Globals.fileStorePath = C:/egovframework/upload/
 *  Globals.SynchrnServerPath = C:/egovframework/upload/Synch/
 *
 */

@ContextConfiguration(
		locations={
		//"classpath*:egovframework/spring/test/security/context-aop-*.xml"
		//,"classpath*:egovframework/spring/test/security/context-common.xml"
		//,"classpath*:egovframework/spring/com/context-whitelist.xml"
		//,"classpath*:egovframework/spring/com/context-datasource.xml"
		//,"classpath*:egovframework/spring/com/context-mail.xml"
		//,"classpath*:egovframework/spring/com/context-config.xml"
		//,"classpath*:egovframework/spring/com/context-validator.xml"
		//,"classpath*:egovframework/spring/com/idgn/context-idgn-*.xml"
		}
)
//@ComponentScan(basePackages = { "egovframework.dev" })
@ActiveProfiles({ "mysql", "dummy" })
@Slf4j
@ExtendWith(SpringExtension.class)
public class TestFileToolBasePath {

	// 파일구분자
	static final char FILE_SEPARATOR = File.separatorChar;
	
	@Test
	void testParsFileByChar_outside() {
		// 허용되지 않은 디렉토리 지정
		String basePath = "C:/egovframework_outside/upload/test";
		String parFile = "test.device.api.web/pom.xml";
		String parChar = ":";
		int parField = 99;

		// 허용되지 않은 base path 사용 시 SecurityException 발생 → 기대 동작, 테스트 성공
		SecurityException thrown = assertThrows(SecurityException.class, () -> {
			EgovFileTool.parsFileByChar(basePath, parFile, parChar, parField);
		}, "Unacceptable base path 사용 시 SecurityException이 발생해야 합니다.");
		assertTrue(thrown.getMessage() != null && thrown.getMessage().contains("Unacceptable base path"),
				"예외 메시지에 'Unacceptable base path'가 포함되어야 합니다.");
	}

	
	@Test
	void testParsFileByChar_inside() {
		// Globals.fileStorePath(테스트: C:/egovframework/upload/) 하위 경로 사용 → SecurityException 없이 성공
		String basePath = "C:/egovframework/upload/";
		String parFile = "test.device.api.web/pom.xmlx";
		String parChar = ":";
		int parField = 99;

		Vector<List<String>> result = assertDoesNotThrow(() ->
				EgovFileTool.parsFileByChar(basePath, parFile, parChar, parField),
				"허용된 base path 사용 시 예외가 발생하면 안 됩니다.");
	}

}
