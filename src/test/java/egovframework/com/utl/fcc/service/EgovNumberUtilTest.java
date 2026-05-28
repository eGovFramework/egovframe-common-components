package egovframework.com.utl.fcc.service;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

/**
 * EgovNumberUtil 단위 테스트
 */
public class EgovNumberUtilTest {

    // -----------------------------------------------------------------------
    // getRandomNum
    // -----------------------------------------------------------------------

    @RepeatedTest(20)
    void testGetRandomNum_resultInRange() {
        int result = EgovNumberUtil.getRandomNum(3, 7);
        assertTrue(result >= 3 && result <= 7,
                "결과는 시작숫자 이상, 종료숫자 이하여야 한다");
    }

    @Test
    void testGetRandomNum_sameStartEnd() {
        int result = EgovNumberUtil.getRandomNum(5, 5);
        assertEquals(5, result, "시작과 종료가 같으면 항상 해당 값 반환");
    }

    // -----------------------------------------------------------------------
    // getNumSearchCheck
    // -----------------------------------------------------------------------

    @Test
    void testGetNumSearchCheck_found() {
        assertTrue(EgovNumberUtil.getNumSearchCheck(12345678, 7),
                "12345678 에 7이 포함됨");
    }

    @Test
    void testGetNumSearchCheck_notFound() {
        assertFalse(EgovNumberUtil.getNumSearchCheck(12345678, 9),
                "12345678 에 9가 없음");
    }

    @Test
    void testGetNumSearchCheck_multiDigitSearch() {
        assertTrue(EgovNumberUtil.getNumSearchCheck(12345678, 234),
                "12345678 에 234가 포함됨");
    }

    @Test
    void testGetNumSearchCheck_sameNumber() {
        assertTrue(EgovNumberUtil.getNumSearchCheck(999, 999),
                "동일 숫자는 항상 존재");
    }

    // -----------------------------------------------------------------------
    // getNumToStrCnvr
    // -----------------------------------------------------------------------

    @Test
    void testGetNumToStrCnvr_positive() {
        assertEquals("20081212", EgovNumberUtil.getNumToStrCnvr(20081212));
    }

    @Test
    void testGetNumToStrCnvr_zero() {
        assertEquals("0", EgovNumberUtil.getNumToStrCnvr(0));
    }

    @Test
    void testGetNumToStrCnvr_negative() {
        assertEquals("-1", EgovNumberUtil.getNumToStrCnvr(-1));
    }

    // -----------------------------------------------------------------------
    // getNumToDateCnvr
    // -----------------------------------------------------------------------

    @Test
    void testGetNumToDateCnvr_eightDigit() {
        String result = EgovNumberUtil.getNumToDateCnvr(20081212);
        assertEquals("2008-12-12", result);
    }

    @Test
    void testGetNumToDateCnvr_eightDigitAnother() {
        String result = EgovNumberUtil.getNumToDateCnvr(20230101);
        assertEquals("2023-01-01", result);
    }

    @Test
    void testGetNumToDateCnvr_invalidLength_throws() {
        // 8자리·14자리 외에는 IllegalArgumentException
        assertThrows(IllegalArgumentException.class,
                () -> EgovNumberUtil.getNumToDateCnvr(2008121));
    }

    @Test
    void testGetNumToDateCnvr_sevenDigit_throws() {
        assertThrows(IllegalArgumentException.class,
                () -> EgovNumberUtil.getNumToDateCnvr(1234567));
    }

    // -----------------------------------------------------------------------
    // getNumberValidCheck
    // -----------------------------------------------------------------------

    @Test
    void testGetNumberValidCheck_allDigits() {
        assertTrue(EgovNumberUtil.getNumberValidCheck("12345"));
    }

    @Test
    void testGetNumberValidCheck_singleDigit() {
        assertTrue(EgovNumberUtil.getNumberValidCheck("0"));
    }

    @Test
    void testGetNumberValidCheck_containsLetter() {
        assertFalse(EgovNumberUtil.getNumberValidCheck("123a5"));
    }

    @Test
    void testGetNumberValidCheck_negativeSign() {
        // '-' 는 숫자가 아니므로 false
        assertFalse(EgovNumberUtil.getNumberValidCheck("-123"));
    }

    @Test
    void testGetNumberValidCheck_decimal() {
        // '.' 는 숫자가 아니므로 false
        assertFalse(EgovNumberUtil.getNumberValidCheck("3.14"));
    }

    // -----------------------------------------------------------------------
    // getNumberCnvr
    // -----------------------------------------------------------------------

    @Test
    void testGetNumberCnvr_basic() {
        // 12345678 에서 123을 999로 치환 → 99945678
        assertEquals(99945678, EgovNumberUtil.getNumberCnvr(12345678, 123, 999));
    }

    @Test
    void testGetNumberCnvr_noMatch() {
        // 치환 대상이 없으면 원본 반환
        assertEquals(12345678, EgovNumberUtil.getNumberCnvr(12345678, 999, 111));
    }

    @Test
    void testGetNumberCnvr_multipleOccurrences() {
        // 111111 에서 11을 22로 치환 → 222211? 로직 추적:
        // source="111111", subject="11"
        // 1회: pre="", next="1111", rtn="22"
        // 2회: pre="", next="11",   rtn="2222"
        // 3회: pre="", next="",     rtn="222222"
        // append nextStr("") → "222222"
        assertEquals(222222, EgovNumberUtil.getNumberCnvr(111111, 11, 22));
    }

    // -----------------------------------------------------------------------
    // checkRlnoInteger
    // -----------------------------------------------------------------------

    @Test
    void testCheckRlnoInteger_negative() {
        assertEquals(-1, EgovNumberUtil.checkRlnoInteger(-1.5));
    }

    @Test
    void testCheckRlnoInteger_negativeInt() {
        assertEquals(-1, EgovNumberUtil.checkRlnoInteger(-3));
    }

    @Test
    void testCheckRlnoInteger_positiveDouble() {
        // String.valueOf(3.14) = "3.14" -> '.' 포함 -> 1
        assertEquals(1, EgovNumberUtil.checkRlnoInteger(3.14));
    }

    @Test
    void testCheckRlnoInteger_positiveIntAsDouble() {
        // double 파라미터로 5 전달 시 String.valueOf(5.0) = "5.0" -> '.' 포함 -> 1
        assertEquals(1, EgovNumberUtil.checkRlnoInteger(5));
    }

    @Test
    void testCheckRlnoInteger_zero() {
        // String.valueOf(0.0) = "0.0" -> '.' 포함 -> 1
        assertEquals(1, EgovNumberUtil.checkRlnoInteger(0));
    }
}
