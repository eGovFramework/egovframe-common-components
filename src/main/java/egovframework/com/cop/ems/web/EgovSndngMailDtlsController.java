package egovframework.com.cop.ems.web;

import java.util.List;

import org.egovframe.rte.fdl.property.EgovPropertyService;
import org.egovframe.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import egovframework.com.cmm.ComDefaultVO;
import egovframework.com.cmm.EgovMessageSource;
import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.annotation.IncludedInfo;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.com.cop.ems.service.EgovSndngMailDetailService;
import egovframework.com.cop.ems.service.EgovSndngMailDtlsService;
import egovframework.com.cop.ems.service.SndngMailVO;
import jakarta.annotation.Resource;

/**
 * 발송메일 내역을 조회하는 컨트롤러 클래스
 * @author 공통서비스 개발팀 박지욱
 * @since 2009.03.12
 * @version 1.0
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자          수정내용
 *  -------    --------    ---------------------------
 *  2009.03.12  박지욱          최초 생성
 *
 *  </pre>
 */
@Controller
public class EgovSndngMailDtlsController {

	/** EgovSndngMailDtlsService */
	@Resource(name = "sndngMailDtlsService")
	private EgovSndngMailDtlsService sndngMailDtlsService;

	/** EgovSndngMailDetailService */
	@Resource(name = "sndngMailDetailService")
	private EgovSndngMailDetailService sndngMailDetailService;

	/** EgovPropertyService */
	@Resource(name = "propertiesService")
	protected EgovPropertyService propertiesService;

	/** EgovMessageSource */
	@Resource(name = "egovMessageSource")
	EgovMessageSource egovMessageSource;

	/**
	 * 발송메일 내역을 조회한다
	 * @param searchVO ComDefaultVO
	 * @return String
	 * @exception Exception
	 */
	@IncludedInfo(name = "발송메일내역", order = 361, gid = 40)
	@RequestMapping(value = "/cop/ems/selectSndngMailList.do")
	public String selectSndngMailList(@ModelAttribute("searchVO") ComDefaultVO searchVO, ModelMap model) throws Exception {

		// 발송메일 내역 조회
		/** EgovPropertyService.sample */
		searchVO.setPageUnit(propertiesService.getInt("pageUnit"));
		searchVO.setPageSize(propertiesService.getInt("pageSize"));

		/** pageing */
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(searchVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(searchVO.getPageUnit());
		paginationInfo.setPageSize(searchVO.getPageSize());

		searchVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		searchVO.setLastIndex(paginationInfo.getLastRecordIndex());
		searchVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

		List<SndngMailVO> sndngMailList = sndngMailDtlsService.selectSndngMailList(searchVO);
		model.addAttribute("resultList", sndngMailList);

		int totCnt = sndngMailDtlsService.selectSndngMailListTotCnt(searchVO);
		paginationInfo.setTotalRecordCount(totCnt);
		model.addAttribute("paginationInfo", paginationInfo);
		model.addAttribute("message", egovMessageSource.getMessage("success.common.select"));

		return "egovframework/com/cop/ems/EgovMailDtls";
	}

	/**
	 * 발송메일을 삭제한다.
	 * @param sndngMailVO SndngMailVO
	 * @return String
	 * @exception
	 */
	@PostMapping("/cop/ems/deleteSndngMailList.do")
	public String deleteSndngMailList(@ModelAttribute("sndngMailVO") SndngMailVO sndngMailVO, ModelMap model) throws Exception {
		// 2026.07.13 KISA 보안취약점 조치
		LoginVO _loginVO = egovAssertLoginUser();


		if (sndngMailVO == null || sndngMailVO.getMssageId() == null || sndngMailVO.getMssageId().equals("")) {
			return "egovframework/com/cmm/egovError";
		}

		LoginVO loginVO = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
		if (loginVO == null || loginVO.getUniqId() == null) {
			throw new IllegalStateException("인증 정보가 없습니다.");
		}
		SndngMailVO resultMailVO = sndngMailDetailService.selectSndngMail(sndngMailVO);
		if (resultMailVO == null || resultMailVO.getMssageId() == null || resultMailVO.getMssageId().equals("")) {
			return "egovframework/com/cmm/egovError";
		}
		// 2026.07.13 KISA 보안취약점 조치
		if (!loginVO.getId().equals(resultMailVO.getDsptchPerson())) {
			java.util.List<String> auth = EgovUserDetailsHelper.getAuthorities();
			if (auth == null || !auth.contains("ROLE_ADMIN")) {
				throw new IllegalStateException("권한이 없습니다.");
			}
		}

		// 1. 발송메일을 삭제한다.
		sndngMailDtlsService.deleteSndngMailList(sndngMailVO);

		// 2. 발송메일 목록 페이지 이동
		return "redirect:/cop/ems/selectSndngMailList.do";
	}

	/**
	 * 2026.07.13 KISA 보안취약점 조치 - 로그인 사용자 확인
	 */
	private LoginVO egovAssertLoginUser() {
		LoginVO loginVO = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
		if (loginVO == null || loginVO.getUniqId() == null || "".equals(loginVO.getUniqId())) {
			throw new IllegalStateException("인증 정보가 없습니다.");
		}
		return loginVO;
	}

	/**
	 * 2026.07.13 KISA 보안취약점 조치 - 관리자 또는 소유자
	 */
	private void egovAssertAdminOrOwner(String ownerUniqId) {
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

}