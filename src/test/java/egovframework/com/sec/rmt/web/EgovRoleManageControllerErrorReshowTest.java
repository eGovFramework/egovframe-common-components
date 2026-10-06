package egovframework.com.sec.rmt.web;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;

import egovframework.com.sec.rmt.service.EgovRoleManageService;
import egovframework.com.sec.rmt.service.RoleManage;
import egovframework.com.sec.rmt.service.RoleManageVO;

/**
 * 롤 수정({@link EgovRoleManageController#updateRole})의 검증 실패 분기가, 형제 상세조회
 * ({@code selectRole})처럼 재표시 폼이 참조하는 {@code roleManageVO}(목록 검색조건)를 model에
 * 복원하는지 검증한다.
 *
 * <p>재표시 JSP({@code EgovRoleUpdate.jsp})는 검색조건 유지용 hidden {@code searchCondition}·
 * {@code searchKeyword}·{@code pageIndex}를 모두 {@code ${roleManageVO...}}에서 읽는다.
 * 검증 실패 분기가 {@code roleManageVO}를 복원하지 않으면 목록에서 검색해 들어온 수정 화면이
 * 검증 오류로 재표시될 때 검색조건이 사라진다.</p>
 */
class EgovRoleManageControllerErrorReshowTest {

	private static final class StubRoleService implements EgovRoleManageService {
		@Override
		public RoleManageVO selectRole(RoleManageVO roleManageVO) {
			throw new UnsupportedOperationException();
		}

		@Override
		public List<RoleManageVO> selectRoleList(RoleManageVO roleManageVO) {
			throw new UnsupportedOperationException();
		}

		@Override
		public void deleteRole(RoleManage roleManage) {
			throw new UnsupportedOperationException();
		}

		@Override
		public void updateRole(RoleManage roleManage) {
			throw new UnsupportedOperationException();
		}

		@Override
		public RoleManageVO insertRole(RoleManage roleManage, RoleManageVO roleManageVO) {
			throw new UnsupportedOperationException();
		}

		@Override
		public int selectRoleListTotCnt(RoleManageVO roleManageVO) {
			throw new UnsupportedOperationException();
		}

		@Override
		public List<RoleManageVO> selectRoleAllList(RoleManageVO roleManageVO) {
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

	/** getCmmCodeDetailList가 부르는 selectCmmCodeDetail만 빈 목록으로 응답하는 최소 프록시. */
	private static egovframework.com.cmm.service.EgovCmmUseService noopCmmUseService() {
		return (egovframework.com.cmm.service.EgovCmmUseService) java.lang.reflect.Proxy.newProxyInstance(
				EgovRoleManageControllerErrorReshowTest.class.getClassLoader(),
				new Class<?>[] { egovframework.com.cmm.service.EgovCmmUseService.class },
				(proxy, method, args) -> List.of());
	}

	private EgovRoleManageController newControllerWithStub() {
		EgovRoleManageController controller = new EgovRoleManageController();
		setPrivateField(controller, "egovRoleManageService", new StubRoleService());
		setPrivateField(controller, "egovCmmUseService", noopCmmUseService());
		return controller;
	}

	private BindingResult failingBindingResult(RoleManage target) {
		BindingResult bindingResult = new BeanPropertyBindingResult(target, "roleManage");
		bindingResult.reject("validation.error");
		return bindingResult;
	}

	@Test
	void updateRole_restoresRoleManageVOOnValidationError() throws Exception {
		EgovRoleManageController controller = newControllerWithStub();
		RoleManage submitted = new RoleManage();
		RoleManageVO searchVO = new RoleManageVO();
		searchVO.setSearchCondition("roleNm");
		searchVO.setSearchKeyword("admin");
		ExtendedModelMap model = new ExtendedModelMap();

		String view = controller.updateRole(submitted, failingBindingResult(submitted), searchVO, model);

		assertEquals("egovframework/com/sec/rmt/EgovRoleUpdate", view);
		assertNotNull(model.get("roleManageVO"),
				"검증 실패 재표시 시 roleManageVO가 model에 있어야 목록 검색조건(searchCondition·searchKeyword)이 보존된다");
		assertEquals("admin", ((RoleManageVO) model.get("roleManageVO")).getSearchKeyword());
	}
}
