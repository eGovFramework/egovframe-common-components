package egovframework.com.sym.cal.web;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import egovframework.com.cmm.ComDefaultCodeVO;
import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.annotation.IncludedInfo;
import egovframework.com.cmm.service.EgovCmmUseService;
import egovframework.com.sym.cal.service.EgovCalRestdeManageService;
import egovframework.com.sym.cal.service.Restde;
import egovframework.com.sym.cal.service.RestdeVO;

import egovframework.rte.fdl.idgnr.EgovIdGnrService;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

import javax.annotation.Resource;

import org.apache.commons.collections.map.ListOrderedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springmodules.validation.commons.DefaultBeanValidator;


/**
 *
 * 공휴일에 관한 요청을 받아 서비스 클래스로 요청을 전달하고 서비스클래스에서 처리한 결과를 웹 화면으로 전달을 위한 Controller를 정의한다
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
 *   2011.8.26	  정진오	   IncludedInfo annotation 추가
 *   2011.10.18  서준식          보안점검 조치 사항으로 sql injection에 대비한 파라미터 체크(달력 출력을 위한 숫자만 가능하도록)
 * </pre>
 */

@Controller
public class EgovCalRestdeManageController {

	@Resource(name = "RestdeManageService")
    private EgovCalRestdeManageService restdeManageService;

    /** EgovPropertyService */
    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;

	@Resource(name="egovRestDeIdGnrService")
	private EgovIdGnrService idgenService;

	@Resource(name="EgovCmmUseService")
	private EgovCmmUseService cmmUseService;

	@Autowired
	private DefaultBeanValidator beanValidator;

	public BindingResult checkRestdeWithValidator(Restde restde, BindingResult bindingResult){

		restde.setRestdeDe("dummy");
		restde.setRestdeNm("dummy");
		restde.setRestdeDc("dummy");
		restde.setRestdeSeCode("dummy");

		beanValidator.validate(restde, bindingResult);

		return bindingResult;
	}

	/**
	 * 달력 메인창을 호출한다.
	 * @param model
	 * @return "egovframework/com/sym/cal/EgovNormalCalPopup"
	 * @throws Exception
	 */
	@RequestMapping(value="/sym/cal/callCalPopup.do")
 	public String callCalendar (ModelMap model
 			) throws Exception {
		return "egovframework/com/sym/cal/EgovCalPopup";
	}

	/**
	 * 달력을 호출한다.
	 * @param model
	 * @return "egovframework/com/sym/cal/EgovNormalCalPopup"
	 * @throws Exception
	 */
	@SuppressWarnings("unused")
	@RequestMapping(value="/sym/cal/callCal.do")
 	public String callCal (Restde restde, BindingResult bindingResult
 			, ModelMap model
 			) throws Exception {

		//2011.10.18 달력 출력을 위해 필요한 숫자 이외의 값을 사용하는 경우 체크
		bindingResult = checkRestdeWithValidator(restde, bindingResult);

		if(bindingResult.hasErrors()){

			return "egovframework/com/cmm/error/dataAccessFailure";

		}


		Calendar cal = Calendar.getInstance();

		if(restde.getYear()==null || restde.getYear().equals("")){
			restde.setYear(Integer.toString(cal.get(Calendar.YEAR)));
		}
		if(restde.getMonth()==null || restde.getMonth().equals("")){
			restde.setMonth(Integer.toString(cal.get(Calendar.MONTH)+1));
		}
		int iYear  = Integer.parseInt(restde.getYear());
		int iMonth = Integer.parseInt(restde.getMonth());

		if (iMonth<1){
			iYear--;
			iMonth = 12;
		}
		if (iMonth>12){
			iYear++;
			iMonth = 1;
		}
		if (iYear<1){
			iYear = 1;
			iMonth = 1;
		}
		if (iYear>9999){
			iYear = 9999;
			iMonth = 12;
		}

		cal.set(iYear,iMonth-1,1);

		int firstWeek = cal.get(Calendar.DAY_OF_WEEK);
		int lastDay   = cal.getActualMaximum(Calendar.DATE);
		int week      = cal.get(Calendar.DAY_OF_WEEK);

		String year   = Integer.toString(iYear);
		String month  = Integer.toString(iMonth);
		String day    = Integer.toString(cal.get(Calendar.DAY_OF_MONTH));

		restde.setStartWeekMonth(firstWeek);
		restde.setLastDayMonth(lastDay);
		restde.setYear(year);
		restde.setMonth(month);

		List<ListOrderedMap> CalInfoList = new ArrayList<ListOrderedMap>();
		String tmpDay = "";

		/**
		 * 계산... START
		 */
		for(int i=0; i<42;i++) {
			ListOrderedMap  map   = new ListOrderedMap();
			int cc = i + 1;
			int dd = cc-firstWeek+1;

			if (dd > 0 && dd <= lastDay) {
				tmpDay = Integer.toString(dd);
			} else {
				tmpDay = "";
			}

			map.put("year",		year);
	        map.put("month",	month);
	        map.put("day",		tmpDay);
	        map.put("cellNum",	cc);
	        map.put("weeks",	(cc - 1) / 7 + 1);
	        map.put("week",		(week-1) % 7 + 1);
	        map.put("restAt",	((week-1) % 7 + 1==1) ? "Y" : "N");

	    	if (dd > 0 && dd <= lastDay) {
				week ++;
			}
	    	CalInfoList.add(map);

		}
		/**
		 * 계산... END
		 */

        model.addAttribute("resultList", CalInfoList);

		return "egovframework/com/sym/cal/EgovCalendar";
	}

	/**
	 * 일반달력 팝업 메인창을 호출한다.
	 * @param model
	 * @return "egovframework/com/sym/cal/EgovNormalCalPopup"
	 * @throws Exception
	 */
	@RequestMapping(value="/sym/cal/EgovNormalCalPopup.do")
 	public String callNormalCalPopup (ModelMap model
 			) throws Exception {
		return "egovframework/com/sym/cal/EgovNormalCalPopup";
	}

	/**
	 * 일반달력 팝업 정보를 조회한다.
	 * @param restde
	 * @param model
	 * @return "egovframework/com/sym/cal/EgovNormalCalendar"
	 * @throws Exception
	 */
	@RequestMapping(value="/sym/cal/EgovselectNormalCalendar.do")
 	public String selectNormalRestdePopup (Restde restde, BindingResult bindingResult
 			, ModelMap model
 			) throws Exception {

		//2011.10.18 달력 출력을 위해 필요한 숫자 이외의 값을 사용하는 경우 체크
		bindingResult = checkRestdeWithValidator(restde, bindingResult);

		if(bindingResult.hasErrors()){

			return "egovframework/com/cmm/error/dataAccessFailure";

		}

		Calendar cal = Calendar.getInstance();

		if(restde.getYear()==null || restde.getYear().equals("")){
			restde.setYear(Integer.toString(cal.get(Calendar.YEAR)));
		}
		if(restde.getMonth()==null || restde.getMonth().equals("")){
			restde.setMonth(Integer.toString(cal.get(Calendar.MONTH)+1));
		}
		int iYear  = Integer.parseInt(restde.getYear());
		int iMonth = Integer.parseInt(restde.getMonth());

		if (iMonth<1){
			iYear--;
			iMonth = 12;
		}
		if (iMonth>12){
			iYear++;
			iMonth = 1;
		}
		if (iYear<1){
			iYear = 1;
			iMonth = 1;
		}
		if (iYear>9999){
			iYear = 9999;
			iMonth = 12;
		}
		restde.setYear(Integer.toString(iYear));
		restde.setMonth(Integer.toString(iMonth));

		cal.set(iYear,iMonth-1,1);

		restde.setStartWeekMonth(cal.get(Calendar.DAY_OF_WEEK));
		restde.setLastDayMonth(cal.getActualMaximum(Calendar.DATE));

        List<?> CalInfoList = restdeManageService.selectNormalRestdePopup(restde);

        model.addAttribute("resultList", CalInfoList);

		return "egovframework/com/sym/cal/EgovNormalCalendar";
	}


