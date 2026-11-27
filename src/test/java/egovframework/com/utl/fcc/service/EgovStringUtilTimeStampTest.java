package egovframework.com.utl.fcc.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.util.Calendar;
import java.util.Locale;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * egovframework.com.utl.fcc.service.EgovStringUtilTimeStampTest
 * <p>
 * Regression test for EgovStringUtil.getTimeStamp().
 * <p>
 * getTimeStamp() is documented as a source of unique values and is used to build
 * history IDs (EgovSysHistoryServiceImpl) and file names (EgovPdfCnvr). It used the
 * 12-hour pattern "hh", so a value produced at 14:30 rendered the hour as "02" -- the
 * same as 02:30 -- causing morning/afternoon collisions of supposedly unique values.
 * The pattern must be 24-hour "HH".
 *
 * The assertions are timezone-independent: the Calendar uses the JVM default time zone,
 * which is the same zone SimpleDateFormat formats in, so the local wall-clock hour is
 * what gets rendered regardless of where the test runs.
 *
 * @author EricSeokgon
 * @version 1.0
 */
class EgovStringUtilTimeStampTest {

    private static long millisAt(int year, int month, int day, int hour, int minute, int second, int milli) {
        Calendar cal = Calendar.getInstance(Locale.KOREA); // default time zone
        cal.clear();
        cal.set(year, month, day, hour, minute, second);
        cal.set(Calendar.MILLISECOND, milli);
        return cal.getTimeInMillis();
    }

    @Test
    @DisplayName("getTimeStamp uses a 24-hour clock so afternoon does not collide with morning")
    void getTimeStamp_uses24HourClock() {
        long afternoon = millisAt(2026, Calendar.JANUARY, 2, 14, 30, 5, 123);
        long morning = millisAt(2026, Calendar.JANUARY, 2, 2, 30, 5, 123);

        String tsPm = EgovStringUtil.getTimeStamp(afternoon);
        String tsAm = EgovStringUtil.getTimeStamp(morning);

        assertEquals(17, tsPm.length(), "timestamp should be 17 digits");
        assertEquals("20260102", tsPm.substring(0, 8), "date part");
        assertEquals("14", tsPm.substring(8, 10),
                "14:30 must render the hour as 24-hour '14', not 12-hour '02'");
        assertEquals("02", tsAm.substring(8, 10));
        assertNotEquals(tsAm, tsPm,
                "afternoon and morning timestamps must not collide (getTimeStamp is a unique-value source)");
    }
}
