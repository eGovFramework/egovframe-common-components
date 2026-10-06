package egovframework.com.utl.fcc.service;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.mock.web.MockMultipartHttpServletRequest;

class EgovFileUploadPrevalidationTest {

	@TempDir
	Path uploadDir;

	@ParameterizedTest
	@MethodSource("invalidFiles")
	void rejectsEntireBatchBeforeReadingOrSavingFiles(String filename, byte[] content, boolean invalidFirst,
			String message) throws Exception {
		Path existing = uploadDir.resolve("existing.txt");
		Files.writeString(existing, "existing content");
		AtomicInteger reads = new AtomicInteger();
		MockMultipartFile valid = new MockMultipartFile("valid", "valid.png", "image/png", new byte[] {1, 2}) {
			@Override
			public InputStream getInputStream() throws IOException {
				reads.incrementAndGet();
				return super.getInputStream();
			}
		};
		MockMultipartFile invalid = new MockMultipartFile("invalid", filename, null, content);
		MockMultipartHttpServletRequest request = new MockMultipartHttpServletRequest();
		request.addFile(invalidFirst ? invalid : valid);
		request.addFile(invalidFirst ? valid : invalid);

		SecurityException error = assertThrows(SecurityException.class,
				() -> EgovFileUploadUtil.uploadFilesExt(request, uploadDir.toString(), 2, ".png"));

		assertEquals(message, error.getMessage());
		assertEquals(0, reads.get());
		assertEquals("existing content", Files.readString(existing));
		try (Stream<Path> entries = Files.list(uploadDir)) {
			assertEquals(List.of(existing), entries.toList());
		}
	}

	private static Stream<Arguments> invalidFiles() {
		return Stream.of(false, true).flatMap(first -> Stream.of(
				Arguments.of("empty.exe", new byte[0], first, "Unacceptable file extension."),
				Arguments.of("blocked.exe", new byte[] {1}, first, "Unacceptable file extension."),
				Arguments.of("no-extension", new byte[] {1}, first, "Unacceptable file extension."),
				Arguments.of("large.png", new byte[] {1, 2, 3}, first, "File size exceeds maximum allowed size.")));
	}

	@Test
	void savesValidFilesInOrderWithOriginalContentAndNaming() throws Exception {
		MockMultipartHttpServletRequest request = new MockMultipartHttpServletRequest();
		request.addFile(new MockMultipartFile("first", "C:\\images\\first.PNG", "image/png", new byte[] {1, 2}));
		request.addFile(new MockMultipartFile("second", "second.png", "image/png", new byte[] {3}));

		List<EgovFormBasedFileVo> files = EgovFileUploadUtil.uploadFilesExt(request, uploadDir.toString(), 2, ".png");

		assertEquals(List.of("first.PNG", "second.png"), files.stream().map(EgovFormBasedFileVo::getFileName).toList());
		assertEquals(List.of(2L, 1L), files.stream().map(EgovFormBasedFileVo::getSize).toList());
		for (EgovFormBasedFileVo file : files) {
			assertEquals("image/png", file.getContentType());
			assertTrue(file.getServerSubPath().matches("[0-9]{8}"));
			assertTrue(file.getPhysicalName().endsWith(".png"));
		}
		assertArrayEquals(new byte[] {1, 2}, Files.readAllBytes(storedPath(files.get(0))));
		assertArrayEquals(new byte[] {3}, Files.readAllBytes(storedPath(files.get(1))));
		try (Stream<Path> paths = Files.walk(uploadDir)) {
			assertEquals(2, paths.filter(Files::isRegularFile).count());
		}
	}

	@Test
	void skipsUnnamedAndAllowedEmptyFilesWithoutChangingValidFileOrder() throws Exception {
		MockMultipartHttpServletRequest request = new MockMultipartHttpServletRequest();
		request.addFile(new MockMultipartFile("unnamed", "", null, new byte[] {1, 2, 3}));
		request.addFile(new MockMultipartFile("empty", "empty.png", "image/png", new byte[0]));
		request.addFile(new MockMultipartFile("valid", "valid.png", "image/png", new byte[] {1}));

		List<EgovFormBasedFileVo> files = EgovFileUploadUtil.uploadFilesExt(request, uploadDir.toString(), 2, ".png");

		assertEquals(1, files.size());
		assertEquals("valid.png", files.get(0).getFileName());
		assertArrayEquals(new byte[] {1}, Files.readAllBytes(storedPath(files.get(0))));
	}

	@Test
	void emptyRequestsDoNotCreateFiles() throws Exception {
		assertTrue(EgovFileUploadUtil.uploadFilesExt(null, uploadDir.toString(), 2, ".png").isEmpty());
		assertTrue(EgovFileUploadUtil.uploadFilesExt(new MockMultipartHttpServletRequest(),
				uploadDir.toString(), 2, ".png").isEmpty());
		try (Stream<Path> entries = Files.list(uploadDir)) {
			assertEquals(0, entries.count());
		}
	}

	private Path storedPath(EgovFormBasedFileVo file) {
		return uploadDir.resolve(file.getServerSubPath()).resolve(file.getPhysicalName() + "_upfile");
	}
}
