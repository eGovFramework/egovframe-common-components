package egovframework.com.utl.fcc.service;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Proxy;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.WriteListener;
import jakarta.servlet.http.HttpServletResponse;

class EgovFormBasedFileUtilTest {

	@TempDir
	File tempDir;

	@Test
	void getTodayString_matchesCurrentDatePattern() {
		String expected = new SimpleDateFormat("yyyyMMdd", Locale.getDefault()).format(new Date());

		assertEquals(expected, EgovFormBasedFileUtil.getTodayString());
	}

	@Test
	void getPhysicalFileName_isUppercaseHexWithoutDashes() {
		String name = EgovFormBasedFileUtil.getPhysicalFileName();

		assertEquals(32, name.length());
		assertTrue(name.matches("[0-9A-F]+"), "물리적 파일명은 대문자 16진수여야 한다: " + name);
	}

	@Test
	void getPhysicalFileName_generatesDistinctValues() {
		assertNotEquals(EgovFormBasedFileUtil.getPhysicalFileName(), EgovFormBasedFileUtil.getPhysicalFileName());
	}

	@Test
	void convert_returnsFilenameUnchanged() throws Exception {
		assertEquals("hello.txt", EgovFormBasedFileUtil.convert("hello.txt"));
	}

	@Test
	void getContentTypeWL_containsExpectedImageMimeTypes() {
		Map<String, String> whitelist = EgovFormBasedFileUtil.getContentTypeWL();

		assertEquals(4, whitelist.size());
		assertEquals("image/gif", whitelist.get("gif"));
		assertEquals("image/jpg", whitelist.get("jpg"));
		assertEquals("image/jpeg", whitelist.get("jpeg"));
		assertEquals("image/png", whitelist.get("png"));
	}

	@Test
	void saveFile_writesStreamContentAndReturnsByteCount() throws IOException {
		byte[] content = "hello world".getBytes(StandardCharsets.UTF_8);
		File target = new File(new File(tempDir, "sub/dir"), "saved.txt");

		long written;
		try (InputStream is = new ByteArrayInputStream(content)) {
			written = EgovFormBasedFileUtil.saveFile(is, target);
		}

		assertEquals(content.length, written);
		assertTrue(target.exists(), "부모 디렉토리가 없어도 생성 후 파일이 저장되어야 한다");
		assertArrayEquals(content, Files.readAllBytes(target.toPath()));
	}

	@Test
	void downloadFile_missingFile_throwsFileNotFoundException() {
		assertThrows(FileNotFoundException.class, () -> EgovFormBasedFileUtil.downloadFile(
				new ResponseStub().proxy(), tempDir.getAbsolutePath(), "2026", "NOPE", "orig.txt"));
	}

	@Test
	void downloadFile_existingFile_writesContentAndHeaders() throws Exception {
		writeTestFile("2026", "PHYS01", "download body");
		ResponseStub response = new ResponseStub();

		EgovFormBasedFileUtil.downloadFile(response.proxy(), tempDir.getAbsolutePath(), "2026", "PHYS01",
				"original name.txt");

		assertEquals("application/octet-stream", response.contentType);
		assertEquals("attachment; filename=\"original name.txt\";", response.headers.get("Content-Disposition"));
		assertArrayEquals("download body".getBytes(StandardCharsets.UTF_8), response.content.toByteArray());
	}

	@Test
	void viewFile_missingFile_throwsFileNotFoundException() {
		assertThrows(FileNotFoundException.class, () -> EgovFormBasedFileUtil.viewFile(new ResponseStub().proxy(),
				tempDir.getAbsolutePath(), "2026", "NOPE", "image/png"));
	}

	@Test
	void viewFile_whitelistedMimeType_isKeptAsIs() throws Exception {
		writeTestFile("2026", "PHYS02_upfile", "png bytes");
		ResponseStub response = new ResponseStub();

		EgovFormBasedFileUtil.viewFile(response.proxy(), tempDir.getAbsolutePath(), "2026", "PHYS02", "image/png");

		assertEquals("image/png", response.contentType);
		assertArrayEquals("png bytes".getBytes(StandardCharsets.UTF_8), response.content.toByteArray());
	}

	@Test
	void viewFile_nonWhitelistedMimeType_fallsBackToOctetStream() throws Exception {
		writeTestFile("2026", "PHYS03_upfile", "text bytes");
		ResponseStub response = new ResponseStub();

		EgovFormBasedFileUtil.viewFile(response.proxy(), tempDir.getAbsolutePath(), "2026", "PHYS03", "text/plain");

		assertEquals("application/octet-stream;", response.contentType);
	}

	@Test
	void viewFile_nullMimeType_fallsBackToOctetStream() throws Exception {
		writeTestFile("2026", "PHYS04_upfile", "text bytes");
		ResponseStub response = new ResponseStub();

		EgovFormBasedFileUtil.viewFile(response.proxy(), tempDir.getAbsolutePath(), "2026", "PHYS04", null);

		assertEquals("application/octet-stream;", response.contentType);
	}

	private void writeTestFile(String serverSubPath, String physicalName, String body) throws IOException {
		File dir = new File(tempDir, serverSubPath);
		dir.mkdirs();
		Files.write(new File(dir, physicalName).toPath(), body.getBytes(StandardCharsets.UTF_8));
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

	private static class ResponseStub {

		private String contentType;
		private final Map<String, String> headers = new HashMap<>();
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
			return (HttpServletResponse) Proxy.newProxyInstance(HttpServletResponse.class.getClassLoader(),
					new Class<?>[] { HttpServletResponse.class }, (proxy, method, args) -> {
						if ("setContentType".equals(method.getName())) {
							contentType = (String) args[0];
							return null;
						}
						if ("setHeader".equals(method.getName())) {
							headers.put((String) args[0], (String) args[1]);
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
