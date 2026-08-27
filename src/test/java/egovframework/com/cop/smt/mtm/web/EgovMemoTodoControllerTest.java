package egovframework.com.cop.smt.mtm.web;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;

import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.service.EgovUserDetailsService;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.com.cop.smt.mtm.service.EgovMemoTodoService;
import egovframework.com.cop.smt.mtm.service.MemoTodo;
import egovframework.com.cop.smt.mtm.service.MemoTodoVO;

/**
 * 서버 검증 실패로 등록/수정 화면을 다시 그릴 때 시/분 선택목록이 모델에 남는지 확인한다.
 */
class EgovMemoTodoControllerTest {

	private static final String UPDT_VIEW = "egovframework/com/cop/smt/mtm/EgovMemoTodoUpdt";
	private static final String REGIST_VIEW = "egovframework/com/cop/smt/mtm/EgovMemoTodoRegist";

	private EgovMemoTodoController controller;

	@BeforeEach
	void setUp() {
		controller = new EgovMemoTodoController();
		controller.memoTodoService = new StubMemoTodoService();
		new EgovUserDetailsHelper().setEgovUserDetailsService(new StubUserDetailsService());
	}

	@AfterEach
	void tearDown() {
		new EgovUserDetailsHelper().setEgovUserDetailsService(null);
	}

	@Test
	void updateMemoTodoKeepsTimeCodeListsWhenValidationFails() throws Exception {
		ModelMap model = new ModelMap();

		assertEquals(UPDT_VIEW, controller.updateMemoTodo(new MemoTodoVO(), rejected(), model));

		assertTimeCodeLists(model);
	}

	@Test
	void insertMemoTodoKeepsTimeCodeListsWhenValidationFails() throws Exception {
		ModelMap model = new ModelMap();

		assertEquals(REGIST_VIEW, controller.insertMemoTodo(new MemoTodoVO(), rejected(), model));

		assertTimeCodeLists(model);
	}

	private BindingResult rejected() {
		BindingResult bindingResult = new BeanPropertyBindingResult(new MemoTodoVO(), "memoTodoVO");
		bindingResult.reject("errors.required");
		return bindingResult;
	}

	private void assertTimeCodeLists(ModelMap model) {
		assertCodeList(model, "todoBeginHour", 24);
		assertCodeList(model, "todoBeginMin", 60);
		assertCodeList(model, "todoEndHour", 24);
		assertCodeList(model, "todoEndMin", 60);
	}

	private void assertCodeList(ModelMap model, String attributeName, int expectedSize) {
		Object codeList = model.get(attributeName);
		assertNotNull(codeList, attributeName + " 선택목록이 모델에 없어 화면의 select가 비어 그려진다.");
		assertEquals(expectedSize, ((List<?>) codeList).size(), attributeName + " 선택목록 건수");
	}

	private static class StubMemoTodoService implements EgovMemoTodoService {

		@Override
		public Map<String, Object> selectMemoTodoList(MemoTodoVO memoTodoVO) {
			return Collections.emptyMap();
		}

		@Override
		public MemoTodoVO selectMemoTodo(MemoTodoVO memoTodoVO) {
			return new MemoTodoVO();
		}

		@Override
		public void updateMemoTodo(MemoTodo memoTodo) {
			// 검증 실패 분기에서는 호출되지 않는다.
		}

		@Override
		public void insertMemoTodo(MemoTodo memoTodo) {
			// 검증 실패 분기에서는 호출되지 않는다.
		}

		@Override
		public void deleteMemoTodo(MemoTodo memoTodo) {
			// 검증 실패 분기에서는 호출되지 않는다.
		}

		@Override
		public List<MemoTodoVO> selectMemoTodoListToday(MemoTodoVO memoTodoVO) {
			return Collections.emptyList();
		}
	}

	private static class StubUserDetailsService implements EgovUserDetailsService {

		@Override
		public Object getAuthenticatedUser() {
			return new LoginVO();
		}

		@Override
		public List<String> getAuthorities() {
			return Collections.emptyList();
		}

		@Override
		public Boolean isAuthenticated() {
			return Boolean.TRUE;
		}
	}
}
