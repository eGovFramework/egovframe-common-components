package egovframework.com.cmm.util;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.exception.EgovAccessDeniedException;

/**
 * 2026.07.30 보안 조치 - 화면별 수정/삭제 권한(작성자 본인 또는 관리자) 공통 검증 유틸.
 *
 * <p>메서드 규칙
 * <ul>
 * <li>{@code isXxx(..)} — 판별만 한다. 예외를 던지지 않고 true/false 를 돌려준다.</li>
 * <li>{@code assertXxx(..)} — 같은 이름의 {@code isXxx} 와 같은 기준으로 판별하고, 통과하지 못하면
 * {@link EgovAccessDeniedException} 을 던진다(접근거부 화면, 403). 거부 사유는 여기서 DEBUG 로그로 남긴다.</li>
 * <li>판별 기준은 규칙마다 {@code xxxDenyReason(..)} 한 곳에만 둔다(통과면 null, 거부면 사유).</li>
 * <li>{@code requireXxx(..)} — 값이 없으면 거부하고, 있으면 그 값을 돌려준다.</li>
 * <li>예외: {@link #assertLoginUser()} 는 로그인 사용자를 돌려준다.</li>
 * </ul>
 *
 * @see egovframework.com.cmm.annotation.RequireAdmin 소유자 개념이 없는 관리자 전용 화면에는 이 애노테이션을 사용한다.
 */
public final class EgovAuthorizationHelper {

	private static final Logger LOGGER = LoggerFactory.getLogger(EgovAuthorizationHelper.class);

	private static final String NO_LOGIN = "인증 정보가 없습니다.";
	private static final String NO_OWNER = "대상 또는 소유자 정보가 없습니다.";
	private static final String NO_DEPT = "대상 또는 부서 정보가 없습니다.";
	private static final String NO_TARGET = "대상 정보가 없습니다.";
	private static final String NOT_PERMITTED = "권한이 없습니다.";

	private EgovAuthorizationHelper() {
	}

	// ------------------------------------------------------------ 로그인

	/**
	 * 로그인 사용자를 반환한다. 미로그인 시 예외를 던진다(이 클래스에서 유일하게 값을 돌려주는 assert).
	 */
	public static LoginVO assertLoginUser() {
		deny("assertLoginUser", null, loginDenyReason());
		return currentUser();
	}

	// ------------------------------------------------------------ 대상 존재

	/**
	 * 조회한 대상이 없으면(요청한 ID 의 레코드가 없음) 접근거부로 중단하고, 있으면 그대로 돌려준다.
	 * 소유권을 따지지 않는 관리자 전용 경로에서 조회 결과를 이어 쓰기 전에 쓴다.
	 * <pre>TargetVO vo = EgovAuthorizationHelper.requireTarget(service.selectTarget(id));</pre>
	 */
	public static <T> T requireTarget(T target) {
		deny("requireTarget", null, target == null ? NO_TARGET : null);
		return target;
	}

	// ------------------------------------------------------------ 관리자

	/**
	 * 로그인한 관리자(ROLE_ADMIN)면 true.
	 */
	public static boolean isAdmin() {
		return adminDenyReason() == null;
	}

	/**
	 * 관리자(ROLE_ADMIN) 여부만 확인한다. 아니면 예외를 던진다.
	 */
	public static void assertAdmin() {
		deny("assertAdmin", null, adminDenyReason());
	}

	// ------------------------------------------------------------ 관리자 또는 소유자

	/**
	 * 관리자이거나 ownerUniqId가 현재 로그인 사용자의 uniqId와 일치하면 true. 미로그인 시 false.
	 * 관리자는 소유자 정보가 없는 데이터도 통과한다.
	 */
	public static boolean isAdminOrOwner(String ownerUniqId) {
		return adminOrOwnerDenyReason(ownerUniqId, false) == null;
	}

	/**
	 * isAdminOrOwner 와 같은 기준. 통과하지 못하면 예외를 던진다.
	 */
	public static void assertAdminOrOwner(String ownerUniqId) {
		deny("assertAdminOrOwner", ownerUniqId, adminOrOwnerDenyReason(ownerUniqId, false));
	}

