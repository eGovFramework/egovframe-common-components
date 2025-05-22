package egovframework.com.cmm.aop;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import egovframework.com.cmm.service.EgovProperties;
import lombok.extern.slf4j.Slf4j;

/**
 * EgovFileBasePathSecurityValidator Class 구현 단위 테스트
 * 
 * @author 이백행
 * @since 2025.05.22
 * @version 1.0
 * @see
 *
 *      <pre>
 *  == 개정이력(Modification Information) ==
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2025.05.22  이백행          최초 생성
 *
 *      </pre>
 */
@Slf4j
public class EgovFileBasePathSecurityValidatorTest {

	@Test
	public void test() {
		// given
		String basePath = null;

		if (log.isDebugEnabled()) {
			log.debug("basePath={}", basePath);
		}

		// when
		boolean validate = EgovFileBasePathSecurityValidator.validate(basePath);

		if (log.isDebugEnabled()) {
			log.debug("validate={}", validate);
		}

		// then
		assertEquals(false, validate);
	}

	@Test
	public void test2() {
		// given
		String basePath = "";

		if (log.isDebugEnabled()) {
			log.debug("basePath={}", basePath);
		}

		// when
		boolean validate = EgovFileBasePathSecurityValidator.validate(basePath);

		if (log.isDebugEnabled()) {
			log.debug("validate={}", validate);
		}

		// then
		assertEquals(false, validate);
	}

	@Test
	public void test3() {
		// given
		String basePath = EgovProperties.getProperty("Globals.fileStorePath");

		if (log.isDebugEnabled()) {
			log.debug("basePath={}", basePath);
		}

		// when
		boolean validate = EgovFileBasePathSecurityValidator.validate(basePath);

		if (log.isDebugEnabled()) {
			log.debug("validate={}", validate);
		}

		// then
		assertEquals(true, validate);
	}

	@Test
	public void test4() {
		// given
		String basePath = EgovProperties.getProperty("Globals.SynchrnServerPath");

		if (log.isDebugEnabled()) {
			log.debug("basePath={}", basePath);
		}

		// when
		boolean validate = EgovFileBasePathSecurityValidator.validate(basePath);

		if (log.isDebugEnabled()) {
			log.debug("validate={}", validate);
		}

		// then
		assertEquals(true, validate);
	}

}
