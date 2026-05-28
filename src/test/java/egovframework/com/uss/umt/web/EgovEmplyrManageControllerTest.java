package egovframework.com.uss.umt.web;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

class EgovEmplyrManageControllerTest {

	private static final String ERROR_VIEW = "egovframework/com/cmm/error/egovError";

	private MockMvc mockMvc;

	@BeforeEach
	void setUp() {
		EgovEmplyrManageController controller = new EgovEmplyrManageController();
		controller.nextUrlWhitelist = Arrays.asList("/uss/umt/EgovMberSbscrbView.do");
		mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
	}

	@Test
	void rlnmCnfirmRejectsNegativeNextUrlIndex() throws Exception {
		mockMvc.perform(post("/uss/umt/EgovRlnmCnfirm.do").param("nextUrl", "-1"))
			.andExpect(status().isBadRequest())
			.andExpect(view().name(ERROR_VIEW));
	}

	@Test
	void rlnmCnfirmRejectsNonNumericNextUrlIndex() throws Exception {
		mockMvc.perform(post("/uss/umt/EgovRlnmCnfirm.do").param("nextUrl", "invalid"))
			.andExpect(status().isBadRequest());
	}

}
