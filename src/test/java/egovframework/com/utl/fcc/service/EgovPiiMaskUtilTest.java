package egovframework.com.utl.fcc.service;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

/**
 * EgovPiiMaskUtil 단위 테스트
 */
public class EgovPiiMaskUtilTest {

    // -----------------------------------------------------------------------
    // maskJuminNo
    // -----------------------------------------------------------------------

    @Test
    void 주민번호_정상_마스킹() {
        assertEquals("901010-1******", EgovPiiMaskUtil.maskJuminNo("901010-1234567"));
    }

    @Test
    void 주민번호_null_원본반환() {
        assertNull(EgovPiiMaskUtil.maskJuminNo(null));
    }

    @Test
    void 주민번호_빈문자_원본반환() {
        assertEquals("", EgovPiiMaskUtil.maskJuminNo(""));
    }

    @Test
    void 주민번호_미매칭_원본반환() {
        assertEquals("ABC", EgovPiiMaskUtil.maskJuminNo("ABC"));
    }

    // -----------------------------------------------------------------------
    // maskPhoneNo
    // -----------------------------------------------------------------------

    @Test
    void 휴대폰_하이픈_가운데마스킹() {
        assertEquals("010-****-5678", EgovPiiMaskUtil.maskPhoneNo("010-1234-5678"));
    }

    @Test
    void 휴대폰_하이픈_3자리가운데() {
        assertEquals("010-***-5678", EgovPiiMaskUtil.maskPhoneNo("010-123-5678"));
    }

    @Test
    void 전화번호_null_원본반환() {
        assertNull(EgovPiiMaskUtil.maskPhoneNo(null));
    }

    @Test
    void 전화번호_빈문자_원본반환() {
        assertEquals("", EgovPiiMaskUtil.maskPhoneNo(""));
    }

    @Test
    void 전화번호_미매칭_원본반환() {
        assertEquals("없는번호", EgovPiiMaskUtil.maskPhoneNo("없는번호"));
    }

    @Test
    void 전화번호_하이픈없이_11자리_마스킹() {
        // 하이픈 없이 11자리: 010****5678 형태로 가운데 마스킹
        assertEquals("010****5678", EgovPiiMaskUtil.maskPhoneNo("01012345678"));
        assertEquals("010****5678", EgovPiiMaskUtil.maskPhoneNo("01001235678"));
    }

    // -----------------------------------------------------------------------
    // maskEmail
    // -----------------------------------------------------------------------

    @Test
    void 이메일_정상_마스킹() {
        assertEquals("ab***@domain.com", EgovPiiMaskUtil.maskEmail("abcde@domain.com"));
    }

    @Test
    void 이메일_로컬파트_2자이하() {
        assertEquals("a@x.com", EgovPiiMaskUtil.maskEmail("a@x.com"));
    }

    @Test
    void 이메일_로컬파트_2자() {
        assertEquals("ab@test.com", EgovPiiMaskUtil.maskEmail("ab@test.com"));
    }

    @Test
    void 이메일_null_원본반환() {
        assertNull(EgovPiiMaskUtil.maskEmail(null));
    }

    @Test
    void 이메일_빈문자_원본반환() {
        assertEquals("", EgovPiiMaskUtil.maskEmail(""));
    }

    @Test
    void 이메일_미매칭_원본반환() {
        assertEquals("notanemail", EgovPiiMaskUtil.maskEmail("notanemail"));
    }

    // -----------------------------------------------------------------------
    // maskCardNo
    // -----------------------------------------------------------------------

    @Test
    void 카드번호_하이픈구분_마스킹() {
        assertEquals("1234-****-****-3456", EgovPiiMaskUtil.maskCardNo("1234-5678-9012-3456"));
    }

    @Test
    void 카드번호_공백구분_마스킹() {
        assertEquals("1234 **** **** 3456", EgovPiiMaskUtil.maskCardNo("1234 5678 9012 3456"));
    }

    @Test
    void 카드번호_null_원본반환() {
        assertNull(EgovPiiMaskUtil.maskCardNo(null));
    }

    @Test
    void 카드번호_빈문자_원본반환() {
        assertEquals("", EgovPiiMaskUtil.maskCardNo(""));
    }

    @Test
    void 카드번호_미매칭_원본반환() {
        assertEquals("1234-5678", EgovPiiMaskUtil.maskCardNo("1234-5678"));
    }

    // -----------------------------------------------------------------------
    // maskBizRegNo
    // -----------------------------------------------------------------------

    @Test
    void 사업자번호_정상_마스킹() {
        assertEquals("123-45-*****", EgovPiiMaskUtil.maskBizRegNo("123-45-67890"));
    }

    @Test
    void 사업자번호_null_원본반환() {
        assertNull(EgovPiiMaskUtil.maskBizRegNo(null));
    }

    @Test
    void 사업자번호_빈문자_원본반환() {
        assertEquals("", EgovPiiMaskUtil.maskBizRegNo(""));
    }

    @Test
    void 사업자번호_미매칭_원본반환() {
        assertEquals("1234567", EgovPiiMaskUtil.maskBizRegNo("1234567"));
    }

    // -----------------------------------------------------------------------
    // maskPrivateIp
    // -----------------------------------------------------------------------

    @Test
    void 사설IP_192168_마스킹() {
        assertEquals("192.168.1.***", EgovPiiMaskUtil.maskPrivateIp("192.168.1.100"));
    }

    @Test
    void 사설IP_10대역_마스킹() {
        assertEquals("10.0.0.***", EgovPiiMaskUtil.maskPrivateIp("10.0.0.1"));
    }

    @Test
    void 사설IP_172대역_마스킹() {
        assertEquals("172.16.0.***", EgovPiiMaskUtil.maskPrivateIp("172.16.0.254"));
    }

    @Test
    void 공인IP_미마스킹() {
        assertEquals("8.8.8.8", EgovPiiMaskUtil.maskPrivateIp("8.8.8.8"));
    }

    @Test
    void 사설IP_null_원본반환() {
        assertNull(EgovPiiMaskUtil.maskPrivateIp(null));
    }

    // -----------------------------------------------------------------------
    // maskAll — 혼합 텍스트
    // -----------------------------------------------------------------------

    @Test
    void maskAll_혼합텍스트_전체마스킹() {
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
    void maskAll_null_null반환() {
        assertNull(EgovPiiMaskUtil.maskAll(null));
    }

    @Test
    void maskAll_개인정보없음_원본유지() {
        String text = "일반 텍스트입니다. 개인정보 없음.";
        assertEquals(text, EgovPiiMaskUtil.maskAll(text));
    }
}
