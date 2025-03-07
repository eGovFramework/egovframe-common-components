package egovframework.com.cop.smt.lsm.web;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
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
import org.springmodules.validation.commons.DefaultBeanValidator;

import egovframework.com.cmm.ComDefaultCodeVO;
import egovframework.com.cmm.EgovMessageSource;
import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.annotation.IncludedInfo;
import egovframework.com.cmm.service.CmmnDetailCode;
import egovframework.com.cmm.service.EgovCmmUseService;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.com.cop.smt.lsm.service.EgovLeaderSchdulService;
import egovframework.com.cop.smt.lsm.service.EmplyrVO;
import egovframework.com.cop.smt.lsm.service.LeaderSchdulVO;
import egovframework.com.cop.smt.lsm.service.LeaderSttus;
import egovframework.com.cop.smt.lsm.service.LeaderSttusVO;
import egovframework.com.utl.fcc.service.EgovStringUtil;

/**
 * 개요
 * - 간부일정에 대한 controller 클래스를 정의한다.
 * 
 * 상세내용
 * - 간부일정에 대한 등록, 수정, 삭제, 조회기능을 제공한다.
 * - 간부일정의 조회기능은 목록조회, 상세조회로 구분된다.
 * @author 장철호
 * @version 1.0
 * @created 28-6-2010 오전 10:59:05
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일             수정자            수정내용
 *  ----------   --------   ---------------------------
 *  2010.06.28   장철호            최초 생성
 *  2011.08.26   정진오            IncludedInfo annotation 추가
 *  2020.11.02   신용호            KISA 보안약점 조치 - 널(null) 값 체크
 *  2024.10.29	LeeBaekHaeng	불필요 형변환 정리
 *
 * </pre>
 */


@Controller
public class EgovLeaderSchdulController {
	
	@Resource(name="EgovLeaderSchdulService")
    protected EgovLeaderSchdulService leaderSchdulService;
	
	@Resource(name="EgovCmmUseService")
	private EgovCmmUseService cmmUseService;
	
	@Resource(name="propertiesService")
    protected EgovPropertyService propertyService;
    
    @Resource(name="egovMessageSource")
    EgovMessageSource egovMessageSource;
    
    @Autowired
    private DefaultBeanValidator beanValidator;
    
    /**
	 * 사용자 정보에 대한 팝업 목록을 조회한다.
	 * @param EmplyrVO
	 * @return  String
	 * 
	 * @param emplyrVO
	 */
	@RequestMapping("/cop/smt/lsm/selectEmplyrListPopup.do")
	public String selectEmplyrListPopup(@ModelAttribute("searchVO") EmplyrVO emplyrVO, ModelMap model) throws Exception{
		return "egovframework/com/cop/smt/lsm/EgovEmplyrListPopup";
	}
	
