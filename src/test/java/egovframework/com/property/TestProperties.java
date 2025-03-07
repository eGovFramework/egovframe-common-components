package egovframework.com.property;

import static org.junit.Assert.assertEquals;

import javax.annotation.Resource;

import org.egovframe.rte.fdl.property.EgovPropertyService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
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
 *
 * @ 특징
   - Property 테스트
   
 * </pre>
 */

@RunWith(SpringJUnit4ClassRunner.class)
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
	public void test() throws Exception {
		
		int pageUnit = propertiesService.getInt("pageUnit");
		int pageSize = propertiesService.getInt("pageSize");
		
		log.debug("===>>> pageUnit = {}", pageUnit);
		log.debug("===>>> pageSize = {}", pageSize);
		
		assertEquals(pageUnit, 20);
		assertEquals(pageSize, 10);
	}

}
