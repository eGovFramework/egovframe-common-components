package egovframework.com.sym.cal.web;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections.map.ListOrderedMap;
import org.egovframe.rte.fdl.idgnr.EgovIdGnrService;
import org.egovframe.rte.fdl.property.EgovPropertyService;
import org.egovframe.rte.psl.dataaccess.util.EgovMap;
import org.egovframe.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springmodules.validation.commons.DefaultBeanValidator;

import egovframework.com.cmm.ComDefaultCodeVO;
import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.annotation.IncludedInfo;
import egovframework.com.cmm.service.EgovCmmUseService;
import egovframework.com.sym.cal.service.EgovCalRestdeManageService;
import egovframework.com.sym.cal.service.Restde;
import egovframework.com.sym.cal.service.RestdeVO;

/**
 * 공휴일에 관한 요청을 받아 서비스 클래스로 요청을 전달하고 서비스클래스에서 처리한 결과를 웹 화면으로 전달을 위한 Controller를
 * 정의한다
 * 
 * @author 공통서비스 개발팀 이중호
 * @since 2009.04.01
 * @version 1.0
 * @see
 *
 *      <pre>
 *  == 개정이력(Modification Information) ==
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2009.04.01  이중호          최초 생성
 *   2011.08.26  정진오          IncludedInfo annotation 추가
 *   2011.10.18  서준식          보안점검 조치 사항으로 sql injection에 대비한 파라미터 체크(달력 출력을 위한 숫자만 가능하도록)
 *   2024.08.31  권태성          휴일 등록 & 수정의 화면과 데이터를 처리하는 method 분리, validation 적용
 *   2025.07.04  이백행          컨트리뷰션 PMD로 소프트웨어 보안약점 진단하고 제거하기-AvoidReassigningParameters(넘겨받는 메소드 parameter 값을 직접 변경하는 코드 탐지)
 *   2025.07.04  이백행          컨트리뷰션 PMD로 소프트웨어 보안약점 진단하고 제거하기-LocalVariableNamingConventions(final이 아닌 변수는 밑줄을 포함할 수 없음)
 *   2025.07.04  이백행          컨트리뷰션 PMD로 소프트웨어 보안약점 진단하고 제거하기-UselessParentheses(불필요한 괄호사용)
 *
 *      </pre>
 */
@Controller
public class EgovCalRestdeManageController {

	@Resource(name = "RestdeManageService")
	private EgovCalRestdeManageService restdeManageService;

	/** EgovPropertyService */
	@Resource(name = "propertiesService")
	protected EgovPropertyService propertiesService;

	@Resource(name = "egovRestDeIdGnrService")
	private EgovIdGnrService idgenService;

	@Resource(name = "EgovCmmUseService")
	private EgovCmmUseService cmmUseService;

	@Autowired
	private DefaultBeanValidator beanValidator;

	private void checkRestdeWithValidator(Restde restde, BindingResult bindingResult) {

		restde.setRestdeDe("dummy");
		restde.setRestdeNm("dummy");
		restde.setRestdeDc("dummy");
		restde.setRestdeSeCode("dummy");

		beanValidator.validate(restde, bindingResult);
	}

	/**
	 * 달력 메인창을 호출한다.
	 * 
	 * @param model
	 * @return "egovframework/com/sym/cal/EgovNormalCalPopup"
	 * @throws Exception
	 */
	@RequestMapping(value = "/sym/cal/callCalPopup.do")
	public String callCalendar(ModelMap model) throws Exception {
		return "egovframework/com/sym/cal/EgovCalPopup";
	}

	/**
	 * 달력을 호출한다.
	 * 
	 * @param model
	 * @return "egovframework/com/sym/cal/EgovNormalCalPopup"
	 * @throws Exception
	 */
	@SuppressWarnings("unused")
	@RequestMapping(value = "/sym/cal/callCal.do")
	public String callCal(Restde restde, BindingResult bindingResult, ModelMap model) throws Exception {

		// 2011.10.18 달력 출력을 위해 필요한 숫자 이외의 값을 사용하는 경우 체크
		checkRestdeWithValidator(restde, bindingResult);

		if (bindingResult.hasErrors()) {

			return "egovframework/com/cmm/error/dataAccessFailure";

		}

		Calendar cal = Calendar.getInstance();

		if (restde.getYear() == null || restde.getYear().equals("")) {
			restde.setYear(Integer.toString(cal.get(Calendar.YEAR)));
		}
		if (restde.getMonth() == null || restde.getMonth().equals("")) {
			restde.setMonth(Integer.toString(cal.get(Calendar.MONTH) + 1));
		}
		int iYear = Integer.parseInt(restde.getYear());
		int iMonth = Integer.parseInt(restde.getMonth());

		if (iMonth < 1) {
			iYear--;
			iMonth = 12;
		}
		if (iMonth > 12) {
			iYear++;
			iMonth = 1;
		}
		if (iYear < 1) {
			iYear = 1;
			iMonth = 1;
		}
		if (iYear > 9999) {
			iYear = 9999;
			iMonth = 12;
		}

		cal.set(iYear, iMonth - 1, 1);

		int firstWeek = cal.get(Calendar.DAY_OF_WEEK);
		int lastDay = cal.getActualMaximum(Calendar.DATE);
		int week = cal.get(Calendar.DAY_OF_WEEK);

		String year = Integer.toString(iYear);
		String month = Integer.toString(iMonth);
		String day = Integer.toString(cal.get(Calendar.DAY_OF_MONTH));

		restde.setStartWeekMonth(firstWeek);
		restde.setLastDayMonth(lastDay);
		restde.setYear(year);
		restde.setMonth(month);

		List<ListOrderedMap> resultCalInfoList = new ArrayList<ListOrderedMap>();
		String tmpDay = "";

		/**
		 * 계산... START
		 */
		for (int i = 0; i < 42; i++) {
			ListOrderedMap map = new ListOrderedMap();
			int cc = i + 1;
			int dd = cc - firstWeek + 1;

			if (dd > 0 && dd <= lastDay) {
				tmpDay = Integer.toString(dd);
			} else {
				tmpDay = "";
			}

			map.put("year", year);
			map.put("month", month);
			map.put("day", tmpDay);
			map.put("cellNum", cc);
			map.put("weeks", (cc - 1) / 7 + 1);
			map.put("week", (week - 1) % 7 + 1);
			map.put("restAt", ((week - 1) % 7 + 1 == 1) ? "Y" : "N");

			if (dd > 0 && dd <= lastDay) {
				week++;
			}
			resultCalInfoList.add(map);

		}
		/**
		 * 계산... END
		 */

		model.addAttribute("resultList", resultCalInfoList);

		return "egovframework/com/sym/cal/EgovCalendar";
	}

	/**
	 * 일반달력 팝업 메인창을 호출한다.
	 * 
	 * @param model
	 * @return "egovframework/com/sym/cal/EgovNormalCalPopup"
	 * @throws Exception
	 */
	@RequestMapping(value = "/sym/cal/EgovNormalCalPopup.do")
	public String callNormalCalPopup(ModelMap model) throws Exception {
		return "egovframework/com/sym/cal/EgovNormalCalPopup";
	}

	/**
	 * 일반달력 팝업 정보를 조회한다.
	 * 
	 * @param restde
	 * @param model
	 * @return "egovframework/com/sym/cal/EgovNormalCalendar"
	 * @throws Exception
	 */
	@RequestMapping(value = "/sym/cal/EgovselectNormalCalendar.do")
	public String selectNormalRestdePopup(Restde restde, BindingResult bindingResult, ModelMap model) throws Exception {

		// 2011.10.18 달력 출력을 위해 필요한 숫자 이외의 값을 사용하는 경우 체크
		checkRestdeWithValidator(restde, bindingResult);

		if (bindingResult.hasErrors()) {

			return "egovframework/com/cmm/error/dataAccessFailure";

		}

		Calendar cal = Calendar.getInstance();

		if (restde.getYear() == null || restde.getYear().equals("")) {
			restde.setYear(Integer.toString(cal.get(Calendar.YEAR)));
		}
		if (restde.getMonth() == null || restde.getMonth().equals("")) {
			restde.setMonth(Integer.toString(cal.get(Calendar.MONTH) + 1));
		}
		int iYear = Integer.parseInt(restde.getYear());
		int iMonth = Integer.parseInt(restde.getMonth());

		if (iMonth < 1) {
			iYear--;
			iMonth = 12;
		}
		if (iMonth > 12) {
			iYear++;
			iMonth = 1;
		}
		if (iYear < 1) {
			iYear = 1;
			iMonth = 1;
		}
		if (iYear > 9999) {
			iYear = 9999;
			iMonth = 12;
		}
		restde.setYear(Integer.toString(iYear));
		restde.setMonth(Integer.toString(iMonth));

		cal.set(iYear, iMonth - 1, 1);

		restde.setStartWeekMonth(cal.get(Calendar.DAY_OF_WEEK));
		restde.setLastDayMonth(cal.getActualMaximum(Calendar.DATE));

		List<EgovMap> resultCalInfoList = restdeManageService.selectNormalRestdePopup(restde);

		model.addAttribute("resultList", resultCalInfoList);

		return "egovframework/com/sym/cal/EgovNormalCalendar";
	}

