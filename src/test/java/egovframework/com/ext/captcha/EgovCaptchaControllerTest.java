package egovframework.com.ext.captcha;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayOutputStream;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.WriteListener;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

class EgovCaptchaControllerTest {

	private static final byte[] PNG_SIGNATURE = {
			(byte) 0x89, 0x50, 0x4e, 0x47, 0x0d, 0x0a, 0x1a, 0x0a
	};

	private final EgovCaptchaController controller = new EgovCaptchaController();

	@Test
	void generateCreatesPngAndStoresDefaultLengthCaptcha() {
		RequestStub request = new RequestStub();
		ResponseStub response = new ResponseStub();

		controller.generate(request.proxy(), response.proxy(), 150, 50, null, null, "test");

		assertEquals(200, response.status);
		assertEquals("image/png", response.contentType);
		assertPng(response.content.toByteArray());
		assertCaptcha(request, "test", 5);
	}

	@Test
	void generateUsesLengthBeforeLegacyLenght() {
		RequestStub request = new RequestStub();
		ResponseStub response = new ResponseStub();

		controller.generate(request.proxy(), response.proxy(), 150, 50, 6, 9, "modern");

		assertEquals(200, response.status);
		assertCaptcha(request, "modern", 6);
	}

	@Test
	void generateSupportsLegacyLenghtParameter() {
		RequestStub request = new RequestStub();
		ResponseStub response = new ResponseStub();

		controller.generate(request.proxy(), response.proxy(), 150, 50, null, 7, "legacy");

		assertEquals(200, response.status);
		assertCaptcha(request, "legacy", 7);
	}

	@ParameterizedTest
	@MethodSource("invalidRequests")
	void generateRejectsInvalidRequestBeforeCreatingSession(
			int width, int height, Integer length, String pgNm) {
		RequestStub request = new RequestStub();
		ResponseStub response = new ResponseStub();

		controller.generate(request.proxy(), response.proxy(), width, height, length, null, pgNm);

		assertEquals(400, response.status);
		assertEquals(0, response.content.size());
		assertNull(request.session);
	}

	private static Stream<Arguments> invalidRequests() {
		return Stream.of(
				Arguments.of(49, 50, 5, "test"),
				Arguments.of(501, 50, 5, "test"),
				Arguments.of(150, 29, 5, "test"),
				Arguments.of(150, 201, 5, "test"),
				Arguments.of(150, 50, 3, "test"),
				Arguments.of(150, 50, 11, "test"),
				Arguments.of(150, 50, 5, ""),
				Arguments.of(150, 50, 5, "a".repeat(51)));
	}

	private void assertPng(byte[] content) {
		assertTrue(content.length > PNG_SIGNATURE.length);
		byte[] signature = new byte[PNG_SIGNATURE.length];
		System.arraycopy(content, 0, signature, 0, PNG_SIGNATURE.length);
		assertArrayEquals(PNG_SIGNATURE, signature);
	}

	private void assertCaptcha(RequestStub request, String pgNm, int expectedLength) {
		assertNotNull(request.session);
		String captcha = (String) request.sessionAttributes.get("captcha" + pgNm);
		assertNotNull(captcha);
		assertEquals(expectedLength, captcha.length());
		assertTrue(captcha.matches("[A-Za-z0-9]+"));
	}

	private static Object defaultValue(Class<?> returnType) {
		if (!returnType.isPrimitive()) {
			return null;
		}
		if (returnType == boolean.class) {
			return false;
		}
		if (returnType == char.class) {
			return '\0';
		}
		return 0;
	}

	private static class RequestStub {

		private final Map<String, Object> sessionAttributes = new HashMap<>();
		private HttpSession session;

		private HttpServletRequest proxy() {
			return (HttpServletRequest) Proxy.newProxyInstance(
					HttpServletRequest.class.getClassLoader(),
					new Class<?>[] { HttpServletRequest.class },
					(proxy, method, args) -> {
						if ("getSession".equals(method.getName())) {
							boolean create = args == null || args.length == 0 || Boolean.TRUE.equals(args[0]);
							if (session == null && create) {
								session = sessionProxy();
							}
							return session;
						}
						return defaultValue(method.getReturnType());
					});
		}

		private HttpSession sessionProxy() {
			return (HttpSession) Proxy.newProxyInstance(
					HttpSession.class.getClassLoader(),
					new Class<?>[] { HttpSession.class },
					(proxy, method, args) -> {
						if ("setAttribute".equals(method.getName())) {
							sessionAttributes.put((String) args[0], args[1]);
							return null;
						}
						if ("getAttribute".equals(method.getName())) {
							return sessionAttributes.get(args[0]);
						}
						return defaultValue(method.getReturnType());
					});
		}
	}

	private static class ResponseStub {

		private int status = HttpServletResponse.SC_OK;
		private String contentType;
		private final ByteArrayOutputStream content = new ByteArrayOutputStream();
		private final ServletOutputStream outputStream = new ServletOutputStream() {
			@Override
			public void write(int value) {
				content.write(value);
			}

			@Override
			public boolean isReady() {
				return true;
			}

			@Override
			public void setWriteListener(WriteListener writeListener) {
				// Synchronous test output stream.
			}
		};

		private HttpServletResponse proxy() {
			return (HttpServletResponse) Proxy.newProxyInstance(
					HttpServletResponse.class.getClassLoader(),
					new Class<?>[] { HttpServletResponse.class },
					(proxy, method, args) -> {
						if ("setStatus".equals(method.getName())) {
							status = (Integer) args[0];
							return null;
						}
						if ("setContentType".equals(method.getName())) {
							contentType = (String) args[0];
							return null;
						}
						if ("getOutputStream".equals(method.getName())) {
							return outputStream;
						}
						return defaultValue(method.getReturnType());
					});
		}
	}
}
