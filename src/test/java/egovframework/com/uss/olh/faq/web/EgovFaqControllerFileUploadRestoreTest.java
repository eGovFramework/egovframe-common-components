package egovframework.com.uss.olh.faq.web;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import egovframework.com.uss.olh.faq.service.FaqVO;

/**
 * FAQ 등록·수정 저장 핸들러({@code insertFaqCn}·{@code updateFaqCn})의 검증 실패 재표시 경로가
 * 파일 업로드 제약값({@code fileUploadExtensions}·{@code fileUploadMaxSize})을 model 에 담는지 검증한다.
 *
 * <p>재표시 JSP 는 이 값을 자바스크립트 인자로 그대로 출력한다. 값이 없으면
 * {@code checkFileSize("egovComFileUploader", )} 가 되어 스크립트 블록 전체가 문법 오류로 죽고
 * 저장 함수가 사라진다.</p>
 *
 * <p>수정 전후로 {@code insertFaqCn} 의 시그니처가 달라 리플렉션으로 호출한다.</p>
 */
class EgovFaqControllerFileUploadRestoreTest {

	private Method findMethod(String name) {
		for (Method m : EgovFaqController.class.getDeclaredMethods()) {
			if (m.getName().equals(name)) {
				return m;
			}
		}
		throw new IllegalStateException(name + " 메서드를 찾을 수 없다");
	}

	private ModelMap invokeWithValidationError(String methodName) throws Throwable {
		EgovFaqController controller = new EgovFaqController();
		Method method = findMethod(methodName);
		ExtendedModelMap model = new ExtendedModelMap();
		boolean hasModelParam = false;
		Class<?>[] types = method.getParameterTypes();
		Object[] args = new Object[types.length];
		for (int i = 0; i < types.length; i++) {
			if (MultipartHttpServletRequest.class.isAssignableFrom(types[i])) {
				args[i] = null;
			} else if (FaqVO.class.isAssignableFrom(types[i])) {
				args[i] = new FaqVO();
			} else if (BindingResult.class.isAssignableFrom(types[i])) {
				BindingResult bindingResult = new BeanPropertyBindingResult(new FaqVO(), "faqVO");
				bindingResult.reject("validation.error");
				args[i] = bindingResult;
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
	@DisplayName("FAQ 등록 검증 실패 시 파일 업로드 제약값을 model에 담는다")
	void insertFaqCnRestoresFileUploadLimits() throws Throwable {
		ModelMap model = invokeWithValidationError("insertFaqCn");
		assertTrue(model.containsAttribute("fileUploadMaxSize"),
				"검증 실패 재표시 시 fileUploadMaxSize가 model에 있어야 checkFileSize 인자가 비지 않는다");
		assertTrue(model.containsAttribute("fileUploadExtensions"), "fileUploadExtensions도 담겨야 한다");
	}

	@Test
	@DisplayName("FAQ 수정 검증 실패 시 파일 업로드 제약값을 model에 담는다")
	void updateFaqCnRestoresFileUploadLimits() throws Throwable {
		ModelMap model = invokeWithValidationError("updateFaqCn");
		assertTrue(model.containsAttribute("fileUploadMaxSize"), "fileUploadMaxSize가 담겨야 한다");
		assertTrue(model.containsAttribute("fileUploadExtensions"), "fileUploadExtensions가 담겨야 한다");
	}
}