	/**
	 * 사용자 정보에 대한 목록을 조회한다.
	 * @param EmplyrVO
	 * @return  String
	 * 
	 * @param emplyrVO
	 */
	@RequestMapping("/cop/smt/lsm/selectEmplyrList.do")
	public String selectEmplyrList(@ModelAttribute("searchVO") EmplyrVO emplyrVO, ModelMap model) throws Exception{
		//LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
		
		//emplyrVO.setUniqId(user.getUniqId());
		
		emplyrVO.setPageUnit(propertyService.getInt("pageUnit"));
		emplyrVO.setPageSize(propertyService.getInt("pageSize"));

		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(emplyrVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(emplyrVO.getPageUnit());
		paginationInfo.setPageSize(emplyrVO.getPageSize());

		emplyrVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		emplyrVO.setLastIndex(paginationInfo.getLastRecordIndex());
		emplyrVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

		Map<String, Object> map = leaderSchdulService.selectEmplyrList(emplyrVO);
		int totCnt = Integer.parseInt((String)map.get("resultCnt"));
		paginationInfo.setTotalRecordCount(totCnt);

		model.addAttribute("resultList", map.get("resultList"));
		model.addAttribute("resultCnt", map.get("resultCnt"));
		model.addAttribute("paginationInfo", paginationInfo);

		return "egovframework/com/cop/smt/lsm/EgovEmplyrList";
	}
	
    /**
	 * 간부일정 정보에 대한 목록을 조회한다.
	* @param LeaderSchdulVO
	 * @return  String
	 * 
	 * @param leaderSchdulVO
	 */
	@IncludedInfo(name="간부일정관리", order = 390 ,gid = 40)
	@RequestMapping(value="/cop/smt/lsm/usr/selectLeaderSchdulList.do")
	public String selectLeaderSchdulList(@ModelAttribute("leaderSchdulVO") LeaderSchdulVO leaderSchdulVO, ModelMap model) throws Exception{
    	
		model.addAttribute("leaderSchdulVO", leaderSchdulVO);

		return "egovframework/com/cop/smt/lsm/EgovLeaderSchdulList"; 
	}
	
	/**
	 * 월별 간부일정 정보에 대한 목록을 조회한다.
	 * @param LeaderSchdulVO
	 * @return  String
	 * 
	 * @param leaderSchdulVO
	 */
	@RequestMapping(value="/cop/smt/lsm/usr/selectLeaderSchdulMonthList.do")
	public String selectLeaderSchdulMonthList(@ModelAttribute("searchVO") LeaderSchdulVO leaderSchdulVO, ModelMap model) throws Exception{
		//일정구분 검색 유지
		//if(leaderSchdulVO.getSearchKeywordEx() != null){
		//	leaderSchdulVO.setSearchKeywordEx(new String(leaderSchdulVO.getSearchKeywordEx().getBytes("8859_1"), "UTF-8"));
		//}
        model.addAttribute("searchKeyword", leaderSchdulVO.getSearchKeyword() == null ? "" : (String)leaderSchdulVO.getSearchKeyword());
        model.addAttribute("searchKeywordEx", leaderSchdulVO.getSearchKeywordEx() == null ? "" : (String)leaderSchdulVO.getSearchKeywordEx());
        model.addAttribute("searchCondition", leaderSchdulVO.getSearchCondition() == null ? "" : (String)leaderSchdulVO.getSearchCondition());
        
        java.util.Calendar cal = java.util.Calendar.getInstance();
		
		String sYear = leaderSchdulVO.getYear();
		String sMonth = leaderSchdulVO.getMonth();

		int iYear = cal.get(java.util.Calendar.YEAR);
		int iMonth = cal.get(java.util.Calendar.MONTH);
		
		//검색 설정
		String sSearchMonth = "";
		if(sYear == null || sMonth == null || sYear.equals("") || sMonth.equals("")){
			sSearchMonth += Integer.toString(iYear);
			sSearchMonth += Integer.toString(iMonth+1).length() == 1 ? "0" + Integer.toString(iMonth+1) : Integer.toString(iMonth+1); 
		}else{
			iYear = Integer.parseInt(sYear); 
			iMonth = Integer.parseInt(sMonth);
			sSearchMonth += sYear;
			sSearchMonth += Integer.toString(iMonth+1).length() == 1 ? "0" + Integer.toString(iMonth+1) :Integer.toString(iMonth+1); 
		}
		
		leaderSchdulVO.setSearchMode("MONTH");
		leaderSchdulVO.setSearchMonth(sSearchMonth);

		/*
		 * 공통코드 
		 * 간부일정구분
		 */
		ComDefaultCodeVO voComCode = new ComDefaultCodeVO();
	   	voComCode = new ComDefaultCodeVO();
    	voComCode.setCodeId("COM057");
    	List<CmmnDetailCode> listComCode = cmmUseService.selectCmmCodeDetail(voComCode);
    	model.addAttribute("schdulSe", listComCode);
    	
        List<LeaderSchdulVO> resultList = leaderSchdulService.selectLeaderSchdulList(leaderSchdulVO);
        model.addAttribute("resultList", resultList);

		return "egovframework/com/cop/smt/lsm/EgovLeaderSchdulMonthList"; 
	}

	/**
	 * 주별 간부일정 정보에 대한 목록을 조회한다.
	 * @param LeaderSchdulVO
	 * @return  String
	 * 
	 * @param leaderSchdulVO
	 */
	@RequestMapping(value="/cop/smt/lsm/usr/selectLeaderSchdulWeekList.do")
	public String selectLeaderSchdulWeekList(@ModelAttribute("searchVO") LeaderSchdulVO leaderSchdulVO, ModelMap model) throws Exception{
		//일정구분 검색 유지
		//if(leaderSchdulVO.getSearchKeywordEx() != null){
		//	leaderSchdulVO.setSearchKeywordEx(new String(leaderSchdulVO.getSearchKeywordEx().getBytes("8859_1"), "UTF-8"));
		//}
        model.addAttribute("searchKeyword", leaderSchdulVO.getSearchKeyword() == null ? "" : (String)leaderSchdulVO.getSearchKeyword());
        model.addAttribute("searchKeywordEx", leaderSchdulVO.getSearchKeywordEx() == null ? "" : (String)leaderSchdulVO.getSearchKeywordEx());
        model.addAttribute("searchCondition", leaderSchdulVO.getSearchCondition() == null ? "" : (String)leaderSchdulVO.getSearchCondition());
        
		/*
		 * 공통코드 
		 * 간부일정구분
		 */
		ComDefaultCodeVO voComCode = new ComDefaultCodeVO();
	   	voComCode = new ComDefaultCodeVO();
    	voComCode.setCodeId("COM057");
    	List<CmmnDetailCode> listComCode = cmmUseService.selectCmmCodeDetail(voComCode);
    	model.addAttribute("schdulSe", listComCode);

		/* *****************************************************************
    	// 캘런더 설정 로직
		****************************************************************** */
        Calendar calNow = Calendar.getInstance();
        Calendar calBefore = Calendar.getInstance();
        Calendar calNext = Calendar.getInstance();
        
		
		String strYear = leaderSchdulVO.getYear();
		String strMonth = leaderSchdulVO.getMonth();
		String strWeek =leaderSchdulVO.getWeek();

		int iNowYear = calNow.get(Calendar.YEAR);
		int iNowMonth = calNow.get(Calendar.MONTH);
		int iNowDate = calNow.get(Calendar.DATE);
		int iNowWeek = 0;

		if(strYear != null && !strYear.equals(""))
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
		
		leaderSchdulVO.setSearchMode("WEEK");
		leaderSchdulVO.setSearchBgnDe(listWeek.get(0));
		leaderSchdulVO.setSearchEndDe(listWeek.get(listWeek.size()-1));
		
		List<LeaderSchdulVO> resultList = leaderSchdulService.selectLeaderSchdulList(leaderSchdulVO);
        model.addAttribute("resultList", resultList);
		
		return "egovframework/com/cop/smt/lsm/EgovLeaderSchdulWeekList";
	}

	/**
	 * 일별 간부일정 정보에 대한 목록을 조회한다.
	 * @param LeaderSchdulVO
	 * @return  String
	 * 
	 * @param leaderSchdulVO
	 */
	@RequestMapping(value="/cop/smt/lsm/usr/selectLeaderSchdulDailyList.do")
	public String selectLeaderSchdulDailyList(@ModelAttribute("searchVO") LeaderSchdulVO leaderSchdulVO, ModelMap model) throws Exception{
		//검색 유지 
		//if(leaderSchdulVO.getSearchKeywordEx() != null){
		//	leaderSchdulVO.setSearchKeywordEx(new String(leaderSchdulVO.getSearchKeywordEx().getBytes("8859_1"), "UTF-8"));
		//}
        model.addAttribute("searchKeyword", leaderSchdulVO.getSearchKeyword() == null ? "" : (String)leaderSchdulVO.getSearchKeyword());
        model.addAttribute("searchKeywordEx", leaderSchdulVO.getSearchKeywordEx() == null ? "" : (String)leaderSchdulVO.getSearchKeywordEx());
        model.addAttribute("searchCondition", leaderSchdulVO.getSearchCondition() == null ? "" : (String)leaderSchdulVO.getSearchCondition());
        
		/*
		 * 공통코드 
		 * 간부일정구분
		 */
		ComDefaultCodeVO voComCode = new ComDefaultCodeVO();
	   	voComCode = new ComDefaultCodeVO();
    	voComCode.setCodeId("COM057");
    	List<CmmnDetailCode> listComCode = cmmUseService.selectCmmCodeDetail(voComCode);
    	model.addAttribute("schdulSe", listComCode);

		/* *****************************************************************
    	// 캘런더 설정 로직
		****************************************************************** */
        Calendar calNow = Calendar.getInstance();
        
        String strYear = leaderSchdulVO.getYear();
		String strMonth = leaderSchdulVO.getMonth();
		String strDay =leaderSchdulVO.getDay();
		
		String strSearchDay = "";
		int iNowYear = calNow.get(Calendar.YEAR);
		int iNowMonth = calNow.get(Calendar.MONTH);
		int iNowDay = calNow.get(Calendar.DATE);

		if(strYear != null && !strYear.equals(""))
		{
		  iNowYear = Integer.parseInt(strYear);
		  iNowMonth = Integer.parseInt(strMonth);
		  iNowDay = Integer.parseInt(strDay);
		}
        
		strSearchDay = Integer.toString(iNowYear);
		strSearchDay += dateTypeIntForString(iNowMonth+1); 
		strSearchDay += dateTypeIntForString(iNowDay); 
		
		leaderSchdulVO.setSearchMode("DAILY");
		leaderSchdulVO.setSearchDay(strSearchDay);

		model.addAttribute("year", iNowYear);
		model.addAttribute("month", iNowMonth);
		model.addAttribute("day", iNowDay);
		
		List<LeaderSchdulVO> resultList = leaderSchdulService.selectLeaderSchdulList(leaderSchdulVO);
        model.addAttribute("resultList", resultList);
        
		return "egovframework/com/cop/smt/lsm/EgovLeaderSchdulDailyList"; 
	}

	/**
	 * 간부일정 정보를 조회한다.
	 * @param LeaderSchdulVO
	 * @return  String
	 * 
	 * @param leaderSchdulVO
	 */
	@RequestMapping(value="/cop/smt/lsm/usr/selectLeaderSchdul.do")
	public String selectLeaderSchdul(@ModelAttribute("leaderSchdulVO") LeaderSchdulVO leaderSchdulVO,  ModelMap model) throws Exception{
		/*
		 * 공통코드  
		 * 간부일정구분
		 */
    	ComDefaultCodeVO voComCode = new ComDefaultCodeVO();
    	voComCode.setCodeId("COM057");
    	List<CmmnDetailCode> listComCode = cmmUseService.selectCmmCodeDetail(voComCode);
    	model.addAttribute("schdulSe", listComCode);
    	
    	/*
    	 * 공통코드  
    	 * 반복구분 조회
    	 */
    	voComCode = new ComDefaultCodeVO();
    	voComCode.setCodeId("COM058");
    	listComCode = cmmUseService.selectCmmCodeDetail(voComCode);
    	model.addAttribute("reptitSeCode", listComCode);
    	
    	LeaderSchdulVO resultVO = leaderSchdulService.selectLeaderSchdul(leaderSchdulVO);
    	resultVO.setSearchMode(leaderSchdulVO.getSearchMode());
    	resultVO.setYear(leaderSchdulVO.getYear());
    	resultVO.setMonth(leaderSchdulVO.getMonth());
    	resultVO.setWeek(leaderSchdulVO.getWeek());
    	resultVO.setDay(leaderSchdulVO.getDay());
        model.addAttribute("leaderSchdulVO", resultVO);
        
		return "egovframework/com/cop/smt/lsm/EgovLeaderSchdulDetail"; 	
	}
	
	/**
	 * 간부일정 정보를 수정할수 있는 수정폼으로 이동한다.
	 * @param LeaderSchdulVO
	 * @return  String
	 * 
	 * @param leaderSchdulVO
	 */
	@RequestMapping(value="/cop/smt/lsm/mng/modifyLeaderSchdul.do")
	public String modifyLeaderSchdul(@ModelAttribute("leaderSchdulVO") LeaderSchdulVO leaderSchdulVO,  ModelMap model) throws Exception{
  
		String sLocationUrl = "egovframework/com/cop/smt/lsm/EgovLeaderSchdulModify"; 
		
		// 0. Spring Security 사용자권한 처리
    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
    		model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
        	return "redirect:/uat/uia/egovLoginUsr.do";
    	}
    	
		/*
		 * 공통코드  
		 * 간부일정구분
		 */
    	ComDefaultCodeVO voComCode = new ComDefaultCodeVO();
    	voComCode.setCodeId("COM057");
    	List<CmmnDetailCode> listComCode = cmmUseService.selectCmmCodeDetail(voComCode);
    	model.addAttribute("schdulSe", listComCode);
    	/*
    	 * 공통코드  
    	 * 반복구분 
    	 */
    	voComCode = new ComDefaultCodeVO();
    	voComCode.setCodeId("COM058");
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

    	LeaderSchdulVO resultVO = leaderSchdulService.selectLeaderSchdul(leaderSchdulVO);
    	
    	String sSchdulBgnde = resultVO.getSchdulBgnDe();
    	String sSchdulEndde = resultVO.getSchdulEndDe();
    	
    	resultVO.setSchdulBgndeYYYMMDD(sSchdulBgnde.substring(0, 4) +"-"+sSchdulBgnde.substring(4, 6)+"-"+sSchdulBgnde.substring(6, 8) );
    	resultVO.setSchdulBgndeHH(sSchdulBgnde.substring(8, 10));
    	resultVO.setSchdulBgndeMM(sSchdulBgnde.substring(10, 12));
    	
    	resultVO.setSchdulEnddeYYYMMDD(sSchdulEndde.substring(0, 4) +"-"+sSchdulEndde.substring(4, 6)+"-"+sSchdulEndde.substring(6, 8) );
    	resultVO.setSchdulEnddeHH(sSchdulEndde.substring(8, 10));
    	resultVO.setSchdulEnddeMM(sSchdulEndde.substring(10, 12));
    	
    	resultVO.setSearchMode(leaderSchdulVO.getSearchMode());
    	resultVO.setYear(leaderSchdulVO.getYear());
    	resultVO.setMonth(leaderSchdulVO.getMonth());
    	resultVO.setWeek(leaderSchdulVO.getWeek());
    	resultVO.setDay(leaderSchdulVO.getDay());
    	model.addAttribute("leaderSchdulVO", resultVO);

		return sLocationUrl; 	
	}
	