	/**
	 * 행정달력 팝업 메인창을 호출한다.
	 * @param model
	 * @return "egovframework/com/sym/cal/EgovAdministCalPopup"
	 * @throws Exception
	 */
	@RequestMapping(value="/sym/cal/EgovAdministCalPopup.do")
 	public String callAdministCalPopup (ModelMap model
 			) throws Exception {
		return "egovframework/com/sym/cal/EgovAdministCalPopup";
	}

	/**
	 * 행정달력 팝업 정보를 조회한다.
	 * @param restde
	 * @param model
	 * @return "egovframework/com/sym/cal/EgovAdministCalendar"
	 * @throws Exception
	 */
	@RequestMapping(value="/sym/cal/EgovselectAdministCalendar.do")
 	public String selectAdministRestdePopup (Restde restde, BindingResult bindingResult
 			, ModelMap model
 			) throws Exception {

		//2011.10.18 달력 출력을 위해 필요한 숫자 이외의 값을 사용하는 경우 체크
		bindingResult = checkRestdeWithValidator(restde, bindingResult);

		if(bindingResult.hasErrors()){

			return "egovframework/com/cmm/error/dataAccessFailure";

		}

		Calendar cal = Calendar.getInstance();

		if(restde.getYear()==null || restde.getYear().equals("")){
			restde.setYear(Integer.toString(cal.get(Calendar.YEAR)));
		}
		if(restde.getMonth()==null || restde.getMonth().equals("")){
			restde.setMonth(Integer.toString(cal.get(Calendar.MONTH)+1));
		}
		int iYear  = Integer.parseInt(restde.getYear());
		int iMonth = Integer.parseInt(restde.getMonth());

		if (iMonth<1){
			iYear--;
			iMonth = 12;
		}
		if (iMonth>12){
			iYear++;
			iMonth = 1;
		}
		if (iYear<1){
			iYear = 1;
			iMonth = 1;
		}
		if (iYear>9999){
			iYear = 9999;
			iMonth = 12;
		}
		restde.setYear(Integer.toString(iYear));
		restde.setMonth(Integer.toString(iMonth));

		cal.set(iYear,iMonth-1,1);

		restde.setStartWeekMonth(cal.get(Calendar.DAY_OF_WEEK));
		restde.setLastDayMonth(cal.getActualMaximum(Calendar.DATE));

        List<?> CalInfoList = restdeManageService.selectAdministRestdePopup(restde);

        model.addAttribute("resultList", CalInfoList);

		return "egovframework/com/sym/cal/EgovAdministCalendar";
	}

	/**
	 * 일반달력 일간
	 * @param restde
	 * @param model
	 * @return "egovframework/com/sym/cal/EgovNormalDayCalendar"
	 * @throws Exception
	 */
	@SuppressWarnings("static-access")
	@RequestMapping(value="/sym/cal/EgovNormalDayCalendar.do")
 	public String selectNormalDayCalendar (Restde restde, BindingResult bindingResult
 			, ModelMap model
 			) throws Exception {

		//2011.10.18 달력 출력을 위해 필요한 숫자 이외의 값을 사용하는 경우 체크
		bindingResult = checkRestdeWithValidator(restde, bindingResult);

		if(bindingResult.hasErrors()){

			return "egovframework/com/cmm/error/dataAccessFailure";

		}

		Calendar cal = Calendar.getInstance();


		if(restde.getYear()==null || restde.getYear().equals("")){
			restde.setYear(Integer.toString(cal.get(Calendar.YEAR)));
		}
		if(restde.getMonth()==null || restde.getMonth().equals("")){
			restde.setMonth(Integer.toString(cal.get(Calendar.MONTH)+1));
		}
		if(restde.getDay()==null || restde.getDay().equals("")){
			restde.setDay(Integer.toString(cal.get(Calendar.DATE)));
		}

		int iYear  = Integer.parseInt(restde.getYear());
		int iMonth = Integer.parseInt(restde.getMonth());
		int iDay   = Integer.parseInt(restde.getDay());

		if (iMonth<1){
			iYear--;
			iMonth = 12;
		}
		if (iMonth>12){
			iYear++;
			iMonth = 1;
		}
		if (iYear<1){
			iYear = 1;
			iMonth = 1;
		}
		if (iYear>9999){
			iYear = 9999;
			iMonth = 12;
		}
		restde.setYear(Integer.toString(iYear));
		restde.setMonth(Integer.toString(iMonth));

		cal.set(iYear,iMonth-1,iDay);
		restde.setStartWeekMonth(cal.get(Calendar.DAY_OF_WEEK));

		cal.set(iYear,iMonth-1,Integer.parseInt(restde.getDay()));
		restde.setLastDayMonth(cal.getActualMaximum(Calendar.DAY_OF_MONTH));

		restde.setYear(Integer.toString(cal.get(cal.YEAR)));
		restde.setMonth(Integer.toString(cal.get(cal.MONTH)+1));
		restde.setDay(Integer.toString(cal.get(cal.DAY_OF_MONTH)));
		restde.setWeek(cal.get(cal.DAY_OF_WEEK));
		restde.setLastDayMonth(cal.getActualMaximum(Calendar.DATE));

		List<?> CalInfoList          = restdeManageService.selectNormalDayCal(restde);
        List<?> NormalWeekRestdeList = restdeManageService.selectNormalDayRestde(restde);

        model.addAttribute("resultList", CalInfoList);
        model.addAttribute("RestdeList", NormalWeekRestdeList);

		return "egovframework/com/sym/cal/EgovNormalDayCalendar";
	}

