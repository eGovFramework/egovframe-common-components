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
 * [10. 로그인][EgovLoginServiceImpl.searchId] ServiceImpl 단위 테스트
 * 
 * @author 이백행
 * @since 2024-09-27
 *
 */
@ContextConfiguration(classes = { EgovLoginServiceImplTestSearchIdTest.class, EgovTestAbstractDAO.class })
@Configuration
@ImportResource({ "classpath*:egovframework/spring/com/idgn/context-idgn-MailMsg.xml", })
@ComponentScan(useDefaultFilters = false, basePackages = { "egovframework.com.uat.uia.service.impl",
		"egovframework.com.cop.ems.service", "egovframework.com.cmm.config",
		"egovframework.com.cmm.service", }, includeFilters = {
				@Filter(type = FilterType.ANNOTATION, classes = { Repository.class, Service.class, }),
				@Filter(type = FilterType.ASSIGNABLE_TYPE, classes = { EgovLoginService.class, EgovLoginConfig.class,
						EgovMultiPartEmail.class, }) })
@NoArgsConstructor
@Slf4j
public class EgovLoginServiceImplTestSearchIdTest extends EgovTestAbstractDAO {

	/**
	 * 일반 로그인, 인증서 로그인을 처리하는 비즈니스 인터페이스 클래스
	 */
	@Autowired
	private EgovLoginService egovLoginService;

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
		final LoginVO resultVO = egovLoginService.searchId(loginVO);

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
