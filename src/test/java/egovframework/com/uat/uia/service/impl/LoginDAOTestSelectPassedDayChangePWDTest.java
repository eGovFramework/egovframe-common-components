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
 * [10. 로그인][LoginDAO.selectPassedDayChangePWD] DAO 단위 테스트
 * 
 * @author 이백행
 * @since 2024-09-21
 *
 */
@ContextConfiguration(classes = { LoginDAOTestSelectPassedDayChangePWDTest.class, EgovTestAbstractDAO.class })
@Configuration
@ComponentScan(useDefaultFilters = false, basePackages = {
		"egovframework.com.uat.uia.service.impl" }, includeFilters = {
				@Filter(type = FilterType.ASSIGNABLE_TYPE, classes = { LoginDAO.class }) })
@NoArgsConstructor
@Slf4j
public class LoginDAOTestSelectPassedDayChangePWDTest extends EgovTestAbstractDAO {

	/**
	 * 일반 로그인, 인증서 로그인을 처리하는 DAO 클래스
	 */
	@Autowired
	private LoginDAO loginDAO;

	/**
	 * 테스트
	 * 
	 * @throws Exception
	 */
	@Test
	public void test() throws Exception {
		// 일반회원
		final LoginVO testDataGNR = testDataGNR();
		testData(testDataGNR);
		selectPassedDayChangePWD(testDataGNR);

		// 기업회원
		final LoginVO testDataENT = testDataENT();
		testData(testDataENT);
		selectPassedDayChangePWD(testDataENT);

		// 업무사용자
		final LoginVO testDataUSR = testDataUSR();
		testData(testDataUSR);
		selectPassedDayChangePWD(testDataUSR);

		assertEquals("", "", "");
	}

	private LoginVO testDataGNR() throws Exception {
		// given
		final LoginVO loginVO = new LoginVO();

		// 일반회원
		loginVO.setUserSe("GNR");

		loginVO.setId("USER");

//				loginVO.setPassword("6TAJYwhKCgkgzPXDb83ZUiHi2/TKHhD7t5Ba6RN2qoo=");
		loginVO.setPassword(EgovFileScrty.encryptPassword("rhdxhd12", loginVO.getId()));

		return loginVO;
	}

	private LoginVO testDataENT() throws Exception {
		// given
		final LoginVO loginVO = new LoginVO();

		// 기업회원
		loginVO.setUserSe("ENT");

		loginVO.setId("ENTERPRISE");

//		loginVO.setPassword("ZQhr3oB5QWjBnBO0kbFF7bvQDLkk+Em0ExjTq5JtVTo=");
		loginVO.setPassword(EgovFileScrty.encryptPassword("rhdxhd12", loginVO.getId()));

		return loginVO;
	}

	private LoginVO testDataUSR() throws Exception {
		// given
		final LoginVO loginVO = new LoginVO();

		// 업무사용자
		loginVO.setUserSe("USR");

		loginVO.setId("TEST1");

//				loginVO.setPassword("raHLBnHFcunwNzcDcfad4PhD11hHgXSUr7fc1Jk9uoQ=");
		loginVO.setPassword(EgovFileScrty.encryptPassword("rhdxhd12", loginVO.getId()));

		return loginVO;
	}

	private void testData(final LoginVO loginVO) throws Exception {
		loginDAO.updatePassword(loginVO);
	}

	private void selectPassedDayChangePWD(final LoginVO testData) throws Exception {
		// given
		final LoginVO loginVO = new LoginVO();

		loginVO.setUserSe(testData.getUserSe());
		loginVO.setId(testData.getId());

		// when
		final int result = loginDAO.selectPassedDayChangePWD(loginVO);

		// then
		asserts(loginVO, result);
	}

	private void asserts(final LoginVO loginVO, final int result) throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("loginVO={}", loginVO);
			log.debug("getUserSe={}", loginVO.getUserSe());
			log.debug("getId={}", loginVO.getId());

			log.debug("result={}", result);
		}

		assertEquals("비밀번호를 수정한후 경과한 날짜를 조회한다.", 0, result);
	}

}
