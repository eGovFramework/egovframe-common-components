package egovframework.com.cmm.web;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.Test;

import egovframework.com.test.EgovAbstractControllerV1Test;

public class EgovComIndexControllerTest_a10_index extends EgovAbstractControllerV1Test {

	@Test
	public void test_a10_index() throws Exception {
// @formatter:off
		mockMvc.perform(get("/index.do"))
		.andExpect(status().isOk())
		.andExpect(view().name("egovframework/com/cmm/EgovUnitMain"))
		;
// @formatter:on
	}

}