	/**
	 * 일반달력 주간
	 * @param restde
	 * @param model
	 * @return "egovframework/com/sym/cal/EgovNormalWeekCalendar"
	 * @throws Exception
	 */
	@SuppressWarnings("static-access")
	@RequestMapping(value="/sym/cal/EgovNormalWeekCalendar.do")
 	public String selectNormalWeekCalendar (Restde restde, BindingResult bindingResult
 			, ModelMap model
 			) throws Exception {

		//2011.10.18 달력 출력을 위해 필요한 숫자 이외의 값을 사용하는 경우 체크
		bindingResult = checkRestdeWithValidator(restde, bindingResult);

		if(bindingResult.hasErrors()){

			return "egovframework/com/cmm/error/dataAccessFailure";

		}

		Calendar cal = Calendar.getInstance();

		if(restde.getYear()==null || restde.getYear().equals("")){
			restde.setYear(Integer.toString(cal.get(Calendar.YEAR)));
		}
		if(restde.getMonth()==null || restde.getMonth().equals("")){
			restde.setMonth(Integer.toString(cal.get(Calendar.MONTH)+1));
		}
		if(restde.getDay()==null || restde.getDay().equals("")){
			restde.setDay(Integer.toString(cal.get(Calendar.DATE)));
		}

		int iYear  = Integer.parseInt(restde.getYear());
		int iMonth = Integer.parseInt(restde.getMonth());

		if (iMonth<1){
			iYear--;
			iMonth = 12;
		}
		if (iMonth>12){
			iYear++;
			iMonth = 1;
		}
		if (iYear<1){
			iYear = 1;
			iMonth = 1;
		}
		if (iYear>9999){
			iYear = 9999;
			iMonth = 12;
		}
		restde.setYear(Integer.toString(iYear));
		restde.setMonth(Integer.toString(iMonth));

		cal.set(iYear,iMonth-1,1);
		restde.setStartWeekMonth(cal.get(Calendar.DAY_OF_WEEK));

		cal.set(iYear,iMonth-1,Integer.parseInt(restde.getDay()));
		restde.setLastDayMonth(cal.getActualMaximum(Calendar.DAY_OF_MONTH));

		int iStartWeek = restde.getStartWeekMonth();
		int iLastDate  = restde.getLastDayMonth();
		int iDayWeek  = cal.get(Calendar.DAY_OF_WEEK);

		int iMaxWeeks = (int)Math.floor(iLastDate/7);
		iMaxWeeks = iMaxWeeks + (int)Math.ceil(((iLastDate - iMaxWeeks * 7) + iStartWeek - 1) / 7.0);
		restde.setMaxWeeks(iMaxWeeks);

		if (iMaxWeeks < restde.getWeeks()) {
			restde.setWeeks(iMaxWeeks);
		}

		Restde vo = new Restde();
		Calendar weekCal = Calendar.getInstance();
		weekCal.setTime(cal.getTime());

		if(restde.getWeeks()!=0){
			weekCal.set(weekCal.DATE, (restde.getWeeks() - 1) * 7 + 1);
			if(restde.getWeeks()>1){
				iDayWeek  = weekCal.get(weekCal.DAY_OF_WEEK);
				weekCal.add(weekCal.DATE, (-1)*(iDayWeek-1));
			}
			restde.setDay(Integer.toString(weekCal.get(weekCal.DAY_OF_MONTH)+1));
		}

		iDayWeek  = weekCal.get(weekCal.DAY_OF_WEEK);

		// 일요일
		weekCal.add(weekCal.DATE, (-1)*(iDayWeek-1));
		vo.setYear(Integer.toString(weekCal.get(weekCal.YEAR)));
		vo.setMonth(Integer.toString(weekCal.get(weekCal.MONTH)+1));
		vo.setDay(Integer.toString(weekCal.get(weekCal.DAY_OF_MONTH)));
		vo.setWeek(weekCal.get(weekCal.DAY_OF_WEEK));
		List<?> CalInfoList_1          = restdeManageService.selectNormalDayCal(vo);
        List<?> NormalWeekRestdeList_1 = restdeManageService.selectNormalDayRestde(vo);

		// 월요일
		weekCal.add(weekCal.DATE, 1);
		vo.setYear(Integer.toString(weekCal.get(weekCal.YEAR)));
		vo.setMonth(Integer.toString(weekCal.get(weekCal.MONTH)+1));
		vo.setDay(Integer.toString(weekCal.get(weekCal.DAY_OF_MONTH)));
		vo.setWeek(weekCal.get(weekCal.DAY_OF_WEEK));
		List<?> CalInfoList_2          = restdeManageService.selectNormalDayCal(vo);
        List<?> NormalWeekRestdeList_2 = restdeManageService.selectNormalDayRestde(vo);

		// 화요일
		weekCal.add(weekCal.DATE, 1);
		vo.setYear(Integer.toString(weekCal.get(weekCal.YEAR)));
		vo.setMonth(Integer.toString(weekCal.get(weekCal.MONTH)+1));
		vo.setDay(Integer.toString(weekCal.get(weekCal.DAY_OF_MONTH)));
		vo.setWeek(weekCal.get(weekCal.DAY_OF_WEEK));
		List<?> CalInfoList_3          = restdeManageService.selectNormalDayCal(vo);
        List<?> NormalWeekRestdeList_3 = restdeManageService.selectNormalDayRestde(vo);

		// 수요일
		weekCal.add(weekCal.DATE, 1);
		vo.setYear(Integer.toString(weekCal.get(weekCal.YEAR)));
		vo.setMonth(Integer.toString(weekCal.get(weekCal.MONTH)+1));
		vo.setDay(Integer.toString(weekCal.get(weekCal.DAY_OF_MONTH)));
		vo.setWeek(weekCal.get(weekCal.DAY_OF_WEEK));
		List<?> CalInfoList_4          = restdeManageService.selectNormalDayCal(vo);
        List<?> NormalWeekRestdeList_4 = restdeManageService.selectNormalDayRestde(vo);

		// 목요일
		weekCal.add(weekCal.DATE, 1);
		vo.setYear(Integer.toString(weekCal.get(weekCal.YEAR)));
		vo.setMonth(Integer.toString(weekCal.get(weekCal.MONTH)+1));
		vo.setDay(Integer.toString(weekCal.get(weekCal.DAY_OF_MONTH)));
		vo.setWeek(weekCal.get(weekCal.DAY_OF_WEEK));
		List<?> CalInfoList_5          = restdeManageService.selectNormalDayCal(vo);
        List<?> NormalWeekRestdeList_5 = restdeManageService.selectNormalDayRestde(vo);

		// 금요일
		weekCal.add(weekCal.DATE, 1);
		vo.setYear(Integer.toString(weekCal.get(weekCal.YEAR)));
		vo.setMonth(Integer.toString(weekCal.get(weekCal.MONTH)+1));
		vo.setDay(Integer.toString(weekCal.get(weekCal.DAY_OF_MONTH)));
		vo.setWeek(weekCal.get(weekCal.DAY_OF_WEEK));
		List<?> CalInfoList_6          = restdeManageService.selectNormalDayCal(vo);
        List<?> NormalWeekRestdeList_6 = restdeManageService.selectNormalDayRestde(vo);

		// 토요일
		weekCal.add(weekCal.DATE, 1);
		vo.setYear(Integer.toString(weekCal.get(weekCal.YEAR)));
		vo.setMonth(Integer.toString(weekCal.get(weekCal.MONTH)+1));
		vo.setDay(Integer.toString(weekCal.get(weekCal.DAY_OF_MONTH)));
		vo.setWeek(weekCal.get(weekCal.DAY_OF_WEEK));
		List<?> CalInfoList_7          = restdeManageService.selectNormalDayCal(vo);
        List<?> NormalWeekRestdeList_7 = restdeManageService.selectNormalDayRestde(vo);

		model.addAttribute("resultList_1", CalInfoList_1);
        model.addAttribute("resultList_2", CalInfoList_2);
        model.addAttribute("resultList_3", CalInfoList_3);
        model.addAttribute("resultList_4", CalInfoList_4);
        model.addAttribute("resultList_5", CalInfoList_5);
        model.addAttribute("resultList_6", CalInfoList_6);
        model.addAttribute("resultList_7", CalInfoList_7);
        model.addAttribute("RestdeList_1", NormalWeekRestdeList_1);
        model.addAttribute("RestdeList_2", NormalWeekRestdeList_2);
        model.addAttribute("RestdeList_3", NormalWeekRestdeList_3);
        model.addAttribute("RestdeList_4", NormalWeekRestdeList_4);
        model.addAttribute("RestdeList_5", NormalWeekRestdeList_5);
        model.addAttribute("RestdeList_6", NormalWeekRestdeList_6);
        model.addAttribute("RestdeList_7", NormalWeekRestdeList_7);

		List<?> CalInfoList = restdeManageService.selectNormalDayCal(restde);
        model.addAttribute("resultList", CalInfoList);

        return "egovframework/com/sym/cal/EgovNormalWeekCalendar";
	}

	/**
	 * 일반달력 월간
	 * @param restde
	 * @param model
	 * @return "egovframework/com/sym/cal/EgovNormalMonthCalendar"
	 * @throws Exception
	 */
	@RequestMapping(value="/sym/cal/EgovNormalMonthCalendar.do")
 	public String selectNormalMonthCalendar (Restde restde, BindingResult bindingResult
 			, ModelMap model
 			) throws Exception {

		//2011.10.18 달력 출력을 위해 필요한 숫자 이외의 값을 사용하는 경우 체크
		bindingResult = checkRestdeWithValidator(restde, bindingResult);

		if(bindingResult.hasErrors()){

			return "egovframework/com/cmm/error/dataAccessFailure";

		}

		Calendar cal = Calendar.getInstance();

		if(restde.getYear()==null || restde.getYear().equals("")){
			restde.setYear(Integer.toString(cal.get(Calendar.YEAR)));
		}
		if(restde.getMonth()==null || restde.getMonth().equals("")){
			restde.setMonth(Integer.toString(cal.get(Calendar.MONTH)+1));
		}
		int iYear  = Integer.parseInt(restde.getYear());
		int iMonth = Integer.parseInt(restde.getMonth());

		if (iMonth<1){
			iYear--;
			iMonth = 12;
		}
		if (iMonth>12){
			iYear++;
			iMonth = 1;
		}
		if (iYear<1){
			iYear = 1;
			iMonth = 1;
		}
		if (iYear>9999){
			iYear = 9999;
			iMonth = 12;
		}
		restde.setYear(Integer.toString(iYear));
		restde.setMonth(Integer.toString(iMonth));

		cal.set(iYear,iMonth-1,1);

		restde.setStartWeekMonth(cal.get(Calendar.DAY_OF_WEEK));
		restde.setLastDayMonth(cal.getActualMaximum(Calendar.DATE));

        List<?> CalInfoList = restdeManageService.selectNormalRestdePopup(restde);


        List<?> NormalMonthRestdeList = restdeManageService.selectNormalMonthRestde(restde);

        model.addAttribute("resultList", CalInfoList);
        model.addAttribute("RestdeList", NormalMonthRestdeList);

        return "egovframework/com/sym/cal/EgovNormalMonthCalendar";
	}

