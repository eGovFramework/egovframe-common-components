package egovframework.com.utl.fcc.service;

import static org.junit.jupiter.api.Assertions.*;

import java.nio.charset.StandardCharsets;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * EgovFormBasedUUID 단위 테스트
 */
public class EgovFormBasedUUIDTest {

    private static final String UUID_REGEX = "[0-9a-f]{8}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{12}";

    @Test
    @DisplayName("name 기반 UUID 표준 형식")
    void testNameUUIDFromBytesReturnsStandardUuid() {
        EgovFormBasedUUID uuid = assertDoesNotThrow(
                () -> EgovFormBasedUUID.nameUUIDFromBytes("egovframework".getBytes(StandardCharsets.UTF_8)),
                "nameUUIDFromBytes는 예외 없이 UUID를 반환해야 한다.");

        assertTrue(uuid.toString().matches(UUID_REGEX), "표준 UUID 문자열 형식");
        assertEquals(3, uuid.version(), "name 기반 UUID 버전");
        assertEquals(2, uuid.variant(), "IETF variant");
    }

    @Test
    @DisplayName("빈 배열 UUID 처리")
    void testNameUUIDFromBytesWithEmptyArray() {
        EgovFormBasedUUID uuid = assertDoesNotThrow(() -> EgovFormBasedUUID.nameUUIDFromBytes(new byte[0]),
                "빈 배열 입력도 예외 없이 처리해야 한다.");

        assertTrue(uuid.toString().matches(UUID_REGEX), "빈 배열 입력의 표준 UUID 문자열 형식");
    }
}
