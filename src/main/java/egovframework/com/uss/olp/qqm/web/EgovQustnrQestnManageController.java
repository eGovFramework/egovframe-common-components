package egovframework.com.uss.olp.qqm.web;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.egovframe.rte.fdl.property.EgovPropertyService;
import org.egovframe.rte.psl.dataaccess.util.EgovMap;
import org.egovframe.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import egovframework.com.cmm.ComDefaultCodeVO;
import egovframework.com.cmm.ComDefaultVO;
import egovframework.com.cmm.EgovMessageSource;
import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.annotation.IncludedInfo;
import egovframework.com.cmm.service.EgovCmmUseService;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.com.uss.olp.qqm.service.EgovQustnrQestnManageService;
import egovframework.com.uss.olp.qqm.service.QustnrQestnManageVO;
import egovframework.com.utl.fcc.service.EgovStringUtil;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;

/**
 * 설문문항을 처리하는 Controller Class 구현
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
 *   2017.09.04  김예영          표준프레임워크 v3.7 개선
 *
 * </pre>
 */
@Controller
public class EgovQustnrQestnManageController {

	private static final Logger LOGGER = LoggerFactory.getLogger(EgovQustnrQestnManageController.class);

	/** EgovMessageSource */
    @Resource(name="egovMessageSource")
    EgovMessageSource egovMessageSource;

	@Resource(name = "egovQustnrQestnManageService")
	private EgovQustnrQestnManageService egovQustnrQestnManageService;

    /** EgovPropertyService */
    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;

	@Resource(name="EgovCmmUseService")
	private EgovCmmUseService cmmUseService;

    /**
     * 설문문항 통계를 조회한다.
     *
     * @param searchVO
     * @param qustnrQestnManageVO
     * @param commandMap
     * @param model
     * @return "egovframework/com/uss/olp/qqm/EgovQustnrQestnManageStatistics"
     * @throws Exception
     */
	@RequestMapping(value = "/uss/olp/qqm/EgovQustnrQestnManageStatistics.do")
    public String egovQustnrQestnManageStatistics(@ModelAttribute("searchVO") ComDefaultVO searchVO, QustnrQestnManageVO qustnrQestnManageVO, @RequestParam Map<?, ?> commandMap, ModelMap model) throws Exception {

        String sLocationUrl = "egovframework/com/uss/olp/qqm/EgovQustnrQestnManageStatistics";

        List<EgovMap> sampleList = egovQustnrQestnManageService.selectQustnrQestnManageDetail(qustnrQestnManageVO);
        model.addAttribute("resultList", sampleList);

        HashMap<String, String> mapParam = new HashMap<>();
        mapParam.put("qestnrQesitmId", qustnrQestnManageVO.getQestnrQesitmId());

        // System.out.println("qustnrQestnManageVO.getQestnTyCode() :
        // "+qustnrQestnManageVO.getQestnTyCode());

        if ("2".equals(qustnrQestnManageVO.getQestnTyCode())) {
            // 주관식 설문통계
            List<EgovMap> statisticsList2 = egovQustnrQestnManageService.selectQustnrManageStatistics2(mapParam);
            model.addAttribute("statisticsList2", statisticsList2);

        } else {
            // 객관식설문통계
            List<?> statisticsList = egovQustnrQestnManageService.selectQustnrManageStatistics(mapParam);
            model.addAttribute("statisticsList", statisticsList);
            // 객관식과 주관식 문항이 동시에 달렸을 경우 주관식 설문 통계
            List<EgovMap> statisticsList2 = egovQustnrQestnManageService.selectQustnrManageStatistics2(mapParam);
            model.addAttribute("statisticsList2", statisticsList2);
        }

        return sLocationUrl;
    }

