package egovframework.com.uss.olh.awm.web;

import java.util.List;

import javax.annotation.Resource;

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
import egovframework.com.uss.olh.awm.service.AdministrationWordVO;
import egovframework.com.uss.olh.awm.service.EgovAdministrationWordService;
import egovframework.com.utl.fcc.service.EgovStringUtil;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

/**
 * 행정전문용어사전관리를 처리하는 Controller Class 구현
 * @author 공통서비스 장동한
 * @since 2009.07.03
 * @version 1.0
 * @see <pre>
 * &lt;&lt; 개정이력(Modification Information) &gt;&gt;
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2009.07.03  장동한          최초 생성
 *   2011.8.26	 정진오			IncludedInfo annotation 추가
 *   2011.09.19  서준식          삭제 후 리스트 상세조회시 다시 삭제되는 문제 수정
 *   2016.08.10  김연호          표준프레임워크 3.6
 *   
 * </pre>
 */

@Controller
public class EgovAdministrationWordController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(EgovAdministrationWordController.class);

    @Autowired
    private DefaultBeanValidator beanValidator;

    /** EgovMessageSource */
    @Resource(name = "egovMessageSource")
    EgovMessageSource egovMessageSource;

    /** egovOnlinePollService */
    @Resource(name = "EgovAdministrationWordService")
    private EgovAdministrationWordService egovAdministrationWordService;
    
    @Resource(name="EgovCmmUseService")
	private EgovCmmUseService cmmUseService;

    /** EgovPropertyService */
    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;
    
    
    /**
     * 행정전문용어사전 목록을 조회한다.
     * @param searchVO
     * @param administrationWord
     * @param model
     * @return "egovframework/com/uss/olh/awm/EgovAdministrationWordList"
     * @throws Exception
     */
    @SuppressWarnings("unused")
	@IncludedInfo(name="행정전문용어사전", order = 560 ,gid = 50)
    @RequestMapping(value = "/uss/olh/awm/selectAdministrationWordList.do")
    public String egovAdministrationWordList(@ModelAttribute("searchVO") AdministrationWordVO searchVO, ModelMap model) throws Exception {

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

        List<?> resultList = egovAdministrationWordService.selectAdministrationWordList(searchVO);
        model.addAttribute("resultList", resultList);

        int totCnt = egovAdministrationWordService.selectAdministrationWordListCnt(searchVO);
        paginationInfo.setTotalRecordCount(totCnt);
        model.addAttribute("paginationInfo", paginationInfo);

        return "egovframework/com/uss/olh/awm/EgovAdministrationWordList";
    }
    
    /**
     * 행정전문용어사전 목록을 상세조회 조회한다.
     * @param searchVO
     * @param administrationWord
     * @param commandMap
     * @param model
     * @return
     *         "/uss/olh/awm/EgovAdministrationWordDetail"
     * @throws Exception
     */
    @SuppressWarnings("unused")
	@RequestMapping(value = "/uss/olh/awm/selectAdministrationWordDetail.do")
    public String selectAdministrationWordDetail(@ModelAttribute("searchVO") AdministrationWordVO searchVO, AdministrationWordVO administrationWord, ModelMap model) throws Exception {

        AdministrationWordVO result = egovAdministrationWordService.selectAdministrationWordDetail(administrationWord);
        model.addAttribute("result", result);

        return "egovframework/com/uss/olh/awm/EgovAdministrationWordDetail";
    }
    
    /**
     * 행정전문용어사전관리 목록을 조회한다.
     * @param searchVO
     * @param model
     * @return "egovframework/com/uss/olh/awm/EgovAdministrationWordManageList"
     * @throws Exception
     */
    @SuppressWarnings("unused")
	@IncludedInfo(name="행정전문용어사전관리", order = 561 ,gid = 50)
    @RequestMapping(value = "/uss/olh/awm/selectAdministrationWordManageList.do")
    public String egovAdministrationWordManageList(@ModelAttribute("searchVO") AdministrationWordVO searchVO, ModelMap model) throws Exception {

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

        List<?> resultList = egovAdministrationWordService.selectAdministrationWordList(searchVO);
        model.addAttribute("resultList", resultList);

        int totCnt = egovAdministrationWordService.selectAdministrationWordListCnt(searchVO);
        paginationInfo.setTotalRecordCount(totCnt);
        model.addAttribute("paginationInfo", paginationInfo);

        return "egovframework/com/uss/olh/awm/EgovAdministrationWordManageList";
    }
    
    /**
     * 행정전문용어사전관리 목록을 상세조회 조회한다.
     * @param searchVO
     * @param administrationWord
     * @param commandMap
     * @param model
     * @return
     *         "/uss/olh/awm/EgovAdministrationWordDetail"
     * @throws Exception
     */
    @SuppressWarnings("unused")
	@RequestMapping(value = "/uss/olh/awm/selectAdministrationWordManageDetail.do")
    public String selectAdministrationWordManageDetail(@ModelAttribute("searchVO") AdministrationWordVO searchVO, AdministrationWordVO administrationWord, ModelMap model) throws Exception {

        AdministrationWordVO result = egovAdministrationWordService.selectAdministrationWordDetail(administrationWord);
        model.addAttribute("result", result);

        return "egovframework/com/uss/olh/awm/EgovAdministrationWordManageDetail";
    }
    
    /**
     * 행정전문용어사전을 등록하기 위한 전 처리(공통코드 처리)
     * @param searchVO
     * @param model
     * @return	"/uss/olh/awm/EgovAdministrationWordRegist"
     * @throws Exception
     */
    @RequestMapping("/uss/olh/awm/insertAdministrationWordView.do")
    public String insertAdministrationWordView(@ModelAttribute("searchVO") AdministrationWordVO searchVO, Model model) throws Exception {

    	// 공통코드를 가져오기 위한 Vo
    	ComDefaultCodeVO vo = new ComDefaultCodeVO();
		vo.setCodeId("COM102");

		List<?> _result = cmmUseService.selectCmmCodeDetail(vo);
		model.addAttribute("wordSeCode", _result);

        model.addAttribute("administrationWordVO", new AdministrationWordVO());

        return "egovframework/com/uss/olh/awm/EgovAdministrationWordRegist";

    }
    
    /**
     * 행정전문용어사전을 등록한다.
     * @param searchVO
     * @param administrationWordVO
     * @param bindingResult
     * @return	"forward:/uss/olh/awm/selectAdministrationWordManageList.do"
     * @throws Exception
     */
    @RequestMapping("/uss/olh/awm/insertAdministrationWord.do")
    public String insertAdministrationWord(
            @ModelAttribute("searchVO") AdministrationWordVO searchVO,  @ModelAttribute("administrationWordVO") AdministrationWordVO administrationWordVO,
            BindingResult bindingResult) throws Exception {

    	beanValidator.validate(administrationWordVO, bindingResult);
		if(bindingResult.hasErrors()){
			return "egovframework/com/uss/olh/awm/EgovAdministrationWordRegist";
		}

    	// 로그인VO에서  사용자 정보 가져오기
    	LoginVO	loginVO = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();

    	String frstRegisterId = loginVO == null ? "" : EgovStringUtil.isNullToString(loginVO.getUniqId());

    	administrationWordVO.setFrstRegisterId(frstRegisterId);		// 최초등록자ID
    	administrationWordVO.setLastUpdusrId(frstRegisterId);    	// 최종수정자ID

        egovAdministrationWordService.insertAdministrationWord(administrationWordVO);

        return "forward:/uss/olh/awm/selectAdministrationWordManageList.do";
    }
    
    /**
     * 행정전문용어사전을 수정하기 위한 전 처리(공통코드 처리)
     * @param administWordId
     * @param searchVO
     * @param model
     * @return	"/uss/olh/hpc/EgovAdministrationWordUpdt"
     * @throws Exception
     */
    @RequestMapping("/uss/olh/awm/updateAdministrationWordView.do")
    public String updateAdministrationWordView(@RequestParam("administWordId") String administWordId ,
            @ModelAttribute("searchVO") AdministrationWordVO searchVO, ModelMap model)
            throws Exception {

    	// 공통코드를 가져오기 위한 Vo
    	ComDefaultCodeVO vo = new ComDefaultCodeVO();
		vo.setCodeId("COM102");

		List<?> _result = cmmUseService.selectCmmCodeDetail(vo);
		model.addAttribute("wordSeCode", _result);

		AdministrationWordVO administrationWordVO = new AdministrationWordVO();
		administrationWordVO.setAdministWordId(administWordId);

        model.addAttribute("administrationWordVO", egovAdministrationWordService.selectAdministrationWordDetail(administrationWordVO));

        return "egovframework/com/uss/olh/awm/EgovAdministrationWordUpdt";
    }
    
    /**
     * 행정전문용어사전을 수정한다.
     * @param searchVO
     * @param administrationWordVO
     * @param bindingResult
     * @return	"forward:/uss/olh/awm/selectAdministrationWordManageList.do"
     * @throws Exception
     */
    @RequestMapping("/uss/olh/awm/updateAdministrationWord.do")
    public String updateAdministrationWord(
            @ModelAttribute("searchVO") AdministrationWordVO searchVO,
            @ModelAttribute("administrationWordVO") AdministrationWordVO administrationWordVO,
            BindingResult bindingResult)
            throws Exception {

    	// Validation
    	beanValidator.validate(administrationWordVO, bindingResult);
		if(bindingResult.hasErrors()){
			return "egovframework/com/uss/olh/awm/EgovAdministrationWordUpdt";
		}

    	LoginVO	loginVO = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
    	String	lastUpdusrId = loginVO == null ? "" : EgovStringUtil.isNullToString(loginVO.getUniqId());

    	administrationWordVO.setLastUpdusrId(lastUpdusrId);    	// 최종수정자ID
    	egovAdministrationWordService.updateAdministrationWord(administrationWordVO);


        return "forward:/uss/olh/awm/selectAdministrationWordManageList.do";

    }
    
    /**
     * 행정전문용어사전을 삭제한다.
     * @param hpcmVO
     * @param searchVO
     * @return	"forward:/uss/olh/awm/selectAdministrationWordManageList.do"
     * @throws Exception
     */
    @RequestMapping("/uss/olh/awm/deleteAdministrationWord.do")
    public String deleteAdministrationWord(AdministrationWordVO administrationWordVO, @ModelAttribute("searchVO") AdministrationWordVO searchVO)  throws Exception {

    	egovAdministrationWordService.deleteAdministrationWord(administrationWordVO);

        return "forward:/uss/olh/awm/selectAdministrationWordManageList.do";
    }
    
}
