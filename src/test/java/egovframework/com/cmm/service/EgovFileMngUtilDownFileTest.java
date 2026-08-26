package egovframework.com.cmm.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;

import jakarta.servlet.http.HttpServletRequest;

/**
 * downFile(HttpServletRequest, HttpServletResponse)의 원본 파일명 처리(resolveRequestAttribute,
 * buildContentDispositionHeader)만 검증한다. downFile() 자체는 클래스 로딩 시점에 고정되는 정적
 * Globals.fileStorePath(테스트 설정상 "C:/egovframework/upload/")를 기준으로 실제 파일 존재 여부를 확인하므로
 * 이번 범위에서 제외했다.
 *
 * <p>MockHttpServletRequest(spring-test)는 Servlet 6.0 이상을 요구하므로, Servlet 5.0 환경과
 * 호환되는 JDK Proxy 기반 스텁을 사용한다.</p>
 */
class EgovFileMngUtilDownFileTest {

	@Test
	void buildContentDispositionHeader_reflectsOrginFileAttribute() throws Exception {
		HttpServletRequest request = stubRequest("orginFile", "원본파일.txt");

		String header = EgovFileMngUtil.buildContentDispositionHeader(request);

		assertEquals("attachment; filename=원본파일.txt", header);
	}

	@Test
	void buildContentDispositionHeader_stripsCarriageReturnAndNewLine() throws Exception {
		HttpServletRequest request = stubRequest("orginFile", "원본\r\n파일.txt");

		String header = EgovFileMngUtil.buildContentDispositionHeader(request);

		assertEquals("attachment; filename=원본파일.txt", header);
	}

	@Test
	void buildContentDispositionHeader_emptyFileName_whenAttributeMissing() throws Exception {
		HttpServletRequest request = stubRequest();

		String header = EgovFileMngUtil.buildContentDispositionHeader(request);

		assertEquals("attachment; filename=", header);
	}

	@Test
	void resolveRequestAttribute_returnsValue_whenPresent() {
		HttpServletRequest request = stubRequest("downFile", "stored-name.dat");

		assertEquals("stored-name.dat", EgovFileMngUtil.resolveRequestAttribute(request, "downFile"));
	}

	@Test
	void resolveRequestAttribute_returnsEmptyString_whenAbsent() {
		HttpServletRequest request = stubRequest();

		assertEquals("", EgovFileMngUtil.resolveRequestAttribute(request, "downFile"));
	}

	// -----------------------------------------------------------------------
	// 헬퍼: JDK Proxy 기반 HttpServletRequest 스텁 (Servlet 5.0 호환)
	// getAttribute(name)만 구현하고 나머지는 null 반환
	// -----------------------------------------------------------------------
	private static HttpServletRequest stubRequest(String... attributePairs) {
		Map<String, String> attributes = new HashMap<>();
		for (int i = 0; i + 1 < attributePairs.length; i += 2) {
			attributes.put(attributePairs[i], attributePairs[i + 1]);
		}
		return (HttpServletRequest) Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(),
				new Class<?>[] { HttpServletRequest.class }, (proxy, method, args) -> {
					if ("getAttribute".equals(method.getName())) {
						return attributes.get((String) args[0]);
					}
					return null;
				});
	}
}
