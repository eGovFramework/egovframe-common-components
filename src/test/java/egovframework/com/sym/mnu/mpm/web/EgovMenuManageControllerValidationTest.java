package egovframework.com.sym.mnu.mpm.web;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;

import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.service.EgovUserDetailsService;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.com.sym.mnu.mpm.service.MenuManageVO;

/**
 * 메뉴 수정 검증 실패 시 폼 재표시 경로 회귀 테스트.
 *
 * updateMenuManage는 검증 실패 시 selectMenuManage로 forward했는데, 그 핸들러는 수정 폼이
 * 보내지 않는 req_menuNo를 필수 파라미터로 요구한다. 그 결과 오류 재표시 대신
 * MissingServletRequestParameterException(400)으로 끊겼다. 형제 핸들러들처럼 뷰 이름을 직접
 * 반환하도록 바꿔, 파라미터 없이도 입력값·오류가 담긴 폼이 다시 그려지게 한다.
 */
class EgovMenuManageControllerValidationTest {

	@Test
	void updateMenuManage_validationFailure_returnsFormViewNotForward() throws Exception {
		bindLoginUser("USRCNFRM_00000000001");

		EgovMenuManageController controller = new EgovMenuManageController();

		MenuManageVO menuManageVO = new MenuManageVO();
		BindingResult bindingResult = new BeanPropertyBindingResult(menuManageVO, "menuManageVO");
		bindingResult.rejectValue("menuNm", "Size", "메뉴명은 50자 이내여야 합니다.");

		String view = controller.updateMenuManage(menuManageVO, bindingResult, new ModelMap());

		assertEquals("egovframework/com/sym/mnu/mpm/EgovMenuDetailSelectUpdt", view,
				"검증 실패 시 req_menuNo를 요구하는 forward가 아니라 수정 폼 뷰를 직접 반환해야 한다");
	}

	private static void bindLoginUser(String uniqId) {
		LoginVO login = new LoginVO();
		login.setUniqId(uniqId);
		EgovUserDetailsService stub = new EgovUserDetailsService() {
			@Override
			public Object getAuthenticatedUser() {
				return login;
			}

			@Override
			public List<String> getAuthorities() {
				return List.of();
			}

			@Override
			public Boolean isAuthenticated() {
				return Boolean.TRUE;
			}
		};
		new EgovUserDetailsHelper().setEgovUserDetailsService(stub);
	}
}