	/**
	 * 일반달력 연간
	 * @param restde
	 * @param model
	 * @return "egovframework/com/sym/cal/EgovNormalYearCalendar"
	 * @throws Exception
	 */
	@RequestMapping(value="/sym/cal/EgovNormalYearCalendar.do")
 	public String selectNormalYearCalendar (Restde restde, BindingResult bindingResult
 			, ModelMap model
 			) throws Exception {

		//2011.10.18 달력 출력을 위해 필요한 숫자 이외의 값을 사용하는 경우 체크
		bindingResult = checkRestdeWithValidator(restde, bindingResult);

		if(bindingResult.hasErrors()){

			return "egovframework/com/cmm/error/dataAccessFailure";

		}

		Calendar cal = Calendar.getInstance();

		if(restde.getYear()==null || restde.getYear().equals("")){
			restde.setYear(Integer.toString(cal.get(Calendar.YEAR)));
		}
		if(restde.getMonth()==null || restde.getMonth().equals("")){
			restde.setMonth(Integer.toString(cal.get(Calendar.MONTH)+1));
		}
		int iYear  = Integer.parseInt(restde.getYear());
		int iMonth = Integer.parseInt(restde.getMonth());

		if (iMonth<1){
			iYear--;
			iMonth = 12;
		}
		if (iMonth>12){
			iYear++;
			iMonth = 1;
		}
		if (iYear<1){
			iYear = 1;
			iMonth = 1;
		}
		if (iYear>9999){
			iYear = 9999;
			iMonth = 12;
		}
		restde.setYear(Integer.toString(iYear));

		/* 월별확인 */

		/* 1월 */
		iMonth = 1;
		restde.setMonth(Integer.toString(iMonth));
		cal.set(iYear,iMonth-1,1);
		restde.setStartWeekMonth(cal.get(Calendar.DAY_OF_WEEK));
		restde.setLastDayMonth(cal.getActualMaximum(Calendar.DATE));
        List<?> CalInfoList_1 = restdeManageService.selectNormalRestdePopup(restde);
        List<?> NormalMonthRestdeList_1 = restdeManageService.selectNormalMonthRestde(restde);

		/* 2월 */
		iMonth = 2;
		restde.setMonth(Integer.toString(iMonth));
		cal.set(iYear,iMonth-1,1);
		restde.setStartWeekMonth(cal.get(Calendar.DAY_OF_WEEK));
		restde.setLastDayMonth(cal.getActualMaximum(Calendar.DATE));
        List<?> CalInfoList_2 = restdeManageService.selectNormalRestdePopup(restde);
        List<?> NormalMonthRestdeList_2 = restdeManageService.selectNormalMonthRestde(restde);

		/* 3월 */
		iMonth = 3;
		restde.setMonth(Integer.toString(iMonth));
		cal.set(iYear,iMonth-1,1);
		restde.setStartWeekMonth(cal.get(Calendar.DAY_OF_WEEK));
		restde.setLastDayMonth(cal.getActualMaximum(Calendar.DATE));
        List<?> CalInfoList_3 = restdeManageService.selectNormalRestdePopup(restde);
        List<?> NormalMonthRestdeList_3 = restdeManageService.selectNormalMonthRestde(restde);

		/* 4월 */
		iMonth = 4;
		restde.setMonth(Integer.toString(iMonth));
		cal.set(iYear,iMonth-1,1);
		restde.setStartWeekMonth(cal.get(Calendar.DAY_OF_WEEK));
		restde.setLastDayMonth(cal.getActualMaximum(Calendar.DATE));
        List<?> CalInfoList_4 = restdeManageService.selectNormalRestdePopup(restde);
        List<?> NormalMonthRestdeList_4 = restdeManageService.selectNormalMonthRestde(restde);

		/* 5월 */
		iMonth = 5;
		restde.setMonth(Integer.toString(iMonth));
		cal.set(iYear,iMonth-1,1);
		restde.setStartWeekMonth(cal.get(Calendar.DAY_OF_WEEK));
		restde.setLastDayMonth(cal.getActualMaximum(Calendar.DATE));
        List<?> CalInfoList_5 = restdeManageService.selectNormalRestdePopup(restde);
        List<?> NormalMonthRestdeList_5 = restdeManageService.selectNormalMonthRestde(restde);

		/* 6월 */
		iMonth = 6;
		restde.setMonth(Integer.toString(iMonth));
		cal.set(iYear,iMonth-1,1);
		restde.setStartWeekMonth(cal.get(Calendar.DAY_OF_WEEK));
		restde.setLastDayMonth(cal.getActualMaximum(Calendar.DATE));
        List<?> CalInfoList_6 = restdeManageService.selectNormalRestdePopup(restde);
        List<?> NormalMonthRestdeList_6 = restdeManageService.selectNormalMonthRestde(restde);

		/* 7월 */
		iMonth = 7;
		restde.setMonth(Integer.toString(iMonth));
		cal.set(iYear,iMonth-1,1);
		restde.setStartWeekMonth(cal.get(Calendar.DAY_OF_WEEK));
		restde.setLastDayMonth(cal.getActualMaximum(Calendar.DATE));
        List<?> CalInfoList_7 = restdeManageService.selectNormalRestdePopup(restde);
        List<?> NormalMonthRestdeList_7 = restdeManageService.selectNormalMonthRestde(restde);

		/* 8월 */
		iMonth = 8;
		restde.setMonth(Integer.toString(iMonth));
		cal.set(iYear,iMonth-1,1);
		restde.setStartWeekMonth(cal.get(Calendar.DAY_OF_WEEK));
		restde.setLastDayMonth(cal.getActualMaximum(Calendar.DATE));
        List<?> CalInfoList_8 = restdeManageService.selectNormalRestdePopup(restde);
        List<?> NormalMonthRestdeList_8 = restdeManageService.selectNormalMonthRestde(restde);

		/* 9월 */
		iMonth = 9;
		restde.setMonth(Integer.toString(iMonth));
		cal.set(iYear,iMonth-1,1);
		restde.setStartWeekMonth(cal.get(Calendar.DAY_OF_WEEK));
		restde.setLastDayMonth(cal.getActualMaximum(Calendar.DATE));
        List<?> CalInfoList_9 = restdeManageService.selectNormalRestdePopup(restde);
        List<?> NormalMonthRestdeList_9 = restdeManageService.selectNormalMonthRestde(restde);

		/* 10월 */
		iMonth = 10;
		restde.setMonth(Integer.toString(iMonth));
		cal.set(iYear,iMonth-1,1);
		restde.setStartWeekMonth(cal.get(Calendar.DAY_OF_WEEK));
		restde.setLastDayMonth(cal.getActualMaximum(Calendar.DATE));
        List<?> CalInfoList_10 = restdeManageService.selectNormalRestdePopup(restde);
        List<?> NormalMonthRestdeList_10 = restdeManageService.selectNormalMonthRestde(restde);

		/* 11월 */
		iMonth = 11;
		restde.setMonth(Integer.toString(iMonth));
		cal.set(iYear,iMonth-1,1);
		restde.setStartWeekMonth(cal.get(Calendar.DAY_OF_WEEK));
		restde.setLastDayMonth(cal.getActualMaximum(Calendar.DATE));
        List<?> CalInfoList_11 = restdeManageService.selectNormalRestdePopup(restde);
        List<?> NormalMonthRestdeList_11 = restdeManageService.selectNormalMonthRestde(restde);

		/* 12월 */
		iMonth = 12;
		restde.setMonth(Integer.toString(iMonth));
		cal.set(iYear,iMonth-1,1);
		restde.setStartWeekMonth(cal.get(Calendar.DAY_OF_WEEK));
		restde.setLastDayMonth(cal.getActualMaximum(Calendar.DATE));
        List<?> CalInfoList_12 = restdeManageService.selectNormalRestdePopup(restde);
        List<?> NormalMonthRestdeList_12 = restdeManageService.selectNormalMonthRestde(restde);

        model.addAttribute("resultList_1" , CalInfoList_1 );
        model.addAttribute("resultList_2" , CalInfoList_2 );
        model.addAttribute("resultList_3" , CalInfoList_3 );
        model.addAttribute("resultList_4" , CalInfoList_4 );
        model.addAttribute("resultList_5" , CalInfoList_5 );
        model.addAttribute("resultList_6" , CalInfoList_6 );
        model.addAttribute("resultList_7" , CalInfoList_7 );
        model.addAttribute("resultList_8" , CalInfoList_8 );
        model.addAttribute("resultList_9" , CalInfoList_9 );
        model.addAttribute("resultList_10", CalInfoList_10);
        model.addAttribute("resultList_11", CalInfoList_11);
        model.addAttribute("resultList_12", CalInfoList_12);
        model.addAttribute("RestdeList_1" , NormalMonthRestdeList_1 );
        model.addAttribute("RestdeList_2" , NormalMonthRestdeList_2 );
        model.addAttribute("RestdeList_3" , NormalMonthRestdeList_3 );
        model.addAttribute("RestdeList_4" , NormalMonthRestdeList_4 );
        model.addAttribute("RestdeList_5" , NormalMonthRestdeList_5 );
        model.addAttribute("RestdeList_6" , NormalMonthRestdeList_6 );
        model.addAttribute("RestdeList_7" , NormalMonthRestdeList_7 );
        model.addAttribute("RestdeList_8" , NormalMonthRestdeList_8 );
        model.addAttribute("RestdeList_9" , NormalMonthRestdeList_9 );
        model.addAttribute("RestdeList_10", NormalMonthRestdeList_10);
        model.addAttribute("RestdeList_11", NormalMonthRestdeList_11);
        model.addAttribute("RestdeList_12", NormalMonthRestdeList_12);

        return "egovframework/com/sym/cal/EgovNormalYearCalendar";
	}


