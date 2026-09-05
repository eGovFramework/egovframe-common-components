/**
 *
 */
package egovframework.com.cmm.web;

import java.util.List;

import org.egovframe.rte.fdl.property.EgovPropertyService;
import org.egovframe.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

import egovframework.com.cmm.ComDefaultVO;
import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import jakarta.annotation.Resource;

/**
 * EgovComAbstractController.java 클래스
 *
 * @author 이백행
 * @since 2022.05.04
 * @version 4.1.0
 * @see
 * 
 *      <pre>
 *  == 개정이력(Modification Information) ==
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2022.05.04  이백행          최초 생성
 *   2026-09-05  이백행          [2026년 컨트리뷰션] 서비스 로거와 추적 로케일 처리 수정
 *
 *      </pre>
 */
public abstract class EgovComAbstractController {

	@Resource(name = "propertiesService")
	private EgovPropertyService egovPropertyService;

	protected PaginationInfo builderPaginationInfo(ComDefaultVO comDefaultVO) {
		comDefaultVO.setPageUnit(egovPropertyService.getInt("pageUnit"));
		comDefaultVO.setPageSize(egovPropertyService.getInt("pageSize"));

		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(comDefaultVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(comDefaultVO.getPageUnit());
		paginationInfo.setPageSize(comDefaultVO.getPageSize());

		comDefaultVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		comDefaultVO.setLastIndex(paginationInfo.getLastRecordIndex());
		comDefaultVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

		return paginationInfo;
	}

	// EgovFileDownloadController

	/**
	 * 2026.07.13 KISA 보안취약점 조치 - 로그인 사용자 확인
	 */
	protected LoginVO egovAssertLoginUser() {
		LoginVO loginVO = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
		if (loginVO == null || loginVO.getUniqId() == null || "".equals(loginVO.getUniqId())) {
			throw new IllegalStateException("인증 정보가 없습니다.");
		}
		return loginVO;
	}

	/**
	 * 2026.07.13 KISA 보안취약점 조치 - 관리자 또는 소유자
	 */
	protected void egovAssertAdminOrOwner(String ownerUniqId) {
		LoginVO loginVO = egovAssertLoginUser();
		if (ownerUniqId != null && ownerUniqId.equals(loginVO.getUniqId())) {
			return;
		}
		java.util.List<String> auth = EgovUserDetailsHelper.getAuthorities();
		if (auth != null && auth.contains("ROLE_ADMIN")) {
			return;
		}
		throw new IllegalStateException("권한이 없습니다.");
	}

	// EgovTroblReqstController

	/**
	 * 2026.08.08 KISA 보안취약점 조치 - 관리자 또는 소유자(로그인ID 기준)<br>
	 * 이 컨트롤러는 등록자ID를 loginVO.getUniqId()가 아니라 loginVO.getId()(로그인 아이디)로 저장한다<br>
	 * (insertTroblReqst 참조). 위 egovAssertAdminOrOwner를 그대로 쓰면 필드가 어긋나 실제<br>
	 * 신청자까지 항상 차단되므로, 이 컨트롤러 전용으로 getId() 기준 비교를 별도로 둔다.<br>
	 */
	protected void egovAssertAdminOrOwnerById(String ownerLoginId) {
		LoginVO loginVO = egovAssertLoginUser();
		if (ownerLoginId != null && ownerLoginId.equals(loginVO.getId())) {
			return;
		}
		java.util.List<String> auth = EgovUserDetailsHelper.getAuthorities();
		if (auth != null && auth.contains("ROLE_ADMIN")) {
			return;
		}
		throw new IllegalStateException("권한이 없습니다.");
	}

	// EgovDeptSchdulManageController

	/**
	 * 관리자 또는 담당자 또는 등록자만 통과 (부서일정은 EgovDeptSchdulManageMainList의<br>
	 * "SCHDUL_CHARGER_ID = uniqId OR FRST_REGISTER_ID = uniqId" 조건이 실제 소유권 정의라<br>
	 * 담당자·등록자 둘 다 owner로 인정한다)<br>
	 */
	protected void egovAssertAdminOrChargerOrOwner(String chargerUniqId, String registerUniqId) {
		LoginVO loginVO = egovAssertLoginUser();
		String uniqId = loginVO.getUniqId();
		if ((chargerUniqId != null && chargerUniqId.equals(uniqId))
				|| (registerUniqId != null && registerUniqId.equals(uniqId))) {
			return;
		}
		java.util.List<String> auth = EgovUserDetailsHelper.getAuthorities();
		if (auth != null && auth.contains("ROLE_ADMIN")) {
			return;
		}
		throw new IllegalStateException("권한이 없습니다.");
	}

	// EgovQustnrRespondInfoController

	/**
	 * 설문응답 관리(응답자결과 조회/수정/삭제)는 관리자만 수행할 수 있도록 검증한다.<br>
	 * (KISA 보안취약점 조치: 응답 내용·응답자 개인정보를 담고 있어 관리자 전용 기능으로 제한)<br>
	 */
	protected void egovAssertAdmin() {
		if (!Boolean.TRUE.equals(EgovUserDetailsHelper.isAuthenticated())) {
			throw new IllegalStateException("인증 정보가 없습니다.");
		}
		List<String> authorities = EgovUserDetailsHelper.getAuthorities();
		if (authorities == null || !authorities.contains("ROLE_ADMIN")) {
			throw new IllegalStateException("권한이 없습니다.");
		}
	}

}
