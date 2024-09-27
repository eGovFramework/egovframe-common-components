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
import egovframework.com.test.EgovTestAbstractDAO;
import egovframework.com.uat.uia.service.EgovLoginService;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * [10. 로그인][EgovLoginServiceImpl.searchPassword] ServiceImpl 단위 테스트
 * 
 * @author 이백행
 * @since 2024-09-28
 *
 */
@ContextConfiguration(classes = { EgovLoginServiceImplTestSearchPasswordTest.class, EgovTestAbstractDAO.class })
@Configuration
@ImportResource({ "classpath*:egovframework/spring/com/idgn/context-idgn-MailMsg.xml",
		"classpath*:egovframework/spring/com/test-context-mail.xml", })
@ComponentScan(useDefaultFilters = false, basePackages = { "egovframework.com.uat.uia.service.impl",
		"egovframework.com.cop.ems.service", "egovframework.com.cmm.config",
		"egovframework.com.cmm.service", }, includeFilters = {
				@Filter(type = FilterType.ANNOTATION, classes = { Repository.class, Service.class, }),
				@Filter(type = FilterType.ASSIGNABLE_TYPE, classes = { EgovLoginService.class,
						EgovLoginConfig.class, }) })
@NoArgsConstructor
@Slf4j
public class EgovLoginServiceImplTestSearchPasswordTest extends EgovTestAbstractDAO {

	/**
	 * 일반 로그인, 인증서 로그인을 처리하는 비즈니스 인터페이스 클래스
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
		final boolean result = egovLoginService.searchPassword(loginVO);

		// then
		debug(loginVO, result);

		assertEquals("비밀번호를 찾는다.", true, result);
	}

	private void debug(final LoginVO loginVO, final boolean result) {
		if (log.isDebugEnabled()) {
			log.debug("loginVO={}", loginVO);
			log.debug("getId={}", loginVO.getId());
			log.debug("getName={}", loginVO.getName());
			log.debug("getEmail={}", loginVO.getEmail());
			log.debug("getPasswordHint={}", loginVO.getPasswordHint());
			log.debug("getPasswordCnsr={}", loginVO.getPasswordCnsr());

			log.debug("result={}", result);

			log.debug("assert");
			log.debug("비밀번호를 찾는다.={}, {}", true, result);
		}
	}

}