	/**
	 * 행정달력 일간
	 * @param restde
	 * @param model
	 * @return "egovframework/com/sym/cal/EgovAdministDayCalendar"
	 * @throws Exception
	 */
	@SuppressWarnings("static-access")
	@RequestMapping(value="/sym/cal/EgovAdministDayCalendar.do")
 	public String selectAdministDayCalendar (Restde restde, BindingResult bindingResult
 			, ModelMap model
 			) throws Exception {

		//2011.10.18 달력 출력을 위해 필요한 숫자 이외의 값을 사용하는 경우 체크
		bindingResult = checkRestdeWithValidator(restde, bindingResult);

		if(bindingResult.hasErrors()){

			return "egovframework/com/cmm/error/dataAccessFailure";

		}

		Calendar cal = Calendar.getInstance();


		if(restde.getYear()==null || restde.getYear().equals("")){
			restde.setYear(Integer.toString(cal.get(Calendar.YEAR)));
		}
		if(restde.getMonth()==null || restde.getMonth().equals("")){
			restde.setMonth(Integer.toString(cal.get(Calendar.MONTH)+1));
		}
		if(restde.getDay()==null || restde.getDay().equals("")){
			restde.setDay(Integer.toString(cal.get(Calendar.DATE)));
		}

		int iYear  = Integer.parseInt(restde.getYear());
		int iMonth = Integer.parseInt(restde.getMonth());
		int iDay   = Integer.parseInt(restde.getDay());

		if (iMonth<1){
			iYear--;
			iMonth = 12;
		}
		if (iMonth>12){
			iYear++;
			iMonth = 1;
		}
		if (iYear<1){
			iYear = 1;
			iMonth = 1;
		}
		if (iYear>9999){
			iYear = 9999;
			iMonth = 12;
		}
		restde.setYear(Integer.toString(iYear));
		restde.setMonth(Integer.toString(iMonth));

		cal.set(iYear,iMonth-1,iDay);
		restde.setStartWeekMonth(cal.get(Calendar.DAY_OF_WEEK));

		cal.set(iYear,iMonth-1,Integer.parseInt(restde.getDay()));
		restde.setLastDayMonth(cal.getActualMaximum(Calendar.DAY_OF_MONTH));

		restde.setYear(Integer.toString(cal.get(cal.YEAR)));
		restde.setMonth(Integer.toString(cal.get(cal.MONTH)+1));
		restde.setDay(Integer.toString(cal.get(cal.DAY_OF_MONTH)));
		restde.setWeek(cal.get(cal.DAY_OF_WEEK));
		restde.setLastDayMonth(cal.getActualMaximum(Calendar.DATE));

		List<?> CalInfoList          = restdeManageService.selectAdministDayCal(restde);
        List<?> AdministWeekRestdeList = restdeManageService.selectAdministDayRestde(restde);

        model.addAttribute("resultList", CalInfoList);
        model.addAttribute("RestdeList", AdministWeekRestdeList);

		return "egovframework/com/sym/cal/EgovAdministDayCalendar";
	}


