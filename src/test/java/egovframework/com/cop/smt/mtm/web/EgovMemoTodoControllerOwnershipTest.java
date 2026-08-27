package egovframework.com.cop.smt.mtm.web;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;

import egovframework.com.cmm.EgovMessageSource;
import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.service.EgovUserDetailsService;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.com.cop.smt.mtm.service.EgovMemoTodoService;
import egovframework.com.cop.smt.mtm.service.MemoTodo;
import egovframework.com.cop.smt.mtm.service.MemoTodoVO;

/**
 * modifyMemoTodo·updateMemoTodo·deleteMemoTodo의 소유권 검증 회귀 테스트.
 *
 * 로그인 사용자가 자신이 등록하지 않은 메모할일을 열람(수정폼 진입)·수정·삭제하려 하면
 * egovAssertAdminOrOwner가 IllegalStateException을 던져 차단해야 한다(IDOR 방지).
 * 수정 전 코드는 인증 여부만 확인하고 소유자를 대조하지 않아, 아래 attacker 테스트가 실패한다.
 * ROLE_ADMIN 보유자는 소유자가 아니어도 통과해야 한다(selectMemoTodo의 기존 KISA 조치와 동일 관례).
 */
class EgovMemoTodoControllerOwnershipTest {

	private static final String OWNER = "USRCNFRM_00000000001";
	private static final String ATTACKER = "USRCNFRM_00000000009";

	/** selectMemoTodo는 저장된 소유자를 돌려주고, update/delete 호출 여부를 기록하는 스텁. */
	private static final class StubService implements EgovMemoTodoService {
		private final MemoTodoVO stored;
		private boolean updateCalled = false;
		private boolean deleteCalled = false;

		StubService(String ownerUniqId) {
			this.stored = new MemoTodoVO();
			this.stored.setFrstRegisterId(ownerUniqId);
		}

		@Override
		public MemoTodoVO selectMemoTodo(MemoTodoVO memoTodoVO) {
			return stored;
		}

		@Override
		public void updateMemoTodo(MemoTodo memoTodo) {
			updateCalled = true;
		}

		@Override
		public void deleteMemoTodo(MemoTodo memoTodo) {
			deleteCalled = true;
		}

		@Override
		public Map<String, Object> selectMemoTodoList(MemoTodoVO memoTodoVO) {
			throw new UnsupportedOperationException();
		}

		@Override
		public void insertMemoTodo(MemoTodo memoTodo) {
			throw new UnsupportedOperationException();
		}

