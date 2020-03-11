package egovframework.com.sym.ccm.cca.web;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springmodules.validation.commons.DefaultBeanValidator;

import egovframework.com.cmm.EgovMessageSource;
import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.annotation.IncludedInfo;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.com.sym.ccm.cca.service.CmmnCode;
import egovframework.com.sym.ccm.cca.service.CmmnCodeVO;
import egovframework.com.sym.ccm.cca.service.EgovCcmCmmnCodeManageService;
import egovframework.com.sym.ccm.ccc.service.CmmnClCodeVO;
import egovframework.com.sym.ccm.ccc.service.EgovCcmCmmnClCodeManageService;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

/**
*
* 공통코드에 관한 요청을 받아 서비스 클래스로 요청을 전달하고 서비스클래스에서 처리한 결과를 웹 화면으로 전달을 위한 Controller를 정의한다
* @author 공통서비스 개발팀 이중호
* @since 2009.04.01
* @version 1.0
* @see
*
* <pre>
* << 개정이력(Modification Information) >>
*
*   수정일      수정자           수정내용
*  -------    --------    ---------------------------
*   2009.04.01  이중호          최초 생성
*   2011.8.26	정진오			IncludedInfo annotation 추가
*   2017.08.16	이정은	표준프레임워크 v3.7 개선
*
* </pre>
*/

@Controller
public class EgovCcmCmmnCodeManageController {
	
	@Resource(name = "CmmnCodeManageService")
    private EgovCcmCmmnCodeManageService cmmnCodeManageService;

	@Resource(name = "CmmnClCodeManageService")
    private EgovCcmCmmnClCodeManageService cmmnClCodeManageService;
	
	/** EgovPropertyService */
	@Resource(name = "propertiesService")
	protected EgovPropertyService propertiesService;
	
	/** EgovMessageSource */
	@Resource(name = "egovMessageSource")
	EgovMessageSource egovMessageSource;
	
	@Autowired
	private DefaultBeanValidator beanValidator;
	
	
	
	/**
	 * 공통분류코드 목록을 조회한다.
	 * 
	 * @param searchVO
	 * @param model
	 * @return "egovframework/com/sym/ccm/cca/EgovCcmCmmnCodeList"
	 * @throws Exception
	 */
	@IncludedInfo(name = "공통코드", listUrl = "/sym/ccm/cca/SelectCcmCmmnCodeList.do", order = 980, gid = 60)
	@RequestMapping(value = "/sym/ccm/cca/SelectCcmCmmnCodeList.do")
	public String selectCmmnCodeList(@ModelAttribute("searchVO") CmmnCodeVO searchVO, ModelMap model)
			throws Exception {
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

		List<?> CmmnCodeList = cmmnCodeManageService.selectCmmnCodeList(searchVO);
		model.addAttribute("resultList", CmmnCodeList);

		int totCnt = cmmnCodeManageService.selectCmmnCodeListTotCnt(searchVO);
		paginationInfo.setTotalRecordCount(totCnt);
		model.addAttribute("paginationInfo", paginationInfo);

		return "egovframework/com/sym/ccm/cca/EgovCcmCmmnCodeList";
	}
	
	/**
	 * 공통코드 상세항목을 조회한다.
	 * 
	 * @param loginVO
	 * @param cmmnCodeVO
	 * @param model
	 * @return "egovframework/com/sym/ccm/cca/EgovCcmCmmnCodeDetail"
	 * @throws Exception
	 */
	@RequestMapping(value = "/sym/ccm/cca/SelectCcmCmmnCodeDetail.do")
	public String selectCmmnCodeDetail(@ModelAttribute("loginVO") LoginVO loginVO, CmmnCodeVO cmmnCodeVO, ModelMap model) throws Exception {
		
		CmmnCodeVO vo = cmmnCodeManageService.selectCmmnCodeDetail(cmmnCodeVO);
		
		model.addAttribute("result", vo);

		return "egovframework/com/sym/ccm/cca/EgovCcmCmmnCodeDetail";
	}
	