	/**
     * 간부일정 등록을 위한 등록 페이지로 이동한다.
     * 
     * @param LeaderSchdulVO
     * @param model
     * @return
     * @throws Exception
     */
	@RequestMapping(value="/cop/smt/lsm/mng/addLeaderSchdul.do")
	public String addLeaderSchdul(
			@ModelAttribute("leaderSchdulVO") LeaderSchdulVO leaderSchdulVO, 
			BindingResult bindingResult,
    		ModelMap model)
	throws Exception {
		String sLocationUrl = "egovframework/com/cop/smt/lsm/EgovLeaderSchdulRegist"; 
		
    	// 0. Spring Security 사용자권한 처리
    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
    		model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
        	return "redirect:/uat/uia/egovLoginUsr.do";
    	}
    	
    	// 1. 로그인 객체 선언
		LoginVO loginVO = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
    	model.addAttribute("schdulChargerId", loginVO == null ? "" : EgovStringUtil.isNullToString(loginVO.getUniqId()));
    	model.addAttribute("schdulChargerName", loginVO == null ? "" : EgovStringUtil.isNullToString(loginVO.getName()));
		
    	/*
     	 * 공통코드  
     	 * 간부일정구분
     	 */
    	ComDefaultCodeVO voComCode = new ComDefaultCodeVO();
    	voComCode.setCodeId("COM057");
    	List<CmmnDetailCode> listComCode = cmmUseService.selectCmmCodeDetail(voComCode);
    	model.addAttribute("schdulSe", listComCode);
    	/*
    	 * 공통코드  
    	 * 반복구분
    	 */
    	voComCode = new ComDefaultCodeVO();
    	voComCode.setCodeId("COM058");
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
    	
