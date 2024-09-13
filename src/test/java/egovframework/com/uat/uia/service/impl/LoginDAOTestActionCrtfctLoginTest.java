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
import egovframework.com.uss.umt.service.UserManageVO;
import egovframework.com.uss.umt.service.impl.UserManageDAO;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * [10. 로그인][LoginDAO.actionCrtfctLogin] DAO 단위 테스트
 * 
 * @author 이백행
 * @since 2024-09-14
 *
 */
@ContextConfiguration(classes = { LoginDAOTestActionCrtfctLoginTest.class, EgovTestAbstractDAO.class })
@Configuration
@ComponentScan(useDefaultFilters = false, basePackages = { "egovframework.com.uat.uia.service.impl",
		"egovframework.com.uss.umt.service.impl" }, includeFilters = {
				@Filter(type = FilterType.ASSIGNABLE_TYPE, classes = { LoginDAO.class, UserManageDAO.class }) })
@NoArgsConstructor
@Slf4j
public class LoginDAOTestActionCrtfctLoginTest extends EgovTestAbstractDAO {

	/**
	 * 일반 로그인, 인증서 로그인을 처리하는 DAO 클래스
	 */
	@Autowired
	private LoginDAO loginDAO;

	/**
	 * 사용자관리에 관한 데이터 접근 클래스를 정의한다.
	 */
	@Autowired
	private UserManageDAO userManageDAO;

	/**
	 * 인증서 로그인을 처리한다
	 * 
	 * @throws Exception
	 */
	@Test
	public void test() throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("인증서 로그인을 처리한다");
		}

		// testData
		final String uniqId = "USRCNFRM_99999999999";
		final UserManageVO testData = userManageDAO.selectUser(uniqId);

		testData.setSubDn("TEST_DN_" + uniqId);
		userManageDAO.updateUser(testData);

		// given
		final LoginVO loginVO = new LoginVO();

		// `CRTFC_DN_VALUE` varchar(100) DEFAULT NULL COMMENT '인증DN값',
		loginVO.setDn(testData.getSubDn());

		// when
		final LoginVO resultVO = loginDAO.actionCrtfctLogin(loginVO);

		// then
		debug(testData, resultVO);

		assertEquals("getUniqId 고유ID", testData.getUniqId(), resultVO.getUniqId());
	}

	private void debug(final UserManageVO userManageVO, final LoginVO resultVO) {
		if (log.isDebugEnabled()) {
			log.debug("resultVO={}", resultVO);
			log.debug("getId={}", resultVO.getId());
			log.debug("getName={}", resultVO.getName());
			log.debug("getPassword={}", resultVO.getPassword());

			log.debug("getIhidNum={}", resultVO.getIhidNum());
			log.debug("getEmail={}", resultVO.getEmail());
			log.debug("getUserSe={}", resultVO.getUserSe());
			log.debug("getOrgnztId={}", resultVO.getOrgnztId());
			log.debug("getUniqId={}", resultVO.getUniqId());

			log.debug("assert");
			log.debug("getUniqId={}, {}", userManageVO.getUniqId(), resultVO.getUniqId());
		}
	}

}
