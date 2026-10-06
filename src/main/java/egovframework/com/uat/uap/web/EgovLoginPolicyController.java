/**
 * 개요
 * - 로그인정책에 대한 controller 클래스를 정의한다.
 *
 * 상세내용
 * - 로그인정책에 대한 등록, 수정, 삭제, 조회, 반영확인 기능을 제공한다.
 * - 로그인정책의 조회기능은 목록조회, 상세조회로 구분된다.
 * @author lee.m.j
 * @version 1.0
 * @created 03-8-2009 오후 2:08:53
 * <pre>
 * == 개정이력(Modification Information) ==
 *
 *   수정일       수정자           수정내용
 *  -------     --------    ---------------------------
 *  2009.8.3    이문준     최초 생성
 *  2011.8.26	정진오			IncludedInfo annotation 추가
 *  2024.10.29	LeeBaekHaeng	검색조건 유지
 * </pre>
 */

package egovframework.com.uat.uap.web;

import org.egovframe.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import egovframework.com.cmm.EgovMessageSource;
import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.annotation.IncludedInfo;
import egovframework.com.cmm.annotation.RequireAdmin;
import egovframework.com.cmm.util.EgovAuthorizationHelper;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.com.uat.uap.service.EgovLoginPolicyService;
import egovframework.com.uat.uap.service.LoginPolicy;
import egovframework.com.uat.uap.service.LoginPolicyVO;
import egovframework.com.utl.fcc.service.EgovStringUtil;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;

@Controller
public class EgovLoginPolicyController {

    @Resource(name="egovMessageSource")
    EgovMessageSource egovMessageSource;

	@Resource(name="egovLoginPolicyService")
	EgovLoginPolicyService egovLoginPolicyService;

	/**
	 * 로그인정책 목록 조회화면으로 이동한다.
	 * @return String - 리턴 Url
	 */
	@RequestMapping("/uat/uap/selectLoginPolicyListView.do")
	@RequireAdmin
	public String selectLoginPolicyListView() throws Exception {
		return "egovframework/com/uat/uap/EgovLoginPolicyList";
	}

	/**
	 * 로그인정책 목록을 조회한다.
	 * @param loginPolicyVO - 로그인정책 VO
	 * @return String - 리턴 Url
	 */
	@IncludedInfo(name="로그인정책관리", order = 30 ,gid = 10)
	@RequestMapping("/uat/uap/selectLoginPolicyList.do")
	public String selectLoginPolicyList(@ModelAttribute("loginPolicyVO") LoginPolicyVO loginPolicyVO,
			                             ModelMap model) throws Exception {

    	/** paging */
    	PaginationInfo paginationInfo = new PaginationInfo();
	    paginationInfo.setCurrentPageNo(loginPolicyVO.getPageIndex());
	    paginationInfo.setRecordCountPerPage(loginPolicyVO.getPageUnit());
	    paginationInfo.setPageSize(loginPolicyVO.getPageSize());

	    loginPolicyVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
	    loginPolicyVO.setLastIndex(paginationInfo.getLastRecordIndex());
	    loginPolicyVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

	    loginPolicyVO.setLoginPolicyList(egovLoginPolicyService.selectLoginPolicyList(loginPolicyVO));
        model.addAttribute("loginPolicyList", loginPolicyVO.getLoginPolicyList());

        int totCnt = egovLoginPolicyService.selectLoginPolicyListTotCnt(loginPolicyVO);
	    paginationInfo.setTotalRecordCount(totCnt);
        model.addAttribute("paginationInfo", paginationInfo);
        model.addAttribute("message", egovMessageSource.getMessage("success.common.select"));

		return "egovframework/com/uat/uap/EgovLoginPolicyList";
	}

	/**
	 * 로그인정책 목록의 상세정보를 조회한다.
	 * @param loginPolicyVO - 로그인정책 VO
	 * @return String - 리턴 Url
	 */
	@PostMapping("/uat/uap/getLoginPolicy.do")
	@RequireAdmin
	public String selectLoginPolicy(@RequestParam("emplyrId") String emplyrId,
			                        @ModelAttribute("loginPolicyVO") LoginPolicyVO loginPolicyVO,
                                     ModelMap model) throws Exception {

		loginPolicyVO.setEmplyrId(emplyrId);

		model.addAttribute("loginPolicy", egovLoginPolicyService.selectLoginPolicy(loginPolicyVO));
		model.addAttribute("message", egovMessageSource.getMessage("success.common.select"));

		LoginPolicyVO vo = (LoginPolicyVO)model.get("loginPolicy");

		if(vo.getRegYn().equals("N")) {
			return "egovframework/com/uat/uap/EgovLoginPolicyRegist";
		} else {
			return "egovframework/com/uat/uap/EgovLoginPolicyUpdt";
		}
	}

