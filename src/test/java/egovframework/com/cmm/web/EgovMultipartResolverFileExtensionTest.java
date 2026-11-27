package egovframework.com.cmm.web;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

class EgovMultipartResolverFileExtensionTest {

	@ParameterizedTest
	@ValueSource(strings = {"png", "pdf", "README"})
	void rejectsFilenamesWithoutExtension(String filename) {
		SecurityException exception = assertThrows(SecurityException.class, () -> validateFile(filename));

		assertEquals("[No file extension] File extension not allowed.", exception.getMessage());
	}

	@Test
	void allowsFilenameWithUppercaseExtension() {
		assertDoesNotThrow(() -> validateFile("image.PNG"));
	}

	private void validateFile(String filename) throws Exception {
		MultipartFile file = new MockMultipartFile("file", filename, "application/octet-stream", new byte[] {1});
		Method validate = EgovMultipartResolver.class
			.getDeclaredMethod("validateFile", MultipartFile.class, String.class, long.class);
		validate.setAccessible(true);
		try {
			validate.invoke(new EgovMultipartResolver(), file, ".png,.pdf", 10L);
		} catch (InvocationTargetException e) {
			throw e.getCause() instanceof Exception ? (Exception)e.getCause() : e;
		}
	}
}
