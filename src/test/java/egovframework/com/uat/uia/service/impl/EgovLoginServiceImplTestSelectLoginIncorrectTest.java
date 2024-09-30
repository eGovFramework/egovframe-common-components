package egovframework.com.uat.uia.service.impl;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;

import org.egovframe.rte.fdl.cmmn.exception.BaseRuntimeException;
import org.egovframe.rte.psl.dataaccess.util.EgovMap;
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
 * [10. 로그인][EgovLoginServiceImpl.selectLoginIncorrect] ServiceImpl 단위 테스트
 * 
 * @author 이백행
 * @since 2024-09-30
 *
 */
@ContextConfiguration(classes = { EgovLoginServiceImplTestSelectLoginIncorrectTest.class, EgovTestAbstractDAO.class })
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
public class EgovLoginServiceImplTestSelectLoginIncorrectTest extends EgovTestAbstractDAO {

	/**
	 * 일반 로그인, 인증서 로그인을 처리하는 비즈니스 인터페이스 클래스
	 */
	@Autowired
	private EgovLoginService egovLoginService;

	/**
	 * 일반회원 로그인인증제한 내역을 조회한다.
	 */
	@Test
	public void test() {
		// given
		final LoginVO loginVO = givenGNR();

		// when
		final EgovMap result = when(loginVO);

		// then
		asserts(loginVO, result);
	}

	/**
	 * 기업회원 로그인인증제한 내역을 조회한다.
	 */
	@Test
	public void test2() {
		// given
		final LoginVO loginVO = givenENT();

		// when
		final EgovMap result = when(loginVO);

		// then
		asserts(loginVO, result);
	}

	/**
	 * 업무사용자 로그인인증제한 내역을 조회한다.
	 */
	@Test
	public void test3() {
		// given
		final LoginVO loginVO = givenUSR();

		// when
		final EgovMap result = when(loginVO);

		// then
		asserts(loginVO, result);
	}

	private LoginVO givenGNR() {
		final LoginVO loginVO = new LoginVO();
		// 일반회원
		loginVO.setUserSe("GNR");
		loginVO.setId("USER");
		return loginVO;
	}

	private LoginVO givenENT() {
		final LoginVO loginVO = new LoginVO();
		// 기업회원
		loginVO.setUserSe("ENT");
		loginVO.setId("ENTERPRISE");
		return loginVO;
	}

	private LoginVO givenUSR() {
		final LoginVO loginVO = new LoginVO();
		// 업무사용자
		loginVO.setUserSe("USR");
		loginVO.setId("TEST1");
		return loginVO;
	}

	private EgovMap when(final LoginVO loginVO) {
		try {
			return (EgovMap) egovLoginService.selectLoginIncorrect(loginVO);
		} catch (Exception e) {
			throw new BaseRuntimeException("Exception: selectLoginIncorrect", e);
		}
	}

	private void asserts(final LoginVO loginVO, final EgovMap result) {
		final String userId = (String) result.get("userId");
		final String userPw = (String) result.get("userPw");
		final String userNm = (String) result.get("userNm");
		final String uniqId = (String) result.get("uniqId");
		final String lockAt = (String) result.get("lockAt");
		final BigDecimal lockCnt = (BigDecimal) result.get("lockCnt");

		if (log.isDebugEnabled()) {
			log.debug("loginVO={}", loginVO);
			log.debug("getUserSe={}", loginVO.getUserSe());
			log.debug("getId={}", loginVO.getId());

			log.debug("");
			log.debug("result={}", result);
			log.debug("userId={}", userId);
			log.debug("userPw={}", userPw);
			log.debug("userNm={}", userNm);
			log.debug("uniqId={}", uniqId);
			log.debug("lockAt={}", lockAt);
			log.debug("lockCnt={}", lockCnt);

			log.debug("");
			log.debug("아이디={}, {}", loginVO.getId(), userId);
		}

		assertEquals("로그인인증제한 내역을 조회한다.", loginVO.getId(), userId);
	}

}
