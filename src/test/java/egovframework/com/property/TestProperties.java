package egovframework.com.property;

import static org.junit.jupiter.api.Assertions.assertEquals;

import jakarta.annotation.Resource;

import org.egovframe.rte.fdl.property.EgovPropertyService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;

import lombok.extern.slf4j.Slf4j;

/**
 * @Transactional Test Class 구현
 * @author 표준프레임워크 신용호
 * @since 2019.04.23
 * @version 3.8
 * @see
 * <pre>
 *
 *  수정일        수정자      수정내용
 *  ----------  --------  ---------------------------
 *  2024.10.16  신용호      최초 생성
 *  2026.01.26  신용호      JUnit 4 => JUnit 5 마이그레이션
 *
 * @ 특징
   - Property 테스트
   
 * </pre>
 */

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = {
		//"file:src/test/resources/egovframework/spring/com/context-*.xml",
		"file:src/test/resources/egovframework/spring/com/property/context-*.xml"
})
//@ContextConfiguration(locations={"classpath*:egovframework/spring/com/context-*.xml",
//								"classpath*:egovframework/spring/com/property/context-*.xml"})

@WebAppConfiguration
@ActiveProfiles({"mysql","session"})
@Slf4j
public class TestProperties {

	@Resource(name = "localPropertiesService")
	private EgovPropertyService propertiesService;
	
	@Test
	void test() throws Exception {
		
		int pageUnit = propertiesService.getInt("pageUnit");
		int pageSize = propertiesService.getInt("pageSize");
		
		log.debug("===>>> pageUnit = {}", pageUnit);
		log.debug("===>>> pageSize = {}", pageSize);
		
		assertEquals(pageUnit, 20);
		assertEquals(pageSize, 10);
	}

}
