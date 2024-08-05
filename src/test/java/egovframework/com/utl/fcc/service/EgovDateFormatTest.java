package egovframework.com.utl.fcc.service;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

public class EgovDateFormatTest {

    @Test
    public void 기본_로케일로_날짜_형식_변환() {
        Date date = new Date();
        String expected = DateFormat.getDateInstance().format(date);
        String actual = EgovDateFormat.formatDate(date);
        assertEquals(expected, actual);
    }

    @Test
    public void 특정_로케일로_날짜_형식_변환() {
        Date date = new Date();
        Locale locale = Locale.FRANCE;
        String expected = DateFormat.getDateInstance(DateFormat.DEFAULT, locale).format(date);
        String actual = EgovDateFormat.formatDate(locale, date);
        assertEquals(expected, actual);
    }

    @Test
    public void 특정_스타일로_날짜_형식_변환() {
        Date date = new Date();
        int style = DateFormat.LONG;
        String expected = DateFormat.getDateInstance(style).format(date);
        String actual = EgovDateFormat.formatDate(style, date);
        assertEquals(expected, actual);
    }

    @Test
    public void 특정_스타일과_로케일로_날짜_형식_변환() {
        Date date = new Date();
        int style = DateFormat.MEDIUM;
        Locale locale = Locale.JAPAN;
        String expected = DateFormat.getDateInstance(style, locale).format(date);
        String actual = EgovDateFormat.formatDate(style, locale, date);
        assertEquals(expected, actual);
    }

    @Test
    public void 기본_로케일로_날짜와_시간_형식_변환() {
        Date date = new Date();
        String expected = DateFormat.getDateTimeInstance().format(date);
        String actual = EgovDateFormat.formatDateTime(date);
        assertEquals(expected, actual);
    }

    @Test
    public void 특정_로케일로_날짜와_시간_형식_변환() {
        Date date = new Date();
        Locale locale = Locale.ITALY;
        String expected = DateFormat.getDateTimeInstance(DateFormat.DEFAULT, DateFormat.DEFAULT, locale).format(date);
        String actual = EgovDateFormat.formatDateTime(locale, date);
        assertEquals(expected, actual);
    }

    @Test
    public void 특정_스타일로_날짜와_시간_형식_변환() {
        Date date = new Date();
        int dateStyle = DateFormat.SHORT;
        int timeStyle = DateFormat.SHORT;
        String expected = DateFormat.getDateTimeInstance(dateStyle, timeStyle).format(date);
        String actual = EgovDateFormat.formatDateTime(dateStyle, timeStyle, date);
        assertEquals(expected, actual);
    }

    @Test
    public void 특정_스타일과_로케일로_날짜와_시간_형식_변환() {
        Date date = new Date();
        int dateStyle = DateFormat.LONG;
        int timeStyle = DateFormat.SHORT;
        Locale locale = Locale.GERMANY;
        String expected = DateFormat.getDateTimeInstance(dateStyle, timeStyle, locale).format(date);
        String actual = EgovDateFormat.formatDateTime(dateStyle, timeStyle, locale, date);
        assertEquals(expected, actual);
    }

    @Test
    public void 기본_로케일로_시간_형식_변환() {
        Date date = new Date();
        String expected = DateFormat.getTimeInstance().format(date);
        String actual = EgovDateFormat.formatTime(date);
        assertEquals(expected, actual);
    }

    @Test
    public void 특정_로케일로_시간_형식_변환() {
        Date date = new Date();
        Locale locale = Locale.CHINA;
        String expected = DateFormat.getTimeInstance(DateFormat.DEFAULT, locale).format(date);
        String actual = EgovDateFormat.formatTime(locale, date);
        assertEquals(expected, actual);
    }

    @Test
    public void 특정_스타일로_시간_형식_변환() {
        Date date = new Date();
        int style = DateFormat.FULL;
        String expected = DateFormat.getTimeInstance(style).format(date);
        String actual = EgovDateFormat.formatTime(style, date);
        assertEquals(expected, actual);
    }

    @Test
    public void 특정_스타일과_로케일로_시간_형식_변환() {
        Date date = new Date();
        int style = DateFormat.SHORT;
        Locale locale = Locale.KOREA;
        String expected = DateFormat.getTimeInstance(style, locale).format(date);
        String actual = EgovDateFormat.formatTime(style, locale, date);
        assertEquals(expected, actual);
    }
}