	/**
	 * 행정달력 팝업 메인창을 호출한다.
	 * 
	 * @param model
	 * @return "egovframework/com/sym/cal/EgovAdministCalPopup"
	 * @throws Exception
	 */
	@RequestMapping(value = "/sym/cal/EgovAdministCalPopup.do")
	public String callAdministCalPopup(ModelMap model) throws Exception {
		return "egovframework/com/sym/cal/EgovAdministCalPopup";
	}

	/**
	 * 행정달력 팝업 정보를 조회한다.
	 * 
	 * @param restde
	 * @param model
	 * @return "egovframework/com/sym/cal/EgovAdministCalendar"
	 * @throws Exception
	 */
	@RequestMapping(value = "/sym/cal/EgovselectAdministCalendar.do")
	public String selectAdministRestdePopup(Restde restde, BindingResult bindingResult, ModelMap model)
			throws Exception {

		// 2011.10.18 달력 출력을 위해 필요한 숫자 이외의 값을 사용하는 경우 체크
		checkRestdeWithValidator(restde, bindingResult);

		if (bindingResult.hasErrors()) {

			return "egovframework/com/cmm/error/dataAccessFailure";

		}

		Calendar cal = Calendar.getInstance();

		if (restde.getYear() == null || restde.getYear().equals("")) {
			restde.setYear(Integer.toString(cal.get(Calendar.YEAR)));
		}
		if (restde.getMonth() == null || restde.getMonth().equals("")) {
			restde.setMonth(Integer.toString(cal.get(Calendar.MONTH) + 1));
		}
		int iYear = Integer.parseInt(restde.getYear());
		int iMonth = Integer.parseInt(restde.getMonth());

		if (iMonth < 1) {
			iYear--;
			iMonth = 12;
		}
		if (iMonth > 12) {
			iYear++;
			iMonth = 1;
		}
		if (iYear < 1) {
			iYear = 1;
			iMonth = 1;
		}
		if (iYear > 9999) {
			iYear = 9999;
			iMonth = 12;
		}
		restde.setYear(Integer.toString(iYear));
		restde.setMonth(Integer.toString(iMonth));

		cal.set(iYear, iMonth - 1, 1);

		restde.setStartWeekMonth(cal.get(Calendar.DAY_OF_WEEK));
		restde.setLastDayMonth(cal.getActualMaximum(Calendar.DATE));

		List<EgovMap> resultCalInfoList = restdeManageService.selectAdministRestdePopup(restde);

		model.addAttribute("resultList", resultCalInfoList);

		return "egovframework/com/sym/cal/EgovAdministCalendar";
	}

	/**
	 * 일반달력 일간
	 * 
	 * @param restde
	 * @param model
	 * @return "egovframework/com/sym/cal/EgovNormalDayCalendar"
	 * @throws Exception
	 */
	@RequestMapping(value = "/sym/cal/EgovNormalDayCalendar.do")
	public String selectNormalDayCalendar(Restde restde, BindingResult bindingResult, ModelMap model) throws Exception {

		// 2011.10.18 달력 출력을 위해 필요한 숫자 이외의 값을 사용하는 경우 체크
		checkRestdeWithValidator(restde, bindingResult);

		if (bindingResult.hasErrors()) {

			return "egovframework/com/cmm/error/dataAccessFailure";

		}

		Calendar cal = Calendar.getInstance();

		if (restde.getYear() == null || restde.getYear().equals("")) {
			restde.setYear(Integer.toString(cal.get(Calendar.YEAR)));
		}
		if (restde.getMonth() == null || restde.getMonth().equals("")) {
			restde.setMonth(Integer.toString(cal.get(Calendar.MONTH) + 1));
		}
		if (restde.getDay() == null || restde.getDay().equals("")) {
			restde.setDay(Integer.toString(cal.get(Calendar.DATE)));
		}

		int iYear = Integer.parseInt(restde.getYear());
		int iMonth = Integer.parseInt(restde.getMonth());
		int iDay = Integer.parseInt(restde.getDay());

		if (iMonth < 1) {
			iYear--;
			iMonth = 12;
		}
		if (iMonth > 12) {
			iYear++;
			iMonth = 1;
		}
		if (iYear < 1) {
			iYear = 1;
			iMonth = 1;
		}
		if (iYear > 9999) {
			iYear = 9999;
			iMonth = 12;
		}
		restde.setYear(Integer.toString(iYear));
		restde.setMonth(Integer.toString(iMonth));

		cal.set(iYear, iMonth - 1, iDay);
		restde.setStartWeekMonth(cal.get(Calendar.DAY_OF_WEEK));

		cal.set(iYear, iMonth - 1, Integer.parseInt(restde.getDay()));
		restde.setLastDayMonth(cal.getActualMaximum(Calendar.DAY_OF_MONTH));

		restde.setYear(Integer.toString(cal.get(Calendar.YEAR)));
		restde.setMonth(Integer.toString(cal.get(Calendar.MONTH) + 1));
		restde.setDay(Integer.toString(cal.get(Calendar.DAY_OF_MONTH)));
		restde.setWeek(cal.get(Calendar.DAY_OF_WEEK));
		restde.setLastDayMonth(cal.getActualMaximum(Calendar.DATE));

		List<EgovMap> resultCalInfoList = restdeManageService.selectNormalDayCal(restde);
		List<EgovMap> resultNormalWeekRestdeList = restdeManageService.selectNormalDayRestde(restde);

		model.addAttribute("resultList", resultCalInfoList);
		model.addAttribute("RestdeList", resultNormalWeekRestdeList);

		return "egovframework/com/sym/cal/EgovNormalDayCalendar";
	}

