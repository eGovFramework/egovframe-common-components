package egovframework.com.uss.ion.ans.web;

import java.io.IOException;
import java.io.InputStream;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springmodules.validation.commons.DefaultBeanValidator;

import egovframework.com.cmm.ComDefaultCodeVO;
import egovframework.com.cmm.EgovMessageSource;
import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.annotation.IncludedInfo;
import egovframework.com.cmm.service.EgovCmmUseService;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.com.uss.ion.ans.service.AnnvrsryManage;
import egovframework.com.uss.ion.ans.service.AnnvrsryManageVO;
import egovframework.com.uss.ion.ans.service.EgovAnnvrsryManageService;
import egovframework.com.utl.fcc.service.EgovDateUtil;
import egovframework.com.utl.fcc.service.EgovStringUtil;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

/**
 * 개요
 * - 기념일관리에 대한 controller 클래스를 정의한다.
 *
 * 상세내용
 * - 기념일관리에 대한 등록, 수정, 삭제, 조회 기능을 제공한다.
 * - 기념일관리의 조회기능은 목록조회, 상세조회로 구분된다.
 * @author 이용
 * @version 1.0
 * @created 06-15-2010 오후 2:08:56
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *  수정일               수정자            수정내용
 *  ----------   --------   ---------------------------
 *  2009.06.25   이용               최초 생성
 *  2011.08.26   정진오            IncludedInfo annotation 추가
 *  2020.11.02   신용호            KISA 보안약점 조치 - 자원해제
 *
 *  </pre>
 */

@Controller
public class EgovAnnvrsryManageController {

	@Resource(name="egovMessageSource")
    EgovMessageSource egovMessageSource;

    @Resource(name = "egovAnnvrsryManageService")
    private EgovAnnvrsryManageService egovAnnvrsryManageService;

    @Autowired
	 private DefaultBeanValidator beanValidator;

	@Resource(name="EgovCmmUseService")
	private EgovCmmUseService cmmUseService;

    /**
	 * 기념일관리 목록화면 이동
	 * @return String
	 * @exception Exception
	 */
    @RequestMapping("/uss/ion/ans/selectAnnvrsryManageListView.do")
    public String selectAnnvrsryManageListView() throws Exception {

        return "egovframework/com/uss/ion/ans/EgovAnnvrsryManageList";
    }