	/**
	 * 행정달력 주간
	 * @param restde
	 * @param model
	 * @return "egovframework/com/sym/cal/EgovAdministWeekCalendar"
	 * @throws Exception
	 */
	@SuppressWarnings("static-access")
	@RequestMapping(value="/sym/cal/EgovAdministWeekCalendar.do")
 	public String selectAdministWeekCalendar (Restde restde, BindingResult bindingResult
 			, ModelMap model
 			) throws Exception {

		//2011.10.18 달력 출력을 위해 필요한 숫자 이외의 값을 사용하는 경우 체크
		bindingResult = checkRestdeWithValidator(restde, bindingResult);

		if(bindingResult.hasErrors()){

			return "egovframework/com/cmm/error/dataAccessFailure";

		}

		Calendar cal = Calendar.getInstance();

		if(restde.getYear()==null || restde.getYear().equals("")){
			restde.setYear(Integer.toString(cal.get(Calendar.YEAR)));
		}
		if(restde.getMonth()==null || restde.getMonth().equals("")){
			restde.setMonth(Integer.toString(cal.get(Calendar.MONTH)+1));
		}
		if(restde.getDay()==null || restde.getDay().equals("")){
			restde.setDay(Integer.toString(cal.get(Calendar.DATE)));
		}

		int iYear  = Integer.parseInt(restde.getYear());
		int iMonth = Integer.parseInt(restde.getMonth());

		if (iMonth<1){
			iYear--;
			iMonth = 12;
		}
		if (iMonth>12){
			iYear++;
			iMonth = 1;
		}
		if (iYear<1){
			iYear = 1;
			iMonth = 1;
		}
		if (iYear>9999){
			iYear = 9999;
			iMonth = 12;
		}
		restde.setYear(Integer.toString(iYear));
		restde.setMonth(Integer.toString(iMonth));

		cal.set(iYear,iMonth-1,1);
		restde.setStartWeekMonth(cal.get(Calendar.DAY_OF_WEEK));

		cal.set(iYear,iMonth-1,Integer.parseInt(restde.getDay()));
		restde.setLastDayMonth(cal.getActualMaximum(Calendar.DAY_OF_MONTH));

		int iStartWeek = restde.getStartWeekMonth();
		int iLastDate  = restde.getLastDayMonth();
		int iDayWeek  = cal.get(Calendar.DAY_OF_WEEK);

		int iMaxWeeks = (int)Math.floor(iLastDate/7);
		iMaxWeeks = iMaxWeeks + (int)Math.ceil(((iLastDate - iMaxWeeks * 7) + iStartWeek - 1) / 7.0);
		restde.setMaxWeeks(iMaxWeeks);

		if (iMaxWeeks < restde.getWeeks()) {
			restde.setWeeks(iMaxWeeks);
		}

		Restde vo = new Restde();
		Calendar weekCal = Calendar.getInstance();
		weekCal.setTime(cal.getTime());

		if(restde.getWeeks()!=0){
			weekCal.set(weekCal.DATE, (restde.getWeeks() - 1) * 7 + 1);
			if(restde.getWeeks()>1){
				iDayWeek  = weekCal.get(weekCal.DAY_OF_WEEK);
				weekCal.add(weekCal.DATE, (-1)*(iDayWeek-1));
			}
			restde.setDay(Integer.toString(weekCal.get(weekCal.DAY_OF_MONTH)+1));
		}
		List<?> CalInfoList = restdeManageService.selectAdministDayCal(restde);

		iDayWeek  = weekCal.get(weekCal.DAY_OF_WEEK);

		// 일요일
		weekCal.add(weekCal.DATE, (-1)*(iDayWeek-1));
		vo.setYear(Integer.toString(weekCal.get(weekCal.YEAR)));
		vo.setMonth(Integer.toString(weekCal.get(weekCal.MONTH)+1));
		vo.setDay(Integer.toString(weekCal.get(weekCal.DAY_OF_MONTH)));
		vo.setWeek(weekCal.get(weekCal.DAY_OF_WEEK));
		List<?> CalInfoList_1          = restdeManageService.selectAdministDayCal(vo);
        List<?> AdministWeekRestdeList_1 = restdeManageService.selectAdministDayRestde(vo);

		// 월요일
		weekCal.add(weekCal.DATE, 1);
		vo.setYear(Integer.toString(weekCal.get(weekCal.YEAR)));
		vo.setMonth(Integer.toString(weekCal.get(weekCal.MONTH)+1));
		vo.setDay(Integer.toString(weekCal.get(weekCal.DAY_OF_MONTH)));
		vo.setWeek(weekCal.get(weekCal.DAY_OF_WEEK));
		List<?> CalInfoList_2          = restdeManageService.selectAdministDayCal(vo);
        List<?> AdministWeekRestdeList_2 = restdeManageService.selectAdministDayRestde(vo);

		// 화요일
		weekCal.add(weekCal.DATE, 1);
		vo.setYear(Integer.toString(weekCal.get(weekCal.YEAR)));
		vo.setMonth(Integer.toString(weekCal.get(weekCal.MONTH)+1));
		vo.setDay(Integer.toString(weekCal.get(weekCal.DAY_OF_MONTH)));
		vo.setWeek(weekCal.get(weekCal.DAY_OF_WEEK));
		List<?> CalInfoList_3          = restdeManageService.selectAdministDayCal(vo);
        List<?> AdministWeekRestdeList_3 = restdeManageService.selectAdministDayRestde(vo);

		// 수요일
		weekCal.add(weekCal.DATE, 1);
		vo.setYear(Integer.toString(weekCal.get(weekCal.YEAR)));
		vo.setMonth(Integer.toString(weekCal.get(weekCal.MONTH)+1));
		vo.setDay(Integer.toString(weekCal.get(weekCal.DAY_OF_MONTH)));
		vo.setWeek(weekCal.get(weekCal.DAY_OF_WEEK));
		List<?> CalInfoList_4          = restdeManageService.selectAdministDayCal(vo);
        List<?> AdministWeekRestdeList_4 = restdeManageService.selectAdministDayRestde(vo);

		// 목요일
		weekCal.add(weekCal.DATE, 1);
		vo.setYear(Integer.toString(weekCal.get(weekCal.YEAR)));
		vo.setMonth(Integer.toString(weekCal.get(weekCal.MONTH)+1));
		vo.setDay(Integer.toString(weekCal.get(weekCal.DAY_OF_MONTH)));
		vo.setWeek(weekCal.get(weekCal.DAY_OF_WEEK));
		List<?> CalInfoList_5          = restdeManageService.selectAdministDayCal(vo);
        List<?> AdministWeekRestdeList_5 = restdeManageService.selectAdministDayRestde(vo);

		// 금요일
		weekCal.add(weekCal.DATE, 1);
		vo.setYear(Integer.toString(weekCal.get(weekCal.YEAR)));
		vo.setMonth(Integer.toString(weekCal.get(weekCal.MONTH)+1));
		vo.setDay(Integer.toString(weekCal.get(weekCal.DAY_OF_MONTH)));
		vo.setWeek(weekCal.get(weekCal.DAY_OF_WEEK));
		List<?> CalInfoList_6          = restdeManageService.selectAdministDayCal(vo);
        List<?> AdministWeekRestdeList_6 = restdeManageService.selectAdministDayRestde(vo);

		// 토요일
		weekCal.add(weekCal.DATE, 1);
		vo.setYear(Integer.toString(weekCal.get(weekCal.YEAR)));
		vo.setMonth(Integer.toString(weekCal.get(weekCal.MONTH)+1));
		vo.setDay(Integer.toString(weekCal.get(weekCal.DAY_OF_MONTH)));
		vo.setWeek(weekCal.get(weekCal.DAY_OF_WEEK));
		List<?> CalInfoList_7          = restdeManageService.selectAdministDayCal(vo);
        List<?> AdministWeekRestdeList_7 = restdeManageService.selectAdministDayRestde(vo);

        model.addAttribute("resultList_1", CalInfoList_1);
        model.addAttribute("resultList_2", CalInfoList_2);
        model.addAttribute("resultList_3", CalInfoList_3);
        model.addAttribute("resultList_4", CalInfoList_4);
        model.addAttribute("resultList_5", CalInfoList_5);
        model.addAttribute("resultList_6", CalInfoList_6);
        model.addAttribute("resultList_7", CalInfoList_7);
        model.addAttribute("RestdeList_1", AdministWeekRestdeList_1);
        model.addAttribute("RestdeList_2", AdministWeekRestdeList_2);
        model.addAttribute("RestdeList_3", AdministWeekRestdeList_3);
        model.addAttribute("RestdeList_4", AdministWeekRestdeList_4);
        model.addAttribute("RestdeList_5", AdministWeekRestdeList_5);
        model.addAttribute("RestdeList_6", AdministWeekRestdeList_6);
        model.addAttribute("RestdeList_7", AdministWeekRestdeList_7);

        model.addAttribute("resultList", CalInfoList);

		return "egovframework/com/sym/cal/EgovAdministWeekCalendar";
	}

	/**
	 * 행정달력 월간
	 * @param restde
	 * @param model
	 * @return "egovframework/com/sym/cal/EgovAdministMonthCalendar"
	 * @throws Exception
	 */
	@RequestMapping(value="/sym/cal/EgovAdministMonthCalendar.do")
 	public String selectAdministMonthCalendar (Restde restde, BindingResult bindingResult
 			, ModelMap model
 			) throws Exception {

		//2011.10.18 달력 출력을 위해 필요한 숫자 이외의 값을 사용하는 경우 체크
		bindingResult = checkRestdeWithValidator(restde, bindingResult);

		if(bindingResult.hasErrors()){

			return "egovframework/com/cmm/error/dataAccessFailure";

		}

		Calendar cal = Calendar.getInstance();

		if(restde.getYear()==null || restde.getYear().equals("")){
			restde.setYear(Integer.toString(cal.get(Calendar.YEAR)));
		}
		if(restde.getMonth()==null || restde.getMonth().equals("")){
			restde.setMonth(Integer.toString(cal.get(Calendar.MONTH)+1));
		}
		int iYear  = Integer.parseInt(restde.getYear());
		int iMonth = Integer.parseInt(restde.getMonth());

		if (iMonth<1){
			iYear--;
			iMonth = 12;
		}
		if (iMonth>12){
			iYear++;
			iMonth = 1;
		}
		if (iYear<1){
			iYear = 1;
			iMonth = 1;
		}
		if (iYear>9999){
			iYear = 9999;
			iMonth = 12;
		}
		restde.setYear(Integer.toString(iYear));
		restde.setMonth(Integer.toString(iMonth));

		cal.set(iYear,iMonth-1,1);

		restde.setStartWeekMonth(cal.get(Calendar.DAY_OF_WEEK));
		restde.setLastDayMonth(cal.getActualMaximum(Calendar.DATE));

        List<?> CalInfoList = restdeManageService.selectAdministRestdePopup(restde);


        List<?> AdministMonthRestdeList = restdeManageService.selectAdministMonthRestde(restde);

        model.addAttribute("resultList", CalInfoList);
        model.addAttribute("RestdeList", AdministMonthRestdeList);

        return "egovframework/com/sym/cal/EgovAdministMonthCalendar";
	}


