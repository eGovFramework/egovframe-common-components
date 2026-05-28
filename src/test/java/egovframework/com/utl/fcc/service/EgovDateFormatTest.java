package egovframework.com.utl.fcc.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * EgovDateFormat 단위 테스트
 */
public class EgovDateFormatTest {

    /** 고정된 테스트 날짜: 2006-02-28 15:30:45 */
    private static Date fixedDate() {
        Calendar cal = Calendar.getInstance();
        cal.set(2006, Calendar.FEBRUARY, 28, 15, 30, 45);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }

    // ─── formatDate(Date) ─────────────────────────────────────────────────

    @Test
    void formatDate_Date_기본로케일_비어있지않음() {
        String result = EgovDateFormat.formatDate(fixedDate());
        Assertions.assertNotNull(result);
        Assertions.assertFalse(result.isEmpty());
    }

    @Test
    void formatDate_Date_날짜포함() {
        // 기본 로케일로 포맷된 결과에 "2006" 연도가 포함되어야 함
        String result = EgovDateFormat.formatDate(fixedDate());
        Assertions.assertTrue(result.contains("2006"),
                "기본 formatDate 결과에 연도 2006이 포함되어야 합니다: " + result);
    }

    // ─── formatDate(Locale, Date) ─────────────────────────────────────────

    @Test
    void formatDate_Locale_US() {
        String result = EgovDateFormat.formatDate(Locale.US, fixedDate());
        Assertions.assertNotNull(result);
        Assertions.assertFalse(result.isEmpty());
        Assertions.assertTrue(result.contains("2006"),
                "US 로케일 formatDate 결과에 연도 2006이 포함되어야 합니다: " + result);
    }

    @Test
    void formatDate_Locale_KOREA() {
        String result = EgovDateFormat.formatDate(Locale.KOREA, fixedDate());
        Assertions.assertNotNull(result);
        Assertions.assertTrue(result.contains("2006"));
    }

    // ─── formatDate(int style, Date) ──────────────────────────────────────

    @Test
    void formatDate_스타일_SHORT() {
        String result = EgovDateFormat.formatDate(DateFormat.SHORT, fixedDate());
        Assertions.assertNotNull(result);
        Assertions.assertFalse(result.isEmpty());
    }

    @Test
    void formatDate_스타일_MEDIUM() {
        String result = EgovDateFormat.formatDate(DateFormat.MEDIUM, fixedDate());
        Assertions.assertNotNull(result);
        Assertions.assertFalse(result.isEmpty());
    }

    @Test
    void formatDate_스타일_LONG() {
        String result = EgovDateFormat.formatDate(DateFormat.LONG, fixedDate());
        Assertions.assertNotNull(result);
        Assertions.assertTrue(result.contains("2006"));
    }

    @Test
    void formatDate_스타일_FULL() {
        String result = EgovDateFormat.formatDate(DateFormat.FULL, fixedDate());
        Assertions.assertNotNull(result);
        Assertions.assertTrue(result.contains("2006"));
    }

    // ─── formatDate(int style, Locale, Date) ──────────────────────────────

    @Test
    void formatDate_스타일_로케일_SHORT_US() {
        String result = EgovDateFormat.formatDate(DateFormat.SHORT, Locale.US, fixedDate());
        // US SHORT: M/d/yy 형식 → "2/28/06"
        Assertions.assertNotNull(result);
        Assertions.assertTrue(result.contains("28") || result.contains("06"),
                "US SHORT 날짜에 '28' 또는 '06'이 포함되어야 합니다: " + result);
    }

    @Test
    void formatDate_스타일_로케일_FULL_US() {
        String result = EgovDateFormat.formatDate(DateFormat.FULL, Locale.US, fixedDate());
        Assertions.assertTrue(result.contains("2006"));
        Assertions.assertTrue(result.contains("February") || result.contains("Feb"),
                "US FULL 날짜에 월 이름이 포함되어야 합니다: " + result);
    }

    @Test
    void formatDate_스타일_로케일_결과일관성() {
        // 동일 인자로 두 번 호출하면 같은 결과
        Date date = fixedDate();
        String r1 = EgovDateFormat.formatDate(DateFormat.MEDIUM, Locale.US, date);
        String r2 = EgovDateFormat.formatDate(DateFormat.MEDIUM, Locale.US, date);
        Assertions.assertEquals(r1, r2);
    }

    // ─── formatDateTime(Date) ─────────────────────────────────────────────

