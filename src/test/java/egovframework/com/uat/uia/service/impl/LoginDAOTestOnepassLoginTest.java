package egovframework.com.uat.uia.service.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.test.context.ContextConfiguration;

import egovframework.com.cmm.LoginVO;
import egovframework.com.test.EgovTestAbstractDAO;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * [10. 로그인][LoginDAO.selectPassedDayChangePWD] DAO 단위 테스트
 * 
 * @author 이백행
 * @since 2024-09-23
 *
 */
@ContextConfiguration(classes = { LoginDAOTestOnepassLoginTest.class, EgovTestAbstractDAO.class })
@Configuration
@ComponentScan(useDefaultFilters = false, basePackages = {
		"egovframework.com.uat.uia.service.impl" }, includeFilters = {
				@Filter(type = FilterType.ASSIGNABLE_TYPE, classes = { LoginDAO.class }) })
@NoArgsConstructor
@Slf4j
public class LoginDAOTestOnepassLoginTest extends EgovTestAbstractDAO {

	/**
	 * 일반 로그인, 인증서 로그인을 처리하는 DAO 클래스
	 */
	@Autowired
	private LoginDAO loginDAO;

	/**
	 * 테스트
	 */
	@Test
	public void test() {
		// given
		final String id = "USER";

		// when
		final LoginVO resultLoginVO = onepassLogin(id);

		// then
		asserts(id, resultLoginVO);
	}

	/**
	 * 테스트
	 */
	@Test
	public void test2() {
		// given
		final String id = "ENTERPRISE";

		// when
		final LoginVO resultLoginVO = onepassLogin(id);

		// then
		asserts(id, resultLoginVO);
	}

	/**
	 * 테스트
	 */
	@Test
	public void test3() {
		// given
		final String id = "TEST1";

		// when
		final LoginVO resultLoginVO = onepassLogin(id);

		// then
		asserts(id, resultLoginVO);
	}

	private LoginVO onepassLogin(final String id) {
		LoginVO resultLoginVO = null;
		try {
			resultLoginVO = loginDAO.onepassLogin(id);
		} catch (Exception e) {
			fail("Exception: onepassLogin");
		}
		return resultLoginVO;
	}

	private void asserts(final String id, final LoginVO resultLoginVO) {
		if (log.isDebugEnabled()) {
			log.debug("id={}", id);

			log.debug("resultLoginVO={}", resultLoginVO);
			log.debug("getId={}", resultLoginVO.getId());

		}

		assertEquals("디지털원패스 인증 회원 조회한다.", id, resultLoginVO.getId());
	}

}
