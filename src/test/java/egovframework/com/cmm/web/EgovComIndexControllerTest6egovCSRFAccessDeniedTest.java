package egovframework.com.cmm.web;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;

import egovframework.com.test.EgovTestAbstractController;
import lombok.RequiredArgsConstructor;

/**
 * csrf Token이 없는경우 이동하는 페이지 Controller 단위 테스트
 * 
 * @author 이백행
 * @since 2023-05-30
 *
 */
@RequiredArgsConstructor
public class EgovComIndexControllerTest6egovCSRFAccessDeniedTest extends EgovTestAbstractController {

	/**
	 * csrf Token이 없는경우 이동하는 페이지 Controller 단위 테스트
	 * 
	 * @throws Exception
	 */
	@Test
	public void test() throws Exception {
		mockMvc.perform(get("/egovCSRFAccessDenied.do")).andExpect(status().isFound()).andDo(print());

		assertEquals("", "", "");
	}

}
