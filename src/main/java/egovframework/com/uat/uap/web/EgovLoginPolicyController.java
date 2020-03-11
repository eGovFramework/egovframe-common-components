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
 * </pre>
 */

package egovframework.com.uat.uap.web;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springmodules.validation.commons.DefaultBeanValidator;

import egovframework.com.cmm.EgovMessageSource;
import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.annotation.IncludedInfo;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.com.uat.uap.service.EgovLoginPolicyService;
import egovframework.com.uat.uap.service.LoginPolicy;
import egovframework.com.uat.uap.service.LoginPolicyVO;
import egovframework.com.utl.fcc.service.EgovStringUtil;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;


@Controller
public class EgovLoginPolicyController {
	
	 
	 
	
    @Resource(name="egovMessageSource")
    EgovMessageSource egovMessageSource;
    
	@Resource(name="egovLoginPolicyService")
	EgovLoginPolicyService egovLoginPolicyService;
	
    @Autowired
	private DefaultBeanValidator beanValidator;
    
	/**
	 * 로그인정책 목록 조회화면으로 이동한다.
	 * @return String - 리턴 Url
	 */
	@RequestMapping("/uat/uap/selectLoginPolicyListView.do")
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
	@RequestMapping("/uat/uap/getLoginPolicy.do")
	public String selectLoginPolicy(@RequestParam("emplyrId") String emplyrId, 
			                        @ModelAttribute("loginPolicyVO") LoginPolicyVO loginPolicyVO, 
                                     ModelMap model) throws Exception {
		
		loginPolicyVO.setEmplyrId(emplyrId);

		model.addAttribute("loginPolicy", egovLoginPolicyService.selectLoginPolicy(loginPolicyVO));
		model.addAttribute("message", egovMessageSource.getMessage("success.common.select"));
		
		LoginPolicyVO vo = (LoginPolicyVO)model.get("loginPolicy");
		
		if(vo.getRegYn().equals("N")) 
			return "egovframework/com/uat/uap/EgovLoginPolicyRegist";
		else 
			return "egovframework/com/uat/uap/EgovLoginPolicyUpdt";
	}

	/**
	 * 로그인정책 정보 등록화면으로 이동한다.
	 * @param loginPolicy - 로그인정책 model
	 * @return String - 리턴 Url
	 */
	@RequestMapping("/uat/uap/addLoginPolicyView.do")
	public String insertLoginPolicyView(@RequestParam("emplyrId") String emplyrId, 
                                        @ModelAttribute("loginPolicyVO") LoginPolicyVO loginPolicyVO, 
                                         ModelMap model) throws Exception {
		
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
	@RequestMapping("/uat/uap/addLoginPolicy.do")
	public String insertLoginPolicy(@ModelAttribute("loginPolicy") LoginPolicy loginPolicy, 
			                         BindingResult bindingResult,
                                     ModelMap model) throws Exception {
		
		beanValidator.validate(loginPolicy, bindingResult); //validation 수행		
		
    	if (bindingResult.hasErrors()) { 
    		model.addAttribute("loginPolicyVO", loginPolicy);
			return "egovframework/com/uat/uap/EgovLoginPolicyRegist";
		} else {
			
			LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
			loginPolicy.setUserId(user == null ? "" : EgovStringUtil.isNullToString(user.getId()));
			
			egovLoginPolicyService.insertLoginPolicy(loginPolicy);
	    	model.addAttribute("message", egovMessageSource.getMessage("success.common.update"));
	    	
			return "forward:/uat/uap/getLoginPolicy.do";		
		}
	}                             		

	/**
	 * 기 등록된 로그인정책 정보를 수정한다.
	 * @param loginPolicy - 로그인정책 model
	 * @return String - 리턴 Url
	 */
	@RequestMapping("/uat/uap/updtLoginPolicy.do")
	public String updateLoginPolicy(@ModelAttribute("loginPolicy") LoginPolicy loginPolicy, 
			                         BindingResult bindingResult,
                                     ModelMap model) throws Exception {
		
		beanValidator.validate(loginPolicy, bindingResult); //validation 수행	
		
    	if (bindingResult.hasErrors()) { 
    		model.addAttribute("loginPolicyVO", loginPolicy);
			return "egovframework/com/uat/uap/EgovLoginPolicyUpdt";
		} else {
			LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
			loginPolicy.setUserId(user == null ? "" : EgovStringUtil.isNullToString(user.getId()));
			
			egovLoginPolicyService.updateLoginPolicy(loginPolicy);
	    	model.addAttribute("message", egovMessageSource.getMessage("success.common.update"));
	    	
	    	return "forward:/uat/uap/selectLoginPolicyList.do";
		}
	}

	/**
	 * 기 등록된 로그인정책 정보를 삭제한다.
	 * @param loginPolicy - 로그인정책 model
	 * @return String - 리턴 Url
	 */
	@RequestMapping("/uat/uap/removeLoginPolicy.do")
	public String deleteLoginPolicy(@ModelAttribute("loginPolicy") LoginPolicy loginPolicy, 
                                     ModelMap model) throws Exception {

		egovLoginPolicyService.deleteLoginPolicy(loginPolicy);

		model.addAttribute("message", egovMessageSource.getMessage("success.common.delete"));
		return "forward:/uat/uap/selectLoginPolicyList.do";
	}


}
