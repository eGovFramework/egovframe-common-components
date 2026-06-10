package egovframework.com.utl.fcc.service;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * EgovPiiMaskUtil 단위 테스트
 */
public class EgovPiiMaskUtilTest {

    // -----------------------------------------------------------------------
    // maskJuminNo
    // -----------------------------------------------------------------------

    @Test
    @DisplayName("주민번호 정상 마스킹")
    void maskJuminNo_masksNormalValue() {
        assertEquals("901010-1******", EgovPiiMaskUtil.maskJuminNo("901010-1234567"));
    }

    @Test
    @DisplayName("주민번호 null 원본반환")
    void maskJuminNo_nullReturnsOriginal() {
        assertNull(EgovPiiMaskUtil.maskJuminNo(null));
    }

    @Test
    @DisplayName("주민번호 빈문자 원본반환")
    void maskJuminNo_emptyReturnsOriginal() {
        assertEquals("", EgovPiiMaskUtil.maskJuminNo(""));
    }

    @Test
    @DisplayName("주민번호 미매칭 원본반환")
    void maskJuminNo_noMatchReturnsOriginal() {
        assertEquals("ABC", EgovPiiMaskUtil.maskJuminNo("ABC"));
    }

    // -----------------------------------------------------------------------
    // maskPhoneNo
    // -----------------------------------------------------------------------

    @Test
    @DisplayName("휴대폰 하이픈 가운데마스킹")
    void maskPhoneNo_hyphenMasksMiddle() {
        assertEquals("010-****-5678", EgovPiiMaskUtil.maskPhoneNo("010-1234-5678"));
    }

    @Test
    @DisplayName("휴대폰 하이픈 3자리가운데")
    void maskPhoneNo_hyphenThreeDigitMiddle() {
        assertEquals("010-***-5678", EgovPiiMaskUtil.maskPhoneNo("010-123-5678"));
    }

    @Test
    @DisplayName("전화번호 null 원본반환")
    void maskPhoneNo_nullReturnsOriginal() {
        assertNull(EgovPiiMaskUtil.maskPhoneNo(null));
    }

    @Test
    @DisplayName("전화번호 빈문자 원본반환")
    void maskPhoneNo_emptyReturnsOriginal() {
        assertEquals("", EgovPiiMaskUtil.maskPhoneNo(""));
    }

    @Test
    @DisplayName("전화번호 미매칭 원본반환")
    void maskPhoneNo_noMatchReturnsOriginal() {
        assertEquals("없는번호", EgovPiiMaskUtil.maskPhoneNo("없는번호"));
    }

    @Test
    @DisplayName("전화번호 하이픈없이 11자리 마스킹")
    void maskPhoneNo_plain11Digits() {
        // 하이픈 없이 11자리: 010****5678 형태로 가운데 마스킹
        assertEquals("010****5678", EgovPiiMaskUtil.maskPhoneNo("01012345678"));
        assertEquals("010****5678", EgovPiiMaskUtil.maskPhoneNo("01001235678"));
    }

    // -----------------------------------------------------------------------
    // maskEmail
    // -----------------------------------------------------------------------

    @Test
    @DisplayName("이메일 정상 마스킹")
    void maskEmail_masksNormalValue() {
        assertEquals("ab***@domain.com", EgovPiiMaskUtil.maskEmail("abcde@domain.com"));
    }

    @Test
    @DisplayName("이메일 로컬파트 2자이하")
    void maskEmail_localPartTwoCharsOrLess() {
        assertEquals("a@x.com", EgovPiiMaskUtil.maskEmail("a@x.com"));
    }

    @Test
    @DisplayName("이메일 로컬파트 2자")
    void maskEmail_localPartTwoChars() {
        assertEquals("ab@test.com", EgovPiiMaskUtil.maskEmail("ab@test.com"));
    }

    @Test
    @DisplayName("이메일 null 원본반환")
    void maskEmail_nullReturnsOriginal() {
        assertNull(EgovPiiMaskUtil.maskEmail(null));
    }

    @Test
    @DisplayName("이메일 빈문자 원본반환")
    void maskEmail_emptyReturnsOriginal() {
        assertEquals("", EgovPiiMaskUtil.maskEmail(""));
    }

    @Test
    @DisplayName("이메일 미매칭 원본반환")
    void maskEmail_noMatchReturnsOriginal() {
        assertEquals("notanemail", EgovPiiMaskUtil.maskEmail("notanemail"));
    }

    // -----------------------------------------------------------------------
    // maskCardNo
    // -----------------------------------------------------------------------

    @Test
    @DisplayName("카드번호 하이픈구분 마스킹")
    void maskCardNo_hyphenSeparated() {
        assertEquals("1234-****-****-3456", EgovPiiMaskUtil.maskCardNo("1234-5678-9012-3456"));
    }

    @Test
    @DisplayName("카드번호 공백구분 마스킹")
    void maskCardNo_spaceSeparated() {
        assertEquals("1234 **** **** 3456", EgovPiiMaskUtil.maskCardNo("1234 5678 9012 3456"));
    }

