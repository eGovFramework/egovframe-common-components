package egovframework.com.cop.ncm.web;

import java.util.Map;

import javax.annotation.Resource;

import org.egovframe.rte.fdl.property.EgovPropertyService;
import org.egovframe.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;
import org.springmodules.validation.commons.DefaultBeanValidator;

import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.annotation.IncludedInfo;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.com.cop.ncm.service.EgovNcrdManageService;
import egovframework.com.cop.ncm.service.NameCard;
import egovframework.com.cop.ncm.service.NameCardUser;
import egovframework.com.cop.ncm.service.NameCardVO;
import egovframework.com.utl.fcc.service.EgovStringUtil;

/**
 * 명함정보를 관리하기 위한 컨트롤러 클래스
 * @author 공통서비스개발팀 이삼섭
 * @since 2009.06.01
 * @version 1.0
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2009.3.30  이삼섭          최초 생성
 *   2011.8.26	정진오			IncludedInfo annotation 추가
 *   2022.11.11 김혜준          시큐어코딩 처리
 *
 * </pre>
 */

@Controller
public class EgovNcrdManageController {

    @Resource(name = "EgovNcrdManageService")
    private EgovNcrdManageService ncrdService;

    @Resource(name = "propertiesService")
    protected EgovPropertyService propertyService;

    @Autowired
    private DefaultBeanValidator beanValidator;

    //Logger log = Logger.getLogger(this.getClass());