	/**
	 * 기념일관리정보를 관리하기 위해 등록된 기념일관리 목록을 조회한다.
	 * @param annvrsryManageVO - 기념일관리 VO
	 * @return String - 리턴 Url
	 */
    @IncludedInfo(name="기념일관리", order = 930 ,gid = 50)
    @RequestMapping(value="/uss/ion/ans/selectAnnvrsryManageList.do")
	 public String selectAnnvrsryManageList( @ModelAttribute("annvrsryManageVO") AnnvrsryManageVO annvrsryManageVO,
											 @ModelAttribute("annvrsryManageVO") AnnvrsryManageVO annvrsryGdcc,
								              ModelMap model) throws Exception {

		java.util.Calendar cal = java.util.Calendar.getInstance();
    	String [] yearList = new String[5];
    	for(int x=0; x < 5 ; x++){
    		yearList[x] = Integer.toString(cal.get(java.util.Calendar.YEAR)+2-x);
    	}
    	if(annvrsryManageVO.getSearchKeyword()== null||annvrsryManageVO.getSearchKeyword().equals(""))  annvrsryManageVO.setSearchKeyword(Integer.toString(cal.get(java.util.Calendar.YEAR)));

		//로그인 객체 선언
		LoginVO loginVO = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
    	if (loginVO == null) {
    		return "egovframework/com/uat/uia/EgovLoginUsr";
    	}
		annvrsryManageVO.setUsid(loginVO.getUniqId());

		/** paging */
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(annvrsryManageVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(annvrsryManageVO.getPageUnit());
		paginationInfo.setPageSize(annvrsryManageVO.getPageSize());

		annvrsryManageVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		annvrsryManageVO.setLastIndex(paginationInfo.getLastRecordIndex());
		annvrsryManageVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
		annvrsryManageVO.setAnnvrsryManageList(egovAnnvrsryManageService.selectAnnvrsryManageList(annvrsryManageVO));
		model.addAttribute("annvrsryManageList", annvrsryManageVO.getAnnvrsryManageList());

		int totCnt = egovAnnvrsryManageService.selectAnnvrsryManageListTotCnt(annvrsryManageVO);
		paginationInfo.setTotalRecordCount(totCnt);
		//annvrsryGdcc.setAnnvrsryManageList(egovAnnvrsryManageService.selectAnnvrsryGdcc(annvrsryManageVO));
		//model.addAttribute("annvrsryGdccList", annvrsryGdcc.getAnnvrsryManageList());

		model.addAttribute("yearList", yearList);
		model.addAttribute("paginationInfo", paginationInfo);

		model.addAttribute("message", egovMessageSource.getMessage("success.common.select"));
		return "egovframework/com/uss/ion/ans/EgovAnnvrsryManageList";
	}

	/**
	 * 등록된 기념일관리의 상세정보를 조회한다.
	 * @param annvrsryManageVO - 기념일관리 VO
	 * @return String - 리턴 Url
	 */
    @RequestMapping(value="/uss/ion/ans/selectAnnvrsryManage.do")
	 public String selectAnnvrsryManage(@ModelAttribute("annvrsryManage") AnnvrsryManage annvrsryManage,
                                        @ModelAttribute("annvrsryManageVO") AnnvrsryManageVO annvrsryManageVO,
                                        @RequestParam Map<?, ?> commandMap,
			                            ModelMap model) throws Exception {

    	String sCmd = commandMap.get("cmd") == null ? "" : (String)commandMap.get("cmd"); // 상세정보 구분
    	String sTempAnnvrsryDe = null;
    	String sTempCldrSe     = null;
    	String sTempAnnvrsrySetup  = null;
    	AnnvrsryManageVO annvrsryManageVO_Temp = new AnnvrsryManageVO();
    	annvrsryManageVO_Temp = egovAnnvrsryManageService.selectAnnvrsryManage(annvrsryManageVO);

    	if("1".equals(annvrsryManageVO_Temp.getCldrSe()))  sTempCldrSe= egovMessageSource.getMessage("comUssIonAns.annvrsryGdcc.cldrSe1");//양
    	else sTempCldrSe= egovMessageSource.getMessage("comUssIonAns.annvrsryGdcc.cldrSe2");//음
    	sTempAnnvrsryDe = annvrsryManageVO_Temp.getAnnvrsryDe()+"("+sTempCldrSe+")";
    	annvrsryManageVO_Temp.setAnnvrsryTemp4(sTempAnnvrsryDe);

    	if("Y".equals(annvrsryManageVO_Temp.getAnnvrsrySetup()))  sTempAnnvrsrySetup="ON";
    	else sTempAnnvrsrySetup="OFF";
    	annvrsryManageVO_Temp.setAnnvrsryTemp5(sTempAnnvrsrySetup);

    	model.addAttribute("annvrsryManageVO", annvrsryManageVO_Temp);
    	model.addAttribute("message", egovMessageSource.getMessage("success.common.select"));


		if(sCmd.equals("update")){

			annvrsryManage.setAnnId(annvrsryManageVO_Temp.getAnnId());
			annvrsryManage.setAnnvrsryNm(annvrsryManageVO_Temp.getAnnvrsryNm());
			annvrsryManage.setAnnvrsryDe(annvrsryManageVO_Temp.getAnnvrsryDe());
			annvrsryManage.setCldrSe(annvrsryManageVO_Temp.getCldrSe());
			annvrsryManage.setUsid(annvrsryManageVO_Temp.getUsid());
			annvrsryManage.setAnnvrsrySe(annvrsryManageVO_Temp.getAnnvrsrySe());

	    	ComDefaultCodeVO vo = new ComDefaultCodeVO();
			vo.setCodeId("COM069");
	        List<?> annvrsrySeCodeList = cmmUseService.selectCmmCodeDetail(vo);
	        model.addAttribute("annvrsrySeCode", annvrsrySeCodeList);
			model.addAttribute("annvrsryManage", annvrsryManage);
			return "egovframework/com/uss/ion/ans/EgovAnnvrsryUpdt";
		}else{
			return "egovframework/com/uss/ion/ans/EgovAnnvrsryDetail";
		}
	}

	/**
	 * 기념일관리 등록 화면으로 이동한다.
	 * @return String - 리턴 Url
	 */
    @RequestMapping(value="/uss/ion/ans/insertViewAnnvrsry.do")
	 public String insertViewAnnvrsryManage(@ModelAttribute("annvrsryManage") AnnvrsryManage annvrsryManage,
				                            @ModelAttribute("annvrsryManageVO") AnnvrsryManageVO annvrsryManageVO,
				                            ModelMap model ) throws Exception {
		//로그인 객체 선언
		LoginVO loginVO = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();

		annvrsryManage.setUsid(loginVO == null ? "" : EgovStringUtil.isNullToString(loginVO.getUniqId()));
		annvrsryManage.setAnnvrsrySetup("Y");
		annvrsryManage.setCldrSe("1");  // 1:양력  2:음력
		annvrsryManageVO.setUsid(loginVO == null ? "" : EgovStringUtil.isNullToString(loginVO.getUniqId()));		           // 사용자ID
		annvrsryManageVO.setAnnvrsryTemp1(loginVO == null ? "" : EgovStringUtil.isNullToString(loginVO.getName()));          // 사용자명
		annvrsryManageVO.setAnnvrsryTemp2(loginVO == null ? "" : EgovStringUtil.isNullToString(loginVO.getOrgnztNm()));      // 조직 ID

    	ComDefaultCodeVO vo = new ComDefaultCodeVO();
		vo.setCodeId("COM069");
        List<?> annvrsrySeCodeList = cmmUseService.selectCmmCodeDetail(vo);
        model.addAttribute("annvrsrySeCode", annvrsrySeCodeList);
        model.addAttribute("annvrsryManage", annvrsryManage);
        model.addAttribute("annvrsryManageVO", annvrsryManageVO);
     	return "egovframework/com/uss/ion/ans/EgovAnnvrsryRegist";
	}

	/**
	 * 기념일관리정보를 신규로 등록한다.
	 * @param annvrsryManage - 기념일관리 model
	 * @return String - 리턴 Url
	 */
    @RequestMapping(value="/uss/ion/ans/insertAnnvrsry.do")
	 public String insertAnnvrsryManage(@ModelAttribute("annvrsryManage") AnnvrsryManage annvrsryManage,
			                            @ModelAttribute("annvrsryManageVO") AnnvrsryManageVO annvrsryManageVO,
			                            BindingResult bindingResult,
			                            SessionStatus status,
						                ModelMap model) throws Exception {

    	beanValidator.validate(annvrsryManage, bindingResult); //validation 수행

    	if (bindingResult.hasErrors()) {
        	ComDefaultCodeVO vo = new ComDefaultCodeVO();
    		vo.setCodeId("COM069");
            List<?> annvrsrySeCodeList = cmmUseService.selectCmmCodeDetail(vo);
            model.addAttribute("annvrsrySeCode", annvrsrySeCodeList);

    		model.addAttribute("annvrsryManageVO", annvrsryManageVO);
    		model.addAttribute("message", egovMessageSource.getMessage("fail.common.insert"));
         	return "egovframework/com/uss/ion/ans/EgovAnnvrsryRegist";
		} else {

	    	LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
	    	status.setComplete();
	    	model.addAttribute("message", egovMessageSource.getMessage("success.common.insert"));
	    	annvrsryManage.setFrstRegisterId((user == null || user.getUniqId() == null) ? "" : user.getUniqId());

	    	if(egovAnnvrsryManageService.selectAnnvrsryManageDplctAt(annvrsryManage)==0){
	    		egovAnnvrsryManageService.insertAnnvrsryManage(annvrsryManage);
		    	model.addAttribute("message", egovMessageSource.getMessage("success.common.insert"));
		    	return "forward:/uss/ion/ans/selectAnnvrsryManageList.do";
	    	}else{
	        	ComDefaultCodeVO vo = new ComDefaultCodeVO();
	    		vo.setCodeId("COM069");
	            List<?> annvrsrySeCodeList = cmmUseService.selectCmmCodeDetail(vo);
	            annvrsryManageVO.setAnnvrsryTemp1(user == null ? "" : EgovStringUtil.isNullToString(user.getName()));
	            annvrsryManageVO.setAnnvrsryTemp2(user == null ? "" : EgovStringUtil.isNullToString(user.getOrgnztNm()));
	            model.addAttribute("annvrsrySeCode", annvrsrySeCodeList);
	            model.addAttribute("annvrsryManageVO", annvrsryManageVO);
		    	model.addAttribute("dplctMessage", egovMessageSource.getMessage("comUssIonAns.common.duplicate"));//이미 등록된 데이타입니다. 해당 데이타를 확인해 주세요"); 
		     	return "egovframework/com/uss/ion/ans/EgovAnnvrsryRegist";
	    	}
		}
	}

	/**
	 * 기 등록된 기념일관리정보를 수정한다.
	 * @param annvrsryManage - 기념일관리 model
	 * @return String - 리턴 Url
	 */
	 @RequestMapping(value="/uss/ion/ans/updateAnnvrsryManage.do")
	 public String updateAnnvrsryManage(@ModelAttribute("annvrsryManage") AnnvrsryManage annvrsryManage,
										@ModelAttribute("annvrsryManageVO") AnnvrsryManageVO annvrsryManageVO,
							            BindingResult bindingResult,
			                            SessionStatus status,
		                                ModelMap model) throws Exception {

		beanValidator.validate(annvrsryManage, bindingResult); //validation 수행

    	if (bindingResult.hasErrors()) {
    		model.addAttribute("annvrsryManageVO", annvrsryManage);
			return "egovframework/com/uss/ion/ans/EgovAnnvrsryManageUpdt";
		} else {

	    	LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
	    	status.setComplete();
	    	annvrsryManage.setLastUpdusrId((user == null || user.getUniqId() == null) ? "" : user.getUniqId());

	    	if(egovAnnvrsryManageService.selectAnnvrsryManageDplctAt(annvrsryManage)==0){
	    		egovAnnvrsryManageService.updateAnnvrsryManage(annvrsryManage);
		    	model.addAttribute("message", egovMessageSource.getMessage("success.common.update"));
		    	return "forward:/uss/ion/ans/selectAnnvrsryManageList.do";
	    	}else{
	        	ComDefaultCodeVO vo = new ComDefaultCodeVO();
	    		vo.setCodeId("COM069");
	            List<?> annvrsrySeCodeList = cmmUseService.selectCmmCodeDetail(vo);
	            annvrsryManageVO.setAnnvrsryTemp1(user == null ? "" : EgovStringUtil.isNullToString(user.getName()));
	            annvrsryManageVO.setAnnvrsryTemp2(user == null ? "" : EgovStringUtil.isNullToString(user.getOrgnztNm()));
	            model.addAttribute("annvrsrySeCode", annvrsrySeCodeList);
	            model.addAttribute("annvrsryManageVO", annvrsryManageVO);
		    	model.addAttribute("dplctMessage", egovMessageSource.getMessage("comUssIonAns.common.duplicate"));//이미 등록된 데이타입니다. 해당 데이타를 확인해 주세요"); 
		     	return "egovframework/com/uss/ion/ans/EgovAnnvrsryUpdt";
	    	}
		}
	}

	/**
	 * 기 등록된 기념일관리정보를 삭제한다.
	 * @param annvrsryManage - 기념일관리 model
	 * @return String - 리턴 Url
	 */
    @RequestMapping(value="/uss/ion/ans/deleteAnnvrsryManage.do")
	 public String deleteAnnvrsryManage(@ModelAttribute("annvrsryManage") AnnvrsryManage annvrsryManage,
			                             SessionStatus status,
			                             ModelMap model) throws Exception {

    	egovAnnvrsryManageService.deleteAnnvrsryManage(annvrsryManage);
    	status.setComplete();
    	model.addAttribute("message", egovMessageSource.getMessage("success.common.delete"));
    	return "forward:/uss/ion/ans/selectAnnvrsryManageList.do";
	}

	/**
	 * Main화면에서 알림설정에 다른 기념일관리 목록을 조회한다.
	 * @param annvrsryManageVO - 기념일관리 VO
	 * @return String - 리턴 Url
	 */
    @IncludedInfo(name="기념일목록(확인용)", order = 931 ,gid = 50)
    @RequestMapping(value="/uss/ion/ans/selectAnnvrsryMainList.do")
	 public String selectAnnvrsryMainList( @ModelAttribute("annvrsryManageVO") AnnvrsryManageVO annvrsryManageVO,
										   @ModelAttribute("annvrsryManageVO") AnnvrsryManageVO annvrsryGdcc,
								           ModelMap model) throws Exception {

		//로그인 객체 선언
		LoginVO loginVO = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
    	if (loginVO == null) {
    		return "egovframework/com/uat/uia/EgovLoginUsr";
    	}
		annvrsryManageVO.setUsid(loginVO.getUniqId());

		/** paging */
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(annvrsryManageVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(annvrsryManageVO.getPageUnit());
		paginationInfo.setPageSize(annvrsryManageVO.getPageSize());

		annvrsryManageVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		annvrsryManageVO.setLastIndex(paginationInfo.getLastRecordIndex());
		annvrsryManageVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

		annvrsryManageVO.setAnnvrsryManageList(egovAnnvrsryManageService.selectAnnvrsryGdcc(annvrsryManageVO));
		model.addAttribute("annvrsryGdccList", annvrsryManageVO.getAnnvrsryManageList());

		int totCnt = egovAnnvrsryManageService.selectAnnvrsryManageListTotCnt(annvrsryManageVO);
		paginationInfo.setTotalRecordCount(totCnt);
		model.addAttribute("paginationInfo", paginationInfo);

		model.addAttribute("message", egovMessageSource.getMessage("success.common.select"));
		return "egovframework/com/uss/ion/ans/EgovAnnvrsryMainList";
	}


	/**
	 * 등록된 기념일관리의 알림 화면을 조회한다.
	 * @param annvrsryManageVO - 기념일관리 VO
	 * @return String - 리턴 Url
	 */
    @RequestMapping(value="/uss/ion/ans/selectAnnvrsryGdcc.do")
	 public String selectAnnvrsryGdcc(@ModelAttribute("annvrsryManageVO") AnnvrsryManageVO annvrsryManageVO,
			                            ModelMap model) throws Exception {
    	String sTempAnnvrsryDe      = null;
    	String sTempCldrSe          = null;
    	String sTempAnnvrsrySetup   = null;
    	String sAnnvrsryDe          = null;
    	AnnvrsryManageVO annvrsryManageVO_Temp = new AnnvrsryManageVO();
/*
    	String sAnnvrsryDe_Temp     = null;

    	sAnnvrsryDe_Temp = EgovStringUtil.removeMinusChar(annvrsryManageVO.getAnnvrsryDe());
    	if("0".equals(annvrsryManageVO.getCldrSe())){  // 음력인 경우 양력으로 환산
    		sAnnvrsryDe_Temp = EgovDateUtil.toSolar(sAnnvrsryDe_Temp, 0);
    		annvrsryManageVO.setAnnvrsryDe(sAnnvrsryDe_Temp);
    	}
*/
    	annvrsryManageVO_Temp = egovAnnvrsryManageService.selectAnnvrsryManage(annvrsryManageVO);
    	sAnnvrsryDe = EgovStringUtil.removeMinusChar(annvrsryManageVO_Temp.getAnnvrsryDe());
    	if("1".equals(annvrsryManageVO_Temp.getCldrSe())){  sTempCldrSe= egovMessageSource.getMessage("comUssIonAns.annvrsryGdcc.cldrSe1");//양
    	}else{
    		sTempCldrSe= egovMessageSource.getMessage("comUssIonAns.annvrsryGdcc.cldrSe2");//음
    		sAnnvrsryDe = EgovDateUtil.toSolar(sAnnvrsryDe, 0);
    	}

    	sTempAnnvrsryDe = annvrsryManageVO_Temp.getAnnvrsryDe()+"("+sTempCldrSe+")";
    	annvrsryManageVO_Temp.setAnnvrsryTemp4(sTempAnnvrsryDe);

    	if("Y".equals(annvrsryManageVO_Temp.getAnnvrsrySetup()))  sTempAnnvrsrySetup="ON";
    	else sTempAnnvrsrySetup="OFF";
    	annvrsryManageVO_Temp.setAnnvrsryTemp5(sTempAnnvrsrySetup);

        /* 날짜 사이의 기간 산출 */
    	long resultDay = 0;
    	Calendar to_day = Calendar.getInstance(); //Calendar객체를 생성합니다.
    	Calendar target_day = Calendar.getInstance();

    	if(sAnnvrsryDe!=null && !sAnnvrsryDe.equals("")){
    		target_day.set(Integer.parseInt(sAnnvrsryDe.substring(0,4)),Integer.parseInt(sAnnvrsryDe.substring(4,6))-1,Integer.parseInt(sAnnvrsryDe.substring(6,8)));
    	}else{
    		target_day.set(to_day.get(Calendar.YEAR),to_day.get(Calendar.MONTH)+1,to_day.get(Calendar.DATE));
    	}

    	long resultTime = target_day.getTime().getTime() - to_day.getTime().getTime(); // 차이 구하기
    	if(resultTime>0){
    		resultDay = resultTime /(1000*60*60*24);// 일로 바꾸기
    	}
    	else resultDay = 0;

    	annvrsryManageVO_Temp.setAnnvrsryBeginDe(Long.toString(resultDay));

    	model.addAttribute("annvrsryManageVO", annvrsryManageVO_Temp);
    	model.addAttribute("message", egovMessageSource.getMessage("success.common.select"));

    	return "egovframework/com/uss/ion/ans/EgovAnnvrsryGdcc";
	}




    /**
     * 기념일일괄등록화면 호출 및  기념일일괄등록처리 프로세스
     * @param annvrsryManageVO  AnnvrsryManageVO
     * @param request       HttpServletRequest
     * @return 출력페이지정보 "ion/bnt/EgovBndtManageListPop"
     * @exception Exception
     */
    @RequestMapping(value="/uss/ion/ans/EgovAnnvrsryManageListPop.do")
    public String selectAnnvrsryManageBnde( final HttpServletRequest request,
							    		@ModelAttribute("annvrsryManageVO") AnnvrsryManageVO annvrsryManageVO,
							    		@RequestParam Map<?, ?> commandMap,
							    		BindingResult bindingResult,
							    		ModelMap model) throws Exception {
        String resultMsg = "";
    	String sCmd = commandMap.get("cmd") == null ? "" : (String)commandMap.get("cmd"); // 상세정보 구분

        // 0. Spring Security 사용자권한 처리

    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
    		model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
        	return "egovframework/com/uat/uia/EgovLoginUsr";
    	}

    	return "egovframework/com/uss/ion/ans/EgovAnnvrsryManageBndeListPop";
    }

    @RequestMapping(value="/uss/ion/ans/EgovAnnvrsryManageListPopAction.do")
    public String selectAnnvrsryManageBndeAction( final MultipartHttpServletRequest multiRequest,
							    		@ModelAttribute("annvrsryManageVO") AnnvrsryManageVO annvrsryManageVO,
							    		@RequestParam Map<?, ?> commandMap,
							    		ModelMap model) throws Exception {
        String resultMsg = "";
    	String sCmd = commandMap.get("cmd") == null ? "" : (String)commandMap.get("cmd"); // 상세정보 구분

        // 0. Spring Security 사용자권한 처리

    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
    		model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
        	return "egovframework/com/uat/uia/EgovLoginUsr";
    	}

		if(sCmd.equals("bnde")){
	    	//final MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
			final Map<String, MultipartFile> files = multiRequest.getFileMap();
			Iterator<Entry<String, MultipartFile>> itr = files.entrySet().iterator();
			MultipartFile file;
			while (itr.hasNext()) {
				Entry<String, MultipartFile> entry = itr.next();
				file = entry.getValue();
				if (!"".equals(file.getOriginalFilename())) {
					// KISA 보안약점 조치 - 자원해제
					InputStream is = null;
					try {
						is = file.getInputStream();
						model.addAttribute("annvrsryManageList", egovAnnvrsryManageService.selectAnnvrsryManageBnde(is));
					} catch (IOException e) {
						throw new IOException(e);
					} finally {
						is.close();
					}
				}else{
					resultMsg = egovMessageSource.getMessage("fail.common.msg");
				}
			}
	    	model.addAttribute("resultMsg", resultMsg);
		}
    	return "egovframework/com/uss/ion/ans/EgovAnnvrsryManageBndeListPop";
    }
    
