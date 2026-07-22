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
import egovframework.com.cmm.service.EgovCmmUseService;
import egovframework.com.cmm.service.EgovUserDetailsService;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.com.uss.olp.qri.service.EgovQustnrRespondInfoService;

/**
 * 설문 응답 등록({@link EgovQustnrRespondInfoController#egovQustnrRespondInfoManageRegist})의
 * 검증 실패 분기가, 정상 표시 경로처럼 재표시 JSP가 참조하는 참조데이터
 * (설문템플릿·설문정보·문항·항목 목록과 hidden {@code qestnrTmplatId}/{@code qestnrId})를
 * model에 복원하는지 검증한다.
 *
 * <p>재표시 JSP는 {@code QustnrTmplatManage[0].qestnrTmplatCours}로 문항 영역
 * {@code <c:import>}의 templateUrl을 만들고, hidden 식별자 두 개로 재제출을 구성한다.
 * 검증 실패 분기가 이를 복원하지 않으면 재표시 화면이 에러 페이지로 대체되고
 * 재제출이 빈 식별자로 저장을 시도해 응답이 사라진다.</p>
 *
 * <p>수정 전 코드에서는 {@code searchVO}만 담겨 모든 검증이 실패한다.</p>
 */
class EgovQustnrRespondInfoControllerValidationRestoreTest {

	/** 조회 메서드 반환 타입에 맞춰 빈 값을 돌려주는 스텁. */
	private final InvocationHandler emptyStub = (proxy, method, args) -> {
		Class<?> rt = method.getReturnType();
		if (List.class.isAssignableFrom(rt)) {
			return Collections.emptyList();
		}
		if (Map.class.isAssignableFrom(rt)) {
			return Collections.emptyMap();
		}
		return null;
	};

	/** 로그인 상태 스텁 — 인증 통과 후 검증 실패 분기에 도달하게 한다. */
	private final InvocationHandler authStub = (proxy, method, args) -> {
		switch (method.getName()) {
		case "isAuthenticated":
			return Boolean.TRUE;
		case "getAuthenticatedUser":
			LoginVO loginVO = new LoginVO();
			loginVO.setUniqId("USRCNFRM_TEST");
			return loginVO;
		case "getAuthorities":
			return Collections.emptyList();
		default:
			return null;
		}
	};

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
	@DisplayName("설문 응답 등록 검증 실패 시 재표시 폼이 참조하는 참조데이터를 복원한다")
	void registRestoresReferenceDataOnValidationError() throws Exception {
		EgovQustnrRespondInfoController controller = new EgovQustnrRespondInfoController();
		ClassLoader cl = getClass().getClassLoader();
		ReflectionTestUtils.setField(controller, "egovQustnrRespondInfoService",
				Proxy.newProxyInstance(cl, new Class[] { EgovQustnrRespondInfoService.class }, emptyStub));
		ReflectionTestUtils.setField(controller, "cmmUseService",
				Proxy.newProxyInstance(cl, new Class[] { EgovCmmUseService.class }, emptyStub));

		ComDefaultVO searchVO = new ComDefaultVO();
		BindingResult bindingResult = new BeanPropertyBindingResult(searchVO, "searchVO");
		bindingResult.reject("required", "문항 입력 없음");

		Map<String, Object> commandMap = new HashMap<>();
		commandMap.put("cmd", "save");
		commandMap.put("qestnrTmplatId", "TMPLAT_TEST01");
		commandMap.put("qestnrId", "QESTNR_TEST01");

		ModelMap model = new ModelMap();

		String view = controller.egovQustnrRespondInfoManageRegist(searchVO, bindingResult, commandMap,
				null, new RedirectAttributesModelMap(), model);

		assertEquals("egovframework/com/uss/olp/qnn/EgovQustnrRespondInfoManageRegist", view);
		assertTrue(model.containsAttribute("QustnrTmplatManage"),
				"재표시 JSP의 문항 영역 templateUrl 원천인 QustnrTmplatManage가 복원되어야 한다");
		assertTrue(model.containsAttribute("Comtnqestnrinfo"), "설문정보가 복원되어야 한다");
		assertTrue(model.containsAttribute("Comtnqustnrqesitm"), "문항정보가 복원되어야 한다");
		assertTrue(model.containsAttribute("Comtnqustnriem"), "항목정보가 복원되어야 한다");
		assertEquals("TMPLAT_TEST01", model.get("qestnrTmplatId"), "hidden 설문템플릿ID가 유지되어야 한다");
		assertEquals("QESTNR_TEST01", model.get("qestnrId"), "hidden 설문ID가 유지되어야 한다");
	}
}
