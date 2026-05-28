package egovframework.com.utl.fcc.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Map;

/**
 * EgovDateUtil 단위 테스트
 */
public class EgovDateUtilTest {

    // ─── addYearMonthDay ───────────────────────────────────────────────────

    @Test
    void addYearMonthDay_일가산_정상() {
        Assertions.assertEquals("19810916", EgovDateUtil.addYearMonthDay("19810828", 0, 0, 19));
    }

    @Test
    void addYearMonthDay_일감산_정상() {
        Assertions.assertEquals("20060218", EgovDateUtil.addYearMonthDay("20060228", 0, 0, -10));
    }

    @Test
    void addYearMonthDay_월말_일가산_월경계() {
        Assertions.assertEquals("20060310", EgovDateUtil.addYearMonthDay("20060228", 0, 0, 10));
    }

    @Test
    void addYearMonthDay_월감산_2월말일처리() {
        Assertions.assertEquals("20050228", EgovDateUtil.addYearMonthDay("20050331", 0, -1, 0));
    }

    @Test
    void addYearMonthDay_년가산_정상() {
        // 2005-03-01 + 1년 + 2월 + 30일 = 2006-05-31
        Assertions.assertEquals("20060531", EgovDateUtil.addYearMonthDay("20050301", 1, 2, 30));
    }

    @Test
    void addYearMonthDay_구분자있는날짜형식() {
        Assertions.assertEquals("20060228", EgovDateUtil.addYearMonthDay("2006-02-28", 0, 0, 0));
    }

