package egovframework.com.cmm.util;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.web.util.WebUtils;

import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.exception.EgovAccessDeniedException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

/**
 * 첨부 개별 삭제 허가.
 *
 * <p>파일 테이블에는 소유자가 없어 공통 삭제(/cmm/fms/deleteFileInfs.do)는 스스로 소유권을 판정하지 못한다.
 * 부모 레코드를 아는 업무 컨트롤러가 소유권을 확인한 뒤 {@link #allowDelete}로 허가를 발급하고,
 * 공통 삭제는 {@link #assertDeleteAllowed}로 그 허가만 확인한다. 허가는 세션에만 있어 삭제 식별자
 * 암호문(기본 키 공개)을 위조해도 얻을 수 없다.</p>
 *
 * <p>로그아웃 없이 다른 계정으로 로그인하면 세션 속성이 이어지므로, 허가는 발급받은 사용자에 묶는다.</p>
 */
public final class EgovAttachmentGrants {

	/** 세션 속성: 첨부 ID → 허가받은 사용자 uniqId */
	private static final String DELETABLE_ATCH_FILE_IDS = "egovDeletableAtchFileIds";

	/** 세션 속성: 다운로드 토큰을 발급한 첨부 ID → 발급받은 사용자 uniqId */
	private static final String DOWNLOADABLE_ATCH_FILE_IDS = "egovDownloadableAtchFileIds";

	private EgovAttachmentGrants() {
	}

	/**
	 * 현재 사용자에게 이 세션에서 첨부 그룹의 개별 삭제를 허가한다. 소유권 확인 직후, DB에서 읽은 atchFileId로 호출한다.
	 */
	public static void allowDelete(HttpServletRequest request, String atchFileId) {
		if (atchFileId == null || atchFileId.isEmpty()) {
			return;
		}
		String uniqId = EgovAuthorizationHelper.assertLoginUser().getUniqId();
		HttpSession session = request.getSession();
		// 같은 세션의 수정 화면이 동시에 처음 열려도 저장소가 하나만 만들어지도록 조회·생성·저장을 묶는다
		synchronized (WebUtils.getSessionMutex(session)) {
			@SuppressWarnings("unchecked")
			Map<String, String> grants = (Map<String, String>) session.getAttribute(DELETABLE_ATCH_FILE_IDS);
			if (grants == null) {
				grants = new ConcurrentHashMap<>();
				session.setAttribute(DELETABLE_ATCH_FILE_IDS, grants);
			}
			grants.put(atchFileId, uniqId);
		}
	}

	/**
	 * 현재 사용자가 이 세션에서 첨부 그룹의 삭제 허가를 받지 않았으면 거부한다.
	 */
	public static void assertDeleteAllowed(HttpServletRequest request, String atchFileId) {
		Object principal = EgovUserDetailsHelper.getAuthenticatedUser();
		LoginVO loginVO = principal instanceof LoginVO ? (LoginVO) principal : null;
		HttpSession session = request.getSession(false);
		Map<?, ?> grants = session == null ? null : (Map<?, ?>) session.getAttribute(DELETABLE_ATCH_FILE_IDS);
		Object grantee = grants == null || atchFileId == null ? null : grants.get(atchFileId);
		if (loginVO == null || grantee == null || !grantee.equals(loginVO.getUniqId())) {
			throw new EgovAccessDeniedException("권한이 없습니다.");
		}
	}
	/**
	 * 2026.11.25 KVE-2026-3293 조치 - 현재 사용자에게 이 세션에서 첨부 그룹의 다운로드를 허가한다.
	 * 첨부 목록(/cmm/fms/selectFileInfs.do)이 다운로드 토큰을 만들 때 호출한다. 다운로드는 토큰 복호화와 별개로
	 * 이 허가를 확인하므로, 같은 키로 만든 다른 암호문을 토큰으로 써도 서버가 목록을 내려 준 첨부만 받을 수 있다.
	 */
	public static void allowDownload(HttpServletRequest request, String atchFileId) {
		if (atchFileId == null || atchFileId.isEmpty()) {
			return;
		}
		String uniqId = EgovAuthorizationHelper.assertLoginUser().getUniqId();
		HttpSession session = request.getSession();
		synchronized (WebUtils.getSessionMutex(session)) {
			@SuppressWarnings("unchecked")
			Map<String, String> grants = (Map<String, String>) session.getAttribute(DOWNLOADABLE_ATCH_FILE_IDS);
			if (grants == null) {
				grants = new ConcurrentHashMap<>();
				session.setAttribute(DOWNLOADABLE_ATCH_FILE_IDS, grants);
			}
			grants.put(atchFileId, uniqId);
		}
	}

	/**
	 * 현재 사용자가 이 세션에서 첨부 그룹의 다운로드 허가를 받지 않았으면 거부한다.
	 */
	public static void assertDownloadAllowed(HttpServletRequest request, String atchFileId) {
		Object principal = EgovUserDetailsHelper.getAuthenticatedUser();
		LoginVO loginVO = principal instanceof LoginVO ? (LoginVO) principal : null;
		HttpSession session = request.getSession(false);
		Map<?, ?> grants = session == null ? null : (Map<?, ?>) session.getAttribute(DOWNLOADABLE_ATCH_FILE_IDS);
		Object grantee = grants == null || atchFileId == null ? null : grants.get(atchFileId);
		if (loginVO == null || grantee == null || !grantee.equals(loginVO.getUniqId())) {
			throw new EgovAccessDeniedException("권한이 없습니다.");
		}
	}
}
