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
 * EgovComIndexController Controller 단위 테스트
 * 
 * @author 이백행
 * @since 2023-05-30
 *
 */
@RequiredArgsConstructor
public class EgovComIndexControllerTest extends EgovTestAbstractController {

	/**
	 * /index.do Controller 단위 테스트
	 * 
	 * @throws Exception
	 */
	@Test
	public void test1index() throws Exception {
		mockMvc.perform(get("/index.do")).andExpect(status().isOk())
				.andExpect(view().name("egovframework/com/cmm/EgovUnitMain"));

		assertEquals("", "", "");
	}

	/**
	 * /EgovTop.do Controller 단위 테스트
	 * 
	 * @throws Exception
	 */
	@Test
	public void test2top() throws Exception {
		mockMvc.perform(get("/EgovTop.do")).andExpect(status().isOk())
				.andExpect(view().name("egovframework/com/cmm/EgovUnitTop")).andDo(print());

		assertEquals("", "", "");
	}

	/**
	 * /EgovBottom.do Controller 단위 테스트
	 * 
	 * @throws Exception
	 */
	@Test
	public void test3bottom() throws Exception {
		mockMvc.perform(get("/EgovBottom.do")).andExpect(status().isOk())
				.andExpect(view().name("egovframework/com/cmm/EgovUnitBottom")).andDo(print());

		assertEquals("", "", "");
	}

	/**
	 * /EgovContent.do Controller 단위 테스트
	 * 
	 * @throws Exception
	 */
	@Test
	public void test4setContent() throws Exception {
		mockMvc.perform(get("/EgovContent.do")).andExpect(status().isOk())
				.andExpect(model().attributeExists("expirePwdDay", "loginVO", "passedDay", "elapsedTimeExpiration"))
				.andExpect(model().attribute("expirePwdDay",
						Integer.valueOf(EgovProperties.getProperty("Globals.ExpirePwdDay"))))
				.andExpect(view().name("egovframework/com/cmm/EgovUnitContent")).andDo(print());

		assertEquals("", "", "");
	}

	/**
	 * /EgovLeft.do Controller 단위 테스트
	 * 
	 * @throws Exception
	 */
	@Test
	public void test5setLeftMenu() throws Exception {
		mockMvc.perform(get("/EgovLeft.do")).andExpect(status().isOk()).andExpect(model().attributeExists("resultList"))
				.andExpect(view().name("egovframework/com/cmm/EgovUnitLeft")).andDo(print());

		assertEquals("", "", "");
	}

	/**
	 * csrf Token이 없는경우 이동하는 페이지 Controller 단위 테스트
	 * 
	 * @throws Exception
	 */
	@Test
	public void test6egovCSRFAccessDenied() throws Exception {
		mockMvc.perform(get("/egovCSRFAccessDenied.do")).andExpect(status().isFound()).andDo(print());

		assertEquals("", "", "");
	}

}