	/**
	 * 일반달력 주간
	 * 
	 * @param restde
	 * @param model
	 * @return "egovframework/com/sym/cal/EgovNormalWeekCalendar"
	 * @throws Exception
	 */
	@RequestMapping(value = "/sym/cal/EgovNormalWeekCalendar.do")
	public String selectNormalWeekCalendar(Restde restde, BindingResult bindingResult, ModelMap model)
			throws Exception {

		// 2011.10.18 달력 출력을 위해 필요한 숫자 이외의 값을 사용하는 경우 체크
		checkRestdeWithValidator(restde, bindingResult);

		if (bindingResult.hasErrors()) {

			return "egovframework/com/cmm/error/dataAccessFailure";

		}

		Calendar cal = Calendar.getInstance();

		if (restde.getYear() == null || restde.getYear().equals("")) {
			restde.setYear(Integer.toString(cal.get(Calendar.YEAR)));
		}
		if (restde.getMonth() == null || restde.getMonth().equals("")) {
			restde.setMonth(Integer.toString(cal.get(Calendar.MONTH) + 1));
		}
		if (restde.getDay() == null || restde.getDay().equals("")) {
			restde.setDay(Integer.toString(cal.get(Calendar.DATE)));
		}

		int iYear = Integer.parseInt(restde.getYear());
		int iMonth = Integer.parseInt(restde.getMonth());

		if (iMonth < 1) {
			iYear--;
			iMonth = 12;
		}
		if (iMonth > 12) {
			iYear++;
			iMonth = 1;
		}
		if (iYear < 1) {
			iYear = 1;
			iMonth = 1;
		}
		if (iYear > 9999) {
			iYear = 9999;
			iMonth = 12;
		}
		restde.setYear(Integer.toString(iYear));
		restde.setMonth(Integer.toString(iMonth));

		cal.set(iYear, iMonth - 1, 1);
		restde.setStartWeekMonth(cal.get(Calendar.DAY_OF_WEEK));

		cal.set(iYear, iMonth - 1, Integer.parseInt(restde.getDay()));
		restde.setLastDayMonth(cal.getActualMaximum(Calendar.DAY_OF_MONTH));

		int iStartWeek = restde.getStartWeekMonth();
		int iLastDate = restde.getLastDayMonth();
		int iDayWeek = cal.get(Calendar.DAY_OF_WEEK);

		int iMaxWeeks = iLastDate / 7;
		iMaxWeeks = iMaxWeeks + (int) Math.ceil((iLastDate - iMaxWeeks * 7 + iStartWeek - 1) / 7.0);
		restde.setMaxWeeks(iMaxWeeks);

		if (iMaxWeeks < restde.getWeeks()) {
			restde.setWeeks(iMaxWeeks);
		}

		Restde vo = new Restde();
		Calendar weekCal = Calendar.getInstance();
		weekCal.setTime(cal.getTime());

		if (restde.getWeeks() != 0) {
			weekCal.set(Calendar.DATE, (restde.getWeeks() - 1) * 7 + 1);
			if (restde.getWeeks() > 1) {
				iDayWeek = weekCal.get(Calendar.DAY_OF_WEEK);
				weekCal.add(Calendar.DATE, (-1) * (iDayWeek - 1));
			}
			restde.setDay(Integer.toString(weekCal.get(Calendar.DAY_OF_MONTH) + 1));
		}

		iDayWeek = weekCal.get(Calendar.DAY_OF_WEEK);

		// 일요일
		weekCal.add(Calendar.DATE, (-1) * (iDayWeek - 1));
		vo.setYear(Integer.toString(weekCal.get(Calendar.YEAR)));
		vo.setMonth(Integer.toString(weekCal.get(Calendar.MONTH) + 1));
		vo.setDay(Integer.toString(weekCal.get(Calendar.DAY_OF_MONTH)));
		vo.setWeek(weekCal.get(Calendar.DAY_OF_WEEK));
		List<EgovMap> resultCalInfo1List = restdeManageService.selectNormalDayCal(vo);
		List<EgovMap> resultNormalWeekRestde1List = restdeManageService.selectNormalDayRestde(vo);

		// 월요일
		weekCal.add(Calendar.DATE, 1);
		vo.setYear(Integer.toString(weekCal.get(Calendar.YEAR)));
		vo.setMonth(Integer.toString(weekCal.get(Calendar.MONTH) + 1));
		vo.setDay(Integer.toString(weekCal.get(Calendar.DAY_OF_MONTH)));
		vo.setWeek(weekCal.get(Calendar.DAY_OF_WEEK));
		List<EgovMap> resultCalInfo2List = restdeManageService.selectNormalDayCal(vo);
		List<EgovMap> resultNormalWeekRestde2List = restdeManageService.selectNormalDayRestde(vo);

		// 화요일
		weekCal.add(Calendar.DATE, 1);
		vo.setYear(Integer.toString(weekCal.get(Calendar.YEAR)));
		vo.setMonth(Integer.toString(weekCal.get(Calendar.MONTH) + 1));
		vo.setDay(Integer.toString(weekCal.get(Calendar.DAY_OF_MONTH)));
		vo.setWeek(weekCal.get(Calendar.DAY_OF_WEEK));
		List<EgovMap> resultCalInfo3List = restdeManageService.selectNormalDayCal(vo);
		List<EgovMap> resultNormalWeekRestde3List = restdeManageService.selectNormalDayRestde(vo);

		// 수요일
		weekCal.add(Calendar.DATE, 1);
		vo.setYear(Integer.toString(weekCal.get(Calendar.YEAR)));
		vo.setMonth(Integer.toString(weekCal.get(Calendar.MONTH) + 1));
		vo.setDay(Integer.toString(weekCal.get(Calendar.DAY_OF_MONTH)));
		vo.setWeek(weekCal.get(Calendar.DAY_OF_WEEK));
		List<EgovMap> resultCalInfo4List = restdeManageService.selectNormalDayCal(vo);
		List<EgovMap> resultNormalWeekRestde4List = restdeManageService.selectNormalDayRestde(vo);

		// 목요일
		weekCal.add(Calendar.DATE, 1);
		vo.setYear(Integer.toString(weekCal.get(Calendar.YEAR)));
		vo.setMonth(Integer.toString(weekCal.get(Calendar.MONTH) + 1));
		vo.setDay(Integer.toString(weekCal.get(Calendar.DAY_OF_MONTH)));
		vo.setWeek(weekCal.get(Calendar.DAY_OF_WEEK));
		List<EgovMap> resultCalInfo5List = restdeManageService.selectNormalDayCal(vo);
		List<EgovMap> resultNormalWeekRestde5List = restdeManageService.selectNormalDayRestde(vo);

		// 금요일
		weekCal.add(Calendar.DATE, 1);
		vo.setYear(Integer.toString(weekCal.get(Calendar.YEAR)));
		vo.setMonth(Integer.toString(weekCal.get(Calendar.MONTH) + 1));
		vo.setDay(Integer.toString(weekCal.get(Calendar.DAY_OF_MONTH)));
		vo.setWeek(weekCal.get(Calendar.DAY_OF_WEEK));
		List<EgovMap> resultCalInfo6List = restdeManageService.selectNormalDayCal(vo);
		List<EgovMap> resultNormalWeekRestde6List = restdeManageService.selectNormalDayRestde(vo);

		// 토요일
		weekCal.add(Calendar.DATE, 1);
		vo.setYear(Integer.toString(weekCal.get(Calendar.YEAR)));
		vo.setMonth(Integer.toString(weekCal.get(Calendar.MONTH) + 1));
		vo.setDay(Integer.toString(weekCal.get(Calendar.DAY_OF_MONTH)));
		vo.setWeek(weekCal.get(Calendar.DAY_OF_WEEK));
		List<EgovMap> resultCalInfo7List = restdeManageService.selectNormalDayCal(vo);
		List<EgovMap> resultNormalWeekRestde7List = restdeManageService.selectNormalDayRestde(vo);

		model.addAttribute("resultList_1", resultCalInfo1List);
		model.addAttribute("resultList_2", resultCalInfo2List);
		model.addAttribute("resultList_3", resultCalInfo3List);
		model.addAttribute("resultList_4", resultCalInfo4List);
		model.addAttribute("resultList_5", resultCalInfo5List);
		model.addAttribute("resultList_6", resultCalInfo6List);
		model.addAttribute("resultList_7", resultCalInfo7List);
		model.addAttribute("RestdeList_1", resultNormalWeekRestde1List);
		model.addAttribute("RestdeList_2", resultNormalWeekRestde2List);
		model.addAttribute("RestdeList_3", resultNormalWeekRestde3List);
		model.addAttribute("RestdeList_4", resultNormalWeekRestde4List);
		model.addAttribute("RestdeList_5", resultNormalWeekRestde5List);
		model.addAttribute("RestdeList_6", resultNormalWeekRestde6List);
		model.addAttribute("RestdeList_7", resultNormalWeekRestde7List);

		List<EgovMap> resultCalInfoList = restdeManageService.selectNormalDayCal(restde);
		model.addAttribute("resultList", resultCalInfoList);

		return "egovframework/com/sym/cal/EgovNormalWeekCalendar";
	}

