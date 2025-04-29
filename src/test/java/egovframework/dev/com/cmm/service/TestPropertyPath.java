package egovframework.dev.com.cmm.service;

import static org.junit.Assert.assertNotEquals;

import java.util.ArrayList;
import java.util.Map;

import org.junit.Test;

import egovframework.com.cmm.service.EgovProperties;
import lombok.extern.slf4j.Slf4j;

/**
 * EgovProperties.loadPropertyFile() Test Class 구현
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

@Slf4j
public class TestPropertyPath {

	@Test
	public void testLoadPropertyFile() {
		
		ArrayList<Map<String, String>> result = new ArrayList<Map<String, String>>();
		log.debug("BASE_PATH ; "+EgovProperties.RELATIVE_PATH_PREFIX);
		
		result = EgovProperties.loadPropertyFile("egovProps/test.properties");
		log.debug("RESULT = " + result);
		
		//assertNotNull(result);
		assertNotEquals(0,result.size());
	}
	
}
