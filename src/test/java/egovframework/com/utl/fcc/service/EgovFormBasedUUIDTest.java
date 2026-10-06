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
        assertEquals(4, uuid.version(), "무작위 salt가 섞이므로 무작위 기반 버전");
        assertEquals(2, uuid.variant(), "IETF variant");
    }

    @Test
    @DisplayName("빈 배열 UUID 처리")
    void testNameUUIDFromBytesWithEmptyArray() {
        EgovFormBasedUUID uuid = assertDoesNotThrow(() -> EgovFormBasedUUID.nameUUIDFromBytes(new byte[0]),
                "빈 배열 입력도 예외 없이 처리해야 한다.");

        assertTrue(uuid.toString().matches(UUID_REGEX), "빈 배열 입력의 표준 UUID 문자열 형식");
    }

    @Test
    @DisplayName("같은 이름이라도 호출마다 다른 UUID를 만든다")
    void testNameUUIDFromBytesIsNotDeterministic() {
        byte[] name = "egovframe".getBytes(java.nio.charset.StandardCharsets.UTF_8);

        EgovFormBasedUUID first = EgovFormBasedUUID.nameUUIDFromBytes(name);
        EgovFormBasedUUID second = EgovFormBasedUUID.nameUUIDFromBytes(name);

        // 매 호출 SecureRandom salt가 섞이므로 이름 기반 UUID의 전제인 결정성이 성립하지 않는다.
        assertNotEquals(first.toString(), second.toString(), "같은 이름의 두 호출 결과");
        assertEquals(4, first.version(), "무작위 기반 버전");
        assertEquals(4, second.version(), "무작위 기반 버전");
    }
}