	/**
	 * 일반달력 월간
	 * 
	 * @param restde
	 * @param model
	 * @return "egovframework/com/sym/cal/EgovNormalMonthCalendar"
	 * @throws Exception
	 */
	@RequestMapping(value = "/sym/cal/EgovNormalMonthCalendar.do")
	public String selectNormalMonthCalendar(Restde restde, BindingResult bindingResult, ModelMap model)
			throws Exception {

		// 2011.10.18 달력 출력을 위해 필요한 숫자 이외의 값을 사용하는 경우 체크
		checkRestdeWithValidator(restde, bindingResult);

		if (bindingResult.hasErrors()) {

			return "egovframework/com/cmm/error/dataAccessFailure";

		}

		Calendar cal = Calendar.getInstance();

		if (restde.getYear() == null || restde.getYear().equals("")) {
			restde.setYear(Integer.toString(cal.get(Calendar.YEAR)));
		}
		if (restde.getMonth() == null || restde.getMonth().equals("")) {
			restde.setMonth(Integer.toString(cal.get(Calendar.MONTH) + 1));
		}
		int iYear = Integer.parseInt(restde.getYear());
		int iMonth = Integer.parseInt(restde.getMonth());

		if (iMonth < 1) {
			iYear--;
			iMonth = 12;
		}
		if (iMonth > 12) {
			iYear++;
			iMonth = 1;
		}
		if (iYear < 1) {
			iYear = 1;
			iMonth = 1;
		}
		if (iYear > 9999) {
			iYear = 9999;
			iMonth = 12;
		}
		restde.setYear(Integer.toString(iYear));
		restde.setMonth(Integer.toString(iMonth));

		cal.set(iYear, iMonth - 1, 1);

		restde.setStartWeekMonth(cal.get(Calendar.DAY_OF_WEEK));
		restde.setLastDayMonth(cal.getActualMaximum(Calendar.DATE));

		List<EgovMap> resultCalInfoList = restdeManageService.selectNormalRestdePopup(restde);

		List<EgovMap> resultNormalMonthRestdeList = restdeManageService.selectNormalMonthRestde(restde);

		model.addAttribute("resultList", resultCalInfoList);
		model.addAttribute("RestdeList", resultNormalMonthRestdeList);

		return "egovframework/com/sym/cal/EgovNormalMonthCalendar";
	}

	/**
	 * 일반달력 연간
	 * 
	 * @param restde
	 * @param model
	 * @return "egovframework/com/sym/cal/EgovNormalYearCalendar"
	 * @throws Exception
	 */
	@RequestMapping(value = "/sym/cal/EgovNormalYearCalendar.do")
	public String selectNormalYearCalendar(Restde restde, BindingResult bindingResult, ModelMap model)
			throws Exception {

		// 2011.10.18 달력 출력을 위해 필요한 숫자 이외의 값을 사용하는 경우 체크
		checkRestdeWithValidator(restde, bindingResult);

		if (bindingResult.hasErrors()) {

			return "egovframework/com/cmm/error/dataAccessFailure";

		}

		Calendar cal = Calendar.getInstance();

		if (restde.getYear() == null || restde.getYear().equals("")) {
			restde.setYear(Integer.toString(cal.get(Calendar.YEAR)));
		}
		if (restde.getMonth() == null || restde.getMonth().equals("")) {
			restde.setMonth(Integer.toString(cal.get(Calendar.MONTH) + 1));
		}
		int iYear = Integer.parseInt(restde.getYear());
		int iMonth = Integer.parseInt(restde.getMonth());

		if (iMonth < 1) {
			iYear--;
			iMonth = 12;
		}
		if (iMonth > 12) {
			iYear++;
			iMonth = 1;
		}
		if (iYear < 1) {
			iYear = 1;
			iMonth = 1;
		}
		if (iYear > 9999) {
			iYear = 9999;
			iMonth = 12;
		}
		restde.setYear(Integer.toString(iYear));

		/* 월별확인 */

		/* 1월 */
		iMonth = 1;
		restde.setMonth(Integer.toString(iMonth));
		cal.set(iYear, iMonth - 1, 1);
		restde.setStartWeekMonth(cal.get(Calendar.DAY_OF_WEEK));
		restde.setLastDayMonth(cal.getActualMaximum(Calendar.DATE));
		List<EgovMap> resultCalInfo1List = restdeManageService.selectNormalRestdePopup(restde);
		List<EgovMap> resultNormalMonthRestde1List = restdeManageService.selectNormalMonthRestde(restde);

		/* 2월 */
		iMonth = 2;
		restde.setMonth(Integer.toString(iMonth));
		cal.set(iYear, iMonth - 1, 1);
		restde.setStartWeekMonth(cal.get(Calendar.DAY_OF_WEEK));
		restde.setLastDayMonth(cal.getActualMaximum(Calendar.DATE));
		List<EgovMap> resultCalInfo2List = restdeManageService.selectNormalRestdePopup(restde);
		List<EgovMap> resultNormalMonthRestde2List = restdeManageService.selectNormalMonthRestde(restde);

		/* 3월 */
		iMonth = 3;
		restde.setMonth(Integer.toString(iMonth));
		cal.set(iYear, iMonth - 1, 1);
		restde.setStartWeekMonth(cal.get(Calendar.DAY_OF_WEEK));
		restde.setLastDayMonth(cal.getActualMaximum(Calendar.DATE));
		List<EgovMap> resultCalInfo3List = restdeManageService.selectNormalRestdePopup(restde);
		List<EgovMap> resultNormalMonthRestde3List = restdeManageService.selectNormalMonthRestde(restde);

		/* 4월 */
		iMonth = 4;
		restde.setMonth(Integer.toString(iMonth));
		cal.set(iYear, iMonth - 1, 1);
		restde.setStartWeekMonth(cal.get(Calendar.DAY_OF_WEEK));
		restde.setLastDayMonth(cal.getActualMaximum(Calendar.DATE));
		List<EgovMap> resultCalInfo4List = restdeManageService.selectNormalRestdePopup(restde);
		List<EgovMap> resultNormalMonthRestde4List = restdeManageService.selectNormalMonthRestde(restde);

		/* 5월 */
		iMonth = 5;
		restde.setMonth(Integer.toString(iMonth));
		cal.set(iYear, iMonth - 1, 1);
		restde.setStartWeekMonth(cal.get(Calendar.DAY_OF_WEEK));
		restde.setLastDayMonth(cal.getActualMaximum(Calendar.DATE));
		List<EgovMap> resultCalInfo5List = restdeManageService.selectNormalRestdePopup(restde);
		List<EgovMap> resultNormalMonthRestde5List = restdeManageService.selectNormalMonthRestde(restde);

		/* 6월 */
		iMonth = 6;
		restde.setMonth(Integer.toString(iMonth));
		cal.set(iYear, iMonth - 1, 1);
		restde.setStartWeekMonth(cal.get(Calendar.DAY_OF_WEEK));
		restde.setLastDayMonth(cal.getActualMaximum(Calendar.DATE));
		List<EgovMap> resultCalInfo6List = restdeManageService.selectNormalRestdePopup(restde);
		List<EgovMap> resultNormalMonthRestde6List = restdeManageService.selectNormalMonthRestde(restde);

		/* 7월 */
		iMonth = 7;
		restde.setMonth(Integer.toString(iMonth));
		cal.set(iYear, iMonth - 1, 1);
		restde.setStartWeekMonth(cal.get(Calendar.DAY_OF_WEEK));
		restde.setLastDayMonth(cal.getActualMaximum(Calendar.DATE));
		List<EgovMap> resultCalInfo7List = restdeManageService.selectNormalRestdePopup(restde);
		List<EgovMap> resultNormalMonthRestde7List = restdeManageService.selectNormalMonthRestde(restde);

		/* 8월 */
		iMonth = 8;
		restde.setMonth(Integer.toString(iMonth));
		cal.set(iYear, iMonth - 1, 1);
		restde.setStartWeekMonth(cal.get(Calendar.DAY_OF_WEEK));
		restde.setLastDayMonth(cal.getActualMaximum(Calendar.DATE));
		List<EgovMap> resultCalInfo8List = restdeManageService.selectNormalRestdePopup(restde);
		List<EgovMap> resultNormalMonthRestde8List = restdeManageService.selectNormalMonthRestde(restde);

		/* 9월 */
		iMonth = 9;
		restde.setMonth(Integer.toString(iMonth));
		cal.set(iYear, iMonth - 1, 1);
		restde.setStartWeekMonth(cal.get(Calendar.DAY_OF_WEEK));
		restde.setLastDayMonth(cal.getActualMaximum(Calendar.DATE));
		List<EgovMap> resultCalInfo9List = restdeManageService.selectNormalRestdePopup(restde);
		List<EgovMap> resultNormalMonthRestde9List = restdeManageService.selectNormalMonthRestde(restde);

		/* 10월 */
		iMonth = 10;
		restde.setMonth(Integer.toString(iMonth));
		cal.set(iYear, iMonth - 1, 1);
		restde.setStartWeekMonth(cal.get(Calendar.DAY_OF_WEEK));
		restde.setLastDayMonth(cal.getActualMaximum(Calendar.DATE));
		List<EgovMap> resultCalInfo1List0 = restdeManageService.selectNormalRestdePopup(restde);
		List<EgovMap> resultNormalMonthRestde10List = restdeManageService.selectNormalMonthRestde(restde);

		/* 11월 */
		iMonth = 11;
		restde.setMonth(Integer.toString(iMonth));
		cal.set(iYear, iMonth - 1, 1);
		restde.setStartWeekMonth(cal.get(Calendar.DAY_OF_WEEK));
		restde.setLastDayMonth(cal.getActualMaximum(Calendar.DATE));
		List<EgovMap> resultCalInfo1List1 = restdeManageService.selectNormalRestdePopup(restde);
		List<EgovMap> resultNormalMonthRestde11List = restdeManageService.selectNormalMonthRestde(restde);

		/* 12월 */
		iMonth = 12;
		restde.setMonth(Integer.toString(iMonth));
		cal.set(iYear, iMonth - 1, 1);
		restde.setStartWeekMonth(cal.get(Calendar.DAY_OF_WEEK));
		restde.setLastDayMonth(cal.getActualMaximum(Calendar.DATE));
		List<EgovMap> resultCalInfo1List2 = restdeManageService.selectNormalRestdePopup(restde);
		List<EgovMap> resultNormalMonthRestde12List = restdeManageService.selectNormalMonthRestde(restde);

		model.addAttribute("resultList_1", resultCalInfo1List);
		model.addAttribute("resultList_2", resultCalInfo2List);
		model.addAttribute("resultList_3", resultCalInfo3List);
		model.addAttribute("resultList_4", resultCalInfo4List);
		model.addAttribute("resultList_5", resultCalInfo5List);
		model.addAttribute("resultList_6", resultCalInfo6List);
		model.addAttribute("resultList_7", resultCalInfo7List);
		model.addAttribute("resultList_8", resultCalInfo8List);
		model.addAttribute("resultList_9", resultCalInfo9List);
		model.addAttribute("resultList_10", resultCalInfo1List0);
		model.addAttribute("resultList_11", resultCalInfo1List1);
		model.addAttribute("resultList_12", resultCalInfo1List2);
		model.addAttribute("RestdeList_1", resultNormalMonthRestde1List);
		model.addAttribute("RestdeList_2", resultNormalMonthRestde2List);
		model.addAttribute("RestdeList_3", resultNormalMonthRestde3List);
		model.addAttribute("RestdeList_4", resultNormalMonthRestde4List);
		model.addAttribute("RestdeList_5", resultNormalMonthRestde5List);
		model.addAttribute("RestdeList_6", resultNormalMonthRestde6List);
		model.addAttribute("RestdeList_7", resultNormalMonthRestde7List);
		model.addAttribute("RestdeList_8", resultNormalMonthRestde8List);
		model.addAttribute("RestdeList_9", resultNormalMonthRestde9List);
		model.addAttribute("RestdeList_10", resultNormalMonthRestde10List);
		model.addAttribute("RestdeList_11", resultNormalMonthRestde11List);
		model.addAttribute("RestdeList_12", resultNormalMonthRestde12List);

		return "egovframework/com/sym/cal/EgovNormalYearCalendar";
	}

