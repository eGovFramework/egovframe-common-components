package egovframework.com.uss.umt.validation;


import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import egovframework.rte.ptl.mvc.validation.RteGenericValidator;

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


public class JuminValidationTest {

	@Test
	public void testIdIhNumInvalidCase1Ok() {
		// - 없이 입력되어야 한다.
		String[] notOk = { "7612041110411" }; //검증코드 오
		
		for (int i = 0; i < notOk.length; i++) {
			assertFalse(RteGenericValidator.isValidIdIhNum(notOk[i]));
		}
		
	}

	@Test
	public void testIdIhNumInvalidCase2Ok() {
		// - 없이 입력되어야 한다.
		// 1800년대 태어난 남자 - 9
		// 1800년대 태어난 여자 - 0
		// 1900년대 태어난 남자 - 1
		// 1900년대 태어난 여자 - 2
		// 2000년대 태어난 남자 - 3
		// 2000년대 태어난 여자 - 4
		// 1900년대 태어난 외국인 남자 - 5
		// 1900년대 태어난 외국인 여자 - 6
		// 2000년대 태어난 외국인 남자 - 7
		// 2000년대 태어난 외국인 여자 - 8
		String[] notOk = { "7612049110419" }; //남녀구분(외국인포함) 오류 1~6
		
		for (int i = 0; i < notOk.length; i++) {
			assertFalse(RteGenericValidator.isValidIdIhNum(notOk[i]));
		}
		
	}

	@Test
	public void testIdIhNumInvalidCase3Ok() {
		// - 없이 입력되어야 한다.
		String[] notOk = { "7613041110410" };//월 1~12
		
		for (int i = 0; i < notOk.length; i++) {
			assertFalse(RteGenericValidator.isValidIdIhNum(notOk[i]));
		}
		
	}

	@Test
	public void testIdIhNumInvalidCase4Ok() {
		// - 없이 입력되어야 한다.
		String[] notOk = { "8802291110410" };//윤년 2월 29일
		
		for (int i = 0; i < notOk.length; i++) {
			assertFalse(RteGenericValidator.isValidIdIhNum(notOk[i]));
		}
		
	}

	@Test
	public void testIdIhNumValidCase1Ok() {
		// - 없이 입력되어야 한다.
		String[] ok = { "7612041110470" }; // 정상 케이스 
		
		for (int i = 0; i < ok.length; i++) {
			assertTrue(RteGenericValidator.isValidIdIhNum(ok[i]));
		}
	}


	@Test
	public void testIdIhNumValidCase2Ok() {
		// - 없이 입력되어야 한다.
		String[] notOk = { "8802291210412" };//윤년 2월 29일
		
		for (int i = 0; i < notOk.length; i++) {
			assertTrue(RteGenericValidator.isValidIdIhNum(notOk[i]));
		}
		
	}

	@Test
	public void testIdIhNumValidCase3Ok() {
		// - 없이 입력되어야 한다.
		String[] notOk = { "8802301210414" };//윤년 2월 30일 (오류)
		
		for (int i = 0; i < notOk.length; i++) {
			assertTrue(RteGenericValidator.isValidIdIhNum(notOk[i]));
		}
		
	}

	@Test
	public void testIdIhNumValidCase4Ok() {
		// - 없이 입력되어야 한다.
		String[] notOk = { "8803311210413" };// 3월 31일
		
		for (int i = 0; i < notOk.length; i++) {
			assertTrue(RteGenericValidator.isValidIdIhNum(notOk[i]));
		}
		
	}

	@Test
	public void testIdIhNumValidCase5Ok() {
		// - 없이 입력되어야 한다.
		String[] notOk = { "8803321210417" };// 3월 32일 (오류)
		
		for (int i = 0; i < notOk.length; i++) {
			assertTrue(RteGenericValidator.isValidIdIhNum(notOk[i]));
		}
		
	}

	
	@Test
	public void testIdIhNumValidCase6Ok() {
		// - 없이 입력되어야 한다.
		// 1800년대 태어난 남자 - 9 *테스트 케이스 
		// 1800년대 태어난 여자 - 0
		// 1900년대 태어난 남자 - 1
		// 1900년대 태어난 여자 - 2
		// 2000년대 태어난 남자 - 3
		// 2000년대 태어난 여자 - 4
		// 1900년대 태어난 외국인 남자 - 5
		// 1900년대 태어난 외국인 여자 - 6
		// 2000년대 태어난 외국인 남자 - 7
		// 2000년대 태어난 외국인 여자 - 8
		String[] notOk = { "8803319210415" };// 3월 31일 , 남녀구분 
		
		for (int i = 0; i < notOk.length; i++) {
			assertTrue(RteGenericValidator.isValidIdIhNum(notOk[i]));
		}
		
	}

	@Test
	public void testIdIhNumValidCase7Ok() {
		// - 없이 입력되어야 한다.
		// 1800년대 태어난 남자 - 9
		// 1800년대 태어난 여자 - 0
		// 1900년대 태어난 남자 - 1
		// 1900년대 태어난 여자 - 2
		// 2000년대 태어난 남자 - 3
		// 2000년대 태어난 여자 - 4
		// 1900년대 태어난 외국인 남자 - 5
		// 1900년대 태어난 외국인 여자 - 6
		// 2000년대 태어난 외국인 남자 - 7
		// 2000년대 태어난 외국인 여자 - 8 *테스트 케이스 
		String[] notOk = { "8803318210412" };// 3월 31일 , 남녀구분 
		
		for (int i = 0; i < notOk.length; i++) {
			assertTrue(RteGenericValidator.isValidIdIhNum(notOk[i]));
		}
		
	}

	@Test
	public void testIdIhNumValidCase8Ok() {
		// - 없이 입력되어야 한다.
		// 1800년대 태어난 남자 - 9
		// 1800년대 태어난 여자 - 0
		// 1900년대 태어난 남자 - 1
		// 1900년대 태어난 여자 - 2
		// 2000년대 태어난 남자 - 3
		// 2000년대 태어난 여자 - 4
		// 1900년대 태어난 외국인 남자 - 5
		// 1900년대 태어난 외국인 여자 - 6 *테스트 케이스 
		// 2000년대 태어난 외국인 남자 - 7
		// 2000년대 태어난 외국인 여자 - 8
		String[] notOk = { "8803316210417" };// 3월 31일 , 남녀구분 
		
		for (int i = 0; i < notOk.length; i++) {
			assertTrue(RteGenericValidator.isValidIdIhNum(notOk[i]));
		}
		
	}

	@Test
	public void testIdIhNumValidCase9Ok() {
		// - 없이 입력되어야 한다.
		// 1800년대 태어난 남자 - 9
		// 1800년대 태어난 여자 - 0
		// 1900년대 태어난 남자 - 1
		// 1900년대 태어난 여자 - 2
		// 2000년대 태어난 남자 - 3
		// 2000년대 태어난 여자 - 4 *테스트 케이스 
		// 1900년대 태어난 외국인 남자 - 5
		// 1900년대 태어난 외국인 여자 - 6
		// 2000년대 태어난 외국인 남자 - 7
		// 2000년대 태어난 외국인 여자 - 8
		String[] notOk = { "8803314210411" };// 3월 31일 , 남녀구분 
		
		for (int i = 0; i < notOk.length; i++) {
			assertTrue(RteGenericValidator.isValidIdIhNum(notOk[i]));
		}
		
	}

	
}
