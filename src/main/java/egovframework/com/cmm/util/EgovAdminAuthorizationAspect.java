package egovframework.com.cmm.util;

import java.util.List;

import egovframework.com.cmm.exception.EgovAccessDeniedException;
import egovframework.com.cmm.exception.EgovLoginRequiredException;

public class EgovAdminAuthorizationAspect {

	/**
	 * 거부는 unchecked 예외로 던진다. checked 예외(ModelAndViewDefiningException)는 대상 메서드가 throws 로
	 * 선언하지 않으면 프록시가 UndeclaredThrowableException 으로 감싸 공통 오류 화면이 떴다.
	 * 화면 매핑은 egov-com-servlet.xml 의 SimpleMappingExceptionResolver 가 맡는다.
	 */
	public void assertAdmin() {
		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if (!isAuthenticated) {
			throw new EgovLoginRequiredException("로그인이 필요합니다.");
		}

		List<String> authorities = EgovUserDetailsHelper.getAuthorities();
		if (authorities == null || !authorities.contains("ROLE_ADMIN")) {
			throw new EgovAccessDeniedException("관리자 권한이 필요합니다.");
		}
	}
}