    @Test
    @DisplayName("카드번호 null 원본반환")
    void maskCardNo_nullReturnsOriginal() {
        assertNull(EgovPiiMaskUtil.maskCardNo(null));
    }

    @Test
    @DisplayName("카드번호 빈문자 원본반환")
    void maskCardNo_emptyReturnsOriginal() {
        assertEquals("", EgovPiiMaskUtil.maskCardNo(""));
    }

    @Test
    @DisplayName("카드번호 미매칭 원본반환")
    void maskCardNo_noMatchReturnsOriginal() {
        assertEquals("1234-5678", EgovPiiMaskUtil.maskCardNo("1234-5678"));
    }

    // -----------------------------------------------------------------------
    // maskBizRegNo
    // -----------------------------------------------------------------------

    @Test
    @DisplayName("사업자번호 정상 마스킹")
    void maskBizRegNo_masksNormalValue() {
        assertEquals("123-45-*****", EgovPiiMaskUtil.maskBizRegNo("123-45-67890"));
    }

    @Test
    @DisplayName("사업자번호 null 원본반환")
    void maskBizRegNo_nullReturnsOriginal() {
        assertNull(EgovPiiMaskUtil.maskBizRegNo(null));
    }

    @Test
    @DisplayName("사업자번호 빈문자 원본반환")
    void maskBizRegNo_emptyReturnsOriginal() {
        assertEquals("", EgovPiiMaskUtil.maskBizRegNo(""));
    }

    @Test
    @DisplayName("사업자번호 미매칭 원본반환")
    void maskBizRegNo_noMatchReturnsOriginal() {
        assertEquals("1234567", EgovPiiMaskUtil.maskBizRegNo("1234567"));
    }

    // -----------------------------------------------------------------------
    // maskPrivateIp
    // -----------------------------------------------------------------------

    @Test
    @DisplayName("사설IP 192168 마스킹")
    void maskPrivateIp_192_168() {
        assertEquals("192.168.1.***", EgovPiiMaskUtil.maskPrivateIp("192.168.1.100"));
    }

    @Test
    @DisplayName("사설IP 10대역 마스킹")
    void maskPrivateIp_10Range() {
        assertEquals("10.0.0.***", EgovPiiMaskUtil.maskPrivateIp("10.0.0.1"));
    }

    @Test
    @DisplayName("사설IP 172대역 마스킹")
    void maskPrivateIp_172Range() {
        assertEquals("172.16.0.***", EgovPiiMaskUtil.maskPrivateIp("172.16.0.254"));
    }

    @Test
    @DisplayName("공인IP 미마스킹")
    void maskPrivateIp_publicIpNotMasked() {
        assertEquals("8.8.8.8", EgovPiiMaskUtil.maskPrivateIp("8.8.8.8"));
    }

    @Test
    @DisplayName("사설IP null 원본반환")
    void maskPrivateIp_nullReturnsOriginal() {
        assertNull(EgovPiiMaskUtil.maskPrivateIp(null));
    }

    // -----------------------------------------------------------------------
    // maskAll — 혼합 텍스트
    // -----------------------------------------------------------------------

    @Test
    @DisplayName("maskAll 혼합텍스트 전체마스킹")
    void maskAll_mixedTextMasksAll() {
        String input = "사용자 홍길동(901010-1234567), 연락처: 010-1234-5678, "
            + "이메일: hong@example.com, 카드: 1234-5678-9012-3456, "
            + "사업자: 123-45-67890, IP: 192.168.0.10";

        String result = EgovPiiMaskUtil.maskAll(input);

        assertTrue(result.contains("901010-1******"), "주민번호 마스킹");
        assertTrue(result.contains("010-****-5678"), "전화번호 마스킹");
        assertTrue(result.contains("ho**@example.com"), "이메일 마스킹");
        assertTrue(result.contains("1234-****-****-3456"), "카드번호 마스킹");
        assertTrue(result.contains("123-45-*****"), "사업자번호 마스킹");
        assertTrue(result.contains("192.168.0.***"), "사설IP 마스킹");
    }

    @Test
    @DisplayName("maskAll 하이픈 없는 전화번호도 마스킹")
    void maskAll_masksPlainPhoneNumbers() {
        String input = "연락처 01012345678 / 보조 0212345678 끝";
        String result = EgovPiiMaskUtil.maskAll(input);
        assertTrue(result.contains("010****5678"), "하이픈 없는 11자리 휴대폰 마스킹");
        assertTrue(result.contains("021***5678"), "하이픈 없는 10자리 번호 마스킹");
    }

    @Test
    @DisplayName("maskAll null null반환")
    void maskAll_nullReturnsNull() {
        assertNull(EgovPiiMaskUtil.maskAll(null));
    }

    @Test
    @DisplayName("maskAll 개인정보없음 원본유지")
    void maskAll_noPiiKeepsOriginal() {
        String text = "일반 텍스트입니다. 개인정보 없음.";
        assertEquals(text, EgovPiiMaskUtil.maskAll(text));
    }
}