	/**
	 * 행정달력 연간
	 * @param restde
	 * @param model
	 * @return "egovframework/com/sym/cal/EgovAdministYearCalendar"
	 * @throws Exception
	 */
	@RequestMapping(value="/sym/cal/EgovAdministYearCalendar.do")
 	public String selectAdministYearCalendar (Restde restde, BindingResult bindingResult
 			, ModelMap model
 			) throws Exception {

		//2011.10.18 달력 출력을 위해 필요한 숫자 이외의 값을 사용하는 경우 체크
		bindingResult = checkRestdeWithValidator(restde, bindingResult);

		if(bindingResult.hasErrors()){

			return "egovframework/com/cmm/error/dataAccessFailure";

		}

		Calendar cal = Calendar.getInstance();

		if(restde.getYear()==null || restde.getYear().equals("")){
			restde.setYear(Integer.toString(cal.get(Calendar.YEAR)));
		}
		if(restde.getMonth()==null || restde.getMonth().equals("")){
			restde.setMonth(Integer.toString(cal.get(Calendar.MONTH)+1));
		}
		int iYear  = Integer.parseInt(restde.getYear());
		int iMonth = Integer.parseInt(restde.getMonth());

		if (iMonth<1){
			iYear--;
			iMonth = 12;
		}
		if (iMonth>12){
			iYear++;
			iMonth = 1;
		}
		if (iYear<1){
			iYear = 1;
			iMonth = 1;
		}
		if (iYear>9999){
			iYear = 9999;
			iMonth = 12;
		}
		restde.setYear(Integer.toString(iYear));

		/* 월별확인 */

		/* 1월 */
		iMonth = 1;
		restde.setMonth(Integer.toString(iMonth));
		cal.set(iYear,iMonth-1,1);
		restde.setStartWeekMonth(cal.get(Calendar.DAY_OF_WEEK));
		restde.setLastDayMonth(cal.getActualMaximum(Calendar.DATE));
        List<?> CalInfoList_1 = restdeManageService.selectAdministRestdePopup(restde);
        List<?> AdministMonthRestdeList_1 = restdeManageService.selectAdministMonthRestde(restde);

		/* 2월 */
		iMonth = 2;
		restde.setMonth(Integer.toString(iMonth));
		cal.set(iYear,iMonth-1,1);
		restde.setStartWeekMonth(cal.get(Calendar.DAY_OF_WEEK));
		restde.setLastDayMonth(cal.getActualMaximum(Calendar.DATE));
        List<?> CalInfoList_2 = restdeManageService.selectAdministRestdePopup(restde);
        List<?> AdministMonthRestdeList_2 = restdeManageService.selectAdministMonthRestde(restde);

		/* 3월 */
		iMonth = 3;
		restde.setMonth(Integer.toString(iMonth));
		cal.set(iYear,iMonth-1,1);
		restde.setStartWeekMonth(cal.get(Calendar.DAY_OF_WEEK));
		restde.setLastDayMonth(cal.getActualMaximum(Calendar.DATE));
        List<?> CalInfoList_3 = restdeManageService.selectAdministRestdePopup(restde);
        List<?> AdministMonthRestdeList_3 = restdeManageService.selectAdministMonthRestde(restde);

		/* 4월 */
		iMonth = 4;
		restde.setMonth(Integer.toString(iMonth));
		cal.set(iYear,iMonth-1,1);
		restde.setStartWeekMonth(cal.get(Calendar.DAY_OF_WEEK));
		restde.setLastDayMonth(cal.getActualMaximum(Calendar.DATE));
        List<?> CalInfoList_4 = restdeManageService.selectAdministRestdePopup(restde);
        List<?> AdministMonthRestdeList_4 = restdeManageService.selectAdministMonthRestde(restde);

		/* 5월 */
		iMonth = 5;
		restde.setMonth(Integer.toString(iMonth));
		cal.set(iYear,iMonth-1,1);
		restde.setStartWeekMonth(cal.get(Calendar.DAY_OF_WEEK));
		restde.setLastDayMonth(cal.getActualMaximum(Calendar.DATE));
        List<?> CalInfoList_5 = restdeManageService.selectAdministRestdePopup(restde);
        List<?> AdministMonthRestdeList_5 = restdeManageService.selectAdministMonthRestde(restde);

		/* 6월 */
		iMonth = 6;
		restde.setMonth(Integer.toString(iMonth));
		cal.set(iYear,iMonth-1,1);
		restde.setStartWeekMonth(cal.get(Calendar.DAY_OF_WEEK));
		restde.setLastDayMonth(cal.getActualMaximum(Calendar.DATE));
        List<?> CalInfoList_6 = restdeManageService.selectAdministRestdePopup(restde);
        List<?> AdministMonthRestdeList_6 = restdeManageService.selectAdministMonthRestde(restde);

		/* 7월 */
		iMonth = 7;
		restde.setMonth(Integer.toString(iMonth));
		cal.set(iYear,iMonth-1,1);
		restde.setStartWeekMonth(cal.get(Calendar.DAY_OF_WEEK));
		restde.setLastDayMonth(cal.getActualMaximum(Calendar.DATE));
        List<?> CalInfoList_7 = restdeManageService.selectAdministRestdePopup(restde);
        List<?> AdministMonthRestdeList_7 = restdeManageService.selectAdministMonthRestde(restde);

		/* 8월 */
		iMonth = 8;
		restde.setMonth(Integer.toString(iMonth));
		cal.set(iYear,iMonth-1,1);
		restde.setStartWeekMonth(cal.get(Calendar.DAY_OF_WEEK));
		restde.setLastDayMonth(cal.getActualMaximum(Calendar.DATE));
        List<?> CalInfoList_8 = restdeManageService.selectAdministRestdePopup(restde);
        List<?> AdministMonthRestdeList_8 = restdeManageService.selectAdministMonthRestde(restde);

		/* 9월 */
		iMonth = 9;
		restde.setMonth(Integer.toString(iMonth));
		cal.set(iYear,iMonth-1,1);
		restde.setStartWeekMonth(cal.get(Calendar.DAY_OF_WEEK));
		restde.setLastDayMonth(cal.getActualMaximum(Calendar.DATE));
        List<?> CalInfoList_9 = restdeManageService.selectAdministRestdePopup(restde);
        List<?> AdministMonthRestdeList_9 = restdeManageService.selectAdministMonthRestde(restde);

		/* 10월 */
		iMonth = 10;
		restde.setMonth(Integer.toString(iMonth));
		cal.set(iYear,iMonth-1,1);
		restde.setStartWeekMonth(cal.get(Calendar.DAY_OF_WEEK));
		restde.setLastDayMonth(cal.getActualMaximum(Calendar.DATE));
        List<?> CalInfoList_10 = restdeManageService.selectAdministRestdePopup(restde);
        List<?> AdministMonthRestdeList_10 = restdeManageService.selectAdministMonthRestde(restde);

		/* 11월 */
		iMonth = 11;
		restde.setMonth(Integer.toString(iMonth));
		cal.set(iYear,iMonth-1,1);
		restde.setStartWeekMonth(cal.get(Calendar.DAY_OF_WEEK));
		restde.setLastDayMonth(cal.getActualMaximum(Calendar.DATE));
        List<?> CalInfoList_11 = restdeManageService.selectAdministRestdePopup(restde);
        List<?> AdministMonthRestdeList_11 = restdeManageService.selectAdministMonthRestde(restde);

		/* 12월 */
		iMonth = 12;
		restde.setMonth(Integer.toString(iMonth));
		cal.set(iYear,iMonth-1,1);
		restde.setStartWeekMonth(cal.get(Calendar.DAY_OF_WEEK));
		restde.setLastDayMonth(cal.getActualMaximum(Calendar.DATE));
        List<?> CalInfoList_12 = restdeManageService.selectAdministRestdePopup(restde);
        List<?> AdministMonthRestdeList_12 = restdeManageService.selectAdministMonthRestde(restde);

        model.addAttribute("resultList_1" , CalInfoList_1 );
        model.addAttribute("resultList_2" , CalInfoList_2 );
        model.addAttribute("resultList_3" , CalInfoList_3 );
        model.addAttribute("resultList_4" , CalInfoList_4 );
        model.addAttribute("resultList_5" , CalInfoList_5 );
        model.addAttribute("resultList_6" , CalInfoList_6 );
        model.addAttribute("resultList_7" , CalInfoList_7 );
        model.addAttribute("resultList_8" , CalInfoList_8 );
        model.addAttribute("resultList_9" , CalInfoList_9 );
        model.addAttribute("resultList_10", CalInfoList_10);
        model.addAttribute("resultList_11", CalInfoList_11);
        model.addAttribute("resultList_12", CalInfoList_12);
        model.addAttribute("RestdeList_1" , AdministMonthRestdeList_1 );
        model.addAttribute("RestdeList_2" , AdministMonthRestdeList_2 );
        model.addAttribute("RestdeList_3" , AdministMonthRestdeList_3 );
        model.addAttribute("RestdeList_4" , AdministMonthRestdeList_4 );
        model.addAttribute("RestdeList_5" , AdministMonthRestdeList_5 );
        model.addAttribute("RestdeList_6" , AdministMonthRestdeList_6 );
        model.addAttribute("RestdeList_7" , AdministMonthRestdeList_7 );
        model.addAttribute("RestdeList_8" , AdministMonthRestdeList_8 );
        model.addAttribute("RestdeList_9" , AdministMonthRestdeList_9 );
        model.addAttribute("RestdeList_10", AdministMonthRestdeList_10);
        model.addAttribute("RestdeList_11", AdministMonthRestdeList_11);
        model.addAttribute("RestdeList_12", AdministMonthRestdeList_12);

        return "egovframework/com/sym/cal/EgovAdministYearCalendar";
	}