    @Test
    void addYearMonthDay_null입력_예외() {
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> EgovDateUtil.addYearMonthDay(null, 0, 0, 1));
    }

    @Test
    void addYearMonthDay_잘못된형식_예외() {
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> EgovDateUtil.addYearMonthDay("2006228", 0, 0, 1));
    }

    // ─── addYear ──────────────────────────────────────────────────────────

    @Test
    void addYear_정상가산() {
        Assertions.assertEquals("20620201", EgovDateUtil.addYear("20000201", 62));
    }

    @Test
    void addYear_정상감산() {
        Assertions.assertEquals("20000201", EgovDateUtil.addYear("20620201", -62));
    }

    @Test
    void addYear_윤년2월29일_비윤년으로이동() {
        Assertions.assertEquals("20060228", EgovDateUtil.addYear("20040229", 2));
    }

    // ─── addMonth ─────────────────────────────────────────────────────────

    @Test
    void addMonth_12개월가산() {
        Assertions.assertEquals("20020201", EgovDateUtil.addMonth("20010201", 12));
    }

    @Test
    void addMonth_월말일처리_2월() {
        Assertions.assertEquals("20060228", EgovDateUtil.addMonth("20060131", 1));
    }

    @Test
    void addMonth_감산() {
        Assertions.assertEquals("20060128", EgovDateUtil.addMonth("20060228", -1));
    }

    // ─── addDay ───────────────────────────────────────────────────────────

    @Test
    void addDay_정상가산() {
        Assertions.assertEquals("20000201", EgovDateUtil.addDay("19991201", 62));
    }

    @Test
    void addDay_정상감산() {
        Assertions.assertEquals("19991201", EgovDateUtil.addDay("20000201", -62));
    }

    @Test
    void addDay_월경계() {
        Assertions.assertEquals("20050903", EgovDateUtil.addDay("20050831", 3));
    }

    // ─── getDaysDiff ──────────────────────────────────────────────────────

    @Test
    void getDaysDiff_양수() {
        Assertions.assertEquals(10, EgovDateUtil.getDaysDiff("20060228", "20060310"));
    }

    @Test
    void getDaysDiff_1년() {
        Assertions.assertEquals(365, EgovDateUtil.getDaysDiff("20060101", "20070101"));
    }

    @Test
    void getDaysDiff_음수() {
        Assertions.assertEquals(-28, EgovDateUtil.getDaysDiff("19990228", "19990131"));
    }

    @Test
    void getDaysDiff_같은날() {
        Assertions.assertEquals(0, EgovDateUtil.getDaysDiff("20060801", "20060801"));
    }

    @Test
    void getDaysDiff_1일() {
        Assertions.assertEquals(1, EgovDateUtil.getDaysDiff("20060801", "20060802"));
    }

    // ─── checkDate(String) ────────────────────────────────────────────────

    @Test
    void checkDate_유효한날짜_8자리() {
        Assertions.assertTrue(EgovDateUtil.checkDate("20060228"));
    }

    @Test
    void checkDate_유효한날짜_구분자() {
        Assertions.assertTrue(EgovDateUtil.checkDate("2006-02-28"));
    }

    @Test
    void checkDate_존재하지않는날짜_35일() {
        Assertions.assertFalse(EgovDateUtil.checkDate("19990235"));
    }

    @Test
    void checkDate_존재하지않는날짜_13월() {
        Assertions.assertFalse(EgovDateUtil.checkDate("20001331"));
    }

    @Test
    void checkDate_11월31일() {
        Assertions.assertFalse(EgovDateUtil.checkDate("20061131"));
    }

    // ─── checkDate(String, String, String) ────────────────────────────────

    @Test
    void checkDate_년월일파라미터_유효() {
        Assertions.assertTrue(EgovDateUtil.checkDate("2006", "02", "28"));
    }

    @Test
    void checkDate_년월일파라미터_무효() {
        Assertions.assertFalse(EgovDateUtil.checkDate("2006", "02", "30"));
    }

    // ─── formatDate ───────────────────────────────────────────────────────

    @Test
    void formatDate_8자리_점구분자() {
        Assertions.assertEquals("2003.04.05", EgovDateUtil.formatDate("20030405", "."));
    }

    @Test
    void formatDate_8자리_슬래시구분자() {
        Assertions.assertEquals("2004/01/01", EgovDateUtil.formatDate("20040101", "/"));
    }

    @Test
    void formatDate_6자리_유효하지않은입력_예외() {
        // validChkDate는 8자리 또는 10자리(yyyy-MM-dd)만 허용
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> EgovDateUtil.formatDate("200304", "."));
    }

    @Test
    void formatDate_4자리_유효하지않은입력_예외() {
        // validChkDate는 8자리 또는 10자리(yyyy-MM-dd)만 허용
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> EgovDateUtil.formatDate("2003", "."));
    }

    @Test
    void formatDate_0000년_빈문자열() {
        Assertions.assertEquals("", EgovDateUtil.formatDate("00000101", "."));
    }

    @Test
    void formatDate_월이00_년도만반환() {
        Assertions.assertEquals("2003", EgovDateUtil.formatDate("20030000", "."));
    }

    @Test
    void formatDate_일이00_년월반환() {
        Assertions.assertEquals("2003.04", EgovDateUtil.formatDate("20030400", "."));
    }

    // ─── formatTime ───────────────────────────────────────────────────────

    @Test
    void formatTime_6자리_예외() {
        // validChkTime은 4자리(HHmm)만 허용 — 6자리 입력은 예외
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> EgovDateUtil.formatTime("151241", "/"));
    }

    @Test
    void formatTime_4자리입력_substring예외() {
        // validChkTime 통과(4자리)하지만 formatTime이 substring(4,6) 호출 → StringIndexOutOfBoundsException
        Assertions.assertThrows(Exception.class,
                () -> EgovDateUtil.formatTime("1512", "/"));
    }

    // ─── isLeapYear ───────────────────────────────────────────────────────
    // 주의: isLeapYear는 윤년이면 false, 비윤년이면 true를 반환하는 구현

    @Test
    void isLeapYear_윤년_false반환() {
        Assertions.assertFalse(EgovDateUtil.isLeapYear(2000)); // 400의 배수
        Assertions.assertFalse(EgovDateUtil.isLeapYear(2004)); // 4의 배수, 100의 배수 아님
    }

    @Test
    void isLeapYear_비윤년_true반환() {
        Assertions.assertTrue(EgovDateUtil.isLeapYear(2005)); // 평년
        Assertions.assertTrue(EgovDateUtil.isLeapYear(2100)); // 100의 배수, 400의 배수 아님
    }

    // ─── convertWeek ──────────────────────────────────────────────────────

    @Test
    void convertWeek_월요일() {
        Assertions.assertEquals("월요일", EgovDateUtil.convertWeek("MON"));
    }

    @Test
    void convertWeek_일요일() {
        Assertions.assertEquals("일요일", EgovDateUtil.convertWeek("SUN"));
    }

    @Test
    void convertWeek_토요일() {
        Assertions.assertEquals("토요일", EgovDateUtil.convertWeek("SAT"));
    }

    @Test
    void convertWeek_금요일() {
        Assertions.assertEquals("금요일", EgovDateUtil.convertWeek("FRI"));
    }

    // ─── validDate ────────────────────────────────────────────────────────

    @Test
    void validDate_유효한날짜() {
        Assertions.assertTrue(EgovDateUtil.validDate("20060228"));
    }

    @Test
    void validDate_무효한날짜() {
        Assertions.assertFalse(EgovDateUtil.validDate("20061131"));
    }

    // ─── validTime ────────────────────────────────────────────────────────

    @Test
    void validTime_유효한시간() {
        Assertions.assertTrue(EgovDateUtil.validTime("1530"));
    }

    @Test
    void validTime_무효한시간() {
        Assertions.assertFalse(EgovDateUtil.validTime("2561"));
    }

    // ─── addYMDtoWeek ─────────────────────────────────────────────────────

    @Test
    void addYMDtoWeek_수요일() {
        // 2006-02-22는 수요일
        Assertions.assertEquals("Wed", EgovDateUtil.addYMDtoWeek("20060222", 0, 0, 0));
    }

    @Test
    void addYMDtoWeek_1일후() {
        // 2006-02-22(수) + 1일 = 2006-02-23(목)
        Assertions.assertEquals("Thu", EgovDateUtil.addYMDtoWeek("20060222", 0, 0, 1));
    }

    // ─── validChkDate / validChkTime ──────────────────────────────────────

    @Test
    void validChkDate_8자리정상() {
        Assertions.assertEquals("20060228", EgovDateUtil.validChkDate("20060228"));
    }

    @Test
    void validChkDate_10자리구분자제거() {
        Assertions.assertEquals("20060228", EgovDateUtil.validChkDate("2006-02-28"));
    }

    @Test
    void validChkDate_null_예외() {
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> EgovDateUtil.validChkDate(null));
    }

    @Test
    void validChkDate_잘못된길이_예외() {
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> EgovDateUtil.validChkDate("200602"));
    }

    @Test
    void validChkTime_4자리정상() {
        Assertions.assertEquals("1530", EgovDateUtil.validChkTime("1530"));
    }

    @Test
    void validChkTime_null_예외() {
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> EgovDateUtil.validChkTime(null));
    }

    // ─── toLunar / toSolar ────────────────────────────────────────────────

    @Test
    void toLunar_양력_to_음력_반환값구조() {
        Map<String, String> result = EgovDateUtil.toLunar("20060228");
        Assertions.assertNotNull(result.get("day"));
        Assertions.assertNotNull(result.get("leap"));
        Assertions.assertEquals(8, result.get("day").length());
    }

    @Test
    void toSolar_음력_to_양력() {
        // toLunar 결과를 toSolar로 되돌렸을 때 원본 날짜 반환 확인
        Map<String, String> lunar = EgovDateUtil.toLunar("20060228");
        String solarBack = EgovDateUtil.toSolar(lunar.get("day"), Integer.parseInt(lunar.get("leap")));
        Assertions.assertEquals("20060228", solarBack);
    }

    // ─── getRandomDate ────────────────────────────────────────────────────

    @Test
    void getRandomDate_범위내반환() {
        String result = EgovDateUtil.getRandomDate("20060101", "20061231");
        Assertions.assertNotNull(result);
        Assertions.assertEquals(8, result.length());
        int val = Integer.parseInt(result);
        Assertions.assertTrue(val >= 20060101 && val <= 20061231);
    }

    @Test
    void getRandomDate_시작이끝보다크면_예외() {
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> EgovDateUtil.getRandomDate("20061231", "20060101"));
    }

}
