package egovframework.com.ssi.syi.sim.web;

import java.util.List;
import java.util.Map;

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
import egovframework.com.ssi.syi.iis.service.CntcInsttVO;
import egovframework.com.ssi.syi.iis.service.CntcServiceVO;
import egovframework.com.ssi.syi.iis.service.CntcSystemVO;
import egovframework.com.ssi.syi.iis.service.EgovCntcInsttService;
import egovframework.com.ssi.syi.sim.service.EgovSystemCntcService;
import egovframework.com.ssi.syi.sim.service.SystemCntc;
import egovframework.com.ssi.syi.sim.service.SystemCntcVO;
import egovframework.com.utl.fcc.service.EgovStringUtil;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.psl.dataaccess.util.EgovMap;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

/**
 *
 * 시스템연계 관리에 관한 요청을 받아 서비스 클래스로 요청을 전달하고 서비스클래스에서 처리한 결과를 웹 화면으로 전달을 위한 Controller를 정의한다
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
public class EgovSystemCntcController {




	@Resource(name = "SystemCntcService")
    private EgovSystemCntcService systemCntcService;

	@Resource(name = "CntcInsttService")
    private EgovCntcInsttService cntcInsttService;

	/** EgovIdGnrService */
	@Resource(name="egovSystemCntcIdGnrService")
	private EgovIdGnrService idgenService;

	/** EgovMessageSource */
    @Resource(name="egovMessageSource")
    EgovMessageSource egovMessageSource;

	/** EgovPropertyService */
    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;

	@Autowired
	private DefaultBeanValidator beanValidator;

	/**
	 * 시스템연계를 삭제한다.
	 * @param loginVO
	 * @param systemCntc
	 * @param model
	 * @return "forward:/ssi/syi/sim/EgovCcmAdministCodeList.do"
	 * @throws Exception
	 */
    @RequestMapping(value="/ssi/syi/sim/removeSystemCntc.do")
	public String deleteSystemCntc (SystemCntc systemCntc
			, ModelMap model
			) throws Exception {
    	systemCntcService.deleteSystemCntc(systemCntc);
        return "forward:/ssi/syi/sim/getSystemCntcList.do";
	}

	/**
	 * 시스템연계를 등록한다.
	 * @param loginVO
	 * @param systemCntc
	 * @param bindingResult
	 * @param model
	 * @return "egovframework/com/ssi/syi/sim/EgovCcmSystemCntcRegist"
	 * @throws Exception
	 */
    @RequestMapping(value="/ssi/syi/sim/addSystemCntc.do")
	public String insertSystemCntc (@ModelAttribute("systemCntc") SystemCntc systemCntc
			, BindingResult bindingResult
			, @RequestParam Map<?, ?> commandMap
			, ModelMap model
			) throws Exception {
		String sCmd = commandMap.get("cmd") == null ? "" : (String)commandMap.get("cmd");
    	if   (sCmd.equals("")) {
			// 연계기관 리스트박스 데이터
    		CntcInsttVO searchCntcInsttVO;
    		searchCntcInsttVO = new CntcInsttVO();
    		searchCntcInsttVO.setRecordCountPerPage(999999);
    		searchCntcInsttVO.setFirstIndex(0);
    		searchCntcInsttVO.setSearchCondition("CodeList");
            List<?> cntcInsttList = cntcInsttService.selectCntcInsttList(searchCntcInsttVO);
            model.addAttribute("cntcInsttList", cntcInsttList);

			// 연계시스템 리스트박스 데이터
            CntcSystemVO searchCntcSystemVO;
            searchCntcSystemVO = new CntcSystemVO();
            searchCntcSystemVO.setRecordCountPerPage(999999);
            searchCntcSystemVO.setFirstIndex(0);
            searchCntcSystemVO.setSearchCondition("CodeList");
            if (systemCntc.getProvdInsttId().equals("")) {
            	if(cntcInsttList.size()>0) {
                	EgovMap emp = (EgovMap)cntcInsttList.get(0);
                	systemCntc.setProvdInsttId(emp.get("insttId").toString());
            	}
            }
            searchCntcSystemVO.setInsttId(systemCntc.getProvdInsttId());
            List<?> cntcProvdSystemList = cntcInsttService.selectCntcSystemList(searchCntcSystemVO);
            model.addAttribute("cntcProvdSystemList", cntcProvdSystemList);

            if (systemCntc.getRequstInsttId().equals("")) {
            	if(cntcInsttList.size()>0) {
                	EgovMap emp = (EgovMap)cntcInsttList.get(0);
                	systemCntc.setRequstInsttId(emp.get("insttId").toString());
            	}
            }
            searchCntcSystemVO.setInsttId(systemCntc.getRequstInsttId());
            List<?> cntcRequstSystemList = cntcInsttService.selectCntcSystemList(searchCntcSystemVO);
            model.addAttribute("cntcRequstSystemList", cntcRequstSystemList);

			// 연계서비스 리스트박스 데이터
            CntcServiceVO searchCntcServiceVO;
            searchCntcServiceVO = new CntcServiceVO();
            searchCntcServiceVO.setRecordCountPerPage(999999);
            searchCntcServiceVO.setFirstIndex(0);
            searchCntcServiceVO.setSearchCondition("CodeList");
            searchCntcServiceVO.setInsttId(systemCntc.getProvdInsttId());
            if (systemCntc.getProvdSysId().equals("")) {
            	if(cntcProvdSystemList.size()>0) {
	            	EgovMap emp = (EgovMap)cntcProvdSystemList.get(0);
	            	systemCntc.setProvdSysId(emp.get("sysId").toString());
            	}
            }
            searchCntcServiceVO.setSysId(systemCntc.getProvdSysId());
            List<?> cntcProvdServiceList = cntcInsttService.selectCntcServiceList(searchCntcServiceVO);
            model.addAttribute("cntcProvdServiceList", cntcProvdServiceList);

            return "egovframework/com/ssi/syi/sim/EgovSystemCntcRegist";
    	} else if (sCmd.equals("Regist")) {

	    	beanValidator.validate(systemCntc, bindingResult);
			if (bindingResult.hasErrors()){

				return "egovframework/com/ssi/syi/sim/EgovSystemCntcRegist";
			}

	    	// 로그인VO에서  사용자 정보 가져오기
	    	LoginVO	loginVO = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
	    	String	uniqId = loginVO == null ? "" : EgovStringUtil.isNullToString(loginVO.getUniqId());
	    	systemCntc.setFrstRegisterId(uniqId);

	    	// ID Generation
	    	String sCntcId = idgenService.getNextStringId();
	    	systemCntc.setCntcId(sCntcId);

	    	systemCntcService.insertSystemCntc(systemCntc);
	        return "forward:/ssi/syi/sim/getSystemCntcList.do";
    	} else {
	        return "forward:/ssi/syi/sim/getSystemCntcList.do";
    	}
    }

	/**
	 * 시스템연계 상세내역을 조회한다.
	 * @param loginVO
	 * @param systemCntc
	 * @param model
	 * @return "egovframework/com/ssi/syi/sim/EgovCcmSystemCntcDetail"
	 * @throws Exception
	 */
	@RequestMapping(value="/ssi/syi/sim/getSystemCntcDetail.do")
 	public String selectSystemCntcDetail (SystemCntc systemCntc
 			, ModelMap model
 			) throws Exception {

    	// 0. Spring Security 사용자권한 처리
    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
    		model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
        	return "egovframework/com/uat/uia/EgovLoginUsr";
    	}

    	String selfUri = "/ssi/syi/sim/getSystemCntcDetail.do";
        model.addAttribute("selfUri", selfUri);

        SystemCntc vo = systemCntcService.selectSystemCntcDetail(systemCntc);
		model.addAttribute("result", vo);

		// 연계기관 리스트박스 데이터
		CntcInsttVO searchCntcInsttVO;
		searchCntcInsttVO = new CntcInsttVO();
		searchCntcInsttVO.setRecordCountPerPage(999999);
		searchCntcInsttVO.setFirstIndex(0);
		searchCntcInsttVO.setSearchCondition("CodeList");
        List<?> cntcInsttList = cntcInsttService.selectCntcInsttList(searchCntcInsttVO);
        model.addAttribute("cntcInsttList", cntcInsttList);

		// 연계시스템 리스트박스 데이터
        CntcSystemVO searchCntcSystemVO;
        searchCntcSystemVO = new CntcSystemVO();
        searchCntcSystemVO.setRecordCountPerPage(999999);
        searchCntcSystemVO.setFirstIndex(0);
        searchCntcSystemVO.setSearchCondition("CodeList");
        searchCntcSystemVO.setInsttId(vo.getProvdInsttId());
        List<?> cntcProvdSystemList = cntcInsttService.selectCntcSystemList(searchCntcSystemVO);
        model.addAttribute("cntcProvdSystemList", cntcProvdSystemList);

        searchCntcSystemVO.setInsttId(vo.getRequstInsttId());
        List<?> cntcRequstSystemList = cntcInsttService.selectCntcSystemList(searchCntcSystemVO);
        model.addAttribute("cntcRequstSystemList", cntcRequstSystemList);

		// 연계서비스 리스트박스 데이터
        CntcServiceVO searchCntcServiceVO;
        searchCntcServiceVO = new CntcServiceVO();
        searchCntcServiceVO.setRecordCountPerPage(999999);
        searchCntcServiceVO.setFirstIndex(0);
        searchCntcServiceVO.setSearchCondition("CodeList");
        searchCntcServiceVO.setInsttId(vo.getProvdInsttId());
        searchCntcServiceVO.setSysId(vo.getProvdSysId());
        List<?> cntcProvdServiceList = cntcInsttService.selectCntcServiceList(searchCntcServiceVO);
        model.addAttribute("cntcProvdServiceList", cntcProvdServiceList);

		return "egovframework/com/ssi/syi/sim/EgovSystemCntcDetail";
	}

    /**
	 * 시스템연계 목록을 조회한다.
     * @param loginVO
     * @param searchVO
     * @param model
     * @return "egovframework/com/ssi/syi/sim/EgovCcmSystemCntcList"
     * @throws Exception
     */
	@IncludedInfo(name="시스템연계관리", listUrl="/ssi/syi/sim/getSystemCntcList.do", order = 1210,gid = 70)
    @RequestMapping(value="/ssi/syi/sim/getSystemCntcList.do")
	public String selectSystemCntcList (@ModelAttribute("searchVO") SystemCntcVO searchVO
			, ModelMap model
			) throws Exception {

    	// 0. Spring Security 사용자권한 처리
    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
    		model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
        	return "egovframework/com/uat/uia/EgovLoginUsr";
    	}

    	String selfUri = "/ssi/syi/sim/getSystemCntcList.do";
        model.addAttribute("selfUri", selfUri);

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

        List<?> CmmnCodeList = systemCntcService.selectSystemCntcList(searchVO);
        model.addAttribute("resultList", CmmnCodeList);

        int totCnt = systemCntcService.selectSystemCntcListTotCnt(searchVO);
		paginationInfo.setTotalRecordCount(totCnt);
        model.addAttribute("paginationInfo", paginationInfo);

        return "egovframework/com/ssi/syi/sim/EgovSystemCntcList";
	}

	/**
	 * 시스템연계를 수정한다.
	 * @param loginVO
	 * @param integInstt
	 * @param bindingResult
	 * @param commandMap
	 * @param model
	 * @return "egovframework/com/ssi/syi/sim/EgovCcmAdministCodeModify"
	 * @throws Exception
	 */
    @RequestMapping(value="/ssi/syi/sim/updateSystemCntc.do")
	public String updateSystemCntc (@ModelAttribute("systemCntc") SystemCntc systemCntc
			, BindingResult bindingResult
			, @RequestParam Map<?, ?> commandMap
			, ModelMap model
			) throws Exception {
		String sCmd = commandMap.get("cmd") == null ? "" : (String)commandMap.get("cmd");
    	if (sCmd.equals("")) {
    		if (systemCntc.getCntcNm().equals("")) {
	    		SystemCntc vo = systemCntcService.selectSystemCntcDetail(systemCntc);
	    		model.addAttribute("systemCntc", vo);
	    		systemCntc.setProvdInsttId(vo.getProvdInsttId());
	    		systemCntc.setRequstInsttId(vo.getRequstInsttId());
	    		systemCntc.setProvdSysId(vo.getProvdSysId());
    		}
    		// 연계기관 리스트박스 데이터
    		CntcInsttVO searchCntcInsttVO;
    		searchCntcInsttVO = new CntcInsttVO();
    		searchCntcInsttVO.setRecordCountPerPage(999999);
    		searchCntcInsttVO.setFirstIndex(0);
    		searchCntcInsttVO.setSearchCondition("CodeList");
            List<?> cntcInsttList = cntcInsttService.selectCntcInsttList(searchCntcInsttVO);
            model.addAttribute("cntcInsttList", cntcInsttList);

    		// 연계시스템 리스트박스 데이터
            CntcSystemVO searchCntcSystemVO;
            searchCntcSystemVO = new CntcSystemVO();
            searchCntcSystemVO.setRecordCountPerPage(999999);
            searchCntcSystemVO.setFirstIndex(0);
            searchCntcSystemVO.setSearchCondition("CodeList");
            searchCntcSystemVO.setInsttId(systemCntc.getProvdInsttId());
            List<?> cntcProvdSystemList = cntcInsttService.selectCntcSystemList(searchCntcSystemVO);
            model.addAttribute("cntcProvdSystemList", cntcProvdSystemList);

            searchCntcSystemVO.setInsttId(systemCntc.getRequstInsttId());
            List<?> cntcRequstSystemList = cntcInsttService.selectCntcSystemList(searchCntcSystemVO);
            model.addAttribute("cntcRequstSystemList", cntcRequstSystemList);

    		// 연계서비스 리스트박스 데이터
            CntcServiceVO searchCntcServiceVO;
            searchCntcServiceVO = new CntcServiceVO();
            searchCntcServiceVO.setRecordCountPerPage(999999);
            searchCntcServiceVO.setFirstIndex(0);
            searchCntcServiceVO.setSearchCondition("CodeList");
            searchCntcServiceVO.setInsttId(systemCntc.getProvdInsttId());
            searchCntcServiceVO.setSysId(systemCntc.getProvdSysId());
            List<?> cntcProvdServiceList = cntcInsttService.selectCntcServiceList(searchCntcServiceVO);
            model.addAttribute("cntcProvdServiceList", cntcProvdServiceList);

    		return "egovframework/com/ssi/syi/sim/EgovSystemCntcUpdt";
    	} else if (sCmd.equals("Modify")) {
            beanValidator.validate(systemCntc, bindingResult);
    		if (bindingResult.hasErrors()){
        		if (systemCntc.getCntcNm().equals("")) {
    	    		SystemCntc vo = systemCntcService.selectSystemCntcDetail(systemCntc);
    	    		model.addAttribute("systemCntc", vo);
    	    		systemCntc.setProvdInsttId(vo.getProvdInsttId());
    	    		systemCntc.setRequstInsttId(vo.getRequstInsttId());
    	    		systemCntc.setProvdSysId(vo.getProvdSysId());
        		}
	    		// 연계기관 리스트박스 데이터
	    		CntcInsttVO searchCntcInsttVO;
	    		searchCntcInsttVO = new CntcInsttVO();
	    		searchCntcInsttVO.setRecordCountPerPage(999999);
	    		searchCntcInsttVO.setFirstIndex(0);
	    		searchCntcInsttVO.setSearchCondition("CodeList");
	            List<?> cntcInsttList = cntcInsttService.selectCntcInsttList(searchCntcInsttVO);
	            model.addAttribute("cntcInsttList", cntcInsttList);

        		// 연계시스템 리스트박스 데이터
                CntcSystemVO searchCntcSystemVO;
                searchCntcSystemVO = new CntcSystemVO();
                searchCntcSystemVO.setRecordCountPerPage(999999);
                searchCntcSystemVO.setFirstIndex(0);
                searchCntcSystemVO.setSearchCondition("CodeList");
                searchCntcSystemVO.setInsttId(systemCntc.getProvdInsttId());
                List<?> cntcProvdSystemList = cntcInsttService.selectCntcSystemList(searchCntcSystemVO);
                model.addAttribute("cntcProvdSystemList", cntcProvdSystemList);

                searchCntcSystemVO.setInsttId(systemCntc.getRequstInsttId());
                List<?> cntcRequstSystemList = cntcInsttService.selectCntcSystemList(searchCntcSystemVO);
                model.addAttribute("cntcRequstSystemList", cntcRequstSystemList);

        		// 연계서비스 리스트박스 데이터
                CntcServiceVO searchCntcServiceVO;
                searchCntcServiceVO = new CntcServiceVO();
                searchCntcServiceVO.setRecordCountPerPage(999999);
                searchCntcServiceVO.setFirstIndex(0);
                searchCntcServiceVO.setSearchCondition("CodeList");
                searchCntcServiceVO.setInsttId(systemCntc.getProvdInsttId());
                searchCntcServiceVO.setSysId(systemCntc.getProvdSysId());
                List<?> cntcProvdServiceList = cntcInsttService.selectCntcServiceList(searchCntcServiceVO);
                model.addAttribute("cntcProvdServiceList", cntcProvdServiceList);

        		return "egovframework/com/ssi/syi/sim/EgovSystemCntcUpdt";
    		}

	    	// 로그인VO에서  사용자 정보 가져오기
	    	LoginVO	loginVO = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
	    	String	uniqId = loginVO == null ? "" : EgovStringUtil.isNullToString(loginVO.getUniqId());

	    	systemCntc.setLastUpdusrId(uniqId);
    		systemCntcService.updateSystemCntc(systemCntc);
	        return "forward:/ssi/syi/sim/getSystemCntcList.do";
    	} else {
    		return "forward:/ssi/syi/sim/getSystemCntcList.do";
    	}
    }

	/**
	 * 시스템연계 승인 목록을 조회한다.
	 * @param loginVO
	 * @param integInstt
	 * @param bindingResult
	 * @param commandMap
	 * @param model
	 * @return "egovframework/com/ssi/syi/sim/EgovCcmAdministCodeModify"
	 * @throws Exception
	 */
    @RequestMapping(value="/ssi/syi/scm/getConfirmSystemCntcList.do")
    //@RequestMapping(value="/ssi/syi/sim/getSystemCntcList.do")
	public String selectConfirmSystemCntcList (@ModelAttribute("searchVO") SystemCntcVO searchVO
			, ModelMap model
			) throws Exception {

    	// 0. Spring Security 사용자권한 처리
    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
    		model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
        	return "egovframework/com/uat/uia/EgovLoginUsr";
    	}

    	String selfUri = "/ssi/syi/scm/getConfirmSystemCntcList.do";
        model.addAttribute("selfUri", selfUri);

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

        List<?> CmmnCodeList = systemCntcService.selectSystemCntcList(searchVO);
        model.addAttribute("resultList", CmmnCodeList);

        int totCnt = systemCntcService.selectSystemCntcListTotCnt(searchVO);
		paginationInfo.setTotalRecordCount(totCnt);
        model.addAttribute("paginationInfo", paginationInfo);

        return "egovframework/com/ssi/syi/sim/EgovSystemCntcList";
	}

	/**
	 * 시스템연계 승인 상세내역을 조회한다.
	 * @param loginVO
	 * @param systemCntc
	 * @param model
	 * @return "egovframework/com/ssi/syi/sim/EgovCcmSystemCntcDetail"
	 * @throws Exception
	 */
    @RequestMapping(value="/ssi/syi/scm/getConfirmSystemCntcDetail.do")
	//@RequestMapping(value="/ssi/syi/sim/getSystemCntcDetail.do")
 	public String selectConfirmSystemCntcDetail (SystemCntc systemCntc
			, @RequestParam Map<?, ?> commandMap
 			, ModelMap model
 			) throws Exception {

    	// 0. Spring Security 사용자권한 처리
    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
    		model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
        	return "egovframework/com/uat/uia/EgovLoginUsr";
    	}

		String sCmd = commandMap.get("cmd") == null ? "" : (String)commandMap.get("cmd");
    	if (sCmd.equals("Confirm")) {

	    	// 로그인VO에서  사용자 정보 가져오기
	    	LoginVO	loginVO = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
	    	String	uniqId = loginVO == null ? "" : EgovStringUtil.isNullToString(loginVO.getUniqId());

	    	systemCntc.setLastUpdusrId(uniqId);
    		systemCntcService.confirmSystemCntc(systemCntc);
    	}

    	String selfUri = "/ssi/syi/scm/getConfirmSystemCntcDetail.do";
        model.addAttribute("selfUri", selfUri);

    	SystemCntc vo = systemCntcService.selectSystemCntcDetail(systemCntc);
		model.addAttribute("result", vo);

		// 연계기관 리스트박스 데이터
		CntcInsttVO searchCntcInsttVO;
		searchCntcInsttVO = new CntcInsttVO();
		searchCntcInsttVO.setRecordCountPerPage(999999);
		searchCntcInsttVO.setFirstIndex(0);
		searchCntcInsttVO.setSearchCondition("CodeList");
        List<?> cntcInsttList = cntcInsttService.selectCntcInsttList(searchCntcInsttVO);
        model.addAttribute("cntcInsttList", cntcInsttList);

		// 연계시스템 리스트박스 데이터
        CntcSystemVO searchCntcSystemVO;
        searchCntcSystemVO = new CntcSystemVO();
        searchCntcSystemVO.setRecordCountPerPage(999999);
        searchCntcSystemVO.setFirstIndex(0);
        searchCntcSystemVO.setSearchCondition("CodeList");
        searchCntcSystemVO.setInsttId(vo.getProvdInsttId());
        List<?> cntcProvdSystemList = cntcInsttService.selectCntcSystemList(searchCntcSystemVO);
        model.addAttribute("cntcProvdSystemList", cntcProvdSystemList);

        searchCntcSystemVO.setInsttId(vo.getRequstInsttId());
        List<?> cntcRequstSystemList = cntcInsttService.selectCntcSystemList(searchCntcSystemVO);
        model.addAttribute("cntcRequstSystemList", cntcRequstSystemList);

		// 연계서비스 리스트박스 데이터
        CntcServiceVO searchCntcServiceVO;
        searchCntcServiceVO = new CntcServiceVO();
        searchCntcServiceVO.setRecordCountPerPage(999999);
        searchCntcServiceVO.setFirstIndex(0);
        searchCntcServiceVO.setSearchCondition("CodeList");
        searchCntcServiceVO.setInsttId(vo.getProvdInsttId());
        searchCntcServiceVO.setSysId(vo.getProvdSysId());
        List<?> cntcProvdServiceList = cntcInsttService.selectCntcServiceList(searchCntcServiceVO);
        model.addAttribute("cntcProvdServiceList", cntcProvdServiceList);

		return "egovframework/com/ssi/syi/sim/EgovSystemCntcDetail";
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