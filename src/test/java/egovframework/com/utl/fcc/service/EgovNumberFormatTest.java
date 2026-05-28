package egovframework.com.utl.fcc.service;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Locale;

import org.junit.jupiter.api.Test;

/**
 * EgovNumberFormat 단위 테스트
 */
public class EgovNumberFormatTest {

    // -----------------------------------------------------------------------
    // formatNumber (기본 Locale)
    // -----------------------------------------------------------------------

    @Test
    void testFormatNumber_integer_grouping() {
        String result = EgovNumberFormat.formatNumber(1000000);
        // 기본 Locale(한국 JVM 환경) 기준 그룹 구분자 포함
        assertTrue(result.contains("1") && result.endsWith("000"),
                "1000000 포맷 결과에 자릿수 표기 포함: " + result);
    }

    @Test
    void testFormatNumber_noGrouping() {
        String result = EgovNumberFormat.formatNumber(1000000, false);
        assertFalse(result.contains(",") || result.contains(".") && result.length() < 8,
                "그룹 구분자 없이 숫자만 포함: " + result);
        assertTrue(result.replace(",", "").replace(".", "").contains("1000000")
                || result.contains("1000000"),
                "1000000 숫자값 포함: " + result);
    }

    @Test
    void testFormatNumber_decimalTruncation() {
        // 소수점 3자리까지만 (MAX_FRACTION_DIGIT=3)
        String result = EgovNumberFormat.formatNumber(3.14159265);
        // 소수점 이하 최대 3자리로 잘림
        String[] parts = result.split("[,.]");
        // 마지막 파트(소수점 이하)가 3자리 이하
        if (result.contains(".")) {
            String decimalPart = result.substring(result.lastIndexOf('.') + 1);
            assertTrue(decimalPart.length() <= 3,
                    "소수점 3자리 초과하지 않아야 함: " + result);
        }
    }

    @Test
    void testFormatNumber_maxFractionDigits_zero() {
        String result = EgovNumberFormat.formatNumber(3.99, 0);
        assertFalse(result.contains("."), "소수점 0자리: 소수점 없어야 함: " + result);
    }

    @Test
    void testFormatNumber_negative() {
        String result = EgovNumberFormat.formatNumber(-500);
        assertTrue(result.contains("-"), "음수 표기 포함: " + result);
    }

    // -----------------------------------------------------------------------
    // formatNumber (Locale 지정)
    // -----------------------------------------------------------------------

    @Test
    void testFormatNumber_localeUS_groupingComma() {
        String result = EgovNumberFormat.formatNumber(Locale.US, 1234567);
        assertEquals("1,234,567", result, "US Locale 그룹 구분자 쉼표");
    }

    @Test
    void testFormatNumber_localeUS_decimal() {
        String result = EgovNumberFormat.formatNumber(Locale.US, 1234.5678, 2);
        assertEquals("1,234.57", result, "US Locale 소수 2자리 반올림");
    }

    @Test
    void testFormatNumber_localeUS_noGrouping() {
        String result = EgovNumberFormat.formatNumber(Locale.US, 1234567, false);
        assertEquals("1234567", result, "US Locale 그룹 구분자 없음");
    }

    @Test
    void testFormatNumber_localeUS_noGrouping_decimal() {
        String result = EgovNumberFormat.formatNumber(Locale.US, 1234.5, false, 1);
        assertEquals("1234.5", result, "US Locale 그룹 없음, 소수 1자리");
    }

    @Test
    void testFormatNumber_localeUS_zero() {
        String result = EgovNumberFormat.formatNumber(Locale.US, 0);
        assertEquals("0", result, "0 포맷");
    }

    // -----------------------------------------------------------------------
    // formatCurrency (기본 Locale)
    // -----------------------------------------------------------------------

    @Test
    void testFormatCurrency_containsNumber() {
        String result = EgovNumberFormat.formatCurrency(1000);
        // 통화 기호 포함 여부와 무관하게 숫자 1000 포함
        assertTrue(result.replace(",", "").contains("1000"),
                "통화 포맷에 1000 포함: " + result);
    }

    @Test
    void testFormatCurrency_noGrouping_containsNumber() {
        String result = EgovNumberFormat.formatCurrency(2000, false);
        assertTrue(result.replace(",", "").contains("2000"),
                "그룹 없는 통화 포맷에 2000 포함: " + result);
    }

    // -----------------------------------------------------------------------
    // formatCurrency (Locale 지정)
    // -----------------------------------------------------------------------

    @Test
    void testFormatCurrency_localeUS() {
        String result = EgovNumberFormat.formatCurrency(Locale.US, 1234);
        assertTrue(result.contains("$") && result.contains("1,234"),
                "US 통화 기호 $ 와 1,234 포함: " + result);
    }

    @Test
    void testFormatCurrency_localeUS_noGrouping() {
        String result = EgovNumberFormat.formatCurrency(Locale.US, 5000, false);
        assertTrue(result.contains("$"), "US 통화 기호 $ 포함: " + result);
        assertFalse(result.contains(","), "그룹 구분자 없음: " + result);
    }

    // -----------------------------------------------------------------------
    // formatPercent (기본 Locale)
    // -----------------------------------------------------------------------

    @Test
    void testFormatPercent_half() {
        String result = EgovNumberFormat.formatPercent(0.5);
        assertTrue(result.contains("50") && result.contains("%"),
                "0.5 → 50% 포함: " + result);
    }

    @Test
    void testFormatPercent_one() {
        String result = EgovNumberFormat.formatPercent(1.0);
        assertTrue(result.contains("100") && result.contains("%"),
                "1.0 → 100% 포함: " + result);
    }

    @Test
    void testFormatPercent_noGrouping() {
        String result = EgovNumberFormat.formatPercent(0.5, false);
        assertTrue(result.contains("%"), "% 기호 포함: " + result);
    }

    @Test
    void testFormatPercent_maxFractionDigits() {
        String result = EgovNumberFormat.formatPercent(0.5, 0);
        assertTrue(result.contains("50") && result.contains("%"),
                "소수 0자리 퍼센트: " + result);
        assertFalse(result.contains("."), "소수점 없음: " + result);
    }

    // -----------------------------------------------------------------------
    // formatPercent (Locale 지정)
    // -----------------------------------------------------------------------

    @Test
    void testFormatPercent_localeUS() {
        String result = EgovNumberFormat.formatPercent(Locale.US, 0.25);
        assertTrue(result.contains("25") && result.contains("%"),
                "US 25% 포함: " + result);
    }

    @Test
    void testFormatPercent_localeUS_noGrouping() {
        String result = EgovNumberFormat.formatPercent(Locale.US, 0.75, false);
        assertTrue(result.contains("75") && result.contains("%"),
                "US 75% 포함: " + result);
    }

    @Test
    void testFormatPercent_localeUS_maxFraction() {
        String result = EgovNumberFormat.formatPercent(Locale.US, 0.333, 1);
        assertTrue(result.contains("%"), "% 포함: " + result);
        // 33.3% 근처 값
        assertTrue(result.contains("33"), "33 포함: " + result);
    }

    @Test
    void testFormatPercent_localeUS_noGrouping_maxFraction() {
        String result = EgovNumberFormat.formatPercent(Locale.US, 0.5, false, 0);
        assertEquals("50%", result, "US 50% (그룹 없음, 소수 0자리)");
    }
}