    /**
     * 명함 정보에 대한 목록을 조회한다.
     * 
     * @param ncrdVO
     * @param sessionVO
     * @param status
     * @param model
     * @return
     * @throws Exception
     */
    @IncludedInfo(name="명함관리",order = 370 ,gid = 40)
    @RequestMapping("/cop/ncm/selectNcrdInfs.do")
    public String selectNcrdItems(@ModelAttribute("searchVO") NameCardVO ncrdVO, SessionStatus status, ModelMap model) throws Exception {
		LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		 // KISA 보안취약점 조치 (2018-12-10, 신용호)
	    if(!isAuthenticated) {
	        return "redirect:/uat/uia/egovLoginUsr.do";
	    }
	
		ncrdVO.setPageUnit(propertyService.getInt("pageUnit"));
		ncrdVO.setPageSize(propertyService.getInt("pageSize"));
	
		PaginationInfo paginationInfo = new PaginationInfo();
		
		paginationInfo.setCurrentPageNo(ncrdVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(ncrdVO.getPageUnit());
		paginationInfo.setPageSize(ncrdVO.getPageSize());
	
		ncrdVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		ncrdVO.setLastIndex(paginationInfo.getLastRecordIndex());
		ncrdVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
	
		ncrdVO.setEmplyrId(user == null ? "" : EgovStringUtil.isNullToString(user.getUniqId()));
		
		Map<String, Object> map = ncrdService.selectNcrdItems(ncrdVO);
		int totCnt = Integer.parseInt((String)map.get("resultCnt"));
		
		paginationInfo.setTotalRecordCount(totCnt);
	
		model.addAttribute("uniqId", user == null ? "" : EgovStringUtil.isNullToString(user.getUniqId()));
		model.addAttribute("resultList", map.get("resultList"));
		model.addAttribute("resultCnt", map.get("resultCnt"));
		model.addAttribute("paginationInfo", paginationInfo);
	
		return "egovframework/com/cop/ncm/EgovNcrdList";
    }

    /**
     * 명함 정보를 삭제한다.
     * 
     * @param nameCard
     * @param sessionVO
     * @param model
     * @return
     * @throws Exception
     */

    @RequestMapping("/cop/ncm/deleteNcrdInf.do")
    public String deleteNcrdItem(@ModelAttribute("searchVO") NameCardVO ncrdVO, SessionStatus status,
	    ModelMap model) throws Exception {

	LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
	 // KISA 보안취약점 조치 (2018-12-10, 신용호)
    if(!isAuthenticated) {
        return "redirect:/uat/uia/egovLoginUsr.do";
    }

	ncrdVO.setEmplyrId(user == null ? "" : EgovStringUtil.isNullToString(user.getUniqId()));

	// 2022.11.11 시큐어코딩 처리
	ncrdService.deleteNcrdItem(ncrdVO);

	return "forward:/cop/ncm/selectNcrdInfs.do";
    }

    /**
     * 명함 정보 등록을 위한 등록페이지로 이동한다.
     * 
     * @param nameCard
     * @param sessionVO
     * @param status
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping("/cop/ncm/addNcrdInf.do")
    public String addNcrdItem(@ModelAttribute("searchVO") NameCardVO ncrdVO, SessionStatus status, ModelMap model) throws Exception {
    	return "egovframework/com/cop/ncm/EgovNcrdRegist";
    }

    /**
     * 명함 정보를 등록한다.
     * 
     * @param nameCard
     * @param sessionVO
     * @param status
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping("/cop/ncm/insertNcrdInf.do")
    public String insertNcrdItem(@ModelAttribute("searchVO") NameCardVO ncrdVO, @ModelAttribute("nameCard") NameCard nameCard,
	    BindingResult bindingResult, SessionStatus status, ModelMap model) throws Exception {

		LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		 // KISA 보안취약점 조치 (2018-12-10, 신용호)
	    if(!isAuthenticated) {
	        return "redirect:/uat/uia/egovLoginUsr.do";
	    }
		
		beanValidator.validate(nameCard, bindingResult);
		if (bindingResult.hasErrors()) {
		    return "egovframework/com/cop/ncm/EgovNcrdRegist";
		}
	
		nameCard.setAdres(nameCard.getZipCode() + " " + nameCard.getAdres());
		nameCard.setFrstRegisterId(user == null ? "" : EgovStringUtil.isNullToString(user.getUniqId()));

		// 2022.11.11 시큐어코딩 처리
		ncrdService.insertNcrdItem(nameCard);

		return "forward:/cop/ncm/selectMyNcrdUseInf.do";
    }

    /**
     * 명함 정보에 대한 상세정보를 조회한다
     * 
     * @param nameCard
     * @param sessionVO
     * @param status
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping("/cop/ncm/selectNcrdInf.do")
    public String selectNcrdItem(@ModelAttribute("searchVO") NameCardVO ncrdVO, SessionStatus status, ModelMap model) throws Exception {

		LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		// KISA 보안취약점 조치 (2018-12-10, 신용호)
	    if(!isAuthenticated) {
	        return "redirect:/uat/uia/egovLoginUsr.do";
	    }
	
		ncrdVO.setFrstRegisterId(user == null ? "" : EgovStringUtil.isNullToString(user.getUniqId()));
		
		NameCardVO vo = ncrdService.selectNcrdItem(ncrdVO);
	
		model.addAttribute("ncrdVO", vo);
		
		return "egovframework/com/cop/ncm/EgovNcrdUpdt";
    }

    /**
     * 명함 정보를 수정한다.
     * 
     * @param nameCard
     * @param sessionVO
     * @param status
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping("/cop/ncm/updateNcrdInf.do")
    public String updateNcrdItem(@ModelAttribute("searchVO") NameCardVO ncrdVO, @RequestParam("ncrdNm") String ncrdNm,
	    @ModelAttribute("nameCard") NameCard nameCard, BindingResult bindingResult, SessionStatus status, ModelMap model) throws Exception {
		LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		// KISA 보안취약점 조치 (2018-12-10, 신용호)
	    if(!isAuthenticated) {
	        return "redirect:/uat/uia/egovLoginUsr.do";
	    }
	
		beanValidator.validate(nameCard, bindingResult);
		if (bindingResult.hasErrors()) {
		    ncrdVO.setFrstRegisterId(user == null ? "" : EgovStringUtil.isNullToString(user.getUniqId()));
		    
		    NameCardVO vo  = ncrdService.selectNcrdItem(ncrdVO);
	
		    model.addAttribute("ncrdVO", vo);
		    
		    return "egovframework/com/cop/ncm/EgovNcrdUpdt";
		}
	
		if (!"".equals(nameCard.getZipCode())) {
		    nameCard.setAdres(nameCard.getZipCode() + " " + nameCard.getAdres());
		}
		
		nameCard.setLastUpdusrId(user == null ? "" : EgovStringUtil.isNullToString(user.getUniqId()));

		// 2022.11.11 시큐어코딩 처리
		ncrdService.updateNcrdItem(nameCard);

		return "forward:/cop/ncm/selectMyNcrdUseInf.do";
    }

    /**
     * 명함사용자 정보를 등록한다.
     * 
     * @param ncrdUser
     * @param sessionVO
     * @param status
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping("/cop/ncm/insertNcrdUseInf.do")
    public String insertNcrdUseInf(@ModelAttribute("ncrdUser") NameCardUser ncrdUser, @ModelAttribute("ncrdVO") NameCardVO ncrdVO,
	    SessionStatus status, ModelMap model) throws Exception {
		LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		 // KISA 보안취약점 조치 (2018-12-10, 신용호)
	    if(!isAuthenticated) {
	        return "redirect:/uat/uia/egovLoginUsr.do";
	    }
	
		ncrdUser.setEmplyrId(user == null ? "" : EgovStringUtil.isNullToString(user.getUniqId()));
		ncrdUser.setUseAt("Y");

		// 2022.11.11 시큐어코딩 처리
		ncrdService.insertNcrdUseInf(ncrdUser);

		return "forward:/cop/ncm/selectMyNcrdUseInf.do";
    }

    /**
     * 명함 정보에 대한 목록을 조회한다.
     * 
     * @param ncrdUser
     * @param sessionVO
     * @param status
     * @param model
     * @return
     * @throws Exception
     */
    @IncludedInfo(name="내명함목록",order = 371 ,gid = 40)
    @RequestMapping("/cop/ncm/selectMyNcrdUseInf.do")
    public String selectNcrdUseInf(@ModelAttribute("searchVO") NameCardUser ncrdUser, SessionStatus status, ModelMap model) throws Exception {
		LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		 // KISA 보안취약점 조치 (2018-12-10, 신용호)
	    if(!isAuthenticated) {
	        return "redirect:/uat/uia/egovLoginUsr.do";
	    }
	
		ncrdUser.setPageUnit(propertyService.getInt("pageUnit"));
		ncrdUser.setPageSize(propertyService.getInt("pageSize"));
	
		PaginationInfo paginationInfo = new PaginationInfo();
		
		paginationInfo.setCurrentPageNo(ncrdUser.getPageIndex());
		paginationInfo.setRecordCountPerPage(ncrdUser.getPageUnit());
		paginationInfo.setPageSize(ncrdUser.getPageSize());
	
		ncrdUser.setFirstIndex(paginationInfo.getFirstRecordIndex());
		ncrdUser.setLastIndex(paginationInfo.getLastRecordIndex());
		ncrdUser.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
	
		ncrdUser.setEmplyrId(user == null ? "" : EgovStringUtil.isNullToString(user.getUniqId()));
		
		Map<String, Object> map = ncrdService.selectNcrdUseInfs(ncrdUser);
		int totCnt = Integer.parseInt((String)map.get("resultCnt"));
		
		paginationInfo.setTotalRecordCount(totCnt);
		
		model.addAttribute("resultList", map.get("resultList"));
		model.addAttribute("resultCnt", map.get("resultCnt"));
		model.addAttribute("uniqId", user == null ? "" : EgovStringUtil.isNullToString(user.getUniqId()));
		model.addAttribute("paginationInfo", paginationInfo);
	
		return "egovframework/com/cop/ncm/EgovMyNcrdList";
    }

    /**
     * 명함사용자 정보를 수정한다.
     * 
     * @param ncrdUser
     * @param sessionVO
     * @param status
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping("/cop/ncm/updateNcrdUseInf.do")
    public String updateNcrdUseInf(@ModelAttribute("ncrdUser") NameCardUser ncrdUser, @ModelAttribute("ncrdVO") NameCardVO ncrdVO,
	    SessionStatus status, ModelMap model) throws Exception {
		@SuppressWarnings("unused")
		LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
	
		ncrdUser.setUseAt("N");
		
		if (isAuthenticated) {
		    ncrdService.updateNcrdUseInf(ncrdUser);
		}
	
		return "forward:/cop/ncm/selectMyNcrdUseInf.do";
    }

    /**
     * 명함 정보에 대한 상세정보를 조회한다.
     * 
     * @param ncrdVO
     * @param sessionVO
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping("/cop/ncm/selectNcrdInfPopup.do")
    public String selectNcrdItemforPop(@ModelAttribute("searchVO") NameCardVO ncrdVO, ModelMap model) throws Exception {
		LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		// KISA 보안취약점 조치 (2018-12-10, 신용호)
	    if(!isAuthenticated) {
	        return "redirect:/uat/uia/egovLoginUsr.do";
	    }
	
		ncrdVO.setFrstRegisterId(user == null ? "" : EgovStringUtil.isNullToString(user.getUniqId()));
		
		NameCardVO vo = ncrdService.selectNcrdItem(ncrdVO);
	
		model.addAttribute("ncrdVO", vo);
		
		return "egovframework/com/cop/ncm/EgovNcrdInqirePopup";
    }
}
