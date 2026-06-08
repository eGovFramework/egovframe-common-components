package egovframework.com.uss.umt.web;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;

class EgovEmplyrManageControllerTest {

	private static final String ERROR_VIEW = "egovframework/com/cmm/error/egovError";

	private EgovEmplyrManageController controller;

	@BeforeEach
	void setUp() {
		controller = new EgovEmplyrManageController();
		controller.nextUrlWhitelist = Arrays.asList("/uss/umt/EgovMberSbscrbView.do");
	}

	@Test
	void rlnmCnfirmRejectsNegativeNextUrlIndex() throws Exception {
		assertEquals(ERROR_VIEW, controller.rlnmCnfirm(model(), commandMap(), -1));
	}

	@Test
	void rlnmCnfirmRejectsOutOfRangeNextUrlIndex() throws Exception {
		assertEquals(ERROR_VIEW, controller.rlnmCnfirm(model(), commandMap(), 1));
	}

	private Model model() {
		return new ExtendedModelMap();
	}

	private Map<String, Object> commandMap() {
		return new HashMap<>();
	}

}
