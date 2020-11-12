package egovframework.com.cop.smt.sim.web;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springmodules.validation.commons.DefaultBeanValidator;

import egovframework.com.cmm.ComDefaultCodeVO;
import egovframework.com.cmm.ComDefaultVO;
import egovframework.com.cmm.EgovComponentChecker;
import egovframework.com.cmm.EgovMessageSource;
import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.annotation.IncludedInfo;
import egovframework.com.cmm.service.EgovCmmUseService;
import egovframework.com.cmm.service.EgovFileMngService;
import egovframework.com.cmm.service.EgovFileMngUtil;
import egovframework.com.cmm.service.FileVO;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.com.cop.smt.sim.service.EgovIndvdlSchdulManageService;
import egovframework.com.cop.smt.sim.service.IndvdlSchdulManageVO;
import egovframework.com.utl.fcc.service.EgovStringUtil;
import egovframework.rte.fdl.property.EgovPropertyService;
/**
 * 일정관리를 처리하는 Controller Class 구현
 * @author 공통서비스 장동한
 * @since 2009.04.10
 * @version 1.0
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *  수정일                수정자           수정내용
 *  ----------   --------   ---------------------------
 *  2009.04.10   장동한            최초 생성
 *  2011.09.02   정진오            10월 주차 테이블에 날짜가 이상하게 나와서 수정함
 *  2011.09.16   이기하            일지관리가 존재할 때 버튼이 나타나도록 수정
 *  2016.08.12   장동한            일정관리 등록 로직 수정
 *  2020.10.27   신용호            파일 업로드 수정 (multiRequest.getFiles)
 *
 * </pre>
 */
@Controller
public class EgovIndvdlSchdulManageController {

	private static final Logger LOGGER = LoggerFactory.getLogger(EgovIndvdlSchdulManageController.class);

	@Autowired
	private DefaultBeanValidator beanValidator;

	/** EgovMessageSource */
    @Resource(name="egovMessageSource")
    EgovMessageSource egovMessageSource;

	@Resource(name = "egovIndvdlSchdulManageService")
	private EgovIndvdlSchdulManageService egovIndvdlSchdulManageService;

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
	 * 메인페이지/일정관리조회
	 * @param commandMap
	 * @param model
	 * @return "egovframework/com/cop/smt/sim/EgovIndvdlSchdulManageMainList"
	 * @throws Exception
	 */
	@RequestMapping(value="/cop/smt/sim/EgovIndvdlSchdulManageMainList.do")
	public String egovIndvdlSchdulManageMainList(
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
		if(loginVO == null){ loginVO = new LoginVO();}

		HashMap<String, String> hmParam = new HashMap<String, String>();

		hmParam.put("uniqId", loginVO.getUniqId());

		List<?> reusltList = egovIndvdlSchdulManageService.selectIndvdlSchdulManageMainList(hmParam);

		 model.addAttribute("resultList", reusltList);

    	return "egovframework/com/cop/smt/sim/EgovIndvdlSchdulManageMainList";

	}

