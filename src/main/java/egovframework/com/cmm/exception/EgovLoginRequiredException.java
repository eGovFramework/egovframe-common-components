package egovframework.com.cmm.exception;

/**
 * EgovLoginRequiredException 클래스
 *
 * <p>
 * 로그인하지 않은 사용자의 거부를 나타낸다.
 * SimpleMappingExceptionResolver 에서 로그인 화면 redirect 로 매핑된다.
 * unchecked 예외라 대상 메서드의 throws 선언과 무관하게 AOP 에서 던질 수 있다.
 * </p>
 *
 * @author 공통컴포넌트
 * @since 2026.09.28
 * @version 1.0
 * @see egovframework.com.cmm.util.EgovAdminAuthorizationAspect
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일        수정자           수정내용
 *  -------      -------------  ----------------------
 *   2026.09.28                최초 생성
 * </pre>
 */
public class EgovLoginRequiredException extends EgovAccessDeniedException {

	private static final long serialVersionUID = 1L;

	public EgovLoginRequiredException(String message) {
		super(message);
	}

}
