package egovframework.com.uss.olp.mgt.web;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springmodules.validation.commons.DefaultBeanValidator;

import egovframework.com.cmm.ComDefaultVO;
import egovframework.com.cmm.EgovMessageSource;
import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.annotation.IncludedInfo;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.com.uss.olp.mgt.service.EgovMeetingManageService;
import egovframework.com.uss.olp.mgt.service.MeetingManageVO;
import egovframework.com.utl.fcc.service.EgovStringUtil;
import org.egovframe.rte.fdl.property.EgovPropertyService;
import org.egovframe.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
/**
 * 회의관리를 처리하기 위한 Controller 구현 Class
 * @author 공통서비스 장동한
 * @since 2009.03.20
 * @version 1.0
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2009.03.20  장동한          최초 생성
 *   2011.8.26	정진오			IncludedInfo annotation 추가
 *
 * </pre>
 */

@Controller
public class EgovMeetingManageController {

	/** Log Member Variable */
	private static final Logger LOGGER = LoggerFactory.getLogger(EgovMeetingManageController.class);

	/** beanValidator Member Variable */
	@Autowired
	private DefaultBeanValidator beanValidator;

	/** egovMeetingManageService Member Variable */
	@Resource(name = "egovMeetingManageService")
	private EgovMeetingManageService egovMeetingManageService;

    /** propertiesService Member Variable */
    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;

    @RequestMapping(value="/uss/olp/mgt/EgovMeetingManageMain.do")
    public String egovMeetingManageMain(ModelMap model) throws Exception {
    	return "egovframework/com/uss/olp/mgt/EgovMeetingManageMain";
    }

    @RequestMapping(value="/uss/olp/mgt/EgovMeetingManageLeft.do")
    public String egovMeetingManageLeft(ModelMap model) throws Exception {
    	return "egovframework/com/uss/olp/mgt/EgovMeetingManageLeft";
    }

	/** EgovMessageSource Member Variable */
    @Resource(name="egovMessageSource")
    EgovMessageSource egovMessageSource;


    /**
     * 개별 배포시 메인메뉴를 조회한다.
     * @param model
     * @return	"/uss/sam/cpy/"
     * @throws Exception
     */
    @RequestMapping(value="/uss/olp/mgt/EgovMain.do")
    public String egovMain(ModelMap model) throws Exception {
    	return "egovframework/com/uss/olp/mgt/EgovMain";
    }

    /**
     * 메뉴를 조회한다.
     * @param model
     * @return	"/uss/sam/cpy/EgovLeft"
     * @throws Exception
     */
    @RequestMapping(value="/uss/olp/mgt/EgovLeft.do")
    public String egovLeft(ModelMap model) throws Exception {
    	return "egovframework/com/uss/olp/mgt/EgovLeft";
    }

    /**
     * 부서목록을 조회한다.
     * @param searchVO
     * @param commandMap
     * @param model
     * @return "egovframework/com/uss/olp/mgt/EgovMeetingManageLisEmpLyrPopup"
     * @throws Exception
     */
    @RequestMapping(value="/uss/olp/mgt/EgovMeetingManageLisAuthorGroupPopup.do")
	public String egovMeetingManageLisAuthorGroupPopupPost (
			@ModelAttribute("searchVO") ComDefaultVO searchVO,
			@RequestParam Map<?, ?> commandMap,
    		ModelMap model)
    throws Exception {

    	 List<?> resultList = egovMeetingManageService.egovMeetingManageLisAuthorGroupPopup(searchVO);
         model.addAttribute("resultList", resultList);

    	return "egovframework/com/uss/olp/mgt/EgovMeetingManageLisAuthorGroupPopup";
    }

    /**
     * 회원목록을 조회한다.
     * @param searchVO
     * @param commandMap
     * @param model
     * @return  "/uss/olp/mgt/EgovMeetingManageLisEmpLyrPopup"
     * @throws Exception
     */
    @RequestMapping(value="/uss/olp/mgt/EgovMeetingManageLisEmpLyrPopup.do")
	public String egovMeetingManageLisEmpLyrPopupPost (
			@ModelAttribute("searchVO") ComDefaultVO searchVO,
			@RequestParam Map<?, ?> commandMap,
    		ModelMap model)
    throws Exception {

    	 List<?> resultList = egovMeetingManageService.egovMeetingManageLisEmpLyrPopup(searchVO);
         model.addAttribute("resultList", resultList);

    	return "egovframework/com/uss/olp/mgt/EgovMeetingManageLisEmpLyrPopup";
    }

	/**
	 * 회의정보 목록을 조회한다.
	 * @param searchVO
	 * @param commandMap
	 * @param meetingManageVO
	 * @param model
	 * @return "egovframework/com/uss/olp/mgt/EgovMeetingManageList"
	 * @throws Exception
	 */
    @IncludedInfo(name="회의관리", order = 650 ,gid = 50)
	@RequestMapping(value="/uss/olp/mgt/EgovMeetingManageList.do")
	public String egovMeetingManageList(
			@ModelAttribute("searchVO") ComDefaultVO searchVO,
			@RequestParam Map<?, ?> commandMap,
			MeetingManageVO meetingManageVO,
    		ModelMap model)
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

        List<?> sampleList = egovMeetingManageService.selectMeetingManageList(searchVO);
        model.addAttribute("resultList", sampleList);

        model.addAttribute("searchKeyword", commandMap.get("searchKeyword") == null ? "" : (String)commandMap.get("searchKeyword"));
        model.addAttribute("searchCondition", commandMap.get("searchCondition") == null ? "" : (String)commandMap.get("searchCondition"));

