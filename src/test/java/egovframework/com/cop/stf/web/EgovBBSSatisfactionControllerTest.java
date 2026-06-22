package egovframework.com.cop.stf.web;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.Collections;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;

import egovframework.com.cmm.EgovMessageSource;
import egovframework.com.cop.bbs.service.EgovBBSSatisfactionService;
import egovframework.com.cop.bbs.service.Satisfaction;
import egovframework.com.cop.bbs.service.SatisfactionVO;
import egovframework.com.utl.sim.service.EgovFileScrty;

class EgovBBSSatisfactionControllerTest {

	private static final String PASSWORD_MESSAGE = "cop.password.not.same.msg";

	@Test
	void updateAnonymousSatisfactionRejectsInvalidPassword() throws Exception {
		EgovBBSSatisfactionController controller = controllerWithStoredPassword("correct");
		RecordingSatisfactionService service = (RecordingSatisfactionService) controller.bbsSatisfactionService;

		Satisfaction satisfaction = satisfactionWithPassword("wrong");
		BindingResult bindingResult = new BeanPropertyBindingResult(satisfaction, "satisfaction");
		ModelMap model = new ModelMap();

		String view = controller.updateAnonymousSatisfaction(new SatisfactionVO(), satisfaction, bindingResult, model);

		assertEquals("forward:/cop/bbs/anonymous/selectBoardArticle.do", view);
		assertEquals(PASSWORD_MESSAGE, model.get("subMsg"));
		assertFalse(service.updateCalled);
	}

	@Test
	void deleteAnonymousSatisfactionTreatsMissingPasswordAsMismatch() throws Exception {
		EgovBBSSatisfactionController controller = controllerWithStoredPassword(null);
		RecordingSatisfactionService service = (RecordingSatisfactionService) controller.bbsSatisfactionService;

		SatisfactionVO satisfactionVO = new SatisfactionVO();
		satisfactionVO.setStsfdgNo("SATISFACTION_1");
		satisfactionVO.setConfirmPassword("any-password");
		ModelMap model = new ModelMap();

		String view = controller.deleteAnonymousSatisfaction(satisfactionVO, new Satisfaction(), model);

		assertEquals("forward:/cop/bbs/anonymous/selectArticleDetail.do", view);
		assertEquals(PASSWORD_MESSAGE, model.get("subMsg"));
		assertFalse(service.deleteCalled);
	}

	private EgovBBSSatisfactionController controllerWithStoredPassword(String rawPassword) throws Exception {
		EgovBBSSatisfactionController controller = new EgovBBSSatisfactionController();
		controller.bbsSatisfactionService = new RecordingSatisfactionService(rawPassword);
		controller.egovMessageSource = new TestMessageSource();
		return controller;
	}

	private Satisfaction satisfactionWithPassword(String password) {
		Satisfaction satisfaction = new Satisfaction();
		satisfaction.setStsfdgNo("SATISFACTION_1");
		satisfaction.setWrterNm("writer");
		satisfaction.setStsfdgPassword(password);
		satisfaction.setStsfdgCn("content");
		satisfaction.setStsfdg(5);
		return satisfaction;
	}

	private static final class TestMessageSource extends EgovMessageSource {
		@Override
		public String getMessage(String code) {
			return code;
		}
	}

	private static final class RecordingSatisfactionService implements EgovBBSSatisfactionService {

		private final String storedPassword;
		private boolean updateCalled;
		private boolean deleteCalled;

		private RecordingSatisfactionService(String rawPassword) throws Exception {
			this.storedPassword = rawPassword == null ? null
					: EgovFileScrty.encryptPassword(rawPassword, "SATISFACTION_1");
		}

		@Override
		public boolean canUseSatisfaction(String bbsId) throws Exception {
			return true;
		}

		@Override
		public Map<String, Object> selectSatisfactionList(SatisfactionVO satisfactionVO) throws Exception {
			return Collections.emptyMap();
		}

		@Override
		public void insertSatisfaction(Satisfaction satisfaction) throws Exception {
		}

		@Override
		public void deleteSatisfaction(SatisfactionVO satisfactionVO) throws Exception {
			deleteCalled = true;
		}

		@Override
		public Satisfaction selectSatisfaction(SatisfactionVO satisfactionVO) throws Exception {
			return null;
		}

		@Override
		public void updateSatisfaction(Satisfaction satisfaction) throws Exception {
			updateCalled = true;
		}

		@Override
		public String getSatisfactionPassword(Satisfaction satisfaction) throws Exception {
			return storedPassword;
		}
	}
}
