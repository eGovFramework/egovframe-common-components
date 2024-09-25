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
import egovframework.com.uss.umt.service.UserManageVO;
import egovframework.com.uss.umt.service.impl.UserManageDAO;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * [10. 로그인][EgovLoginServiceImpl.actionCrtfctLogin] ServiceImpl 단위 테스트
 * 
 * @author 이백행
 * @since 2024-09-26
 *
 */
@ContextConfiguration(classes = { EgovLoginServiceImplTestActionCrtfctLoginTest.class, EgovTestAbstractDAO.class })
@Configuration
@ImportResource({ "classpath*:egovframework/spring/com/idgn/context-idgn-MailMsg.xml",
		"classpath*:egovframework/spring/com/idgn/context-idgn-UsrCnfrm.xml" })
@ComponentScan(useDefaultFilters = false, basePackages = { "egovframework.com.uat.uia.service.impl",
		"egovframework.com.cop.ems.service", "egovframework.com.cmm.config", "egovframework.com.cmm.service",
		"egovframework.com.uss.umt.service.impl" }, includeFilters = {
				@Filter(type = FilterType.ANNOTATION, classes = { Repository.class, Service.class }),
				@Filter(type = FilterType.ASSIGNABLE_TYPE, classes = { EgovLoginService.class, EgovLoginConfig.class,
						EgovMultiPartEmail.class, UserManageDAO.class, }) })
@NoArgsConstructor
@Slf4j
public class EgovLoginServiceImplTestActionCrtfctLoginTest extends EgovTestAbstractDAO {

	/**
	 * 일반 로그인, 인증서 로그인을 처리하는 DAO 클래스
	 */
	@Autowired
	private EgovLoginService egovLoginService;

	/**
	 * 사용자관리에 관한 데이터 접근 클래스를 정의한다.
	 */
	@Autowired
	private UserManageDAO userManageDAO;

	/**
	 * 테스트
	 * 
	 * @throws Exception
	 */
	@Test
	public void test() throws Exception {
		final UserManageVO testData = testData();

		// given
		final LoginVO loginVO = new LoginVO();

		// `CRTFC_DN_VALUE` varchar(100) DEFAULT NULL COMMENT '인증DN값',
		loginVO.setDn(testData.getSubDn());

		// when
		final LoginVO resultLoginVO = egovLoginService.actionCrtfctLogin(loginVO);

		// then
		asserts(testData, loginVO, resultLoginVO);

		assertEquals("getUniqId 고유ID", testData.getUniqId(), resultLoginVO.getUniqId());
	}

	private UserManageVO testData() {
		final String uniqId = "USRCNFRM_99999999999";
		final UserManageVO testData = userManageDAO.selectUser(uniqId);

		testData.setSubDn("TEST_DN_" + uniqId);
		userManageDAO.updateUser(testData);

		return testData;
	}

	private void asserts(final UserManageVO testData, final LoginVO loginVO, final LoginVO resultLoginVO) {
		if (log.isDebugEnabled()) {
			log.debug("testData={}", testData);
			log.debug("getUniqId={}", testData.getUniqId());

			log.debug("loginVO={}", loginVO);
			log.debug("getDn={}", loginVO.getDn());

			log.debug("resultLoginVO={}", resultLoginVO);
			log.debug("getId={}", resultLoginVO.getId());
			log.debug("getName={}", resultLoginVO.getName());
			log.debug("getPassword={}", resultLoginVO.getPassword());

			log.debug("getIhidNum={}", resultLoginVO.getIhidNum());
			log.debug("getEmail={}", resultLoginVO.getEmail());
			log.debug("getUserSe={}", resultLoginVO.getUserSe());
			log.debug("getOrgnztId={}", resultLoginVO.getOrgnztId());
			log.debug("getUniqId={}", resultLoginVO.getUniqId());

			log.debug("assert");
			log.debug("getUniqId={}, {}", testData.getUniqId(), resultLoginVO.getUniqId());
		}

		assertEquals("getUniqId 고유ID", testData.getUniqId(), resultLoginVO.getUniqId());
	}

}