        int totCnt = egovMeetingManageService.selectMeetingManageListCnt(searchVO);
		paginationInfo.setTotalRecordCount(totCnt);
        model.addAttribute("paginationInfo", paginationInfo);

		return "egovframework/com/uss/olp/mgt/EgovMeetingManageList";

	}

	/**
	 * 회의정보 목록을 상세조회 조회한다.
	 * @param searchVO
	 * @param meetingManageVO
	 * @param commandMap
	 * @param model
	 * @return "egovframework/com/uss/olp/mgt/EgovMeetingManageDetail"
	 * @throws Exception
	 */
	@RequestMapping(value="/uss/olp/mgt/EgovMeetingManageDetail.do")
	public String egovMeetingManageDetail(
			@ModelAttribute("searchVO") ComDefaultVO searchVO,
			MeetingManageVO meetingManageVO,
			@RequestParam Map<?, ?> commandMap,
    		ModelMap model)
    throws Exception {

		String sLocationUrl = "egovframework/com/uss/olp/mgt/EgovMeetingManageDetail";

		String sCmd = commandMap.get("cmd") == null ? "" : (String)commandMap.get("cmd");
		if(sCmd.equals("del")){
			egovMeetingManageService.deleteMeetingManage(meetingManageVO);
			sLocationUrl = "redirect:/uss/olp/mgt/EgovMeetingManageList.do";
		}else{
			List<?> sampleList = egovMeetingManageService.selectMeetingManageDetail(meetingManageVO);
        	model.addAttribute("resultList", sampleList);
		}

		return sLocationUrl;
	}

	/**
	  * 회의정보를 수정한다.
	 * @param searchVO
	 * @param meetingManageVO
	 * @param bindingResult
	 * @param commandMap
	 * @param model
	 * @return "egovframework/com/uss/olp/mgt/EgovMeetingManageModify"
	 * @throws Exception
	 */
	@RequestMapping(value="/uss/olp/mgt/EgovMeetingManageModify.do")
	public String meetingManageModify(
			@ModelAttribute("searchVO") ComDefaultVO searchVO,
			@ModelAttribute("meetingManageVO") MeetingManageVO meetingManageVO,
			BindingResult bindingResult,
			@RequestParam Map<?, ?> commandMap,
    		ModelMap model)
    throws Exception {

    	// 0. Spring Security 사용자권한 처리
    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
    		model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
        	return "egovframework/com/uat/uia/EgovLoginUsr";
    	}
		//로그인 객체 선언
		LoginVO loginVO = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();

		String sLocationUrl = "egovframework/com/uss/olp/mgt/EgovMeetingManageModify";

		String sCmd = commandMap.get("cmd") == null ? "" : (String)commandMap.get("cmd");

        if(sCmd.equals("save")){
    		//서버  validate 체크
            beanValidator.validate(meetingManageVO, bindingResult);
    		if(bindingResult.hasErrors()){
                List<?> resultList = egovMeetingManageService.selectMeetingManageDetail(meetingManageVO);
                model.addAttribute("resultList", resultList);
    			return sLocationUrl;
    		}
    		//아이디 설정
        	meetingManageVO.setFrstRegisterId(loginVO == null ? "" : EgovStringUtil.isNullToString(loginVO.getUniqId()));
        	meetingManageVO.setLastUpdusrId(loginVO == null ? "" : EgovStringUtil.isNullToString(loginVO.getUniqId()));

        	egovMeetingManageService.updateMeetingManage(meetingManageVO);
        	sLocationUrl = "redirect:/uss/olp/mgt/EgovMeetingManageList.do";
        }else{
            List<?> resultList = egovMeetingManageService.selectMeetingManageDetail(meetingManageVO);
            model.addAttribute("resultList", resultList);
        }

		return sLocationUrl;
	}

	/**
	 * 회의정보를 등록한다.
	 * @param searchVO
	 * @param meetingManageVO
	 * @param bindingResult
	 * @param commandMap
	 * @param model
	 * @return "egovframework/com/uss/olp/mgt/EgovMeetingManageRegist"
	 * @throws Exception
	 */
	@RequestMapping(value="/uss/olp/mgt/EgovMeetingManageRegist.do")
	public String meetingManageRegist(
			@ModelAttribute("searchVO") ComDefaultVO searchVO,
			@ModelAttribute("meetingManageVO") MeetingManageVO meetingManageVO,
			BindingResult bindingResult,
			@RequestParam Map<?, ?> commandMap,
    		ModelMap model)
    throws Exception {
    	// 0. Spring Security 사용자권한 처리
    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
    		model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
        	return "egovframework/com/uat/uia/EgovLoginUsr";
    	}

		//로그인 객체 선언
		LoginVO loginVO = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();

		String sLocationUrl = "egovframework/com/uss/olp/mgt/EgovMeetingManageRegist";

		String sCmd = commandMap.get("cmd") == null ? "" : (String)commandMap.get("cmd");
		LOGGER.info("cmd => {}", sCmd);

        if(sCmd.equals("save")){
    		//서버  validate 체크
            beanValidator.validate(meetingManageVO, bindingResult);
    		if(bindingResult.hasErrors()){
    			return sLocationUrl;
    		}
    		//아이디 설정
        	meetingManageVO.setFrstRegisterId(loginVO == null ? "" : EgovStringUtil.isNullToString(loginVO.getUniqId()));
        	meetingManageVO.setLastUpdusrId(loginVO == null ? "" : EgovStringUtil.isNullToString(loginVO.getUniqId()));

        	egovMeetingManageService.insertMeetingManage(meetingManageVO);
        	sLocationUrl = "redirect:/uss/olp/mgt/EgovMeetingManageList.do";
        }

		return sLocationUrl;
	}

}


