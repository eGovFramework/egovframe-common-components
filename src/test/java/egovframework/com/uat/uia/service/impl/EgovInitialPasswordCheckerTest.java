package egovframework.com.uat.uia.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.lang.reflect.Proxy;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;

import egovframework.com.cmm.LoginVO;
import egovframework.com.uat.uia.service.EgovLoginService;
import egovframework.com.utl.sim.service.EgovFileScrty;

/**
 * 초기 비밀번호 사용 여부 확인 테스트
 *
 * <p>로그인 서비스는 실제와 같은 규칙(사용자 ID 를 salt 로 한 SHA-256 저장값과 비교)으로 동작하는 스텁이다.</p>
 */
class EgovInitialPasswordCheckerTest {

	private static final String INITIAL = "rhdxhd12";

	/** 사용자 구분+ID → 저장된 비밀번호 해시 */
	private final Map<String, String> stored = new HashMap<>();

	private int serviceCalls;

	private boolean failService;

	private EgovInitialPasswordChecker checker(List<String> initialPasswords) {
		EgovLoginService service = (EgovLoginService) Proxy.newProxyInstance(getClass().getClassLoader(),
				new Class<?>[] { EgovLoginService.class }, (proxy, method, args) -> {
					if (!"actionLogin".equals(method.getName())) {
						throw new UnsupportedOperationException(method.getName());
					}
					serviceCalls++;
					if (failService) {
						throw new IllegalStateException("DB down");
					}
					// EgovLoginServiceImpl.actionLogin 과 같이 인자의 password 를 암호문으로 바꾼 뒤 조회한다
					LoginVO vo = (LoginVO) args[0];
					vo.setPassword(EgovFileScrty.encryptPassword(vo.getPassword(), vo.getId()));
					String hash = stored.get(vo.getUserSe() + ":" + vo.getId());
					LoginVO result = new LoginVO();
					if (vo.getPassword().equals(hash)) {
						result.setId(vo.getId());
						result.setPassword(hash);
					}
					return result;
				});
		EgovInitialPasswordChecker checker = new EgovInitialPasswordChecker() {
			@Override
			List<String> initialPasswords() {
				return initialPasswords;
			}
		};
		checker.setLoginService(service);
		return checker;
	}

	private void store(String userSe, String id, String password) {
		stored.put(userSe + ":" + id, EgovFileScrty.encryptPassword(password, id));
	}

	private static LoginVO login(String userSe, String id) {
		LoginVO vo = new LoginVO();
		vo.setUserSe(userSe);
		vo.setId(id);
		return vo;
	}

	@Test
	void 초기_비밀번호를_쓰는_세_사용자_구분을_모두_찾는다() {
		store("USR", "TEST1", INITIAL);
		store("GNR", "USER", INITIAL);
		store("ENT", "ENTERPRISE", INITIAL);
		EgovInitialPasswordChecker checker = checker(Collections.singletonList(INITIAL));

		assertTrue(checker.isInitialPassword(login("USR", "TEST1")));
		assertTrue(checker.isInitialPassword(login("GNR", "USER")));
		assertTrue(checker.isInitialPassword(login("ENT", "ENTERPRISE")));
	}

	@Test
	void 비밀번호를_바꾼_사용자는_해당하지_않는다() {
		store("USR", "TEST1", "Changed-Pw-2026!");
		assertFalse(checker(Collections.singletonList(INITIAL)).isInitialPassword(login("USR", "TEST1")));
	}

	@Test
	void 같은_비밀번호라도_사용자_ID_가_salt_라서_다른_계정의_해시와는_맞지_않는다() {
		// TEST1 의 저장값을 webmaster 에 넣어도(같은 평문 rhdxhd12) webmaster 기준 해시와는 다르다
		stored.put("USR:webmaster", EgovFileScrty.encryptPassword(INITIAL, "TEST1"));
		assertFalse(checker(Collections.singletonList(INITIAL)).isInitialPassword(login("USR", "webmaster")));
	}

	@Test
	void 목록의_어느_비밀번호든_맞으면_해당한다() {
		store("USR", "TEST1", "Temp-1234");
		assertTrue(checker(Arrays.asList(INITIAL, "Temp-1234")).isInitialPassword(login("USR", "TEST1")));
	}

	@Test
	void 목록이_비면_검사하지_않는다() {
		store("USR", "TEST1", INITIAL);
		assertFalse(checker(Collections.emptyList()).isInitialPassword(login("USR", "TEST1")));
		assertEquals(0, serviceCalls);
	}

	@Test
	void 사용자_정보가_없으면_조회하지_않고_false() {
		EgovInitialPasswordChecker checker = checker(Collections.singletonList(INITIAL));
		assertFalse(checker.isInitialPassword(null));
		assertFalse(checker.isInitialPassword(login("USR", " ")));
		assertFalse(checker.isInitialPassword(login(null, "TEST1")));
		assertEquals(0, serviceCalls);
	}

	@Test
	void 세션의_로그인_정보를_바꾸지_않는다() {
		store("USR", "TEST1", INITIAL);
		LoginVO session = login("USR", "TEST1");
		session.setPassword("session-value");

		assertTrue(checker(Collections.singletonList(INITIAL)).isInitialPassword(session));
		assertEquals("session-value", session.getPassword(), "서비스가 덮어쓰는 password 는 복사본에만 적용돼야 한다");
	}

	@Test
	void 조회가_실패하면_첫_화면을_막지_않고_false() {
		store("USR", "TEST1", INITIAL);
		failService = true;
		assertFalse(checker(Collections.singletonList(INITIAL)).isInitialPassword(login("USR", "TEST1")));
	}

	@Test
	void 설정값_파싱() {
		assertEquals(Arrays.asList("rhdxhd12", "Temp-1234"), EgovInitialPasswordChecker.parse(" rhdxhd12 , ,Temp-1234,"));
		assertTrue(EgovInitialPasswordChecker.parse("").isEmpty());
		assertTrue(EgovInitialPasswordChecker.parse("   ").isEmpty());
		assertTrue(EgovInitialPasswordChecker.parse(null).isEmpty());
		assertEquals(Collections.singletonList("99abc-pw"), EgovInitialPasswordChecker.parse("99abc-pw"));
	}
}
