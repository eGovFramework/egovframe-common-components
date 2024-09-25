package egovframework.com.uat.uia.service.impl;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.ImportResource;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.test.context.ContextConfiguration;

import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.config.EgovLoginConfig;
import egovframework.com.cop.ems.service.EgovMultiPartEmail;
import egovframework.com.test.EgovTestAbstractDAO;
import egovframework.com.uat.uia.service.EgovLoginService;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * [10. 로그인][EgovLoginServiceImpl.actionLogin] ServiceImpl 단위 테스트
 * 
 * @author 이백행
 * @since 2024-09-25
 */

@ContextConfiguration(classes = { EgovLoginServiceImplTestActionLoginTest.class, EgovTestAbstractDAO.class })
@Configuration
@ImportResource({ "egovframework/spring/com/idgn/context-idgn-MailMsg.xml", })
@ComponentScan(useDefaultFilters = false, basePackages = { "egovframework.com.uat.uia.service.impl",
		"egovframework.com.cop.ems.service", "egovframework.com.cmm.config",
		"egovframework.com.cmm.service", }, includeFilters = {
				@Filter(type = FilterType.ANNOTATION, classes = { Repository.class, Service.class }),
				@Filter(type = FilterType.ASSIGNABLE_TYPE, classes = { EgovLoginService.class, EgovLoginConfig.class,
						EgovMultiPartEmail.class, }) })
@NoArgsConstructor
@Slf4j
public class EgovLoginServiceImplTestActionLoginTest extends EgovTestAbstractDAO {

	/**
	 * 일반 로그인, 인증서 로그인을 처리하는 DAO 클래스
	 */
	@Autowired
	private EgovLoginService egovLoginService;

	/**
	 * 테스트
	 * 
	 * @throws Exception
	 */
	@Test
	public void test() throws Exception {
		// given
		final LoginVO loginVO = new LoginVO();
		loginVO.setUserSe("USR");
		loginVO.setId("TEST1");
		loginVO.setPassword("rhdxhd12");

		// when
		final LoginVO resultLoginVO = egovLoginService.actionLogin(loginVO);

		// then
		asserts(loginVO, resultLoginVO);
	}

	private void asserts(final LoginVO loginVO, final LoginVO resultLoginVO) {
		if (log.isDebugEnabled()) {
			log.debug("resultLoginVO={}", resultLoginVO);
			log.debug("getUserSe={}, {}", loginVO.getUserSe(), resultLoginVO.getUserSe());
			log.debug("getId={}, {}", loginVO.getId(), resultLoginVO.getId());
			log.debug("getPassword={}, {}", loginVO.getPassword(), resultLoginVO.getPassword());
		}

		assertEquals("getUserSe", loginVO.getUserSe(), resultLoginVO.getUserSe());
		assertEquals("아이디", loginVO.getId(), resultLoginVO.getId());
		assertEquals("패스워드", loginVO.getPassword(), resultLoginVO.getPassword());
	}

}
