package egovframework.com.uat.uia.service.impl;

import static org.junit.Assert.assertEquals;

import java.util.Map;

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
 * [10. 로그인][LoginDAO.selectLoginIncorrect] DAO 단위 테스트
 * 
 * @author 이백행
 * @since 2024-09-19
 *
 */
@ContextConfiguration(classes = { LoginDAOTestSelectLoginIncorrectTest.class, EgovTestAbstractDAO.class })
@Configuration
@ComponentScan(useDefaultFilters = false, basePackages = {
		"egovframework.com.uat.uia.service.impl" }, includeFilters = {
				@Filter(type = FilterType.ASSIGNABLE_TYPE, classes = { LoginDAO.class }) })
@NoArgsConstructor
@Slf4j
public class LoginDAOTestSelectLoginIncorrectTest extends EgovTestAbstractDAO {

	/**
	 * 일반 로그인, 인증서 로그인을 처리하는 DAO 클래스
	 */
	@Autowired
	private LoginDAO loginDAO;

	/**
	 * 로그인인증제한 내역을 조회한다.
	 * 
	 * @throws Exception
	 */
	@Test
	public void test() throws Exception {
		// given
		final LoginVO loginVO = new LoginVO();

		// 일반회원
		loginVO.setUserSe("GNR");

		loginVO.setId("USER");

//		// 기업회원
//		loginVO.setUserSe("ENT");
//
//		loginVO.setId("ENTERPRISE");

//		// 업무사용자
//		loginVO.setUserSe("USR");
//
//		loginVO.setId("TEST1");

		// when
		final Map<?, ?> result = loginDAO.selectLoginIncorrect(loginVO);

		final String userId = (String) result.get("userId");

		// then
		if (log.isDebugEnabled()) {
			log.debug("result={}", result);
			log.debug("아이디={}, {}", loginVO.getId(), userId);
		}

		assertEquals("로그인인증제한 내역을 조회한다.", loginVO.getId(), userId);
	}

}
