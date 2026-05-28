package egovframework.com.cmm.util;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import java.io.ByteArrayInputStream;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;

import org.junit.jupiter.api.Test;

/**
 * EgovResourceCloseHelper 리소스 닫기 유틸 테스트
 */
public class EgovResourceCloseHelperTest {

	@Test
	void testClose_nullResource() {
		assertDoesNotThrow(() -> EgovResourceCloseHelper.close((Closeable) null));
	}

	@Test
	void testClose_validStream() {
		InputStream in = new ByteArrayInputStream(new byte[0]);
		assertDoesNotThrow(() -> EgovResourceCloseHelper.close(in));
	}

	@Test
	void testClose_multipleResources() {
		InputStream in1 = new ByteArrayInputStream(new byte[0]);
		InputStream in2 = new ByteArrayInputStream(new byte[0]);
		assertDoesNotThrow(() -> EgovResourceCloseHelper.close(in1, in2));
	}

	@Test
	void testClose_nullAmongResources() {
		InputStream in1 = new ByteArrayInputStream(new byte[0]);
		assertDoesNotThrow(() -> EgovResourceCloseHelper.close(in1, null));
	}

	@Test
	void testClose_throwingResource() {
		Closeable throwingResource = () -> { throw new IOException("close error"); };
		assertDoesNotThrow(() -> EgovResourceCloseHelper.close(throwingResource));
	}
}