    	model.addAttribute("searchMode", leaderSchdulVO.getSearchMode());
    	model.addAttribute("year", leaderSchdulVO.getYear());
    	model.addAttribute("month", leaderSchdulVO.getMonth());
    	model.addAttribute("week", leaderSchdulVO.getWeek());
    	model.addAttribute("day", leaderSchdulVO.getDay());
    	
    	
    	return sLocationUrl; 
    	
	}

	/**
	 * 간부일정 정보를 등록한다.
	 * @param LeaderSchdul
	 * @return  String
	 * 
	 * @param leaderSchdul
	 */
	@RequestMapping(value="/cop/smt/lsm/mng/insertLeaderSchdul.do")
	public String insertLeaderSchdul(@ModelAttribute("leaderSchdulVO") LeaderSchdulVO leaderSchdulVO,  
		    BindingResult bindingResult, ModelMap model) throws Exception {
		// 0. Spring Security 사용자권한 처리
    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
    		model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
        	return "redirect:/uat/uia/egovLoginUsr.do";
    	}
    	
		//로그인 객체 선언
		LoginVO loginVO = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
		
		String sLocationUrl = "egovframework/com/cop/smt/lsm/EgovLeaderSchdulRegist"; 
		
		//서버  validate 체크
        beanValidator.validate(leaderSchdulVO, bindingResult);
		if(bindingResult.hasErrors()){

			return sLocationUrl;
		}
		
		//아이디 설정
		leaderSchdulVO.setFrstRegisterId(loginVO == null ? "" : EgovStringUtil.isNullToString(loginVO.getUniqId()));
		leaderSchdulVO.setLastUpdusrId(loginVO == null ? "" : EgovStringUtil.isNullToString(loginVO.getUniqId()));
		
		leaderSchdulService.insertLeaderSchdul(leaderSchdulVO);
    	sLocationUrl = "forward:/cop/smt/lsm/usr/selectLeaderSchdulList.do"; 
        
        return sLocationUrl;
	}
	
	/**
	 * 간부일정 정보를 수정한다.
	 * @param LeaderSchdul
	 * @return  String
	 * 
	 * @param leaderSchdul
	 */
	@RequestMapping(value="/cop/smt/lsm/mng/updateLeaderSchdul.do")
	public String updateLeaderSchdul(@ModelAttribute("leaderSchdulVO") LeaderSchdulVO leaderSchdulVO, 
			BindingResult bindingResult, ModelMap model) throws Exception{
		// 0. Spring Security 사용자권한 처리
    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
    		model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
        	return "redirect:/uat/uia/egovLoginUsr.do";
    	}
    	
		//로그인 객체 선언
		LoginVO loginVO = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
		
		String sLocationUrl = "egovframework/com/cop/smt/lsm/EgovLeaderSchdulModify"; 
		
		//서버  validate 체크
        beanValidator.validate(leaderSchdulVO, bindingResult);
		if(bindingResult.hasErrors()){

			return sLocationUrl;
		}
		
		//아이디 설정
		leaderSchdulVO.setLastUpdusrId(loginVO == null ? "" : EgovStringUtil.isNullToString(loginVO.getUniqId()));
		
		leaderSchdulService.updateLeaderSchdul(leaderSchdulVO);
    	sLocationUrl = "forward:/cop/smt/lsm/usr/selectLeaderSchdulList.do"; 
        
        return sLocationUrl;
	}
	
	/**
	 * 간부일정 정보를 삭제한다.
	 * @param LeaderSchdul
	 * @return  String
	 * 
	 * @param leaderSchdul
	 */
	@RequestMapping(value="/cop/smt/lsm/mng/deleteLeaderSchdul.do")
	public String deleteLeaderSchdul(@ModelAttribute("leaderSchdulVO") LeaderSchdulVO leaderSchdulVO, ModelMap model) throws Exception{
		// 0. Spring Security 사용자권한 처리
    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
    		model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
        	return "redirect:/uat/uia/egovLoginUsr.do";
    	}
		leaderSchdulService.deleteLeaderSchdul(leaderSchdulVO);
		return "forward:/cop/smt/lsm/usr/selectLeaderSchdulList.do";
	}
	
	/**
	 * 간부상태 정보에 대한 목록을 조회한다. (사용자 화면)
	 * @param LeaderSttusVO
	 * @return  String
	 * 
	 * @param leaderSttusVO
	 */
	@RequestMapping("/cop/smt/lsm/usr/selectLeaderSttusList.do")
	public String selectLeaderSttusListView(@ModelAttribute("searchVO") LeaderSttusVO leaderSttusVO, ModelMap model) throws Exception{
		//LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
		String sLocationUrl = "egovframework/com/cop/smt/lsm/EgovLeaderSttusListView";
		
		boolean po = false;
		List<String> authenticated = EgovUserDetailsHelper.getAuthorities();
		// KISA 보안약점 조치 - 널(null) 값 체크
		if ( authenticated != null ) {
			for(int i=0; i< authenticated.size(); i++){
				if("ROLE_LEADERSCHDUL".equals(String.valueOf(authenticated.get(i)).trim())){
					po = true;
				}
			}
		}
		
		if(po){
			return "forward:/cop/smt/lsm/mng/selectLeaderSttusList.do";
		}
		
		leaderSttusVO.setPageUnit(propertyService.getInt("pageUnit"));
		leaderSttusVO.setPageSize(propertyService.getInt("pageSize"));

		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(leaderSttusVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(leaderSttusVO.getPageUnit());
		paginationInfo.setPageSize(leaderSttusVO.getPageSize());

		leaderSttusVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		leaderSttusVO.setLastIndex(paginationInfo.getLastRecordIndex());
		leaderSttusVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
		
		Map<String, Object> map = leaderSchdulService.selectLeaderSttusList(leaderSttusVO);
		int totCnt = Integer.parseInt((String)map.get("resultCnt"));
		paginationInfo.setTotalRecordCount(totCnt);
		
		model.addAttribute("resultList", map.get("resultList"));
		model.addAttribute("resultCnt", map.get("resultCnt"));
		model.addAttribute("paginationInfo", paginationInfo);

		return sLocationUrl;
	}
	
	/**
	 * 간부상태 정보에 대한 목록을 조회한다. (관리자 화면)
	 * @param LeaderSttusVO
	 * @return  String
	 * 
	 * @param leaderSttusVO
	 */
	@RequestMapping("/cop/smt/lsm/mng/selectLeaderSttusList.do")
	public String selectLeaderSttusList(@ModelAttribute("searchVO") LeaderSttusVO leaderSttusVO, ModelMap model) throws Exception{
		//LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
		String sLocationUrl = "egovframework/com/cop/smt/lsm/EgovLeaderSttusList";
		
		leaderSttusVO.setPageUnit(propertyService.getInt("pageUnit"));
		leaderSttusVO.setPageSize(propertyService.getInt("pageSize"));

		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(leaderSttusVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(leaderSttusVO.getPageUnit());
		paginationInfo.setPageSize(leaderSttusVO.getPageSize());

		leaderSttusVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		leaderSttusVO.setLastIndex(paginationInfo.getLastRecordIndex());
		leaderSttusVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
		
		Map<String, Object> map = leaderSchdulService.selectLeaderSttusList(leaderSttusVO);
		int totCnt = Integer.parseInt((String)map.get("resultCnt"));
		paginationInfo.setTotalRecordCount(totCnt);
		
		model.addAttribute("resultList", map.get("resultList"));
		model.addAttribute("resultCnt", map.get("resultCnt"));
		model.addAttribute("paginationInfo", paginationInfo);

		return sLocationUrl;
	}
	
	/**
	 * 간부상태 정보의 등록화면으로 이동한다.
	 * @param LeaderSttus
	 * @return  String
	 * 
	 * @param LeaderSttus
	 */
	@RequestMapping("/cop/smt/lsm/mng/addLeaderSttus.do")
	public String addLeaderSttus(
			@ModelAttribute("leaderSttusVO") LeaderSttusVO leaderSttusVO,
			ModelMap model) throws Exception{
		String sLocationUrl = "egovframework/com/cop/smt/lsm/EgovLeaderSttusRegist"; 
		
    	// 0. Spring Security 사용자권한 처리
    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
    		model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
        	return "redirect:/uat/uia/egovLoginUsr.do";
    	}
    	
    	/*
		 * 공통코드 
		 * 간부상태
		 */
		ComDefaultCodeVO voComCode = new ComDefaultCodeVO();
	   	voComCode = new ComDefaultCodeVO();
    	voComCode.setCodeId("COM061");
    	List<CmmnDetailCode> listComCode = cmmUseService.selectCmmCodeDetail(voComCode);
    	model.addAttribute("leaderSttus", listComCode);
    	
    	return sLocationUrl; 
	}
	
	/**
	 * 간부상태 정보의 수정화면으로 이동한다.
	 * @param LeaderSttus
	 * @return  String
	 * 
	 * @param LeaderSttus
	 */
	@RequestMapping("/cop/smt/lsm/mng/modifyLeaderSttus.do")
	public String modifyLeaderSttus(@ModelAttribute("leaderSttusVO") LeaderSttusVO leaderSttusVO, ModelMap model) throws Exception{
		// 0. Spring Security 사용자권한 처리
    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
    		model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
        	return "redirect:/uat/uia/egovLoginUsr.do";
    	}
    	
    	LeaderSttusVO resultVO = leaderSchdulService.selectLeaderSttus(leaderSttusVO);
		resultVO.setSearchCnd(leaderSttusVO.getSearchCnd());
		resultVO.setSearchWrd(leaderSttusVO.getSearchWrd());
		resultVO.setPageIndex(leaderSttusVO.getPageIndex());
		
		/*
		 * 공통코드 
		 * 간부상태
		 */
		ComDefaultCodeVO voComCode = new ComDefaultCodeVO();
	   	voComCode = new ComDefaultCodeVO();
    	voComCode.setCodeId("COM061");
    	List<CmmnDetailCode> listComCode = cmmUseService.selectCmmCodeDetail(voComCode);
    	model.addAttribute("leaderSttus", listComCode);
    	
        model.addAttribute("leaderSttusVO", resultVO);
        
		return "egovframework/com/cop/smt/lsm/EgovLeaderSttusUpdt";
	}
	
	/**
	 * 간부상태 정보를 수정한다.
	 * @param LeaderSttusVO
	 * @return  String
	 * 
	 * @param leaderSttusVO
	 */
	@RequestMapping("/cop/smt/lsm/mng/updateLeaderSttus.do")
	public String updateLeaderSttus(@ModelAttribute("leaderSttusVO") LeaderSttusVO leaderSttusVO, BindingResult bindingResult, ModelMap model) throws Exception{
		LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();

		beanValidator.validate(leaderSttusVO, bindingResult);
		if (bindingResult.hasErrors()) {
			//LeaderSttus result = leaderSchdulService.selectLeaderSttus(leaderSttusVO);
		    //model.addAttribute("leaderSttus", result);
		    return "egovframework/com/cop/smt/lsm/EgovLeaderSttusUpdt";
		}

		if (isAuthenticated) {
			leaderSttusVO.setLastUpdusrId(user == null ? "" : EgovStringUtil.isNullToString(user.getUniqId()));		    
			leaderSchdulService.updateLeaderSttus(leaderSttusVO);
		}

		return "forward:/cop/smt/lsm/mng/selectLeaderSttusList.do";
	}

	/**
	 * 간부상태 정보를 등록한다.
	 * @param LeaderSttusVO
	 * @return  String
	 * 
	 * @param leaderSttusVO
	 */
	@RequestMapping("/cop/smt/lsm/mng/insertLeaderSttus.do")
	public String insertLeaderSttus(@ModelAttribute("leaderSttusVO") LeaderSttusVO leaderSttusVO, BindingResult bindingResult, ModelMap model) throws Exception{
		// 0. Spring Security 사용자권한 처리
    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
    		model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
        	return "redirect:/uat/uia/egovLoginUsr.do";
    	}
    	
		//로그인 객체 선언
		LoginVO loginVO = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
		
		String sLocationUrl = "egovframework/com/cop/smt/lsm/EgovLeaderSttusRegist"; 
		
		//서버  validate 체크
        beanValidator.validate(leaderSttusVO, bindingResult);
		if(bindingResult.hasErrors()){
			return sLocationUrl;
		}
		
		//아이디 설정
		leaderSttusVO.setFrstRegisterId(loginVO == null ? "" : EgovStringUtil.isNullToString(loginVO.getUniqId()));
		leaderSttusVO.setLastUpdusrId(loginVO == null ? "" : EgovStringUtil.isNullToString(loginVO.getUniqId()));
		
		//간부상태 중복체크
		if(leaderSchdulService.selectLeaderSttusCheck(leaderSttusVO) > 0){
			model.addAttribute("leaderIdDuplicated", "true");
			sLocationUrl = "forward:/cop/smt/lsm/mng/addLeaderSttus.do";
		}else{
			leaderSchdulService.insertLeaderSttus(leaderSttusVO);
	    	sLocationUrl = "forward:/cop/smt/lsm/mng/selectLeaderSttusList.do";
		}
		return sLocationUrl;
	}

	/**
	 * 간부상태 정보를 삭제한다.
	 * @param LeaderSttus
	 * @return  String
	 * 
	 * @param LeaderSttus
	 */
	@RequestMapping("/cop/smt/lsm/mng/deleteLeaderSttus.do")
	public String deleteLeaderSttus(@ModelAttribute("leaderSttusVO") LeaderSttus leaderSttus, ModelMap model) throws Exception{
		// 0. Spring Security 사용자권한 처리
    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
    		model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
        	return "redirect:/uat/uia/egovLoginUsr.do";
    	}
    	leaderSchdulService.deleteLeaderSttus(leaderSttus);
		return "forward:/cop/smt/lsm/mng/selectLeaderSttusList.do";
	}

	/**
	 * 시간의 LIST를 반환한다.
	 * @return  List
	 * @throws 
	 */
	private List<ComDefaultCodeVO> getTimeHH (){
    	ArrayList<ComDefaultCodeVO> listHH = new ArrayList<ComDefaultCodeVO>();
    	//HashMap hmHHMM;
    	for(int i=0;i < 24; i++){
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
	 * 분의 LIST를 반환한다.
	 * @return  List
	 * @throws 
	 */
	private List<ComDefaultCodeVO> getTimeMM (){
    	ArrayList<ComDefaultCodeVO> listMM = new ArrayList<ComDefaultCodeVO>();
    	//HashMap hmHHMM;
    	for(int i=0;i < 60; i++){
    		
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
    private String dateTypeIntForString(int iInput){
		String sOutput = "";
		if(Integer.toString(iInput).length() == 1){
			sOutput = "0" + Integer.toString(iInput);
		}else{
			sOutput = Integer.toString(iInput);
		}
		
       return sOutput;
    }

}