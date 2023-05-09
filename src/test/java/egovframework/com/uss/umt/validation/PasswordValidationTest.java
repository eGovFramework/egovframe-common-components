package egovframework.com.uss.umt.validation;


import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import org.egovframe.rte.ptl.mvc.validation.RteGenericValidator;

/**
 * 비밀번호 생성 Test Class 구현
 * @author 표준프레임워크 윤주호
 * @since 2020.10.07
 * @version 3.10
 * @see
 * <pre>
 *
 *  수정일         수정자          수정내용
 *  ----------   ------------  ---------------------------
 *  2020.10.07   윤주호          최초 생성 (실행환경 테스트케이스에서 복사)
 *  2020.10.08   신용호          주민번호 샘플 수정 
 *
 * </pre>
 */


public class PasswordValidationTest {
	
	String[] password = {
			"1234567", "123456789012345678901", "한글패스워드입니다.", " 12345678",
			"abcdaaee", "abcaabbee", "aaaatest", "aaatesta", 
			"#!@#^#$#@", "\ttesttest"
		};
	
	@Test
	public void testPassword1() {
		String[] notOk = { "1234567", "123456789012345678901" };
		String[] ok = { "12345678", "12345678901234567890" };
		
		for (int i = 0; i < notOk.length; i++) {
			assertFalse(RteGenericValidator.checkLength(notOk[i]));
		}
		
		for (int i = 0; i < ok.length; i++) {
			assertTrue(RteGenericValidator.checkLength(ok[i]));
		}
	}
	
	@Test
	public void testPassword2() {
		String[] notOk = { "한글패스워드입니다", "abc def" };
		String[] ok = { "abcdefgh", "12345678", "#!@#^#$#@" };
		
		for (int i = 0; i < notOk.length; i++) {
//			assertFalse(RteGenericValidator.checkCharacterType(notOk[i]));
			assertFalse(RteGenericValidator.isMoreThan2CharTypeComb(notOk[i]));
			assertFalse(RteGenericValidator.isMoreThan3CharTypeComb(notOk[i]));
		}
		
		for (int i = 0; i < ok.length; i++) {
//			assertTrue(RteGenericValidator.checkCharacterType(ok[i]));
			assertFalse(RteGenericValidator.isMoreThan2CharTypeComb(ok[i]));
			assertFalse(RteGenericValidator.isMoreThan3CharTypeComb(ok[i]));
		}
	}
	
	@Test
	public void testPassword3() {
		String[] notOk = { "abcdaaee" };
		String[] ok = { "abcaabbee", };
		
		for (int i = 0; i < notOk.length; i++) {
//			assertFalse(RteGenericValidator.checkSeries(notOk[i]));
			assertTrue(RteGenericValidator.isSeriesCharacter(notOk[i]));
			assertFalse(RteGenericValidator.isRepeatCharacter(notOk[i]));
		}
		
		for (int i = 0; i < ok.length; i++) {
//			assertTrue(RteGenericValidator.checkSeries(ok[i]));
			assertTrue(RteGenericValidator.isSeriesCharacter(ok[i]));
			assertFalse(RteGenericValidator.isRepeatCharacter(ok[i]));
		}
	}
	
	@Test
	public void testPassword4() {
		String[] notOk = { "aaaatest" };
		String[] ok = { "aaatesta", };
		
		for (int i = 0; i < notOk.length; i++) {
//			assertFalse(RteGenericValidator.checkSeries(notOk[i]));
			assertFalse(RteGenericValidator.isSeriesCharacter(notOk[i]));
			assertTrue(RteGenericValidator.isRepeatCharacter(notOk[i]));
		}
		
		for (int i = 0; i < ok.length; i++) {
//			assertTrue(RteGenericValidator.checkSeries(ok[i]));
			assertFalse(RteGenericValidator.isSeriesCharacter(ok[i]));
			assertTrue(RteGenericValidator.isRepeatCharacter(ok[i]));
		}
	}
	
}
