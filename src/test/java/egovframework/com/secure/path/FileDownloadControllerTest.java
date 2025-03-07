package egovframework.com.secure.path;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.FileNotFoundException;

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

/**
 * @ FileDownload 취약점 Test Class 구현
 * @author 표준프레임워크 신용호
 * @since 2019.04.23
 * @version 4.2
 * @see
 * <pre>
 *
 *  수정일         수정자       수정내용
 *  ----------   --------   ---------------------------
 *  2024.12.05   신용호       최초 생성
 *
 * </pre>
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
		//"file:src/main/resources/egovframework/spring/com/**/context-*.xml",
		//"classpath*:egovframework/spring/com/test-context-*.xml",
		"classpath*:egovframework/spring/com/test-context-common.xml",
		//"file:src/main/resources/egovframework/spring/com/scheduling/context-*.xml",
		//"file:src/test/resources/egovframework/spring/com/scheduling/context-scheduling-sym-bat.xml",
		//"file:src/test/resources/egovframework/spring/com/scheduling/context-scheduling-sym-sym.xml",
		"classpath*:egovframework/mvc/test-egov-com-servlet.xml"
})
@WebAppConfiguration
//@Profile("mysql")
@ActiveProfiles({"mysql","session"})
public class FileDownloadControllerTest {

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
		mockMvc.perform(MockMvcRequestBuilders.get("/downFile.do")
				.param("streFileNm","/etc/passwd") // macOS , Linux OS
				.param("streFileNm","C:\\Windows\\System32\\drivers\\etc\\hosts") // WindowsOS
				//.param("streFileNm","/C:/Windows/System32/drivers/etc/hosts")
                .param("orignFileNm", "hosts.txt"))
			.andExpect(status().is5xxServerError()); // 내부적으로 FileNotFoundException 발생
			//.andExpect(status().isOk()); // 보안 취약점 발생의 경우
		
	}


}
