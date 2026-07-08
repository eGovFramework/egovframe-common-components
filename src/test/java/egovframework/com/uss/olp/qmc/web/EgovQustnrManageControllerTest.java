package egovframework.com.uss.olp.qmc.web;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.egovframe.rte.fdl.property.EgovPropertyService;
import org.egovframe.rte.psl.dataaccess.util.EgovMap;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.ui.ModelMap;

import egovframework.com.cmm.ComDefaultVO;
import egovframework.com.cmm.EgovMessageSource;
import egovframework.com.cmm.service.EgovUserDetailsService;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.com.uss.olp.qmc.service.EgovQustnrManageService;
import egovframework.com.uss.olp.qmc.service.QustnrManageVO;

class EgovQustnrManageControllerTest {

	private final EgovUserDetailsHelper userDetailsHelper = new EgovUserDetailsHelper();

	private EgovUserDetailsService originalUserDetailsService;

	private EgovQustnrManageController controller;

	private RecordingQustnrManageService qustnrManageService;

	@BeforeEach
	void setUp() {
		originalUserDetailsService = userDetailsHelper.getEgovUserDetailsService();
		userDetailsHelper.setEgovUserDetailsService(new FixedUserDetailsService(false));

		controller = new EgovQustnrManageController();
		qustnrManageService = new RecordingQustnrManageService();

		ReflectionTestUtils.setField(controller, "egovMessageSource", new FixedMessageSource());
		ReflectionTestUtils.setField(controller, "egovQustnrManageService", qustnrManageService);
		ReflectionTestUtils.setField(controller, "propertiesService", fixedPropertyService());
	}

	@AfterEach
	void tearDown() {
		userDetailsHelper.setEgovUserDetailsService(originalUserDetailsService);
	}

	@Test
	void listPopupDeleteRequiresAuthentication() throws Exception {
		ModelMap model = new ModelMap();

		String viewName = controller.egovQustnrManageListPopup(new ComDefaultVO(), deleteCommand(),
				new QustnrManageVO(), model);

		assertEquals(0, qustnrManageService.deleteCount);
		assertLoginRedirect(viewName, model);
	}

	@Test
	void listDeleteRequiresAuthentication() throws Exception {
		ModelMap model = new ModelMap();

		String viewName = controller.egovQustnrManageList(new ComDefaultVO(), deleteCommand(), new QustnrManageVO(),
				model);

		assertEquals(0, qustnrManageService.deleteCount);
		assertLoginRedirect(viewName, model);
	}

	@Test
	void detailDeleteRequiresAuthentication() throws Exception {
		ModelMap model = new ModelMap();

		String viewName = controller.egovQustnrManageDetail(new ComDefaultVO(), new QustnrManageVO(), deleteCommand(),
				model);

		assertEquals(0, qustnrManageService.deleteCount);
		assertLoginRedirect(viewName, model);
	}

	private static Map<String, String> deleteCommand() {
		return Collections.singletonMap("cmd", "del");
	}

	private static void assertLoginRedirect(String viewName, ModelMap model) {
		assertEquals("redirect:/uat/uia/egovLoginUsr.do", viewName);
		assertEquals("fail.common.login", model.get("message"));
	}

	private static EgovPropertyService fixedPropertyService() {
		return (EgovPropertyService) java.lang.reflect.Proxy.newProxyInstance(
				EgovPropertyService.class.getClassLoader(),
				new Class<?>[] { EgovPropertyService.class },
				(proxy, method, args) -> {
					Class<?> returnType = method.getReturnType();
					if (returnType == boolean.class) {
						return false;
					}
					if (returnType == int.class) {
						return 10;
					}
					if (returnType == long.class) {
						return 0L;
					}
					if (returnType == float.class) {
						return 0F;
					}
					if (returnType == double.class) {
						return 0D;
					}
					if (returnType == String.class) {
						return "";
					}
					if (returnType == String[].class) {
						return new String[0];
					}
					if (Iterator.class.isAssignableFrom(returnType)) {
						return Collections.emptyIterator();
					}
					return null;
				});
	}

	private static class FixedMessageSource extends EgovMessageSource {

		@Override
		public String getMessage(String code) {
			return code;
		}
	}

	private static class FixedUserDetailsService implements EgovUserDetailsService {

		private final boolean authenticated;

		FixedUserDetailsService(boolean authenticated) {
			this.authenticated = authenticated;
		}

		@Override
		public Object getAuthenticatedUser() {
			return null;
		}

		@Override
		public List<String> getAuthorities() {
			return Collections.emptyList();
		}

		@Override
		public Boolean isAuthenticated() {
			return authenticated;
		}
	}

	private static class RecordingQustnrManageService implements EgovQustnrManageService {

		private int deleteCount;

		@Override
		public List<EgovMap> selectQustnrTmplatManageList(QustnrManageVO qustnrManageVO) {
			return Collections.emptyList();
		}

		@Override
		public List<EgovMap> selectQustnrManageList(ComDefaultVO searchVO) {
			return Collections.emptyList();
		}

		@Override
		public List<EgovMap> selectQustnrManageDetail(QustnrManageVO qustnrManageVO) {
			return Collections.emptyList();
		}

		@Override
		public QustnrManageVO selectQustnrManageDetailModel(QustnrManageVO qustnrManageVO) {
			return new QustnrManageVO();
		}

		@Override
		public int selectQustnrManageListCnt(ComDefaultVO searchVO) {
			return 0;
		}

		@Override
		public void insertQustnrManage(QustnrManageVO qustnrManageVO) {
		}

		@Override
		public void updateQustnrManage(QustnrManageVO qustnrManageVO) {
		}

		@Override
		public void deleteQustnrManage(QustnrManageVO qustnrManageVO) {
			deleteCount++;
		}
	}
}
