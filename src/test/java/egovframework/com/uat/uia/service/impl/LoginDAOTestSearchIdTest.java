package egovframework.com.uat.uia.service.impl;

import static org.junit.Assert.assertEquals;

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
 * [10. 로그인][LoginDAO.searchId] DAO 단위 테스트
 * 
 * @author 이백행
 * @since 2024-09-16
 *
 */
@ContextConfiguration(classes = { LoginDAOTestSearchIdTest.class, EgovTestAbstractDAO.class })
@Configuration
@ComponentScan(useDefaultFilters = false, basePackages = {
		"egovframework.com.uat.uia.service.impl" }, includeFilters = {
				@Filter(type = FilterType.ASSIGNABLE_TYPE, classes = { LoginDAO.class }) })
@NoArgsConstructor
@Slf4j
public class LoginDAOTestSearchIdTest extends EgovTestAbstractDAO {

	/**
	 * 일반 로그인, 인증서 로그인을 처리하는 DAO 클래스
	 */
	@Autowired
	private LoginDAO loginDAO;

	/**
	 * 아이디를 찾는다.
	 * 
	 * @throws Exception
	 */
	@Test
	public void test() throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("아이디를 찾는다.");
		}

		// given
		final LoginVO loginVO = new LoginVO();
		// 일반회원
		loginVO.setUserSe("GNR");

		loginVO.setName("일반회원");
		loginVO.setEmail("egovframesupport@gmail.com");

		loginVO.setId("USER");

//		// 기업회원
//		loginVO.setUserSe("ENT");
//
//		loginVO.setName("NIA");
//		loginVO.setEmail("egovframesupport@gmail.com");
//
//		loginVO.setId("ENTERPRISE");

//		// 업무사용자
//		loginVO.setUserSe("USR");
//
//		loginVO.setName("테스트1");
//		loginVO.setEmail("egovframesupport@gmail.com");
//
//		loginVO.setId("TEST1");

		// when
		final LoginVO resultVO = loginDAO.searchId(loginVO);

		// then
		debug(loginVO, resultVO);

		assertEquals("getId 아이디", loginVO.getId(), resultVO.getId());
	}

	private void debug(final LoginVO loginVO, final LoginVO resultVO) {
		if (log.isDebugEnabled()) {
			log.debug("resultVO={}", resultVO);
			log.debug("getId={}", resultVO.getId());

			log.debug("assert");
			log.debug("getId={}, {}", resultVO.getId(), resultVO.getId());
		}
	}

}