	/**
	 * 기념일정보를 일괄등록처리한다.
	 * @param annvrsryManageVO     - 기념일관리 VO
	 * @param String               - 기념일정보
	 * @return String              - 리턴 Url
	 */
    @RequestMapping(value="/uss/ion/ans/insertAnnvrsryManageBnde.do")
	 public String insertAnnvrsryManageBnde(@RequestParam("checkedAnnvrsryManageForInsert") String checkedAnnvrsryManageForInsert ,
			                            @ModelAttribute("annvrsryManageVO") AnnvrsryManageVO annvrsryManageVO,
			                            SessionStatus status,
						                ModelMap model) throws Exception {
    	    //int iTemp = egovAnnvrsryManageService.selectAnnvrsryManageMonthCnt(annvrsryManageVO);
    	   // if(iTemp == 0 ){

	    	    LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();

	    	    annvrsryManageVO.setFrstRegisterId((user == null || user.getUniqId() == null) ? "" : user.getUniqId());
	    	    egovAnnvrsryManageService.insertAnnvrsryManageBnde(annvrsryManageVO, checkedAnnvrsryManageForInsert);
		    	status.setComplete();
		    	model.addAttribute("message", "true");
		    	return "egovframework/com/uss/ion/ans/EgovAnnvrsryManageBndeListPop";
    	  //  }else{
    	    	//String sTempMessage = annvrsryManageVO.getBndtDe().substring(0,4)+"년"+bndtManageVO.getBndtDe().substring(4,6)+"월 데이타가 존재합니다.";
    	    	//model.addAttribute("message", sTempMessage);
		   // 	return "egovframework/com/uss/ion/bnt/EgovBndtManageBndeListPop";
    	   // }
	}
}