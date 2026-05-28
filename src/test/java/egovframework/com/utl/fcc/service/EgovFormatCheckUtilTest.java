package egovframework.com.utl.fcc.service;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

/**
 * EgovFormatCheckUtil 단위 테스트
 *
 * @author 공통컴포넌트 개발팀
 * @since 2025.05.28
 */
public class EgovFormatCheckUtilTest {

    // ─── checkFormatTell(String, String, String) ───────────────────────────

    @Test
    void 전화번호_3분할_서울_유효() {
        assertTrue(EgovFormatCheckUtil.checkFormatTell("02", "123", "4567"));
    }

    @Test
    void 전화번호_3분할_서울_중간4자리_유효() {
        assertTrue(EgovFormatCheckUtil.checkFormatTell("02", "1234", "5678"));
    }

    @Test
    void 전화번호_3분할_경기_031_유효() {
        assertTrue(EgovFormatCheckUtil.checkFormatTell("031", "123", "4567"));
    }

    @Test
    void 전화번호_3분할_070_유효() {
        assertTrue(EgovFormatCheckUtil.checkFormatTell("070", "123", "4567"));
    }

    @Test
    void 전화번호_3분할_0505_유효() {
        assertTrue(EgovFormatCheckUtil.checkFormatTell("0505", "123", "4567"));
    }

    @Test
    void 전화번호_3분할_미등록국번_무효() {
        assertFalse(EgovFormatCheckUtil.checkFormatTell("099", "123", "4567"));
    }

    @Test
    void 전화번호_3분할_중간번호_0으로시작_무효() {
        assertFalse(EgovFormatCheckUtil.checkFormatTell("031", "023", "4567"));
    }

    @Test
    void 전화번호_3분할_비숫자_포함_무효() {
        assertFalse(EgovFormatCheckUtil.checkFormatTell("031", "12a", "4567"));
    }

    @Test
    void 전화번호_3분할_서울_뒷번호_3자리_무효() {
        assertFalse(EgovFormatCheckUtil.checkFormatTell("02", "123", "456"));
    }

    @Test
    void 전화번호_3분할_비서울_중간3자리초과_무효() {
        assertFalse(EgovFormatCheckUtil.checkFormatTell("031", "1234", "5678"));
    }

    // ─── checkFormatTell(String) ──────────────────────────────────────────

    @Test
    void 전화번호_통합_하이픈포함_유효() {
        assertTrue(EgovFormatCheckUtil.checkFormatTell("031-123-4567"));
    }

    @Test
    void 전화번호_통합_하이픈없음_유효() {
        assertTrue(EgovFormatCheckUtil.checkFormatTell("0311234567"));
    }

    @Test
    void 전화번호_통합_서울02_9자리_유효() {
        assertTrue(EgovFormatCheckUtil.checkFormatTell("02-123-4567"));
    }

    @Test
    void 전화번호_통합_서울02_10자리_유효() {
        assertTrue(EgovFormatCheckUtil.checkFormatTell("02-1234-5678"));
    }

    @Test
    void 전화번호_통합_0505_유효() {
        assertTrue(EgovFormatCheckUtil.checkFormatTell("0505-123-4567"));
    }

    @Test
    void 전화번호_통합_0으로시작하지않음_무효() {
        assertFalse(EgovFormatCheckUtil.checkFormatTell("1234567890"));
    }

    @Test
    void 전화번호_통합_8자리이하_무효() {
        assertFalse(EgovFormatCheckUtil.checkFormatTell("02-12-345"));
    }

    @Test
    void 전화번호_통합_12자리초과_무효() {
        assertFalse(EgovFormatCheckUtil.checkFormatTell("031-1234-56789"));
    }

    @Test
    void 전화번호_통합_서울_11자리_무효() {
        assertFalse(EgovFormatCheckUtil.checkFormatTell("02123456789"));
    }

    // ─── checkFormatCell(String, String, String) ──────────────────────────

    @Test
    void 휴대폰_3분할_010_10자리_유효() {
        assertTrue(EgovFormatCheckUtil.checkFormatCell("010", "123", "4567"));
    }

    @Test
    void 휴대폰_3분할_010_11자리_유효() {
        assertTrue(EgovFormatCheckUtil.checkFormatCell("010", "1234", "5678"));
    }

