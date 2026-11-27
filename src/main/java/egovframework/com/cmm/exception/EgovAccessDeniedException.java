package egovframework.com.cmm.exception;

/**
 * EgovAccessDeniedException 클래스
 *
 * <p>
 * 인가 실패(미인증, 관리자 권한 없음, 소유자 아님)를 나타낸다.
 * SimpleMappingExceptionResolver 에서 accessDenied 화면으로 매핑된다.
 * </p>
 *
 * <p>
 * 기존 인가 검사가 던지던 {@link IllegalStateException} 을 상속한다.
 * 호출부와 테스트가 IllegalStateException 을 기대하고 있어 호환을 유지하면서
 * 거부 화면만 분리하기 위한 것이다.
 * </p>
 *
 * @author 공통컴포넌트
 * @since 2026.09.21
 * @version 1.0
 * @see egovframework.com.cmm.util.EgovAuthorizationHelper
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일        수정자           수정내용
 *  -------      -------------  ----------------------
 *   2026.09.21                최초 생성
 * </pre>
 */
public class EgovAccessDeniedException extends IllegalStateException {

	private static final long serialVersionUID = 1L;

	public EgovAccessDeniedException(String message) {
		super(message);
	}

	public EgovAccessDeniedException(String message, Throwable cause) {
		super(message, cause);
	}

}
