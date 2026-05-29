package egovframework.com.cmm.web;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class EgovComUtlControllerTest {

	private static final String ERROR_VIEW = "egovframework/com/cmm/error/egovError";

	private EgovComUtlController controller;

	@BeforeEach
	void setUp() {
		controller = new EgovComUtlController();
		controller.egovWhitelist = Arrays.asList("/egovframework/com/main_bottom");
	}

	@Test
	void moveToPageRejectsNegativeLinkIndex() {
		assertEquals(ERROR_VIEW, controller.moveToPage(-1));
	}

	@Test
	void moveToPageRejectsOutOfRangeLinkIndex() {
		assertEquals(ERROR_VIEW, controller.moveToPage(1));
	}

}