	/**
	 * 설문문항 팝업 목록을 조회한다.
	 * @param searchVO
	 * @param qustnrQestnManageVO
	 * @param commandMap
	 * @param model
	 * @return "egovframework/com/uss/olp/qqm/EgovQustnrQestnManageListPopup"
	 * @throws Exception
	 */
	@RequestMapping(value="/uss/olp/qqm/EgovQustnrQestnManageListPopup.do")
	public String egovQustnrQestnManageListPopup(
			@ModelAttribute("searchVO") ComDefaultVO searchVO,
			@ModelAttribute("qustnrQestnManageVO") QustnrQestnManageVO qustnrQestnManageVO,
			@RequestParam Map<?, ?> commandMap,
    		ModelMap model)
    throws Exception {
		LOGGER.info("#### POPUP CONTROLLER ENTER ###");
		String sSearchMode = commandMap.get("searchMode") == null ? "" : (String)commandMap.get("searchMode");
		
		//설문지정보에서 넘어오면 자동검색 설정
		if(sSearchMode.equals("Y")){
			String qestnrId = qustnrQestnManageVO.getQestnrId();
			if(qestnrId !=null && !"".equals(qestnrId)) {
				searchVO.setSearchCondition("QESTNR_ID");
				searchVO.setSearchKeyword(qestnrId);
			}
		}

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

        List<?> resultList = egovQustnrQestnManageService.selectQustnrQestnManageList(searchVO);
        LOGGER.info("#### resultList size = " + resultList.size());
        model.addAttribute("resultList", resultList);

        int totCnt = egovQustnrQestnManageService.selectQustnrQestnManageListCnt(searchVO);
		paginationInfo.setTotalRecordCount(totCnt);
        model.addAttribute("paginationInfo", paginationInfo);

		return "egovframework/com/uss/olp/qqm/EgovQustnrQestnManageListPopup";
	}

	/**
	 * 설문문항 목록을 조회한다.
	 * @param searchVO
	 * @param qustnrQestnManageVO
	 * @param commandMap
	 * @param model
	 * @return "egovframework/com/uss/olp/qqm/EgovQustnrQestnManageList"
	 * @throws Exception
	 */
	@SuppressWarnings("unused")
	@IncludedInfo(name="질문관리", order = 630 ,gid = 50)
	@RequestMapping(value="/uss/olp/qqm/EgovQustnrQestnManageList.do")
	public String egovQustnrQestnManageList(
			@ModelAttribute("searchVO") ComDefaultVO searchVO,
			@ModelAttribute("qustnrQestnManageVO") QustnrQestnManageVO qustnrQestnManageVO,
			@RequestParam Map<?, ?> commandMap,
			RedirectAttributes redirectAttributes,
    		ModelMap model)
    throws Exception {
    	// 0. Spring Security 사용자권한 처리
    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
    		redirectAttributes.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
        	return "redirect:/uat/uia/egovLoginUsr.do";
    	}

		//로그인 객체 선언
		LoginVO loginVO = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();

		String sCmd = commandMap.get("cmd") == null ? "" : (String)commandMap.get("cmd");
		String sSearchMode = commandMap.get("searchMode") == null ? "" : (String)commandMap.get("searchMode");

		if(sCmd.equals("del")){
			egovQustnrQestnManageService.deleteQustnrQestnManage(qustnrQestnManageVO);
		}

		//설문지정보에서 넘어오면 자동검색 설정
		if(sSearchMode.equals("Y")){
			searchVO.setSearchCondition("QESTNR_ID");
			searchVO.setSearchKeyword(qustnrQestnManageVO.getQestnrId());
		}

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

        List<?> sampleList = egovQustnrQestnManageService.selectQustnrQestnManageList(searchVO);
        model.addAttribute("resultList", sampleList);

        /** 2017.09.04 model에 addAttribute 추가 */
        model.addAttribute("searchKeyword", commandMap.get("searchKeyword") == null ? "" : (String)commandMap.get("searchKeyword"));
        model.addAttribute("searchCondition", commandMap.get("searchCondition") == null ? "" : (String)commandMap.get("searchCondition"));

        int totCnt = egovQustnrQestnManageService.selectQustnrQestnManageListCnt(searchVO);
		paginationInfo.setTotalRecordCount(totCnt);
        model.addAttribute("paginationInfo", paginationInfo);