    @Test
    void formatDateTime_Date_날짜시간포함() {
        String result = EgovDateFormat.formatDateTime(fixedDate());
        Assertions.assertNotNull(result);
        Assertions.assertTrue(result.contains("2006"));
    }

    // ─── formatDateTime(Locale, Date) ─────────────────────────────────────

    @Test
    void formatDateTime_Locale_US() {
        String result = EgovDateFormat.formatDateTime(Locale.US, fixedDate());
        Assertions.assertNotNull(result);
        Assertions.assertTrue(result.contains("2006"));
    }

    // ─── formatDateTime(int dateStyle, int timeStyle, Date) ───────────────

    @Test
    void formatDateTime_dateStyle_timeStyle_SHORT() {
        String result = EgovDateFormat.formatDateTime(DateFormat.SHORT, DateFormat.SHORT, fixedDate());
        Assertions.assertNotNull(result);
        Assertions.assertFalse(result.isEmpty());
    }

    @Test
    void formatDateTime_dateStyle_timeStyle_MEDIUM() {
        String result = EgovDateFormat.formatDateTime(DateFormat.MEDIUM, DateFormat.MEDIUM, fixedDate());
        Assertions.assertNotNull(result);
        Assertions.assertFalse(result.isEmpty());
    }

    // ─── formatDateTime(int dateStyle, int timeStyle, Locale, Date) ───────

    @Test
    void formatDateTime_스타일_로케일_결과일관성() {
        Date date = fixedDate();
        String r1 = EgovDateFormat.formatDateTime(DateFormat.MEDIUM, DateFormat.SHORT, Locale.US, date);
        String r2 = EgovDateFormat.formatDateTime(DateFormat.MEDIUM, DateFormat.SHORT, Locale.US, date);
        Assertions.assertEquals(r1, r2);
    }

    @Test
    void formatDateTime_FULL_US_날짜시간포함() {
        String result = EgovDateFormat.formatDateTime(DateFormat.FULL, DateFormat.FULL, Locale.US, fixedDate());
        Assertions.assertTrue(result.contains("2006"));
        // 시간 15:30이 어떤 형태로든 포함
        Assertions.assertTrue(result.contains("3:30") || result.contains("15:30"),
                "FULL 날짜시간에 시간 정보가 포함되어야 합니다: " + result);
    }

    // ─── formatTime(Date) ─────────────────────────────────────────────────

    @Test
    void formatTime_Date_비어있지않음() {
        String result = EgovDateFormat.formatTime(fixedDate());
        Assertions.assertNotNull(result);
        Assertions.assertFalse(result.isEmpty());
    }

    // ─── formatTime(Locale, Date) ─────────────────────────────────────────

    @Test
    void formatTime_Locale_US() {
        String result = EgovDateFormat.formatTime(Locale.US, fixedDate());
        Assertions.assertNotNull(result);
        Assertions.assertFalse(result.isEmpty());
    }

    // ─── formatTime(int style, Date) ──────────────────────────────────────

    @Test
    void formatTime_스타일_SHORT() {
        String result = EgovDateFormat.formatTime(DateFormat.SHORT, fixedDate());
        Assertions.assertNotNull(result);
        Assertions.assertFalse(result.isEmpty());
    }

    @Test
    void formatTime_스타일_MEDIUM() {
        String result = EgovDateFormat.formatTime(DateFormat.MEDIUM, fixedDate());
        Assertions.assertNotNull(result);
        Assertions.assertFalse(result.isEmpty());
    }

    // ─── formatTime(int style, Locale, Date) ──────────────────────────────

    @Test
    void formatTime_스타일_로케일_결과일관성() {
        Date date = fixedDate();
        String r1 = EgovDateFormat.formatTime(DateFormat.SHORT, Locale.US, date);
        String r2 = EgovDateFormat.formatTime(DateFormat.SHORT, Locale.US, date);
        Assertions.assertEquals(r1, r2);
    }

    @Test
    void formatTime_MEDIUM_US_분포함() {
        String result = EgovDateFormat.formatTime(DateFormat.MEDIUM, Locale.US, fixedDate());
        // 15:30:45 → US MEDIUM "3:30:45 PM"
        Assertions.assertTrue(result.contains("30"),
                "시간 포맷 결과에 '30'(분)이 포함되어야 합니다: " + result);
    }

    @Test
    void formatTime_FULL_US_비어있지않음() {
        String result = EgovDateFormat.formatTime(DateFormat.FULL, Locale.US, fixedDate());
        Assertions.assertNotNull(result);
        Assertions.assertFalse(result.isEmpty());
    }

}
