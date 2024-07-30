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
 * 일반 로그인을 처리한다 DAO 단위 테스트
 * 
 * @author 이백행
 * @since 2024-07-31
 */

@ContextConfiguration(classes = { ActionLoginLoginDAOTest.class, EgovTestAbstractDAO.class })
@Configuration
@ComponentScan(useDefaultFilters = false, basePackages = {
		"egovframework.com.uat.uia.service.impl", }, includeFilters = {
				@Filter(type = FilterType.ASSIGNABLE_TYPE, classes = { LoginDAO.class, }) })
@NoArgsConstructor
@Slf4j
public class ActionLoginLoginDAOTest extends EgovTestAbstractDAO {

	/**
	 * 일반 로그인, 인증서 로그인을 처리하는 DAO 클래스
	 */
	@Autowired
	private LoginDAO loginDAO;

	/**
	 * 일반 로그인을 처리한다
	 * 
	 * @throws Exception
	 */
	@Test
	public void selectDeptJob() throws Exception {
		// given
		final LoginVO loginVO = new LoginVO();
		loginVO.setUserSe("USR");
		loginVO.setId("TEST1");
		loginVO.setPassword(EgovFileScrty.encryptPassword("rhdxhd12", loginVO.getId()));

		// when
		final LoginVO result = loginDAO.actionLogin(loginVO);

		if (log.isDebugEnabled()) {
			log.debug("result={}", result);
			log.debug("getUserSe={}, {}", loginVO.getUserSe(), result.getUserSe());
			log.debug("getId={}, {}", loginVO.getId(), result.getId());
			log.debug("getPassword={}, {}", loginVO.getPassword(), result.getPassword());
		}

		// then
		final String message = egovMessageSource.getMessage(FAIL_COMMON_SELECT);
		assertEquals(message, loginVO.getUserSe(), result.getUserSe());
		assertEquals(message, loginVO.getId(), result.getId());
		assertEquals(message, loginVO.getPassword(), result.getPassword());
	}

}