    @Test
    void 휴대폰_3분할_011_유효() {
        assertTrue(EgovFormatCheckUtil.checkFormatCell("011", "123", "4567"));
    }

    @Test
    void 휴대폰_3분할_016_유효() {
        assertTrue(EgovFormatCheckUtil.checkFormatCell("016", "123", "4567"));
    }

    @Test
    void 휴대폰_3분할_미등록번호_무효() {
        assertFalse(EgovFormatCheckUtil.checkFormatCell("020", "123", "4567"));
    }

    @Test
    void 휴대폰_3분할_중간번호_0으로시작_무효() {
        assertFalse(EgovFormatCheckUtil.checkFormatCell("010", "023", "4567"));
    }

    @Test
    void 휴대폰_3분할_비숫자_포함_무효() {
        assertFalse(EgovFormatCheckUtil.checkFormatCell("010", "12a", "4567"));
    }

    @Test
    void 휴대폰_3분할_뒷번호_3자리_무효() {
        assertFalse(EgovFormatCheckUtil.checkFormatCell("010", "123", "456"));
    }

    @Test
    void 휴대폰_3분할_중간번호_5자리_무효() {
        assertFalse(EgovFormatCheckUtil.checkFormatCell("010", "12345", "6789"));
    }

    // ─── checkFormatCell(String) ──────────────────────────────────────────

    @Test
    void 휴대폰_통합_하이픈포함_10자리_유효() {
        assertTrue(EgovFormatCheckUtil.checkFormatCell("010-123-4567"));
    }

    @Test
    void 휴대폰_통합_하이픈포함_11자리_유효() {
        assertTrue(EgovFormatCheckUtil.checkFormatCell("010-1234-5678"));
    }

    @Test
    void 휴대폰_통합_하이픈없음_11자리_유효() {
        assertTrue(EgovFormatCheckUtil.checkFormatCell("01012345678"));
    }

    @Test
    void 휴대폰_통합_0으로시작하지않음_무효() {
        assertFalse(EgovFormatCheckUtil.checkFormatCell("1101234567"));
    }

    @Test
    void 휴대폰_통합_9자리이하_무효() {
        assertFalse(EgovFormatCheckUtil.checkFormatCell("010-12-345"));
    }

    @Test
    void 휴대폰_통합_12자리초과_무효() {
        assertFalse(EgovFormatCheckUtil.checkFormatCell("010-12345-6789"));
    }

    // ─── checkFormatMail(String, String) ─────────────────────────────────

    @Test
    void 이메일_2분할_유효() {
        // mail2는 소문자 알파벳과 점(.)만 허용, 점은 정확히 1개여야 함
        assertTrue(EgovFormatCheckUtil.checkFormatMail("user", "example.com"));
    }

    @Test
    void 이메일_2분할_대문자포함_유효() {
        // mail1은 대소문자 및 숫자 허용
        assertTrue(EgovFormatCheckUtil.checkFormatMail("User123", "example.com"));
    }

    @Test
    void 이메일_2분할_mail2_점_2개_무효() {
        // 점이 2개이면 count != 1 이므로 false
        assertFalse(EgovFormatCheckUtil.checkFormatMail("user", "mail.example.com"));
    }

    @Test
    void 이메일_2분할_mail2_점_없음_무효() {
        assertFalse(EgovFormatCheckUtil.checkFormatMail("user", "examplecom"));
    }

    @Test
    void 이메일_2분할_mail2_대문자포함_무효() {
        // mail2는 소문자만 허용
        assertFalse(EgovFormatCheckUtil.checkFormatMail("user", "Example.com"));
    }

    @Test
    void 이메일_2분할_mail2_숫자포함_무효() {
        // mail2는 소문자와 점만 허용 (숫자 불가)
        assertFalse(EgovFormatCheckUtil.checkFormatMail("user", "example1.com"));
    }

    // ─── checkFormatMail(String) ──────────────────────────────────────────

    @Test
    void 이메일_통합_유효() {
        assertTrue(EgovFormatCheckUtil.checkFormatMail("user@example.com"));
    }

    @Test
    void 이메일_통합_at없음_무효() {
        assertFalse(EgovFormatCheckUtil.checkFormatMail("userexample.com"));
    }

    @Test
    void 이메일_통합_at_2개_무효() {
        assertFalse(EgovFormatCheckUtil.checkFormatMail("user@exam@ple.com"));
    }
}
