package egovframework.com.cop.scp.web;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;

import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.service.EgovUserDetailsService;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.com.cop.scp.service.EgovArticleScrapService;
import egovframework.com.cop.scp.service.Scrap;
import egovframework.com.cop.scp.service.ScrapVO;

/**
 * 스크랩 수정·삭제의 소유권 검증 회귀 테스트.
 *
 * selectArticleScrapDetail·updateArticleScrapView는 본인(FRST_REGISTER_ID) 또는 관리자만
 * 통과시키는데, 실제 변경을 처리하는 updateArticleScrap·deleteArticleScrap은 같은 대조 없이
 * 로그인만 확인하고 바로 실행한다.
 */
class EgovArticleScrapControllerOwnershipTest {

	private static final String OWNER = "USRCNFRM_00000000001";
	private static final String OUTSIDER = "USRCNFRM_00000000009";

	private static final class StubService implements EgovArticleScrapService {
		private final ScrapVO stored;
		private boolean updateCalled = false;
		private boolean deleteCalled = false;

		StubService(String ownerUniqId) {
			this.stored = new ScrapVO();
			this.stored.setScrapId("1");
			this.stored.setFrstRegisterId(ownerUniqId);
		}

		@Override
		public Map<String, Object> selectArticleScrapList(ScrapVO scrapVO) {
			throw new UnsupportedOperationException();
		}

		@Override
		public void insertArticleScrap(Scrap scrap) {
			throw new UnsupportedOperationException();
		}

		@Override
		public ScrapVO selectArticleScrapDetail(ScrapVO scrapVO) {
			return stored;
		}

		@Override
		public void deleteArticleScrap(ScrapVO scrapVO) {
			deleteCalled = true;
		}

		@Override
		public void updateArticleScrap(Scrap scrap) {
			updateCalled = true;
		}
	}

	private static void setPrivateField(Object target, String fieldName, Object value) {
		try {
			java.lang.reflect.Field field = target.getClass().getDeclaredField(fieldName);
			field.setAccessible(true);
			field.set(target, value);
		} catch (ReflectiveOperationException e) {
			throw new IllegalStateException(e);
		}
	}

	private static void bindLoginUser(String uniqId, List<String> authorities) {
		LoginVO login = new LoginVO();
		login.setUniqId(uniqId);
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

	private static EgovArticleScrapController controllerWith(StubService service) {
		EgovArticleScrapController controller = new EgovArticleScrapController();
		setPrivateField(controller, "egovArticleScrapService", service);
		return controller;
	}

	private static ScrapVO requestFor(String scrapId) {
		ScrapVO vo = new ScrapVO();
		vo.setScrapId(scrapId);
		return vo;
	}

	@Test
	void deleteByOutsiderIsRejected() {
		StubService service = new StubService(OWNER);
		EgovArticleScrapController controller = controllerWith(service);
		bindLoginUser(OUTSIDER, List.of());

		ScrapVO vo = requestFor("1");
		Scrap scrap = new Scrap();
		ModelMap model = new ModelMap();

		assertThrows(IllegalStateException.class,
				() -> controller.deleteArticleScrap(vo, scrap, model),
				"본인 소유가 아닌 스크랩은 삭제할 수 없어야 한다.");
		assertFalse(service.deleteCalled, "거부되면 실제 삭제가 실행되지 않아야 한다.");
	}

	@Test
	void deleteByOwnerSucceeds() throws Exception {
		StubService service = new StubService(OWNER);
		EgovArticleScrapController controller = controllerWith(service);
		bindLoginUser(OWNER, List.of());

		ScrapVO vo = requestFor("1");
		Scrap scrap = new Scrap();
		ModelMap model = new ModelMap();

		assertDoesNotThrow(() -> controller.deleteArticleScrap(vo, scrap, model));
		assertTrue(service.deleteCalled);
	}

	@Test
	void deleteByAdminSucceedsEvenWhenNotOwner() throws Exception {
		StubService service = new StubService(OWNER);
		EgovArticleScrapController controller = controllerWith(service);
		bindLoginUser(OUTSIDER, List.of("ROLE_ADMIN"));

		ScrapVO vo = requestFor("1");
		Scrap scrap = new Scrap();
		ModelMap model = new ModelMap();

		assertDoesNotThrow(() -> controller.deleteArticleScrap(vo, scrap, model));
		assertTrue(service.deleteCalled);
	}

	@Test
	void updateByOutsiderIsRejected() {
		StubService service = new StubService(OWNER);
		EgovArticleScrapController controller = controllerWith(service);
		bindLoginUser(OUTSIDER, List.of());

		ScrapVO vo = requestFor("1");
		Scrap scrap = new Scrap();
		BindingResult bindingResult = new BeanPropertyBindingResult(scrap, "Scrap");
		ModelMap model = new ModelMap();

		assertThrows(IllegalStateException.class,
				() -> controller.updateArticleScrap(vo, scrap, bindingResult, model),
				"본인 소유가 아닌 스크랩은 수정할 수 없어야 한다.");
		assertFalse(service.updateCalled, "거부되면 실제 수정이 실행되지 않아야 한다.");
	}

	@Test
	void updateByOwnerSucceeds() throws Exception {
		StubService service = new StubService(OWNER);
		EgovArticleScrapController controller = controllerWith(service);
		bindLoginUser(OWNER, List.of());

		ScrapVO vo = requestFor("1");
		Scrap scrap = new Scrap();
		BindingResult bindingResult = new BeanPropertyBindingResult(scrap, "Scrap");
		ModelMap model = new ModelMap();

		assertDoesNotThrow(() -> controller.updateArticleScrap(vo, scrap, bindingResult, model));
		assertTrue(service.updateCalled);
	}
}