	/**
	 * isAdminOrOwner 의 로그인 id(loginVO.getId()) 비교판.
	 * (예: 첨부/게시 계열 중 WRTER_ID를 로그인 id와 비교해 온 기존 관례가 있는 화면)
	 */
	public static boolean isAdminOrOwnerById(String ownerId) {
		return adminOrOwnerDenyReason(ownerId, true) == null;
	}

	/**
	 * isAdminOrOwnerById 와 같은 기준. 통과하지 못하면 예외를 던진다.
	 */
	public static void assertAdminOrOwnerById(String ownerId) {
		deny("assertAdminOrOwnerById", ownerId, adminOrOwnerDenyReason(ownerId, true));
	}

	// ------------------------------------------------------------ 소유자

	/**
	 * allowedUniqId가 현재 로그인 사용자의 uniqId와 일치하면 true. 관리자도 예외가 아니다.
	 * allowedUniqId 는 호출하는 메서드가 DB 에서 읽은 레코드의 필드로 정한다(등록자·결재자·예약자 등).
	 * 값이 없으면 대상 미존재/불완전한 데이터로 보고 false.
	 */
	public static boolean isOwner(String allowedUniqId) {
		return ownerDenyReason(allowedUniqId, false) == null;
	}

	/**
	 * isOwner 와 같은 기준. 통과하지 못하면 예외를 던진다.
	 */
	public static void assertOwner(String allowedUniqId) {
		deny("assertOwner", allowedUniqId, ownerDenyReason(allowedUniqId, false));
	}

	/**
	 * isOwner 의 로그인 id(loginVO.getId()) 비교판. 관리자도 예외가 아니다.
	 * 허용 대상을 uniqId 가 아닌 로그인 id 로 저장해 온 모듈용이며, 저장값이 uniqId 로 통일되면 isOwner 로 바꾼다.
	 */
	public static boolean isOwnerById(String allowedId) {
		return ownerDenyReason(allowedId, true) == null;
	}

	/**
	 * isOwnerById 와 같은 기준. 통과하지 못하면 예외를 던진다.
	 */
	public static void assertOwnerById(String allowedId) {
		deny("assertOwnerById", allowedId, ownerDenyReason(allowedId, true));
	}

	// ------------------------------------------------------------ 같은 부서

	/**
	 * deptId 가 현재 로그인 사용자의 부서(loginVO.getOrgnztId())와 같으면 true. 관리자도 예외가 아니다.
	 * 부서원이 함께 쓰는 자원(부서업무·부서일정)용. 부서 정보가 없으면 false.
	 */
	public static boolean isSameDept(String deptId) {
		return sameDeptDenyReason(deptId) == null;
	}

	/**
	 * isSameDept 와 같은 기준. 통과하지 못하면 예외를 던진다.
	 */
	public static void assertSameDept(String deptId) {
		deny("assertSameDept", deptId, sameDeptDenyReason(deptId));
	}

	// ------------------------------------------------------------ 게시글 열람

	/**
	 * 게시글 열람 가능 여부. 비밀글(secretAt='Y')은 작성자(frstRegisterId)만 true, 관리자도 예외가 아니다.
	 * 목록에서 본문을 가릴 때처럼 요청을 멈추지 않고 판별만 할 때 쓴다. 미로그인 시 비밀글은 false.
	 */
	public static boolean isArticleReadable(String secretAt, String frstRegisterId) {
		return articleReadDenyReason(secretAt, frstRegisterId) == null;
	}

	/**
	 * isArticleReadable 과 같은 기준. 게시글 상세뿐 아니라 그 글에 딸린 스크랩·댓글·만족도도 이 검사를 거친 뒤 조회·등록한다.
	 */
	public static void assertArticleReadable(String secretAt, String frstRegisterId) {
		deny("assertArticleReadable", frstRegisterId, articleReadDenyReason(secretAt, frstRegisterId));
	}

	// ------------------------------------------------------------ 명함 열람

