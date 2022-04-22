package egovframework.com.uss.olh.omm.web;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springmodules.validation.commons.DefaultBeanValidator;

import egovframework.com.cmm.ComDefaultCodeVO;
import egovframework.com.cmm.EgovMessageSource;
import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.annotation.IncludedInfo;
import egovframework.com.cmm.service.EgovCmmUseService;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.com.cmm.util.EgovXssChecker;
import egovframework.com.uss.olh.omm.service.EgovOnlineManualService;
import egovframework.com.uss.olh.omm.service.OnlineManualVO;
import egovframework.com.utl.fcc.service.EgovStringUtil;
import org.egovframe.rte.fdl.property.EgovPropertyService;
import org.egovframe.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
/**
 * 온라인메뉴얼를 처리하는 Controller Class 구현
 * @author 공통서비스 장동한
 * @since 2009.07.03
 * @version 1.0
 * @see <pre>
 * &lt;&lt; 개정이력(Modification Information) &gt;&gt;
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2009.07.03  장동한          최초 생성
 *   2011.8.26	정진오			IncludedInfo annotation 추가
 *   2016.8.12	김연호 			표준프레임워크 3.6 개선
 *
 * </pre>
 */
@Controller
public class EgovOnlineManualController {
	private static final Logger LOGGER = LoggerFactory.getLogger(EgovOnlineManualController.class);

    @Autowired
    private DefaultBeanValidator beanValidator;

    /** EgovMessageSource */
    @Resource(name = "egovMessageSource")
    EgovMessageSource egovMessageSource;

    /** egovOnlinePollService */
    @Resource(name = "EgovOnlineManualService")
    private EgovOnlineManualService egovOnlineManualService;

    /** EgovPropertyService */
    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;

    /** Egov Common Code Service */
    @Resource(name="EgovCmmUseService")
    private EgovCmmUseService cmmUseService;
    
    /**
     * 사용자 온라인메뉴얼 목록을 조회한다.
     * @param searchVO
     * @param onlineManual
     * @param model
     * @return "egovframework/com/uss/olh/omn/EgovOnlineManualList"
     * @throws Exception
     */
    @SuppressWarnings("unused")
	@IncludedInfo(name="사용자온라인매뉴얼", order = 571 ,gid = 50)
    @RequestMapping(value = "/uss/olh/omn/selectOnlineManualList.do")
    public String selectOnlineManualUserList(@ModelAttribute("searchVO") OnlineManualVO searchVO, ModelMap model)
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

        List<?> reusltList = egovOnlineManualService.selectOnlineManualList(searchVO);
        model.addAttribute("resultList", reusltList);

        int totCnt = egovOnlineManualService.selectOnlineManualListCnt(searchVO);
        paginationInfo.setTotalRecordCount(totCnt);
        model.addAttribute("paginationInfo", paginationInfo);

