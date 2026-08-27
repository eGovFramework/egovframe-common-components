package egovframework.com.uss.umt.web;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;

import egovframework.com.cmm.ComDefaultCodeVO;
import egovframework.com.cmm.service.CmmnDetailCode;
import egovframework.com.cmm.service.EgovCmmUseService;
import egovframework.com.uss.umt.service.MberManageInsertVO;
import egovframework.com.uss.umt.service.UserDefaultVO;

/**
 * 일반회원가입신청 화면(EgovMberSbscrb)이 필요로 하는 코드목록이
 * 진입 경로와 입력값검증 실패 경로에서 동일하게 화면모델에 담기는지 확인한다.
 */
class EgovMberManageControllerTest {

	private static final String SBSCRB_VIEW = "egovframework/com/uss/umt/EgovMberSbscrb";

	private EgovMberManageController controller;

	@BeforeEach
	void setUp() throws Exception {
		controller = new EgovMberManageController();
		Field cmmUseService = EgovMberManageController.class.getDeclaredField("cmmUseService");
		cmmUseService.setAccessible(true);
		cmmUseService.set(controller, cmmCodeStub());
	}

	@Test
	void sbscrbMberViewPopulatesCodeLists() throws Exception {
		Model model = new ExtendedModelMap();

		String view = controller.sbscrbMberView(new UserDefaultVO(), new MberManageInsertVO(), new HashMap<>(), model);

		assertEquals(SBSCRB_VIEW, view);
		assertCodeList(model, "passwordHint_result");
		assertCodeList(model, "sexdstnCode_result");
	}

	@Test
	void sbscrbMberRepopulatesCodeListsWhenValidationFails() throws Exception {
		Model model = new ExtendedModelMap();
		MberManageInsertVO mberManageInsertVO = new MberManageInsertVO();
		BindingResult bindingResult = new BeanPropertyBindingResult(mberManageInsertVO, "mberManageVO");
		bindingResult.reject("required");

		Object view = invokeSbscrbMber(mberManageInsertVO, bindingResult, model);

		assertEquals(SBSCRB_VIEW, view);
		assertCodeList(model, "passwordHint_result");
		assertCodeList(model, "sexdstnCode_result");
	}

	/**
	 * sbscrbMber는 화면모델 파라메터 유무로 시그니처가 갈리므로 리플렉션으로 호출한다.
	 */
	private Object invokeSbscrbMber(MberManageInsertVO mberManageInsertVO, BindingResult bindingResult, Model model)
			throws Exception {
		for (Method method : EgovMberManageController.class.getMethods()) {
			if (!"sbscrbMber".equals(method.getName())) {
				continue;
			}
			Class<?>[] parameterTypes = method.getParameterTypes();
			Object[] args = new Object[parameterTypes.length];
			for (int i = 0; i < parameterTypes.length; i++) {
				if (parameterTypes[i].isInstance(mberManageInsertVO)) {
					args[i] = mberManageInsertVO;
				} else if (parameterTypes[i].isInstance(bindingResult)) {
					args[i] = bindingResult;
				} else if (parameterTypes[i].isInstance(model)) {
					args[i] = model;
				}
			}
			return method.invoke(controller, args);
		}
		throw new AssertionError("sbscrbMber 메소드를 찾을 수 없습니다.");
	}

	private void assertCodeList(Model model, String attributeName) {
		Map<String, Object> modelMap = model.asMap();
		assertNotNull(modelMap.get(attributeName), attributeName + " 코드목록이 화면모델에 없습니다.");
		assertFalse(((List<?>) modelMap.get(attributeName)).isEmpty(), attributeName + " 코드목록이 비어 있습니다.");
	}

	private EgovCmmUseService cmmCodeStub() {
		return (EgovCmmUseService) Proxy.newProxyInstance(EgovCmmUseService.class.getClassLoader(),
				new Class<?>[] { EgovCmmUseService.class }, (proxy, method, args) -> {
					if (!"selectCmmCodeDetail".equals(method.getName())) {
						return null;
					}
					String codeId = ((ComDefaultCodeVO) args[0]).getCodeId();
					CmmnDetailCode cmmnDetailCode = new CmmnDetailCode();
					cmmnDetailCode.setCode(codeId + "01");
					cmmnDetailCode.setCodeNm(codeId);
					return Arrays.asList(cmmnDetailCode);
				});
	}

}