	/**
	 * 행정달력 일간
	 * 
	 * @param restde
	 * @param model
	 * @return "egovframework/com/sym/cal/EgovAdministDayCalendar"
	 * @throws Exception
	 */
	@SuppressWarnings("static-access")
	@RequestMapping(value = "/sym/cal/EgovAdministDayCalendar.do")
	public String selectAdministDayCalendar(Restde restde, BindingResult bindingResult, ModelMap model)
			throws Exception {

		// 2011.10.18 달력 출력을 위해 필요한 숫자 이외의 값을 사용하는 경우 체크
		checkRestdeWithValidator(restde, bindingResult);

		if (bindingResult.hasErrors()) {

			return "egovframework/com/cmm/error/dataAccessFailure";

		}

		Calendar cal = Calendar.getInstance();

		if (restde.getYear() == null || restde.getYear().equals("")) {
			restde.setYear(Integer.toString(cal.get(Calendar.YEAR)));
		}
		if (restde.getMonth() == null || restde.getMonth().equals("")) {
			restde.setMonth(Integer.toString(cal.get(Calendar.MONTH) + 1));
		}
		if (restde.getDay() == null || restde.getDay().equals("")) {
			restde.setDay(Integer.toString(cal.get(Calendar.DATE)));
		}

		int iYear = Integer.parseInt(restde.getYear());
		int iMonth = Integer.parseInt(restde.getMonth());
		int iDay = Integer.parseInt(restde.getDay());

		if (iMonth < 1) {
			iYear--;
			iMonth = 12;
		}
		if (iMonth > 12) {
			iYear++;
			iMonth = 1;
		}
		if (iYear < 1) {
			iYear = 1;
			iMonth = 1;
		}
		if (iYear > 9999) {
			iYear = 9999;
			iMonth = 12;
		}
		restde.setYear(Integer.toString(iYear));
		restde.setMonth(Integer.toString(iMonth));

		cal.set(iYear, iMonth - 1, iDay);
		restde.setStartWeekMonth(cal.get(Calendar.DAY_OF_WEEK));

		cal.set(iYear, iMonth - 1, Integer.parseInt(restde.getDay()));
		restde.setLastDayMonth(cal.getActualMaximum(Calendar.DAY_OF_MONTH));

		restde.setYear(Integer.toString(cal.get(cal.YEAR)));
		restde.setMonth(Integer.toString(cal.get(cal.MONTH) + 1));
		restde.setDay(Integer.toString(cal.get(cal.DAY_OF_MONTH)));
		restde.setWeek(cal.get(cal.DAY_OF_WEEK));
		restde.setLastDayMonth(cal.getActualMaximum(Calendar.DATE));

		List<EgovMap> resultCalInfoList = restdeManageService.selectAdministDayCal(restde);
		List<EgovMap> resultAdministWeekRestdeList = restdeManageService.selectAdministDayRestde(restde);

		model.addAttribute("resultList", resultCalInfoList);
		model.addAttribute("RestdeList", resultAdministWeekRestdeList);

		return "egovframework/com/sym/cal/EgovAdministDayCalendar";
	}

