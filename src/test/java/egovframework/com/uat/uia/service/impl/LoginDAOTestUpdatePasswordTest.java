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
import egovframework.com.utl.sim.service.EgovFileScrty;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * [10. 로그인][LoginDAO.updatePassword] DAO 단위 테스트
 * 
 * @author 이백행
 * @since 2024-09-18
 *
 */
@ContextConfiguration(classes = { LoginDAOTestUpdatePasswordTest.class, EgovTestAbstractDAO.class })
@Configuration
@ComponentScan(useDefaultFilters = false, basePackages = {
		"egovframework.com.uat.uia.service.impl" }, includeFilters = {
				@Filter(type = FilterType.ASSIGNABLE_TYPE, classes = { LoginDAO.class }) })
@NoArgsConstructor
@Slf4j
public class LoginDAOTestUpdatePasswordTest extends EgovTestAbstractDAO {

	/**
	 * 일반 로그인, 인증서 로그인을 처리하는 DAO 클래스
	 */
	@Autowired
	private LoginDAO loginDAO;

	/**
	 * 변경된 비밀번호를 저장한다.
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

//		loginVO.setPassword("6TAJYwhKCgkgzPXDb83ZUiHi2/TKHhD7t5Ba6RN2qoo=");
		loginVO.setPassword(EgovFileScrty.encryptPassword("rhdxhd12", loginVO.getId()));

//		// 기업회원
//		loginVO.setUserSe("ENT");
//
//		loginVO.setId("ENTERPRISE");
//
//		loginVO.setPassword("ZQhr3oB5QWjBnBO0kbFF7bvQDLkk+Em0ExjTq5JtVTo=");
////		loginVO.setPassword(EgovFileScrty.encryptPassword("rhdxhd12", loginVO.getId()));

//		// 업무사용자
//		loginVO.setUserSe("USR");
//
//		loginVO.setId("TEST1");
//
////		loginVO.setPassword("raHLBnHFcunwNzcDcfad4PhD11hHgXSUr7fc1Jk9uoQ=");
//		loginVO.setPassword(EgovFileScrty.encryptPassword("rhdxhd12", loginVO.getId()));

		// when
		final int result = loginDAO.updatePassword(loginVO);

		// then
		if (log.isDebugEnabled()) {
			log.debug("result={}, {}", 1, result);
		}

		assertEquals("변경된 비밀번호를 저장한다.", 1, result);
	}

}
