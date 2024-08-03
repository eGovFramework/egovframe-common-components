package egovframework.com.cmm.web;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.Test;

import egovframework.com.test.EgovTestAbstractController;
import lombok.RequiredArgsConstructor;

/**
 * /EgovLeft.do Controller 단위 테스트
 * 
 * @author 이백행
 * @since 2023-05-30
 *
 */
@RequiredArgsConstructor
public class EgovComIndexControllerTest5setLeftMenuTest extends EgovTestAbstractController {

	/**
	 * /EgovLeft.do Controller 단위 테스트
	 * 
	 * @throws Exception
	 */
	@Test
	public void test() throws Exception {
		mockMvc.perform(get("/EgovLeft.do")).andExpect(status().isOk()).andExpect(model().attributeExists("resultList"))
				.andExpect(view().name("egovframework/com/cmm/EgovUnitLeft")).andDo(print());

		assertEquals("", "", "");
	}

}