		return "egovframework/com/uss/olp/qqm/EgovQustnrQestnManageList";
	}

	/**
	 * 설문문항 목록을 상세조회 조회한다.
	 * @param searchVO
	 * @param qustnrQestnManageVO
	 * @param commandMap
	 * @param model
	 * @return "egovframework/com/uss/olp/qqm/EgovQustnrQestnManageDetail"
	 * @throws Exception
	 */
	@RequestMapping(value="/uss/olp/qqm/EgovQustnrQestnManageDetail.do")
	public String egovQustnrQestnManageDetail(
			@ModelAttribute("searchVO") ComDefaultVO searchVO,
			@ModelAttribute("qustnrQestnManageVO") QustnrQestnManageVO qustnrQestnManageVO,
			@RequestParam Map<?, ?> commandMap,
    		ModelMap model)
    throws Exception {

		String sLocationUrl = "egovframework/com/uss/olp/qqm/EgovQustnrQestnManageDetail";

		String sCmd = commandMap.get("cmd") == null ? "" : (String)commandMap.get("cmd");

		if(sCmd.equals("del")){
			egovQustnrQestnManageService.deleteQustnrQestnManage(qustnrQestnManageVO);
			/** 목록으로갈때 검색조건 유지 */
			sLocationUrl = "redirect:/uss/olp/qqm/EgovQustnrQestnManageList.do?";
        	sLocationUrl = sLocationUrl + "searchMode=" + qustnrQestnManageVO.getSearchMode();
        	sLocationUrl = sLocationUrl + "&qestnrId=" + qustnrQestnManageVO.getQestnrId();
        	sLocationUrl = sLocationUrl + "&qestnrTmplatId=" +qustnrQestnManageVO.getQestnrTmplatId();
		}else{
	     	//공통코드 질문유형 조회
	    	ComDefaultCodeVO voComCode = new ComDefaultCodeVO();
	    	voComCode.setCodeId("COM018");
	    	List<?> listComCode = cmmUseService.selectCmmCodeDetail(voComCode);
	    	model.addAttribute("cmmCode018", listComCode);

	        List<EgovMap> sampleList = egovQustnrQestnManageService.selectQustnrQestnManageDetail(qustnrQestnManageVO);
	        model.addAttribute("resultList", sampleList);
		}

		return sLocationUrl;
	}
	
	/**
	 * 설문문항 수정페이지 조회.
	 * @param searchVO
	 * @param qustnrQestnManageVO
	 * @param redirectAttributes
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@GetMapping(value = "/uss/olp/qqm/EgovQustnrQestnManageModify.do")
		public String qustnrQestnManageModifyView(
				@RequestParam("qestnrQesitmId") String qestnrQesitmId,
		        @ModelAttribute("searchVO") ComDefaultVO searchVO,
		        @ModelAttribute("qustnrQestnManageVO") QustnrQestnManageVO qustnrQestnManageVO,
		        RedirectAttributes redirectAttributes,
		        ModelMap model) throws Exception {

		    // 0. 인증 체크
		    if (!EgovUserDetailsHelper.isAuthenticated()) {
		        redirectAttributes.addAttribute(
		            "message",
		            egovMessageSource.getMessage("fail.common.login")
		        );
		        return "redirect:/uat/uia/egovLoginUsr.do";
		    }

		    // 1. 공통코드 (질문유형)
		    ComDefaultCodeVO voComCode = new ComDefaultCodeVO();
		    voComCode.setCodeId("COM018");
		    model.addAttribute(
		        "cmmCode018",
		        cmmUseService.selectCmmCodeDetail(voComCode)
		    );

		    // 2. 설문문항 상세 조회
		    QustnrQestnManageVO vo = new QustnrQestnManageVO();
		    vo.setQestnrQesitmId(qestnrQesitmId);
		    
		    List<?> resultList =
		        egovQustnrQestnManageService
		            .selectQustnrQestnManageDetail(vo);
		    model.addAttribute("resultList", resultList);
		    model.addAttribute("qustnrQestnManageVO", vo);

		    return "egovframework/com/uss/olp/qqm/EgovQustnrQestnManageModify";
		}
	
	/**
	 * 설문문항 수정하기.
	 * @param searchVO
	 * @param qustnrQestnManageVO
	 * @param bindingResult
	 * @param redirectAttributes
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@PostMapping(value = "/uss/olp/qqm/EgovQustnrQestnManageModify.do")
	public String qustnrQestnManageModifySave(
	        @ModelAttribute("searchVO") ComDefaultVO searchVO,
	        @Valid @ModelAttribute("qustnrQestnManageVO")
	            QustnrQestnManageVO qustnrQestnManageVO,
	        BindingResult bindingResult,
	        RedirectAttributes redirectAttributes,
	        ModelMap model) throws Exception {

	    // 0. 인증 체크
	    if (!EgovUserDetailsHelper.isAuthenticated()) {
	        redirectAttributes.addAttribute(
	            "message",
	            egovMessageSource.getMessage("fail.common.login")
	        );
	        return "redirect:/uat/uia/egovLoginUsr.do";
	    }

	    // 1. 공통코드 (에러 시 재렌더링 대비)
	    ComDefaultCodeVO voComCode = new ComDefaultCodeVO();
	    voComCode.setCodeId("COM018");
	    model.addAttribute(
	        "cmmCode018",
	        cmmUseService.selectCmmCodeDetail(voComCode)
	    );

	    // 2. validation 실패
	    if (bindingResult.hasErrors()) {

	        // 설문 제목 복구
	        Map<String, String> map = new HashMap<>();
	        map.put("qestnrId", qustnrQestnManageVO.getQestnrId());
	        map.put("qestnrTmplatId", qustnrQestnManageVO.getQestnrTmplatId());

	        model.addAttribute(
	            "qestnrInfo",
	            egovQustnrQestnManageService
	                .selectQustnrManageQestnrSj(map)
	        );

	        model.addAttribute(
	            "resultList",
	            egovQustnrQestnManageService
	                .selectQustnrQestnManageDetail(qustnrQestnManageVO)
	        );

	        return "egovframework/com/uss/olp/qqm/EgovQustnrQestnManageModify";
	    }

	    // 3. 저장
	    LoginVO loginVO =
	        (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();

	    qustnrQestnManageVO.setFrstRegisterId(
	        loginVO == null ? "" : loginVO.getUniqId()
	    );
	    qustnrQestnManageVO.setLastUpdusrId(
	        loginVO == null ? "" : loginVO.getUniqId()
	    );

	    egovQustnrQestnManageService
	        .updateQustnrQestnManage(qustnrQestnManageVO);

	    // 4. 목록 이동
	    return "redirect:/uss/olp/qqm/EgovQustnrQestnManageList.do"
	        + "?searchMode=" + qustnrQestnManageVO.getSearchMode()
	        + "&qestnrId=" + qustnrQestnManageVO.getQestnrId()
	        + "&qestnrTmplatId=" + qustnrQestnManageVO.getQestnrTmplatId();
	}
	
	/**
	 * 설문문항 등록페이지 조회.
	 * @param searchVO
	 * @param commandMap
	 * @param model
	 * @param redirectAttributes
	 * @return
	 * @throws Exception
	 */
	@GetMapping(value = "/uss/olp/qqm/EgovQustnrQestnManageRegist.do")
		public String qustnrQestnManageRegistForm(
		        @ModelAttribute("searchVO") ComDefaultVO searchVO,
		        @RequestParam Map<?, ?> commandMap,
		        ModelMap model,
		        RedirectAttributes redirectAttributes
		) throws Exception {

	    // 0. Spring Security 사용자권한 처리
	    Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
	    if (!isAuthenticated) {
	        redirectAttributes.addAttribute(
	            "message",
	            egovMessageSource.getMessage("fail.common.login")
	        );
	        return "redirect:/uat/uia/egovLoginUsr.do";
	    }

	    // 공통코드 질문유형 조회
	    ComDefaultCodeVO voComCode = new ComDefaultCodeVO();
	    voComCode.setCodeId("COM018");
	    List<?> listComCode = cmmUseService.selectCmmCodeDetail(voComCode);
	    model.addAttribute("cmmCode018", listComCode);

	    // 설문 제목 조회
	    String sQestnrId = commandMap.get("qestnrId") == null ? "" : (String) commandMap.get("qestnrId");
	    String sQestnrTmplatId = commandMap.get("qestnrTmplatId") == null ? "" : (String) commandMap.get("qestnrTmplatId");

	    if (!sQestnrId.isEmpty() && !sQestnrTmplatId.isEmpty()) {
	        Map<String, String> mapQustnrManage = new HashMap<>();
	        mapQustnrManage.put("qestnrId", sQestnrId);
	        mapQustnrManage.put("qestnrTmplatId", sQestnrTmplatId);
	        model.addAttribute(
	            "qestnrInfo",
	            egovQustnrQestnManageService.selectQustnrManageQestnrSj(mapQustnrManage)
	        );
	    }
	    model.addAttribute("qustnrQestnManageVO", new QustnrQestnManageVO());
	    
	    return "egovframework/com/uss/olp/qqm/EgovQustnrQestnManageRegist";
	}

	/**
	 * 설문문항 등록하기.
	 * @param searchVO
	 * @param qustnrQestnManageVO
	 * @param bindingResult
	 * @param commandMap
	 * @param model
	 * @param redirectAttributes
	 * @return
	 * @throws Exception
	 */
	@PostMapping(value = "/uss/olp/qqm/EgovQustnrQestnManageRegist.do")
		public String qustnrQestnManageRegistSubmit(
		        @ModelAttribute("searchVO") ComDefaultVO searchVO,
		        @Valid @ModelAttribute("qustnrQestnManageVO") QustnrQestnManageVO qustnrQestnManageVO,
		        BindingResult bindingResult,
		        @RequestParam Map<?, ?> commandMap,
		        ModelMap model,
		        RedirectAttributes redirectAttributes
		) throws Exception {
		
	    // 0. Spring Security 사용자권한 처리
	    Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
	    if (!isAuthenticated) {
	        redirectAttributes.addAttribute(
	            "message",
	            egovMessageSource.getMessage("fail.common.login")
	        );
	        return "redirect:/uat/uia/egovLoginUsr.do";
	    }

	    // 공통코드 질문유형 조회 (에러 시 화면 복구용)
	    ComDefaultCodeVO voComCode = new ComDefaultCodeVO();
	    voComCode.setCodeId("COM018");
	    List<?> listComCode = cmmUseService.selectCmmCodeDetail(voComCode);
	    model.addAttribute("cmmCode018", listComCode);

	    // validation 에러
	    if (bindingResult.hasErrors()) {
	    	 LOGGER.error("#### VALIDATION ERROR OCCURRED");
	    	  bindingResult.getAllErrors().forEach(error -> {
	    	        LOGGER.error(
	    	            "#### error object={}, codes={}, defaultMessage={}",
	    	            error.getObjectName(),
	    	            Arrays.toString(error.getCodes()),
	    	            error.getDefaultMessage()
	    	        );
	    	    });
	    	  bindingResult.getFieldErrors().forEach(error -> {
	    	        LOGGER.error(
	    	            "#### field={}, rejectedValue=[{}], message={}",
	    	            error.getField(),
	    	            error.getRejectedValue(),
	    	            error.getDefaultMessage()
	    	        );
	    	    });
	    	  
	        String sQestnrId = commandMap.get("qestnrId") == null ? "" : (String) commandMap.get("qestnrId");
	        String sQestnrTmplatId = commandMap.get("qestnrTmplatId") == null ? "" : (String) commandMap.get("qestnrTmplatId");
	        
	        if(!sQestnrId.equals("") && !sQestnrTmplatId.equals("")){
           		Map<String, String> mapQustnrManage = new HashMap<>();
           		mapQustnrManage.put("qestnrId", sQestnrId);
           		mapQustnrManage.put("qestnrTmplatId", sQestnrTmplatId);
           		model.addAttribute("qestnrInfo", egovQustnrQestnManageService.selectQustnrManageQestnrSj(mapQustnrManage));
	        }
	        return "egovframework/com/uss/olp/qqm/EgovQustnrQestnManageRegist";
	    }

	    // 로그인 정보
	    LoginVO loginVO = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
	    qustnrQestnManageVO.setFrstRegisterId(
	        loginVO == null ? "" : EgovStringUtil.isNullToString(loginVO.getUniqId())
	    );
	    qustnrQestnManageVO.setLastUpdusrId(
	        loginVO == null ? "" : EgovStringUtil.isNullToString(loginVO.getUniqId())
	    );

	    // 저장
	    egovQustnrQestnManageService.insertQustnrQestnManage(qustnrQestnManageVO);
	    LOGGER.info(
	    	    "#### DB SELECT qestnrId=[{}], qestnrTmplatId=[{}]",
	    	    qustnrQestnManageVO.getQestnrId(),
	    	    qustnrQestnManageVO.getQestnrTmplatId()
	    	);


	    // 목록 이동
	    String sLocationUrl = "redirect:/uss/olp/qqm/EgovQustnrQestnManageList.do";
	    sLocationUrl += "?searchMode=" + qustnrQestnManageVO.getSearchMode();
	    sLocationUrl += "&qestnrId=" + qustnrQestnManageVO.getQestnrId();
	    sLocationUrl += "&qestnrTmplatId=" + qustnrQestnManageVO.getQestnrTmplatId();

	    return sLocationUrl;
	}

	
}


