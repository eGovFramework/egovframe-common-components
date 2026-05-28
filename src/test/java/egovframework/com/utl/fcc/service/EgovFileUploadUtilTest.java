package egovframework.com.utl.fcc.service;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.mock.web.MockMultipartHttpServletRequest;

class EgovFileUploadUtilTest {

	@TempDir
	Path uploadDir;

	@Test
	void uploadFilesExtRejectsFilesExceedingMaxFileSizeBeforeSaving() throws Exception {
		MockMultipartHttpServletRequest request = new MockMultipartHttpServletRequest();
		request.addFile(new MockMultipartFile("file", "too-big.png", "image/png", new byte[] {1, 2, 3}));

		SecurityException exception = assertThrows(SecurityException.class,
			() -> EgovFileUploadUtil.uploadFilesExt(request, uploadDir.toString(), 2L, ".png"));

		assertEquals("File size exceeds maximum allowed size.", exception.getMessage());
		assertEquals(0, countRegularFiles(uploadDir));
	}

	@Test
	void uploadFilesExtStoresFilesAtMaxFileSize() throws Exception {
		byte[] content = new byte[] {1, 2};
		MockMultipartHttpServletRequest request = new MockMultipartHttpServletRequest();
		request.addFile(new MockMultipartFile("file", "image.png", "image/png", content));

		List<EgovFormBasedFileVo> files = EgovFileUploadUtil.uploadFilesExt(request, uploadDir.toString(), 2L, ".png");

		assertEquals(1, files.size());
		EgovFormBasedFileVo file = files.get(0);
		assertEquals(2L, file.getSize());

		Path storedFile = uploadDir.resolve(file.getServerSubPath()).resolve(file.getPhysicalName() + "_upfile");
		assertTrue(Files.isRegularFile(storedFile));
		assertArrayEquals(content, Files.readAllBytes(storedFile));
	}

	private long countRegularFiles(Path path) throws Exception {
		try (Stream<Path> files = Files.walk(path)) {
			return files.filter(Files::isRegularFile).count();
		}
	}

}
