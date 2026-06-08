package egovframework.com.cmm.util;

import java.util.List;

import egovframework.com.cmm.LoginVO;
import egovframework.com.utl.fcc.service.EgovStringUtil;

/**
 * 사용자관리(UMT) 수정 권한 Helper
 */
public final class EgovUmtAuthorizationHelper {

	private EgovUmtAuthorizationHelper() {
	}

	public static String getLoginUniqId() {
		LoginVO loginUser = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
		return loginUser == null ? "" : EgovStringUtil.isNullToString(loginUser.getUniqId());
	}

	public static boolean isAdmin() {
		List<String> authorities = EgovUserDetailsHelper.getAuthorities();
		return authorities != null && authorities.contains("ROLE_ADMIN");
	}

	public static boolean isSelfTarget(String targetUniqId) {
		String loginUniqId = getLoginUniqId();
		return !loginUniqId.isEmpty()
				&& targetUniqId != null
				&& loginUniqId.equals(targetUniqId);
	}

	/** 관리자이거나 수정 대상이 본인인 경우 true */
	public static boolean canModifyUser(String targetUniqId) {
		return isAdmin() || isSelfTarget(targetUniqId);
	}
}
