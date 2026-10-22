package egovframework.com.uat.uia.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.service.EgovProperties;
import egovframework.com.uat.uia.service.EgovLoginService;
import jakarta.annotation.Resource;

/**
 * 초기 비밀번호 사용 여부 확인
 *
 * <p>로그인 사용자가 초기(공개) 비밀번호 목록(Globals.InitialPasswords) 중 하나를 쓰고 있는지 확인한다.
 * 비밀번호는 사용자 ID 를 salt 로 한 SHA-256 으로 저장되므로 계정마다 저장값이 다르다. 그래서 고정된 해시와
 * 비교하지 않고, 목록의 각 비밀번호로 기존 로그인 조회(EgovLoginService.actionLogin - ID·암호화한 비밀번호·
 * 사용자 구분으로 조회하는 SELECT)가 통과하는지 본다. SQL 을 추가하지 않으며 평문 비밀번호를 세션에 두지 않는다.</p>
 *
 * <p>로그인 직후 첫 화면(EgovContent.do)과 비밀번호 안내 팝업이 이 결과로 변경 안내를 띄운다.</p>
 *
 * @author 개발팀
 * @since 2026.10.02
 * @version 1.0
 *
 *      <pre>
 *  == 개정이력(Modification Information) ==
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2026.10.02  개발팀         최초 생성
 *
 *      </pre>
 */
@Component("egovInitialPasswordChecker")
public class EgovInitialPasswordChecker {

	private static final Logger LOGGER = LoggerFactory.getLogger(EgovInitialPasswordChecker.class);

	/** 초기 비밀번호 목록 설정 키 */
	static final String PROPERTY_KEY = "Globals.InitialPasswords";

	@Resource(name = "loginService")
	private EgovLoginService loginService;

	public void setLoginService(EgovLoginService loginService) {
		this.loginService = loginService;
	}

	/**
	 * 로그인 사용자가 초기 비밀번호를 쓰고 있는지 확인한다.
	 *
	 * @param loginVO 로그인 사용자(세션의 값 - 바꾸지 않는다)
	 * @return 초기 비밀번호를 쓰고 있으면 true. 사용자 정보가 없거나 목록이 비었거나 조회에 실패하면 false
	 */
	public boolean isInitialPassword(LoginVO loginVO) {
		if (loginVO == null || isBlank(loginVO.getId()) || isBlank(loginVO.getUserSe())) {
			return false;
		}
		for (String initialPassword : initialPasswords()) {
			// actionLogin 은 인자의 password 를 암호문으로 덮어쓰므로 세션 객체가 아닌 복사본을 넘긴다
			LoginVO probe = new LoginVO();
			probe.setId(loginVO.getId());
			probe.setUserSe(loginVO.getUserSe());
			probe.setPassword(initialPassword);
			try {
				LoginVO result = loginService.actionLogin(probe);
				if (result != null && !isBlank(result.getId())) {
					return true;
				}
			} catch (Exception e) {
				// 확인 실패가 첫 화면을 막지 않도록 한다
				LOGGER.warn("Initial password check failed for user type {}: {}", loginVO.getUserSe(), e.getMessage());
				return false;
			}
		}
		return false;
	}

	/**
	 * 설정의 초기 비밀번호 목록(쉼표 구분, 앞뒤 공백 제거, 빈 값 제외).
	 *
	 * @return 목록 - 설정이 없거나 비어 있으면 빈 목록(검사하지 않음)
	 */
	List<String> initialPasswords() {
		return parse(EgovProperties.getProperty(PROPERTY_KEY));
	}

	static List<String> parse(String value) {
		if (value == null || value.isBlank()) {
			// EgovProperties 는 키가 없으면 빈 문자열을 돌려준다
			return Collections.emptyList();
		}
		List<String> passwords = new ArrayList<>();
		for (String token : value.split(",")) {
			String password = token.trim();
			if (!password.isEmpty()) {
				passwords.add(password);
			}
		}
		return passwords;
	}

	private static boolean isBlank(String value) {
		return value == null || value.trim().isEmpty();
	}
}
