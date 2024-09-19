package egovframework.com.sym.cal.service;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 *
 * RestVO 테스트
 * 
 * @author 컨트리뷰션 권태성
 * @since 2024.09.19
 * @version 1.0
 * @see
 *
 *      <pre>
* << 개정이력(Modification Information) >>
*
*   수정일        수정자         수정내용
*  -----------   --------    ---------------------------
*   2024.09.19    권태성         최초 생성
 *      </pre>
 */
public class RestdeVoTest {

	private RestdeVO restdeVO;

	@Before
	public void setUp() {
		restdeVO = new RestdeVO();
	}

	@Test
	public void test_getFormattedDtKeyword_withHyphen() {
		// Given
		restdeVO.setSearchKeyword("2024-08-01");

		// When
		String formattedKeyword = restdeVO.getFormattedDtKeyword();

		// Then
		assertEquals("20240801", formattedKeyword); // 하이픈이 제거된 값을 기대
	}

	@Test
	public void test_getFormattedDtKeyword_withoutHyphen() {
		// Given
		restdeVO.setSearchKeyword("20240801");

		// When
		String formattedKeyword = restdeVO.getFormattedDtKeyword();

		// Then
		assertEquals("20240801", formattedKeyword); // 하이픈이 없으면 그대로
	}

	@Test
	public void test_getFormattedDtKeyword_nullKeyword() {
		// Given
		restdeVO.setSearchKeyword(null);

		// When
		String formattedKeyword = restdeVO.getFormattedDtKeyword();

		// Then
		assertEquals("", formattedKeyword); // null일 때 빈 문자열을 기대
	}

	@Test
	public void test_getFormattedDtKeyword_emptyKeyword() {
		// Given
		restdeVO.setSearchKeyword("");

		// When
		String formattedKeyword = restdeVO.getFormattedDtKeyword();

		// Then
		assertEquals("", formattedKeyword); // 빈 문자열일 때 그대로 반환
	}

	@Test
	public void test_getFormattedDtKeyword_withMultipleHyphens() {
		// Given
		restdeVO.setSearchKeyword("2024-08-01-12");

		// When
		String formattedKeyword = restdeVO.getFormattedDtKeyword();

		// Then
		assertEquals("2024080112", formattedKeyword); // 여러 하이픈을 제거한 값을 기대
	}

}
