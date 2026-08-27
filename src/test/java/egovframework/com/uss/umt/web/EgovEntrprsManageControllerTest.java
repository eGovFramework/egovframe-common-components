package egovframework.com.uss.umt.web;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;

import egovframework.com.cmm.ComDefaultCodeVO;
import egovframework.com.cmm.service.CmmnDetailCode;
import egovframework.com.cmm.service.EgovCmmUseService;
import egovframework.com.uss.umt.service.EntrprsManageInsertVO;
import egovframework.com.uss.umt.service.UserDefaultVO;

/**
 * 기업회원 가입신청 화면의 코드목록 복원 검증.
 *
 * <p>입력값 검증에 실패하면 가입신청 화면(EgovEntrprsSbscrb.jsp)을 그대로 다시 그리는데,
 * 이때 화면이 form:options로 참조하는 코드목록을 모델에 다시 담지 않아
 * 비밀번호힌트·업종코드·기업구분코드 선택상자가 빈 채로 표시되던 문제에 대한 회귀 방지 테스트.</p>
 *
 * <p>Spring 컨텍스트·DB 없이 EgovCmmUseService 동적 프록시와 ReflectionTestUtils 필드 주입으로
 * 검증 실패 분기만 확인한다.</p>
 */
class EgovEntrprsManageControllerTest {

	private static final String SBSCRB_VIEW = "egovframework/com/uss/umt/EgovEntrprsSbscrb";

	private EgovEntrprsManageController controller;

	@BeforeEach
	void setUp() {
		controller = new EgovEntrprsManageController();
		ReflectionTestUtils.setField(controller, "cmmUseService", stubCmmUseService());
	}

	@Test
	void sbscrbEntrprsMberRestoresCodeListWhenValidationFails() throws Exception {
		Model model = new ExtendedModelMap();

		assertEquals(SBSCRB_VIEW, invokeSbscrbEntrprsMber(model));

		// EgovEntrprsSbscrb.jsp가 form:options로 참조하는 코드목록
		assertNotNull(model.getAttribute("passwordHint_result"), "비밀번호힌트 코드목록이 있어야 한다");
		assertNotNull(model.getAttribute("indutyCode_result"), "업종코드 목록이 있어야 한다");
		assertNotNull(model.getAttribute("entrprsSeCode_result"), "기업구분코드 목록이 있어야 한다");
	}

	@Test
	void sbscrbEntrprsMberKeepsSameCodeListAsSbscrbView() throws Exception {
		Model viewModel = new ExtendedModelMap();
		controller.sbscrbEntrprsMberView(new UserDefaultVO(), insertVO(), new HashMap<>(), viewModel);

		Model errorModel = new ExtendedModelMap();
		invokeSbscrbEntrprsMber(errorModel);

		assertEquals(codeListNames(viewModel), codeListNames(errorModel),
				"가입신청 화면 진입 경로와 검증 실패 재표시 경로의 코드목록이 같아야 한다");
	}

	/**
	 * 검증 실패를 처리하는 sbscrbEntrprsMber 를 리플렉션으로 호출한다.
	 *
	 * <p>수정 전에는 Model 파라미터가 없으므로, 직접 호출하면 테스트가 수정 전 소스에서
	 * 컴파일되지 않아 RED 를 실패가 아니라 컴파일 오류로 만든다. 두 시그니처를 모두 받아
	 * 같은 테스트가 수정 전후 양쪽에서 돌아가게 한다.</p>
	 */
	private String invokeSbscrbEntrprsMber(Model model) throws Exception {
		Method withModel = null;
		Method withoutModel = null;
		for (Method method : EgovEntrprsManageController.class.getDeclaredMethods()) {
			if (!"sbscrbEntrprsMber".equals(method.getName())) {
				continue;
			}
			Class<?>[] types = method.getParameterTypes();
			if (types.length == 3 && Model.class.isAssignableFrom(types[2])) {
				withModel = method;
			} else if (types.length == 2 && BindingResult.class.isAssignableFrom(types[1])) {
				withoutModel = method;
			}
		}
		if (withModel != null) {
			return (String) withModel.invoke(controller, insertVO(), rejectedBindingResult(), model);
		}
		assertNotNull(withoutModel, "검증 실패를 처리하는 sbscrbEntrprsMber 를 찾지 못했다");
		return (String) withoutModel.invoke(controller, insertVO(), rejectedBindingResult());
	}

	private EntrprsManageInsertVO insertVO() {
		return new EntrprsManageInsertVO();
	}

	private BindingResult rejectedBindingResult() {
		BindingResult bindingResult = new BeanPropertyBindingResult(insertVO(), "entrprsManageVO");
		bindingResult.rejectValue("passwordHint", "errors.required");
		return bindingResult;
	}

	private Set<String> codeListNames(Model model) {
		return model.asMap().keySet().stream().filter(name -> name.endsWith("_result"))
				.collect(Collectors.toCollection(TreeSet::new));
	}

	/** 요청한 코드ID를 그대로 되돌려주는 EgovCmmUseService 페이크 생성. */
	private EgovCmmUseService stubCmmUseService() {
		return (EgovCmmUseService) Proxy.newProxyInstance(
				getClass().getClassLoader(),
				new Class<?>[] { EgovCmmUseService.class },
				(proxy, method, args) -> {
					if (method.getReturnType() != List.class) {
						return null;
					}
					CmmnDetailCode detailCode = new CmmnDetailCode();
					detailCode.setCode(((ComDefaultCodeVO) args[0]).getCodeId());
					detailCode.setCodeNm("stub");
					return Collections.singletonList(detailCode);
				});
	}

}