	/**
	 * 명함 열람 가능 여부. 공개(othbcAt='Y') 명함은 true, 비공개 명함은 등록자(frstRegisterId)만 true. 관리자도 예외가 아니다.
	 */
	public static boolean isNcrdReadable(String othbcAt, String frstRegisterId) {
		return ncrdReadDenyReason(othbcAt, frstRegisterId) == null;
	}

	/**
	 * isNcrdReadable 과 같은 기준. 명함 ID 를 직접 넣어 여는 팝업·명함첩 추가처럼 목록을 거치지 않는 경로에서 쓴다.
	 */
	public static void assertNcrdReadable(String othbcAt, String frstRegisterId) {
		deny("assertNcrdReadable", frstRegisterId, ncrdReadDenyReason(othbcAt, frstRegisterId));
	}

	// ------------------------------------------------------------ 판별 기준(통과면 null, 거부면 사유)

	private static String loginDenyReason() {
		LoginVO loginVO = currentUser();
		return (loginVO == null || loginVO.getUniqId() == null || loginVO.getUniqId().isEmpty()) ? NO_LOGIN : null;
	}

	private static String adminDenyReason() {
		String reason = loginDenyReason();
		if (reason != null) {
			return reason;
		}
		List<String> auth = EgovUserDetailsHelper.getAuthorities();
		return (auth != null && auth.contains("ROLE_ADMIN")) ? null : NOT_PERMITTED;
	}

	private static String adminOrOwnerDenyReason(String ownerId, boolean byLoginId) {
		String reason = loginDenyReason();
		if (reason != null) {
			return reason;
		}
		if (adminDenyReason() == null) {
			return null;
		}
		return ownerDenyReason(ownerId, byLoginId);
	}

	private static String ownerDenyReason(String allowedId, boolean byLoginId) {
		String reason = loginDenyReason();
		if (reason != null) {
			return reason;
		}
		if (allowedId == null || allowedId.isBlank()) {
			return NO_OWNER;
		}
		LoginVO loginVO = currentUser();
		return allowedId.equals(byLoginId ? loginVO.getId() : loginVO.getUniqId()) ? null : NOT_PERMITTED;
	}

	private static String sameDeptDenyReason(String deptId) {
		String reason = loginDenyReason();
		if (reason != null) {
			return reason;
		}
		if (deptId == null || deptId.isBlank()) {
			return NO_DEPT;
		}
		return deptId.equals(currentUser().getOrgnztId()) ? null : NOT_PERMITTED;
	}

	private static String articleReadDenyReason(String secretAt, String frstRegisterId) {
		if (!"Y".equals(secretAt)) {
			return null;
		}
		// 비밀글은 소유자 규칙과 같다 — 미로그인·작성자 정보 없음(미로그인+빈 작성자 포함)·다른 사용자는 거부
		return ownerDenyReason(frstRegisterId, false);
	}

	private static String ncrdReadDenyReason(String othbcAt, String frstRegisterId) {
		if ("Y".equals(othbcAt)) {
			return null;
		}
		// 비공개 명함은 소유자 규칙과 같다 — 미로그인·등록자 정보 없음(명함 없음 포함)·다른 사용자는 거부
		return ownerDenyReason(frstRegisterId, false);
	}

	// ------------------------------------------------------------ 공통

	private static LoginVO currentUser() {
		Object principal = EgovUserDetailsHelper.getAuthenticatedUser();
		return principal instanceof LoginVO ? (LoginVO) principal : null;
	}

	/**
	 * 사유가 있으면 DEBUG 로그를 남기고 거부한다. 호출 지점마다 로그를 따로 넣지 않도록 여기서 한 번만 남긴다.
	 */
	private static void deny(String check, String target, String reason) {
		if (reason == null) {
			return;
		}
		if (LOGGER.isDebugEnabled()) {
			LoginVO loginVO = currentUser();
			LOGGER.debug("접근 거부 {} target={} user={} reason={}", check, target,
					loginVO == null ? null : loginVO.getUniqId(), reason);
		}
		throw new EgovAccessDeniedException(reason);
	}
}
