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
import egovframework.com.cmm.exception.EgovAccessDeniedException;
import egovframework.com.cmm.util.EgovAuthorizationHelper;
import egovframework.com.utl.fcc.service.EgovStringUtil;
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
	 */
	@IncludedInfo(name = "발송메일내역", order = 361, gid = 40)
	@RequestMapping(value = "/cop/ems/selectSndngMailList.do")
	public String selectSndngMailList(@ModelAttribute("searchVO") ComDefaultVO searchVO, ModelMap model) {

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
	 */
	@PostMapping("/cop/ems/deleteSndngMailList.do")
	public String deleteSndngMailList(@ModelAttribute("sndngMailVO") SndngMailVO sndngMailVO, ModelMap model) {
		// 2026.07.13 KISA 보안취약점 조치
		LoginVO _loginVO = EgovAuthorizationHelper.assertLoginUser();


		if (sndngMailVO == null || sndngMailVO.getMssageId() == null || sndngMailVO.getMssageId().equals("")) {
			return "egovframework/com/cmm/error/egovError";
		}

		LoginVO loginVO = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
		if (loginVO == null || loginVO.getUniqId() == null) {
			throw new EgovAccessDeniedException("인증 정보가 없습니다.");
		}
		// mssageId는 콤마로 이어진 목록이다. 모두 확인한 뒤 삭제한다.
		// 첨부 삭제 목록은 요청값이 아니라 확인을 마친 메일 원본의 첨부 ID 로만 만든다(남의 첨부 사용중지 차단)
		StringBuilder atchFileIdList = new StringBuilder();
		for (String mssageId : EgovStringUtil.split(sndngMailVO.getMssageId(), ",")) {
			SndngMailVO keyVO = new SndngMailVO();
			keyVO.setMssageId(mssageId);
			SndngMailVO resultMailVO = sndngMailDetailService.selectSndngMail(keyVO);
			EgovAuthorizationHelper.assertOwnerById(resultMailVO == null ? null : resultMailVO.getDsptchPerson());
			if (resultMailVO.getAtchFileId() != null && !resultMailVO.getAtchFileId().isEmpty()) {
				atchFileIdList.append(atchFileIdList.length() == 0 ? "" : ",").append(resultMailVO.getAtchFileId());
			}
		}
		sndngMailVO.setAtchFileIdList(atchFileIdList.toString());

		// 1. 발송메일을 삭제한다.
		sndngMailDtlsService.deleteSndngMailList(sndngMailVO);

		// 2. 발송메일 목록 페이지 이동
		return "redirect:/cop/ems/selectSndngMailList.do";
	}

}
