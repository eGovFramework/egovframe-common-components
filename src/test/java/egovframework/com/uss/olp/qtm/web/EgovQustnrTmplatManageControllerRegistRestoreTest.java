package egovframework.com.uss.olp.qtm.web;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Collections;

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
import egovframework.com.uss.olp.qtm.service.QustnrTmplatManageVO;

/**
 * 설문 템플릿 등록 저장 핸들러({@code qustnrTmplatManageRegistActor})의 검증 실패 재표시 경로가
 * 파일 업로드 허용 확장자·최대 크기({@code fileUploadExtensions}·{@code fileUploadMaxSize})를
 * {@code model}에 담는지 검증한다.
 *
 * <p>수정 전에는 검증 실패 반환 지점이 두 값을 복원하지 않아 재표시 JSP의
 * {@code checkExtensions("qestnrTmplatImage", "")} 허용 목록이 비고, 빈 허용 목록은 모든 확장자를
 * 거부해 정상 이미지도 첨부할 수 없었다.</p>
 *
 * <p>수정 전후로 메서드 시그니처가 달라(수정 후 {@code ModelMap} 파라미터 추가) 리플렉션으로 호출한다.</p>
 */
class EgovQustnrTmplatManageControllerRegistRestoreTest {

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

	private Method findRegistActor() {
		for (Method m : EgovQustnrTmplatManageController.class.getDeclaredMethods()) {
			if (m.getName().equals("qustnrTmplatManageRegistActor")) {
				return m;
			}
		}
		throw new IllegalStateException("qustnrTmplatManageRegistActor 메서드를 찾을 수 없다");
	}

	@Test
	@DisplayName("설문 템플릿 등록 검증 실패 시 파일 업로드 제약값을 model에 담는다")
	void registActorRestoresFileUploadLimits() throws Throwable {
		EgovQustnrTmplatManageController controller = new EgovQustnrTmplatManageController();
		Method method = findRegistActor();
		ExtendedModelMap model = new ExtendedModelMap();
		boolean hasModelParam = false;
		Class<?>[] types = method.getParameterTypes();
		Object[] args = new Object[types.length];
		for (int i = 0; i < types.length; i++) {
			if (MultipartHttpServletRequest.class.isAssignableFrom(types[i])) {
				args[i] = null;
			} else if (QustnrTmplatManageVO.class.isAssignableFrom(types[i])) {
				args[i] = new QustnrTmplatManageVO();
			} else if (ComDefaultVO.class.isAssignableFrom(types[i])) {
				args[i] = new ComDefaultVO();
			} else if (BindingResult.class.isAssignableFrom(types[i])) {
				BindingResult bindingResult = new BeanPropertyBindingResult(new QustnrTmplatManageVO(),
						"qustnrTmplatManageVO");
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

		assertTrue(model.containsAttribute("fileUploadExtensions"),
				"검증 실패 재표시 시 fileUploadExtensions가 model에 있어야 확장자 허용 목록이 비지 않는다");
		assertTrue(model.containsAttribute("fileUploadMaxSize"), "fileUploadMaxSize도 담겨야 한다");
	}
}
