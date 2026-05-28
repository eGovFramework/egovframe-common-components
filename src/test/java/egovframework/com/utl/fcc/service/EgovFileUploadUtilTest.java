package egovframework.com.utl.fcc.service;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Proxy;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

class EgovFileUploadUtilTest {

	@TempDir
	Path uploadDir;

	@Test
	void uploadFilesExtRejectsFilesExceedingMaxFileSizeBeforeSaving() throws Exception {
		MultipartHttpServletRequest request = multipartRequest(multipartFile("file", "too-big.png", "image/png", new byte[] {1, 2, 3}));

		SecurityException exception = assertThrows(SecurityException.class,
			() -> EgovFileUploadUtil.uploadFilesExt(request, uploadDir.toString(), 2L, ".png"));

		assertEquals("File size exceeds maximum allowed size.", exception.getMessage());
		assertEquals(0, countRegularFiles(uploadDir));
	}

	@Test
	void uploadFilesExtStoresFilesAtMaxFileSize() throws Exception {
		byte[] content = new byte[] {1, 2};
		MultipartHttpServletRequest request = multipartRequest(multipartFile("file", "image.png", "image/png", content));

		List<EgovFormBasedFileVo> files = EgovFileUploadUtil.uploadFilesExt(request, uploadDir.toString(), 2L, ".png");

		assertEquals(1, files.size());
		EgovFormBasedFileVo file = files.get(0);
		assertEquals(2L, file.getSize());

		Path storedFile = uploadDir.resolve(file.getServerSubPath()).resolve(file.getPhysicalName() + "_upfile");
		assertTrue(Files.isRegularFile(storedFile));
		assertArrayEquals(content, Files.readAllBytes(storedFile));
	}

	private MultipartHttpServletRequest multipartRequest(MultipartFile file) {
		return (MultipartHttpServletRequest)Proxy.newProxyInstance(
			MultipartHttpServletRequest.class.getClassLoader(),
			new Class<?>[] {MultipartHttpServletRequest.class},
			(proxy, method, args) -> {
				if ("getFileNames".equals(method.getName())) {
					return Collections.singleton(file.getName()).iterator();
				}
				if ("getFile".equals(method.getName())) {
					return file.getName().equals(args[0]) ? file : null;
				}
				if ("toString".equals(method.getName())) {
					return "MultipartHttpServletRequest proxy for " + file.getOriginalFilename();
				}
				if ("hashCode".equals(method.getName())) {
					return System.identityHashCode(proxy);
				}
				if ("equals".equals(method.getName())) {
					return proxy == args[0];
				}
				throw new UnsupportedOperationException(method.getName());
			});
	}

	private MultipartFile multipartFile(String name, String originalFilename, String contentType, byte[] content) {
		return new MultipartFile() {
			@Override
			public String getName() {
				return name;
			}

			@Override
			public String getOriginalFilename() {
				return originalFilename;
			}

			@Override
			public String getContentType() {
				return contentType;
			}

			@Override
			public boolean isEmpty() {
				return content.length == 0;
			}

			@Override
			public long getSize() {
				return content.length;
			}

			@Override
			public byte[] getBytes() {
				return content.clone();
			}

			@Override
			public InputStream getInputStream() {
				return new ByteArrayInputStream(content);
			}

			@Override
			public void transferTo(File dest) throws IOException {
				Files.write(dest.toPath(), content);
			}
		};
	}

	private long countRegularFiles(Path path) throws Exception {
		try (Stream<Path> files = Files.walk(path)) {
			return files.filter(Files::isRegularFile).count();
		}
	}

}
