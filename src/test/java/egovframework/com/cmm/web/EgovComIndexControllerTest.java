package egovframework.com.cmm.web;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.egovframe.rte.fdl.cmmn.exception.BaseRuntimeException;
import org.junit.Test;

import egovframework.com.cmm.service.EgovProperties;
import egovframework.com.test.EgovAbstractTestJUnit4;

/**
 * EgovComIndexController Test
 * 
 * @author 이백행
 * @since 2023.05.30
 * @version 4.1.0
 * @see
 *
 *      <pre>
 *  == 개정이력(Modification Information) ==
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2023.05.30  이백행          2023년 컨트리뷰션 최초 생성
 *   2025.07.16  이백행          2025년 컨트리뷰션 BaseRuntimeException 추가
 *
 *      </pre>
 */
public class EgovComIndexControllerTest extends EgovAbstractTestJUnit4 {

	@Test
	public void test1index() throws BaseRuntimeException, Exception {
		mockMvc.perform(get("/index.do"))

				.andExpect(status().isOk())

				.andExpect(view().name("egovframework/com/cmm/EgovUnitMain"))

				.andDo(print())

		;
	}

	@Test
	public void test2top() throws BaseRuntimeException, Exception {
		mockMvc.perform(get("/EgovTop.do"))

				.andExpect(status().isOk())

				.andExpect(view().name("egovframework/com/cmm/EgovUnitTop"))

				.andDo(print())

		;
	}

	@Test
	public void test3bottom() throws BaseRuntimeException, Exception {
		mockMvc.perform(get("/EgovBottom.do"))

				.andExpect(status().isOk())

				.andExpect(view().name("egovframework/com/cmm/EgovUnitBottom"))

				.andDo(print())

		;
	}

	@Test
	public void test4setContent() throws BaseRuntimeException, Exception {
		mockMvc.perform(get("/EgovContent.do"))

				.andExpect(status().isOk())

				.andExpect(model().attribute("expirePwdDay",
						Integer.parseInt(EgovProperties.getProperty("Globals.ExpirePwdDay"))))

				.andExpect(model().attributeExists("expirePwdDay", "loginVO", "passedDay", "elapsedTimeExpiration"))

				.andDo(print())

		;
	}

	@Test
	public void test5setLeftMenu() throws BaseRuntimeException, Exception {
		mockMvc.perform(get("/EgovLeft.do"))

				.andExpect(status().isOk())

				.andExpect(model().attributeExists("resultList"))

				.andExpect(view().name("egovframework/com/cmm/EgovUnitLeft"))

				.andDo(print())

		;
	}

	@Test
	public void test6egovCSRFAccessDenied() throws BaseRuntimeException, Exception {
		mockMvc.perform(get("/egovCSRFAccessDenied.do"))

				.andExpect(status().isFound())

				.andDo(print())

		;
	}

}
