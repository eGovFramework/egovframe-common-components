package egovframework.com.utl.fcc.service;

import static org.junit.Assert.*;

import org.junit.Test;

public class EgovNumberCheckUtilTest {

	@Test
	public void testValidForeignNumbers() {
        assertTrue("외국인 등록번호 샘플", EgovNumberCheckUtil.checkForeignNumber("990101", "5020063"));
    }

	@Test
	public void testInvalidForeignNumbers() {
        assertFalse("잘못된 성별 코드", EgovNumberCheckUtil.checkForeignNumber("990101", "4123456"));
        assertFalse("존재하지 않는 날짜 (2월 30일)", EgovNumberCheckUtil.checkForeignNumber("800230", "5123456"));
        assertFalse("잘못된 형식 (7자리 앞번호)", EgovNumberCheckUtil.checkForeignNumber("2000105", "7234567"));
        assertFalse("잘못된 성별 코드", EgovNumberCheckUtil.checkForeignNumber("031015", "9223456"));
        assertFalse("체크섬 검증 실패", EgovNumberCheckUtil.checkForeignNumber("881212", "5134560"));
    }
}
