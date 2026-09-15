package egovframework.com.uss.ion.rss.web;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.lang.reflect.Field;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;

import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.service.EgovUserDetailsService;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.com.uss.ion.rss.service.EgovRssTagManageService;
import egovframework.com.uss.ion.rss.service.RssManage;

/**
 * RSS태그관리 수정·등록의 관리자 검증 회귀 테스트.
 *
 * 같은 리소스를 삭제하는 두 경로(listRssTagManage.do·detailRssTagManage.do의 cmd=del)는
 * ROLE_ADMIN을 요구하는데, 수정(updtRssTagManage.do)·등록(insertRssTagManage.do)은
 * 로그인 여부만 확인하고 관리자 검증이 없었다.
 */
class EgovRssTagManageControllerAdminCheckTest {

	private static final class StubService implements EgovRssTagManageService {
		private boolean updateCalled = false;
		private boolean insertCalled = false;

		@Override
		public List<?> selectRssTagManageTableList() {
			return List.of();
		}

		@Override
		public List<?> selectRssTagManageTableColumnList(java.util.Map<?, ?> map) {
			throw new UnsupportedOperationException();
		}

		@Override
		public List<?> selectRssTagManageList(RssManage rssManage) {
			throw new UnsupportedOperationException();
		}

		@Override
		public int selectRssTagManageListCnt(RssManage rssManage) {
			throw new UnsupportedOperationException();
		}

		@Override
		public RssManage selectRssTagManageDetail(RssManage rssManage) {
			return rssManage;
		}

		@Override
		public void insertRssTagManage(RssManage rssManage) {
			insertCalled = true;
		}

		@Override
		public void updateRssTagManage(RssManage rssManage) {
			updateCalled = true;
		}

		@Override
		public void deleteRssTagManage(RssManage rssManage) {
			throw new UnsupportedOperationException();
		}
	}

	private static void bindLoginUser(List<String> authorities) {
		LoginVO login = new LoginVO();
		login.setUniqId("USRCNFRM_00000000009");
		EgovUserDetailsService stub = new EgovUserDetailsService() {
			@Override
			public Object getAuthenticatedUser() {
				return login;
			}

			@Override
			public List<String> getAuthorities() {
				return authorities;
			}

			@Override
			public Boolean isAuthenticated() {
				return Boolean.TRUE;
			}
		};
		new EgovUserDetailsHelper().setEgovUserDetailsService(stub);
	}

	private static EgovRssTagManageController controllerWith(StubService service) throws Exception {
		EgovRssTagManageController controller = new EgovRssTagManageController();
		Field serviceField = EgovRssTagManageController.class.getDeclaredField("egovRssManageService");
		serviceField.setAccessible(true);
		serviceField.set(controller, service);

		Field messageSourceField = EgovRssTagManageController.class.getDeclaredField("egovMessageSource");
		messageSourceField.setAccessible(true);
		messageSourceField.set(controller, new egovframework.com.cmm.EgovMessageSource() {
			@Override
			public String getMessage(String code) {
				return code;
			}
		});
		return controller;
	}

	// ---- updtRssTagManage.do (수정) ----

	@Test
	void updateByNonAdminIsRejected() throws Exception {
		StubService service = new StubService();
		EgovRssTagManageController controller = controllerWith(service);
		bindLoginUser(List.of());

		RssManage rssManage = new RssManage();
		BindingResult bindingResult = new BeanPropertyBindingResult(rssManage, "rssManage");
		java.util.Map<String, String> commandMap = java.util.Map.of("cmd", "save");
		ModelMap model = new ModelMap();

		assertThrows(IllegalStateException.class,
				() -> controller.EgovRssTagManageModify(commandMap, rssManage, bindingResult, model),
				"A logged-in non-admin must not be able to modify the site-wide RSS tag configuration.");
		assertFalse(service.updateCalled, "updateRssTagManage must not be reached by a non-admin.");
	}

	@Test
	void updateByAdminSucceeds() throws Exception {
		StubService service = new StubService();
		EgovRssTagManageController controller = controllerWith(service);
		bindLoginUser(List.of("ROLE_ADMIN"));

		RssManage rssManage = new RssManage();
		BindingResult bindingResult = new BeanPropertyBindingResult(rssManage, "rssManage");
		java.util.Map<String, String> commandMap = java.util.Map.of("cmd", "save");
		ModelMap model = new ModelMap();

		assertDoesNotThrow(() -> controller.EgovRssTagManageModify(commandMap, rssManage, bindingResult, model));
		assertTrue(service.updateCalled, "An admin must be able to modify the RSS tag configuration.");
	}

	// ---- insertRssTagManage.do (등록) ----

	@Test
	void insertByNonAdminIsRejected() throws Exception {
		StubService service = new StubService();
		EgovRssTagManageController controller = controllerWith(service);
		bindLoginUser(List.of());

		RssManage rssManage = new RssManage();
		BindingResult bindingResult = new BeanPropertyBindingResult(rssManage, "rssManage");
		ModelMap model = new ModelMap();

		assertThrows(IllegalStateException.class,
				() -> controller.insertRssTagManage(rssManage, bindingResult, model),
				"A logged-in non-admin must not be able to create a new site-wide RSS tag configuration.");
		assertFalse(service.insertCalled, "insertRssTagManage must not be reached by a non-admin.");
	}

	@Test
	void insertByAdminSucceeds() throws Exception {
		StubService service = new StubService();
		EgovRssTagManageController controller = controllerWith(service);
		bindLoginUser(List.of("ROLE_ADMIN"));

		RssManage rssManage = new RssManage();
		BindingResult bindingResult = new BeanPropertyBindingResult(rssManage, "rssManage");
		ModelMap model = new ModelMap();

		assertDoesNotThrow(() -> controller.insertRssTagManage(rssManage, bindingResult, model));
		assertTrue(service.insertCalled, "An admin must be able to create a new RSS tag configuration.");
	}
}
