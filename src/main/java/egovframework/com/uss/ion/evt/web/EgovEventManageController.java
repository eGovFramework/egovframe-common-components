package egovframework.com.uss.ion.evt.web;

import java.util.List;
import java.util.Map;

import egovframework.com.cmm.ComDefaultCodeVO;
import egovframework.com.cmm.EgovMessageSource;
import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.annotation.IncludedInfo;
import egovframework.com.cmm.service.EgovCmmUseService;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.com.uss.ion.evt.service.EgovEventManageService;
import egovframework.com.uss.ion.evt.service.EventAtdrn;
import egovframework.com.uss.ion.evt.service.EventManage;
import egovframework.com.uss.ion.evt.service.EventManageVO;
import egovframework.com.utl.fcc.service.EgovDateUtil;

import org.egovframe.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;
import org.springmodules.validation.commons.DefaultBeanValidator;

/**
 * 개요
 * - 행사관리에 대한 controller 클래스를 정의한다.
 *
 * 상세내용
 * - 행사관리에 대한 등록, 수정, 삭제, 조회 기능을 제공한다.
 * - 행사관리의 조회기능은 목록조회, 상세조회로 구분된다.
 * @author 이용
 * @version 1.0
 * @created 06-15-2010 오후 2:08:56
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자          수정내용
 *  -------    --------    ---------------------------
 *  2010.6.15	이용          최초 생성
 *  2011.8.26	정진오			IncludedInfo annotation 추가
 *
 *  </pre>
 */

@Controller
public class EgovEventManageController {




	@Resource(name="egovMessageSource")
    EgovMessageSource egovMessageSource;

    @Resource(name = "egovEventManageService")
    private EgovEventManageService egovEventManageService;

    @Autowired
	 private DefaultBeanValidator beanValidator;

	@Resource(name="EgovCmmUseService")
	private EgovCmmUseService cmmUseService;

    /**
	 * 행사관리 목록화면 이동
	 * @return String
	 * @exception Exception
	 */
    @RequestMapping("/uss/ion/evt/EgovEventReqstManageListView.do")
    public String selectEventManageListView() throws Exception {

        return "egovframework/com/uss/ion/evt/EgovEventReqstManageList";
    }

