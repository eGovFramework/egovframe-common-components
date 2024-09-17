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
 * [10. 로그인][LoginDAO.searchPassword] DAO 단위 테스트
 * 
 * @author 이백행
 * @since 2024-09-16
 *
 */
@ContextConfiguration(classes = { LoginDAOTestSearchPasswordTest.class, EgovTestAbstractDAO.class })
@Configuration
@ComponentScan(useDefaultFilters = false, basePackages = {
		"egovframework.com.uat.uia.service.impl" }, includeFilters = {
				@Filter(type = FilterType.ASSIGNABLE_TYPE, classes = { LoginDAO.class }) })
@NoArgsConstructor
@Slf4j
public class LoginDAOTestSearchPasswordTest extends EgovTestAbstractDAO {

	/**
	 * 일반 로그인, 인증서 로그인을 처리하는 DAO 클래스
	 */
	@Autowired
	private LoginDAO loginDAO;

	/**
	 * 비밀번호를 찾는다.
	 * 
	 * @throws Exception
	 */
	@Test
	public void test() throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("비밀번호를 찾는다.");
		}

		// given
		final LoginVO loginVO = new LoginVO();
		// 일반회원
		loginVO.setUserSe("GNR");

		loginVO.setId("USER");
		loginVO.setName("일반회원");
		loginVO.setEmail("egovframesupport@gmail.com");
		loginVO.setPasswordHint("P01");
		loginVO.setPasswordCnsr("전자정부표준프레임워크센터");

		loginVO.setPassword("6TAJYwhKCgkgzPXDb83ZUiHi2/TKHhD7t5Ba6RN2qoo=");

//		// 기업회원
//		loginVO.setUserSe("ENT");
//
//		loginVO.setId("ENTERPRISE");
//		loginVO.setName("NIA");
//		loginVO.setEmail("egovframesupport@gmail.com");
//		loginVO.setPasswordHint("P01");
//		loginVO.setPasswordCnsr("전자정부표준프레임워크센터");
//
//		loginVO.setPassword("ZQhr3oB5QWjBnBO0kbFF7bvQDLkk+Em0ExjTq5JtVTo=");

//		// 업무사용자
//		loginVO.setUserSe("USR");
//
//		loginVO.setId("TEST1");
//		loginVO.setName("테스트1");
//		loginVO.setEmail("egovframesupport@gmail.com");
//		loginVO.setPasswordHint("P01");
//		loginVO.setPasswordCnsr("전자정부표준프레임워크센터");
//
//		loginVO.setPassword("raHLBnHFcunwNzcDcfad4PhD11hHgXSUr7fc1Jk9uoQ=");

		// when
		final LoginVO resultVO = loginDAO.searchPassword(loginVO);

		// then
		debug(loginVO, resultVO);

		assertEquals("getPassword 패스워드", loginVO.getPassword(), resultVO.getPassword());
	}

	private void debug(final LoginVO loginVO, final LoginVO resultVO) {
		if (log.isDebugEnabled()) {
			log.debug("resultVO={}", resultVO);
			log.debug("getPassword={}", resultVO.getPassword());

			log.debug("assert");
			log.debug("getPassword={}, {}", resultVO.getPassword(), resultVO.getPassword());
		}
	}

}