	/**
	 * 휴일을 삭제한다.
	 * @param loginVO
	 * @param restde
	 * @param model
	 * @return "forward:/sym/cal/EgovRestdeList.do"
	 * @throws Exception
	 */
    @RequestMapping(value="/sym/cal/EgovRestdeRemove.do")
	public String deleteRestde (@ModelAttribute("loginVO") LoginVO loginVO
			, Restde restde
			, ModelMap model
			) throws Exception {
    	restdeManageService.deleteRestde(restde);
        return "forward:/sym/cal/EgovRestdeList.do";
	}


    /**
     * 휴일을 등록한다.
     * @param loginVO
     * @param restde
     * @param bindingResult
     * @param model
     * @return "egovframework/com/sym/cal/EgovRestdeRegist"
     * @throws Exception
     */
    @RequestMapping(value="/sym/cal/EgovRestdeRegist.do")
	public String insertRestde (@ModelAttribute("loginVO") LoginVO loginVO
			, @ModelAttribute("restde") Restde restde
			, BindingResult bindingResult
			, ModelMap model
			) throws Exception {
    	if   (restde.getRestdeDe() == null
    		||restde.getRestdeDe().equals("")) {

    		ComDefaultCodeVO vo = new ComDefaultCodeVO();
    		vo.setCodeId("COM017");
            List<?> restdeCodeList = cmmUseService.selectCmmCodeDetail(vo);
            model.addAttribute("restdeCode", restdeCodeList);

            return "egovframework/com/sym/cal/EgovRestdeRegist";
    	}

        beanValidator.validate(restde, bindingResult);
		if (bindingResult.hasErrors()){
            return "egovframework/com/sym/cal/EgovRestdeRegist";
		}

    	restde.setRestdeNo(idgenService.getNextIntegerId()%1000000);
    	restde.setFrstRegisterId(loginVO.getUniqId());

    	restdeManageService.insertRestde(restde);
        return "forward:/sym/cal/EgovRestdeList.do";
    }


    /**
     * 휴일 세부내역을 조회한다.
     * @param loginVO
     * @param restde
     * @param model
     * @return "egovframework/com/sym/cal/EgovRestdeDetail"
     * @throws Exception
     */
	@RequestMapping(value="/sym/cal/EgovRestdeDetail.do")
 	public String selectRestdeDetail (@ModelAttribute("loginVO") LoginVO loginVO
 			, Restde restde
 			, ModelMap model
 			) throws Exception {
		Restde vo = restdeManageService.selectRestdeDetail(restde);
		model.addAttribute("result", vo);

		return "egovframework/com/sym/cal/EgovRestdeDetail";
	}

    /**
	 * 휴일 리스트를 조회한다.
     * @param loginVO
     * @param searchVO
     * @param model
     * @return "egovframework/com/sym/cal/EgovRestdeList"
     * @throws Exception
     */
	@IncludedInfo(name="공휴일관리(달력)", listUrl="/sym/cal/EgovRestdeList.do", order = 1300 ,gid = 90)
    @RequestMapping(value="/sym/cal/EgovRestdeList.do")
	public String selectRestdeList (@ModelAttribute("loginVO") LoginVO loginVO
			, @ModelAttribute("searchVO") RestdeVO searchVO
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

        List<?> CmmnCodeList = restdeManageService.selectRestdeList(searchVO);
        model.addAttribute("resultList", CmmnCodeList);

        int totCnt = restdeManageService.selectRestdeListTotCnt(searchVO);
		paginationInfo.setTotalRecordCount(totCnt);
        model.addAttribute("paginationInfo", paginationInfo);

        return "egovframework/com/sym/cal/EgovRestdeList";
	}

    /**
	 * 휴일을 수정한다.
     * @param loginVO
     * @param restde
     * @param bindingResult
     * @param commandMap
     * @param model
     * @return "egovframework/com/sym/cal/EgovRestdeModify"
     * @throws Exception
     */
    @RequestMapping(value="/sym/cal/EgovRestdeModify.do")
	public String updateRestde (@ModelAttribute("loginVO") LoginVO loginVO
			, @ModelAttribute("restde") Restde restde
			, BindingResult bindingResult
			, @RequestParam Map<?, ?> commandMap
			, ModelMap model
			) throws Exception {
		String sCmd = commandMap.get("cmd") == null ? "" : (String)commandMap.get("cmd");
    	if (sCmd.equals("")) {
    		Restde vo = restdeManageService.selectRestdeDetail(restde);
    		model.addAttribute("restde", vo);

    		ComDefaultCodeVO CodeVO = new ComDefaultCodeVO();
    		CodeVO.setCodeId("COM017");
            List<?> restdeCodeList = cmmUseService.selectCmmCodeDetail(CodeVO);
            model.addAttribute("restdeCode", restdeCodeList);

            return "egovframework/com/sym/cal/EgovRestdeModify";
    	} else if (sCmd.equals("Modify")) {
            beanValidator.validate(restde, bindingResult);
    		if (bindingResult.hasErrors()){
        		ComDefaultCodeVO CodeVO = new ComDefaultCodeVO();
        		CodeVO.setCodeId("COM017");
                List<?> restdeCodeList = cmmUseService.selectCmmCodeDetail(CodeVO);
                model.addAttribute("restdeCode", restdeCodeList);

                return "egovframework/com/sym/cal/EgovRestdeModify";
    		}

    		restde.setLastUpdusrId(loginVO.getUniqId());
    		restdeManageService.updateRestde(restde);
	        return "forward:/sym/cal/EgovRestdeList.do";
    	} else {
    		return "forward:/sym/cal/EgovRestdeList.do";
    	}
    }


}