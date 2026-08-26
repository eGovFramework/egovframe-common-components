package egovframework.com.uss.olp.qri.web;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.mvc.support.RedirectAttributesModelMap;

import egovframework.com.cmm.ComDefaultVO;
import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.service.EgovUserDetailsService;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.com.uss.olp.qri.service.EgovQustnrRespondInfoService;
import egovframework.com.uss.olp.qri.service.QustnrRespondInfoVO;

/**
 * 설문 응답 수정 처리({@link EgovQustnrRespondInfoController#qustnrRespondInfoModify})의 검증 실패 분기가,
 * 재표시 화면이 참조하는 {@code resultList}를 model에 복원하는지 검증한다.
 *
 * <p>검증 실패 분기가 {@code resultList}를 복원하지 않으면 재표시 화면의 hidden 식별자가 비어,
 * 사용자가 오류를 고쳐 재제출할 때 빈 식별자로 갱신이 이뤄져 수정이 유실된다.
 * 수정 전 코드에서는 {@code resultList}가 담기지 않아 검증이 실패한다.</p>
 */
class EgovQustnrRespondInfoControllerModifyRestoreTest {

	private final InvocationHandler authStub = (proxy, method, args) -> {
		switch (method.getName()) {
		case "isAuthenticated":
			return Boolean.TRUE;
		case "getAuthenticatedUser":
			LoginVO loginVO = new LoginVO();
			loginVO.setUniqId("USRCNFRM_TEST");
			return loginVO;
		case "getAuthorities":
			return List.of("ROLE_ADMIN");
		default:
			return null;
		}
	};

	private final InvocationHandler emptyStub = (proxy, method, args) ->
			List.class.isAssignableFrom(method.getReturnType()) ? Collections.emptyList() : null;

	@BeforeEach
	void setUpStaticAuth() {
		EgovUserDetailsService auth = (EgovUserDetailsService) Proxy.newProxyInstance(
				getClass().getClassLoader(), new Class[] { EgovUserDetailsService.class }, authStub);
		ReflectionTestUtils.setField(EgovUserDetailsHelper.class, "egovUserDetailsService", auth);
	}

	@AfterEach
	void tearDownStaticAuth() {
		ReflectionTestUtils.setField(EgovUserDetailsHelper.class, "egovUserDetailsService", null);
	}

	@Test
	@DisplayName("설문 응답 수정 검증 실패 시 재표시가 참조하는 resultList를 복원한다")
	void modifyRestoresResultListOnValidationError() throws Exception {
		EgovQustnrRespondInfoController controller = new EgovQustnrRespondInfoController();
		ReflectionTestUtils.setField(controller, "egovQustnrRespondInfoService",
				Proxy.newProxyInstance(getClass().getClassLoader(),
						new Class[] { EgovQustnrRespondInfoService.class }, emptyStub));

		QustnrRespondInfoVO qustnrRespondInfoVO = new QustnrRespondInfoVO();
		BindingResult bindingResult = new BeanPropertyBindingResult(qustnrRespondInfoVO, "qustnrRespondInfoVO");
		bindingResult.reject("validation.error");

		Map<String, Object> commandMap = new HashMap<>();
		commandMap.put("cmd", "save");
		ModelMap model = new ModelMap();

		String view = controller.qustnrRespondInfoModify(new ComDefaultVO(), commandMap, null,
				qustnrRespondInfoVO, bindingResult, new RedirectAttributesModelMap(), model);

		assertEquals("egovframework/com/uss/olp/qri/EgovQustnrRespondInfoModify", view);
		assertTrue(model.containsAttribute("resultList"),
				"검증 실패 재표시 시 resultList가 model에 있어야 재제출 대상 식별자가 보존된다");
	}
}
