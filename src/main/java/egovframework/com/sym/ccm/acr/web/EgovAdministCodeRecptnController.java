package egovframework.com.sym.ccm.acr.web;

import java.util.List;
import java.util.Map;

import egovframework.com.cmm.ComDefaultCodeVO;
import egovframework.com.cmm.annotation.IncludedInfo;
import egovframework.com.cmm.service.EgovCmmUseService;
import egovframework.com.sym.ccm.acr.service.AdministCodeRecptn;
import egovframework.com.sym.ccm.acr.service.AdministCodeRecptnVO;
import egovframework.com.sym.ccm.acr.service.EgovAdministCodeRecptnService;

import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * 법정동코드를 수신에 관한 요청을 받아 서비스 클래스로 요청을 전달하고 서비스클래스에서 처리한 결과를 웹 화면으로 전달을 위한 Controller를 정의한다
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
 *
 * Copyright (C) 2009 by MOPAS  All right reserved.
 * </pre>
 */

@Controller
public class EgovAdministCodeRecptnController {

	@Resource(name = "AdministCodeRecptnService")
    private EgovAdministCodeRecptnService administCodeManageService;

    /** EgovPropertyService */
    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;

	@Resource(name="EgovCmmUseService")
	private EgovCmmUseService cmmUseService;

	/**
	 * TEST 용 Controller
	 * 실 적용시 Job Scheduler 에 등록하여 처리한다.
	 * 법정동코드를 수신처리한다.
	 * @param loginVO
	 * @param administCode
	 * @param bindingResult
	 * @param model
	 * @return "egovframework/com/sym/ccm/adc/EgovCcmAdministCodeRegist"
	 * @throws Exception
	 */
    @RequestMapping(value="/sym/ccm/acr/addAdministCode.do")
	public String insertAdministCodeRecptn (AdministCodeRecptn administCodeRecptn
			, BindingResult bindingResult
			, @RequestParam Map<?, ?> commandMap
			, ModelMap model
			) throws Exception {
		String sCmd = commandMap.get("cmd") == null ? "" : (String)commandMap.get("cmd");
    	if   (sCmd.equals("")) {

	    	administCodeManageService.insertAdministCodeRecptn();

    		return "egovframework/com/sym/ccm/acr/EgovAdministCodeRegist_TEST";
    	} else {
	        return "forward:/sym/ccm/acr/getAdministCodeRecptnList.do";
    	}
    }

	/**
	 * 법정동코드 상세내역을 조회한다.
	 * @param loginVO
	 * @param administCode
	 * @param model
	 * @return "egovframework/com/sym/ccm/adc/EgovCcmAdministCodeDetail"
	 * @throws Exception
	 */
	@RequestMapping(value="/sym/ccm/acr/getAdministCodeDetail.do")
 	public String selectAdministCodeDetail (@ModelAttribute("administCode") AdministCodeRecptn administCode
			, @ModelAttribute("administCodeRecptnVO") AdministCodeRecptnVO administCodeRecptnVO
			, ModelMap model
 			) throws Exception {
		/* 법정동코드 조회 */
		AdministCodeRecptn vo = administCodeManageService.selectAdministCodeDetail(administCode);
		model.addAttribute("result", vo);

    	/* 법정동코드수신 리스트 */
    	administCodeRecptnVO.setRecordCountPerPage(9999999);
    	administCodeRecptnVO.setFirstIndex(0);


    	/* 공통코드처리 */
    	ComDefaultCodeVO comCodeVO = new ComDefaultCodeVO();

    	/* 변경구분코드 */
    	comCodeVO.setCodeId("COM043");
        List<?> changeSeCodeList = cmmUseService.selectCmmCodeDetail(comCodeVO);
        model.addAttribute("changeSeCodeList", changeSeCodeList);

    	/* 처리구분코드 */
        comCodeVO.setCodeId("COM044");
        List<?> processSeList = cmmUseService.selectCmmCodeDetail(comCodeVO);
        model.addAttribute("processSeList", processSeList);

        administCodeRecptnVO.setSearchCondition("CodeList");
        List<?> administCodeRecptnList = administCodeManageService.selectAdministCodeRecptnList(administCodeRecptnVO);
        model.addAttribute("administCodeRecptnList", administCodeRecptnList);

		return "egovframework/com/sym/ccm/acr/EgovAdministCodeDetail";
	}

    /**
	 * 법정동코드수신 목록을 조회한다.
     * @param loginVO
     * @param searchVO
     * @param model
     * @return "egovframework/com/sym/ccm/adc/EgovCcmAdministCodeList"
     * @throws Exception
     */
	@IncludedInfo(name="행정코드관리", listUrl="/sym/ccm/acr/getAdministCodeRecptnList.do", order = 1010 ,gid = 60)
    @RequestMapping(value="/sym/ccm/acr/getAdministCodeRecptnList.do")
	public String selectAdministCodeRecptnList (@ModelAttribute("searchVO") AdministCodeRecptnVO searchVO
			, ModelMap model
			) throws Exception {
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

        List<?> administCodeRecptnList = administCodeManageService.selectAdministCodeRecptnList(searchVO);
        model.addAttribute("resultList", administCodeRecptnList);

        int totCnt = administCodeManageService.selectAdministCodeRecptnListTotCnt(searchVO);
		paginationInfo.setTotalRecordCount(totCnt);
        model.addAttribute("paginationInfo", paginationInfo);

        return "egovframework/com/sym/ccm/acr/EgovAdministCodeRecptnList";
	}

    /**
	 * 법정동코드수신 개인화페이지용 목록을 조회한다.
     * @param loginVO
     * @param searchVO
     * @param model
     * @return "egovframework/com/sym/ccm/adc/EgovCcmAdministCodeList"
     * @throws Exception
     */
    @RequestMapping(value="/sym/ccm/acr/getAdministCodeRecptnMainList.do")
	public String selectAdministCodeRecptnMainList (@ModelAttribute("searchVO") AdministCodeRecptnVO searchVO
			, ModelMap model
			) throws Exception {
    	/** pageing */
		searchVO.setRecordCountPerPage(6);
		searchVO.setFirstIndex(0);

        List<?> administCodeRecptnList = administCodeManageService.selectAdministCodeRecptnList(searchVO);
        model.addAttribute("resultList", administCodeRecptnList);

        int totCnt = administCodeManageService.selectAdministCodeRecptnListTotCnt(searchVO);
    	PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setTotalRecordCount(totCnt);
        model.addAttribute("paginationInfo", paginationInfo);

        return "egovframework/com/sym/ccm/acr/EgovAdministCodeRecptnMainList";
	}

    /**
     * Map 내용을 확인한다.
     * @param commandMap
     * @return
     */
	public String printParameterMap(@RequestParam Map<?, ?> commandMap){
		String ret = "";
       	for(Object key:commandMap.keySet()){
    		Object value = commandMap.get(key);

    		ret += "key:" + key.toString() + " value:" + value.toString();
    	}
       	return ret;
	}

}