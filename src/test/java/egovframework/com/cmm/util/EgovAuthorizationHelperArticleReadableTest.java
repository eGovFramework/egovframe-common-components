package egovframework.com.cmm.util;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.exception.EgovAccessDeniedException;
import egovframework.com.cmm.service.EgovUserDetailsService;

/**
 * 비밀글 열람 판정(isArticleReadable·assertArticleReadable) — 스크랩·댓글·만족도·게시글 상세가 같이 쓰는 기준.
 */
class EgovAuthorizationHelperArticleReadableTest {

	private static final String WRITER = "USRCNFRM_00000000000";

	@AfterEach
	void clearLoginUser() {
		new EgovUserDetailsHelper().setEgovUserDetailsService(null);
	}

	@Test
	void secretArticleIsReadableOnlyByWriter() {
		login(WRITER, "ROLE_USER");
		assertTrue(EgovAuthorizationHelper.isArticleReadable("Y", WRITER));
		assertDoesNotThrow(() -> EgovAuthorizationHelper.assertArticleReadable("Y", WRITER));

		login("USRCNFRM_00000000001", "ROLE_ADMIN"); // 관리자도 예외 아님
		assertFalse(EgovAuthorizationHelper.isArticleReadable("Y", WRITER));
		assertThrows(EgovAccessDeniedException.class, () -> EgovAuthorizationHelper.assertArticleReadable("Y", WRITER));
		assertTrue(EgovAuthorizationHelper.isArticleReadable("N", WRITER));
		assertTrue(EgovAuthorizationHelper.isArticleReadable(null, WRITER));

		assertFalse(EgovAuthorizationHelper.isArticleReadable("Y", "")); // 작성자 정보 없는 비밀글

		login(null, null); // 미로그인
		assertFalse(EgovAuthorizationHelper.isArticleReadable("Y", WRITER));
		assertFalse(EgovAuthorizationHelper.isArticleReadable("Y", null));
		assertFalse(EgovAuthorizationHelper.isArticleReadable("Y", "")); // 미로그인 + 빈 작성자
		assertTrue(EgovAuthorizationHelper.isArticleReadable("N", ""));
	}

	private static void login(String uniqId, String role) {
		LoginVO loginVO = new LoginVO();
		loginVO.setUniqId(uniqId);
		new EgovUserDetailsHelper().setEgovUserDetailsService(new EgovUserDetailsService() {
			@Override
			public Object getAuthenticatedUser() {
				return uniqId == null ? null : loginVO;
			}

			@Override
			public List<String> getAuthorities() {
				return role == null ? List.of() : List.of(role);
			}

			@Override
			public Boolean isAuthenticated() {
				return uniqId != null;
			}
		});
	}
}
