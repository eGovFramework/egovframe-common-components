package egovframework.com.cop.smt.lsm.web;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;

import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.service.EgovCmmUseService;
import egovframework.com.cmm.service.EgovUserDetailsService;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.com.cop.smt.lsm.service.LeaderSttusVO;

/**
 * 간부상태 등록 처리({@link EgovLeaderSchdulController#insertLeaderSttus})의 검증 실패 분기가,
 * 표시 경로({@code addLeaderSttus})처럼 간부상태 공통코드 목록({@code leaderSttus}, 코드 COM061)을
 * model에 복원하는지 검증한다.
 *
 * <p>재표시 JSP는 필수 항목인 간부상태를 {@code <form:options items="${leaderSttus}">}로 그린다.
 * 검증 실패 분기가 목록을 복원하지 않으면 필수 셀렉트박스가 옵션 없이 렌더되어 사용자가 값을 고를 수 없다.
 * 수정 전 코드에서는 {@code leaderSttus}가 담기지 않아 검증이 실패한다.</p>
 */
class EgovLeaderSchdulControllerLeaderSttusRestoreTest {

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
	@DisplayName("간부상태 등록 검증 실패 시 간부상태 공통코드 목록(leaderSttus)을 복원한다")
	void insertLeaderSttusRestoresCodeListOnValidationError() throws Exception {
		EgovLeaderSchdulController controller = new EgovLeaderSchdulController();
		ReflectionTestUtils.setField(controller, "cmmUseService",
				Proxy.newProxyInstance(getClass().getClassLoader(),
						new Class[] { EgovCmmUseService.class }, emptyStub));

		LeaderSttusVO leaderSttusVO = new LeaderSttusVO();
		BindingResult bindingResult = new BeanPropertyBindingResult(leaderSttusVO, "leaderSttusVO");
		bindingResult.reject("validation.error");
		ModelMap model = new ModelMap();

		String view = controller.insertLeaderSttus(leaderSttusVO, bindingResult, model);

		assertEquals("egovframework/com/cop/smt/lsm/EgovLeaderSttusRegist", view);
		assertTrue(model.containsAttribute("leaderSttus"),
				"검증 실패 재표시 시 leaderSttus가 model에 있어야 필수 셀렉트박스가 비지 않는다");
	}

	@Test
	@DisplayName("간부상태 수정 검증 실패 시 간부상태 공통코드 목록(leaderSttus)을 복원한다")
	void updateLeaderSttusRestoresCodeListOnValidationError() throws Exception {
		EgovLeaderSchdulController controller = new EgovLeaderSchdulController();
		ReflectionTestUtils.setField(controller, "cmmUseService",
				Proxy.newProxyInstance(getClass().getClassLoader(),
						new Class[] { EgovCmmUseService.class }, emptyStub));

		LeaderSttusVO leaderSttusVO = new LeaderSttusVO();
		BindingResult bindingResult = new BeanPropertyBindingResult(leaderSttusVO, "leaderSttusVO");
		bindingResult.reject("validation.error");
		ModelMap model = new ModelMap();

		String view = controller.updateLeaderSttus(leaderSttusVO, bindingResult, model);

		assertEquals("egovframework/com/cop/smt/lsm/EgovLeaderSttusUpdt", view);
		assertTrue(model.containsAttribute("leaderSttus"),
				"검증 실패 재표시 시 leaderSttus가 model에 있어야 필수 셀렉트박스가 비지 않는다");
	}
}
