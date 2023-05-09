package egovframework.com.web;

import javax.inject.Inject;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @ Spring MVC 기반 Controller 및 Dispatcher Test Class 구현
 * @author 표준프레임워크 신용호
 * @since 2019.04.23
 * @version 3.8
 * @see
 * <pre>
 *
 *  수정일              수정자          수정내용
 *  ----------  --------  ---------------------------
 *  2019.04.23  신용호          최초 생성
 *
 * @ 특징
   - Spring MVC 기반 Controller및 Dispatcher Servlet 테스트
   - init-method="init" 주석처리
     src/test/resources/~~~/context-scheduling-sym-bat.xml
     src/test/resources/~~~/context-scheduling-sym-sym.xml
   
 * </pre>
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { 
		//"file:src/main/resources/egovframework/spring/com/**/context-*.xml",
		"file:src/main/resources/egovframework/spring/com/context-*.xml",
		"file:src/main/resources/egovframework/spring/com/idgn/context-*.xml",
		//"file:src/main/resources/egovframework/spring/com/scheduling/context-*.xml",
		"file:src/test/resources/egovframework/spring/com/scheduling/context-scheduling-sym-bat.xml",
		"file:src/test/resources/egovframework/spring/com/scheduling/context-scheduling-sym-sym.xml",
		"file:src/main/webapp/WEB-INF/config/egovframework/springmvc/egov-com-servlet.xml"
})
@WebAppConfiguration
//@Profile("mysql")
@ActiveProfiles({"mysql","session"})
public class TestController {

	@Inject
	private WebApplicationContext wac;
	
	private MockMvc mockMvc;
	
	@Before
	public void setup() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
	}
	
	@Test 
	public void test() throws Exception {
		//fail("Not yet implemented");
		//mockMvc.perform(MockMvcRequestBuilders.get("/cmm/main/mainPage.do"));
		System.out.println("===> start test");
		mockMvc.perform(MockMvcRequestBuilders.post("/index.do").param("name","value"))
			.andExpect(status().isOk());
		
	}


}
