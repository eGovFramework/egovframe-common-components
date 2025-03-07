package egovframework.com.cop.smt.sdm.web;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.egovframe.rte.fdl.property.EgovPropertyService;
import org.egovframe.rte.psl.dataaccess.util.EgovMap;
import org.egovframe.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springmodules.validation.commons.DefaultBeanValidator;

import egovframework.com.cmm.ComDefaultCodeVO;
import egovframework.com.cmm.ComDefaultVO;
import egovframework.com.cmm.EgovMessageSource;
import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.annotation.IncludedInfo;
import egovframework.com.cmm.service.CmmnDetailCode;
import egovframework.com.cmm.service.EgovCmmUseService;
import egovframework.com.cmm.service.EgovFileMngService;
import egovframework.com.cmm.service.EgovFileMngUtil;
import egovframework.com.cmm.service.FileVO;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.com.cop.smt.sdm.service.DeptSchdulManageVO;
import egovframework.com.cop.smt.sdm.service.EgovDeptSchdulManageService;
import egovframework.com.utl.fcc.service.EgovStringUtil;
/**
 * 부서일정관리를 처리하는 Controller Class 구현
 * @author 공통서비스 장동한
 * @since 2009.04.10
 * @version 1.0
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *    2009.04.10  장동한          최초 생성
 *    2011.8.26	정진오			IncludedInfo annotation 추가
 *	  2011.9.1	정진오			10월 주별 달력 테이블에 날짜가 이상하게 나와서 수정함
 *
 * Copyright (C) 2009 by MOPAS  All right reserved.
 * </pre>
 */


@Controller
public class EgovDeptSchdulManageController {

	private static final Logger LOGGER = LoggerFactory.getLogger(EgovDeptSchdulManageController.class);

	@Autowired
	private DefaultBeanValidator beanValidator;

	/** EgovMessageSource */
    @Resource(name="egovMessageSource")
    EgovMessageSource egovMessageSource;

	@Resource(name = "egovDeptSchdulManageService")
	private EgovDeptSchdulManageService egovDeptSchdulManageService;

	@Resource(name="EgovCmmUseService")
	private EgovCmmUseService cmmUseService;

    /** EgovPropertyService */
    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;

	// 첨부파일 관련
	@Resource(name="EgovFileMngService")
	private EgovFileMngService fileMngService;

	@Resource(name="EgovFileMngUtil")
	private EgovFileMngUtil fileUtil;

    /**
     * 개별 배포시 메인메뉴를 조회한다.
     * @param model
     * @return	"/cop/smt/sdm/EgovMain"
     * @throws Exception
     */
    @RequestMapping(value="/cop/smt/EgovMain.do")
    public String egovMain(ModelMap model) throws Exception {
    	return "egovframework/com/cop/smt/sdm/EgovMain";
    }

    /**
     * 메뉴를 조회한다.
     * @param model
     * @return	"/cop/smt/sdm/EgovLeft"
     * @throws Exception
     */
    @RequestMapping(value="/cop/smt/EgovLeft.do")
    public String egovLeft(ModelMap model) throws Exception {
    	return "egovframework/com/cop/smt/sdm/EgovLeft";
    }
    
    
    
    /**
     * 부서목록을 조회한다.
     * @param searchVO
     * @param commandMap
     * @param model
     * @return "uss/olp/mgt/EgovDeptSchdulManageAuthorGroupPopup"
     * @throws Exception
     */
    @RequestMapping(value="/cop/smt/sdm/EgovDeptSchdulManageAuthorGroupPopup.do")
	public String egovMeetingManageLisAuthorGroupPopupPost (
			@ModelAttribute("searchVO") ComDefaultVO searchVO,
			@RequestParam Map<?, ?> commandMap,
    		ModelMap model)
    throws Exception {

    	 List<EgovMap> resultList = egovDeptSchdulManageService.selectDeptSchdulManageAuthorGroupPopup(searchVO);
         model.addAttribute("resultList", resultList);

    	return "egovframework/com/cop/smt/sdm/EgovDeptSchdulManageAuthorGroupPopup";
    }

    /**
     * 회원목록을 조회한다.
     * @param searchVO
     * @param commandMap
     * @param model
     * @return  "/uss/olp/mgt/EgovMeetingManageLisEmpLyrPopup"
     * @throws Exception
     */
    @RequestMapping(value="/cop/smt/sdm/EgovDeptSchdulManageEmpLyrPopup.do")
	public String egovMeetingManageLisEmpLyrPopupPost (
			@ModelAttribute("searchVO") ComDefaultVO searchVO,
			@RequestParam Map<?, ?> commandMap,
    		ModelMap model)
    throws Exception {

    	 List<EgovMap> resultList = egovDeptSchdulManageService.selectDeptSchdulManageEmpLyrPopup(searchVO);
         model.addAttribute("resultList", resultList);

    	return "egovframework/com/cop/smt/sdm/EgovDeptSchdulManageEmpLyrPopup";
    }
    

