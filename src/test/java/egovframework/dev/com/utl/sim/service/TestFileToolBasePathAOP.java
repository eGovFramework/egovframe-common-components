package egovframework.dev.com.utl.sim.service;

import java.io.File;
import java.util.List;
import java.util.Vector;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

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

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={
		"classpath*:egovframework/spring/test/security/context-aop-*.xml"
		,"classpath*:egovframework/spring/test/security/context-common.xml"
		,"classpath*:egovframework/spring/com/context-whitelist.xml"
		,"classpath*:egovframework/spring/com/context-datasource.xml"
		,"classpath*:egovframework/spring/com/context-mail.xml"
		,"classpath*:egovframework/spring/com/context-config.xml"
		,"classpath*:egovframework/spring/com/context-validator.xml"
		,"classpath*:egovframework/spring/com/idgn/context-idgn-*.xml"
		})
//@ComponentScan(basePackages = { "egovframework.com" })
@ActiveProfiles({ "mysql", "dummy" })
@Slf4j
public class TestFileToolBasePathAOP {

	@Autowired
	EgovFileToolBean fileTool;
	
	// 파일구분자
	static final char FILE_SEPARATOR = File.separatorChar;
	
	@Test
	public void testParsFileByChar() {
		
		String basePath = "D:/TEMP/";
		String parFile = "test.device.api.web/pom.xml";
		String parChar = ":";
		int parField = 99;
		
		Vector<List<String>> list = new Vector<List<String>>();
		
		try {
			list = fileTool.parsFileByChar(basePath, parFile, parChar, parField);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		int rowCount = 0;
		for (List<String> row : list) {
	        log.debug("Row = {} : Content = {}", rowCount++, row);
	    }
		
	}
	
}
