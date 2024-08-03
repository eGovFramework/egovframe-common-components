package egovframework.com.cmm.web;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.Test;

import egovframework.com.cmm.service.EgovProperties;
import egovframework.com.test.EgovTestAbstractController;
import lombok.RequiredArgsConstructor;

/**
 * /EgovContent.do Controller 단위 테스트
 * 
 * @author 이백행
 * @since 2023-05-30
 *
 */
@RequiredArgsConstructor
public class EgovComIndexControllerTest4setContentTest extends EgovTestAbstractController {

	/**
	 * /EgovContent.do Controller 단위 테스트
	 * 
	 * @throws Exception
	 */
	@Test
	public void test() throws Exception {
		mockMvc.perform(get("/EgovContent.do")).andExpect(status().isOk())
				.andExpect(model().attributeExists("expirePwdDay", "loginVO", "passedDay", "elapsedTimeExpiration"))
				.andExpect(model().attribute("expirePwdDay",
						Integer.valueOf(EgovProperties.getProperty("Globals.ExpirePwdDay"))))
				.andExpect(view().name("egovframework/com/cmm/EgovUnitContent")).andDo(print());

		assertEquals("", "", "");
	}

}
