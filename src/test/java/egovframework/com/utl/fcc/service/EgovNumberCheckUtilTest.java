package egovframework.com.utl.fcc.service;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class EgovNumberCheckUtilTest {

	@Test
	void testValidForeignNumbers() {
        assertTrue(EgovNumberCheckUtil.checkForeignNumber("990101", "5020063"), "외국인 등록번호 샘플");
    }

	@Test
	void testInvalidForeignNumbers() {
        assertFalse(EgovNumberCheckUtil.checkForeignNumber("990101", "4123456"), "잘못된 성별 코드");
        assertFalse(EgovNumberCheckUtil.checkForeignNumber("800230", "5123456"), "존재하지 않는 날짜 (2월 30일)");
        assertFalse(EgovNumberCheckUtil.checkForeignNumber("2000105", "7234567"), "잘못된 형식 (7자리 앞번호)");
        assertFalse(EgovNumberCheckUtil.checkForeignNumber("031015", "9223456"), "잘못된 성별 코드");
        assertFalse(EgovNumberCheckUtil.checkForeignNumber("881212", "5134560"), "체크섬 검증 실패");
    }

	// 아래 주민등록번호는 모두 검증번호 산식(가중치 2,3,4,5,6,7,8,9,2,3,4,5)만 만족하도록 생성한 합성값이다.
	// 일련번호 자리를 모두 0으로 고정했으므로 실제 발급된 번호가 아니다.

	@Test
	void testJuminCenturyFollowsGenderCode() {
        // 출생 세기는 생년 앞자리가 아니라 성별구분 숫자가 결정한다.
        assertTrue(EgovNumberCheckUtil.checkJuminNumber("8503150000001"), "성별 0 → 1885-03-15");
        assertTrue(EgovNumberCheckUtil.checkJuminNumber("8503159000006"), "성별 9 → 1885-03-15");
        assertTrue(EgovNumberCheckUtil.checkJuminNumber("8503151000004"), "성별 1 → 1985-03-15");
        assertTrue(EgovNumberCheckUtil.checkJuminNumber("8503152000007"), "성별 2 → 1985-03-15");
        assertTrue(EgovNumberCheckUtil.checkJuminNumber("0001013000008"), "성별 3 → 2000-01-01");
        assertTrue(EgovNumberCheckUtil.checkJuminNumber("1907014000007"), "성별 4 → 2019-07-01");
    }

	@Test
	void testJuminBornIn2020sIsValid() {
        // 생년 앞자리가 2 이상이라는 이유로 1900년대로 단정하면 2020년 이후 출생자가 모두 무효 판정된다.
        assertTrue(EgovNumberCheckUtil.checkJuminNumber("2001023000008"), "2020-01-02 출생");
        assertTrue(EgovNumberCheckUtil.checkJuminNumber("2103153000001"), "2021-03-15 출생");
        assertTrue(EgovNumberCheckUtil.checkJuminNumber("2411084000008"), "2024-11-08 출생");
        assertTrue(EgovNumberCheckUtil.checkJuminNumber("2602013000003"), "2026-02-01 출생");
        assertTrue(EgovNumberCheckUtil.checkJuminNumber("9912314000005"), "2099-12-31 출생");
    }

	@Test
	void testJuminRejectsForeignGenderCode() {
        // 5~8은 외국인등록번호의 성별구분 숫자이므로 주민등록번호로는 무효다.
        assertFalse(EgovNumberCheckUtil.checkJuminNumber("8503155000005"), "성별 5");
        assertFalse(EgovNumberCheckUtil.checkJuminNumber("8503156000008"), "성별 6");
        assertFalse(EgovNumberCheckUtil.checkJuminNumber("8503157000001"), "성별 7");
        assertFalse(EgovNumberCheckUtil.checkJuminNumber("8503158000003"), "성별 8");
    }

	@Test
	void testJuminLeapDayFollowsResolvedCentury() {
        assertTrue(EgovNumberCheckUtil.checkJuminNumber("9602292000006"), "1996-02-29 (윤년)");
        assertTrue(EgovNumberCheckUtil.checkJuminNumber("0002293000001"), "2000-02-29 (윤년)");
        assertTrue(EgovNumberCheckUtil.checkJuminNumber("2002293000008"), "2020-02-29 (윤년)");
        assertFalse(EgovNumberCheckUtil.checkJuminNumber("0002291000006"), "1900-02-29 (평년이므로 없는 날짜)");
    }

	@Test
	void testJuminRejectsInvalidDate() {
        assertFalse(EgovNumberCheckUtil.checkJuminNumber("8502301000000"), "존재하지 않는 날짜 (2월 30일)");
        assertFalse(EgovNumberCheckUtil.checkJuminNumber("8513151000001"), "존재하지 않는 월 (13월)");
    }

	@Test
	void testNullReturnsFalseWithoutException() {
        // 단일 인자 검증 메서드는 null 입력 시 NPE가 아니라 false(무효)를 반환해야 한다.
        assertFalse(EgovNumberCheckUtil.checkJuminNumber(null), "주민번호 null");
        assertFalse(EgovNumberCheckUtil.checkBubinNumber(null), "법인번호 null");
        assertFalse(EgovNumberCheckUtil.checkCompNumber(null), "사업자번호 null");
        assertFalse(EgovNumberCheckUtil.checkForeignNumber((String) null), "외국인등록번호 null");
    }
}
