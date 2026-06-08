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

	@Test
	void testNullReturnsFalseWithoutException() {
        // 단일 인자 검증 메서드는 null 입력 시 NPE가 아니라 false(무효)를 반환해야 한다.
        assertFalse(EgovNumberCheckUtil.checkJuminNumber(null), "주민번호 null");
        assertFalse(EgovNumberCheckUtil.checkBubinNumber(null), "법인번호 null");
        assertFalse(EgovNumberCheckUtil.checkCompNumber(null), "사업자번호 null");
        assertFalse(EgovNumberCheckUtil.checkForeignNumber((String) null), "외국인등록번호 null");
    }
}
