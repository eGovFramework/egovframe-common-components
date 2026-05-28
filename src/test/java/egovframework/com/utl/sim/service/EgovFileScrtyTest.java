package egovframework.com.utl.sim.service;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.nio.charset.StandardCharsets;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * EgovFileScrty 단위 테스트
 *
 * @author 공통컴포넌트 개발팀
 * @since 2026.05.28
 * @version 1.0
 * @see EgovFileScrty
 */
public class EgovFileScrtyTest {

    // ──────────────────────────────────────────────
    // encodeBinary / decodeBinary 라운드트립
    // ──────────────────────────────────────────────

    @Test
    @DisplayName("encodeBinary: 바이트 배열을 Base64 문자열로 인코딩한다")
    void testEncodeBinary_returnsBase64String() throws Exception {
        byte[] input = "egovframe".getBytes(StandardCharsets.UTF_8);
        String encoded = EgovFileScrty.encodeBinary(input);
        assertNotNull(encoded);
        // 기대값: echo -n "egovframe" | base64 → "ZWdvdmZyYW1l"
        assertEquals("ZWdvdmZyYW1l", encoded);
    }

    @Test
    @DisplayName("encodeBinary: null 입력 시 빈 문자열을 반환한다")
    void testEncodeBinary_nullInput_returnsEmpty() throws Exception {
        assertEquals("", EgovFileScrty.encodeBinary(null));
    }

    @Test
    @DisplayName("decodeBinary: Base64 문자열을 원래 바이트 배열로 복원한다")
    void testDecodeBinary_returnsOriginalBytes() throws Exception {
        byte[] original = "egovframe".getBytes(StandardCharsets.UTF_8);
        String encoded = EgovFileScrty.encodeBinary(original);
        byte[] decoded = EgovFileScrty.decodeBinary(encoded);
        assertArrayEquals(original, decoded);
    }

    @Test
    @DisplayName("encodeBinary → decodeBinary 라운드트립: 한글 문자열도 복원된다")
    void testRoundTrip_koreanString() throws Exception {
        byte[] original = "전자정부표준프레임워크".getBytes(StandardCharsets.UTF_8);
        String encoded = EgovFileScrty.encodeBinary(original);
        byte[] decoded = EgovFileScrty.decodeBinary(encoded);
        assertArrayEquals(original, decoded);
        assertEquals("전자정부표준프레임워크", new String(decoded, StandardCharsets.UTF_8));
    }

    @Test
    @DisplayName("encodeBinary: 빈 바이트 배열도 처리한다")
    void testEncodeBinary_emptyArray() throws Exception {
        String encoded = EgovFileScrty.encodeBinary(new byte[0]);
        assertNotNull(encoded);
        byte[] decoded = EgovFileScrty.decodeBinary(encoded);
        assertArrayEquals(new byte[0], decoded);
    }

    // ──────────────────────────────────────────────
    // encryptPassword(String, String) — SHA-256 + SALT(id)
    // ──────────────────────────────────────────────

    @Test
    @DisplayName("encryptPassword: 동일한 입력에 대해 항상 동일한 해시값을 반환한다(결정적)")
    void testEncryptPassword_deterministic() throws Exception {
        String result1 = EgovFileScrty.encryptPassword("password123", "userId");
        String result2 = EgovFileScrty.encryptPassword("password123", "userId");
        assertNotNull(result1);
        assertEquals(result1, result2);
    }

    @Test
    @DisplayName("encryptPassword: salt(id)가 다르면 결과가 달라진다")
    void testEncryptPassword_differentSaltProducesDifferentHash() throws Exception {
        String hash1 = EgovFileScrty.encryptPassword("password123", "user1");
        String hash2 = EgovFileScrty.encryptPassword("password123", "user2");
        assertFalse(hash1.equals(hash2), "salt가 다르면 해시가 달라야 한다");
    }

    @Test
    @DisplayName("encryptPassword: password가 null이면 빈 문자열을 반환한다")
    void testEncryptPassword_nullPassword_returnsEmpty() throws Exception {
        assertEquals("", EgovFileScrty.encryptPassword(null, "userId"));
    }

    @Test
    @DisplayName("encryptPassword: id가 null이면 빈 문자열을 반환한다")
    void testEncryptPassword_nullId_returnsEmpty() throws Exception {
        assertEquals("", EgovFileScrty.encryptPassword("password123", (String) null));
    }

    // ──────────────────────────────────────────────
    // encryptPassword(String, byte[]) + checkPassword
    // ──────────────────────────────────────────────

    @Test
    @DisplayName("checkPassword: 올바른 비밀번호와 salt로 검증하면 true를 반환한다")
    void testCheckPassword_correct_returnsTrue() throws Exception {
        byte[] salt = "randomSalt123".getBytes(StandardCharsets.UTF_8);
        String encoded = EgovFileScrty.encryptPassword("mySecret!", salt);
        assertTrue(EgovFileScrty.checkPassword("mySecret!", encoded, salt));
    }

    @Test
    @DisplayName("checkPassword: 잘못된 비밀번호로 검증하면 false를 반환한다")
    void testCheckPassword_wrongPassword_returnsFalse() throws Exception {
        byte[] salt = "randomSalt123".getBytes(StandardCharsets.UTF_8);
        String encoded = EgovFileScrty.encryptPassword("mySecret!", salt);
        assertFalse(EgovFileScrty.checkPassword("wrongPassword", encoded, salt));
    }

    @Test
    @DisplayName("checkPassword: salt가 달라지면 검증이 실패한다")
    void testCheckPassword_differentSalt_returnsFalse() throws Exception {
        byte[] salt1 = "saltA".getBytes(StandardCharsets.UTF_8);
        byte[] salt2 = "saltB".getBytes(StandardCharsets.UTF_8);
        String encoded = EgovFileScrty.encryptPassword("mySecret!", salt1);
        assertFalse(EgovFileScrty.checkPassword("mySecret!", encoded, salt2));
    }

    @Test
    @DisplayName("encryptPassword(byte[] salt): null 비밀번호 입력 시 빈 문자열을 반환한다")
    void testEncryptPasswordWithSalt_nullInput_returnsEmpty() throws Exception {
        byte[] salt = "anySalt".getBytes(StandardCharsets.UTF_8);
        assertEquals("", EgovFileScrty.encryptPassword(null, salt));
    }
}
