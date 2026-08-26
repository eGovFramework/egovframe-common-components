package egovframework.com.uss.olh.hpc.web;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.lang.reflect.Proxy;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;

import egovframework.com.cmm.service.EgovCmmUseService;
import egovframework.com.uss.olh.hpc.service.HpcmVO;

/**
 * 도움말 수정({@link EgovHpcmController#updateHpcm})·등록({@code insertHpcmCn})의 검증 실패 분기를 검증한다.
 *
 * <p>수정 폼 JSP는 {@code <form:form modelAttribute="hpcmVO">}로 바인딩하고 도움말구분
 * 드롭다운을 {@code ${hpcmSeCode}}로 채운다. 수정 대상 메서드가 다른 이름으로 바인딩하면
 * 검증 실패 재표시 때 {@code hpcmVO}가 model에 없어 form 태그가
 * {@code IllegalStateException}을 던지고(500), {@code hpcmSeCode}를 복원하지 않으면
 * 필수 드롭다운이 빈 채로 렌더된다.</p>
 *
 * <p>수정 전 코드에서는 수정 처리의 바인딩명이 {@code hpcmManageVO}이고 두 분기 모두
 * {@code hpcmSeCode}를 복원하지 않아 세 검증이 모두 실패한다.</p>
 *
 * <p>수정 전후로 메서드 시그니처가 달라 리플렉션으로 호출한다.</p>
 */
class EgovHpcmControllerValidationRestoreTest {

	private final InvocationHandler emptyStub = (proxy, method, args) ->
			List.class.isAssignableFrom(method.getReturnType()) ? Collections.emptyList() : null;

	private EgovHpcmController newControllerWithStubs() {
		EgovHpcmController controller = new EgovHpcmController();
		ReflectionTestUtils.setField(controller, "cmmUseService",
				Proxy.newProxyInstance(getClass().getClassLoader(),
						new Class[] { EgovCmmUseService.class }, emptyStub));
		return controller;
	}

	private Method findMethod(String name) {
		for (Method m : EgovHpcmController.class.getDeclaredMethods()) {
			if (m.getName().equals(name)) {
				return m;
			}
		}
		throw new IllegalStateException(name + " 메서드를 찾을 수 없다");
	}

	/** 파라미터 타입에 맞춰 인자를 만들어 호출하고, JSP가 읽을 model을 돌려준다. */
	private ModelMap invokeWithValidationError(Method method, EgovHpcmController controller) throws Throwable {
		ExtendedModelMap model = new ExtendedModelMap();
		boolean hasModelParam = false;
		Object[] args = new Object[method.getParameterCount()];
		Class<?>[] types = method.getParameterTypes();
		for (int i = 0; i < types.length; i++) {
			if (HpcmVO.class.isAssignableFrom(types[i])) {
				args[i] = new HpcmVO();
			} else if (BindingResult.class.isAssignableFrom(types[i])) {
				BindingResult bindingResult = new BeanPropertyBindingResult(new HpcmVO(), "hpcmVO");
				bindingResult.reject("validation.error");
				args[i] = bindingResult;
			} else if (types[i].isAssignableFrom(ExtendedModelMap.class)
					|| Model.class.isAssignableFrom(types[i]) || ModelMap.class.isAssignableFrom(types[i])) {
				args[i] = model;
				hasModelParam = true;
			} else {
				args[i] = null;
			}
		}
		if (!hasModelParam) {
			fail("검증 실패 분기에서 참조데이터를 복원할 Model 파라미터가 없다");
		}
		try {
			method.invoke(controller, args);
		} catch (InvocationTargetException e) {
			throw e.getCause();
		}
		return model;
	}

	@Test
	@DisplayName("수정 처리의 폼 바인딩명이 JSP form:form의 modelAttribute(hpcmVO)와 일치한다")
	void updateHpcmBindsUnderTheNameTheJspExpects() {
		Method updateHpcm = findMethod("updateHpcm");
		String boundName = null;
		for (Parameter p : updateHpcm.getParameters()) {
			ModelAttribute ann = p.getAnnotation(ModelAttribute.class);
			if (ann != null && !"searchVO".equals(ann.value())) {
				boundName = ann.value();
			}
		}
		assertNotNull(boundName, "수정 대상 VO의 @ModelAttribute 바인딩명을 찾을 수 없다");
		assertEquals("hpcmVO", boundName,
				"EgovHpcmUpdt.jsp의 <form:form modelAttribute=\"hpcmVO\">와 일치해야 검증 실패 재표시가 500이 되지 않는다");
	}

	@Test
	@DisplayName("도움말 수정 검증 실패 시 도움말구분 드롭다운 목록(hpcmSeCode)을 복원한다")
	void updateHpcmRestoresCodeListOnValidationError() throws Throwable {
		ModelMap model = invokeWithValidationError(findMethod("updateHpcm"), newControllerWithStubs());
		assertTrue(model.containsAttribute("hpcmSeCode"),
				"검증 실패 재표시 시 hpcmSeCode가 model에 있어야 드롭다운이 비지 않는다");
	}

	@Test
	@DisplayName("도움말 등록 검증 실패 시 도움말구분 드롭다운 목록(hpcmSeCode)을 복원한다")
	void insertHpcmRestoresCodeListOnValidationError() throws Throwable {
		ModelMap model = invokeWithValidationError(findMethod("insertHpcmCn"), newControllerWithStubs());
		assertTrue(model.containsAttribute("hpcmSeCode"),
				"검증 실패 재표시 시 hpcmSeCode가 model에 있어야 드롭다운이 비지 않는다");
	}
}
