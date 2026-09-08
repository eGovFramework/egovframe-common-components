package egovframework.com.sym.mnu.mpm.web;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.ui.ModelMap;

import egovframework.com.cmm.ComDefaultVO;
import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.service.EgovUserDetailsService;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.com.sym.mnu.mpm.service.EgovMenuManageService;
import egovframework.com.sym.mnu.mpm.service.MenuManageVO;
import org.egovframe.rte.psl.dataaccess.util.EgovMap;

/**
 * 메뉴 다건 삭제의 하위 메뉴 검사 회귀 테스트.
 *
 * 단건 삭제 deleteMenuManage 는 자기가 지울 메뉴로 selectUpperMenuNoByPk 를 물어 하위 메뉴가
 * 있으면 거절한다. 다건 삭제 deleteMenuManageList 는 체크된 목록의 첫 항목으로만 같은 검사를
 * 하고, 통과하면 체크된 전체를 삭제한다.
 */
class EgovMenuManageControllerBatchDeleteTest {

	/** 하위 메뉴를 가진 메뉴번호 하나만 1 을 돌려주고, 삭제 호출 인자를 기록하는 스텁. */
	private static final class StubService implements EgovMenuManageService {
		private final int menuNoWithChild;
		private String lastDeletedList;

		private StubService(int menuNoWithChild) {
			this.menuNoWithChild = menuNoWithChild;
		}

		@Override
		public int selectUpperMenuNoByPk(MenuManageVO vo) {
			return vo.getMenuNo() == menuNoWithChild ? 1 : 0;
		}

		@Override
		public void deleteMenuManageList(String checkedMenuNoForDel) {
			lastDeletedList = checkedMenuNoForDel;
		}

		@Override
		public MenuManageVO selectMenuManage(ComDefaultVO vo) {
			throw new UnsupportedOperationException();
		}

		@Override
		public List<EgovMap> selectMenuManageList(ComDefaultVO vo) {
			throw new UnsupportedOperationException();
		}

		@Override
		public int selectMenuManageListTotCnt(ComDefaultVO vo) {
			throw new UnsupportedOperationException();
		}

		@Override
		public int selectMenuNoByPk(MenuManageVO vo) {
			throw new UnsupportedOperationException();
		}

		@Override
		public void insertMenuManage(MenuManageVO vo) {
			throw new UnsupportedOperationException();
		}

		@Override
		public void updateMenuManage(MenuManageVO vo) {
			throw new UnsupportedOperationException();
		}

		@Override
		public void deleteMenuManage(MenuManageVO vo) {
			throw new UnsupportedOperationException();
		}

		@Override
		public List<EgovMap> selectMenuList() {
			throw new UnsupportedOperationException();
		}

		@Override
		public List<?> selectMainMenuHead(MenuManageVO vo) {
			throw new UnsupportedOperationException();
		}

		@Override
		public List<?> selectMainMenuLeft(MenuManageVO vo) {
			throw new UnsupportedOperationException();
		}

		@Override
		public String selectLastMenuURL(int iMenuNo, String sUniqId) {
			throw new UnsupportedOperationException();
		}

		@Override
		public boolean menuBndeAllDelete() {
			throw new UnsupportedOperationException();
		}

		@Override
		public String menuBndeRegist(MenuManageVO vo, InputStream inputStream) {
			throw new UnsupportedOperationException();
		}
	}

	private static void bindLoginUser() {
		LoginVO login = new LoginVO();
		login.setUniqId("USRCNFRM_00000000001");
		EgovUserDetailsService stub = new EgovUserDetailsService() {
			@Override
			public Object getAuthenticatedUser() {
				return login;
			}

			@Override
			public List<String> getAuthorities() {
				return List.of("ROLE_ADMIN");
			}

			@Override
			public Boolean isAuthenticated() {
				return Boolean.TRUE;
			}
		};
		new EgovUserDetailsHelper().setEgovUserDetailsService(stub);
	}

	private static EgovMenuManageController controllerWith(StubService service) throws Exception {
		EgovMenuManageController controller = new EgovMenuManageController();
		Field serviceField = EgovMenuManageController.class.getDeclaredField("menuManageService");
		serviceField.setAccessible(true);
		serviceField.set(controller, service);

		Field messageSourceField = EgovMenuManageController.class.getDeclaredField("egovMessageSource");
		messageSourceField.setAccessible(true);
		messageSourceField.set(controller, new egovframework.com.cmm.EgovMessageSource() {
			@Override
			public String getMessage(String code) {
				return code;
			}
		});
		return controller;
	}

	@Test
	void batchDeleteRejectsWhenAnyCheckedMenuHasChildren() throws Exception {
		StubService service = new StubService(20); // 두 번째 항목이 하위 메뉴를 갖는다
		EgovMenuManageController controller = controllerWith(service);
		bindLoginUser();
		ModelMap model = new ModelMap();

		controller.deleteMenuManageList("10,20,30", new MenuManageVO(), model);

		assertNull(service.lastDeletedList,
				"체크된 항목 중 하나라도 하위 메뉴를 가지면 다건 삭제를 실행하지 않아야 한다.");
		assertEquals("fail.common.delete.upperMenuExist", model.get("resultMsg"),
				"단건 삭제와 같은 사유 메시지로 거절해야 한다.");
	}

	@Test
	void batchDeleteStillRejectsWhenTheFirstCheckedMenuHasChildren() throws Exception {
		StubService service = new StubService(10); // 첫 항목이 하위 메뉴를 갖는다 (기존에도 잡히던 경우)
		EgovMenuManageController controller = controllerWith(service);
		bindLoginUser();
		ModelMap model = new ModelMap();

		controller.deleteMenuManageList("10,20,30", new MenuManageVO(), model);

		assertNull(service.lastDeletedList, "첫 항목이 하위 메뉴를 가지면 기존과 같이 거절해야 한다.");
	}

	@Test
	void batchDeleteProceedsWhenNoCheckedMenuHasChildren() throws Exception {
		StubService service = new StubService(-1); // 아무도 하위 메뉴가 없다
		EgovMenuManageController controller = controllerWith(service);
		bindLoginUser();
		ModelMap model = new ModelMap();

		controller.deleteMenuManageList("10,20,30", new MenuManageVO(), model);

		assertEquals("10,20,30", service.lastDeletedList,
				"하위 메뉴가 없으면 체크된 전체를 그대로 삭제해야 한다.");
	}
}
