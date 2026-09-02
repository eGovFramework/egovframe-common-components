package egovframework.com.cmm.web;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import egovframework.com.cmm.service.EgovProperties;

/**
 * EgovMultipartResolver가 업로드 설정을 요청당 한 번만 읽는지 검증한다.
 */
class EgovMultipartResolverConfigReadTest {

	private static final String BASE_CONFIG =
		"Globals.fileUpload.Extensions = .png\n"
		+ "Globals.fileUpload.maxFileCount = 10\n"
		+ "Globals.fileUpload.maxSize = 104857600\n";

	@Test
	void maxFileSizeIsReadOncePerRequestNotOncePerFile() throws Exception {
		Path config = Paths.get(EgovProperties.GLOBALS_PROPERTIES_FILE);
		byte[] original = Files.readAllBytes(config);
		try {
			Files.write(config, BASE_CONFIG.getBytes(StandardCharsets.UTF_8));

			// 첫 번째 파일을 검증하는 도중 설정 파일의 최대 크기를 1바이트로 바꾼다.
			// 최대 크기를 파일마다 다시 읽으면 뒤이은 크기 검증이 바뀐 값을 보게 된다.
			Runnable shrinkMaxSize = () -> {
				try {
					Files.write(config, BASE_CONFIG.replace("104857600", "1").getBytes(StandardCharsets.UTF_8));
				} catch (Exception e) {
					throw new IllegalStateException(e);
				}
			};

			MultipartHttpServletRequest request = multipartRequest(
				file("first.png", 100L, shrinkMaxSize),
				file("second.png", 100L, null));

			assertDoesNotThrow(() -> validateUploadedFiles(request));
		} finally {
			Files.write(config, original);
		}
	}

	@Test
	void fileLargerThanMaxSizeIsStillRejected() throws Exception {
		Path config = Paths.get(EgovProperties.GLOBALS_PROPERTIES_FILE);
		byte[] original = Files.readAllBytes(config);
		try {
			Files.write(config, BASE_CONFIG.replace("104857600", "99").getBytes(StandardCharsets.UTF_8));

			MultipartHttpServletRequest request = multipartRequest(file("big.png", 100L, null));

			assertThrows(SecurityException.class, () -> validateUploadedFiles(request));
		} finally {
			Files.write(config, original);
		}
	}

	private static void validateUploadedFiles(MultipartHttpServletRequest request) throws Exception {
		Method validate = EgovMultipartResolver.class
			.getDeclaredMethod("validateUploadedFiles", MultipartHttpServletRequest.class);
		validate.setAccessible(true);
		try {
			validate.invoke(new EgovMultipartResolver(), request);
		} catch (InvocationTargetException e) {
			throw e.getCause() instanceof Exception ? (Exception)e.getCause() : e;
		}
	}

	private static MultipartFile file(String name, long size, Runnable onNameRead) {
		return (MultipartFile)Proxy.newProxyInstance(
			EgovMultipartResolverConfigReadTest.class.getClassLoader(),
			new Class<?>[] {MultipartFile.class},
			(proxy, method, args) -> {
				switch (method.getName()) {
					case "getOriginalFilename":
						if (onNameRead != null) {
							onNameRead.run();
						}
						return name;
					case "getName":
						return "atchFile";
					case "isEmpty":
						return Boolean.FALSE;
					case "getSize":
						return size;
					case "hashCode":
						return System.identityHashCode(proxy);
					case "equals":
						return proxy == args[0];
					case "toString":
						return name;
					default:
						return null;
				}
			});
	}

	private static MultipartHttpServletRequest multipartRequest(MultipartFile... files) {
		List<MultipartFile> list = new ArrayList<>();
		for (MultipartFile file : files) {
			list.add(file);
		}
		Map<String, List<MultipartFile>> map = new LinkedHashMap<>();
		map.put("atchFile", list);
		return (MultipartHttpServletRequest)Proxy.newProxyInstance(
			EgovMultipartResolverConfigReadTest.class.getClassLoader(),
			new Class<?>[] {MultipartHttpServletRequest.class},
			(proxy, method, args) -> {
				switch (method.getName()) {
					case "getMultiFileMap":
						return new LinkedMultiValueMap<>(map);
					case "hashCode":
						return System.identityHashCode(proxy);
					case "equals":
						return proxy == args[0];
					case "toString":
						return "multipartRequest";
					default:
						return null;
				}
			});
	}
}
