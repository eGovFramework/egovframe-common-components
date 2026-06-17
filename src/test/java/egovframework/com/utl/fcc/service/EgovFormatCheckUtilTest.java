package egovframework.com.utl.fcc.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayName("EgovFormatCheckUtil 형식 검증 테스트")
public class EgovFormatCheckUtilTest {

    // ─── checkFormatTell(3개 파라미터) ─────────────────────────────────

    @Test
    @DisplayName("서울 지역번호(02) 3자리 중간번호 유효")
    void checkFormatTell_seoul3digit_valid() {
        assertTrue(EgovFormatCheckUtil.checkFormatTell("02", "123", "4567"));
    }

    @Test
    @DisplayName("서울 지역번호(02) 4자리 중간번호 유효")
    void checkFormatTell_seoul4digit_valid() {
        assertTrue(EgovFormatCheckUtil.checkFormatTell("02", "1234", "5678"));
    }

    @Test
    @DisplayName("비서울 지역번호(031) 유효")
    void checkFormatTell_nonSeoul_valid() {
        assertTrue(EgovFormatCheckUtil.checkFormatTell("031", "123", "4567"));
    }

    @Test
    @DisplayName("존재하지 않는 국번은 무효")
    void checkFormatTell_invalidAreaCode_false() {
        assertFalse(EgovFormatCheckUtil.checkFormatTell("099", "123", "4567"));
    }

    @Test
    @DisplayName("중간번호 첫자리가 0이면 무효")
    void checkFormatTell_middleStartsWithZero_false() {
        assertFalse(EgovFormatCheckUtil.checkFormatTell("031", "012", "3456"));
    }

    @Test
    @DisplayName("비서울 중간번호 4자리면 무효")
    void checkFormatTell_nonSeoul4digitMiddle_false() {
        assertFalse(EgovFormatCheckUtil.checkFormatTell("031", "1234", "5678"));
    }

    @Test
    @DisplayName("숫자 아닌 문자 포함 시 무효")
    void checkFormatTell_nonNumeric_false() {
        assertFalse(EgovFormatCheckUtil.checkFormatTell("031", "12a", "4567"));
    }

    // ─── checkFormatTell(문자열 1개) ───────────────────────────────────

    @Test
    @DisplayName("하이픈 포함 서울 번호 유효")
    void checkFormatTell_string_seoulWithHyphen_valid() {
        assertTrue(EgovFormatCheckUtil.checkFormatTell("02-1234-5678"));
    }

    @Test
    @DisplayName("하이픈 없는 지방 번호 유효")
    void checkFormatTell_string_nonSeoulNoHyphen_valid() {
        assertTrue(EgovFormatCheckUtil.checkFormatTell("0311234567"));
    }

    @Test
    @DisplayName("평생번호(0505) 유효")
    void checkFormatTell_string_0505_valid() {
        assertTrue(EgovFormatCheckUtil.checkFormatTell("0505-123-4567"));
    }

    @Test
    @DisplayName("짧은 번호는 무효")
    void checkFormatTell_string_tooShort_false() {
        assertFalse(EgovFormatCheckUtil.checkFormatTell("02-123"));
    }

    @Test
    @DisplayName("0으로 시작하지 않으면 무효")
    void checkFormatTell_string_notStartWithZero_false() {
        assertFalse(EgovFormatCheckUtil.checkFormatTell("1234-5678"));
    }

    // ─── checkFormatCell(3개 파라미터) ────────────────────────────────

    @Test
    @DisplayName("010 번호 10자리 유효")
    void checkFormatCell_010_10digit_valid() {
        assertTrue(EgovFormatCheckUtil.checkFormatCell("010", "123", "4567"));
    }

    @Test
    @DisplayName("010 번호 11자리 유효")
    void checkFormatCell_010_11digit_valid() {
        assertTrue(EgovFormatCheckUtil.checkFormatCell("010", "1234", "5678"));
    }

    @Test
    @DisplayName("011 번호 유효")
    void checkFormatCell_011_valid() {
        assertTrue(EgovFormatCheckUtil.checkFormatCell("011", "123", "4567"));
    }

    @Test
    @DisplayName("존재하지 않는 휴대폰 첫자리 무효")
    void checkFormatCell_invalidPrefix_false() {
        assertFalse(EgovFormatCheckUtil.checkFormatCell("012", "123", "4567"));
    }

    @Test
    @DisplayName("중간번호 첫자리 0이면 무효")
    void checkFormatCell_middleStartsWithZero_false() {
        assertFalse(EgovFormatCheckUtil.checkFormatCell("010", "012", "3456"));
    }

    // ─── checkFormatCell(문자열 1개) ──────────────────────────────────

    @Test
    @DisplayName("하이픈 포함 010 번호 유효")
    void checkFormatCell_string_withHyphen_valid() {
        assertTrue(EgovFormatCheckUtil.checkFormatCell("010-1234-5678"));
    }

    @Test
    @DisplayName("하이픈 없는 10자리 유효")
    void checkFormatCell_string_10digit_valid() {
        assertTrue(EgovFormatCheckUtil.checkFormatCell("0101234567"));
    }

    @Test
    @DisplayName("9자리 미만은 무효")
    void checkFormatCell_string_tooShort_false() {
        assertFalse(EgovFormatCheckUtil.checkFormatCell("010-123"));
    }

    // ─── checkFormatMail ──────────────────────────────────────────────

    @Test
    @DisplayName("정상 이메일 유효")
    void checkFormatMail_valid() {
        assertTrue(EgovFormatCheckUtil.checkFormatMail("user", "example.com"));
    }

    @Test
    @DisplayName("도메인 점이 2개이면 무효")
    void checkFormatMail_twoDots_false() {
        assertFalse(EgovFormatCheckUtil.checkFormatMail("user", "example.co.kr"));
    }

    @Test
    @DisplayName("@ 없는 이메일 무효")
    void checkFormatMail_noAt_false() {
        assertFalse(EgovFormatCheckUtil.checkFormatMail("userexample.com"));
    }

    @Test
    @DisplayName("@ 2개 이상이면 무효")
    void checkFormatMail_doubleAt_false() {
        assertFalse(EgovFormatCheckUtil.checkFormatMail("user@@example.com"));
    }

    @Test
    @DisplayName("전체 이메일 문자열 유효")
    void checkFormatMail_fullString_valid() {
        assertTrue(EgovFormatCheckUtil.checkFormatMail("user@example.com"));
    }

}