	/**
	 * 일정(일별) 목록을 조회한다.
	 * @param searchVO
	 * @param commandMap
	 * @param indvdlSchdulManageVO
	 * @param model
	 * @return "egovframework/com/cop/smt/sim/EgovIndvdlSchdulManageDailyList"
	 * @throws Exception
	 */
	@RequestMapping(value="/cop/smt/sim/EgovIndvdlSchdulManageDailyList.do")
	public String egovIndvdlSchdulManageDailyList(
			@ModelAttribute("searchVO") ComDefaultVO searchVO,
			@RequestParam Map<String, String> commandMap,
			IndvdlSchdulManageVO indvdlSchdulManageVO,
    		ModelMap model)
    throws Exception {

		//일정구분 검색 유지
        model.addAttribute("searchKeyword", commandMap.get("searchKeyword") == null ? "" : (String)commandMap.get("searchKeyword"));
        model.addAttribute("searchCondition", commandMap.get("searchCondition") == null ? "" : (String)commandMap.get("searchCondition"));

		//공통코드 일정종류
		ComDefaultCodeVO voComCode = new ComDefaultCodeVO();
	   	voComCode = new ComDefaultCodeVO();
    	voComCode.setCodeId("COM030");
    	List<?> listComCode = cmmUseService.selectCmmCodeDetail(voComCode);
    	model.addAttribute("schdulSe", listComCode);

		/* *****************************************************************
    	// 캘런더 설정 로직
		****************************************************************** */
        Calendar calNow = Calendar.getInstance();

		String strYear = commandMap.get("year");
		String strMonth = commandMap.get("month");
		String strDay =commandMap.get("day");
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

		List<?> resultList = egovIndvdlSchdulManageService.selectIndvdlSchdulManageRetrieve(commandMap);
        model.addAttribute("resultList", resultList);

		return "egovframework/com/cop/smt/sim/EgovIndvdlSchdulManageDailyList";
	}

