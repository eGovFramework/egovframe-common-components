package egovframework.com.cmm.web;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.Test;

import egovframework.com.test.EgovAbstractControllerV1Test;

public class EgovComIndexControllerTest_a50_setLeftMenu extends EgovAbstractControllerV1Test {

	@Test
	public void test_a50_setLeftMenu() throws Exception {
// @formatter:off
		mockMvc.perform(get("/EgovLeft.do"))
		.andExpect(status().isOk())
		.andExpect(model().attributeExists("resultList"))
		.andExpect(view().name("egovframework/com/cmm/EgovUnitLeft"))
		;
// @formatter:on
	}

}
