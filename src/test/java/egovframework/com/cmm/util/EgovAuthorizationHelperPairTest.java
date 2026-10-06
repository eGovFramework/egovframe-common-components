package egovframework.com.cmm.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.exception.EgovAccessDeniedException;
import egovframework.com.cmm.service.EgovUserDetailsService;

/**
 * isXxx 와 assertXxx 가 같은 기준으로 판별하는지, 거부 문구가 규칙대로인지 확인한다.
 */
class EgovAuthorizationHelperPairTest {

	private static final String ME = "USRCNFRM_00000000000";
	private static final String ME_ID = "TEST1";
	private static final String MY_DEPT = "ORGNZT_0000000000000";
	private static final String OTHER = "USRCNFRM_99999999999";

	@AfterEach
	void clearLoginUser() {
		new EgovUserDetailsHelper().setEgovUserDetailsService(null);
	}

	@Test
	void ownerRules() {
		login("ROLE_USER");
		expect(EgovAuthorizationHelper::isOwner, EgovAuthorizationHelper::assertOwner, ME, null);
		expect(EgovAuthorizationHelper::isOwner, EgovAuthorizationHelper::assertOwner, OTHER, "권한이 없습니다.");
		expect(EgovAuthorizationHelper::isOwner, EgovAuthorizationHelper::assertOwner, "", "대상 또는 소유자 정보가 없습니다.");
		expect(EgovAuthorizationHelper::isOwnerById, EgovAuthorizationHelper::assertOwnerById, ME_ID, null);
		expect(EgovAuthorizationHelper::isOwnerById, EgovAuthorizationHelper::assertOwnerById, ME, "권한이 없습니다.");
		expect(EgovAuthorizationHelper::isSameDept, EgovAuthorizationHelper::assertSameDept, MY_DEPT, null);
		expect(EgovAuthorizationHelper::isSameDept, EgovAuthorizationHelper::assertSameDept, "ORGNZT_X", "권한이 없습니다.");
		expect(EgovAuthorizationHelper::isSameDept, EgovAuthorizationHelper::assertSameDept, null, "대상 또는 부서 정보가 없습니다.");
		expect(EgovAuthorizationHelper::isAdminOrOwner, EgovAuthorizationHelper::assertAdminOrOwner, OTHER, "권한이 없습니다.");
		expect(EgovAuthorizationHelper::isAdminOrOwnerById, EgovAuthorizationHelper::assertAdminOrOwnerById, ME_ID, null);
		assertEquals(false, EgovAuthorizationHelper.isAdmin());
		assertThrows(EgovAccessDeniedException.class, EgovAuthorizationHelper::assertAdmin);
		// 명함: 공개는 누구나, 비공개는 등록자만, 명함 없음(등록자 null)은 거부
		assertEquals(true, EgovAuthorizationHelper.isNcrdReadable("Y", OTHER));
		assertEquals(true, EgovAuthorizationHelper.isNcrdReadable("N", ME));
		EgovAccessDeniedException e = assertThrows(EgovAccessDeniedException.class, () -> EgovAuthorizationHelper.assertNcrdReadable("N", OTHER));
		assertEquals("권한이 없습니다.", e.getMessage());
		e = assertThrows(EgovAccessDeniedException.class, () -> EgovAuthorizationHelper.assertNcrdReadable(null, null));
		assertEquals("대상 또는 소유자 정보가 없습니다.", e.getMessage());
	}

	@Test
	void adminPassesAdminRulesButNotOwnerRules() {
		login("ROLE_ADMIN");
		expect(EgovAuthorizationHelper::isAdminOrOwner, EgovAuthorizationHelper::assertAdminOrOwner, OTHER, null);
		expect(EgovAuthorizationHelper::isAdminOrOwner, EgovAuthorizationHelper::assertAdminOrOwner, null, null);
		expect(EgovAuthorizationHelper::isAdminOrOwnerById, EgovAuthorizationHelper::assertAdminOrOwnerById, "", null);
		expect(EgovAuthorizationHelper::isOwner, EgovAuthorizationHelper::assertOwner, OTHER, "권한이 없습니다."); // 관리자 예외 없음
		assertEquals(true, EgovAuthorizationHelper.isAdmin());
		EgovAuthorizationHelper.assertAdmin();
	}

	@Test
	void notLoggedIn() {
		loginNobody();
		expect(EgovAuthorizationHelper::isOwner, EgovAuthorizationHelper::assertOwner, ME, "인증 정보가 없습니다.");
		expect(EgovAuthorizationHelper::isAdminOrOwner, EgovAuthorizationHelper::assertAdminOrOwner, ME, "인증 정보가 없습니다.");
		expect(EgovAuthorizationHelper::isSameDept, EgovAuthorizationHelper::assertSameDept, MY_DEPT, "인증 정보가 없습니다.");
		assertEquals(false, EgovAuthorizationHelper.isNcrdReadable("N", "")); // 미로그인 + 빈 등록자
		assertEquals(true, EgovAuthorizationHelper.isNcrdReadable("Y", ""));
		EgovAccessDeniedException e = assertThrows(EgovAccessDeniedException.class, EgovAuthorizationHelper::assertLoginUser);
		assertEquals("인증 정보가 없습니다.", e.getMessage());
	}

	@Test
	void requireTargetReturnsTargetOrDenies() {
		login("ROLE_ADMIN");
		Object target = new Object();
		assertEquals(target, EgovAuthorizationHelper.requireTarget(target));
		EgovAccessDeniedException e = assertThrows(EgovAccessDeniedException.class, () -> EgovAuthorizationHelper.requireTarget(null));
		assertEquals("대상 정보가 없습니다.", e.getMessage());
	}

	/** is 결과와 assert 통과 여부가 같고, 거부 시 문구가 reason 인지 확인한다. reason==null 이면 통과. */
	private static void expect(Predicate<String> is, Consumer<String> assertion, String target, String reason) {
		assertEquals(reason == null, is.test(target), "is: " + target);
		if (reason == null) {
			assertion.accept(target);
		} else {
			EgovAccessDeniedException e = assertThrows(EgovAccessDeniedException.class, () -> assertion.accept(target));
			assertEquals(reason, e.getMessage(), "assert: " + target);
		}
	}

	private static void login(String role) {
		LoginVO loginVO = new LoginVO();
		loginVO.setUniqId(ME);
		loginVO.setId(ME_ID);
		loginVO.setOrgnztId(MY_DEPT);
		setService(loginVO, List.of(role));
	}

	private static void loginNobody() {
		setService(null, List.of());
	}

	private static void setService(LoginVO loginVO, List<String> roles) {
		new EgovUserDetailsHelper().setEgovUserDetailsService(new EgovUserDetailsService() {
			@Override
			public Object getAuthenticatedUser() {
				return loginVO;
			}

			@Override
			public List<String> getAuthorities() {
				return roles;
			}

			@Override
			public Boolean isAuthenticated() {
				return loginVO != null;
			}
		});
	}
}
