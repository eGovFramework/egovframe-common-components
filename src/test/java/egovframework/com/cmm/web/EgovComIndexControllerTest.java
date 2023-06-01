package egovframework.com.cmm.web;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.Test;

import egovframework.com.cmm.service.EgovProperties;
import egovframework.com.test.EgovAbstractControllerV1Test;

public class EgovComIndexControllerTest extends EgovAbstractControllerV1Test {

	@Test
	public void test_a10_index() throws Exception {
// @formatter:off
		mockMvc.perform(get("/index.do"))
		.andExpect(status().isOk())
		.andExpect(view().name("egovframework/com/cmm/EgovUnitMain"))
		;
// @formatter:on
	}

	@Test
	public void test_a20_top() throws Exception {
// @formatter:off
		mockMvc.perform(get("/EgovTop.do"))
		.andExpect(status().isOk())
		.andExpect(view().name("egovframework/com/cmm/EgovUnitTop"))
		;
// @formatter:on
	}

	@Test
	public void test_a30_bottom() throws Exception {
// @formatter:off
		mockMvc.perform(get("/EgovBottom.do"))
		.andExpect(status().isOk())
		.andExpect(view().name("egovframework/com/cmm/EgovUnitBottom"))
		;
// @formatter:on
	}

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

	@Test
	public void test_a60_egovCSRFAccessDenied() throws Exception {
// @formatter:off
		mockMvc.perform(get("/egovCSRFAccessDenied.do"))
		.andExpect(status().isOk())
		.andExpect(view().name("egovframework/com/cmm/error/csrfAccessDenied"))
		;
// @formatter:on
	}

}
