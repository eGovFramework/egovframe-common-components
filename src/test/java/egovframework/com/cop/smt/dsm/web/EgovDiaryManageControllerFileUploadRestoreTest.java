package egovframework.com.cop.smt.dsm.web;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
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
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributesModelMap;

import egovframework.com.cmm.ComDefaultVO;
import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.service.EgovUserDetailsService;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.com.cop.smt.dsm.service.DiaryManageVO;

/**
 * 일지관리 등록·수정 저장 핸들러({@code diaryManageRegistActor}·{@code diaryManageModifyActor})의
 * 검증 실패 재표시 경로가 파일 업로드 제약값({@code fileUploadExtensions}·{@code fileUploadMaxSize})을
 * {@code model}에 담는지 검증한다.
 *
 * <p>두 핸들러는 forward 뷰를 반환하는데, 수정 전에는 두 값을 {@code RedirectAttributes}에 담아
 * forward 뷰에 병합되지 않았다. 그 결과 재표시 JSP의 {@code checkFileSize("egovComFileUploader", )}가
 * 인자 없는 자바스크립트로 렌더되어 스크립트 블록 전체가 문법 오류로 죽고 저장 함수가 사라진다.</p>
 *
 * <p>수정 전후로 메서드 시그니처가 달라(수정 후 {@code ModelMap} 파라미터 추가) 리플렉션으로 호출한다.</p>
 */
class EgovDiaryManageControllerFileUploadRestoreTest {

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

	private Method findMethod(String name) {
		for (Method m : EgovDiaryManageController.class.getDeclaredMethods()) {
			if (m.getName().equals(name)) {
				return m;
			}
		}
		throw new IllegalStateException(name + " 메서드를 찾을 수 없다");
	}

	private ModelMap invokeWithValidationError(String methodName) throws Throwable {
		EgovDiaryManageController controller = new EgovDiaryManageController();
		Method method = findMethod(methodName);
		ExtendedModelMap model = new ExtendedModelMap();
		boolean hasModelParam = false;
		Class<?>[] types = method.getParameterTypes();
		Object[] args = new Object[types.length];
		for (int i = 0; i < types.length; i++) {
			if (MultipartHttpServletRequest.class.isAssignableFrom(types[i])) {
				args[i] = null;
			} else if (Map.class.isAssignableFrom(types[i]) && !Model.class.isAssignableFrom(types[i])
					&& !ModelMap.class.isAssignableFrom(types[i])) {
				Map<String, Object> commandMap = new HashMap<>();
				commandMap.put("cmd", "save");
				args[i] = commandMap;
			} else if (DiaryManageVO.class.isAssignableFrom(types[i])) {
				args[i] = new DiaryManageVO();
			} else if (ComDefaultVO.class.isAssignableFrom(types[i])) {
				args[i] = new ComDefaultVO();
			} else if (BindingResult.class.isAssignableFrom(types[i])) {
				BindingResult bindingResult = new BeanPropertyBindingResult(new DiaryManageVO(), "diaryManageVO");
				bindingResult.reject("validation.error");
				args[i] = bindingResult;
			} else if (RedirectAttributes.class.isAssignableFrom(types[i])) {
				args[i] = new RedirectAttributesModelMap();
			} else if (Model.class.isAssignableFrom(types[i]) || ModelMap.class.isAssignableFrom(types[i])) {
				args[i] = model;
				hasModelParam = true;
			} else {
				args[i] = null;
			}
		}
		if (!hasModelParam) {
			fail("검증 실패 재표시에 파일 업로드 제약값을 담을 Model 파라미터가 없다");
		}
		try {
			method.invoke(controller, args);
		} catch (InvocationTargetException e) {
			throw e.getCause();
		}
		return model;
	}

	@Test
	@DisplayName("일지 등록 검증 실패 시 파일 업로드 제약값을 model에 담는다")
	void registActorRestoresFileUploadLimits() throws Throwable {
		ModelMap model = invokeWithValidationError("diaryManageRegistActor");
		assertTrue(model.containsAttribute("fileUploadMaxSize"),
				"검증 실패 재표시 시 fileUploadMaxSize가 model에 있어야 checkFileSize 인자가 비지 않는다");
		assertTrue(model.containsAttribute("fileUploadExtensions"), "fileUploadExtensions도 담겨야 한다");
	}

	@Test
	@DisplayName("일지 수정 검증 실패 시 파일 업로드 제약값을 model에 담는다")
	void modifyActorRestoresFileUploadLimits() throws Throwable {
		ModelMap model = invokeWithValidationError("diaryManageModifyActor");
		assertTrue(model.containsAttribute("fileUploadMaxSize"), "fileUploadMaxSize가 담겨야 한다");
		assertTrue(model.containsAttribute("fileUploadExtensions"), "fileUploadExtensions가 담겨야 한다");
	}
}
