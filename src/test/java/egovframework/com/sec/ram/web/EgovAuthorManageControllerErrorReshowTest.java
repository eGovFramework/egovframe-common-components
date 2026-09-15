package egovframework.com.sec.ram.web;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;

import egovframework.com.sec.ram.service.AuthorManage;
import egovframework.com.sec.ram.service.AuthorManageVO;
import egovframework.com.sec.ram.service.EgovAuthorManageService;

/**
 * 권한 수정({@link EgovAuthorManageController#updateAuthor})의 검증 실패 분기가, 형제 상세조회
 * ({@code selectAuthor})처럼 재표시 폼이 참조하는 {@code authorManageVO}(목록 검색조건)를 model에
 * 복원하는지 검증한다.
 *
 * <p>재표시 JSP({@code EgovAuthorUpdate.jsp})는 "목록으로" 링크의 쿼리스트링과 hidden
 * {@code searchCondition}·{@code searchKeyword}·{@code pageIndex}를 모두
 * {@code ${authorManageVO...}}에서 읽는다. 검증 실패 분기가 {@code authorManageVO}를 복원하지
 * 않으면 목록에서 검색해 들어온 수정 화면이 검증 오류로 재표시될 때 검색조건이 사라진다.</p>
 */
class EgovAuthorManageControllerErrorReshowTest {

	private static final class StubService implements EgovAuthorManageService {
		@Override
		public java.util.List<AuthorManageVO> selectAuthorAllList(AuthorManageVO authorManageVO) {
			throw new UnsupportedOperationException();
		}

		@Override
		public void deleteAuthor(AuthorManage authorManage) {
			throw new UnsupportedOperationException();
		}

		@Override
		public void insertAuthor(AuthorManage authorManage) {
			throw new UnsupportedOperationException();
		}

		@Override
		public AuthorManageVO selectAuthor(AuthorManageVO authorManageVO) {
			throw new UnsupportedOperationException();
		}

		@Override
		public java.util.List<AuthorManageVO> selectAuthorList(AuthorManageVO authorManageVO) {
			throw new UnsupportedOperationException();
		}

		@Override
		public void updateAuthor(AuthorManage authorManage) {
			throw new UnsupportedOperationException();
		}

		@Override
		public int selectAuthorListTotCnt(AuthorManageVO authorManageVO) {
			throw new UnsupportedOperationException();
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

	private EgovAuthorManageController newControllerWithStub() {
		EgovAuthorManageController controller = new EgovAuthorManageController();
		setPrivateField(controller, "egovAuthorManageService", new StubService());
		return controller;
	}

	private BindingResult failingBindingResult(AuthorManage target) {
		BindingResult bindingResult = new BeanPropertyBindingResult(target, "authorManage");
		bindingResult.reject("validation.error");
		return bindingResult;
	}

	@Test
	void updateAuthor_restoresAuthorManageVOOnValidationError() throws Exception {
		EgovAuthorManageController controller = newControllerWithStub();
		AuthorManage submitted = new AuthorManage();
		AuthorManageVO searchVO = new AuthorManageVO();
		searchVO.setSearchCondition("authorNm");
		searchVO.setSearchKeyword("admin");
		ExtendedModelMap model = new ExtendedModelMap();

		String view = controller.updateAuthor(searchVO, submitted, failingBindingResult(submitted), model);

		assertEquals("egovframework/com/sec/ram/EgovAuthorUpdate", view);
		assertNotNull(model.get("authorManageVO"),
				"검증 실패 재표시 시 authorManageVO가 model에 있어야 목록 검색조건(searchCondition·searchKeyword)이 보존된다");
		assertEquals("admin", ((AuthorManageVO) model.get("authorManageVO")).getSearchKeyword());
	}
}