	/**
	 * 메인페이지/부서일정관리조회
	 * @param commandMap
	 * @param model
	 * @return "egovframework/com/cop/smt/sim/EgovIndvdlSchdulManageMainList"
	 * @throws Exception
	 */
	@RequestMapping(value="/cop/smt/sdm/EgovDeptSchdulManageMainList.do")
	public String egovDeptSchdulManageList(
			@RequestParam Map<?, ?> commandMap,
    		ModelMap model)
    throws Exception {

    	// 0. Spring Security 사용자권한 처리
    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
    		model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
        	return "redirect:/uat/uia/egovLoginUsr.do";
    	}

		//로그인 객체 선언
		LoginVO loginVO = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
		if(loginVO == null){ loginVO = new LoginVO();}

		Map<String, String> hmParam = new HashMap<>();

		hmParam.put("uniqId", (String)loginVO.getUniqId());

		List<EgovMap> reusltList = egovDeptSchdulManageService.selectDeptSchdulManageMainList(hmParam);

		 model.addAttribute("resultList", reusltList);

    	return "egovframework/com/cop/smt/sdm/EgovDeptSchdulManageMainList";

	}

	/**
	 * 일지관리 목록을 조회한다.
	 * @param searchVO
	 * @param model
	 * @return "egovframework/com/cop/smt/dsm/EgovDiaryManageList"
	 * @throws Exception
	 */
	@RequestMapping(value="/cop/smt/sdm/EgovDeptSchdulManageListPopup.do")
	public String egovDeptSchdulManageListPopup(
			@ModelAttribute("searchVO") ComDefaultVO searchVO,
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

		List<EgovMap> resultList = egovDeptSchdulManageService.selectDeptSchdulManageList(searchVO);
        model.addAttribute("resultList", resultList);

        int totCnt = egovDeptSchdulManageService.selectDeptSchdulManageListCnt(searchVO);
		paginationInfo.setTotalRecordCount(totCnt);
        model.addAttribute("paginationInfo", paginationInfo);

		return "egovframework/com/cop/smt/sdm/EgovDeptSchdulManageListPopup";
	}

	/**
	 *  부서일정(일별) 목록을 조회한다.
	 * @param searchVO
	 * @param commandMap
	 * @param deptSchdulManageVO
	 * @param model
	 * @return "egovframework/com/cop/smt/sdm/EgovDeptSchdulManageDailyList"
	 * @throws Exception
	 */
	@RequestMapping(value="/cop/smt/sdm/EgovDeptSchdulManageDailyList.do")
	public String egovDeptSchdulManageDailyList(
			@ModelAttribute("searchVO") ComDefaultVO searchVO,
			@RequestParam Map<String, String> commandMap,
			DeptSchdulManageVO deptSchdulManageVO,
    		ModelMap model)
    throws Exception {

		//검색 유지
        model.addAttribute("searchKeyword", commandMap.get("searchKeyword") == null ? "" : (String)commandMap.get("searchKeyword"));
        model.addAttribute("searchCondition", commandMap.get("searchCondition") == null ? "" : (String)commandMap.get("searchCondition"));

		//공통코드 부서일정종류
		ComDefaultCodeVO voComCode = new ComDefaultCodeVO();
	   	voComCode = new ComDefaultCodeVO();
    	voComCode.setCodeId("COM030");
    	List<CmmnDetailCode> listComCode = cmmUseService.selectCmmCodeDetail(voComCode);
    	model.addAttribute("schdulSe", listComCode);

		/* *****************************************************************
    	// 캘런더 설정 로직
		****************************************************************** */
        Calendar calNow = Calendar.getInstance();

		String strYear = (String)commandMap.get("year");
		String strMonth = (String)commandMap.get("month");
		String strDay =( String)commandMap.get("day");
		String strSearchDay = "";
		int iNowYear = calNow.get(Calendar.YEAR);
		int iNowMonth = calNow.get(Calendar.MONTH);
		int iNowDay = calNow.get(Calendar.DATE);

		if(strYear != null)
		{
		  iNowYear = Integer.parseInt(strYear);
		  iNowMonth = Integer.parseInt(strMonth);
		  iNowDay = Integer.parseInt(strDay);
		}

		strSearchDay = Integer.toString(iNowYear);
		strSearchDay += dateTypeIntForString(iNowMonth+1);
		strSearchDay += dateTypeIntForString(iNowDay);

		commandMap.put("searchMode", "DAILY");
		commandMap.put("searchDay", strSearchDay);

		model.addAttribute("year", iNowYear);
		model.addAttribute("month", iNowMonth);
		model.addAttribute("day", iNowDay);

		List<EgovMap> resultList = egovDeptSchdulManageService.selectDeptSchdulManageRetrieve(commandMap);
        model.addAttribute("resultList", resultList);

		return "egovframework/com/cop/smt/sdm/EgovDeptSchdulManageDailyList";
	}

	/**
	 * 부서일정(주간별) 목록을 조회한다.
	 * @param searchVO
	 * @param commandMap
	 * @param deptSchdulManageVO
	 * @param model
	 * @return "egovframework/com/cop/smt/sdm/EgovDeptSchdulManageWeekList"
	 * @throws Exception
	 */
	@RequestMapping(value="/cop/smt/sdm/EgovDeptSchdulManageWeekList.do")
	public String egovDeptSchdulManageWeekList(
			@ModelAttribute("searchVO") ComDefaultVO searchVO,
			@RequestParam Map<String, String> commandMap,
			DeptSchdulManageVO deptSchdulManageVO,
    		ModelMap model)
    throws Exception {

		//일정구분 검색 유지
        model.addAttribute("searchKeyword", commandMap.get("searchKeyword") == null ? "" : (String)commandMap.get("searchKeyword"));
        model.addAttribute("searchCondition", commandMap.get("searchCondition") == null ? "" : (String)commandMap.get("searchCondition"));

		//공통코드 부서일정종류
		ComDefaultCodeVO voComCode = new ComDefaultCodeVO();
	   	voComCode = new ComDefaultCodeVO();
    	voComCode.setCodeId("COM030");
    	List<CmmnDetailCode> listComCode = cmmUseService.selectCmmCodeDetail(voComCode);
    	model.addAttribute("schdulSe", listComCode);

		/* *****************************************************************
    	// 캘런더 설정 로직
		****************************************************************** */
        Calendar calNow = Calendar.getInstance();
        Calendar calBefore = Calendar.getInstance();
        Calendar calNext = Calendar.getInstance();


		String strYear = (String)commandMap.get("year");
		String strMonth = (String)commandMap.get("month");
		String strWeek =( String)commandMap.get("week");

		int iNowYear = calNow.get(Calendar.YEAR);
		int iNowMonth = calNow.get(Calendar.MONTH);
		int iNowDate = calNow.get(Calendar.DATE);
		int iNowWeek = 0;

		if(strYear != null)
		{
		  iNowYear = Integer.parseInt(strYear);
		  iNowMonth = Integer.parseInt(strMonth);
		  iNowWeek = Integer.parseInt(strWeek);
		}

		//연도/월 셋팅
		calNow.set(iNowYear, iNowMonth, 1);
		calBefore.set(iNowYear, iNowMonth, 1);
		calNext.set(iNowYear, iNowMonth, 1);

		calBefore.add(Calendar.MONTH, -1);
		calNext.add(Calendar.MONTH, +1);

//		int startDay = calNow.getMinimum(Calendar.DATE);
		int endDay = calNow.getActualMaximum(Calendar.DAY_OF_MONTH);
		int startWeek = calNow.get(Calendar.DAY_OF_WEEK);


		List<List<String>> listWeekGrop = new ArrayList<>();
		List<String> listWeekDate = new ArrayList<>();

		String sUseDate = "";

		calBefore.add(Calendar.DATE , calBefore.getActualMaximum(Calendar.DAY_OF_MONTH) - (startWeek-1));
		for(int i = 1; i < startWeek ; i++ )
		{
			sUseDate = Integer.toString(calBefore.get(Calendar.YEAR));
			sUseDate += dateTypeIntForString(calBefore.get(Calendar.MONTH)+1);
			sUseDate += dateTypeIntForString(calBefore.get(Calendar.DATE));


			listWeekDate.add(sUseDate);
			calBefore.add(Calendar.DATE, +1);
		}

		int iBetweenCount = startWeek;

		// 주별로 자른다. BETWEEN 구하기
		for(int i=1; i <= endDay; i++)
		{
			sUseDate = Integer.toString(iNowYear);
			//sUseDate += Integer.toString(iNowMonth).length() == 1 ? "0" + Integer.toString(iNowMonth+1) : Integer.toString(iNowMonth+1);
			// (2011.9.1 수정사항) 10월의 주별 날짜가 이상하게 나와서 LeaderSchedule 보고 수정함. 위의 코드가 원래 코드
			sUseDate += Integer.toString(iNowMonth+1).length() == 1 ? "0" + Integer.toString(iNowMonth+1) : Integer.toString(iNowMonth+1);
			sUseDate += Integer.toString(i).length() == 1 ? "0" + Integer.toString(i) : Integer.toString(i);



			listWeekDate.add(sUseDate);

			if( iBetweenCount % 7 == 0){
				listWeekGrop.add(listWeekDate);
				listWeekDate = new ArrayList<>();

				if(strYear == null &&  i < iNowDate){
					iNowWeek++;

				}
			}

			//미지막 7일 자동계산
			if(i == endDay){

				for(int j=listWeekDate.size(); j < 7;j++){
					String sUseNextDate = Integer.toString(calNext.get(Calendar.YEAR));
					sUseNextDate += dateTypeIntForString(calNext.get(Calendar.MONTH)+1);
					sUseNextDate += dateTypeIntForString(calNext.get(Calendar.DATE));
					listWeekDate.add(sUseNextDate);
					calNext.add(Calendar.DATE, +1);
				}

				listWeekGrop.add(listWeekDate);
			}

			iBetweenCount++;
		}

		model.addAttribute("year", iNowYear);
		model.addAttribute("month", iNowMonth);
		model.addAttribute("week", iNowWeek);


		model.addAttribute("listWeekGrop", listWeekGrop);

		List<String> listWeek = listWeekGrop.get(iNowWeek);
		commandMap.put("searchMode", "WEEK");
		commandMap.put("schdulBgnde", (String)listWeek.get(0));
		commandMap.put("schdulEndde", (String)listWeek.get(listWeek.size()-1));

		List<?> resultList = egovDeptSchdulManageService.selectDeptSchdulManageRetrieve(commandMap);
        model.addAttribute("resultList", resultList);

		return "egovframework/com/cop/smt/sdm/EgovDeptSchdulManageWeekList";
	}

	/**
	 *  부서일정(월별) 목록을 조회한다.
	 * @param searchVO
	 * @param commandMap
	 * @param deptSchdulManageVO
	 * @param model
	 * @return "egovframework/com/cop/smt/sdm/EgovDeptSchdulManageMonthList"
	 * @throws Exception
	 */
	@RequestMapping(value="/cop/smt/sdm/EgovDeptSchdulManageMonthList.do")
	public String egovDeptSchdulManageMonthList(
			@ModelAttribute("searchVO") ComDefaultVO searchVO,
			@RequestParam Map<String, String> commandMap,
			DeptSchdulManageVO deptSchdulManageVO,
    		ModelMap model)
    throws Exception {

		//일정구분 검색 유지
        model.addAttribute("searchKeyword", commandMap.get("searchKeyword") == null ? "" : (String)commandMap.get("searchKeyword"));
        model.addAttribute("searchCondition", commandMap.get("searchCondition") == null ? "" : (String)commandMap.get("searchCondition"));

		java.util.Calendar cal = java.util.Calendar.getInstance();

		String sYear = (String)commandMap.get("year");
		String sMonth = (String)commandMap.get("month");

		int iYear = cal.get(java.util.Calendar.YEAR);
		int iMonth = cal.get(java.util.Calendar.MONTH);
//		int iDate = cal.get(java.util.Calendar.DATE);

		//검색 설정
		String sSearchDate = "";
		if(sYear == null || sMonth == null){
			sSearchDate += Integer.toString(iYear);
			sSearchDate += Integer.toString(iMonth+1).length() == 1 ? "0" + Integer.toString(iMonth+1) : Integer.toString(iMonth+1);
		}else{
			iYear = Integer.parseInt(sYear);
			iMonth = Integer.parseInt(sMonth);
			sSearchDate += sYear;
			sSearchDate += Integer.toString(iMonth+1).length() == 1 ? "0" + Integer.toString(iMonth+1) :Integer.toString(iMonth+1);
		}



		commandMap.put("searchMonth", sSearchDate);

		//공통코드 부서일정종류
		ComDefaultCodeVO voComCode = new ComDefaultCodeVO();
	   	voComCode = new ComDefaultCodeVO();
    	voComCode.setCodeId("COM030");
    	List<CmmnDetailCode> listComCode = cmmUseService.selectCmmCodeDetail(voComCode);
    	model.addAttribute("schdulSe", listComCode);

    	commandMap.put("searchMode", "MONTH");
        List<EgovMap> resultList = egovDeptSchdulManageService.selectDeptSchdulManageRetrieve(commandMap);
        model.addAttribute("resultList", resultList);

		return "egovframework/com/cop/smt/sdm/EgovDeptSchdulManageMonthList";
	}

	/**
	 * 부서일정 목록을 조회한다.
	 * @return "egovframework/com/cop/smt/sdm/EgovDeptSchdulManageList"
	 */
	@IncludedInfo(name="부서일정관리", order = 320 ,gid = 40)
	@RequestMapping(value="/cop/smt/sdm/EgovDeptSchdulManageList.do")
	public String egovDeptSchdulManageList() {
		return "egovframework/com/cop/smt/sdm/EgovDeptSchdulManageList";
	}

	/**
	 *  부서일정 목록을 상세조회 조회한다.
	 * @param searchVO
	 * @param deptSchdulManageVO
	 * @param commandMap
	 * @param model
	 * @return "egovframework/com/cop/smt/sdm/EgovDeptSchdulManageDetail"
	 * @throws Exception
	 */
	@RequestMapping(value="/cop/smt/sdm/EgovDeptSchdulManageDetail.do")
	public String egovDeptSchdulManageDetail(
			@ModelAttribute("searchVO") ComDefaultVO searchVO,
			DeptSchdulManageVO deptSchdulManageVO,
			@RequestParam Map<String, String> commandMap,
    		ModelMap model)
    throws Exception {

		String sLocationUrl = "egovframework/com/cop/smt/sdm/EgovDeptSchdulManageDetail";

		String sCmd = commandMap.get("cmd");

		if("del".equals(sCmd)){
			egovDeptSchdulManageService.deleteDeptSchdulManage(deptSchdulManageVO);
			sLocationUrl = "redirect:/cop/smt/sdm/EgovDeptSchdulManageList.do";
		}else{

	     	//공통코드  중요도 조회
	    	ComDefaultCodeVO voComCode = new ComDefaultCodeVO();
	    	voComCode.setCodeId("COM019");
	    	List<CmmnDetailCode> listComCode = cmmUseService.selectCmmCodeDetail(voComCode);
	    	model.addAttribute("schdulIpcrCode", listComCode);
	    	//공통코드  일정구분 조회
	    	voComCode = new ComDefaultCodeVO();
	    	voComCode.setCodeId("COM030");
	    	listComCode = cmmUseService.selectCmmCodeDetail(voComCode);
	    	model.addAttribute("schdulSe", listComCode);
	    	//공통코드  반복구분 조회
	    	voComCode = new ComDefaultCodeVO();
	    	voComCode.setCodeId("COM031");
	    	listComCode = cmmUseService.selectCmmCodeDetail(voComCode);
	    	model.addAttribute("reptitSeCode", listComCode);

	    	List<EgovMap> resultList = egovDeptSchdulManageService.selectDeptSchdulManageDetail(deptSchdulManageVO);
	        model.addAttribute("resultList", resultList);
		}

		return sLocationUrl;
	}

	/**
	 * 부서일정를 수정 폼
	 * @param searchVO
	 * @param commandMap
	 * @param deptSchdulManageVO
	 * @param bindingResult
	 * @param model
	 * @return "egovframework/com/cop/smt/sdm/EgovDeptSchdulManageModify"
	 * @throws Exception
	 */
	@RequestMapping(value="/cop/smt/sdm/EgovDeptSchdulManageModify.do")
	public String deptSchdulManageModify(
			@ModelAttribute("searchVO") ComDefaultVO searchVO,
			DeptSchdulManageVO deptSchdulManageVO,
    		ModelMap model)
    throws Exception {

		String sLocationUrl = "egovframework/com/cop/smt/sdm/EgovDeptSchdulManageModify";

     	//공통코드  중요도 조회
    	ComDefaultCodeVO voComCode = new ComDefaultCodeVO();
    	voComCode.setCodeId("COM019");
    	List<CmmnDetailCode> listComCode = cmmUseService.selectCmmCodeDetail(voComCode);
    	model.addAttribute("schdulIpcrCode", listComCode);
    	//공통코드  일정구분 조회
    	voComCode = new ComDefaultCodeVO();
    	voComCode.setCodeId("COM030");
    	listComCode = cmmUseService.selectCmmCodeDetail(voComCode);
    	model.addAttribute("schdulSe", listComCode);
    	//공통코드  반복구분 조회
    	voComCode = new ComDefaultCodeVO();
    	voComCode.setCodeId("COM031");
    	listComCode = cmmUseService.selectCmmCodeDetail(voComCode);
    	model.addAttribute("reptitSeCode", listComCode);

    	//일정시작일자(시)
    	model.addAttribute("schdulBgndeHH", getTimeHH());
    	//일정시작일자(분)
    	model.addAttribute("schdulBgndeMM", getTimeMM());
    	//일정종료일자(시)
    	model.addAttribute("schdulEnddeHH", getTimeHH());
    	//일정정료일자(분)
    	model.addAttribute("schdulEnddeMM", getTimeMM());

    	DeptSchdulManageVO resultDeptSchdulManageVOReuslt = egovDeptSchdulManageService.selectDeptSchdulManageDetailVO(deptSchdulManageVO);

    	String sSchdulBgnde = resultDeptSchdulManageVOReuslt.getSchdulBgnde();
    	String sSchdulEndde = resultDeptSchdulManageVOReuslt.getSchdulEndde();

    	resultDeptSchdulManageVOReuslt.setSchdulBgndeYYYMMDD(sSchdulBgnde.substring(0, 4) +"-"+sSchdulBgnde.substring(4, 6)+"-"+sSchdulBgnde.substring(6, 8) );
    	resultDeptSchdulManageVOReuslt.setSchdulBgndeHH(sSchdulBgnde.substring(8, 10));
    	resultDeptSchdulManageVOReuslt.setSchdulBgndeMM(sSchdulBgnde.substring(10, 12));

       	resultDeptSchdulManageVOReuslt.setSchdulEnddeYYYMMDD(sSchdulEndde.substring(0, 4) +"-"+sSchdulEndde.substring(4, 6)+"-"+sSchdulEndde.substring(6, 8) );
    	resultDeptSchdulManageVOReuslt.setSchdulEnddeHH(sSchdulEndde.substring(8, 10));
    	resultDeptSchdulManageVOReuslt.setSchdulEnddeMM(sSchdulEndde.substring(10, 12));

    	model.addAttribute("deptSchdulManageVO", resultDeptSchdulManageVOReuslt);

		return sLocationUrl;
	}

	/**
	 * 부서일정를 수정 처리 한다.
	 * @param multiRequest
	 * @param commandMap
	 * @param deptSchdulManageVO
	 * @param bindingResult
	 * @param model
	 * @return "egovframework/com/cop/smt/sdm/EgovDeptSchdulManageModify"
	 * @throws Exception
	 */
	@RequestMapping(value="/cop/smt/sdm/EgovDeptSchdulManageModifyActor.do")
	public String deptSchdulManageModifyActor(
			final MultipartHttpServletRequest multiRequest,
			@RequestParam Map<String, String> commandMap,
			@ModelAttribute("deptSchdulManageVO") DeptSchdulManageVO deptSchdulManageVO,
			BindingResult bindingResult,
    		ModelMap model)
    throws Exception {

    	// 0. Spring Security 사용자권한 처리
    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
    		model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
        	return "redirect:/uat/uia/egovLoginUsr.do";
    	}

		//로그인 객체 선언
		LoginVO loginVO = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();

		String sLocationUrl = "egovframework/com/cop/smt/sdm/EgovDeptSchdulManageModify";

		String sCmd = commandMap.get("cmd");

		if("save".equals(sCmd)){
    		//서버  validate 체크
            beanValidator.validate(deptSchdulManageVO, bindingResult);
    		if(bindingResult.hasErrors()){

    	     	//공통코드  중요도 조회
    	    	ComDefaultCodeVO voComCode = new ComDefaultCodeVO();
    	    	voComCode.setCodeId("COM019");
    	    	List<CmmnDetailCode> listComCode = cmmUseService.selectCmmCodeDetail(voComCode);
    	    	model.addAttribute("schdulIpcrCode", listComCode);
    	    	//공통코드  일정구분 조회
    	    	voComCode = new ComDefaultCodeVO();
    	    	voComCode.setCodeId("COM030");
    	    	listComCode = cmmUseService.selectCmmCodeDetail(voComCode);
    	    	model.addAttribute("schdulSe", listComCode);
    	    	//공통코드  반복구분 조회
    	    	voComCode = new ComDefaultCodeVO();
    	    	voComCode.setCodeId("COM031");
    	    	listComCode = cmmUseService.selectCmmCodeDetail(voComCode);
    	    	model.addAttribute("reptitSeCode", listComCode);

    	    	//일정시작일자(시)
    	    	model.addAttribute("schdulBgndeHH", getTimeHH());
    	    	//일정시작일자(분)
    	    	model.addAttribute("schdulBgndeMM", getTimeMM());
    	    	//일정종료일자(시)
    	    	model.addAttribute("schdulEnddeHH", getTimeHH());
    	    	//일정정료일자(분)
    	    	model.addAttribute("schdulEnddeMM", getTimeMM());

    			return sLocationUrl;
    		}
    		/* *****************************************************************
        	// 아이디 설정
			****************************************************************** */
    		deptSchdulManageVO.setFrstRegisterId(loginVO.getUniqId());
    		deptSchdulManageVO.setLastUpdusrId(loginVO.getUniqId());
    		/* *****************************************************************
        	// 첨부파일 관련 ID 생성 start....
			****************************************************************** */
    		String _atchFileId = deptSchdulManageVO.getAtchFileId();


    		//final Map<String, MultipartFile> files = multiRequest.getFileMap();
    		final List<MultipartFile> files = multiRequest.getFiles("file_1");

    		if(!files.isEmpty()){
    			String atchFileAt = commandMap.get("atchFileAt");
    			if("N".equals(atchFileAt) || "".equals(atchFileAt)){
    				List<FileVO> _result = fileUtil.parseFileInf(files, "DSCH_", 0, _atchFileId, "");
    				_atchFileId = fileMngService.insertFileInfs(_result);

    				// 첨부파일 ID 셋팅
    				deptSchdulManageVO.setAtchFileId(_atchFileId);    	// 첨부파일 ID
    			}else{
    				FileVO fvo = new FileVO();
    				fvo.setAtchFileId(_atchFileId);
    				int _cnt = fileMngService.getMaxFileSN(fvo);
    				List<FileVO> _result = fileUtil.parseFileInf(files, "DSCH_", _cnt, _atchFileId, "");
    				fileMngService.updateFileInfs(_result);
    			}
    		}

    		/* *****************************************************************
        	// 일정관리정보 업데이트 처리
			****************************************************************** */
        	egovDeptSchdulManageService.updateDeptSchdulManage(deptSchdulManageVO);
        	sLocationUrl = "redirect:/cop/smt/sdm/EgovDeptSchdulManageList.do";
		}

		return sLocationUrl;
	}

	/**
	 *  부서일정를 등록한다. / 등록 초기페이지
	 * @param searchVO
	 * @param deptSchdulManageVO
	 * @param model
	 * @return "egovframework/com/cop/smt/sdm/EgovDeptSchdulManageRegist"
	 * @throws Exception
	 */
	@RequestMapping(value="/cop/smt/sdm/EgovDeptSchdulManageRegist.do")
	public String deptSchdulManageRegist(
			@ModelAttribute("searchVO") ComDefaultVO searchVO,
			@ModelAttribute("deptSchdulManageVO") DeptSchdulManageVO deptSchdulManageVO,
    		ModelMap model)
    throws Exception {

		String sLocationUrl = "egovframework/com/cop/smt/sdm/EgovDeptSchdulManageRegist";

    	// 0. Spring Security 사용자권한 처리
    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
    		model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
        	return "redirect:/uat/uia/egovLoginUsr.do";
    	}

     	//공통코드  중요도 조회
    	ComDefaultCodeVO voComCode = new ComDefaultCodeVO();
    	voComCode.setCodeId("COM019");
    	List<CmmnDetailCode> listComCode = cmmUseService.selectCmmCodeDetail(voComCode);
    	model.addAttribute("schdulIpcrCode", listComCode);
    	//공통코드  일정구분 조회
    	voComCode = new ComDefaultCodeVO();
    	voComCode.setCodeId("COM030");
    	listComCode = cmmUseService.selectCmmCodeDetail(voComCode);
    	model.addAttribute("schdulSe", listComCode);
    	//공통코드  반복구분 조회
    	voComCode = new ComDefaultCodeVO();
    	voComCode.setCodeId("COM031");
    	listComCode = cmmUseService.selectCmmCodeDetail(voComCode);
    	model.addAttribute("reptitSeCode", listComCode);

    	//일정시작일자(시)
    	model.addAttribute("schdulBgndeHH", getTimeHH());
    	//일정시작일자(분)
    	model.addAttribute("schdulBgndeMM", getTimeMM());
    	//일정종료일자(시)
    	model.addAttribute("schdulEnddeHH", getTimeHH());
    	//일정정료일자(분)
    	model.addAttribute("schdulEnddeMM", getTimeMM());


    	return sLocationUrl;

	}

	/**
	 *  부서일정를 등록한다. / 등록 처리 한다.
	 * @param multiRequest
	 * @param searchVO
	 * @param commandMap
	 * @param deptSchdulManageVO
	 * @param bindingResult
	 * @param model
	 * @return  "/cop/smt/sdm/EgovDeptSchdulManageRegist"
	 * @throws Exception
	 */
	@RequestMapping(value="/cop/smt/sdm/EgovDeptSchdulManageRegistActor.do")
	public String deptSchdulManageRegistActor(
			final MultipartHttpServletRequest multiRequest,
			@ModelAttribute("searchVO") ComDefaultVO searchVO,
			@RequestParam Map<?, ?> commandMap,
			@ModelAttribute("deptSchdulManageVO") DeptSchdulManageVO deptSchdulManageVO,
			BindingResult bindingResult,
    		ModelMap model)
    throws Exception {
    	// 0. Spring Security 사용자권한 처리
    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
    		model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
        	return "redirect:/uat/uia/egovLoginUsr.do";
    	}

		//로그인 객체 선언
		LoginVO loginVO = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();

		String sLocationUrl = "egovframework/com/cop/smt/sdm/EgovDeptSchdulManageRegist";

		String sCmd = commandMap.get("cmd") == null ? "" : (String)commandMap.get("cmd");
		LOGGER.info("cmd => {}", sCmd);

        if(sCmd.equals("save")){
    		//서버  validate 체크
            beanValidator.validate(deptSchdulManageVO, bindingResult);
    		if(bindingResult.hasErrors()){

    			return sLocationUrl;
    		}

        	// 첨부파일 관련 첨부파일ID 생성
    		List<FileVO> _result = null;
    		String _atchFileId = "";

    		//final Map<String, MultipartFile> files = multiRequest.getFileMap();
    		final List<MultipartFile> files = multiRequest.getFiles("file_1");

    		if(!files.isEmpty()){
    		 _result = fileUtil.parseFileInf(files, "DSCH_", 0, "", "");
    		 _atchFileId = fileMngService.insertFileInfs(_result);  //파일이 생성되고나면 생성된 첨부파일 ID를 리턴한다.
    		}

        	// 리턴받은 첨부파일ID를 셋팅한다..
    		deptSchdulManageVO.setAtchFileId(_atchFileId);			// 첨부파일 ID

    		//아이디 설정
    		deptSchdulManageVO.setFrstRegisterId(loginVO == null ? "" : EgovStringUtil.isNullToString(loginVO.getUniqId()));
    		deptSchdulManageVO.setLastUpdusrId(loginVO == null ? "" : EgovStringUtil.isNullToString(loginVO.getUniqId()));

        	egovDeptSchdulManageService.insertDeptSchdulManage(deptSchdulManageVO);
        	sLocationUrl = "redirect:/cop/smt/sdm/EgovDeptSchdulManageList.do";
        }

        return sLocationUrl;


	}


	/**
	 * 시간을 LIST를 반환한다.
	 * @return  List
	 * @throws
	 */
	@SuppressWarnings("unused")
	private List<ComDefaultCodeVO> getTimeHH (){
    	ArrayList<ComDefaultCodeVO> listHH = new ArrayList<ComDefaultCodeVO>();
    	HashMap<?, ?> hmHHMM;
    	for(int i=0;i <= 24; i++){
    		String sHH = "";
    		String strI = String.valueOf(i);
    		if(i<10){
    			sHH = "0" + strI;
    		}else{
    			sHH = strI;
    		}

    		ComDefaultCodeVO codeVO = new ComDefaultCodeVO();
    		codeVO.setCode(sHH);
    		codeVO.setCodeNm(sHH);

    		listHH.add(codeVO);
    	}

    	return listHH;
	}

	/**
	 * 분을 LIST를 반환한다.
	 * @return  List
	 * @throws
	 */
	@SuppressWarnings({ "rawtypes", "unused", "unchecked" })
	private List getTimeMM (){
    	ArrayList listMM = new ArrayList();
    	HashMap hmHHMM;
    	for(int i=0;i <= 60; i++){

    		String sMM = "";
    		String strI = String.valueOf(i);
    		if(i<10){
    			sMM = "0" + strI;
    		}else{
    			sMM = strI;
    		}

    		ComDefaultCodeVO codeVO = new ComDefaultCodeVO();
    		codeVO.setCode(sMM);
    		codeVO.setCodeNm(sMM);

    		listMM.add(codeVO);
    	}
    	return listMM;
	}

	/**
	 * 0을 붙여 반환
	 * @return  String
	 * @throws
	 */
    public String dateTypeIntForString(int iInput){
		String sOutput = "";
		if(Integer.toString(iInput).length() == 1){
			sOutput = "0" + Integer.toString(iInput);
		}else{
			sOutput = Integer.toString(iInput);
		}

       return sOutput;
    }

}


