package egovframework.com.cmm.web;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

class EgovComUtlControllerTest {

	private static final String ERROR_VIEW = "egovframework/com/cmm/error/egovError";

	private MockMvc mockMvc;

	@BeforeEach
	void setUp() {
		EgovComUtlController controller = new EgovComUtlController();
		controller.egovWhitelist = Arrays.asList("/egovframework/com/main_bottom");
		mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
	}

	@Test
	void moveToPageRejectsNegativeLinkIndex() throws Exception {
		mockMvc.perform(get("/EgovPageLink.do").param("linkIndex", "-1"))
			.andExpect(status().isBadRequest())
			.andExpect(view().name(ERROR_VIEW));
	}

	@Test
	void moveToPageRejectsNonNumericLinkIndex() throws Exception {
		mockMvc.perform(get("/EgovPageLink.do").param("linkIndex", "invalid"))
			.andExpect(status().isBadRequest());
	}

}
