package egovframework.com.uat.uia.service.impl;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

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
 * [10. 로그인][LoginDAO.updateLoginIncorrect] DAO 단위 테스트
 * 
 * @author 이백행
 * @since 2024-09-20
 *
 */
@ContextConfiguration(classes = { LoginDAOTestUpdateLoginIncorrectTest.class, EgovTestAbstractDAO.class })
@Configuration
@ComponentScan(useDefaultFilters = false, basePackages = {
		"egovframework.com.uat.uia.service.impl" }, includeFilters = {
				@Filter(type = FilterType.ASSIGNABLE_TYPE, classes = { LoginDAO.class }) })
@NoArgsConstructor
@Slf4j
public class LoginDAOTestUpdateLoginIncorrectTest extends EgovTestAbstractDAO {

	/**
	 * 일반 로그인, 인증서 로그인을 처리하는 DAO 클래스
	 */
	@Autowired
	private LoginDAO loginDAO;

	/**
	 * 로그인인증제한 내역을 업데이트 한다.
	 * 
	 * @throws Exception
	 */
	@Test
	public void test() throws Exception {
		// given
		final Map<String, Object> map = new ConcurrentHashMap<>();

		// 로그인인증제한 변경 > 일반회원
		map.put("USER_SE", "GNR");
		map.put("updateAt", "E"); // LOCK 해제
		map.put("id", "USER");

//		// 로그인인증제한 변경 > 기업사용자
//		map.put("USER_SE", "ENT");
//		map.put("updateAt", "E"); // LOCK 해제
//		map.put("id", "ENTERPRISE");

//		// 로그인인증제한 변경 > 업무사용자
//		map.put("USER_SE", "USR");
//		map.put("updateAt", "E"); // LOCK 해제
//		map.put("id", "TEST1");

		// when
		loginDAO.updateLoginIncorrect(map);

		// then
		asserts(map);
	}

	private void asserts(final Map<String, Object> map) throws Exception {
		final LoginVO loginVO = new LoginVO();
		loginVO.setUserSe((String) map.get("USER_SE"));
		loginVO.setId((String) map.get("id"));
		final Map<?, ?> result = loginDAO.selectLoginIncorrect(loginVO);
		final String userId = (String) result.get("userId");
		final String lockAt = (String) result.get("lockAt");
		final BigDecimal lockCnt = (BigDecimal) result.get("lockCnt");

		if (log.isDebugEnabled()) {
			log.debug("map={}", map);
			log.debug("result={}", result);
			log.debug("아이디={}, {}", loginVO.getId(), userId);
			log.debug("lockAt={}, {}", "N", lockAt);
			log.debug("lockCnt={}, {}", new BigDecimal(0), lockCnt);
		}

		assertEquals("로그인인증제한 내역을 업데이트 한다. LOCK 해제 userId", loginVO.getId(), userId);
		assertEquals("로그인인증제한 내역을 업데이트 한다. LOCK 해제 lockAt", "N", lockAt);
		assertEquals("로그인인증제한 내역을 업데이트 한다. LOCK 해제 lockCnt", new BigDecimal(0), lockCnt);
	}

}
