package egovframework.com.cmm.web;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.Test;

import egovframework.com.cmm.service.EgovProperties;
import egovframework.com.test.EgovAbstractControllerV1Test;

public class EgovComIndexControllerTest_a40_setContent extends EgovAbstractControllerV1Test {

	@Test
	public void test_a40_setContent() throws Exception {
// @formatter:off
		mockMvc.perform(get("/EgovContent.do"))
		.andExpect(status().isOk())
		.andExpect(model().attributeExists("expirePwdDay", "loginVO", "passedDay", "elapsedTimeExpiration"))
		.andExpect(model().attribute("expirePwdDay", Integer.valueOf(EgovProperties.getProperty("Globals.ExpirePwdDay"))))
		.andExpect(view().name("egovframework/com/cmm/EgovUnitContent"))
		;
// @formatter:on
	}

}