	/**
	 * 행정달력 주간
	 * 
	 * @param restde
	 * @param model
	 * @return "egovframework/com/sym/cal/EgovAdministWeekCalendar"
	 * @throws Exception
	 */
	@SuppressWarnings("static-access")
	@RequestMapping(value = "/sym/cal/EgovAdministWeekCalendar.do")
	public String selectAdministWeekCalendar(Restde restde, BindingResult bindingResult, ModelMap model)
			throws Exception {

		// 2011.10.18 달력 출력을 위해 필요한 숫자 이외의 값을 사용하는 경우 체크
		checkRestdeWithValidator(restde, bindingResult);

		if (bindingResult.hasErrors()) {

			return "egovframework/com/cmm/error/dataAccessFailure";

		}

		Calendar cal = Calendar.getInstance();

		if (restde.getYear() == null || restde.getYear().equals("")) {
			restde.setYear(Integer.toString(cal.get(Calendar.YEAR)));
		}
		if (restde.getMonth() == null || restde.getMonth().equals("")) {
			restde.setMonth(Integer.toString(cal.get(Calendar.MONTH) + 1));
		}
		if (restde.getDay() == null || restde.getDay().equals("")) {
			restde.setDay(Integer.toString(cal.get(Calendar.DATE)));
		}

		int iYear = Integer.parseInt(restde.getYear());
		int iMonth = Integer.parseInt(restde.getMonth());

		if (iMonth < 1) {
			iYear--;
			iMonth = 12;
		}
		if (iMonth > 12) {
			iYear++;
			iMonth = 1;
		}
		if (iYear < 1) {
			iYear = 1;
			iMonth = 1;
		}
		if (iYear > 9999) {
			iYear = 9999;
			iMonth = 12;
		}
		restde.setYear(Integer.toString(iYear));
		restde.setMonth(Integer.toString(iMonth));

		cal.set(iYear, iMonth - 1, 1);
		restde.setStartWeekMonth(cal.get(Calendar.DAY_OF_WEEK));

		cal.set(iYear, iMonth - 1, Integer.parseInt(restde.getDay()));
		restde.setLastDayMonth(cal.getActualMaximum(Calendar.DAY_OF_MONTH));

		int iStartWeek = restde.getStartWeekMonth();
		int iLastDate = restde.getLastDayMonth();
		int iDayWeek = cal.get(Calendar.DAY_OF_WEEK);

		int iMaxWeeks = iLastDate / 7;
		iMaxWeeks = iMaxWeeks + (int) Math.ceil((iLastDate - iMaxWeeks * 7 + iStartWeek - 1) / 7.0);
		restde.setMaxWeeks(iMaxWeeks);

		if (iMaxWeeks < restde.getWeeks()) {
			restde.setWeeks(iMaxWeeks);
		}

		Restde vo = new Restde();
		Calendar weekCal = Calendar.getInstance();
		weekCal.setTime(cal.getTime());

		if (restde.getWeeks() != 0) {
			weekCal.set(weekCal.DATE, (restde.getWeeks() - 1) * 7 + 1);
			if (restde.getWeeks() > 1) {
				iDayWeek = weekCal.get(weekCal.DAY_OF_WEEK);
				weekCal.add(weekCal.DATE, (-1) * (iDayWeek - 1));
			}
			restde.setDay(Integer.toString(weekCal.get(weekCal.DAY_OF_MONTH) + 1));
		}
		List<EgovMap> resultCalInfoList = restdeManageService.selectAdministDayCal(restde);

		iDayWeek = weekCal.get(weekCal.DAY_OF_WEEK);

		// 일요일
		weekCal.add(weekCal.DATE, (-1) * (iDayWeek - 1));
		vo.setYear(Integer.toString(weekCal.get(weekCal.YEAR)));
		vo.setMonth(Integer.toString(weekCal.get(weekCal.MONTH) + 1));
		vo.setDay(Integer.toString(weekCal.get(weekCal.DAY_OF_MONTH)));
		vo.setWeek(weekCal.get(weekCal.DAY_OF_WEEK));
		List<EgovMap> resultCalInfo1List = restdeManageService.selectAdministDayCal(vo);
		List<EgovMap> resultAdministWeekRestde1List = restdeManageService.selectAdministDayRestde(vo);

		// 월요일
		weekCal.add(weekCal.DATE, 1);
		vo.setYear(Integer.toString(weekCal.get(weekCal.YEAR)));
		vo.setMonth(Integer.toString(weekCal.get(weekCal.MONTH) + 1));
		vo.setDay(Integer.toString(weekCal.get(weekCal.DAY_OF_MONTH)));
		vo.setWeek(weekCal.get(weekCal.DAY_OF_WEEK));
		List<EgovMap> resultCalInfo2List = restdeManageService.selectAdministDayCal(vo);
		List<EgovMap> resultAdministWeekRestde2List = restdeManageService.selectAdministDayRestde(vo);

		// 화요일
		weekCal.add(weekCal.DATE, 1);
		vo.setYear(Integer.toString(weekCal.get(weekCal.YEAR)));
		vo.setMonth(Integer.toString(weekCal.get(weekCal.MONTH) + 1));
		vo.setDay(Integer.toString(weekCal.get(weekCal.DAY_OF_MONTH)));
		vo.setWeek(weekCal.get(weekCal.DAY_OF_WEEK));
		List<EgovMap> resultCalInfo3List = restdeManageService.selectAdministDayCal(vo);
		List<EgovMap> resultAdministWeekRestde3List = restdeManageService.selectAdministDayRestde(vo);

		// 수요일
		weekCal.add(weekCal.DATE, 1);
		vo.setYear(Integer.toString(weekCal.get(weekCal.YEAR)));
		vo.setMonth(Integer.toString(weekCal.get(weekCal.MONTH) + 1));
		vo.setDay(Integer.toString(weekCal.get(weekCal.DAY_OF_MONTH)));
		vo.setWeek(weekCal.get(weekCal.DAY_OF_WEEK));
		List<EgovMap> resultCalInfo4List = restdeManageService.selectAdministDayCal(vo);
		List<EgovMap> resultAdministWeekRestde4List = restdeManageService.selectAdministDayRestde(vo);

		// 목요일
		weekCal.add(weekCal.DATE, 1);
		vo.setYear(Integer.toString(weekCal.get(weekCal.YEAR)));
		vo.setMonth(Integer.toString(weekCal.get(weekCal.MONTH) + 1));
		vo.setDay(Integer.toString(weekCal.get(weekCal.DAY_OF_MONTH)));
		vo.setWeek(weekCal.get(weekCal.DAY_OF_WEEK));
		List<EgovMap> resultCalInfo5List = restdeManageService.selectAdministDayCal(vo);
		List<EgovMap> resultAdministWeekRestde5List = restdeManageService.selectAdministDayRestde(vo);

		// 금요일
		weekCal.add(weekCal.DATE, 1);
		vo.setYear(Integer.toString(weekCal.get(weekCal.YEAR)));
		vo.setMonth(Integer.toString(weekCal.get(weekCal.MONTH) + 1));
		vo.setDay(Integer.toString(weekCal.get(weekCal.DAY_OF_MONTH)));
		vo.setWeek(weekCal.get(weekCal.DAY_OF_WEEK));
		List<EgovMap> resultCalInfo6List = restdeManageService.selectAdministDayCal(vo);
		List<EgovMap> resultAdministWeekRestde6List = restdeManageService.selectAdministDayRestde(vo);

		// 토요일
		weekCal.add(weekCal.DATE, 1);
		vo.setYear(Integer.toString(weekCal.get(weekCal.YEAR)));
		vo.setMonth(Integer.toString(weekCal.get(weekCal.MONTH) + 1));
		vo.setDay(Integer.toString(weekCal.get(weekCal.DAY_OF_MONTH)));
		vo.setWeek(weekCal.get(weekCal.DAY_OF_WEEK));
		List<EgovMap> resultCalInfo7List = restdeManageService.selectAdministDayCal(vo);
		List<EgovMap> resultAdministWeekRestde7List = restdeManageService.selectAdministDayRestde(vo);

		model.addAttribute("resultList_1", resultCalInfo1List);
		model.addAttribute("resultList_2", resultCalInfo2List);
		model.addAttribute("resultList_3", resultCalInfo3List);
		model.addAttribute("resultList_4", resultCalInfo4List);
		model.addAttribute("resultList_5", resultCalInfo5List);
		model.addAttribute("resultList_6", resultCalInfo6List);
		model.addAttribute("resultList_7", resultCalInfo7List);
		model.addAttribute("RestdeList_1", resultAdministWeekRestde1List);
		model.addAttribute("RestdeList_2", resultAdministWeekRestde2List);
		model.addAttribute("RestdeList_3", resultAdministWeekRestde3List);
		model.addAttribute("RestdeList_4", resultAdministWeekRestde4List);
		model.addAttribute("RestdeList_5", resultAdministWeekRestde5List);
		model.addAttribute("RestdeList_6", resultAdministWeekRestde6List);
		model.addAttribute("RestdeList_7", resultAdministWeekRestde7List);

		model.addAttribute("resultList", resultCalInfoList);

		return "egovframework/com/sym/cal/EgovAdministWeekCalendar";
	}

	/**
	 * 행정달력 월간
	 * 
	 * @param restde
	 * @param model
	 * @return "egovframework/com/sym/cal/EgovAdministMonthCalendar"
	 * @throws Exception
	 */
	@RequestMapping(value = "/sym/cal/EgovAdministMonthCalendar.do")
	public String selectAdministMonthCalendar(Restde restde, BindingResult bindingResult, ModelMap model)
			throws Exception {

		// 2011.10.18 달력 출력을 위해 필요한 숫자 이외의 값을 사용하는 경우 체크
		checkRestdeWithValidator(restde, bindingResult);

		if (bindingResult.hasErrors()) {

			return "egovframework/com/cmm/error/dataAccessFailure";

		}

		Calendar cal = Calendar.getInstance();

		if (restde.getYear() == null || restde.getYear().equals("")) {
			restde.setYear(Integer.toString(cal.get(Calendar.YEAR)));
		}
		if (restde.getMonth() == null || restde.getMonth().equals("")) {
			restde.setMonth(Integer.toString(cal.get(Calendar.MONTH) + 1));
		}
		int iYear = Integer.parseInt(restde.getYear());
		int iMonth = Integer.parseInt(restde.getMonth());

		if (iMonth < 1) {
			iYear--;
			iMonth = 12;
		}
		if (iMonth > 12) {
			iYear++;
			iMonth = 1;
		}
		if (iYear < 1) {
			iYear = 1;
			iMonth = 1;
		}
		if (iYear > 9999) {
			iYear = 9999;
			iMonth = 12;
		}
		restde.setYear(Integer.toString(iYear));
		restde.setMonth(Integer.toString(iMonth));

		cal.set(iYear, iMonth - 1, 1);

		restde.setStartWeekMonth(cal.get(Calendar.DAY_OF_WEEK));
		restde.setLastDayMonth(cal.getActualMaximum(Calendar.DATE));

		List<EgovMap> resultCalInfoList = restdeManageService.selectAdministRestdePopup(restde);

		List<?> resultAdministMonthRestdeList = restdeManageService.selectAdministMonthRestde(restde);

		model.addAttribute("resultList", resultCalInfoList);
		model.addAttribute("RestdeList", resultAdministMonthRestdeList);

		return "egovframework/com/sym/cal/EgovAdministMonthCalendar";
	}

