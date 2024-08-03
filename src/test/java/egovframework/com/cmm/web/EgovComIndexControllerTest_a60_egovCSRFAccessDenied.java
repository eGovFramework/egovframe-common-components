package egovframework.com.cmm.web;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;

import egovframework.com.test.EgovAbstractControllerV1Test;

public class EgovComIndexControllerTest_a60_egovCSRFAccessDenied extends EgovAbstractControllerV1Test {

	@Test
	public void test_a60_egovCSRFAccessDenied() throws Exception {
// @formatter:off
// @formatter:on
		mockMvc.perform(get("/egovCSRFAccessDenied.do")).andExpect(status().isFound());
	}

}