	/**
	 * 일정(주간별) 목록을 조회한다.
	 * @param searchVO
	 * @param commandMap
	 * @param indvdlSchdulManageVO
	 * @param model
	 * @return "egovframework/com/cop/smt/sim/EgovIndvdlSchdulManageWeekList"
	 * @throws Exception
	 */
	@SuppressWarnings({ "rawtypes", "unused", "unchecked" })
	@RequestMapping(value="/cop/smt/sim/EgovIndvdlSchdulManageWeekList.do")
	public String egovIndvdlSchdulManageWeekList(
			@ModelAttribute("searchVO") ComDefaultVO searchVO,
			@RequestParam Map commandMap,
			IndvdlSchdulManageVO indvdlSchdulManageVO,
    		ModelMap model)
    throws Exception {

		//일정구분 검색 유지
        model.addAttribute("searchKeyword", commandMap.get("searchKeyword") == null ? "" : (String)commandMap.get("searchKeyword"));
        model.addAttribute("searchCondition", commandMap.get("searchCondition") == null ? "" : (String)commandMap.get("searchCondition"));

		//공통코드 일정종류
		ComDefaultCodeVO voComCode = new ComDefaultCodeVO();
	   	voComCode = new ComDefaultCodeVO();
    	voComCode.setCodeId("COM030");
    	List listComCode = cmmUseService.selectCmmCodeDetail(voComCode);
    	model.addAttribute("schdulSe", listComCode);

		/* *****************************************************************
    	// 캘런더 설정 로직
		****************************************************************** */
        Calendar calNow = Calendar.getInstance();
        Calendar calBefore = Calendar.getInstance();
        Calendar calNext = Calendar.getInstance();


		String strYear = (String) commandMap.get("year");
		String strMonth = (String) commandMap.get("month");
		String strWeek =(String) commandMap.get("week");

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

		//년도/월 셋팅
		calNow.set(iNowYear, iNowMonth, 1);
		calBefore.set(iNowYear, iNowMonth, 1);
		calNext.set(iNowYear, iNowMonth, 1);

		calBefore.add(Calendar.MONTH, -1);
		calNext.add(Calendar.MONTH, +1);

		int startDay = calNow.getMinimum(Calendar.DATE);
		int endDay = calNow.getActualMaximum(Calendar.DAY_OF_MONTH);
		int startWeek = calNow.get(Calendar.DAY_OF_WEEK);


		ArrayList listWeekGrop = new ArrayList();
		ArrayList listWeekDate = new ArrayList();

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
			// (2011.09.02 수정사항) 10월의 주별 날짜가 이상하게 나와서 LeaderSchedule 보고 수정함. 위의 코드가 원래 코드
			sUseDate += Integer.toString(iNowMonth+1).length() == 1 ? "0" + Integer.toString(iNowMonth+1) : Integer.toString(iNowMonth+1);
			sUseDate += Integer.toString(i).length() == 1 ? "0" + Integer.toString(i) : Integer.toString(i);

			listWeekDate.add(sUseDate);

			if( iBetweenCount % 7 == 0){
				listWeekGrop.add(listWeekDate);
				listWeekDate = new ArrayList();

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

		List listWeek = (List)listWeekGrop.get(iNowWeek);
		commandMap.put("searchMode", "WEEK");
		commandMap.put("schdulBgnde", listWeek.get(0));
		commandMap.put("schdulEndde", listWeek.get(listWeek.size()-1));

		List resultList = egovIndvdlSchdulManageService.selectIndvdlSchdulManageRetrieve(commandMap);
        model.addAttribute("resultList", resultList);

		return "egovframework/com/cop/smt/sim/EgovIndvdlSchdulManageWeekList";
	}

	/**
	 * 일정(월별) 목록을 조회한다.
	 * @param searchVO
	 * @param commandMap
	 * @param indvdlSchdulManageVO
	 * @param model
	 * @return "egovframework/com/cop/smt/sim/EgovIndvdlSchdulManageMonthList"
	 * @throws Exception
	 */
	@SuppressWarnings({ "rawtypes", "unused" })
	@RequestMapping(value="/cop/smt/sim/EgovIndvdlSchdulManageMonthList.do")
	public String egovIndvdlSchdulManageMonthList(
			@ModelAttribute("searchVO") ComDefaultVO searchVO,
			@RequestParam Map<String, String> commandMap,
			IndvdlSchdulManageVO indvdlSchdulManageVO,
    		ModelMap model)
    throws Exception {

		//일정구분 검색 유지
        model.addAttribute("searchKeyword", commandMap.get("searchKeyword") == null ? "" : (String)commandMap.get("searchKeyword"));
        model.addAttribute("searchCondition", commandMap.get("searchCondition") == null ? "" : (String)commandMap.get("searchCondition"));

		java.util.Calendar cal = java.util.Calendar.getInstance();

		String sYear = commandMap.get("year");
		String sMonth = commandMap.get("month");

		int iYear = cal.get(java.util.Calendar.YEAR);
		int iMonth = cal.get(java.util.Calendar.MONTH);
		int iDate = cal.get(java.util.Calendar.DATE);

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



		//공통코드 일정종류
		ComDefaultCodeVO voComCode = new ComDefaultCodeVO();
	   	voComCode = new ComDefaultCodeVO();
    	voComCode.setCodeId("COM030");
    	List listComCode = cmmUseService.selectCmmCodeDetail(voComCode);
    	model.addAttribute("schdulSe", listComCode);

    	commandMap.put("searchMonth", sSearchDate);
    	commandMap.put("searchMode", "MONTH");

        List resultList = egovIndvdlSchdulManageService.selectIndvdlSchdulManageRetrieve(commandMap);
        model.addAttribute("resultList", resultList);

		return "egovframework/com/cop/smt/sim/EgovIndvdlSchdulManageMonthList";
	}

	/**
	 * 일정 목록을 조회한다.
	 * @param searchVO
	 * @param commandMap
	 * @param indvdlSchdulManageVO
	 * @param model
	 * @return "egovframework/com/cop/smt/sim/EgovIndvdlSchdulManageList"
	 * @throws Exception
	 */
	@IncludedInfo(name="일정관리", order = 330 ,gid = 40)
	@RequestMapping(value="/cop/smt/sim/EgovIndvdlSchdulManageList.do")
	public String egovIndvdlSchdulManageList(
			@ModelAttribute("searchVO") ComDefaultVO searchVO,
			@RequestParam Map<?, ?> commandMap,
			IndvdlSchdulManageVO indvdlSchdulManageVO,
    		ModelMap model)
    throws Exception {


        List<?> resultList = egovIndvdlSchdulManageService.selectIndvdlSchdulManageList(searchVO);
        model.addAttribute("resultList", resultList);

		return "egovframework/com/cop/smt/sim/EgovIndvdlSchdulManageList";
	}

	/**
	 * 일정 목록을 상세조회 조회한다.
	 * @param searchVO
	 * @param indvdlSchdulManageVO
	 * @param commandMap
	 * @param model
	 * @return "egovframework/com/cop/smt/sim/EgovIndvdlSchdulManageDetail"
	 * @throws Exception
	 */
	@RequestMapping(value="/cop/smt/sim/EgovIndvdlSchdulManageDetail.do")
	public String egovIndvdlSchdulManageDetail(
			@ModelAttribute("searchVO") ComDefaultVO searchVO,
			IndvdlSchdulManageVO indvdlSchdulManageVO,
			@RequestParam Map<?, ?> commandMap,
    		ModelMap model)
    throws Exception {

		String sLocationUrl = "egovframework/com/cop/smt/sim/EgovIndvdlSchdulManageDetail";

		String sCmd = commandMap.get("cmd") == null ? "" : (String)commandMap.get("cmd");

		if(sCmd.equals("del")){
			egovIndvdlSchdulManageService.deleteIndvdlSchdulManage(indvdlSchdulManageVO);
			sLocationUrl = "redirect:/cop/smt/sim/EgovIndvdlSchdulManageList.do";
		}else{

	     	//공통코드  중요도 조회
	    	ComDefaultCodeVO voComCode = new ComDefaultCodeVO();
	    	voComCode.setCodeId("COM019");
	    	List<?> listComCode = cmmUseService.selectCmmCodeDetail(voComCode);
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

	        List<?> sampleList = egovIndvdlSchdulManageService.selectIndvdlSchdulManageDetail(indvdlSchdulManageVO);
	        model.addAttribute("resultList", sampleList);

	        //-----------------------------------------------------------
	        // 2011.09.16 : 일지관리가 존재할 때 버튼이 나타나도록 수정
	        //-----------------------------------------------------------

	        if(EgovComponentChecker.hasComponent("egovDiaryManageService")){
	        	model.addAttribute("useDiaryManage", "true");
	        }

	        ////-------------------------------
		}


		return sLocationUrl;
	}

	/**
	 * 일정 수정 폼
	 * @param searchVO
	 * @param commandMap
	 * @param indvdlSchdulManageVO
	 * @param bindingResult
	 * @param model
	 * @return "egovframework/com/cop/smt/sim/EgovIndvdlSchdulManageModify"
	 * @throws Exception
	 */
	@SuppressWarnings("unused")
	@RequestMapping(value="/cop/smt/sim/EgovIndvdlSchdulManageModify.do")
	public String indvdlSchdulManageModify(
			@ModelAttribute("searchVO") ComDefaultVO searchVO,
			@RequestParam Map<?, ?> commandMap,
			IndvdlSchdulManageVO indvdlSchdulManageVO,
			BindingResult bindingResult,
    		ModelMap model)
    throws Exception {

		String sLocationUrl = "egovframework/com/cop/smt/sim/EgovIndvdlSchdulManageModify";

		String sCmd = commandMap.get("cmd") == null ? "" : (String)commandMap.get("cmd");

     	//공통코드  중요도 조회
    	ComDefaultCodeVO voComCode = new ComDefaultCodeVO();
    	voComCode.setCodeId("COM019");
    	List<?> listComCode = cmmUseService.selectCmmCodeDetail(voComCode);
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

    	IndvdlSchdulManageVO resultIndvdlSchdulManageVOReuslt = egovIndvdlSchdulManageService.selectIndvdlSchdulManageDetailVO(indvdlSchdulManageVO);

    	String sSchdulBgnde = resultIndvdlSchdulManageVOReuslt.getSchdulBgnde();
    	String sSchdulEndde = resultIndvdlSchdulManageVOReuslt.getSchdulEndde();

    	resultIndvdlSchdulManageVOReuslt.setSchdulBgndeYYYMMDD(sSchdulBgnde.substring(0, 4) +"-"+sSchdulBgnde.substring(4, 6)+"-"+sSchdulBgnde.substring(6, 8) );
    	resultIndvdlSchdulManageVOReuslt.setSchdulBgndeHH(sSchdulBgnde.substring(8, 10));
    	resultIndvdlSchdulManageVOReuslt.setSchdulBgndeMM(sSchdulBgnde.substring(10, 12));

       	resultIndvdlSchdulManageVOReuslt.setSchdulEnddeYYYMMDD(sSchdulEndde.substring(0, 4) +"-"+sSchdulEndde.substring(4, 6)+"-"+sSchdulEndde.substring(6, 8) );
    	resultIndvdlSchdulManageVOReuslt.setSchdulEnddeHH(sSchdulEndde.substring(8, 10));
    	resultIndvdlSchdulManageVOReuslt.setSchdulEnddeMM(sSchdulEndde.substring(10, 12));

    	System.out.println("resultIndvdlSchdulManageVOReuslt>>>"+resultIndvdlSchdulManageVOReuslt.getAtchFileId());
    	model.addAttribute("indvdlSchdulManageVO", resultIndvdlSchdulManageVOReuslt);

		return sLocationUrl;
	}

	/**
	 * 일정를 수정 처리 한다.
	 * @param multiRequest
	 * @param searchVO
	 * @param commandMap
	 * @param indvdlSchdulManageVO
	 * @param bindingResult
	 * @param model
	 * @return "egovframework/com/cop/smt/sim/EgovIndvdlSchdulManageModifyActor"
	 * @throws Exception
	 */
	@RequestMapping(value="/cop/smt/sim/EgovIndvdlSchdulManageModifyActor.do")
	public String indvdlSchdulManageModifyActor(
			final MultipartHttpServletRequest multiRequest,
			ComDefaultVO searchVO,
			@RequestParam Map<?, ?> commandMap,
			@ModelAttribute("indvdlSchdulManageVO") IndvdlSchdulManageVO indvdlSchdulManageVO,
			BindingResult bindingResult,
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

		String sLocationUrl = "egovframework/com/cop/smt/sim/EgovIndvdlSchdulManageModify";

		String sCmd = commandMap.get("cmd") == null ? "" : (String)commandMap.get("cmd");



        if(sCmd.equals("save")){
    		//서버  validate 체크
            beanValidator.validate(indvdlSchdulManageVO, bindingResult);
    		if(bindingResult.hasErrors()){

    	     	//공통코드  중요도 조회
    	    	ComDefaultCodeVO voComCode = new ComDefaultCodeVO();
    	    	voComCode.setCodeId("COM019");
    	    	List<?> listComCode = cmmUseService.selectCmmCodeDetail(voComCode);
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
    		indvdlSchdulManageVO.setFrstRegisterId(loginVO == null ? "" : EgovStringUtil.isNullToString(loginVO.getUniqId()));
    		indvdlSchdulManageVO.setLastUpdusrId(loginVO == null ? "" : EgovStringUtil.isNullToString(loginVO.getUniqId()));
    		/* *****************************************************************
        	// 첨부파일 관련 ID 생성 start....
			****************************************************************** */
    		String _atchFileId = indvdlSchdulManageVO.getAtchFileId();


    		//final Map<String, MultipartFile> files = multiRequest.getFileMap();
    		final List<MultipartFile> files = multiRequest.getFiles("file_1");

    		if(!files.isEmpty()){
    			String atchFileAt = commandMap.get("atchFileAt") == null ? "" : (String)commandMap.get("atchFileAt");
    			if("N".equals(atchFileAt)){
    				List<FileVO> _result = fileUtil.parseFileInf(files, "DSCH_", 0, _atchFileId, "");
    				_atchFileId = fileMngService.insertFileInfs(_result);

    				// 첨부파일 ID 셋팅
    				indvdlSchdulManageVO.setAtchFileId(_atchFileId);    	// 첨부파일 ID

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
        	egovIndvdlSchdulManageService.updateIndvdlSchdulManage(indvdlSchdulManageVO);
        	sLocationUrl = "redirect:/cop/smt/sim/EgovIndvdlSchdulManageList.do";
		}

		return sLocationUrl;
	}

	/**
	 * 일정를 등록 폼
	 * @param searchVO
	 * @param commandMap
	 * @param indvdlSchdulManageVO
	 * @param bindingResult
	 * @param model
	 * @return "egovframework/com/cop/smt/sim/EgovIndvdlSchdulManageRegist"
	 * @throws Exception
	 */
	@RequestMapping(value="/cop/smt/sim/EgovIndvdlSchdulManageRegist.do")
	public String indvdlSchdulManageRegist(
			@ModelAttribute("searchVO") ComDefaultVO searchVO,
			@RequestParam Map<?, ?> commandMap,
			@ModelAttribute("indvdlSchdulManageVO") IndvdlSchdulManageVO indvdlSchdulManageVO,
			BindingResult bindingResult,
    		ModelMap model)
    throws Exception {

		String sLocationUrl = "egovframework/com/cop/smt/sim/EgovIndvdlSchdulManageRegist";

    	// 0. Spring Security 사용자권한 처리
    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
    		model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
        	return "egovframework/com/uat/uia/EgovLoginUsr";
    	}

     	//공통코드  중요도 조회
    	ComDefaultCodeVO voComCode = new ComDefaultCodeVO();
    	voComCode.setCodeId("COM019");
    	List<?> listComCode = cmmUseService.selectCmmCodeDetail(voComCode);
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
	 * 일정를 등록 처리 한다.
	 * @param multiRequest
	 * @param searchVO
	 * @param commandMap
	 * @param indvdlSchdulManageVO
	 * @param bindingResult
	 * @param model
	 * @return "egovframework/com/cop/smt/sim/EgovIndvdlSchdulManageRegistActor"
	 * @throws Exception
	 */
	@RequestMapping(value="/cop/smt/sim/EgovIndvdlSchdulManageRegistActor.do")
	public String indvdlSchdulManageRegistActor(
			final MultipartHttpServletRequest multiRequest,
			@ModelAttribute("searchVO") ComDefaultVO searchVO,
			@RequestParam Map<?, ?> commandMap,
			@ModelAttribute("indvdlSchdulManageVO") IndvdlSchdulManageVO indvdlSchdulManageVO,
			BindingResult bindingResult,
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

		String sLocationUrl = "egovframework/com/cop/smt/sim/EgovIndvdlSchdulManageRegist";

		String sCmd = commandMap.get("cmd") == null ? "" : (String)commandMap.get("cmd");
		LOGGER.info("cmd => {}", sCmd);

        if(sCmd.equals("save")){
    		//서버  validate 체크
            beanValidator.validate(indvdlSchdulManageVO, bindingResult);
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
    		indvdlSchdulManageVO.setAtchFileId(_atchFileId);			// 첨부파일 ID

    		//아이디 설정
    		indvdlSchdulManageVO.setFrstRegisterId(loginVO == null ? "" : EgovStringUtil.isNullToString(loginVO.getUniqId()));
    		indvdlSchdulManageVO.setLastUpdusrId(loginVO == null ? "" : EgovStringUtil.isNullToString(loginVO.getUniqId()));
    		//일정 담당자 자신으로 등록(2017.08.12 modify by jdh)
    		indvdlSchdulManageVO.setSchdulChargerId(loginVO == null ? "" : EgovStringUtil.isNullToString(loginVO.getUniqId()));

        	egovIndvdlSchdulManageService.insertIndvdlSchdulManage(indvdlSchdulManageVO);
        	sLocationUrl = "redirect:/cop/smt/sim/EgovIndvdlSchdulManageList.do";
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
	@SuppressWarnings("unused")
	private List<ComDefaultCodeVO> getTimeMM (){
    	ArrayList<ComDefaultCodeVO> listMM = new ArrayList<ComDefaultCodeVO>();
    	HashMap<?, ?> hmHHMM;
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


