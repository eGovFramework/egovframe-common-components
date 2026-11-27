package egovframework.com.uss.ion.nws.web;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.lang.reflect.Proxy;

import org.junit.jupiter.api.Test;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import egovframework.com.uss.ion.nws.service.NewsVO;

class EgovNewsControllerTest {

	private static final String REGIST_VIEW = "egovframework/com/uss/ion/nws/EgovNewsRegist";

	@Test
	void insertNewsReturnsRegistViewBeforeReadingFilesWhenValidationFails() throws Exception {
		EgovNewsController controller = new EgovNewsController();
		NewsVO newsVO = new NewsVO();
		BindingResult bindingResult = new BeanPropertyBindingResult(newsVO, "newsVO");
		bindingResult.rejectValue("newsSj", "required");

		MultipartHttpServletRequest request = multipartRequestFailingOnFileAccess();

		ModelMap model = new ModelMap();
		String view = controller.insertNews(request, newsVO, bindingResult, model);

		assertEquals(REGIST_VIEW, view);
		assertEquals(newsVO, model.get("newsVO"));
		assertNull(newsVO.getAtchFileId());
	}

	private MultipartHttpServletRequest multipartRequestFailingOnFileAccess() {
		return (MultipartHttpServletRequest) Proxy.newProxyInstance(MultipartHttpServletRequest.class.getClassLoader(),
				new Class<?>[] { MultipartHttpServletRequest.class }, (proxy, method, args) -> {
					if (method.getDeclaringClass() == Object.class) {
						return handleObjectMethod(proxy, method.getName(), args);
					}

					if ("getFiles".equals(method.getName())) {
						throw new AssertionError("Validation errors must be handled before reading multipart files.");
					}

					throw new UnsupportedOperationException(method.toString());
				});
	}

	private Object handleObjectMethod(Object proxy, String methodName, Object[] args) {
		if ("toString".equals(methodName)) {
			return "multipartRequest";
		}
		if ("hashCode".equals(methodName)) {
			return System.identityHashCode(proxy);
		}
		if ("equals".equals(methodName)) {
			return proxy == args[0];
		}
		throw new UnsupportedOperationException(methodName);
	}
}