	/**
	 * 행정달력 연간
	 * 
	 * @param restde
	 * @param model
	 * @return "egovframework/com/sym/cal/EgovAdministYearCalendar"
	 * @throws Exception
	 */
	@RequestMapping(value = "/sym/cal/EgovAdministYearCalendar.do")
	public String selectAdministYearCalendar(Restde restde, BindingResult bindingResult, ModelMap model)
			throws Exception {

		// 2011.10.18 달력 출력을 위해 필요한 숫자 이외의 값을 사용하는 경우 체크
		checkRestdeWithValidator(restde, bindingResult);

		if (bindingResult.hasErrors()) {

			return "egovframework/com/cmm/error/dataAccessFailure";

		}

		Calendar cal = Calendar.getInstance();

		if (restde.getYear() == null || restde.getYear().equals("")) {
			restde.setYear(Integer.toString(cal.get(Calendar.YEAR)));
		}
		if (restde.getMonth() == null || restde.getMonth().equals("")) {
			restde.setMonth(Integer.toString(cal.get(Calendar.MONTH) + 1));
		}
		int iYear = Integer.parseInt(restde.getYear());
		int iMonth = Integer.parseInt(restde.getMonth());

		if (iMonth < 1) {
			iYear--;
			iMonth = 12;
		}
		if (iMonth > 12) {
			iYear++;
			iMonth = 1;
		}
		if (iYear < 1) {
			iYear = 1;
			iMonth = 1;
		}
		if (iYear > 9999) {
			iYear = 9999;
			iMonth = 12;
		}
		restde.setYear(Integer.toString(iYear));

		/* 월별확인 */

		/* 1월 */
		iMonth = 1;
		restde.setMonth(Integer.toString(iMonth));
		cal.set(iYear, iMonth - 1, 1);
		restde.setStartWeekMonth(cal.get(Calendar.DAY_OF_WEEK));
		restde.setLastDayMonth(cal.getActualMaximum(Calendar.DATE));
		List<EgovMap> resultCalInfo1List = restdeManageService.selectAdministRestdePopup(restde);
		List<?> resultAdministMonthRestde1List = restdeManageService.selectAdministMonthRestde(restde);

		/* 2월 */
		iMonth = 2;
		restde.setMonth(Integer.toString(iMonth));
		cal.set(iYear, iMonth - 1, 1);
		restde.setStartWeekMonth(cal.get(Calendar.DAY_OF_WEEK));
		restde.setLastDayMonth(cal.getActualMaximum(Calendar.DATE));
		List<EgovMap> resultCalInfo2List = restdeManageService.selectAdministRestdePopup(restde);
		List<?> resultAdministMonthRestde2List = restdeManageService.selectAdministMonthRestde(restde);

		/* 3월 */
		iMonth = 3;
		restde.setMonth(Integer.toString(iMonth));
		cal.set(iYear, iMonth - 1, 1);
		restde.setStartWeekMonth(cal.get(Calendar.DAY_OF_WEEK));
		restde.setLastDayMonth(cal.getActualMaximum(Calendar.DATE));
		List<EgovMap> resultCalInfo3List = restdeManageService.selectAdministRestdePopup(restde);
		List<?> resultAdministMonthRestde3List = restdeManageService.selectAdministMonthRestde(restde);

		/* 4월 */
		iMonth = 4;
		restde.setMonth(Integer.toString(iMonth));
		cal.set(iYear, iMonth - 1, 1);
		restde.setStartWeekMonth(cal.get(Calendar.DAY_OF_WEEK));
		restde.setLastDayMonth(cal.getActualMaximum(Calendar.DATE));
		List<EgovMap> resultCalInfo4List = restdeManageService.selectAdministRestdePopup(restde);
		List<?> resultAdministMonthRestde4List = restdeManageService.selectAdministMonthRestde(restde);

		/* 5월 */
		iMonth = 5;
		restde.setMonth(Integer.toString(iMonth));
		cal.set(iYear, iMonth - 1, 1);
		restde.setStartWeekMonth(cal.get(Calendar.DAY_OF_WEEK));
		restde.setLastDayMonth(cal.getActualMaximum(Calendar.DATE));
		List<EgovMap> resultCalInfo5List = restdeManageService.selectAdministRestdePopup(restde);
		List<?> resultAdministMonthRestde5List = restdeManageService.selectAdministMonthRestde(restde);

		/* 6월 */
		iMonth = 6;
		restde.setMonth(Integer.toString(iMonth));
		cal.set(iYear, iMonth - 1, 1);
		restde.setStartWeekMonth(cal.get(Calendar.DAY_OF_WEEK));
		restde.setLastDayMonth(cal.getActualMaximum(Calendar.DATE));
		List<EgovMap> resultCalInfo6List = restdeManageService.selectAdministRestdePopup(restde);
		List<?> resultAdministMonthRestde6List = restdeManageService.selectAdministMonthRestde(restde);

		/* 7월 */
		iMonth = 7;
		restde.setMonth(Integer.toString(iMonth));
		cal.set(iYear, iMonth - 1, 1);
		restde.setStartWeekMonth(cal.get(Calendar.DAY_OF_WEEK));
		restde.setLastDayMonth(cal.getActualMaximum(Calendar.DATE));
		List<EgovMap> resultCalInfo7List = restdeManageService.selectAdministRestdePopup(restde);
		List<?> resultAdministMonthRestde7List = restdeManageService.selectAdministMonthRestde(restde);

		/* 8월 */
		iMonth = 8;
		restde.setMonth(Integer.toString(iMonth));
		cal.set(iYear, iMonth - 1, 1);
		restde.setStartWeekMonth(cal.get(Calendar.DAY_OF_WEEK));
		restde.setLastDayMonth(cal.getActualMaximum(Calendar.DATE));
		List<EgovMap> resultCalInfo8List = restdeManageService.selectAdministRestdePopup(restde);
		List<?> resultAdministMonthRestde8List = restdeManageService.selectAdministMonthRestde(restde);

		/* 9월 */
		iMonth = 9;
		restde.setMonth(Integer.toString(iMonth));
		cal.set(iYear, iMonth - 1, 1);
		restde.setStartWeekMonth(cal.get(Calendar.DAY_OF_WEEK));
		restde.setLastDayMonth(cal.getActualMaximum(Calendar.DATE));
		List<EgovMap> resultCalInfo9List = restdeManageService.selectAdministRestdePopup(restde);
		List<?> resultAdministMonthRestde9List = restdeManageService.selectAdministMonthRestde(restde);

		/* 10월 */
		iMonth = 10;
		restde.setMonth(Integer.toString(iMonth));
		cal.set(iYear, iMonth - 1, 1);
		restde.setStartWeekMonth(cal.get(Calendar.DAY_OF_WEEK));
		restde.setLastDayMonth(cal.getActualMaximum(Calendar.DATE));
		List<EgovMap> resultCalInfo1List0 = restdeManageService.selectAdministRestdePopup(restde);
		List<?> resultAdministMonthRestde10List = restdeManageService.selectAdministMonthRestde(restde);

		/* 11월 */
		iMonth = 11;
		restde.setMonth(Integer.toString(iMonth));
		cal.set(iYear, iMonth - 1, 1);
		restde.setStartWeekMonth(cal.get(Calendar.DAY_OF_WEEK));
		restde.setLastDayMonth(cal.getActualMaximum(Calendar.DATE));
		List<EgovMap> resultCalInfo1List1 = restdeManageService.selectAdministRestdePopup(restde);
		List<?> resultAdministMonthRestde11List = restdeManageService.selectAdministMonthRestde(restde);

		/* 12월 */
		iMonth = 12;
		restde.setMonth(Integer.toString(iMonth));
		cal.set(iYear, iMonth - 1, 1);
		restde.setStartWeekMonth(cal.get(Calendar.DAY_OF_WEEK));
		restde.setLastDayMonth(cal.getActualMaximum(Calendar.DATE));
		List<EgovMap> resultCalInfo1List2 = restdeManageService.selectAdministRestdePopup(restde);
		List<?> resultAdministMonthRestde12List = restdeManageService.selectAdministMonthRestde(restde);

		model.addAttribute("resultList_1", resultCalInfo1List);
		model.addAttribute("resultList_2", resultCalInfo2List);
		model.addAttribute("resultList_3", resultCalInfo3List);
		model.addAttribute("resultList_4", resultCalInfo4List);
		model.addAttribute("resultList_5", resultCalInfo5List);
		model.addAttribute("resultList_6", resultCalInfo6List);
		model.addAttribute("resultList_7", resultCalInfo7List);
		model.addAttribute("resultList_8", resultCalInfo8List);
		model.addAttribute("resultList_9", resultCalInfo9List);
		model.addAttribute("resultList_10", resultCalInfo1List0);
		model.addAttribute("resultList_11", resultCalInfo1List1);
		model.addAttribute("resultList_12", resultCalInfo1List2);
		model.addAttribute("RestdeList_1", resultAdministMonthRestde1List);
		model.addAttribute("RestdeList_2", resultAdministMonthRestde2List);
		model.addAttribute("RestdeList_3", resultAdministMonthRestde3List);
		model.addAttribute("RestdeList_4", resultAdministMonthRestde4List);
		model.addAttribute("RestdeList_5", resultAdministMonthRestde5List);
		model.addAttribute("RestdeList_6", resultAdministMonthRestde6List);
		model.addAttribute("RestdeList_7", resultAdministMonthRestde7List);
		model.addAttribute("RestdeList_8", resultAdministMonthRestde8List);
		model.addAttribute("RestdeList_9", resultAdministMonthRestde9List);
		model.addAttribute("RestdeList_10", resultAdministMonthRestde10List);
		model.addAttribute("RestdeList_11", resultAdministMonthRestde11List);
		model.addAttribute("RestdeList_12", resultAdministMonthRestde12List);

		return "egovframework/com/sym/cal/EgovAdministYearCalendar";
	}