		@Override
		public List<MemoTodoVO> selectMemoTodoListToday(MemoTodoVO memoTodoVO) {
			throw new UnsupportedOperationException();
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

	private static EgovMemoTodoController controllerWith(StubService service) {
		EgovMemoTodoController controller = new EgovMemoTodoController();
		controller.memoTodoService = service;
		controller.egovMessageSource = new EgovMessageSource() {
			@Override
			public String getMessage(String code) {
				return code;
			}
		};
		return controller;
	}

	private static MemoTodoVO requestFor(String todoId) {
		MemoTodoVO memoTodoVO = new MemoTodoVO();
		memoTodoVO.setTodoId(todoId);
		memoTodoVO.setTodoNm("edited");
		return memoTodoVO;
	}

	// ---- modifyMemoTodo (수정폼 진입, 정보노출 IDOR) ----

	@Test
	void modifyByNonOwnerIsRejected() {
		StubService service = new StubService(OWNER);
		EgovMemoTodoController controller = controllerWith(service);
		bindLoginUser(ATTACKER, List.of());

		MemoTodoVO memoTodoVO = requestFor("8000001");
		BindingResult bindingResult = new BeanPropertyBindingResult(memoTodoVO, "memoTodoVO");
		ModelMap model = new ModelMap();

		assertThrows(IllegalStateException.class,
				() -> controller.modifyMemoTodo(memoTodoVO, bindingResult, model),
				"A logged-in non-owner must not be able to open another member's memo/todo edit form.");
	}

	@Test
	void modifyByOwnerSucceeds() throws Exception {
		StubService service = new StubService(OWNER);
		EgovMemoTodoController controller = controllerWith(service);
		bindLoginUser(OWNER, List.of());

		MemoTodoVO memoTodoVO = requestFor("8000001");
		BindingResult bindingResult = new BeanPropertyBindingResult(memoTodoVO, "memoTodoVO");
		ModelMap model = new ModelMap();

		String view = controller.modifyMemoTodo(memoTodoVO, bindingResult, model);
		assertTrue(view.contains("EgovMemoTodoUpdt"));
	}

	@Test
	void modifyByAdminSucceedsEvenWhenNotOwner() throws Exception {
		StubService service = new StubService(OWNER);
		EgovMemoTodoController controller = controllerWith(service);
		bindLoginUser(ATTACKER, List.of("ROLE_ADMIN"));

		MemoTodoVO memoTodoVO = requestFor("8000001");
		BindingResult bindingResult = new BeanPropertyBindingResult(memoTodoVO, "memoTodoVO");
		ModelMap model = new ModelMap();

		String view = controller.modifyMemoTodo(memoTodoVO, bindingResult, model);
		assertTrue(view.contains("EgovMemoTodoUpdt"));
	}

	// ---- updateMemoTodo ----

	@Test
	void updateByNonOwnerDoesNotReachTheUpdateService() {
		StubService service = new StubService(OWNER);
		EgovMemoTodoController controller = controllerWith(service);
		bindLoginUser(ATTACKER, List.of());

		MemoTodoVO memoTodoVO = requestFor("8000001");
		BindingResult bindingResult = new BeanPropertyBindingResult(memoTodoVO, "memoTodoVO");
		ModelMap model = new ModelMap();

		assertThrows(IllegalStateException.class,
				() -> controller.updateMemoTodo(memoTodoVO, bindingResult, model),
				"A logged-in non-owner must not be able to update another member's memo/todo.");
		assertTrue(!service.updateCalled, "updateMemoTodo service must not be reached by a non-owner.");
	}

	@Test
	void updateByOwnerReachesTheUpdateService() throws Exception {
		StubService service = new StubService(OWNER);
		EgovMemoTodoController controller = controllerWith(service);
		bindLoginUser(OWNER, List.of());

		MemoTodoVO memoTodoVO = requestFor("8000001");
		BindingResult bindingResult = new BeanPropertyBindingResult(memoTodoVO, "memoTodoVO");
		ModelMap model = new ModelMap();

		controller.updateMemoTodo(memoTodoVO, bindingResult, model);
		assertTrue(service.updateCalled, "The owner must be able to update their own memo/todo.");
	}

	@Test
	void updateByAdminReachesTheUpdateServiceEvenWhenNotOwner() throws Exception {
		StubService service = new StubService(OWNER);
		EgovMemoTodoController controller = controllerWith(service);
		bindLoginUser(ATTACKER, List.of("ROLE_ADMIN"));

		MemoTodoVO memoTodoVO = requestFor("8000001");
		BindingResult bindingResult = new BeanPropertyBindingResult(memoTodoVO, "memoTodoVO");
		ModelMap model = new ModelMap();

		controller.updateMemoTodo(memoTodoVO, bindingResult, model);
		assertTrue(service.updateCalled, "An admin must be able to update any member's memo/todo.");
	}

	// ---- deleteMemoTodo ----

	@Test
	void deleteByNonOwnerDoesNotReachTheDeleteService() {
		StubService service = new StubService(OWNER);
		EgovMemoTodoController controller = controllerWith(service);
		bindLoginUser(ATTACKER, List.of());

		MemoTodoVO memoTodoVO = requestFor("8000001");
		ModelMap model = new ModelMap();

		assertThrows(IllegalStateException.class,
				() -> controller.deleteMemoTodo(memoTodoVO, model),
				"A logged-in non-owner must not be able to delete another member's memo/todo.");
		assertTrue(!service.deleteCalled, "deleteMemoTodo service must not be reached by a non-owner.");
	}

	@Test
	void deleteByOwnerReachesTheDeleteService() throws Exception {
		StubService service = new StubService(OWNER);
		EgovMemoTodoController controller = controllerWith(service);
		bindLoginUser(OWNER, List.of());

		MemoTodoVO memoTodoVO = requestFor("8000001");
		ModelMap model = new ModelMap();

		controller.deleteMemoTodo(memoTodoVO, model);
		assertTrue(service.deleteCalled, "The owner must be able to delete their own memo/todo.");
	}
}
