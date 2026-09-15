package egovframework.com.cop.smt.mtm.web;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BeanPropertyBindingResult;

import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.service.EgovUserDetailsService;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.com.cop.smt.mtm.service.EgovMemoTodoService;
import egovframework.com.cop.smt.mtm.service.MemoTodo;
import egovframework.com.cop.smt.mtm.service.MemoTodoVO;

class EgovMemoTodoControllerMissingTodoTest {

	private static final String OWNER = "USRCNFRM_00000000001";
	private static final String ADMIN = "USRCNFRM_00000000002";
	private EgovUserDetailsService previousUserDetailsService;
	private EgovMemoTodoController controller;
	private StubMemoTodoService service;

	@BeforeEach
	void setUp() {
		previousUserDetailsService = new EgovUserDetailsHelper().getEgovUserDetailsService();
		service = new StubMemoTodoService();
		controller = new EgovMemoTodoController();
		controller.memoTodoService = service;
	}

	@AfterEach
	void restoreUserDetailsService() {
		new EgovUserDetailsHelper().setEgovUserDetailsService(previousUserDetailsService);
	}

	@ParameterizedTest(name = "{0}, admin={1}")
	@MethodSource("missingTodoRequests")
	void missingTodoIsRejectedWithoutMutations(Action action, boolean admin) {
		bindUser(admin);
		MemoTodoVO request = request();
		ModelMap model = new ModelMap();

		IllegalStateException error = assertThrows(IllegalStateException.class,
				() -> invoke(action, request, model));

		assertEquals("권한이 없습니다.", error.getMessage());
		assertEquals(1, service.selectCount);
		assertEquals(request.getTodoId(), service.selectedId);
		assertEquals(0, service.updateCount);
		assertEquals(0, service.deleteCount);
		assertEquals(0, service.insertCount);
	}

	@ParameterizedTest
	@ValueSource(booleans = { false, true })
	void existingTodoDetailIsAvailableToOwnerAndAdmin(boolean admin) throws Exception {
		bindUser(admin);
		service.stored = storedTodo();
		ModelMap model = new ModelMap();

		String view = controller.selectMemoTodo(request(), model);

		assertEquals("egovframework/com/cop/smt/mtm/EgovMemoTodoDetail", view);
		assertSame(service.stored, model.get("memoTodo"));
	}

	@ParameterizedTest
	@ValueSource(booleans = { false, true })
	void validationErrorsOnExistingTodoKeepFormAndTimeChoices(boolean admin) throws Exception {
		bindUser(admin);
		service.stored = storedTodo();
		ModelMap model = new ModelMap();

		String view = invoke(Action.INVALID_UPDATE, request(), model);

		assertEquals("egovframework/com/cop/smt/mtm/EgovMemoTodoUpdt", view);
		assertSame(service.stored, model.get("memoTodo"));
		assertEquals(24, ((List<?>) model.get("todoBeginHour")).size());
		assertEquals(60, ((List<?>) model.get("todoBeginMin")).size());
		assertEquals(24, ((List<?>) model.get("todoEndHour")).size());
		assertEquals(60, ((List<?>) model.get("todoEndMin")).size());
		assertEquals(0, service.updateCount);
		assertEquals(0, service.deleteCount);
	}

	@Test
	void adminCanStillDeleteAnExistingTodoOwnedByAnotherUser() throws Exception {
		bindUser(true);
		service.stored = storedTodo();

		assertEquals("forward:/cop/smt/mtm/selectMemoTodoList.do",
				controller.deleteMemoTodo(request(), new ModelMap()));
		assertEquals(1, service.deleteCount);
	}

	private String invoke(Action action, MemoTodoVO request, ModelMap model) throws Exception {
		BeanPropertyBindingResult binding = new BeanPropertyBindingResult(request, "memoTodoVO");
		if (action == Action.INVALID_UPDATE) {
			binding.reject("errors.required");
		}
		switch (action) {
		case DETAIL:
			return controller.selectMemoTodo(request, model);
		case MODIFY_FORM:
			return controller.modifyMemoTodo(request, binding, model);
		case UPDATE:
		case INVALID_UPDATE:
			return controller.updateMemoTodo(request, binding, model);
		case DELETE:
			return controller.deleteMemoTodo(request, model);
		default:
			throw new AssertionError(action);
		}
	}

	static Stream<Arguments> missingTodoRequests() {
		return Arrays.stream(Action.values())
				.flatMap(action -> Stream.of(Arguments.of(action, false), Arguments.of(action, true)));
	}

	private MemoTodoVO request() {
		MemoTodoVO request = new MemoTodoVO();
		request.setTodoId("999999999");
		request.setTodoDe("2026-09-09");
		request.setTodoBeginHour("09");
		request.setTodoBeginMin("30");
		request.setTodoEndHour("10");
		request.setTodoEndMin("30");
		return request;
	}

	private MemoTodoVO storedTodo() {
		MemoTodoVO stored = new MemoTodoVO();
		stored.setTodoId("999999999");
		stored.setFrstRegisterId(OWNER);
		stored.setWrterId(OWNER);
		return stored;
	}

	private void bindUser(boolean admin) {
		LoginVO user = new LoginVO();
		user.setUniqId(admin ? ADMIN : OWNER);
		new EgovUserDetailsHelper().setEgovUserDetailsService(new EgovUserDetailsService() {
			@Override
			public Object getAuthenticatedUser() {
				return user;
			}

			@Override
			public List<String> getAuthorities() {
				return admin ? List.of("ROLE_ADMIN") : List.of();
			}

			@Override
			public Boolean isAuthenticated() {
				return Boolean.TRUE;
			}
		});
	}

	private enum Action {
		DETAIL, MODIFY_FORM, UPDATE, INVALID_UPDATE, DELETE
	}

	private static final class StubMemoTodoService implements EgovMemoTodoService {
		private MemoTodoVO stored;
		private int selectCount;
		private String selectedId;
		private int updateCount;
		private int deleteCount;
		private int insertCount;

		@Override
		public MemoTodoVO selectMemoTodo(MemoTodoVO request) {
			selectCount++;
			selectedId = request.getTodoId();
			return stored;
		}

		@Override
		public void updateMemoTodo(MemoTodo request) {
			updateCount++;
		}

		@Override
		public void deleteMemoTodo(MemoTodo request) {
			deleteCount++;
		}

		@Override
		public void insertMemoTodo(MemoTodo request) {
			insertCount++;
		}

		@Override
		public Map<String, Object> selectMemoTodoList(MemoTodoVO request) {
			throw new UnsupportedOperationException();
		}

		@Override
		public List<MemoTodoVO> selectMemoTodoListToday(MemoTodoVO request) {
			throw new UnsupportedOperationException();
		}
	}
}