	/**
	 * 휴일을 삭제한다.
	 * 
	 * @param loginVO
	 * @param restde
	 * @param model
	 * @return "forward:/sym/cal/EgovRestdeList.do"
	 * @throws Exception
	 */
	@RequestMapping(value = "/sym/cal/EgovRestdeRemove.do")
	public String deleteRestde(@ModelAttribute("loginVO") LoginVO loginVO, Restde restde, ModelMap model)
			throws Exception {
		restdeManageService.deleteRestde(restde);
		return "forward:/sym/cal/EgovRestdeList.do";
	}

	/**
	 * 휴일 등록 화면
	 *
	 * @param loginVO
	 * @param model
	 * @return "egovframework/com/sym/cal/EgovRestdeRegist"
	 * @throws Exception
	 */
	@GetMapping("/sym/cal/EgovRestdeRegistView.do")
	public String insertRestde(@ModelAttribute("loginVO") LoginVO loginVO, ModelMap model) throws Exception {
		ComDefaultCodeVO vo = new ComDefaultCodeVO();
		vo.setCodeId("COM017");
		List<?> restdeCodeList = cmmUseService.selectCmmCodeDetail(vo);
		model.addAttribute("restdeCode", restdeCodeList);
		model.addAttribute("restde", new Restde());
		return "egovframework/com/sym/cal/EgovRestdeRegist";
	}

	/**
	 * 휴일을 등록한다.
	 *
	 * @param loginVO
	 * @param restde
	 * @param bindingResult
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@PostMapping(value = "/sym/cal/EgovRestdeRegist.do")
	public String insertRestde(@ModelAttribute("loginVO") LoginVO loginVO, @ModelAttribute("restde") Restde restde,
			BindingResult bindingResult, ModelMap model) throws Exception {
		beanValidator.validate(restde, bindingResult);
		if (bindingResult.hasErrors()) {
			ComDefaultCodeVO vo = new ComDefaultCodeVO();
			vo.setCodeId("COM017");
			List<?> restdeCodeList = cmmUseService.selectCmmCodeDetail(vo);
			model.addAttribute("restdeCode", restdeCodeList);
			model.addAttribute("restde", restde);
			return "egovframework/com/sym/cal/EgovRestdeRegist";
		}

		restde.setRestdeNo(idgenService.getNextIntegerId() % 1000000);
		restde.setFrstRegisterId(loginVO.getUniqId());

		restdeManageService.insertRestde(restde);
		return "forward:/sym/cal/EgovRestdeList.do";
	}

	/**
	 * 휴일 세부내역을 조회한다.
	 * 
	 * @param loginVO
	 * @param restde
	 * @param model
	 * @return "egovframework/com/sym/cal/EgovRestdeDetail"
	 * @throws Exception
	 */
	@RequestMapping(value = "/sym/cal/EgovRestdeDetail.do")
	public String selectRestdeDetail(@ModelAttribute("loginVO") LoginVO loginVO, Restde restde, ModelMap model)
			throws Exception {
		Restde vo = restdeManageService.selectRestdeDetail(restde);
		model.addAttribute("result", vo);

		return "egovframework/com/sym/cal/EgovRestdeDetail";
	}

	/**
	 * 휴일 리스트를 조회한다.
	 * 
	 * @param loginVO
	 * @param searchVO
	 * @param model
	 * @return "egovframework/com/sym/cal/EgovRestdeList"
	 * @throws Exception
	 */
	@IncludedInfo(name = "공휴일관리(달력)", listUrl = "/sym/cal/EgovRestdeList.do", order = 1300, gid = 90)
	@RequestMapping(value = "/sym/cal/EgovRestdeList.do")
	public String selectRestdeList(@ModelAttribute("loginVO") LoginVO loginVO,
			@ModelAttribute("searchVO") RestdeVO searchVO, ModelMap model) throws Exception {
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

		List<?> resultCmmnCodeList = restdeManageService.selectRestdeList(searchVO);
		model.addAttribute("resultList", resultCmmnCodeList);

		int totCnt = restdeManageService.selectRestdeListTotCnt(searchVO);
		paginationInfo.setTotalRecordCount(totCnt);
		model.addAttribute("paginationInfo", paginationInfo);

		return "egovframework/com/sym/cal/EgovRestdeList";
	}

	/**
	 * 휴일 수정 화면
	 * 
	 * @param loginVO
	 * @param restde
	 * @param model
	 * @return "egovframework/com/sym/cal/EgovRestdeModify"
	 * @throws Exception
	 */
	@GetMapping("/sym/cal/EgovRestdeModifyView.do")
	public String updateRestde(@ModelAttribute("loginVO") LoginVO loginVO, @ModelAttribute("restde") Restde restde,
			ModelMap model) throws Exception {
		Restde vo = restdeManageService.selectRestdeDetail(restde);
		model.addAttribute("restde", vo);

		ComDefaultCodeVO codeVO = new ComDefaultCodeVO();
		codeVO.setCodeId("COM017");
		List<?> restdeCodeList = cmmUseService.selectCmmCodeDetail(codeVO);
		model.addAttribute("restdeCode", restdeCodeList);

		return "egovframework/com/sym/cal/EgovRestdeModify";
	}

	/**
	 * 휴일을 수정한다.
	 *
	 * @param loginVO
	 * @param restde
	 * @param bindingResult
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@PostMapping("/sym/cal/EgovRestdeModify.do")
	public String updateRestde(@ModelAttribute("loginVO") LoginVO loginVO, @ModelAttribute("restde") Restde restde,
			BindingResult bindingResult, ModelMap model) throws Exception {
		beanValidator.validate(restde, bindingResult);
		if (bindingResult.hasErrors()) {
			ComDefaultCodeVO comDefaultCodeVO = new ComDefaultCodeVO();
			comDefaultCodeVO.setCodeId("COM017");
			List<?> restdeCodeList = cmmUseService.selectCmmCodeDetail(comDefaultCodeVO);
			model.addAttribute("restdeCode", restdeCodeList);

			return "egovframework/com/sym/cal/EgovRestdeModify";
		}

		restde.setLastUpdusrId(loginVO.getUniqId());
		restdeManageService.updateRestde(restde);
		return "forward:/sym/cal/EgovRestdeList.do";
	}

}
