package egovframework.com.cmm.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 관리자(ROLE_ADMIN)만 호출할 수 있는 메서드에 붙인다. 관리자가 아니면 접근거부(403).
 *
 * <p>관리자 전용 메서드는 관리자끼리 신뢰하므로 수정·삭제에 작성자(소유권) 검사를 두지 않는다.
 * 결재자·신청자·예약자처럼 기능상 담당자가 있는 경우와 부서 공유 자원만
 * {@code EgovAuthorizationHelper} 로 담당자·같은 부서를 확인한다.</p>
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface RequireAdmin {
}