	/**
	 * 공통코드 등록을 위한 등록페이지로 이동한다.
	 * 
	 * @param cmmnCodeVO
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/sym/ccm/cca/RegistCcmCmmnCodeView.do")
	public String insertCmmnCodeView(@ModelAttribute("cmmnCodeVO")CmmnCodeVO cmmnCodeVO, ModelMap model) throws Exception {
		
		CmmnClCodeVO searchVO = new CmmnClCodeVO();
		searchVO.setFirstIndex(0);
        List<?> CmmnCodeList = cmmnClCodeManageService.selectCmmnClCodeList(searchVO);
        
        model.addAttribute("clCodeList", CmmnCodeList);

		return "egovframework/com/sym/ccm/cca/EgovCcmCmmnCodeRegist";
	}
	
	/**
     * 공통코드를 등록한다.
     * 
     * @param CmmnCodeVO
     * @param CmmnCodeVO
     * @param status
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping("/sym/ccm/cca/RegistCcmCmmnCode.do")
    public String insertCmmnCode(@ModelAttribute("searchVO") CmmnCodeVO cmmnCode, @ModelAttribute("cmmnCodeVO") CmmnCodeVO cmmnCodeVO,
	    BindingResult bindingResult, ModelMap model) throws Exception {

		LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
		
		CmmnClCodeVO searchVO = new CmmnClCodeVO();
	
		beanValidator.validate(cmmnCodeVO, bindingResult);
	
		if (bindingResult.hasErrors()) {
			
	        List<?> CmmnCodeList = cmmnClCodeManageService.selectCmmnClCodeList(searchVO);
	        model.addAttribute("clCodeList", CmmnCodeList);
	        
		    return "egovframework/com/sym/ccm/cca/EgovCcmCmmnCodeRegist";
		}
		
		if(cmmnCode.getCodeId() != null){
			CmmnCode vo = cmmnCodeManageService.selectCmmnCodeDetail(cmmnCode);
			if(vo != null){
				model.addAttribute("message", egovMessageSource.getMessage("comSymCcmCca.validate.codeCheck"));
				
				searchVO.setFirstIndex(0);
		        List<?> CmmnCodeList = cmmnClCodeManageService.selectCmmnClCodeList(searchVO);
		        model.addAttribute("clCodeList", CmmnCodeList);
		        
				return "egovframework/com/sym/ccm/cca/EgovCcmCmmnCodeRegist";
			}
		}
	
		cmmnCodeVO.setFrstRegisterId((user == null || user.getUniqId() == null) ? "" : user.getUniqId());
			cmmnCodeManageService.insertCmmnCode(cmmnCodeVO);
	
		return "forward:/sym/ccm/cca/SelectCcmCmmnCodeList.do";
    }
        
    /**
     * 공통코드를 삭제한다.
     * 
     * @param cmmnCodeVO
     * @param status
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping("/sym/ccm/cca/RemoveCcmCmmnCode.do")
    public String deleteCmmnCode(@ModelAttribute("cmmnCodeVO") CmmnCodeVO cmmnCodeVO,
	    BindingResult bindingResult, ModelMap model) throws Exception {

    	LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();

    		cmmnCodeVO.setLastUpdusrId((user == null || user.getUniqId() == null) ? "" : user.getUniqId());
    		cmmnCodeManageService.deleteCmmnCode(cmmnCodeVO);

    		return "forward:/sym/ccm/cca/SelectCcmCmmnCodeList.do";
        }
    
    /**
     * 공통코드 수정을 위한 수정페이지로 이동한다.
     * 
     * @param cmmnCodeVO
     * @param model
     * @return "egovframework/com/sym/ccm/cca/EgovCcmCmmnCodeUpdt"
     * @throws Exception
     */
    @RequestMapping("/sym/ccm/cca/UpdateCcmCmmnCodeView.do")
    public String updateCmmnCodeView(@ModelAttribute("cmmnCodeVO") CmmnCodeVO cmmnCodeVO, ModelMap model)
	    throws Exception {
		
    	CmmnCode result = cmmnCodeManageService.selectCmmnCodeDetail(cmmnCodeVO);
		
		model.addAttribute("cmmnCodeVO", result);
	
		return "egovframework/com/sym/ccm/cca/EgovCcmCmmnCodeUpdt";  
    }
    
    /**
     * 공통코드를 수정한다.
     * 
     * @param cmmnCodeVO
     * @param status
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping("/sym/ccm/cca/UpdateCcmCmmnCode.do")
    public String updateCmmnCode(@ModelAttribute("searchVO") CmmnCodeVO cmmnCode, @ModelAttribute("cmmnCodeVO") CmmnCodeVO cmmnCodeVO,
	    BindingResult bindingResult, ModelMap model) throws Exception {

				LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
	
		beanValidator.validate(cmmnCodeVO, bindingResult);
		if (bindingResult.hasErrors()) {
	
			CmmnCode result = cmmnCodeManageService.selectCmmnCodeDetail(cmmnCode);
		    model.addAttribute("cmmnCodeVO", result);
	
		    return "egovframework/com/sym/ccm/cca/EgovCcmCmmnCodeUpdt";
		}
	
		cmmnCodeVO.setLastUpdusrId((user == null || user.getUniqId() == null) ? "" : user.getUniqId());
		cmmnCodeManageService.updateCmmnCode(cmmnCodeVO);

		return "forward:/sym/ccm/cca/SelectCcmCmmnCodeList.do";
    }
	
	
}