	/**
	 * 로그인정책 정보 등록화면으로 이동한다.
	 * @param loginPolicy - 로그인정책 model
	 * @return String - 리턴 Url
	 */
	@PostMapping("/uat/uap/addLoginPolicyView.do")
	@RequireAdmin
	public String insertLoginPolicyView(@RequestParam("emplyrId") String emplyrId,
                                        @ModelAttribute("loginPolicyVO") LoginPolicyVO loginPolicyVO,
                                         ModelMap model) throws Exception {
		// 2026.07.13 KISA 보안취약점 조치
		LoginVO _loginVO = EgovAuthorizationHelper.assertLoginUser();


		loginPolicyVO.setEmplyrId(emplyrId);

		model.addAttribute("loginPolicy", egovLoginPolicyService.selectLoginPolicy(loginPolicyVO));
		model.addAttribute("message", egovMessageSource.getMessage("success.common.select"));

		return "egovframework/com/uat/uap/EgovLoginPolicyRegist";
	}

	/**
	 * 로그인정책 정보를 신규로 등록한다.
	 * @param loginPolicy - 로그인정책 model
	 * @return String - 리턴 Url
	 */
	@PostMapping("/uat/uap/addLoginPolicy.do")
	@RequireAdmin
	public String insertLoginPolicy(@Valid @ModelAttribute("loginPolicy") LoginPolicy loginPolicy,
			                         BindingResult bindingResult,
                                     ModelMap model,
                                     RedirectAttributes redirectAttributes) throws Exception {

    	if (bindingResult.hasErrors()) {
    		model.addAttribute("loginPolicyVO", loginPolicy);
			return "egovframework/com/uat/uap/EgovLoginPolicyRegist";
		} else {

			LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
			loginPolicy.setUserId(user == null ? "" : EgovStringUtil.isNullToString(user.getId()));

			egovLoginPolicyService.insertLoginPolicy(loginPolicy);
			model.addAttribute("message", egovMessageSource.getMessage("success.common.update"));

			// 2026.08.25 Spring 6 이관 조치 - RedirectAttributes 로 명시 전달
			redirectAttributes.addAttribute("emplyrId", loginPolicy.getEmplyrId());
			redirectAttributes.addAttribute("searchCondition", loginPolicy.getSearchCondition());
			redirectAttributes.addAttribute("searchKeyword", loginPolicy.getSearchKeyword());
			redirectAttributes.addAttribute("pageIndex", loginPolicy.getPageIndex());

			return "redirect:/uat/uap/getLoginPolicy.do";
		}
	}

	/**
	 * 기 등록된 로그인정책 정보를 수정한다.
	 * @param loginPolicy - 로그인정책 model
	 * @return String - 리턴 Url
	 */
	@PostMapping("/uat/uap/updtLoginPolicy.do")
	@RequireAdmin
	public String updateLoginPolicy(@Valid @ModelAttribute("loginPolicy") LoginPolicy loginPolicy,
			                         BindingResult bindingResult,
                                     ModelMap model, RedirectAttributes redirectAttributes) throws Exception {


    	if (bindingResult.hasErrors()) {
    		model.addAttribute("loginPolicyVO", loginPolicy);
			return "egovframework/com/uat/uap/EgovLoginPolicyUpdt";
		} else {
			LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
			loginPolicy.setUserId(user == null ? "" : EgovStringUtil.isNullToString(user.getId()));

			egovLoginPolicyService.updateLoginPolicy(loginPolicy);
			model.addAttribute("message", egovMessageSource.getMessage("success.common.update"));

			redirectAttributes.addAttribute("searchCondition", loginPolicy.getSearchCondition());
			redirectAttributes.addAttribute("searchKeyword", loginPolicy.getSearchKeyword());
			redirectAttributes.addAttribute("pageIndex", loginPolicy.getPageIndex());

			return "redirect:/uat/uap/selectLoginPolicyList.do";
		}
	}

	/**
	 * 기 등록된 로그인정책 정보를 삭제한다.
	 * @param loginPolicy - 로그인정책 model
	 * @return String - 리턴 Url
	 */
	@PostMapping("/uat/uap/removeLoginPolicy.do")
	@RequireAdmin
	public String deleteLoginPolicy(@ModelAttribute("loginPolicy") LoginPolicy loginPolicy,
                                     ModelMap model, RedirectAttributes redirectAttributes) throws Exception {


		egovLoginPolicyService.deleteLoginPolicy(loginPolicy);

		model.addAttribute("message", egovMessageSource.getMessage("success.common.delete"));

		redirectAttributes.addAttribute("searchCondition", loginPolicy.getSearchCondition());
		redirectAttributes.addAttribute("searchKeyword", loginPolicy.getSearchKeyword());
		redirectAttributes.addAttribute("pageIndex", loginPolicy.getPageIndex());

		return "redirect:/uat/uap/selectLoginPolicyList.do";
	}


}