        return "egovframework/com/uss/olh/omm/EgovOnlineManualUserList";
    }
    
    /**
     * 사용자온라인메뉴얼 상세조회 조회한다.
     * @param searchVO
     * @param onlineManualVO
     * @param model
     * @return
     *         "/uss/olh/omn/EgovOnlineManualUserDetail"
     * @throws Exception
     */
    @SuppressWarnings("unused")
	@RequestMapping(value = "/uss/olh/omn/selectOnlineManualDetail.do")
    public String selectOnlineManualUserDetail(@ModelAttribute("searchVO") OnlineManualVO searchVO, OnlineManualVO onlineManualVO, ModelMap model) throws Exception {

    	OnlineManualVO result = egovOnlineManualService.selectOnlineManualDetail(onlineManualVO);
        model.addAttribute("result", result);

        return "egovframework/com/uss/olh/omm/EgovOnlineManualUserDetail";
    }
    
    /**
     * 온라인메뉴얼 목록을 조회한다.
     * @param searchVO
     * @param onlineManual
     * @param model
     * @return "egovframework/com/uss/olh/omm/EgovOnlineManualList"
     * @throws Exception
     */
    @SuppressWarnings("unused")
	@IncludedInfo(name="온라인매뉴얼", order = 570 ,gid = 50)
    @RequestMapping(value = "/uss/olh/omm/selectOnlineManualList.do")
    public String selectOnlineManualList(@ModelAttribute("searchVO") OnlineManualVO searchVO, ModelMap model)
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

        List<?> reusltList = egovOnlineManualService.selectOnlineManualList(searchVO);
        model.addAttribute("resultList", reusltList);

        int totCnt = egovOnlineManualService.selectOnlineManualListCnt(searchVO);
        paginationInfo.setTotalRecordCount(totCnt);
        model.addAttribute("paginationInfo", paginationInfo);

        return "egovframework/com/uss/olh/omm/EgovOnlineManualList";
    }
    
    /**
     * 온라인메뉴얼 상세조회 조회한다.
     * @param searchVO
     * @param onlineManualVO
     * @param model
     * @return
     *         "/uss/olh/omm/EgovOnlineManualDetail"
     * @throws Exception
     */
    @SuppressWarnings("unused")
	@RequestMapping(value = "/uss/olh/omm/selectOnlineManualDetail.do")
    public String selectOnlineManualDetail(@ModelAttribute("searchVO") OnlineManualVO searchVO, OnlineManualVO onlineManualVO, ModelMap model) throws Exception {

    	OnlineManualVO result = egovOnlineManualService.selectOnlineManualDetail(onlineManualVO);
        model.addAttribute("result", result);

        return "egovframework/com/uss/olh/omm/EgovOnlineManualDetail";
    }
    
    /**
     * 온라인메뉴얼을 등록하기 위한 전 처리(공통코드 처리)
     * @param searchVO
     * @param model
     * @return	"/uss/olh/omm/EgovOnlineManualRegist"
     * @throws Exception
     */
    @RequestMapping("/uss/olh/omm/insertOnlineManualView.do")
    public String insertOnlineManualView(@ModelAttribute("searchVO") OnlineManualVO searchVO, Model model) throws Exception {

    	// 공통코드를 가져오기 위한 Vo
    	ComDefaultCodeVO vo = new ComDefaultCodeVO();
		vo.setCodeId("COM041");

		List<?> _result = cmmUseService.selectCmmCodeDetail(vo);
		model.addAttribute("onlineMnlSeCode", _result);

        model.addAttribute("onlineManualVO", new OnlineManualVO());

        return "egovframework/com/uss/olh/omm/EgovOnlineManualRegist";

    }
    
    /**
     * 온라인메뉴얼을 등록한다.
     * @param searchVO
     * @param onlineManualVO
     * @param bindingResult
     * @return	"forward:/uss/olh/awm/selectAdministrationWordManageList.do"
     * @throws Exception
     */
    @RequestMapping("/uss/olh/omm/insertOnlineManual.do")
    public String insertOnlineManual(
            @ModelAttribute("searchVO") OnlineManualVO searchVO,  @ModelAttribute("onlineManualVO") OnlineManualVO onlineManualVO,
            BindingResult bindingResult) throws Exception {

    	beanValidator.validate(onlineManualVO, bindingResult);
		if(bindingResult.hasErrors()){
			return "egovframework/com/uss/olh/omm/EgovOnlineManualRegist";
		}

    	// 로그인VO에서  사용자 정보 가져오기
    	LoginVO	loginVO = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();

    	String frstRegisterId = loginVO == null ? "" : EgovStringUtil.isNullToString(loginVO.getUniqId());

    	onlineManualVO.setFrstRegisterId(frstRegisterId);		// 최초등록자ID
    	onlineManualVO.setLastUpdusrId(frstRegisterId);    	// 최종수정자ID

        egovOnlineManualService.insertOnlineManual(onlineManualVO);

        return "forward:/uss/olh/omm/selectOnlineManualList.do";
    }
    
    /**
     * 온라인메뉴얼을 수정하기 위한 전 처리(공통코드 처리)
     * @param onlineMnlId
     * @param searchVO
     * @param model
     * @return	"/uss/olh/omm/EgovOnlineManualUpdt"
     * @throws Exception
     */
    @RequestMapping("/uss/olh/omm/updateOnlineManualView.do")
    public String updateOnlineManualView(@RequestParam("onlineMnlId") String onlineMnlId ,
            @ModelAttribute("searchVO") OnlineManualVO searchVO, ModelMap model)
            throws Exception {

    	// 공통코드를 가져오기 위한 Vo
    	ComDefaultCodeVO vo = new ComDefaultCodeVO();
		vo.setCodeId("COM041");

		List<?> _result = cmmUseService.selectCmmCodeDetail(vo);
		model.addAttribute("onlineMnlSeCode", _result);

		OnlineManualVO onlineManualVO = new OnlineManualVO();
		onlineManualVO.setOnlineMnlId(onlineMnlId);

        model.addAttribute("onlineManualVO", egovOnlineManualService.selectOnlineManualDetail(onlineManualVO));

        return "egovframework/com/uss/olh/omm/EgovOnlineManualUpdt";
    }
    
    /**
     * 온라인메뉴얼을 수정한다.
     * @param searchVO
     * @param onlineManualVO
     * @param bindingResult
     * @return	"forward:/uss/olh/omm/selectOnlineManualList.do"
     * @throws Exception
     */
    @RequestMapping("/uss/olh/omm/updateOnlineManual.do")
    public String updateOnlineManual(
    		HttpServletRequest request,
            @ModelAttribute("searchVO") OnlineManualVO searchVO,
            @ModelAttribute("onlineManualVO") OnlineManualVO onlineManualVO,
            BindingResult bindingResult)
            throws Exception {

    	// Validation
    	beanValidator.validate(onlineManualVO, bindingResult);
		if(bindingResult.hasErrors()){
			return "egovframework/com/uss/olh/omm/EgovOnlineManualUpdt";
		}
		
		//--------------------------------------------------------------------------------------------
		// @ XSS 사용자권한체크 START
		// param1 : 사용자고유ID(uniqId,esntlId)
		//--------------------------------------------------------
		LOGGER.debug("@ XSS 권한체크 START ----------------------------------------------");

		//step1 DB에서 해당 게시물의 uniqId 조회
		OnlineManualVO vo = (OnlineManualVO)egovOnlineManualService.selectOnlineManualDetail(onlineManualVO);

		//step2 EgovXssChecker 공통모듈을 이용한 권한체크
		EgovXssChecker.checkerUserXss(request, vo.getFrstRegisterId()); 
		LOGGER.debug("@ XSS 권한체크 END ------------------------------------------------");
		//--------------------------------------------------------
		// @ XSS 사용자권한체크 END
		//--------------------------------------------------------------------------------------------

    	LoginVO	loginVO = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
    	String	lastUpdusrId = loginVO == null ? "" : EgovStringUtil.isNullToString(loginVO.getUniqId());

    	onlineManualVO.setLastUpdusrId(lastUpdusrId);    	// 최종수정자ID
    	egovOnlineManualService.updateOnlineManual(onlineManualVO);


        return "forward:/uss/olh/omm/selectOnlineManualList.do";

    }
    
    /**
     * 온라인메뉴얼을 삭제한다.
     * @param onlineManualVO
     * @param searchVO
     * @return	"forward:/uss/olh/omm/selectOnlineManualList.do"
     * @throws Exception
     */
    @RequestMapping("/uss/olh/omm/deleteOnlineManual.do")
    public String deleteOnlineManual(
    		HttpServletRequest request,
    		OnlineManualVO onlineManualVO, 
    		@ModelAttribute("searchVO") OnlineManualVO searchVO)  throws Exception {

		//--------------------------------------------------------------------------------------------
		// @ XSS 사용자권한체크 START
		// param1 : 사용자고유ID(uniqId,esntlId)
		//--------------------------------------------------------
		LOGGER.debug("@ XSS 권한체크 START ----------------------------------------------");

		//step1 DB에서 해당 게시물의 uniqId 조회
		OnlineManualVO vo = (OnlineManualVO)egovOnlineManualService.selectOnlineManualDetail(onlineManualVO);

		//step2 EgovXssChecker 공통모듈을 이용한 권한체크
		EgovXssChecker.checkerUserXss(request, vo.getFrstRegisterId()); 
		LOGGER.debug("@ XSS 권한체크 END ------------------------------------------------");
		//--------------------------------------------------------
		// @ XSS 사용자권한체크 END
		//--------------------------------------------------------------------------------------------
		
    	egovOnlineManualService.deleteOnlineManual(onlineManualVO);

        return "forward:/uss/olh/omm/selectOnlineManualList.do";
    }
}