	/**
	 * 행사관리정보를 관리하기 위해 등록된 행사관리 목록을 조회한다.
	 * @param eventManageVO - 행사관리 VO
	 * @return String - 리턴 Url
	 */
    @IncludedInfo(name="행사신청관리", order = 940 ,gid = 50)
    @RequestMapping(value="/uss/ion/evt/EgovEventReqstManageList.do")
	 public String selectEventManageList(@ModelAttribute("eventManageVO") EventManageVO eventManageVO,
			                                 ModelMap model) throws Exception {

		//행사년월
    	java.util.Calendar cal = java.util.Calendar.getInstance();
    	String [] yearList = new String[5];
    	for(int x=0; x < 5 ; x++){
    		yearList[x] = Integer.toString(cal.get(java.util.Calendar.YEAR)-x);
    	}
        //행사구분
    	ComDefaultCodeVO vo = new ComDefaultCodeVO();
		vo.setCodeId("COM053");
        List<?> eventSeCodeList = cmmUseService.selectCmmCodeDetail(vo);
        model.addAttribute("eventSeCode", eventSeCodeList);

    	/** paging */
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(eventManageVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(eventManageVO.getPageUnit());
		paginationInfo.setPageSize(eventManageVO.getPageSize());

		eventManageVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		eventManageVO.setLastIndex(paginationInfo.getLastRecordIndex());
		eventManageVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
		eventManageVO.setSearchKeyword(eventManageVO.getSearchYear()+eventManageVO.getSearchMonth());
		eventManageVO.setEventManageList(egovEventManageService.selectEventManageList(eventManageVO));

		model.addAttribute("eventManageList", eventManageVO.getEventManageList());

		int totCnt = egovEventManageService.selectEventManageListTotCnt(eventManageVO);
		paginationInfo.setTotalRecordCount(totCnt);
		model.addAttribute("paginationInfo", paginationInfo);
		model.addAttribute("yearList", yearList);
		model.addAttribute("message", egovMessageSource.getMessage("success.common.select"));

		return "egovframework/com/uss/ion/evt/EgovEventReqstManageList";
	}

	/**
	 * 등록된 행사관리의 상세정보를 조회한다.
	 * @param eventManageVO - 행사관리 VO
	 * @return String - 리턴 Url
	 */
    @RequestMapping(value="/uss/ion/evt/EgovEventReqstDetail.do")
	 public String selectEventManage(   @ModelAttribute("eventManage") EventManageVO eventManage,
			                            @ModelAttribute("eventManageVO") EventManageVO eventManageVO,
			                            @RequestParam Map<?, ?> commandMap,
			                            ModelMap model) throws Exception {

    	String sCmd = commandMap.get("cmd") == null ? "" : (String)commandMap.get("cmd"); // 상세정보 구분
    	EventManageVO eventManageVO1 = egovEventManageService.selectEventManage(eventManageVO);
    	eventManageVO1.setEventBeginDe(  EgovDateUtil.formatDate(eventManageVO1.getEventBeginDe(), "-"));
    	eventManageVO1.setEventEndDe(    EgovDateUtil.formatDate(eventManageVO1.getEventEndDe()  , "-"));
    	eventManageVO1.setRceptBeginDe(  EgovDateUtil.formatDate(eventManageVO1.getRceptBeginDe(), "-"));
    	eventManageVO1.setRceptEndDe(    EgovDateUtil.formatDate(eventManageVO1.getRceptEndDe()  , "-"));

    	model.addAttribute("eventManageVO", eventManageVO1);
    	model.addAttribute("message", egovMessageSource.getMessage("success.common.select"));

		if(sCmd.equals("updt")){
	    	eventManage.setEventId(          eventManageVO1.getEventId()           );
	    	eventManage.setEventSe(          eventManageVO1.getEventSe()           );
	    	eventManage.setEventNm(          eventManageVO1.getEventNm()           );
	    	eventManage.setEventPurps(       eventManageVO1.getEventPurps()        );
	    	eventManage.setEventBeginDe(     eventManageVO1.getEventBeginDe()      );
	    	eventManage.setEventEndDe(       eventManageVO1.getEventEndDe()        );
	    	eventManage.setEventAuspcInsttNm(eventManageVO1.getEventAuspcInsttNm() );
	    	eventManage.setEventMngtInsttNm( eventManageVO1.getEventMngtInsttNm()  );
	    	eventManage.setEventPlace(       eventManageVO1.getEventPlace()        );
	    	eventManage.setEventCn(          eventManageVO1.getEventCn()           );
	    	eventManage.setCtOccrrncAt(      eventManageVO1.getCtOccrrncAt()       );
	    	eventManage.setPartcptCt(        eventManageVO1.getPartcptCt()         );
	    	eventManage.setPsncpa(           eventManageVO1.getPsncpa()            );
	    	eventManage.setRefrnUrl(         eventManageVO1.getRefrnUrl()          );
	    	eventManage.setRceptBeginDe(     eventManageVO1.getRceptBeginDe()      );
	    	eventManage.setRceptEndDe(       eventManageVO1.getRceptEndDe()        );
	    	model.addAttribute("eventManage", eventManage);
			return "egovframework/com/uss/ion/evt/EgovEventReqstUpdt";
		}else if(sCmd.equals("popup")){
			model.addAttribute("check_popup", "Y");
			return "egovframework/com/uss/ion/evt/EgovEventReqstDetail";
		}else{
			return "egovframework/com/uss/ion/evt/EgovEventReqstDetail";
		}
	}

	/**
	 * 행사관리 등록 화면으로 이동한다.
	 * @return String - 리턴 Url
	 */
    @RequestMapping(value="/uss/ion/evt/EgovEventReqstRegist.do")
	 public String insertViewEventManage(  @ModelAttribute("eventManage") EventManageVO eventManage,
											 @ModelAttribute("eventManageVO") EventManageVO eventManageVO,
											 ModelMap model ) throws Exception {

    	ComDefaultCodeVO vo = new ComDefaultCodeVO();
		vo.setCodeId("COM053");
        List<?> eventSeCodeList = cmmUseService.selectCmmCodeDetail(vo);
        model.addAttribute("eventSeCode", eventSeCodeList);

    	return "egovframework/com/uss/ion/evt/EgovEventReqstRegist";
	}

	/**
	 * 행사관리정보를 신규로 등록한다.
	 * @param eventManage - 행사관리 model
	 * @return String - 리턴 Url
	 */
    @RequestMapping(value="/uss/ion/evt/insertEventManage.do")
	 public String insertEventManage(   @ModelAttribute("eventManage") EventManage eventManage,
			                            @ModelAttribute("eventManageVO") EventManageVO eventManageVO,
			                            BindingResult bindingResult,
			                            SessionStatus status,
						                ModelMap model) throws Exception {

    	beanValidator.validate(eventManage, bindingResult); //validation 수행

    	if (bindingResult.hasErrors()) {
    		model.addAttribute("eventManageVO", eventManageVO);
			return "egovframework/com/uss/ion/evt/EgovEventReqstRegist";
		} else {
	    	LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
	    	eventManage.setFrstRegisterId((user == null || user.getUniqId() == null) ? "" : user.getUniqId());
	    	status.setComplete();
	    	egovEventManageService.insertEventManage(eventManage);
	    	model.addAttribute("message", egovMessageSource.getMessage("success.common.insert"));
	    	return "forward:/uss/ion/evt/EgovEventReqstManageList.do";
    	}
	}

	/**
	 * 기 등록된 행사관리정보를 수정한다.
	 * @param eventManage - 행사관리 model
	 * @return String - 리턴 Url
	 */
	 @RequestMapping(value="/uss/ion/evt/EgovEventReqstSave.do")
	 public String updtEventManage(      @ModelAttribute("eventManage") EventManage eventManage,
										 @ModelAttribute("eventManageVO") EventManageVO eventManageVO,
							             BindingResult bindingResult,
			                             SessionStatus status,
		                                 ModelMap model) throws Exception {

		beanValidator.validate(eventManage, bindingResult); //validation 수행

    	if (bindingResult.hasErrors()) {
    		model.addAttribute("eventManageVO", eventManage);
			return "egovframework/com/uss/ion/evt/EgovEventReqstUpdt";
		} else {
	    	LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
	    	eventManage.setLastUpdusrId((user == null || user.getUniqId() == null) ? "" : user.getUniqId());
	    	status.setComplete();
	    	egovEventManageService.updtEventManage(eventManage);
	    	return "forward:/uss/ion/evt/EgovEventReqstManageList.do";
		}
	}

	/**
	 * 기 등록된 행사관리정보를 삭제한다.
	 * @param eventManage - 행사관리 model
	 * @return String - 리턴 Url
	 */
    @RequestMapping(value="/uss/ion/evt/EgovEventReqstDelete.do")
	 public String deleteEventManage(   @ModelAttribute("eventManage") EventManage eventManage,
			                             SessionStatus status,
			                             ModelMap model) throws Exception {

    	egovEventManageService.deleteEventManage(eventManage);
    	status.setComplete();
    	model.addAttribute("message", egovMessageSource.getMessage("success.common.delete"));
    	return "forward:/uss/ion/evt/EgovEventReqstManageList.do";
	}


    /** 행사접수관리  **/
	/**
	 * 행사접수관리정보를 관리하기 위해 등록된 행사접수관리 목록을 조회한다.
	 * @param eventManageVO - 행사관리 VO
	 * @return String - 리턴 Url
	 */
    @IncludedInfo(name="행사접수관리", order = 941 ,gid = 50)
    @RequestMapping(value="/uss/ion/evt/EgovEventRcrptManageList.do")
	 public String selectEventAtdrnList(@ModelAttribute("eventManageVO") EventManageVO eventManageVO,
			                            ModelMap model) throws Exception {

		//행사년월
    	java.util.Calendar cal = java.util.Calendar.getInstance();
    	String [] yearList = new String[5];
    	for(int x=0; x < 5 ; x++){
    		yearList[x] = Integer.toString(cal.get(java.util.Calendar.YEAR)-x);
    	}
		model.addAttribute("yearList", yearList);

        //행사구분
    	ComDefaultCodeVO vo = new ComDefaultCodeVO();
		vo.setCodeId("COM053");
        List<?> eventSeCodeList = cmmUseService.selectCmmCodeDetail(vo);
        model.addAttribute("eventSeCode", eventSeCodeList);

		/** paging */
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(eventManageVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(eventManageVO.getPageUnit());
		paginationInfo.setPageSize(eventManageVO.getPageSize());

		eventManageVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		eventManageVO.setLastIndex(paginationInfo.getLastRecordIndex());
		eventManageVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

		eventManageVO.setSearchKeyword(eventManageVO.getSearchYear()+eventManageVO.getSearchMonth());
    	LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
    	if (user == null) {
    		return "egovframework/com/uat/uia/EgovLoginUsr";
    	}

    	eventManageVO.setApplcntId(user.getUniqId());// 사용자UniqID
		eventManageVO.setEventManageList(egovEventManageService.selectEventAtdrnList(eventManageVO));
		model.addAttribute("eventManageList", eventManageVO.getEventManageList());

		int totCnt = egovEventManageService.selectEventAtdrnListTotCnt(eventManageVO);
		paginationInfo.setTotalRecordCount(totCnt);
		model.addAttribute("paginationInfo", paginationInfo);
		model.addAttribute("message", egovMessageSource.getMessage("success.common.select"));
		return "egovframework/com/uss/ion/evt/EgovEventRceptManageList";
	}

	/**
	 * 등록된 행사접수관리의 상세정보를 조회한다.
	 * @param eventManageVO - 행사관리 VO
	 * @return String - 리턴 Url
	 */
    @RequestMapping(value="/uss/ion/evt/EgovEventRcrptDetail.do")
	 public String selectEventAtdrn(    @ModelAttribute("eventAtdrn")    EventAtdrn eventAtdrn,
                                        @ModelAttribute("eventManageVO") EventManageVO eventManageVO,
			                            ModelMap model) throws Exception {
    	EventManageVO eventManageVO1 = egovEventManageService.selectEventAtdrn(eventManageVO);

    	eventAtdrn.setEventId(           eventManageVO1.getEventId()           );
    	eventAtdrn.setApplcntId(         eventManageVO1.getApplcntId()         );
    	eventManageVO1.setEventBeginDe(  EgovDateUtil.formatDate(eventManageVO1.getEventBeginDe(), "-"));
    	eventManageVO1.setEventEndDe(    EgovDateUtil.formatDate(eventManageVO1.getEventEndDe()  , "-"));
    	eventManageVO1.setRceptBeginDe(  EgovDateUtil.formatDate(eventManageVO1.getRceptBeginDe(), "-"));
    	eventManageVO1.setRceptEndDe(    EgovDateUtil.formatDate(eventManageVO1.getRceptEndDe()  , "-"));

    	model.addAttribute("eventAtdrn", eventAtdrn);
    	model.addAttribute("eventManageVO", eventManageVO1);
    	model.addAttribute("message", egovMessageSource.getMessage("success.common.select"));

    	return "egovframework/com/uss/ion/evt/EgovEventRceptDetail";
	}

	/**
	 * 행사접수관리 등록 화면으로 이동한다.
	 * @return String - 리턴 Url
	 */
    @RequestMapping(value="/uss/ion/evt/EgovEventRceptRegist.do")
	 public String insertViewEventAtdrn(
										 @ModelAttribute("eventAtdrn")    EventAtdrn    eventAtdrn,
										 @ModelAttribute("eventManageVO") EventManageVO eventManageVO,
										 SessionStatus status,
										 ModelMap model
										 ) throws Exception {
    	EventManageVO eventManageVO1 = egovEventManageService.selectEventManage(eventManageVO);
    	LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
    	status.setComplete();
    	eventAtdrn.setApplcntId((user == null || user.getUniqId() == null) ? "" : user.getUniqId());
    	eventAtdrn.setEventId(eventManageVO1.getEventId());
    	eventManageVO1.setEventBeginDe(  EgovDateUtil.formatDate(eventManageVO1.getEventBeginDe(), "-"));
    	eventManageVO1.setEventEndDe(    EgovDateUtil.formatDate(eventManageVO1.getEventEndDe()  , "-"));
    	eventManageVO1.setRceptBeginDe(  EgovDateUtil.formatDate(eventManageVO1.getRceptBeginDe(), "-"));
    	eventManageVO1.setRceptEndDe(    EgovDateUtil.formatDate(eventManageVO1.getRceptEndDe()  , "-"));
    	eventManageVO1.setEventTemp6((user == null || user.getName() == null) ? "" : user.getName());
    	eventManageVO1.setEventTemp7((user == null || user.getOrgnztNm() == null) ? "" : user.getOrgnztNm());

    	model.addAttribute("eventAtdrn",    eventAtdrn);
    	model.addAttribute("eventManageVO", eventManageVO1);
    	model.addAttribute("message", egovMessageSource.getMessage("success.common.select"));

    	return "egovframework/com/uss/ion/evt/EgovEventRceptRegist";
	}

	/**
	 * 행사접수관리정보를 신규로 등록한다.
	 * @param eventAtdrn - 행사참석자 model
	 * @return String - 리턴 Url
	 */
    @RequestMapping(value="/uss/ion/evt/insertEventAtdrn.do")
	 public String insertEventAtdrn(    @ModelAttribute("eventAtdrn") EventAtdrn eventAtdrn,
			                            @ModelAttribute("eventManageVO") EventManageVO eventManageVO,
			                            BindingResult bindingResult,
			                            SessionStatus status,
						                ModelMap model) throws Exception {

    	beanValidator.validate(eventAtdrn, bindingResult); //validation 수행

    	if (bindingResult.hasErrors()) {
    		model.addAttribute("eventManageVO", eventManageVO);
			return "forward:/uss/ion/evt/EgovEventRceptRegist.do";
		} else {
			EventManageVO eventManageVO_check = new EventManageVO();
			eventManageVO_check = egovEventManageService.selectEventManage(eventManageVO);
			if(eventManageVO_check.getPsncpa()> egovEventManageService.selectEventReqstAtdrnListTotCnt(eventManageVO))
			{
	    		java.util.Calendar cal = java.util.Calendar.getInstance();

	    		int iYear  = cal.get(java.util.Calendar.YEAR);
	    		int iMonth = cal.get(java.util.Calendar.MONTH);
	    		int iDate  = cal.get(java.util.Calendar.DATE);

	    		//검색 설정
	    		String sSearchDate = "";
				sSearchDate += Integer.toString(iYear);
				sSearchDate += Integer.toString(iMonth+1).length() == 1 ? "0" + Integer.toString(iMonth+1) : Integer.toString(iMonth+1);
				sSearchDate += Integer.toString(iDate);
				eventAtdrn.setReqstDe(sSearchDate);//신청일자

				LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
		    	eventAtdrn.setFrstRegisterId((user == null || user.getUniqId() == null) ? "" : user.getUniqId());
		    	status.setComplete();
		    	egovEventManageService.insertEventAtdrn(eventAtdrn);
		    	model.addAttribute("message", egovMessageSource.getMessage("success.common.insert"));

		    	return "forward:/uss/ion/evt/EgovEventRcrptManageList.do";
			}else{
	        	EventManageVO eventManageVO1 = egovEventManageService.selectEventManage(eventManageVO);
	        	LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
	        	status.setComplete();
	        	eventAtdrn.setApplcntId((user == null || user.getUniqId() == null) ? "" : user.getUniqId());
	        	eventAtdrn.setEventId(eventManageVO1.getEventId());
	        	eventManageVO1.setEventBeginDe(  EgovDateUtil.formatDate(eventManageVO1.getEventBeginDe(), "-"));
	        	eventManageVO1.setEventEndDe(    EgovDateUtil.formatDate(eventManageVO1.getEventEndDe()  , "-"));
	        	eventManageVO1.setRceptBeginDe(  EgovDateUtil.formatDate(eventManageVO1.getRceptBeginDe(), "-"));
	        	eventManageVO1.setRceptEndDe(    EgovDateUtil.formatDate(eventManageVO1.getRceptEndDe()  , "-"));
	        	eventManageVO1.setEventTemp6((user == null || user.getName() == null) ? "" : user.getName());
	        	eventManageVO1.setEventTemp7((user == null || user.getOrgnztNm() == null) ? "" : user.getOrgnztNm());

	        	model.addAttribute("eventAtdrn",    eventAtdrn);
	        	model.addAttribute("eventManageVO", eventManageVO1);
	        	model.addAttribute("errMessage", "정원초과");

	        	return "egovframework/com/uss/ion/evt/EgovEventRceptRegist";
			}
		}
	}

	/**
	 * 기 등록된 행사접수관리정보를 취소한다.
	 * @param eventManage - 행사관리 model
	 * @return String - 리턴 Url
	 */
    @RequestMapping(value="/uss/ion/evt/deleteEventAtdrn.do")
	 public String deleteEventAtdrn(    @ModelAttribute("eventAtdrn") EventAtdrn eventAtdrn,
			                             SessionStatus status,
			                             ModelMap model) throws Exception {

    	egovEventManageService.deleteEventAtdrn(eventAtdrn);
    	status.setComplete();
    	model.addAttribute("message", egovMessageSource.getMessage("success.common.delete"));
    	return "forward:/uss/ion/evt/EgovEventRcrptManageList.do";
	}


	/**
	 * 행사접수승인/반려 처리를 위해 등록된 행사접수 목록을 조회한다.
	 * @param eventManageVO - 행사관리 VO
	 * @return String - 리턴 Url
	 */
    @IncludedInfo(name="행사접수승인관리", order = 942 ,gid = 50)
    @RequestMapping(value="/uss/ion/evt/selectEventRceptConfmList.do")
	 public String selectEventRceptConfmList(@ModelAttribute("eventManageVO") EventManageVO eventManageVO,
			 								 @RequestParam Map<?, ?> commandMap,
			                                 ModelMap model) throws Exception {
		//행사년월
    	java.util.Calendar cal = java.util.Calendar.getInstance();
    	String [] yearList = new String[5];
    	for(int x=0; x < 5 ; x++){
    		yearList[x] = Integer.toString(cal.get(java.util.Calendar.YEAR)-x);
    	}
        //행사구분
    	ComDefaultCodeVO vo = new ComDefaultCodeVO();
		vo.setCodeId("COM053");
        List<?> eventSeCodeList = cmmUseService.selectCmmCodeDetail(vo);
        model.addAttribute("eventSeCode", eventSeCodeList);

		/** paging */
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(eventManageVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(eventManageVO.getPageUnit());
		paginationInfo.setPageSize(eventManageVO.getPageSize());

		eventManageVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		eventManageVO.setLastIndex(paginationInfo.getLastRecordIndex());
		eventManageVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

    	LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
    	if (user == null) {
    		return "egovframework/com/uat/uia/EgovLoginUsr";
    	}
    	
    	eventManageVO.setSanctnerId(user.getUniqId());// 승인권자UniqID

		eventManageVO.setSearchKeyword(eventManageVO.getSearchYear()+eventManageVO.getSearchMonth());

		int totCnt = egovEventManageService.selectEventRceptConfmListTotCnt(eventManageVO);
		eventManageVO.setEventManageList(egovEventManageService.selectEventRceptConfmList(eventManageVO));
		model.addAttribute("eventRceptConfmList", eventManageVO.getEventManageList());

		paginationInfo.setTotalRecordCount(totCnt);

		model.addAttribute("paginationInfo", paginationInfo);
		model.addAttribute("yearList", yearList);
		model.addAttribute("message", egovMessageSource.getMessage("success.common.select"));

		return "egovframework/com/uss/ion/evt/EgovEventRceptConfm";
	}

	/**
	 * 기 등록된 행사접수관리정보를 승인/반려처리한다.
	 * @param  eventAtdrn - 행사참석자 model
	 * @return String - 리턴 Url
	 */
	 @RequestMapping(value="/uss/ion/evt/updtEventAtdrn.do")
	 public String updtEventAtdrn(@RequestParam("checkedEventRceptForConfm") String checkedEventRceptForConfm,
			                            @ModelAttribute("eventAtdrn") EventAtdrn eventAtdrn,
			                            @RequestParam Map<?, ?> commandMap,
			                            SessionStatus status,
		                                ModelMap model) throws Exception {
    	String sCmd = commandMap.get("cmd")  == null ? "" : (String)commandMap.get("cmd"); // 상세정보 구분
    	LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
    	eventAtdrn.setConfmAt(sCmd);
    	eventAtdrn.setSanctnerId((user == null || user.getUniqId() == null) ? "" : user.getUniqId());
    	eventAtdrn.setFrstRegisterId((user == null || user.getUniqId() == null) ? "" : user.getUniqId());
    	egovEventManageService.updtEventAtdrn(eventAtdrn, checkedEventRceptForConfm);
    	return "forward:/uss/ion/evt/selectEventRceptConfmList.do";
	 }

		/**
		 * 행사접수자 정보 목록을 조회한다.
		 * @param eventManageVO - 행사관리 VO
		 * @return String - 리턴 Url
		 */
	 //@IncludedInfo(name="행사참가요청자목록", order = 942)
	 @RequestMapping(value="/uss/ion/evt/EgovEventReqstAtdrnList.do")
	 public String selectEventReqstAtdrnList(@ModelAttribute("eventManageVO") EventManageVO eventManageVO,
				                             ModelMap model) throws Exception {
		/** paging */
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(eventManageVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(eventManageVO.getPageUnit());
		paginationInfo.setPageSize(eventManageVO.getPageSize());

		eventManageVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		eventManageVO.setLastIndex(paginationInfo.getLastRecordIndex());
		eventManageVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

		eventManageVO.setEventManageList(egovEventManageService.selectEventReqstAtdrnList(eventManageVO));
		model.addAttribute("eventManageList", eventManageVO.getEventManageList());

		int totCnt = egovEventManageService.selectEventReqstAtdrnListTotCnt(eventManageVO);
		paginationInfo.setTotalRecordCount(totCnt);
		model.addAttribute("paginationInfo", paginationInfo);
		model.addAttribute("message", egovMessageSource.getMessage("success.common.select"));

		return "egovframework/com/uss/ion/evt/EgovEventReqstAtdrnList";
	}


}
